package aodv.row;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class RoutingTabelle {

    private static RoutingTabelle instance = null;
    private List<RoutingRow> routingRows = null;
    private List<ReversRoutingRow> reversRoutingRows = null;
    private final Logger log = LoggerFactory.getLogger(RoutingTabelle.class);

    private RoutingTabelle() {
        this.routingRows = new LinkedList<>();
        this.reversRoutingRows = new LinkedList<>();
    }

    public static RoutingTabelle getInstance() {
        if (instance == null)
            instance = new RoutingTabelle();

        return instance;
    }

    public boolean add(Row row, int index) {
        if (row instanceof ReversRoutingRow) {
            log.info(() -> "add instanceof ReversRoutingRow");
            this.reversRoutingRows.add(index, (ReversRoutingRow) row);
            return true;
        }

        if (row instanceof RoutingRow) {
            log.info(() -> "add instanceof RoutingRow");
            this.routingRows.add(index, (RoutingRow) row);
            return true;
        }
        return false;
    }

    public boolean add(Row row) {
        if (row instanceof ReversRoutingRow) {
            log.info(() -> "add instanceof ReversRoutingRow");
            return this.reversRoutingRows.add((ReversRoutingRow) row);
        }

        if (row instanceof RoutingRow) {
            log.info(() -> "add instanceof RoutingRow");
            return this.routingRows.add((RoutingRow) row);
        }
        return false;
    }

    public boolean remove(Row row) {
        if (row instanceof ReversRoutingRow) {
            log.info(() -> "remove instanceof ReversRoutingRow");
            return this.reversRoutingRows.remove((ReversRoutingRow) row);
        }

        if (row instanceof RoutingRow) {
            log.info(() -> "remove instanceof RoutingRow");
            return this.routingRows.remove((RoutingRow) row);
        }

        return false;
    }

    public List<RoutingRow> getRoutingRows() {
        return new LinkedList<>(routingRows);
    }

    public void setRoutingRows(List<RoutingRow> routingRows) {
        this.routingRows = routingRows;
    }

    public List<ReversRoutingRow> getReversRoutingRows() {
        return new LinkedList<>(reversRoutingRows);
    }

    public void setReversRoutingRows(List<ReversRoutingRow> reversRoutingRows) {
        this.reversRoutingRows = reversRoutingRows;
    }

    public boolean isEmptyReversRoutingRow() {
        return this.reversRoutingRows.isEmpty();
    }

    public boolean isEmptyRoutingRows() {
        return this.routingRows.isEmpty();
    }

    @Override
    public String toString() {
        return "RoutingTabelle{" +
                "routingRows=" + routingRows +
                ", reversRoutingRows=" + reversRoutingRows +
                '}';
    }
}
