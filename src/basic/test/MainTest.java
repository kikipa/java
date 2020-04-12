package basic.test;

/**
 * @description:
 * @author: za-hejin
 * @time: 2020/3/12 9:57
 */
public class MainTest {

    public static void main(String[] args) {
        ConcreteService concreteService = new ConcreteService();

        concreteService.method1();
        System.out.println("------");
        concreteService.method2();
    }
}
