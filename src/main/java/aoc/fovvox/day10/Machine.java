package aoc.fovvox.day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import aoc.fovvox.Util;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class Machine {
    private final String initialString;
    private String targetState;
    private List<Action> actions;
    private List<Integer> targetJoltage;

    public static Machine parse(final String string) {
        final String[] parts = string.split(" ");
        final String targetState = parts[0].replaceAll("\\[|\\]", "");
        final List<Action> actions = new ArrayList<>();
        for (int i = 1; i < parts.length - 1 ; i++) {
            final String part = parts[i].replaceAll("\\(|\\)","");
            final List<Integer> toggles = Arrays.stream(part.split(",")).map(Integer::parseInt).toList();
            actions.add(new Action(toggles));
        }
        final String joltageString = parts[parts.length -1 ].replaceAll("\\{|\\}", "");
        final List<Integer> joltage = new ArrayList<>(Arrays.stream(joltageString.split(",")).map(Integer::parseInt).toList());
        return new Machine(targetState, actions, joltage, string);
    }

    private Machine(final String targetState, final List<Action> actions, final List<Integer> joltage, final String initialString) {
        this.targetState = targetState;
        this.actions = actions;
        this.targetJoltage = joltage;
        this.initialString = initialString;
    }

    public int enable() {
        List<String> states = List.of(".".repeat(targetState.length()));
        List<String> nextStates = new ArrayList<>();
        int steps = 1;

        while (true) {
            for (final String state : states) {
                for (final Action action : actions) {
                    final String newState = action.apply(state);
                    if (targetState.equals(newState)) {
                        return steps;
                    }
                    nextStates.add(newState);
                }
            }
            states = nextStates;
            nextStates = new ArrayList<>();
            steps++;
        }
    }

    public int configureJoltage() {
        System.out.println(this);
        final double[][] coefficients = new double[targetJoltage.size()][];
        for (int i = 0; i < coefficients.length; i++) {
            coefficients[i] = new double[actions.size()];
//            if (i< actions.size()) {
                for (final Integer toggle : actions.get(i).toggles) {
                    coefficients[i][toggle] = 1;
                }
//            }
        }
        final double[] targetData = targetJoltage.stream().mapToDouble(Integer::intValue).toArray();

        final RealMatrix coefficientsMatrix = new Array2DRowRealMatrix(coefficients);
        System.out.println("Coeficient: \n" + coefficientsMatrix);
        final RealVector target = new ArrayRealVector(targetData);
        System.out.println("target: \n" + target);


        final DecompositionSolver solver = new QRDecomposition(coefficientsMatrix).getSolver();

        // Solve Ax = b
        RealVector x = solver.solve(target);


        return (int) Arrays.stream(x.toArray()).sum();
    }

    public String getTargetState() {
        return targetState;
    }

    public void setTargetState(final String targetState) {
        this.targetState = targetState;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(final List<Action> actions) {
        this.actions = actions;
    }

    public List<Integer> getTargetJoltage() {
        return targetJoltage;
    }

    public void setTargetJoltage(final List<Integer> targetJoltage) {
        this.targetJoltage = targetJoltage;
    }

    @Override
    public String toString() {
        return initialString;
    }

    private boolean joltageOverflow(final List<Integer> joltage) {
        for (int i = 0; i < joltage.size(); i++) {
            if (joltage.get(i) > targetJoltage.get(i)) {
                return true;
            }
        }
        return false;
    }

    private static class Action {
        List<Integer> toggles;

        public Action(final List<Integer> toggles) {
            this.toggles = toggles;
        }

        public String apply(final String state) {
            final char[] lamps = state.toCharArray();
            for (final Integer toggle : toggles) {
                if (lamps[toggle] == '#') {
                    lamps[toggle] = '.';
                } else {
                    lamps[toggle] = '#';
                }
            }
            return new String(lamps);
        }

        public List<Integer> applyJoltage(final List<Integer> joltage) {
            final List<Integer> newJoltage = new ArrayList<>(List.copyOf(joltage));
            for (final Integer toggle : toggles) {
                newJoltage.set(toggle, newJoltage.get(toggle)+1);
            }
            return newJoltage;
        }

        public List<Integer> getToggles() {
            return toggles;
        }

        public void setToggles(final List<Integer> toggles) {
            this.toggles = toggles;
        }
    }
}
