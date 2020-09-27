package com.sport.fantasypredictionnews;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.sport.fantasypredictionnews.Modal.MyModal;
import com.sport.fantasypredictionnews.RecyclerAdaper.RecyclerAdapter;

import java.util.ArrayList;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AdView adView;
    private DatabaseReference db;
    private ArrayList<MyModal> arraylist;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arraylist = new ArrayList<>();

        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        FirebaseRecyclerOptions<MyModal> options=new FirebaseRecyclerOptions.Builder<MyModal>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Team"), MyModal.class)
                .build();

        adapter = new RecyclerAdapter(options,this);
        recyclerView.setAdapter(adapter);

        FirebaseMessaging.getInstance().subscribeToTopic("notify")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                    }
                });
        adView();
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    private void adView() {
        AudienceNetworkAds.initialize(this);
        adView= new AdView(this,"239189574067822_251825139470932", AdSize.BANNER_HEIGHT_50);
        LinearLayout linearLayout= findViewById(R.id.banner_ads);
        linearLayout.addView(adView);
        adView.loadAd();
    }

    @Override
    protected void onDestroy() {
        if(adView != null){
            adView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.tips:
                Toast.makeText(getApplicationContext(),"Tips Will be Update Soon as Soon", Toast.LENGTH_LONG).show();
                break;

            case R.id.about:
                Toast.makeText(getApplicationContext(),"About as Will be Update Soon as Soon", Toast.LENGTH_LONG).show();
                break;

            case R.id.privacy:
                Intent intent=new Intent(Home.this, PrivacyPolicy.class);
                startActivity(intent);
                break;

            case R.id.share:
                Intent i=new Intent();
                i.setAction(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT,"Fantasy");
                String str="https://play.google.com/store/apps/details?id=com.sport.fantasypredictionnews";
                i.putExtra(Intent.EXTRA_TEXT,str);
                startActivity(Intent.createChooser(i,"Share on"));
        }
        return true;
    }
}