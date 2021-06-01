package greatoffer.class09;

import java.util.*;

public class Code01_TopK {

    // 测试链接：https://www.lintcode.com/problem/top-k-frequent-words-ii/

    private Node[] heap;
    private int heapSize;
    private HashMap<String, Node> strNodeMap;
    private HashMap<Node, Integer> nodeIndexMap;
    private NodeHeapComp comp;
    private TreeSet<Node> treeSet;

    public static class NodeHeapComp implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.times != o2.times ? (o1.times - o2.times) : (o2.str.compareTo(o1.str));
        }

    }

    public static class NodeTreeSetComp implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.times != o2.times ? (o2.times - o1.times) : (o1.str.compareTo(o2.str));
        }

    }

    public Code01_TopK(int K) {
        heap = new Node[K];
        heapSize = 0;
        strNodeMap = new HashMap<>();
        nodeIndexMap = new HashMap<>();
        comp = new NodeHeapComp();
        treeSet = new TreeSet<>(new NodeTreeSetComp());
    }

    public static class Node {
        public String str;
        public int times;

        public Node(String s, int t) {
            str = s;
            times = t;
        }
    }


    public void add(String str) {
        if (heap.length == 0) {
            return;
        }
        Node curNode = null;
        int preIndex = -1;
        if (strNodeMap.containsKey(str)) {
            curNode = strNodeMap.get(str);
            if (treeSet.contains(curNode)) {
                treeSet.remove(curNode);
            }
            curNode.times++;
            preIndex = nodeIndexMap.get(curNode);
        } else {
            curNode = new Node(str, 1);
            strNodeMap.put(str, curNode);
            nodeIndexMap.put(curNode, -1);
        }

        if (preIndex == -1) {
            if (heapSize == heap.length) {
                if (comp.compare(heap[0], curNode) < 0) {
                    nodeIndexMap.put(heap[0], -1);
                    treeSet.remove(heap[0]);
                    treeSet.add(curNode);
                    nodeIndexMap.put(curNode, 0);
                    heap[0] = curNode;
                    heapify(0, heapSize);
                }
            }else{
                treeSet.add(curNode);
                nodeIndexMap.put(curNode, heapSize);
                heap[heapSize] = curNode;
                heapInsert(heapSize++);
            }

        }else{
            treeSet.add(curNode);
            heapify(preIndex, heapSize);
        }
    }

    public List<String> topK(){
        List<String> ans = new ArrayList<>();
        for (Node node : treeSet) {
            ans.add(node.str);
        }
        return ans;
    }

    public void heapInsert(int index) {
        while (index >= 0) {
            int parent = (index - 1) / 2;
            if (comp.compare(heap[index], heap[parent]) < 0) {
                swap(index, parent);
                index = parent;
            }else{
                break;
            }
        }
    }

    public void heapify(int index, int heapSize) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int smallSize = index;
        while (left < heapSize) {
            if (comp.compare(heap[left], heap[smallSize]) < 0) {
                smallSize = left;
            }
            if (right < heapSize && comp.compare(heap[right], heap[smallSize]) < 0) {
                smallSize = right;
            }
            if (smallSize == index) {
                break;
            }else{
                swap(index, smallSize);
            }
            index = smallSize;
            left = 2 * index + 1;
            right = 2 * index + 2;
        }
    }

    public void swap(int index1, int index2) {
        nodeIndexMap.put(heap[index1], index2);
        nodeIndexMap.put(heap[index2], index1);
        Node temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }



}
