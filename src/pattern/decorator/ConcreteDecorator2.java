package pattern.decorator;

public class ConcreteDecorator2 extends Decorator {
    public ConcreteDecorator2(Component component) {
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
