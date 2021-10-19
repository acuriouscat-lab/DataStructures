package test;

import java.util.*;

public class test3 {

    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
//
//        Map<Integer,Integer> map = new HashMap<>();
//
//        Set<Integer> integers = map.keySet();
//        Collection<Integer> values = map.values();
//        LinkedList<Integer> tmp = new LinkedList<>();
//        tmp.addLast();
//        tmp.offerLast()
        Stack<Integer> stack = new Stack<>();
        int res = 0;

        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int j = stack.pop();
                res = Math.max(res, stack.isEmpty() ? heights[j] : Math.min(heights[i], heights[stack.peek()]) * (i - stack.peek() + 1));
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            res = Math.max(res, heights[j]);
        }
        return res;
    }

    public static void main(String[] args) {
//        largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3});
        System.out.println(Integer.valueOf("06"));
        System.out.println('a' + 1);
        String[] deads = new String[]{"123", "asda"};
//        HashSet<String> set = new HashSet<>(deads);
//        ArrayList<Interval> intervals = new ArrayList<>();
//
//        Arrays.sort(intervals, (Comparator<Interval>) (o1, o2) -> {
//            return return o1.start - o2.start;
//        });
//
//        intervals.sort(new Comparator<Interval>() {
//            @Override
//            public int compare(Interval o1, Interval o2) {
//                return
//            }
//        });

        //intervals.sort((o1, o2) -> o1.start != o2.start ? o1.start - o2.start : o2.end - o1.end);


        //ArrayList<Integer> tmp = new ArrayList<>((Comparator<Integer>) (o1, o2) -> o2 - o1);

    }

    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

}
