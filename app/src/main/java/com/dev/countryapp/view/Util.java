package com.dev.countryapp.view;

import android.content.Context;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dev.countryapp.R;

public class Util {

    public static void loadImage(ImageView imageView, String url, CircularProgressDrawable circularProgressDrawable){
        RequestOptions requestOptions=new RequestOptions()
                .placeholder(circularProgressDrawable)
                .error(R.mipmap.ic_launcher_round);

        Glide.with(imageView.getContext())
                .applyDefaultRequestOptions(requestOptions)
                .load(url)
                .into(imageView);

    }

    public static  CircularProgressDrawable getProgressDrawable(Context context){
        CircularProgressDrawable progressDrawable=new CircularProgressDrawable(context);
        progressDrawable.setStrokeWidth(10f);
        progressDrawable.setCenterRadius(50f);
        progressDrawable.start();

        return progressDrawable;
    }
}
