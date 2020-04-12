package basic.test;

/**
 * @description:
 * @author: za-hejin
 * @time: 2020/3/12 9:54
 */
public abstract class BasicService implements IService{
    @Override
    public void method1() {
        System.out.println("BasicService method1");
        this.method2();
    }

    @Override
    public void method2() {
        System.out.println("BasicService method2");
    }
}
