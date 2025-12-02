package aoc.fovvox;

public class ExecutionTimer {
    private long startTime;
    private long endTime;
    private boolean running = false;

    public void start() {
        startTime = System.nanoTime();
        running = true;
    }

    public void stop() {
        endTime = System.nanoTime();
        running = false;
    }

    public long getNano() {
        return running ? System.nanoTime() - startTime : endTime - startTime;
    }

    public double getMillis() {
        return getNano() / 1_000_000.0;
    }

    public double getSeconds() {
        return getNano() / 1_000_000_000.0;
    }

    public String getFormatted() {
        return String.format("Elapsed: %d ns | %.3f ms | %.6f s",
            getNano(), getMillis(), getSeconds());
    }
}
