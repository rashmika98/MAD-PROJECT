package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    //Arrays
    public  int[] slide_images = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3
    };

    public  String[] slide_headings = {
            "Your groceries without strees",
            "Amazing Discounts and Offers",
            "Speedy Doorstep Delivery"
    };

    public String[] slide_desc = {
            "Shop from a wide selection of grocery items from top brands, with over 50,000 items on our online supermarket.",
            "Cheaper prices than yur local supermarket, great discounts and cashback offers to top it off.",
            "Guaranteed delivery of groceries. Pay on delivery."
    };


    @Override
    public int getCount() {

        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.img1);
        TextView slideHeading = (TextView) view.findViewById(R.id.txt1);
        TextView slideDescription = (TextView) view.findViewById(R.id.txt2);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_desc[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((RelativeLayout)object);
    }
}
