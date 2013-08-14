package com.image.utils.test;

import com.utils.math.Vector2;

import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;

public class ImageMode {
	public static final int MODE_HUE = 0x001;
	public static final int MODE_SAT = 0x002;
	public static final int MODE_VAL = 0x003;
	
	private static float SAT_VAL = 0;
	private static float HUE_VAL = 0;
	
	
	public volatile static int MODE = 0;
	
	public static void setMode(int new_mode) {
		ImageMode.MODE = new_mode;
	}
	
	public static Vector2 touch = new Vector2();
	public static boolean isTouched = false;

	public static void updateSat(float f) {
		SAT_VAL+= f;
		if (SAT_VAL > 1){
			SAT_VAL = 1;
		}else if(SAT_VAL < 0){
			SAT_VAL = 0;
		}
	}
	
	public static float getSAT_VAL() {
		return SAT_VAL;
	}
	
	public static float getHUE_VAL() {
		return HUE_VAL;
	}
	
	
	public static  void updateHue(float f) {
		HUE_VAL += f;
		if (ImageMode.HUE_VAL > 180){
			ImageMode.HUE_VAL = 180;
		}else if (ImageMode.HUE_VAL < -180){
			ImageMode.HUE_VAL = -180;
		}
	}
	
	
	public static void adjustHue(ColorMatrix cm, float value)
	{
	    value = cleanValue(value, 180f) / 180f * (float) Math.PI;
	    if (value == 0)
	    {
	        return;
	    }
	    float cosVal = (float) Math.cos(value);
	    float sinVal = (float) Math.sin(value);
	    float lumR = 0.213f;
	    float lumG = 0.715f;
	    float lumB = 0.072f;
	    float[] mat = new float[]
	    { 
	            lumR + cosVal * (1 - lumR) + sinVal * (-lumR), lumG + cosVal * (-lumG) + sinVal * (-lumG), lumB + cosVal * (-lumB) + sinVal * (1 - lumB), 0, 0, 
	            lumR + cosVal * (-lumR) + sinVal * (0.143f), lumG + cosVal * (1 - lumG) + sinVal * (0.140f), lumB + cosVal * (-lumB) + sinVal * (-0.283f), 0, 0,
	            lumR + cosVal * (-lumR) + sinVal * (-(1 - lumR)), lumG + cosVal * (-lumG) + sinVal * (lumG), lumB + cosVal * (1 - lumB) + sinVal * (lumB), 0, 0, 
	            0f, 0f, 0f, 1f, 0f, 
	            0f, 0f, 0f, 0f, 1f };
	    cm.postConcat(new ColorMatrix(mat));
	}

	private static float cleanValue(float p_val, float p_limit)
	{
	    return Math.min(p_limit, Math.max(-p_limit, p_val));
	}
	
	
}
