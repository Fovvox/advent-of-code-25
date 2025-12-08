package aoc.fovvox.day7;

import aoc.fovvox.ExecutionTimer;
import aoc.fovvox.Util;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    static void main() throws IOException {
        List<String> parts = Util.parseLines("inputs/day7.txt");
        List<List<Integer>> splitters = parseSplitters(parts);
        int start = parts.getFirst().indexOf("S");
        ExecutionTimer timer = new ExecutionTimer();
        timer.start();
        long result = part1(start, splitters);
        timer.stop();
        System.out.println("Part1: " + result);
        System.out.println(timer.getFormatted());
        System.out.println();
//        parameters = parsePart2(parts);
//        timer.start();
//        result = calculate(operators, parameters);
//        timer.stop();
//        System.out.println("Part2: " + result);
//        System.out.println(timer.getFormatted());
//        System.out.println();
        
    }

    static private long part1(int start, List<List<Integer>> splitters) {
        Set<Integer> lasers = new HashSet<>();
        lasers.add(start);
        int splits = 0;
//        System.out.println(lasers);
        for (List<Integer> splitterLine : splitters) {
          Set<Integer> newLasers = new HashSet<>();
          for (Integer laser : lasers) {
            if (splitterLine.contains(laser)) {
                newLasers.add(laser - 1);
                newLasers.add(laser + 1);
                splits++;
            } else {
                newLasers.add(laser);
            }
          }
          lasers = newLasers;
//            System.out.println(lasers);
        }
        return  splits;
    }

    private static List<List<Integer>> parseSplitters(List<String> parts) {

        List<List<Integer>> splitters = new ArrayList<>();
        for (int i = 2; i < parts.size(); i+=2) {
            List<Integer> splitterLine = new ArrayList<>();
            for (int j = 0; j < parts.get(i).length(); j++) {
                if (parts.get(i).charAt(j) == '^') {
                    splitterLine.add(j);
                }
            }
            splitters.add(splitterLine);
        }

        return splitters;
    }
}
