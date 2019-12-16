package pattern.command.macro_command;

import java.util.ArrayList;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/10 10:01
 */
public class CompositeInvoker implements Command{
    private ArrayList<Command> children;
    public CompositeInvoker(){
        children = new ArrayList<Command>();
    }
    @Override
    public void execute() {
        for(Command command : children){
            command.execute();
        }
    }

    public void add(Command command){
        children.add(command);
    }

    public void remove(Command command){
        children.remove(command);
    }
}
