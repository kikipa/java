package middleware.memcache;

/**
 * @description: 实现一个自己的Hash函数，尽量将Hash值散开来
 * @author: za-hejin
 * @time: 2020/2/13 11:44
 */
public class FNV1_32_HASH {
    //尽量将hash值散开
    public static int getHash(String str){
        final int p = 16777619;
        int hash = (int)2166136261L;
        for(int i=0; i<str.length(); i++){
            hash = (hash^str.charAt(i))*p;
        }
        hash += hash<<13;
        hash ^= hash>>7;
        hash += hash<<3;
        hash ^= hash>>17;
        hash += hash<<5;

        //如果算出来的值为负数则取其绝对值
        if(hash<0){
            hash = Math.abs(hash);
        }

        return hash;
    }

}
