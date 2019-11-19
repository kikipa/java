package basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class JavaIO {
	
	public static void main(String[] args) throws Exception{
//		test1();
		test2();
	}
	
	/**
	 * 如何将一个 java 对象序列化到文件里？
	 * 在 java 中能够被序列化的类必须先实现 Serializable 接口，该接口没有任何抽象方法只是起到一个标记作用
	 * */
	static void test1() throws Exception{
		
		//对象输出流
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(
						new File("F://obj")));
		oos.writeObject(new User("Yves",100, null));
		oos.close();
		
		//对象输入流
		ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(
						new File("F://obj")));
		User user = (User)ois.readObject();
		System.out.println(user);
		ois.close();
	}
	
	
	/**
	 * 如何实现对象克隆？
	 * 有两种方式:
	 * 实现 Cloneable 接口并重写 Object 类中的 clone()方法
	 * 实现 Serializable 接口，通过对象的序列化和反序列化实现克隆，可以实现真正的深度克隆
	 * */
	static void test2() {
		
		try {
			User user1 = new User("Yves",18, new Car("BMW"));
			User user2 = MyUtil.clone(user1);// 深度克隆
			user2.getCar().setBrand("BYD");
			// 修改克隆的 User 对象 user2 关联的汽车对象的品牌属性
            // 原来的 User 对象 user1 关联的汽车不会受到任何影响
            // 因为在克隆 User 对象时其关联的汽车对象也被克隆了
			System.out.println(user1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 基于序列化和反序列化实现的克隆不仅仅是深度克隆，
	 * 更重要的是通过泛型限定，可以检查出要克隆的对象是否支持序列化，
	 * 这项检查是编译器完成的，不是在运行时抛出异常，这种是方案明显优于使用 Object 类的 clone 方法克隆对象
	 * */
	static class MyUtil{
		private MyUtil() {
			throw new AssertionError();
		}
		
		public static <T extends Serializable> T clone(T obj) throws Exception{
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			
			ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bis);
			
			return (T)ois.readObject();
			// 说明：调用 ByteArrayInputStream 或 ByteArrayOutputStream 对象的 close 方法没有任何意义
	        // 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这不同于对外部资源（如文件流）的释放
		}
	}
	

	static class User implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		String name;
		int age;
		Car car;
		
		User(String name, int age, Car car){
			this.name = name;
			this.age = age;
			this.car = car;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public Car getCar() {
			return car;
		}

		public void setCar(Car car) {
			this.car = car;
		}
		
		@Override
	    public String toString() {
	        return "User [name=" + name + ", age=" + age + ", car=" + car + "]";
	    }
	}
	
	static class Car implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private String brand;
		
		Car(String brand){
			this.brand = brand;
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}
		
		@Override
	    public String toString() {
	        return "Car [brand=" + brand + "]";
	    }
	}
}
