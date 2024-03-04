package com.lge.media.launcher.sp;

import android.content.Context;
import android.media.AudioTrack;
import android.media.audiofx.Equalizer;
import com.lge.UDAP.ROAP.b;
import com.lge.media.launcher.control.common.d;
import com.lge.media.launcher.control.common.e;
import com.lge.media.launcher.control.common.f;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class a {
    public static final int j = 1024;
    public static final int l = 256;
    public static final int o = 153600;
    public static final int p = 61440;
    public static final int q = 8192;
    private static e t;
    public int a;
    private long w;
    private long x;
    private int y;
    private int z;
    private static final a r = new a();
    private static b s = null;
    public static int b = 0;
    private boolean u = false;
    private boolean v = false;
    public String c = null;
    public Thread d = null;
    public Thread e = null;
    public Thread f = null;
    public DatagramSocket g = null;
    public byte[] h = null;
    public byte[] i = null;
    public int k = 0;
    public int m = 0;
    public Equalizer n = null;

    protected a() {
    }

    public static a a(Context context, e eVar) {
        t = eVar;
        return r;
    }

    static /* synthetic */ int b(a aVar, int i) {
        int i2 = aVar.z + i;
        aVar.z = i2;
        return i2;
    }

    static /* synthetic */ int d(a aVar, int i) {
        int i2 = aVar.y + i;
        aVar.y = i2;
        return i2;
    }

    public void a() {
        Thread thread = this.d;
        if (thread != null && thread.isAlive()) {
            this.d.interrupt();
        }
        Thread thread2 = this.f;
        if (thread2 != null && thread2.isAlive()) {
            this.f.interrupt();
        }
        this.v = true;
        e eVar = t;
        eVar.x = false;
        if (eVar.I == null) {
            t.I = new AudioTrack(3, this.a, 12, 2, b, 1);
        } else {
            t.I.setPlaybackRate(this.a);
            e eVar2 = t;
            eVar2.y = true;
            eVar2.G = 0;
        }
        DatagramSocket datagramSocket = this.g;
        if (datagramSocket != null) {
            datagramSocket.close();
        }
        this.i = new byte[o];
        this.y = 0;
        this.z = 0;
        this.d = new Thread(new Runnable() { // from class: com.lge.media.launcher.sp.a.1
            @Override // java.lang.Runnable
            public void run() {
                StringBuilder sb;
                String exc;
                com.lge.media.launcher.control.common.a.c("recvAudio start");
                try {
                    a.this.h = new byte[a.b];
                    DatagramPacket datagramPacket = new DatagramPacket(a.this.h, 1024);
                    a.this.g = new DatagramSocket((int) d.aK);
                    a.this.w = System.currentTimeMillis();
                    while (a.this.u && !Thread.currentThread().isInterrupted()) {
                        long currentTimeMillis = System.currentTimeMillis();
                        a.this.g.receive(datagramPacket);
                        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                        if (currentTimeMillis2 > 300) {
                            com.lge.media.launcher.control.common.a.b("Receive Time is over 300 ms, Time: " + currentTimeMillis2 + " ms, RI: " + a.this.z + ", WI: " + a.this.y);
                            if (!a.t.D) {
                                a.t.I.pause();
                                a.t.C = true;
                                a.t.G = 0;
                            }
                        }
                        if (a.this.z > 153600) {
                            a.this.z = 0;
                        } else {
                            if (a.this.z == 153600) {
                                System.arraycopy(datagramPacket.getData(), 0, a.this.i, 0, datagramPacket.getLength());
                                a.this.z = datagramPacket.getLength();
                            } else {
                                System.arraycopy(datagramPacket.getData(), 0, a.this.i, a.this.z, datagramPacket.getLength());
                                a.b(a.this, datagramPacket.getLength());
                            }
                            if (!a.this.u) {
                                return;
                            }
                            if (a.t.y) {
                                a.t.G += datagramPacket.getLength();
                                a.this.k = datagramPacket.getLength();
                                if (a.this.k == 1024) {
                                    a.this.m = 4096;
                                } else {
                                    a.this.m = 2048;
                                }
                                if (a.t.G > a.b && a.t.I.getState() == 1) {
                                    a.t.y = false;
                                    com.lge.media.launcher.control.common.a.b("Unmute Audio!!");
                                    a.t.I.play();
                                }
                            }
                            if (!a.t.x && datagramPacket.getLength() > 0) {
                                a.t.x = true;
                            }
                        }
                    }
                } catch (SocketException e) {
                    sb = new StringBuilder();
                    sb.append("SocketException : ");
                    exc = e.toString();
                    sb.append(exc);
                    com.lge.media.launcher.control.common.a.a(sb.toString());
                } catch (IOException e2) {
                    sb = new StringBuilder();
                    sb.append("IOException : ");
                    exc = e2.toString();
                    sb.append(exc);
                    com.lge.media.launcher.control.common.a.a(sb.toString());
                } catch (Exception e3) {
                    com.lge.media.launcher.control.common.a.a("at thrdRecv thread ");
                    sb = new StringBuilder();
                    sb.append("Exception : ");
                    exc = e3.toString();
                    sb.append(exc);
                    com.lge.media.launcher.control.common.a.a(sb.toString());
                }
            }
        });
        this.f = new Thread(new Runnable() { // from class: com.lge.media.launcher.sp.a.2
            @Override // java.lang.Runnable
            public void run() {
                com.lge.media.launcher.control.common.a.c("thrdAudioWrite start");
                int i = 0;
                while (a.this.u && !Thread.currentThread().isInterrupted()) {
                    try {
                        if (a.this.z != a.this.y && a.this.k != 0) {
                            if (!a.t.D) {
                                if (a.this.y < a.this.z) {
                                    if (a.this.y + a.p < a.this.z) {
                                        a.this.y = a.this.z - 8192;
                                        i = 0;
                                    } else if (a.this.y + 8192 < a.this.z) {
                                        if (i % 256 == 255) {
                                            com.lge.media.launcher.control.common.a.b("Delay[2], RI: " + a.this.z + ", WI: " + a.this.y + ", gap = " + (a.this.z - a.this.y));
                                            a.d(a.this, a.this.m % a.o);
                                            i = 0;
                                        }
                                        i++;
                                    }
                                } else if (a.this.y + a.p < a.this.z + a.o) {
                                    a.this.y = ((a.this.z + a.o) - 8192) % a.o;
                                    i = 0;
                                } else if (a.this.y + 8192 < a.this.z + a.o) {
                                    if (i % 256 == 255) {
                                        com.lge.media.launcher.control.common.a.b("Delay[4], RI: " + a.this.z + ", WI: " + a.this.y + ", gap = " + ((a.this.z + a.o) - a.this.y));
                                        a.d(a.this, a.this.m % a.o);
                                        i = 0;
                                    }
                                    i++;
                                }
                            }
                            if (a.this.y > 153600) {
                                a.this.y = 0;
                            }
                            if (a.this.y == 153600) {
                                a.t.I.write(a.this.i, 0, a.this.k);
                                a.this.y = a.this.k;
                            } else {
                                a.t.I.write(a.this.i, a.this.y, a.this.k);
                                a.d(a.this, a.this.k);
                            }
                            if (a.this.u && a.t.I.getPlayState() != 3 && a.t.I.getState() == 1) {
                                a.t.I.play();
                            }
                        }
                    } catch (Exception e) {
                        com.lge.media.launcher.control.common.a.a("at thrdAudio thread ");
                        com.lge.media.launcher.control.common.a.a("Exception : " + e.toString());
                        return;
                    }
                }
            }
        });
        this.d.setDaemon(true);
        this.d.setPriority(10);
        this.d.start();
        this.f.setDaemon(true);
        this.f.start();
        b();
    }

    public void a(boolean z) {
        com.lge.media.launcher.control.common.a.c("setSoundPrivacy on, isSpOn = " + z);
        this.u = z;
        if (z) {
            s = t.p();
            d();
            s.b.a(this.c, d.aK);
            com.lge.media.launcher.control.common.a.c("Send action SPStart");
            return;
        }
        com.lge.media.launcher.control.common.a.c("recvAudio stop");
        if (this.v) {
            Equalizer equalizer = this.n;
            if (equalizer != null) {
                equalizer.release();
            }
            this.d.interrupt();
            this.f.interrupt();
            this.e.interrupt();
            this.g.close();
            this.v = false;
            e eVar = t;
            eVar.x = false;
            eVar.y = true;
            eVar.G = 0;
            f.c(true);
            com.lge.media.launcher.control.common.a.a("AudioTrack stop");
            com.lge.media.launcher.control.common.a.a("audiotrack status is : " + t.I.getState());
            if (t.I != null && t.I.getState() == 1) {
                t.I.pause();
                t.I.flush();
                t.I.release();
                t.I = null;
            }
            t.am.cancel();
            com.lge.media.launcher.control.common.a.c("action sp stop");
            s.b.b();
        }
    }

    public void b() {
        Thread thread = this.e;
        if (thread != null && thread.isAlive()) {
            this.e.interrupt();
        }
        this.e = new Thread(new Runnable() { // from class: com.lge.media.launcher.sp.a.3
            @Override // java.lang.Runnable
            public void run() {
                com.lge.media.launcher.control.common.a.c("thraSPAct start");
                while (true) {
                    a.this.w = System.currentTimeMillis();
                    while (a.this.u && !a.this.e.isInterrupted()) {
                        a.this.x = System.currentTimeMillis();
                        if (a.this.x - a.this.w >= 5000) {
                            break;
                        }
                    }
                    return;
                    com.lge.media.launcher.control.common.a.a("5 sec delayed action sp playing..");
                    a.s.b.c();
                }
            }
        });
        this.e.setDaemon(true);
        this.e.start();
    }

    public void c() {
        this.a = t.z();
        com.lge.media.launcher.control.common.a.c("sampleRate : " + this.a);
        e();
    }

    public void d() {
        this.c = t.b;
        com.lge.media.launcher.control.common.a.c("Local IPAddress : " + this.c);
    }

    public void e() {
        b = AudioTrack.getMinBufferSize(48000, 12, 2);
        b = t.D ? b * 10 : b + 500;
        com.lge.media.launcher.control.common.a.c("bufSize : " + b);
    }
}
