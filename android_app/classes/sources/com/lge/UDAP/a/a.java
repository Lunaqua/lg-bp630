package com.lge.UDAP.a;

import android.os.Handler;
import android.os.Message;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.ByteOrder;
import java.util.Enumeration;
import java.util.StringTokenizer;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class a {
    public static ByteOrder b = ByteOrder.BIG_ENDIAN;
    private int f;
    private String k;
    private String m;
    private String n;
    private byte o;
    private Handler p;
    private DatagramSocket c = null;
    private final int d = 9740;
    private final int e = 2000;
    private boolean g = false;
    private byte[] h = null;
    private final int i = 1024;
    private InterfaceC0064a j = null;
    public String a = null;
    private String l = null;
    private final int q = 1;
    private final int r = 2;
    private final int s = 3;

    /* renamed from: com.lge.UDAP.a.a$a  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface InterfaceC0064a {
        void a(int i);

        void a(c cVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class b {
        private String b;
        private String c;

        public b(String str, String str2) {
            this.b = str;
            this.c = str2;
        }

        public String a() {
            return this.b;
        }

        public String b() {
            return this.c;
        }
    }

    public a(String str, String str2, String str3, byte b2) {
        this.k = null;
        this.k = str;
        this.m = str2;
        this.n = str3;
        this.o = b2;
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(byte[] bArr) {
        if (this.c == null) {
            return -1;
        }
        boolean z = false;
        int i = -1;
        while (!z) {
            DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length);
            System.out.println("recvPacket created.");
            try {
                System.out.println("start receive here");
                this.c.setSoTimeout(this.f);
                this.c.receive(datagramPacket);
                System.out.println("Socket received packet.");
                if (!this.g) {
                    System.out.println("not recvStopFlag");
                    Message obtain = Message.obtain(this.p);
                    c cVar = new c(datagramPacket);
                    PrintStream printStream = System.out;
                    printStream.println("crc32 result: " + cVar.b());
                    if (cVar.b()) {
                        obtain.what = 2;
                    } else {
                        obtain.what = 1;
                    }
                    obtain.obj = cVar;
                    this.p.sendMessage(obtain);
                }
            } catch (SocketException e) {
                PrintStream printStream2 = System.out;
                printStream2.println("Discovery receive SocketException:" + e);
                e.printStackTrace();
            } catch (SocketTimeoutException e2) {
                PrintStream printStream3 = System.out;
                printStream3.println("Discovery receive SocketTimeoutException:" + e2);
                z = true;
                i = 0;
            } catch (IOException e3) {
                PrintStream printStream4 = System.out;
                printStream4.println("Discovery receive IOException:" + e3);
                e3.printStackTrace();
            }
        }
        return i;
    }

    private int b(String str) {
        if (str == null) {
            return -1;
        }
        this.a = str;
        this.g = false;
        this.l = e();
        PrintStream printStream = System.out;
        printStream.println("localIpAddress:" + this.a);
        if (this.a == null || this.k == null || this.l == null || this.m == null || this.n == null || this.j == null) {
            return -1;
        }
        new Thread(new Runnable() { // from class: com.lge.UDAP.a.a.2
            @Override // java.lang.Runnable
            public void run() {
                Message obtain = Message.obtain(a.this.p);
                System.out.println("Before sendPacket! ");
                if (a.this.g() >= 0) {
                    int a = a.this.a(new byte[1024]);
                    if (a >= 0) {
                        if (a == 0) {
                            obtain.what = 3;
                        }
                        a.this.p.sendMessage(obtain);
                    }
                }
                obtain.what = 1;
                a.this.p.sendMessage(obtain);
            }
        }).start();
        return 0;
    }

    private void c() {
        this.p = new Handler() { // from class: com.lge.UDAP.a.a.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                InterfaceC0064a interfaceC0064a;
                int i;
                super.handleMessage(message);
                int i2 = message.what;
                if (i2 == 1) {
                    interfaceC0064a = a.this.j;
                    i = -1;
                } else if (i2 == 2) {
                    a.this.j.a((c) message.obj);
                    return;
                } else if (i2 != 3) {
                    return;
                } else {
                    interfaceC0064a = a.this.j;
                    i = 0;
                }
                interfaceC0064a.a(i);
            }
        };
    }

    private int d() {
        b f = f();
        if (f == null) {
            return -1;
        }
        this.a = f.a();
        this.g = false;
        this.l = e();
        PrintStream printStream = System.out;
        printStream.println("localIpAddress:" + this.a);
        if (this.a == null || this.k == null || this.l == null || this.m == null || this.n == null || this.j == null) {
            return -1;
        }
        new Thread(new Runnable() { // from class: com.lge.UDAP.a.a.3
            @Override // java.lang.Runnable
            public void run() {
                Message obtain = Message.obtain(a.this.p);
                System.out.println("Before sendPacket! ");
                if (a.this.g() >= 0) {
                    int a = a.this.a(new byte[1024]);
                    if (a >= 0) {
                        if (a == 0) {
                            obtain.what = 3;
                        }
                        a.this.p.sendMessage(obtain);
                    }
                }
                obtain.what = 1;
                a.this.p.sendMessage(obtain);
            }
        }).start();
        return 0;
    }

    private String e() {
        String str = this.a;
        if (str == null) {
            return null;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, ".");
        String str2 = com.lge.media.launcher.a.d;
        for (int i = 0; i < 3; i++) {
            str2 = str2 + stringTokenizer.nextToken() + ".";
        }
        return str2 + "255";
    }

    private b f() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement2 = inetAddresses.nextElement();
                    if (!nextElement2.isLoopbackAddress() && !nextElement.getName().contains("usb") && (nextElement2 instanceof Inet4Address)) {
                        return new b(nextElement2.getHostAddress(), new String(com.lge.media.launcher.a.d));
                    }
                    PrintStream printStream = System.out;
                    printStream.println("ip:" + nextElement2.getHostAddress());
                }
            }
            return null;
        } catch (SocketException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int g() {
        this.h = new com.lge.UDAP.a.b(this.k, this.m, this.n, this.o).a();
        try {
            DatagramPacket datagramPacket = new DatagramPacket(this.h, this.h.length, InetAddress.getByName(this.l), 9740);
            PrintStream printStream = System.out;
            printStream.println("sendPacket created." + InetAddress.getByName(this.l));
            this.c = new DatagramSocket();
            this.c.send(datagramPacket);
            return 0;
        } catch (Exception e) {
            PrintStream printStream2 = System.out;
            printStream2.println("Discovery send Exception:" + e);
            e.printStackTrace();
            return -1;
        }
    }

    public int a() {
        this.f = 2000;
        return d();
    }

    public int a(int i) {
        this.f = i;
        return d();
    }

    public int a(String str) {
        this.f = 2000;
        return b(str);
    }

    public void a(InterfaceC0064a interfaceC0064a) {
        this.j = interfaceC0064a;
    }

    public void b() {
        this.g = true;
    }
}
