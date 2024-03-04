package android.support.v4.h;

import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.support.v4.j.q;
import android.util.Base64;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class c {
    private final String a;
    private final String b;
    private final String c;
    private final List<List<byte[]>> d;
    private final int e;
    private final String f;

    public c(@af String str, @af String str2, @af String str3, @android.support.annotation.e int i) {
        this.a = (String) q.a(str);
        this.b = (String) q.a(str2);
        this.c = (String) q.a(str3);
        this.d = null;
        q.a(i != 0);
        this.e = i;
        this.f = this.a + "-" + this.b + "-" + this.c;
    }

    public c(@af String str, @af String str2, @af String str3, @af List<List<byte[]>> list) {
        this.a = (String) q.a(str);
        this.b = (String) q.a(str2);
        this.c = (String) q.a(str3);
        this.d = (List) q.a(list);
        this.e = 0;
        this.f = this.a + "-" + this.b + "-" + this.c;
    }

    @af
    public String a() {
        return this.a;
    }

    @af
    public String b() {
        return this.b;
    }

    @af
    public String c() {
        return this.c;
    }

    @ag
    public List<List<byte[]>> d() {
        return this.d;
    }

    @android.support.annotation.e
    public int e() {
        return this.e;
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public String f() {
        return this.f;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.a + ", mProviderPackage: " + this.b + ", mQuery: " + this.c + ", mCertificates:");
        for (int i = 0; i < this.d.size(); i++) {
            sb.append(" [");
            List<byte[]> list = this.d.get(i);
            for (int i2 = 0; i2 < list.size(); i2++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list.get(i2), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.e);
        return sb.toString();
    }
}
