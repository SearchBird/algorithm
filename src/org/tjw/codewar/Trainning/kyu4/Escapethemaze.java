package org.tjw.codewar.Trainning.kyu4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Escapethemaze {

    public static void main(String[] args) {

        System.out.println(escape(new char[][]
                {{' ', ' ', '#', '#', '#','#', ' ','#'},
                 {'#', '#', ' ', ' ', ' ','#', ' ','#'},
                 {' ', '#', ' ', '#', ' ','#', ' ',' '},
                 {' ', '#', ' ', '#', '<','#', '#','#'},
                 {' ', '#', ' ', ' ', '#','#', '#','#'},
                 {' ', '#', ' ', ' ', '#','#', '#','#'},
                 {' ', ' ', ' ', ' ', ' ',' ', ' ',' '},
                 {'#', '#', '#', '#', '#','#', '#','#'}}));
    }
    private static int x, xd, y, yd, begin;
    private static int bond[][];
    private static boolean free = false;
    public static List<Character> escape(char[][] maze) {
        // Have a nice sleep... ;)
        x = maze.length;
        y = maze[0].length;
        xd = maze.length - 1;
        int xde = xd * y;
        yd = maze[0].length - 1;
        bond = new int[x][y];
        for(int j = 1;j < yd;j ++)
            if(maze[0][j] == ' ') {
                bond[0][j] = j; begin = j;
            }

        int mx = 0, my = 0;
        for(int i = 1;i < xd;i ++) {
            boolean lineFind = false;
            for(int j = begin, temp = 0;++ temp < yd; j = (begin + temp) % yd) {
                if(j == 0) continue;
                char t = maze[i][j];
                boolean me = t == '<' || t == '>' || t == 'V' || t == '^', find = false;
                if(me) {
                    mx = i; my = j;
                }
                if(t == ' ' || me) {
                    if(bond[i - 1][j] > 0) {
                        bond[i][j] = bond[i - 1][j]; lineFind = find = true;
                    }
                    else if(bond[i][j - 1] > 0){
                        bond[i][j] = bond[i][j - 1]; lineFind = find = true;
                    }
                    else if(bond[i][j + 1] > 0) {
                        bond[i][j] = bond[i][j + 1]; lineFind = find = true;
                    }
                    if(me && find) {
                        List<Character> move = checkMove(
                                recurtion(mx, my, new boolean[x][y], bond[mx][my], maze[mx][my], maze, new ArrayList<Character>()));
                        return move;
                    }
                }
            }
            if(!lineFind) break;
        }
        return Collections.EMPTY_LIST;
    }

    private static List<Character> checkMove(List<Character> characters) {
        int len = characters.size();
        List<Character> newChars = new ArrayList<>(len);
        for(int i = 1;i < len;i ++) {
            char res = checkDirect(characters.get(i), characters.get(i - 1));
            newChars.add(res);
            if(res != 'F') newChars.add('F');
        }
        return newChars;
    }

    private static char checkDirect(char cur, char before) {
        switch(before) {
            case '<' : {
                switch(cur) {
                    case '<' : return 'F';
                    case '>' : return 'B';
                    case 'V' : return 'L';
                    case '^' : return 'R';
                }
            }
            case '>' : {
                switch(cur) {
                    case '<' : return 'B';
                    case '>' : return 'F';
                    case 'V' : return 'R';
                    case '^' : return 'L';
                }
            }
            case 'V' : {
                switch(cur) {
                    case '<' : return 'R';
                    case '>' : return 'L';
                    case 'V' : return 'F';
                    case '^' : return 'B';
                }
            }
            case '^' : {
                switch(cur) {
                    case '<' : return 'L';
                    case '>' : return 'R';
                    case 'V' : return 'B';
                    case '^' : return 'F';
                }
            }
        }
        return cur;
    }

    private static List<Character> recurtion(int mx, int my, boolean[][] booleans, int i, char site, char[][] maze, ArrayList<Character> characters) {
        characters.add(site);
        booleans[mx][my] = true;
        if(mx == 0 || mx == xd || my == 0 || my == yd) {
            free = true;
            return characters;
        }
        if(!booleans[mx - 1][my] && bond[mx - 1][my] == i) {
            recurtion(mx - 1, my, booleans, i, '^', maze, characters);
            if(free) return characters;
            else characters.remove(characters.size() - 1);
        }

        if(!booleans[mx + 1][my] && bond[mx + 1][my] == i) {
            recurtion(mx + 1, my, booleans, i, 'V', maze, characters);
            if(free) return characters;
            else characters.remove(characters.size() - 1);
        }


        if(!booleans[mx][my - 1] && bond[mx][my - 1] == i) {
            recurtion(mx, my - 1, booleans, i, '<', maze, characters);
            if(free) return characters;
            else characters.remove(characters.size() - 1);
        }

        if(!booleans[mx][my + 1] && bond[mx][my + 1] == i) {
            recurtion(mx, my + 1, booleans, i, '>', maze, characters);
            if(free) return characters;
            else characters.remove(characters.size() - 1);
        }
        return characters;
    }

    static class Point{
        int x;
        int y;
        Point[] next = new Point[3];
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
