package aodv.packet;

public class RrepPacket extends ReqRepBase {
    private int ttl;

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public RrepPacket(int flags, int hopAddress, int prevHopAddress, int requestId, int destAddress, int destSequence, int hopCount, int originAddress, int ttl) {
        super(1, flags, hopAddress, prevHopAddress, requestId, destAddress, destSequence, hopCount, originAddress);
        this.ttl = ttl;
    }

    public RrepPacket() {
    }
}
