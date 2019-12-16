package pattern.adapter.demo;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/3 10:25
 */
public class ElectricAdapter implements Motor {
    private ElectricMotor electricMotor;
    public ElectricAdapter(){
        this.electricMotor = new ElectricMotor();
    }
    @Override
    public void drive() {
        electricMotor.electricDrive();
    }
}
