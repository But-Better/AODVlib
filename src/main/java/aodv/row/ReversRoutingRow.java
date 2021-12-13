package aodv.row;

import java.util.Objects;

public class ReversRoutingRow extends Row {

    private int source;
    private int rreqId;
    private int previousHop;

    public ReversRoutingRow() {
        super(-1, -1);
    }

    /**
     *
     * @param destination =  An wem ging der RREQ
     * @param metrix = Hops bis zum Ziel
     * @param source = Wer hat RREQ gesendet
     * @param rreqId = RREQ ID
     * @param previousHop =  Von wem wurde der Hops erfasst
     */
    public ReversRoutingRow(int destination, int metrix, int source, int rreqId, int previousHop) {
        super(destination, metrix);
        this.source = source;
        this.rreqId = rreqId;
        this.previousHop = previousHop;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getRreqId() {
        return rreqId;
    }

    public void setRreqId(int rreqId) {
        this.rreqId = rreqId;
    }

    public int getPreviousHop() {
        return previousHop;
    }

    public void setPreviousHop(int previousHop) {
        this.previousHop = previousHop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ReversRoutingRow that = (ReversRoutingRow) o;
        return source == that.source && rreqId == that.rreqId && previousHop == that.previousHop;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), source, rreqId, previousHop);
    }

    @Override
    public String toString() {
        return "ReversRoutingTabelle{" +
                "source=" + source +
                ", rreqId=" + rreqId +
                ", previousHop=" + previousHop +
                '}';
    }
}
