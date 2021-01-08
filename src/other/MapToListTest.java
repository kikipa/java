package other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @Author: Jin.HE
 * @Date: 2021/1/7 18:37
 */
public class MapToListTest {

  public static void main(String[] args) {
    // 遍历带有DomainStorageStrategy注释的类
    Map<String,String> beans = new HashMap<>();
    beans.put("3", "3");
    beans.put("1", "1");
    beans.put("6", "6");
    beans.put("2", "2");
    beans.put("5", "5");
    beans.put("9", "9");

    for (String serviceBean : beans.values()) {
      System.out.println(serviceBean);
    }

    System.out.println("-------------");


    Map<String,String> sortedMap = new TreeMap<>((o1,o2)->{
      return Integer.parseInt(o2) - Integer.parseInt(o1);
    });

    sortedMap.putAll(beans);

//    beans.values().stream().sorted((o1,o2)->{
//      return Integer.parseInt(o1) - Integer.parseInt(o2);
//    }).collect(Collectors.toList());
//
    List<String> list= new ArrayList<>();
    for (String serviceBean : sortedMap.values()) {
      list.add(serviceBean);
    }
    list.forEach(x->{
      System.out.println(x);
    });

    System.out.println("-------------");

    List<String> handlerList = new ArrayList<>(10);
    handlerList.add("111");
    System.out.println(handlerList.size());
  }
}
