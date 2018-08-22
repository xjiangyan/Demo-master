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
public class ForestNodeMerger {
    /**
     * 将节点数组归并为一个森林（多棵树）（填充节点的children域）
     * 时间复杂度为O(n^2)
     * @param items 节点域
     * @return 多棵树的根节点集合
     */
    public static List<TreeNodes> merge(TreeNodes[] items){
        ForestNodeManager forestNodeManager = new ForestNodeManager(items);
        for (TreeNodes treeNode : items) {
            if(treeNode.getPid()!="0"){
                TreeNodes t = forestNodeManager.getTreeNodeAT(treeNode.getPid());
                if(t!=null)
                t.getChildren().add(treeNode);
            }
        }
        return forestNodeManager.getRoot();
    }

    /**
     * 将节点数组归并为一个森林（多棵树）（填充节点的children域）
     * 时间复杂度为O(n^2)
     * @param items 节点域
     * @return 多棵树的根节点集合
     */
    public static List<TreeNodes> merge(List<TreeNodes> items){
        ForestNodeManager forestNodeManager = new ForestNodeManager(items);
        for (TreeNodes treeNode : items) {
            if(treeNode.getPid()!="0"){
                TreeNodes t = forestNodeManager.getTreeNodeAT(treeNode.getPid());
                t.getChildren().add(treeNode);
            }
        }
        return forestNodeManager.getRoot();
    }
}
