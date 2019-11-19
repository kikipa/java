package basic;

public class JavaEquals {
	
	/**
	 * equals和==最大的区别是一个是方法一个是运算符
	 * 
	 * ==：如果比较的对象是基本数据类型，则比较的是数值是否相等；
	 * 如果比较的是引用数据类型，则比较的是对象的地址值是否相等
	 * 
	 * equals()：用来比较方法两个对象的内容是否相等。
	 * equals方法不能用于基本数据类型的变量，如果没有对equals方法进行重写，则比较的是引用类型的变量所指向的对象的地址
	 * */
	
	/**
	 * String s="abcd"是一种非常特殊的形式,和new 有本质的区别。
	 * 它是java中唯一不需要new 就可以产生对象的途径。
	 * 以String s="abcd";形式赋值在java中叫直接量,它是在常量池中而不是象new一样放在压缩堆中。 
	 * 这种形式的字符串，在JVM内部发生字符串拘留，
	 * 即当声明这样的一个字符串后，JVM会在常量池中先查找有有没有一个值为"abcd"的对象,
	 * 如果有,就会把它赋给当前引用.即原来那个引用和现在这个引用指点向了同一对象, 
	 * 如果没有,则在常量池中新创建一个"abcd",下一次如果有String s1 = "abcd";
	 * 又会将s1指向"abcd"这个对象,即以这形式声明的字符串,只要值相等,任何多个引用都指向同一对象
	 * */
	public static void main(String[] args) {
		
		String a = new String("abc");
		String b = new String("abc");
		String c = "abc";
		String d = "abc";
		
		if(a==b) {
			System.out.println("a==b");
		}
		if(c==d) {
			System.out.println("c==d");
		}
		if(a.equals(b)) {
			System.out.println("a=b");
		}
		if(c.equals(d)) {
			System.out.println("c=d");
		}
		
	}

}
