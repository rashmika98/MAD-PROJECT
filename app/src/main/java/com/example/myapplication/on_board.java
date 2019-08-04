package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class on_board extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;

    private TextView[] mdots;

    private SliderAdapter sliderAdapter;

    private Button mNextBtn;
    private Button mBackBtn;

    private  int mCurrentPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotLayout);

        mNextBtn = (Button) findViewById(R.id.button6);
        mBackBtn = (Button) findViewById(R.id.button5);

        sliderAdapter = new SliderAdapter(this);

        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);

        //OnClickListener

        mNextBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                mSlideViewPager.setCurrentItem(mCurrentPage + 1);
            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mSlideViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });
    }

    public void addDotsIndicator(int position){

        mdots = new TextView[3];
        mDotLayout.removeAllViews();

        for (int i = 0; i < mdots.length; i++ ){
            mdots[i] = new TextView(this);
            mdots[i].setText(Html.fromHtml("&#8226;"));
            mdots[i].setTextSize(35);
            mdots[i].setTextColor(getResources().getColor(R.color.grey));

            mDotLayout.addView(mdots[i]);
        }

        if(mdots.length > 0){

            mdots[position].setTextColor(getResources().getColor(R.color.pureBlack));
        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDotsIndicator(position);

            mCurrentPage = position;

            if(position == 0)
            {
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(false);

                mBackBtn.setVisibility(View.INVISIBLE);

                mNextBtn.setText("NEXT");
                mBackBtn.setText("");
            }
            else if(position == mdots.length - 1)
            {
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);

                mBackBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("FINISH");
                mBackBtn.setText("BACK");
            }
            else
            {
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);

                mBackBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("NEXT");
                mBackBtn.setText("BACK");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
