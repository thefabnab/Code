package project.pkg7;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Nabil Azamy & Derek Holtberg
 */
public class Project7 extends Application
{
    
    //Class Variables
    TextField nodeSize;
    TextField Action;
    TextField fetchProf;
    TextField deleteProf;
    TextField updateProf;
    Button insert;
    Button fetch;
    Button delete;
    Button update;
    Button output;
    Button exit;
    Button reset;

    /**
     * Method for establishing the GUI space, text fields, and buttons
     * 
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) 
    {
        GridPane playerGrid = new GridPane();
        playerGrid.setHgap(10);
        playerGrid.setVgap(10);
        playerGrid.setPadding(new Insets(0, 10, 0, 10));
        ButtonEventHandler eventHandler = new ButtonEventHandler();

        insert = (new Button("Insert"));
        insert.setOnAction(eventHandler);
        GridPane.setHalignment(insert, HPos.LEFT);
        playerGrid.add(insert, 0, 5);

        fetch = (new Button("Fetch:"));
        fetch.setOnAction(eventHandler);
        GridPane.setHalignment(fetch, HPos.LEFT);
        playerGrid.add(fetch, 0, 1);

        fetchProf = new TextField("");
        playerGrid.add(fetchProf, 1, 1);
        fetchProf.setAlignment(Pos.BASELINE_RIGHT);

        delete = (new Button("Delete:"));
        delete.setOnAction(eventHandler);
        GridPane.setHalignment(delete, HPos.LEFT);
        playerGrid.add(delete, 0, 2);

        deleteProf = new TextField("");
        playerGrid.add(deleteProf, 1, 2);
        deleteProf.setAlignment(Pos.BASELINE_RIGHT);

        update = (new Button("Update:"));
        update.setOnAction(eventHandler);
        GridPane.setHalignment(update, HPos.LEFT);
        playerGrid.add(update, 0, 4);

        updateProf = new TextField("");
        playerGrid.add(updateProf, 1, 4);
        updateProf.setAlignment(Pos.BASELINE_RIGHT);

        output = (new Button("Output"));
        output.setOnAction(eventHandler);
        GridPane.setHalignment(output, HPos.LEFT);
        playerGrid.add(output, 0, 6);

        exit = (new Button("Exit"));
        exit.setOnAction(eventHandler);
        GridPane.setHalignment(exit, HPos.LEFT);
        playerGrid.add(exit, 0, 8);

        reset = (new Button("Reset"));
        reset.setOnAction(eventHandler);
        GridPane.setHalignment(reset, HPos.LEFT);
        playerGrid.add(reset, 0, 7);

        Scene scene = new Scene(playerGrid);
        primaryStage.setTitle("Project 5 - Derek & Nabil");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    /**
     * Class for handling the events focused around the buttons within the
     * application
     *
     */
    public class ButtonEventHandler implements EventHandler<ActionEvent> 
    {

        Hashed list;
        boolean first = true;

        @Override
        public void handle(ActionEvent e) 
        {
            if (first) 
            {
                list = new Hashed(50);
                list.insert( new StudentListings("Steve", 1234, 4.0) );
                list.insert( new StudentListings("Frank", 4234, 3.0) );
                list.insert( new StudentListings("Jim", 2456, 2.0) );
                first = false;
            }

            if (e.getSource() == insert) 
            {
                StudentListings insertNode = new StudentListings();
                insertNode.input();
                list.insert(insertNode);
                
                System.out.println("***Node " + insertNode.getKey() + " "
                       + "added***");
            } 
            else if (e.getSource() == fetch) 
            {
                String n = fetchProf.getText();
                list.fetch(n);
                System.out.println("***Node " + list.fetch(n).toString());

            } 
            else if (e.getSource() == delete) 
            {
                list.delete(deleteProf.getText());
                System.out.println("***Node " + deleteProf.getText() + " "
                        + "deleted***");
            } 
            else if (e.getSource() == update) 
            {
                StudentListings inputNode1 = new StudentListings();
                inputNode1.input();
                System.out.println( "First update:" );
                list.update(list.fetch(updateProf.getText()));

            } 
            else if (e.getSource() == output) 
            {
                System.out.println("***Your Structure contains the following***");
                System.out.println(list);

            } 
            else if (e.getSource() == reset) 
            {
                System.out.println("***Nodes have been reset to initial "
                        + "conditions***");
                first = true;
            } 
            else if (e.getSource() == exit) 
            {
                System.out.println("***Exiting Application now***");
                System.exit(0);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
    
}
