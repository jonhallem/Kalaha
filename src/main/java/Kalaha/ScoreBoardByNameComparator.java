package Kalaha;

import java.util.Comparator;
import java.util.List;

public class ScoreBoardByNameComparator <T extends Comparable<T>> implements Comparator<List<String>> {

    //Source: https://stackoverflow.com/questions/35761864/java-sort-list-of-lists

    @Override
    public int compare(List<String> l1, List<String> l2) {

        return l1.get(0).compareTo(l2.get(0));
    }
    
}
