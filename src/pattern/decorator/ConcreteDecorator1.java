package pattern.decorator;

public class ConcreteDecorator1 extends Decorator{
    public ConcreteDecorator1(Component component) {
        super(component);
    }
    public void operation(){
        super.operation();
        addedFunction();
    }
    public void addedFunction(){
        System.out.println("ConcreteDecorator1 addedFunction");
    }
}
