package algorithms.basic.leetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public static void main(String[] args) throws Exception {
        int[] a = {2, 7, 11, 15};
        int[] b = new TwoSum().twoSumOptimized(a,9);
        for(int i=0;i<b.length;i++){
            System.out.println(b[i]);
        }

    }

    public int[] twoSum(int[] nums, int target) {
        int m = 0;
        int n = 0;
        int len = nums.length;
        int k = 0;
        outer: for(int i=0; i<len; i++){
            if(nums[i]>target){
                continue;
            }
            m = i;
            k = target - nums[i];
            for(int j=i+1; j<len; j++){
                if(nums[j]==k){
                    n = j;
                    break outer;
                }
            }
        }
        int[] a = {m,n};
        return a;
    }

    public int[] twoSumOptimized(int[] nums, int target) throws Exception {
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0; i<nums.length; i++){
            int k = target - nums[i];
            if(map.containsKey(k)){
                return new int[]{i,map.get(k)};
            }
            map.put(nums[i],i);
        }
        throw new Exception("no solution error");
    }
}
