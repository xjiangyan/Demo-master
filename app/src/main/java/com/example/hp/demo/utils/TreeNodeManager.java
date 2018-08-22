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
public class TreeNodeManager {
    private List<TreeNodes> list;// 树的所有节点

    public TreeNodeManager(TreeNodes[] items) {
        list = new ArrayList<TreeNodes>();
        for (TreeNodes treeNode : items) {
            list.add(treeNode);
        }
    }

    public TreeNodeManager(List<TreeNodes> items) {
        list = items;
    }

    /**
     * 根据节点ID获取一个节点
     *
     * @param id 节点ID
     * @return 对应的节点对象
     */
    public TreeNodes getTreeNodeAT(String id) {
        for (TreeNodes treeNode : list) {
            if (treeNode.getId().equals(id))
                return treeNode;
        }
        return null;
    }

    /**
     * 获取树的根节点
     *
     * @return 一棵树的根节点
     */
    public TreeNodes getRoot() {
        for (TreeNodes treeNode : list) {
            if (treeNode.getPid().equals("1"))
                return treeNode;
        }
        return null;
    }
}
