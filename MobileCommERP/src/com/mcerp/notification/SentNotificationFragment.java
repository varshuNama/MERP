package com.mcerp.notification;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mcerp.adapter.MyAdapterpush;
import com.mcerp.main.R;

public class SentNotificationFragment extends Fragment{
	String[] msg_arr={"snmbdnsdbnsbdnbsndbsnbdnsbdnbsndbsnbdnsbd","sb dbnnsdnbs db sbd bs dbs d"," s d s dsndnnsbdsbnjaskjaksjaksjakjskajsk",
			"dnmsndmsandmnasmdnamnanKNKAK","BCVBXVCBVXBCVBXVCXHJJKASKJASHDKJKAKJKA","SBkjsshkdbbdbfndbnbdnfbdn"};
  MyAdapterpush adapter;
  ListView list;
  View rootView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.sent_notification, container,
				false);	
		list=(ListView) rootView.findViewById(R.id.list_view_sent_msg);
	    adapter=new MyAdapterpush(getActivity(), msg_arr);
	    list.setAdapter(adapter);
		return rootView;
	}

}
