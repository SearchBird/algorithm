package org.tjw.leetcode.algorithm.changlle30;

import org.tjw.leetcode.algorithm.bitnarySearch.other.FindRightInterval;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CloneGraph {


    public static void main(String[] args) {
        CloneGraph pp = new CloneGraph();
        Solution s = pp.new Solution();

        Node node = pp.new Node();
        node.val = 1;
        Node node2 = pp.new Node();
        node2.val = 2;
        Node node3 = pp.new Node();
        node3.val = 3;
        Node node4 = pp.new Node();
        node4.val = 4;

        node.neighbors.add(node2);
        node2.neighbors.add(node3);
        node2.neighbors.add(node4);
        node3.neighbors.add(node2);
        node4.neighbors.add(node2);

        Node res = s.cloneGraph(node);

    }


    class Solution {
        public Node cloneGraph(Node node) {
            if (node == null) {
                return node;
            }
            HashMap<Integer, Node> cacheMap = new HashMap<Integer, Node>();
            return clone(node, cacheMap);
        }

        public Node clone(Node node, HashMap<Integer, Node> cacheMap) {
            Node newNode = new Node();
            newNode.val = node.val;
            newNode.neighbors = new ArrayList<>();
            cacheMap.put(node.val, newNode);
            for(Node ne : node.neighbors) {
                if(cacheMap.containsKey(ne.val)) {
                    newNode.neighbors.add(cacheMap.get(ne.val));
                }
                else {
                    Node one = clone(ne, cacheMap);
                    one.val = ne.val;
                    newNode.neighbors.add(one);
                }
            }
            return newNode;
        }
    }

    class Node {
        public int val;
        public List<Node> neighbors = new ArrayList<>();
    }
}
