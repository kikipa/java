package pattern.adapter;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/3 9:42
 */
public class ClassAdapter extends Adaptee implements Target{
    @Override
    public void request() {
        specificRequest();
    }
}
