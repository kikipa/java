package pattern.state;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/12 9:54
 */
public class Context {
    private State state;
    public Context(){
        //init state
        this.state = new ConcreteStateA();
    }
    public void setState(State state){
        this.state = state;
    }
    public State getState(){
        return this.state;
    }
    public void handle(){
        state.handle(this);
    }
}
