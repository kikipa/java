package performance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Jin.HE
 * @Date: 2021/4/12 10:17
 */
public class MapTest {

  static List<Long> putTimes = new ArrayList<>();
  static List<Long> getTimes = new ArrayList<>();
  static List<Long> removeTimes = new ArrayList<>();

  public static void main(String[] args) {
//    Long times = 1000L;
//    Long size = 10000L;

//    Long times = 1000L;
//    Long size = 100000L;

    Long times = 1000L;
    Long size = 1000000L;

//    mapTest(new HashMap<>(), size, times);
    mapTest(new ConcurrentHashMap<>(), size, times);

    analysePerformance(size, times);
  }

  private static void analysePerformance(Long size, Long times) {
    Long putAvg = 0L;
    Long putTotal = 0L;
    for(Long p : putTimes){
      putTotal += p;
    }
    putAvg = putTotal / putTimes.size();
    System.out.println(times+ " times put "+size+" times putAvg: " + putAvg + "ns");
    System.out.println("avg single put time: " + putAvg/size + "ns");

    Long getAvg = 0L;
    Long getTotal = 0L;
    for(Long p : getTimes){
      getTotal += p;
    }
    getAvg = getTotal / getTimes.size();
    System.out.println(times+ " times get "+size+" times getAvg: " + getAvg + "ns");
    System.out.println("avg single get time: " + getAvg/size + "ns");

    Long removeAvg = 0L;
    Long removeTotal = 0L;
    for(Long p : removeTimes){
      removeTotal += p;
    }
    removeAvg = removeTotal / removeTimes.size();
    System.out.println(times+ " times remove "+size+" times removeAvg: " + removeAvg + "ns");
    System.out.println("avg single remove time: " + removeAvg/size + "ns");
  }

  static void mapTest(Map<String, String> map, Long size, Long times){
    Random ran = new Random();
//    List<Long> keyList = new ArrayList<>();
    List<String> keyList = new ArrayList<>();
    List<String> valueList = new ArrayList<>();
//    Map<Long, String> hashMap = new HashMap<>();
    for(int i=0; i<size; i++){
      Long num = ran.nextLong();
//      keyList.add(num);
      keyList.add(num+"-key");
      valueList.add("str-"+num);
    }

    for(int k=0; k<times; k++){

      Long t1 = System.nanoTime();
      for(int i=0; i<size; i++){
        map.put(keyList.get(i), valueList.get(i));
      }
      Long t2 = System.nanoTime();
      for(int i=0; i<size; i++){
        map.get(keyList.get(i));
      }
      Long t3 = System.nanoTime();
      for(int i=0; i<size; i++){
        map.remove(keyList.get(i));
      }
      Long t4 = System.nanoTime();

      putTimes.add(t2 - t1);
      getTimes.add(t3 - t2);
      removeTimes.add(t4 - t3);
    }

  }

}
