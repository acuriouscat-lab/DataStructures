package greatoffer.class03;

import java.util.*;

public class Code08_DistanceKNodes {


    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    // 找到离指定节点 k 距离的节点
    public static List<Node> distanceKNodes(Node root, Node target, int K) {

        HashMap<Node, Node> parentMap = new HashMap<>();
        parentMap.put(root, null);
        createParentMap(root, parentMap);
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> visited = new HashSet<>();
        queue.offer(target);
        visited.add(target);
        int level = 0;
        List<Node> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (level == K) {
                    ans.add(node);
                }
                if (node.left != null && !visited.contains(node.left)) {
                    queue.offer(node.left);
                    visited.add(node.left);
                }
                if (node.right != null && !visited.contains(node.right)) {
                    queue.offer(node.right);
                    visited.add(node.right);
                }
                if (parentMap.get(node) != null && !visited.contains(parentMap.get(node))) {
                    queue.offer(parentMap.get(node));
                    visited.add(parentMap.get(node));
                }
            }
            level++;
            if (level > K) {
                break;
            }
        }
        return ans;
    }

    public static void createParentMap(Node node, HashMap<Node,Node> parentMap){
        if(node == null){
            return;
        }
        if (node.left != null) {
            parentMap.put(node.left, node);
            createParentMap(node.left, parentMap);
        }
        if (node.right != null) {
            parentMap.put(node.right, node);
            createParentMap(node.right, parentMap);
        }
    }

    public static List<Node> distanceKNodes2(Node root, Node target, int K) {
        HashMap<Node, Node> parents = new HashMap<>();
        parents.put(root, null);
        createParentMap(root, parents);
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> visited = new HashSet<>();
        queue.offer(target);
        visited.add(target);
        int curLevel = 0;
        List<Node> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Node cur = queue.poll();
                if (curLevel == K) {
                    ans.add(cur);
                }
                if (cur.left != null && !visited.contains(cur.left)) {
                    visited.add(cur.left);
                    queue.offer(cur.left);
                }
                if (cur.right != null && !visited.contains(cur.right)) {
                    visited.add(cur.right);
                    queue.offer(cur.right);
                }
                if (parents.get(cur) != null && !visited.contains(parents.get(cur))) {
                    visited.add(parents.get(cur));
                    queue.offer(parents.get(cur));
                }
            }
            curLevel++;
            if (curLevel > K) {
                break;
            }
        }
        return ans;
    }



    public static void main(String[] args) {
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);

        n3.left = n5;
        n3.right = n1;
        n5.left = n6;
        n5.right = n2;
        n1.left = n0;
        n1.right = n8;
        n2.left = n7;
        n2.right = n4;

        Node root = n3;
        Node target = n4;
        int K = 3;

        List<Node> ans = distanceKNodes(root, target, K);
        List<Node> res = distanceKNodes2(root, target, K);
        for (Node o1 : ans) {
            System.out.print(o1.value + "  ");
        }
        System.out.println();
        for (Node o1 : res) {
            System.out.print(o1.value + " ");
        }

    }

}
