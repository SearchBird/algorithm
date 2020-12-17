package org.tjw.leetcode.algorithm;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws Throwable {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int temp = 0;
        for(int i = 1;i < num;i ++) {
            temp += sc.nextInt();
            System.out.print(temp + " ");
        }
    }

    public static BigDecimal getMinAvailWalletAmount(List<WalletAssetDetail> walletAssetDetailList, int days) {
        //如果资产持有时间不足闲置天数，认为没有闲钱
        if (walletAssetDetailList.size() < days) {
            return BigDecimal.ZERO;
        }
        return Collections.min(walletAssetDetailList.subList(0, days), new Comparator<WalletAssetDetail>() {
            @Override
            public int compare(WalletAssetDetail o1, WalletAssetDetail o2) {
                return o1.getAvailasset().compareTo(o2.getAvailasset());
            }
        }).getAvailasset();
    }
}
