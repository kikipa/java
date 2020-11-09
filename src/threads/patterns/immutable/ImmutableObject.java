package threads.patterns.immutable;

public final class ImmutableObject {

    private final Object stateX;

    private final Object stateN;

    public ImmutableObject(Object stateX, Object stateN){
        this.stateX = stateX;
        this.stateN = stateN;
    }

    public Object getStateX(){
        return stateX;
    }

    public Object getStateN(){
        return stateN;
    }

}
