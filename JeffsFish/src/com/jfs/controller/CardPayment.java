package com.jfs.controller;

import com.jfs.linkedlist.Product;
import com.jfs.linkedlist.ProductList;
import com.jfs.model.Invoice;
import com.jfs.model.Receipt;
import com.jfs.service.EmailService;
import com.jfs.util.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;


public class CardPayment {
    public static Map<String,String> userDetails;

    public static ProductList theProductList;

    @FXML
    private TextField cardNumber;


    public void purchase() {

        DBConnection connectionClass = new DBConnection();
        Connection connection = connectionClass.getConnection();

        try {


            PreparedStatement listData = connection.prepareStatement("INSERT INTO transaction_log (userId,FL100m,FL150m,FL200m,SI50g,SI100g,SI150g," +
                    "SWS,SWM,SWL,FH4,FH5,FH6,RO1m,RO2m,RO3m,FRS,FRM,FRL,totalPaid,date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            for(int i=1;i<=19;i++){
                listData.setInt(i,0);
            }
            listData.setInt(1,Integer.parseInt(userDetails.get("userId")));
            for (Product item: theProductList.getValuesAsList()) {
                if (item.getProductCode().equals("FL")) {
                    if (item.getSize().equals("100m")) {
                        listData.setInt(2, item.getQuantity());
                    } else if (item.getSize().equals("150m")) {
                        listData.setInt(3, item.getQuantity());
                    } else {
                        listData.setInt(4, item.getQuantity());
                    }
                } else if (item.getProductCode().equals("SI")) {
                    if (item.getSize().equals("50g")) {
                        listData.setInt(5, item.getQuantity());
                    } else if (item.getSize().equals("100g")) {
                        listData.setInt(6, item.getQuantity());
                    } else {
                        listData.setInt(7, item.getQuantity());
                    }
                } if (item.getProductCode().equals("SW")) {
                    if (item.getSize().equals("S")) {
                        listData.setInt(8,item.getQuantity());
                    } else if (item.getSize().equals("M")) {
                        listData.setInt(9,item.getQuantity());
                    } else {
                        listData.setInt(10,item.getQuantity());
                    }
                }else if (item.getProductCode().equals("FH")) {
                    if (item.getSize().equals("#4")) {
                        listData.setInt(11,item.getQuantity());
                    } else if (item.getSize().equals("#5")) {
                        listData.setInt(12,item.getQuantity());
                    } else {
                        listData.setInt(13,item.getQuantity());
                    }
                }else if (item.getProductCode().equals("RO")) {
                    if (item.getSize().equals("1m")) {
                        listData.setInt(14,item.getQuantity());
                    } else if (item.getSize().equals("2m")) {
                        listData.setInt(15,item.getQuantity());
                    } else {
                        listData.setInt(16,item.getQuantity());
                    }
                } else {
                    if (item.getSize().equals("S")) {
                        listData.setInt(17,item.getQuantity());
                    } else if (item.getSize().equals("M")) {
                        listData.setInt(18,item.getQuantity());
                    } else {
                        listData.setInt(19,item.getQuantity());
                    }
                }

            }

            //getting todays date
            long millis=System.currentTimeMillis();
            Date date=new Date(millis);

            listData.setDouble(20, ShoppingCart.totalPrice);
            listData.setDate(21,date);
            int updateResult =  listData.executeUpdate();

            //getting the last receipt id
            PreparedStatement getLastReceiptId=connection.prepareStatement("SELECT receiptId FROM transaction_log");
            ResultSet resultSet2=getLastReceiptId.executeQuery();
            ArrayList<Integer> arrayList2 = new ArrayList<Integer>();
            while(resultSet2.next()) {
                arrayList2.add(resultSet2.getInt("receiptId"));
            }

            System.out.println(arrayList2);
            int lastReceiptId=arrayList2.get(arrayList2.size()-1);

            if(updateResult == 1){
                Receipt receipt=new Receipt(lastReceiptId,date, ShoppingCart.totalPrice);

                //sending email for tax invoice
                EmailService emailService = new EmailService();
                Invoice invoice=new Invoice(userDetails, theProductList,receipt);
                String printedInvoice=invoice.printInvoice();
                emailService.sendEmail(printedInvoice,userDetails.get("email"));


                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Purchase Success");
                alert.setHeaderText(null);
                alert.setContentText(receipt.printReceipt());

                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {

                    Parent root = null;
                    try {
                        URL url = new File("src/com/jfs/ui/customer_home.fxml").toURL();
                        root = FXMLLoader.load(url);
                    } catch (IOException e) {

                    }

                    Scene scene = new Scene(root);
                    Stage stage = (Stage) cardNumber.getScene().getWindow();

                    stage.setTitle("Card Payment Screen");
                    stage.setScene(scene);
                    stage.show();

                }

            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

    }

}

