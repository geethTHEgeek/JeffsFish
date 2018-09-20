package com.jfs.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Purchase {

    private SimpleStringProperty productCode;
    private SimpleStringProperty description;
    private SimpleStringProperty size;
    private SimpleDoubleProperty prize;
    private SimpleStringProperty imageName;
    private SimpleIntegerProperty quantity;

    public Purchase(String productCode, String description, String size, double prize, int quantity) {
        this.productCode = new SimpleStringProperty(productCode);
        this.description = new SimpleStringProperty(description);
        this.size =  new SimpleStringProperty(size);
        this.prize = new SimpleDoubleProperty(prize);
        this.quantity = new SimpleIntegerProperty(quantity);

    }


    public Purchase(String description, String size,int quantity,double prize) {
        this.description = new SimpleStringProperty(description);
        this.size =  new SimpleStringProperty(size);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.prize = new SimpleDoubleProperty(prize);

    }





    public String getProductCode() {
        return productCode.get();
    }

    public SimpleStringProperty productCodeProperty() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode.set(productCode);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getSize() {
        return size.get();
    }

    public SimpleStringProperty sizeProperty() {
        return size;
    }

    public void setSize(String size) {
        this.size.set(size);
    }

    public double getPrize() {
        return prize.get();
    }

    public SimpleDoubleProperty prizeProperty() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize.set(prize);
    }

    public String getImageName() {
        return imageName.get();
    }

    public SimpleStringProperty imageNameProperty() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName.set(imageName);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }


}
