package aodv.Handle;

public class StreamData {

    private StreamDataHandle streamDataHandle = null;

    public StreamData() {
        this.streamDataHandle = new StreamDataHandle();
    }

    public StreamData(StreamDataHandle streamDataHandle) {
        this.streamDataHandle = streamDataHandle;
    }

    public synchronized void handle(byte[] packets) {
        this.streamDataHandle.readDataTraffic(packets);
    }
}
