package other;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @Author: Jin.HE
 * @Date: 2021/4/19 11:51
 */
public class MapEnumKeyTest {

    public static void main(String[] args) {
        Order order = new Order();
        Map<KeyEnum, String> keyMap = new HashMap<>();
        keyMap.put(KeyEnum.TXT, "txt");

        order.setKeyMap(keyMap);

        System.out.println(order.getKeyMap().get(KeyEnum.TXT));
        System.out.println(order.getKeyMap().containsKey(KeyEnum.TXT));
    }

    enum KeyEnum {
        TXT,
        JPG
    }

   static class Order{
        private Map<KeyEnum, String> keyMap;

        public Map<KeyEnum, String> getKeyMap() {
            return keyMap;
        }

        public void setKeyMap(Map<KeyEnum, String> keyMap) {
            this.keyMap = keyMap;
        }
    }
}
