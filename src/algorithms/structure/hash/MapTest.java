package algorithms.structure.hash;

import java.util.*;

/**
 * @Description: TODO
 * @Author: Jin.HE
 * @Date: 2021/5/10 15:10
 */
public class MapTest {

    public static void main(String[] args) {
//        test(10000);
//        test2(10000);
//
//        test(100000);
//        test2(100000);

        test(1000000);
        test2(1000000);
    }

    public static void test(int times){

        Random ran = new Random();
        List<Integer> keys = new ArrayList<>();
        for(int i=0; i<times; i++){
            keys.add(ran.nextInt());
        }

        Map<Integer, Integer> map = new HashMap<>();

        long t1 = System.nanoTime();
        for(int i=0; i< times; i++){
            map.put(keys.get(i), i);
        }
        long t2 = System.nanoTime();

        long t3 = System.nanoTime();
        for(int i=0; i<times; i++){
            map.get(keys.get(i));
        }
        long t4 = System.nanoTime();



        long t5 = System.nanoTime();
        for(int i=0; i<times; i++){
//            bpt.search(keys.get(i), keys2.get(i));
        }
        long t6 = System.nanoTime();


        long t7 = System.nanoTime();
        for(int i=0; i<times; i++){
            map.remove(keys.get(i));
        }
        long t8 = System.nanoTime();

        System.out.println("insert " + (t2-t1) + " ns, avg: " + (t2-t1)/times + " ns");
        System.out.println("search(k) " + (t4-t3) + " ns, avg: " + (t4-t3)/times + " ns");
        System.out.println("search(k1, k2) " + (t6-t5) + " ns, avg: " + (t6-t5)/times + " ns");
        System.out.println("delete " + (t8-t7) + " ns, avg: " + (t8-t7)/times + " ns");

    }

    public static void test2(int times){

        Random ran = new Random();
        List<String> keys = new ArrayList<>();
        for(int i=0; i<times; i++){
            keys.add(ran.nextInt()+"s"+ran.nextInt());
        }

        Map<String, Integer> map = new HashMap<>();

        long t1 = System.nanoTime();
        for(int i=0; i< times; i++){
            map.put(keys.get(i), i);
        }
        long t2 = System.nanoTime();

        long t3 = System.nanoTime();
        for(int i=0; i<times; i++){
            map.get(keys.get(i));
        }
        long t4 = System.nanoTime();



        long t5 = System.nanoTime();
        for(int i=0; i<times; i++){
//            bpt.search(keys.get(i), keys2.get(i));
        }
        long t6 = System.nanoTime();


        long t7 = System.nanoTime();
        for(int i=0; i<times; i++){
            map.remove(keys.get(i));
        }
        long t8 = System.nanoTime();

        System.out.println("insert " + (t2-t1) + " ns, avg: " + (t2-t1)/times + " ns");
        System.out.println("search(k) " + (t4-t3) + " ns, avg: " + (t4-t3)/times + " ns");
        System.out.println("search(k1, k2) " + (t6-t5) + " ns, avg: " + (t6-t5)/times + " ns");
        System.out.println("delete " + (t8-t7) + " ns, avg: " + (t8-t7)/times + " ns");

    }


}
