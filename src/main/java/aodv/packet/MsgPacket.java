package aodv.packet;

public class MsgPacket extends Packet {
    private int destAddress;
    private int originSequence;
    private int hopCount;
    private String text;

    public int getDestAddress() {
        return destAddress;
    }

    public int getOriginSequence() {
        return originSequence;
    }

    public int getHopCount() {
        return hopCount;
    }

    public String getText() {
        return text;
    }

    public void setDestAddress(int destAddress) {
        this.destAddress = destAddress;
    }

    public void setOriginSequence(int originSequence) {
        this.originSequence = originSequence;
    }

    public void setHopCount(int hopCount) {
        this.hopCount = hopCount;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MsgPacket(int flags, int hopAddress, int prevHopAddress, int destAddress, int originSequence, int hopCount, String text) {
        super(3, flags, hopAddress, prevHopAddress);
        this.destAddress = destAddress;
        this.originSequence = originSequence;
        this.hopCount = hopCount;
        this.text = text;
    }

    public MsgPacket() {
    }
}
