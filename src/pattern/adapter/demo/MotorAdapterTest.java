package pattern.adapter.demo;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/3 10:24
 */
public class MotorAdapterTest {
    public static void main(String[] args) {
        Motor motor = (Motor)ReadXML.getObject();
        motor.drive();
    }
}
