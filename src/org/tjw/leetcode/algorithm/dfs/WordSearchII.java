package org.tjw.leetcode.algorithm.dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WordSearchII {
    public static void main(String[] args) {
        Solution solution = new WordSearchII().new Solution();

        char[][] board01 = new char[][]{
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}};
        String[] input01 = new String[]{"oath","pea","eat","rain"};
        System.out.println(solution.findWords(board01, input01));
    }

    static int x;
    static int y;
    static String[] words2;
    // 方向，为了减少代码量
    static int[][] direct = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

    // 结果集并且用hash去重
    HashSet<String> set;
    static char[][] board2;
    class Solution {

        public List<String> findWords(char[][] board, String[] words) {
            // 将words全部存入字典树
            Tree root = new Tree();
            board2 = board;
            words2 = words;
            for(int i = 0;i < words.length;i ++) {
                String str = words[i];
                Tree tree = root;
                for(char ch : str.toCharArray()) {
                    int index = ch - 'a';
                    Tree next = tree.next[index] == null ? new Tree() : tree.next[index];
                    tree.next[index] = next;
                    tree = next;
                }
                tree.end = true;
                tree.index = i;
            }

            // 将二维数组遍历一遍
            set = new HashSet<String>();
            x = board2.length;
            y = board2[0].length;
            int c = x * y;
            for(int i = 0;i < x;i ++) {
                for(int j = 0;j < y;j ++) {
                    int index = board2[i][j] - 'a';
                    if(root.next[index] != null) {
                        // 为了不重复利用，设置参观表
                        boolean[][] visited = new boolean[x][y];
                        search(root,index, i,j,visited);
                    }
                }
            }

            return new ArrayList<String>(set);
        }

        public void search(Tree root,int index, int i, int j, boolean[][] visited) {
            if(root.next[index] != null) {
                // 匹配到为true
                visited[i][j] = true;
                if(root.next[index].end) set.add(words2[root.next[index].index]);
                for(int[] arr : direct) {
                    int x1 = i + arr[0];
                    int y1 = j + arr[1];
                    // 不约边，并且不重复浏览visited
                    if(x1 >= 0 && x1 < x && y1 >= 0 && y1 < y && !visited[x1][y1]) {
                        search(root.next[index],board2[x1][y1] - 'a',x1,y1,visited);
                    }
                }
                // 处理完成变为false
                visited[i][j] = false;
            }
        }
    }

    class Tree {
        Tree[] next = new Tree[26];
        boolean end = false;
        int index = 0;
    }
}
