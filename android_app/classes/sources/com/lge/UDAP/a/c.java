package com.lge.UDAP.a;

import java.net.DatagramPacket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.zip.CRC32;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class c {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    private int e;
    private byte f;
    private int g;
    private String h;
    private String i;
    private String j;
    private String k;
    private byte l;
    private byte m;
    private byte n;
    private String o;
    private boolean p;
    private ArrayList<a> d = new ArrayList<>();
    private final int q = 17;
    private final int r = 12;
    private b s = b.ROAP_TYPE_PROTOBUFFER;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class a {
        private byte b;
        private int c;

        public a(byte b, int i) {
            this.b = b;
            this.c = i;
        }

        public byte a() {
            return this.b;
        }

        public void a(byte b) {
            this.b = b;
        }

        public void a(int i) {
            this.c = i;
        }

        public int b() {
            return this.c;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum b {
        ROAP_TYPE_HDCP,
        ROAP_TYPE_PROTOBUFFER
    }

    public c() {
    }

    public c(DatagramPacket datagramPacket) {
        byte[] data = datagramPacket.getData();
        byte[] bArr = new byte[data.length - 9];
        System.arraycopy(data, 9, bArr, 0, data.length - 9);
        ByteBuffer allocate = ByteBuffer.allocate(data.length);
        allocate.order(com.lge.UDAP.a.a.b);
        allocate.put(data);
        allocate.position(0);
        this.e = allocate.getInt();
        this.f = allocate.get();
        this.g = allocate.getInt();
        byte[] bArr2 = new byte[17];
        allocate.get(bArr2);
        this.h = new String(bArr2);
        byte[] bArr3 = new byte[12];
        allocate.get(bArr3);
        this.i = new String(bArr3);
        byte[] bArr4 = new byte[allocate.getInt()];
        allocate.get(bArr4);
        this.j = new String(bArr4);
        byte[] bArr5 = new byte[allocate.getInt()];
        allocate.get(bArr5);
        this.k = new String(bArr5);
        this.l = allocate.get();
        this.m = allocate.get();
        this.n = allocate.get();
        for (int i = 0; i < this.n; i++) {
            this.d.add(new a(allocate.get(), allocate.getInt()));
        }
        this.o = datagramPacket.getAddress().getHostAddress();
        this.p = a(this.e, bArr, this.g);
    }

    private boolean a(int i, byte[] bArr, int i2) {
        CRC32 crc32 = new CRC32();
        crc32.update(bArr, 0, i2);
        return i == ((int) crc32.getValue());
    }

    public int a() {
        return this.e;
    }

    public void a(b bVar) {
        this.s = bVar;
    }

    public void a(String str) {
        this.o = str;
    }

    public void a(ArrayList<a> arrayList) {
        this.d = arrayList;
    }

    public boolean b() {
        return this.p;
    }

    public byte c() {
        return this.f;
    }

    public String d() {
        return this.h;
    }

    public String e() {
        return this.i;
    }

    public String f() {
        return this.j;
    }

    public String g() {
        return this.k;
    }

    public byte h() {
        return this.l;
    }

    public byte i() {
        return this.m;
    }

    public ArrayList<a> j() {
        return this.d;
    }

    public String k() {
        return this.o;
    }

    public b l() {
        return this.s;
    }
}
