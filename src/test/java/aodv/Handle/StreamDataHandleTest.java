package aodv.Handle;

import aodv.packet.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamDataHandleTest {

    @Test
    void testReadDataTraffic(){
        //RREQ msg
        byte[] incommingMSG = new byte[9];
        incommingMSG[0] = 0;
        incommingMSG[1] = -128;
        incommingMSG[2] = 1;
        incommingMSG[3] = 2;
        incommingMSG[4] = 3;
        incommingMSG[5] = 4;
        incommingMSG[6] = 5;
        incommingMSG[7] = 6;
        incommingMSG[8] = 7;

        Packet packet = StreamDataHandle.readDataTraffic(incommingMSG);
        assertEquals(0,packet.getMessageType());
        RreqPacket rreqPacket = (RreqPacket) packet;

        assertEquals(0,rreqPacket.getMessageType());
        assertEquals(0,rreqPacket.getFlags());
        assertEquals(-128,rreqPacket.getHopAddress());
        assertEquals(1,rreqPacket.getPrevHopAddress());
        assertEquals(2,rreqPacket.getRequestId());
        assertEquals(3,rreqPacket.getDestAddress());
        assertEquals(4,rreqPacket.getDestSequence());
        assertEquals(5,rreqPacket.getHopCount());
        assertEquals(6,rreqPacket.getOriginAddress());
        assertEquals(7,rreqPacket.getOriginSequence());

        //RREP packet
        incommingMSG = new byte[9];
        incommingMSG[0] = 17;
        incommingMSG[1] = 8;
        incommingMSG[2] = 1;
        incommingMSG[3] = 2;
        incommingMSG[4] = 3;
        incommingMSG[5] = 4;
        incommingMSG[6] = 5;
        incommingMSG[7] = 6;
        incommingMSG[8] = 7;

        packet = StreamDataHandle.readDataTraffic(incommingMSG);
        assertEquals(1,packet.getMessageType());
        RrepPacket rrepPacket = (RrepPacket) packet;

        assertEquals(1,rrepPacket.getMessageType());
        assertEquals(1,rrepPacket.getFlags());
        assertEquals(8,rrepPacket.getHopAddress());
        assertEquals(1,rrepPacket.getPrevHopAddress());
        assertEquals(2,rrepPacket.getRequestId());
        assertEquals(3,rrepPacket.getDestAddress());
        assertEquals(4,rrepPacket.getDestSequence());
        assertEquals(5,rrepPacket.getHopCount());
        assertEquals(6,rrepPacket.getOriginAddress());
        assertEquals(7,rrepPacket.getTtl());

        //RERR packet
        incommingMSG = new byte[12];
        incommingMSG[0] = 32; //type + flag
        incommingMSG[1] = 8; //hop
        incommingMSG[2] = 1; //prevhop

        incommingMSG[3] = 2; //path count
        incommingMSG[4] = 3; //destaddr 1
        incommingMSG[5] = 4; //destseq 1

        incommingMSG[6] = 5; //destaddr 2
        incommingMSG[7] = 6; //destseq 2
        incommingMSG[8] = 0; //padding

        incommingMSG[9] = 7; //destaddr 3
        incommingMSG[10] = 8; //destaddr 3
        incommingMSG[11] = 0; //padding

        packet = StreamDataHandle.readDataTraffic(incommingMSG);
        assertEquals(2,packet.getMessageType());
        RerrPacket rerrPacket = (RerrPacket) packet;

        assertEquals(2,rerrPacket.getMessageType());
        assertEquals(0,rerrPacket.getFlags());
        assertEquals(8,rerrPacket.getHopAddress());
        assertEquals(1,rerrPacket.getPrevHopAddress());
        assertEquals(2,rerrPacket.getPathCount());
        assertEquals(3,rerrPacket.getDestAdresses()[0]);
        assertEquals(4,rerrPacket.getDestSequences()[0]);

        assertEquals(5,rerrPacket.getDestAdresses()[1]);
        assertEquals(6,rerrPacket.getDestSequences()[1]);

        assertEquals(7,rerrPacket.getDestAdresses()[2]);
        assertEquals(8,rerrPacket.getDestSequences()[2]);

        //MSG packet
        incommingMSG = new byte[10];
        incommingMSG[0] = 16+32;
        incommingMSG[1] = 8;
        incommingMSG[2] = 1;
        incommingMSG[3] = 2;
        incommingMSG[4] = 3;
        incommingMSG[5] = 4;
        //msg
        incommingMSG[6] = 'A';
        incommingMSG[7] = 'B';
        incommingMSG[8] = 'C';
        incommingMSG[9] = 'D';

        packet = StreamDataHandle.readDataTraffic(incommingMSG);
        assertEquals(3,packet.getMessageType());
        MsgPacket msgPacket = (MsgPacket) packet;

        assertEquals(3,msgPacket.getMessageType());
        assertEquals(0,msgPacket.getFlags());
        assertEquals(8,msgPacket.getHopAddress());
        assertEquals(1,msgPacket.getPrevHopAddress());
        assertEquals(2,msgPacket.getDestAddress());
        assertEquals(3,msgPacket.getOriginSequence());
        assertEquals(4,msgPacket.getHopCount());
        assertEquals("ABCD",msgPacket.getText());

        //Ack packet
        incommingMSG = new byte[3];
        incommingMSG[0] = 65;
        incommingMSG[1] = 8;
        incommingMSG[2] = 1;

        packet = StreamDataHandle.readDataTraffic(incommingMSG);
        assertEquals(4,packet.getMessageType());
        AckPacket ackPacket = (AckPacket) packet;

        assertEquals(4,ackPacket.getMessageType());
        assertEquals(1,ackPacket.getFlags());
        assertEquals(8,ackPacket.getHopAddress());
        assertEquals(1,ackPacket.getPrevHopAddress());

    }


    @Test
    void testGetPacketType(){
        byte[] incommingMSG = new byte[9];
        incommingMSG[0] = 0;
        incommingMSG[1] = (byte)-128;
        incommingMSG[2] = 1;
        incommingMSG[3] = 2;
        incommingMSG[4] = 3;
        incommingMSG[5] = 4;
        incommingMSG[6] = 5;
        incommingMSG[7] = 6;
        incommingMSG[8] = 7;

        //rreq no flags
        assertEquals(0, Util.getPacketType(incommingMSG));
        //rreq with flag
        incommingMSG[0] = 1;
        assertEquals(0, Util.getPacketType(incommingMSG));

        //rrep no flags
        incommingMSG[0] = 16;
        assertEquals(1, Util.getPacketType(incommingMSG));
        //rrep with flags
        incommingMSG[0] = 17;
        assertEquals(1, Util.getPacketType(incommingMSG));

        //rerr no flags
        incommingMSG[0] = 32;
        assertEquals(2, Util.getPacketType(incommingMSG));
        //rerr with flag
        incommingMSG[0] = 33;
        assertEquals(2, Util.getPacketType(incommingMSG));

        //msg no flags
        incommingMSG[0] = 16+32;
        assertEquals(3, Util.getPacketType(incommingMSG));
        //msg with flag
        incommingMSG[0] = 16+32+1;
        assertEquals(3, Util.getPacketType(incommingMSG));

        //ack no flags
        incommingMSG[0] = 64;
        assertEquals(4, Util.getPacketType(incommingMSG));
        //ack with flag
        incommingMSG[0] = 64+1;
        assertEquals(4, Util.getPacketType(incommingMSG));
    }

    @Test
    void testReturnRREQ(){
        byte[] incommingMSG = new byte[9];
        incommingMSG[0] = 0;
        incommingMSG[1] = -128;
        incommingMSG[2] = 1;
        incommingMSG[3] = 2;
        incommingMSG[4] = 3;
        incommingMSG[5] = 4;
        incommingMSG[6] = 5;
        incommingMSG[7] = 6;
        incommingMSG[8] = 7;

        RreqPacket rreqPacket = StreamDataHandle.returnRREQ(incommingMSG);

        assertEquals(0,rreqPacket.getMessageType());
        assertEquals(0,rreqPacket.getFlags());
        assertEquals(-128,rreqPacket.getHopAddress());
        assertEquals(1,rreqPacket.getPrevHopAddress());
        assertEquals(2,rreqPacket.getRequestId());
        assertEquals(3,rreqPacket.getDestAddress());
        assertEquals(4,rreqPacket.getDestSequence());
        assertEquals(5,rreqPacket.getHopCount());
        assertEquals(6,rreqPacket.getOriginAddress());
        assertEquals(7,rreqPacket.getOriginSequence());
    }

    @Test
    void testReturnRREP(){
        byte[] incommingMSG = new byte[9];
        incommingMSG[0] = 17;
        incommingMSG[1] = 8;
        incommingMSG[2] = 1;
        incommingMSG[3] = 2;
        incommingMSG[4] = 3;
        incommingMSG[5] = 4;
        incommingMSG[6] = 5;
        incommingMSG[7] = 6;
        incommingMSG[8] = 7;

        RrepPacket rrepPacket = StreamDataHandle.returnRREP(incommingMSG);

        assertEquals(1,rrepPacket.getMessageType());
        assertEquals(1,rrepPacket.getFlags());
        assertEquals(8,rrepPacket.getHopAddress());
        assertEquals(1,rrepPacket.getPrevHopAddress());
        assertEquals(2,rrepPacket.getRequestId());
        assertEquals(3,rrepPacket.getDestAddress());
        assertEquals(4,rrepPacket.getDestSequence());
        assertEquals(5,rrepPacket.getHopCount());
        assertEquals(6,rrepPacket.getOriginAddress());
        assertEquals(7,rrepPacket.getTtl());
    }

    @Test
    void testRERRHandler(){
        byte[] incommingMSG = new byte[12];
        incommingMSG[0] = 32; //type + flag
        incommingMSG[1] = 8; //hop
        incommingMSG[2] = 1; //prevhop

        incommingMSG[3] = 2; //path count
        incommingMSG[4] = 3; //destaddr 1
        incommingMSG[5] = 4; //destseq 1

        incommingMSG[6] = 5; //destaddr 2
        incommingMSG[7] = 6; //destseq 2
        incommingMSG[8] = 0; //padding

        incommingMSG[9] = 7; //destaddr 3
        incommingMSG[10] = 8; //destaddr 3
        incommingMSG[11] = 0; //padding

        RerrPacket rerrPacket = StreamDataHandle.returnRERR(incommingMSG);

        assertEquals(2,rerrPacket.getMessageType());
        assertEquals(0,rerrPacket.getFlags());
        assertEquals(8,rerrPacket.getHopAddress());
        assertEquals(1,rerrPacket.getPrevHopAddress());
        assertEquals(2,rerrPacket.getPathCount());
        assertEquals(3,rerrPacket.getDestAdresses()[0]);
        assertEquals(4,rerrPacket.getDestSequences()[0]);

        assertEquals(5,rerrPacket.getDestAdresses()[1]);
        assertEquals(6,rerrPacket.getDestSequences()[1]);

        assertEquals(7,rerrPacket.getDestAdresses()[2]);
        assertEquals(8,rerrPacket.getDestSequences()[2]);

    }

    @Test
    void testMSGHandler(){
        byte[] incommingMSG = new byte[10];
        incommingMSG[0] = 16+32;
        incommingMSG[1] = 8;
        incommingMSG[2] = 1;
        incommingMSG[3] = 2;
        incommingMSG[4] = 3;
        incommingMSG[5] = 4;
        //msg
        incommingMSG[6] = 'A';
        incommingMSG[7] = 'B';
        incommingMSG[8] = 'C';
        incommingMSG[9] = 'D';

        MsgPacket msgPacket = StreamDataHandle.returnMSG(incommingMSG);

        assertEquals(3,msgPacket.getMessageType());
        assertEquals(0,msgPacket.getFlags());
        assertEquals(8,msgPacket.getHopAddress());
        assertEquals(1,msgPacket.getPrevHopAddress());
        assertEquals(2,msgPacket.getDestAddress());
        assertEquals(3,msgPacket.getOriginSequence());
        assertEquals(4,msgPacket.getHopCount());
        assertEquals("ABCD",msgPacket.getText());

    }

    @Test
    void testAckHandler(){
        byte[] incommingMSG = new byte[3];
        incommingMSG[0] = 65;
        incommingMSG[1] = 8;
        incommingMSG[2] = 1;

        AckPacket ackPacket = StreamDataHandle.returnAck(incommingMSG);

        assertEquals(4,ackPacket.getMessageType());
        assertEquals(1,ackPacket.getFlags());
        assertEquals(8,ackPacket.getHopAddress());
        assertEquals(1,ackPacket.getPrevHopAddress());

    }

}
