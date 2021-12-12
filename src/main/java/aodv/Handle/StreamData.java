package aodv.Handle;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

public class StreamData {

    private StreamDataHandle streamDataHandle = null;
    private final Queue<byte[]> queue = new SynchronousQueue<>();

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public StreamData() {
        this.streamDataHandle = new StreamDataHandle();
    }

    public StreamData(StreamDataHandle streamDataHandle) {
        this.streamDataHandle = streamDataHandle;
    }

    public synchronized void handle(byte[] packets) {
        this.executor.submit(() -> {
            try {
                this.queue.add(packets);
                //this.streamDataHandle.readDataTraffic(this.queue.);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
