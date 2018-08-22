package com.example.hp.demo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class fragmentadapter extends FragmentPagerAdapter {
    private final ArrayList<Fragment> fragments;
    private final Context content;

    public fragmentadapter(FragmentManager fm, Context context, ArrayList<Fragment> fragments) {
        super(fm);
        this.content = context;
        this.fragments = fragments;
    }


    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "标题";
    }
}
