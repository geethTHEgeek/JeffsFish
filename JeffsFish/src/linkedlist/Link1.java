package linkedlist;

public class Link1 {

    private String bookName;
    private int millionsSold;
    private Link1 next;

    public Link1 getNext() {
        return next;
    }

    public void setNext(Link1 next) {
        this.next = next;
    }

    public String getBookName() {
        return bookName;
    }

    public int getMillionsSold() {
        return millionsSold;
    }

    public Link1(String bookName, int millionsSold){
        this.bookName=bookName;
        this.millionsSold=millionsSold;
    }



    public void display(){

        System.out.println(bookName+"  : "+millionsSold+",000,000");
    }

    public String toString(){
        return bookName;
    }

}
