package Interview;

public class Code01_Cola {
    /*
     * 买饮料 时间限制： 3000MS 内存限制： 589824KB 题目描述：
     * 游游今年就要毕业了，和同学们在携程上定制了日本毕业旅行。愉快的一天行程结束后大家回到了酒店房间，这时候同学们都很口渴，
     * 石头剪刀布选出游游去楼下的自动贩卖机给大家买可乐。 贩卖机只支持硬币支付，且收退都只支持10 ，50，100
     * 三种面额。一次购买行为只能出一瓶可乐，且每次购买后总是找零最小枚数的硬币。（例如投入100圆，可乐30圆，则找零50圆一枚，10圆两枚）
     * 游游需要购买的可乐数量是 m，其中手头拥有的 10,50,100 面额硬币的枚数分别是 a,b,c，可乐的价格是x(x是10的倍数)。
     * 如果游游优先使用大面额购买且钱是够的情况下,请计算出需要投入硬币次数？ 输入描述 依次输入， 需要可乐的数量为 m 10元的张数为 a 50元的张数为 b
     * 100元的张树为 c 1瓶可乐的价格为 x 输出描述 输出当前金额下需要投入硬币的次数
     * 例如需要购买2瓶可乐，每瓶可乐250圆，手里有100圆3枚，50圆4枚，10圆1枚。 购买第1瓶投递100圆3枚，找50圆 购买第2瓶投递50圆5枚
     * 所以是总共需要操作8次金额投递操作 样例输入 2 1 4 3 250 样例输出 8
     *
     *  优先使用大面值的进行购买 只有大面值的不够一瓶才用小一等的面值，
     *  找零也是优先找大面值的
     */

    /**
     * @param m 要买多少瓶
     * @param a 10元有多少张
     * @param b 50元
     * @param c 100元
     * @param x 单价
     * @return 要投几次
     */
    public static int putTimes(int m, int a, int b, int c, int x) {
        int[] money = {100, 50, 10};
        int[] zhang = {c, b, a};
        // 一共要投的次数
        int puts = 0;
        // 之前面值还剩下多少钱
        int preMoney = 0;
        // 之前面值还有多少张
        int preZhang = 0;
        for (int i = 0; i < 3 && m != 0; i++) {
            int curMoneyFirstBuyZhang = (x - preMoney + money[i] - 1) / money[i];
            // 和之前的钱加在一起 能买一瓶的话
            if (zhang[i] >= curMoneyFirstBuyZhang) {
                // 购买一瓶的找零
                int rest = (preMoney + money[i] * curMoneyFirstBuyZhang - x);
                giveRest(money, i + 1, zhang, rest, 1);
                puts += curMoneyFirstBuyZhang + preZhang;
                m--;
                zhang[i] -= curMoneyFirstBuyZhang;
            } else {
                // 如果不能买一瓶的话
                preMoney += zhang[i] * money[i];
                preZhang += zhang[i];
                continue;
            }
            // 当前面值 自己还能买多少瓶
            // 买一瓶需要多少张
            int curMoneyBuyOneColaZhang = (x + money[i] - 1) / money[i];
            // 用当前的钱能够买多少瓶
            int curCanBuy = Math.min(m, zhang[i] / curMoneyBuyOneColaZhang);
            giveRest(money, i + 1, zhang, curMoneyBuyOneColaZhang * money[i] - x, curCanBuy);
            puts += curMoneyBuyOneColaZhang * curCanBuy;
            m -= curCanBuy;
            zhang[i] -= curMoneyBuyOneColaZhang * curCanBuy;
            preZhang = zhang[i];
            preMoney = zhang[i] * money[i];
        }
        return m == 0 ? puts : -1;
    }

    //    public static void giveRest(int[] money, int index, int[] zhang, int oneTimeToRest, int times) {
//        while (index < 3) {
//            zhang[index] += (oneTimeToRest / money[index]) * times;
//            oneTimeToRest -= money[index];
//            index++;
//        }
//    }
    public static void giveRest(int[] qian, int i, int[] zhang, int oneTimeRest, int times) {
        for (; i < 3; i++) {
            zhang[i] += (oneTimeRest / qian[i]) * times;
            oneTimeRest %= qian[i];
        }
    }

    public static void main(String[] args) {
        int testTime = 1000;
        int zhangMax = 10;
        int colaMax = 10;
        int priceMax = 20;
        System.out.println("如果错误会打印错误数据，否则就是正确");
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int m = (int) (Math.random() * colaMax);
            int a = (int) (Math.random() * zhangMax);
            int b = (int) (Math.random() * zhangMax);
            int c = (int) (Math.random() * zhangMax);
            int x = ((int) (Math.random() * priceMax) + 1) * 10;
            int ans1 = putTimes(m, a, b, c, x);
            int ans2 = right(m, a, b, c, x);
            if (ans1 != ans2) {
                System.out.println("int m = " + m + ";");
                System.out.println("int a = " + a + ";");
                System.out.println("int b = " + b + ";");
                System.out.println("int c = " + c + ";");
                System.out.println("int x = " + x + ";");
                break;
            }
        }
        System.out.println("test end");
    }

    // 暴力尝试，为了验证正式方法而已
    public static int right(int m, int a, int b, int c, int x) {
        int[] qian = {100, 50, 10};
        int[] zhang = {c, b, a};
        int puts = 0;
        while (m != 0) {
            int cur = buy(qian, zhang, x);
            if (cur == -1) {
                return -1;
            }
            puts += cur;
            m--;
        }
        return puts;
    }

    public static int buy(int[] qian, int[] zhang, int rest) {
        int first = -1;
        for (int i = 0; i < 3; i++) {
            if (zhang[i] != 0) {
                first = i;
                break;
            }
        }
        if (first == -1) {
            return -1;
        }
        if (qian[first] >= rest) {
            zhang[first]--;
            giveRest(qian, first + 1, zhang, qian[first] - rest, 1);
            return 1;
        } else {
            zhang[first]--;
            int next = buy(qian, zhang, rest - qian[first]);
            if (next == -1) {
                return -1;
            }
            return 1 + next;
        }
    }


}
