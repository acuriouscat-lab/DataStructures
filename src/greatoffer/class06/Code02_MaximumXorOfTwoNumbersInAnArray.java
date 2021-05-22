package greatoffer.class06;

public class Code02_MaximumXorOfTwoNumbersInAnArray {

    //数组中所有数都异或起来的结果，叫做异或和
    //给定一个数组arr，想知道arr中哪两个数的异或结果最大
    //返回最大的异或结果
    //https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/
    public int findMaximumXOR(int[] nums) {

        if(nums == null || nums.length < 2){
            return 0;
        }
        NumTrie trie = new NumTrie();
        trie.add(nums[0]);
        int max = Integer.MIN_VALUE;
        for(int i = 1; i < nums.length; i++){
            max = Math.max(max,trie.maxEor(nums[i]));
            trie.add(nums[i]);
        }
        return max;

    }

    public static class Node{
        public Node[] nexts = new Node[2];
    }

    public static class NumTrie{
        public Node head = new Node();

        public void add(int num){
            Node node = head;
            for(int i = 30; i >= 0; i--){
                int path = (num >> i) & 1;
                if(node.nexts[path] == null){
                    node.nexts[path] = new Node();
                }
                node = node.nexts[path];
            }
        }

        public int maxEor(int num){
            int ans = 0;
            Node node = head;
            for(int i = 30; i >= 0; i--){
                int path = (num >> i) & 1;
                int best = path ^ 1;
                best = node.nexts[best] != null ? best : (best ^ 1);
                ans |= (best ^ path) << i;
                node = node.nexts[best];
            }
            return ans;
        }


    }


}
