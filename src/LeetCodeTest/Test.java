package LeetCodeTest;

import java.util.Arrays;

/**
 * @author Administrator
 * @Description
 * @create 2021-08-15 19:25
 */
public class Test {

    public static void main(String[] args) {

        Solution1 s = new Solution1();
        s.respace(new String[]{"looked", "just", "like", "her", "brother"}, "jesslookedjustliketimherbrother");


    }



}


class Solution1 {

    Node head;


    public int respace(String[] dictionary, String sentence) {
        int n = dictionary.length;
        int m = sentence.length();
        int[] dp = new int[m + 1];
        Arrays.fill(dp,m + 2);
        dp[m] = 0;
        head = new Node();

        for(String str : dictionary){
            fillWord(str);
        }

        for(int i = m - 1; i >= 0; i--){
            Node node = head;
            for(int j = i; j < m; j++){
                int path = sentence.charAt(j) - 'a';
                if(node.nexts[path] == null){
                    dp[i] = Math.min(dp[i],dp[j + 1] + j - i + 1);
                    break;
                }
                node = node.nexts[path];
                if(node.isEnd){
                    dp[i] = Math.min(dp[i],dp[j + 1]);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[0];
    }


    public void fillWord(String str){
        Node node = head;
        for(int i = 0; i < str.length(); i++){
            int path = str.charAt(i) - 'a';
            if(node.nexts[path] == null){
                node.nexts[path] = new Node();
            }
        }
        node.isEnd = true;
    }

    public static class Node{
        Node[] nexts;
        boolean isEnd;
        public Node(){
            nexts = new Node[26];
            isEnd = false;
        }
    }

}
