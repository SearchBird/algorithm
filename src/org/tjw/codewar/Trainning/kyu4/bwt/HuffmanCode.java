package org.tjw.codewar.Trainning.kyu4.bwt;

import java.util.*;

public class HuffmanCode {

    public static void main(String[] args) {
        TreeNode hTree = new TreeNode(-1, -1); // 哈夫曼码表
        byte[] bArr = "banananabababanewtfaqfweqfcwgwfxzcvsarewcvfdsagwerqavasdarewgqgdsafdsqrewazzddeewwfqhhfdsareweedafdsatafdasfashfllananbana".getBytes();
        for(byte i : bArr) System.out.print(Integer.toBinaryString(i));
        System.out.println();
        BurrowsWheeler.BWT bwt = BurrowsWheeler.encode("banananabababanewtfaqfweqfcwgwfxzcvsarewcvfdsagwerqavasdarewgqgdsafdsqrewazzddeewwfqhhfdsareweedafdsatafdasfashfllananbana");
        System.out.println(bwt);
        int[] mtfRes = Movetofront.encode(bwt);
        for(int i : mtfRes) System.out.print(i + " ");
        System.out.println();
        int[] rlcRes = RunLengthCode.encode(mtfRes);
        for(int i : rlcRes) System.out.print(i + " ");
        System.out.println();
        String res = encode(rlcRes, hTree);
        System.out.print(res);
        System.out.println();
        System.out.println();

        // 解码
        // 解哈夫曼
        int[] deArr = decode(res, hTree);
        for(int i : deArr) System.out.print(i + " ");
        System.out.println();
        // 解词频
        int[] marr = RunLengthCode.decode(deArr);
        for(int i : marr) System.out.print(i + " ");
        System.out.println();
        // 解mtf
        String mStr = Movetofront.decode(marr);
        System.out.println(mStr);
        // 解bwt
        String dRes = BurrowsWheeler.decode(mStr, bwt.index);
        System.out.println(dRes);
    }

    public static String encode(int[] arr, TreeNode hTree) {
        int len = arr.length;
        // 1、再处理词频
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0;i < len;i ++) map.put(arr[i], map.getOrDefault(arr[i], 0) + arr[i]);
        List<TreeNode> upStack = new ArrayList<>();
        for(int i : map.keySet()) upStack.add(new TreeNode(i, map.get(i)));
        // 2、词频排序
        Collections.sort(upStack, new Comparator<TreeNode>() {
            @Override
            public int compare(TreeNode o1, TreeNode o2) {
                return o1.rank - o2.rank;
            }
        });
        // 3、构建哈夫曼树
        while(upStack.size() > 1) {
            TreeNode o1 = upStack.remove(upStack.size() - 1);
            TreeNode o2 = upStack.remove(upStack.size() - 1);
            TreeNode oo = new TreeNode(0, o1.rank + o2.rank);
            oo.left = o1;
            oo.right = o2;
            int size = upStack.size();
            while(size - 1 >= 0 && upStack.get(size - 1).rank < oo.rank) size --;
            upStack.add(size, oo);
        }
        // 4、获取哈夫曼编码
        TreeNode root = upStack.get(0);
        hTree.left = root.left;
        hTree.right = root.right;
        hTree.rank = root.rank;
        hTree.val = root.val;
        StringBuilder builder = new StringBuilder();
        Map<Integer, String> sMap = new HashMap<>();
        recurtion(sMap, builder, upStack.get(0), 0);
        // 5、对游程编码后的数组编码
        for(int i = 0;i < len;i ++)
                builder.append(sMap.get(arr[i]));
        return builder.toString();
    }

    private static void recurtion(Map<Integer, String> sMap, StringBuilder tempBuilder, TreeNode root, int v) {
        if(root != null) {
            tempBuilder.append(v);
            boolean nlf = root.left == null;
            boolean nrf = root.right == null;
            if(nlf && nrf) {
                sMap.put(root.val, tempBuilder.toString());
            } else {
                recurtion(sMap, tempBuilder, root.left, 0);
                recurtion(sMap, tempBuilder, root.right, 1);
            }
            tempBuilder.setLength(tempBuilder.length() - 1);
        }
    }

    public static int[] decode(String str, TreeNode hTree) {
        int len = str.length();
        if(len == 1) return new int[]{hTree.val};
        char[] chs = str.toCharArray();
        List<Integer> res = new ArrayList<>();
        boolean isRoot = true;
        TreeNode temp = hTree;
        for(int i = 0;i < len;) {
            boolean nlf = temp.left == null;
            boolean nrf = temp.right == null;
            if(nlf && nrf) {
                res.add(temp.val);
                temp = hTree;
                isRoot = true;
            } else {
                if(isRoot) isRoot = false;
                else temp = chs[i] == '0' ? temp.left : temp.right;
                i ++;
            }
        }
        res.add(temp.val);
        int size = res.size();
        int[] resArr = new int[size];
        for(int i = 0;i < size;i ++) resArr[i] = res.get(i);
        return resArr;
    }

    static class TreeNode {
        int val;
        int rank;
        TreeNode left;
        TreeNode right;
        TreeNode(int val, int rank){
            this.val = val;
            this.rank = rank;
        }
    }
}
