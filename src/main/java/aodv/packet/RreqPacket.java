package aodv.packet;

public class RreqPacket extends ReqRepBase {
    private int originSequence;

    public void setOriginSequence(int originSequence) {
        this.originSequence = originSequence;
    }

    public int getOriginSequence() {
        return originSequence;
    }
}
