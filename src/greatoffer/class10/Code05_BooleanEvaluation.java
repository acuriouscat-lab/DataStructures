package greatoffer.class10;

public class Code05_BooleanEvaluation {

    //https://leetcode-cn.com/problems/boolean-evaluation-lcci/
    //给定一个布尔表达式和一个期望的布尔结果 result，布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成。
    // 实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。
    public int countEval(String s, int result) {
        if(s == null || s.equals("")){
            return 0;
        }
        char[] exp = s.toCharArray();
        int N = exp.length;
        Info[][] dp = new Info[N][N];
        Info info = process(exp,0,N - 1,dp);
        return result == 1 ? info.tn : info.fn;
    }


    public Info process(char[] exp,int L,int R,Info[][] dp){
        if(dp[L][R] != null){
            return dp[L][R];
        }
        int t = 0;
        int f = 0;
        if(L == R){
            t = exp[L] == '1' ? 1 : 0;
            f = exp[R] == '0' ? 1 : 0;
        }else{
            for(int split = L + 1; split < R; split += 2){
                Info leftInfo = process(exp,L,split - 1,dp);
                Info rightInfo = process(exp,split+ 1,R,dp);
                int a = leftInfo.tn;
                int b = leftInfo.fn;
                int c = rightInfo.tn;
                int d = rightInfo.fn;
                switch(exp[split]){
                    case '&':
                        t += a * c;
                        f += a * d + b * c +b *d;
                        break;
                    case '|':
                        t += a * c + a * d + b * c;
                        f += b * d;
                        break;
                    case '^':
                        t += a * d + b * c;
                        f += a * c + b * d;
                        break;
                }
            }
        }
        dp[L][R] = new Info(t,f);
        return dp[L][R];
    }

    public static class Info{
        int tn;
        int fn;
        public Info(int t,int f){
            tn = t;
            fn = f;
        }
    }





}
