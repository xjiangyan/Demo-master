package com.example.hp.demo.utils;


import com.example.hp.demo.bean.TreeNodes;

import java.util.List;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class TreeNodeMerger {
    /**
     * 将节点数组归并为一棵树（填充节点的children域）
     * 时间复杂度为O(n^2)
     *
     * @param items 节点域
     * @return
     */
    public static TreeNodes merge(TreeNodes[] items) {
        TreeNodeManager treeNodeManager = new TreeNodeManager(items);
        for (TreeNodes treeNode : items) {
            if (!treeNode.getPid().equals("1")) {
                TreeNodes t = treeNodeManager.getTreeNodeAT(treeNode.getPid());
                if (t != null && t.getChildren() != null) {
                    t.getChildren().add(treeNode);
                } else {
                    continue;
                }
            }
        }
        return treeNodeManager.getRoot();
    }

    /**
     * 将节点数组归并为一棵树（填充节点的children域）
     * 时间复杂度为O(n^2)
     *
     * @param items 节点域
     * @return
     */
    public static TreeNodes merge(List<TreeNodes> items) {
        TreeNodeManager treeNodeManager = new TreeNodeManager(items);
        for (TreeNodes treeNode : items) {
            if (!treeNode.getPid().equals("1")) {
                TreeNodes t = treeNodeManager.getTreeNodeAT(treeNode.getPid());
                t.getChildren().add(treeNode);
            }
        }
        return treeNodeManager.getRoot();
    }
}
