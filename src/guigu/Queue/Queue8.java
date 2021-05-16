package guigu.Queue;

public class Queue8 {
    //定义一个max表示工有多少个皇后
    int max = 8;

    int[] arr = new int[max];

    static int count = 0;
    static int judgecount = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("一共有" + count + "种解放");
        System.out.println("一共查找了" + judgecount);
    }

    //判断功能
    public boolean judge(int n) {
        judgecount++;
        for (int i = 0; i < n; i++) {
            //判断第n个皇后是否和前n-1个皇后同列或者在同一斜线上
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    //放置皇后功能
    public void check(int n) {
        if (n == max) {
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            arr[n] = i;
            if (judge(n)) {
                check(n + 1);
            }
        }
    }

    //输出放置的位置输出
    public void print(){
        count++;
        for (int i = 0; i < arr.length; i++) {

            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
}
