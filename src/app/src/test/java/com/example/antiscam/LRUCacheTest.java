package com.example.antiscam;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.example.antiscam.tool.LRUCache;

import org.junit.Before;
import org.junit.Test;

public class LRUCacheTest {

    private LRUCache<String, Integer> cache;

    @Test
    public void testBasicFunction() {
        cache = new LRUCache<>(3);
        cache.put("one", 1);
        assertEquals(Integer.valueOf(1), cache.get("one"));
    }

    @Test
    public void testEmptyCache() {
        cache = new LRUCache<>(3);
        assertNull(cache.get("anyKey"));
    }

    @Test
    public void testCacheSizeLimit() {
        cache = new LRUCache<>(3);
        cache.put("one", 1);
        cache.put("two", 2);
        cache.put("three", 3);
        assertEquals(3, cache.getSize());

        cache.put("four", 4);
        cache.put("five", 5);
        cache.put("six", 6);
        cache.put("seven", 7);
        cache.put("eight", 8);
        cache.put("nine", 9);
        assertEquals(3, cache.getSize());
    }

    @Test
    public void testAddWhenFull() {
        cache = new LRUCache<>(3);
        cache.put("one", 1);
        cache.put("two", 2);
        cache.put("three", 3);
        cache.put("four", 4);
        assertNull(cache.get("one"));
        assertEquals(3, cache.getSize());
    }

    @Test
    public void testGetAll() {
        cache = new LRUCache<>(3);
        cache.put("one", 1);
        cache.put("two", 2);
        cache.put("three", 3);
        assertEquals(3, cache.getAll().size());
        assertEquals(Integer.valueOf(3), cache.getAll().get(0));
        assertEquals(Integer.valueOf(2), cache.getAll().get(1));
        assertEquals(Integer.valueOf(1), cache.getAll().get(2));
    }

    @Test
    public void testRemove() {
        cache = new LRUCache<>(3);
        cache.put("one", 1);
        cache.put("two", 2);
        cache.put("three", 3);
        cache.remove("three", 3);
        assertEquals(2, cache.getSize());
    }

    @Test
    public void testAddOrder() {
        cache = new LRUCache<>(3);
        cache.put("one", 1);
        cache.put("two", 2);
        cache.put("three", 3);
        cache.get("one");
        cache.put("four", 4);
        assertNull(cache.get("two"));
    }

    @Test
    public void testRecentUsage() {
        cache = new LRUCache<>(3);
        cache.put("one", 1);
        cache.put("two", 2);
        cache.put("three", 3);
        cache.get("two");
        cache.put("five", 5);
        assertNotNull(cache.get("three"));
        assertEquals(Integer.valueOf(2), cache.get("two"));
    }

    @Test
    public void testOverwriteExistingKey() {
        cache = new LRUCache<>(3);
        cache.put("two", 2);
        cache.put("two", 22);
        assertEquals(Integer.valueOf(22), cache.get("two"));
    }

    @Test
    public void testNullValue() {
        cache = new LRUCache<>(3);
        cache.put("nullTest", null);
        assertNull(cache.get("nullTest"));
    }
}
