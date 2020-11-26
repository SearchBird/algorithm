package org.tjw.leetcode.algorithm;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
    public static void main(String[] args) throws Throwable {

        List<WalletAssetDetail> list = new ArrayList<>();
        WalletAssetDetail detail = new WalletAssetDetail();
        detail.setDdate("20200630");
        detail.setFundcode("000509");
        detail.setAvailasset(new BigDecimal("10.01"));
        list.add(detail);

        BigDecimal res = getMinAvailWalletAmount(list, 29);
        System.out.println(res);
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
