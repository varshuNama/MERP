package com.mcerp.notification;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.mcerp.main.R;

public class WriteNotificationFragment extends Fragment implements
		OnClickListener {
	Button send_btn, reset_btn;
	View rootView;
	ImageView img_close_emp;
	EditText text_message, subject_message;
	AutoCompleteTextView emp_spin;
	ImageView imageview;
	String[] strEmp = { "ALL", "AAAAA", "BABA", "CAAABABA", "ANBNABNAB",
			"DDDDD" };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.write_notifications, container,
				false);
		init();

		return rootView;
	}

	private void init() {
		send_btn = (Button) rootView.findViewById(R.id.send_btn);
		reset_btn = (Button) rootView.findViewById(R.id.reset_btn);
		text_message = (EditText) rootView.findViewById(R.id.message);
		subject_message = (EditText) rootView.findViewById(R.id.subject_message);
		img_close_emp=(ImageView) rootView.findViewById(R.id.img_close_emp);
		send_btn.setOnClickListener(this);
		reset_btn.setOnClickListener(this);
		img_close_emp.setOnClickListener(this);

		emp_spin = (AutoCompleteTextView) rootView
				.findViewById(R.id.emp_spinner_notify);
		emp_spin.setAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_dropdown_item_1line, strEmp));
		emp_spin.setThreshold(1);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.send_btn:
			

			break;
		case R.id.reset_btn:
			subject_message.setText("");
			text_message.setText("");
			emp_spin.setSelection(0);
			break;
		case R.id.img_close_emp:
			emp_spin.setText("");
			break;
			
		default:
			break;
		}

	}

}
