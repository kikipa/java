package algorithms.basic.interview;

import java.util.LinkedList;

/**
 * @description: 合并两个排序的链表
 * @author: za-hejin
 * @time: 2020/2/6 10:29
 */
public class MergeSortedList {
    public static void main(String[] args) {
        LinkedList<Integer> a = new LinkedList<>();
        LinkedList<Integer> b = new LinkedList<>();
        a.add(6);
        a.add(7);
        a.add(12);
        a.add(36);
        a.add(50);
        b.add(2);
        b.add(5);
        b.add(35);
        b.add(46);
        b.add(60);
        LinkedList<Integer> r = new MergeSortedList().mergeSortedListInteger(a,b);
        System.out.println(r.toString());
    }

    /**
     *
     * */
    LinkedList<Integer> mergeSortedListInteger(LinkedList<Integer> a, LinkedList<Integer> b){
        LinkedList<Integer> r = new LinkedList<>();
        while(a.size()>0||b.size()>0){
           int v,v1,v2;
           if(a.size()==0){
               v = b.poll();
               r.add(v);
               continue;
           }
           if(b.size()==0){
               v = a.poll();
               r.add(v);
               continue;
           }
           v1 = a.peek();
           v2 = b.peek();
           if(v1<v2){
               v = v1;
               a.remove();
               r.add(v);
               continue;
           }else {
               v = v2;
               b.remove();
               r.add(v);
               continue;
           }
       }
        return r;
    }

}
