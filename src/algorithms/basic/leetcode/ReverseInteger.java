package algorithms.basic.leetcode;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * */
public class ReverseInteger {
    public static void main(String[] args) {
        System.out.println(new ReverseInteger().reverseInt(2147483641));  //7463847412
        System.out.println(new ReverseInteger().reverseInt(-2147483641));  //2147483648
    }

    public int reverseInt(int x){
        int rev = 0;
        while(x!=0){
            int pop = x%10;
            //判断溢出情况
            if(rev>Integer.MAX_VALUE/10||(rev==Integer.MAX_VALUE/10&&pop>7)){
                return 0;
            }else if(rev<Integer.MIN_VALUE/10||(rev==Integer.MIN_VALUE/10&&pop<-8)){
                return 0;
            }
            int tmp = rev*10+pop;
            rev = tmp;
            x /= 10;
        }
        return rev;
    }
}
