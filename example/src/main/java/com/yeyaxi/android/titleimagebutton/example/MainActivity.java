package com.yeyaxi.android.titleimagebutton.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.yeyaxi.android.titledimagebutton.TitledImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.button_vertical)
    TitledImageButton buttonVertical;
    @BindView(R.id.button_horizontal)
    TitledImageButton buttonHorizontal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);

        /*
         * As Butterknife doesn't support annotation OnClick for View.callOnClick,
         * we'll have to use the traditional way to set the onClickListener for our
         * buttons
         *
         * @link{https://github.com/JakeWharton/butterknife/issues/674}
         */

        buttonVertical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText("Vertical Button Clicked");
            }
        });

        buttonHorizontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText("Horizontal Button Clicked");
            }
        });
    }
}
