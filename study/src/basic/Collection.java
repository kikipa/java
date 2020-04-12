package basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Collection {
	
	/**
	 * HashMap排序:
	 * 已知一个 HashMap<Integer，User>集合， User 有 name（String）和 age（int）属性。
	 * 请写一个方法实现对HashMap 的排序功能，
	 * 该方法接收 HashMap<Integer，User>为形参，返回类型为 HashMap<Integer，User>，
	 * 要求对 HashMap 中的 User 的 age 倒序进行排序。排序时 key=value 键值对不得拆散
	 * */
	static void test1() {
		Map<Integer, String> map = new HashMap<Integer, String>();
	}
	
	
	
	
	
	/**
	 * 请问 ArrayList、HashSet、HashMap 是线程安全的吗？如果不是怎么获取线程安全的集合？
	 * 以上类的源码进行分析，每个方法都没有加锁，显然都是非线程安全的
	 * 在集合中Vector 和HashTable是线程安全的
	 * */
	static void test2() {
		ArrayList<String> al = new ArrayList<String>();
		
		Collections.synchronizedCollection(al);
	}
	
	/**
	 * ArrayList内部用什么实现的？
	 * */
	
	
	/**
	 * 并发集合和普通集合如何区别？
	 * */
	
	
	/**
	 * List 和 Map、Set 的区别？
	 * */
	
	
	
}
