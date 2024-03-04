package com.google.protobuf;

import java.util.Collections;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class UninitializedMessageException extends RuntimeException {
    private static final long a = -7466929953374883507L;
    private final List<String> b;

    public UninitializedMessageException(MessageLite messageLite) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
        this.b = null;
    }

    public UninitializedMessageException(List<String> list) {
        super(a(list));
        this.b = list;
    }

    private static String a(List<String> list) {
        StringBuilder sb = new StringBuilder("Message missing required fields: ");
        boolean z = true;
        for (String str : list) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(str);
        }
        return sb.toString();
    }

    public List<String> a() {
        return Collections.unmodifiableList(this.b);
    }

    public InvalidProtocolBufferException b() {
        return new InvalidProtocolBufferException(getMessage());
    }
}