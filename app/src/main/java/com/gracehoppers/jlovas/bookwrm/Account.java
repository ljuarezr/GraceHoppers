package com.gracehoppers.jlovas.bookwrm;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by nlovas on 10/15/15.
 */

public class Account {
    /*
    The Account of the user, which includes their username, city, email, friendlist, and booklist,
     */

    private String username;
    private String city;
    private String email;

    //private Friendlist friendlist = new Friendlist(); //uncomment this once it has been written
    private Inventory inventory;
    //private SaveLoad saveload; //for saving your account
    private Friends friends; //create friend list

    public TradeHistory getTradeHistory() {
        return tradeHistory;
    }

    public void setTradeHistory(TradeHistory tradeHistory) {
        this.tradeHistory = tradeHistory;
    }

    private TradeHistory tradeHistory = new TradeHistory(); // create trade history

    //****For DemoAccount use, delete after server works********************************************
    //for storing a list of existing accounts

    private ArrayList<Account> totalAccounts = new ArrayList<Account>();

    public ArrayList<Account> getAccounts(){
        return totalAccounts;
    }

    public boolean isInAccounts(String username){
        for(int i=0; i < totalAccounts.size(); i++){
            if(totalAccounts.get(i).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public Account searchAccountsByUsername(String username) throws DoesNotExistException, BlankFieldException, AlreadyAddedException{
        //need to protect string inputs
        /*
        if(username.equals("")) throw new BlankFieldException();
        if(totalAccounts.size() ==0) throw new DoesNotExistException();
        if(totalAccounts.size() ==1) return totalAccounts.get(0);
        else {
            for (int i = 0; i < totalAccounts.size(); i++) {
                if(totalAccounts.get(i).getUsername().equals(username)) return totalAccounts.get(i);
            }
        }
        //did not find it
        throw new DoesNotExistException();
*/
        return totalAccounts.get(0);
    }

    //**********************************************************************************************


    public Account() {
        inventory = new Inventory();
        friends = new Friends();
        //checks to see if the account already exists, cant do this without the database

        //If(searchDatabase(Username)==true){throw new AlreadyExistsException;}

    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws NoSpacesException, TooLongException {
//length constraint: 20 characters
        if(username.isEmpty()){
            throw new IllegalArgumentException();
        } else
            //fields cant have spaces
                if(username.contains(" ")){
                    throw new NoSpacesException();
                    } else{
                    if(username.length()>20){
                        throw new TooLongException();
                    }else
                    this.username = username;
                    }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) throws NoSpacesException, TooLongException {
//length constraint: 20 characters
        if(city.isEmpty()){
            throw new IllegalArgumentException();
        } else
        if(city.length()>20){
            throw new TooLongException();
        } else
             //cities ARE allowed to have spaces
                {
                    this.city = city;
                }

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws IllegalEmailException, NoSpacesException, TooLongException {
//length constraint: 20 characters
        if(email.isEmpty()){
            throw new IllegalArgumentException();
        } else
            //must have a valid email
            if(!email.contains("@")){
                throw new IllegalEmailException();
            } else //fields cant have spaces
                if(email.contains(" ")){
                    throw new NoSpacesException();
                } else
                if(email.length()>20){
                    throw new TooLongException();
                } else
                {
                    this.email = email;
                   }

    }

    public Friends getFriends(){
        return friends;
    }

    //not sure how i can access the inventory otherwise - need this for tests
    //change if you have to


    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory anInventory){
        inventory = anInventory;
    }
}
