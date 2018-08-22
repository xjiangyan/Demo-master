package com.zl.fankongdanwei.utils;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.example.hp.demo.R;
import com.example.hp.demo.utils.HelpDialog;

import java.util.Calendar;


/**
 * @author lkw
 *         提醒框工具类
 *         1.单选提醒框
 *         2.多选提醒框
 */
public class AlertDialogUtil {
    /**
     * 内部类，定义了alert-dialog的参数
     */
    public static class AlertDialogParams {
        public int mIconResId = -1;
        public CharSequence[] mItems = null;
        public DialogInterface.OnClickListener mItemClickListener = null;

        //确认按键
        public int mPositiveButtonResId = -1;
        public DialogInterface.OnClickListener mPositiveButtonClickListener = null;

        //title
        public int mTitleResId = -1;
        public String mTitleString = null;

        //多选
        public DialogInterface.OnMultiChoiceClickListener mMultiClickListener = null;

        //消息
        public int mMessageResId = -1;
        public String mMessageString = null;

        //onCancelListener
        public DialogInterface.OnCancelListener mOnCancelListener = null;

        //取消按键
        public DialogInterface.OnClickListener mNegativeButtonClickListener = null;
        public int mNegativeButtonResId = -1;

        //Neutral按钮
        public DialogInterface.OnClickListener mNeutralButtonClickListener = null;
        public int mNeutralButtonResId = -1;

        //单选：默认勾选选项
        public int mSingleCheckedItemIndex = 0;
        //多选：默认勾选选项列表
        public boolean[] mMultiCheckedItems = null;
    }

    /**
     * 单选提醒框
     */
    @SuppressWarnings("unused")
    public static AlertDialog singleChoseAlert(Context context, AlertDialogParams params) {
        if (null == context || null == params) {
            return null;
        }

        //创建builder
        Builder builder = new Builder(context);
        if (null == builder) {
            return null;
        }

        //设置title
        if (params.mTitleResId > 0) {
            builder.setTitle(params.mTitleResId);
        }

        //设置icon
        if (params.mIconResId > 0) {
            builder.setIcon(params.mIconResId);
        }

        //设置单选
        if (null != params.mItems && null != params.mItemClickListener) {
            builder.setSingleChoiceItems(params.mItems, params.mSingleCheckedItemIndex, params.mItemClickListener);
        }

        //设置positive按键
        if (params.mPositiveButtonResId > 0 && null != params.mPositiveButtonClickListener) {
            builder.setPositiveButton(params.mPositiveButtonResId, params.mPositiveButtonClickListener);
        }

        //设置negavite按键
        if (params.mNegativeButtonResId > 0 && null != params.mNegativeButtonClickListener) {
            builder.setNegativeButton(params.mNegativeButtonResId, params.mNegativeButtonClickListener);
        }

        //设置cancellistener
        if (null != params.mOnCancelListener) {
            builder.setOnCancelListener(params.mOnCancelListener);
        }

        //创建alert-dialog
        AlertDialog alertDialog = builder.create();
        if (null == alertDialog) {
            return null;
        }

        alertDialog.show();
        return alertDialog;
    }

    /**
     * 多选提醒框
     */
    @SuppressWarnings("unused")
    public static AlertDialog multiChoseAlert(Context context, AlertDialogParams params) {
        if (null == context || null == params) {
            return null;
        }

        //创建builder
        Builder builder = new Builder(context);
        if (null == builder) {
            return null;
        }

        //设置title
        if (params.mTitleResId > 0) {
            builder.setTitle(params.mTitleResId);
        }

        //设置icon
        if (params.mIconResId > 0) {
            builder.setIcon(params.mIconResId);
        }

        //设置多选
        if (null != params.mItems && null != params.mMultiClickListener && null != params.mMultiCheckedItems) {
            builder.setMultiChoiceItems(params.mItems, params.mMultiCheckedItems, params.mMultiClickListener);
        }

        //设置positive按键
        if (params.mPositiveButtonResId > 0 && null != params.mPositiveButtonClickListener) {
            builder.setPositiveButton(params.mPositiveButtonResId, params.mPositiveButtonClickListener);
        }

        //设置negavite按键
        if (params.mNegativeButtonResId > 0 && null != params.mNegativeButtonClickListener) {
            builder.setNegativeButton(params.mNegativeButtonResId, params.mNegativeButtonClickListener);
        }

        //设置cancellistener
        if (null != params.mOnCancelListener) {
            builder.setOnCancelListener(params.mOnCancelListener);
        }

        //创建alert-dialog
        AlertDialog alertDialog = builder.create();
        if (null == alertDialog) {
            return null;
        }
        alertDialog.show();

        return alertDialog;
    }

    /**
     * 纯文本提示框
     */
    @SuppressWarnings("unused")
    public static AlertDialog textAlert(Context context, AlertDialogParams params) {
        if (null == context || null == params) {
            return null;
        }

        //创建builder
        Builder builder = new Builder(context);
        if (null == builder) {
            return null;
        }

        //设置title
        if (params.mTitleResId > 0) {
            builder.setTitle(params.mTitleResId);
        }
        if (params.mTitleString != null) {
            builder.setTitle(params.mTitleString);
        }

        //设置icon
        if (params.mIconResId > 0) {
            builder.setIcon(params.mIconResId);
        }

        //设置Message
        if (params.mMessageResId > 0) {
            builder.setMessage(params.mMessageResId);
        } else if (null != params.mMessageString) {
            builder.setMessage(params.mMessageString);
        }

        //设置positive按键
        if (params.mPositiveButtonResId > 0 && null != params.mPositiveButtonClickListener) {
            builder.setPositiveButton(params.mPositiveButtonResId, params.mPositiveButtonClickListener);
        }

        //设置neutral按键
        if (params.mNeutralButtonResId > 0 && null != params.mNeutralButtonClickListener) {
            builder.setNeutralButton(params.mNeutralButtonResId, params.mNeutralButtonClickListener);
        }

        //设置negavite按键
        if (params.mNegativeButtonResId > 0 && null != params.mNegativeButtonClickListener) {
            builder.setNegativeButton(params.mNegativeButtonResId, params.mNegativeButtonClickListener);
        }

        //设置cancellistener
        if (null != params.mOnCancelListener) {
            builder.setOnCancelListener(params.mOnCancelListener);
        }

        //创建alert-dialog
        AlertDialog alertDialog = builder.create();
        if (null == alertDialog) {
            return null;
        }
        alertDialog.show();

        return alertDialog;
    }


    /**
     * 信息提示框（纯文本信息提示框）
     *
     * @param context
     * @param tilte   提示框标题
     * @param message 提示信息
     */
    @SuppressWarnings("unused")
    public static AlertDialog showAlertDialog(Context context, int tilte, int message) {
        if (null == context || message <= 0) {
            return null;
        }
        Builder builder = new Builder(context);
        if (null == builder) {
            return null;
        }

        AlertDialog alert = builder.create();
        if (null == alert) {
            return null;
        }

        alert.setIcon(R.drawable.notification);
        if (tilte > 0) {
            alert.setTitle(tilte);
        }

        alert.setMessage(context.getResources().getString(message));
        alert.setButton(context.getResources().getString(android.R.string.ok),
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alert.show();

        return alert;
    }

    /**
     * 显示提示框（view content）
     *
     * @param context
     * @param view    提示信息的view
     * @param params  主要设置 mTitleResId、mPositiveButtonResId、以及 mPositiveButtonClickListener
     */
    @SuppressWarnings("unused")
    public static AlertDialog showAlertDialog(Context context, ImageView view, AlertDialogParams params) {
        if (null == context || null == params || null == view || null == view.getDrawable()) {
            return null;
        }
        Builder builder = new Builder(context);
        if (null == builder) {
            return null;
        }

        //设置alertDialog Title
        if (params.mTitleResId > 0) {
            builder.setTitle(params.mTitleResId);
        }

        //设置AlertDialog的View content
        ImageView view_content = new ImageView(context);
        if (null == view_content) {
            return null;
        }

        if (null != view.getDrawable()) {
            view_content.setImageDrawable(view.getDrawable());
        }

        builder.setView(view_content);

        if (null != params.mPositiveButtonClickListener && params.mPositiveButtonResId > 0) {
            builder.setPositiveButton(context.getResources().getString(params.mPositiveButtonResId), params.mPositiveButtonClickListener);
        }


        builder.setNegativeButton(context.getResources().getString(params.mNegativeButtonResId),
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        if (null == alert) {
            return null;
        }

        alert.show();

        return alert;
    }


    /**
     * 信息提示框（纯文本信息提示框） 单按键
     *
     * @param context
     * @param tilte   提示框标题
     * @param message 提示信息
     */
    @SuppressWarnings("unused")
    public static AlertDialog showAlertDialog(Context context, int tilte, String message) {
        if (null == context || tilte <= 0 || null == message || message.length() == 0) {
            return null;
        }
        Builder builder = new Builder(context);
        if (null == builder) {
            return null;
        }

        AlertDialog alert = builder.create();
        if (null == alert) {
            return null;
        }

        alert.setIcon(R.drawable.notification);
        alert.setTitle(tilte);
        alert.setMessage(message);
        alert.setButton(context.getResources().getString(android.R.string.ok),
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alert.show();

        return alert;
    }

    /**
     * 显示带图片的对话框
     */
    @SuppressWarnings("unused")
    public static AlertDialog imageAlert(Context context, AlertDialogParams params, Uri imgUri) {
        if (null == context || null == params || null == imgUri) {
            return null;
        }

        //创建builder
        Builder builder = new Builder(context);
        if (null == builder) {
            return null;
        }

        //设置title
        if (params.mTitleResId > 0) {
            builder.setTitle(params.mTitleResId);
        }

        //设置icon
        if (params.mIconResId > 0) {
            builder.setIcon(params.mIconResId);
        }

        //设置Message
        if (params.mMessageResId > 0) {
            builder.setMessage(params.mMessageResId);
        }

        //设置positive按键
        if (params.mPositiveButtonResId > 0 && null != params.mPositiveButtonClickListener) {
            builder.setPositiveButton(params.mPositiveButtonResId, params.mPositiveButtonClickListener);
        }

        //设置negavite按键
        if (params.mNegativeButtonResId > 0 && null != params.mNegativeButtonClickListener) {
            builder.setNegativeButton(params.mNegativeButtonResId, params.mNegativeButtonClickListener);
        }

        //设置cancellistener
        if (null != params.mOnCancelListener) {
            builder.setOnCancelListener(params.mOnCancelListener);
        }

        //设置图片
        if (null != imgUri) {
            ImageView img = new ImageView(context);
            if (null != img) {
                img.setImageURI(imgUri);
                builder.setView(img);
            }
        }

        //创建alert-dialog
        AlertDialog alertDialog = builder.create();
        if (null == alertDialog) {
            return null;
        }
        alertDialog.show();

        return alertDialog;
    }

    /**
     * 显示提示框（view content）
     *
     * @param context
     * @param view    包含控件的View
     * @param params  主要设置 mTitleResId、mPositiveButtonResId、以及 mPositiveButtonClickListener
     */
    @SuppressWarnings("unused")
    public static AlertDialog showAlertDialog(Context context, View view, AlertDialogParams params) {
        if (null == context || null == params || null == view || null == view) {
            return null;
        }
        Builder builder = new Builder(context);
        if (null == builder) {
            return null;
        }

        //设置alertDialog Title
        if (params.mTitleResId > 0) {
            builder.setTitle(params.mTitleResId);
        }

        //设置AlertDialog的View content
        builder.setView(view);

        if (null != params.mPositiveButtonClickListener && params.mPositiveButtonResId > 0) {
            builder.setPositiveButton(params.mPositiveButtonResId, params.mPositiveButtonClickListener);
        }

        //取消按钮
        if (null != params.mNegativeButtonClickListener && params.mNegativeButtonResId > 0) {
            builder.setNegativeButton(params.mNegativeButtonResId, params.mNegativeButtonClickListener);
        }

        //取消按钮
        if (null != params.mOnCancelListener) {
            builder.setOnCancelListener(params.mOnCancelListener);
        }

        AlertDialog alert = builder.create();
        if (null == alert) {
            return null;
        }

        alert.show();

        return alert;
    }


    /**
     * 显示提示框（view 为大滚轮）
     * @param context
     * @param iGroupPosition 默认选中的位置
     * @param params 主要设置 mTitleResId、mPositiveButtonResId、以及 mPositiveButtonClickListener
     * */
//	@SuppressWarnings("unused")
//	public static AlertDialog showAlertDialog(Context context,int selectIndex ,final AlertDialogParams params)
//	{
//		if (null == params || null == params.mItems || params.mItems.length <= 0 || selectIndex < 0) {
//			return null;
//		}
//		
//		//diaglog的大滚轮view
//		final WheelView catalogWheel = new WheelView(context);
//		if (null == catalogWheel) {
//			return null;
//		}
//		
//		Builder builder = new AlertDialog.Builder(context);
//		if (null == builder) {
//			return null;
//		}
//		
//		if (params.mTitleResId > 0) {
//			builder.setTitle(params.mTitleResId);
//		}
//		   
//		if (params.mPositiveButtonResId > 0 && null != params.mPositiveButtonClickListener) {
//			builder.setPositiveButton(params.mPositiveButtonResId,new DialogInterface.OnClickListener() {   
//				  
//			    public void onClick(DialogInterface dialog, int which) {
//			    	if (null == catalogWheel) {
//						return ;
//					}
//			    	
//			    	//当前选中的index
//			    	int icurrent = catalogWheel.getCurrentItem();
//			    	
//			    	which = icurrent;
//			    	
//			    	if (null != params.mPositiveButtonClickListener) {
//			    		params.mPositiveButtonClickListener.onClick(dialog, which);
//					}
//			    	
//			    	dialog.dismiss();
//			    }   
//			       
//			});
//		}
//		
//		//设置neutral按键
//		if(params.mNeutralButtonResId > 0 && null != params.mNeutralButtonClickListener){
//			builder.setNeutralButton(params.mNeutralButtonResId, params.mNeutralButtonClickListener);
//		}
//		
//		//设置negavite按键
//		if (params.mNegativeButtonResId > 0 && null != params.mNegativeButtonClickListener) {
//			builder.setNegativeButton(params.mNegativeButtonResId, params.mNegativeButtonClickListener);
//		}
//		
//		//设置cancellistener
//		if(null != params.mOnCancelListener){
//			builder.setOnCancelListener(params.mOnCancelListener);
//		}
//		
//		if (null != catalogWheel ) {
//			builder.setView(catalogWheel); 
//		}
//		
//		//设置能见几行
//		catalogWheel.setVisibleItems(5);
//		
//		//设置当前选中哪个
//		catalogWheel.setCurrentItem(selectIndex);
//		
//		// 设置显示的字,CATAS为种类的名字
//		catalogWheel.setAdapter(new ArrayWheelAdapter<CharSequence>(params.mItems));
//		//*/
//		
//		AlertDialog alert = builder.create();
//		if (null == alert) {
//			return null;
//		}
//		
//		alert.show();
//		
//		return alert;
//	}

    /**
     * 显示错误提示框
     * void
     *
     */
    public static AlertDialog showErrorDialog(Context context, int resId) {
        if (resId < 0) {
            return null;
        }

        AlertDialogParams params = new AlertDialogParams();
        if (null == params) {
            return null;
        }

        params.mTitleResId = R.string.app_name;
        //设置显示错误
        params.mMessageResId = resId;
        //设置确定按钮
        params.mPositiveButtonResId = android.R.string.ok;
        //设置确定按钮事件
        params.mPositiveButtonClickListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (null != dialog) {
                    dialog.dismiss();
                }
            }
        };
        //设置系统返回键事件
        params.mOnCancelListener = new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                if (null != dialog) {
                    dialog.dismiss();
                }
            }
        };
        return AlertDialogUtil.textAlert(context, params);
    }

    /**
     * 弹出透明背景的alertdialog
     * void
     *
     * @param context
     */
    public static HelpDialog showTransparentAlert(Context context, int style, final View.OnClickListener listener) {
        if (null == context || style < 0) {
            return null;
        }
        //重写dialog后的显示方法，可用.9图片
        final HelpDialog dialog = new HelpDialog(context, style);
        //设置点击事件
        dialog.setClickListener(listener);
        //设置系统返回键事件
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                if (null != dialog) {
                    dialog.dismiss();
                }

            }
        });

        //设置layputparams(全屏)
        LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        dialog.addContentView(new View(context), params);

        //显示dialog
        dialog.show();

        return dialog;
    }

    /**
     * 显示带CheckBox的对话框
     */
    @SuppressWarnings("unused")
    public static AlertDialog checkBoxAlert(Context context, AlertDialogParams params, CheckBox box) {
        if (null == context || null == params || null == box) {
            return null;
        }

        //创建builder
        Builder builder = new Builder(context);
        if (null == builder) {
            return null;
        }

        //设置title
        if (params.mTitleResId > 0) {
            builder.setTitle(params.mTitleResId);
        }

        //设置icon
        if (params.mIconResId > 0) {
            builder.setIcon(params.mIconResId);
        }

        //设置Message
        if (params.mMessageResId > 0) {
            builder.setMessage(params.mMessageResId);
        }

        //设置positive按键
        if (params.mPositiveButtonResId > 0 && null != params.mPositiveButtonClickListener) {
            builder.setPositiveButton(params.mPositiveButtonResId, params.mPositiveButtonClickListener);
        }

        //设置negavite按键
        if (params.mNegativeButtonResId > 0 && null != params.mNegativeButtonClickListener) {
            builder.setNegativeButton(params.mNegativeButtonResId, params.mNegativeButtonClickListener);
        }

        //设置cancellistener
        if (null != params.mOnCancelListener) {
            builder.setOnCancelListener(params.mOnCancelListener);
        }

        //设置checkbox
        if (null != box) {
            builder.setView(box);
        }

        //创建alert-dialog
        AlertDialog alertDialog = builder.create();
        if (null == alertDialog) {
            return null;
        }
        alertDialog.show();

        return alertDialog;
    }

    //弹出手机自带日期设置对话框
    public static void showDateSetDialog(Context context, OnDateSetListener onDateSetListener) {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(context, onDateSetListener, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();

    }

    //弹出手机自带日期设置对话框
    public static void showTimeSetDialog(Context context, OnTimeSetListener onTimeSetListener) {
        Calendar c = Calendar.getInstance();
        new TimePickerDialog(context, onTimeSetListener, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
    }
}
