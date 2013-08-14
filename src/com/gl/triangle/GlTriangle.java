package com.gl.triangle;

import com.utils.GlUtils;

import jpm.white.fang.CustomActivity;
import android.content.Context;
import android.opengl.GLSurfaceView;

public class GlTriangle extends CustomActivity{
	GLSurfaceView glSurfaceView;
	
	@Override
	public void create(Context context) {
		glSurfaceView = GlUtils.getGlSurfaceView(this, new TriangleRender(this), GLSurfaceView.RENDERMODE_CONTINUOUSLY);
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