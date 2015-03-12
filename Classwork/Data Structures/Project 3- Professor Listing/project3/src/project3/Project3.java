package project3;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This Java FX Application Driver delivers a GUI input for the Unsorted 
 * Optimized Array ProfessorListing nodes produced. From this GUI one can perform
 * fetch, insert, delete, update, and reset commands which serve to modify the 
 * existing nodes.
 *
 * @author Nabil Azamy & Derek Holtberg
 */
public class Project3 extends Application 
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

        Text row1 = new Text("Node Size:");
        row1.setFont(Font.font("Arial", 14));
        GridPane.setHalignment(row1, HPos.LEFT);
        playerGrid.add(row1, 0, 0);

        nodeSize = new TextField("");
        nodeSize.setPromptText("REQUIRED");
        playerGrid.add(nodeSize, 1, 0);
        nodeSize.setAlignment(Pos.BASELINE_RIGHT);

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
        primaryStage.setTitle("Project 3 - Derek & Nabil");
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

        UOA array;
        boolean first = true;

        public void handle(ActionEvent e) 
        {
            //if statement which upon second iteration is found false to allow
            //for maintaining the data within the array variable.
            if (first) 
            {
                int arraySize = Integer.parseInt(nodeSize.getText());

                array = new UOA(arraySize);

                array.insert(new ProfessorListings("Phil", "Physics",
                        "Quantum electrodynamics", "AppleOnTheHead@gmail.com"));
                array.insert(new ProfessorListings("Steve", "Chemistry",
                        "Organic Chemistry", "GibbsEnergyCO@yhoo.com"));
                array.insert(new ProfessorListings("Frank", "English",
                        "Plato", "FormerHomer@apple.com"));
                array.insert(new ProfessorListings("John", "Music",
                        "Baroque Theory", "FunkDaBadunk@gmail.com"));

                first = false;
            }

            if (e.getSource() == insert) 
            {
                ProfessorListings inputNode = new ProfessorListings();
                inputNode.input();
                array.insert(inputNode);
                System.out.println("***Node " + inputNode.getName() + " "
                        + "added***");
            } 
            else if (e.getSource() == fetch) 
            {
                String n = fetchProf.getText();
                array.fetch(n);
                System.out.println("***Node " + array.fetch(n).toString());

            } 
            else if (e.getSource() == delete) 
            {
                array.delete(deleteProf.getText());
                System.out.println("Node " + deleteProf.getText() + " "
                        + "deleted***");
            } 
            else if (e.getSource() == update) 
            {
                ProfessorListings inputNode1 = new ProfessorListings();
                array.update(updateProf.getText(), inputNode1);

            } 
            else if (e.getSource() == output) 
            {
                System.out.println("Printing out all Professors: \n" + array.toString());

            } 
            else if (e.getSource() == reset) 
            {
                first = true;
            } 
            else if (e.getSource() == exit) 
            {
                System.exit(0);
            }
        }
    }

    /**
     * The main method serving to execute the application
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }

}
