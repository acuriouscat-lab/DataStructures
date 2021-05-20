package greatoffer.class04;

public class Code05_CandyProblem {

    public static int candy1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] left = new int[N];
        int[] right = new int[N];
        left[0] = 1;
        for (int i = 1; i < N; i++) {
            if (arr[i] > arr[i - 1]) {
                left[i] = left[i - 1] + 1;
            }else{
                left[i] = 1;
            }
        }
        right[N - 1] = 1;
        for (int i = N - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                right[i] = right[i + 1] + 1;
            }else{
                right[i] = 1;
            }
        }
        int max = 0;
        for (int i = 0; i < N; i++) {
            max += Math.max(left[i], right[i]);
        }
        return max;
    }



}
