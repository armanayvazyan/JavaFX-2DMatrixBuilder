package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;


public class Main extends Application {

    Stage window;
    GridPane grid;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = new Stage();
        window.setTitle("Marix Builder - JavaFX");
        window.setResizable(false);

        grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));

        Label row = new Label("Row: ");
        GridPane.setConstraints(row,0,0);
        Label column = new Label("Column: ");
        GridPane.setConstraints(column,0,1);
        Label error = new Label();
        GridPane.setConstraints(error,1,4);

        TextField rowInput = new TextField();
        GridPane.setConstraints(rowInput,1,0);

        TextField columnInput = new TextField();
        GridPane.setConstraints(columnInput,1,1);

        Button nextBtn = new Button("Next");
        nextBtn.setPrefWidth(100);
        GridPane.setConstraints(nextBtn,1,2);

        nextBtn.setOnAction(e  -> {
            int rowResult = 0;
            int columnResult=0;
            boolean isOk= false;
            try{
                rowResult = Integer.parseInt(rowInput.getText());
                isOk = true;
            } catch (NumberFormatException e2){
                rowInput.setText("");
                columnInput.setText("");
            }
            try{
                columnResult = Integer.parseInt(columnInput.getText());
                isOk = true;
            } catch(NumberFormatException e2){
                columnInput.setText("");
                rowInput.setText("");
            }
            int finalRowResult = rowResult;
            int finalColumnResult = columnResult;
            if(finalRowResult == 0 || finalColumnResult == 0){
                error.setText("");
                error.setText("Please write a number that is not 0");
            }
            else if(!(finalRowResult < 6 && finalColumnResult < 6)){
                error.setText("");
                error.setText("Please write a number that is lesser than 6");
            }
            else if(isOk){
                error.setText("");
                onNextClick(finalRowResult, finalColumnResult);
            }
            else
                error.setText("Please write a number!");
        });

        grid.getChildren().addAll(row,column,rowInput,columnInput,error,nextBtn);
        grid.setAlignment(Pos.CENTER);

        window.setScene(new Scene(grid, 400, 300));
        window.show();


    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void onNextClick(int row,int column)  {
        MatrixView.display(row,column);
    }
}
