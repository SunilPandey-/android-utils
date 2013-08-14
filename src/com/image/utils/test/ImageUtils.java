package com.image.utils.test;

import jpm.white.fang.CustomActivity;
import jpm.white.fang.R;
import jpm.white.fang.R.id;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.utils.BitmapUtils;
import com.utils.OSUtils;
import com.utils.math.Vector2;

public class ImageUtils extends CustomActivity implements OnCheckedChangeListener{

	final int resId[] = {R.drawable.girl01, R.drawable.girl02};
	final String TAG = getClass().getName();
	Context context;
	
	ImageManipulator manipulator;
	@Override
	public void create(Context context) {
		setContentView(R.layout.imageutils_layout);
		this.context = context;
		Bitmap bitmap = BitmapUtils.decodeSampledBitmapFromResource(context, resId[1], 512, 512);
//		ImageView imgView = (ImageView) findViewById(R.id.imgView);
//		imgView.setImageBitmap(bitmap);
		
		Log.e(TAG, ""+ bitmap.getWidth() + " x " + bitmap.getHeight());
		
		initRadioButtons();
		manipulator = new ImageManipulator(this, bitmap);
	}

	private void initRadioButtons() {
		RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup);
		group.setOnCheckedChangeListener(this);
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case id.hue:
			OSUtils.showToast(context, "Hue");
			ImageMode.setMode(ImageMode.MODE_HUE);
			break;

		case id.saturation:
			OSUtils.showToast(context, "sat");
			ImageMode.setMode(ImageMode.MODE_SAT);
			break;
			
		case id.value:
			OSUtils.showToast(context, "val");
			ImageMode.setMode(ImageMode.MODE_VAL);
			break;
		}
	}
	
	
	final Vector2 initialTouch = new Vector2();
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (MotionEvent.ACTION_DOWN == event.getAction()){
			initialTouch.set(event.getX(), event.getY());
		}
		
		if (MotionEvent.ACTION_MOVE == event.getAction()){
			
			if (initialTouch.x < event.getX() - 5f){
				ImageMode.isTouched = true;
//				ImageMode.touch.setX(Math.abs(event.getX() - initialTouch.x));
				manipulator.update(1f, 0.02f, 0);
//				manipulator.valuesView.setText("Hue: " + ImageMode.getSAT_VAL());
			}else if (initialTouch.x > event.getX() + 5f){
				ImageMode.isTouched = true;
//				ImageMode.touch.setX(Math.abs(event.getX() - initialTouch.x));
//				ImageMode.updateSat(-0.05f);
				manipulator.update(-1f, -0.02f, 0);
//				manipulator.valuesView.setText("Hue: " + ImageMode.getSAT_VAL());
			}else{
				ImageMode.isTouched = false;
			}
		}
		
		if (MotionEvent.ACTION_UP == event.getAction()){
			ImageMode.isTouched =false;
			ImageMode.touch.reset();
		}
		
		
		
		return super.onTouchEvent(event);
	}

}
