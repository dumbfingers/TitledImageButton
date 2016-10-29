# TitledImageButton

I create this library simply because I'm tired of wrapping an `ImageView` and a `TextView` inside a layout just to simulate a button with title and icon.

Yes and you can add icon to a `Button` via methods like `drawableLeft/drawableTop`. However I found these methods never suit my need and you have very limited options to adjust either the icon or the title. So I create this library and hopefully it can save you some hassel when you have the requirement of fiddling with "Button with Image".

## Introduction

This is a library for Android simply to solve the issue that Android is lack of a proper button with both icon and title.

With this library you can add icon to your button and it also provides the ability to set the button's orientation.

## Example

![img](https://raw.githubusercontent.com/dumbfingers/TitledImageButton/master/device-2016-10-29-145306.png)

The upper is the button with `vertical` orientation,
and the bottom shows the `horizontal` orientation.

## Customization
TitledImageButton allows you to have various customisation to meet your needs.

Here are all the customisation options:

```
  <com.yeyaxi.android.titledimagebutton.TitledImageButton
        android:id="@+id/button_horizontal"
        android:layout_width="wrap_content"
        android:layout_height="70dp"
        app:tib_backgroundColor="@color/colorPrimary"
        app:tib_icon="@drawable/ic_launcher"
        app:tib_orientation="horizontal"
        app:tib_tint="@color/colorAccent"
        app:tib_title="Image Button"
        app:tib_titleStyle="@style/horizontalButtonTextStyle"
        app:tib_enabled="true" />        
```

* `tib_backgroundColor`: the overall background colour of this button, can be either a colour or a `ColorStateList`
* `tib_icon`: the icon of this button
* `tib_tint`: this option allows you to apply an additional tint to your icon
* `tib_title`: this is the button's title
* `tib_titleStyle`: you can provide your title's text style here (title's font, size, etc)
* `tib_orientation`: `vertical`: icon on the top with title under it. `horizontal`: icon on the left and title on the right
* `tib_titleColor`: this is a convenient method if you just want to set the title colour

Please feel free to use my library and also please let me know if there are any bugs and parts that needs any improvement.

All contributions are appreciated.
