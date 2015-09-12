package com.example.sergbek.pushnotifications.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sergbek.pushnotifications.model.NotificationEntity;
import com.example.sergbek.pushnotifications.R;
import com.example.sergbek.pushnotifications.adapter.RecyclerViewAdapter;
import com.example.sergbek.pushnotifications.database.Database;

import java.util.List;


public class SecondActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private List<NotificationEntity> mNotificationList;
    private RecyclerViewAdapter mAdapter;
    private Database mDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerView);

        mDatabase=new Database(this);

        mNotificationList=mDatabase.getAllNotification();

        mAdapter =new RecyclerViewAdapter(mNotificationList,this);
        mLinearLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_second_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id==R.id.btn_update_MSA){
            mNotificationList=mDatabase.getAllNotification();
            mAdapter=new RecyclerViewAdapter(mNotificationList,this);
            mRecyclerView.setAdapter(mAdapter);
        }


        return super.onOptionsItemSelected(item);

    }
}
