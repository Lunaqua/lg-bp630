package com.google.protobuf;

import android.support.v4.d.a.a;
import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessage;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class ExtensionRegistry extends ExtensionRegistryLite {
    private static final ExtensionRegistry EMPTY = new ExtensionRegistry(true);
    private final Map<String, ExtensionInfo> extensionsByName;
    private final Map<DescriptorIntPair, ExtensionInfo> extensionsByNumber;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class DescriptorIntPair {
        private final Descriptors.Descriptor a;
        private final int b;

        DescriptorIntPair(Descriptors.Descriptor descriptor, int i) {
            this.a = descriptor;
            this.b = i;
        }

        public boolean equals(Object obj) {
            if (obj instanceof DescriptorIntPair) {
                DescriptorIntPair descriptorIntPair = (DescriptorIntPair) obj;
                return this.a == descriptorIntPair.a && this.b == descriptorIntPair.b;
            }
            return false;
        }

        public int hashCode() {
            return (this.a.hashCode() * a.a) + this.b;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class ExtensionInfo {
        public final Descriptors.FieldDescriptor a;
        public final Message b;

        private ExtensionInfo(Descriptors.FieldDescriptor fieldDescriptor) {
            this.a = fieldDescriptor;
            this.b = null;
        }

        private ExtensionInfo(Descriptors.FieldDescriptor fieldDescriptor, Message message) {
            this.a = fieldDescriptor;
            this.b = message;
        }
    }

    private ExtensionRegistry() {
        this.extensionsByName = new HashMap();
        this.extensionsByNumber = new HashMap();
    }

    private ExtensionRegistry(ExtensionRegistry extensionRegistry) {
        super(extensionRegistry);
        this.extensionsByName = Collections.unmodifiableMap(extensionRegistry.extensionsByName);
        this.extensionsByNumber = Collections.unmodifiableMap(extensionRegistry.extensionsByNumber);
    }

    private ExtensionRegistry(boolean z) {
        super(ExtensionRegistryLite.getEmptyRegistry());
        this.extensionsByName = Collections.emptyMap();
        this.extensionsByNumber = Collections.emptyMap();
    }

    private void add(ExtensionInfo extensionInfo) {
        if (!extensionInfo.a.isExtension()) {
            throw new IllegalArgumentException("ExtensionRegistry.add() was given a FieldDescriptor for a regular (non-extension) field.");
        }
        this.extensionsByName.put(extensionInfo.a.getFullName(), extensionInfo);
        this.extensionsByNumber.put(new DescriptorIntPair(extensionInfo.a.getContainingType(), extensionInfo.a.getNumber()), extensionInfo);
        Descriptors.FieldDescriptor fieldDescriptor = extensionInfo.a;
        if (fieldDescriptor.getContainingType().getOptions().getMessageSetWireFormat() && fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && fieldDescriptor.isOptional() && fieldDescriptor.getExtensionScope() == fieldDescriptor.getMessageType()) {
            this.extensionsByName.put(fieldDescriptor.getMessageType().getFullName(), extensionInfo);
        }
    }

    public static ExtensionRegistry getEmptyRegistry() {
        return EMPTY;
    }

    public static ExtensionRegistry newInstance() {
        return new ExtensionRegistry();
    }

    public void add(Descriptors.FieldDescriptor fieldDescriptor) {
        if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            throw new IllegalArgumentException("ExtensionRegistry.add() must be provided a default instance when adding an embedded message extension.");
        }
        add(new ExtensionInfo(fieldDescriptor, null));
    }

    public void add(Descriptors.FieldDescriptor fieldDescriptor, Message message) {
        if (fieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            throw new IllegalArgumentException("ExtensionRegistry.add() provided a default instance for a non-message extension.");
        }
        add(new ExtensionInfo(fieldDescriptor, message));
    }

    public void add(GeneratedMessage.GeneratedExtension<?, ?> generatedExtension) {
        ExtensionInfo extensionInfo;
        if (generatedExtension.a().getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            extensionInfo = new ExtensionInfo(generatedExtension.a(), null);
        } else if (generatedExtension.b() == null) {
            throw new IllegalStateException("Registered message-type extension had null default instance: " + generatedExtension.a().getFullName());
        } else {
            extensionInfo = new ExtensionInfo(generatedExtension.a(), generatedExtension.b());
        }
        add(extensionInfo);
    }

    public ExtensionInfo findExtensionByName(String str) {
        return this.extensionsByName.get(str);
    }

    public ExtensionInfo findExtensionByNumber(Descriptors.Descriptor descriptor, int i) {
        return this.extensionsByNumber.get(new DescriptorIntPair(descriptor, i));
    }

    @Override // com.google.protobuf.ExtensionRegistryLite
    public ExtensionRegistry getUnmodifiable() {
        return new ExtensionRegistry(this);
    }
}
