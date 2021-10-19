package greatoffer.class02;

import java.util.Arrays;
import java.util.TreeMap;

public class Code01_ChooseWork {

    //给定数组hard和money，长度都为N
    //hard[i]表示i号的难度， money[i]表示i号工作的收入
    //给定数组ability，长度都为M，ability[j]表示j号人的能力
    //每一号工作，都可以提供无数的岗位，难度和收入都一样
    //但是人的能力必须>=这份工作的难度，才能上班
    //返回一个长度为M的数组ans，ans[j]表示j号人能获得的最好收入

    public static class Job{
        public int money;
        public int hard;

        public Job(int m, int h) {
            money = m;
            hard = h;
        }
    }

    public static int[] getMoneys(Job[] jobs, int[] ability) {

        // 先按照难度进行升序排序、报酬降序排序
        Arrays.sort(jobs, (o1, o2) -> o1.hard == o2.hard ? o2.money - o1.money : o1.hard - o2.hard);

        TreeMap<Integer, Integer> map = new TreeMap<>();
        Job cur = jobs[0];
        map.put(cur.hard, cur.money);
        map.put(0, 0);
        for (int i = 1; i < jobs.length; i++) {
            // 要得到，难度高的，报酬也要
            // 去掉难度一样的但是报酬少的 和 难度高但是给的却比难度低的少的
            if (cur.hard == jobs[i].hard || cur.money > jobs[i].money) {
                continue;
            }
            cur = jobs[i];
            map.put(cur.hard, cur.money);
        }
        int[] ans = new int[ability.length];
        for (int i = 0; i < ability.length; i++) {
            // ability[i] 当前人的能力 <= ability[i]  且离它最近的
            Integer workIndex = map.floorKey(ability[i]);
            ans[i] = map.get(workIndex);
        }
        return ans;
    }

}
