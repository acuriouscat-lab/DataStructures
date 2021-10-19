package test;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode93 {

    public static void main(String[] args) {
        LinkedList<String> que = new LinkedList<>();
        que.add("123");
        que.add("22828");
        que.add("282");
        Queue<String> que1 = new LinkedList<>();
        System.out.println(String.join(",", que));
    }

}
