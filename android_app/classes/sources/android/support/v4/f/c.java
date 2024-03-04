package android.support.v4.f;

import android.net.TrafficStats;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.support.annotation.af;
import java.net.DatagramSocket;
import java.net.Socket;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class c {
    private c() {
    }

    @Deprecated
    public static void a() {
        TrafficStats.clearThreadStatsTag();
    }

    @Deprecated
    public static void a(int i) {
        TrafficStats.incrementOperationCount(i);
    }

    @Deprecated
    public static void a(int i, int i2) {
        TrafficStats.incrementOperationCount(i, i2);
    }

    public static void a(@af DatagramSocket datagramSocket) {
        if (Build.VERSION.SDK_INT >= 24) {
            TrafficStats.tagDatagramSocket(datagramSocket);
            return;
        }
        ParcelFileDescriptor fromDatagramSocket = ParcelFileDescriptor.fromDatagramSocket(datagramSocket);
        TrafficStats.tagSocket(new b(datagramSocket, fromDatagramSocket.getFileDescriptor()));
        fromDatagramSocket.detachFd();
    }

    @Deprecated
    public static void a(Socket socket) {
        TrafficStats.tagSocket(socket);
    }

    @Deprecated
    public static int b() {
        return TrafficStats.getThreadStatsTag();
    }

    @Deprecated
    public static void b(int i) {
        TrafficStats.setThreadStatsTag(i);
    }

    public static void b(@af DatagramSocket datagramSocket) {
        if (Build.VERSION.SDK_INT >= 24) {
            TrafficStats.untagDatagramSocket(datagramSocket);
            return;
        }
        ParcelFileDescriptor fromDatagramSocket = ParcelFileDescriptor.fromDatagramSocket(datagramSocket);
        TrafficStats.untagSocket(new b(datagramSocket, fromDatagramSocket.getFileDescriptor()));
        fromDatagramSocket.detachFd();
    }

    @Deprecated
    public static void b(Socket socket) {
        TrafficStats.untagSocket(socket);
    }
}
