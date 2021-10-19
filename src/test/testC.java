package test;

/**
 * @author Administrator
 * @Description
 * @create 2021-07-25 11:02
 */
public class testC {

    public static void main(String[] args) {
        System.out.println((char) (1));
        char s = 1 + 48;
        System.out.println(s);
//        System.out.println(maximumNumber("132", new int[]{9, 8, 5, 0, 3, 6, 4, 2, 6, 8}));
    }




    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int[] index = new int[students.length];
        for (int i = 1; i < index.length; i++) {
            index[i] = i;
        }
        return maxCompatibilitySum(0, index, students, mentors);
    }

    private int maxCompatibilitySum(int i, int[] index, int[][] students, int[][] mentors) {
        if (i == index.length) {
            int count = 0;
            for (int j = 0; j < index.length; j++) {
                for (int k = 0; k < students[0].length; k++) {
                    if (students[j][k] == mentors[index[j]][k]) {
                        count++;
                    }
                }
            }
            return count;
        }
        int max = 0;
        for (int j = i; j < index.length; j++) {
            index[i] = index[j] + 0 * (index[j] = index[i]);
            max = Math.max(max, maxCompatibilitySum(i + 1, index, students, mentors));
            index[i] = index[j] + 0 * (index[j] = index[i]);
        }
        return max;
    }



}
