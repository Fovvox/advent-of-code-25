package aoc.fovvox.day5;

import aoc.fovvox.ExecutionTimer;
import aoc.fovvox.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static void main() throws IOException {
        List<String> parts = Util.parse("inputs/day5.txt", "\n\n");
        List<Range> ranges = Arrays.stream(parts.getFirst().split("\n"))
            .map(s -> s.split("-"))
            .map(strings ->
                new Range(Long.parseLong(strings[0]), Long.parseLong(strings[1])))
            .collect(Collectors.toList());
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

        timer.start();
        result = part2(ranges);
        timer.stop();
        System.out.println("Part2: " + result);
        System.out.println(timer.getFormatted());
        System.out.println();
    }

    //too high 675484123462146
    private static long part2(List<Range> ranges) {
        ranges.sort(Comparator.comparingLong(r -> r.start));
        List<Range> mergedRanges = new ArrayList<>();
        long curStart = ranges.getFirst().start;
        long curEnd = ranges.getFirst().end;

        for (int i = 1; i < ranges.size(); i++) {
            if (ranges.get(i).start <= curEnd) {
                curEnd = Math.max(ranges.get(i).end, curEnd);
            } else {
                mergedRanges.add(new Range(curStart, curEnd));
                curStart = ranges.get(i).start;
                curEnd = ranges.get(i).end;
            }
        }
        mergedRanges.add(new Range(curStart, curEnd));
//        for (Range r : ranges) {
//            System.out.println(r + " : " + r.size());
//        }
//        System.out.println("------------------");
//        for (Range r : mergedRanges) {
//            System.out.println(r + " : " + r.size());
//        }

        return mergedRanges.stream().mapToLong(Range::size).sum();
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

        long size() {
            return end - start + 1;
        }

        @Override
        public String toString() {
            return start + "-" + end;
        }
    }
}
