package com.bignerdranch.android.remotecontrol;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class RemoteControlFragment extends Fragment {
	
	private final static String TAG = "RemoteControlFragment";
	
	private TextView mSelectedTextView;
	private TextView mWorkingTextView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, 
			Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.fragment_remote_control, parent, false);
		
		mSelectedTextView = (TextView)v.findViewById(R.id.fragment_remote_control_selectedTextView);
		
		mWorkingTextView = (TextView)v.findViewById(R.id.fragment_remote_control_workingTextView);
		
		View.OnClickListener numberButtonListener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TextView textView = (TextView)v;
				String working = mWorkingTextView.getText().toString();
				String text = textView.getText().toString();
				
				if(working.equals("0")) {
					mWorkingTextView.setText(text);
				}
				else {
					mWorkingTextView.setText(working + text);
				}
				
			}
		};
		
		/*
		Button zeroButton = (Button)v.findViewById(R.id.fragment_remote_control_zeroButton);
		zeroButton.setOnClickListener(numberButtonListener);
		
		Button oneButton = (Button)v.findViewById(R.id.fragment_remote_control_oneButton);
		oneButton.setOnClickListener(numberButtonListener);
		*/
		Log.i(TAG,"Getting tableLayout");
		TableLayout tableLayout = (TableLayout)v.findViewById(R.id.fragment_remote_control_tableLayout);
		int number = 1;
		
		Log.i(TAG,"Begin loops to build digit buttons");
		//Start at 2 to skip over the first two TextBoxes and start on the buttons
		for(int i = 2; i < tableLayout.getChildCount() - 1; i++) {
			TableRow row = (TableRow)tableLayout.getChildAt(i);
			for(int j = 0; j < row.getChildCount(); j ++) {
				Button button = (Button)row.getChildAt(j);
				button.setText("" + number);
				button.setOnClickListener(numberButtonListener);
				number++;
			}
		}
		
		Log.i(TAG,"Get Bottom Row");
		TableRow bottomRow = (TableRow)tableLayout.getChildAt(tableLayout.getChildCount() - 1);
		Log.i(TAG,"Get Delete Button");
		Button deleteButton = (Button)bottomRow.getChildAt(0);
		Log.i(TAG,"Set text on listener for delteButton");
		deleteButton.setText("Delete");
		deleteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mWorkingTextView.setText("0");
				
			}
		});
		
		Log.i(TAG,"Build zeroButton");
		Button zeroButton = (Button)bottomRow.getChildAt(1);
		zeroButton.setText("0");
		zeroButton.setOnClickListener(numberButtonListener);
		
		Log.i(TAG,"Build deleteButton");
		Button enterButton = (Button)bottomRow.getChildAt(2);
		enterButton.setText("Enter");
		enterButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				CharSequence working = mWorkingTextView.getText();
				if(working.length() > 0)
					mSelectedTextView.setText(working);
				mWorkingTextView.setText("0");
				
			}
		});
		
		return v;
	}

}
