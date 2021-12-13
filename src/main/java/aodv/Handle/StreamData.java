package aodv.Handle;

import aodv.packet.Packet;

public class StreamData {

    public synchronized void handle(byte[] packets) {
        Packet data = StreamDataHandle.readDataTraffic(packets);
    }
}
