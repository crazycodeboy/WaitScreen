package com.jph.ui.ws;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import com.jph.ui.R;
import com.jph.ui.view.WaitScreen;

/**
 * 等待弹屏
 * @author JPH
 * @date 2015-4-17 下午1:29:49
 */
public class MainActivity extends Activity implements OnClickListener{
	PopupWindow popupWindow;
	private WaitScreen waitScreen;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (popupWindow!=null) {
					waitScreen.close();
				}
			}
		}, 3*1000);
	}
	@Override
	public void onAttachedToWindow() {
		show();
		super.onAttachedToWindow();
	}
	private void show() {
		waitScreen=new WaitScreen(this);
		popupWindow=waitScreen.show();
	}
	@Override
	public void onClick(View v) {
		show();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_MENU:
			if (popupWindow!=null) {
				waitScreen.close();
				return true;
			}
			break;

		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
}
