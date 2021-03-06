package test;

import java.util.LinkedList;
import java.util.TreeMap;

public class test_code {


    public static void main(String[] args) {
//        System.out.println(reverseParentheses("(abcd)"));
//        StringBuilder sb = new StringBuilder("abcd");
//        System.out.println(sb.reverse());
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(3, 8);
        System.out.println(map.lastKey());
        System.out.println(map.firstKey());
        map.put(5, 9);
        System.out.println(map.lastKey());
        System.out.println(map.firstKey());
        map.put(9, 10);
        map.put(2, 1);
        System.out.println(map.lastKey());
        System.out.println(map.firstKey());
    }

    public static String reverseParentheses(String s) {
        if(s == null || s.length() < 2){
            return s;
        }
        char[] str = s.toCharArray();
        return process(str,0).str;
    }

    public static Info process(char[] str,int index){
        LinkedList<String> que = new LinkedList<>();
        int i = index;
        while(i < str.length && str[i] != ')'){
            if(str[i] == '('){
                Info info = process(str,i + 1);
                i = info.index + 1;
                que.addLast(info.str);
            }else{
                System.out.println(str[i]);
                que.addLast(String.valueOf(str[i]));
                i++;
            }
        }
        String strs = reverse(que);
        return new Info(strs,i);
    }

    public static class Info{
        String str;
        int index;
        public Info(String s,int i){
            str = s;
            index = i;
        }
    }

    public static String reverse(LinkedList<String> que ){

        StringBuilder sb = new StringBuilder();
        while(!que.isEmpty()){
            String str = que.pollLast();
            sb.append(str);
        }
        System.out.println(sb);
        return sb.reverse().toString();
    }
}
