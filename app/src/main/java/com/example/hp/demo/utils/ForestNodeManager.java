package com.example.hp.demo.utils;


import com.example.hp.demo.bean.TreeNodes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class ForestNodeManager {
    private List<TreeNodes> list;// 森林的所有节点

    public ForestNodeManager(TreeNodes[] items) {
        list = new ArrayList<TreeNodes>();
        for (TreeNodes treeNode : items) {
            list.add(treeNode);
        }
    }

    public ForestNodeManager(List<TreeNodes> items) {
        list = items;
    }

    /**
     * 根据节点ID获取一个节点
     *
     * @param id
     *            节点ID
     * @return 对应的节点对象
     */
    public TreeNodes getTreeNodeAT(String id) {
        for (TreeNodes treeNode : list) {
            if (treeNode.getId() == id)
                return treeNode;
        }
        return null;
    }

    /**
     * 获取树的根节点【一个森林对应多颗树】
     *
     * @return 树的根节点集合
     */
    public List<TreeNodes> getRoot() {
        List<TreeNodes> roots = new ArrayList<TreeNodes>();
        for (TreeNodes treeNode : list) {
            if (treeNode.getPid() == "0")
                roots.add(treeNode);
        }
        return roots;
    }
}
