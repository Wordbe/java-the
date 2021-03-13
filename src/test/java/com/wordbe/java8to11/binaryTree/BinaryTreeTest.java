package com.wordbe.java8to11.binaryTree;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinaryTreeTest {
    private Node root;
    private BinaryTree binaryTree;

    /**
     *  1
     *      2
     *          4
     *              7
     *                  9
     *      3
     *          5
     *          6
     *              8
     *                  10
     */
    @BeforeEach
    void setUp() {
        Node node10 = new Node(null,null,10);
        Node node9 = new Node(null,null,9);
        Node node8 = new Node(node10,null,8);
        Node node7 = new Node(null,node9,7);
        Node node6 = new Node(node8,null,6);
        Node node5 = new Node(null,null,5);
        Node node4= new Node(node7,null,4);
        Node node3 = new Node(node5,node6,3);
        Node node2 = new Node(node4,null,2);
        Node node1 = new Node(node2,node3,1);

        binaryTree = new BinaryTree(node1);
        root = binaryTree.getRoot();
    }

    @Test
    void getRoot() {
        Assertions.assertEquals(1, root.getValue());
    }

    @Test
    void bfs() {
        binaryTree.bfs(root);
    }

    @Test
    void dfs() {
        binaryTree.dfs(root);
    }

}