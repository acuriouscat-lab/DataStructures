package algorithmlearning003.class01;

public class Code06_MakeNo {

    //给定一个正整数M，请构造出一个长度为M的数组arr，要求
    //对任意的i、j、k三个位置，如果i<j<k，都有arr[i] + arr[k] != 2*arr[j]
    //返回构造出的arr
    //二分 左边为奇数 右边为偶数的构造方式
    public static int[] makeNo(int size) {
        if(size == 1){
            return new int[]{1};
        }
        int halfSize = (size + 1) >> 1;
        int[] base = makeNo(halfSize);
        int[] res = new int[size];
        int index = 0;
        for (int i = 0; i < halfSize; i++) {
            res[i] = base[index++] * 2 - 1;
        }
        for (int i = 0; index < size; i++,index++) {
            res[index] = base[i] * 2;
        }
        return res;
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
