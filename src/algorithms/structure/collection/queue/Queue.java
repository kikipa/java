package algorithms.structure.collection.queue;

import java.util.Iterator;

/**
 * 链表实现的队列
 * */
public class Queue<Item> implements Iterable<Item> {
    //指向最早添加的结点的链接
    private Node first;
    //指向最近添加的结点的链接
    private Node last;
    //队列中元素的数量
    private int N;
    private class Node{
        Item item;
        Node next;
    }

    public void enqueue(Item item){
        //向表尾添加元素
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()){
            first = last;
        }else {
            oldLast.next = last;
        }
        N++;
    }

    public Item dequeue(){
        //从表头删除元素
        Item item = first.item;
        first = first.next;
        if(isEmpty()){
            last = null;
        }
        N--;
        return item;
    }

    public boolean isEmpty(){
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
            Item item =  current.item;
            current = current.next;
            return item;
        }
    }
}
