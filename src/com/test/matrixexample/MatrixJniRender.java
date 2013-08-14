package com.test.matrixexample;

import javax.microedition.khronos.egl.EGLConfig;

import android.content.Context;
import jpm.white.fang.CustomRenderer;

public class MatrixJniRender extends CustomRenderer {

	MatrixJniStarter context;
	public MatrixJniRender(MatrixJniStarter context) {
		super(context);
		this.context = context;
	}

	@Override
	public void surfaceCreated(EGLConfig config) {
		context.create();
	}

	@Override
	public void surfaceChanged(int width, int height) {
		context.reshape(width, height);
	}

	@Override
	public void draw() {
		context.draw();
	}

	@Override
	public Context getContext(Context context) {
		return context;
	}

}
