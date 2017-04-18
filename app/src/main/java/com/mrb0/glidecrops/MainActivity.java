package com.mrb0.glidecrops;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String IMAGE_URL_SHORT = "https://placehold.it/300x100";
    private static final String IMAGE_URL_TALL = "https://placehold.it/300x400";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        // Load the short image first

        Glide
                .with(MainActivity.this)
                .load(IMAGE_URL_SHORT)
                .into(imageView);

        // Wait a couple seconds, then replace the current view with a taller one

        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                changeImage();
            }
        }, 2000);
    }

    private void changeImage() {
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        final ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();

        // Verify that the ImageView still has WRAP_CONTENT on height
        if (layoutParams.height != ViewGroup.LayoutParams.WRAP_CONTENT) {
            throw new IllegalStateException(String.format((Locale)null, "ImageView expected to have WRAP_CONTENT height, but it had %d instead", layoutParams.height));
        }

        // Replace the image with a taller one
        Glide
                .with(MainActivity.this)
                .load(IMAGE_URL_TALL)
                .into(imageView);
    }
}
