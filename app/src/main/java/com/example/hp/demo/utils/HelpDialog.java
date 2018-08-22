
package com.example.hp.demo.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

/**
 * 提示框，主要用于透明提示框的
 *
 * @author lkw
 */
public class HelpDialog extends Dialog {
    private Context mContext = null;
    private View.OnClickListener mClickListener = null;

    /**
     * @param context
     */
    public HelpDialog(Context context) {
        super(context);
        mContext = context;
    }

    /**
     * @param context
     * @param theme
     */
    public HelpDialog(Context context, int theme) {
        super(context, theme);
        mContext = context;
    }

    /**
     * @param context
     * @param cancelable
     * @param cancelListener
     */
    public HelpDialog(Context context, boolean cancelable,
                      OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
    }

    /**
     * 设置点击事件
     * void
     *
     * @param listener
     */
    public void setClickListener(View.OnClickListener listener) {
        if (null == listener) {
            return;
        }

        mClickListener = listener;
    }

    /**
     * 重写touch事件，主要是因为图片用.9的格式后，dialog的contentView的点击事件就不被调用了（不知道什么原因）
     * 所以用重写touch事件来触发点击事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != event && null != mClickListener) {
            if (MotionEvent.ACTION_UP == event.getAction()) {
                mClickListener.onClick(null);
                this.dismiss();
            }
        }

        return super.onTouchEvent(event);
    }


}