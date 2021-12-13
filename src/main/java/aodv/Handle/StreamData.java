package aodv.Handle;

import aodv.packages.Packet;

public class StreamData {

    public synchronized void handle(byte[] packets) {
        Packet data = StreamDataHandle.readDataTraffic(packets);

    }


}
