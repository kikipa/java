package pattern.proxy;

public class ProxyPattern {
    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.request();
    }

}

//abstract subject
interface Subject{
    public void request();
}

//Real subject
class RealSubject implements Subject{
    @Override
    public void request() {
        System.out.println("do real subject actions...");
    }
}

//Proxy
class Proxy implements Subject{
    RealSubject realSubject;

    public void preRequest(){
        System.out.println("handle before calling real subject...");
    }

    @Override
    public void request() {
        preRequest();
        if(realSubject==null){
            realSubject = new RealSubject();
        }
        realSubject.request();
        postRequest();
    }

    public void postRequest(){
        System.out.println("handle after calling real subject...");
    }
}
