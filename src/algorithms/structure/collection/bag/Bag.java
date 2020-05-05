package algorithms.structure.collection.bag;

import java.util.Iterator;

/**
 * 链表实现的背包
 * */
public class Bag<Item> implements Iterable<Item> {
    //链表的首结点
    private Node first;
    //元素数量
    private int N;
    private class Node{
        Item item;
        Node next;
    }

    public void add(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    boolean isEmpty(){
        return first == null;
    }

    int size(){
        return N;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
