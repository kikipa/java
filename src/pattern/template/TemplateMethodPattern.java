package pattern.template;

public class TemplateMethodPattern {
    public static void main(String[] args) {
        AbstractClass tm = new ConcreteClass();
        tm.templateMethod();
    }
}

//虚函数的多态性技术
abstract class AbstractClass{
    public void templateMethod(){
        specificMethod();
        abstractMethod1();
        abstractMethod2();
    }

    public void specificMethod(){
        System.out.println("抽象类中的具体方法被调用...");
    }

    abstract void abstractMethod1();
    abstract void abstractMethod2();
}

class ConcreteClass extends AbstractClass{

    @Override
    void abstractMethod1() {
        System.out.println("抽象方法1的实现被调用...");
    }

    @Override
    void abstractMethod2() {
        System.out.println("抽象方法2的实现被调用...");
    }
}