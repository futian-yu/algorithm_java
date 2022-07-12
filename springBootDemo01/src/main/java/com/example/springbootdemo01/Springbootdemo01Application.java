package com.example.springbootdemo01;

import com.example.springbootdemo01.binary_tree.BinaryTree01;
import com.example.springbootdemo01.common.TreeNode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class Springbootdemo01Application {

    static class TreeNodeNew {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNodeNew(int x) { val = x; }
    }

    public static void main(String[] args) {
        //1. 107. 二叉树的层序遍历 II
        Integer[] data = new Integer[]{1,2,3,4,5,6};
        ArrayList<TreeNode> list = new ArrayList<>();
        create(data,list);
        List<List<Integer>> lists = BinaryTree01.levelOrderBottom(list.get(0));
        System.out.println("========01:"+lists.toString());

        //2.637. 二叉树的层平均值
        Integer[] data02 = new Integer[]{1,2,3,4,5,6};
        ArrayList<TreeNode> list02 = new ArrayList<>();
        create(data02,list02);
        List<Double> doubles = BinaryTree01.averageOfLevels(list02.get(0));
        System.out.println("========02:"+doubles.toString());

        //3.111. 二叉树的最小深度
        Integer[] data03 = new Integer[]{1,2,3,4,5};
        ArrayList<TreeNode> list03 = new ArrayList<>();
        create(data03,list03);
        int minDepth = BinaryTree01.minDepth(list03.get(0));
        System.out.println("========03:"+minDepth);

        //4.112. 路径总和
        Integer[] data04 = new Integer[]{1,2,3,4,5,6};
        ArrayList<TreeNode> list04 = new ArrayList<>();
        int sum =10;
        create(data04,list04);
        boolean hasPathSum = BinaryTree01.hasPathSum(list04.get(0), sum);
        System.out.println("========04:"+hasPathSum);

        //5.257. 二叉树的所有路径
        Integer[] data05 = new Integer[]{1,2,3,4,5,6,7};
        ArrayList<TreeNode> list05 = new ArrayList<>();
        create(data05,list05);
        List<String> strings = BinaryTree01.binaryTreePaths(list05.get(0));
        System.out.println("========05:"+strings);

        //6.二叉树dfs前中后序递归、迭代、层序遍历实现
        Integer[] data06 = new Integer[]{1,2,3,4,5,6};
//        Integer[] data06 = new Integer[]{1,2,2,3,4,4,3};
        ArrayList<TreeNode> list06 = new ArrayList<>();
        create(data06,list06);
        ArrayList<Integer> resultList = new ArrayList<>();
        BinaryTree01.dfsDemo01(list06.get(0),resultList);
        System.out.println("========06前序:"+resultList);
        resultList.clear();

        BinaryTree01.dfsDemo04(list06.get(0),resultList);
        System.out.println("========06非递归前序:"+resultList);
        resultList.clear();

        BinaryTree01.dfsDemo02(list06.get(0),resultList);
        System.out.println("========06中序:"+resultList);
        resultList.clear();

        BinaryTree01.dfsDemo05(list06.get(0),resultList);
        System.out.println("========06非递归中序:"+resultList);
        resultList.clear();

        BinaryTree01.dfsDemo03(list06.get(0),resultList);
        System.out.println("========06后续:"+resultList);
        resultList.clear();

        BinaryTree01.dfsDemo06(list06.get(0),resultList);
        System.out.println("========06非递归后续:"+resultList);
        resultList.clear();

        BinaryTree01.levelDemo07(list06.get(0),resultList);
        System.out.println("========07.层序遍历:"+resultList);
        resultList.clear();

        //7.q27二叉树的镜像
        BinaryTree01.minioTree(list06.get(0));
        BinaryTree01.levelDemo07(list06.get(0),resultList);
        System.out.println("========08.镜像后，层序遍历:"+resultList);
        resultList.clear();

        BinaryTree01.minioTree(list06.get(0));
        BinaryTree01.levelDemo07(list06.get(0),resultList);
        System.out.println("========08.还原二叉树，镜像前，层序遍历:"+resultList);
        resultList.clear();

        //8.q28. 对称的二叉树
        boolean balance = BinaryTree01.isBalance(list06.get(0));
        System.out.println("========09.是否为对称二叉树:"+balance);

        //9.34. 二叉树中和为某一值的路径
        ArrayList<List<Integer>> paths = BinaryTree01.findTargetPath(list06.get(0), 10);
        System.out.println("========10.和为target==10的路径:"+paths.toString());

        //6 2 8 0 4
        //11（1）.68.二叉搜索树的最近公共祖先
        Integer[] data10 = new Integer[]{6,2,8,0,4};
        ArrayList<TreeNode> list10 = new ArrayList<>();
        create(data10,list10);
        TreeNode treeNodeZero = new TreeNode(0);
        TreeNode treeNodeFour = new TreeNode(4);
        TreeNode treeNode = BinaryTree01.lowestCommonAncestor(list10.get(0), treeNodeZero, treeNodeFour);
        System.out.println("========11.二叉搜索树 最近公共祖先:"+treeNode.getValue());

        //10（2）.68.二叉树 的最近公共祖先
        TreeNode root = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        root.setRight(right);
        TreeNode left = new TreeNode(3);
        root.setLeft(left);
        TreeNode ancestorIIMethodOne = BinaryTree01.lowestCommonAncestorIIMethodOne(root, left, right);
        TreeNode ancestorIIMethodTwo = BinaryTree01.lowestCommonAncestorIIMethodTwo(root, left, right);
        System.out.println("==========12.二叉树 最近公共祖先（递归）："+ancestorIIMethodOne.getValue());
        System.out.println("==========12.二叉树 最近公共祖先（非 递归）："+ancestorIIMethodTwo.getValue());

        SpringApplication.run(Springbootdemo01Application.class, args);
    }

    /**
     * 创建二叉树
     * @param datas
     * @param list
     */
    public static void create(Integer[] datas, List<TreeNode> list){
        // 将数组里面的东西变成节点的形式
        for(int i=0;i<datas.length;i++) {
            TreeNode node=new TreeNode(datas[i]);
            list.add(node);
        }

        // 节点关联成树
        for(int index=0;index<list.size()/2-1;index++){
            //编号为n的节点他的左子节点编号为2*n 右子节点编号为2*n+1 但是因为list从0开始编号，所以还要+1
            list.get(index).setLeft(list.get(index*2+1));
            //与上同理
            list.get(index).setRight(list.get(index*2+2));
        }

        // 最后一个父节点，因为最后一个父节点可能没有右孩子，所以单独拿出来处理 避免单孩子情况
        int lastParentIndex=list.size()/2-1;
        list.get(lastParentIndex).setLeft(list.get(lastParentIndex*2+1));
        if (list.size()%2==1) {
            // 如果有奇数个节点，最后一个父节点才有右子节点
            list.get(lastParentIndex).setRight(list.get(lastParentIndex*2+2));
        }
    }





}


