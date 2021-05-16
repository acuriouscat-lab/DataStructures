package algorithmlearning003.class03;

import java.util.HashMap;

public class Code07_TopKTimesRealTime {

    /*
    请实现如下结构：
        TopRecord{
        public TopRecord(int K)  :  构造时事先指定好K的大小，构造后就固定不变了
        public  void add(String str)  :   向该结构中加入一个字符串，可以重复加入
        public  List<String> top() : 返回之前加入的所有字符串中，词频最大的K个
        }
        要求：
        add方法，复杂度O(log K);
        top方法，复杂度O(K)
     */
    public static class Node {
        public String str;
        public int times;

        public Node(String s, int t) {
            str = s;
            times = t;
        }
    }
    public static class TopKRecord{
        private Node[] heap;
        private int heapsize;
        private HashMap<String,Node> strNodeMap;
        private HashMap<Node,Integer> nodeIndexMap;

        public TopKRecord(int topK) {
            heap = new Node[topK];
            strNodeMap = new HashMap<>();
            nodeIndexMap = new HashMap<>();
        }

        public void add(String str) {
            Node curNode = null;
            int preIndex = -1;
            if (!strNodeMap.containsKey(str)) {
                curNode = new Node(str, 1);
                strNodeMap.put(str, curNode);
                nodeIndexMap.put(curNode, -1);
            }else{
                curNode = strNodeMap.get(str);
                curNode.times ++;
                preIndex = nodeIndexMap.get(curNode);
            }

            if (preIndex == -1) { // 不在堆上
                if (heapsize == heap.length) {// 堆满了
                    if (heap[0].times < curNode.times) {
                        nodeIndexMap.put(heap[0], -1);
                        nodeIndexMap.put(curNode, 0);
                        heap[0] = curNode;
                        heapify(0, heapsize);
                    }
                }else{// 堆没有满
                    nodeIndexMap.put(curNode, heapsize);
                    heap[heapsize] = curNode;
                    heapInsert(heapsize++);
                }
            }else{ // str已经在堆上了
                heapify(preIndex,heapsize);
            }
        }
        public void printTopK() {
            System.out.println("TOP: ");
            for (int i = 0; i != heap.length; i++) {
                if (heap[i] == null) {
                    break;
                }
                System.out.print("Str: " + heap[i].str);
                System.out.println(" Times: " + heap[i].times);
            }
        }

        private void heapInsert(int index) {
            while (index != 0) {
                int parent = (index - 1) / 2;
                if (heap[index].times < heap[parent].times) {
                    swap(index, parent);
                    index = parent;
                }else{
                    break;
                }
            }
        }
        private void heapify(int index, int heapsize) {
            int leftIndex = index * 2 + 1;
            int rightIndex = index * 2 + 2;
            int smallest = index;
            while (leftIndex < heapsize) {
                if (heap[leftIndex].times < heap[smallest].times) {
                    smallest = leftIndex;
                }
                if (rightIndex < heapsize && heap[rightIndex].times < heap[smallest].times) {
                    smallest = rightIndex;
                }
                if (smallest != index) {
                    swap(smallest, index);
                }else{
                    break;
                }
                index = smallest;
                leftIndex = smallest * 2 + 1;
                rightIndex = smallest * 2 + 2;
            }
        }


        private void swap(int index1, int index2) {
            nodeIndexMap.put(heap[index1], index2);
            nodeIndexMap.put(heap[index2], index1);
            Node temp = heap[index1];
            heap[index1] = heap[index2];
            heap[index2] = temp;
        }
    }


    public static String[] generateRandomArray(int len, int max) {
        String[] res = new String[len];
        for (int i = 0; i != len; i++) {
            res[i] = String.valueOf((int) (Math.random() * (max + 1)));
        }
        return res;
    }

    public static void printArray(String[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TopKRecord record = new TopKRecord(2);
        record.add("zuo");
        record.printTopK();
        record.add("cheng");
        record.add("cheng");
        record.printTopK();
        record.add("Yun");
        record.add("Yun");
        record.printTopK();

    }


}
