package com.mcerp.adapter;

import java.util.ArrayList;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mcerp.main.R;
import com.mcerp.notification.InboxNotificationActivity;

public class MyAdapterpush extends BaseAdapter {

	Activity act;
	//ArrayList<Map<String, String>> arraylist = new ArrayList<Map<String, String>>();
	String[] arr;

	/*public MyAdapterpush(FragmentActivity activity,
			ArrayList<Map<String, String>> arraylist1) {
		super();
		act = activity;
		arraylist = arraylist1;

	}*/

	public MyAdapterpush(FragmentActivity activity, String[] msg_arr) {
		arr = msg_arr;
		act = activity;
	}

	public MyAdapterpush(InboxNotificationActivity inboxNotificationFragment,
			String[] msg_arr) {
		arr = msg_arr;
		act = inboxNotificationFragment;
		
	}

	class ViewHolder {

		public TextView tvmessage;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arr.length;
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
		//if (arraylist.get(position).get("pushMsg") != null) {
			row.setBackgroundColor((position & 1) == 1 ? Color.WHITE : act
					.getResources().getColor(R.color.cellback));
			//holder.tvmessage.setText(arraylist.get(position).get("pushMsg"));
			holder.tvmessage.setText(arr[position]);
		//}
		return row;
	}
}