import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestMergeSort {

    public static void main(String[] args) {
        int size = 10000;
        for (int i = 0; i < 5; i++) {
            long start = System.currentTimeMillis();
            if (runTest(size, new ParallelMergeSort())) {
                System.out.println("Test Passed");
            } else {
                System.out.println("Test Failed!!!!");
            }
            long end = System.currentTimeMillis();
            System.out.println("The test took " + (end - start) + " millis");
            size *= 10;
        }
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
        System.out.println("List Size:" + size);
        for (int i = 0; i < size; i++) {
            if (i > 0 && list.get(i) < list.get(i-1)) {
                return false;
            }
        }
        return true;
    }
}