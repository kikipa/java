package leetcode.algorithms;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/3 13:03
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 *
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        String[] strs2 = {"dog","racecar","car"};
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(strs));
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(strs2));
    }

    public String longestCommonPrefix(String[] strs){
        if(strs==null||strs.length==0){
            return "";
        }
        String shortestStr = strs[0];
        for(int i=0;i<strs.length;i++){
            if(strs[i].length()<shortestStr.length()){
                shortestStr = strs[i];
            }
        }

        String commonPrefix = "";
        outer:for(int i=0;i<shortestStr.length();i++){
            for(int j=0;j<strs.length;j++){
                if(shortestStr.charAt(i)!=strs[j].charAt(i)){
                    break outer;
                }
            }
            commonPrefix += shortestStr.charAt(i);
        }

        return commonPrefix;
    }

}
