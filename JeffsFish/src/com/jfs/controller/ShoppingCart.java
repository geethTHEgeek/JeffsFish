package com.jfs.controller;

import com.jfs.linkedlist.ProductList;
import com.jfs.model.Purchase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class ShoppingCart implements Initializable{


public static ProductList theProductList;
    @FXML
   private TableView<Purchase> table;

    @FXML
    private TableColumn<Purchase,String> product;

    @FXML
    private  TableColumn<Purchase,String> size;

    @FXML
    private TableColumn<Purchase,Integer> quantity;

    @FXML
    private  TableColumn<Purchase,Double> price;

    public ObservableList<Purchase> list;

    @FXML
    private Label totalLbl;


    public static Map<String,String> userDetails;
public static double totalPrice;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        list= FXCollections.observableArrayList();
     list= theProductList.getValues(list);

     double total = 0;

     for(Purchase item : list){
         total += item.getPrize()*item.getQuantity();
     }

         product.setCellValueFactory(new PropertyValueFactory<Purchase, String>("description"));
         size.setCellValueFactory(new PropertyValueFactory<Purchase, String>("size"));
         quantity.setCellValueFactory(new PropertyValueFactory<Purchase, Integer>("quantity"));
         price.setCellValueFactory(new PropertyValueFactory<Purchase, Double>("prize"));


        table.setItems(list);
        totalLbl.setText("$"+Double.toString(total));
        totalPrice=total;

    }

    /**
     * Buy now from shopping cart
     */
    public void buyNow(){

    CardPayment.userDetails=userDetails;
    CardPayment.theProductList = theProductList;
    Parent root = null;
    try {
        URL url = new File("src/com/jfs/ui/card_payment.fxml").toURL();
        root = FXMLLoader.load(url);
    } catch (IOException e) {
        e.printStackTrace();
    }

    Scene scene = new Scene(root);
    Stage stage = (Stage) table.getScene().getWindow();

    stage.setTitle("Card Payment Screen");
    stage.setScene(scene);
    stage.show();
    }

    /**
     * Go back to product Catalogue
     */
    public void goBack(){

        Parent root = null;
        try {
            URL url = new File("src/com/jfs/ui/product_catalogue.fxml").toURL();
            root = FXMLLoader.load(url);
        } catch (IOException e) {
          e.printStackTrace();
        }

        Scene scene = new Scene(root);
        Stage stage = (Stage) table.getScene().getWindow();

        stage.setTitle("Product Catalogue Screen");
        stage.setScene(scene);
        stage.show();




    }


}
