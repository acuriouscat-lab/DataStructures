package algorithmlearning.class03;

import java.util.Stack;

public class Code05_GetMinStack {
    //实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
    //1）pop、push、getMin操作的时间复杂度都是 O(1)。
    //2）设计的栈类型可以使用现成的栈结构。
    public static class MyStack1{
        public Stack<Integer> stackData;
        public Stack<Integer> stackMin;
        public MyStack1(){
            stackData = new Stack<>();
            stackMin = new Stack<>();
        }

        public void push(int value) {
            if (stackMin.isEmpty()) {
                stackMin.push(value);
            }else if (stackMin.peek() >= value) {
                    stackMin.push(value);
                }
            stackData.push(value);

        }

        public int pop(){
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            int value = this.stackData.pop();
            if (value == this.getMin()) {
                this.stackMin.pop();
            }
            return value;

        }

        public int getMin(){
            if (stackMin.isEmpty()) {
                throw new RuntimeException("is empty");
            }
            return stackMin.peek();
        }
    }

    public static class MyStack2 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack2() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        public void push(int newNum) {
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(newNum);
            } else if (newNum < this.getmin()) {
                this.stackMin.push(newNum);
            } else {
                int newMin = this.stackMin.peek();
                this.stackMin.push(newMin);
            }
            this.stackData.push(newNum);
        }

        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            this.stackMin.pop();
            return this.stackData.pop();
        }

        public int getmin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }
    }
    public static void main(String[] args) {
        MyStack1 stack1 = new MyStack1();
        stack1.push(3);
        System.out.println(stack1.getMin());
        stack1.push(4);
        System.out.println(stack1.getMin());
        stack1.push(1);
        System.out.println(stack1.getMin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getMin());

        System.out.println("=============");

        MyStack1 stack2 = new MyStack1();
        stack2.push(3);
        System.out.println(stack2.getMin());
        stack2.push(4);
        System.out.println(stack2.getMin());
        stack2.push(1);
        System.out.println(stack2.getMin());
        System.out.println(stack2.pop());
        System.out.println(stack2.getMin());
    }



}
