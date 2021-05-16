package algorithmlearning.class10;

import java.util.HashSet;
import java.util.Stack;

public class Code03_DFS {
    //深度优先遍历
    //1，利用栈实现
    //2，从源节点开始把节点按照深度放入栈，然后弹出
    //3，每弹出一个点，把该节点以及下一个没有进过栈的邻接点放入栈
    //4，直到栈变空
    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();//注册表
        stack.push(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}
