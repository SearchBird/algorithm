package org.tjw.leetcode.algorithm.hashTable;

public class DesignHashSet {
    public static void main(String[] args) {
        MyHashSet set = new DesignHashSet().new MyHashSet();
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(set.contains(72));
        set.remove(91);
        set.add(48);
        set.add(41);
        System.out.println(set.contains(96));
        set.remove(87);
        System.out.println(set.contains(48));
        System.out.println(set.contains(49));
        set.add(84);set.add(82);set.add(24);set.add(7);
        set.remove(56);
        set.add(87);set.add(81);set.add(55);set.add(19);
        set.add(40);set.add(68);set.add(23);set.add(80);
        set.add(53);set.add(76);
        System.out.println(set.contains(93));
        set.add(95);
        System.out.println(set.contains(95));
        set.add(67);
        set.add(31);
        System.out.println(set.contains(80));
        set.add(62);
        set.add(73);
        set.remove(97);
        set.add(33);
        set.add(28);
        set.add(62);
        set.add(81);
        set.add(57);
        System.out.println(set.contains(40));
        set.add(11);
        set.add(89);
        set.add(28);
        set.remove(97);
        System.out.println(set.contains(86));
        set.add(20);
        System.out.println(set.contains(5));
        set.add(77);
        set.add(52);
        set.add(57);
        set.add(88);
        set.add(20);
        /// 要注意正负问题
        System.out.println(set.contains(48));
        set.remove(42);
        set.remove(86);
        set.add(49);
        set.remove(62);
        ///
        System.out.println(set.contains(53));
        set.add(43);
        set.remove(98);
        set.add(32);
        set.add(15);
        set.add(42);
        set.add(50);
        System.out.println(set.contains(19));
        System.out.println(set.contains(32));
        set.add(67);
        set.remove(84);
        set.remove(60);
        set.remove(8);
        set.remove(85);
        set.add(43);
        set.add(59);
        System.out.println(set.contains(65));
        set.add(40);
        set.add(81);
        set.remove(55);
        set.add(56);
        set.add(54);
        set.add(59);
        set.add(78);
        set.add(53);
        set.add(0);
        set.add(24);
        set.add(7);
        set.remove(53);
        set.add(33);
        set.remove(69);
        set.remove(86);
        set.add(7);
        set.remove(1);
        set.add(16);
        set.remove(58);
        set.add(61);
        set.add(34);
        set.add(53);
        set.remove(84);
        set.remove(21);
        set.remove(58);
        set.add(25);
        System.out.println(set.contains(45));
        set.add(3);
    }

    class MyHashSet {
        int BIN = 63;
        private long[] setArr = new long[15874];

        /** Initialize your data structure here. */
        public MyHashSet() {

        }

        public void add(int key) {
            int slot = key / BIN;
            long pos = 1l << (key % BIN);
            setArr[slot] |= pos;
        }

        public void remove(int key) {
            int slot = key / BIN;
            // -1是全部二进制位1的数，可作为蒙版
            long pos = (1l << (key % BIN)) ^ -1l;
            setArr[slot] &= pos;
        }

        /** Returns true if this set contains the specified element */
        public boolean contains(int key) {
            int slot = key / BIN;
            long pos = 1l << (key % BIN);
            return (setArr[slot] & pos) != 0;
        }
    }
}
