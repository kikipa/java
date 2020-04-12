package basic;

public class DataType {

	public static void main(String[] args) {
		test1();
	}
	
	/**
	 * short s1 = 1; s1 = s1 + 1; 有错吗?short s1 = 1; s1 += 1 有错吗？
	 * 前者不正确，后者正确
	 * 由于 1 是 int 类型，因此 s1+1 运算结果也是 int 型，需要强制转换类型才能赋值给 short 型
	 * 因为 s1+= 1;相当于 s1 = (short)(s1 + 1);其中有隐含的强制类型转换
	 * */
	
	
	/**
	 * 下面 Integer 类型的数值比较输出的结果为？
	 * 装箱的本质是什么呢？当我们给一个Integer 对象赋一个 int 值的时候，会调用 Integer 类的静态方法 valueOf
	 * 
	 * 如果整型字面量的值在-128 到 127 之间，那么不会 new 新的 Integer 对象，而是直接引用常量池中的Integer对象，
	 * 所以上面的面试题中f1==f2的结果是 true，而f3==f4 的结果是false
	 * */
	static void test1() {
		Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150;
        System.out.println(f1 == f2);
        System.out.println(f3 == f4); 
	}
	
	/**
	 * String、StringBuffer、StringBuilder的区别？
	 * 可变不可变:
	 * String：字符串常量，在修改时不会改变自身；若修改，等于重新生成新的字符串对象。
	 * StringBuffer：在修改时会改变对象自身，每次操作都是对 StringBuffer 对象本身进行修改，不是生成新的对象；
	 * 使用场景：对字符串经常改变情况下，主要方法：append()，insert()等
	 * 
	 * 线程是否安全:
	 * String：对象定义后不可变，线程安全
	 * StringBuffer：是线程安全的（对调用方法加入同步锁），执行效率较慢，适用于多线程下操作字符串缓冲区大量数据
	 * StringBuilder：是线程不安全的，适用于单线程下操作字符串缓冲区大量数据
	 * 
	 * 共同点:
	 * StringBuilder与StringBuffer有公共父类 AbstractStringBuilder(抽象类)
	 * 
	 * */
	
	/**
	 * 数据类型之间的转换？
	 * 字符串如何转基本数据类型？
	 * 调用基本数据类型对应的包装类中的方法 parseXXX(String)或valueOf(String)即可返回相应基本类型
	 * 
	 * 基本数据类型如何转字符串？
	 * 一种方法是将基本数据类型与空字符串（“”）连接（+）即可获得其所对应的字符串；
	 * 另一种方法是调用 String类中的 valueOf()方法返回相应字符串
	 * */
	
	
	
}
