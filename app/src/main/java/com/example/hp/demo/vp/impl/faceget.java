package com.example.hp.demo.vp.impl;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class faceget {

    Clicklistener clicklistener;

    public void get() {
        clicklistener.ItemClick(6);
    }

    public interface Clicklistener {
        void ItemClick(int position);
    }

    public void setClicklistener(Clicklistener Clicklistener) {
        this.clicklistener = clicklistener;
    }

}
