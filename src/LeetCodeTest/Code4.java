package LeetCodeTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Administrator
 * @Description
 * @create 2021-08-10 15:47
 */
public class Code4 {

    public static void main(String[] args) {

        Solution s = new Solution();



    }


    public static int multi(int a, int b) {
        int sum = 0;
        while(b != 0){
            if ((b & 1) == 1) {
                sum = add(sum, a);
            }
            a <<= 1;
            b >>= 1;
        }
        return sum;
    }

    public static int add(int a, int b) {
        int sum = 0;
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }



}

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        PriorityQueue<Node> heap = new PriorityQueue<>((o1, o2) -> o1.cnt - o2.cnt);
        HashMap<String,Node> map = new HashMap<>();
        for(String str : words){
            Node cur = map.getOrDefault(str,new Node(0,str));
            cur.cnt++;
            map.put(str,cur);
        }
        List<String> res = new ArrayList<>();
        for(Node cur : map.values()){
            if(heap.size() > k && cur.cnt > heap.peek().cnt){
                heap.poll();
            }
            heap.add(cur);
        }
        while(!heap.isEmpty() && k != 0){
            k--;
            res.add(heap.poll().str);
        }
        return res;
    }

    public static class Node{
        String str;
        int cnt;
        public Node(int c,String s){
            str = s;
            cnt = c;
        }
    }

}
