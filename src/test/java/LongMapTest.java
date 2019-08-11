import de.comparus.opensource.longmap.LongMapImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class LongMapTest {
    @Test
    public void testPut() {
        LongMapImpl<Object> longMap = new LongMapImpl<>();
        longMap.put(123, new Object());
        Assertions.assertEquals(1, longMap.size());
    }

    @Test
    public void testPutSame() {
        LongMapImpl<Object> longMap = new LongMapImpl<>();
        longMap.put(123, "Test");
        longMap.put(123, "Test1");
        Assertions.assertEquals(1, longMap.size());
        Assertions.assertEquals("Test1", longMap.get(123));
    }

    @Test
    public void testGet() {
        LongMapImpl<Object> longMap = new LongMapImpl<>();
        longMap.put(123, new Object());
        Assertions.assertNotNull(longMap.get(123));
    }

    @Test
    public void testGetNegative() {
        LongMapImpl<Object> longMap = new LongMapImpl<>();
        longMap.put(123, new Object());
        Assertions.assertNull(longMap.get(124));
    }


    @Test
    public void testRemove(){
    LongMapImpl<Object> longMap = new LongMapImpl<>();
        longMap.put(123, new Object());
        longMap.remove(123);
        Assertions.assertEquals(0, longMap.size());
}
@Test
    public void testRemoveNegative(){
    LongMapImpl<Object> longMap = new LongMapImpl<>();
        longMap.put(123, new Object());
        longMap.remove(124);
        Assertions.assertEquals(1, longMap.size());
}

    @Test
    public void testIsEmpty() {
        LongMapImpl<Object> longMap = new LongMapImpl<>();
        longMap.put(123, new Object());
        longMap.remove(123);
        Assertions.assertTrue(longMap.isEmpty());
    }

    @Test
    public void testContainsKey() {
        LongMapImpl<Object> longMap = new LongMapImpl<>();
        longMap.put(123, new Object());
        Assertions.assertTrue(longMap.containsKey(123));
    }

    @Test
    public void testContainsValue() {
        LongMapImpl<Object> longMap = new LongMapImpl<>();
        Object ob = new Object();
        longMap.put(123, ob);
        Assertions.assertTrue(longMap.containsValue(ob));
    }

    @Test
    public void testKeys() {
        LongMapImpl<Object> longMap = new LongMapImpl<>();
        longMap.put(123, new Object());
        Assertions.assertTrue(longMap.keys().length==1);
    }

    @Test
    public void testValues() {
        LongMapImpl<Object> longMap = new LongMapImpl<>();
        longMap.put(123, new Object());
        Assertions.assertTrue(longMap.values().length==1);
    }

    @Test
    public void testSize() {
        LongMapImpl<Object> longMap = new LongMapImpl<>();
        longMap.put(123, new Object());
        Assertions.assertTrue(longMap.size()==1);
    }

    @Test
    public void testClear() {
        LongMapImpl<Object> longMap = new LongMapImpl<>();
        longMap.put(123, new Object());
        longMap.put(124, new Object());
        longMap.clear();
        Assertions.assertTrue(longMap.size()==0);
    }
}