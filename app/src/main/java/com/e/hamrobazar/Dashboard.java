package com.e.hamrobazar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.e.hamrobazar.API.HamroBazarApi;
import com.e.hamrobazar.Adapter.ProductAdapter;
import com.e.hamrobazar.Adapter.ViewPageAdapter;
import com.e.hamrobazar.Model.Product;
import com.e.hamrobazar.URL.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends AppCompatActivity {
    ImageView btnUsers;
    ViewPager myView;
    CardView cardView;
    RecyclerView recyclerView, recycleView2;
    LinearLayout expandableViews;
    Button btnDown, btnUpArrows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        btnUsers = findViewById(R.id.btnUsers);
        myView = findViewById(R.id.myView);
        cardView = findViewById(R.id.cardView);
        expandableViews = findViewById(R.id.expandableViews);
        btnDown = findViewById(R.id.btnDown);
        btnUpArrows = findViewById(R.id.btnUpArrows);
        recyclerView = findViewById(R.id.recycleView);
        recycleView2=findViewById(R.id.recycleView2);
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(this);

        myView.setAdapter(viewPageAdapter);
        recycleView();
        recycleViewTwo();

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (expandableViews.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableViews.setVisibility(View.VISIBLE);
                    btnUpArrows.setVisibility(View.VISIBLE);
                    btnDown.setVisibility(View.GONE);
                } else {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableViews.setVisibility(View.GONE);
                    btnUpArrows.setVisibility(View.GONE);
                }
            }
        });

        btnUpArrows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (expandableViews.getVisibility() == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableViews.setVisibility(View.GONE);
                    btnUpArrows.setVisibility(View.GONE);
                    btnDown.setVisibility(View.VISIBLE);
                }
            }
        });

        btnUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, LoginPage.class);
                startActivity(intent);
            }
        });


    }

    private void recycleView() {
        HamroBazarApi hamroBazarApi = URL.getInstance().create(HamroBazarApi.class);
        Call<List<Product>> listCall = hamroBazarApi.getProduct();

        listCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(Dashboard.this, "Error code"+response.code(), Toast.LENGTH_SHORT).show();

                    return;
                }
                List<Product>productList =response.body();
                ProductAdapter productAdapter= new ProductAdapter(Dashboard.this,productList);
                recyclerView.setAdapter(productAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Dashboard.this,LinearLayoutManager.HORIZONTAL,false));
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(Dashboard.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }


    private void recycleViewTwo() {
        HamroBazarApi hamroBazarApi = URL.getInstance().create(HamroBazarApi.class);
        Call<List<Product>> listCall = hamroBazarApi.getProduct();

        listCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(Dashboard.this, "Error code"+response.code(), Toast.LENGTH_SHORT).show();

                    return;
                }
                List<Product>productList =response.body();
                ProductAdapter productAdapter= new ProductAdapter(Dashboard.this,productList);
                recycleView2.setAdapter(productAdapter);
                LinearLayoutManager linearLayoutManager= new LinearLayoutManager(Dashboard.this,LinearLayoutManager.HORIZONTAL,true);
//                linearLayoutManager.setReverseLayout(true);
                linearLayoutManager.setStackFromEnd(true);
                recycleView2.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(Dashboard.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}
