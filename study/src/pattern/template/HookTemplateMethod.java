package pattern.template;

public class HookTemplateMethod {
    public static void main(String[] args) {
        HookAbstractClass tm = new HookConcreteClass();
        tm.templateMethod();
    }
}

//不用调用我，让我来调用你”的反向控制技术
//正确使用“钩子方法”可以使得子类控制父类的行为
abstract class HookAbstractClass{
    public void templateMethod(){
        abstractMethod1();
        hookMethod1();
        if(hookMethod2()){
            specificMethod();
        }
        abstractMethod2();
    }

    public void specificMethod(){
        System.out.println("抽象类中的具体方法被调用...");
    }

    public void hookMethod1(){}
    public boolean hookMethod2(){
        return true;
    }

    public abstract void abstractMethod1();
    public abstract void abstractMethod2();
}

class HookConcreteClass extends HookAbstractClass{

    public void hookMethod1(){
        System.out.println("钩子方法1被重写...");
    }

    public boolean hookMethod2(){
        return false;
    }

    @Override
    public void abstractMethod1() {
        System.out.println("抽象方法1的实现被调用...");
    }

    @Override
    public void abstractMethod2() {
        System.out.println("抽象方法2的实现被调用...");
    }
}