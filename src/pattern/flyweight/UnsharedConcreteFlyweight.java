package pattern.flyweight;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/13 10:09
 */
public class UnsharedConcreteFlyweight {
    private String info;
    public UnsharedConcreteFlyweight(String info){
        this.info = info;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
}
