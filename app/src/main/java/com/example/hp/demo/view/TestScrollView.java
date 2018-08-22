package com.example.hp.demo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class TestScrollView extends ScrollView {
    private onScrollViewListener onscrolllistener;

    public TestScrollView(Context context) {
        super(context);
    }

    public TestScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (null != onscrolllistener) {
            onscrolllistener.onScroll(l, t, oldl, oldt);
        }
    }

    public interface onScrollViewListener {
        void onScroll(int l, int t, int oldl, int oldt);
    }

    public void setOnScrollListener(onScrollViewListener onscrolllistener) {
        this.onscrolllistener = onscrolllistener;
    }
}
