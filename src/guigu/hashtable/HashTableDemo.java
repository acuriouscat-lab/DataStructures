package guigu.hashtable;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);

        String key = "";
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("del: 删除雇员");
            System.out.println("exit: 退出系统");

            key = sc.next();
            switch (key) {
                case "add":
                    System.out.println("请输入id:");
                    int id = sc.nextInt();
                    System.out.println("请输入名字:");
                    String name = sc.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.print("请输入id:");
                    id = sc.nextInt();
                    System.out.println(hashTab.findEmpByID(id));
                    break;
                case "del":
                    System.out.println("请输入id:");
                    id = sc.nextInt();
                    hashTab.del(id);
                    break;
                case "exit":
                    sc.close();
                    System.exit(0);
            }
        }
    }
}

class HashTab{
    public EmpLinkedList[] empLinkedLists;
    public int size;

    public HashTab(int size) {
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        for (int i = 0; i < empLinkedLists.length; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    //增加员工的操作
    public void add(Emp emp) {
        int fun = hasFun(emp.id);
        empLinkedLists[fun].add(emp);
    }
    //遍历员工
    public void list(){
        for (int i = 0; i < empLinkedLists.length; i++) {
            if (empLinkedLists[i].head != null) {
                empLinkedLists[i].list(i);
            }
        }
    }

    //根据id查找雇员
    public Emp findEmpByID(int id) {
        int  fun = hasFun(id);
        return empLinkedLists[fun].findEmpById(id);
    }

    //编写一个散列函数，使用一个简单取模法
    public int hasFun(int id) {
        return id % size;
    }

    //删除雇员
    public void del(int id) {
        int i = hasFun(id);
        boolean b = empLinkedLists[i].delById(id);
        if (b) {
            System.out.println("删除成功");
        }else{
            System.out.println("输入有误");
        }
    }
}

class Emp{
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class EmpLinkedList{

    public Emp head;

    public void list(int no){
        if (head == null) {
            System.out.println("当前" + (no + 1) + "序列没有员工");
            return;
        }
        System.out.println("当前第"+(no+1)+"条序列的信息为：");
        Emp curEmp = head;
        while (true) {
            if (curEmp == null) {
                break;
            }
            System.out.printf("员工的id为：%d,姓名为：%s\t\n", curEmp.id, curEmp.name);
            curEmp = curEmp.next;
        }
    }

    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp curEmp = head;

        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }
    //查找雇员
    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("不存在该用户信息");
            return null;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp == null) {
                System.out.println("不存在该用户信息");
                return null;
            }
            if (curEmp.id == id) {
                return curEmp;
            }
            curEmp = curEmp.next;
        }
    }

    //删除雇员
    public boolean delById(int id) {
        if (head == null) {
            System.out.println("没有该ID");
            return false;
        }
        if (head.id == id) {
            head = null;
            return true;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            if (curEmp.next.id == id) {
                curEmp.next = curEmp.next.next;
                return true;
            }
            curEmp = curEmp.next;
        }
        return false;
    }
}
