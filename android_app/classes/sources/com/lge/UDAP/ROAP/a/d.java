package com.lge.UDAP.ROAP.a;

import com.lge.UDAP.ROAP.Roap;
import com.lge.UDAP.ROAP.a;
import com.lge.UDAP.ROAP.a.b;
import java.io.IOException;
import java.io.PipedOutputStream;
import java.io.PrintStream;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class d extends com.lge.UDAP.ROAP.a implements a.ab {
    public d(PipedOutputStream pipedOutputStream) {
        super(pipedOutputStream);
    }

    private byte[] a(b.d dVar, int[] iArr, int i, boolean z) {
        Roap.ROAPMessage.a newBuilder = Roap.ROAPMessage.newBuilder();
        newBuilder.setType(Roap.ROAPMessage.MessageType.COMMAND);
        Roap.CommandMessage.a newBuilder2 = Roap.CommandMessage.newBuilder();
        newBuilder2.setType(Roap.CommandMessage.CommandType.TOUCH);
        Roap.Touch.a newBuilder3 = Roap.Touch.newBuilder();
        newBuilder3.setType(Roap.Touch.TouchType.valueOf(dVar.a()));
        newBuilder3.setXDelta(iArr[0]);
        newBuilder3.setYDelta(iArr[1]);
        newBuilder3.setZDelta(iArr[2]);
        newBuilder3.setRatio(i);
        newBuilder3.setBData(z);
        newBuilder2.setTouchMessage(newBuilder3);
        newBuilder.setCommandMessage(newBuilder2);
        return newBuilder.build().toByteArray();
    }

    @Override // com.lge.UDAP.ROAP.a.ab
    public void a() {
        try {
            a(a(b.d.CLICK, new int[]{0, 0, 0}, 0, false));
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPTouch touch click error:" + e);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.ab
    public void a(int i) {
        try {
            a(a(b.d.ZOOM, new int[]{0, 0, 0}, i, false));
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPTouch touch zoom error:" + e);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.ab
    public void a(int i, int i2, int i3, int i4) {
        try {
            a(a(b.d.MOVE, new int[]{i, i2, i3}, i4, false));
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPTouch touch move error:" + e);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.ab
    public void a(boolean z) {
        try {
            a(a(b.d.DRAG, new int[]{0, 0, 0}, 0, z));
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPTouch touch drag error:" + e);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.ab
    public void b(int i, int i2, int i3, int i4) {
        try {
            a(a(b.d.WHEEL, new int[]{i, i2, i3}, i4, false));
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPTouch touch wheel error:" + e);
        }
    }
}
