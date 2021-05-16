package guigu.tenalgorithm;

public class DivideAndConquer {
    public static void main(String[] args) {
        hanoiTower(3, 'A', 'B', 'C');
    }

    //汉罗塔的移动方法
    //使用分治算法
    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("the on   e from " + a + " -> " + c);
        }else{
            //如果我们有n>=2的情况 1最下面的一个盘 2上面的盘
            //先把 最上面的所有盘A->B 移动过程会使用到c
            hanoiTower(num - 1, a, c, b);
            //再把最下边的盘 A->C
            System.out.println("the " + num + " pan from " + a + " -> " + c);
            //B -> C
            hanoiTower(num - 1, b, a, c);
        }
    }
}


