package jpm.white.fang;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gl.triangle.BlueTriangle;
import com.gl.triangle.GlTriangle;
import com.image.utils.test.ImageUtils;
import com.test.matrixexample.MatrixJniStarter;

public class Launcher extends ListActivity implements OnItemClickListener {

	ArrayAdapter<String> listAdapter;

	final boolean isDebug = true;
	final String klazzNames[] = { "Triangle", 
									"ImageUtilsTest", 
									"BlueTriangle",
									"Matrix4Jni" };
	
	final Class<?> klazz[] = { GlTriangle.class, 
								ImageUtils.class,
								BlueTriangle.class, 
								MatrixJniStarter.class };

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_launcher);
		listAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, klazzNames);
		setListAdapter(listAdapter);

		ListView lv = getListView();
		lv.setOnItemClickListener(this);
	}

	public void addItems(View v) {
		listAdapter.notifyDataSetChanged();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int pos, long arg3) {
		startActivity(new Intent(Launcher.this, klazz[pos]));
	}

}
