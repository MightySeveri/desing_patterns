import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


interface Sorter {
    void sort(int[] arr);
}

class SortingContext {
    private Sorter sorter;

    void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }

    void sort(int[] numbers) {
        sorter.sort(numbers);
    }
}

class BucketSort implements Sorter {

    @Override
    public void sort(int[] arr) {

        if (arr == null || arr.length <= 1) {
            return;
        }

        int min = arr[0];
        int max = arr[0];

        for (int value : arr) {
            if (value < min) {
                min = value;
            }

            if (value > max) {
                max = value;
            }
        }

        int bucketCount = arr.length;

        List<List<Integer>> buckets = new ArrayList<>();

        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int value : arr) {

            int bucketIndex;

            if (max == min) {
                bucketIndex = 0;
            } else {
                bucketIndex = (int) (
                    ((long) value - min) * (bucketCount - 1)
                    / ((long) max - min)
                );
            }

            buckets.get(bucketIndex).add(value);
        }

        // Insertion sort each bucket without using a built-in sort.
        for (List<Integer> bucket : buckets) {
            for (int i = 1; i < bucket.size(); i++) {
                int value = bucket.get(i);
                int j = i - 1;
                while (j >= 0 && bucket.get(j) > value) {
                    bucket.set(j + 1, bucket.get(j));
                    j--;
                }
                bucket.set(j + 1, value);
            }
        }

        int index = 0;

        for (List<Integer> bucket : buckets) {
            for (int value : bucket) {
                arr[index++] = value;
            }
        }
    }
}

class PigeonHoleSort implements Sorter {

    @Override
    public void sort(int[] arr) {

        if (arr == null || arr.length <= 1) {
            return;
        }

        int min = arr[0];
        int max = arr[0];

        for (int value : arr) {
            if (value < min) {
                min = value;
            }

            if (value > max) {
                max = value;
            }
        }

        int range = max - min + 1;

        int[] holes = new int[range];

        for (int value : arr) {
            holes[value - min]++;
        }

        int index = 0;

        for (int i = 0; i < range; i++) {
            while (holes[i] > 0) {
                arr[index++] = i + min;
                holes[i]--;
            }
        }
    }
}

class BubbleSort implements Sorter {

    @Override
    public void sort(int[] arr) {

        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {

            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {

                if (arr[j] > arr[j + 1]) {

                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {
        Random random = new Random(42);
        int[] sizes = {30, 10_000};
        SortingContext context = new SortingContext();

        for (int size : sizes) {
            int[] original = new int[size];
            for (int i = 0; i < size; i++) {
                original[i] = random.nextInt(10_000);
            }

            System.out.println("Data set: " + size + " integers");
            testSorter("Bubble Sort", new BubbleSort(), context, original);
            testSorter("Pigeonhole Sort", new PigeonHoleSort(), context, original);
            testSorter("Bucket Sort", new BucketSort(), context, original);
            System.out.println();
        }
    }

    private static void testSorter(
            String name,
            Sorter sorter,
            SortingContext context,
            int[] original) {
        int[] numbers = Arrays.copyOf(original, original.length);
        context.setSorter(sorter);
        long start = System.nanoTime();
        context.sort(numbers);
        long elapsed = System.nanoTime() - start;

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i - 1] > numbers[i]) {
                throw new IllegalStateException(name + " did not sort the data");
            }
        }
        System.out.printf("%-17s %.3f ms%n", name + ":", elapsed / 1_000_000.0);
    }
}
