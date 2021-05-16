package guigu.SingleLinkedList;

/*
        Josephu(约瑟夫、约瑟夫环)问题
                Josephu问题为: 设编号为1, 2，... n的n个人围坐-一圈，约定编号为k (1<=k<=n)的人从1开始报数，数
                到m的那个人出列，它的下一位又从1开始报数，数到m的那个人又出列，依次类推，直到所有人出列为止，由
                此产生-一个出队编号的序列。

 */
public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList cs = new CircleSingleLinkedList();

        cs.addBoy(125);
        cs.showBoy();

        cs.countBoy(10, 20, 125);
    }
}


//创建一个环形单向链表
class CircleSingleLinkedList {

    //创建一个first节点，当前没有编号
    private Boy first = null;

    //添加小孩结点，构建成一个环形链表
    public void addBoy(int nums) {
        //判断输入数据
        if (nums < 1) {
            System.out.println("你的输入有误");
            return;
        }
        //定义一个辅助节点
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩结点
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first); //构成环
                curBoy = first; //指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }

    }

    //遍历当前环形链表
    public void showBoy() {
        Boy boy = first;
        if (first == null) {
            System.out.println("没有任何小孩");
            return;
        }
        while (true) {
            System.out.println("小孩的编号为：" + boy.getNo());
            if (boy.getNext() == first) {
                break;
            }
            boy = boy.getNext();
        }
    }

    //根据用户输入，计算出小孩出圈的顺序

    /**
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 一次数几下
     * @param num      表示最初有几个小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int num) {
        if (countNum < 1 || num < 1 || startNo > num) {
            System.out.println("输入有误");
            return;
        }

        //得到最后一个节点helper 辅助节点
        Boy helper = first;
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }

        //让first和helper走startNo-1步
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        while (first != helper) {
            //报数 并移动
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println("出圈的小孩编号是：" + first.getNo());
            //出圈
            first = first.getNext();
            helper.setNext(first);

        }
        System.out.println("出圈的小孩编号是：" + first.getNo());
    }
}

//创建一个BOy类，表示一个结点
class Boy {

    private int no;
    private Boy next; //指向下一个节点

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}