package com.gl.triangle;

import com.utils.GlUtils;

import android.content.Context;
import android.opengl.GLSurfaceView;
import jpm.white.fang.CustomActivity;

public class BlueTriangle extends CustomActivity {
	GLSurfaceView glSurfaceView;

	@Override
	public void create(Context context) {
		glSurfaceView = GlUtils.getGlSurfaceView(this, new BTriangle(this),
						GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}

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
