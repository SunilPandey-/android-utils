package com.image.utils.test;

import jpm.white.fang.R;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ImageManipulator {
	Bitmap bitmap;
	private final Handler mHandler = new Handler();
	Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			draw();
		}
	};
	
	ImageUtils context;
	SurfaceView surfaceView;
	SurfaceHolder holder;
	ImageAlgo algo;
	public TextView valuesView;
	public ImageManipulator(ImageUtils context, Bitmap bitmap) {
		this.bitmap = bitmap;
		this.context = context;
		
		surfaceView = new SurfaceView(context);
		
		valuesView = getTextView();
		
		FrameLayout frame = (FrameLayout) context.findViewById(R.id.frame);
        frame.addView(surfaceView);
        frame.addView(valuesView);
        
        holder = surfaceView.getHolder();
        
        draw();
        
        algo = new ImageAlgo(bitmap);
	}
	
	
	
	private TextView getTextView() {
		TextView valuesView = new TextView(context);
		valuesView.setBackgroundColor(Color.GRAY);
		valuesView.setTextColor(Color.GREEN);
		valuesView.setTextSize(20);
		valuesView.setText("----------");
		valuesView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		return valuesView;
	}
	
	public void update(float hue, float sat, float value) {
		switch (ImageMode.MODE) {
		case ImageMode.MODE_HUE:
			ImageMode.updateHue(hue);
			valuesView.setText("Hue: "+ ImageMode.getHUE_VAL());
			break;
		case ImageMode.MODE_SAT:
			ImageMode.updateSat(sat);
			valuesView.setText("Sat:" + ImageMode.getSAT_VAL());
			break;
		case ImageMode.MODE_VAL:
			break;
		}
	}
	
	private void draw() {
		Canvas c = null;
		try {
			c = holder.lockCanvas();
			if (c != null) {
				algo.update(c);
			}
		} finally {
			if (c != null)
				holder.unlockCanvasAndPost(c);
		}
		mHandler.removeCallbacks(runnable);
		mHandler.postDelayed(runnable, 1000 / 60);
	}
}
