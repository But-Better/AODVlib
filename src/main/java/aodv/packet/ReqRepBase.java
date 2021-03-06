package aodv.packet;

public abstract class ReqRepBase extends Packet {
    private int requestId;
    private int destAddress;
    private int destSequence;
    private int hopCount;
    private int originAddress;

    public int getRequestId() {
        return requestId;
    }

    public int getDestAddress() {
        return destAddress;
    }

    public int getDestSequence() {
        return destSequence;
    }

    public int getHopCount() {
        return hopCount;
    }

    public int getOriginAddress() {
        return originAddress;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public void setDestAddress(int destAddress) {
        this.destAddress = destAddress;
    }

    public void setDestSequence(int destSequence) {
        this.destSequence = destSequence;
    }

    public void setHopCount(int hopCount) {
        this.hopCount = hopCount;
    }

    public void setOriginAddress(int originAddress) {
        this.originAddress = originAddress;
    }

    public ReqRepBase(int messageType, int flags, int hopAddress, int prevHopAddress, int requestId, int destAddress, int destSequence, int hopCount, int originAddress) {
        super(messageType, flags, hopAddress, prevHopAddress);
        this.requestId = requestId;
        this.destAddress = destAddress;
        this.destSequence = destSequence;
        this.hopCount = hopCount;
        this.originAddress = originAddress;
    }

    public ReqRepBase() {
    }
}
