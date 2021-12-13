package aodv.Handle;

import aodv.packet.*;

import aodv.packet.Packet;
import aodv.packet.RreqPacket;
import aodv.row.ReversRoutingRow;
import aodv.row.RoutingTabelle;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalTime;
import java.util.List;
import java.util.Base64;

public class Util {

    /**
     * How long must a message wait before can send it
     *
     * @param timer = e.g 60 = 1minute
     * @return datetime.now()+random*timer
     */
    public static LocalTime waitingPeriod(int timer) {
        LocalTime time = LocalTime.now();
        return time.plusSeconds((int) (Math.random() * timer));
    }

    /**
     * @return <a href="https://datatracker.ietf.org/doc/html/rfc3561">AODV RFC look at page 11</a>
     */
    public static int twosComplementSubtract(int a, int b) {
        return a + (~b + 1);
    }


    /**
     * returns the type of the message as an integer
     *
     * @param msg that is recieved
     * @return 0 for RREQ, 1 for RREP, 2 for RERR, 3 for MSG, 4 for ACK
     */
    public static int getPacketType(byte[] msg) {
        if (msg[0] % 2 == 1) {
            return ((msg[0] - 1) / (int) Math.pow(2, 4));
        }
        return ((msg[0]) / (int) Math.pow(2, 4));
    }

    /**
     * Add Routing information from RREQ to {@link RoutingTabelle}
     *
     * @param rreqPacket = {@link RreqPacket}
     */
    public static void callbackOfRREQ(RreqPacket rreqPacket) {
        List<ReversRoutingRow> row = RoutingTabelle.getInstance().getReversRoutingRows();

        int index = 0;
        boolean nodeNotFound = true;
        for (ReversRoutingRow routingRow : row) {
            if (rreqPacket.getOriginAddress() == routingRow.getSource()
                    && rreqPacket.getRequestId() == routingRow.getRreqId()
            ) {
                if (rreqPacket.getHopCount() < routingRow.getMetrix()) {
                    RoutingTabelle.getInstance().add(
                            new ReversRoutingRow(
                                    rreqPacket.getDestAddress(),
                                    rreqPacket.getHopCount(),
                                    rreqPacket.getOriginAddress(),
                                    rreqPacket.getRequestId(),
                                    rreqPacket.getPrevHopAddress()),
                            index);
                }
            } else {
                nodeNotFound = false;
            }
            index++;
        }

        if (nodeNotFound) {
            RoutingTabelle.getInstance().add(
                    new ReversRoutingRow(
                            rreqPacket.getDestAddress(),
                            rreqPacket.getHopCount(),
                            rreqPacket.getOriginAddress(),
                            rreqPacket.getRequestId(),
                            rreqPacket.getPrevHopAddress())
            );
        }
    }

    private <T extends Packet> byte[] objectToByteArray(T packet) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        byte[] arr = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(packet);
            out.flush();
            arr = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }
        return arr;
    }

    /**
     * returns a String base64 encoded message ready to be sent
     * @param pack the package object
     * @return a package encoded as a base 64 String
     */
    public static String encodePackage(Packet pack){
        switch (pack.getMessageType()){
            case 0:
                return rreqToBase64String((RreqPacket) pack);
            case 1:
                return rrepToBase64String((RreqPacket) pack);
            case 2:
                return rerrToBase64String((RerrPacket) pack);
            case 3:
                return msgToBase64String((MsgPacket) pack);
            case 4:
                return ackToBase64String((AckPacket) pack);
            default:
                return null;
        }
    }

    public static String rreqToBase64String(RreqPacket rreq){
        byte[] rreqAsByteArray = new byte[9];
        rreqAsByteArray[0] = (byte)rreq.getFlags();
        //rreqAsByteArray[0] += (byte) Math.pow(2,4) * rreq.getMessageType();
        rreqAsByteArray[1] = -1;
        rreqAsByteArray[2] = (byte)rreq.getPrevHopAddress();
        rreqAsByteArray[3] = (byte)rreq.getRequestId();
        rreqAsByteArray[4] = (byte)rreq.getDestAddress();
        rreqAsByteArray[5] = (byte)rreq.getDestSequence();
        rreqAsByteArray[6] = (byte)rreq.getHopCount();
        rreqAsByteArray[7] = (byte)rreq.getOriginAddress();
        rreqAsByteArray[8] = (byte)rreq.getOriginSequence();

        //encode to base 64:
        String rreqAsBase64String = Base64.getEncoder().encodeToString(rreqAsByteArray);
        return rreqAsBase64String;
    }

    private static String rrepToBase64String(RreqPacket rrep){
        byte[] rrepAsByteArray = new byte[9];
        rrepAsByteArray[0] = 16;
        rrepAsByteArray[1] = (byte)rrep.getHopAddress();
        rrepAsByteArray[2] = (byte)rrep.getPrevHopAddress();

        rrepAsByteArray[3] = (byte)rrep.getRequestId();
        rrepAsByteArray[4] = (byte)rrep.getDestAddress();
        rrepAsByteArray[5] = (byte)rrep.getDestSequence();

        rrepAsByteArray[6] = (byte)rrep.getHopCount();
        rrepAsByteArray[7] = (byte)rrep.getOriginAddress();
        rrepAsByteArray[8] = (byte)rrep.getOriginSequence();

        String rrepAsBase64String = Base64.getEncoder().encodeToString(rrepAsByteArray);
        return rrepAsBase64String;
    }

    private static String rerrToBase64String(RerrPacket rerr){
        int arraySize = rerr.getDestAdresses().length*3 +3;
        byte[] rerrAsByteArray = new byte[arraySize];

        rerrAsByteArray[0] = 32;
        rerrAsByteArray[1] = (byte)rerr.getHopAddress();
        rerrAsByteArray[2] = (byte)rerr.getPrevHopAddress();

        rerrAsByteArray[3] = (byte)rerr.getPathCount();
        rerrAsByteArray[4] = (byte)rerr.getDestAdresses()[0];
        rerrAsByteArray[5] = (byte)rerr.getDestSequences()[0];

        int startHere = 5;
        for(int i = 1; i < rerr.getDestAdresses().length; ++i){
            rerrAsByteArray[startHere+i] = (byte)rerr.getDestAdresses()[i];
            rerrAsByteArray[++startHere+i] = (byte)rerr.getDestSequences()[i];
            rerrAsByteArray[++startHere+i] = 0;
        }

        String rerrAsBase64String = Base64.getEncoder().encodeToString(rerrAsByteArray);
        return rerrAsBase64String;
    }

    private static String msgToBase64String(MsgPacket msg){
        int arrayLength = 6 + msg.getText().length();
        byte[] msgAsByteArray = new byte[arrayLength];

        msgAsByteArray[0] = 32+16;
        msgAsByteArray[1] = (byte)msg.getHopAddress();
        msgAsByteArray[2] = (byte)msg.getPrevHopAddress();

        msgAsByteArray[3] = (byte)msg.getDestAddress();
        msgAsByteArray[4] = (byte)msg.getOriginSequence();
        msgAsByteArray[5] = (byte)msg.getHopAddress();

        String firstPartOfReturn = Base64.getEncoder().encodeToString(msgAsByteArray);
        return firstPartOfReturn + msg.getText();
    }

    private static String ackToBase64String(AckPacket ack){
        byte[] ackAsByteArray = new byte[3];

        ackAsByteArray[0] = 64;
        ackAsByteArray[1] = (byte)ack.getHopAddress();
        ackAsByteArray[2] = (byte)ack.getPrevHopAddress();

        String ackAsBAse64String = Base64.getEncoder().encodeToString(ackAsByteArray);
        return ackAsBAse64String;
    }




}
