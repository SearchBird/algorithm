package org.tjw.leetcode.algorithm;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: nijl
 * 创建日期: 2020/7/4 11:52
 * 描述: 客户维度的钱袋子资产明细，已包含钱袋子切换场景
 */
public class WalletAssetDetail implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 自然日
     */
    private String ddate;

    /**
     * 基金代码
     */
    private String fundcode;

    /**
     * 当日可用资产
     */
    private BigDecimal availasset;


    public String getDdate() {
        return ddate;
    }

    public void setDdate(String ddate) {
        this.ddate = ddate;
    }

    public String getFundcode() {
        return fundcode;
    }

    public void setFundcode(String fundcode) {
        this.fundcode = fundcode;
    }

    public BigDecimal getAvailasset() {
        return availasset;
    }

    public void setAvailasset(BigDecimal availasset) {
        this.availasset = availasset;
    }

    @Override
    public String toString() {
        return "WalletAssetDetail{" +
                ", ddate='" + ddate + '\'' +
                ", fundcode='" + fundcode + '\'' +
                ", availasset=" + availasset +
                '}';
    }
}
