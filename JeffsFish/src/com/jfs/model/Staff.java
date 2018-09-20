package com.jfs.model;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Staff extends User {

    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public Staff() {
    }

    public Staff( String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean hasValidPassword(String password) {

        String regEx="[a-zA-Z]*";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(password);   // get a matcher object
        int count = 0;
        int nonAlphabetic=0;

        while(m.find()) {
            count++;

            m.start();
            m.end();

            if(m.start()==m.end()){
                nonAlphabetic++;
            }
        }

        System.out.println("Non-Alphabetic character = "+(nonAlphabetic-1));

        if(password.length()<8){
            System.out.println("Password length should be greater than 8 characters");

            return false;
        }
        else if((nonAlphabetic-1)<2){
            System.out.println("PasswordMain should contain a minimum of 2 non-alphabetic character");

            return false;
        }else{
            System.out.println("Registration Successful");

        }

        return true;

    }
}






