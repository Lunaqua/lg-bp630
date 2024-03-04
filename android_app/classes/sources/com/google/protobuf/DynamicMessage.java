package com.google.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.Descriptors;
import java.io.InputStream;
import java.util.Map;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class DynamicMessage extends AbstractMessage {
    private final FieldSet<Descriptors.FieldDescriptor> fields;
    private int memoizedSize;
    private final Descriptors.Descriptor type;
    private final UnknownFieldSet unknownFields;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class Builder extends AbstractMessage.Builder<Builder> {
        private FieldSet<Descriptors.FieldDescriptor> fields;
        private final Descriptors.Descriptor type;
        private UnknownFieldSet unknownFields;

        private Builder(Descriptors.Descriptor descriptor) {
            this.type = descriptor;
            this.fields = FieldSet.a();
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public DynamicMessage buildParsed() {
            if (isInitialized()) {
                return buildPartial();
            }
            throw newUninitializedMessageException((Message) new DynamicMessage(this.type, this.fields, this.unknownFields)).b();
        }

        private void verifyContainingType(Descriptors.FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.getContainingType() != this.type) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
        }

        @Override // com.google.protobuf.Message.Builder
        public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            verifyContainingType(fieldDescriptor);
            this.fields.b((FieldSet<Descriptors.FieldDescriptor>) fieldDescriptor, obj);
            return this;
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public DynamicMessage build() {
            if (this.fields == null || isInitialized()) {
                return buildPartial();
            }
            throw newUninitializedMessageException((Message) new DynamicMessage(this.type, this.fields, this.unknownFields));
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public DynamicMessage buildPartial() {
            FieldSet<Descriptors.FieldDescriptor> fieldSet = this.fields;
            if (fieldSet != null) {
                fieldSet.c();
                DynamicMessage dynamicMessage = new DynamicMessage(this.type, this.fields, this.unknownFields);
                this.fields = null;
                this.unknownFields = null;
                return dynamicMessage;
            }
            throw new IllegalStateException("build() has already been called on this Builder.");
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            FieldSet<Descriptors.FieldDescriptor> fieldSet = this.fields;
            if (fieldSet != null) {
                fieldSet.d();
                return this;
            }
            throw new IllegalStateException("Cannot call clear() after build().");
        }

        @Override // com.google.protobuf.Message.Builder
        public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            verifyContainingType(fieldDescriptor);
            this.fields.c(fieldDescriptor);
            return this;
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
        /* renamed from: clone */
        public Builder mo3clone() {
            Builder builder = new Builder(this.type);
            builder.fields.a(this.fields);
            return builder;
        }

        @Override // com.google.protobuf.Message.Builder
        public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
            return this.fields.e();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public DynamicMessage getDefaultInstanceForType() {
            return DynamicMessage.getDefaultInstance(this.type);
        }

        @Override // com.google.protobuf.Message.Builder
        public Descriptors.Descriptor getDescriptorForType() {
            return this.type;
        }

        @Override // com.google.protobuf.Message.Builder
        public Object getField(Descriptors.FieldDescriptor fieldDescriptor) {
            verifyContainingType(fieldDescriptor);
            Object b = this.fields.b((FieldSet<Descriptors.FieldDescriptor>) fieldDescriptor);
            return b == null ? fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE ? DynamicMessage.getDefaultInstance(fieldDescriptor.getMessageType()) : fieldDescriptor.getDefaultValue() : b;
        }

        @Override // com.google.protobuf.Message.Builder
        public Object getRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i) {
            verifyContainingType(fieldDescriptor);
            return this.fields.a((FieldSet<Descriptors.FieldDescriptor>) fieldDescriptor, i);
        }

        @Override // com.google.protobuf.Message.Builder
        public int getRepeatedFieldCount(Descriptors.FieldDescriptor fieldDescriptor) {
            verifyContainingType(fieldDescriptor);
            return this.fields.d(fieldDescriptor);
        }

        @Override // com.google.protobuf.Message.Builder
        public UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.google.protobuf.Message.Builder
        public boolean hasField(Descriptors.FieldDescriptor fieldDescriptor) {
            verifyContainingType(fieldDescriptor);
            return this.fields.a((FieldSet<Descriptors.FieldDescriptor>) fieldDescriptor);
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public boolean isInitialized() {
            return DynamicMessage.isInitialized(this.type, this.fields);
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Builder mergeFrom(Message message) {
            if (message instanceof DynamicMessage) {
                DynamicMessage dynamicMessage = (DynamicMessage) message;
                if (dynamicMessage.type == this.type) {
                    this.fields.a(dynamicMessage.fields);
                    mergeUnknownFields(dynamicMessage.unknownFields);
                    return this;
                }
                throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type.");
            }
            return (Builder) super.mergeFrom(message);
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            this.unknownFields = UnknownFieldSet.newBuilder(this.unknownFields).mergeFrom(unknownFieldSet).build();
            return this;
        }

        @Override // com.google.protobuf.Message.Builder
        public Builder newBuilderForField(Descriptors.FieldDescriptor fieldDescriptor) {
            verifyContainingType(fieldDescriptor);
            if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                return new Builder(fieldDescriptor.getMessageType());
            }
            throw new IllegalArgumentException("newBuilderForField is only valid for fields with message type.");
        }

        @Override // com.google.protobuf.Message.Builder
        public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            verifyContainingType(fieldDescriptor);
            this.fields.a((FieldSet<Descriptors.FieldDescriptor>) fieldDescriptor, obj);
            return this;
        }

        @Override // com.google.protobuf.Message.Builder
        public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            verifyContainingType(fieldDescriptor);
            this.fields.a((FieldSet<Descriptors.FieldDescriptor>) fieldDescriptor, i, obj);
            return this;
        }

        @Override // com.google.protobuf.Message.Builder
        public Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            this.unknownFields = unknownFieldSet;
            return this;
        }
    }

    private DynamicMessage(Descriptors.Descriptor descriptor, FieldSet<Descriptors.FieldDescriptor> fieldSet, UnknownFieldSet unknownFieldSet) {
        this.memoizedSize = -1;
        this.type = descriptor;
        this.fields = fieldSet;
        this.unknownFields = unknownFieldSet;
    }

    public static DynamicMessage getDefaultInstance(Descriptors.Descriptor descriptor) {
        return new DynamicMessage(descriptor, FieldSet.b(), UnknownFieldSet.getDefaultInstance());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isInitialized(Descriptors.Descriptor descriptor, FieldSet<Descriptors.FieldDescriptor> fieldSet) {
        for (Descriptors.FieldDescriptor fieldDescriptor : descriptor.getFields()) {
            if (fieldDescriptor.isRequired() && !fieldSet.a((FieldSet<Descriptors.FieldDescriptor>) fieldDescriptor)) {
                return false;
            }
        }
        return fieldSet.g();
    }

    public static Builder newBuilder(Descriptors.Descriptor descriptor) {
        return new Builder(descriptor);
    }

    public static Builder newBuilder(Message message) {
        return new Builder(message.getDescriptorForType()).mergeFrom(message);
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor descriptor, ByteString byteString) {
        return newBuilder(descriptor).mergeFrom(byteString).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor descriptor, ByteString byteString, ExtensionRegistry extensionRegistry) {
        return newBuilder(descriptor).mergeFrom(byteString, (ExtensionRegistryLite) extensionRegistry).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor descriptor, CodedInputStream codedInputStream) {
        return newBuilder(descriptor).mergeFrom(codedInputStream).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor descriptor, CodedInputStream codedInputStream, ExtensionRegistry extensionRegistry) {
        return newBuilder(descriptor).mergeFrom(codedInputStream, (ExtensionRegistryLite) extensionRegistry).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor descriptor, InputStream inputStream) {
        return newBuilder(descriptor).mergeFrom(inputStream).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor descriptor, InputStream inputStream, ExtensionRegistry extensionRegistry) {
        return newBuilder(descriptor).mergeFrom(inputStream, (ExtensionRegistryLite) extensionRegistry).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor descriptor, byte[] bArr) {
        return newBuilder(descriptor).mergeFrom(bArr).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor descriptor, byte[] bArr, ExtensionRegistry extensionRegistry) {
        return newBuilder(descriptor).mergeFrom(bArr, (ExtensionRegistryLite) extensionRegistry).buildParsed();
    }

    private void verifyContainingType(Descriptors.FieldDescriptor fieldDescriptor) {
        if (fieldDescriptor.getContainingType() != this.type) {
            throw new IllegalArgumentException("FieldDescriptor does not match message type.");
        }
    }

    @Override // com.google.protobuf.Message
    public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
        return this.fields.e();
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public DynamicMessage getDefaultInstanceForType() {
        return getDefaultInstance(this.type);
    }

    @Override // com.google.protobuf.Message
    public Descriptors.Descriptor getDescriptorForType() {
        return this.type;
    }

    @Override // com.google.protobuf.Message
    public Object getField(Descriptors.FieldDescriptor fieldDescriptor) {
        verifyContainingType(fieldDescriptor);
        Object b = this.fields.b((FieldSet<Descriptors.FieldDescriptor>) fieldDescriptor);
        return b == null ? fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE ? getDefaultInstance(fieldDescriptor.getMessageType()) : fieldDescriptor.getDefaultValue() : b;
    }

    @Override // com.google.protobuf.Message
    public Object getRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i) {
        verifyContainingType(fieldDescriptor);
        return this.fields.a((FieldSet<Descriptors.FieldDescriptor>) fieldDescriptor, i);
    }

    @Override // com.google.protobuf.Message
    public int getRepeatedFieldCount(Descriptors.FieldDescriptor fieldDescriptor) {
        verifyContainingType(fieldDescriptor);
        return this.fields.d(fieldDescriptor);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int h;
        int serializedSize;
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        if (this.type.getOptions().getMessageSetWireFormat()) {
            h = this.fields.i();
            serializedSize = this.unknownFields.getSerializedSizeAsMessageSet();
        } else {
            h = this.fields.h();
            serializedSize = this.unknownFields.getSerializedSize();
        }
        int i2 = h + serializedSize;
        this.memoizedSize = i2;
        return i2;
    }

    @Override // com.google.protobuf.Message
    public UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    @Override // com.google.protobuf.Message
    public boolean hasField(Descriptors.FieldDescriptor fieldDescriptor) {
        verifyContainingType(fieldDescriptor);
        return this.fields.a((FieldSet<Descriptors.FieldDescriptor>) fieldDescriptor);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public boolean isInitialized() {
        return isInitialized(this.type, this.fields);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return new Builder(this.type);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder toBuilder() {
        return newBuilderForType().mergeFrom((Message) this);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) {
        if (this.type.getOptions().getMessageSetWireFormat()) {
            this.fields.b(codedOutputStream);
            this.unknownFields.writeAsMessageSetTo(codedOutputStream);
            return;
        }
        this.fields.a(codedOutputStream);
        this.unknownFields.writeTo(codedOutputStream);
    }
}
