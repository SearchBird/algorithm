package org.tjw.codewar.Trainning.kyu4;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Blocksequence {

    public static void main(String[] args) {
        Solution s = new Solution();
        int res = s.solve(1); // 1
//        System.out.println(res);
//        System.out.println();
//        res = s.solve(2);  // 1
//        System.out.println(res);
//        System.out.println();
//        res = s.solve(3); // 2
//        System.out.println(res);
//        System.out.println();
//        res = s.solve(100);  // 1
//        System.out.println(res);
//        System.out.println();
//        res = s.solve(2100);  // 2
//        System.out.println(res);
//        System.out.println();
//        res = s.solve(123456L); // 6
//        System.out.println(res);
//        System.out.println();
//        res = s.solve(123456789L); // 3
//        System.out.println(res);
//        System.out.println();
//        res = s.solve(999999999999999999L); // 4
//        System.out.println(res);
//        System.out.println();
//        res = s.solve(1000000000000000000L); // 1
//        System.out.println(res);
//        System.out.println();
        res = s.solve(317961737459799745L); // 6
        System.out.println(res);
        System.out.println();
    }

    static class Solution{
        public static int solve(long n){
            long begin = 0, number = 1, dd = 0;
            long length = 0;
            boolean falg = false;
//            StringBuilder s = new StringBuilder();
//            for(long i = 0, len = s.length(); i < n; i ++) {
//                if(begin < len) {
//                    System.out.print(s.charAt(begin ++));
//                }
//                else {
//                    if(i != 0) System.out.println();
//                    begin = 0;
//                    s.append(number ++);
//                    System.out.print(s.charAt(begin ++));
//                    len = s.length();
//                }
//            }
//            System.out.println();

//            for(long i = 0; i < n;) {
//                if(begin < length) {
//                    if(i + length < n) {
//                        i += length;
//                        begin = length;
//                    } else {
//                        if(!falg) {
//                            falg = true;
//                            dd = n - i;
//                        }
//                        i ++;
//                    }
//                }
//                else {
//                    length += String.valueOf(number ++).length();
//                }
//            }
//            System.out.println(dd);
//            System.out.println(number);

            long d = 1, pre = 0, preMax = 0;
            List<Long> list = new ArrayList<>();
            list.add(0L);
            while(0 < n) {
                long num = ((long)Math.pow(10, d) - 1 - pre), a0 = preMax + d;
                long areaMax = num * a0 + (num - 1) * num / 2 * d;
                pre = (long)Math.pow(10, d) - 1;
                preMax += num * d;
                list.add(preMax); // 前缀集合
                if(areaMax < n) {
                    n -= areaMax;
                    d ++;
                } else {
                    // 先进行 Block 统计
                    BigDecimal powVal = new BigDecimal(2 * a0 - d).multiply(new BigDecimal(2 * a0 - d));
                    BigDecimal sqrtVal = powVal.add(
                            new BigDecimal(8)
                                    .multiply(new BigDecimal(n))
                                    .multiply(new BigDecimal(d)));
                    long sqrtLong = sqrt(sqrtVal, 4).setScale(0, BigDecimal.ROUND_DOWN).longValue(); // 防止四舍五入
                    num = (d - 2 * a0 + sqrtLong) / (2 * d);
                    n -= new BigDecimal(num).multiply(new BigDecimal(a0)).add(new BigDecimal(num - 1).multiply(new BigDecimal(num)).divide(new BigDecimal(2)).multiply(new BigDecimal(d))).longValue();

                    if(n == 0) { // 已经确认是 block 最后一个
                        d --;
                        int tens = d == 0 ? 0 : (int)Math.pow(10, d) - 1;
                        String siteStr = String.valueOf(tens + num);
                        return siteStr.charAt(siteStr.length() - 1) - '0';
                    } else { // 进入确认 block 第几位
                        for(int i = 1, size = list.size(); i < size; i ++) {
                            Long ll = list.get(i) - list.get(i - 1); // 需要减去差分
                            if(n > ll) n-= ll;
                            else { // 确认了有 i 位
                                long ni = n / i; // 先算出 i 位数值
                                long tens = (long)Math.pow(10, i - 1); // 将 i 位前数值算出
                                if(n % i == 0) {
                                    String siteStr = String.valueOf(tens - 1 + ni);
                                    return siteStr.charAt(siteStr.length() - 1) - '0';
                                } else {
                                    String siteStr = String.valueOf(tens - 1 + ni + 1); // 因为 n 个字符有余, 所以是 ni + 1, 特意写出来
                                    return siteStr.charAt((int)(n % i) - 1) - '0';
                                }
                            }
                        }
                    }
                }
            }
            return -1;
        }

        public static BigDecimal sqrt(BigDecimal value, int scale){
            BigDecimal num2 = BigDecimal.valueOf(2);
            int precision = 100;
            MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);
            BigDecimal deviation = value;
            int cnt = 0;
            while (cnt < precision) {
                deviation = (deviation.add(value.divide(deviation, mc))).divide(num2, mc);
                cnt++;
            }
            deviation = deviation.setScale(scale, BigDecimal.ROUND_HALF_UP);
            return deviation;
        }
    }
}
