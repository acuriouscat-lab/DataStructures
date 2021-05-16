package guigu.SingleLinkedList;
/**
 *  单向链表
 */

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        HeroNode hero1 = new HeroNode(1, "宋江1", "及时雨");
        HeroNode hero2 = new HeroNode(3, "卢俊义1", "玉麒麟");
        HeroNode hero3 = new HeroNode(5, "吴用1", "智多星");
        HeroNode hero4 = new HeroNode(7, "林冲1", "豹子头");


        SingleLinkedList sl = new SingleLinkedList();
//        sl.addData(hero1);
//        sl.addData(hero4);
//        sl.addData(hero2);
//        sl.addData(hero3);
        sl.addByOrder(hero1);
        sl.addByOrder(hero4);
        sl.addByOrder(hero3);
        sl.addByOrder(hero2);
        sl.showList();
        System.out.println("---------------");

        HeroNode hero5 = new HeroNode(2, "宋江2", "及时雨");
        HeroNode hero6 = new HeroNode(4, "卢俊义2", "玉麒麟");
        HeroNode hero7 = new HeroNode(6, "吴用2", "智多星");
        HeroNode hero8 = new HeroNode(8, "林冲2", "豹子头");
        SingleLinkedList s2 = new SingleLinkedList();
        s2.addByOrder(hero5);
        s2.addByOrder(hero6);
        s2.addByOrder(hero7);
        s2.addByOrder(hero8);
        s2.showList();
        System.out.println("---------------");
        HeroNode heroNode = conbineList(sl.getHead(), s2.getHead());
        SingleLinkedList s3 = new SingleLinkedList();
        s3.addData(heroNode);
        s3.showList();
        //list.showList();
        //sl.reverse(sl.getHead());
        //reversePrint(sl.getHead());
//        System.out.println("---------------");
//        HeroNode hero5 = new HeroNode(4, "林冲冲", "豹子头头");
//        sl.updata(hero5);
//
//        sl.showList();
//        System.out.println("---------------");
//
//        sl.delete(3);
//        sl.delete(6);
//        sl.showList();

//        System.out.println(getLength(sl.getHead()));
//        System.out.println("---------------");
//        System.out.println(getNode(sl.getHead(),3));
    }
    //利用栈的数据结构，先进后出的特点，实现逆序打印的效果
    public static void reversePrint(HeroNode heroNode) {
        if (heroNode == null) {
            return;
        }

        Stack<HeroNode> hn = new Stack<HeroNode>();

        HeroNode temp = heroNode.next;
        //入栈
        while (temp != null) {
            hn.push(temp);
            temp = temp.next;
        }

        while (hn.size() > 0) {
            System.out.println(hn.pop());
        }
    }

    //合并两个有序的单链表，合并之后的链表依然有序
    public static HeroNode conbineList(HeroNode h1, HeroNode h2) {
        //创建一个新的HeroNOde接受
        HeroNode newhero = new HeroNode(0, "", "");
//        HeroNode temp1 = h1.next;
//        HeroNode temp2 = h2.next;
      /*  while (h1.next != null) {
            if (h1.next.no < h2.next.no) {
                HeroNode cur = h1.next;
                cur.next = newhero.next;
                newhero.next = cur;
                h1.next = h1.next.next;
            } else if (h1.next.no > h2.next.no) {
                HeroNode cur = h2.next;
                cur.next = newhero.next;
                newhero.next = cur;
                h2.next= h2.next.next;
            }
        }*/
        HeroNode h1Node = h1.next;
        HeroNode h2Node = h2.next;
        HeroNode temp = newhero;
        while (h1Node != null && h2Node != null) {
            if (h1Node.no < h2Node.no) {
                temp.next = h1Node;
                temp = temp.next;
                h1Node = h1Node.next;
            }else if (h1Node.no > h2Node.no) {
                temp.next = h2Node;
                temp = temp.next;
                h2Node = h2Node.next;
            }
        }
        while (h1Node != null) {
            temp.next = h1Node;
            temp = temp.next;
            h1Node = h1Node.next;
        }
        while (h2Node != null) {
            temp.next = h2Node;
            temp = temp.next;
            h2Node = h2Node.next;
        }
        return newhero;
    }
}

class SingleLinkedList{

    //初始化一个头节点，头节点不存放数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }
    //添加节点到单向链表
    public void addData(HeroNode heroNode) {

        //因为头节点不能动，所以我们需要一个辅助变量temp
        HeroNode temp = head;
        while (true) {
            //判断是否为最后一个节点
            if (temp.next == null) {
                break;
            }else{
                temp = temp.next;
            }
        }
        temp.next = heroNode;
    }

    //按照编号插入元素到链表,如果有则添加失败，并给出提示    与辅助节点的下一个节点比较  先找到位置 再插入数据
    public void addByOrder(HeroNode heroNode) {
        //头节点不能动 定义一个临时变量替代
        HeroNode temp =head;
        boolean flag = false;
        //先找位置
        while (true) {
            if (temp.next == null) { //説明已經到了最後
                break;
            } else if (temp.next.no > heroNode.no) {//位置找到了
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //增加数据  结束循环后在temp的后面添加元素
        if (!flag) {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }else{
            System.out.println("当前编号人物已存在");
        }

    }

    //修改节点信息，根据No信息来修改
    public void updata(HeroNode newHeroNode) {
        //定义一个临时变量
        HeroNode temp = head.next;
        boolean flag = false; //判断是否找到、
        //先找是否存在
        while (true) {
            //判断是否为空
            if (temp == null) {
                break;
            } else if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        //根据查找来输出
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else{
            System.out.println("不存在该编号");
        }

    }
    //根据编号进行删除节点        与辅助节点的下一个节点比较
    public void delete(int no) {
        //头节点不能动，所以需要用一个temp辅助节点找到待删除节点的前一个节点
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.next = temp.next.next;
        }else{
            System.out.println("请输入正确的编号");
        }
    }
    //遍历单向链表
    public void showList(){
        if (head.next == null) {
            System.out.println("该链表为空");
            return;
        }
        //建立一个辅助变量
        HeroNode temp = head.next;

        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
//        while (true) {
//            if (temp == null) {
//                break;
//            }
//            System.out.println(temp);
//            temp = temp.next;
//        }
    }

    //获取单链表中有效节点的个数
    public static int getLength(HeroNode head){
        int count = 0;
        if (head.next == null) {
            System.out.println("该链表为空");
            return 0;
        }
        HeroNode cur = head.next;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    //查找单链表中的倒数第k个结点
    public static HeroNode getNode(HeroNode head, int index) {
        //判断是否为空链表
        if (head.next == null) {
            System.out.println("该链表为空");
            return null;
        }
        //遍历链表获得链表的长度Length
        HeroNode temp = head.next;
        int length = 0;//长度
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        //判断长度是否合理
        if (index <= 0 || index > length) {
            return null;
        }
        temp = head.next;
        //再次遍历Length-index个得到该结点
        for (int i = 0; i < (length - index); i++) {
            temp = temp.next;
        }
        return temp;
    }


    //单链表的反转
    public static void reverse(HeroNode head) {
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个新的HeroNode接受原来的HeroNode
        HeroNode newHero = new HeroNode(0, "", "");
        HeroNode cur = head.next;
        HeroNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = newHero.next;
            newHero.next = cur;
            cur = next;
        }
        head.next = newHero.next;
    }
    }

class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}