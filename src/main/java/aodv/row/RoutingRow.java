package aodv.row;

import aodv.Handle.Util;

import java.util.Arrays;
import java.util.Objects;

public class RoutingRow extends Row {

    private int nextHop;
    private int[] precursors;
    private int sequenceNumber;
    private boolean isValid = true;

    public RoutingRow() {
        super(-1, -1);
    }

    /**
     * @param destination    = Finales ziel
     * @param metrix         = Hops bis zum ziel
     * @param nextHop        = Nächstes geräte
     * @param precursors     = Wer verwendet die Route
     * @param sequenceNumber = Epoche oder auch Aktualität
     *                       Die sequence number wird als unsigned gezählt aber
     *                       als sigend (zweierkomplement) interpretiert
     * @see "Util.twosComplementSubtract()"
     * @param isValid        = check of sequenceNumber
     */
    public RoutingRow(int destination, int metrix, int nextHop, int[] precursors, int sequenceNumber, boolean isValid) {
        super(destination, metrix);
        this.nextHop = nextHop;
        this.precursors = precursors;
        this.sequenceNumber = sequenceNumber;
        this.isValid = isValid;
    }

    public int getNextHop() {
        return nextHop;
    }

    public void setNextHop(int nextHop) {
        this.nextHop = nextHop;
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
        return Util.twosComplementSubtract(a, b);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RoutingRow that = (RoutingRow) o;
        return nextHop == that.nextHop && sequenceNumber == that.sequenceNumber && isValid == that.isValid && Arrays.equals(precursors, that.precursors);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), nextHop, sequenceNumber, isValid);
        result = 31 * result + Arrays.hashCode(precursors);
        return result;
    }

    @Override
    public String toString() {
        return "RoutingRow{" +
                "NextHop=" + nextHop +
                ", precursors=" + Arrays.toString(precursors) +
                ", sequenceNumber=" + sequenceNumber +
                ", isValid=" + isValid +
                '}';
    }
}
