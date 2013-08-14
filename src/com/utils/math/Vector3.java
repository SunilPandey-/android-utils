package com.utils.math;


public class Vector3 {
	public float x, y, z;

	public Vector3() {
		this.x = this.y = this.z = 0;
	}

	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void normalize() {
		float invLength = (float) (1 / Math.sqrt(x * x + y * y + z * z));
		x *= invLength;
		y *= invLength;
		z *= invLength;
	}
	
	
	public Vector3 getNewNormalizeVec(){
		float invLength = (float) (1 / Math.sqrt(x * x + y * y + z * z));
		return new Vector3(x*invLength, y*invLength, z*invLength);
	}

	public void sub(Vector3 rhs) {
		this.set(x - rhs.x, y - rhs.y, z - rhs.z);
	}
	
	
	public Vector3 getNewVecSub(Vector3 rhs) {
		return new Vector3(x - rhs.x, y - rhs.y, z - rhs.z);
	}

	public void cross(Vector3 rhs) {
		this.set(y * rhs.z - z * rhs.y, z * rhs.x - x * rhs.z, x * rhs.y - y* rhs.x);
	}
	
	public Vector3 getNewVecCross(Vector3 rhs){
		return new Vector3(y * rhs.z - z * rhs.y, z * rhs.x - x * rhs.z, x * rhs.y - y* rhs.x);
	}
	

}
