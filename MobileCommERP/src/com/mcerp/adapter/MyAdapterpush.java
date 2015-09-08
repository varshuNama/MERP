package com.mcerp.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mcerp.main.R;
import com.mcerp.model.PushModel;
import com.mcerp.notification.InboxNotificationActivity;

public class MyAdapterpush extends BaseAdapter {

	Activity act;
	ArrayList<PushModel> msgArray;;

	public MyAdapterpush(InboxNotificationActivity activity,
			ArrayList<PushModel> msg_arr) {
		msgArray = msg_arr;
		act = activity;
	}

	class ViewHolder {

		public TextView tvmessage;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return msgArray.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View row, ViewGroup parent) {
		// TODO Auto-generated method stub

		LayoutInflater mInflater = (LayoutInflater) act
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ViewHolder holder;

		if (row == null)
			row = mInflater.inflate(R.layout.rowpushmsg, null);
		holder = new ViewHolder();
		holder.tvmessage = (TextView) row.findViewById(R.id.textmsgpush);
		row.setTag(holder);

		row.setBackgroundColor((position & 1) == 1 ? Color.WHITE : act
				.getResources().getColor(R.color.cellback));

		holder.tvmessage.setText(msgArray.get(position).getMsg());

		return row;
	}
}