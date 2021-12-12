package aodv.row;

import java.util.Arrays;
import java.util.Objects;

public class RoutingRow extends Row {

    private int NextHop;
    private int[] precursors;
    private int sequenceNumber;
    private boolean isValid = true;

    public RoutingRow() {
        super(-1, -1);
    }

    public RoutingRow(int destination, int metrix, int nextHop, int[] precursors, int sequenceNumber, boolean isValid) {
        super(destination, metrix);
        NextHop = nextHop;
        this.precursors = precursors;
        this.sequenceNumber = sequenceNumber;
        this.isValid = isValid;
    }

    public int getNextHop() {
        return NextHop;
    }

    public void setNextHop(int nextHop) {
        NextHop = nextHop;
    }

    public int[] getPrecursors() {
        return precursors;
    }

    public void setPrecursors(int[] precursors) {
        this.precursors = precursors;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public int twosComplementSubtract(int a, int b) {
        return a + (~b + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RoutingRow that = (RoutingRow) o;
        return NextHop == that.NextHop && sequenceNumber == that.sequenceNumber && isValid == that.isValid && Arrays.equals(precursors, that.precursors);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), NextHop, sequenceNumber, isValid);
        result = 31 * result + Arrays.hashCode(precursors);
        return result;
    }

    @Override
    public String toString() {
        return "RoutingTabelle{" +
                "NextHop=" + NextHop +
                ", precursors=" + Arrays.toString(precursors) +
                ", sequenceNumber=" + sequenceNumber +
                ", isValid=" + isValid +
                '}';
    }
}
