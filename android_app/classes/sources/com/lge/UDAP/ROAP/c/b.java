package com.lge.UDAP.ROAP.c;

import com.lge.UDAP.ROAP.Roap;
import com.lge.UDAP.ROAP.a;
import java.io.IOException;
import java.io.PipedOutputStream;
import java.io.PrintStream;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class b extends com.lge.UDAP.ROAP.a implements a.c {
    public b(PipedOutputStream pipedOutputStream) {
        super(pipedOutputStream);
    }

    private byte[] a(Roap.Data.DataType dataType) {
        Roap.ROAPMessage.a newBuilder = Roap.ROAPMessage.newBuilder();
        newBuilder.setType(Roap.ROAPMessage.MessageType.REQUEST);
        Roap.RequestMessage.a newBuilder2 = Roap.RequestMessage.newBuilder();
        newBuilder2.setType(Roap.RequestMessage.RequestType.DATA);
        Roap.Data.a newBuilder3 = Roap.Data.newBuilder();
        newBuilder3.setType(dataType);
        newBuilder2.setDataMessage(newBuilder3);
        newBuilder.setRequestMessage(newBuilder2);
        return newBuilder.build().toByteArray();
    }

    @Override // com.lge.UDAP.ROAP.a.c
    public void a() {
        try {
            a(a(Roap.Data.DataType.TEXT_LIMIT));
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPData dataTextLimit error:" + e);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.c
    public void a(a.p pVar) {
        com.lge.UDAP.ROAP.d.a a = com.lge.UDAP.ROAP.d.b.a();
        a.a(pVar);
        com.lge.UDAP.ROAP.d.b.a(a);
    }

    @Override // com.lge.UDAP.ROAP.a.c
    public void a(a.q qVar) {
        com.lge.UDAP.ROAP.d.a a = com.lge.UDAP.ROAP.d.b.a();
        a.a(qVar);
        com.lge.UDAP.ROAP.d.b.a(a);
    }

    @Override // com.lge.UDAP.ROAP.a.c
    public void a(a.r rVar) {
        com.lge.UDAP.ROAP.d.a a = com.lge.UDAP.ROAP.d.b.a();
        a.a(rVar);
        com.lge.UDAP.ROAP.d.b.a(a);
    }

    @Override // com.lge.UDAP.ROAP.a.c
    public void a(a.s sVar) {
        com.lge.UDAP.ROAP.d.a a = com.lge.UDAP.ROAP.d.b.a();
        a.a(sVar);
        com.lge.UDAP.ROAP.d.b.a(a);
    }

    @Override // com.lge.UDAP.ROAP.a.c
    public void b() {
        try {
            a(a(Roap.Data.DataType.DMR_UUID));
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPData dataTextLimit error:" + e);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.c
    public void c() {
        try {
            a(a(Roap.Data.DataType.DMR_AVAILABLE));
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPData dataTextLimit error:" + e);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.c
    public void d() {
        try {
            a(a(Roap.Data.DataType.USB_AVAILABLE));
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPData dataTextLimit error:" + e);
        }
    }
}
