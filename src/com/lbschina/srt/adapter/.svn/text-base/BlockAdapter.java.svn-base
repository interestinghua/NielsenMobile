package com.lbschina.srt.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lbschina.hht.entity.NieBlock;
import com.lbschina.srt.R;

public class BlockAdapter extends BaseAdapter {
	
	List<NieBlock> NieBlock_list;
	Context context;
	LayoutInflater layout;

	public BlockAdapter(List<NieBlock> nieBlock_list, Context context) {
		this.NieBlock_list = nieBlock_list;
		this.context = context;
		this.layout = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return NieBlock_list.size();
	}

	@Override
	public Object getItem(int position) {
		return NieBlock_list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	class ViewHolder {
		ImageView payimgleft;
		TextView paymiddle_txt;
		ImageView payimgright;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		convertView = null;
		NieBlock mNieBlock = NieBlock_list.get(position);
		ViewHolder viewholder = new ViewHolder();

		if (convertView == null) {
			convertView = layout.inflate(R.layout.block_item,null);
		}

		viewholder.payimgleft = (ImageView) convertView.findViewById(R.id.paychannel_icon);
		viewholder.paymiddle_txt = (TextView) convertView.findViewById(R.id.paychannel_txt);
		viewholder.payimgright = (ImageView) convertView.findViewById(R.id.paychannel_img_right);

		viewholder.paymiddle_txt.setText(mNieBlock.getBlockID());

		return convertView;
	}
}
