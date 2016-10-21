package com.yeyaxi.android.titledimagebutton;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

//
// Created by Yaxi on 21/10/2016.
// Copyright (c) 2016. All rights reserved.
//

public class TitledImageButton extends LinearLayout {

    @BindView(R2.id.container)
    LinearLayout containerView;
    @BindView(R2.id.icon)
    ImageView iconView;
    @BindView(R2.id.title)
    TextView titleView;

    private OnTouchListener onTouchListener;

    private int iconRes;
    private CharSequence title;
    private int titleStyleRes;
    private ColorStateList titleColor;
    private ColorStateList tint;
    private ColorStateList backgroundColor;
    private boolean enabled;


    public TitledImageButton(Context context) {
        super(context);
        commonInit(context, null);
    }

    public TitledImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        commonInit(context, attrs);
    }

    public TitledImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        commonInit(context, attrs);
    }

    @TargetApi(21)
    public TitledImageButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        commonInit(context, attrs);
    }

    private void commonInit(Context context, AttributeSet attrs) {
        TypedArray ta = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.TitledImageButton, 0, 0);
        try {
            iconRes = ta.getResourceId(R.styleable.TitledImageButton_tib_icon, 0);
            title = ta.getText(R.styleable.TitledImageButton_tib_title);
            titleStyleRes = ta.getResourceId(R.styleable.TitledImageButton_tib_titleStyle, 0);
            titleColor = ta.getColorStateList(R.styleable.TitledImageButton_tib_titleColor);
            tint = ta.getColorStateList(R.styleable.TitledImageButton_tib_tint);
            backgroundColor = ta.getColorStateList(R.styleable.TitledImageButton_tib_backgroundColor);
            enabled = ta.getBoolean(R.styleable.TitledImageButton_tib_enabled, true);
        } finally {
            ta.recycle();
        }

        View.inflate(context, R.layout.layout_titled_image_button, this);
        ButterKnife.bind(this);

        setEnabled(enabled);

        setIconTint(tint);
        setIconView(iconRes);
        setTitle(title);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            titleView.setTextAppearance(titleStyleRes);
        } else {
            titleView.setTextAppearance(getContext(), titleStyleRes);
        }

        setTitleColor(titleColor);
        setBackgroundColor(backgroundColor);

        containerView.setOnTouchListener(getOnTouchListener());
    }

    public void setTitleView(@StringRes int resId) {
        titleView.setText(resId);
    }

    public void setTitle(CharSequence text) {
        titleView.setText(text);
    }

    public void setTitle(String text) {
        titleView.setText(text);
    }

    public void setIconView(@DrawableRes int resId) {
        iconView.setImageResource(resId);
    }

    public void setTitleColor(ColorStateList color) {
        if (color != null) {
            titleView.setTextColor(color);
        }
    }

    public void setIconTint(ColorStateList colorStateList) {
        if (colorStateList != null) {
            iconView.setColorFilter(colorStateList.getColorForState(getDrawableState(), ContextCompat.getColor(getContext(), 0)));
        }
    }

    public void setBackgroundColor(ColorStateList colorStateList) {
        if (colorStateList != null) {
            containerView.setBackgroundColor(colorStateList.getColorForState(getDrawableState(), ContextCompat.getColor(getContext(), 0)));
        }
    }

    private OnTouchListener getOnTouchListener() {
        if (onTouchListener == null) {
            onTouchListener = new OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            setPressed(true);
                            break;
                        case MotionEvent.ACTION_UP:
                            setPressed(false);
                            Rect rect = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                            if (rect.contains((int)motionEvent.getX(), (int)motionEvent.getY())) {
                                // dispatch onClick
                                TitledImageButton.this.callOnClick();
                            }
                            break;
                    }
                    return true;
                }
            };
        }
        return onTouchListener;
    }

    @Override
    public boolean isInEditMode() {
        return true;
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        if (pressed) {
            // change the content's alpha
            changeAlpha(0.4f);
        } else {
            changeAlpha(1f);
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        containerView.setEnabled(enabled);
        iconView.setEnabled(enabled);
        titleView.setEnabled(enabled);
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        setBackgroundColor(backgroundColor);
        setIconTint(tint);
        setTitleColor(titleColor);
    }

    private void changeAlpha(float alpha) {
        containerView.setAlpha(alpha);
        titleView.setAlpha(alpha);
        iconView.setAlpha(alpha);
    }
}
