package aoc.fovvox.day6;

import aoc.fovvox.ExecutionTimer;
import aoc.fovvox.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    static void main() throws IOException {
        List<String> parts = Util.parseLines("inputs/day6.txt");
        List<String> operators = Arrays.stream(parts.getLast().split(" +")).toList();
        List<List<Integer>> parameters = parsePart1(parts, operators);
        ExecutionTimer timer = new ExecutionTimer();
        timer.start();
        long result = calculate(operators, parameters);
        timer.stop();
        System.out.println("Part1: " + result);
        System.out.println(timer.getFormatted());
        System.out.println();
        parameters = parsePart2(parts);
        timer.start();
        result = calculate(operators, parameters);
        timer.stop();
        System.out.println("Part2: " + result);
        System.out.println(timer.getFormatted());
        System.out.println();
        
    }

    private static List<List<Integer>> parsePart2(List<String> parts) {
        List<List<Integer>> parameters = new ArrayList<>();

        for (int i = 0; i < parts.getFirst().length(); i++) {
            List<Integer> param = new ArrayList<>();
            StringBuilder num = new StringBuilder();
            for (int j = 0; j < parts.size() - 1; j++) {
                num.append(parts.get(j).charAt(i));
            }
            while (!num.toString().trim().isEmpty()) {
//                System.out.println(num);
                param.add(Integer.parseInt(num.toString().trim()));
                i++;
                num= new StringBuilder();
                for (int j = 0; j < parts.size() - 1; j++) {
                    num.append(parts.get(j).charAt(i));
                }
            }

            parameters.add(param);
        }


        return parameters;
    }

    private static List<List<Integer>> parsePart1(List<String> parts, List<String> operators) {
        List<List<Integer>> parameters =
                IntStream.range(0, operators.size())
                        .mapToObj(i -> new ArrayList<Integer>())
                        .collect(Collectors.toList());
        for (int i = 0; i < parts.size() - 1; i++) {
            int[] params = Arrays.stream(parts.get(i).split(" +")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < params.length; j++) {
                parameters.get(j).add(params[j]);
            }
        }
        return parameters;
    }

    private static long calculate(List<String> operators, List<List<Integer>> parameters) {
        long result = 0;

        for (int i = 0; i < operators.size(); i++) {
            long calculation = 0;
            if (operators.get(i).equals("+")) {
                result += parameters.get(i).stream().mapToLong(n -> n).reduce(Long::sum).getAsLong();
            } else if (operators.get(i).equals("*")) {
                result += parameters.get(i).stream().mapToLong(n -> n).reduce((n1, n2) -> n1 * n2).getAsLong();
            }
            result += calculation;
        }

        return result;
    }
}
