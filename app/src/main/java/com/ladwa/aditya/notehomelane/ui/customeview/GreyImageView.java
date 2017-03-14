package com.ladwa.aditya.notehomelane.ui.customeview;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Aditya on 14-Mar-17.
 */

public class GreyImageView extends android.support.v7.widget.AppCompatImageView {


    public GreyImageView(Context context) {
        super(context);
    }

    public GreyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GreyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean consumed = super.onTouchEvent(event);
        final int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                getDrawable().setColorFilter(filter);
                consumed = true;
                break;

            case MotionEvent.ACTION_UP:
                ColorMatrix colorMatrix = new ColorMatrix();
                ColorMatrixColorFilter colorfilter = new ColorMatrixColorFilter(colorMatrix);
                getDrawable().setColorFilter(colorfilter);
                break;
        }
        return consumed;
    }
}
