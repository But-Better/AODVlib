package aodv.Handle.row;

import aodv.row.ReversRoutingRow;
import aodv.row.RoutingRow;
import aodv.row.RoutingTabelle;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class RowTabelleTest {

    @Disabled
    @Test
    public void viewRows(){
        RoutingTabelle routingTabelle = RoutingTabelle.getInstance();
        routingTabelle.add(new ReversRoutingRow());
        routingTabelle.add(new RoutingRow());
    }

}
