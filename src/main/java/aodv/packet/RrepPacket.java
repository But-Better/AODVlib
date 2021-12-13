package aodv.packet;

public class RrepPacket extends ReqRepBase {
    private int ttl;

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }
}
