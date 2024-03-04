package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.Message;
import com.google.protobuf.UnknownFieldSet;
import com.lge.media.launcher.a;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class AbstractMessage extends AbstractMessageLite implements Message {
    private int memoizedSize = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.protobuf.AbstractMessage$1  reason: invalid class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[Descriptors.FieldDescriptor.Type.values().length];

        static {
            try {
                a[Descriptors.FieldDescriptor.Type.GROUP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[Descriptors.FieldDescriptor.Type.MESSAGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[Descriptors.FieldDescriptor.Type.ENUM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class Builder<BuilderType extends Builder> extends AbstractMessageLite.Builder<BuilderType> implements Message.Builder {
        private static List<String> findMissingFields(Message message) {
            ArrayList arrayList = new ArrayList();
            findMissingFields(message, a.d, arrayList);
            return arrayList;
        }

        private static void findMissingFields(Message message, String str, List<String> list) {
            for (Descriptors.FieldDescriptor fieldDescriptor : message.getDescriptorForType().getFields()) {
                if (fieldDescriptor.isRequired() && !message.hasField(fieldDescriptor)) {
                    list.add(str + fieldDescriptor.getName());
                }
            }
            for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : message.getAllFields().entrySet()) {
                Descriptors.FieldDescriptor key = entry.getKey();
                Object value = entry.getValue();
                if (key.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    if (key.isRepeated()) {
                        int i = 0;
                        for (Message message2 : (List) value) {
                            findMissingFields(message2, subMessagePrefix(str, key, i), list);
                            i++;
                        }
                    } else if (message.hasField(key)) {
                        findMissingFields((Message) value, subMessagePrefix(str, key, -1), list);
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Removed duplicated region for block: B:37:0x008f  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0094  */
        /* JADX WARN: Removed duplicated region for block: B:81:0x0150  */
        /* JADX WARN: Removed duplicated region for block: B:82:0x0154  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static boolean mergeFieldFrom(com.google.protobuf.CodedInputStream r8, com.google.protobuf.UnknownFieldSet.Builder r9, com.google.protobuf.ExtensionRegistryLite r10, com.google.protobuf.Message.Builder r11, int r12) {
            /*
                Method dump skipped, instructions count: 344
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.AbstractMessage.Builder.mergeFieldFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.UnknownFieldSet$Builder, com.google.protobuf.ExtensionRegistryLite, com.google.protobuf.Message$Builder, int):boolean");
        }

        private static void mergeMessageSetExtensionFromCodedStream(CodedInputStream codedInputStream, UnknownFieldSet.Builder builder, ExtensionRegistryLite extensionRegistryLite, Message.Builder builder2) {
            Descriptors.Descriptor descriptorForType = builder2.getDescriptorForType();
            int i = 0;
            Message.Builder builder3 = null;
            Descriptors.FieldDescriptor fieldDescriptor = null;
            ByteString byteString = null;
            while (true) {
                int a = codedInputStream.a();
                if (a == 0) {
                    break;
                } else if (a == WireFormat.n) {
                    i = codedInputStream.m();
                    if (i != 0) {
                        ExtensionRegistry.ExtensionInfo findExtensionByNumber = extensionRegistryLite instanceof ExtensionRegistry ? ((ExtensionRegistry) extensionRegistryLite).findExtensionByNumber(descriptorForType, i) : null;
                        if (findExtensionByNumber != null) {
                            fieldDescriptor = findExtensionByNumber.a;
                            builder3 = findExtensionByNumber.b.newBuilderForType();
                            Message message = (Message) builder2.getField(fieldDescriptor);
                            if (message != null) {
                                builder3.mergeFrom(message);
                            }
                            if (byteString != null) {
                                builder3.mergeFrom(CodedInputStream.a(byteString.f()));
                                byteString = null;
                            }
                        } else if (byteString != null) {
                            builder.mergeField(i, UnknownFieldSet.Field.a().a(byteString).a());
                            byteString = null;
                        }
                    }
                } else if (a == WireFormat.o) {
                    if (i == 0) {
                        byteString = codedInputStream.l();
                    } else if (builder3 == null) {
                        builder.mergeField(i, UnknownFieldSet.Field.a().a(codedInputStream.l()).a());
                    } else {
                        codedInputStream.a(builder3, extensionRegistryLite);
                    }
                } else if (!codedInputStream.b(a)) {
                    break;
                }
            }
            codedInputStream.a(WireFormat.m);
            if (builder3 != null) {
                builder2.setField(fieldDescriptor, builder3.build());
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public static UninitializedMessageException newUninitializedMessageException(Message message) {
            return new UninitializedMessageException(findMissingFields(message));
        }

        private static String subMessagePrefix(String str, Descriptors.FieldDescriptor fieldDescriptor, int i) {
            StringBuilder sb = new StringBuilder(str);
            if (fieldDescriptor.isExtension()) {
                sb.append('(');
                sb.append(fieldDescriptor.getFullName());
                sb.append(')');
            } else {
                sb.append(fieldDescriptor.getName());
            }
            if (i != -1) {
                sb.append('[');
                sb.append(i);
                sb.append(']');
            }
            sb.append('.');
            return sb.toString();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType clear() {
            for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : getAllFields().entrySet()) {
                clearField(entry.getKey());
            }
            return this;
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder
        /* renamed from: clone */
        public abstract BuilderType mo3clone();

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public boolean mergeDelimitedFrom(InputStream inputStream) {
            return super.mergeDelimitedFrom(inputStream);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public boolean mergeDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return super.mergeDelimitedFrom(inputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(ByteString byteString) {
            return (BuilderType) super.mergeFrom(byteString);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (BuilderType) super.mergeFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(CodedInputStream codedInputStream) {
            return mergeFrom(codedInputStream, (ExtensionRegistryLite) ExtensionRegistry.getEmptyRegistry());
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            int a;
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
            do {
                a = codedInputStream.a();
                if (a == 0) {
                    break;
                }
            } while (mergeFieldFrom(codedInputStream, newBuilder, extensionRegistryLite, this, a));
            setUnknownFields(newBuilder.build());
            return this;
        }

        @Override // com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(Message message) {
            Object value;
            if (message.getDescriptorForType() == getDescriptorForType()) {
                for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : message.getAllFields().entrySet()) {
                    Descriptors.FieldDescriptor key = entry.getKey();
                    if (key.isRepeated()) {
                        for (Object obj : (List) entry.getValue()) {
                            addRepeatedField(key, obj);
                        }
                    } else {
                        if (key.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                            Message message2 = (Message) getField(key);
                            if (message2 != message2.getDefaultInstanceForType()) {
                                value = message2.newBuilderForType().mergeFrom(message2).mergeFrom((Message) entry.getValue()).build();
                                setField(key, value);
                            }
                        }
                        value = entry.getValue();
                        setField(key, value);
                    }
                }
                mergeUnknownFields(message.getUnknownFields());
                return this;
            }
            throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type.");
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(InputStream inputStream) {
            return (BuilderType) super.mergeFrom(inputStream);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (BuilderType) super.mergeFrom(inputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(byte[] bArr) {
            return (BuilderType) super.mergeFrom(bArr);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(byte[] bArr, int i, int i2) {
            return (BuilderType) super.mergeFrom(bArr, i, i2);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) {
            return (BuilderType) super.mergeFrom(bArr, i, i2, extensionRegistryLite);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (BuilderType) super.mergeFrom(bArr, extensionRegistryLite);
        }

        @Override // com.google.protobuf.Message.Builder
        public BuilderType mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            setUnknownFields(UnknownFieldSet.newBuilder(getUnknownFields()).mergeFrom(unknownFieldSet).build());
            return this;
        }
    }

    @Override // com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Message) {
            Message message = (Message) obj;
            return getDescriptorForType() == message.getDescriptorForType() && getAllFields().equals(message.getAllFields()) && getUnknownFields().equals(message.getUnknownFields());
        }
        return false;
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        boolean messageSetWireFormat = getDescriptorForType().getOptions().getMessageSetWireFormat();
        for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : getAllFields().entrySet()) {
            Descriptors.FieldDescriptor key = entry.getKey();
            Object value = entry.getValue();
            i2 += (messageSetWireFormat && key.isExtension() && key.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && !key.isRepeated()) ? CodedOutputStream.h(key.getNumber(), (Message) value) : FieldSet.c(key, value);
        }
        UnknownFieldSet unknownFields = getUnknownFields();
        int serializedSizeAsMessageSet = i2 + (messageSetWireFormat ? unknownFields.getSerializedSizeAsMessageSet() : unknownFields.getSerializedSize());
        this.memoizedSize = serializedSizeAsMessageSet;
        return serializedSizeAsMessageSet;
    }

    @Override // com.google.protobuf.Message
    public int hashCode() {
        return ((((779 + getDescriptorForType().hashCode()) * 53) + getAllFields().hashCode()) * 29) + getUnknownFields().hashCode();
    }

    @Override // com.google.protobuf.MessageLite
    public boolean isInitialized() {
        for (Descriptors.FieldDescriptor fieldDescriptor : getDescriptorForType().getFields()) {
            if (fieldDescriptor.isRequired() && !hasField(fieldDescriptor)) {
                return false;
            }
        }
        for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : getAllFields().entrySet()) {
            Descriptors.FieldDescriptor key = entry.getKey();
            if (key.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                if (key.isRepeated()) {
                    for (Message message : (List) entry.getValue()) {
                        if (!message.isInitialized()) {
                            return false;
                        }
                    }
                    continue;
                } else if (!((Message) entry.getValue()).isInitialized()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // com.google.protobuf.Message
    public final String toString() {
        return TextFormat.a(this);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) {
        boolean messageSetWireFormat = getDescriptorForType().getOptions().getMessageSetWireFormat();
        for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : getAllFields().entrySet()) {
            Descriptors.FieldDescriptor key = entry.getKey();
            Object value = entry.getValue();
            if (messageSetWireFormat && key.isExtension() && key.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && !key.isRepeated()) {
                codedOutputStream.d(key.getNumber(), (Message) value);
            } else {
                FieldSet.a(key, value, codedOutputStream);
            }
        }
        UnknownFieldSet unknownFields = getUnknownFields();
        if (messageSetWireFormat) {
            unknownFields.writeAsMessageSetTo(codedOutputStream);
        } else {
            unknownFields.writeTo(codedOutputStream);
        }
    }
}
