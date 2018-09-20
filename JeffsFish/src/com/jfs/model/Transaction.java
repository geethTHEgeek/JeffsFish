package com.jfs.model;


import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Transaction {

        private SimpleIntegerProperty receiptId;
        private SimpleIntegerProperty userId;
        private SimpleDoubleProperty totalAmount;
        private SimpleStringProperty date;


        public int getReceiptId() {
            return receiptId.get();
        }

        public SimpleIntegerProperty receiptIdProperty() {
            return receiptId;
        }

        public int getUserId() {
            return userId.get();
        }

        public SimpleIntegerProperty userIdProperty() {
            return userId;
        }

        public double getTotalAmount() {
            return totalAmount.get();
        }

        public SimpleDoubleProperty totalAmountProperty() {
            return totalAmount;
        }

        public String getDate() {
            return date.get();
        }

        public SimpleStringProperty dateProperty() {
            return date;
        }

        public Transaction(int receiptId, int userId, double totalAmount, Date date) {
            this.receiptId = new SimpleIntegerProperty(receiptId);
            this.userId =  new SimpleIntegerProperty(userId);
            this.totalAmount = new SimpleDoubleProperty(totalAmount);

            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy:MM:dd");
            String currentDate = dateformat.format(date);
            this.date = new SimpleStringProperty(currentDate);
        }

        public String toString(){
            return ""+getReceiptId();
        }




}
