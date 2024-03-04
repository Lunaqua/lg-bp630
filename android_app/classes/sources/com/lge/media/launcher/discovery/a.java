package com.lge.media.launcher.discovery;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class a {
    public static final String a = "192.168.1.255";
    public static final String b = "255.255.255.255";
    public static final int c = 9;

    public static void a(String str) {
        try {
            byte[] b2 = b(str);
            byte[] bArr = new byte[(b2.length * 16) + 6];
            for (int i = 0; i < 6; i++) {
                bArr[i] = -1;
            }
            for (int i2 = 6; i2 < bArr.length; i2 += b2.length) {
                System.arraycopy(b2, 0, bArr, i2, b2.length);
            }
            DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length, InetAddress.getByName(a), 9);
            DatagramSocket datagramSocket = new DatagramSocket();
            datagramSocket.send(datagramPacket);
            datagramPacket.setAddress(InetAddress.getByName(b));
            datagramSocket.send(datagramPacket);
            datagramSocket.close();
            System.out.println("Wake-on-LAN packet sent.");
        } catch (Exception unused) {
            System.out.println("Failed to send Wake-on-LAN packet: + e");
        }
    }

    private static byte[] b(String str) {
        byte[] bArr = new byte[6];
        String[] split = str.split("(\\:|\\-)");
        if (split.length == 6) {
            for (int i = 0; i < 6; i++) {
                try {
                    bArr[i] = (byte) Integer.parseInt(split[i], 16);
                } catch (NumberFormatException unused) {
                    throw new IllegalArgumentException("Invalid hex digit in MAC address.");
                }
            }
            return bArr;
        }
        throw new IllegalArgumentException("Invalid MAC address.");
    }
}
