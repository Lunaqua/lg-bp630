package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.FieldSet;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLite;
import com.google.protobuf.WireFormat;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class GeneratedMessageLite extends AbstractMessageLite {

    /* renamed from: com.google.protobuf.GeneratedMessageLite$1  reason: invalid class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[WireFormat.JavaType.values().length];

        static {
            try {
                a[WireFormat.JavaType.MESSAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[WireFormat.JavaType.ENUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class Builder<MessageType extends GeneratedMessageLite, BuilderType extends Builder> extends AbstractMessageLite.Builder<BuilderType> {
        protected Builder() {
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder
        /* renamed from: clone */
        public BuilderType mo3clone() {
            throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public abstract MessageType getDefaultInstanceForType();

        protected abstract MessageType internalGetResult();

        public abstract BuilderType mergeFrom(MessageType messagetype);

        protected boolean parseUnknownField(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, int i) {
            return codedInputStream.b(i);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class ExtendableBuilder<MessageType extends ExtendableMessage<MessageType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>> extends Builder<MessageType, BuilderType> {
        protected ExtendableBuilder() {
        }

        public final <Type> BuilderType addExtension(GeneratedExtension<MessageType, List<Type>> generatedExtension, Type type) {
            MessageType internalGetResult = internalGetResult();
            internalGetResult.verifyExtensionContainingType(generatedExtension);
            ((ExtendableMessage) internalGetResult).extensions.b((FieldSet) ((GeneratedExtension) generatedExtension).d, (Object) type);
            return this;
        }

        public final <Type> BuilderType clearExtension(GeneratedExtension<MessageType, ?> generatedExtension) {
            MessageType internalGetResult = internalGetResult();
            internalGetResult.verifyExtensionContainingType(generatedExtension);
            ((ExtendableMessage) internalGetResult).extensions.c(((GeneratedExtension) generatedExtension).d);
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder
        /* renamed from: clone */
        public BuilderType mo3clone() {
            throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
        }

        public final <Type> Type getExtension(GeneratedExtension<MessageType, Type> generatedExtension) {
            return (Type) internalGetResult().getExtension(generatedExtension);
        }

        public final <Type> Type getExtension(GeneratedExtension<MessageType, List<Type>> generatedExtension, int i) {
            return (Type) internalGetResult().getExtension(generatedExtension, i);
        }

        public final <Type> int getExtensionCount(GeneratedExtension<MessageType, List<Type>> generatedExtension) {
            return internalGetResult().getExtensionCount(generatedExtension);
        }

        public final boolean hasExtension(GeneratedExtension<MessageType, ?> generatedExtension) {
            return internalGetResult().hasExtension(generatedExtension);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite.Builder
        public abstract MessageType internalGetResult();

        protected final void mergeExtensionFields(MessageType messagetype) {
            ((ExtendableMessage) internalGetResult()).extensions.a(((ExtendableMessage) messagetype).extensions);
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0058  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x005d  */
        @Override // com.google.protobuf.GeneratedMessageLite.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        protected boolean parseUnknownField(com.google.protobuf.CodedInputStream r7, com.google.protobuf.ExtensionRegistryLite r8, int r9) {
            /*
                Method dump skipped, instructions count: 322
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.GeneratedMessageLite.ExtendableBuilder.parseUnknownField(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite, int):boolean");
        }

        public final <Type> BuilderType setExtension(GeneratedExtension<MessageType, List<Type>> generatedExtension, int i, Type type) {
            MessageType internalGetResult = internalGetResult();
            internalGetResult.verifyExtensionContainingType(generatedExtension);
            ((ExtendableMessage) internalGetResult).extensions.a((FieldSet) ((GeneratedExtension) generatedExtension).d, i, (Object) type);
            return this;
        }

        public final <Type> BuilderType setExtension(GeneratedExtension<MessageType, Type> generatedExtension, Type type) {
            MessageType internalGetResult = internalGetResult();
            internalGetResult.verifyExtensionContainingType(generatedExtension);
            ((ExtendableMessage) internalGetResult).extensions.a((FieldSet) ((GeneratedExtension) generatedExtension).d, (Object) type);
            return this;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class ExtendableMessage<MessageType extends ExtendableMessage<MessageType>> extends GeneratedMessageLite {
        private final FieldSet<ExtensionDescriptor> extensions = FieldSet.a();

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        protected class ExtensionWriter {
            private final Iterator<Map.Entry<ExtensionDescriptor, Object>> b;
            private Map.Entry<ExtensionDescriptor, Object> c;
            private final boolean d;

            private ExtensionWriter(boolean z) {
                this.b = ExtendableMessage.this.extensions.f();
                if (this.b.hasNext()) {
                    this.c = this.b.next();
                }
                this.d = z;
            }

            /* synthetic */ ExtensionWriter(ExtendableMessage extendableMessage, boolean z, AnonymousClass1 anonymousClass1) {
                this(z);
            }

            public void a(int i, CodedOutputStream codedOutputStream) {
                while (true) {
                    Map.Entry<ExtensionDescriptor, Object> entry = this.c;
                    if (entry == null || entry.getKey().getNumber() >= i) {
                        return;
                    }
                    ExtensionDescriptor key = this.c.getKey();
                    if (this.d && key.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !key.isRepeated()) {
                        codedOutputStream.d(key.getNumber(), (MessageLite) this.c.getValue());
                    } else {
                        FieldSet.a(key, this.c.getValue(), codedOutputStream);
                    }
                    this.c = this.b.hasNext() ? this.b.next() : null;
                }
            }
        }

        protected ExtendableMessage() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void verifyExtensionContainingType(GeneratedExtension<MessageType, ?> generatedExtension) {
            if (generatedExtension.a() != getDefaultInstanceForType()) {
                throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
            }
        }

        protected boolean extensionsAreInitialized() {
            return this.extensions.g();
        }

        protected int extensionsSerializedSize() {
            return this.extensions.h();
        }

        protected int extensionsSerializedSizeAsMessageSet() {
            return this.extensions.i();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final <Type> Type getExtension(GeneratedExtension<MessageType, Type> generatedExtension) {
            verifyExtensionContainingType(generatedExtension);
            Type type = (Type) this.extensions.b((FieldSet<ExtensionDescriptor>) ((GeneratedExtension) generatedExtension).d);
            return type == null ? (Type) ((GeneratedExtension) generatedExtension).b : type;
        }

        public final <Type> Type getExtension(GeneratedExtension<MessageType, List<Type>> generatedExtension, int i) {
            verifyExtensionContainingType(generatedExtension);
            return (Type) this.extensions.a((FieldSet<ExtensionDescriptor>) ((GeneratedExtension) generatedExtension).d, i);
        }

        public final <Type> int getExtensionCount(GeneratedExtension<MessageType, List<Type>> generatedExtension) {
            verifyExtensionContainingType(generatedExtension);
            return this.extensions.d(((GeneratedExtension) generatedExtension).d);
        }

        public final boolean hasExtension(GeneratedExtension<MessageType, ?> generatedExtension) {
            verifyExtensionContainingType(generatedExtension);
            return this.extensions.a((FieldSet<ExtensionDescriptor>) ((GeneratedExtension) generatedExtension).d);
        }

        protected ExtendableMessage<MessageType>.ExtensionWriter newExtensionWriter() {
            return new ExtensionWriter(this, false, null);
        }

        protected ExtendableMessage<MessageType>.ExtensionWriter newMessageSetExtensionWriter() {
            return new ExtensionWriter(this, true, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class ExtensionDescriptor implements FieldSet.FieldDescriptorLite<ExtensionDescriptor> {
        private final Internal.EnumLiteMap<?> enumTypeMap;
        private final boolean isPacked;
        private final boolean isRepeated;
        private final int number;
        private final WireFormat.FieldType type;

        private ExtensionDescriptor(Internal.EnumLiteMap<?> enumLiteMap, int i, WireFormat.FieldType fieldType, boolean z, boolean z2) {
            this.enumTypeMap = enumLiteMap;
            this.number = i;
            this.type = fieldType;
            this.isRepeated = z;
            this.isPacked = z2;
        }

        /* synthetic */ ExtensionDescriptor(Internal.EnumLiteMap enumLiteMap, int i, WireFormat.FieldType fieldType, boolean z, boolean z2, AnonymousClass1 anonymousClass1) {
            this(enumLiteMap, i, fieldType, z, z2);
        }

        @Override // java.lang.Comparable
        public int compareTo(ExtensionDescriptor extensionDescriptor) {
            return this.number - extensionDescriptor.number;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public Internal.EnumLiteMap<?> getEnumType() {
            return this.enumTypeMap;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public WireFormat.JavaType getLiteJavaType() {
            return this.type.getJavaType();
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public WireFormat.FieldType getLiteType() {
            return this.type;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public int getNumber() {
            return this.number;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public MessageLite.Builder internalMergeFrom(MessageLite.Builder builder, MessageLite messageLite) {
            return ((Builder) builder).mergeFrom((Builder) ((GeneratedMessageLite) messageLite));
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public boolean isPacked() {
            return this.isPacked;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public boolean isRepeated() {
            return this.isRepeated;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class GeneratedExtension<ContainingType extends MessageLite, Type> {
        private ContainingType a;
        private Type b;
        private MessageLite c;
        private ExtensionDescriptor d;

        private GeneratedExtension() {
        }

        /* synthetic */ GeneratedExtension(AnonymousClass1 anonymousClass1) {
            this();
        }

        private void a(ContainingType containingtype, Type type, MessageLite messageLite, ExtensionDescriptor extensionDescriptor) {
            this.a = containingtype;
            this.b = type;
            this.c = messageLite;
            this.d = extensionDescriptor;
        }

        public ContainingType a() {
            return this.a;
        }

        public void a(ContainingType containingtype, MessageLite messageLite, Internal.EnumLiteMap<?> enumLiteMap, int i, WireFormat.FieldType fieldType, boolean z) {
            a(containingtype, Collections.emptyList(), messageLite, new ExtensionDescriptor(enumLiteMap, i, fieldType, true, z, null));
        }

        public void a(ContainingType containingtype, Type type, MessageLite messageLite, Internal.EnumLiteMap<?> enumLiteMap, int i, WireFormat.FieldType fieldType) {
            a(containingtype, type, messageLite, new ExtensionDescriptor(enumLiteMap, i, fieldType, false, false, null));
        }

        public int b() {
            return this.d.getNumber();
        }

        public MessageLite c() {
            return this.c;
        }
    }

    protected GeneratedMessageLite() {
    }

    public static <ContainingType extends MessageLite, Type> GeneratedExtension<ContainingType, Type> newGeneratedExtension() {
        return new GeneratedExtension<>(null);
    }
}
