package com.example.agradinarul.bottomsheettech;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

public class BottomSheetDialog extends BottomSheetDialogFragment {
	
	public static final String TAG = "my_dialog";
	
	private FragmentManager mfm;
	public BottomSheetDialog() {
	
	}
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.bottom_sheet_dialog_view, container, false);
	}
	
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public void show(){
		if(getFragmentManager() != null) {
			this.show(getFragmentManager(), TAG);
		}
	}
}
