package com.example.hp.demo.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.hp.demo.R;
import com.meetic.marypopup.MaryPopup;


public class PopUpHelper {

    private static PopUpHelper instance;

    private View popUpView;
    private LinearLayout contentLayout;
    private MaryPopup maryPopup;

    private PopUpHelper() {
    }

    public synchronized static PopUpHelper getInstance() {
        if (instance == null) {
            instance = new PopUpHelper();
        }
        return instance;
    }

    public interface PopUpInit {
        void initPopUpView(View rootView);
    }

    public PopUpInit popUpInit;

    public void initPopUp(Context context, int layoutId, PopUpInit popUpInit) {
        this.popUpInit = popUpInit;
        popUpView = View.inflate(context, layoutId, null);
        View closeBtn = popUpView.findViewById(R.id.back);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hasDismiss();
            }
        });
        if (popUpInit != null) {
            popUpInit.initPopUpView(popUpView);
        }
    }

    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        popUpView.setLayoutParams(layoutParams);
    }

    public void addContentItem(View itemView) {
        contentLayout.addView(itemView);
    }

    public void show(View author, Activity activity, boolean canDrag) {
        maryPopup = MaryPopup.with(activity)
                .cancellable(true)
                .draggable(canDrag)
                .scaleDownDragging(canDrag)
                .fadeOutDragging(canDrag)
                .blackOverlayColor(Color.parseColor("#DD444444"))
                .backgroundColor(Color.TRANSPARENT)
                .content(popUpView)
                .center(true)
                .from(author);
        maryPopup.show();
    }

    public void show(View author, Activity activity, boolean canDrag, boolean shadow) {
        maryPopup = MaryPopup.with(activity)
                .cancellable(true)
                .draggable(canDrag)
                .shadow(shadow)
                .scaleDownDragging(canDrag)
                .fadeOutDragging(canDrag)
                .blackOverlayColor(Color.parseColor("#DD444444"))
                .backgroundColor(Color.TRANSPARENT)
                .content(popUpView)
                .center(true)
                .from(author);
        maryPopup.show();
    }

    public boolean hasDismiss() {
        if (maryPopup != null) {
            if (maryPopup.isOpened()) {
                maryPopup.close(true);
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }


}
