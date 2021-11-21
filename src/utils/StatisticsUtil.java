package utils;

import java.util.Collections;
import java.util.List;

public class StatisticsUtil {

    private StatisticsUtil() {

    }

    public static double median(List<Integer> list) {
        Collections.sort(list);
        double median;
        if (list.size() % 2 == 0) {
            int bottom = list.get(list.size() / 2 - 1);
            int top = list.get(list.size() / 2);
            median = (bottom + top) / 2.0;
        } else {
            median = list.get((list.size() + 1) / 2 - 1);
        }
        return median;
    }

    public static double lowerQuartile(List<Integer> list) {
        Collections.sort(list);
        // Get index of median
        double medianIndex;
        if (list.size() % 2 == 0) {
            medianIndex = list.size() / 2.0 - 0.5;
        } else medianIndex = (list.size() + 1) / 2.0 - 1;
        // Lower quartile
        List<Integer> lowerList = list.subList(0, (int) Math.round(medianIndex + 0.1));
        return median(lowerList);
    }

    public static double upperQuartile(List<Integer> list) {
        Collections.sort(list);
        // Get index of median
        double medianIndex;
        if (list.size() % 2 == 0) {
            medianIndex = list.size() / 2.0 - 0.5;
        } else medianIndex = (list.size() + 1) / 2.0 - 1;
        // Upper quartile
        List<Integer> upperList = list.subList((int) Math.round(medianIndex + 0.1), list.size());
        return median(upperList);
    }
}
