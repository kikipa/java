package other.lambda;

import java.time.Period;
import java.util.Arrays;
import java.util.Comparator;

public class Demo02 {
    static class Person{
        String name;
        int age;
        private String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        private int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        Person(String name, int age){
            this.name = name;
            this.age = age;
        }
        @Override
        public String toString() {
            return name+":"+age;
        }
    }

    public static void main(String[] args) {
        // 本来年龄乱序的对象数组
        Person[] array = {
                new Person("古力娜扎", 19),
                new Person("迪丽热巴", 18),
                new Person("马尔扎哈", 20) };

//        // 匿名内部类
//        Comparator<Person> comp = new Comparator<Person>() {
//            @Override
//            public int compare(Person o1, Person o2) {
//                return o2.getAge() - o1.getAge();
//            }
//        };
//        Arrays.sort(array, comp); // 第二个参数为排序规则，即Comparator接口实例

        //lambda实现
        Arrays.sort(array,(Person o1, Person o2)->{
            return o2.getAge() - o1.getAge();
        });

        for (Person person : array) {
            System.out.println(person);
        }
    }
}
