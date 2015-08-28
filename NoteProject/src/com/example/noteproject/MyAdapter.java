package com.example.noteproject;

import java.util.ArrayList;



import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<NoteItem>{
	Context context;
	ArrayList<NoteItem> arrlist1;
	public MyAdapter(Context context, ArrayList<NoteItem> objects) {
		super(context, R.layout.note_item, objects);
		this.context=context;
		this.arrlist1=objects;
		// TODO Auto-generated constructor stub
	}

	@SuppressLint("ViewHolder") @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater layout1 = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view1 = layout1.inflate(R.layout.note_item, parent, false);
		TextView id = (TextView)view1.findViewById(R.id.textView1);
		id.setVisibility(View.GONE);
		TextView title=(TextView)view1.findViewById(R.id.textView2);
		id.setText("" + arrlist1.get(position).getId());
		title.setText(arrlist1.get(position).getTitle());
		return view1;
	}
}
