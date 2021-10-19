package LeetCodeTest;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Administrator
 * @Description
 * @create 2021-07-29 19:56
 */
public class NowCode3 {


    public static void main(String[] args){
        int n = 4;
        int m = 5;
        int k = 5;

        boolean find = false;
        int ans = -1;
        if(k == 1){
            System.out.println(1);
        }else{
            char[][] matrix = new char[n][m];
            matrix[0] = new char[]{'a', 'a', 'b', 'c','d'};
            matrix[1] = new char[]{'a', 'a', 'a', 'a','a'};
            matrix[2] = new char[]{'a', 'x', 'a', 'z','x'};
            matrix[3] = new char[]{'a', 'b', 'c', 'd', 'q'};
            int min = Math.min(m,n);
            for(int i = 2; i <= min; i++){
                if(find){
                    break;
                }
                for(int x = 0; x <= n - i; x++){
                    for(int y = 0; y <= m - i; y++){
                        if(check(matrix,x,y,k,i)){
                            find = true;
                            ans = i;
                            break;
                        }
                    }
                }
            }
            System.out.println(ans);
        }
    }

    public static boolean check(char[][] matrix,int x,int y,int k,int len){
        Set<Character> set = new HashSet<>();
        for(int i = x; i < x + len; i++){
            for(int j = y; j < y + len; j++){
                set.add(matrix[i][j]);
                if(set.size() == k){
                    return true;
                }
            }
        }
        return false;
    }


}
