package com.google.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class GeneratedMessage extends AbstractMessage {
    private UnknownFieldSet unknownFields = UnknownFieldSet.getDefaultInstance();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.protobuf.GeneratedMessage$1  reason: invalid class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[Descriptors.FieldDescriptor.JavaType.values().length];

        static {
            try {
                a[Descriptors.FieldDescriptor.JavaType.MESSAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[Descriptors.FieldDescriptor.JavaType.ENUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class Builder<BuilderType extends Builder> extends AbstractMessage.Builder<BuilderType> {
        private FieldAccessorTable internalGetFieldAccessorTable() {
            return internalGetResult().internalGetFieldAccessorTable();
        }

        @Override // com.google.protobuf.Message.Builder
        public BuilderType addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            internalGetFieldAccessorTable().a(fieldDescriptor).addRepeated(this, obj);
            return this;
        }

        @Override // com.google.protobuf.Message.Builder
        public BuilderType clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            internalGetFieldAccessorTable().a(fieldDescriptor).clear(this);
            return this;
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
        /* renamed from: clone */
        public BuilderType mo3clone() {
            throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
        }

        @Override // com.google.protobuf.Message.Builder
        public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
            return internalGetResult().getAllFields();
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return internalGetFieldAccessorTable().a;
        }

        @Override // com.google.protobuf.Message.Builder
        public Object getField(Descriptors.FieldDescriptor fieldDescriptor) {
            return fieldDescriptor.isRepeated() ? Collections.unmodifiableList((List) internalGetResult().getField(fieldDescriptor)) : internalGetResult().getField(fieldDescriptor);
        }

        @Override // com.google.protobuf.Message.Builder
        public Object getRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i) {
            return internalGetResult().getRepeatedField(fieldDescriptor, i);
        }

        @Override // com.google.protobuf.Message.Builder
        public int getRepeatedFieldCount(Descriptors.FieldDescriptor fieldDescriptor) {
            return internalGetResult().getRepeatedFieldCount(fieldDescriptor);
        }

        @Override // com.google.protobuf.Message.Builder
        public final UnknownFieldSet getUnknownFields() {
            return internalGetResult().unknownFields;
        }

        @Override // com.google.protobuf.Message.Builder
        public boolean hasField(Descriptors.FieldDescriptor fieldDescriptor) {
            return internalGetResult().hasField(fieldDescriptor);
        }

        protected abstract GeneratedMessage internalGetResult();

        @Override // com.google.protobuf.MessageLite.Builder
        public boolean isInitialized() {
            return internalGetResult().isInitialized();
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public final BuilderType mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            GeneratedMessage internalGetResult = internalGetResult();
            internalGetResult.unknownFields = UnknownFieldSet.newBuilder(internalGetResult.unknownFields).mergeFrom(unknownFieldSet).build();
            return this;
        }

        @Override // com.google.protobuf.Message.Builder
        public Message.Builder newBuilderForField(Descriptors.FieldDescriptor fieldDescriptor) {
            return internalGetFieldAccessorTable().a(fieldDescriptor).newBuilder();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public boolean parseUnknownField(CodedInputStream codedInputStream, UnknownFieldSet.Builder builder, ExtensionRegistryLite extensionRegistryLite, int i) {
            return builder.mergeFieldFrom(i, codedInputStream);
        }

        @Override // com.google.protobuf.Message.Builder
        public BuilderType setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            internalGetFieldAccessorTable().a(fieldDescriptor).set(this, obj);
            return this;
        }

        @Override // com.google.protobuf.Message.Builder
        public BuilderType setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            internalGetFieldAccessorTable().a(fieldDescriptor).setRepeated(this, i, obj);
            return this;
        }

        @Override // com.google.protobuf.Message.Builder
        public final BuilderType setUnknownFields(UnknownFieldSet unknownFieldSet) {
            internalGetResult().unknownFields = unknownFieldSet;
            return this;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class ExtendableBuilder<MessageType extends ExtendableMessage, BuilderType extends ExtendableBuilder> extends Builder<BuilderType> {
        public final <Type> BuilderType addExtension(GeneratedExtension<MessageType, List<Type>> generatedExtension, Type type) {
            ExtendableMessage<MessageType> internalGetResult = internalGetResult();
            internalGetResult.verifyExtensionContainingType(generatedExtension);
            ((ExtendableMessage) internalGetResult).extensions.b((FieldSet) generatedExtension.a(), generatedExtension.d(type));
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
        public BuilderType addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            if (fieldDescriptor.isExtension()) {
                ExtendableMessage<MessageType> internalGetResult = internalGetResult();
                internalGetResult.verifyContainingType(fieldDescriptor);
                ((ExtendableMessage) internalGetResult).extensions.b((FieldSet) fieldDescriptor, obj);
                return this;
            }
            return (BuilderType) super.addRepeatedField(fieldDescriptor, obj);
        }

        public final <Type> BuilderType clearExtension(GeneratedExtension<MessageType, ?> generatedExtension) {
            ExtendableMessage<MessageType> internalGetResult = internalGetResult();
            internalGetResult.verifyExtensionContainingType(generatedExtension);
            ((ExtendableMessage) internalGetResult).extensions.c(generatedExtension.a());
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
        public BuilderType clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.isExtension()) {
                ExtendableMessage<MessageType> internalGetResult = internalGetResult();
                internalGetResult.verifyContainingType(fieldDescriptor);
                ((ExtendableMessage) internalGetResult).extensions.c(fieldDescriptor);
                return this;
            }
            return (BuilderType) super.clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
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
        @Override // com.google.protobuf.GeneratedMessage.Builder
        public abstract ExtendableMessage<MessageType> internalGetResult();

        /* JADX INFO: Access modifiers changed from: protected */
        public final void mergeExtensionFields(ExtendableMessage extendableMessage) {
            ((ExtendableMessage) internalGetResult()).extensions.a(extendableMessage.extensions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage.Builder
        public boolean parseUnknownField(CodedInputStream codedInputStream, UnknownFieldSet.Builder builder, ExtensionRegistryLite extensionRegistryLite, int i) {
            internalGetResult();
            return AbstractMessage.Builder.mergeFieldFrom(codedInputStream, builder, extensionRegistryLite, this, i);
        }

        public final <Type> BuilderType setExtension(GeneratedExtension<MessageType, List<Type>> generatedExtension, int i, Type type) {
            ExtendableMessage<MessageType> internalGetResult = internalGetResult();
            internalGetResult.verifyExtensionContainingType(generatedExtension);
            ((ExtendableMessage) internalGetResult).extensions.a((FieldSet) generatedExtension.a(), i, generatedExtension.d(type));
            return this;
        }

        public final <Type> BuilderType setExtension(GeneratedExtension<MessageType, Type> generatedExtension, Type type) {
            ExtendableMessage<MessageType> internalGetResult = internalGetResult();
            internalGetResult.verifyExtensionContainingType(generatedExtension);
            ((ExtendableMessage) internalGetResult).extensions.a((FieldSet) generatedExtension.a(), generatedExtension.c(type));
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
        public BuilderType setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            if (fieldDescriptor.isExtension()) {
                ExtendableMessage<MessageType> internalGetResult = internalGetResult();
                internalGetResult.verifyContainingType(fieldDescriptor);
                ((ExtendableMessage) internalGetResult).extensions.a((FieldSet) fieldDescriptor, obj);
                return this;
            }
            return (BuilderType) super.setField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
        public BuilderType setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            if (fieldDescriptor.isExtension()) {
                ExtendableMessage<MessageType> internalGetResult = internalGetResult();
                internalGetResult.verifyContainingType(fieldDescriptor);
                ((ExtendableMessage) internalGetResult).extensions.a((FieldSet) fieldDescriptor, i, obj);
                return this;
            }
            return (BuilderType) super.setRepeatedField(fieldDescriptor, i, obj);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class ExtendableMessage<MessageType extends ExtendableMessage> extends GeneratedMessage {
        private final FieldSet<Descriptors.FieldDescriptor> extensions = FieldSet.a();

        /* JADX INFO: Access modifiers changed from: protected */
        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public class ExtensionWriter {
            private final Iterator<Map.Entry<Descriptors.FieldDescriptor, Object>> b;
            private Map.Entry<Descriptors.FieldDescriptor, Object> c;
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
                    Map.Entry<Descriptors.FieldDescriptor, Object> entry = this.c;
                    if (entry == null || entry.getKey().getNumber() >= i) {
                        return;
                    }
                    Descriptors.FieldDescriptor key = this.c.getKey();
                    if (this.d && key.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !key.isRepeated()) {
                        codedOutputStream.d(key.getNumber(), (Message) this.c.getValue());
                    } else {
                        FieldSet.a(key, this.c.getValue(), codedOutputStream);
                    }
                    this.c = this.b.hasNext() ? this.b.next() : null;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void verifyContainingType(Descriptors.FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.getContainingType() != getDescriptorForType()) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void verifyExtensionContainingType(GeneratedExtension<MessageType, ?> generatedExtension) {
            if (generatedExtension.a().getContainingType() == getDescriptorForType()) {
                return;
            }
            throw new IllegalArgumentException("Extension is for type \"" + generatedExtension.a().getContainingType().getFullName() + "\" which does not match message type \"" + getDescriptorForType().getFullName() + "\".");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public boolean extensionsAreInitialized() {
            return this.extensions.g();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public int extensionsSerializedSize() {
            return this.extensions.h();
        }

        protected int extensionsSerializedSizeAsMessageSet() {
            return this.extensions.i();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.Message
        public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
            Map allFieldsMutable = getAllFieldsMutable();
            allFieldsMutable.putAll(this.extensions.e());
            return Collections.unmodifiableMap(allFieldsMutable);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final <Type> Type getExtension(GeneratedExtension<MessageType, Type> generatedExtension) {
            verifyExtensionContainingType(generatedExtension);
            Descriptors.FieldDescriptor a = generatedExtension.a();
            Object b = this.extensions.b((FieldSet<Descriptors.FieldDescriptor>) a);
            return b == null ? a.isRepeated() ? (Type) Collections.emptyList() : a.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE ? (Type) generatedExtension.b() : (Type) generatedExtension.a(a.getDefaultValue()) : (Type) generatedExtension.a(b);
        }

        public final <Type> Type getExtension(GeneratedExtension<MessageType, List<Type>> generatedExtension, int i) {
            verifyExtensionContainingType(generatedExtension);
            return (Type) generatedExtension.b(this.extensions.a((FieldSet<Descriptors.FieldDescriptor>) generatedExtension.a(), i));
        }

        public final <Type> int getExtensionCount(GeneratedExtension<MessageType, List<Type>> generatedExtension) {
            verifyExtensionContainingType(generatedExtension);
            return this.extensions.d(generatedExtension.a());
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.Message
        public Object getField(Descriptors.FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.isExtension()) {
                verifyContainingType(fieldDescriptor);
                Object b = this.extensions.b((FieldSet<Descriptors.FieldDescriptor>) fieldDescriptor);
                return b == null ? fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE ? DynamicMessage.getDefaultInstance(fieldDescriptor.getMessageType()) : fieldDescriptor.getDefaultValue() : b;
            }
            return super.getField(fieldDescriptor);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.Message
        public Object getRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i) {
            if (fieldDescriptor.isExtension()) {
                verifyContainingType(fieldDescriptor);
                return this.extensions.a((FieldSet<Descriptors.FieldDescriptor>) fieldDescriptor, i);
            }
            return super.getRepeatedField(fieldDescriptor, i);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.Message
        public int getRepeatedFieldCount(Descriptors.FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.isExtension()) {
                verifyContainingType(fieldDescriptor);
                return this.extensions.d(fieldDescriptor);
            }
            return super.getRepeatedFieldCount(fieldDescriptor);
        }

        public final boolean hasExtension(GeneratedExtension<MessageType, ?> generatedExtension) {
            verifyExtensionContainingType(generatedExtension);
            return this.extensions.a((FieldSet<Descriptors.FieldDescriptor>) generatedExtension.a());
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.Message
        public boolean hasField(Descriptors.FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.isExtension()) {
                verifyContainingType(fieldDescriptor);
                return this.extensions.a((FieldSet<Descriptors.FieldDescriptor>) fieldDescriptor);
            }
            return super.hasField(fieldDescriptor);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public boolean isInitialized() {
            return super.isInitialized() && extensionsAreInitialized();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public ExtendableMessage<MessageType>.ExtensionWriter newExtensionWriter() {
            return new ExtensionWriter(this, false, null);
        }

        protected ExtendableMessage<MessageType>.ExtensionWriter newMessageSetExtensionWriter() {
            return new ExtensionWriter(this, true, null);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class FieldAccessorTable {
        private final Descriptors.Descriptor a;
        private final FieldAccessor[] b;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public interface FieldAccessor {
            void addRepeated(Builder builder, Object obj);

            void clear(Builder builder);

            Object get(GeneratedMessage generatedMessage);

            Object getRepeated(GeneratedMessage generatedMessage, int i);

            int getRepeatedCount(GeneratedMessage generatedMessage);

            boolean has(GeneratedMessage generatedMessage);

            Message.Builder newBuilder();

            void set(Builder builder, Object obj);

            void setRepeated(Builder builder, int i, Object obj);
        }

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        private static final class RepeatedEnumFieldAccessor extends RepeatedFieldAccessor {
            private final Method getValueDescriptorMethod;
            private final Method valueOfMethod;

            RepeatedEnumFieldAccessor(Descriptors.FieldDescriptor fieldDescriptor, String str, Class<? extends GeneratedMessage> cls, Class<? extends Builder> cls2) {
                super(fieldDescriptor, str, cls, cls2);
                this.valueOfMethod = GeneratedMessage.getMethodOrDie(this.type, "valueOf", Descriptors.EnumValueDescriptor.class);
                this.getValueDescriptorMethod = GeneratedMessage.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor, com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void addRepeated(Builder builder, Object obj) {
                super.addRepeated(builder, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, obj));
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor, com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object get(GeneratedMessage generatedMessage) {
                ArrayList arrayList = new ArrayList();
                for (Object obj : (List) super.get(generatedMessage)) {
                    arrayList.add(GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, obj, new Object[0]));
                }
                return Collections.unmodifiableList(arrayList);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor, com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object getRepeated(GeneratedMessage generatedMessage, int i) {
                return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(generatedMessage, i), new Object[0]);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor, com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void setRepeated(Builder builder, int i, Object obj) {
                super.setRepeated(builder, i, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, obj));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static class RepeatedFieldAccessor implements FieldAccessor {
            protected final Method addRepeatedMethod;
            protected final Method clearMethod;
            protected final Method getCountMethod;
            protected final Method getMethod;
            protected final Method getRepeatedMethod;
            protected final Method setRepeatedMethod;
            protected final Class type;

            RepeatedFieldAccessor(Descriptors.FieldDescriptor fieldDescriptor, String str, Class<? extends GeneratedMessage> cls, Class<? extends Builder> cls2) {
                this.getMethod = GeneratedMessage.getMethodOrDie(cls, "get" + str + "List", new Class[0]);
                StringBuilder sb = new StringBuilder();
                sb.append("get");
                sb.append(str);
                this.getRepeatedMethod = GeneratedMessage.getMethodOrDie(cls, sb.toString(), Integer.TYPE);
                this.type = this.getRepeatedMethod.getReturnType();
                this.setRepeatedMethod = GeneratedMessage.getMethodOrDie(cls2, "set" + str, Integer.TYPE, this.type);
                this.addRepeatedMethod = GeneratedMessage.getMethodOrDie(cls2, "add" + str, this.type);
                this.getCountMethod = GeneratedMessage.getMethodOrDie(cls, "get" + str + "Count", new Class[0]);
                StringBuilder sb2 = new StringBuilder();
                sb2.append("clear");
                sb2.append(str);
                this.clearMethod = GeneratedMessage.getMethodOrDie(cls2, sb2.toString(), new Class[0]);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void addRepeated(Builder builder, Object obj) {
                GeneratedMessage.invokeOrDie(this.addRepeatedMethod, builder, obj);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void clear(Builder builder) {
                GeneratedMessage.invokeOrDie(this.clearMethod, builder, new Object[0]);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object get(GeneratedMessage generatedMessage) {
                return GeneratedMessage.invokeOrDie(this.getMethod, generatedMessage, new Object[0]);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object getRepeated(GeneratedMessage generatedMessage, int i) {
                return GeneratedMessage.invokeOrDie(this.getRepeatedMethod, generatedMessage, Integer.valueOf(i));
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public int getRepeatedCount(GeneratedMessage generatedMessage) {
                return ((Integer) GeneratedMessage.invokeOrDie(this.getCountMethod, generatedMessage, new Object[0])).intValue();
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public boolean has(GeneratedMessage generatedMessage) {
                throw new UnsupportedOperationException("hasField() called on a singular field.");
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Message.Builder newBuilder() {
                throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void set(Builder builder, Object obj) {
                clear(builder);
                for (Object obj2 : (List) obj) {
                    addRepeated(builder, obj2);
                }
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void setRepeated(Builder builder, int i, Object obj) {
                GeneratedMessage.invokeOrDie(this.setRepeatedMethod, builder, Integer.valueOf(i), obj);
            }
        }

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        private static final class RepeatedMessageFieldAccessor extends RepeatedFieldAccessor {
            private final Method newBuilderMethod;

            RepeatedMessageFieldAccessor(Descriptors.FieldDescriptor fieldDescriptor, String str, Class<? extends GeneratedMessage> cls, Class<? extends Builder> cls2) {
                super(fieldDescriptor, str, cls, cls2);
                this.newBuilderMethod = GeneratedMessage.getMethodOrDie(this.type, "newBuilder", new Class[0]);
            }

            private Object coerceType(Object obj) {
                return this.type.isInstance(obj) ? obj : ((Message.Builder) GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message) obj).build();
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor, com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void addRepeated(Builder builder, Object obj) {
                super.addRepeated(builder, coerceType(obj));
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor, com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Message.Builder newBuilder() {
                return (Message.Builder) GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor, com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void setRepeated(Builder builder, int i, Object obj) {
                super.setRepeated(builder, i, coerceType(obj));
            }
        }

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        private static final class SingularEnumFieldAccessor extends SingularFieldAccessor {
            private Method getValueDescriptorMethod;
            private Method valueOfMethod;

            SingularEnumFieldAccessor(Descriptors.FieldDescriptor fieldDescriptor, String str, Class<? extends GeneratedMessage> cls, Class<? extends Builder> cls2) {
                super(fieldDescriptor, str, cls, cls2);
                this.valueOfMethod = GeneratedMessage.getMethodOrDie(this.type, "valueOf", Descriptors.EnumValueDescriptor.class);
                this.getValueDescriptorMethod = GeneratedMessage.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.SingularFieldAccessor, com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object get(GeneratedMessage generatedMessage) {
                return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.get(generatedMessage), new Object[0]);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.SingularFieldAccessor, com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void set(Builder builder, Object obj) {
                super.set(builder, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, obj));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static class SingularFieldAccessor implements FieldAccessor {
            protected final Method clearMethod;
            protected final Method getMethod;
            protected final Method hasMethod;
            protected final Method setMethod;
            protected final Class type;

            SingularFieldAccessor(Descriptors.FieldDescriptor fieldDescriptor, String str, Class<? extends GeneratedMessage> cls, Class<? extends Builder> cls2) {
                this.getMethod = GeneratedMessage.getMethodOrDie(cls, "get" + str, new Class[0]);
                this.type = this.getMethod.getReturnType();
                this.setMethod = GeneratedMessage.getMethodOrDie(cls2, "set" + str, this.type);
                this.hasMethod = GeneratedMessage.getMethodOrDie(cls, "has" + str, new Class[0]);
                this.clearMethod = GeneratedMessage.getMethodOrDie(cls2, "clear" + str, new Class[0]);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void addRepeated(Builder builder, Object obj) {
                throw new UnsupportedOperationException("addRepeatedField() called on a singular field.");
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void clear(Builder builder) {
                GeneratedMessage.invokeOrDie(this.clearMethod, builder, new Object[0]);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object get(GeneratedMessage generatedMessage) {
                return GeneratedMessage.invokeOrDie(this.getMethod, generatedMessage, new Object[0]);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object getRepeated(GeneratedMessage generatedMessage, int i) {
                throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public int getRepeatedCount(GeneratedMessage generatedMessage) {
                throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public boolean has(GeneratedMessage generatedMessage) {
                return ((Boolean) GeneratedMessage.invokeOrDie(this.hasMethod, generatedMessage, new Object[0])).booleanValue();
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Message.Builder newBuilder() {
                throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void set(Builder builder, Object obj) {
                GeneratedMessage.invokeOrDie(this.setMethod, builder, obj);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void setRepeated(Builder builder, int i, Object obj) {
                throw new UnsupportedOperationException("setRepeatedField() called on a singular field.");
            }
        }

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        private static final class SingularMessageFieldAccessor extends SingularFieldAccessor {
            private final Method newBuilderMethod;

            SingularMessageFieldAccessor(Descriptors.FieldDescriptor fieldDescriptor, String str, Class<? extends GeneratedMessage> cls, Class<? extends Builder> cls2) {
                super(fieldDescriptor, str, cls, cls2);
                this.newBuilderMethod = GeneratedMessage.getMethodOrDie(this.type, "newBuilder", new Class[0]);
            }

            private Object coerceType(Object obj) {
                return this.type.isInstance(obj) ? obj : ((Message.Builder) GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message) obj).build();
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.SingularFieldAccessor, com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Message.Builder newBuilder() {
                return (Message.Builder) GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.SingularFieldAccessor, com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void set(Builder builder, Object obj) {
                super.set(builder, coerceType(obj));
            }
        }

        public FieldAccessorTable(Descriptors.Descriptor descriptor, String[] strArr, Class<? extends GeneratedMessage> cls, Class<? extends Builder> cls2) {
            this.a = descriptor;
            this.b = new FieldAccessor[descriptor.getFields().size()];
            for (int i = 0; i < this.b.length; i++) {
                Descriptors.FieldDescriptor fieldDescriptor = descriptor.getFields().get(i);
                if (fieldDescriptor.isRepeated()) {
                    if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                        this.b[i] = new RepeatedMessageFieldAccessor(fieldDescriptor, strArr[i], cls, cls2);
                    } else if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
                        this.b[i] = new RepeatedEnumFieldAccessor(fieldDescriptor, strArr[i], cls, cls2);
                    } else {
                        this.b[i] = new RepeatedFieldAccessor(fieldDescriptor, strArr[i], cls, cls2);
                    }
                } else if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    this.b[i] = new SingularMessageFieldAccessor(fieldDescriptor, strArr[i], cls, cls2);
                } else if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
                    this.b[i] = new SingularEnumFieldAccessor(fieldDescriptor, strArr[i], cls, cls2);
                } else {
                    this.b[i] = new SingularFieldAccessor(fieldDescriptor, strArr[i], cls, cls2);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public FieldAccessor a(Descriptors.FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.getContainingType() == this.a) {
                if (fieldDescriptor.isExtension()) {
                    throw new IllegalArgumentException("This type does not have extensions.");
                }
                return this.b[fieldDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("FieldDescriptor does not match message type.");
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class GeneratedExtension<ContainingType extends Message, Type> {
        private Descriptors.FieldDescriptor a;
        private Class b;
        private Method c;
        private Method d;
        private Message e;

        private GeneratedExtension() {
        }

        /* synthetic */ GeneratedExtension(AnonymousClass1 anonymousClass1) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Object a(Object obj) {
            if (this.a.isRepeated()) {
                if (this.a.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE || this.a.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
                    ArrayList arrayList = new ArrayList();
                    for (Object obj2 : (List) obj) {
                        arrayList.add(b(obj2));
                    }
                    return arrayList;
                }
                return obj;
            }
            return b(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Object b(Object obj) {
            int i = AnonymousClass1.a[this.a.getJavaType().ordinal()];
            return i != 1 ? i != 2 ? obj : GeneratedMessage.invokeOrDie(this.c, null, (Descriptors.EnumValueDescriptor) obj) : this.b.isInstance(obj) ? obj : this.e.newBuilderForType().mergeFrom((Message) obj).build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Object c(Object obj) {
            if (this.a.isRepeated()) {
                if (this.a.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
                    ArrayList arrayList = new ArrayList();
                    for (Object obj2 : (List) obj) {
                        arrayList.add(d(obj2));
                    }
                    return arrayList;
                }
                return obj;
            }
            return d(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Object d(Object obj) {
            return AnonymousClass1.a[this.a.getJavaType().ordinal()] != 2 ? obj : GeneratedMessage.invokeOrDie(this.d, obj, new Object[0]);
        }

        public Descriptors.FieldDescriptor a() {
            return this.a;
        }

        public void a(Descriptors.FieldDescriptor fieldDescriptor, Class cls) {
            if (this.a != null) {
                throw new IllegalStateException("Already initialized.");
            }
            if (!fieldDescriptor.isExtension()) {
                throw new IllegalArgumentException("GeneratedExtension given a regular (non-extension) field.");
            }
            this.a = fieldDescriptor;
            this.b = cls;
            int i = AnonymousClass1.a[fieldDescriptor.getJavaType().ordinal()];
            if (i != 1) {
                if (i != 2) {
                    this.c = null;
                    this.d = null;
                } else {
                    this.c = GeneratedMessage.getMethodOrDie(cls, "valueOf", Descriptors.EnumValueDescriptor.class);
                    this.d = GeneratedMessage.getMethodOrDie(cls, "getValueDescriptor", new Class[0]);
                }
                this.e = null;
                return;
            }
            this.c = null;
            this.d = null;
            this.e = (Message) GeneratedMessage.invokeOrDie(GeneratedMessage.getMethodOrDie(cls, "getDefaultInstance", new Class[0]), null, new Object[0]);
            if (this.e != null) {
                return;
            }
            throw new IllegalStateException(cls.getName() + ".getDefaultInstance() returned null.");
        }

        public Message b() {
            return this.e;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.lang.Object] */
    public Map<Descriptors.FieldDescriptor, Object> getAllFieldsMutable() {
        ?? r3;
        TreeMap treeMap = new TreeMap();
        for (Descriptors.FieldDescriptor fieldDescriptor : internalGetFieldAccessorTable().a.getFields()) {
            if (fieldDescriptor.isRepeated()) {
                r3 = (List) getField(fieldDescriptor);
                if (!r3.isEmpty()) {
                    treeMap.put(fieldDescriptor, r3);
                }
            } else if (hasField(fieldDescriptor)) {
                r3 = getField(fieldDescriptor);
                treeMap.put(fieldDescriptor, r3);
            }
        }
        return treeMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Method getMethodOrDie(Class cls, String str, Class... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Generated message class \"" + cls.getName() + "\" missing method \"" + str + "\".", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object invokeOrDie(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    public static <ContainingType extends Message, Type> GeneratedExtension<ContainingType, Type> newGeneratedExtension() {
        return new GeneratedExtension<>(null);
    }

    @Override // com.google.protobuf.Message
    public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
        return Collections.unmodifiableMap(getAllFieldsMutable());
    }

    @Override // com.google.protobuf.Message
    public Descriptors.Descriptor getDescriptorForType() {
        return internalGetFieldAccessorTable().a;
    }

    @Override // com.google.protobuf.Message
    public Object getField(Descriptors.FieldDescriptor fieldDescriptor) {
        return internalGetFieldAccessorTable().a(fieldDescriptor).get(this);
    }

    @Override // com.google.protobuf.Message
    public Object getRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i) {
        return internalGetFieldAccessorTable().a(fieldDescriptor).getRepeated(this, i);
    }

    @Override // com.google.protobuf.Message
    public int getRepeatedFieldCount(Descriptors.FieldDescriptor fieldDescriptor) {
        return internalGetFieldAccessorTable().a(fieldDescriptor).getRepeatedCount(this);
    }

    @Override // com.google.protobuf.Message
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    @Override // com.google.protobuf.Message
    public boolean hasField(Descriptors.FieldDescriptor fieldDescriptor) {
        return internalGetFieldAccessorTable().a(fieldDescriptor).has(this);
    }

    protected abstract FieldAccessorTable internalGetFieldAccessorTable();

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public boolean isInitialized() {
        for (Descriptors.FieldDescriptor fieldDescriptor : getDescriptorForType().getFields()) {
            if (fieldDescriptor.isRequired() && !hasField(fieldDescriptor)) {
                return false;
            }
            if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                if (fieldDescriptor.isRepeated()) {
                    for (Message message : (List) getField(fieldDescriptor)) {
                        if (!message.isInitialized()) {
                            return false;
                        }
                    }
                    continue;
                } else if (hasField(fieldDescriptor) && !((Message) getField(fieldDescriptor)).isInitialized()) {
                    return false;
                }
            }
        }
        return true;
    }
}
