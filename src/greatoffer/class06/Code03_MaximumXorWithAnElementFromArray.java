package greatoffer.class06;

public class Code03_MaximumXorWithAnElementFromArray {


    public int[] maximizeXor(int[] nums, int[][] queries) {
        NumTrie trie = new NumTrie();
        for (int i = 0; i < nums.length; i++) {
            trie.add(nums[i]);
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            res[i] = trie.maxEor(queries[i][0], queries[i][1]);
        }
        return res;
    }


    public static class Node{
        public int min = Integer.MAX_VALUE;
        public Node[] nexts = new Node[2];
    }

    public static class  NumTrie{
        public Node head = new Node();

        public void add(int num) {
            Node node = head;
            node.min = Math.min(node.min, num);
            for (int i = 30; i >= 0; i--) {
                int path = (num >> i) & 1;
                if (node.nexts[path] == null) {
                    node.nexts[path] = new Node();
                }
                node = node.nexts[path];
                node.min = Math.min(node.min, num);
            }
        }

        public int maxEor(int num, int limit) {
            Node node = head;
            if (node.min > limit) {
                return -1;
            }
            int ans = 0;
            for (int i = 30; i >= 0; i--) {
                int path = (num >> i) & 1;
                int best = path ^ 1;
                best = node.nexts[best] != null && node.nexts[best].min <= limit ? best : best ^ 1;
                ans |= (path ^ best) << i;
                node = node.nexts[best];
            }
            return ans;
        }
    }

}
