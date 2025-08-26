package com.app.internetspeed;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.app.internetspeed.CustomAdapter.CustomAdapter;
import com.app.internetspeed.CustomAdapter.MyDatabaseHelper;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    RecyclerView recyclerView;

    MyDatabaseHelper myDB;
    ArrayList<String> time, type, ping, download,upload;
    private CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recyclerView);

        myDB = new MyDatabaseHelper(History.this);

        time = new ArrayList<>();
        type = new ArrayList<>();
        ping = new ArrayList<>();
        download = new ArrayList<>();
        upload = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(History.this,this ,time, type, ping , download , upload);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(History.this));

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor res = myDB.readAllData();
        if(res.getCount() == 0){
        }else{
            while (res.moveToNext()){
                time.add(res.getString(1));
                type.add(res.getString(2));
                ping.add(res.getString(3));
                download.add(res.getString(4));
                upload.add(res.getString(5));
            }
        }
    }
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menux, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.setting) {
            AlertDialog.Builder builder = new AlertDialog.Builder(History.this);
            builder.setMessage("All Data Will Be Clear!").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @SuppressLint("WrongConstant")
                public void onClick(DialogInterface dialog, int id) {

                    try {
                        myDB.deleteAllData();
                    } catch (Exception e) {
                        //no need
                    } finally {
                        Intent next = new Intent(History.this, MainActivity.class);
                        History.this.startActivity(next);
                        History.this.finish();
                    }
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.setTitle("Do You Want To Reset Data?");
            alert.show();
        }
        return super.onOptionsItemSelected(item);
    }

}