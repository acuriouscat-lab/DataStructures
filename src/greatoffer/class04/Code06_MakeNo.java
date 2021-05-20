package greatoffer.class04;

public class Code06_MakeNo {

    // 生成长度为size的达标数组
    // 达标：对于任意的 i<k<j，满足 [i] + [j] != [k] * 2
    public static int[] makeNo(int size) {
        if (size == 1) {
            return new int[]{1};
        }
        int half = (size + 1) >> 1;
        int[] base = makeNo(half);
        int[] ans = new int[size];
        int index = 0;
        for (; index < half; index++) {
            ans[index] = base[index] * 2 ;
        }
        for (int i = 0; index < size; index++, i++) {
            ans[index] = base[i] * 2 + 1;
        }
        return ans;
    }

    // 检验函数
    public static boolean isValid(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int k = i + 1; k < N; k++) {
                for (int j = k + 1; j < N; j++) {
                    if (arr[i] + arr[j] == 2 * arr[k]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        for (int N = 1; N < 1000; N++) {
            int[] arr = makeNo(N);
            if (!isValid(arr)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
        System.out.println(isValid(makeNo(1042)));
        System.out.println(isValid(makeNo(2981)));
    }

}
