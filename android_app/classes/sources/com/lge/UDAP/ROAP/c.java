package com.lge.UDAP.ROAP;

import android.os.Handler;
import android.os.Message;
import com.google.protobuf.InvalidProtocolBufferException;
import com.lge.UDAP.ROAP.Roap;
import com.lge.UDAP.ROAP.a;
import com.lge.UDAP.ROAP.b;
import com.lge.UDAP.ROAP.b.a;
import com.lge.UDAP.ROAP.b.c;
import com.lge.UDAP.ROAP.c.d;
import com.lge.UDAP.ROAP.d.b;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class c {
    private b a;
    private C0055c b;
    private Socket c;
    private DatagramSocket d;
    private com.lge.UDAP.ROAP.d.a g;
    private com.lge.UDAP.ROAP.b.b h;
    private Handler j;
    private String i = com.lge.media.launcher.a.d;
    private PipedInputStream e = new PipedInputStream();
    private PipedOutputStream f = new PipedOutputStream(this.e);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.lge.UDAP.ROAP.c$4  reason: invalid class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;
        static final /* synthetic */ int[] c;

        static {
            try {
                d[Roap.Demand.DemandType.CAPABILITY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                d[Roap.Demand.DemandType.CONTENTS_INFO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                d[Roap.Demand.DemandType.CONTENTS_LIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                d[Roap.Demand.DemandType.SYNC_STATUS_INFO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                d[Roap.Demand.DemandType.SP_INFO.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                d[Roap.Demand.DemandType.CP_LIST.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                d[Roap.Demand.DemandType.EXTSPK_LIST.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                d[Roap.Demand.DemandType.PLAY.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            c = new int[Roap.Data.DataType.values().length];
            try {
                c[Roap.Data.DataType.TEXT_LIMIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                c[Roap.Data.DataType.DMR_UUID.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                c[Roap.Data.DataType.DMR_AVAILABLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                c[Roap.Data.DataType.USB_AVAILABLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused12) {
            }
            b = new int[d.a.values().length];
            try {
                b[d.a.AUTH_REQUST_KEY_OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                b[d.a.AUTH_REQUST_KEY_FAIL.ordinal()] = 2;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                b[d.a.AUTH_SEND_KEY_OK.ordinal()] = 3;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                b[d.a.AUTH_SEND_KEY_FAIL.ordinal()] = 4;
            } catch (NoSuchFieldError unused16) {
            }
            a = new int[Roap.Authentication.AuthResult.values().length];
            try {
                a[Roap.Authentication.AuthResult.AUTH_REQUST_KEY_OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                a[Roap.Authentication.AuthResult.AUTH_REQUST_KEY_FAIL.ordinal()] = 2;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                a[Roap.Authentication.AuthResult.AUTH_SEND_KEY_OK.ordinal()] = 3;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                a[Roap.Authentication.AuthResult.AUTH_SEND_KEY_FAIL.ordinal()] = 4;
            } catch (NoSuchFieldError unused20) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class a extends Thread {
        private Socket b;
        private DatagramSocket c;
        private int d;
        private b.a g;
        private final int f = 65536;
        private byte[] e = new byte[65536];

        public a(b.a aVar, DatagramSocket datagramSocket) {
            this.c = datagramSocket;
            this.g = aVar;
        }

        public void a(b.a aVar) {
            this.g = aVar;
        }

        protected byte[] a() {
            return this.e;
        }

        protected boolean b() {
            return this.g.c() == 1;
        }

        protected boolean c() {
            return this.g.c() == 2;
        }

        protected String d() {
            return this.g.a();
        }

        protected int e() {
            return this.g.b();
        }

        protected Socket f() {
            return this.b;
        }

        protected DatagramSocket g() {
            return this.c;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class b extends a {
        private DatagramPacket c;
        private byte[] d;
        private int e;
        private boolean f;
        private int g;
        private int h;
        private int i;
        private Handler j;

        public b(b.a aVar, DatagramSocket datagramSocket, Handler handler) {
            super(aVar, datagramSocket);
            this.c = new DatagramPacket(a(), a().length);
            PrintStream printStream = System.out;
            printStream.println("---------ReceiveThread created" + a().length);
            this.j = handler;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private com.lge.UDAP.ROAP.e.a a(byte[] bArr) {
            PrintStream printStream;
            String str;
            SAXException sAXException;
            StringBuilder sb;
            try {
                XMLReader xMLReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
                com.lge.UDAP.ROAP.e.c cVar = new com.lge.UDAP.ROAP.e.c();
                xMLReader.setContentHandler(cVar);
                xMLReader.parse(new InputSource(new ByteArrayInputStream(bArr)));
                return cVar.a();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                printStream = System.out;
                str = "MalformedURLException error:";
                sb = new StringBuilder();
                sAXException = e;
                sb.append(str);
                sb.append(sAXException);
                printStream.println(sb.toString());
                return null;
            } catch (IOException e2) {
                e2.printStackTrace();
                printStream = System.out;
                str = "XML parser error:";
                sb = new StringBuilder();
                sAXException = e2;
                sb.append(str);
                sb.append(sAXException);
                printStream.println(sb.toString());
                return null;
            } catch (ParserConfigurationException e3) {
                e3.printStackTrace();
                printStream = System.out;
                str = "ParserConfigurationException error:";
                sb = new StringBuilder();
                sAXException = e3;
                sb.append(str);
                sb.append(sAXException);
                printStream.println(sb.toString());
                return null;
            } catch (SAXException e4) {
                e4.printStackTrace();
                printStream = System.out;
                str = "SAXException error:";
                sb = new StringBuilder();
                sAXException = e4;
                sb.append(str);
                sb.append(sAXException);
                printStream.println(sb.toString());
                return null;
            }
        }

        private void a(Roap.Data data) {
            int i = AnonymousClass4.c[data.getType().ordinal()];
            if (i == 1) {
                if (c.this.g.h() == null) {
                    return;
                }
                c.this.g.h().a(data.getNData());
            } else if (i == 2) {
                if (c.this.g.j() == null) {
                    return;
                }
                c.this.g.j().a(data.getStrData());
            } else if (i == 3) {
                if (c.this.g.i() == null) {
                    return;
                }
                c.this.g.i().a(data.getBData());
            } else if (i == 4 && c.this.g.k() != null) {
                c.this.g.k().a(data.getBData());
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:40:0x0124, code lost:
            if (r0 == com.lge.UDAP.ROAP.Roap.Demand.Result.ContentsListResult.CONTENTS_LIST_OK) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x013f, code lost:
            if (r0 == com.lge.UDAP.ROAP.Roap.Demand.Result.InformationResult.INFO_OK) goto L38;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void a(com.lge.UDAP.ROAP.Roap.Demand r8) {
            /*
                Method dump skipped, instructions count: 578
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lge.UDAP.ROAP.c.b.a(com.lge.UDAP.ROAP.Roap$Demand):void");
        }

        private void a(Roap.Demand demand, Object obj, int i) {
            if (AnonymousClass4.d[demand.getType().ordinal()] != 5) {
                return;
            }
            d.l valueOf = obj == null ? d.l.SP_INFO_FAIL : d.l.valueOf(((Roap.Demand.Result.SoundPrivacyInfoResult) obj).name());
            PrintStream printStream = System.out;
            printStream.println("spStatusResult : " + valueOf + "nData : " + i);
            c.this.g.g().a(valueOf, i);
        }

        private void a(Roap.Demand demand, Object obj, String str, int i) {
            if (AnonymousClass4.d[demand.getType().ordinal()] != 4) {
                return;
            }
            d.m valueOf = obj == null ? d.m.SYNC_STATUS_INFO_FAIL : d.m.valueOf(((Roap.Demand.Result.SyncStatusInfoResult) obj).name());
            PrintStream printStream = System.out;
            printStream.println("syncstatusresult : " + valueOf + " data is " + str);
            c.this.g.f().a(valueOf, str, i);
        }

        private void a(Roap.Demand demand, Object obj, byte[] bArr) {
            int i = AnonymousClass4.d[demand.getType().ordinal()];
            if (i == 1) {
                d.c valueOf = obj == null ? d.c.CAP_FAIL : d.c.valueOf(((Roap.Demand.Result.CapabilityResult) obj).name());
                com.lge.UDAP.ROAP.e.a a = a(bArr);
                System.out.println(new String(bArr));
                c.this.g.b().a(valueOf, a);
            } else if (i == 2) {
                Roap.Demand.DemandSubtype.ContentsDataType dataType = demand.getSubtypeMessage().getDataType();
                Roap.Demand.DemandSubtype.ContentsInfoType infoType = demand.getSubtypeMessage().getInfoType();
                d.j valueOf2 = obj == null ? d.j.INFO_FAIL : d.j.valueOf(((Roap.Demand.Result.InformationResult) obj).name());
                if (valueOf2 != d.j.INFO_IN_PROGRESS) {
                    c.this.g.d().a(valueOf2, d.e.valueOf(infoType.name()), d.EnumC0056d.valueOf(dataType.name()), bArr);
                    c.this.g.c().a(valueOf2, d.e.valueOf(infoType.name()), d.EnumC0056d.valueOf(dataType.name()), bArr);
                }
            } else if (i == 3) {
                Roap.Demand.DemandSubtype.ContentsMediaType mediaType = demand.getSubtypeMessage().getMediaType();
                c.this.g.e().a(obj == null ? d.g.CONTENTS_LIST_FAIL : d.g.valueOf(((Roap.Demand.Result.ContentsListResult) obj).name()), d.EnumC0056d.valueOf(demand.getSubtypeMessage().getDataType().name()), d.h.valueOf(mediaType.name()), bArr);
            } else if (i == 4) {
                d.m valueOf3 = obj == null ? d.m.SYNC_STATUS_INFO_FAIL : d.m.valueOf(((Roap.Demand.Result.SyncStatusInfoResult) obj).name());
                PrintStream printStream = System.out;
                printStream.println("syncstatusresult : " + valueOf3 + " data is " + bArr);
                c.this.g.f().a(valueOf3, bArr);
            } else if (i == 6) {
                d.b valueOf4 = obj == null ? d.b.CP_LIST_FAIL : d.b.valueOf(((Roap.Demand.Result.CpListResult) obj).name());
                PrintStream printStream2 = System.out;
                printStream2.println("cpListResult : " + valueOf4 + " data is " + bArr);
                c.this.g.l().a(valueOf4, bArr);
            } else if (i != 7) {
            } else {
                d.i valueOf5 = obj == null ? d.i.EXTSPK_LIST_FAIL : d.i.valueOf(((Roap.Demand.Result.ExtspkListResult) obj).name());
                PrintStream printStream3 = System.out;
                printStream3.println("extSpkListResult : " + valueOf5 + " data is " + bArr);
                c.this.g.m().a(valueOf5, bArr);
            }
        }

        private void a(Roap.ROAPMessage rOAPMessage) {
            a.n a;
            a.i iVar;
            if (c.this.h.a() != null) {
                Roap.EventMessage.StatusMessage.ViewStatus viewStatus = rOAPMessage.getEventMessage().getStatusMessage().getViewStatus();
                String strData = rOAPMessage.getEventMessage().getStrData();
                int byteLength = rOAPMessage.getEventMessage().getByteLength();
                PrintStream printStream = System.out;
                printStream.println("viewStatusListenerCall strData : " + rOAPMessage.getEventMessage().getStrData());
                PrintStream printStream2 = System.out;
                printStream2.println("viewStatusListenerCall strLength : " + rOAPMessage.getEventMessage().getByteLength());
                if (viewStatus == Roap.EventMessage.StatusMessage.ViewStatus.VIEW_TEXTINPUT) {
                    a = c.this.h.a();
                    iVar = a.i.VIEW_TEXTINPUT;
                } else if (viewStatus != Roap.EventMessage.StatusMessage.ViewStatus.VIEW_TOUCHPAD) {
                    return;
                } else {
                    a = c.this.h.a();
                    iVar = a.i.VIEW_TOUCHPAD;
                    strData = null;
                    byteLength = 0;
                }
                a.a(iVar, strData, byteLength);
            }
        }

        private void a(Roap.ROAPMessage rOAPMessage, String str) {
            a.j b;
            a.e eVar;
            if (c.this.h.b() != null) {
                Roap.EventMessage.StatusMessage.PlaybackStatus playbackStatus = rOAPMessage.getEventMessage().getStatusMessage().getPlaybackStatus();
                String strData = rOAPMessage.getEventMessage().getStrData();
                int nData1 = rOAPMessage.getEventMessage().getNData1();
                int nData2 = rOAPMessage.getEventMessage().getNData2();
                if (playbackStatus == Roap.EventMessage.StatusMessage.PlaybackStatus.PLAYBACK_PLAY) {
                    b = c.this.h.b();
                    eVar = a.e.PLAYBACK_PLAY;
                } else if (playbackStatus == Roap.EventMessage.StatusMessage.PlaybackStatus.PLAYBACK_PAUSE) {
                    b = c.this.h.b();
                    eVar = a.e.PLAYBACK_PAUSE;
                } else if (playbackStatus == Roap.EventMessage.StatusMessage.PlaybackStatus.PLAYBACK_STOP) {
                    b = c.this.h.b();
                    eVar = a.e.PLAYBACK_STOP;
                } else if (playbackStatus == Roap.EventMessage.StatusMessage.PlaybackStatus.PLAYBACK_FAST_BWD) {
                    b = c.this.h.b();
                    eVar = a.e.PLAYBACK_FAST_BWD;
                } else if (playbackStatus == Roap.EventMessage.StatusMessage.PlaybackStatus.PLAYBACK_FAST_FWD) {
                    b = c.this.h.b();
                    eVar = a.e.PLAYBACK_FAST_FWD;
                } else if (playbackStatus == Roap.EventMessage.StatusMessage.PlaybackStatus.PLAYBACK_SLOW_FWD) {
                    b = c.this.h.b();
                    eVar = a.e.PLAYBACK_SLOW_FWD;
                } else if (playbackStatus != Roap.EventMessage.StatusMessage.PlaybackStatus.PLAYBACK_NOW_PLAYING) {
                    return;
                } else {
                    b = c.this.h.b();
                    eVar = a.e.PLAYBACK_NOW_PLAYING;
                }
                b.a(eVar, strData, nData1, nData2, str);
            }
        }

        private void b(Roap.Demand demand) {
            System.out.println("Demand ListenerCall here...");
            switch (demand.getType()) {
                case CAPABILITY:
                case CONTENTS_INFO:
                case CONTENTS_LIST:
                case SYNC_STATUS_INFO:
                case SP_INFO:
                case CP_LIST:
                case EXTSPK_LIST:
                    a(demand);
                    return;
                case PLAY:
                default:
                    return;
            }
        }

        private void b(Roap.ROAPMessage rOAPMessage) {
            a.k e;
            a.f fVar;
            if (c.this.h.e() != null) {
                Roap.EventMessage.StatusMessage.SpStatus spStatus = rOAPMessage.getEventMessage().getStatusMessage().getSpStatus();
                if (spStatus == Roap.EventMessage.StatusMessage.SpStatus.SP_SOUND_ON) {
                    e = c.this.h.e();
                    fVar = a.f.SP_SOUND_ON;
                } else if (spStatus == Roap.EventMessage.StatusMessage.SpStatus.SP_SOUND_OFF) {
                    e = c.this.h.e();
                    fVar = a.f.SP_SOUND_OFF;
                } else if (spStatus == Roap.EventMessage.StatusMessage.SpStatus.SP_SAMPLERATE_CHANGE) {
                    e = c.this.h.e();
                    fVar = a.f.SP_SAMPLERATE_CHANGE;
                } else if (spStatus == Roap.EventMessage.StatusMessage.SpStatus.SP_STOP) {
                    e = c.this.h.e();
                    fVar = a.f.SP_STOP;
                } else if (spStatus == Roap.EventMessage.StatusMessage.SpStatus.SP_NOT_SUPPORT) {
                    e = c.this.h.e();
                    fVar = a.f.SP_NOT_SUPPORT;
                } else if (spStatus != Roap.EventMessage.StatusMessage.SpStatus.SP_ALIVE) {
                    return;
                } else {
                    e = c.this.h.e();
                    fVar = a.f.SP_ALIVE;
                }
                e.a(fVar);
            }
        }

        private void c(Roap.ROAPMessage rOAPMessage) {
            a.l f;
            a.g gVar;
            if (c.this.h.f() != null) {
                Roap.EventMessage.StatusMessage.SyncStatus syncStatus = rOAPMessage.getEventMessage().getStatusMessage().getSyncStatus();
                if (syncStatus == Roap.EventMessage.StatusMessage.SyncStatus.SS_VIEW_CHANGE) {
                    f = c.this.h.f();
                    gVar = a.g.SS_VIEW_CHANGE;
                } else if (syncStatus != Roap.EventMessage.StatusMessage.SyncStatus.SS_ENABLED) {
                    return;
                } else {
                    f = c.this.h.f();
                    gVar = a.g.SS_ENABLED;
                }
                f.a(gVar);
            }
        }

        private void d(Roap.ROAPMessage rOAPMessage) {
            a.f g;
            a.EnumC0054a enumC0054a;
            if (c.this.h.g() != null) {
                Roap.EventMessage.StatusMessage.BgmInfoStatus bgmInfoStatus = rOAPMessage.getEventMessage().getStatusMessage().getBgmInfoStatus();
                if (bgmInfoStatus == Roap.EventMessage.StatusMessage.BgmInfoStatus.BGM_INFO_OK) {
                    g = c.this.h.g();
                    enumC0054a = a.EnumC0054a.BGM_INFO_OK;
                } else if (bgmInfoStatus == Roap.EventMessage.StatusMessage.BgmInfoStatus.BGM_INFO_FAIL) {
                    g = c.this.h.g();
                    enumC0054a = a.EnumC0054a.BGM_INFO_FAIL;
                } else if (bgmInfoStatus == Roap.EventMessage.StatusMessage.BgmInfoStatus.BGM_INFO_LOADING) {
                    g = c.this.h.g();
                    enumC0054a = a.EnumC0054a.BGM_INFO_LOADING;
                } else if (bgmInfoStatus != Roap.EventMessage.StatusMessage.BgmInfoStatus.BGM_INFO_CANCEL) {
                    return;
                } else {
                    g = c.this.h.g();
                    enumC0054a = a.EnumC0054a.BGM_INFO_CANCEL;
                }
                g.a(enumC0054a);
            }
        }

        private void e(Roap.ROAPMessage rOAPMessage) {
            a.g h;
            a.b bVar;
            if (c.this.h.h() != null) {
                Roap.EventMessage.StatusMessage.CpStatus cpStatus = rOAPMessage.getEventMessage().getStatusMessage().getCpStatus();
                if (cpStatus == Roap.EventMessage.StatusMessage.CpStatus.CP_LOADING_START) {
                    h = c.this.h.h();
                    bVar = a.b.CP_LOADING_START;
                } else if (cpStatus == Roap.EventMessage.StatusMessage.CpStatus.CP_LOADING_END) {
                    h = c.this.h.h();
                    bVar = a.b.CP_LOADING_END;
                } else if (cpStatus != Roap.EventMessage.StatusMessage.CpStatus.CP_LIST_CHANGE) {
                    return;
                } else {
                    h = c.this.h.h();
                    bVar = a.b.CP_LIST_CHANGE;
                }
                h.a(bVar);
            }
        }

        private void f(Roap.ROAPMessage rOAPMessage) {
            Roap.EventMessage.StatusMessage.EXTSPKStatus extspkStatus;
            if (c.this.h.h() == null || (extspkStatus = rOAPMessage.getEventMessage().getStatusMessage().getExtspkStatus()) == Roap.EventMessage.StatusMessage.EXTSPKStatus.EXTSPK_UNKNOWN || extspkStatus != Roap.EventMessage.StatusMessage.EXTSPKStatus.EXTSPK_LIST_CHANGE) {
                return;
            }
            c.this.h.i().a(a.c.EXTSPK_LIST_CHANGE);
        }

        private void g(Roap.ROAPMessage rOAPMessage) {
            a.i c;
            a.d dVar;
            if (c.this.h.c() != null) {
                Roap.EventMessage.StatusMessage.NetworkStatus networkStatus = rOAPMessage.getEventMessage().getStatusMessage().getNetworkStatus();
                if (networkStatus == Roap.EventMessage.StatusMessage.NetworkStatus.NETWORK_WIRED_CONNECTED) {
                    c = c.this.h.c();
                    dVar = a.d.NETWORK_WIRED_CONNECTED;
                } else {
                    if (networkStatus != Roap.EventMessage.StatusMessage.NetworkStatus.NETWORK_WIRED_DISCONNECTED) {
                        if (networkStatus == Roap.EventMessage.StatusMessage.NetworkStatus.NETWORK_WIRELESS_CONNECTED) {
                            c = c.this.h.c();
                            dVar = a.d.NETWORK_WIRELESS_CONNECTED;
                        } else if (networkStatus != Roap.EventMessage.StatusMessage.NetworkStatus.NETWORK_WIRELESS_DISCONNECTED) {
                            return;
                        }
                    }
                    c = c.this.h.c();
                    dVar = a.d.NETWORK_WIRED_DISCONNECTED;
                }
                c.a(dVar);
            }
        }

        private void h(Roap.ROAPMessage rOAPMessage) {
            if (c.this.g.a() != null) {
                Roap.Authentication authMessage = rOAPMessage.getResponseMessage().getAuthMessage();
                Roap.Authentication.AuthResult authResult = rOAPMessage.getResponseMessage().getAuthMessage().getAuthResult();
                d.a aVar = null;
                if (authMessage.getType() == Roap.Authentication.AuthenticationType.AUTH_RESULT) {
                    int i = AnonymousClass4.a[authResult.ordinal()];
                    if (i == 1) {
                        aVar = d.a.AUTH_REQUST_KEY_OK;
                    } else if (i == 2) {
                        aVar = d.a.AUTH_REQUST_KEY_FAIL;
                    } else if (i == 3) {
                        aVar = d.a.AUTH_SEND_KEY_OK;
                        c.this.i = rOAPMessage.getSessionId();
                    } else if (i == 4) {
                        aVar = d.a.AUTH_SEND_KEY_FAIL;
                    }
                    int i2 = AnonymousClass4.b[aVar.ordinal()];
                    if (i2 == 1 || i2 == 2) {
                        c.this.g.a().b(aVar);
                    } else if (i2 == 3 || i2 == 4) {
                        c.this.g.a().a(aVar);
                    }
                }
            }
        }

        public void a(byte[] bArr, String str) {
            System.out.println("\t\t\t\t\t\treceive Data Process...");
            try {
                Roap.ROAPMessage parseFrom = Roap.ROAPMessage.parseFrom(bArr);
                if (parseFrom.getType() == Roap.ROAPMessage.MessageType.RESPONSE) {
                    Roap.ResponseMessage responseMessage = parseFrom.getResponseMessage();
                    if (responseMessage.getType() == Roap.ResponseMessage.ResponseType.AUTHENTICATION) {
                        h(parseFrom);
                    } else if (responseMessage.getType() == Roap.ResponseMessage.ResponseType.DATA) {
                        a(responseMessage.getDataMessage());
                    } else if (responseMessage.getType() == Roap.ResponseMessage.ResponseType.DEMAND) {
                        System.out.println("\t\t\t\t\t\tDemand!!!...");
                        b(responseMessage.getDemandMessage());
                    }
                } else if (parseFrom.getType() == Roap.ROAPMessage.MessageType.EVENT) {
                    Roap.EventMessage eventMessage = parseFrom.getEventMessage();
                    if (eventMessage.getType() == Roap.EventMessage.EventType.VIEW_STATUS) {
                        a(parseFrom);
                    } else if (eventMessage.getType() == Roap.EventMessage.EventType.PLAYBACK_STATUS) {
                        a(parseFrom, str);
                    } else if (eventMessage.getType() == Roap.EventMessage.EventType.NETWORK_STATUS) {
                        g(parseFrom);
                    } else if (eventMessage.getType() != Roap.EventMessage.EventType.USB_STATUS) {
                        if (eventMessage.getType() == Roap.EventMessage.EventType.SP_STATUS) {
                            b(parseFrom);
                        } else if (eventMessage.getType() == Roap.EventMessage.EventType.SYNC_STATUS) {
                            c(parseFrom);
                        } else if (eventMessage.getType() == Roap.EventMessage.EventType.BGM_INFO_STATUS) {
                            d(parseFrom);
                        } else if (eventMessage.getType() == Roap.EventMessage.EventType.CP_STATUS) {
                            e(parseFrom);
                        } else if (eventMessage.getType() == Roap.EventMessage.EventType.EXTSPK_STATUS) {
                            f(parseFrom);
                        }
                    }
                }
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
                PrintStream printStream = System.out;
                printStream.println("ReceiveThread data parse error:" + e);
            }
        }

        public void h() {
            g().close();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (true) {
                if (c()) {
                    try {
                        this.c.setLength(this.c.getData().length);
                        g().receive(this.c);
                        PrintStream printStream = System.out;
                        printStream.println("raw packet length: " + this.c.getLength());
                        ByteBuffer allocate = ByteBuffer.allocate(this.c.getLength());
                        allocate.order(ByteOrder.LITTLE_ENDIAN);
                        allocate.put(this.c.getData(), 0, this.c.getLength());
                        PrintStream printStream2 = System.out;
                        printStream2.println("recv total cnt:" + allocate.array().length);
                        String[] split = this.c.getAddress().toString().split("/");
                        String num = Integer.toString(this.c.getPort());
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(split[1]);
                        arrayList.add(num);
                        arrayList.add(allocate.array());
                        Message obtain = Message.obtain(this.j);
                        obtain.obj = arrayList;
                        this.j.sendMessage(obtain);
                    } catch (IOException e) {
                        e.printStackTrace();
                        PrintStream printStream3 = System.out;
                        printStream3.println("Receive Thread, receive error:" + e);
                        System.out.println("Receive Thread, end");
                        return;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.lge.UDAP.ROAP.c$c  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class C0055c extends a {
        private PipedInputStream c;
        private DatagramPacket d;

        public C0055c(b.a aVar, DatagramSocket datagramSocket, PipedInputStream pipedInputStream) {
            super(aVar, datagramSocket);
            this.c = pipedInputStream;
        }

        private byte[] a(byte[] bArr) {
            try {
                Roap.ROAPMessage parseFrom = Roap.ROAPMessage.parseFrom(bArr);
                boolean z = false;
                if (parseFrom.getType() == Roap.ROAPMessage.MessageType.REQUEST && parseFrom.getRequestMessage().getType() == Roap.RequestMessage.RequestType.AUTHENTICATION) {
                    z = true;
                }
                if (z) {
                    return bArr;
                }
                Roap.ROAPMessage.a newBuilder = Roap.ROAPMessage.newBuilder(parseFrom);
                newBuilder.setSessionId(c.this.i);
                return newBuilder.build().toByteArray();
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
                return null;
            }
        }

        public void h() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            int read;
            PrintStream printStream;
            String str;
            byte[] a = a();
            int i = 0;
            int i2 = 1;
            while (true) {
                try {
                    read = this.c.read();
                } catch (IOException e) {
                    e.printStackTrace();
                    PrintStream printStream2 = System.out;
                    printStream2.println("Send Thread, piped input stream read error:" + e);
                }
                if (read == 255) {
                    if (this.c.read() == 18) {
                        int read2 = this.c.read();
                        while (i2 > 0 && i != read2) {
                            i2 = this.c.read(a, i, read2 - i);
                            i += i2;
                        }
                        if (i != read2) {
                            break;
                        }
                        if (c()) {
                            byte[] bArr = new byte[read2];
                            System.arraycopy(a, 0, bArr, 0, bArr.length);
                            byte[] a2 = a(bArr);
                            if (a2 != null) {
                                PrintStream printStream3 = System.out;
                                printStream3.println("send Datagram packet create.." + a2 + " buflength: " + a2.length + "InetAddress.getByName()" + d() + " port: " + e());
                                this.d = new DatagramPacket(a2, a2.length, InetAddress.getByName(d()), e());
                                g().send(this.d);
                                printStream = System.out;
                                str = "packet send";
                            } else {
                                printStream = System.out;
                                str = "Set SessionID failed!";
                            }
                            printStream.println(str);
                        }
                        i = 0;
                    } else if (read < 0) {
                        break;
                    }
                } else if (read < 0) {
                    break;
                }
            }
            System.out.println("Send Thread, end");
        }
    }

    public c(b.a aVar) {
        b();
        f();
        a();
        a(aVar);
    }

    private void a() {
        this.j = new Handler() { // from class: com.lge.UDAP.ROAP.c.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                System.out.println("main handler received msg");
                ArrayList arrayList = (ArrayList) message.obj;
                String str = (String) arrayList.get(1);
                byte[] bArr = (byte[]) arrayList.get(2);
                c.this.a.a(bArr, (String) arrayList.get(0));
            }
        };
    }

    private void a(b.a aVar) {
        int c = aVar.c();
        if (c != 1 && c == 2) {
            try {
                this.d = new DatagramSocket((SocketAddress) null);
                this.d.setReuseAddress(true);
                this.d.bind(new InetSocketAddress(aVar.b()));
                PrintStream printStream = System.out;
                printStream.println("UDPSocket:" + this.d);
                this.a = new b(aVar, this.d, this.j);
                PrintStream printStream2 = System.out;
                printStream2.println("ReceiveThread:" + this.a);
                this.b = new C0055c(aVar, this.d, this.e);
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }
    }

    private void b() {
        this.g = new com.lge.UDAP.ROAP.d.a();
        new com.lge.UDAP.ROAP.d.b(this.g).a(new b.a() { // from class: com.lge.UDAP.ROAP.c.2
            @Override // com.lge.UDAP.ROAP.d.b.a
            public void a(com.lge.UDAP.ROAP.d.a aVar) {
                c.this.g = aVar;
            }
        });
    }

    private void f() {
        this.h = new com.lge.UDAP.ROAP.b.b();
        new com.lge.UDAP.ROAP.b.c(this.h).a(new c.a() { // from class: com.lge.UDAP.ROAP.c.3
            @Override // com.lge.UDAP.ROAP.b.c.a
            public void a(com.lge.UDAP.ROAP.b.b bVar) {
                c.this.h = bVar;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(b.a aVar) {
        b bVar;
        int c = aVar.c();
        if (c == 1 || c != 2 || (bVar = this.a) == null || this.b == null) {
            return;
        }
        bVar.a(aVar);
        this.b.a(aVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PipedOutputStream c() {
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d() {
        this.a.start();
        this.b.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void e() {
        this.a.h();
        this.b.h();
        this.f.close();
    }
}
