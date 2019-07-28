import de.comparus.opensource.longmap.LongMapImpl;

import org.junit.Assert;
import org.junit.Test;


public class LongMapTests {
    @Test
    public void testPut() {
        LongMapImpl<Object> longMap = new LongMapImpl<>();
        longMap.put(123, new Object());
        Assert.assertTrue(longMap.size() == 1);
    }

    @Test
    public void testGet() {
        LongMapImpl<Object> longMap = new LongMapImpl<>();
        longMap.put(123, new Object());
        Assert.assertTrue(longMap.get(123) != null);
    }

    @Test
    public void testRemove(){
    LongMapImpl<Object> longMap = new LongMapImpl<>();
        longMap.put(123, new Object());
        longMap.remove(123);
        Assert.assertTrue(longMap.size()==0);
}

    @Test
    public void testIsEmpty() {
        LongMapImpl<Object> longMap = new LongMapImpl<>();
        longMap.put(123, new Object());
        longMap.remove(123);
        Assert.assertTrue(longMap.isEmpty());
    }

    @Test
    public void testContainsKey() {
        LongMapImpl<Object> longMap = new LongMapImpl<>();
        longMap.put(123, new Object());
        Assert.assertTrue(longMap.containsKey(123));
    }

    @Test
    public void testContainsValue() {
        LongMapImpl<Object> longMap = new LongMapImpl<>();
        Object ob = new Object();
        longMap.put(123, ob);
        Assert.assertTrue(longMap.containsValue(ob));
    }

    @Test
    public void testKeys() {
        LongMapImpl<Object> longMap = new LongMapImpl<>();
        longMap.put(123, new Object());
        Assert.assertTrue(longMap.keys().length==1);
    }

    @Test
    public void testValues() {
        LongMapImpl<Object> longMap = new LongMapImpl<>();
        longMap.put(123, new Object());
        Assert.assertTrue(longMap.values().length==1);
    }

    @Test
    public void testSize() {
        LongMapImpl<Object> longMap = new LongMapImpl<>();
        longMap.put(123, new Object());
        Assert.assertTrue(longMap.size()==1);
    }

    @Test
    public void testClear() {
        LongMapImpl<Object> longMap = new LongMapImpl<>();
        longMap.put(123, new Object());
        longMap.put(124, new Object());
        longMap.clear();
        Assert.assertTrue(longMap.size()==0);
    }
}