package pattern.flyweight;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/13 10:15
 */
public class FlyweightPatternTest {
    public static void main(String[] args) {
        Dto dto = new Dto();
        int integer = dto.getInteger()==null?0:dto.getInteger();
        long l = 10L * integer;



        FlyweightFactory factory = new FlyweightFactory();
        Flyweight f01=factory.getFlyweight("a");
        Flyweight f02=factory.getFlyweight("a");
        Flyweight f03=factory.getFlyweight("a");
        Flyweight f11=factory.getFlyweight("b");
        Flyweight f12=factory.getFlyweight("b");
        f01.operation(new UnsharedConcreteFlyweight("第1次调用a。"));
        f02.operation(new UnsharedConcreteFlyweight("第2次调用a。"));
        f03.operation(new UnsharedConcreteFlyweight("第3次调用a。"));
        f11.operation(new UnsharedConcreteFlyweight("第1次调用b。"));
        f12.operation(new UnsharedConcreteFlyweight("第2次调用b。"));
    }

    static class Dto{
        java.lang.Integer integer;
        public void setInteger(Integer i){
            this.integer = i;
        }
        public Integer getInteger(){
            return this.integer;
        }
    }
}
