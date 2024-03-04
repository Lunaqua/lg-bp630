package com.google.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class AbstractMessageLite implements MessageLite {

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class Builder<BuilderType extends Builder> implements MessageLite.Builder {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static final class LimitedInputStream extends FilterInputStream {
            private int a;

            /* JADX INFO: Access modifiers changed from: package-private */
            public LimitedInputStream(InputStream inputStream, int i) {
                super(inputStream);
                this.a = i;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int available() {
                return Math.min(super.available(), this.a);
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read() {
                if (this.a <= 0) {
                    return -1;
                }
                int read = super.read();
                if (read >= 0) {
                    this.a--;
                }
                return read;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read(byte[] bArr, int i, int i2) {
                int i3 = this.a;
                if (i3 <= 0) {
                    return -1;
                }
                int read = super.read(bArr, i, Math.min(i2, i3));
                if (read >= 0) {
                    this.a -= read;
                }
                return read;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public long skip(long j) {
                long skip = super.skip(Math.min(j, this.a));
                if (skip >= 0) {
                    this.a = (int) (this.a - skip);
                }
                return skip;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public static <T> void addAll(Iterable<T> iterable, Collection<? super T> collection) {
            for (T t : iterable) {
                if (t == null) {
                    throw new NullPointerException();
                }
            }
            if (iterable instanceof Collection) {
                collection.addAll((Collection) iterable);
                return;
            }
            for (T t2 : iterable) {
                collection.add(t2);
            }
        }

        protected static UninitializedMessageException newUninitializedMessageException(MessageLite messageLite) {
            return new UninitializedMessageException(messageLite);
        }

        @Override // 
        /* renamed from: clone */
        public abstract BuilderType mo3clone();

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public boolean mergeDelimitedFrom(InputStream inputStream) {
            return mergeDelimitedFrom(inputStream, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public boolean mergeDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            int read = inputStream.read();
            if (read == -1) {
                return false;
            }
            mergeFrom((InputStream) new LimitedInputStream(inputStream, CodedInputStream.a(read, inputStream)), extensionRegistryLite);
            return true;
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(ByteString byteString) {
            try {
                CodedInputStream g = byteString.g();
                mergeFrom(g);
                g.a(0);
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", e2);
            }
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            try {
                CodedInputStream g = byteString.g();
                mergeFrom(g, extensionRegistryLite);
                g.a(0);
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", e2);
            }
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(CodedInputStream codedInputStream) {
            return mergeFrom(codedInputStream, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public abstract BuilderType mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite);

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(InputStream inputStream) {
            CodedInputStream a = CodedInputStream.a(inputStream);
            mergeFrom(a);
            a.a(0);
            return this;
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            CodedInputStream a = CodedInputStream.a(inputStream);
            mergeFrom(a, extensionRegistryLite);
            a.a(0);
            return this;
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(byte[] bArr) {
            return mergeFrom(bArr, 0, bArr.length);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(byte[] bArr, int i, int i2) {
            try {
                CodedInputStream a = CodedInputStream.a(bArr, i, i2);
                mergeFrom(a);
                a.a(0);
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e2);
            }
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) {
            try {
                CodedInputStream a = CodedInputStream.a(bArr, i, i2);
                mergeFrom(a, extensionRegistryLite);
                a.a(0);
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e2);
            }
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return mergeFrom(bArr, 0, bArr.length, extensionRegistryLite);
        }
    }

    @Override // com.google.protobuf.MessageLite
    public byte[] toByteArray() {
        try {
            byte[] bArr = new byte[getSerializedSize()];
            CodedOutputStream a = CodedOutputStream.a(bArr);
            writeTo(a);
            a.c();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    @Override // com.google.protobuf.MessageLite
    public ByteString toByteString() {
        try {
            ByteString.CodedBuilder c = ByteString.c(getSerializedSize());
            writeTo(c.b());
            return c.a();
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a ByteString threw an IOException (should never happen).", e);
        }
    }

    @Override // com.google.protobuf.MessageLite
    public void writeDelimitedTo(OutputStream outputStream) {
        int serializedSize = getSerializedSize();
        CodedOutputStream a = CodedOutputStream.a(outputStream, CodedOutputStream.a(CodedOutputStream.q(serializedSize) + serializedSize));
        a.p(serializedSize);
        writeTo(a);
        a.a();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(OutputStream outputStream) {
        CodedOutputStream a = CodedOutputStream.a(outputStream, CodedOutputStream.a(getSerializedSize()));
        writeTo(a);
        a.a();
    }
}
