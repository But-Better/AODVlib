package aodv.packages;

public abstract class Packet {

    private int messageType;
    private int flags;
    private int hopAddress;
    private int prevHopAddress;

    public int getHopAddress() {
        return hopAddress;
    }

    public int getMessageType() {
        return messageType;
    }

    public int getFlags() {
        return flags;
    }

    public void setHopAddress(int hopAddress) {
        this.hopAddress = hopAddress;
    }

    public int getPrevHopAddress() {
        return prevHopAddress;
    }

    public void setPrevHopAddress(int prevHopAddress) {
        this.prevHopAddress = prevHopAddress;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }
}
