package com.example.noteproject;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class OptionDialog extends DialogFragment{
	Context context;
	String choice;
	CharSequence[] choiceList = {"Text","Check List"};
	public interface MessageAlertDialogInput {
		public void onDialogSaveClick(DialogFragment dialog, String choice);
		public void onDialogDeleteClick(DialogFragment dialog, String name,String phone);
	}

	MessageAlertDialogInput alertDialogInput;

	public OptionDialog(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try {
			alertDialogInput = (MessageAlertDialogInput) activity;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		

		return new AlertDialog.Builder(getActivity())
				.setSingleChoiceItems(choiceList, 0, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						
					}
				})
		// positive button
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
						//Toast.makeText(MainActivity.this, choice[selectedPosition], Toast.LENGTH_SHORT).show();
//						Log.d("",choiceList[selectedPosition].toString());
						choice = choiceList[selectedPosition].toString();
						alertDialogInput.onDialogSaveClick(OptionDialog.this,choice);

						
					}
				})

				// negative button
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
//							alertDialogInput.onDialogDeleteClick(OptionDialog.this, name, phone);
							}
						}).create();
	}

	
}
