package com.gracehoppers.jlovas.bookwrm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class ViewBookActivity extends ActionBarActivity {

    /*
    When a user clicks on a book in their inventory, they can view its details, choose to edit it, or delete it.
     */

    TextView bookTitle;
    TextView bookAuthor;
    TextView category;
    TextView quantity;
    TextView privacy;
    TextView description;
    RatingBar rating;
    Button deleteButton;
    Button editButton;
    //put photo stuff here

    private SaveLoad saveload = new SaveLoad();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);

        //set up UI parts-------------------------------------------------
        bookTitle= (TextView)findViewById(R.id.BookTitletextView);
        bookAuthor= (TextView)findViewById(R.id.authortextView);
        category= (TextView)findViewById(R.id.categorytypetextview);
        quantity= (TextView)findViewById(R.id.quantitynumtextview);
        privacy= (TextView)findViewById(R.id.privatebooktextview);
        description= (TextView)findViewById(R.id.commentsdesctextview);
        rating= (RatingBar)findViewById(R.id.qualityrating);
        rating.setNumStars(5);
        deleteButton= (Button)findViewById(R.id.deleteBookbutton);
        editButton= (Button)findViewById(R.id.EditBookButton);
        //----------------------------------------------------------------



        int pos = getIntent().getIntExtra("listPosition", 0); //https://youtu.be/ViwazAAR-vE, TZCoder, 2015-30-10

        Account account;

        account = saveload.loadFromFile(ViewBookActivity.this);

        Book tempBook = account.getInventory().getBookByIndex(pos);

        bookTitle.setText(tempBook.getTitle());
        bookAuthor.setText(tempBook.getAuthor());
        category.setText(tempBook.getCategory());
        quantity.setText(tempBook.getQuantity() + "");
        description.setText(tempBook.getDescription());
        rating.setRating((float) tempBook.getQuality());

        if(tempBook.isPrivate()){
            privacy.setText("Private Book");
        } else privacy.setText("Public Book");
    //put photo stuff here

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ask the user if they're sure they want to delete this book
                openDialog();


            }
        });


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewBookActivity.this, EditBookActivity.class);
                startActivity(intent);


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void openDialog() {
        //asks the user if they're sure they want to delete the book.
        //http://stackoverflow.com/questions/31151306/how-to-show-a-dialog-with-android-studio heloisasim 2015-31-10
        AlertDialog.Builder singleInfo=new AlertDialog.Builder(this);
        singleInfo.setTitle("Delete Book");
        singleInfo.setMessage("Are you sure you want to delete this book?");
        singleInfo.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
               //user wants to delete the book
                finish();
            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                     // user does not want to delete the book, do nothing
                        dialog.dismiss();
                    }

                });
        AlertDialog SingleInfo=singleInfo.create();
        SingleInfo.show();
    }



}
