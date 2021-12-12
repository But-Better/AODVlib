package aodv.Handle;

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

    public RreqPacket showRREQ(byte[] rreq){

        int type = 0;
        int flags;
        if(rreq[0]%2==1){
            flags = 1;
        }
        else{
            flags = 0;
        }
        int hopAddress = byte[1];



        RreqPacket rreqPacket = new RreqPacket();
        rreqPacket.setMessageType(0);

        rreqPacket.se
    }

}