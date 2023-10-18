package com.example.antiscam;

import static org.junit.Assert.*;

import com.example.antiscam.tool.AVLTree;

import org.junit.Before;
import org.junit.Test;

public class AVLTreeTest {

    private AVLTree<Integer, Integer> tree;

    @Before
    public void setUp() {
        tree = new AVLTree<>();
    }

    @Test
    public void testBasicPutAndGet() {
        tree.add(1, 1);
        assertEquals(Integer.valueOf(1), tree.get(1));
    }

    @Test
    public void leftRotateTest() {
        AVLTree<Integer, Integer> avl = new AVLTree<>();
        avl.add(5,5);
        avl.add(8,8);
        avl.add(10,10);
        assertEquals(Integer.valueOf(5), avl.getRoot().getLeft().getValue());
    }

    @Test
    public void rightRotateTest() {
        AVLTree<Integer, Integer> avl = new AVLTree<>();
        avl.add(10,10);
        avl.add(8,8);
        avl.add(5,5);
        assertEquals(Integer.valueOf(5), avl.getRoot().getLeft().getValue());
    }

    @Test
    public void testBalancingSimple() {
        tree.add(2, 2);
        tree.add(3, 3);
        tree.add(4, 4);
        tree.add(5, 5);
        assertTrue(tree.isBalanced());
    }

    @Test
    public void testBalancingAfterDelete() {
        tree.add(1, 1);
        tree.add(2, 2);
        tree.add(3, 3);
        tree.remove(3);
        assertNull(tree.get(3));
        assertTrue(tree.isBalanced());
    }

    @Test
    public void testBalancingComplicate() {
        for (int i = 6; i <= 100; i++) {
            tree.add(1 + i, i);
        }
        assertTrue(tree.isBalanced());
    }

    @Test
    public void testInsertAndRetrieve() {
        tree.add(1, 1);
        assertEquals(Integer.valueOf(1), tree.get(1));
    }

    @Test
    public void testDeleteAndRetrieve() {
        tree.add(2, 2);
        tree.remove(2);
        assertNull(tree.get(2));
    }

    @Test
    public void testKeyOverwrite() {
        tree.add(3, 3);
        tree.add(3, 33);
        assertEquals(Integer.valueOf(33), tree.get(3));
    }


    @Test
    public void testDeleteNonExistentKey() {
        tree.remove(null);
        assertTrue(tree.isBalanced());
    }
}