package com.example.springbootdemo01.binary_tree;

import com.example.springbootdemo01.common.TreeNode;

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
     *
     * @param root
     * @param list
     */
    public static void levelDemo07(TreeNode root, ArrayList<Integer> list) {
        if (root != null) {
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                list.add(node.getValue());
                if (node.getLeft() != null) {
                    queue.offer(node.getLeft());
                }

                if (node.getRight() != null) {
                    queue.offer(node.getRight());
                }
            }

        }
    }

    /**
     * q27二叉树的镜像
     * <p>
     * 思路: 递归一直遍历得到叶子节点，叶子节点的左右镜像交换后，继续往上一层，这时只要交换左右整颗树即可，如此继续往上循环。
     *
     * @param root
     */
    public static TreeNode minioTree(TreeNode root) {
        if (root == null) {
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
    public static boolean isBalance(TreeNode root) {
        if (root == null) {
            //空树默认为对称
            return true;
        }
        return symmetricTree(root, root);
    }

    /**
     * 双指针递归判断是否对称思想
     *
     * @param p
     * @param q
     * @return
     */
    public static boolean symmetricTree(TreeNode p, TreeNode q) {
        //1.出口（出口好好判断一下，一般是上一层函数的p.getLeft()为null传进来，在这一层函数就是p==null，这一点一定要注意）
        //确定好递归参数后，这里出口怎么写是该递归函数的关键。
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }

        //2.条件：递归，root两个指针p,q;  p左孩子==q右孩子 && p右孩子==p左孩子
        return p.getValue() == q.getValue()
                && symmetricTree(p.getLeft(), q.getRight())
                && symmetricTree(p.getRight(), q.getLeft());
    }

    public static ArrayList<List<Integer>> findTargetPath(TreeNode root, int target) {

        ArrayList<List<Integer>> allPaths = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        getPath(root, target, allPaths, path);
        return allPaths;
    }

    /**
     * q34. 二叉树中和为某一值的路径
     *
     * @param root
     * @param target
     * @param allPaths
     * @param path
     * @return
     */
    public static ArrayList<List<Integer>> getPath(TreeNode root, int target, ArrayList<List<Integer>> allPaths, LinkedList<Integer> path) {
        if (root == null) {
            return null;
        }

        path.add(root.getValue());
        target = target - root.getValue();

        if (root.getLeft() == null && root.getRight() == null && target == 0) {
            allPaths.add(new LinkedList<>(path));//注意，这里一定要把新开辟一个链表，然后把path作为构造函数参数传进去，否则在最后一行就被清零了。
        }

        getPath(root.getLeft(), target, allPaths, path);
        getPath(root.getRight(), target, allPaths, path);
        //如果一直遍历到叶子节点都不符合，那么把该节点从链表末尾删除；
        path.removeLast();
        return allPaths;
    }

    /**
     * 二叉搜索树 的公共祖先(1)
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> path_p = getPath(root, p);
        List<TreeNode> path_q = getPath(root, q);
        TreeNode ancestor = null;
        for (int i = 0; i < path_p.size() && i < path_q.size(); ++i) {
            if (path_p.get(i) == path_q.get(i)) {
                ancestor = path_p.get(i);//找到两个路径中最后一个相同的节点。
            } else {
                break;
            }
        }
        return ancestor;
    }

    //获取二叉搜索树从根节点到某一节点的路径（我们默认认为二叉搜索树没有重复元素。即定义：左子树 < root < 右子树）
    public static List<TreeNode> getPath(TreeNode root, TreeNode target) {
        List<TreeNode> path = new ArrayList<TreeNode>();
        TreeNode node = root;
        while (node.getValue() != target.getValue()) {
            path.add(node);
            if (target.getValue() < node.getValue()) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        path.add(node);
        return path;
    }

    /**
     * 二叉树 的公共祖先(2)   递归法
     * 写递归的时候为了避免想的太复杂，可以用特指法，简化一个树的实例，根据这个实例写逻辑和递归。
     * <p>
     * 思路：
     * 1、在左、右子树中分别查找是否包含p或q，如果（两种情况：左子树包含p，右子树包含q/左子树包含q，右子树包含p），
     * 那么此时的根节点就是最近公共祖先
     * 2、如果左子树包含p和q，那么到root->left中查找，最近公共祖先在左子树里面
     * 3、如果右子树包含p和q，那么到root->right中查找，最近公共祖先在右子树里面
     * 4、注意：不可能left和right的返回值同时都是nullptr
     */
    public static TreeNode lowestCommonAncestorIIMethodOne(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {//出口
            return root;
        }

        //p或q是否在左子树 ？ 返回root
        TreeNode left = lowestCommonAncestorIIMethodOne(root.getLeft(), p, q);
        //p或q是否在右子树 ？ 返回root
        TreeNode right = lowestCommonAncestorIIMethodOne(root.getRight(), p, q);

        //p、q左子树和右子树各一个
        if (left != null && right != null) {
            return root;
        }

        //都在左子树或都在右子树
        return left == null ? right : left;
    }

    /**
     * 二叉树 的公共祖先(2)   非递归
     * <p>
     * 1、找到root->p的路径
     * 2、找到root->q的路径
     * 3、两条路径求最后一个相交节点
     */
    public static TreeNode lowestCommonAncestorIIMethodTwo(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }

        List<TreeNode> pPath = findPath(root, p);
        List<TreeNode> qPath = findPath(root, q);

        TreeNode common = null;
        for (int i = 0, j = 0; i < pPath.size() && j < qPath.size(); i++, j++) {
            if (pPath.get(i) == qPath.get(j)) {
                common = pPath.get(i);//获取路径中最后一个相同的节点
            }
        }

        return common;
    }

    private static List<TreeNode> findPath(TreeNode root, TreeNode node) {
        List<TreeNode> path = new ArrayList<>();
        dfs(root, node, new ArrayList<>(), path);
        return path;
    }

    /**
     * 深度优先 - 先序。 ---> 获取路径，从根节点到指定节点的路径。（可以，所有都走了一遍，条件过滤掉不满足的即可）
     *
     * @param root
     * @param node
     * @param tmp
     * @param path
     */
    private static void dfs(TreeNode root, TreeNode node, List<TreeNode> tmp, List<TreeNode> path) {
        if (root == null) {
            return;
        }

        tmp.add(root);

        if (root == node) {
            path.addAll(new ArrayList<>(tmp));
        }

        dfs(root.getLeft(), node, tmp, path);
        dfs(root.getRight(), node, tmp, path);

        tmp.remove(tmp.size() - 1);
    }

    /**
     * 54.二叉搜索树的第k大节点(简单：中序遍历，遍历出的结果是从小到大。再get(length - k)个元素)
     *
     * @param root
     * @param k
     * @return
     */
    public static int kthLargest(TreeNode root, int k) {
        List<TreeNode> treeNodes = new LinkedList<>();
        dfs(root, treeNodes);
        return treeNodes.get(treeNodes.size() - k).getValue();
    }

    /**
     * 递归中序遍历 - 二叉搜索树，结果放到list中
     *
     * @param root
     */
    static void dfs(TreeNode root, List<TreeNode> treeNodes) {
        //出口
        if (root == null) {
            return;
        }
        //左中右
        dfs(root.getLeft(), treeNodes);
        treeNodes.add(root);
        dfs(root.getRight(), treeNodes);
    }

    /**
     * 102. 二叉树的层序遍历
     * <p>
     * 思路： 1. 遍历每一层，存储每一层的节点值，汇总输出。
     * 2. 每一层中有个逻辑判断，从左往右 or 从右往左； 顺序控制输入输出用双端队列实现，LinkedList其实就是一个双端队列。
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> nodeQueue = new ArrayDeque<TreeNode>();
        nodeQueue.offer(root);
        boolean isOrderLeft = true;

        // 将每一层的节点元素添加到 nodeQueue中
        while (!nodeQueue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<Integer>();
            int size = nodeQueue.size();
            // 遍历某一层的节点，并将节点放入双端队列中，放的顺序根据标志来判断。
            for (int i = 0; i < size; ++i) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(curNode.getValue());
                } else {
                    levelList.offerFirst(curNode.getValue());
                }
                if (curNode.getLeft() != null) {
                    nodeQueue.offer(curNode.getLeft());
                }
                if (curNode.getRight() != null) {
                    nodeQueue.offer(curNode.getRight());
                }
            }
            ans.add(new LinkedList<Integer>(levelList));
            isOrderLeft = !isOrderLeft;
        }

        return ans;
    }

    /**
     * 113.路径总和 II（中等）
     * 思路：能够递归写出root节点到叶子节点的路径，基本就ok了。
     * @param root
     * @param targetSum
     * @return
     */
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        Deque<Integer> path = new LinkedList<Integer>();
        dfs(root, targetSum, ret, path);
        return ret;
    }

    public static void dfs(TreeNode root, int targetSum, List<List<Integer>> ret, Deque<Integer> path) {
        if (root == null) {
            return;
        }
        path.offerLast(root.getValue());
        targetSum -= root.getValue();
        if (root.getLeft() == null && root.getRight() == null && targetSum == 0) {
            ret.add(new LinkedList<Integer>(path));
        }
        dfs(root.getLeft(), targetSum, ret, path);
        dfs(root.getRight(), targetSum, ret, path);
        path.pollLast();
    }

}
