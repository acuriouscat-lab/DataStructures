package JVM.test;

/**
 * @author Administrator
 * @Description
 * @create 2021-08-14 17:39
 */
public class Test2 {
    public static void main(String[] args) {
        Father f = new Son();
        System.out.println(f.x);
    }


}

class Father {
    int x = 10;

    public Father() {
        this.print();
        x = 20;
    }

    public void print() {
        System.out.println("Father.x = " + x);
    }
}

class Son extends Father {
    int x = 30;

    public Son() {
        this.print();
        x = 40;
    }

    public void print() {
        System.out.println("Son.x = " + x);
    }
}