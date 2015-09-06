package com.mcerp.main;

import java.util.List;

import com.mcerp.model.Rowitem;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("ResourceAsColor")
public class Adapter extends BaseAdapter {

	Context context;
	List<Rowitem> rowitem;

	Adapter(Context context, List<Rowitem> rowitem) {
		this.context = context;
		this.rowitem = rowitem;

	}

	public class ViewHolder {
		TextView title;
		ImageView image;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item_old, null);
			holder = new ViewHolder();
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.image = (ImageView) convertView.findViewById(R.id.imageview1);
			holder.title.setText(rowitem.get(position).getTitle());
			Drawable id=context.getResources().getDrawable(R.drawable.icon);
			holder.image.setImageDrawable(id);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		return convertView;

	}

	@Override
	public int getCount() {
		return rowitem.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

}
