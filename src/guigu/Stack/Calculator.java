package guigu.Stack;

public class Calculator {

    public static void main(String[] args) {
        String expression = "12*2*2-25+7-4";

        ArrayStack2 operStack = new ArrayStack2(10);
        ArrayStack2 numStack = new ArrayStack2(10);
        
        //
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        int index = 0;
        String sb = "";  //用于拼接多位数
        while (index < expression.length()) {
            char ch = expression.substring(index, index + 1).charAt(0);
            if (operStack.isOper(ch)) {
                if (operStack.isEmpty()) {
                    operStack.push(ch);
                } else {
                    if (operStack.operPriority(ch) <= operStack.operPriority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                }
            } else {
                //numStack.push(ch);
                sb += ch;
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(sb));
                } else if (numStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                    numStack.push(Integer.parseInt(sb));
                    sb = "";
                }
            }
            index++;
        }

        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();

            res = numStack.cal(num1, num2, oper);

            numStack.push(res);
        }
        res = numStack.pop();
        System.out.println("最后的结果是：" + res);

    }
}
class ArrayStack2{

    //用数组模拟栈的结构
    private int MaxSize;    //栈的大小
    private int[] stack;    //用数组模拟栈的机构
    private int top = -1;   //栈顶的指针  默认为-1
    //判断是否是符号
    public boolean isOper(int oper){
        return oper == '+' || oper == '-' || oper == '*' || oper == '/';
    }

    //定义操作符的优先级
    public int operPriority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        }else{
            return -1;
        }
    }

    //得到栈顶的数
    public int peek(){
        return stack[top];
    }


    //通过栈的大小来初始化
    public ArrayStack2(int maxSize) {
        this.MaxSize = maxSize;
        stack = new int[this.MaxSize];
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
                default:
                    break;
        }
        return res;
    }

    //判断栈满
    public boolean isFull() {
        return top == this.MaxSize - 1;
    }

    //判断栈空
    public boolean isEmpty() {
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
    public void show() {
        for (int i = top; i >= 0; i--) {
            System.out.println("stack[" + i + "] = " + stack[i]);
        }
    }
}
