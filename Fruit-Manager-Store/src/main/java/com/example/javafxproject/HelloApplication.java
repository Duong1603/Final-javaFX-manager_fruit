package com.example.javafxproject;

import com.example.javafxproject.data.DBConnection;
import com.example.javafxproject.data.models.Fruit;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.cert.PolicyNode;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelloApplication extends Application {

    private Scene scene;
    private static final String EMPTY = "";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {



        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        DBConnection DB = new DBConnection();

        ArrayList<Fruit> FruitList = DB.getFruit();

        grid.add(new Label("Name:"), 0, 0);
        var tfName = new TextField();
        grid.add(tfName, 0, 1);
        //
        grid.add(new Label("Image:"), 1, 0);
        var tfImage = new TextField();
        grid.add(tfImage, 1, 1);
        //
        grid.add(new Label("Price:"), 2, 0);
        var tfPrice = new TextField();
        grid.add(tfPrice, 2, 1);
        //
        grid.add(new Label("Type Fruit:"),3,  0);
        var tfTypefruit = new TextField();
        grid.add(tfTypefruit, 3, 1);
        //
        grid.add(new Label("Quality:"),4,  0);
        var tfQuality = new TextField();
        grid.add(tfQuality, 4, 1);
        //

        // add
        var btnAdd = new Button("Add");
        btnAdd.setPadding(new Insets(5, 15, 5, 15));
        btnAdd.setOnAction(e -> {
            String name = tfName.getText();
            String image = tfImage.getText();
            Float price = Float.valueOf(tfPrice.getText());
            String typefruit = tfTypefruit.getText();
            Integer quality = Integer.valueOf(tfQuality.getText());
            if (!name.equals(EMPTY) && !image.equals(EMPTY) && !price.equals(EMPTY) && !typefruit.equals(EMPTY) && !quality.equals(EMPTY)) {
                DB.Add(new Fruit(name,quality, price, typefruit,image));
                try {
                    start(stage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                return;
            }
            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank!");
            alert.showAndWait();
        });
        grid.add(btnAdd, 5, 1);

        //show
        for(int i = 0; i < FruitList.size(); i++){
            Image image = new Image(FruitList.get(i).getImage());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(110);
            imageView.setFitHeight(110);

            grid.add(new Label (FruitList.get(i).getName()), 0, i+2);
            grid.add(imageView, 1, i+2);
            grid.add(new Label ("$"+(FruitList.get(i).getPrice())), 2, i+2);
            grid.add(new Label (FruitList.get(i).getTypefruit()), 3, i+2);
            grid.add(new Label (""+FruitList.get(i).getQuality()), 4, i+2);

            // Update
            var btnUpdate = new Button("Update");
            btnUpdate.setId(String.valueOf(i));
            btnUpdate.setOnAction(e -> {
                btnAdd.setVisible(false);
                int finali = Integer.parseInt(btnUpdate.getId());
                TextField tfname = (TextField) grid.getChildren().get(1);
                tfname.setText("" + FruitList.get(finali).getName());
                TextField tfimage = (TextField) grid.getChildren().get(3);
                tfimage.setText("" + FruitList.get(finali).getImage());
//                name.setText(stdList.get(Integer.parseInt(btnUpdate.getId())).getName());
                TextField tfprice = (TextField) grid.getChildren().get(5);
                tfprice.setText("" + FruitList.get(finali).getPrice());
                TextField tftypecake = (TextField) grid.getChildren().get(7);
                tftypecake.setText("" + FruitList.get(finali).getTypefruit());
                TextField tfquality = (TextField) grid.getChildren().get(9);
                tfquality.setText("" + FruitList.get(finali).getQuality());
                var newbtnAdd = new Button("Update");
                newbtnAdd.setPadding(new Insets(6, 20, 6, 20));
                newbtnAdd.setOnAction(newe -> {
                    int Newid = FruitList.get(finali).id;
                    String Newname = tfname.getText();
                    String Newimage = tfimage.getText();
                    Float Newprice = Float.valueOf(tfprice.getText());
                    String Newtypefruit = tfTypefruit.getText();
                    Integer Newquality = Integer.valueOf(tfquality.getText());
                    if (!Newname.equals(EMPTY) && !Newimage.equals(EMPTY) && !Newprice.equals(EMPTY) && !Newtypefruit.equals(EMPTY) && !Newquality.equals(EMPTY)) {
                        DB.Update(new Fruit(Newid, Newname,Newquality, Newprice, Newtypefruit, Newimage));
                        try {
                            start(stage);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        return;
                    }
                    var alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all blank!");
                    alert.showAndWait();
                });
                grid.add(newbtnAdd, 5, 1);
            });
            grid.add(btnUpdate, 5, i+2);

            // Delete
            var btnDelete = new Button("Delete");

            btnDelete.setId(String.valueOf(FruitList.get(i).id));
            btnDelete.setOnAction(e -> {
                int iddelete = Integer.parseInt(btnDelete.getId());
                DB.Delete(iddelete);
                var alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Deleted!");
                alert.showAndWait();
                try {
                    start(stage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
            grid.add(btnDelete, 6, i+2);
        }

        scene = new Scene(grid, 1000, 700);
        stage.setTitle("Product icons table");
        stage.setScene(scene);
        stage.show();
    }


}
