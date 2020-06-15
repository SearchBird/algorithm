package org.tjw.luogu.province;


/**
 * =========== 01背包 ===========
 * 一维：逆序遍历
 * for(int j = capital + 1;j -- >= w[i];)
 *      dp[j] = max(dp[j - w[i]] + v[i], dp[j])
 *
 * 滚动数组：和上一元素结果集（相当于上一行）比较，使用k作为空间位置参数
 * int k = i & 1
 * dp[k][j] = dp[k ^ 1][j]
 * if(j >= w[i])
 *      dp[k][j] = max(dp[k ^ 1][j - w[i]] + v[i], dp[k ^ 1][j])
 *
 * 二维：和上一元素结果集（相当于上一行）比较
 * dp[i][j] = dp[i - 1][j]
 * if(j >= w[i])
 *      max(dp[i][j], dp[i][j - w[i]] + v[i])
 *
 *
 * =========== 完全背包 ===========
 * 一维：正序遍历
 * for(int j = w[i];j <= capital;j ++)
 *      dp[j] = max(dp[j - w[i]] + v[i], dp[j])
 *
 * 二维：每次 capital_i 的时候，得要比较 capital_i / w[i] 次
 * int k = capital_i / w[i]
 * if(k ++ >= 1)
 *      int temp;
 *      for(;k -- > 0;)
 *          temp = max(dp[i - 1][j - w[i] * k] + k * v[i], temp)
 *      dp[i][j] = max(dp[i - 1][j], temp)
 */
public class Package {
    // 下标0不算入，从1开始
    private static int[] moneys = new int[]{0,1,10,11,60};
    private static int[] moneys2 = new int[]{0,1,10,11,60,77};

    private static int capital = 80;
    private static int capital2 = 100;

    public static void main(String[] args) {
        Package mypackage = new Package();

        int[] mymoney = moneys2;
        int mycapital = capital2;

        // 01背包
        System.out.println("一维01背包最大总价值:" + mypackage.package01_01(mycapital, mymoney));
        System.out.println("滚动01背包最大总价值:" + mypackage.package01_02(mycapital, mymoney));
        System.out.println("二维01背包最大总价值:" + mypackage.package01_03(mycapital, mymoney));

        // 多重背包
        System.out.println("一维多重背包最大总价值:" + mypackage.package02_01(mycapital, mymoney));
        System.out.println("二维多重背包最大总价值:" + mypackage.package02_02(mycapital, mymoney));

        // 一百元数量，需要最少张钞票
        System.out.println(mypackage.dp(34, mymoney));
        System.out.println(mypackage.recurtion(23, mymoney) - 1);
    }


    /**
     * 一维实现01背包
     */
    public int package01_01(int capital,int[] moneys) {
        int[] dp = new int[capital + 1];

        for(int i = 1;i < moneys.length;i ++) {
            // 一维背包就逆序遍历
            for(int j = capital;j >= moneys[i];j --) {
                    dp[j] = Math.max(dp[j], dp[j - moneys[i]] + moneys[i]);
            }
        }
        return dp[capital];
    }


    /**
     * 滚动数组，一维实现01背包
     */
    public int package01_02(int capital,int[] moneys) {

        int[][] dp = new int[2][capital];
        for(int i = moneys[0];i < capital;i ++) {
            dp[0][i] = moneys[0];
        }

        int k = 0;
        for(int i = 1;i < moneys.length;i ++) {
            for(int j = 1;j < capital;j ++) {
                k = i & 1;// 相当于 k = i % 2;
                dp[k][j] = dp[k ^ 1][j];
                // 如果有超过容量的物体时候，就出事了
                if(j >= moneys[i])
                    dp[k][j] = Math.max(dp[k][j], dp[k ^ 1][j - moneys[i]] + moneys[i]);
            }
        }
        return dp[k][capital - 1];
    }


    /**
     * 二维实现01背包
     */
    public int package01_03(int capital,int[] moneys) {

        int[][] dp = new int[moneys.length][capital + 1];
        // 从1开始
        for(int i = moneys[1];i <= capital;i ++) {
            dp[1][i] = moneys[1];
        }

        for(int i = 2;i < moneys.length;i ++) {
            for(int j = 1;j <= capital;j ++) {
                dp[i][j] = dp[i - 1][j];
                // 如果有超过容量的物体时候，就出事了
                if(j >= moneys[i])
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - moneys[i]] + moneys[i]);
            }
        }
        return dp[moneys.length - 1][capital];
    }


    /**
     * 一维数组完全背包
     */
    public int package02_01(int capital,int[] moneys) {
        int dp[] = new int[capital + 1];

        for(int i = 1;i < moneys.length;i ++) {
            // 正序遍历，完全背包
            for(int j = moneys[i];j <= capital;j ++) {
                // 每一次都会拿到 j - w[i] 处存放value相加，到了第k此也是得到前k - 1次值
                if(dp[j] < dp[j - moneys[i]] + moneys[i]) {
                    dp[j] = dp[j - moneys[i]] + moneys[i];
                }

            }
        }
        return dp[capital];
    }


    /**
     * 二维数组完全背包
     */
    public int package02_02(int capital,int[] moneys) {
        int[][] dp = new int[moneys.length + 1][capital + 1];
        // 初始化第一行
        for(int i = moneys[1];i <= capital; i ++) {
            dp[0][i] = moneys[1] * i / moneys[1];
        }

        for(int i = 2;i < moneys.length;i ++) {
            for(int j = 1;j <= capital;j ++) {
                dp[i][j] = dp[i - 1][j];

                // 计算容纳量（容纳多少的w[i]）
                int num = j / moneys[i];
                if(num >= 1) {
                    int temp = 0;
                    // 进行多次容量比较，将上一行的，j - k * w[i]所存放价值，
                    // 每增加一次w[i]都拿出来比较value
                    for(int k = 1; k <= num; k ++) {
                        // 加上 上一行的存放价值 + k * moneys[i] 的价值
                        temp = Math.max(dp[i - 1][j - k * moneys[i]] + k * moneys[i]
                                , temp);
                    }

                    // 最后进行比较即可
                    dp[i][j] = Math.max(dp[i][j], temp);
                }
            }
        }
        return dp[moneys.length - 1][capital];
    }


    public int dp(int money,int[] moneys) {
        int[] dp = new int[money + 1];

        // dp[1]开始初始化，保证dp[0] = 0，才能继续min(dp[j - moneys[i]] + 1, dp[j])
        // 不然就全Integer.MAX_VALUE了
        for(int i = 1; i <= money; i++)
            dp[i] = Integer.MAX_VALUE;

        for(int i = 1; i < moneys.length; i++) {
            for(int j = moneys[i];j <= money; j++) {
                    dp[j] = Math.min(dp[j - moneys[i]] + 1, dp[j]);
            }
        }

        return dp[money];
    }

    /**
     * 根据 min(money) = min(money - moneys[i]) + 1 写出递归
     */
    public int recurtion(int money,int[] moneys) {
        // 计算到为0的返回
        if(money != 0) {
            // min下一项统计数最小值 min = min(money - moneys[i])
            int min = Integer.MAX_VALUE;
            for(int i = 1;i < moneys.length;i ++) {
                // 将可行的元素放行
                if(money - moneys[i] >= 0) {
                    // 往下递归钱数量和统计数
                    int temp = recurtion(money - moneys[i], moneys);
                    // 获取c(money - moneys[i])最小统计数
                    if(min > temp) {
                        min = temp;
                    }
                }
            }
            // 返回c(money - moneys[i]) + 1
            if(min != Integer.MAX_VALUE) {
                return min + 1;
            }
            // 返回这个最大值是无效的数字
            else return min;
        } else return 1;

    }
}
