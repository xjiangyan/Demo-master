package com.example.hp.demo.vp;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public interface INewsList {
    interface View extends BaseView {
        void setTotal(int total);

        int getPage();
    }

    interface Presenter extends BasePresenter {
        /**
         * 判断是上拉还是下拉来更改总数 0下拉，1上拉
         * @param loadType
         */
        void startLoad(int loadType);
    }
}
