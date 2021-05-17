package greatoffer.class02;

import java.util.HashMap;

public class Code03_ReceiveAndPrintOrderLine {

    //已知一个消息流会不断地吐出整数 1~N，
    //但不一定按照顺序依次吐出
    //如果上次打印的序号为i， 那么当i+1出现时
    //请打印 i+1 及其之后接收过的并且连续的所有数
    //直到1~N全部接收并打印完
    //请设计这种接收并打印的结构

    public static class Node{
        String info;
        Node next;
        public Node(String s){
            info = s;
        }
    }

    public static class MessageBox{
        private HashMap<Integer,Node> headMap;
        private HashMap<Integer,Node> tailMap;
        private int waitIndex;

        public MessageBox(){
            headMap = new HashMap<>();
            tailMap = new HashMap<>();
            waitIndex = 1;
        }

        public void receive(Integer index,String info){
            if(index < 1){
                return;
            }
            Node cur = new Node(info);
            headMap.put(index, cur);
            tailMap.put(index, cur);

            if (tailMap.containsKey(index - 1)) {
                Node node = tailMap.get(index - 1);
                node.next = cur;
                tailMap.remove(index - 1);
                headMap.remove(index);
            }
            if (headMap.containsKey(index + 1)) {
                Node node = headMap.get(index + 1);
                cur.next = node;
                headMap.remove(index + 1);
                tailMap.remove(index);
            }

            if (waitIndex == index) {
                print();
            }
        }

        public void print(){
            Node node = headMap.get(waitIndex);
            headMap.remove(waitIndex);

            while(node != null){
                System.out.print(node.info + "  ");
                node = node.next;
                waitIndex++;
            }
            tailMap.remove(waitIndex - 1);
            System.out.println();
        }


    }

    public static void main(String[] args) {
        // MessageBox only receive 1~N
        MessageBox box = new MessageBox();
        // 1....
        System.out.println("这是2来到的时候");
        box.receive(2,"B"); // - 2"
        System.out.println("这是1来到的时候");
        box.receive(1,"A"); // 1 2 -> print, trigger is 1
        box.receive(4,"D"); // - 4
        box.receive(5,"E"); // - 4 5
        box.receive(7,"G"); // - 4 5 - 7
        box.receive(8,"H"); // - 4 5 - 7 8
        box.receive(6,"F"); // - 4 5 6 7 8
        box.receive(3,"C"); // 3 4 5 6 7 8 -> print, trigger is 3
        box.receive(9,"I"); // 9 -> print, trigger is 9
        box.receive(10,"J"); // 10 -> print, trigger is 10
        box.receive(12,"L"); // - 12
        box.receive(13,"M"); // - 12 13
        box.receive(11,"K"); // 11 12 13 -> print, trigger is 11

    }

}
