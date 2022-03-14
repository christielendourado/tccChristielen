package com.christielen.integrativo.util;

public class RandomString {

    public static String getAlphaNumericString(int n){

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    public static String getNumericString(int n){

        // chose a Character random from this String
        String numericString = "0123456789";

        // create StringBuffer size of numericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to numericString variable length
            int index
                    = (int)(numericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(numericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
