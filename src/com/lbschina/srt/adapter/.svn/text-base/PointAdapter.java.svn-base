package com.lbschina.srt.adapter;

import java.util.ArrayList;
import com.lbschina.srt.NewPoint;
import com.lbschina.srt.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PointAdapter extends BaseAdapter {

	class ViewHolder {
		TextView StrID;
		TextView CreateTime;
	}

	LayoutInflater mInflater;
	ArrayList<NewPoint> dBeanList = new ArrayList<NewPoint>();

	public PointAdapter(LayoutInflater inflater) {
		this.mInflater = inflater;
	}

	public void setData(ArrayList<NewPoint> beanList) {
		this.dBeanList = beanList;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return dBeanList.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {

			convertView = mInflater.inflate(R.layout.point_item, null);
			viewHolder = new ViewHolder();
			viewHolder.StrID = (TextView) convertView.findViewById(R.id.StrID);

			viewHolder.CreateTime = (TextView) convertView.findViewById(R.id.CreateTime);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.StrID.setText(dBeanList.get(position).ID);
		viewHolder.CreateTime.setText("采集时间: "+dBeanList.get(position).createTime);
		return convertView;
	}
}
