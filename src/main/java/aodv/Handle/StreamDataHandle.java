package aodv.Handle;

import aodv.packages.RrepPacket;
import aodv.packages.RreqPacket;

import java.util.Base64;

public class StreamDataHandle {



    public String readDataTraffic(byte[] msg) {
        return analysePacket(Base64.getEncoder().encodeToString(msg));
    }

    private String analysePacket(String text) {
        switch (text) {
            case "1":
                return "new AckPacket();";
        }
        return null;
    }

    /**
     * returns the type of the message as an integer
     * @param msg that is recieved
     * @return 0 for RREQ, 1 for RREP, 2 for RERR, 3 for MSG, 4 for ACK
     */
    public int getPacketType(byte[] msg){
        if(msg[0]%2==1){
            return ((msg[0] - 1) / (int) Math.pow(2, 4));
        }
        return ((msg[0]) / (int) Math.pow(2, 4));
    }

    /**
     * takes a byte array that is a rreq message and turns it into an rreq object
     * @param rreq message as a byte array
     * @return rreq object
     */
    public RreqPacket returnRREQ(byte[] rreq){

        RreqPacket returnRreq = new RreqPacket();

        int type = 0;
        int flags = returnFlags(rreq[0]);

        returnRreq.setMessageType(type);
        returnRreq.setFlags(flags);
        returnRreq.setHopAddress(rreq[1]);
        returnRreq.setPrevHopAddress(rreq[2]);
        returnRreq.setRequestId(rreq[3]);
        returnRreq.setDestAddress(rreq[4]);
        returnRreq.setDestSequence(rreq[5]);
        returnRreq.setHopCount(rreq[6]);
        returnRreq.setOriginAddress(rreq[7]);
        returnRreq.setOriginSequence(rreq[8]);

        return returnRreq;
    }

    /**
     * takes a byte array that is a rrep message and turns it into an rrep object
     * @param rrep message as a byte array
     * @return rrep object
     */
    public RrepPacket returnRREP(byte[] rrep){
        RrepPacket rrepPacket = new RrepPacket();

        int type = 1;
        int flags = returnFlags(rrep[0]);

        rrepPacket.setMessageType(type);
        rrepPacket.setFlags(flags);
        rrepPacket.setHopAddress(rrep[1]);
        rrepPacket.setPrevHopAddress(rrep[2]);
        rrepPacket.setRequestId(rrep[3]);
        rrepPacket.setDestAddress(rrep[4]);
        rrepPacket.setDestSequence(rrep[5]);
        rrepPacket.setHopCount(rrep[6]);
        rrepPacket.setOriginAddress(rrep[7]);
        rrepPacket.setTtl(rrep[8]);

        return rrepPacket;
    }

    private int returnFlags(byte typeAndFlags){
        if(typeAndFlags%2==1){
            return 1;
        }
        return 0;
    }

}