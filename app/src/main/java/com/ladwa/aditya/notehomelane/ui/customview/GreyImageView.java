package com.ladwa.aditya.notehomelane.ui.customview;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * A Custom {@link android.widget.ImageView} that adds Greyscale filter on touch
 * Created by Aditya on 14-Mar-17.
 */

public class GreyImageView extends android.support.v7.widget.AppCompatImageView {

    private final String TAG = GreyImageView.class.getSimpleName();

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
        final int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                if (getDrawable() != null) {
                    getDrawable().setColorFilter(filter);
                } else {
                    Log.e(TAG, "No image View Set");
                }
                return true;
            case MotionEvent.ACTION_UP:
                ColorMatrix colorMatrix = new ColorMatrix();
                ColorMatrixColorFilter colorfilter = new ColorMatrixColorFilter(colorMatrix);
                if (getDrawable() != null) {
                    getDrawable().setColorFilter(colorfilter);
                } else {
                    Log.e(TAG, "No image View Set");
                }
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }
}
