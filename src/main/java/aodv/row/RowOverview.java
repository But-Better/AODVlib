package aodv.row;

import java.util.Arrays;

public class RowOverview implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println(Arrays.stream(RoutingRow.class.getClasses()).iterator());
            System.out.println(Arrays.stream(ReversRoutingRow.class.getClasses()).iterator());
        }
    }
}
