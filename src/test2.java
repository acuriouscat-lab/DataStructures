import java.util.ArrayList;
import java.util.HashMap;

public class test2 {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));

//        HashMap<Character, Integer> map = new HashMap<>();
//        map.containsKey()

    }


        public static String longestPalindrome(String s) {
            if(s == null || s.length() == 0) return "";
            char[] arr = manacherString(s);
            int C = -1;
            int R = -1;
            int maxR = -1;
            int start = -1;
            int[] pArr = new int[arr.length];
            for(int i = 0 ;i < arr.length; i++){
                pArr[i] = R > i ? Math.min(R-i,pArr[2*C-i]):1;
                while(i + pArr[i] < arr.length && i - pArr[i] >=0){
                    if(arr[i + pArr[i]] == arr[i - pArr[i]]){
                        pArr[i]++;
                    }else{
                        break;
                    }
                }
                if( R < i + pArr[i]){
                    R = i + pArr[i];
                    C = i;
                }
                if( pArr[i] > maxR){
                    maxR = pArr[i];
                    start = (i - maxR +1) /2;
                }
            }
            return s.substring(start,start+maxR-1);
        }
        public static char[] manacherString(String str){
            int size = str.length();
            char[] res = new char[size*2 +1];
            int index = 0;
            for(int i = 0; i < res.length; i++){
                if((i&1) != 0 ){
                    res[i] = str.charAt(index++);
                }else{
                    res[i] = '#';
                }
            }
            return res;
        }

}
