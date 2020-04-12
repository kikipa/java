package pattern.adapter;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/3 9:54
 */
public class ObjectAdapter implements Target{
    private Adaptee adaptee;
    public ObjectAdapter(Adaptee adaptee){
        this.adaptee = adaptee;
    }
    @Override
    public void request() {
        adaptee.specificRequest();
    }
}
