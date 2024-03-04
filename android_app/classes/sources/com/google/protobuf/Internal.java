package com.google.protobuf;

import java.io.UnsupportedEncodingException;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class Internal {

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface EnumLite {
        int getNumber();
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface EnumLiteMap<T extends EnumLite> {
        T findValueByNumber(int i);
    }

    public static String a(String str) {
        try {
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Java VM does not support a standard character set.", e);
        }
    }

    public static ByteString b(String str) {
        try {
            return ByteString.a(str.getBytes("ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Java VM does not support a standard character set.", e);
        }
    }
}
