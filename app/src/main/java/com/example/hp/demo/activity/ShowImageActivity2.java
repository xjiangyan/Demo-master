package com.example.hp.demo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.hp.demo.R;

public class ShowImageActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image2);
        ImageView viewById = (ImageView) findViewById(R.id.imageview);
        Intent intent = getIntent();
        byte [] bis=intent.getByteArrayExtra("imageinfo");
        Bitmap bitmap= BitmapFactory.decodeByteArray(bis, 0, bis.length);
        viewById.setImageBitmap(bitmap);

    }
}
