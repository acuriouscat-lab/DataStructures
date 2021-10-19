package LeetCodeTest;

public class Code1 {

    public static void main(String[] args) {
        System.out.println(NNAplusB(9, 9));
    }

    public static long NNAplusB (int a, int b) {
        // write code here
        StringBuilder aStr = new StringBuilder();
        StringBuilder bStr = new StringBuilder();
        int tempa = a;
        while(a != 0){
            bStr.append(b + "");
            a--;
        }
        while(b != 0){
            aStr.append(tempa + "");
            b--;
        }
        long la = Long.valueOf(aStr.toString());
        System.out.println(la);
        long lb = Long.valueOf(bStr.toString());
        System.out.println(lb);
        return la + lb;
    }

}
