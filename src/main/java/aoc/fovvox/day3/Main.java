package aoc.fovvox.day3;

import aoc.fovvox.ExecutionTimer;
import aoc.fovvox.Util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    static void main() throws IOException {
        List<String> ranges = Util.parseLines("inputs/day3.txt");
        ExecutionTimer timer = new ExecutionTimer();
        timer.start();
        long result = part1(ranges);
        timer.stop();
        System.out.println("Part1: " + result);
        System.out.println(timer.getFormatted());
        System.out.println();

        timer.start();
        result = part2(ranges);
        timer.stop();
        System.out.println("Part2: " + result);
        System.out.println(timer.getFormatted());
        System.out.println();
    }


    //too high: 17462(repeating the same number)
    //correct: 17301
    private static long part1(List<String> banks) {
        long result = 0;

        for (String bank : banks) {
//            System.out.print(bank + ": ");
            int[] batteries = Arrays.stream(bank.split("")).mapToInt(Integer::parseInt).toArray();
            long jolts = maxJolts(batteries, 2);
//            System.out.println(jolts);
            result += jolts;
        }

        return result;
    }

    private static long part2(List<String> banks) {
        long result = 0;

        for (String bank : banks) {
//            System.out.print(bank + ": ");
            int[] batteries = Arrays.stream(bank.split("")).mapToInt(Integer::parseInt).toArray();
            long jolts = maxJolts(batteries, 12);
//            System.out.println(jolts);
            result += jolts;
        }

        return result;
    }


    private static long maxJolts(int[] batteries, int amountToEnable) {
        int[] indexes = new int[amountToEnable];
        indexes[0] = findIndexOfFirstMaxNumber(batteries, 0, batteries.length - amountToEnable);
        ;
        for (int i = 1; i < amountToEnable; i++) {
            int index = findIndexOfFirstMaxNumber(batteries, indexes[i - 1] + 1, batteries.length - (amountToEnable - i));
            indexes[i] = index;
        }

        long result = 0;
        for (int i = 0; i < indexes.length; i++) {
            result += (long) batteries[indexes[i]] * Math.powExact(10L, indexes.length - i - 1);
        }
        return result;
    }


    private static int findIndexOfFirstMaxNumber(int[] arr, int start, int end) {

        for (int i = 9; i > 0; i--) {
            for (int j = start; j <= end; j++) {
                if (arr[j] == i) {
                    return j;
                }
            }
        }

        return -1;
    }


}

