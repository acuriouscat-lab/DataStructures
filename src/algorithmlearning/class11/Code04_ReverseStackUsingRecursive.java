package algorithmlearning.class11;

import java.util.Stack;

public class Code04_ReverseStackUsingRecursive {


    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        // 每次将栈底的元素弹出 并递归 直到没有元素
        int l = f(stack);
        reverse(stack);
        stack.push(l);
    }

    //把栈底弹出返回 其他的正常放下
    public static int f(Stack<Integer> stack) {
        Integer result = stack.pop();
        // 如果是最后一个则不压入 直接返回
        if (stack.isEmpty()) {
            return result;
        }else {
            // 不是最后一个则递归调用 并把当前弹出的重新压入
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }

    }
}
