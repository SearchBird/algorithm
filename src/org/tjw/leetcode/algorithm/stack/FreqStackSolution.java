package org.tjw.leetcode.algorithm.stack;

import org.tjw.leetcode.algorithm.Test;

import java.util.*;

public class FreqStackSolution {
    public static void main(String[] args) throws Throwable {
        FreqStackSolution.FreqStack s = new FreqStackSolution().new FreqStack();
        s.push(1);
        s.push(1);
        s.push(1);
        s.push(2);
        System.out.println(s.pop());
        System.out.println(s.pop());
        s.push(2);
        s.push(2);
        s.push(1);
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.pop());
    }

    private static List<Integer> indexList = new ArrayList<>(); // 暂存 频次 索引栈
    private static List<List<Integer>> freValsStack = new ArrayList<>(); // 暂存 频次 - 数据组
    class FreqStack {
        int maxFre; // 用于记录频次, 降低创建 ArrayList 次数
        Map<Integer, Integer> cache; // 暂存频率节点

        public FreqStack() {
            maxFre = 0;
            cache = new HashMap<>();
        }

        public void push(int x) {
            Integer fre = cache.getOrDefault(x, 0);
            if(fre == freValsStack.size()) {
                freValsStack.add(new ArrayList<>());
                indexList.add(0);
                freValsStack.get(fre).add(x);
            } else {
                Integer index = indexList.get(fre) + 1;
                indexList.set(fre, index);
                List<Integer> list = freValsStack.get(fre);
                if(index == list.size()) list.add(x);
                else list.set(index, x);
            }
            cache.put(x, fre + 1);
            maxFre = Math.max(maxFre, fre + 1);
        }

        public int pop() {
            if(maxFre == 0) return -1;
            // 移除频次最多
            List<Integer> topList = freValsStack.get(maxFre - 1);
            Integer index = indexList.get(maxFre - 1);
            Integer res = topList.get(index);
            indexList.set(maxFre - 1, index - 1);
            // 频次 - 1
            if(index == 0) maxFre --;
            cache.put(res, cache.get(res) - 1);
            return res;
        }
    }

    class FreqStack2 {
        int top;
        List<Integer> stack; // 暂存数据
        Map<Integer, Integer> cache; // 暂存频率节点
        PriorityQueue<Integer> queue; // 频率优先
        Map<Integer, Queue<Integer>> indexs; // 索引优先

        public FreqStack2() {
            queue = new PriorityQueue<>((i1, i2) -> i2 - i1);
            indexs = new HashMap<>();
            cache = new HashMap<>();
            stack = new ArrayList<>();
        }

        public void push(int x) {
            stack.add(x);
            Integer fre;
            // 获取该数值频率
            fre = cache.getOrDefault(x, 0) + 1;
            cache.put(x, fre);

            if(indexs.containsKey(fre)) { // 增加该频率优先索引
                Queue<Integer> q = indexs.get(fre);
                q.offer(top ++);
            } else {
                Queue<Integer> q = new PriorityQueue<>((t1, t2) -> t2 - t1);
                q.offer(top ++);
                indexs.put(fre, q);
            }
            // 添加优先频率
            queue.offer(fre);
        }

        public int pop() {
            if(queue.isEmpty()) return -1;
            Integer fre = queue.poll();
            Integer index = indexs.get(fre).poll();
            Integer res = stack.get(index);
            cache.put(res, cache.get(res) - 1);
            return res;
        }
    }
}
