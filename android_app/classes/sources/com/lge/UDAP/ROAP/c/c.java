package com.lge.UDAP.ROAP.c;

import com.lge.UDAP.ROAP.Roap;
import com.lge.UDAP.ROAP.a;
import com.lge.UDAP.ROAP.c.d;
import java.io.IOException;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.Timer;
import java.util.TimerTask;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class c extends com.lge.UDAP.ROAP.a implements a.d {
    public static final String a = "BGM_INFO";
    public static final String b = "DISC_INFO";
    public static final String c = "XML_DATA";
    public static final String d = "THUMBNAIL_DATA";
    private Timer e;
    private a.w f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class a extends TimerTask {
        private com.lge.UDAP.ROAP.d.a b;

        public a(com.lge.UDAP.ROAP.d.a aVar) {
            this.b = null;
            this.b = aVar;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            System.out.println("Capa Time out Job has started");
            com.lge.UDAP.ROAP.d.a aVar = this.b;
            if (aVar == null || aVar.a) {
                return;
            }
            PrintStream printStream = System.out;
            printStream.println("CAPA FAIL MESSAGE SHOULD BE SENT" + this.b);
            this.b.b().a(d.c.CAP_FAIL, null);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    class b extends TimerTask {
        b() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            c.this.a(d.e.BGM_INFO.name(), d.EnumC0056d.TEXT_DATA.name(), new String[0]);
        }
    }

    public c(PipedOutputStream pipedOutputStream) {
        super(pipedOutputStream);
        this.f = new a.w() { // from class: com.lge.UDAP.ROAP.c.c.1
            @Override // com.lge.UDAP.ROAP.a.w
            public void a(d.j jVar, d.e eVar, d.EnumC0056d enumC0056d, byte[] bArr) {
                if (jVar == d.j.INFO_IN_PROGRESS || c.this.e == null) {
                    return;
                }
                c.this.e.cancel();
            }
        };
        com.lge.UDAP.ROAP.d.a a2 = com.lge.UDAP.ROAP.d.b.a();
        a2.a(this.f);
        com.lge.UDAP.ROAP.d.b.a(a2);
    }

    private byte[] a(Roap.Demand.DemandType demandType, String... strArr) {
        String str;
        Roap.Demand.DemandSubtype.a newBuilder;
        String str2;
        System.out.println("create roapMsg");
        Roap.ROAPMessage.a newBuilder2 = Roap.ROAPMessage.newBuilder();
        newBuilder2.setType(Roap.ROAPMessage.MessageType.REQUEST);
        System.out.println("create requestMsg");
        Roap.RequestMessage.a newBuilder3 = Roap.RequestMessage.newBuilder();
        newBuilder3.setType(Roap.RequestMessage.RequestType.DEMAND);
        Roap.Demand.a newBuilder4 = Roap.Demand.newBuilder();
        newBuilder4.setType(demandType);
        if (demandType == Roap.Demand.DemandType.PLAY) {
            if (strArr.length != 0) {
                str = strArr[0];
                newBuilder4.setStrData(str);
            }
        } else if (demandType != Roap.Demand.DemandType.CAPABILITY) {
            if (demandType == Roap.Demand.DemandType.CONTENTS_INFO && strArr.length != 0) {
                newBuilder = Roap.Demand.DemandSubtype.newBuilder();
                System.out.println("Demand CONTENTS_INFO..");
                PrintStream printStream = System.out;
                printStream.println("str[0] : " + strArr[0]);
                if (strArr[0].equals(a)) {
                    System.out.println("BGM INFO..");
                    newBuilder.setInfoType(Roap.Demand.DemandSubtype.ContentsInfoType.BGM_INFO);
                    if (!strArr[1].equals(c)) {
                        if (strArr[1].equals(d)) {
                            newBuilder.setDataType(Roap.Demand.DemandSubtype.ContentsDataType.THUMBNAIL_DATA);
                            str2 = strArr[2];
                            newBuilder4.setStrData(str2);
                        }
                        newBuilder.setInfoType(Roap.Demand.DemandSubtype.ContentsInfoType.valueOf(strArr[0]));
                        newBuilder.setDataType(Roap.Demand.DemandSubtype.ContentsDataType.valueOf(strArr[1]));
                    }
                    newBuilder.setDataType(Roap.Demand.DemandSubtype.ContentsDataType.TEXT_DATA);
                    newBuilder.setInfoType(Roap.Demand.DemandSubtype.ContentsInfoType.valueOf(strArr[0]));
                    newBuilder.setDataType(Roap.Demand.DemandSubtype.ContentsDataType.valueOf(strArr[1]));
                } else {
                    if (strArr[0].equals(b)) {
                        System.out.println("DISC INFO..");
                        newBuilder.setInfoType(Roap.Demand.DemandSubtype.ContentsInfoType.DISC_INFO);
                        if (!strArr[1].equals(c)) {
                            if (strArr[1].equals(d)) {
                                newBuilder.setDataType(Roap.Demand.DemandSubtype.ContentsDataType.THUMBNAIL_DATA);
                                str2 = strArr[2];
                                newBuilder4.setStrData(str2);
                            }
                        }
                        newBuilder.setDataType(Roap.Demand.DemandSubtype.ContentsDataType.TEXT_DATA);
                    }
                    newBuilder.setInfoType(Roap.Demand.DemandSubtype.ContentsInfoType.valueOf(strArr[0]));
                    newBuilder.setDataType(Roap.Demand.DemandSubtype.ContentsDataType.valueOf(strArr[1]));
                }
            } else if (demandType == Roap.Demand.DemandType.CONTENTS_LIST) {
                newBuilder = Roap.Demand.DemandSubtype.newBuilder();
                newBuilder.setLinkedMediaType(Roap.Demand.DemandSubtype.ContentsLinkedMediaType.valueOf(strArr[0]));
                newBuilder.setDataType(Roap.Demand.DemandSubtype.ContentsDataType.valueOf(strArr[1]));
                newBuilder.setMediaType(Roap.Demand.DemandSubtype.ContentsMediaType.valueOf(strArr[2]));
                newBuilder.setContentsPath(strArr[3]);
            } else if (demandType != Roap.Demand.DemandType.SYNC_STATUS_INFO) {
                Roap.Demand.DemandType demandType2 = Roap.Demand.DemandType.SP_INFO;
            } else if (strArr.length != 0) {
                str = strArr[0];
                newBuilder4.setStrData(str);
            }
            newBuilder4.setSubtypeMessage(newBuilder);
        }
        newBuilder3.setDemandMessage(newBuilder4);
        newBuilder2.setRequestMessage(newBuilder3);
        return newBuilder2.build().toByteArray();
    }

    @Override // com.lge.UDAP.ROAP.a.d
    public void a() {
        try {
            a(a(Roap.Demand.DemandType.CAPABILITY, new String[0]));
            a aVar = new a(com.lge.UDAP.ROAP.d.b.a());
            Timer timer = new Timer();
            System.out.println("TimeoutTimer started in 1 secs");
            timer.schedule(aVar, 1000L);
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPDemand demandCapability error:" + e);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.d
    public void a(a.aa aaVar) {
        com.lge.UDAP.ROAP.d.a a2 = com.lge.UDAP.ROAP.d.b.a();
        a2.a(aaVar);
        com.lge.UDAP.ROAP.d.b.a(a2);
        com.lge.UDAP.ROAP.d.a a3 = com.lge.UDAP.ROAP.d.b.a();
        PrintStream printStream = System.out;
        printStream.println("syncinfo response?" + a3);
    }

    @Override // com.lge.UDAP.ROAP.a.d
    public void a(a.t tVar) {
        com.lge.UDAP.ROAP.d.a a2 = com.lge.UDAP.ROAP.d.b.a();
        a2.a(tVar);
        com.lge.UDAP.ROAP.d.b.a(a2);
    }

    @Override // com.lge.UDAP.ROAP.a.d
    public void a(a.u uVar) {
        com.lge.UDAP.ROAP.d.a a2 = com.lge.UDAP.ROAP.d.b.a();
        a2.a(uVar);
        a2.a = false;
        com.lge.UDAP.ROAP.d.b.a(a2);
        com.lge.UDAP.ROAP.d.a a3 = com.lge.UDAP.ROAP.d.b.a();
        PrintStream printStream = System.out;
        printStream.println("setted response?" + a3);
    }

    @Override // com.lge.UDAP.ROAP.a.d
    public void a(a.v vVar) {
        com.lge.UDAP.ROAP.d.a a2 = com.lge.UDAP.ROAP.d.b.a();
        a2.a(vVar);
        com.lge.UDAP.ROAP.d.b.a(a2);
        com.lge.UDAP.ROAP.d.a a3 = com.lge.UDAP.ROAP.d.b.a();
        PrintStream printStream = System.out;
        printStream.println("contentsinfo response?" + a3);
    }

    @Override // com.lge.UDAP.ROAP.a.d
    public void a(a.x xVar) {
        com.lge.UDAP.ROAP.d.a a2 = com.lge.UDAP.ROAP.d.b.a();
        a2.a(xVar);
        com.lge.UDAP.ROAP.d.b.a(a2);
    }

    @Override // com.lge.UDAP.ROAP.a.d
    public void a(a.y yVar) {
        com.lge.UDAP.ROAP.d.a a2 = com.lge.UDAP.ROAP.d.b.a();
        a2.a(yVar);
        com.lge.UDAP.ROAP.d.b.a(a2);
    }

    @Override // com.lge.UDAP.ROAP.a.d
    public void a(a.z zVar) {
        com.lge.UDAP.ROAP.d.a a2 = com.lge.UDAP.ROAP.d.b.a();
        a2.a(zVar);
        com.lge.UDAP.ROAP.d.b.a(a2);
    }

    @Override // com.lge.UDAP.ROAP.a.d
    public void a(String str) {
        try {
            a(a(Roap.Demand.DemandType.PLAY, str));
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPDemand demandPlay error:" + e);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.d
    public void a(String str, String str2, String str3, String str4) {
        try {
            a(a(Roap.Demand.DemandType.CONTENTS_LIST, str, str2, str3, str4));
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPDemand demandContentsList error:" + e);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.d
    public void a(String str, String str2, String... strArr) {
        Roap.Demand.DemandType demandType = Roap.Demand.DemandType.CONTENTS_INFO;
        String[] strArr2 = new String[3];
        strArr2[0] = str;
        strArr2[1] = str2;
        strArr2[2] = strArr.length != 0 ? strArr[0] : null;
        try {
            a(a(demandType, strArr2));
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPDemand demandInformation error:" + e);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.d
    public void b() {
        b bVar = new b();
        this.e = new Timer();
        this.e.scheduleAtFixedRate(bVar, 10000L, 2000L);
        System.out.println("Request timer start");
    }

    @Override // com.lge.UDAP.ROAP.a.d
    public void b(String str) {
        try {
            a(a(Roap.Demand.DemandType.SYNC_STATUS_INFO, str));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override // com.lge.UDAP.ROAP.a.d
    public void c() {
        Timer timer = this.e;
        if (timer != null) {
            timer.cancel();
            System.out.println("Request timer stop");
        }
    }

    @Override // com.lge.UDAP.ROAP.a.d
    public void d() {
        try {
            a(a(Roap.Demand.DemandType.SYNC_STATUS_INFO, new String[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override // com.lge.UDAP.ROAP.a.d
    public void e() {
        try {
            a(a(Roap.Demand.DemandType.SYNC_STATUS_INFO, new String[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override // com.lge.UDAP.ROAP.a.d
    public void f() {
        try {
            a(a(Roap.Demand.DemandType.SP_INFO, new String[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override // com.lge.UDAP.ROAP.a.d
    public void g() {
        try {
            a(a(Roap.Demand.DemandType.CP_LIST, new String[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override // com.lge.UDAP.ROAP.a.d
    public void h() {
        try {
            a(a(Roap.Demand.DemandType.EXTSPK_LIST, new String[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
