package com.example.hp.demo.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.example.hp.demo.R;
import com.example.hp.demo.bean.DeptBean;
import com.example.hp.demo.bean.TreeNodes;
import com.example.hp.demo.databinding.ActivityMulitListBinding;
import com.example.hp.demo.holder.ArrowExpandSelectableHeaderHolder;
import com.example.hp.demo.holder.IconTreeItemHolder;
import com.example.hp.demo.utils.ManageTreesUtils;
import com.example.hp.demo.utils.TreeNodeMerger;
import com.google.gson.Gson;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.Arrays;
import java.util.List;

public class MulitList2Activity extends AppCompatActivity implements TreeNode.TreeNodeClickListener {
    private ActivityMulitListBinding binding;
    private String dept;
    private AndroidTreeView tView;
    private TreeNode treeone;
    private TreeNode treetwo;
    private TreeNode treethree;
    private boolean first;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MulitList2Activity.this, R.layout.activity_mulit_list);
        initdata();
        initView();
        if (savedInstanceState != null) {
            String state = savedInstanceState.getString("tState");
            if (!TextUtils.isEmpty(state)) {
                tView.restoreState(state);
            }
        }
    }

    private void initView() {

        Gson gson = new Gson();
        final DeptBean testBean = gson.fromJson(dept, DeptBean.class);
        List<DeptBean.ListBean> list = testBean.getList();

        TreeNode root = TreeNode.root();

        TreeNodes[] treeNodes = new TreeNodes[list.size()];

        for (int i = 0; i < list.size(); i++) {
            treeNodes[i] = new TreeNodes(list.get(i).getId(), list.get(i).getPid(), list.get(i).getDepshort(), list.get(i).getDepcode());
        }
        TreeNodes treeNode = TreeNodeMerger.merge(treeNodes);
        //一级
        treeone = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_people, treeNode.getContent(), treeNode.getCode())).setViewHolder(new ArrowExpandSelectableHeaderHolder(getApplicationContext()));

        //        root.addChild(ManageTreesUtils.manageTrees(treeone, treeNode, MulitList2Activity.this));
        root.addChild(ManageTreesUtils.manageTrees(treeone, treeNode, MulitList2Activity.this));

        tView = new AndroidTreeView(getApplicationContext(), root);
        tView.setDefaultAnimation(true);
        tView.setUse2dScroll(true);
        tView.setDefaultContainerStyle(R.style.TreeNodeStyleDivided);
        tView.setDefaultNodeClickListener(this);
        tView.setDefaultViewHolder(ArrowExpandSelectableHeaderHolder.class);
        binding.line.addView(tView.getView());
        tView.setUseAutoToggle(false);

        tView.expandLevel(1);
    }


    public static <T> List<T> jsonToList(String jsonStr, Class<T[]> type) {
        Gson gson = new Gson();
        T[] list = gson.fromJson(jsonStr, type);
        return Arrays.asList(list);
    }

    @Override
    public void onClick(TreeNode node, Object value) {

        binding.tvSelect.setText(((IconTreeItemHolder.IconTreeItem) value).text);
    }


    private void initdata() {
        dept = "{\n" +
                "  \"returncode\" : null,\n" +
                "  \"returnstr\" : null,\n" +
                "  \"count\" : 72,\n" +
                "  \"list\" : [ {\n" +
                "    \"id\" : \"d8d7828e-efa6-4ffa-b194-d21d7e43c3b2\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203010000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局国内安全保卫大队\",\n" +
                "    \"depshort\" : \"海曙分局国保大队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533189000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020301\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"5d81aad6-f28a-46a9-a823-fe6eb5614577\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203020000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局经济犯罪侦查大队\",\n" +
                "    \"depshort\" : \"海曙分局经侦大队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533189000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020302\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"17d07f6b-1b0e-45e7-bc86-fb43ac34612f\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203030000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局治安大队\",\n" +
                "    \"depshort\" : \"海曙分局治安大队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533189000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020303\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"51d7b899-e736-41b0-ab2b-93f44ee5a31b\",\n" +
                "    \"pid\" : \"17d07f6b-1b0e-45e7-bc86-fb43ac34612f\",\n" +
                "    \"depcode\" : \"330203030100\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局治安大队经文保中队\",\n" +
                "    \"depshort\" : \"海曙分局治安大队经文保中队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533189000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"3302030301\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"9b29e1b1-c215-4cfa-a33c-d5cf574d31f6\",\n" +
                "    \"pid\" : \"17d07f6b-1b0e-45e7-bc86-fb43ac34612f\",\n" +
                "    \"depcode\" : \"330203030200\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局治安大队行动中队\",\n" +
                "    \"depshort\" : \"海曙分局治安大队行动中队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533189000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"3302030302\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"6f41c7ac-b2a5-4541-83f2-f1c67872c926\",\n" +
                "    \"pid\" : \"17d07f6b-1b0e-45e7-bc86-fb43ac34612f\",\n" +
                "    \"depcode\" : \"330203030300\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局治安大队基础管理中队\",\n" +
                "    \"depshort\" : \"海曙分局治安大队基础管理中队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533189000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"3302030303\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"56f26770-4cd3-4e6f-add1-c68ce35cc99f\",\n" +
                "    \"pid\" : \"17d07f6b-1b0e-45e7-bc86-fb43ac34612f\",\n" +
                "    \"depcode\" : \"330203030400\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局户证中心\",\n" +
                "    \"depshort\" : \"海曙分局户证中心\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533189000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"3302030304\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"5b0d831e-220a-433d-a1bd-19d8493f0b7a\",\n" +
                "    \"pid\" : \"17d07f6b-1b0e-45e7-bc86-fb43ac34612f\",\n" +
                "    \"depcode\" : \"330203030500\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局治安大队保安管理中队\",\n" +
                "    \"depshort\" : \"海曙分局治安大队保安管理中队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533189000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"3302030305\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"b28d1108-574e-4d44-86e2-8832ccb21c12\",\n" +
                "    \"pid\" : \"17d07f6b-1b0e-45e7-bc86-fb43ac34612f\",\n" +
                "    \"depcode\" : \"330203030600\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局治安大队综合室\",\n" +
                "    \"depshort\" : \"海曙分局治安大队综合室\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533189000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"3302030306\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"99feed64-018e-4fc9-91f0-98216b673d95\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203050000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局刑事侦查大队\",\n" +
                "    \"depshort\" : \"海曙分局刑侦大队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533189000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020305\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"26f3fa0c-b068-4654-acf7-7a9f62e59941\",\n" +
                "    \"pid\" : \"99feed64-018e-4fc9-91f0-98216b673d95\",\n" +
                "    \"depcode\" : \"330203050400\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局刑事侦查大队重案中队\",\n" +
                "    \"depshort\" : \"海曙分局刑侦大队重案中队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533190000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"3302030504\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"995ca548-bdfc-4164-8ed9-1885527cf54d\",\n" +
                "    \"pid\" : \"99feed64-018e-4fc9-91f0-98216b673d95\",\n" +
                "    \"depcode\" : \"330203050500\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局刑事侦查大队技术中队\",\n" +
                "    \"depshort\" : \"海曙分局刑侦大队技术中队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533190000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"3302030505\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"c3516161-cdf5-43d4-a64e-f8e279f9f866\",\n" +
                "    \"pid\" : \"99feed64-018e-4fc9-91f0-98216b673d95\",\n" +
                "    \"depcode\" : \"330203050600\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局刑事侦查大队盗抢犯罪情报研判中队\",\n" +
                "    \"depshort\" : \"海曙分局刑侦大队盗抢犯罪情报研判中队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533190000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"3302030506\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"49de9ebe-9f95-48fd-98d9-7f4649b378bc\",\n" +
                "    \"pid\" : \"99feed64-018e-4fc9-91f0-98216b673d95\",\n" +
                "    \"depcode\" : \"330203050700\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局刑事侦查大队信息中队\",\n" +
                "    \"depshort\" : \"海曙分局刑事侦查大队信息中队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533190000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"3302030507\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"2dc726fa-5982-422e-965b-904d2a07dab6\",\n" +
                "    \"pid\" : \"99feed64-018e-4fc9-91f0-98216b673d95\",\n" +
                "    \"depcode\" : \"330203050800\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局刑事侦查大队综合室\",\n" +
                "    \"depshort\" : \"海曙分局刑侦大队综合室\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533190000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"3302030508\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"55e08ccd-f250-41d2-a101-6223a492e10b\",\n" +
                "    \"pid\" : \"99feed64-018e-4fc9-91f0-98216b673d95\",\n" +
                "    \"depcode\" : \"330203050900\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局刑事侦查大队办案中队\",\n" +
                "    \"depshort\" : \"海曙分局刑事侦查大队办案中队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533190000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"3302030509\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"20eb1fde-8962-42d8-b9f2-bbdb39af9092\",\n" +
                "    \"pid\" : \"99feed64-018e-4fc9-91f0-98216b673d95\",\n" +
                "    \"depcode\" : \"330203051100\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局刑事侦查大队街面案件侦查中队\",\n" +
                "    \"depshort\" : \"海曙分局刑侦大队街面案件侦查中队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533190000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"3302030511\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"49e56794-8935-4782-beb4-ffc3c05b2b43\",\n" +
                "    \"pid\" : \"99feed64-018e-4fc9-91f0-98216b673d95\",\n" +
                "    \"depcode\" : \"330203051200\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局刑事侦查大队系列性案件侦查中队\",\n" +
                "    \"depshort\" : \"海曙分局刑侦大队系列性案件侦查中队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533190000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"3302030512\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"befbc826-87d7-4f82-ba66-cf455b2ccf84\",\n" +
                "    \"pid\" : \"99feed64-018e-4fc9-91f0-98216b673d95\",\n" +
                "    \"depcode\" : \"330203059700\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局刑事侦查大队有组织犯罪侦查中队\",\n" +
                "    \"depshort\" : \"海曙分局刑侦大队有组织犯罪侦查中队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533190000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"3302030597\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"015b2abf-b460-4fc5-8e67-941c1bcf669e\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203110000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局网络警察大队\",\n" +
                "    \"depshort\" : \"海曙分局网警大队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533190000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020311\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"a2e44c98-a346-4e0a-a1f0-038e6666cf7f\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203180000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局法制大队\",\n" +
                "    \"depshort\" : \"海曙分局法制大队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533190000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020318\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"9ddc1676-f69a-40a7-94d6-4de2b7a71f24\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203210000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局禁毒大队\",\n" +
                "    \"depshort\" : \"海曙分局禁毒大队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533190000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020321\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"e9be99c1-8954-4c7e-bb74-732feeb6cb6e\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203220000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局信通科技科\",\n" +
                "    \"depshort\" : \"海曙分局信通科技科\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533190000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020322\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"7f2620a1-5230-4dc4-8307-acf6dca6d1ed\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203230000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局后勤科\",\n" +
                "    \"depshort\" : \"海曙分局后勤科\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533190000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020323\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"713327ab-fa36-43b5-8253-5bd211d9a7ce\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203250000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局警卫科\",\n" +
                "    \"depshort\" : \"海曙分局警卫科\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533190000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020325\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"f943c0f8-bb26-4491-a547-59c4f90cedd2\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203260000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局协辅警管理科\",\n" +
                "    \"depshort\" : \"海曙分局协辅警管理科\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533190000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020326\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"639d5614-d12a-42c1-b9cb-9075291601ca\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203270000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局行政执法保障大队\",\n" +
                "    \"depshort\" : \"海曙分局行政执法保障大队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533190000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020327\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"c33ed1c4-7b2e-454d-a0fd-9f5d9569eca5\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203310000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局办公室\",\n" +
                "    \"depshort\" : \"海曙分局办公室\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533190000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020331\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"cb708f0a-5527-4ed7-8429-f067cc58bafb\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203320000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局纪委\",\n" +
                "    \"depshort\" : \"海曙分局纪委\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533190000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020332\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"be21c56e-39bc-4cd5-a94c-e6fac317ef69\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203340000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局警务督察大队\",\n" +
                "    \"depshort\" : \"海曙分局督察大队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533190000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020334\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"e9e00865-0acc-4bfd-b689-2a4ff0f5b467\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203350000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局政治处\",\n" +
                "    \"depshort\" : \"海曙分局政治处\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533190000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020335\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"ee524b4d-54eb-439a-a1db-4c47163595d0\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203360000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局110指挥中心\",\n" +
                "    \"depshort\" : \"海曙分局110指挥中心\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533190000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020336\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"1e972603-4b18-4059-97fb-da43f99d7da8\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203370000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局情报科\",\n" +
                "    \"depshort\" : \"海曙分局情报科\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533191000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020337\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"a44ed62d-7e74-48a0-9854-fc6b33ce90aa\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203380000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局反恐大队\",\n" +
                "    \"depshort\" : \"海曙分局反恐大队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533191000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020338\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"1ffdc2bc-232d-4600-8eeb-eee3bfeef27b\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203390000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局出入境管理科\",\n" +
                "    \"depshort\" : \"海曙分局出入境管理科\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533191000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020339\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"9587cf56-bb72-4350-a8d4-1eaa45110490\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203460000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局巡（特）警大队\",\n" +
                "    \"depshort\" : \"海曙分局巡（特）警大队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533191000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020346\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"6887ccda-7b01-481c-a86c-7ce4d5310838\",\n" +
                "    \"pid\" : \"9587cf56-bb72-4350-a8d4-1eaa45110490\",\n" +
                "    \"depcode\" : \"330203461100\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局巡（特）警大队二中队\",\n" +
                "    \"depshort\" : \"海曙分局巡（特）警大队二中队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533191000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"3302034611\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"aba96add-130d-4848-839c-5eb3125ef968\",\n" +
                "    \"pid\" : \"9587cf56-bb72-4350-a8d4-1eaa45110490\",\n" +
                "    \"depcode\" : \"330203461200\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局巡（特）警大队三中队\",\n" +
                "    \"depshort\" : \"海曙分局巡（特）警大队三中队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533191000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"3302034612\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"e6b47377-1833-4546-85db-c2a80ec3ef21\",\n" +
                "    \"pid\" : \"9587cf56-bb72-4350-a8d4-1eaa45110490\",\n" +
                "    \"depcode\" : \"330203461300\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局巡（特）警大队一中队\",\n" +
                "    \"depshort\" : \"海曙分局巡（特）警大队一中队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533191000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"3302034613\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"9ac30d8a-f8a6-45e0-9046-151d73bc69f9\",\n" +
                "    \"pid\" : \"9587cf56-bb72-4350-a8d4-1eaa45110490\",\n" +
                "    \"depcode\" : \"330203461500\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局巡（特）警大队综合室\",\n" +
                "    \"depshort\" : \"海曙分局巡（特）警大队综合室\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533191000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"3302034615\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"c59ac82f-0d5b-48cb-9d4f-14a51fd31add\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203480000\",\n" +
                "    \"depname\" : \"浙江省宁波市海曙区看守所\",\n" +
                "    \"depshort\" : \"海曙看守所\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533203000,\n" +
                "    \"modifier\" : \"330204196811309010\",\n" +
                "    \"modifydate\" : 1492328661000,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020348\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"8e4864ef-8965-47df-a9ab-06c3902d8299\",\n" +
                "    \"pid\" : \"c59ac82f-0d5b-48cb-9d4f-14a51fd31add\",\n" +
                "    \"depcode\" : \"330203481000\",\n" +
                "    \"depname\" : \"浙江省宁波市海曙区看守所一中队\",\n" +
                "    \"depshort\" : \"海曙看守所一中队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533203000,\n" +
                "    \"modifier\" : \"330204196811309010\",\n" +
                "    \"modifydate\" : 1492328767000,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"3302034810\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"bdc289f2-2321-45d1-9073-78fed2844669\",\n" +
                "    \"pid\" : \"c59ac82f-0d5b-48cb-9d4f-14a51fd31add\",\n" +
                "    \"depcode\" : \"330203482000\",\n" +
                "    \"depname\" : \"浙江省宁波市海曙区看守所二中队\",\n" +
                "    \"depshort\" : \"海曙看守所二中队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533203000,\n" +
                "    \"modifier\" : \"330204196811309010\",\n" +
                "    \"modifydate\" : 1492328818000,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"3302034820\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"e7e097ad-4e0f-4d02-b839-05560fe4cc55\",\n" +
                "    \"pid\" : \"c59ac82f-0d5b-48cb-9d4f-14a51fd31add\",\n" +
                "    \"depcode\" : \"330203483000\",\n" +
                "    \"depname\" : \"浙江省宁波市海曙区看守所三中队\",\n" +
                "    \"depshort\" : \"海曙看守所三中队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533203000,\n" +
                "    \"modifier\" : \"330204196811309010\",\n" +
                "    \"modifydate\" : 1492328872000,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"3302034830\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"c6a78f4c-36f5-47ca-89f0-1d2aed0caa83\",\n" +
                "    \"pid\" : \"c59ac82f-0d5b-48cb-9d4f-14a51fd31add\",\n" +
                "    \"depcode\" : \"330203484000\",\n" +
                "    \"depname\" : \"浙江省宁波市海曙区看守所四中队\",\n" +
                "    \"depshort\" : \"海曙看守所四中队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533203000,\n" +
                "    \"modifier\" : \"330204196811309010\",\n" +
                "    \"modifydate\" : 1492328917000,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"3302034840\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"ebc5e616-690c-4bec-929a-e234f1bce270\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203510000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局江厦派出所\",\n" +
                "    \"depshort\" : \"海曙分局江厦派出所\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533191000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"4\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020351\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203510000\",\n" +
                "    \"mainShortCode\" : \"33020351\"\n" +
                "  }, {\n" +
                "    \"id\" : \"8467b2d0-6e18-4ed5-adc0-f03a28a5187c\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203520000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局月湖派出所\",\n" +
                "    \"depshort\" : \"海曙分局月湖派出所\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533191000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"4\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020352\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203520000\",\n" +
                "    \"mainShortCode\" : \"33020352\"\n" +
                "  }, {\n" +
                "    \"id\" : \"72099108-d924-4dac-874d-4aff8625bc14\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203530000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局南门派出所\",\n" +
                "    \"depshort\" : \"海曙分局南门派出所\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533191000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"4\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020353\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203530000\",\n" +
                "    \"mainShortCode\" : \"33020353\"\n" +
                "  }, {\n" +
                "    \"id\" : \"140a56ec-0d9d-4fc6-a7b0-c131c7783891\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203540000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局西门派出所\",\n" +
                "    \"depshort\" : \"海曙分局西门派出所\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533191000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"4\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020354\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203540000\",\n" +
                "    \"mainShortCode\" : \"33020354\"\n" +
                "  }, {\n" +
                "    \"id\" : \"8f882c2a-3655-41c9-a418-4ca7efa25b73\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203550000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局白云派出所\",\n" +
                "    \"depshort\" : \"海曙分局白云派出所\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533191000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"4\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020355\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203550000\",\n" +
                "    \"mainShortCode\" : \"33020355\"\n" +
                "  }, {\n" +
                "    \"id\" : \"25996ce0-e40d-4a8c-ad67-7454f078d693\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203560000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局望春派出所\",\n" +
                "    \"depshort\" : \"海曙分局望春派出所\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533191000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"4\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020356\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203560000\",\n" +
                "    \"mainShortCode\" : \"33020356\"\n" +
                "  }, {\n" +
                "    \"id\" : \"dcd5522d-f793-4dd1-b12b-694ed7409990\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203570000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局段塘派出所\",\n" +
                "    \"depshort\" : \"海曙分局段塘派出所\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533191000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"4\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020357\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203570000\",\n" +
                "    \"mainShortCode\" : \"33020357\"\n" +
                "  }, {\n" +
                "    \"id\" : \"7a50a1c0-4f79-42bd-8af2-c915c9c84841\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203580000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局广场派出所\",\n" +
                "    \"depshort\" : \"海曙分局广场派出所\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533191000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"4\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020358\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203580000\",\n" +
                "    \"mainShortCode\" : \"33020358\"\n" +
                "  }, {\n" +
                "    \"id\" : \"03f0442b-2a07-40e6-8523-390240c3ba39\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203590000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局鼓楼派出所\",\n" +
                "    \"depshort\" : \"海曙分局鼓楼派出所\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533191000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"4\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020359\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203590000\",\n" +
                "    \"mainShortCode\" : \"33020359\"\n" +
                "  }, {\n" +
                "    \"id\" : \"d13f941b-9205-419e-9b66-3f3f8a733ade\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203610000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局石碶派出所\",\n" +
                "    \"depshort\" : \"宁波市局海曙分局石碶派出所\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"zladmin\",\n" +
                "    \"creatdate\" : 1489563798000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"4\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020361\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203610000\",\n" +
                "    \"mainShortCode\" : \"33020361\"\n" +
                "  }, {\n" +
                "    \"id\" : \"56b1126c-2892-4530-abd2-d08fae3940ff\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203620000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局集士港派出所\",\n" +
                "    \"depshort\" : \"宁波市局海曙分局集士港派出所\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"zladmin\",\n" +
                "    \"creatdate\" : 1489563810000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"4\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020362\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203620000\",\n" +
                "    \"mainShortCode\" : \"33020362\"\n" +
                "  }, {\n" +
                "    \"id\" : \"8f2255f1-5ae9-4b86-8007-4b3eca6cb790\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203630000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局高桥派出所\",\n" +
                "    \"depshort\" : \"宁波市局海曙分局高桥派出所\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"zladmin\",\n" +
                "    \"creatdate\" : 1489564020000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"4\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020363\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203630000\",\n" +
                "    \"mainShortCode\" : \"33020363\"\n" +
                "  }, {\n" +
                "    \"id\" : \"54faf9b3-b87f-4b62-84d6-f8598832d0f9\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203640000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局古林派出所\",\n" +
                "    \"depshort\" : \"宁波市局海曙分局古林派出所\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"zladmin\",\n" +
                "    \"creatdate\" : 1489564032000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"4\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020364\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203640000\",\n" +
                "    \"mainShortCode\" : \"33020364\"\n" +
                "  }, {\n" +
                "    \"id\" : \"dc21e2e3-4c0c-4c96-b03e-e0277e94c56e\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203650000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局横街派出所\",\n" +
                "    \"depshort\" : \"宁波市局海曙分局横街派出所\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"zladmin\",\n" +
                "    \"creatdate\" : 1489564043000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"4\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020365\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203650000\",\n" +
                "    \"mainShortCode\" : \"33020365\"\n" +
                "  }, {\n" +
                "    \"id\" : \"264bece4-7b5d-4c80-8fbb-e285b6cc7aea\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203660000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局鄞江派出所\",\n" +
                "    \"depshort\" : \"宁波市局海曙分局鄞江派出所\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"zladmin\",\n" +
                "    \"creatdate\" : 1489564056000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"4\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020366\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203660000\",\n" +
                "    \"mainShortCode\" : \"33020366\"\n" +
                "  }, {\n" +
                "    \"id\" : \"aaf0a8e3-77b0-4e5c-bd9e-4ebf4ffd2d3e\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203670000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局洞桥派出所\",\n" +
                "    \"depshort\" : \"宁波市局海曙分局洞桥派出所\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"zladmin\",\n" +
                "    \"creatdate\" : 1489564068000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"4\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020367\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203670000\",\n" +
                "    \"mainShortCode\" : \"33020367\"\n" +
                "  }, {\n" +
                "    \"id\" : \"c1f98501-3fd4-48ce-85c7-53093e344530\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203680000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局龙观派出所\",\n" +
                "    \"depshort\" : \"宁波市局海曙分局龙观派出所\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"zladmin\",\n" +
                "    \"creatdate\" : 1489564080000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"4\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020368\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203680000\",\n" +
                "    \"mainShortCode\" : \"33020368\"\n" +
                "  }, {\n" +
                "    \"id\" : \"9e518e00-0bbe-47eb-9642-1aedecbc6ae9\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203690000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局章水派出所\",\n" +
                "    \"depshort\" : \"宁波市局海曙分局章水派出所\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"zladmin\",\n" +
                "    \"creatdate\" : 1489564092000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"4\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020369\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203690000\",\n" +
                "    \"mainShortCode\" : \"33020369\"\n" +
                "  }, {\n" +
                "    \"id\" : \"21c6d62f-805a-48ef-87f2-00bca0335ea7\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203X00000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安消防支队海曙区大队\",\n" +
                "    \"depshort\" : \"海曙大队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533191000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"330203X0\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"24035b47-d3b9-41e8-b2d7-a013944c26b1\",\n" +
                "    \"pid\" : \"21c6d62f-805a-48ef-87f2-00bca0335ea7\",\n" +
                "    \"depcode\" : \"330203X00100\",\n" +
                "    \"depname\" : \"浙江省宁波市公安消防支队海曙区大队海曙中队\",\n" +
                "    \"depshort\" : \"海曙中队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533191000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"330203X001\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"3f4a25a4-1bab-4641-add3-681a44ad9467\",\n" +
                "    \"pid\" : \"21c6d62f-805a-48ef-87f2-00bca0335ea7\",\n" +
                "    \"depcode\" : \"330203X00200\",\n" +
                "    \"depname\" : \"浙江省宁波市公安消防支队海曙区大队鄞奉路中队\",\n" +
                "    \"depshort\" : \"鄞奉路中队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533191000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"330203X002\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"aacdf193-8782-43e0-a5f0-03e42d937664\",\n" +
                "    \"pid\" : \"21c6d62f-805a-48ef-87f2-00bca0335ea7\",\n" +
                "    \"depcode\" : \"330203X00300\",\n" +
                "    \"depname\" : \"浙江省宁波市公安消防支队海曙区大队天一中队\",\n" +
                "    \"depshort\" : \"天一中队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533191000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"330203X003\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"3da7e9a2-ea00-4b41-9579-ff8e05318ebf\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203X10000\",\n" +
                "    \"depname\" : \"浙江省市公安消防支队特勤大队\",\n" +
                "    \"depshort\" : \"特勤大队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533191000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"330203X1\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"bfee6c7f-db4e-4219-b5d7-e2a82a9bb738\",\n" +
                "    \"pid\" : \"3da7e9a2-ea00-4b41-9579-ff8e05318ebf\",\n" +
                "    \"depcode\" : \"330203X10100\",\n" +
                "    \"depname\" : \"浙江省宁波市公安消防支队特勤大队特勤一中队\",\n" +
                "    \"depshort\" : \"特勤一中队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533191000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"330203X101\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"92ea0f6b-e184-42bd-9025-69f025bd3017\",\n" +
                "    \"pid\" : \"3da7e9a2-ea00-4b41-9579-ff8e05318ebf\",\n" +
                "    \"depcode\" : \"330203X10200\",\n" +
                "    \"depname\" : \"浙江省宁波市公安消防支队特勤大队特勤二中队\",\n" +
                "    \"depshort\" : \"特勤二中队\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"0\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533191000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"330203X102\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"pid\" : \"1\",\n" +
                "    \"depcode\" : \"330203000000\",\n" +
                "    \"depname\" : \"浙江省宁波市公安局海曙分局\",\n" +
                "    \"depshort\" : \"海曙分局\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : \"255\",\n" +
                "    \"creator\" : \"sys\",\n" +
                "    \"creatdate\" : 1394533189000,\n" +
                "    \"modifier\" : \"330227198809275417\",\n" +
                "    \"modifydate\" : 1399945503000,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"330203\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  }, {\n" +
                "    \"id\" : \"e8bd9a7a-dbf4-4163-a9ae-d3a5a19fa9e6\",\n" +
                "    \"pid\" : \"444d3aa1-fcc7-4952-bab3-5bbdb745f665\",\n" +
                "    \"depcode\" : \"330203490000\",\n" +
                "    \"depname\" : \"拘留所\",\n" +
                "    \"depshort\" : \"海曙分局拘留所\",\n" +
                "    \"telephone\" : null,\n" +
                "    \"address\" : null,\n" +
                "    \"remark\" : null,\n" +
                "    \"sort\" : null,\n" +
                "    \"creator\" : \"330226198111150472\",\n" +
                "    \"creatdate\" : 1492592097000,\n" +
                "    \"modifier\" : null,\n" +
                "    \"modifydate\" : null,\n" +
                "    \"depgrade\" : \"3\",\n" +
                "    \"pname\" : null,\n" +
                "    \"shortCode\" : \"33020349\",\n" +
                "    \"csize\" : 0,\n" +
                "    \"mainCode\" : \"330203000000\",\n" +
                "    \"mainShortCode\" : \"330203\"\n" +
                "  } ]\n" +
                "}";
    }

}
