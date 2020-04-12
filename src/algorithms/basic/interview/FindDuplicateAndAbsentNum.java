package algorithms.basic.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 *
 * 由于记账错误，给定的一个正整数序列里面，有两个数字重复了，同时缺少了一个数字。
 * 要求写一个函数，找出序列中重复的数字和缺少的数字。
 *
 * 例如：
 * 输入：[1, 5, 2, 2, 3]
 * 输出：[2, 4]
 *
 * @author: za-hejin
 * @time: 2020/3/30 14:46
 */
public class FindDuplicateAndAbsentNum {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(5);
        nums.add(2);
        nums.add(2);
        nums.add(3);
        nums.add(10);
        nums.add(3);
        nums.add(5);
        nums.add(7);
        nums.add(3);
        FindDuplicateAndAbsentNum.findDuplicateAndAbsentNum(nums);
    }

    public static List<Integer> findDuplicateAndAbsentNum(List<Integer> nums){

        //排序获得有序的序列
        Collections.sort(nums);

        List<Integer> result = new ArrayList<>();
        int max = nums.get(nums.size()-1);
        for(int i=0, min = nums.get(0); i<nums.size()&&min<=max; i++,min++){
            if(nums.get(i)==min){
                //匹配
                continue;
            }
            if(nums.get(i)<min){
                //nums.get(i)是重复数字
                System.out.println(nums.get(i));
                result.add(nums.get(i));
                //下次循环继续检查min
                min--;
            }else if(nums.get(i)>min){
                //min是缺失数字
                System.out.println(min);
                result.add(min);
                //下次循环继续检查nums.get(i)
                i--;
            }
        }

        return result;
    }
}
