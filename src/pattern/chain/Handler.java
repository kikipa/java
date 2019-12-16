package pattern.chain;

public abstract class Handler {
    private Handler next;
    public void setNext(Handler handler){
        this.next = handler;
    }
    public Handler getNext(){
        return next;
    }
    public abstract void handleRequest(String request);
}
