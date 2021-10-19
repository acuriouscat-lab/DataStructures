package LeetCodeTest;

public class Code2Test {


    public static void main(String[] args) {
        System.out.println(NS_LIS("20"));
        System.out.println(NS_LIS("199"));
        System.out.println(NS_LIS("110328324"));
    }

    public static int NS_LIS (String n) {
        // write code here
        if(n == null || n.length() == 0){return 0;}
        if(n.length() == 1){
            return Integer.valueOf(n);
        }
        int res = 8;
        int len = n.length();
        boolean isAll = true;
        for(int i = 0; i < len; i++){
            if(n.charAt(i) != '9'){
                isAll = false;
                break;
            }
        }
        if(isAll){
            return 8 + ((len - 1) * 9) + 1;
        }else{
            return (8 + ((len - 2) * 9)) + n.charAt(0) - '0';
        }
    }

}
