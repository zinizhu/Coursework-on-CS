package hash;

import edu.princeton.cs.algs4.In;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int N = oomages.size();
        double upperBound = N/2.5;
        double lowerBound = N/50;
        int[] buckets = new int[M];
        for (int i = 0; i < M; i += 1) {
            buckets[i] = 0;
        }

        for (int i = 0; i < N; i += 1) {
            Oomage o = oomages.get(i);
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            buckets[bucketNum] += 1;
        }

        int index = 0;
        while (index < M) {
            if (buckets[index] > upperBound || buckets[index] < lowerBound) {
                return false;
            }
            M += 1;
        }
        return true;
    }
}
