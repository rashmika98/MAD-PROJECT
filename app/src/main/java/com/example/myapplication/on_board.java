package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private Button mLogBtn;


    private  int mCurrentPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);

        if(restorePrefData()){

            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);

        }

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotLayout);

        mNextBtn = (Button) findViewById(R.id.button6);
        mBackBtn = (Button) findViewById(R.id.button5);
        mLogBtn = (Button) findViewById(R.id.button7);

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

        mLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                savePrefsData();
                finish();
            }
        });
    }

    public boolean restorePrefData() {
        SharedPreferences pref = getApplication().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroActivityOpenedBefore = pref.getBoolean("isIntroOpened", false);
        return isIntroActivityOpenedBefore;
    }

    public void savePrefsData(){
        SharedPreferences pref = getApplication().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.commit();
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
                mLogBtn.setVisibility(View.INVISIBLE);

                mNextBtn.setText("NEXT");
                mBackBtn.setText("");
            }
            else if(position == mdots.length - 1)
            {
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);

                mBackBtn.setVisibility(View.VISIBLE);
                mLogBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("FINISH");
                mBackBtn.setText("BACK");
            }
            else
            {
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);

                mBackBtn.setVisibility(View.VISIBLE);
                mLogBtn.setVisibility(View.INVISIBLE);

                mNextBtn.setText("NEXT");
                mBackBtn.setText("BACK");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
