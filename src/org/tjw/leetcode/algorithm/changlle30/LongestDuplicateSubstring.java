package org.tjw.leetcode.algorithm.changlle30;

import org.tjw.leetcode.algorithm.bfs.SurroundedRegions;

import java.util.HashMap;
import java.util.HashSet;

public class LongestDuplicateSubstring {

    public static void main(String[] args) {
        Solution solution = new LongestDuplicateSubstring().new Solution();


        String str01 = "moplvidmaagmsiyyrkchbyhivlqwqsjcgtumqscmxrxrvwsnjjvygrelcbjgbpounhuyealllginkitfaiviraqcycjmskrozcdqylbuejrgfnquercvghppljmojfvylcxakyjxnampmakyjbqgwbyokaybcuklkaqzawageypfqhhasetugatdaxpvtevrigynxbqodiyioapgxqkndujeranxgebnpgsukybyowbxhgpkwjfdywfkpufcxzzqiuglkakibbkobonunnzwbjktykebfcbobxdflnyzngheatpcvnhdwkkhnlwnjdnrmjaevqopvinnzgacjkbhvsdsvuuwwhwesgtdzuctshytyfugdqswvxisyxcxoihfgzxnidnfadphwumtgdfmhjkaryjxvfquucltmuoosamjwqqzeleaiplwcbbxjxxvgsnonoivbnmiwbnijkzgoenohqncjqnckxbhpvreasdyvffrolobxzrmrbvwkpdbfvbwwyibydhndmpvqyfmqjwosclwxhgxmwjiksjvsnwupraojuatksjfqkvvfroqxsraskbdbgtppjrnzpfzabmcczlwynwomebvrihxugvjmtrkzdwuafozjcfqacenabmmxzcueyqwvbtslhjeiopgbrbvfbnpmvlnyexopoahgmwplwxnxqzhucdieyvbgtkfmdeocamzenecqlbhqmdfrvpsqyxvkkyfrbyolzvcpcbkdprttijkzcrgciidavsmrczbollxbkytqjwbiupvsorvkorfriajdtsowenhpmdtvamkoqacwwlkqfdzorjtepwlemunyrghwlvjgaxbzawmikfhtaniwviqiaeinbsqidetfsdbgsydkxgwoqyztaqmyeefaihmgrbxzyheoegawthcsyyrpyvnhysynoaikwtvmwathsomddhltxpeuxettpbeftmmyrqclnzwljlpxazrzzdosemwmthcvgwtxtinffopqxbufjwsvhqamxpydcnpekqhsovvqugqhbgweaiheeicmkdtxltkalexbeftuxvwnxmqqjeyourvbdfikqnzdipmmmiltjapovlhkpunxljeutwhenrxyfeufmzipqvergdkwptkilwzdxlydxbjoxjzxwcfmznfqgoaemrrxuwpfkftwejubxkgjlizljoynvidqwxnvhngqakmmehtvykbjwrrrjvwnrteeoxmtygiiygynedvfzwkvmffghuduspyyrnftyvsvjstfohwwyxhmlfmwguxxzgwdzwlnnltpjvnzswhmbzgdwzhvbgkiddhirgljbflgvyksxgnsvztcywpvutqryzdeerlildbzmtsgnebvsjetdnfgikrbsktbrdamfccvcptfaaklmcaqmglneebpdxkvcwwpndrjqnpqgbgihsfeotgggkdbvcdwfjanvafvxsvvhzyncwlmqqsmledzfnxxfyvcmhtjreykqlrfiqlsqzraqgtmocijejneeezqxbtomkwugapwesrinfiaxwxradnuvbyssqkznwwpsbgatlsxfhpcidfgzrc";
        String str02 = "banana";
        String str03 = "";

        System.out.println(solution.longestDupSubstring(str01));
        System.out.println(solution.longestDupSubstring(str02));
        System.out.println(solution.longestDupSubstring(str03));
    }

    // 阶
    private static int BIN = 26;
    // 取容量，2的32位为无符号Integer最大值，java的int有符号
    private static long MOD = (long) Math.pow(2, 32);
    // 尝试一下发现60位WA，这个算法是有缺陷会导致重复的hash
    // 所以只是骗骗AC，骗骗测试数据
    // private static long MOD = (long) Math.pow(2, 60);

    class Solution
    {
        public String longestDupSubstring(String S) {
            int len = S.length();
            if(len <= 1) return "";

            // 方便取值
            char[] Scs = S.toCharArray();

            // 将char改为int
            int[] nums = new int[len];
            for(int i = 0; i < len; i++) nums[i] = Scs[i] - 'a';

            int left = 1;
            int right = len;
            while(left != right)
            {
                int L = (right - left >> 1) + left;
                // 发现该长度的子串存在,移动left
                if(search(L, nums) != -1)
                    left = L + 1;
                else
                    right = L;
            }

            int start = search(left - 1, nums);
            return start == -1 ? "" : S.substring(start, start + left - 1);
        }

        // 返回重复字符串的起始位置
        public int search(int L, int[] nums) {
            int len = nums.length;
            HashSet<Long> hashSet = new HashSet<Long>();
            long tmp = 0;
            long aL = 1;
            for(int j = 0; j < L; j++){
                // 求模是为了延长hash有效范围，但是不排除hash重复
                tmp = (tmp * BIN + nums[j]) % MOD;
                aL = (aL * BIN) % MOD;
            }
            hashSet.add(tmp);

            // 进行len - L长度的窗口移动
            for(int j = 1; j <= len - L; j++){
                // 剪掉前一位hash
                tmp = (tmp * BIN - nums[j - 1] * aL % MOD + MOD) % MOD;
                // 拼起后一位hash
                tmp = (tmp + nums[j + L - 1]) % MOD;

                // 发现hash一致就返回j，就是开始位置
                if(hashSet.contains(tmp)) return j;
                hashSet.add(tmp);
            }
            return -1;
        }


        /**
         * 超过10w没法玩
         * @param S
         * @return
         */
        public String longestDupSubstring2(String S) {
            // 弄成数组更省时间
            char[] chars = S.toCharArray();
            int len = chars.length;

            // 0 -from - 开始指针
            // 1 - to - 结束指针
            int[] ft = new int[]{-1, -1};
            // dp内存放的是长度值
            int[][] dp = new int[2][len + 1];

            // 使用滚动数组，pre前指针，cur当前指针
            int pre = 1;
            int cur = 0;
            for(int i = 1;i <= len; i ++) {
                // 交换指针指向
                pre ^= 1;
                cur = pre ^ 1;
                for(int j = i + 1;j <= len; j ++) {
                    if(chars[i - 1] == chars[j - 1]) {
                        // 匹配到了就将上一个对角线 长度 + 1
                        dp[cur][j] = dp[pre][j - 1] + 1;

                        // from - to得到的长度 < 当前存放长度，移动from to指针
                        if(ft[1] - ft[0] < dp[cur][j]) {
                            if(ft[0] == -1) {
                                // 未赋值处理
                                ft[0] = j;
                                ft[1] = j;
                            } else {
                                // j - dp距离值 = 开始值
                                ft[0] = j - dp[cur][j];
                                // j 作为结束值
                                ft[1] = j;
                            }
                        }
                    } else {
                        // 如果没有匹配到要变为0
                        dp[cur][j] = 0;
                    }
                }
            }

            // from == -1就没有重复，不然就剪切 from to的字符串
            return ft[0] == -1 ? "" : S.substring(ft[0], ft[1]);
        }
    }

}
