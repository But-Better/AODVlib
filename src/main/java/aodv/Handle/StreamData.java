package aodv.Handle;

public class StreamData {

    public synchronized void handle(byte[] packets) {
        StreamDataHandle.readDataTraffic(packets);
    }
}
