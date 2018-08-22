package com.example.hp.demo.presenter.imp;

import android.content.Context;

import com.example.hp.demo.presenter.IVP;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class Presenterimp implements IVP.demo_presenter {


    private final IVP.demo_view view;
    private final Context context;

    public Presenterimp(Context context, IVP.demo_view view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void isedit(String word) {
        if (word != null && word != "") {
            view.changeword(word);
        } else {
            view.changeword("内容为空");

        }
    }


}
