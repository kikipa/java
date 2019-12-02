package pattern.chain_of_responsibility;

public class ConcreteHandler1 extends Handler {
    @Override
    public void handleRequest(String request) {
        if("one".equals(request)){
            System.out.println("ConcreteHandler1 handleRequest : "+request);
        }else {
            if(getNext()!=null){
                getNext().handleRequest(request);
            }else {
                System.out.println("no one can handle error!");
            }
        }
    }
}
