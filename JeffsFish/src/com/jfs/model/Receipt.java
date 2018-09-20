package com.jfs.model;

import java.sql.Date;

public class Receipt {

    private int receiptId;
    private Date date;
    private double totalAmount;


    public Receipt(int receiptId, Date date, double totalAmount) {
        this.receiptId = receiptId;
        this.date = date;
        this.totalAmount = totalAmount;

    }

    public int getReceiptId() {
        return receiptId;
    }

    public Date getDate() {
        return date;
    }

    public double getTotalAmount() {
        return totalAmount;
    }


    /**
     * Print receipt method
     * @return receiptText
     */
    public String printReceipt(){

    String receiptText="PURCHASE SUCCESSFUL\n";
    receiptText+="Receipt #"+getReceiptId()+"\n";
    receiptText+="Full Amount        = "+getTotalAmount()+"\n";

    receiptText+="Payable Amount = "+getTotalAmount()+"\n";
    receiptText+="[credit card]";


    return receiptText;
    }

}