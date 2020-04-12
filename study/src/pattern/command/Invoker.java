package pattern.command;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/9 14:05
 */
public class Invoker {
    private Command command;
    public Invoker(Command command){
        this.command = command;
    }
    public void setCommand(Command command){
        this.command = command;
    }
    public void cell(){
        command.execute();
    }
}
