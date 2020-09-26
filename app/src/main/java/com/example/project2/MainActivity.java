package com.example.project2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity = 2;
    int priceCreamTopping = 1;
    int priceChocolateTopping = 2;
    int coffeePrice = 5;
    int totalPrice = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        CheckBox cream = findViewById(R.id.checkbox_cream);
        CheckBox chocolate = findViewById(R.id.checkbox_chocolate);
        calculateToppingsPrice(cream.isChecked(), chocolate.isChecked());
        EditText name = findViewById(R.id.name);
        String message = name.getText().toString() + "\n";
        message += "Add whipped cream : " + cream.isChecked() + "\n";
        message += "Add chocolate : " + chocolate.isChecked() + "\n";
        message += "Quantity: " + quantity + "\n";
        message += "Total price: " + NumberFormat.getCurrencyInstance().format(totalPrice) + "\n";
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    private void calculateToppingsPrice(Boolean cream, Boolean chocolate){
        if (cream && chocolate){
            totalPrice += ((priceChocolateTopping + priceCreamTopping)*quantity);
        } else if (cream){
            totalPrice += (priceCreamTopping * quantity);
        } else {
            totalPrice += (priceChocolateTopping *quantity);
        }
    }

    private void display() {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }

    public void minus(View view) {
        decrement();
        display();
    }
    public void plus(View view) {
        increment();
        display();
    }

    private void decrement(){
        quantity--;
    }
    private void increment(){
        quantity++;
    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.message);
        priceTextView.setText(message);
    }
}