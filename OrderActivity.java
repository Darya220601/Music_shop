package com.example.musicshops;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
String emailText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle("Your Order");
        Intent recievedOrderIntent = getIntent();
        String userName = recievedOrderIntent.getStringExtra("userNameForIntent");
        String goodsName = recievedOrderIntent.getStringExtra("goodsNameForIntent");
        double price = recievedOrderIntent.getDoubleExtra("orderPrice",0.0);
        int quantity = recievedOrderIntent.getIntExtra("quantity",0);
        TextView orderTextView = findViewById(R.id.textViewOrder);
        emailText =userName+"\n"+goodsName+"\n"+price+"\n"+quantity;
        orderTextView.setText(emailText);
    }

    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL,"aremurfy@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT,"ORDER");
        intent.putExtra(Intent.EXTRA_TEXT,emailText);
        if(intent.resolveActivity(getPackageManager())!=null){
           startActivity(intent) ;
        }
    }
}