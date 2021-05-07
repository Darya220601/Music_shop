package com.example.musicshops;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
int quantity = 0;
double price;
EditText userNameEditText;
ArrayList spinnerArrayList;
ArrayAdapter stringArrayAdapter;
Spinner spinner;
ImageView imageView;

HashMap priceHashMap;
String goodsName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       createSpinner();
        userNameEditText = findViewById(R.id.editTextTextPersonName);
imageView =findViewById(R.id.imageView2);
createMap();

    }

    public void createSpinner(){
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList<>();
        spinnerArrayList.add("guitar");
        spinnerArrayList.add("keyboard");
        spinnerArrayList.add("drums");
        stringArrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, spinnerArrayList);
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(stringArrayAdapter);
    }

    public void increaseQuantity(View view) {
        quantity++;
        TextView quatityText = findViewById(R.id.textViewQuantityCount);
        quatityText.setText(String.valueOf(quantity));
        TextView textViewPrice = findViewById(R.id.priceViewCount);
        textViewPrice.setText(String.valueOf(price*quantity));
    }

    public void decreaseQuantity(View view) {
        quantity--;
        if(quantity<0){
            quantity=0;
        }
        TextView quatityText = findViewById(R.id.textViewQuantityCount);
        quatityText.setText(String.valueOf(quantity));
        TextView textViewPrice = findViewById(R.id.priceViewCount);
        textViewPrice.setText(String.valueOf(price*quantity));
    }
    void createMap(){
        priceHashMap = new HashMap();
        priceHashMap.put("guitar",1500.0);
        priceHashMap.put("drums",2000.0);
        priceHashMap.put("keyboard",2500.0);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double) priceHashMap.get(goodsName);
        TextView textViewPrice = findViewById(R.id.priceViewCount);
        textViewPrice.setText(String.valueOf(price*quantity));
switch(goodsName){
    case"keyboard":
      imageView.setImageResource(R.drawable.keyboard);
      break;
    case"drums":
        imageView.setImageResource(R.drawable.drums);
        break;
    case "guitar":
        imageView.setImageResource(R.drawable.guitar);
        break;
}

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {

        Order order = new Order();
        order.userName = userNameEditText.getText().toString();
        Log.d("printUserName", order.userName); //d - debugging. В режиме дебага можно проссматривать. PrintUserName - ключ в дебаге
        order.goodsName = goodsName;
        order.orderPrice = quantity*price;
        order.quantite = quantity;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userNameForIntent",order.userName);
        orderIntent.putExtra("goodsNameForIntent", order.goodsName);
        orderIntent.putExtra("orderPrice", order.orderPrice);
        orderIntent.putExtra("quantity", order.quantite);



        startActivity(orderIntent);




    }
}