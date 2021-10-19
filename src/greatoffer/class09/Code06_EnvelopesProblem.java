package greatoffer.class09;

import java.util.Arrays;

public class Code06_EnvelopesProblem {

    //https://leetcode-cn.com/problems/russian-doll-envelopes/
    /*
    给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
    当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
    请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
     */
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        // 按照宽度从短到长进行排序，宽度相同的高度按照从高到低
        Arrays.sort(envelopes, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]);
        int N = envelopes.length;
        int[] end = new int[N];
        end[0] = envelopes[0][1];
        int l = 0;
        int r = 0;
        int m = 0;
        int right = 0;
        for (int i = 1; i < N; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = (l + r) >> 1;
                if (end[m] < envelopes[i][1]) {
                    l = m + 1;
                }else{
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            end[l] = envelopes[i][1];
        }
        return right + 1;
    }


}
