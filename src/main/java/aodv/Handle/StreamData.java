package aodv.Handle;

import aodv.Init;
import aodv.packet.*;
import aodv.row.RoutingRow;
import aodv.row.RoutingTable;
import lora.LoraCommando;

import java.util.ArrayList;

public class StreamData {

    public synchronized ArrayList<byte[]> handle(byte[] packets) {
        Packet data = StreamDataHandle.readDataTraffic(packets);
        switch (data.getMessageType()){
            case 0: RreqPacket rreq = (RreqPacket)data;
            //What do we do with a RREQ?...
                //What happens To RT/RRT (Kevin)

                //How is the message forwarded? (Mathis)

                //is the rreq for me?
                if(rreq.getDestAddress() == Init.getAddress()){
                    //respond with RREP
                    RrepPacket rrepResponse = new RrepPacket(0,
                                                        rreq.getPrevHopAddress(), //next hop
                                                        Init.getAddress(), //prev hop address (me)
                                                        rreq.getRequestId(), //RREQ ID
                                                        rreq.getOriginAddress(), //Dest Address
                                                        rreq.getDestSequence(), //Dest Seq from RREQ
                                                        0,
                                                        Init.getAddress(), //originator Address (me)
                                                        rreq.getHopCount()); //ttl

                    String base64EncodedResponse = Util.encodePackage(rrepResponse);
                    String[] responseSendingCommand = LoraCommando.sendMsg(base64EncodedResponse);
                    //HOW DO I SEND THIS NOW?

                    return;
                }
                //do i have a valid route to the requested address? wird dann überhaupt was gemacht oder der RREQ einfach bis zur dest weitergeleitet?
                //yes
                   //send RREP
                for (RoutingRow routingRow : RoutingTable.getInstance().getRoutingRows()) {
                    if (routingRow.getDestination() == rreq.getDestAddress() && routingRow.isValid()){
                        RrepPacket rrepResponse = new RrepPacket(0,
                                rreq.getPrevHopAddress(), //next hop
                                Init.getAddress(), //prev hop address (me)
                                rreq.getRequestId(), //RREQ ID
                                rreq.getOriginAddress(), //Dest Address
                                rreq.getDestSequence(), //Dest Seq from RREQ
                                0,
                                Init.getAddress(), //originator Address (me)
                                rreq.getHopCount()); //ttl

                    }
                }
                //no
                    //Forward RREQ
                RreqPacket rreqForward = new RreqPacket(0,
                                                        Init.getAddress(),
                                                        rreq.getRequestId(),
                                                        rreq.getDestAddress(),
                                                        rreq.getDestSequence(),
                                                        rreq.getHopCount()+1,
                                                        rreq.getOriginAddress(),
                                                        rreq.getOriginSequence());
                String base64EncodedResponse = Util.encodePackage(rreqForward);
                String[] responseSendingCommand = LoraCommando.sendMsg(base64EncodedResponse);
                break;
            case 1: data = (RrepPacket)data;
            //What do we do with a RREP?...
                break;
            case 2: data = (RerrPacket)data;
            //What do we do with a RERR?...
                break;
            case 3: data = (MsgPacket)data;
            //What do we do with a MSG?...
                break;
            case 4: data = (AckPacket)data;
            //What do we do with a ACK?...
                break;
            default:
        }
    }


    /**
     * takes a RREQ and enters its values
     * @param rreq
     */
    private void answerToRREQ(RreqPacket rreq){
        if()
    }

}
