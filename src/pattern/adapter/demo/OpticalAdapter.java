package pattern.adapter.demo;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/3 10:25
 */
public class OpticalAdapter implements Motor {
    private OpticalMotor opticalMotor;
    public OpticalAdapter(){
        this.opticalMotor = new OpticalMotor();
    }
    @Override
    public void drive() {
        opticalMotor.opticalDrive();
    }
}
