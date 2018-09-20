package com.jfs.linkedlist;

public class Product {

    private String productCode;
    private String description;
    private String size;
    private double prize;
    private int quantity;
    private String imageName;
    private Product next;

    public Product getNext() {
        return next;
    }

    public void setNext(Product next) {
        this.next = next;
    }


    public String getProductCode() {
        return productCode;
    }

    public String getDescription() {
        return description;
    }

    public String getSize() {
        return size;
    }

    public double getPrize() {
        return prize;
    }

    public String getImageName(){
        return imageName;
    }

    public int getQuantity(){ return quantity;}



    public Product(String productCode, String description, String size, double prize, int quantity){
       this.productCode=productCode;
       this.description=description;
       this.size=size;
       this.prize=prize;
       this.quantity=quantity;
    }

    public Product(String productCode, String description, String size, double prize, String imageName){
        this.productCode=productCode;
        this.description=description;
        this.size=size;
        this.prize=prize;
        this.imageName = imageName;
    }

    public double itemAmount(){
        return prize*quantity;
    }


    public void display(){
        System.out.println(productCode+" "+description+" "+size+" "+prize+" "+quantity);
    }

    @Override
    public String toString() {
        return  description + " " + size + " $" + prize;
    }
}
