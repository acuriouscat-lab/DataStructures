package algorithmlearning001.class05;

public class Code02_AddShortestEnd {

    public static String shortestEnd(String str){
        if (str == null || str.length() == 0) {
            return null;
        }
        char[] manacher = manacherString(str);
        int C = -1;
        int R = -1;
        int maxContainsEnd = -1;
        int[] pArr = new int[manacher.length];
        for (int i = 0; i < manacher.length; i++) {
            pArr[i] = R > i ? Math.min(R - i, pArr[2 * C - i]) : 1;

            while (i + pArr[i] < manacher.length && i - pArr[i] >= 0) {
                if (manacher[i + pArr[i]] == manacher[i - pArr[i]]) {
                    pArr[i]++;
                }else {
                    break;
                }
            }
            if (R < i + pArr[i]) {
                C = i ;
                R = i + pArr[i];
            }
            if (manacher.length == R) {
                maxContainsEnd = pArr[i];
                break;
            }
        }

        char[] res = new char[str.length() - maxContainsEnd + 1];
        for (int i = 0; i < res.length; i++) {
            res[res.length - i - 1] = manacher[2 * i + 1];
        }
        return String.valueOf(res);
    }

    public static char[] manacherString(String str){
        char[] strArr = str.toCharArray();
        char[] res = new char[strArr.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : strArr[index++];
        }
        return  res;
    }


    public static void main(String[] args) {
        String str1 = "aacecaaa";
        System.out.println(shortestEnd(str1));
    }
}
