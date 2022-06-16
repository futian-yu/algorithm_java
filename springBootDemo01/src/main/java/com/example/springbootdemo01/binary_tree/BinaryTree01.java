package com.example.springbootdemo01.binary_tree;

import com.example.springbootdemo01.common.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * 二叉树题目汇总
 */
public class BinaryTree01 {
    /**
     * 107. 二叉树的层序遍历 II
     * 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levelOrder = new LinkedList<>();
        if (root == null) {
            return levelOrder;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.getValue());
                TreeNode leftNode = node.getLeft();
                TreeNode rightNode = node.getRight();

                if (leftNode != null) {
                    queue.offer(leftNode);
                }
                if (rightNode != null) {
                    queue.offer(rightNode);
                }
            }
            //头插元素
            levelOrder.add(0, level);
        }

        return levelOrder;
    }

    /**
     * 637.二叉树的层平均值
     * <p>
     * 首先想到的是广度遍历，深度遍历的答案没看懂，以后有时间再看吧。
     * 广度遍历的核心是如何确定每一层是否结束。 平均值 = 每一层的和/每一层的数量
     *
     * @param root
     * @return
     */
    public static List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return null;
        }

        ArrayList<Double> averages = new ArrayList<>();
        //初始化一个队列
        Queue<TreeNode> queue = new LinkedList<>();
        double sum = 0;
        queue.offer(root);

        while (!queue.isEmpty()) {
            //每一层的节点数
            int size = queue.size();
            sum = 0;
            //处理树的每一层【关键】
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum = sum + node.getValue();
                if (node.getLeft() != null) {
                    queue.offer(node.getLeft());
                }

                if (node.getRight() != null) {
                    queue.offer(node.getRight());
                }
            }
            averages.add(sum / size);
        }
        return averages;
    }

    /**
     * 111. 二叉树的最小深度
     * 最小深度是指根节点到最近叶子节点的节点数量
     *
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }
        if (root.getLeft() == null && root.getRight() == null) {
            return 1;
        }

        //左子树、右子树的最小深度，递归求解。
        int minDepth = Integer.MAX_VALUE;
        //递归求左右树的最小深度
        //左树递归.
        if (root.getLeft() != null) {
            minDepth = Math.min(minDepth(root.getLeft()), minDepth);
        }

        //右树递归(左右树前后顺序可调换)
        if (root.getRight() != null) {
            minDepth = Math.min(minDepth(root.getRight()), minDepth);
        }

        return minDepth + 1;
    }

    /**
     * 112.路径总和
     * 给一个数,问是否存在一个路径，该路径的和刚好等于这个数？
     *
     * @param root
     * @param targetSum
     * @return
     */
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.getLeft() == null && root.getRight() == null) {
            return targetSum == root.getValue();
        }

        return hasPathSum(root.getLeft(), targetSum - root.getValue())
                || hasPathSum(root.getRight(), targetSum - root.getValue());
    }

    /**
     * 257. 二叉树的所有路径
     * 打印出二叉树的所有路径（根节点到叶子节点）
     * <p>
     * 思路：递归先用文字简单写出思路，递归然后找出递归的出口。进而再根据已有的思路开始写代码，不要一开始就开始写代码。
     * 一开始重要的是思路，伪代码写出即可。
     *
     * @param root
     * @return
     */
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> strings = new ArrayList<>();
        //处理根节点
        if (root == null) {
            return null;
        }
        findPaths(root, new StringBuffer(), strings);
        return strings;

    }

    private static void findPaths(TreeNode root, StringBuffer stringBuffer, List<String> strings) {
        if (root != null) {
            StringBuffer tempString = new StringBuffer(stringBuffer);
            tempString.append(root.getValue());
            //叶子结点
            if (root.getLeft() == null && root.getRight() == null) {
                strings.add(tempString.toString());
            } else {
                //非叶子节点
                tempString.append("->");
                findPaths(root.getLeft(), tempString, strings);
                findPaths(root.getRight(), tempString, strings);
            }
        }
    }

    /**
     * 递归前序
     * 二叉树的前序、中序、后序遍历
     */
    public static void dfsDemo01(TreeNode root, ArrayList<Integer> list) {
        //1.0前序
        if (root != null) {
            int value = root.getValue();
            list.add(value);
            dfsDemo01(root.getLeft(), list);
            dfsDemo01(root.getRight(), list);
        }
    }

    /**
     * 递归中序
     * 二叉树的前序、中序、后序遍历
     */
    public static void dfsDemo02(TreeNode root, ArrayList<Integer> list) {
        if (root != null) {
            //2.0中序
            dfsDemo02(root.getLeft(), list);
            int value = root.getValue();
            list.add(value);
            dfsDemo02(root.getRight(), list);
        }
    }

    /**
     * 递归后续
     * 二叉树的前序、中序、后序遍历
     */
    public static void dfsDemo03(TreeNode root, ArrayList<Integer> list) {
        if (root != null) {//注意这个出口不要忘记了！容易忘记该递归的出口。
            //2.0中序
            dfsDemo03(root.getLeft(), list);
            dfsDemo03(root.getRight(), list);
            int value = root.getValue();
            list.add(value);
        }
    }

    /**
     * dfs先序 迭代法
     *
     * @param root
     * @param list
     */
    public static void dfsDemo04(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        //可以用链表结构的List定义一个栈。  linkedList.push()入栈，linkedList.pop()出栈。
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            if (node != null) {
                list.add(node.getValue());
                stack.push(node.getRight());
                stack.push(node.getLeft());
            }
        }

    }

    /**
     * 中序-迭代法
     *
     * @param root
     * @param list
     */
    public static void dfsDemo05(TreeNode root, ArrayList<Integer> list) {
        TreeNode cur = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.getLeft();
            } else {
                TreeNode node = stack.pop();//叶节点的左孩子为空，则弹出自己；叶节点的右孩子为空，则弹出父节点。
                list.add(node.getValue());
                cur = node.getRight();
            }
        }


    }

    /**
     * 后序-迭代法 (在前序的基础上调换一行代码再翻转一下。)
     *
     * @param root
     * @param list
     */
    public static void dfsDemo06(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        //可以用链表结构的List定义一个栈。  linkedList.push()入栈，linkedList.pop()出栈。
        //用链表定义栈等价于 new Stack<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            if (node != null) {
                list.add(node.getValue());
                stack.push(node.getLeft());
                stack.push(node.getRight());
            }
        }
        Collections.reverse(list);//翻转ArrayList
    }

    /**
     * 二叉树的层序遍历
     * @param root
     * @param list
     */
    public static void levelDemo07(TreeNode root,ArrayList<Integer> list){
        if (root != null){
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while(!queue.isEmpty()){
                TreeNode node = queue.poll();
                list.add(node.getValue());
                if (node.getLeft()!=null){
                    queue.offer(node.getLeft());
                }

                if (node.getRight()!=null){
                    queue.offer(node.getRight());
                }
            }

        }
    }

    /**
     * q27二叉树的镜像
     *
     * 思路: 递归一直遍历得到叶子节点，叶子节点的左右镜像交换后，继续往上一层，这时只要交换左右整颗树即可，如此继续往上循环。
     *
     * @param root
     */
    public static TreeNode minioTree(TreeNode root){
        if (root==null){
            return null;
        }

        TreeNode left = minioTree(root.getLeft());
        TreeNode right = minioTree(root.getRight());

        //交换的是root所指向的节点
        root.setLeft(right);
        root.setRight(left);

        return root;
    }

    /**
     * q28. 对称的二叉树
     *
     * @param root
     * @return
     */
    public static boolean isBalance(TreeNode root){
        if (root==null){
            //空树默认为对称
            return true;
        }
        return symmetricTree(root, root);
    }

    /**
     * 双指针递归判断是否对称思想
     * @param p
     * @param q
     * @return
     */
    public static boolean symmetricTree(TreeNode p,TreeNode q){
        //1.出口（出口好好判断一下，一般是上一层函数的p.getLeft()为null传进来，在这一层函数就是p==null，这一点一定要注意）
        //确定好递归参数后，这里出口怎么写是该递归函数的关键。
        if (p==null && q==null){
            return true;
        }
        if (p==null || q==null){
            return false;
        }

        //2.条件：递归，root两个指针p,q;  p左孩子==q右孩子 && p右孩子==p左孩子
        return p.getValue()==q.getValue()
                && symmetricTree(p.getLeft(),q.getRight())
                && symmetricTree(p.getRight(),q.getLeft());
    }

    public static ArrayList<List<Integer>> findTargetPath(TreeNode root,int target){

        ArrayList<List<Integer>> allPaths = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        getPath(root, target, allPaths, path);
        return allPaths;
    }

    /**
     *  q34. 二叉树中和为某一值的路径
     * @param root
     * @param target
     * @param allPaths
     * @param path
     * @return
     */
    public static ArrayList<List<Integer>> getPath(TreeNode root,int target,ArrayList<List<Integer>> allPaths,LinkedList<Integer> path){
        if (root == null){
            return null;
        }

        path.add(root.getValue());
        target = target - root.getValue();

        if (root.getLeft()==null && root.getRight() == null && target == 0){
            allPaths.add(new LinkedList<>(path));//注意，这里一定要把新开辟一个链表，然后把path作为构造函数参数传进去，否则在最后一行就被清零了。
        }

        getPath(root.getLeft(),target,allPaths,path);
        getPath(root.getRight(),target,allPaths,path);
        //如果一直遍历到叶子节点都不符合，那么把该节点从链表末尾删除；
        path.removeLast();
        return allPaths;
    }

}
