package com.example.javafxproject;

import com.example.javafxproject.data.DBConnection;
import com.example.javafxproject.data.models.Admin;
import com.example.javafxproject.data.models.Fruit;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelloApplication extends Application {

    private Scene scene, screenLogin,screenregister;
    TextField name, pass;
    private static final String EMPTY = "";
    private Stage window;

    public static void main(String[] args) {
        launch(args);
    }
    void hompage(DBConnection conn, GridPane grid, Stage primaryStage){
        ArrayList<Fruit> FruitList = conn.getFruit();

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
                conn.Add(new Fruit(name,quality, price, typefruit,image));
                try {
                    start(primaryStage);
                    window.setScene(scene);
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

            grid.add(new Label (""+FruitList.get(i).getId()), 0, i+2);
            grid.add(new Label (FruitList.get(i).getName()), 1, i+2);
            grid.add(imageView, 2, i+2);
            grid.add(new Label ("$"+(FruitList.get(i).getPrice())), 3, i+2);
            grid.add(new Label (FruitList.get(i).getTypefruit()), 4, i+2);
            grid.add(new Label (""+FruitList.get(i).getQuality()), 5, i+2);

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
                        conn.Update(new Fruit(Newid, Newname,Newquality, Newprice, Newtypefruit, Newimage));
                        try {
                            start(primaryStage);
                            window.setScene(scene);
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
            grid.add(btnUpdate, 6, i+2);

            // Delete
            var btnDelete = new Button("Delete");

            btnDelete.setId(String.valueOf(FruitList.get(i).id));
            btnDelete.setOnAction(e -> {
                int iddelete = Integer.parseInt(btnDelete.getId());
                conn.Delete(iddelete);
                var alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Deleted!");
                alert.showAndWait();
                try {
                    start(primaryStage);
                    window.setScene(scene);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
            grid.add(btnDelete, 7, i+2);
        }
    }

//    private GridPane createRegistrationFormPane() {
//        // Instantiate a new Grid Pane
//        GridPane gridPane = new GridPane();
//
//        // Position the pane at the center of the screen, both vertically and horizontally
//        gridPane.setAlignment(Pos.CENTER);
//
//        // Set a padding of 20px on each side
//        gridPane.setPadding(new Insets(40, 40, 40, 40));
//
//        // Set the horizontal gap between columns
//        gridPane.setHgap(10);
//
//        // Set the vertical gap between rows
//        gridPane.setVgap(10);
//
//        // Add Column Constraints
//
//        // columnOneConstraints will be applied to all the nodes placed in column one.
//        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
//        columnOneConstraints.setHalignment(HPos.RIGHT);
//
//        // columnTwoConstraints will be applied to all the nodes placed in column two.
//        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
//        columnTwoConstrains.setHgrow(Priority.ALWAYS);
//
//        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);
//
//        return gridPane;
//    }


    // SHOW LOGIN
    void  showLogin(VBox loginPage , DBConnection conn){
        Label labelLogin =new Label("LOGIN");
        Label Aname = new Label("Name: ");
        Label Apassword = new Label("Password: ");
        name = new TextField();
        pass= new TextField();
        HBox fieldName = new HBox();
        fieldName.getChildren().addAll(Aname,name);
        fieldName.setSpacing(25);
        fieldName.setAlignment(Pos.BASELINE_CENTER);
        HBox fieldPass = new HBox();
        fieldPass.getChildren().addAll(Apassword,pass);
        fieldPass.setSpacing(10);
        fieldPass.setAlignment(Pos.BASELINE_CENTER);
        Button btnGoBack = new Button("Register");
        btnGoBack.setOnAction(actionEvent -> {
            window.setScene(screenregister);
        });
        Button btnLogin = new Button("LOGIN");
        btnLogin.setOnAction(actionEvent -> {
            this.checkLogin(conn);
        });
        HBox btnLoginPage = new HBox();
        btnLoginPage.getChildren().addAll(btnLogin,btnGoBack);
        btnLoginPage.setSpacing(10);
        btnLoginPage.setAlignment(Pos.BASELINE_CENTER);
        loginPage.getChildren().addAll(labelLogin,fieldName,fieldPass,btnLoginPage);
        loginPage.setSpacing(15);
        loginPage.setAlignment(Pos.BASELINE_CENTER);
    }

    // CHECK LOGIN
    void checkLogin(DBConnection db){
        ArrayList<Admin> ad = new ArrayList<>();
        ad = (ArrayList<Admin>) db.getAdmin();
        String inputName = name.getText();
        String inputPass = pass.getText();
        if(inputName.equals(ad.get(0).name)&& inputPass.equals(ad.get(0).password)){
            LoginSuccess();
            window.setScene(scene);
        }else{
            LoginError();
        }
    }

    //CHECK SUCCESS OR ERROR
    private void LoginSuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setHeaderText("Hi "+name.getText());
        alert.setContentText("Login successfully!");
        alert.show();
    }
    private void LoginError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR");
        alert.setContentText("Login fail!");
        alert.show();
    }

    @Override
    public void start(Stage primaryStage) {

        DBConnection conn = new DBConnection();
        VBox loginPage = new VBox();
        this.showLogin(loginPage, conn);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        this.hompage(conn, grid, primaryStage);
        ScrollPane scrollPane = new ScrollPane(grid);
        // Setting a horizontal scroll bar is always display
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        // Setting vertical scroll bar is never displayed.
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(grid);

        screenLogin = new Scene(loginPage, 400, 400);

        scene = new Scene(scrollPane, 1000, 600);
        primaryStage.setTitle("Product icons table");
        window = primaryStage;
        window.setScene(screenLogin);
        window.show();

    }
}
