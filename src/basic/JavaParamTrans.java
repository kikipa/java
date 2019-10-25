package basic;

public class JavaParamTrans {
	
	public static void main(String[] args) {
//		test1();
//		test2();
//		test3();
//		test4();
		test5();
		test6();
	}
	
	/**
	 * 基本类型传递时，线程在栈上分配形式参数并拷贝实际参数的值
	 * change方法修改的只是形式参数，对实际参数没有作用。方法调用结束后形式参数随着栈帧回收
	 * */
	static void test1() {
		int value = 18;
		change(value);
		System.out.println("outer value: " + value);
	}
	
	static void change(int value) {
		value = 20;
		System.out.println("inner value: " + value);
	}
	
	/**
	 * 引用类型传递时，传递的是引用的值，从这个角度来讲还是值传递
	 * 如果是引用传递的话，传递的应该是引用的地址，而不是引用的值
	 * change方法修改的是引用所指向的数据空间的数据，所以方法外部也能看到修改的结果
	 * */
	static void test2() {
		Person value = new Person();
		value.setAge(18);
		change(value);
		System.out.println("outer value: " + value.getAge());
	}
	
	static void change(Person value) {
		value.setAge(20);
		System.out.println("inner value: " + value.getAge());
	}

	static class Person {
		Person(){
			
		}
		int age;
		void setAge(int age) {
			this.age = age;
		}
		int getAge() {
			return this.age;
		}
	}
	
	/**
	 * 基本类型的数组也是对象，所以int[] 传递的也是对象应用的值
	 * */
	static void test3() {
		int[] value = new int[10];
		value[0] = 18;
		change(value);
		System.out.println("outer value: " + value[0]);
	}
	
	static void change(int[] value) {
		value[0] = 20;
		System.out.println("inner value: " + value[0]);
	}
	
	/**
	 * 如果对引用类型的传递稍作修改
	 * 对引用类型的传递做修改，实际上是创建了一个新的对象
	 * */
	static void test4() {
		Person value = new Person();
		value.setAge(18);
		change2(value);
		System.out.println("outer value: " + value.getAge());
	}
	
	static void change2(Person value) {
		value = new Person();
		value.setAge(20);
		System.out.println("inner value: " + value.getAge());
	}
	
	/**
	 * 同理String，Integer等类型的封装类型为final类型，对数据的修改操作实际上是创建了一个新的对象
	 * */
	static void test5() {
		String value = "abc";
		change2(value);
		System.out.println("outer value: " + value);
	}
	
	static void change2(String value) {
		value = "123";
		System.out.println("inner value: " + value);
	}
	
	/**
	 * 同理String，Integer等类型的封装类型为final类型，对数据的修改操作实际上是创建了一个新的对象
	 * */
	static void test6() {
		Integer value = 18;
		change2(value);
		System.out.println("outer value: " + value);
	}
	
	static void change2(Integer value) {
		value = 20;
		System.out.println("inner value: " + value);
	}
}
