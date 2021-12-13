package aodv.packet;

public class RreqPacket extends ReqRepBase {
    private int originSequence;

    public void setOriginSequence(int originSequence) {
        this.originSequence = originSequence;
    }

    public int getOriginSequence() {
        return originSequence;
    }

    public RreqPacket(int flags, int prevHopAddress, int requestId, int destAddress, int destSequence, int hopCount, int originAddress, int originSequence) {
        super(0, flags, -1, prevHopAddress, requestId, destAddress, destSequence, hopCount, originAddress);
        this.originSequence = originSequence;
    }

    public RreqPacket() {
    }
}
