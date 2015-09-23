package com.example.this_is_ayan.findmyadvocate.Widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by this_is_ayan on 14-07-2015.
 */
public class MyTextViewRegularFont extends TextView {

    public MyTextViewRegularFont(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyTextViewRegularFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextViewRegularFont(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/DINPro_Regular.ttf");
        setTypeface(tf ,1);

    }

}
