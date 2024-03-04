package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.MessageLite;
import java.io.InputStream;
import java.util.Map;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public interface Message extends MessageLite {

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface Builder extends MessageLite.Builder {
        Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj);

        @Override // 
        Message build();

        @Override // 
        Message buildPartial();

        @Override // 
        Builder clear();

        Builder clearField(Descriptors.FieldDescriptor fieldDescriptor);

        @Override // 
        Builder clone();

        Map<Descriptors.FieldDescriptor, Object> getAllFields();

        @Override // 
        Message getDefaultInstanceForType();

        Descriptors.Descriptor getDescriptorForType();

        Object getField(Descriptors.FieldDescriptor fieldDescriptor);

        Object getRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i);

        int getRepeatedFieldCount(Descriptors.FieldDescriptor fieldDescriptor);

        UnknownFieldSet getUnknownFields();

        boolean hasField(Descriptors.FieldDescriptor fieldDescriptor);

        @Override // 
        boolean mergeDelimitedFrom(InputStream inputStream);

        @Override // 
        boolean mergeDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite);

        @Override // 
        Builder mergeFrom(ByteString byteString);

        @Override // 
        Builder mergeFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite);

        @Override // 
        Builder mergeFrom(CodedInputStream codedInputStream);

        @Override // 
        Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite);

        Builder mergeFrom(Message message);

        @Override // 
        Builder mergeFrom(InputStream inputStream);

        @Override // 
        Builder mergeFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite);

        @Override // 
        Builder mergeFrom(byte[] bArr);

        @Override // 
        Builder mergeFrom(byte[] bArr, int i, int i2);

        @Override // 
        Builder mergeFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite);

        @Override // 
        Builder mergeFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite);

        Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet);

        Builder newBuilderForField(Descriptors.FieldDescriptor fieldDescriptor);

        Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj);

        Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj);

        Builder setUnknownFields(UnknownFieldSet unknownFieldSet);
    }

    boolean equals(Object obj);

    Map<Descriptors.FieldDescriptor, Object> getAllFields();

    @Override // 
    Message getDefaultInstanceForType();

    Descriptors.Descriptor getDescriptorForType();

    Object getField(Descriptors.FieldDescriptor fieldDescriptor);

    Object getRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i);

    int getRepeatedFieldCount(Descriptors.FieldDescriptor fieldDescriptor);

    UnknownFieldSet getUnknownFields();

    boolean hasField(Descriptors.FieldDescriptor fieldDescriptor);

    int hashCode();

    @Override // 
    Builder newBuilderForType();

    @Override // 
    Builder toBuilder();

    String toString();
}
