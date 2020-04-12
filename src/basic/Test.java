package basic;

/**
 * @description:
 * @author: za-hejin
 * @time: 2020/2/17 15:11
 */
public class Test {
    public static void main(String[] args) {
        String val = "3.765";
        Float f = 0f;
        f = Float.parseFloat(val);
        System.out.println(f);
        System.out.println(f.equals(Float.parseFloat("3.765")));
    }
}
