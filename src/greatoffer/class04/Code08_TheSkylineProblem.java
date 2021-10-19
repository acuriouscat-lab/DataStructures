package greatoffer.class04;

import java.util.*;
import java.util.Map.Entry;

// 本题测试链接 : https://leetcode-cn.com/problems/the-skyline-problem/
public class Code08_TheSkylineProblem {

    public static class Node {
        public int x;
        public boolean isAdd;
        public int h;

        public Node(int x, boolean isAdd, int h) {
            this.x = x;
            this.isAdd = isAdd;
            this.h = h;
        }
    }

    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.x - o2.x;
        }
    }

    // 外轮廓线要保证去到每一个点的最大的高度，并且当前高度如果和上一个一样的话，需要淘汰掉
    // 那么就需要记录到达每个点的时候 目前的最高的记录，并且在高度变化的时候，需要对高度的删除
    // 那么对应一个高楼，左边是该高度出现的位置，右边是该高度删除的位置
    // 每个点 [最终] 的高度应该 是这个点能见到的高度
    public static List<List<Integer>> getSkyline(int[][] matrix) {
        Node[] nodes = new Node[matrix.length * 2];
        for (int i = 0; i < matrix.length; i++) {
            nodes[i * 2] = new Node(matrix[i][0], true, matrix[i][2]);
            nodes[i * 2 + 1] = new Node(matrix[i][1], false, matrix[i][2]);
        }
        // 按照 x 进行排序，保证到达该点的时候，能够见到的 其他店的高度 都应该在表里了
        Arrays.sort(nodes, new NodeComparator());
        // key  高度  value 次数
        TreeMap<Integer, Integer> mapHeightTimes = new TreeMap<>();
        // key 坐标 value 高度
        TreeMap<Integer, Integer> mapXHeight = new TreeMap<>();
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].isAdd) { // 如果是左边，代表高度出现的位置，记录一下这个高度出现的次数，因为可能存在多个
                if (!mapHeightTimes.containsKey(nodes[i].h)) {
                    mapHeightTimes.put(nodes[i].h, 1);
                } else {
                    mapHeightTimes.put(nodes[i].h, mapHeightTimes.get(nodes[i].h) + 1);
                }
            } else {
                if (mapHeightTimes.get(nodes[i].h) == 1) {
                    mapHeightTimes.remove(nodes[i].h);
                } else {
                    mapHeightTimes.put(nodes[i].h, mapHeightTimes.get(nodes[i].h) - 1);
                }
            }
            if (mapHeightTimes.isEmpty()) {// 记录一下当前这个点的高度 -- 最大的高度
                mapXHeight.put(nodes[i].x, 0);
            } else {
                mapXHeight.put(nodes[i].x, mapHeightTimes.lastKey());
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (Entry<Integer, Integer> entry : mapXHeight.entrySet()) {
            int curX = entry.getKey();
            int curMaxHeight = entry.getValue();
            if (ans.isEmpty() || ans.get(ans.size() - 1).get(1) != curMaxHeight) {
                ans.add(new ArrayList<>(Arrays.asList(curX, curMaxHeight)));
            }
        }
        return ans;
    }


    public List<List<Integer>> getSkyline1(int[][] buildings) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        for (int[] building : buildings) {
            pq.offer(new int[] { building[0], -building[2] });
            pq.offer(new int[] { building[1], building[2] });
        }

        List<List<Integer>> res = new ArrayList<>();

        TreeMap<Integer, Integer> heights = new TreeMap<>((a, b) -> b - a);
        heights.put(0, 1);
        int left = 0, height = 0;
        while (!pq.isEmpty()) {
            int[] arr = pq.poll();
            if (arr[1] < 0) {
                heights.put(-arr[1], heights.getOrDefault(-arr[1], 0) + 1);
            } else {
                heights.put(arr[1], heights.get(arr[1]) - 1);
                if (heights.get(arr[1]) == 0) heights.remove(arr[1]);
            }
            int maxHeight = heights.keySet().iterator().next();
            if (maxHeight != height) {
                left = arr[0];
                height = maxHeight;
                res.add(Arrays.asList(left, height));
            }
        }

        return res;
    }




}



