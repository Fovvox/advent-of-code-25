package aoc.fovvox.day4;

import aoc.fovvox.ExecutionTimer;
import aoc.fovvox.Util;

import java.io.IOException;
import java.util.List;

public class Main {
    static void main() throws IOException {
        String[][] rolls = Util.parseStringMatrix("inputs/day4.txt");
        ExecutionTimer timer = new ExecutionTimer();
        timer.start();
        long result = part1(rolls);
        timer.stop();
        System.out.println("Part1: " + result);
        System.out.println(timer.getFormatted());
        System.out.println();

//        timer.start();
//        result = part2(ranges);
//        timer.stop();
//        System.out.println("Part2: " + result);
//        System.out.println(timer.getFormatted());
//        System.out.println();
    }

    //correct: 1602
    private static long part1(String[][] rolls) {
        long result = 0;
        for (int i = 0; i < rolls.length; i++) {
            for (int j = 0; j < rolls[i].length; j++) {
                if (rolls[i][j].equals(".")) {
                    continue;
                }
                int adjacentRolls = countAdjacentRolls(rolls, i, j);
                if (adjacentRolls < 4) {
                    result++;
                }
            }
        }
        return result;
    }

    private static int countAdjacentRolls(String[][] rolls, int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            if (x + i < 0 || x + i >= rolls.length) {
                continue;
            }
            for (int j = -1; j <= 1; j++) {
                if (y + j < 0 || y + j >= rolls[x].length || i ==0 && j == 0) {
                    continue;
                }
                if (rolls[x+i][y+j].equals("@")) {
                    count++;
                }
            }
        }
        return count;
    }
}
