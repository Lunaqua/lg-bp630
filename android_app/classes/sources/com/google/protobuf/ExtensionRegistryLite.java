package com.google.protobuf;

import android.support.v4.d.a.a;
import com.google.protobuf.GeneratedMessageLite;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ExtensionRegistryLite {
    private static final ExtensionRegistryLite EMPTY = new ExtensionRegistryLite(true);
    private final Map<ObjectIntPair, GeneratedMessageLite.GeneratedExtension<?, ?>> extensionsByNumber;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static final class ObjectIntPair {
        private final Object a;
        private final int b;

        ObjectIntPair(Object obj, int i) {
            this.a = obj;
            this.b = i;
        }

        public boolean equals(Object obj) {
            if (obj instanceof ObjectIntPair) {
                ObjectIntPair objectIntPair = (ObjectIntPair) obj;
                return this.a == objectIntPair.a && this.b == objectIntPair.b;
            }
            return false;
        }

        public int hashCode() {
            return (System.identityHashCode(this.a) * a.a) + this.b;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExtensionRegistryLite() {
        this.extensionsByNumber = new HashMap();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExtensionRegistryLite(ExtensionRegistryLite extensionRegistryLite) {
        this.extensionsByNumber = extensionRegistryLite == EMPTY ? Collections.emptyMap() : Collections.unmodifiableMap(extensionRegistryLite.extensionsByNumber);
    }

    private ExtensionRegistryLite(boolean z) {
        this.extensionsByNumber = Collections.emptyMap();
    }

    public static ExtensionRegistryLite getEmptyRegistry() {
        return EMPTY;
    }

    public static ExtensionRegistryLite newInstance() {
        return new ExtensionRegistryLite();
    }

    public final void add(GeneratedMessageLite.GeneratedExtension<?, ?> generatedExtension) {
        this.extensionsByNumber.put(new ObjectIntPair(generatedExtension.a(), generatedExtension.b()), generatedExtension);
    }

    public <ContainingType extends MessageLite> GeneratedMessageLite.GeneratedExtension<ContainingType, ?> findLiteExtensionByNumber(ContainingType containingtype, int i) {
        return (GeneratedMessageLite.GeneratedExtension<ContainingType, ?>) this.extensionsByNumber.get(new ObjectIntPair(containingtype, i));
    }

    public ExtensionRegistryLite getUnmodifiable() {
        return new ExtensionRegistryLite(this);
    }
}
