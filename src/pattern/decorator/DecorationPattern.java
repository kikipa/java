package pattern.decorator;

public class DecorationPattern {
    public static void main(String[] args) {
        Component p = new ConcreteComponent();
        p.operation();
        System.out.println("-------------------------------------");
        Component d = new ConcreteDecorator1(p);
        d.operation();
    }
}
