package Kalaha;

import java.util.Comparator;
import java.util.List;

public class SortScoreBoardByName <T extends Comparable<T>> implements Comparator<List<String>> {



    @Override
    public int compare(List<String> l1, List<String> l2) {

        return l1.get(0).compareTo(l2.get(0));
    }
    
}
