package org.tjw.leetcode.algorithm.hashTable;


import java.util.ArrayList;
import java.util.Collections;

/**
 * Suppose Andy and Doris want to choose a restaurant for dinner, and they
 * both have a list of favorite restaurants represented by strings.
 *
 * You need to help them find out their common interest with the least
 * list index sum. If there is a choice tie between answers, output all of
 * them with no order requirement. You could assume there always exists an answer.
 *
 * Example 1:
 * Input:
 * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 * ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
 * Output: ["Shogun"]
 * Explanation: The only restaurant they both like is "Shogun".
 * Example 2:
 * Input:
 * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 * ["KFC", "Shogun", "Burger King"]
 * Output: ["Shogun"]
 * Explanation: The restaurant they both like and have the least index
 * sum is "Shogun" with index sum 1 (0+1).
 * Note:
 * The length of both lists will be in the range of [1, 1000].
 * The length of strings in both lists will be in the range of [1, 30].
 * The index is starting from 0 to the list length minus 1.
 * No duplicates in both lists.
 */
public class MinimumIndexSumofTwoLists {


    public static void main(String[] args) {
        Solution solution = new MinimumIndexSumofTwoLists().new Solution();

        // 问题1 需要考虑多个一致数据，开始只考虑一个result
        String[] str1 = new String[]{"Shogun","Tapioca Express","Burger King","KFC"};
        String[] str2 = new String[]{"KFC","Burger King","Tapioca Express","Shogun"};
        String[] str = solution.findRestaurant(str1, str2);
        for(int i = 0;i < str.length;i ++) {
            System.out.print(str[i] + "  ");
        }
    }

    class Solution {

        private TrieTree tree = new TrieTree();

        public String[] findRestaurant(String[] list1, String[] list2) {

            insertList(list1);
            ArrayList<Integer> result = search(list2);
            Collections.sort(result);
            String[] strs = new String[result.size()];
            for(int i = 0;i < result.size();i ++) {
                strs[i] = list1[result.get(i)];
            }
            return strs;
        }

        public ArrayList<Integer> search(String[] list2) {
            int len02 = list2.length;
            int min = Integer.MAX_VALUE;
            ArrayList<Integer> result = new ArrayList<Integer>();

            A:for(int i = 0;i < len02;i ++) {
                String str = list2[i];
                int len = str.length();
                TrieTree temp = tree;
                for(int j = 0;j < len;j ++) {
                    char ch = str.charAt(j);
                    int intch = getIndex(ch);
                    if(temp.links[intch] == null) continue A;
                    temp = temp.links[intch];
                }
                // 问题1 这里要进行比较并返回一个最小和的index列表，不只有一个
                if(temp.isEnd) {
                    int a = temp.index + i;
                    if(a < min) {
                        min = a;
                        // 发现最小的就清空
                        result.clear();
                        result.add(temp.index);
                    } else if(a == min) result.add(temp.index);
                }
            }
            return result;
        }

        public void insertList(String[] list1) {
            int len01 = list1.length;
            for(int i = 0;i < len01;i ++) {
                String str = list1[i];
                int len = str.length();
                TrieTree temp = tree;
                for(int j = 0;j < len;j ++) {
                    char ch = str.charAt(j);
                    int intch = getIndex(ch);
                    if(temp.links[intch] == null) temp.links[intch] = new TrieTree();
                    temp = temp.links[intch];
                }
                temp.isEnd = true;
                temp.index = i;
            }
        }

        public int getIndex(char ch) {
            int intch = ch - 'a' + 1;
            if(intch < 0) {
                intch = ch - 'A';
                if(intch < 0) intch = 0; else intch += 27;
            }
            return intch;
        }
    }

    class TrieTree {
        TrieTree[] links = new TrieTree[53];
        int index = -1;
        boolean isEnd = false;
    }
}
