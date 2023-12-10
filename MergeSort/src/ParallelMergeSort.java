import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort implements MergeSort {

    @Override
    public void sort(List<Integer> list) {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        if (list.size() == 0) {
            return;
        }
        pool.invoke(new RecursiveSort(list, 0, list.size() - 1, 10000000));
    }

    public static class RecursiveSort extends RecursiveAction {
        private List<Integer> list;
        private int start;
        private int end;
        private int threshold;

        public RecursiveSort(List<Integer> list, int start, int end, int threshold) {
            this.list = list;
            this.start = start;
            this.end = end;
            this.threshold = threshold;
        }
        @Override
        protected void compute() {
            if ((end-start+1) < threshold) {
                Collections.sort(list.subList(start, end+1));
            } else {
                int mid = (start + end)/2;
                List<RecursiveSort> tasks = new ArrayList();
                tasks.add(new RecursiveSort(list, start, mid, threshold));
                tasks.add(new RecursiveSort(list, mid+1, end, threshold));
                ForkJoinTask.invokeAll(tasks);
                Utils.merge(list, start, mid, end);
            }
        }
    }
}
