package basic.oop;

/**
 * @Author: Jin.HE
 * @Date: 2021/1/20 11:05
 */
public class ObjectImpl2 extends AbstractObject{

  public void init(){
//    this.value = "ObjectImpl2";
    super.value = "ObjectImpl2";
  }

  public void print(){
//    System.out.println(this.value);
    System.out.println(super.value);
  }
}
