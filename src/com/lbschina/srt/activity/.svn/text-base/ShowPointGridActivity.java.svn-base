package com.lbschina.srt.activity;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.lbschina.srt.App;
import com.lbschina.srt.NewPoint;
import com.lbschina.srt.R;
import com.lbschina.srt.adapter.PointAdapter;
import com.lbschina.srt.db.SalesPointHelp;

public class ShowPointGridActivity extends Activity {
	
	private SalesPointHelp pointHelper;
	private ListView listView;
	PointAdapter mAdapter;
	ArrayList<NewPoint> noteList = new ArrayList<NewPoint>();
	private TextView navigateBack;
	private TextView showAllPoint;
	private App MyApp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		HideTitle();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_point_grid);
		
		pointHelper = new SalesPointHelp();
		pointHelper.init(ShowPointGridActivity.this);
		
		if(pointHelper.getAllPoint()!=null && pointHelper.getAllPoint().size()>0){
			noteList = pointHelper.getAllPoint();
		}
		
		initUI();
		
		MyApp=(App) this.getApplication();

		navigateBack = (TextView) findViewById(R.id.NavigateBack);		
		navigateBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ReturnMainPage();
			}
		});

		showAllPoint = (TextView) findViewById(R.id.NavigateAllPoint);
		showAllPoint.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				MyApp.setShowStr("SHOWALL");
				Intent intent = new Intent(ShowPointGridActivity.this,ShowPointMapActivity.class);
				intent.putExtra("NoteList", noteList);
				startActivity(intent);
			}

		});
	}

	private void initUI() {		
		
		listView = (ListView) findViewById(R.id.listView);
		mAdapter = new PointAdapter(getLayoutInflater());
		listView.setAdapter(mAdapter);
		listView.setOnItemClickListener(new NoteListOnItemClickListener());	
		refreshPage();
	}

	class NoteListOnItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
			
			final int index = position;			
			String[] operations = new String[] { "查看", "删除" };			
			AlertDialog.Builder operationDialog = new AlertDialog.Builder(ShowPointGridActivity.this);
			operationDialog.setIcon(android.R.drawable.ic_dialog_info);
			operationDialog.setItems(operations,
					new android.content.DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							
							final NewPoint noteBean = noteList.get(index);
							
							if (0 == which ) {
								//查看
								MyApp.setShowStr("SHOWONE");
								Intent intent = new Intent();
								intent.setClass(ShowPointGridActivity.this,ShowPointMapActivity.class);
								
								intent.putExtra("StrID", noteBean.ID);
								intent.putExtra("X", noteBean.X);
								intent.putExtra("Y", noteBean.Y);
								startActivity(intent);
							} else {
								
								//删除
								AlertDialog.Builder builder = new AlertDialog.Builder(ShowPointGridActivity.this);
								
								builder.setTitle("确定删除？");
								builder.setPositiveButton("是", new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,	int which) {
										pointHelper.delPoint(noteBean.ID);
										//删除完成后需要刷新页面
										refreshPage();
									}
								});
								builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which) {
										dialog.dismiss();
									}
								});
								builder.show();								
							}
						}
					});
			operationDialog.create().show();
		}
	}

	/**
	 * 刷新yemian
	 */
	private void refreshPage() {
		noteList = pointHelper.getAllPoint();
		mAdapter.setData(noteList);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			ReturnMainPage();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	

	@Override
	protected void onResume() {
		super.onResume();
		refreshPage();
	}

	@TargetApi(5)
	@SuppressLint("NewApi")
	private void ReturnMainPage() {

		Intent myIntent = new Intent();
		myIntent = new Intent(ShowPointGridActivity.this, MainActivity.class);
		startActivity(myIntent);
		overridePendingTransition(android.R.anim.slide_in_left,
				android.R.anim.slide_out_right);

		this.finish();
	}

	private void HideTitle() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

}
