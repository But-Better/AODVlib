package aodv.packet;

public class AckPacket extends Packet {
    public AckPacket(int flags, int hopAddress, int prevHopAddress) {
        super(4, flags, hopAddress, prevHopAddress);
    }

    public AckPacket() {
    }
}
