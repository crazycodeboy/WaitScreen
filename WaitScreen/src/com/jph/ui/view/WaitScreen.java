package com.jph.ui.view;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import com.jph.ui.R;

/**
 * 等待弹屏
 * @author JPH
 * @date 2015-4-17 上午10:31:14
 */
public class WaitScreen {
	private PopupWindow popupWindow;
	private Context context;
	private View view;
	public WaitScreen(Context context) {
		this.context=context;
		view=LayoutInflater.from(context).inflate(R.layout.poplayout, null);
		popupWindow=new PopupWindow(view, LayoutParams.MATCH_PARENT,  LayoutParams.MATCH_PARENT);
	}
	/**
	 * 弹出等待提示框
	 * @author JPH
	 * @date 2015-4-17 下午1:35:00
	 * @return
	 */
	public PopupWindow show() {
		popupWindow.showAsDropDown(view);
		return popupWindow;
	}
	/**
	 * 以动画的方式关闭等待弹屏
	 * @author JPH
	 * @date 2015-4-17 上午10:40:39
	 */
	public void close() {
		if (popupWindow!=null&&popupWindow.isShowing()) {
			popupWindow.setFocusable(false);
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					popupWindow.dismiss();
				}
			}, 500);
			View progressBar1=view.findViewById(R.id.progressBar1);
			progressBar1.setVisibility(View.GONE);
			View left=view.findViewById(R.id.left);
			View right=view.findViewById(R.id.right);
			TranslateAnimation leftAnimation= new TranslateAnimation(0,-left.getWidth(),0f,0f);
			leftAnimation.setDuration(500);//设置动画持续时间为3秒
			leftAnimation.setInterpolator(context, android.R.anim.overshoot_interpolator);//设置动画插入器
			leftAnimation.setFillAfter(true);//设置动画结束后保持当前的位置（即不返回到动画开始前的位置）
			left.startAnimation(leftAnimation);  
			TranslateAnimation rightAnimation= new TranslateAnimation(0f,left.getWidth(),0f,0f);
			rightAnimation.setDuration(500);//设置动画持续时间为3秒
			rightAnimation.setInterpolator(context, android.R.anim.overshoot_interpolator);//设置动画插入器
			rightAnimation.setFillAfter(true);//设置动画结束后保持当前的位置（即不返回到动画开始前的位置）
			right.startAnimation(rightAnimation);  
		}
	}
	public void dismiss() {
		if (popupWindow!=null&&popupWindow.isShowing()) {
			popupWindow.dismiss();	
		}
	}
}
