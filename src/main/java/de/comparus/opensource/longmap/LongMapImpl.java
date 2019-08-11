package de.comparus.opensource.longmap;


import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

public class LongMapImpl<V> implements de.comparus.opensource.longmap.LongMap<V> {
    private int capasity = 16;
    private float loadFactor = 0.75f;
    private int size = 0;
    private Set<Long> keys = new HashSet<>();
    LinkedList<Node<V>>[] list = new LinkedList[capasity];
    Node<V>[] table = new Node[capasity];

    class Node<V> {
        final int hash;
        final Long key;
        Node<V> next = null;
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
        return putV(key, value, false);
    }

    private V putV(long key, V value, boolean addCapasity) {
        V oldValue = null;
        //Проверка на переполнение capasity
        if (size > (capasity * loadFactor)) {
            capasity = capasity * 2;

            Node<V>[] oldList = this.table;
            Node<V>[] list = new Node[capasity];
            keys = new HashSet<>();
            this.table = list;

            //Добавление старый node в новый список(увеличиный)
            for (int i = 0; i < oldList.length; i++) {
                Node<V> node = oldList[i];
                if (node != null) {
                    do{
                        putV(oldList[i].key, oldList[i].value, true);
                        node = oldList[i].next;
                    }while (node != null);

                }
            }
        }
        //Определение номера бакета
        int index = new Long(key).hashCode() % capasity;
        //Проверка на отсутствие бакета
        if (table[index] == null) {
            table[index] = new Node(key, value);
            size++;
            //Если не null, проверяем на дубликат
        } else if (table[index] != null) {
            Node<V> node = table[index];
            do {
                if (node.getKey() == key) {
                    oldValue = table[index].value;
                    table[index].value = value;
                    break;
                }
                node = node.next;
            } while (table[index].next != null);
            node.next = new Node(key, value);
            size++;
        }

        return oldValue;
    }


    public V get(long key) {
        int index = new Long(key).hashCode() % capasity;
        if (list[index] != null) {
            for (Node<V> i : list[index]) {
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
                    size--;
                    return i.value;
                }
            }
        }
        return null;
    }

    public boolean isEmpty() {
        if (size == 0) return true;
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
        Node<V>[] tab;
        V v;
        if ((tab = table) != null && size > 0) {
            for (Node<V> e : tab) {
                for (; e != null; e = e.next) {
                    if ((v = e.value) == value ||
                            (value != null && value.equals(v)))
                        return true;
                }
            }
        }
        return false;
    }

    public long[] keys() {
        Long[] arr = keys.toArray(new Long[keys.size()]);
        return ArrayUtils.toPrimitive(arr);
    }

    public V[] values() {
        List<V> resaultList = new ArrayList<>();
        if (size != 0) {

            for (int i = 0; i < table.length; i++) {
                if (table[i] != null) {
                    do {
                        resaultList.add(table[i].getValue());
                    }
                    while (table[i].next != null);
                }
            }
            return (V[]) resaultList.toArray();
        }
        return null;
    }

    public long size() {
        return size;
    }

    public void clear() {
        for (int i = 0; i < list.length; ++i)
            list[i] = null;
        size = 0;
    }
}