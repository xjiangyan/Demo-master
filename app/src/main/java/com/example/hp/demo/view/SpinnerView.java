package com.example.hp.demo.view;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

import com.example.hp.demo.R;
import com.example.hp.demo.bean.AllSBLXBean;
import com.example.hp.demo.bean.SBLXBean;
import com.example.hp.demo.databinding.ItemSpinnerviewBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**三级联动spinner
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class SpinnerView extends RelativeLayout {

    private LayoutInflater layoutInflater;
    ItemSpinnerviewBinding binding;
    private Context context;
    private ArrayAdapter<SBLXBean> spiner_oneadapter, spiner_twoadapter, spiner_threeadapter;
    Map<String, List<SBLXBean>> maptwo = new HashMap<>();
    Map<String, List<SBLXBean>> mapthree = new HashMap<>();
    private SBLXBean sblxBean;
    List<SBLXBean> mSBLXBeanList, firstSpinnerDatas, secondSpinnerDatas, thirdSpinnerDatas;
    private List<AllSBLXBean> allsblxbean;
    private String firstselectedid, secondselectedid, thirdselectedid, firstselectedtext, secondselectedtext, thirdselectedtext;


    public SpinnerView(Context context) {
        super(context);
        layoutInflater = ((Activity) context).getLayoutInflater();
        init();
    }

    public SpinnerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        layoutInflater = ((Activity) context).getLayoutInflater();
        init();
    }

    public SpinnerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        layoutInflater = ((Activity) context).getLayoutInflater();
        init();
    }

    private void init() {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_spinnerview, this, true);
    }


    public void initAdapter(Context context, List<AllSBLXBean> allsblxbean) {
        this.context = context;
        this.allsblxbean = allsblxbean;

        mSBLXBeanList = new ArrayList<>();
        sblxBean = new SBLXBean();
        sblxBean.setText("全部");
        sblxBean.setId("allFirst");
        mSBLXBeanList.add(sblxBean);
        for (int i = 0; i < allsblxbean.size(); i++) {
            sblxBean = new SBLXBean();
            sblxBean.setText(allsblxbean.get(i).getTEXT());
            sblxBean.setId(allsblxbean.get(i).getID());
            mSBLXBeanList.add(sblxBean);
        }

        List<SBLXBean> secondList = null;
        for (int i = 0; i < allsblxbean.size(); i++) {
            secondList = new ArrayList<>();
            for (int m = 0; m < allsblxbean.get(i).getChild().size(); m++) {
                if (m == 0) {
                    SBLXBean all = new SBLXBean();
                    all.setId("AllSecond");
                    all.setParentId(allsblxbean.get(i).getID());
                    all.setText("全部");
                    secondList.add(0, all);
                }
                sblxBean = new SBLXBean();
                sblxBean.setText(allsblxbean.get(i).getChild().get(m).getTEXT());
                sblxBean.setId(allsblxbean.get(i).getChild().get(m).getID());
                sblxBean.setParentId(allsblxbean.get(i).getID());
                secondList.add(sblxBean);
            }
            maptwo.put(allsblxbean.get(i).getID(), secondList);
        }

        List<SBLXBean> thirdList = null;

        for (int i = 0; i < allsblxbean.size(); i++) {
            AllSBLXBean firstBean = allsblxbean.get(i);

            for (int m = 0; m < firstBean.getChild().size(); m++) {
                AllSBLXBean.ChildBeanX secondBean = firstBean.getChild().get(m);

                thirdList = new ArrayList<>();
                for (int n = 0; n < secondBean.getChild().size(); n++) {
                    AllSBLXBean.ChildBeanX.ChildBean thirdBean = secondBean.getChild().get(n);

                    if (n == 0) {
                        SBLXBean all = new SBLXBean();
                        all.setId("AllThird");
                        all.setText("全部");
                        all.setParentId(secondBean.getID());
                        thirdList.add(0, all);
                    }
                    sblxBean = new SBLXBean();
                    sblxBean.setText(thirdBean.getTEXT());
                    sblxBean.setId(thirdBean.getID());
                    sblxBean.setParentId(secondBean.getID());
                    thirdList.add(sblxBean);
                }
                mapthree.put(secondBean.getID(), thirdList);
            }
        }

        initSpinner();

    }

    private void initSpinner() {
        firstSpinnerDatas = new ArrayList<>();
        firstSpinnerDatas.addAll(mSBLXBeanList);
        //适配器
        spiner_oneadapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, firstSpinnerDatas);
        //设置样式
        spiner_oneadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        binding.spinnerOne.setAdapter(spiner_oneadapter);

        binding.spinnerOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                SBLXBean curItem = spiner_oneadapter.getItem(position);
                List<SBLXBean> second = maptwo.get(curItem.getId());
                firstselectedid = curItem.getId();
                firstselectedtext = curItem.getText();
                initSpinnerTwo(second);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initSpinnerTwo(List<SBLXBean> second) {
        if (second == null) {
            second = new ArrayList<>();
            SBLXBean all = new SBLXBean();
            all.setText("全部");
            all.setId("allSecond");
            second.add(all);
        }

        if (secondSpinnerDatas == null) {
            secondSpinnerDatas = new ArrayList<>();
        } else {
            secondSpinnerDatas.clear();
        }
        secondSpinnerDatas.addAll(second);
        if (spiner_twoadapter == null) {
            //适配器
            spiner_twoadapter = new ArrayAdapter<SBLXBean>(context, android.R.layout.simple_spinner_item, secondSpinnerDatas);
            //设置样式
            spiner_twoadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //加载适配器
            binding.spinnerTwo.setAdapter(spiner_twoadapter);
        } else {
            spiner_twoadapter.notifyDataSetChanged();
            binding.spinnerTwo.setSelection(0);
        }
        binding.spinnerTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SBLXBean curItem = spiner_twoadapter.getItem(position);
                List<SBLXBean> third = mapthree.get(curItem.getId());
                secondselectedid = curItem.getId();
                secondselectedtext = curItem.getText();
                initSpinnerThree(third);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void initSpinnerThree(List<SBLXBean> third) {
        if (third == null) {
            third = new ArrayList<>();
            SBLXBean all = new SBLXBean();
            all.setText("全部");
            all.setId("allThird");
            third.add(all);
        }
        if (thirdSpinnerDatas == null) {
            thirdSpinnerDatas = new ArrayList<>();
        } else {
            thirdSpinnerDatas.clear();
        }
        thirdSpinnerDatas.addAll(third);
        if (spiner_threeadapter == null) {
            //适配器
            spiner_threeadapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, thirdSpinnerDatas);
            //设置样式
            spiner_threeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //加载适配器
            binding.spinnerThree.setAdapter(spiner_threeadapter);
        } else {
            spiner_threeadapter.notifyDataSetChanged();
            binding.spinnerThree.setSelection(0);
        }
        binding.spinnerThree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SBLXBean curItem = spiner_threeadapter.getItem(position);
                thirdselectedtext = curItem.getText();
                thirdselectedid = curItem.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public String getSelectedItemText() {
        if (binding.spinnerOne.getSelectedItemPosition() == 0) {
            return "";
        } else if (binding.spinnerOne.getSelectedItemPosition() != 0 && binding.spinnerTwo.getSelectedItemPosition() == 0) {
            return firstselectedtext;
        } else if (binding.spinnerOne.getSelectedItemPosition() != 0 && binding.spinnerTwo.getSelectedItemPosition() != 0 && binding.spinnerThree.getSelectedItemPosition() == 0) {
            return secondselectedtext;
        } else if (binding.spinnerOne.getSelectedItemPosition() != 0 && binding.spinnerTwo.getSelectedItemPosition() != 0 && binding.spinnerThree.getSelectedItemPosition() != 0) {
            return thirdselectedtext;
        }
        return "";
    }

    public String getSelectedItemID() {
        if (binding.spinnerOne.getSelectedItemPosition() == 0) {
            return "";
        } else if (binding.spinnerOne.getSelectedItemPosition() != 0 && binding.spinnerTwo.getSelectedItemPosition() == 0) {
            return firstselectedid;
        } else if (binding.spinnerOne.getSelectedItemPosition() != 0 && binding.spinnerTwo.getSelectedItemPosition() != 0 && binding.spinnerThree.getSelectedItemPosition() == 0) {
            return secondselectedid;
        } else if (binding.spinnerOne.getSelectedItemPosition() != 0 && binding.spinnerTwo.getSelectedItemPosition() != 0 && binding.spinnerThree.getSelectedItemPosition() != 0) {
            return thirdselectedid;
        }
        return "";
    }
}
