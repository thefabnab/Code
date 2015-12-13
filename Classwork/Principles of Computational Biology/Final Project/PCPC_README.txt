Here you guys go. The script creates a windowed application that allows a user to select a folder containing their GPR files of interest, what columns in the files to do stats on 
(generally fXXX and bXXX for foreground and background spot information) and what names in the files should be treated as controls (empty, control, etc...).
Once an XLS file has been "selected" the user can then run the app. The output is placed in the XLS file for review. Stats like the pairwise Pearson correlation, Mean and CV of each array, etc... 
are outputted to appropriately named worksheets within the XLS file.

I've got versions of this that are "compiled" into self extracting EXE for use on systems that don't have perl or the needed modules.
