package de.comparus.opensource.longmap;

import java.util.*;

public class LongMapImpl<V> implements de.comparus.opensource.longmap.LongMap<V> {
    private int capasity = 16;
    private float loadFactor = 0.75f;
    private int countElements = 0;
    LinkedList<Node<V>>[] list = new LinkedList[capasity];

    class Node<V> {
        final int hash;
        final Long key;
        V value;

        Node(Long key, V value) {
            this.key = key;
            this.hash = hashCode();
            this.value = value;
        }

        public final Long getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final String toString() {
            return key + "=" + value;
        }

        public final int hashCode() {
            return Objects.hashCode(key);
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }

    public V put(long key, V value) {
        boolean isDouble = false;
        V oldValue = null;
        if (countElements > (capasity * loadFactor)) {
            capasity = capasity * 2;
            LinkedList<Node<V>>[] OldList= this.list;
            LinkedList<Node<V>>[] list = new LinkedList[capasity];
            this.list = list;
            for (LinkedList<Node<V>> n : OldList) {
                if (n != null) {
                    for (Node<V> i : n) {
                        put(i.key, i.value);
                    }
                }
            }
        }
        int numberCell = new Long(key).hashCode() % capasity;
        if (list[numberCell] == null) {
            list[numberCell] = new LinkedList<>();
        }
        for (Node<V> i : list[numberCell]) {
            if (i.key == key) {
                oldValue = i.value;
                i.value = value;
                isDouble = true;
                break;
            }
        }

        if (isDouble == false) {
            Node<V> node = (new Node(key, value));
            list[numberCell].add(node);
            countElements++;
        }
        return oldValue;
    }

    public V get(long key) {
        int numberCell = new Long(key).hashCode() % capasity;
        if (list[numberCell] != null) {
            for (Node<V> i : list[numberCell]) {
                if (i.key == key) {
                    return i.value;
                }
            }
        }
        return null;
    }

    public V remove(long key) {
        int numberCell = new Long(key).hashCode() % capasity;
        if (list[numberCell] != null) {
            for (Node<V> i : list[numberCell]) {
                if (i.key == key) {
                    list[numberCell].remove(i);
                    countElements--;
                    return i.value;
                }
            }
        }
        return null;
    }

    public boolean isEmpty() {
        if (countElements == 0) return true;
        return false;
    }

    public boolean containsKey(long key) {
        int numberCell = new Long(key).hashCode() % capasity;
        if (list[numberCell] != null) {
            for (Node<V> i : list[numberCell]) {
                if (i.key == key) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (LinkedList<Node<V>> n : list) {
            if (n != null) {
                for (Node<V> i : n) {
                    if (i.value == value) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public long[] keys() {
        if (countElements != 0) {
            long[] resaultList = new long[countElements];
            int count = 0;
            for (LinkedList<Node<V>> n : list) {
                if (n != null) {
                    for (Node<V> i : n) {
                        resaultList[count] = i.key;
                        count++;
                    }
                }
            }
            return resaultList;
        }
        return null;
    }

    public V[] values() {
        List<V> resaultList = new ArrayList<>();
        if (countElements != 0) {
            for (LinkedList<Node<V>> n : list) {
                if (n != null) {
                    for (Node<V> i : n) {
                        resaultList.add(i.value);
                    }
                }
            }
            return (V[]) resaultList.toArray();
        }
        return null;
    }

    public long size() {
        return countElements;
    }

    public void clear() {
        for (int i = 0; i < list.length; ++i)
            list[i] = null;
        countElements = 0;
    }
}