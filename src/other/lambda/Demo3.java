package other.lambda;

public class Demo3 {
    public static void main(String[] args) {
//        invokeCalc(120,130,(int a, int b)->{
//            return a+b;
//        });

        invokeCalc(120,130,(a, b)->a+b);

    }

    static void invokeCalc(int a, int b, Calculator calc){
        System.out.println(calc.calc(a,b));
    }
}

interface Calculator{
    int calc(int a, int b);
}