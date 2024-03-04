package com.lge.UDAP.ROAP.c;

import com.lge.UDAP.ROAP.Roap;
import com.lge.UDAP.ROAP.a;
import java.io.IOException;
import java.io.PipedOutputStream;
import java.io.PrintStream;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class a extends com.lge.UDAP.ROAP.a implements a.b {
    public a(PipedOutputStream pipedOutputStream) {
        super(pipedOutputStream);
    }

    private byte[] a(Roap.Authentication.AuthenticationType authenticationType, String... strArr) {
        Roap.ROAPMessage.a newBuilder = Roap.ROAPMessage.newBuilder();
        newBuilder.setType(Roap.ROAPMessage.MessageType.REQUEST);
        Roap.RequestMessage.a newBuilder2 = Roap.RequestMessage.newBuilder();
        newBuilder2.setType(Roap.RequestMessage.RequestType.AUTHENTICATION);
        Roap.Authentication.a newBuilder3 = Roap.Authentication.newBuilder();
        newBuilder3.setType(authenticationType);
        if (strArr.length != 0) {
            newBuilder3.setAuthKey(strArr[0]);
        }
        newBuilder2.setAuthMessage(newBuilder3);
        newBuilder.setRequestMessage(newBuilder2);
        return newBuilder.build().toByteArray();
    }

    @Override // com.lge.UDAP.ROAP.a.b
    public void a() {
        try {
            a(a(Roap.Authentication.AuthenticationType.AUTH_REQUST_KEY, new String[0]));
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPAuthentication requestKey error:" + e);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.b
    public void a(a.o oVar) {
        com.lge.UDAP.ROAP.d.a a = com.lge.UDAP.ROAP.d.b.a();
        a.a(oVar);
        com.lge.UDAP.ROAP.d.b.a(a);
    }

    @Override // com.lge.UDAP.ROAP.a.b
    public void a(String str) {
        try {
            a(a(Roap.Authentication.AuthenticationType.AUTH_SEND_KEY, str));
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPAuthentication authentication error:" + e);
        }
    }
}
