package com.utils;

import jpm.white.fang.CustomRenderer;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

public class GlUtils {
	public static int getGLSupport(Context context) {
		 final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		    final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
		    final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;
		    if (supportsEs2){
		    	return 2;
		    }else{
		    	return 1;
		    }
	}
	
	
	public static GLSurfaceView getGlSurfaceView(Activity context, CustomRenderer renderer, int renderMode){
		GLSurfaceView glSurfaceView = new GLSurfaceView(context);
		glSurfaceView.setEGLContextClientVersion(getGLSupport(context));
		context.setContentView(glSurfaceView);
		glSurfaceView.setRenderer(renderer);
		return glSurfaceView;
	}
	
	
	public static int loadShader(String shader, int shaderType) {
		int shaderHandle = GLES20.glCreateShader(shaderType);
		if (shaderHandle != 0)
		{
		    GLES20.glShaderSource(shaderHandle, shader);
		    GLES20.glCompileShader(shaderHandle);
		    final int[] compileStatus = new int[1];
		    GLES20.glGetShaderiv(shaderHandle, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
		    if (compileStatus[0] == 0)
		    {
		        GLES20.glDeleteShader(shaderHandle);
		        shaderHandle = 0;
		    }
		}
		 
		if (shaderHandle == 0)
		{
			String temp;
			if (shaderType == GLES20.GL_FRAGMENT_SHADER){
				temp = "GL_FRAGMENT_SHADER";
			}else {
				temp = "GL_VERTEX_SHADER";
			}
		    throw new RuntimeException("Error creating " + temp);
		}
		return shaderHandle;
	}

	
	public static int createProgramHandle(int vertShader, int fragShader) {
		int programHandle = GLES20.glCreateProgram();
		if (programHandle != 0)
		{
		    GLES20.glAttachShader(programHandle, vertShader);
		    GLES20.glAttachShader(programHandle, fragShader);
		    GLES20.glBindAttribLocation(programHandle, 0, "a_Position");
		    GLES20.glBindAttribLocation(programHandle, 1, "a_Color");
		    GLES20.glLinkProgram(programHandle);
		    final int[] linkStatus = new int[1];
		    GLES20.glGetProgramiv(programHandle, GLES20.GL_LINK_STATUS, linkStatus, 0);
		    if (linkStatus[0] == 0)
		    {
		        GLES20.glDeleteProgram(programHandle);
		        programHandle = 0;
		    }
		}
		 
		if (programHandle == 0)
		{
		    throw new RuntimeException("Error creating program.");
		}
		return programHandle;
	}
	
	public static int createProgramHandle(String vertShader, String fragShader){
		int vertProg = GlUtils.loadShader(vertShader, GLES20.GL_VERTEX_SHADER);
		int fragProg = GlUtils.loadShader(fragShader, GLES20.GL_FRAGMENT_SHADER);
		int program = GlUtils.createProgramHandle(vertProg, fragProg);
		Log.d("Shader", "Created Program Successfully with pId-->" + program);
		return program;
	}

}
