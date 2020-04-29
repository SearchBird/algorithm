package org.tjw.leetcode.algorithm.trie;

import java.util.ArrayList;
import java.util.List;

public class AddandSearchWord {
    public static void main(String[] args) {
        WordDictionary solution = new AddandSearchWord().new WordDictionary();

        // 1-问题 开头.会返回false
        solution.addWord("bat");
        System.out.println(solution.search(".at"));
        // 1-问题 开头.会返回false

        // 2-问题 两个以上的结尾，如果再多一个.就会变成true
        solution.addWord("and");
        solution.addWord("add");
        System.out.println(solution.search("a.d."));
        // 2-问题 两个以上的结尾，如果再多一个.就会变成true

    }

    class WordDictionary {

        private TrieTree node = new TrieTree();

        public void addWord(String word) {
            int len = word.length();
            TrieTree temp = node;
            for(int i = 0;i < len;i ++) {
                char ch = word.charAt(i);
                int v = ch - 'a';
                if(!temp.contain(v)) temp.put(v);
                temp = temp.get(v);
            }
            temp.setEnd();
        }

        public boolean search(String word) {
            int len = word.length();
            List<TrieTree> stack = new ArrayList<TrieTree>();
            stack.add(node);
            for(int i = 0;i < len;i ++) {
                char ch = word.charAt(i);
                int stackLen = stack.size();
                // 1-问题
                // 判断.放到外面, 不应该在for里面，不然导致只放一个节点下面的
                if(ch != '.') {
                    for(int j = stackLen;j -- > 0;) {
                        TrieTree temp = stack.remove(0);
                        int v = ch - 'a';
                        if(!temp.contain(v)) continue;
                        stack.add(temp.get(v));
                    }
                } else {
                    // 2-问题
                    // 一样要for循环，不然只放第一个，第二个以后都没remove出来
                    for(int j = stackLen;j -- > 0;) {
                        stack.remove(0).getLinks(stack);
                    }
                }
                if(stack.isEmpty()) return false;
            }

            for(int j = stack.size();j -- > 0;) {
                if(stack.get(j).isEnd()) return true;
            }
            return false;
        }
    }

    class TrieTree {
        private final static int BIN = 26;
        private TrieTree[] links = new TrieTree[BIN];
        private int length;
        private boolean isEnd;

        public void getLinks(List<TrieTree> stack) {
            int j = 0;
            for(int i = 0;i < BIN;i ++) {
                TrieTree node = links[i];
                if(node != null) {
                    stack.add(node);
                    // 1-问题
                    // 这个break放到这里，应该是put之后才增加j
                    if(++ j == length) break;
                }
            }
        }

        public void put(int v) {
            links[v] = new TrieTree();
            length ++;
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean contain(int v) {
            return links[v] != null;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public TrieTree get(int v) {
            return links[v];
        }
    }

}
