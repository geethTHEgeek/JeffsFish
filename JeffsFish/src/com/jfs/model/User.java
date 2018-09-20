package com.jfs.model;

import java.sql.SQLException;

public abstract class User {

     /**
      * Abstract method for password validation
      * @param password
      * @return
      */
     public abstract boolean hasValidPassword(String password);

}
