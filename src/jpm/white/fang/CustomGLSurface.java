package jpm.white.fang;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.gl.triangle.TriangleRender;

public class CustomGLSurface extends GLSurfaceView{

	public CustomGLSurface(Context context) {
		super(context);
        setRenderer(new TriangleRender(context));
        setEGLContextClientVersion(2);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}

}
