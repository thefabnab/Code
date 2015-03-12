package testingflags;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This is a program that produces a series of flags using the Hbox functionality of JavaFx
 * 
 *
 * @author Nabil Azamy
 */
public class TestingFlags extends Application 
{
    
    public void start(Stage primaryStage) 
    {
        BorderPane pane = new BorderPane();
        
        pane.setTop(getHBox1());
        pane.setBottom(getHBox2());
        
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Country Flags!!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
        
    /**
     * A method creating Hbox1 which contains the Canadian and Chinese flags
     * 
     * @return hBox containing links to Canadian and Chinese flags
     */
        public HBox getHBox1() 
            {
                HBox hBox = new HBox(15);
                hBox.setPadding(new Insets(15, 15, 15, 15));
                hBox.getChildren().add(new ImageView(new Image("http://www2.sunysuffolk.edu/struckc/CST141/images/canada.gif")));
                hBox.getChildren().add(new ImageView(new Image("http://www2.sunysuffolk.edu/struckc/CST141/images/china.gif")));
                return hBox;

            }
    /**
     * A method creating Hbox2 which contains the UK and American flags
     * 
     * @return hBox containing the british and US flags
     */
        public HBox getHBox2() 
            {
                HBox hBox = new HBox(15);
                hBox.setPadding(new Insets(15, 15, 15, 15));
                hBox.getChildren().add(new ImageView(new Image("http://www2.sunysuffolk.edu/struckc/CST141/images/uk.gif")));
                hBox.getChildren().add(new ImageView(new Image("http://www2.sunysuffolk.edu/struckc/CST141/images/usa.gif")));
                return hBox;

            }
    


    /**
     * Main method launching the program
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
}
