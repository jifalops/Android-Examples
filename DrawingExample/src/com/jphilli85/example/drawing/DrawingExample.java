package com.jphilli85.example.drawing;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;

// make lots of activities

public class DrawingExample extends Activity {
	TouchCircle mTouchCircle;

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        mTouchCircle = new TouchCircle(this);
        
        setContentView(mTouchCircle);
        
    }
    
    public class TouchCircle extends View {
        private ShapeDrawable mDrawable;

        public TouchCircle(Context context) {
            super(context);

            int x = 10;
            int y = 10;
            int width = 300;
            int height = 50;

            mDrawable = new ShapeDrawable(new OvalShape());
            mDrawable.getPaint().setColor(0xff74AC23);
            mDrawable.setBounds(x, y, x + width, y + height);
        }

        protected void onDraw(Canvas canvas) {
            mDrawable.draw(canvas);
        }
    }
}