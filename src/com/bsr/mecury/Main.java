package com.bsr.mecury;

import com.payneteasy.tlv.*;

public class Main {

    public static void main(String[] args) {
      //byte e=  new UInt16(22).;

        byte[] data=intToByteArray(400);//new byte[2];
//        data[0] = (byte) (400 & 0xFF);
//        data[1] = (byte) ((400 >> 8) & 0xFF);
        byte[] d=new byte[4];
       d[0] = (byte) 400;
       d[1] = (byte) (400>>> 8);
       d[2] = (byte) (400>>> 16);
       d[3] = (byte) (400>>> 24);
       int rt=byteArrayToInt(data);
       int yu=byteArrayToInt(d);
       // System.out.println(String.format( String.format("%8s", Integer.toBinaryString(data[0] & 0xFF)).replace(' ', '0')));
       // System.out.println(String.format( String.format("%8s", Integer.toBinaryString(data[1] & 0xFF)).replace(' ', '0')));
       // String.format("%02X ", b);
        int ff=((data[2]  & 0xff) << 8) | (data[3]  & 0xff);
        int num = Integer.parseInt("0x53h", 16);

        BerTlvs f= new BerTlvBuilder()
                .addHex(new BerTag(0x50), "56495341")
                .addHex(new BerTag(0x58), "1000023100000033D44122011003400000481F")
                .buildTlvs();
        BerTlvBuilder fwf=new  BerTlvBuilder() ;
        fwf.addBerTlv(new BerTlv(new BerTag(0x60),f.getList()));


        byte[] bytes =  fwf

                .buildArray();

        BerTlvParser parser = new BerTlvParser();
        BerTlvs tlvs = parser.parse(bytes, 0, bytes.length);
       int fff=0;

    }

    public static byte[] intToByteArray(int a)
    {
        byte[] ret = new byte[4];
        ret[3] = (byte) (a & 0xFF);
        ret[2] = (byte) ((a >> 8) & 0xFF);
        ret[1] = (byte) ((a >> 16) & 0xFF);
        ret[0] = (byte) ((a >> 24) & 0xFF);
        return ret;
    }

    public static int byteArrayToInt(byte[] b) {
        if (b.length == 4)
            return b[0] << 24 | (b[1] & 0xff) << 16 | (b[2] & 0xff) << 8
                    | (b[3] & 0xff);
        else if (b.length == 2)
            return 0x00 << 24 | 0x00 << 16 | (b[0] & 0xff) << 8 | (b[1] & 0xff);

        return 0;
    }


}
