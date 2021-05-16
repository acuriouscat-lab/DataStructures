package guigu.SingleLinkedList;

/**
 * 双向链表
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList dll = new DoubleLinkedList();
//        HeroNode2 hero1 = new HeroNode2(1, "宋江1", "及时雨");
//        HeroNode2 hero2 = new HeroNode2(3, "卢俊义1", "玉麒麟");
//        HeroNode2 hero3 = new HeroNode2(5, "吴用1", "智多星");
//        HeroNode2 hero4 = new HeroNode2(7, "林冲1", "豹子头");
//
//        dll.addData(hero1);
//        dll.addData(hero3);
//        dll.addData(hero2);
//        dll.addData(hero4);
//        System.out.println("添加功能");
//        dll.showList();
//
//        System.out.println("--------");
//        HeroNode2 hero5 = new HeroNode2(7, "林冲2", "豹子头");
//        dll.updata(hero5);
//        System.out.println("修改功能");
//        dll.showList();
//        System.out.println("--------");
//        dll.delete(3);
//        System.out.println("删除功能");
//        dll.showList();
//        System.out.println("--------");
        System.out.println("按照编号添加");
        HeroNode2 hero9 = new HeroNode2(2, "宋江2", "及时雨");
        HeroNode2 hero6 = new HeroNode2(4, "卢俊义2", "玉麒麟");
        HeroNode2 hero7 = new HeroNode2(6, "吴用2", "智多星");
        HeroNode2 hero8 = new HeroNode2(8, "林冲2", "豹子头");
        HeroNode2 hero1 = new HeroNode2(1, "宋江1", "及时雨");
        HeroNode2 hero2 = new HeroNode2(3, "卢俊义1", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(5, "吴用1", "智多星");
        HeroNode2 hero4 = new HeroNode2(7, "林冲1", "豹子头");
        dll.addByOrder(hero8);
        dll.addByOrder(hero7);
        dll.addByOrder(hero9);
        dll.addByOrder(hero6);
        dll.addByOrder(hero4);
        dll.addByOrder(hero3);
        dll.addByOrder(hero2);
        dll.addByOrder(hero1);

        dll.showList();
    }
}

class DoubleLinkedList{
    //初始化一个头节点，头节点不存放数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    //添加节点到单向链表
    public void addData(HeroNode2 heroNode) {
        //因为头节点不能动，所以我们需要一个辅助变量temp
        HeroNode2 temp = head;
        while (true) {
            //判断是否为最后一个节点
            if (temp.next == null) {
                break;
            }else{
                temp = temp.next;
            }
        }
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //修改节点信息，根据No信息来修改
    public void updata(HeroNode2 newHeroNode) {
        //定义一个临时变量
        HeroNode2 temp = head.next;
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

    //根据编号进行删除节点
    public void delete(int no) {
        //头节点不能动，所以需要用一个temp辅助节点找到待删除节点的前一个节点
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
            temp.pre.next = temp.next;
        }else{
            System.out.println("请输入正确的编号");
        }
    }

    //遍历双向链表
    public void showList(){
        if (head.next == null) {
            System.out.println("该链表为空");
            return;
        }
        //建立一个辅助变量
        HeroNode2 temp = head.next;

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

    //按照编号插入元素到链表,如果有则添加失败，并给出提示
    public void addByOrder(HeroNode2 heroNode) {
        //头节点不能动 定义一个临时变量替代

        HeroNode2 temp =head;
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
            if (temp.next != null) {
                temp.next.pre=heroNode;
            }
            heroNode.pre = temp;
            heroNode.next = temp.next;
            temp.next = heroNode;
        }else{
            System.out.println("当前编号人物已存在");
        }
    }
}

class  HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; //指向下一个结点
    public HeroNode2 pre;  //指向上一个结点
    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

}