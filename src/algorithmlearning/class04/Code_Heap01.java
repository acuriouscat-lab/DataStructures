package algorithmlearning.class04;

/**
 * 大根堆
 */
public class Code_Heap01 {

    public static class MyMaxHeap {
        private final int limit;
        private int heapSize;
        private int[] arr;

        public MyMaxHeap(int limit) {
            this.limit = limit;
            arr = new int[limit];
            heapSize = 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        //放入一个数 并且保持大根堆
        public void push(int value) {
            if (heapSize == limit) {
                throw new RuntimeException("heap is full");
            }
            arr[heapSize] = value;
            heapInsert(arr, heapSize++);
        }

        //新插入的值 往上看  是否需要调换
        private void heapInsert(int[] arr, int index) {
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        //用户需要弹出最大的值，并保持大根堆的结构
        public int pop() {

            int ans = arr[0];

            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);

            return ans;
        }

        //往下看
        private void heapify(int[] arr, int index, int heapSize) {
            int left = 2 * index + 1;
            while (left < heapSize) {
                //判断左右孩子哪个大
                int max = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
                //判断孩子和当前节点哪个大
                max = arr[max] > arr[index] ? max : index;
                if (max == index) {
                    break;
                }
                swap(arr, max, index);
                index = max;
                left = 2 * index + 1;
            }
        }

        private void swap(int[] arr, int index, int f) {
            int temp = arr[index];
            arr[index] = arr[f];
            arr[f] = temp;
        }
    }

    public static class RightMaxHeap {
        private int[] arr;
        private final int limit;
        private int size;

        public RightMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("heap is full");
            }
            arr[size++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = arr[maxIndex];
            arr[maxIndex] = arr[--size];
            return ans;
        }

    }

    public static void main(String[] args) {
        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyMaxHeap my = new MyMaxHeap(curLimit);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops!");
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.push(curValue);
                } else if (my.isFull()) {
                    if (my.pop() != test.pop()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else {
                        if (my.pop() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");

    }
}
