package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;


public class MatrixView {




    public static void display(int row, int column)  {
        int WINDOW_WIDTH= 500 ;
        int WINDOW_HEIGHT = 400;

        if(column == 5){
            WINDOW_WIDTH = 600;
        }

        Stage window = new Stage();
        window.setTitle("Marix Builder - JavaFX");

        BorderPane layout = new BorderPane();
        VBox box = new VBox();
        GridPane grid = new GridPane();

        Rectangle rectangle = new Rectangle();
        Ellipse ellipse = new Ellipse();
        FileInputStream inputstream = null;
        Image image = null;
        try{
            inputstream = new FileInputStream("/Users//Ayvazyan//Downloads//image.jpg");
            image = new Image(inputstream);
        } catch(Exception e){
            e.printStackTrace();
        }

        grid.setPadding(new Insets(10, 10, 10, 30));
        grid.setAlignment(Pos.CENTER);
        int[][] array = new int[row][column];
        int cIndex = 0;
        int rIndex = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                Random rand = new Random();
                int result = rand.nextInt(30);
                Button btn = new Button(String.valueOf(result));
                btn.setPrefWidth(60);
                btn.setPrefHeight(60);
                GridPane.setConstraints(btn, cIndex, rIndex);
                grid.getChildren().add(btn);

                if (i > j && result % 2 != 0) {
                    btn.setOnAction(e -> {
                        rectangle.setWidth(180);
                        rectangle.setHeight(180);
                        box.setAlignment(Pos.CENTER);
                        box.setPadding(new Insets(10, 30, 10, 10));
                        box.getChildren().clear();
                        box.getChildren().add(rectangle);
                    });
                } else if (i > j) {
                    btn.setOnAction(e -> {
                        ellipse.setRadiusX(80);
                        ellipse.setRadiusY(80);
                        box.setAlignment(Pos.CENTER);
                        box.setPadding(new Insets(10, 30, 10, 10));
                        box.getChildren().clear();
                        box.getChildren().add(ellipse);
                    });
                } else if(i == j){
                    Image finalImage = image;
                    btn.setOnAction(e -> {
                        final ImageView selectedImage = new ImageView();
                        if(!selectedImage.equals(null)){
                            selectedImage.setImage(finalImage);
                            selectedImage.setFitWidth(180);
                            selectedImage.setFitHeight(180);
                            box.setAlignment(Pos.CENTER);
                            box.setPadding(new Insets(10, 30, 10, 10));
                            box.getChildren().clear();
                            box.getChildren().add(selectedImage);
                        } else
                            box.getChildren().clear();
                    });
                } else {
                    btn.setOnAction(e -> {
                        box.getChildren().clear();
                    });
                }
                cIndex += 1;
            }
                cIndex = 0;
                rIndex += 1;
            }

            layout.setLeft(grid);
            layout.setRight(box);
            Scene scene = new Scene(layout, WINDOW_WIDTH, WINDOW_HEIGHT);
            window.setScene(scene);
            window.show();

        }
    }

