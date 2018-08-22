package com.example.hp.demo.utils;

import android.content.Context;

import com.example.hp.demo.R;
import com.example.hp.demo.bean.TreeNodes;
import com.example.hp.demo.holder.ArrowExpandSelectableHeaderHolder;
import com.example.hp.demo.holder.IconTreeItemHolder;
import com.unnamed.b.atv.model.TreeNode;


/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class ManageTreesUtils {
    private static TreeNode treeone, treetwo, treethree, treefour, treefive, treesix;
    private static int x = 0;
    private static TreeNode[] child = new TreeNode[999];

    public static TreeNode manageTrees(TreeNode treeone, TreeNodes treeNode, Context context) {
        //        for (TreeNodes nodes : treeNode.getChildren()) {
        //            TreeNode treesix = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_people, nodes.getContent(), nodes.getCode())).setViewHolder(new ArrowExpandSelectableHeaderHolder(context));
        //            treeone.addChild(treesix);
        //            if (nodes.getChildren().size() > 0) {
        //                return manageTrees(treesix, nodes, context);
        //            } else {
        //                continue;
        //            }
        //        }
        //        return treeone;


        if (treeNode.getChildren() != null) {

            for (int m = 0; m < treeNode.getChildren().size(); m++) {
                //二级
                treetwo = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_people, treeNode.getChildren().get(m).getContent(), treeNode.getChildren().get(m).getCode())).setViewHolder(new ArrowExpandSelectableHeaderHolder(context));
                treeone.addChild(treetwo);
                if (treeNode.getChildren().get(m).getChildren() != null) {
                    for (int n = 0; n < treeNode.getChildren().get(m).getChildren().size(); n++) {
                        //三级
                        treethree = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_person, treeNode.getChildren().get(m).getChildren().get(n).getContent(), treeNode.getChildren().get(m).getChildren().get(n).getCode())).setViewHolder(new ArrowExpandSelectableHeaderHolder(context));
                        treetwo.addChild(treethree);
                        if (treeNode.getChildren().get(m).getChildren().get(n).getChildren() != null) {
                            for (int k = 0; k < treeNode.getChildren().get(m).getChildren().get(n).getChildren().size(); k++) {
                                //四级
                                treefour = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_person, treeNode.getChildren().get(m).getChildren().get(n).getChildren().get(k).getContent(), treeNode.getChildren().get(m).getChildren().get(n).getChildren().get(k).getCode())).setViewHolder(new ArrowExpandSelectableHeaderHolder(context));
                                treethree.addChild(treefour);

                                if (treeNode.getChildren().get(m).getChildren().get(n).getChildren().get(k).getChildren() != null) {
                                    for (int u = 0; u < treeNode.getChildren().get(m).getChildren().get(n).getChildren().get(k).getChildren().size(); u++) {
                                        //五级
                                        treefive = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_person, treeNode.getChildren().get(m).getChildren().get(n).getChildren().get(k).getChildren().get(u).getContent(), treeNode.getChildren().get(m).getChildren().get(n).getChildren().get(k).getChildren().get(u).getCode())).setViewHolder(new ArrowExpandSelectableHeaderHolder(context));
                                        treefour.addChild(treefive);
                                        if (treeNode.getChildren().get(m).getChildren().get(n).getChildren().get(k).getChildren().get(u).getChildren() != null) {
                                            for (int p = 0; p < treeNode.getChildren().get(m).getChildren().get(n).getChildren().get(k).getChildren().get(u).getChildren().size(); p++) {
                                                //六级
                                                treesix = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_person, treeNode.getChildren().get(m).getChildren().get(n).getChildren().get(k).getChildren().get(u).getChildren().get(p).getContent(), treeNode.getChildren().get(m).getChildren().get(n).getChildren().get(k).getChildren().get(u).getChildren().get(p).getContent())).setViewHolder(new ArrowExpandSelectableHeaderHolder(context));
                                                treefive.addChild(treesix);
                                            }
                                        } else {
                                            continue;
                                        }
                                    }
                                } else {
                                    continue;
                                }
                            }
                        } else {
                            continue;
                        }
                    }
                } else {
                    continue;
                }
            }
        }
        return treeone;
    }
}
