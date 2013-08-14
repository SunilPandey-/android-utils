package com.utils.math;

public class Vector2 {
	
	public float x, y;
	
	public Vector2() {
		x = 0;
		y = 0;
	}
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	
	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void reset(){
		this.x = 0;
		this.y = 0;
	}
}
