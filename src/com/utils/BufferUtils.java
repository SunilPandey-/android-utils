package com.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class BufferUtils {
	public static FloatBuffer getFloatBuffer(float[] verts) {
		FloatBuffer buffer = ByteBuffer.allocateDirect(verts.length * 4)
			    .order(ByteOrder.nativeOrder()).asFloatBuffer();
		buffer.put(verts).position(0);
		return buffer;
	}
	
	public static IntBuffer allocIntBuffer(int length){
		IntBuffer buffer = IntBuffer.allocate(length * 4);
		buffer.position(0);
		return buffer;
	}
}
