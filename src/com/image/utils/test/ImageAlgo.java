package com.image.utils.test;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

public class ImageAlgo {
	
	Bitmap bitmap;
	Paint paint;
	float saturation = 0;
	public ImageAlgo(Bitmap bitmap) {
		this.bitmap = bitmap;
		paint = new Paint();
	}
	
	float[] matrix = { 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, // red
			0, 0, 0, 0, 0, // green
			0, 0, 0, 0, 0, // blue
			1, 1, 1, 1, 1 // alpha
	};

	public void update(Canvas c) {
		c.drawBitmap(bitmap, 0, 0, paint);
		if (ImageMode.isTouched)
		switch (ImageMode.MODE) {
		case ImageMode.MODE_HUE:
			updateHue();
			break;
		case ImageMode.MODE_SAT:
			updateSaturation();
			break;
		case ImageMode.MODE_VAL:
			updateValue();
			break;
		}
	}
	
	
	ColorMatrix mat = new ColorMatrix();
	ColorMatrix satMat = new ColorMatrix();
	ColorMatrix hueMat = new ColorMatrix();
	
	private void updateHue() {
		System.out.println("updateHue");
		ImageMode.adjustHue(hueMat, ImageMode.getHUE_VAL());
		mat.setConcat(satMat, hueMat);
		paint.setColorFilter(new ColorMatrixColorFilter(mat));
	}

	

	private void updateValue() {
		
	}

	private void updateSaturation() {
		System.out.println("updatesat");
//		mat.reset();
		satMat.setSaturation(ImageMode.getSAT_VAL());
//		mat.setSaturation(ImageMode.getSAT_VAL());
		mat.setConcat(hueMat, satMat);
		paint.setColorFilter(new ColorMatrixColorFilter(mat));
	}
}
