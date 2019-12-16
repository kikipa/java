package pattern.state.share;

import java.util.HashMap;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/13 9:47
 */
public class ShareContext {
    private ShareState state;
    private HashMap<String,ShareState> stateSet = new HashMap<String,ShareState>();
    public ShareContext(){
        state = new ConcreteStateA();
        stateSet.put("A",state);
        state = new ConcreteStateB();
        stateSet.put("B",state);
        state = stateSet.get("A");
    }
    public void setState(ShareState state){
        this.state = state;
    }
    public ShareState getState(String key){
        return stateSet.get(key);
    }
    public void handle(){
        state.handle(this);
    }
}
