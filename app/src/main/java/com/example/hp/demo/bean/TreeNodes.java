package com.example.hp.demo.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class TreeNodes {
    private String code;
    private String id;//主键ID


    private String pid;//父节点ID
    private String content;//节点内容
    private List<TreeNodes> children = new ArrayList<TreeNodes>();//子孙节点

    public TreeNodes(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public TreeNodes(String id, String pid, String content, String code) {
        this.id = id;
        this.pid = pid;
        this.content = content;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<TreeNodes> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNodes> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TreeNode [id=" + id + ", pid=" + pid + ", content=" + content
                + ", children=" + children + "]";
    }
}
