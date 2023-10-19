package com.example.antiscam;

import static org.junit.Assert.*;

import com.example.antiscam.tool.ListNode;

import org.junit.Before;
import org.junit.Test;

public class ListNodeTest {

    private ListNode<String> node;

    @Before
    public void setUp() {
        node = new ListNode<>("initialKey");
    }

    @Test
    public void testGetKey() {
        assertEquals("initialKey", node.getKey());
    }

    @Test
    public void testSetKey() {
        node.setKey("newKey");
        assertEquals("newKey", node.getKey());
    }

    @Test
    public void testGetPrev() {
        assertNull(node.getPrev());

        ListNode<String> prevNode = new ListNode<>("prevKey");
        node.setPrev(prevNode);
        assertEquals(prevNode, node.getPrev());
    }

    @Test
    public void testSetPrev() {
        ListNode<String> prevNode = new ListNode<>("prevKey");
        node.setPrev(prevNode);
        assertEquals(prevNode, node.getPrev());
    }

    @Test
    public void testGetNext() {
        assertNull(node.getNext());

        ListNode<String> nextNode = new ListNode<>("nextKey");
        node.setNext(nextNode);
        assertEquals(nextNode, node.getNext());
    }

    @Test
    public void testSetNext() {
        ListNode<String> nextNode = new ListNode<>("nextKey");
        node.setNext(nextNode);
        assertEquals(nextNode, node.getNext());
    }
}
