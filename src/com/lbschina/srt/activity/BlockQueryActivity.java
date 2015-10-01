package com.lbschina.srt.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.lbschina.hht.HHTCallBlockHelp;
import com.lbschina.hht.entity.NieBlock;
import com.lbschina.srt.App;
import com.lbschina.srt.R;
import com.lbschina.srt.adapter.BlockAdapter;

public class BlockQueryActivity extends Activity {
	
	private ListView Block_list=null;
	BlockAdapter mBlockAdapter=null;
	BlockAdapter mBlockAdapterQry=null;
	
	List<NieBlock> NieBlockQry_list=new ArrayList<NieBlock>();
	Intent mGetIntent=null;
	HHTCallBlockHelp mHHTCallBlockHelp = null;
	
	Button qryBtn=null;
	EditText blockIDTxt=null;
	private Boolean isSDCard=false;
	//全局变量存储位置
	private App MyApp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blockqry);
		
		MyApp=(App) this.getApplication();
		isSDCard=Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		
		mHHTCallBlockHelp= new HHTCallBlockHelp();
		mHHTCallBlockHelp.init(BlockQueryActivity.this);
		
		qryBtn=(Button) findViewById(R.id.qryBtn);
		Block_list=(ListView) findViewById(R.id.Block_list);
		
		blockIDTxt=(EditText) findViewById(R.id.blockIDTxt);
		
		qryBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String mBlockID=blockIDTxt.getText().toString();
				String mCityID=MyApp.getmCityID();
				MyApp.setmSecQryBlock("SecondQueryRslt");
				
				if(mHHTCallBlockHelp!=null && mCityID!=null && mCityID.length()>0){			 		 
					NieBlockQry_list=mHHTCallBlockHelp.getNieBlock(mBlockID,mCityID);
				}
				
				if(NieBlockQry_list!=null && NieBlockQry_list.size()>0){
					mBlockAdapterQry=new BlockAdapter(NieBlockQry_list, BlockQueryActivity.this); 
					mBlockAdapterQry.notifyDataSetChanged();
					Block_list.setAdapter(mBlockAdapterQry);
				}else{
					if(!isSDCard){
						Toast.makeText(getApplicationContext(), "请检查SD卡，获取地图块数据失败！",Toast.LENGTH_LONG).show();
					}					
				}						
			}
		});
		
		
		Block_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				
				if(NieBlockQry_list!=null && NieBlockQry_list.size()>0){
					
					 NieBlock nb=NieBlockQry_list.get(position);					 
					 String blockCoord=nb.getBlockCoord().trim();
					 String blockID=nb.getBlockID().trim();
					 
					 Intent RsltIntent = new Intent();
					 RsltIntent.putExtra("BLOCKCOORD",blockCoord);	
					 RsltIntent.putExtra("BLOCKID",blockID);
					 
					 BlockQueryActivity.this.setResult(RESULT_OK, RsltIntent);
					 BlockQueryActivity.this.finish(); 				
				}
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		String mCityID = MyApp.getmCityID();

		if(mHHTCallBlockHelp!=null && mCityID!=null && mCityID.length()>0){			
			NieBlockQry_list=mHHTCallBlockHelp.getNieBlockByCityId(mCityID);
		}
		
		if(NieBlockQry_list!=null && NieBlockQry_list.size()>0){
			mBlockAdapter=new BlockAdapter(NieBlockQry_list, this); 
			mBlockAdapter.notifyDataSetChanged();
			Block_list.setAdapter(mBlockAdapter);	
		}else{
			if(!isSDCard){
				Toast.makeText(getApplicationContext(), "请检查SD卡，获取地图块数据失败！",Toast.LENGTH_LONG).show();
			}
		}			
	}
}
