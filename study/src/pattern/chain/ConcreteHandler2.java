package pattern.chain;

public class ConcreteHandler2 extends Handler {
    @Override
    public void handleRequest(String request) {
        if("two".equals(request)){
            System.out.println("ConcreteHandler2 handleRequest : "+request);
        }else {
            if(getNext()!=null){
                getNext().handleRequest(request);
            }else {
                System.out.println("no one can handle error!");
            }
        }
    }
}
