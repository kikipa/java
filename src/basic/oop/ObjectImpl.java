package basic.oop;

/**
 * @Author: Jin.HE
 * @Date: 2021/1/20 11:04
 */
public class ObjectImpl extends AbstractObject{

  public void init(){
//    this.value = "ObjectImpl";
    super.value = "ObjectImpl";
  }

  public void print(){
//    System.out.println(this.value);
    System.out.println(super.value);
  }

}
