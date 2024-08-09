package com.smile67.day2.hiorder;

import org.apache.catalina.mbeans.SparseUserDatabaseMBean;

import java.util.LinkedList;
import java.util.function.Consumer;

public class C02BinaryTree {
    public record TreeNode(int value, TreeNode left, TreeNode right) {
        @Override
        public String toString() {
            return "%d".formatted(value);
        }
    }

    enum Type {
        PRE, IN, POST
    }

    /**
     * 深度优先遍历的 非递归 实现
     * 递归遍历使用的是方法栈可能会溢出
     */
    public static void traversal0(TreeNode root, Type type, Consumer<TreeNode> consumer) {
        // 用来记录回去的路
        LinkedList<TreeNode> stack = new LinkedList<>();
        // 当前节点
        TreeNode curr = root;
        // 记录最近一次处理完的节点
        TreeNode last = null;
        // 没有向左走到头或者还有未归路
        while (curr != null || !stack.isEmpty()) {
            // 左边未走完
            if (curr != null) {
                // 记住来时的路
                stack.push(curr);
                // ------------------ 处理前序遍历的值
                if (type == Type.PRE) {
                    consumer.accept(curr);
                }
                // 下次向左走
                curr = curr.left;
            }
            // 左边已经走完
            else {
                // 上次的路
                TreeNode peek = stack.peek();
                // 没有右子树
                if (peek.right == null) {
                    last = stack.pop();
                    // ------------------ 处理中序、后序遍历的值
                    if (type == Type.IN || type == Type.POST) {
                        consumer.accept(peek);
                    }
                }
                // 有右子树，已经走完
                else if (peek.right == last) {
                    // ------------------ 处理后序遍历的值
                    if (type == Type.POST) {
                        consumer.accept(peek);
                    }
                    last = stack.pop();
                }
                // 有右子树，未走完
                else {
                    // ------------------ 处理中序遍历的值
                    if (type == Type.IN) {
                        consumer.accept(peek);
                    }
                    curr = peek.right;
                }
            }
        }
    }

    public static void main(String[] args) {
//                1
//               / \
//              2   3
//             /   / \
//            4   5   6
//
//            前序 1 2 4 3 5 6  值左右
//            中序 4 2 1 5 3 6  左值右
//            后序 4 2 5 6 3 1  左右值


        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4, null, null),
                        null
                ),
                new TreeNode(3,
                        new TreeNode(5, null, null),
                        new TreeNode(6, null, null)
                )
        );
        traversal0(root, Type.POST, System.out::print);
    }

    public static void traversal2(TreeNode root, Type type, Consumer<TreeNode> consumer) {
        if (root == null) {
            return;
        }
        // 前序处理值
        if (type == Type.PRE) {
            consumer.accept(root);
        }
        traversal2(root.left, type, consumer);
        // 中序处理值
        if (type == Type.IN) {
            consumer.accept(root);
        }
        traversal2(root.right, type, consumer);
        // 后序处理值
        if (type == Type.POST) {
            consumer.accept(root);
        }
    }
}
