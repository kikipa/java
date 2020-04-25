package leetcode.algorithms;

import java.util.Stack;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/4 13:03
 */
public class ValidParentheses {
    public static void main(String[] args) {
//        Stack<Character> stack = new Stack<Character>();
//        stack.push('(');
//        if('('!=stack.pop()){
//            System.out.println("error");
//        }


        System.out.println(new ValidParentheses().isValid("()[]{}"));
        System.out.println(new ValidParentheses().isValid("([)]"));
//        System.out.println(new ValidParentheses().isValid("{[]}"));
    }
    public boolean isValid(String s){
        boolean r = false;
        if(s==null||s.length()==0){
            return r;
        }
        Stack<Character> stack = new Stack<Character>();
        for(int i=0;i<s.length();i++){
            if('('==s.charAt(i)){
                stack.push('(');
            }
            if('['==s.charAt(i)){
                stack.push('[');
            }
            if('{'==s.charAt(i)){
                stack.push('{');
            }
            if(')'==s.charAt(i)){
                if(stack.empty()){
                    return r;
                }
                if('('==stack.peek()){
                    System.out.println(stack.pop());
//                    System.out.println(stack.empty());
//                    System.out.println("---");
                }else{
                    return r;
                }
            }
            if(']'==s.charAt(i)){
                if(stack.empty()){
                    return r;
                }
                if('['==stack.peek()){
                    System.out.println(stack.pop());
//                    System.out.println(stack.empty());
//                    System.out.println("---");
                }else {
                    return r;
                }
            }
            if('}'==s.charAt(i)){
                if(stack.empty()){
                    return r;
                }
                if('{'==stack.peek()){
                    System.out.println(stack.pop());
//                    System.out.println(stack.empty());
//                    System.out.println("---");
                }else {
                    return r;
                }
            }
        }
        r = true;
        return r;
    }
}
