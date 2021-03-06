package leetcode.algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 *
 * 示例 1:
 *
 * 输入: "III"
 * 输出: 3
 * 示例 2:
 *
 * 输入: "IV"
 * 输出: 4
 * 示例 3:
 *
 * 输入: "IX"
 * 输出: 9
 * 示例 4:
 *
 * 输入: "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 * 示例 5:
 *
 * 输入: "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/roman-to-integer
 * */
public class RomanToInteger {
    public static void main(String[] args) {
        System.out.println(new RomanToInteger().romanToInt3("LVIII"));
    }

    public int romanToInt(String s) {
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("I",1);
        map.put("V",5);
        map.put("X",10);
        map.put("L",50);
        map.put("C",100);
        map.put("D",500);
        map.put("M",1000);
        map.put("IV",3);
        map.put("IX",8);
        map.put("XL",30);
        map.put("XC",80);
        map.put("CD",300);
        map.put("CM",800);

        char[] ch = s.toCharArray();
        int len = s.length();
        if(len==0){
            return map.get(s);
        }
        int v = 0;
        for(int i=0;i<len;){
            v+=map.get(ch[i]+"");
            if(i<len-1){
                String tc = ch[i]+""+ch[i+1];
                if(map.containsKey(tc)){
                    v+=map.get(tc);
                    i+=2;
                }else {
                    i++;
                }
            }else{
                i++;
            }
        }
        return v;
    }

    public int romanToInt2(String s) {
        int v = 0;
        for(int i=0;i<s.length();i++){
            if(i<s.length()-1){
                int l = getValue(s.charAt(i));
                int r = getValue(s.charAt(i+1));
                if(l<r){
                    v -= l;
                }else {
                    v += l;
                }
            }else{
                v += getValue(s.charAt(i));
            }
        }
        return v;
    }

    public int romanToInt3(String s) {
        int v = 0;
        char[] ch = s.toCharArray();
        for(int i=0;i<ch.length-1;i++){
            if(ch[i]=='I'&&(ch[i+1]=='V'||ch[i+1]=='X')){
                v -= 2;
            }
            if(ch[i]=='X'&&(ch[i+1]=='L'||ch[i+1]=='C')){
                v -= 20;
            }
            if(ch[i]=='C'&&(ch[i+1]=='D'||ch[i+1]=='M')){
                v -= 200;
            }
        }
        for(int i=0;i<ch.length;i++){
            v += getValue(ch[i]);
        }
        return v;
    }

    public int getValue(char c){
        switch(c){
            case 'I':return 1;
            case 'V':return 5;
            case 'X':return 10;
            case 'L':return 50;
            case 'C':return 100;
            case 'D':return 500;
            case 'M':return 1000;
            default:return 0;
        }
    }
}
