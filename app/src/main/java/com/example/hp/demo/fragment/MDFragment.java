package com.example.hp.demo.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.demo.R;
import com.example.hp.demo.databinding.FragmentbymdBinding;


/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class MDFragment extends Fragment {
    private FragmentbymdBinding binding;
    private View rootView;

    public MDFragment() {
    }

    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView != null) {
            return rootView;
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragmentbymd, null, false);

        rootView = binding.getRoot();
        return rootView;


    }

}
