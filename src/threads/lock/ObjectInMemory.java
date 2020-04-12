package threads.lock;



public class ObjectInMemory {
	public static void main(String[] args) {
		int a = 1;
		
		Teacher james = new Teacher();
		james.stu = new Student();
	}
}

class Teacher{
	String name = "James";
	int age = 30;
	boolean gender = true;
	
	Student stu;
}

class Student{
	String name = "Yves";
	int age = 20;
	boolean gender = false;
}
