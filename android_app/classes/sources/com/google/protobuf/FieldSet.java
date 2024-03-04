package com.google.protobuf;

import com.google.protobuf.FieldSet.FieldDescriptorLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLite;
import com.google.protobuf.WireFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class FieldSet<FieldDescriptorType extends FieldDescriptorLite<FieldDescriptorType>> {
    private static final FieldSet b = new FieldSet(true);
    private Map<FieldDescriptorType, Object> a;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface FieldDescriptorLite<T extends FieldDescriptorLite<T>> extends Comparable<T> {
        Internal.EnumLiteMap<?> getEnumType();

        WireFormat.JavaType getLiteJavaType();

        WireFormat.FieldType getLiteType();

        int getNumber();

        MessageLite.Builder internalMergeFrom(MessageLite.Builder builder, MessageLite messageLite);

        boolean isPacked();

        boolean isRepeated();
    }

    private FieldSet() {
        this.a = new TreeMap();
    }

    private FieldSet(boolean z) {
        this.a = Collections.emptyMap();
    }

    private static int a(WireFormat.FieldType fieldType, int i, Object obj) {
        int o = CodedOutputStream.o(i);
        if (fieldType == WireFormat.FieldType.GROUP) {
            o *= 2;
        }
        return o + b(fieldType, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(WireFormat.FieldType fieldType, boolean z) {
        if (z) {
            return 2;
        }
        return fieldType.getWireType();
    }

    public static <T extends FieldDescriptorLite<T>> FieldSet<T> a() {
        return new FieldSet<>();
    }

    public static Object a(CodedInputStream codedInputStream, WireFormat.FieldType fieldType) {
        switch (fieldType) {
            case DOUBLE:
                return Double.valueOf(codedInputStream.c());
            case FLOAT:
                return Float.valueOf(codedInputStream.d());
            case INT64:
                return Long.valueOf(codedInputStream.f());
            case UINT64:
                return Long.valueOf(codedInputStream.e());
            case INT32:
                return Integer.valueOf(codedInputStream.g());
            case FIXED64:
                return Long.valueOf(codedInputStream.h());
            case FIXED32:
                return Integer.valueOf(codedInputStream.i());
            case BOOL:
                return Boolean.valueOf(codedInputStream.j());
            case STRING:
                return codedInputStream.k();
            case BYTES:
                return codedInputStream.l();
            case UINT32:
                return Integer.valueOf(codedInputStream.m());
            case SFIXED32:
                return Integer.valueOf(codedInputStream.o());
            case SFIXED64:
                return Long.valueOf(codedInputStream.p());
            case SINT32:
                return Integer.valueOf(codedInputStream.q());
            case SINT64:
                return Long.valueOf(codedInputStream.r());
            case GROUP:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle nested groups.");
            case MESSAGE:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle embedded messages.");
            case ENUM:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle enums.");
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    private static void a(CodedOutputStream codedOutputStream, WireFormat.FieldType fieldType, int i, Object obj) {
        if (fieldType == WireFormat.FieldType.GROUP) {
            codedOutputStream.a(i, (MessageLite) obj);
            return;
        }
        codedOutputStream.m(i, a(fieldType, false));
        a(codedOutputStream, fieldType, obj);
    }

    private static void a(CodedOutputStream codedOutputStream, WireFormat.FieldType fieldType, Object obj) {
        switch (fieldType) {
            case DOUBLE:
                codedOutputStream.a(((Double) obj).doubleValue());
                return;
            case FLOAT:
                codedOutputStream.a(((Float) obj).floatValue());
                return;
            case INT64:
                codedOutputStream.b(((Long) obj).longValue());
                return;
            case UINT64:
                codedOutputStream.a(((Long) obj).longValue());
                return;
            case INT32:
                codedOutputStream.b(((Integer) obj).intValue());
                return;
            case FIXED64:
                codedOutputStream.c(((Long) obj).longValue());
                return;
            case FIXED32:
                codedOutputStream.c(((Integer) obj).intValue());
                return;
            case BOOL:
                codedOutputStream.a(((Boolean) obj).booleanValue());
                return;
            case STRING:
                codedOutputStream.a((String) obj);
                return;
            case BYTES:
                codedOutputStream.a((ByteString) obj);
                return;
            case UINT32:
                codedOutputStream.d(((Integer) obj).intValue());
                return;
            case SFIXED32:
                codedOutputStream.f(((Integer) obj).intValue());
                return;
            case SFIXED64:
                codedOutputStream.d(((Long) obj).longValue());
                return;
            case SINT32:
                codedOutputStream.g(((Integer) obj).intValue());
                return;
            case SINT64:
                codedOutputStream.e(((Long) obj).longValue());
                return;
            case GROUP:
                codedOutputStream.a((MessageLite) obj);
                return;
            case MESSAGE:
                codedOutputStream.c((MessageLite) obj);
                return;
            case ENUM:
                codedOutputStream.e(((Internal.EnumLite) obj).getNumber());
                return;
            default:
                return;
        }
    }

    public static void a(FieldDescriptorLite<?> fieldDescriptorLite, Object obj, CodedOutputStream codedOutputStream) {
        WireFormat.FieldType liteType = fieldDescriptorLite.getLiteType();
        int number = fieldDescriptorLite.getNumber();
        if (!fieldDescriptorLite.isRepeated()) {
            a(codedOutputStream, liteType, number, obj);
            return;
        }
        List<Object> list = (List) obj;
        if (!fieldDescriptorLite.isPacked()) {
            for (Object obj2 : list) {
                a(codedOutputStream, liteType, number, obj2);
            }
            return;
        }
        codedOutputStream.m(number, 2);
        int i = 0;
        for (Object obj3 : list) {
            i += b(liteType, obj3);
        }
        codedOutputStream.p(i);
        for (Object obj4 : list) {
            a(codedOutputStream, liteType, obj4);
        }
    }

    private static void a(WireFormat.FieldType fieldType, Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        boolean z = false;
        switch (fieldType.getJavaType()) {
            case INT:
                z = obj instanceof Integer;
                break;
            case LONG:
                z = obj instanceof Long;
                break;
            case FLOAT:
                z = obj instanceof Float;
                break;
            case DOUBLE:
                z = obj instanceof Double;
                break;
            case BOOLEAN:
                z = obj instanceof Boolean;
                break;
            case STRING:
                z = obj instanceof String;
                break;
            case BYTE_STRING:
                z = obj instanceof ByteString;
                break;
            case ENUM:
                z = obj instanceof Internal.EnumLite;
                break;
            case MESSAGE:
                z = obj instanceof MessageLite;
                break;
        }
        if (!z) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    private static int b(WireFormat.FieldType fieldType, Object obj) {
        switch (fieldType) {
            case DOUBLE:
                return CodedOutputStream.b(((Double) obj).doubleValue());
            case FLOAT:
                return CodedOutputStream.b(((Float) obj).floatValue());
            case INT64:
                return CodedOutputStream.g(((Long) obj).longValue());
            case UINT64:
                return CodedOutputStream.f(((Long) obj).longValue());
            case INT32:
                return CodedOutputStream.h(((Integer) obj).intValue());
            case FIXED64:
                return CodedOutputStream.h(((Long) obj).longValue());
            case FIXED32:
                return CodedOutputStream.i(((Integer) obj).intValue());
            case BOOL:
                return CodedOutputStream.b(((Boolean) obj).booleanValue());
            case STRING:
                return CodedOutputStream.b((String) obj);
            case BYTES:
                return CodedOutputStream.b((ByteString) obj);
            case UINT32:
                return CodedOutputStream.j(((Integer) obj).intValue());
            case SFIXED32:
                return CodedOutputStream.l(((Integer) obj).intValue());
            case SFIXED64:
                return CodedOutputStream.i(((Long) obj).longValue());
            case SINT32:
                return CodedOutputStream.m(((Integer) obj).intValue());
            case SINT64:
                return CodedOutputStream.j(((Long) obj).longValue());
            case GROUP:
                return CodedOutputStream.d((MessageLite) obj);
            case MESSAGE:
                return CodedOutputStream.f((MessageLite) obj);
            case ENUM:
                return CodedOutputStream.k(((Internal.EnumLite) obj).getNumber());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static <T extends FieldDescriptorLite<T>> FieldSet<T> b() {
        return b;
    }

    public static int c(FieldDescriptorLite<?> fieldDescriptorLite, Object obj) {
        WireFormat.FieldType liteType = fieldDescriptorLite.getLiteType();
        int number = fieldDescriptorLite.getNumber();
        if (fieldDescriptorLite.isRepeated()) {
            int i = 0;
            if (fieldDescriptorLite.isPacked()) {
                for (Object obj2 : (List) obj) {
                    i += b(liteType, obj2);
                }
                return CodedOutputStream.o(number) + i + CodedOutputStream.q(i);
            }
            for (Object obj3 : (List) obj) {
                i += a(liteType, number, obj3);
            }
            return i;
        }
        return a(liteType, number, obj);
    }

    public Object a(FieldDescriptorType fielddescriptortype, int i) {
        if (fielddescriptortype.isRepeated()) {
            Object obj = this.a.get(fielddescriptortype);
            if (obj != null) {
                return ((List) obj).get(i);
            }
            throw new IndexOutOfBoundsException();
        }
        throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
    }

    public void a(CodedOutputStream codedOutputStream) {
        for (Map.Entry<FieldDescriptorType, Object> entry : this.a.entrySet()) {
            a((FieldDescriptorLite<?>) entry.getKey(), entry.getValue(), codedOutputStream);
        }
    }

    public void a(FieldDescriptorType fielddescriptortype, int i, Object obj) {
        if (!fielddescriptortype.isRepeated()) {
            throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
        }
        Object obj2 = this.a.get(fielddescriptortype);
        if (obj2 == null) {
            throw new IndexOutOfBoundsException();
        }
        a(fielddescriptortype.getLiteType(), obj);
        ((List) obj2).set(i, obj);
    }

    public void a(FieldDescriptorType fielddescriptortype, Object obj) {
        if (!fielddescriptortype.isRepeated()) {
            a(fielddescriptortype.getLiteType(), obj);
        } else if (!(obj instanceof List)) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        } else {
            ArrayList<Object> arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            for (Object obj2 : arrayList) {
                a(fielddescriptortype.getLiteType(), obj2);
            }
            obj = arrayList;
        }
        this.a.put(fielddescriptortype, obj);
    }

    public void a(FieldSet<FieldDescriptorType> fieldSet) {
        Object obj;
        for (Map.Entry<FieldDescriptorType, Object> entry : fieldSet.a.entrySet()) {
            FieldDescriptorType key = entry.getKey();
            Object value = entry.getValue();
            if (key.isRepeated()) {
                Object obj2 = this.a.get(key);
                if (obj2 == null) {
                    this.a.put(key, new ArrayList((List) value));
                } else {
                    ((List) obj2).addAll((List) value);
                }
            } else if (key.getLiteJavaType() != WireFormat.JavaType.MESSAGE || (obj = this.a.get(key)) == null) {
                this.a.put(key, value);
            } else {
                this.a.put(key, key.internalMergeFrom(((MessageLite) obj).toBuilder(), (MessageLite) value).build());
            }
        }
    }

    public boolean a(FieldDescriptorType fielddescriptortype) {
        if (fielddescriptortype.isRepeated()) {
            throw new IllegalArgumentException("hasField() can only be called on non-repeated fields.");
        }
        return this.a.get(fielddescriptortype) != null;
    }

    public Object b(FieldDescriptorType fielddescriptortype) {
        return this.a.get(fielddescriptortype);
    }

    public void b(CodedOutputStream codedOutputStream) {
        for (Map.Entry<FieldDescriptorType, Object> entry : this.a.entrySet()) {
            FieldDescriptorType key = entry.getKey();
            if (key.getLiteJavaType() != WireFormat.JavaType.MESSAGE || key.isRepeated() || key.isPacked()) {
                a((FieldDescriptorLite<?>) key, entry.getValue(), codedOutputStream);
            } else {
                codedOutputStream.d(entry.getKey().getNumber(), (MessageLite) entry.getValue());
            }
        }
    }

    public void b(FieldDescriptorType fielddescriptortype, Object obj) {
        List list;
        if (!fielddescriptortype.isRepeated()) {
            throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
        }
        a(fielddescriptortype.getLiteType(), obj);
        Object obj2 = this.a.get(fielddescriptortype);
        if (obj2 == null) {
            list = new ArrayList();
            this.a.put(fielddescriptortype, list);
        } else {
            list = (List) obj2;
        }
        list.add(obj);
    }

    public void c() {
        for (Map.Entry<FieldDescriptorType, Object> entry : this.a.entrySet()) {
            if (entry.getKey().isRepeated()) {
                this.a.put(entry.getKey(), Collections.unmodifiableList((List) entry.getValue()));
            }
        }
        this.a = Collections.unmodifiableMap(this.a);
    }

    public void c(FieldDescriptorType fielddescriptortype) {
        this.a.remove(fielddescriptortype);
    }

    public int d(FieldDescriptorType fielddescriptortype) {
        if (fielddescriptortype.isRepeated()) {
            Object obj = this.a.get(fielddescriptortype);
            if (obj == null) {
                return 0;
            }
            return ((List) obj).size();
        }
        throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
    }

    public void d() {
        this.a.clear();
    }

    public Map<FieldDescriptorType, Object> e() {
        return Collections.unmodifiableMap(this.a);
    }

    public Iterator<Map.Entry<FieldDescriptorType, Object>> f() {
        return this.a.entrySet().iterator();
    }

    public boolean g() {
        for (Map.Entry<FieldDescriptorType, Object> entry : this.a.entrySet()) {
            FieldDescriptorType key = entry.getKey();
            if (key.getLiteJavaType() == WireFormat.JavaType.MESSAGE) {
                if (key.isRepeated()) {
                    for (MessageLite messageLite : (List) entry.getValue()) {
                        if (!messageLite.isInitialized()) {
                            return false;
                        }
                    }
                    continue;
                } else if (!((MessageLite) entry.getValue()).isInitialized()) {
                    return false;
                }
            }
        }
        return true;
    }

    public int h() {
        int i = 0;
        for (Map.Entry<FieldDescriptorType, Object> entry : this.a.entrySet()) {
            i += c(entry.getKey(), entry.getValue());
        }
        return i;
    }

    public int i() {
        int i = 0;
        for (Map.Entry<FieldDescriptorType, Object> entry : this.a.entrySet()) {
            FieldDescriptorType key = entry.getKey();
            i += (key.getLiteJavaType() != WireFormat.JavaType.MESSAGE || key.isRepeated() || key.isPacked()) ? c(key, entry.getValue()) : CodedOutputStream.h(entry.getKey().getNumber(), (MessageLite) entry.getValue());
        }
        return i;
    }
}
