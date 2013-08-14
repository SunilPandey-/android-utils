package com.test.matrixexample;

import jpm.white.fang.CustomActivity;
import android.content.Context;
import android.opengl.GLSurfaceView;

import com.utils.GlUtils;

public class MatrixJniStarter extends CustomActivity{
	GLSurfaceView glSurfaceView;

	@Override
	public void create(Context context) {
		loadLibrary();
		glSurfaceView = GlUtils.getGlSurfaceView(this, new MatrixJniRender(this), GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}

	private void loadLibrary() {
		System.loadLibrary("matrixjni");
	}
	
	public native void draw();
	public native void reshape(int w, int h);
	public native void create();
	

	@Override
	public void destroy() {
		
	}

	@Override
	public void pause() {
		glSurfaceView.onPause();
	}

	@Override
	public void resume() {
		glSurfaceView.onResume();
	}
}
