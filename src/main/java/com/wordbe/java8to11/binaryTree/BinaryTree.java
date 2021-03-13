package com.wordbe.java8to11.binaryTree;

import java.util.LinkedList;

public class BinaryTree {
    private Node root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }


    public void bfs(Node root) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.getValue() + " ");

            if (node.getLeft() != null) {
                queue.offer(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.offer(node.getRight());
            }
        }
        System.out.println();
    }

    // Inorder (left, root, right order)
    public void dfs(Node root) {
        if (root == null) return;

        dfs(root.getLeft());
        System.out.print(root.getValue() + " ");
        dfs(root.getRight());
    }
}
