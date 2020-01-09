package com.e.hamrobazar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.e.hamrobazar.Adapter.ViewPageAdapter;

public class Dashboard extends AppCompatActivity {
     ImageView btnUsers;
     ViewPager myView;
     CardView cardView;
     LinearLayout expandableViews;
     Button btnDown ,btnUpArrows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        btnUsers=findViewById(R.id.btnUsers);
        myView=findViewById(R.id.myView);
        cardView=findViewById(R.id.cardView);
        expandableViews=findViewById(R.id.expandableViews);
        btnDown=findViewById(R.id.btnDown);
        btnUpArrows=findViewById(R.id.btnUpArrows);
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(this);

        myView.setAdapter(viewPageAdapter);

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View expandableView = null;
                if (expandableView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.VISIBLE);
                    btnUpArrows.setVisibility(View.VISIBLE);
                    btnDown.setVisibility(View.GONE);
                } else {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.GONE);
                    btnUpArrows.setVisibility(View.GONE);
                }
            }
        });

        btnUpArrows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View expandableView = null;
                if (expandableView.getVisibility()==View.VISIBLE){
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.GONE);
                    btnUpArrows.setVisibility(View.GONE);
                    btnDown.setVisibility(View.VISIBLE);
                }
            }
        });

        btnUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,LoginPage.class);
                startActivity(intent);
            }
        });
    }
}
