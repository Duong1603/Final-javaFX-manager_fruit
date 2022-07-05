package com.example.javafxproject;

import com.example.javafxproject.data.DBConnection;
import com.example.javafxproject.data.models.Admin;
import com.example.javafxproject.data.models.Fruit;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelloApplication extends Application {

    private Scene scene, screenLogin,navigationButton, home;
    TextField name, pass;
    private static final String EMPTY = "";
    private Stage window;

    public static void main(String[] args) {
        launch(args);
    }
    void hompage(DBConnection conn, GridPane grid, Stage primaryStage) {
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
        grid.add(new Label("Type Fruit:"), 3, 0);
        var tfTypefruit = new TextField();
        grid.add(tfTypefruit, 3, 1);
        //
        grid.add(new Label("Quality:"), 4, 0);
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
                conn.Add(new Fruit(name, quality, price, typefruit, image));
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
        for (int i = 0; i < FruitList.size(); i++) {
            Image image = new Image(FruitList.get(i).getImage());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(110);
            imageView.setFitHeight(110);

            grid.add(new Label("" + FruitList.get(i).getId()), 1, i + 2);
            grid.add(new Label(FruitList.get(i).getName()), 2, i + 2);
            grid.add(imageView, 3, i + 2);
            grid.add(new Label("$" + (FruitList.get(i).getPrice())), 4, i + 2);
            grid.add(new Label(FruitList.get(i).getTypefruit()), 5, i + 2);
            grid.add(new Label("" + FruitList.get(i).getQuality()), 6, i + 2);

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
                        conn.Update(new Fruit(Newid, Newname, Newquality, Newprice, Newtypefruit, Newimage));
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
                grid.add(newbtnAdd, 6, 1);
            });
            grid.add(btnUpdate, 9, i + 2);

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
            grid.add(btnDelete, 10, i + 2);
        }
        //button back
        Button btnGoBack = new Button("Back");
        btnGoBack.setPadding(new Insets(5, 15, 5, 15));
        btnGoBack.setOnAction(actionEvent -> {
            window.setScene(navigationButton);
            window.centerOnScreen();
        });
        grid.add(btnGoBack,12,0);
    }

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
//        Button btnGoBack = new Button("Register");
//        btnGoBack.setOnAction(actionEvent -> {
//            window.setScene(screenregister);
//        });
        Button btnLogin = new Button("LOGIN");
        btnLogin.setOnAction(actionEvent -> {
            this.checkLogin(conn);
        });
        HBox btnLoginPage = new HBox();
        btnLoginPage.getChildren().addAll(btnLogin);
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
            window.setScene(navigationButton);
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

    // choose page
    void navigationButton( VBox sceneNavigationButton) {
        GridPane goAdmin = new GridPane();
        GridPane goHome = new GridPane();
        GridPane goHomeAdmin = new GridPane();
        goHomeAdmin.setAlignment(Pos.CENTER);
        Button btnGoAdmin = new Button("GO ADMIN");
        btnGoAdmin.setPadding(new Insets(15, 25, 15, 25));
        btnGoAdmin.setOnAction(actionEvent -> {
            window.setScene(scene);
            window.centerOnScreen();
        });
        Button btnGoHome = new Button("GO Home");
        btnGoHome.setPadding(new Insets(15, 25, 15, 25));
        btnGoHome.setOnAction(actionEvent -> {
            window.setScene(home);
            window.centerOnScreen();
        });
        GridPane gridAdmin = new GridPane();
        gridAdmin.getChildren().addAll(btnGoAdmin);

        GridPane btnHome = new GridPane();
        btnHome.getChildren().add(btnGoHome);

        goAdmin.add(gridAdmin, 5, 3);

        goHome.add(btnHome, 10, 3);

        goHomeAdmin.add( goHome,5,3);
        goHomeAdmin.add( goAdmin,10,3);
        sceneNavigationButton.getChildren().addAll(goHomeAdmin,goAdmin,goHome,btnGoAdmin,btnHome,btnGoHome);

    }
    // Homepage
    void home( VBox sceneHome) {
        Button btnGoBack = new Button("Back");
        btnGoBack.setPadding(new Insets(5, 15, 5, 15));
        btnGoBack.setOnAction(actionEvent -> {
            window.setScene(navigationButton);
            window.centerOnScreen();
        });
        GridPane home = new GridPane();
        GridPane grid = new GridPane();
        GridPane goBack = new GridPane();
        GridPane btnBack = new GridPane();
        btnBack.getChildren().addAll(btnGoBack);
        goBack.add(btnBack, 2, 0);
        home.add( goBack,2,0);
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        DBConnection DB = new DBConnection();
        ArrayList<Fruit> fruitList = DB.getFruit();
        grid.add(new Label("WELLCOM TO FRIUT STORE"), 30, 0);

        //show
        for(int i = 0; i < fruitList.size(); i++){

            var btnBuy = new Button("Buy Now");
            Image image = new Image(fruitList.get(i).getImage());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);
            Text textName = new Text(fruitList.get(i).getName());

            grid.add(imageView, 10, i+1);
            grid.add((textName), 15, i+1);
            grid.add(new Label ("Quality: "+String.valueOf(fruitList.get(i).getQuality())), 20, i+1);
            grid.add(new Label ("Price: $"+String.valueOf(fruitList.get(i).getPrice())), 30, i+1);
            grid.add((btnBuy), 50, i+1);
        }
        home.getChildren().add(grid);
        ScrollPane scrollHome = new ScrollPane(grid);
        scrollHome.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollHome.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollHome.setContent(grid);
        sceneHome.getChildren().addAll(home,scrollHome,btnGoBack,goBack);
    }

    // Start program
    @Override
    public void start(Stage primaryStage) {

        DBConnection conn = new DBConnection();
        VBox loginPage = new VBox();
        VBox sceneNavigationButton = new VBox();
        VBox sceneHome= new VBox();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        this.home(sceneHome);
        this.navigationButton(sceneNavigationButton);
        this.showLogin(loginPage, conn);
        this.hompage(conn, grid, primaryStage);

        ScrollPane scrollHomepage = new ScrollPane(grid);
        // Setting a horizontal scroll bar is always display
        scrollHomepage.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        // Setting vertical scroll bar is never displayed.
        scrollHomepage.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollHomepage.setContent(grid);

        scene = new Scene(scrollHomepage, 1200, 600);
        home = new Scene(sceneHome, 1200,600);
        navigationButton = new Scene(sceneNavigationButton, 400, 400);
        screenLogin = new Scene(loginPage, 400, 400);
        primaryStage.setTitle("Product icons table");
        window = primaryStage;
        window.setScene(screenLogin);
        window.show();

    }
}
