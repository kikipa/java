package basic.test.structure;

import java.util.*;

/**
 * @Description: TODO
 * @Author: Jin.HE
 * @Date: 2021/5/10 16:26
 */
public class ArrayTest {

    public static void main(String[] args) {
        test(1, 50, 10);
    }

    public static void test(int times, int nums, int fields){

        Random ran = new Random();

        List<List<Long>> lists = new ArrayList<>();
        for(int k=0; k<fields; k++){
            List<Long> keys = new ArrayList<>();
            for(int i=0; i<nums; i++){
                keys.add((long)ran.nextInt());
            }
            lists.add(keys);
        }

//        Set<Long> keySet = new TreeSet<>();
        Set<Long> keySet = new HashSet<>();
        List<Long> allKeys = new ArrayList<>();
        long t1 = System.nanoTime();

//        for(int i=0; i<times; i++){
//            for(int k=0; k<fields; k++){
//                keySet.addAll(lists.get(k));
//            }

        for(int k=0; k<fields; k++){
            List<Long> list = lists.get(k);
            int size = list.size();
            for(int m=0; m<size; m++){
                keySet.add(list.get(m));
            }
        }

//            for(Long s : keySet){
//                allKeys.add(s);
//            }
//        }
        long t2 = System.nanoTime();

        long t3 = System.nanoTime();

        long t4 = System.nanoTime();



        long t5 = System.nanoTime();

        long t6 = System.nanoTime();


        long t7 = System.nanoTime();

        long t8 = System.nanoTime();

        System.out.println("insert " + (t2-t1) + " ns, avg: " + (t2-t1)/times + " ns");
        System.out.println("search(k) " + (t4-t3) + " ns, avg: " + (t4-t3)/times + " ns");
        System.out.println("search(k1, k2) " + (t6-t5) + " ns, avg: " + (t6-t5)/times + " ns");
        System.out.println("delete " + (t8-t7) + " ns, avg: " + (t8-t7)/times + " ns");

    }


}
