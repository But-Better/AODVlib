package aodv.packet;

public class RerrPacket extends Packet {
    private int pathCount;
    private int[] destAdresses;
    private int[] destSequences;
    private static final byte PADDING_BLOCK = (byte)0;

    public void setPathCount(int pathCount) {
        this.pathCount = pathCount;
    }

    public void setDestAdresses(int[] destAdresses) {
        this.destAdresses = destAdresses;
    }

    public void setDestSequences(int[] destSequences) {
        this.destSequences = destSequences;
    }

    public int getPathCount() {
        return pathCount;
    }

    public int[] getDestAdresses() {
        return destAdresses;
    }

    public int[] getDestSequences() {
        return destSequences;
    }

    public RerrPacket(int flags, int hopAddress, int prevHopAddress, int pathCount, int[] destAdresses, int[] destSequences) {
        super(2, flags, hopAddress, prevHopAddress);
        this.pathCount = pathCount;
        this.destAdresses = destAdresses;
        this.destSequences = destSequences;
    }

    public RerrPacket() {
    }
}
