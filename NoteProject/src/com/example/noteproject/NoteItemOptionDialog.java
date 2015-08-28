package com.example.noteproject;

import java.nio.channels.AlreadyConnectedException;
import java.util.List;

import com.example.noteproject.OptionDialog.MessageAlertDialogInput;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.os.Bundle;

public class NoteItemOptionDialog extends DialogFragment {
	public static final String DATA = "items";

	public static final String SELECTED = "selected";
	CharSequence[] choice = {"Edit","Delete"};
	int position;
	
	public NoteItemOptionDialog(int position)
	{
		this.position=position;
	}
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		alertDialogInput = (MessageAlertDialogInput) activity;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		Resources res = getActivity().getResources();
		Bundle bundle = getArguments();

		AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

		dialog.setTitle("Please Select");
		dialog.setPositiveButton("Cancel", new PositiveButtonClickListener());

//		List<String> list = (List<String>) bundle.get(DATA);
//		int position = bundle.getInt(SELECTED);

		
		dialog.setSingleChoiceItems(choice, 0, selectItemListener);

		return dialog.create();
	}

	
	class PositiveButtonClickListener implements
			DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();
		}
	}

	OnClickListener selectItemListener = new OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// process
			// which means position
			alertDialogInput.onDialogItemClick(NoteItemOptionDialog.this, choice[which].toString(),position);
			dialog.dismiss();
		}

	};
	public interface MessageAlertDialogInput {
		//public void onDialogSaveClick(DialogFragment dialog, String choice);
		public void onDialogItemClick(DialogFragment dialog, String choice1,int noteItem_id);
	}

	MessageAlertDialogInput alertDialogInput;

}
