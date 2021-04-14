package org.tjw.leetcode.algorithm.changlle30;

public class VowelSpellchecker {

    public static void main(String[] args) {
        Solution s = new VowelSpellchecker().new Solution();
        String[] res = s.spellchecker(new String[]{"KiTe","kite","hare","Hare"}, new String[]{"kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"});
        for(String str :res) System.out.print(str + " ");
    }

    private static String empty = "";

    class Solution {
        public String[] spellchecker(String[] wordlist, String[] queries) {
            Node root = new Node();
            int len = queries.length;
            String[] res = new String[len];
            for(String str : wordlist) {
                Node temp = root;
                for(char ch : str.toCharArray()) {
                    if(ch < 'a') {
                        int t = ch - 'A';
                        if(temp.child[t] == null) temp.child[t] = new Node();
                        temp = temp.child[t];
                    } else {
                        int t = ch - 'a' + 26;
                        if(temp.child[t] == null) temp.child[t] = new Node();
                        temp = temp.child[t];
                    }
                }
                temp.isEnd = true;
                temp.val = str;
            }

            A:for(int i = 0; i < len; i ++) {
                String str = queries[i];
                Node temp = root;
                for(char ch : str.toCharArray()) {
                    if(ch < 'a') {
                        int t = ch - 'A';
                        if(temp.child[t] == null) {
                            if(temp.child[t + 26] != null) {
                                temp = temp.child[t + 26];
                            } else {
                                res[i] = empty;
                                continue A;
                            }
                        } else {
                            temp = temp.child[t];
                        }
                    } else {
                        int t = ch - 'a' + 26;
                        if(temp.child[t] == null) {
                            if(temp.child[t - 26] != null) {
                                temp = temp.child[t - 26];
                            } else {
                                res[i] = empty;
                                continue A;
                            }
                        } else {
                            temp = temp.child[t];
                        }
                    }
                }
                res[i] = temp.val;
            }

            return res;
        }
    }

    class Node {
        String val;
        boolean isEnd;
        Node[] child = new Node[52];
    }
}
