package com.gl.triangle;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;

import jpm.white.fang.CustomRenderer;
import android.content.Context;
import android.opengl.GLES20;

import com.utils.BufferUtils;
import com.utils.GlUtils;

public class BTriangle extends CustomRenderer{
	private final FloatBuffer triangleBuffer;
	private int programHandle;
	public BTriangle(Context context) {
		super(context);
		float triangle_vertices[] = {
			     0.0f,  0.8f,
			    -0.8f, -0.8f,
			     0.8f, -0.8f
			  };
		triangleBuffer = BufferUtils.getFloatBuffer(triangle_vertices);
	}

	public final String vertShader = "attribute vec2 coord2d;\n"+
										"void main(void) { \n"+
									"gl_Position = vec4(coord2d, 0.0, 1.0); \n"+
									"}\n";
	
	public final String fragShader = "void main(void) {\n" +
		  "gl_FragColor[0] = gl_FragCoord.x/640.0; \n"+
		  "gl_FragColor[1] = gl_FragCoord.y/480.0; \n"+
		  "gl_FragColor[2] = 0.5;\n"+
		  "} \n";
	
	public final String fragShader1 = "void main(void) {\n" +
			  "gl_FragColor[0] = 0.0; \n"+
			  "gl_FragColor[1] = 0.0; \n"+
			  "gl_FragColor[2] = 1.0;\n"+
			  "} \n";

	@Override
	public void draw() {
		 GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		 GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
		 
		 GLES20.glUseProgram(programHandle);
		 GLES20.glEnableVertexAttribArray(attribute_coord2d);
		

		 GLES20.glVertexAttribPointer(attribute_coord2d, 2, GLES20.GL_FLOAT, false, 0, triangleBuffer);
		 GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
		 GLES20.glDisableVertexAttribArray(attribute_coord2d);
		  
	}

	@Override
	public void surfaceChanged(int width, int height) {
		
	}

	int attribute_coord2d;
	@Override
	public void surfaceCreated(EGLConfig config) {
		programHandle = GlUtils.createProgramHandle(vertShader, fragShader1);
		
		String attribute_name = "coord2d";
		  attribute_coord2d = GLES20.glGetAttribLocation(programHandle, attribute_name);
	}

	@Override
	public Context getContext(Context context) {
		return null;
	}
}
