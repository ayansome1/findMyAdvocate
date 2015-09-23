package com.example.this_is_ayan.findmyadvocate.Widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by this_is_ayan on 14-07-2015.
 */
public class MyEditTextBoldFont extends EditText {

    public MyEditTextBoldFont(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyEditTextBoldFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditTextBoldFont(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/DINPro_Bold.ttf");
        setTypeface(tf ,1);

    }

}
