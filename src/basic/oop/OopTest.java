package basic.oop;

/**
 * @Author: Jin.HE
 * @Date: 2021/1/20 11:06
 */
public class OopTest {

  public static void main(String[] args) {

    ObjectImpl o1 = new ObjectImpl();
    o1.init();

    ObjectImpl2 o2 = new ObjectImpl2();
    o2.init();

    o1.print();
    o2.print();


  }
}
