package linkedlist;

public class LinkMain {



    public static void main(String [] args){

        String imageType= "FL-fishing line-100m-$,5";
        String[] imageArr = imageType.split("-");

        System.out.println(imageArr[0]);
        System.out.println(imageArr[1]);
        System.out.println(imageArr[2]);
        System.out.println(imageArr[3]);

       String[] price= imageArr[3].split(",");

        System.out.println(price[1]);

      /*  ProductList theProductList=new ProductList();
        theProductList.insertFLink("FL","fishing line","100m",50.5);
        theProductList.insertFLink("FL","fishing line","200m",100.5);
        theProductList.insertFLink("FL","fishing line","300m",150.5);


        theProductList.display();

       // System.out.println(theProductList.findPrice("fishing line","100m"));

       Product newLink=theProductList.findLink("fishing line","200m");
        System.out.println(newLink.getPrize());*/

    }
}
