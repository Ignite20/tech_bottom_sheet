package com.example.agradinarul.bottomsheettech;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {
	
	private Unbinder unbinder;
	private BottomSheetBehavior<ConstraintLayout> sheet;
	private FragmentManager mfm;
	
	@Nullable
	@BindView(R.id.include_bottom_sheet)
	protected ConstraintLayout bottomSheetView;
	
	@BindView(R.id.fab_action)
	protected FloatingActionButton fab;
	
	@BindView(R.id.toolbar_sheet_dialog)
	protected ImageView sheetDialogButton;
	
	
	@BindView(R.id.imageView)
	protected ImageView mImageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		unbinder = ButterKnife.bind(this);
		
		setupSheet();
		fab.setScaleType(ImageView.ScaleType.CENTER);
	}
	
	private void setupFragmentManager(){
	
	}
	
	private void setupSheet(){
		if(bottomSheetView != null) {
			sheet = BottomSheetBehavior.from(bottomSheetView);
			sheet.setHideable(true);
			sheet.setSkipCollapsed(false);
			sheet.setState(BottomSheetBehavior.STATE_HIDDEN);
			sheet.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
				@Override
				public void onStateChanged(@NonNull View view, int i) {
					switch (i){
						case BottomSheetBehavior.STATE_COLLAPSED:
							mImageView.setImageResource(R.drawable.ic_expand_less_black_24dp);
							break;
						case BottomSheetBehavior.STATE_DRAGGING:
							mImageView.setImageResource(R.drawable.ic_drag_handle_black_24dp);
							break;
						case BottomSheetBehavior.STATE_EXPANDED:
							mImageView.setImageResource(R.drawable.ic_expand_more_black_24dp);
							break;
					}
				}
				
				@Override
				public void onSlide(@NonNull View view, float v) {
					float scaler = Math.abs(1.0f - v);
					if(scaler <= 1f) {
						fab.setScaleX(scaler);
						fab.setScaleY(scaler);
					}
					
					
				}
			});
		}
	}
	
	private void openSheetDialog(){
		BottomSheetDialog dialog = new BottomSheetDialog();
		
		dialog.show(this.getSupportFragmentManager(), BottomSheetDialog.TAG);
	}
	
	@OnClick(R.id.fab_action)
	void onFABClick(){
		sheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
		mImageView.setImageResource(R.drawable.ic_expand_less_black_24dp);
	}
	
	@OnClick(R.id.toolbar_sheet_dialog)
	void onSheetDialogButtonClick(){
		Toast.makeText(this, "Open Dialog", Toast.LENGTH_SHORT).show();
		openSheetDialog();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbinder.unbind();
	}
}
