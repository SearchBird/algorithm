package org.tjw.leetcode.algorithm.changlle30;

public class ValidateIPAddress {


    public static void main(String[] args) {
        Solution solution = new ValidateIPAddress().new Solution();

        // 末尾判断出错
        System.out.println(solution.validIPAddress("172.16..1"));
        System.out.println(solution.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:"));
        System.out.println(solution.validIPAddress("20EE:Fb8:85a3:0:0:8A2E:0370:7334"));
    }

    class Solution {
        public String validIPAddress(String IP) {
            // 排除自身分隔符开头结尾
            if(IP == null || "".equals(IP)
                    || ':' == IP.charAt(IP.length() - 1) || '.' == IP.charAt(IP.length() - 1))
                return "Neither";

            String[] ipv4 = IP.split("\\.");

            // 限制为 4 串数
            if(ipv4.length == 4) {

                // 任何报错都返回不正确，挺暴力的
                try{
                    for(String ip : ipv4) {
                        char first = ip.charAt(0);
                        // 必须限制 0 ~ 9 字符，不以 0 开头，0 ~ 3位数
                        if(ip.length() > 3 || (ip.length() > 1 && '0' == first)
                                || '0' > first || '9' < first)
                            return "Neither";
                        if(Integer.valueOf(ip) / 256 != 0)
                            return "Neither";
                    }
                } catch(Exception e) {
                    return "Neither";
                }

                return "IPv4";

                // 只有发现 1 串数，才进行切割
            } else if(ipv4.length == 1) {

                // 必须要 8 串数
                String[] ipv6 = IP.split(":");
                if(ipv6.length == 8) {

                    try{
                        for(String ip : ipv6) {
                            char first = ip.charAt(0);
                            // 必须不以 - + 开头，0 ~ 4位数，其他都交给trycatch
                            if(ip.length() > 4|| '-' == first || '+' == first)
                                return "Neither";
                            if(Integer.parseInt(ip,16) / 0xffff != 0)
                                return "Neither";
                        }
                    } catch(Exception e) {
                        return "Neither";
                    }

                    return "IPv6";
                }
            }
            return "Neither";
        }
    }
}
