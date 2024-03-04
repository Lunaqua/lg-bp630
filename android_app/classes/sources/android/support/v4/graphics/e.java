package android.support.v4.graphics;

import android.graphics.PointF;
import android.support.annotation.af;
import android.support.v4.j.q;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class e {
    private final PointF a;
    private final float b;
    private final PointF c;
    private final float d;

    public e(@af PointF pointF, float f, @af PointF pointF2, float f2) {
        this.a = (PointF) q.a(pointF, "start == null");
        this.b = f;
        this.c = (PointF) q.a(pointF2, "end == null");
        this.d = f2;
    }

    @af
    public PointF a() {
        return this.a;
    }

    public float b() {
        return this.b;
    }

    @af
    public PointF c() {
        return this.c;
    }

    public float d() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof e) {
            e eVar = (e) obj;
            return Float.compare(this.b, eVar.b) == 0 && Float.compare(this.d, eVar.d) == 0 && this.a.equals(eVar.a) && this.c.equals(eVar.c);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.a.hashCode() * 31;
        float f = this.b;
        int floatToIntBits = (((hashCode + (f != 0.0f ? Float.floatToIntBits(f) : 0)) * 31) + this.c.hashCode()) * 31;
        float f2 = this.d;
        return floatToIntBits + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0);
    }

    public String toString() {
        return "PathSegment{start=" + this.a + ", startFraction=" + this.b + ", end=" + this.c + ", endFraction=" + this.d + '}';
    }
}
