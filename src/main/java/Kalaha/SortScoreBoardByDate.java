package Kalaha;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class SortScoreBoardByDate <T extends Comparable<T>> implements Comparator<List<String>> {



    @Override
    public int compare(List<String> l1, List<String> l2) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        try {
            Date date1 = dateFormat.parse(l1.get(4));
            Date date2 = dateFormat.parse(l2.get(4));
            
            return date2.compareTo(date1);

        } catch (Exception e) {

            System.out.println("Error in date translation " + e);
            return 0;
            //TODO: handle exception
}

    }
    
}
