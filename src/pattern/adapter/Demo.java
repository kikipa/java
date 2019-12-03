package pattern.adapter;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/3 10:02
 */
public class Demo {
    public static void main(String[] args) {
        Motor motor = (Motor)ReadXML.getObject();
        motor.drive();
    }
}

interface Motor{
    public void drive();
}

class  ElectricMotor{
    public void electricDrive(){
        System.out.println("ElectricMotor electricDrive");
    }
}

class OpticalMotor{
    public void opticalDrive(){
        System.out.println("OpticalMotor opticalDrive");
    }
}

class ElectricAdapter implements Motor{
    private ElectricMotor electricMotor;
    public ElectricAdapter(){
        this.electricMotor = new ElectricMotor();
    }
    @Override
    public void drive() {
        electricMotor.electricDrive();
    }
}

class OpticalAdapter implements Motor{
    private OpticalMotor opticalMotor;
    public OpticalAdapter(){
        this.opticalMotor = new OpticalMotor();
    }
    @Override
    public void drive() {
        opticalMotor.opticalDrive();
    }
}



class ReadXML
{
    public static Object getObject()
    {
        try
        {
            DocumentBuilderFactory dFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=dFactory.newDocumentBuilder();
            Document doc;
            doc=builder.parse(new File("src/pattern/adapter/config.xml"));
            NodeList nl=doc.getElementsByTagName("className");
            Node classNode=nl.item(0).getFirstChild();
            String cName="adapter."+classNode.getNodeValue();
            Class<?> c=Class.forName(cName);
            Object obj=c.newInstance();
            return obj;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}