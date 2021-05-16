package guigu.Queue;
/**
 *  队列：有序列表 先入先出
 *
 *      单向队列
 */

import java.util.Scanner;

public class QueueArrayDemo {

    private int rear;
    private int front;
    private int[] arr;
    private int maxSize;

    public QueueArrayDemo(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1; //指向队列头，指向队列头的前一个位置
        rear = -1;  //指向队列尾， 指向队列尾的数据
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return front == rear;
    }

    //判断队列是否为满
    public boolean isFull(){
        return rear == maxSize - 1;
    }

    //添加数据到队列
    public void addData(int data) {
        if (!isFull()) {
            rear++;
            arr[rear] = data;
        }else{
            System.out.println("数组已经满了");
            return;
        }
    }

    //从列队中出去
    public int getData() {
        if (isEmpty()) {
            throw new  RuntimeException("数组已经为空了");
        }
        return arr[++front];
    }

    //遍历数组
    public void showQueue(){
        if (isEmpty()) {
            System.out.println("数组为空");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    //显示队列头数据
    public int headData(){
        if (isEmpty()) {
            throw new RuntimeException("数据为空");
        }
        return arr[front + 1];
    }
    public static void main(String[] args) {
        QueueArrayDemo qa = new QueueArrayDemo(10);

        //定义一个界面接受选择

        Scanner sc = new Scanner(System.in);

        boolean loop = true;
        while (loop) {

            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据");
            System.out.println("g(get):获取数据");
            System.out.println("h(head):查看队列头数据");

            char s = sc.next().charAt(0);


            switch (s) {
                case 's':
                    qa.showQueue();
                    break;
                case 'e':
                    sc.close();
                    loop = false;
                    break;
                case 'a':
                    int value = sc.nextInt();

                    qa.addData(value);
                    break;
                case 'g':
                    try {
                        System.out.println(qa.getData());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.println(qa.headData());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                 default:
                     break;
            }

        }
        System.out.println("程序退出了");

    }
}
