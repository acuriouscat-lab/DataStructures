package guigu.Stack;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack as = new ArrayStack(5);

        as.show();

        as.push(5);
        as.push(3);
        as.push(7);
        as.push(8);
        as.push(10);
        System.out.println("_---------");
        as.show();

        System.out.println(as.pop());
        System.out.println(as.pop());
        System.out.println(as.pop());

        System.out.println("_---------");
        as.show();
    }
}

class ArrayStack{

    //用数组模拟栈的结构
    private int MaxSize;    //栈的大小
    private int[] stack;    //用数组模拟栈的机构
    private int top = -1;   //栈顶的指针  默认为-1

    //通过栈的大小来初始化
    public ArrayStack(int maxSize) {
        this.MaxSize = maxSize;
        stack = new int[this.MaxSize];
    }

    //判断栈满
    public boolean isFull(){
        return top == this.MaxSize - 1;
    }
    //判断栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //模拟栈的添加元素功能    入栈
    public void push(int data) {
        if (isFull()) {
            System.out.println("栈已经满了无法添加");
            return;
        }
        top++;
        stack[top] = data;
    }

    //模拟栈的删除元素功能    出栈
    public int pop() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据了");
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈中的数据
    //从栈顶遍历数据到栈底
    public void show(){
        for (int i = top ; i >= 0; i--) {
            System.out.println("stack[" + i + "] = " + stack[i]);
        }
    }
}