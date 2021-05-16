package greatoffer.class01;

public class Code03_Near2Power {

    //给定一个非负整数num，
    //如何不用循环语句，
    //返回>=num，并且离num最近的，2的某次方

    public static int tableSizeFor(int n) {
        // 为了防止一开始就是 2 的某次幂
        n--;
        // 将 n的最高位二进制之后的数都填满
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        /////
        return (n < 0) ? 1 : (n + 1);
    }

    public static void main(String[] args) {
        int cap = 120;
        System.out.println(tableSizeFor(cap));
    }


}
