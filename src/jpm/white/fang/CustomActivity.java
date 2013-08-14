package jpm.white.fang;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public abstract class CustomActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		create(this);
	}
	
	@Override
	protected void onResume() {
		resume();
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		pause();
		super.onPause();
	}
	
	@Override
	protected void onDestroy() {
		destroy();
		super.onDestroy();
	}
	
	public abstract void create(Context context);
	public abstract void destroy();
	public abstract void pause();
	public abstract void resume();
	
}
