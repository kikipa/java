package pattern.adapter;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/3 9:43
 */
public class AdapterTest {
    public static void main(String[] args) {
        Target target = new ClassAdapter();
        target.request();

        Adaptee adaptee = new Adaptee();
        Target target1 = new ObjectAdapter(adaptee);
        target1.request();
    }
}
