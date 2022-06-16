package com.example.springbootdemo01.common;

public class TreeNode {
    private int value;        //节点的值
    private TreeNode node;        //此节点，数据类型为Node
    private TreeNode left;        //此节点的左子节点，数据类型为Node
    private TreeNode right;       //此节点的右子节点，数据类型为Node

    public TreeNode(){}

    public TreeNode(int value){
        this.value=value;
        this.left = null;
        this.right = null;
    }

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNode getNode() {
        return node;
    }

    public void setNode(TreeNode node) {
        this.node = node;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
