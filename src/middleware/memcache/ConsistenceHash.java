package middleware.memcache;

import java.util.*;

/**
 * @description: 自己实现一致性Hash算法
 * @author: za-hejin
 * @time: 2020/2/13 10:55
 */
public class ConsistenceHash {
    //物理节点集合
    private List<String> realNodes = new ArrayList<>();

    //虚拟节点，跟物理节点对应关系: key物理节点名，value存虚拟节点hash值
    private Map<String,List<Integer>> real2VirtualMap = new HashMap<String,List<Integer>>();

    //虚拟节点数量
    private int virtualNums = 100;

    //圆环：排序存储结构，红黑树实现。Key存储虚拟节点hash值，Value存储对应物理节点
    private SortedMap<Integer,String> sortedMap = new TreeMap<>();

    public ConsistenceHash(){}

    public ConsistenceHash(int virtualNums){
        this.virtualNums = virtualNums;
    }

    /**
     * @description: 添加物理节点
     * @param node 物理节点名字
     * @return:
     * @author: za-hejin
     * @time: 2020/2/13 14:12
     */
    public void addServer(String node){
        realNodes.add(node);
        String vnode = null;

        int count = 0;
        int i = 0;
        List<Integer> virtualNodes = new ArrayList<>();
        real2VirtualMap.put(node,virtualNodes);
        //创建虚拟节点,并放到环上去（排序存储）
        while(count<virtualNums){
            vnode = node + "V_" + i;
            i++;
            int hashValue = FNV1_32_HASH.getHash(vnode);
            //解决hash碰撞问题
            if(!sortedMap.containsKey(hashValue)){
                virtualNodes.add(hashValue);
                sortedMap.put(hashValue,node);
                count++;
            }
        }
    }

    /**
     * @description: 移除服务器
     * @param node 物理服务器名称
     * @return:
     * @author: za-hejin
     * @time: 2020/2/13 14:14
     */
    public void removeServer(String node){
        List<Integer> virturalNodes = real2VirtualMap.get(node);
        //下圆环
        if(virturalNodes!=null&&!virturalNodes.isEmpty()){
            for(Integer hashVal : virturalNodes){
                sortedMap.remove(hashVal);
            }
        }
        //移除对应关系
        real2VirtualMap.remove(node);
        //移除物理节点
        realNodes.remove(node);
    }

    /**
     * @description: 根据key找到对应物理节点
     * @param key
     * @return: 物理节点名称
     * @author: za-hejin
     * @time: 2020/2/13 11:22
     */
    public String getServer(String key){
        //根据key计算hash值
        int hashValue = FNV1_32_HASH.getHash(key);
        SortedMap<Integer,String> subMap = sortedMap.tailMap(hashValue);
        if(subMap==null||subMap.isEmpty()){
            //最大值的情况，使用最小值
            return sortedMap.get(sortedMap.firstKey());
        }else{
            return subMap.get(subMap.firstKey());
        }
    }

    public static void main(String[] args) {
        ConsistenceHash ch = new ConsistenceHash();
        ch.addServer("192.168.100.10");
        ch.addServer("192.168.100.11");
        ch.addServer("192.168.100.12");

        for(int i = 0; i < 100; i++) {
            String realServerName = ch.getServer("key"+i);
            System.out.println("key"+i+" 对应的服务器是："+realServerName);
        }
    }
}
