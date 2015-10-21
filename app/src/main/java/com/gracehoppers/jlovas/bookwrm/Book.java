package com.gracehoppers.jlovas.bookwrm;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

/**
 * Created by jlovas on 10/17/15.
 * Hello I am a book!
 * I contain:
 *  -the name of the book
 *  -author
 *  -quantity
 *  -category (easier as an enum I would think)
 *  -private/public view ability
 *  -comments/description
 *  -photo (will figure this out in time)
 *
 *  -setters and getters for each
 */
public class Book {

    public Book() {

    }

    private enum Category{
            //Enum will give each a number by default. NONE=0, HARDBACK=1, etc
        NONE, HARDBACK, PAPERBACK, AUDIOBOOK, COMIC, TEXTBOOK, PICTURE, BRAILLE, REFERENCE, RECIPE, DIY;

    }

    private String title;
    private String author;
    private int quantity;
    private Category category;
    private boolean isPrivate;
    private String description;
    ImageView photo; //will figure out if they change the image stuff later


    public Book(ImageView photo) {
        //no args? just creates a book
        //default values here - may remove these and put into setters
        this.title = "Untitled";
        this.author= "No author specified";
        this.quantity=1;
        this.category=category.NONE; //will see if this works the way I want it to
        this.isPrivate=false; //false= public, true=private
        this.description = "None";
        this.photo= photo; //insert a default image like some grey image
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title.isEmpty()){
            //throw an exception
            throw new IllegalArgumentException();
        }
        else {
            this.title = title;
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if(author.isEmpty()){
            //throw exception
            throw new IllegalArgumentException();
        }else {
            this.author = author;
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity<1){
            //throw exception
            throw new IllegalArgumentException();
        }else {
            this.quantity = quantity;
        }
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {

            this.description = description;

    }

    public void setQuantity(String quantity){
        int converted =Integer.parseInt(quantity);
        if(converted <=0){
            throw new IllegalArgumentException();
        }
        else {
            this.quantity = Integer.parseInt(quantity); //NEED TO CATCH EXCEPTION IF THIS DOESN'T WORK
        }
    }
/*
will return to this when understood better
    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
    //some sort of protection
        this.photo = photo;
    }
    */
}