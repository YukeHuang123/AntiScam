package com.example.antiscam;

import com.example.antiscam.tool.LRUCache;

import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Before;

public class LRUCacheTest {

    private LRUCache<String, Integer> cache;

    @Before
    public void setUp() {
        cache = new LRUCache<>(3);
    }

    @Test
    public void testBasicFunction() {
        cache.put("one", 1);
        assertEquals(Integer.valueOf(1), cache.get("one"));
    }

    @Test
    public void testEmptyCache() {
        assertNull(cache.get("anyKey"));
    }

    @Test
    public void testCacheSizeLimit() {
        cache.put("one", 1);
        cache.put("two", 2);
        cache.put("three", 3);
        assertEquals(3, cache.getSize());

        cache.put("four", 4);
        assertEquals(3, cache.getSize());
    }

    @Test
    public void testEvictionWhenFull() {
        cache.put("one", 1);
        cache.put("two", 2);
        cache.put("three", 3);
        cache.put("four", 4);
        assertNull(cache.get("one"));
        assertEquals(3, cache.getSize());
    }

    @Test
    public void testEvictionOrder() {
        cache.put("one", 1);
        cache.put("two", 2);
        cache.put("three", 3);
        cache.get("one");
        cache.put("four", 4);
        assertNull(cache.get("two"));
    }

    @Test
    public void testRecentUsage() {
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
        cache.put("two", 2);
        cache.put("two", 22);
        assertEquals(Integer.valueOf(22), cache.get("two"));
    }

    @Test
    public void testNullValue() {
        cache.put("nullTest", null);
        assertNull(cache.get("nullTest"));
    }
}
