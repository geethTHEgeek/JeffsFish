package JFS;

public class Product {

    private String productCode;
    private String description;
    private String size;
    private double prize;
    private String imageName;

    public Product() {
    }

    public Product(String productCode, String description, String size, double prize, String imageName){
        this.productCode=productCode;
        this.description=description;
        this.size=size;
        this.prize=prize;
        this.imageName = imageName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return  description + " " + size + " $" + prize;
    }
}
