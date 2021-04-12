package basic.test;

/**
 * @description:
 * @author: za-hejin
 * @time: 2020/3/12 9:57
 */
public class MainTest {

    public static void main(String[] args) {
//        ConcreteService concreteService = new ConcreteService();
//
//        concreteService.method1();
//        System.out.println("------");
//        concreteService.method2();
//
//        System.out.println(add(null, 1));

//        A a = new B();
//        B b = new B();


//        Integer f1=100, f2=100, f3=150, f4=150, f5=127, f6=127, f7=128, f8=128;
        Long f1=100L, f2=100L, f3=150L, f4=150L, f5=127L, f6=127L, f7=128L, f8=128L;
        System.out.println(f1==f2);
        System.out.println(f3==f4);
    }


    static int add(Integer a, Integer b){
        try {
            return a + b;
        }catch (Exception e){
            System.out.printf("error!");
        }finally {
            System.out.printf("fin	");
        }
        return 0;
    }

    static class A{
        static {
            System.out.println("1");
        }
        public A(){
            System.out.println("A");
        }
    }
    static class B extends A{
        static {
            System.out.println("2");
        }
        public B(){
            System.out.println("B");
        }
    }
}
