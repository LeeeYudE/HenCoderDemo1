package com.charco.android.hencoderdemo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void scale(View view) {
        startActivity(new Intent(this,ScaleActivity.class));
    }

    public void sector(View view) {
        startActivity(new Intent(this,SectorActivity.class));
    }

    public void circular(View view) {
        startActivity(new Intent(this,CircularActivity.class));
    }

    public void avatar(View view) {
        startActivity(new Intent(this,AvatarActivity.class));
    }

    public void imagetext(View view) {
        startActivity(new Intent(this,ImageTextActivity.class));
    }

    

}
