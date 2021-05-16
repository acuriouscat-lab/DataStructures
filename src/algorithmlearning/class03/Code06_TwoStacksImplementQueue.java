package algorithmlearning.class03;

import java.util.Stack;

public class Code06_TwoStacksImplementQueue {
    //1）如何用栈结构实现队列结构
    public static class TwoStacksQueue{
        //压入栈和弹出栈
        public Stack<Integer> stackPush;
        public Stack<Integer> stackPop;

        public TwoStacksQueue(){
            stackPush = new Stack<>();
            stackPop = new Stack<>();
        }

        public void add(int value) {
            stackPush.push(value);
            pushToPop();
        }

        public int poll(){
            if (stackPop.isEmpty() && stackPush.isEmpty()) {
                throw new RuntimeException("empty");
            }
            pushToPop();
            return stackPop.pop();
        }

        public void pushToPop(){
            if (stackPop.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }
        public int peek(){
            if (stackPop.isEmpty() && stackPush.isEmpty()) {
                throw new RuntimeException("empty");
            }
            pushToPop();
            return stackPop.peek();
        }

    }
    public static void main(String[] args) {
        TwoStacksQueue test = new TwoStacksQueue();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }
}
