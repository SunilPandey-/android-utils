package com.gl.triangle;

import javax.microedition.khronos.egl.EGLConfig;

import jpm.white.fang.CustomRenderer;
import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.SystemClock;

import com.utils.GlUtils;

public class TriangleRender extends CustomRenderer{
	
	private final boolean isTextureMode = true;
	
	private float[] mViewMatrix = new float[16];
	private float[] mMVPMatrix = new float[16];
	 
	private final int mStrideBytes = 7 * 4;
	 
	private final int mPositionOffset = 0;
	private final int mPositionDataSize = 3;
	private final int mColorOffset = 3;
	private final int mColorDataSize = 4;
	private int mMVPMatrixHandle;
	private int mPositionHandle;
	private int mColorHandle;
	private float[] mModelMatrix = new float[16];
	    
	Triangle triangle;
	public TriangleRender(Context context) {
		super(context);
		triangle = new Triangle();
	}

	@Override
	public void draw() {
		long time = SystemClock.uptimeMillis() % 10000L;
        float angleInDegrees = (360.0f / 10000.0f) * ((int) time);
        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.rotateM(mModelMatrix, 0, angleInDegrees, 0.0f, 0.0f, 1.0f);
        drawTriangle();
	}

	private float[] mProjectionMatrix = new float[16];
	@Override
	public void surfaceChanged(int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        final float ratio = (float) width / height;
        final float left = -ratio;
        final float right = ratio;
        final float bottom = -1.0f;
        final float top = 1.0f;
        final float near = 1.0f;
        final float far = 10.0f;
     
        Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, near, far);
	}

	@Override
	public void surfaceCreated(EGLConfig config) {
	    final float eyeX = 0.0f;
	    final float eyeY = 0.0f;
	    final float eyeZ = 1.5f;
	 
	    final float lookX = 0.0f;
	    final float lookY = 0.0f;
	    final float lookZ = -5.0f;
	 
	    final float upX = 0.0f;
	    final float upY = 1.0f;
	    final float upZ = 0.0f;
	    Matrix.setLookAtM(mViewMatrix, 0, eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ);
	    
	    
	    int vertShader = GlUtils.loadShader(triangle.vertexShader, GLES20.GL_VERTEX_SHADER);
	    int fragShader = GlUtils.loadShader(triangle.fragmentShader, GLES20.GL_FRAGMENT_SHADER);
	    int programHandle = GlUtils.createProgramHandle(vertShader, fragShader);
	    
	    mMVPMatrixHandle = GLES20.glGetUniformLocation(programHandle, "u_MVPMatrix");
	    mPositionHandle = GLES20.glGetAttribLocation(programHandle, "a_Position");
	    mColorHandle = GLES20.glGetAttribLocation(programHandle, "a_Color");
	 
	    GLES20.glUseProgram(programHandle);
	}

	@Override
	public Context getContext(Context context) {
		return context;
	}
	
	public void drawTriangle() {
		triangle.mTriangle1Vertices.position(mPositionOffset);
	    GLES20.glVertexAttribPointer(mPositionHandle, mPositionDataSize, GLES20.GL_FLOAT, false,mStrideBytes, triangle.mTriangle1Vertices);
	 
	    GLES20.glEnableVertexAttribArray(mPositionHandle);
	    triangle.mTriangle1Vertices.position(mColorOffset);
	    GLES20.glVertexAttribPointer(mColorHandle, mColorDataSize, GLES20.GL_FLOAT, false,mStrideBytes, triangle.mTriangle1Vertices);
	 
	    GLES20.glEnableVertexAttribArray(mColorHandle);
	    Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);
	    Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0);
	 
	    GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);
	    
	    if(isTextureMode){
	    	GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
	    }else{
	    	GLES20.glDrawArrays(GLES20.GL_LINE_LOOP, 0, 3);
	    }
	    
		
	}


}
