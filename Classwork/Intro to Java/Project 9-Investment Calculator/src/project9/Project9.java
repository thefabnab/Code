package project9;

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
import javax.swing.JOptionPane;

/**
 * Investment-Value calculator Project 9
 * 
 * @author James Meister & Nabil Azamy
 * @version CST 141 FALL 2014 Project 9
 */

public class Project9 extends Application {
    //Initalizing variables
    
    TextField investmentAmount;
    TextField numberOfYears;
    TextField annualInterestRate;
    TextField futureValue;
    Button calculate;
    
    
    @Override
    public void start(Stage primaryStage) {
        /**
         * Creating a GridPane for the program
         * Adding a ButtonEventHandler to later make the button work
         */
        GridPane playerGrid = new GridPane();
        playerGrid.setHgap(10);
        playerGrid.setVgap(10);
        playerGrid.setPadding(new Insets(0, 10, 0, 10));
        ButtonEventHandler eventHandler = new ButtonEventHandler();

        /**
         * Creating the 4 rows in column 1 and setting up the font, alignment and position
         * Also creating the TextFields in column 2 for the user to enter the data
         */
        
        Text row1 = new Text("Investment Amount:");
        row1.setFont(Font.font("Arial", 16));
        GridPane.setHalignment(row1, HPos.LEFT);
        playerGrid.add(row1, 0,3);

        investmentAmount = new TextField("");
        playerGrid.add(investmentAmount, 1,3);
        investmentAmount.setAlignment(Pos.BASELINE_RIGHT);
        

        Text row2 = new Text("Number of Years:");
        row2.setFont(Font.font("Arial", 16));
        GridPane.setHalignment(row2, HPos.LEFT);
        playerGrid.add(row2, 0,4);

        numberOfYears = new TextField("");
        playerGrid.add(numberOfYears, 1,4);
        numberOfYears.setAlignment(Pos.BASELINE_RIGHT);
        
        Text row3 = new Text("Annual Interest Rate:");
        row3.setFont(Font.font("Arial", 16));
        GridPane.setHalignment(row3, HPos.LEFT);
        playerGrid.add(row3, 0,5);

        annualInterestRate = new TextField("");
        playerGrid.add(annualInterestRate, 1,5);
        annualInterestRate.setAlignment(Pos.BASELINE_RIGHT);

        Text row4 = new Text("Future Value:");
        row4.setFont(Font.font("Arial", 16));
        GridPane.setHalignment(row4, HPos.LEFT);
        playerGrid.add(row4, 0,6);

        futureValue = new TextField("");
        playerGrid.add(futureValue, 1,6);
        futureValue.setEditable(false);
        futureValue.setAlignment(Pos.BASELINE_RIGHT);
        
        /**
         * Adding a button called Calculate to calculate the Future Value of the Investment 
         * amount, Number of years, and Annual interest rate.
         */  
        calculate = ( new Button("Calculate") );
        calculate.setOnAction(eventHandler);
        GridPane.setHalignment(calculate, HPos.RIGHT);
        playerGrid.add(calculate, 1,7);

        /**
         * Setting up the scene by adding a title and giving it some custom settings such as 
         * making it non-resizable
         */
        Scene scene = new Scene(playerGrid);
        primaryStage.setTitle("Project 9 - James & Nabil");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * This is where we added the ButtonEventHandler to make the calculate button output the Future Value
     * It takes the Investment Amount, Number Of Years, and Annual Interest Rate and does the formula on line 127
     * which then outputs it to the futureValue TextField.
     * 
     * We also have a catch Exception incase someone fills something out wrong they will be notified to fix what they did.
     * 
     * @param args     
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public class ButtonEventHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            try
            {
                double investmentAmountA = Double.parseDouble( investmentAmount.getText() );
                double numberOfYearsA = Double.parseDouble( numberOfYears.getText() );
                double annualInterstRateA = Double.parseDouble( annualInterestRate.getText() );
                double futureValueA = investmentAmountA * Math.pow((1 + (annualInterstRateA/1200)),(numberOfYearsA * 12));
                
                if (e.getSource() == calculate)
                {
                    futureValue.setText( String.format("$%.2f", (futureValueA) ) );
                }
                
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Exception: Empty text field or non number entered, try again, son.");
            }
        }
    }
}