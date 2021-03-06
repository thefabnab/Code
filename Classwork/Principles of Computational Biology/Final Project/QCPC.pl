#!/bin/env perl
use strict;
use Wx;

#use PAR::Kludge;

my $wx = QCPC::MainWindow->new();
$wx->MainLoop;

package QCPC::MainWindow;
use strict;
use base qw(Wx::App);

sub OnInit {
	my $self = shift;
	my $frame =
	  QCPC::Frame->new( undef, -1, "QCPC Application", [ 1, 1 ], [ 400, 400 ] );
	$self->SetTopWindow($frame);
	$frame->Show(1);
}
1;

package QCPC::Frame;
use strict;
use File::Find;
use Math::Complex;
use File::Basename;
use Excel::Writer::XLSX;
use Excel::Writer::XLSX::Utility;
use List::Util qw(max min);

use base qw(Wx::Frame);

use Wx::Event qw( EVT_BUTTON );

my @control_list;
my @desired_list;

my @gprs;
my @graphs;    # holds all of the various graphs created for the excel file for
               # output at the end of the run
my %gprs_stats_cache
  ;            # cache the calculated means and stddev for each of the gpr files
my %peptide_row;    # holds the rows for peptides that are output
my ( $row, $col ) = ( 0, 0 );

my @mean;
my ( $datasheet, $graph );
my @peptides;

sub new {
	my $class = shift;
	my $self  = $class->SUPER::new(@_);    # call the superclass' constructor
	     # Then define a Panel to put the button on
	my $panel = Wx::Panel->new(
		$self,    # parent
		-1        # id
	);
	my ( $gpr_dir_button, $xls_button, $run_button ) = ( 1 .. 10 );
	$self->{default_columns} =
	  [ "name", "id", "f532 median", "f532 mean", "b532 median", "b532 mean" ];

	$self->{list} = Wx::ListBox->new(
		$panel, -1,
		[ 0,   180 ],
		[ 240, 100 ],
		[], &Wx::wxLB_EXTENDED
	);
	@{ $self->{filter} } = (
		Wx::RadioButton->new(
			$panel, -1,
			"Mean", [ 250, 60 ],
			&Wx::wxDefaultSize, &Wx::wxRB_GROUP
		),

		Wx::RadioButton->new(
			$panel, -1, "Median", [ 250, 80 ],
			&Wx::wxDefaultSize
		)
	);

	$self->{gpr} = Wx::Button->new(
		$panel,             # parent
		$gpr_dir_button,    # ButtonID
		"GPR Folder",       # label
		[ 0, 300 ]          # position
	);
	EVT_BUTTON(
		$self,              # Object to bind to
		$gpr_dir_button,    # ButtonID
		\&GPRClicked        # Subroutine to execute
	);
	$self->{xls} = Wx::Button->new(
		$panel,             # parent
		$xls_button,        # ButtonID
		"XLS File",         # label
		[ 80, 300 ]         # position
	);
	EVT_BUTTON(
		$self,              # Object to bind to
		$xls_button,        # ButtonID
		\&XLSClicked        # Subroutine to execute
	);
	$self->{run} = Wx::Button->new(
		$panel,             # parent
		$run_button,        # ButtonID
		"Run Analysis",     # label
		[ 160, 300 ]        # position
	);
	EVT_BUTTON(
		$self,              # Object to bind to
		$run_button,        # ButtonID
		\&RunClicked        # Subroutine to execute
	);

	Wx::StaticText->new(
		$panel,             # parent
		1,                  # id
		"Controls",         # label
		[ 0, 3 ]            # position
	);

	$self->{controls} = Wx::TextCtrl->new(
		$panel, -1,
		"fiducial, empty, blank, negative, positive, landing_lights",
		[ 80,  0 ],
		[ 160, 20 ],
	);

	Wx::StaticText->new(
		$panel,              # parent
		1,                   # id
		"Columns to Use",    # label
		[ 0, 60 ]            # position
	);

	$self->{columns} = Wx::ListBox->new(
		$panel, -1,
		[ 80,  60 ],
		[ 160, 100 ],
		[], &Wx::wxLB_EXTENDED
	);
	$self->{columns}->Set( $self->{default_columns} );
	foreach ( 0 .. scalar @{ $self->{default_columns} } ) {
		$self->{columns}->SetSelection($_);
	}
	return $self;
}

=head1 C<file_peptide_stats>

file_peptide_stats uses the cached gpr data held in @gprs to calculate the
mean, standard deviation and cv of each array for the non-controls.

	file_peptide_stats()
	
=cut

sub file_peptide_stats {
	my ($self) = @_;
	foreach my $gpr (@gprs) {
		my @data = ( $gpr->{"file"}, "Signal" );
		foreach my $signal (@mean) {
			my %data;
			my @signals;
			my $mean;
			my $stddev;
			foreach my $key ( keys %{ $gpr->{"peptides"} } ) {
				$mean += $gpr->{"peptides"}->{$key}->{$signal};
				push @signals, $gpr->{"peptides"}->{$key}->{$signal};
			}
			$mean /= scalar( keys %{ $gpr->{"peptides"} } );
			push @data, "$mean";

			foreach my $key ( keys %{ $gpr->{"peptides"} } ) {
				$stddev += ( $gpr->{"peptides"}->{$key}->{$signal} - $mean )**2;
			}
			$stddev = sqrt( $stddev / scalar( keys %{ $gpr->{"peptides"} } ) );
			push @data, "$stddev";
			my $cv = $stddev / $mean;
			push @data, "$cv";

			# min and max of the peptides
			my ($min) = sort { $a <=> $b } @signals;
			my ($max) = sort { $b <=> $a } @signals;
			push @data, ( "$min", "$max" );

			$data{"mean"}                                       = $mean;
			$data{"stddev"}                                     = $stddev;
			$gprs_stats_cache{ $gpr->{"file"} . "-" . $signal } = \%data;
		}
		$self->{worksheet}->write( $row, $col, \@data );
		$row++;
	}
}

=head1 C<file_control_stats>
file_control_stats prints out the mean, standard deviation and cv for the 
different controls found in the gpr files

	file_control_stats()	

=cut

sub file_control_stats {
	my ($self) = @_;
	foreach my $gpr (@gprs) {
		foreach my $control ( keys %{ $gpr->{"controls"} } ) {
			my @data = ( $gpr->{"file"}, "$control" );
			foreach my $signal (@mean) {
				my $mean;
				my $stddev;
				foreach my $key ( @{ $gpr->{"controls"}->{$control} } ) {
					$mean += $key->{$signal};
				}
				$mean /= scalar( @{ $gpr->{"controls"}->{$control} } );
				push @data, "$mean";

				foreach my $key ( @{ $gpr->{"controls"}->{$control} } ) {
					$stddev += ( $key->{$signal} - $mean )**2;
				}
				$stddev = sqrt(
					$stddev / scalar( @{ $gpr->{"controls"}->{$control} } ) );
				push @data, "$stddev";
				my $cv = $stddev / $mean;
				push @data, "$cv";

				# min and max of the controls
				my ($min) =
				  sort { $a->{$signal} <=> $b->{$signal} }
				  @{ $gpr->{"controls"}->{$control} };
				my ($max) =
				  sort { $b->{$signal} <=> $a->{$signal} }
				  @{ $gpr->{"controls"}->{$control} };
				push @data, ( $min->{$signal}, $max->{$signal} );
			}
			$self->{worksheet}->write( $row, $col, \@data );
			$row++;
		}
	}
}

=head1 C<file_total_stats>

file_total_stats uses the cached gpr data held in @gprs to calculate the total
inter-array mean, standard deviation and cv for all the peptides and all the 
controls separately.

	file_total_stats()
	
=cut

sub file_total_stats {
	my ($self) = @_;
	my @controls;    # the controls that were actually found for the first file
	                 #(and so for each file after I hope)
	$col = 1;
	foreach my $signal (@mean) {
		$row = 1;
		foreach my $control (@control_list) {
			my $mean;
			my $stddev;
			my $count;
			next
			  if ( !defined( $gprs[0]->{"controls"}->{$control} ) );
			push @controls, $control;

			foreach my $gpr (@gprs) {
				foreach my $key ( @{ $gpr->{"controls"}->{$control} } ) {
					$mean += $key->{$signal};
					$count++;
				}
			}
			next if ( !$count );
			$mean /= $count;

			foreach my $gpr (@gprs) {
				foreach my $key ( @{ $gpr->{"controls"}->{$control} } ) {
					$stddev += ( $key->{$signal} - $mean )**2;
				}
				$stddev = sqrt( $stddev / $count );
			}
			my $cv = $stddev / $mean;
			$self->{worksheet}
			  ->write( $row++, $col, [ "$mean", "$stddev", "$cv" ] );
		}

		# found the mean for the various control types. Now do the same for all
		# the peptides
		my ( $mean, $stddev, $cv, $count );

		foreach my $gpr (@gprs) {
			foreach my $key ( keys %{ $gpr->{"peptides"} } ) {
				$mean += $gpr->{"peptides"}->{$key}->{$signal};
				$count++;
			}
		}
		next if ( !$count );
		$mean /= $count;

		foreach my $gpr (@gprs) {
			foreach my $key ( keys %{ $gpr->{"peptides"} } ) {
				$stddev += ( $gpr->{"peptides"}->{$key}->{$signal} - $mean )**2;
			}
			$stddev = sqrt( $stddev / $count );
		}
		$cv = $stddev / $mean;
		$self->{worksheet}
		  ->write( $row++, $col, [ "$mean", "$stddev", "$cv" ] );
		$col += 3;
	}
	$col = 0;
	$row = 1;
	foreach ( unique(@controls) ) {
		$self->{worksheet}->write( $row++, $col, $_ );
	}
	$self->{worksheet}->write( $row, $col, "Signal" );
}

=head1 C<col_to_ref>

Converts A1 excel notation to $A$1

	col_to_ref($col)
	
=cut

sub col_to_ref {
	my ($col) = @_;
	if ( $col =~ /([a-z]+)([\d]+)/i ) { $col = '$' . $1 . '$' . $2; }
	return $col;
}

sub log_base {
	my ( $base, $value ) = @_;
	if ( $value > 0 && $base > 0 ) {
		return log($value) / log($base);
	}
	else { return 0; }
}

sub GPRClicked {
	my ( $self, $event ) = @_;
	$self->{dir} = Wx::DirSelector("Select GPR Directory");
	if ( -e $self->{dir} && -d $self->{dir} ) {
		opendir my $dh, $self->{dir}
		  || die "can't opendir " . $self->{dir} . " $!";
		my @files = grep { /\.gpr$/ && -f $self->{dir} . "/$_" } readdir($dh);
		$self->{list}->Set( \@files );
		open my $fh, $self->{dir} . "/" . $files[0];
		while ( my $line = <$fh> ) {
			if ( $line =~ /^"?block"?/i ) {
				chomp($line);
				$line =~ s/["']//g;

				my @current_selections = $self->{columns}->GetSelections();
				foreach ( 0 .. $#current_selections ) {
					$current_selections[$_] =
					  $self->{columns}->GetString( $current_selections[$_] );
				}

				#split the line on tabs for mapping
				my @temp = split( /\t/, lc($line) );
				$self->{columns}->Set( \@temp );
				foreach ( 0 .. $#temp ) {
					if ( inlist( $temp[$_], @current_selections ) ) {
						$self->{columns}->SetSelection($_);
					}
				}
				last;
			}
		}

	}
}

sub XLSClicked {
	my ( $self, $event ) = @_;
	( $self->{xls} ) = Wx::FileSelector("Select XLSX File for writing");
	if ( $self->{xls} !~ /\.xlsx$/i ) {
		$self->{xls} =~ s/\.[a-z]{2,4}$//i;
		$self->{xls} .= '.xlsx';
	}
}

sub RunClicked {
	my ( $self, $event ) = @_;
	@control_list = @desired_list = @gprs = @graphs = @peptides = ();

	%gprs_stats_cache = %peptide_row = {};
	$row              = 0;
	$col              = 0;

	if ( $self->{dir} eq "" ) {
		Wx::MessageBox( "No Directory was Selected", "ERROR!!!", "", $self );
		return;
	}
	if ( ref( $self->{xls} ) ne "" || $self->{xls} eq "" ) {
		Wx::MessageBox( "No Excel File was Selected", "ERROR!!!", "", $self );
		return;
	}
	@control_list = split( /\s?+,\s?+/, $self->{controls}->GetValue );
	@desired_list = $self->{columns}->GetSelections();

	foreach ( 0 .. $#desired_list ) {
		$desired_list[$_] = $self->{columns}->GetString( $desired_list[$_] );
	}

	my $filter = "median";
	foreach my $rb ( @{ $self->{filter} } ) {
		if ( $rb->GetValue() ) {
			$filter = $rb->GetLabel();
		}
	}

	my $search = '^[fb]\d{1,3}.*' . lc($filter) . '$';

	@mean = grep( /$search/, @desired_list );

	my $EXCEL = Excel::Writer::XLSX->new( $self->{xls} );

	my @files = $self->{list}->GetSelections();
	foreach ( 0 .. $#files ) {
		files( $self->{dir} . "/" . $self->{list}->GetString( $files[$_] ) );
	}

	$self->{worksheet} = $EXCEL->add_worksheet('Files');
	my $graphsheet = $EXCEL->add_worksheet('Graphs');

	foreach my $gpr (@gprs) {
		$self->{worksheet}->write( $row, $col++, $gpr->{"file"} );
		foreach ( keys %{ $gpr->{controls} } ) {
			$self->{worksheet}
			  ->write( $row, $col, [ $_, scalar @{ $gpr->{controls}->{$_} } ] );
			$col += 2;
		}
		$self->{worksheet}->write( $row, $col,
			[ "peptides", scalar keys( %{ $gpr->{peptides} } ) ] );
		$row++;
		$col = 0;
	}

	$self->{worksheet} = $EXCEL->add_worksheet('File Statistics');

	my @headers = ( "File", "Control" );
	foreach (@mean) {
		push @headers, ( "$_", "$_ stddev", "$_ cv", "Min", "Max" );
	}
	$self->{worksheet}->write( 0, 0, \@headers );
	$row = 1;
	$col = 0;

	$self->file_peptide_stats();
	$self->file_control_stats();

	$self->{worksheet} = $EXCEL->add_worksheet('Array Data');
	$self->{worksheet}->hide();
	$row = 1;
	$col = 0;

	my $count = 1;
	my @peptides;
	foreach my $gpr (@gprs) {
		push @peptides, keys %{ $gpr->{"peptides"} };
	}
	@peptides = unique(@peptides);
	for ( my $i = 0 ;
		$i < @peptides ; $i += max( 1, int( @peptides / 20000 ) ) )
	{
		$self->{worksheet}->write( $count, $col, $peptides[$i] );
		$peptide_row{ $peptides[$i] } = $count++;
	}
	$col = 1;

	# Write out the array data to an excel sheet then create the relation graphs
	# for each pairs of data written out. Think I'll merge these into each other
	# for simplicity of the creation of the graphs.
	my ($mean) = @mean;
	my @keys = keys %peptide_row;
	foreach my $gpr (@gprs) {
		$self->{worksheet}->write( 0, $col, $gpr->{'file'} );
		foreach my $key (@keys) {
			if (   defined( $gpr->{"peptides"}->{$key} )
				&& defined( $peptide_row{$key} ) )
			{
				$self->{worksheet}->write( $peptide_row{$key}, $col,
					log_base( 10, $gpr->{"peptides"}->{$key}->{$mean} ) );
			}
		}
		$col++;
	}
	foreach my $i ( 1 .. $col - 2 ) {
		foreach my $j ( $i + 1 .. $col - 1 ) {
			my $graph = $EXCEL->add_chart( type => 'scatter', embedded => 1 );
			$graph->set_title( name => $gprs[ $i - 1 ]->{'file'} . ' vs '
				  . $gprs[ $j - 1 ]->{"file"} );
			$graph->add_series(
				categories => '='
				  . $self->{worksheet}->get_name() . '!'
				  . col_to_ref( xl_rowcol_to_cell( 1,            $i ) ) . ':'
				  . col_to_ref( xl_rowcol_to_cell( scalar @keys, $i ) ),
				values => '='
				  . $self->{worksheet}->get_name() . '!'
				  . col_to_ref( xl_rowcol_to_cell( 1,            $j ) ) . ':'
				  . col_to_ref( xl_rowcol_to_cell( scalar @keys, $j ) ),
			);
			$graph->set_x_axis( name => "log10 " . $gprs[ $i - 1 ]->{"file"} );
			$graph->set_y_axis( name => "log10 " . $gprs[ $j - 1 ]->{"file"} );
			$graph->set_legend( position => 'none' );
			push @graphs, $graph;
		}
	}

	$datasheet = $EXCEL->add_worksheet('Log Ratio Data');
	$self->{worksheet} = $EXCEL->add_worksheet('File Log Ratios');
	$datasheet->hide();
	$row = $col = 0;

	my $graph = $EXCEL->add_chart(
		type     => 'column',
		embedded => 1
	);

	my $data_col = 0;
	my %ratio_buckets;    # gather up all the log2 ratio data into buckets
	foreach ( keys %peptide_row ) {
		$datasheet->write( $peptide_row{$_}, $data_col, $_ );
	}
	$data_col++;
	$row = $col = 0;
	($mean) = @mean;
	$self->{worksheet}->write( $row++, $col,
		[ "File 1", "File 2", "$_ Ratio", "$mean 95% CI" ] );
	foreach my $i ( 0 .. $#gprs - 1 ) {
		my $gpr1 = $gprs[$i];
		my @keys = keys %{ $gpr1->{"peptides"} };
		foreach my $j ( $i + 1 .. $#gprs ) {
			my @CI;    # confidence interval
			my $count = 0;
			my $array_mean;
			my $gpr2 = $gprs[$j];
			$datasheet->write( 0, $data_col,
				$gpr1->{"file"} . "/" . $gpr2->{"file"} );
			foreach my $key (@keys) {
				if (   defined( $gpr1->{"peptides"}->{$key} )
					&& defined( $gpr2->{"peptides"}->{$key} ) )
				{
					if (   $gpr1->{"peptides"}->{$key}->{$mean} > 0
						&& $gpr2->{"peptides"}->{$key}->{$mean} > 0 )
					{
						$count++;
						push @CI,
						  log_base( 2,
							$gpr1->{"peptides"}->{$key}->{$mean} /
							  $gpr2->{"peptides"}->{$key}->{$mean} );
						$ratio_buckets{ int( $CI[$#CI] * 10 ) }++;
						$CI[$#CI] = abs( $CI[$#CI] );
						$array_mean += $CI[$#CI];
						if ( defined( $peptide_row{$key} ) ) {
							$datasheet->write( $peptide_row{$key}, $data_col,
								$CI[$#CI] );
						}

					}
				}
			}
			if ( $count > 0 ) {
				@CI = sort @CI;
				$self->{worksheet}->write(
					$row++,
					$col,
					[
						$gpr1->{"file"},      $gpr2->{"file"},
						$array_mean / $count, 2**$CI[ int( .95 * @CI ) ]
					]
				);
			}
			$data_col++;
		}
	}
	my ( $x_name, $y_name );
	$x_name = $y_name = '=' . $datasheet->get_name() . '!';
	$data_col++
	  ; # create an empty column between the file ratios and the overall bucket data
	$row = 0;

	$datasheet->write( $row++, $data_col, [ "Power", "Count" ] );
	$x_name .= col_to_ref( xl_rowcol_to_cell( $row, $data_col ) ) . ':';
	$y_name .= col_to_ref( xl_rowcol_to_cell( $row, $data_col + 1 ) ) . ':';
	foreach my $key ( sort { $a <=> $b } keys %ratio_buckets ) {
		$datasheet->write( $row++, $data_col,
			[ $key / 10, $ratio_buckets{$key} ] );
	}
	$x_name .= col_to_ref( xl_rowcol_to_cell( $row - 1, $data_col ) );
	$y_name .= col_to_ref( xl_rowcol_to_cell( $row - 1, $data_col + 1 ) );
	$graph->set_title( name => 'Log Ratio Buckets' );
	$graph->set_x_axis( name => 'Log2 Power' );
	$graph->set_y_axis( name => 'Count' );
	$graph->add_series(
		name       => 'Ratios',
		categories => $x_name,
		values     => $y_name,
	);
	push @graphs, $graph;

	$self->{worksheet} = $EXCEL->add_worksheet('Across Array Stats');
	@headers = ("Control");
	foreach (@mean) {
		push @headers, ( "$_", "$_ stddev", "$_ cv" );
	}
	$self->{worksheet}->write( 0, 0, \@headers );
	$row = 1;
	$col = 0;
	$self->file_total_stats();

	$self->{worksheet} = $EXCEL->add_worksheet('Correlation');
	$col = $row = 0;

	# Correlation calculations

	$self->{worksheet}->write( $row++, $col,
		[ "File 1", "File 2", "Correlation", "R-squared" ] );
	($mean) = @mean;

	#print $mean. "\n";
	foreach my $i ( 0 .. $#gprs - 1 ) {
		my $gpr1 = $gprs[$i];
		my @keys = keys %peptide_row;
		foreach my $j ( $i + 1 .. $#gprs ) {
			my $count = 0;
			my $r;
			my $gpr2 = $gprs[$j];
			foreach my $key (@peptides) {
				if (   defined( $gpr1->{"peptides"}->{$key} )
					&& defined( $gpr2->{"peptides"}->{$key} ) )
				{
					if (   $gpr1->{"peptides"}->{$key}->{$mean} >= 0
						&& $gpr2->{"peptides"}->{$key}->{$mean} >= 0 )
					{
						$count++;
						$r += (
							(
								$gpr1->{"peptides"}->{$key}->{$mean} -
								  $gprs_stats_cache{ $gpr1->{"file"} . "-"
									  . $mean }->{"mean"}
							) /
							  $gprs_stats_cache{ $gpr1->{"file"} . "-" . $mean }
							  ->{"stddev"}
						  ) * (
							(
								$gpr2->{"peptides"}->{$key}->{$mean} -
								  $gprs_stats_cache{ $gpr2->{"file"} . "-"
									  . $mean }->{"mean"}
							) /
							  $gprs_stats_cache{ $gpr2->{"file"} . "-" . $mean }
							  ->{"stddev"}
						  );
					}
				}
			}

			$r /= $count if ( $count > 0 );

			$self->{worksheet}->write(
				$row++, $col,
				[ $gpr1->{"file"}, $gpr2->{"file"}, $r, $r**2, $mean ]
			);
		}
	}

	$self->{worksheet} = $EXCEL->add_worksheet('Slide Density Data');
	$self->{worksheet}->hide();
	my $max = 0;   # what is the max array size so that the index can be created
	my $col = 1;
	foreach my $i ( 0 .. $#gprs ) {
		my $gpr = $gprs[$i];
		foreach my $j ( 0 .. $#mean ) {
			my $mean = $mean[$j];
			$self->{worksheet}->write( 0, $col, $gpr->{"file"} . " " . $mean );
			my @density;
			foreach my $key ( keys %{ $gpr->{"peptides"} } ) {
				$density[
				  int(
					  10 * log_base( 10, $gpr->{"peptides"}->{$key}->{$mean} ) )
				]++;
			}
			$self->{worksheet}->write( 1, $col, [ \@density ] );
			$max = max( $max, scalar $#density );
			my $graph = $EXCEL->add_chart( type => 'column', embedded => 1 );
			my ( $x_name, $y_name );
			$x_name = $y_name = '=' . $self->{worksheet}->get_name() . '!';
			$x_name .=
			    col_to_ref( xl_rowcol_to_cell( 1,    0 ) ) . ':'
			  . col_to_ref( xl_rowcol_to_cell( $max, 0 ) );
			$y_name .=
			    col_to_ref( xl_rowcol_to_cell( 1,    $col ) ) . ':'
			  . col_to_ref( xl_rowcol_to_cell( $max, $col ) );

			$graph->set_title( name => $gpr->{"file"} . " " . $mean );
			$graph->set_x_axis( name => 'Log10 Signal' );
			$graph->set_y_axis( name => 'Count' );
			$graph->add_series(
				name       => $gpr->{"file"} . " " . $mean,
				categories => $x_name,
				values     => $y_name,
			);
			push @graphs, $graph;
			$col++;
		}
	}
	my $i = 2;
	($mean) = @mean;
	foreach my $gpr (@gprs) {
		foreach my $control ( keys %{ $gpr->{"controls"} } ) {
			my @density;
			$self->{worksheet}
			  ->write( 0, $col, $gpr->{"file"} . " " . $control );
			foreach ( @{ $gpr->{"controls"}->{$control} } ) {
				$density[ int( 10 * log_base( 10, $_->{$mean} ) ) ]++;
			}
			$max = max( $max, scalar $#density );
			$self->{worksheet}->write( 1, $col, [ \@density ] );
			my $graph = $EXCEL->add_chart(
				name     => "A$i",
				type     => 'column',
				embedded => 1
			);
			my ( $x_name, $y_name );
			$x_name = $y_name = '=' . $self->{worksheet}->get_name() . '!';
			$x_name .=
			    col_to_ref( xl_rowcol_to_cell( 1,    0 ) ) . ':'
			  . col_to_ref( xl_rowcol_to_cell( $max, 0 ) );
			$y_name .=
			    col_to_ref( xl_rowcol_to_cell( 1,    $col ) ) . ':'
			  . col_to_ref( xl_rowcol_to_cell( $max, $col ) );

			$graph->set_title( name => $gpr->{"file"} . " " . $control );
			$graph->set_x_axis( name => 'Log10 Signal' );
			$graph->set_y_axis( name => 'Count' );
			$graph->add_series(
				name       => $gpr->{"file"} . " " . $control,
				categories => $x_name,
				values     => $y_name,
			);
			push @graphs, $graph;
			$col++;
		}
	}
	foreach ( 0 .. $max ) {
		$self->{worksheet}->write( $_ + 1, 0, $_ / 10 );
	}
	foreach ( 0 .. $#graphs ) {
		$graphsheet->insert_chart(
			xl_rowcol_to_cell( 20 * int( $_ / 5 ), 9 * ( $_ % 5 ) ),
			$graphs[$_] );
	}
}

=head1 C<inlist>

inlist searches through a list of items to see if there is a match with the
desired item.  Handles dealing with an array of strings or an array of sequence
objects from BioPerl.  This function could probably be made more robust, but
nothing has forced it to be changed any further.

	inlist($item, @find_in_array)
	
=cut

sub inlist {
	my $value = shift;
	$value =~ s/(['"\\\[\]])/\\$1/g;
	local ($_);
	if ( ref( $_[0] ) eq 'HASH' ) {
		$_ = '~';
		foreach my $seq (@_) {
			$_ .= $seq->seq . '~';
		}
	}
	elsif ( !( ref( $_[0] ) ) ) {
		$_ = '~' . join( '~', @_ ) . '~';
	}
	return (0) unless /^(.*?~$value)~/i;
	my $chunk = $1;
	$chunk =~ tr/~//;
}

=head1 C<average>
average returns the mean value of an array of values. They do not need to be 
presorted before calling.

	average(\@values)	

=cut

sub average {
	my ($array_ref) = @_;
	my $sum;
	my $count = scalar @$array_ref;
	foreach (@$array_ref) { $sum += $_; }
	return $sum / $count;
}

=head1 C<median>
median returns the median value of an array of values. They do not need to be 
presorted before calling.

	median(\@values)	

=cut

sub median {
	@_ == 1 or die('Sub usage: $median = median(\@array);');
	my ($array_ref) = @_;
	my $count = scalar @$array_ref;

	# Sort a COPY of the array, leaving the original untouched
	my @array = sort { $a <=> $b } @$array_ref;
	if ( $count % 2 ) {
		return $array[ int( $count / 2 ) ];
	}
	else {
		return ( $array[ $count / 2 ] + $array[ $count / 2 - 1 ] ) / 2;
	}
}

=head1 C<unique>

Takes in a list of items and returns an array of just the unique items contained in it.
	unique(@items)

=cut

sub unique {
	my @list  = @_;
	my %seen  = ();
	my @uniqu = grep { !$seen{$_}++ } @list;
	return @uniqu;
}

=head1 C<files>
files is called by the File::Find module to compile together an array of the 
gpr files for future processing

	files()	

=cut

sub files {
	$_ = shift @_;

	#print "$_\n";
	return if ( $_ !~ /\.gpr$/ );

	my %gpr;

	my %headers;

	my %peptides
	  ;    # this should hold the data for the various peptides on an array
	my %controls
	  ;    # this should hold the data for the various controls on an array

	open my $fh, $_ || return;
	$gpr{'file'} = fileparse( $_, '.gpr' );

	# need to strip the header rows off the GPR file
	# probably not important to keep them around

	while ( my $line = <$fh> ) {
		if ( $line =~ /^"?block"?/i ) {

			# newline character was causing name issues, but wasn't noticed
			# with genepix gpr files as the name column wasn't the last one in
			# the list for the header row
			chomp($line);
			$line =~ s/["']//g;

			#split the line on tabs for mapping
			my @temp = split( /\t/, $line );
			foreach ( 0 .. $#temp ) {
				$headers{ lc( $temp[$_] ) } = $_;

				#print lc( $temp[$_] ) ."\t".$_."\n";
			}
			last;
		}
	}

 # at this point we've read in all the various header rows and setup the column
 # header data now we need to parse through the actual data columns and pull out
 # the data that we need

	while ( my $line = <$fh> ) {
		my %datapoint;
		chomp $line;
		$line =~ s/["']//g;
		my @line = split( /\t/, $line );

		foreach (@desired_list) {
			$datapoint{$_} = $line[ $headers{$_} ];
		}

		# is this a control element or a data point. each is handled slightly
		# differently.
		if (   inlist( $line[ $headers{'name'} ], @control_list )
			|| inlist( $line[ $headers{'id'} ], @control_list ) )
		{
			if ( $line[ $headers{'name'} ] =~ /[\w\d]+/i ) {
				push @{ $controls{ lc( $line[ $headers{'name'} ] ) } },
				  \%datapoint;
			}
			else {
				push @{ $controls{ lc( $line[ $headers{'id'} ] ) } },
				  \%datapoint;
			}

			#print "Control Name: " . $line[ $headers{'name'} ] . "\n";
		}

	  # else this is a piece of data. Need to temporarily store these to do
	  # data manipulation on a single array of data before comparing data across
	  # the various gpr files.
		else {
			push @{ $peptides{ $line[ $headers{'name'} ] } }, \%datapoint;

			#print "Peptide Name: ".$line[ $headers{'name'} ]."\n";
		}
	}

	my %temp;

	foreach ( keys %peptides ) {
		if ( @{ $peptides{$_} } <= 1 ) {
			$temp{$_} = ${ $peptides{$_} }[0];
		}
		else {
			my %datapoint;
			foreach my $element (@desired_list) {
				if ( $element eq "name" ) {
					$datapoint{$element} = $_;
					next;
				}
				if ( $element eq "id" ) {
					$datapoint{$element} = ${ $peptides{$_} }[0]->{$element};
					next;
				}
				my @data
				  ; # holds the values that are going to be either averaged or median
				foreach my $data ( @{ $peptides{$_} } ) {
					push @data, $data->{$element};
				}
				if ( $element =~ /median/i ) {
					$datapoint{$element} = median( \@data );
				}
				elsif ( $element =~ /mean/i ) {
					$datapoint{$element} = average( \@data );
				}
			}
			$temp{$_} = \%datapoint;
		}

	}
	%peptides        = %temp;
	$gpr{"controls"} = \%controls;
	$gpr{"peptides"} = \%peptides;
	push @gprs, \%gpr;
}
1;
