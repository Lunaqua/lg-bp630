package com.google.protobuf;

import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public interface MessageLite {

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface Builder extends Cloneable {
        MessageLite build();

        MessageLite buildPartial();

        Builder clear();

        Builder clone();

        MessageLite getDefaultInstanceForType();

        boolean isInitialized();

        boolean mergeDelimitedFrom(InputStream inputStream);

        boolean mergeDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite);

        Builder mergeFrom(ByteString byteString);

        Builder mergeFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite);

        Builder mergeFrom(CodedInputStream codedInputStream);

        Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite);

        Builder mergeFrom(InputStream inputStream);

        Builder mergeFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite);

        Builder mergeFrom(byte[] bArr);

        Builder mergeFrom(byte[] bArr, int i, int i2);

        Builder mergeFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite);

        Builder mergeFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite);
    }

    MessageLite getDefaultInstanceForType();

    int getSerializedSize();

    boolean isInitialized();

    Builder newBuilderForType();

    Builder toBuilder();

    byte[] toByteArray();

    ByteString toByteString();

    void writeDelimitedTo(OutputStream outputStream);

    void writeTo(CodedOutputStream codedOutputStream);

    void writeTo(OutputStream outputStream);
}
