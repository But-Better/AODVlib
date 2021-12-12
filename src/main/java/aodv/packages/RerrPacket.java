package aodv.packages;

public class RerrPacket extends Packet {
    private int pathCount;
    private int[] destAdresses;
    private int[] destSequences;
    private static final byte PADDING_BLOCK = (byte)0;
}
