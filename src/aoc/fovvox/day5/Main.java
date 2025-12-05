package aoc.fovvox.day5;

import aoc.fovvox.ExecutionTimer;
import aoc.fovvox.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static void main() throws IOException {
        List<String> parts = Util.parse("inputs/day5.txt", "\n\n");
        List<Range> ranges = Arrays.stream(parts.getFirst().split("\n"))
            .map(s -> s.split("-"))
            .map(strings ->
                new Range(Long.parseLong(strings[0]), Long.parseLong(strings[1])))
            .toList();
        List<Long> ids = Arrays.stream(parts.getLast().split("\n"))
            .mapToLong(Long::parseLong)
            .boxed()
            .toList();

        ExecutionTimer timer = new ExecutionTimer();
        timer.start();
        long result = part1(ranges, ids);
        timer.stop();
        System.out.println("Part1: " + result);
        System.out.println(timer.getFormatted());
        System.out.println();
//
//        rolls = Util.parseStringMatrix("inputs/day4.txt");
//        timer.start();
//        result = part2(rolls);
//        timer.stop();
//        System.out.println("Part2: " + result);
//        System.out.println(timer.getFormatted());
//        System.out.println();
    }

    private static long part1(List<Range> ranges, List<Long> ids) {
        int count = 0;

        for (Long id : ids) {
            for (Range range : ranges) {
                if (range.contains(id)) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }


    private static class Range {
        long start;
        long end;

        public Range(long start, long end) {
            this.start = start;
            this.end = end;
        }

        boolean contains(long value) {
            return value >= start && value <= end;
        }
    }
}
