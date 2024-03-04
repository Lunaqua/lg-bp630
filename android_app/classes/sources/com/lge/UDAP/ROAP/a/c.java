package com.lge.UDAP.ROAP.a;

import com.lge.UDAP.ROAP.Roap;
import com.lge.UDAP.ROAP.a;
import com.lge.UDAP.ROAP.a.b;
import java.io.IOException;
import java.io.PipedOutputStream;
import java.io.PrintStream;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class c extends com.lge.UDAP.ROAP.a implements a.e {
    public c(PipedOutputStream pipedOutputStream) {
        super(pipedOutputStream);
    }

    private byte[] b(b.a aVar, b.c cVar) {
        Roap.ROAPMessage.a newBuilder = Roap.ROAPMessage.newBuilder();
        newBuilder.setType(Roap.ROAPMessage.MessageType.COMMAND);
        Roap.CommandMessage.a newBuilder2 = Roap.CommandMessage.newBuilder();
        newBuilder2.setType(Roap.CommandMessage.CommandType.KEY);
        Roap.Key.a newBuilder3 = Roap.Key.newBuilder();
        newBuilder3.setAction(Roap.Key.Action.valueOf(aVar.a()));
        newBuilder3.setCode(Roap.Key.KeyCode.valueOf(cVar.a()));
        newBuilder2.setKeyMessage(newBuilder3);
        newBuilder.setCommandMessage(newBuilder2);
        return newBuilder.build().toByteArray();
    }

    @Override // com.lge.UDAP.ROAP.a.e
    public void a(b.a aVar, b.c cVar) {
        try {
            a(b(aVar, cVar));
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPKey keyAction error:" + e);
        }
    }
}
