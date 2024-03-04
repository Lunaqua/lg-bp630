package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class UnknownFieldSet implements MessageLite {
    private static final UnknownFieldSet defaultInstance = new UnknownFieldSet(Collections.emptyMap());
    private Map<Integer, Field> fields;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class Builder implements MessageLite.Builder {
        private Map<Integer, Field> fields;
        private Field.Builder lastField;
        private int lastFieldNumber;

        private Builder() {
        }

        static /* synthetic */ Builder access$000() {
            return create();
        }

        private static Builder create() {
            Builder builder = new Builder();
            builder.reinitialize();
            return builder;
        }

        private Field.Builder getFieldBuilder(int i) {
            Field.Builder builder = this.lastField;
            if (builder != null) {
                int i2 = this.lastFieldNumber;
                if (i == i2) {
                    return builder;
                }
                addField(i2, builder.a());
            }
            if (i == 0) {
                return null;
            }
            Field field = this.fields.get(Integer.valueOf(i));
            this.lastFieldNumber = i;
            this.lastField = Field.a();
            if (field != null) {
                this.lastField.a(field);
            }
            return this.lastField;
        }

        private void reinitialize() {
            this.fields = Collections.emptyMap();
            this.lastFieldNumber = 0;
            this.lastField = null;
        }

        public Builder addField(int i, Field field) {
            if (i != 0) {
                if (this.lastField != null && this.lastFieldNumber == i) {
                    this.lastField = null;
                    this.lastFieldNumber = 0;
                }
                if (this.fields.isEmpty()) {
                    this.fields = new TreeMap();
                }
                this.fields.put(Integer.valueOf(i), field);
                return this;
            }
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }

        public Map<Integer, Field> asMap() {
            getFieldBuilder(0);
            return Collections.unmodifiableMap(this.fields);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public UnknownFieldSet build() {
            getFieldBuilder(0);
            UnknownFieldSet defaultInstance = this.fields.isEmpty() ? UnknownFieldSet.getDefaultInstance() : new UnknownFieldSet(Collections.unmodifiableMap(this.fields));
            this.fields = null;
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public UnknownFieldSet buildPartial() {
            return build();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            reinitialize();
            return this;
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clone */
        public Builder m4clone() {
            getFieldBuilder(0);
            return UnknownFieldSet.newBuilder().mergeFrom(new UnknownFieldSet(this.fields));
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public UnknownFieldSet getDefaultInstanceForType() {
            return UnknownFieldSet.getDefaultInstance();
        }

        public boolean hasField(int i) {
            if (i != 0) {
                return i == this.lastFieldNumber || this.fields.containsKey(Integer.valueOf(i));
            }
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public boolean mergeDelimitedFrom(InputStream inputStream) {
            int read = inputStream.read();
            if (read == -1) {
                return false;
            }
            mergeFrom((InputStream) new AbstractMessageLite.Builder.LimitedInputStream(inputStream, CodedInputStream.a(read, inputStream)));
            return true;
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public boolean mergeDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return mergeDelimitedFrom(inputStream);
        }

        public Builder mergeField(int i, Field field) {
            if (i != 0) {
                if (hasField(i)) {
                    getFieldBuilder(i).a(field);
                } else {
                    addField(i, field);
                }
                return this;
            }
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }

        public boolean mergeFieldFrom(int i, CodedInputStream codedInputStream) {
            int b = WireFormat.b(i);
            int a = WireFormat.a(i);
            if (a == 0) {
                getFieldBuilder(b).a(codedInputStream.f());
                return true;
            } else if (a == 1) {
                getFieldBuilder(b).b(codedInputStream.h());
                return true;
            } else if (a == 2) {
                getFieldBuilder(b).a(codedInputStream.l());
                return true;
            } else if (a == 3) {
                Builder newBuilder = UnknownFieldSet.newBuilder();
                codedInputStream.a(b, newBuilder, ExtensionRegistry.getEmptyRegistry());
                getFieldBuilder(b).a(newBuilder.build());
                return true;
            } else if (a != 4) {
                if (a == 5) {
                    getFieldBuilder(b).a(codedInputStream.i());
                    return true;
                }
                throw InvalidProtocolBufferException.f();
            } else {
                return false;
            }
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder mergeFrom(ByteString byteString) {
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
        public Builder mergeFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return mergeFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder mergeFrom(CodedInputStream codedInputStream) {
            int a;
            do {
                a = codedInputStream.a();
                if (a == 0) {
                    break;
                }
            } while (mergeFieldFrom(a, codedInputStream));
            return this;
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return mergeFrom(codedInputStream);
        }

        public Builder mergeFrom(UnknownFieldSet unknownFieldSet) {
            if (unknownFieldSet != UnknownFieldSet.getDefaultInstance()) {
                for (Map.Entry entry : unknownFieldSet.fields.entrySet()) {
                    mergeField(((Integer) entry.getKey()).intValue(), (Field) entry.getValue());
                }
            }
            return this;
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder mergeFrom(InputStream inputStream) {
            CodedInputStream a = CodedInputStream.a(inputStream);
            mergeFrom(a);
            a.a(0);
            return this;
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder mergeFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return mergeFrom(inputStream);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder mergeFrom(byte[] bArr) {
            try {
                CodedInputStream a = CodedInputStream.a(bArr);
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
        public Builder mergeFrom(byte[] bArr, int i, int i2) {
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
        public Builder mergeFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) {
            return mergeFrom(bArr, i, i2);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder mergeFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return mergeFrom(bArr);
        }

        public Builder mergeVarintField(int i, int i2) {
            if (i != 0) {
                getFieldBuilder(i).a(i2);
                return this;
            }
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class Field {
        private static final Field a = a().a();
        private List<Long> b;
        private List<Integer> c;
        private List<Long> d;
        private List<ByteString> e;
        private List<UnknownFieldSet> f;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static final class Builder {
            private Field a;

            private Builder() {
            }

            static /* synthetic */ Builder c() {
                return d();
            }

            private static Builder d() {
                Builder builder = new Builder();
                builder.a = new Field();
                return builder;
            }

            public Builder a(int i) {
                if (this.a.c == null) {
                    this.a.c = new ArrayList();
                }
                this.a.c.add(Integer.valueOf(i));
                return this;
            }

            public Builder a(long j) {
                if (this.a.b == null) {
                    this.a.b = new ArrayList();
                }
                this.a.b.add(Long.valueOf(j));
                return this;
            }

            public Builder a(ByteString byteString) {
                if (this.a.e == null) {
                    this.a.e = new ArrayList();
                }
                this.a.e.add(byteString);
                return this;
            }

            public Builder a(Field field) {
                if (!field.b.isEmpty()) {
                    if (this.a.b == null) {
                        this.a.b = new ArrayList();
                    }
                    this.a.b.addAll(field.b);
                }
                if (!field.c.isEmpty()) {
                    if (this.a.c == null) {
                        this.a.c = new ArrayList();
                    }
                    this.a.c.addAll(field.c);
                }
                if (!field.d.isEmpty()) {
                    if (this.a.d == null) {
                        this.a.d = new ArrayList();
                    }
                    this.a.d.addAll(field.d);
                }
                if (!field.e.isEmpty()) {
                    if (this.a.e == null) {
                        this.a.e = new ArrayList();
                    }
                    this.a.e.addAll(field.e);
                }
                if (!field.f.isEmpty()) {
                    if (this.a.f == null) {
                        this.a.f = new ArrayList();
                    }
                    this.a.f.addAll(field.f);
                }
                return this;
            }

            public Builder a(UnknownFieldSet unknownFieldSet) {
                if (this.a.f == null) {
                    this.a.f = new ArrayList();
                }
                this.a.f.add(unknownFieldSet);
                return this;
            }

            public Field a() {
                Field field;
                List unmodifiableList;
                Field field2;
                List unmodifiableList2;
                Field field3;
                List unmodifiableList3;
                Field field4;
                List unmodifiableList4;
                Field field5;
                List unmodifiableList5;
                if (this.a.b == null) {
                    field = this.a;
                    unmodifiableList = Collections.emptyList();
                } else {
                    field = this.a;
                    unmodifiableList = Collections.unmodifiableList(field.b);
                }
                field.b = unmodifiableList;
                if (this.a.c == null) {
                    field2 = this.a;
                    unmodifiableList2 = Collections.emptyList();
                } else {
                    field2 = this.a;
                    unmodifiableList2 = Collections.unmodifiableList(field2.c);
                }
                field2.c = unmodifiableList2;
                if (this.a.d == null) {
                    field3 = this.a;
                    unmodifiableList3 = Collections.emptyList();
                } else {
                    field3 = this.a;
                    unmodifiableList3 = Collections.unmodifiableList(field3.d);
                }
                field3.d = unmodifiableList3;
                if (this.a.e == null) {
                    field4 = this.a;
                    unmodifiableList4 = Collections.emptyList();
                } else {
                    field4 = this.a;
                    unmodifiableList4 = Collections.unmodifiableList(field4.e);
                }
                field4.e = unmodifiableList4;
                if (this.a.f == null) {
                    field5 = this.a;
                    unmodifiableList5 = Collections.emptyList();
                } else {
                    field5 = this.a;
                    unmodifiableList5 = Collections.unmodifiableList(field5.f);
                }
                field5.f = unmodifiableList5;
                Field field6 = this.a;
                this.a = null;
                return field6;
            }

            public Builder b() {
                this.a = new Field();
                return this;
            }

            public Builder b(long j) {
                if (this.a.d == null) {
                    this.a.d = new ArrayList();
                }
                this.a.d.add(Long.valueOf(j));
                return this;
            }
        }

        private Field() {
        }

        public static Builder a() {
            return Builder.c();
        }

        public static Builder a(Field field) {
            return a().a(field);
        }

        public static Field b() {
            return a;
        }

        private Object[] h() {
            return new Object[]{this.b, this.c, this.d, this.e, this.f};
        }

        public int a(int i) {
            int i2 = 0;
            for (Long l : this.b) {
                i2 += CodedOutputStream.f(i, l.longValue());
            }
            for (Integer num : this.c) {
                i2 += CodedOutputStream.h(i, num.intValue());
            }
            for (Long l2 : this.d) {
                i2 += CodedOutputStream.h(i, l2.longValue());
            }
            for (ByteString byteString : this.e) {
                i2 += CodedOutputStream.c(i, byteString);
            }
            for (UnknownFieldSet unknownFieldSet : this.f) {
                i2 += CodedOutputStream.e(i, unknownFieldSet);
            }
            return i2;
        }

        public void a(int i, CodedOutputStream codedOutputStream) {
            for (Long l : this.b) {
                codedOutputStream.a(i, l.longValue());
            }
            for (Integer num : this.c) {
                codedOutputStream.b(i, num.intValue());
            }
            for (Long l2 : this.d) {
                codedOutputStream.c(i, l2.longValue());
            }
            for (ByteString byteString : this.e) {
                codedOutputStream.a(i, byteString);
            }
            for (UnknownFieldSet unknownFieldSet : this.f) {
                codedOutputStream.a(i, unknownFieldSet);
            }
        }

        public int b(int i) {
            int i2 = 0;
            for (ByteString byteString : this.e) {
                i2 += CodedOutputStream.d(i, byteString);
            }
            return i2;
        }

        public void b(int i, CodedOutputStream codedOutputStream) {
            for (ByteString byteString : this.e) {
                codedOutputStream.b(i, byteString);
            }
        }

        public List<Long> c() {
            return this.b;
        }

        public List<Integer> d() {
            return this.c;
        }

        public List<Long> e() {
            return this.d;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Field) {
                return Arrays.equals(h(), ((Field) obj).h());
            }
            return false;
        }

        public List<ByteString> f() {
            return this.e;
        }

        public List<UnknownFieldSet> g() {
            return this.f;
        }

        public int hashCode() {
            return Arrays.hashCode(h());
        }
    }

    private UnknownFieldSet() {
    }

    private UnknownFieldSet(Map<Integer, Field> map) {
        this.fields = map;
    }

    public static UnknownFieldSet getDefaultInstance() {
        return defaultInstance;
    }

    public static Builder newBuilder() {
        return Builder.access$000();
    }

    public static Builder newBuilder(UnknownFieldSet unknownFieldSet) {
        return newBuilder().mergeFrom(unknownFieldSet);
    }

    public static UnknownFieldSet parseFrom(ByteString byteString) {
        return newBuilder().mergeFrom(byteString).build();
    }

    public static UnknownFieldSet parseFrom(CodedInputStream codedInputStream) {
        return newBuilder().mergeFrom(codedInputStream).build();
    }

    public static UnknownFieldSet parseFrom(InputStream inputStream) {
        return newBuilder().mergeFrom(inputStream).build();
    }

    public static UnknownFieldSet parseFrom(byte[] bArr) {
        return newBuilder().mergeFrom(bArr).build();
    }

    public Map<Integer, Field> asMap() {
        return this.fields;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof UnknownFieldSet) && this.fields.equals(((UnknownFieldSet) obj).fields);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public UnknownFieldSet getDefaultInstanceForType() {
        return defaultInstance;
    }

    public Field getField(int i) {
        Field field = this.fields.get(Integer.valueOf(i));
        return field == null ? Field.b() : field;
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = 0;
        for (Map.Entry<Integer, Field> entry : this.fields.entrySet()) {
            i += entry.getValue().a(entry.getKey().intValue());
        }
        return i;
    }

    public int getSerializedSizeAsMessageSet() {
        int i = 0;
        for (Map.Entry<Integer, Field> entry : this.fields.entrySet()) {
            i += entry.getValue().b(entry.getKey().intValue());
        }
        return i;
    }

    public boolean hasField(int i) {
        return this.fields.containsKey(Integer.valueOf(i));
    }

    public int hashCode() {
        return this.fields.hashCode();
    }

    @Override // com.google.protobuf.MessageLite
    public boolean isInitialized() {
        return true;
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder toBuilder() {
        return newBuilder().mergeFrom(this);
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

    public String toString() {
        return TextFormat.a(this);
    }

    public void writeAsMessageSetTo(CodedOutputStream codedOutputStream) {
        for (Map.Entry<Integer, Field> entry : this.fields.entrySet()) {
            entry.getValue().b(entry.getKey().intValue(), codedOutputStream);
        }
    }

    @Override // com.google.protobuf.MessageLite
    public void writeDelimitedTo(OutputStream outputStream) {
        CodedOutputStream a = CodedOutputStream.a(outputStream);
        a.p(getSerializedSize());
        writeTo(a);
        a.a();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) {
        for (Map.Entry<Integer, Field> entry : this.fields.entrySet()) {
            entry.getValue().a(entry.getKey().intValue(), codedOutputStream);
        }
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(OutputStream outputStream) {
        CodedOutputStream a = CodedOutputStream.a(outputStream);
        writeTo(a);
        a.a();
    }
}
