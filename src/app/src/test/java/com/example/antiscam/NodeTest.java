package com.example.antiscam;

import static org.junit.Assert.assertEquals;

import com.example.antiscam.tool.Node;

import org.junit.Before;
import org.junit.Test;

public class NodeTest {

    private Node<String, Integer> node;
    private Node<String, Integer> node_left;
    private Node<String, Integer> node_right;
    @Before
    public void setUp() {
        node = new Node<>("one", 1);
        node_left = new Node<>("two", 2);
        node_right = new Node<>("three", 3);
        node.setLeft(node_left);
        node.setRight(node_right);
    }

    @Test
    public void getAndSetKeyTest() {
        assertEquals("one", node.getKey());
        node.setKey("test");
        assertEquals("test", node.getKey());
    }

    @Test
    public void getLeftKeyTest() {
        assertEquals("two", node.getLeft().getKey());
    }

    @Test
    public void getRightKeyTest() {
        assertEquals("three", node.getRight().getKey());
    }
}
