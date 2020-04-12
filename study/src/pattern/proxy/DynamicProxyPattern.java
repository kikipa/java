package pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyPattern {
    public static void main(String[] args) {
        AbstractSubject subject = null;
        InvocationHandler handler = null;
        handler = new DynamicProxy(new RealSubject01());
        subject = (AbstractSubject) Proxy.newProxyInstance(
                AbstractSubject.class.getClassLoader(),
                new Class[]{AbstractSubject.class},
                handler);
        subject.request();
    }
}

interface AbstractSubject{
    public void request();
}

class RealSubject01 implements AbstractSubject{
    @Override
    public void request() {
        System.out.println("do real subject 01 actions...");
    }
}

class RealSubject02 implements AbstractSubject{
    @Override
    public void request() {
        System.out.println("do real subject 02 actions...");
    }
}

class DynamicProxy implements InvocationHandler{
    private Object obj;

    public DynamicProxy(Object obj){
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(proxy,args);
        return null;
    }
}

