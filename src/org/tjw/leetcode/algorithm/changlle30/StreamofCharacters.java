package org.tjw.leetcode.algorithm.changlle30;

public class StreamofCharacters{

    public static void main(String[] args) {
        StreamChecker st = new StreamofCharacters().new StreamChecker(new String[]{"cd","f","kl"});
        System.out.println(st.query('a'));
        System.out.println(st.query('b'));
        System.out.println(st.query('c'));
        System.out.println(st.query('d'));
        System.out.println(st.query('e'));
        System.out.println(st.query('f'));
        System.out.println(st.query('g'));
        System.out.println(st.query('h'));
        System.out.println(st.query('i'));
        System.out.println(st.query('j'));
        System.out.println(st.query('k'));
        System.out.println(st.query('l'));
        System.out.println(st.query('m'));
    }

    class StreamChecker {

        Node root;
        StringBuilder builder;

        StreamChecker(String[] words) { // 构造函数中先创建字典树
            root = new Node();
            builder = new StringBuilder();
            for(String str : words) {
                Node temp = root;
                for(int i = str.length(); i -- > 0;) { // 从每个 String 最后一个开始创建
                    int index = str.charAt(i) - 'a';
                    if(temp.chil[index] == null) {
                        temp.chil[index] = new Node();
                    }
                    temp = temp.chil[index];
                }
                temp.isEnd = true;
            }
        }

        public boolean query(char letter) {
            int index = letter - 'a';
            builder.append(letter); // 每次输入一个 char 都用 builder 存起来
            int len = builder.length() - 1;
            Node temp = root;
            if(temp.chil[index] != null) { // 然后进行后缀匹配
                temp = temp.chil[index];
                if(temp.isEnd) return true;
                A:while(true) {
                    int ch;
                    if(len != 0) ch = builder.charAt(-- len) - 'a';
                    else break;
                    for(int i = 0;i < 26;i ++) {
                        Node node = temp.chil[i];
                        if(node != null && ch == i) {
                            if(node.isEnd) return true;
                            temp = temp.chil[i];continue A;
                        }
                    }
                    break;
                }
            }
            return false;
        }
    }

    class Node { // 字典树节点
        Node[] chil;
        boolean isEnd;
        public Node() {
            chil = new Node[26];
            isEnd = false;
        }
    }
}
