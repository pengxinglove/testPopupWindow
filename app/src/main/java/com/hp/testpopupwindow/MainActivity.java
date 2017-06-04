package com.hp.testpopupwindow;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {

    private  Button topButton;
    PopupWindow popupWindow = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         topButton = (Button)findViewById(R.id.topBtn);
        Button downBtn = (Button)findViewById(R.id.downBtn);
        downBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View popupView = getLayoutInflater().inflate(R.layout.popup_down, null);
                int height = popupView.getMeasuredHeight();
                popupView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                height = popupView.getMeasuredHeight();

                int[] locations = new int[2];
                view.getLocationOnScreen(locations);
                Display display = getWindowManager().getDefaultDisplay();


                popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setTouchable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
                if(locations[1] + height < display.getHeight()){
                    popupWindow.showAsDropDown(view);
                }else{
                    popupWindow.showAtLocation(topButton, Gravity.TOP, locations[0], locations[1]- height);
                }

            }
        });

    }
}
