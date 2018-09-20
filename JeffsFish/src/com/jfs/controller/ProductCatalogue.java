package com.jfs.controller;

import com.jfs.linkedlist.Product;
import com.jfs.linkedlist.ProductList;
import com.jfs.util.DBConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class ProductCatalogue implements Initializable {




    @FXML
    private ChoiceBox productsChoiceBox;

@FXML
private ChoiceBox quantityChoiceBox;
@FXML
private Button addToCartBtn;
@FXML
private Button buyNowBtn;
@FXML
private Label priceTag;
@FXML
private ImageView image;


    private ObservableList<Product> productDetails=FXCollections.observableArrayList();
    private ObservableList<String> quantity= FXCollections.observableArrayList("1","2","3","4","5");
    public static ProductList theProductList;
    private String currentImage;
    private double currentPrize;

    public static Map<String,String> userDetails;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        quantityChoiceBox.setValue("1");
        quantityChoiceBox.setItems(quantity);

        theProductList =new ProductList();

        DBConnection connectionClass=new DBConnection();
        Connection connection=connectionClass.getConnection();

        Statement stmt= null;
        ResultSet rst= null;
        try {
            stmt = connection.createStatement();
            rst = stmt.executeQuery("SELECT * FROM product");
            int i = 0;

            while(rst.next()) {

                String productCode=rst.getString("prodcode");
                String description= rst.getString("description");
                String size=rst.getString("size");
                Double price=rst.getDouble("prize");
                String imageName = rst.getString("image");

                Product product = new Product(productCode, description, size, price,imageName);

                productDetails.add(product);

                i++;
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }


        productsChoiceBox.setItems(productDetails);

        ChangeListener<Product> changeListener = new ChangeListener<Product>() {
            @Override
            public void changed(ObservableValue<? extends Product> observable, Product oldValue, Product newValue) {

                if(newValue != null){
                    Image imageObj = null;
                    try {
                        currentImage=newValue.getImageName();
                        imageObj = new Image(new FileInputStream("C:\\Users\\DELL\\Desktop\\JavaFX\\"+currentImage));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    image.setImage(imageObj);
                    currentPrize=newValue.getPrize();
                    priceTag.setText("$"+Double.toString(currentPrize));
                }
            }
        };

        productsChoiceBox.getSelectionModel().selectedItemProperty().addListener(changeListener);


    }


public void addToCart(){

    DBConnection connectionClass=new DBConnection();
    Connection connection=connectionClass.getConnection();

    Statement stmt= null;
    ResultSet rst= null;
    try {
        stmt = connection.createStatement();
        PreparedStatement lineItem = connection.prepareStatement("SELECT * FROM product WHERE (image=? AND prize=?  )");
        lineItem.setString(1,currentImage);
        lineItem.setDouble(2,currentPrize);
        rst = lineItem.executeQuery();

        if(rst.next()) {

            String productCode = rst.getString("prodcode");
            String description = rst.getString("description");
            String size = rst.getString("size");
            Double price = rst.getDouble("prize");
           int quantity=Integer.parseInt((String) quantityChoiceBox.getValue());

            theProductList.insertFLink(productCode,description,size,price,quantity);

        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    /**
     * Go to shopping cart screen
     */
    public void buyNow(){

        ShoppingCart.theProductList = theProductList;
        ShoppingCart.userDetails=userDetails;

        Parent root = null;
    try {
        URL url = new File("src/com/jfs/ui/shopping_cart.fxml").toURL();
        root = FXMLLoader.load(url);
    } catch (IOException e) {
       e.printStackTrace();
    }
    Scene scene = new Scene(root);
        Stage stage = (Stage) priceTag.getScene().getWindow();
    stage.setTitle("Shopping Cart");
    stage.setScene(scene);
    stage.show();
}

    /**
     * Go to customer home screen
     */
    public void goBack(){

        Parent root = null;
        try {
            URL url = new File("src/com/jfs/ui/customer_home.fxml").toURL();
            root = FXMLLoader.load(url);
        } catch (IOException e) {

        }
        Scene scene = new Scene(root);
        Stage stage = (Stage) priceTag.getScene().getWindow();

        stage.setTitle("Customer Home");
        stage.setScene(scene);
        stage.show();

    }



}
