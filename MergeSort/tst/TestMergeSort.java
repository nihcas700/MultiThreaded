import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestMergeSort {

    public static void main(String[] args) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter("./MergeSort/tst/TestMergeSortResult.txt"));
        int size = 100000000;
        writer.write("List Size:" + size + "\n");
        // Run parallel
        writer.write("Running Parallel" + "\n");
        long start = System.currentTimeMillis();
        if (runParallel(size)) {
            writer.write("Test Passed" + "\n");
        } else {
            writer.write("Test Failed!!!!" + "\n");
        }
        long end = System.currentTimeMillis();
        writer.write("The test took " + (end - start) + " millis" + "\n");
        // Divider
        writer.write("=================================" + "\n");
        // Run sequential
        writer.write("Running Sequential" + "\n");
        start = System.currentTimeMillis();
        if (runSequential(size)) {
            writer.write("Test Passed" + "\n");
        } else {
            writer.write("Test Failed!!!!" + "\n");
        }
        end = System.currentTimeMillis();
        writer.write("The test took " + (end - start) + " millis" + "\n");
        writer.flush();
        writer.close();
    }

    private static boolean runParallel(int size) {
        return runTest(size, new ParallelMergeSort());
    }

    private static boolean runSequential(int size) {
        return runTest(size, new SequentialMergeSort());
    }

    private static boolean runTest(int size, MergeSort sort) {
        // Initialize
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(1000));
        }

        // Sort
        sort.sort(list);

        // Test
        for (int i = 0; i < size; i++) {
            if (i > 0 && list.get(i) < list.get(i-1)) {
                return false;
            }
        }
        return true;
    }
}