package leetcode.algorithms;

public class ReverseString {
    public String reverseString(String s) {

        String[] strs = s.split("");

        for(int i=0,j=strs.length-1; i<j&&i<strs.length; i++,j--){

            String tmp = "";

            tmp = strs[i];
            strs[i] = strs[j];
            strs[j] = tmp;
        }

        StringBuffer sbuf = new StringBuffer();
        for(String str : strs){
            sbuf.append(str);
        }

        return sbuf.toString();
    }
}
