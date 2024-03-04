package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLite;
import com.google.protobuf.UnknownFieldSet;
import com.lge.media.launcher.a;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class DescriptorProtos {
    private static Descriptors.Descriptor A;
    private static GeneratedMessage.FieldAccessorTable B;
    private static Descriptors.Descriptor C;
    private static GeneratedMessage.FieldAccessorTable D;
    private static Descriptors.Descriptor E;
    private static GeneratedMessage.FieldAccessorTable F;
    private static Descriptors.Descriptor G;
    private static GeneratedMessage.FieldAccessorTable H;
    private static Descriptors.Descriptor I;
    private static GeneratedMessage.FieldAccessorTable J;
    private static Descriptors.FileDescriptor K;
    private static Descriptors.Descriptor a;
    private static GeneratedMessage.FieldAccessorTable b;
    private static Descriptors.Descriptor c;
    private static GeneratedMessage.FieldAccessorTable d;
    private static Descriptors.Descriptor e;
    private static GeneratedMessage.FieldAccessorTable f;
    private static Descriptors.Descriptor g;
    private static GeneratedMessage.FieldAccessorTable h;
    private static Descriptors.Descriptor i;
    private static GeneratedMessage.FieldAccessorTable j;
    private static Descriptors.Descriptor k;
    private static GeneratedMessage.FieldAccessorTable l;
    private static Descriptors.Descriptor m;
    private static GeneratedMessage.FieldAccessorTable n;
    private static Descriptors.Descriptor o;
    private static GeneratedMessage.FieldAccessorTable p;
    private static Descriptors.Descriptor q;
    private static GeneratedMessage.FieldAccessorTable r;
    private static Descriptors.Descriptor s;
    private static GeneratedMessage.FieldAccessorTable t;
    private static Descriptors.Descriptor u;
    private static GeneratedMessage.FieldAccessorTable v;
    private static Descriptors.Descriptor w;
    private static GeneratedMessage.FieldAccessorTable x;
    private static Descriptors.Descriptor y;
    private static GeneratedMessage.FieldAccessorTable z;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class DescriptorProto extends GeneratedMessage {
        public static final int ENUM_TYPE_FIELD_NUMBER = 4;
        public static final int EXTENSION_FIELD_NUMBER = 6;
        public static final int EXTENSION_RANGE_FIELD_NUMBER = 5;
        public static final int FIELD_FIELD_NUMBER = 2;
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int NESTED_TYPE_FIELD_NUMBER = 3;
        public static final int OPTIONS_FIELD_NUMBER = 7;
        private static final DescriptorProto defaultInstance = new DescriptorProto(true);
        private List<EnumDescriptorProto> enumType_;
        private List<ExtensionRange> extensionRange_;
        private List<FieldDescriptorProto> extension_;
        private List<FieldDescriptorProto> field_;
        private boolean hasName;
        private boolean hasOptions;
        private int memoizedSerializedSize;
        private String name_;
        private List<DescriptorProto> nestedType_;
        private MessageOptions options_;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static final class Builder extends GeneratedMessage.Builder<Builder> {
            private DescriptorProto result;

            private Builder() {
            }

            static /* synthetic */ Builder access$3400() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public DescriptorProto buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static Builder create() {
                Builder builder = new Builder();
                builder.result = new DescriptorProto();
                return builder;
            }

            public Builder addAllEnumType(Iterable<? extends EnumDescriptorProto> iterable) {
                if (this.result.enumType_.isEmpty()) {
                    this.result.enumType_ = new ArrayList();
                }
                GeneratedMessage.Builder.addAll(iterable, this.result.enumType_);
                return this;
            }

            public Builder addAllExtension(Iterable<? extends FieldDescriptorProto> iterable) {
                if (this.result.extension_.isEmpty()) {
                    this.result.extension_ = new ArrayList();
                }
                GeneratedMessage.Builder.addAll(iterable, this.result.extension_);
                return this;
            }

            public Builder addAllExtensionRange(Iterable<? extends ExtensionRange> iterable) {
                if (this.result.extensionRange_.isEmpty()) {
                    this.result.extensionRange_ = new ArrayList();
                }
                GeneratedMessage.Builder.addAll(iterable, this.result.extensionRange_);
                return this;
            }

            public Builder addAllField(Iterable<? extends FieldDescriptorProto> iterable) {
                if (this.result.field_.isEmpty()) {
                    this.result.field_ = new ArrayList();
                }
                GeneratedMessage.Builder.addAll(iterable, this.result.field_);
                return this;
            }

            public Builder addAllNestedType(Iterable<? extends DescriptorProto> iterable) {
                if (this.result.nestedType_.isEmpty()) {
                    this.result.nestedType_ = new ArrayList();
                }
                GeneratedMessage.Builder.addAll(iterable, this.result.nestedType_);
                return this;
            }

            public Builder addEnumType(EnumDescriptorProto.Builder builder) {
                if (this.result.enumType_.isEmpty()) {
                    this.result.enumType_ = new ArrayList();
                }
                this.result.enumType_.add(builder.build());
                return this;
            }

            public Builder addEnumType(EnumDescriptorProto enumDescriptorProto) {
                if (enumDescriptorProto != null) {
                    if (this.result.enumType_.isEmpty()) {
                        this.result.enumType_ = new ArrayList();
                    }
                    this.result.enumType_.add(enumDescriptorProto);
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder addExtension(FieldDescriptorProto.Builder builder) {
                if (this.result.extension_.isEmpty()) {
                    this.result.extension_ = new ArrayList();
                }
                this.result.extension_.add(builder.build());
                return this;
            }

            public Builder addExtension(FieldDescriptorProto fieldDescriptorProto) {
                if (fieldDescriptorProto != null) {
                    if (this.result.extension_.isEmpty()) {
                        this.result.extension_ = new ArrayList();
                    }
                    this.result.extension_.add(fieldDescriptorProto);
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder addExtensionRange(ExtensionRange.Builder builder) {
                if (this.result.extensionRange_.isEmpty()) {
                    this.result.extensionRange_ = new ArrayList();
                }
                this.result.extensionRange_.add(builder.build());
                return this;
            }

            public Builder addExtensionRange(ExtensionRange extensionRange) {
                if (extensionRange != null) {
                    if (this.result.extensionRange_.isEmpty()) {
                        this.result.extensionRange_ = new ArrayList();
                    }
                    this.result.extensionRange_.add(extensionRange);
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder addField(FieldDescriptorProto.Builder builder) {
                if (this.result.field_.isEmpty()) {
                    this.result.field_ = new ArrayList();
                }
                this.result.field_.add(builder.build());
                return this;
            }

            public Builder addField(FieldDescriptorProto fieldDescriptorProto) {
                if (fieldDescriptorProto != null) {
                    if (this.result.field_.isEmpty()) {
                        this.result.field_ = new ArrayList();
                    }
                    this.result.field_.add(fieldDescriptorProto);
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder addNestedType(Builder builder) {
                if (this.result.nestedType_.isEmpty()) {
                    this.result.nestedType_ = new ArrayList();
                }
                this.result.nestedType_.add(builder.build());
                return this;
            }

            public Builder addNestedType(DescriptorProto descriptorProto) {
                if (descriptorProto != null) {
                    if (this.result.nestedType_.isEmpty()) {
                        this.result.nestedType_ = new ArrayList();
                    }
                    this.result.nestedType_.add(descriptorProto);
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public DescriptorProto build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public DescriptorProto buildPartial() {
                DescriptorProto descriptorProto = this.result;
                if (descriptorProto != null) {
                    if (descriptorProto.field_ != Collections.EMPTY_LIST) {
                        DescriptorProto descriptorProto2 = this.result;
                        descriptorProto2.field_ = Collections.unmodifiableList(descriptorProto2.field_);
                    }
                    if (this.result.extension_ != Collections.EMPTY_LIST) {
                        DescriptorProto descriptorProto3 = this.result;
                        descriptorProto3.extension_ = Collections.unmodifiableList(descriptorProto3.extension_);
                    }
                    if (this.result.nestedType_ != Collections.EMPTY_LIST) {
                        DescriptorProto descriptorProto4 = this.result;
                        descriptorProto4.nestedType_ = Collections.unmodifiableList(descriptorProto4.nestedType_);
                    }
                    if (this.result.enumType_ != Collections.EMPTY_LIST) {
                        DescriptorProto descriptorProto5 = this.result;
                        descriptorProto5.enumType_ = Collections.unmodifiableList(descriptorProto5.enumType_);
                    }
                    if (this.result.extensionRange_ != Collections.EMPTY_LIST) {
                        DescriptorProto descriptorProto6 = this.result;
                        descriptorProto6.extensionRange_ = Collections.unmodifiableList(descriptorProto6.extensionRange_);
                    }
                    DescriptorProto descriptorProto7 = this.result;
                    this.result = null;
                    return descriptorProto7;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                if (this.result != null) {
                    this.result = new DescriptorProto();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public Builder clearEnumType() {
                this.result.enumType_ = Collections.emptyList();
                return this;
            }

            public Builder clearExtension() {
                this.result.extension_ = Collections.emptyList();
                return this;
            }

            public Builder clearExtensionRange() {
                this.result.extensionRange_ = Collections.emptyList();
                return this;
            }

            public Builder clearField() {
                this.result.field_ = Collections.emptyList();
                return this;
            }

            public Builder clearName() {
                this.result.hasName = false;
                this.result.name_ = DescriptorProto.getDefaultInstance().getName();
                return this;
            }

            public Builder clearNestedType() {
                this.result.nestedType_ = Collections.emptyList();
                return this;
            }

            public Builder clearOptions() {
                this.result.hasOptions = false;
                this.result.options_ = MessageOptions.getDefaultInstance();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3clone() {
                return create().mergeFrom(this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public DescriptorProto getDefaultInstanceForType() {
                return DescriptorProto.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return DescriptorProto.getDescriptor();
            }

            public EnumDescriptorProto getEnumType(int i) {
                return this.result.getEnumType(i);
            }

            public int getEnumTypeCount() {
                return this.result.getEnumTypeCount();
            }

            public List<EnumDescriptorProto> getEnumTypeList() {
                return Collections.unmodifiableList(this.result.enumType_);
            }

            public FieldDescriptorProto getExtension(int i) {
                return this.result.getExtension(i);
            }

            public int getExtensionCount() {
                return this.result.getExtensionCount();
            }

            public List<FieldDescriptorProto> getExtensionList() {
                return Collections.unmodifiableList(this.result.extension_);
            }

            public ExtensionRange getExtensionRange(int i) {
                return this.result.getExtensionRange(i);
            }

            public int getExtensionRangeCount() {
                return this.result.getExtensionRangeCount();
            }

            public List<ExtensionRange> getExtensionRangeList() {
                return Collections.unmodifiableList(this.result.extensionRange_);
            }

            public FieldDescriptorProto getField(int i) {
                return this.result.getField(i);
            }

            public int getFieldCount() {
                return this.result.getFieldCount();
            }

            public List<FieldDescriptorProto> getFieldList() {
                return Collections.unmodifiableList(this.result.field_);
            }

            public String getName() {
                return this.result.getName();
            }

            public DescriptorProto getNestedType(int i) {
                return this.result.getNestedType(i);
            }

            public int getNestedTypeCount() {
                return this.result.getNestedTypeCount();
            }

            public List<DescriptorProto> getNestedTypeList() {
                return Collections.unmodifiableList(this.result.nestedType_);
            }

            public MessageOptions getOptions() {
                return this.result.getOptions();
            }

            public boolean hasName() {
                return this.result.hasName();
            }

            public boolean hasOptions() {
                return this.result.hasOptions();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.Builder
            public DescriptorProto internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 10) {
                        setName(codedInputStream.k());
                    } else if (a == 18) {
                        MessageLite.Builder newBuilder2 = FieldDescriptorProto.newBuilder();
                        codedInputStream.a(newBuilder2, extensionRegistryLite);
                        addField(newBuilder2.buildPartial());
                    } else if (a == 26) {
                        MessageLite.Builder newBuilder3 = DescriptorProto.newBuilder();
                        codedInputStream.a(newBuilder3, extensionRegistryLite);
                        addNestedType(newBuilder3.buildPartial());
                    } else if (a == 34) {
                        MessageLite.Builder newBuilder4 = EnumDescriptorProto.newBuilder();
                        codedInputStream.a(newBuilder4, extensionRegistryLite);
                        addEnumType(newBuilder4.buildPartial());
                    } else if (a == 42) {
                        MessageLite.Builder newBuilder5 = ExtensionRange.newBuilder();
                        codedInputStream.a(newBuilder5, extensionRegistryLite);
                        addExtensionRange(newBuilder5.buildPartial());
                    } else if (a == 50) {
                        MessageLite.Builder newBuilder6 = FieldDescriptorProto.newBuilder();
                        codedInputStream.a(newBuilder6, extensionRegistryLite);
                        addExtension(newBuilder6.buildPartial());
                    } else if (a == 58) {
                        MessageOptions.Builder newBuilder7 = MessageOptions.newBuilder();
                        if (hasOptions()) {
                            newBuilder7.mergeFrom(getOptions());
                        }
                        codedInputStream.a(newBuilder7, extensionRegistryLite);
                        setOptions(newBuilder7.buildPartial());
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            public Builder mergeFrom(DescriptorProto descriptorProto) {
                if (descriptorProto == DescriptorProto.getDefaultInstance()) {
                    return this;
                }
                if (descriptorProto.hasName()) {
                    setName(descriptorProto.getName());
                }
                if (!descriptorProto.field_.isEmpty()) {
                    if (this.result.field_.isEmpty()) {
                        this.result.field_ = new ArrayList();
                    }
                    this.result.field_.addAll(descriptorProto.field_);
                }
                if (!descriptorProto.extension_.isEmpty()) {
                    if (this.result.extension_.isEmpty()) {
                        this.result.extension_ = new ArrayList();
                    }
                    this.result.extension_.addAll(descriptorProto.extension_);
                }
                if (!descriptorProto.nestedType_.isEmpty()) {
                    if (this.result.nestedType_.isEmpty()) {
                        this.result.nestedType_ = new ArrayList();
                    }
                    this.result.nestedType_.addAll(descriptorProto.nestedType_);
                }
                if (!descriptorProto.enumType_.isEmpty()) {
                    if (this.result.enumType_.isEmpty()) {
                        this.result.enumType_ = new ArrayList();
                    }
                    this.result.enumType_.addAll(descriptorProto.enumType_);
                }
                if (!descriptorProto.extensionRange_.isEmpty()) {
                    if (this.result.extensionRange_.isEmpty()) {
                        this.result.extensionRange_ = new ArrayList();
                    }
                    this.result.extensionRange_.addAll(descriptorProto.extensionRange_);
                }
                if (descriptorProto.hasOptions()) {
                    mergeOptions(descriptorProto.getOptions());
                }
                mergeUnknownFields(descriptorProto.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof DescriptorProto) {
                    return mergeFrom((DescriptorProto) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeOptions(MessageOptions messageOptions) {
                DescriptorProto descriptorProto;
                if (!this.result.hasOptions() || this.result.options_ == MessageOptions.getDefaultInstance()) {
                    descriptorProto = this.result;
                } else {
                    descriptorProto = this.result;
                    messageOptions = MessageOptions.newBuilder(descriptorProto.options_).mergeFrom(messageOptions).buildPartial();
                }
                descriptorProto.options_ = messageOptions;
                this.result.hasOptions = true;
                return this;
            }

            public Builder setEnumType(int i, EnumDescriptorProto.Builder builder) {
                this.result.enumType_.set(i, builder.build());
                return this;
            }

            public Builder setEnumType(int i, EnumDescriptorProto enumDescriptorProto) {
                if (enumDescriptorProto != null) {
                    this.result.enumType_.set(i, enumDescriptorProto);
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setExtension(int i, FieldDescriptorProto.Builder builder) {
                this.result.extension_.set(i, builder.build());
                return this;
            }

            public Builder setExtension(int i, FieldDescriptorProto fieldDescriptorProto) {
                if (fieldDescriptorProto != null) {
                    this.result.extension_.set(i, fieldDescriptorProto);
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setExtensionRange(int i, ExtensionRange.Builder builder) {
                this.result.extensionRange_.set(i, builder.build());
                return this;
            }

            public Builder setExtensionRange(int i, ExtensionRange extensionRange) {
                if (extensionRange != null) {
                    this.result.extensionRange_.set(i, extensionRange);
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setField(int i, FieldDescriptorProto.Builder builder) {
                this.result.field_.set(i, builder.build());
                return this;
            }

            public Builder setField(int i, FieldDescriptorProto fieldDescriptorProto) {
                if (fieldDescriptorProto != null) {
                    this.result.field_.set(i, fieldDescriptorProto);
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setName(String str) {
                if (str != null) {
                    this.result.hasName = true;
                    this.result.name_ = str;
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setNestedType(int i, Builder builder) {
                this.result.nestedType_.set(i, builder.build());
                return this;
            }

            public Builder setNestedType(int i, DescriptorProto descriptorProto) {
                if (descriptorProto != null) {
                    this.result.nestedType_.set(i, descriptorProto);
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setOptions(MessageOptions.Builder builder) {
                this.result.hasOptions = true;
                this.result.options_ = builder.build();
                return this;
            }

            public Builder setOptions(MessageOptions messageOptions) {
                if (messageOptions != null) {
                    this.result.hasOptions = true;
                    this.result.options_ = messageOptions;
                    return this;
                }
                throw new NullPointerException();
            }
        }

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static final class ExtensionRange extends GeneratedMessage {
            public static final int END_FIELD_NUMBER = 2;
            public static final int START_FIELD_NUMBER = 1;
            private static final ExtensionRange defaultInstance = new ExtensionRange(true);
            private int end_;
            private boolean hasEnd;
            private boolean hasStart;
            private int memoizedSerializedSize;
            private int start_;

            /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
            public static final class Builder extends GeneratedMessage.Builder<Builder> {
                private ExtensionRange result;

                private Builder() {
                }

                static /* synthetic */ Builder access$2700() {
                    return create();
                }

                /* JADX INFO: Access modifiers changed from: private */
                public ExtensionRange buildParsed() {
                    if (isInitialized()) {
                        return buildPartial();
                    }
                    throw newUninitializedMessageException((Message) this.result).b();
                }

                private static Builder create() {
                    Builder builder = new Builder();
                    builder.result = new ExtensionRange();
                    return builder;
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public ExtensionRange build() {
                    if (this.result == null || isInitialized()) {
                        return buildPartial();
                    }
                    throw newUninitializedMessageException((Message) this.result);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public ExtensionRange buildPartial() {
                    ExtensionRange extensionRange = this.result;
                    if (extensionRange != null) {
                        this.result = null;
                        return extensionRange;
                    }
                    throw new IllegalStateException("build() has already been called on this Builder.");
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Builder clear() {
                    if (this.result != null) {
                        this.result = new ExtensionRange();
                        return this;
                    }
                    throw new IllegalStateException("Cannot call clear() after build().");
                }

                public Builder clearEnd() {
                    this.result.hasEnd = false;
                    this.result.end_ = 0;
                    return this;
                }

                public Builder clearStart() {
                    this.result.hasStart = false;
                    this.result.start_ = 0;
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
                /* renamed from: clone */
                public Builder mo3clone() {
                    return create().mergeFrom(this.result);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public ExtensionRange getDefaultInstanceForType() {
                    return ExtensionRange.getDefaultInstance();
                }

                @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
                public Descriptors.Descriptor getDescriptorForType() {
                    return ExtensionRange.getDescriptor();
                }

                public int getEnd() {
                    return this.result.getEnd();
                }

                public int getStart() {
                    return this.result.getStart();
                }

                public boolean hasEnd() {
                    return this.result.hasEnd();
                }

                public boolean hasStart() {
                    return this.result.hasStart();
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.google.protobuf.GeneratedMessage.Builder
                public ExtensionRange internalGetResult() {
                    return this.result;
                }

                @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
                public boolean isInitialized() {
                    return this.result.isInitialized();
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                    while (true) {
                        int a = codedInputStream.a();
                        if (a == 0) {
                            break;
                        } else if (a == 8) {
                            setStart(codedInputStream.g());
                        } else if (a == 16) {
                            setEnd(codedInputStream.g());
                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                            break;
                        }
                    }
                    setUnknownFields(newBuilder.build());
                    return this;
                }

                public Builder mergeFrom(ExtensionRange extensionRange) {
                    if (extensionRange == ExtensionRange.getDefaultInstance()) {
                        return this;
                    }
                    if (extensionRange.hasStart()) {
                        setStart(extensionRange.getStart());
                    }
                    if (extensionRange.hasEnd()) {
                        setEnd(extensionRange.getEnd());
                    }
                    mergeUnknownFields(extensionRange.getUnknownFields());
                    return this;
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Builder mergeFrom(Message message) {
                    if (message instanceof ExtensionRange) {
                        return mergeFrom((ExtensionRange) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder setEnd(int i) {
                    this.result.hasEnd = true;
                    this.result.end_ = i;
                    return this;
                }

                public Builder setStart(int i) {
                    this.result.hasStart = true;
                    this.result.start_ = i;
                    return this;
                }
            }

            static {
                DescriptorProtos.b();
                defaultInstance.initFields();
            }

            private ExtensionRange() {
                this.start_ = 0;
                this.end_ = 0;
                this.memoizedSerializedSize = -1;
                initFields();
            }

            private ExtensionRange(boolean z) {
                this.start_ = 0;
                this.end_ = 0;
                this.memoizedSerializedSize = -1;
            }

            public static ExtensionRange getDefaultInstance() {
                return defaultInstance;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DescriptorProtos.g;
            }

            private void initFields() {
            }

            public static Builder newBuilder() {
                return Builder.access$2700();
            }

            public static Builder newBuilder(ExtensionRange extensionRange) {
                return newBuilder().mergeFrom(extensionRange);
            }

            public static ExtensionRange parseDelimitedFrom(InputStream inputStream) {
                Builder newBuilder = newBuilder();
                if (newBuilder.mergeDelimitedFrom(inputStream)) {
                    return newBuilder.buildParsed();
                }
                return null;
            }

            public static ExtensionRange parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                Builder newBuilder = newBuilder();
                if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                    return newBuilder.buildParsed();
                }
                return null;
            }

            public static ExtensionRange parseFrom(ByteString byteString) {
                return ((Builder) newBuilder().mergeFrom(byteString)).buildParsed();
            }

            public static ExtensionRange parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return ((Builder) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
            }

            public static ExtensionRange parseFrom(CodedInputStream codedInputStream) {
                return ((Builder) newBuilder().mergeFrom(codedInputStream)).buildParsed();
            }

            public static ExtensionRange parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
            }

            public static ExtensionRange parseFrom(InputStream inputStream) {
                return ((Builder) newBuilder().mergeFrom(inputStream)).buildParsed();
            }

            public static ExtensionRange parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return ((Builder) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
            }

            public static ExtensionRange parseFrom(byte[] bArr) {
                return ((Builder) newBuilder().mergeFrom(bArr)).buildParsed();
            }

            public static ExtensionRange parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return ((Builder) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public ExtensionRange getDefaultInstanceForType() {
                return defaultInstance;
            }

            public int getEnd() {
                return this.end_;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSerializedSize;
                if (i != -1) {
                    return i;
                }
                int g = hasStart() ? 0 + CodedOutputStream.g(1, getStart()) : 0;
                if (hasEnd()) {
                    g += CodedOutputStream.g(2, getEnd());
                }
                int serializedSize = g + getUnknownFields().getSerializedSize();
                this.memoizedSerializedSize = serializedSize;
                return serializedSize;
            }

            public int getStart() {
                return this.start_;
            }

            public boolean hasEnd() {
                return this.hasEnd;
            }

            public boolean hasStart() {
                return this.hasStart;
            }

            @Override // com.google.protobuf.GeneratedMessage
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.h;
            }

            @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder newBuilderForType() {
                return newBuilder();
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder toBuilder() {
                return newBuilder(this);
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) {
                getSerializedSize();
                if (hasStart()) {
                    codedOutputStream.a(1, getStart());
                }
                if (hasEnd()) {
                    codedOutputStream.a(2, getEnd());
                }
                getUnknownFields().writeTo(codedOutputStream);
            }
        }

        static {
            DescriptorProtos.b();
            defaultInstance.initFields();
        }

        private DescriptorProto() {
            this.name_ = a.d;
            this.field_ = Collections.emptyList();
            this.extension_ = Collections.emptyList();
            this.nestedType_ = Collections.emptyList();
            this.enumType_ = Collections.emptyList();
            this.extensionRange_ = Collections.emptyList();
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private DescriptorProto(boolean z) {
            this.name_ = a.d;
            this.field_ = Collections.emptyList();
            this.extension_ = Collections.emptyList();
            this.nestedType_ = Collections.emptyList();
            this.enumType_ = Collections.emptyList();
            this.extensionRange_ = Collections.emptyList();
            this.memoizedSerializedSize = -1;
        }

        public static DescriptorProto getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.e;
        }

        private void initFields() {
            this.options_ = MessageOptions.getDefaultInstance();
        }

        public static Builder newBuilder() {
            return Builder.access$3400();
        }

        public static Builder newBuilder(DescriptorProto descriptorProto) {
            return newBuilder().mergeFrom(descriptorProto);
        }

        public static DescriptorProto parseDelimitedFrom(InputStream inputStream) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static DescriptorProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static DescriptorProto parseFrom(ByteString byteString) {
            return ((Builder) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static DescriptorProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static DescriptorProto parseFrom(CodedInputStream codedInputStream) {
            return ((Builder) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static DescriptorProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static DescriptorProto parseFrom(InputStream inputStream) {
            return ((Builder) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static DescriptorProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static DescriptorProto parseFrom(byte[] bArr) {
            return ((Builder) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static DescriptorProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public DescriptorProto getDefaultInstanceForType() {
            return defaultInstance;
        }

        public EnumDescriptorProto getEnumType(int i) {
            return this.enumType_.get(i);
        }

        public int getEnumTypeCount() {
            return this.enumType_.size();
        }

        public List<EnumDescriptorProto> getEnumTypeList() {
            return this.enumType_;
        }

        public FieldDescriptorProto getExtension(int i) {
            return this.extension_.get(i);
        }

        public int getExtensionCount() {
            return this.extension_.size();
        }

        public List<FieldDescriptorProto> getExtensionList() {
            return this.extension_;
        }

        public ExtensionRange getExtensionRange(int i) {
            return this.extensionRange_.get(i);
        }

        public int getExtensionRangeCount() {
            return this.extensionRange_.size();
        }

        public List<ExtensionRange> getExtensionRangeList() {
            return this.extensionRange_;
        }

        public FieldDescriptorProto getField(int i) {
            return this.field_.get(i);
        }

        public int getFieldCount() {
            return this.field_.size();
        }

        public List<FieldDescriptorProto> getFieldList() {
            return this.field_;
        }

        public String getName() {
            return this.name_;
        }

        public DescriptorProto getNestedType(int i) {
            return this.nestedType_.get(i);
        }

        public int getNestedTypeCount() {
            return this.nestedType_.size();
        }

        public List<DescriptorProto> getNestedTypeList() {
            return this.nestedType_;
        }

        public MessageOptions getOptions() {
            return this.options_;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int b = hasName() ? 0 + CodedOutputStream.b(1, getName()) : 0;
            for (FieldDescriptorProto fieldDescriptorProto : getFieldList()) {
                b += CodedOutputStream.g(2, fieldDescriptorProto);
            }
            for (DescriptorProto descriptorProto : getNestedTypeList()) {
                b += CodedOutputStream.g(3, descriptorProto);
            }
            for (EnumDescriptorProto enumDescriptorProto : getEnumTypeList()) {
                b += CodedOutputStream.g(4, enumDescriptorProto);
            }
            for (ExtensionRange extensionRange : getExtensionRangeList()) {
                b += CodedOutputStream.g(5, extensionRange);
            }
            for (FieldDescriptorProto fieldDescriptorProto2 : getExtensionList()) {
                b += CodedOutputStream.g(6, fieldDescriptorProto2);
            }
            if (hasOptions()) {
                b += CodedOutputStream.g(7, getOptions());
            }
            int serializedSize = b + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public boolean hasName() {
            return this.hasName;
        }

        public boolean hasOptions() {
            return this.hasOptions;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.f;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            for (FieldDescriptorProto fieldDescriptorProto : getFieldList()) {
                if (!fieldDescriptorProto.isInitialized()) {
                    return false;
                }
            }
            for (FieldDescriptorProto fieldDescriptorProto2 : getExtensionList()) {
                if (!fieldDescriptorProto2.isInitialized()) {
                    return false;
                }
            }
            for (DescriptorProto descriptorProto : getNestedTypeList()) {
                if (!descriptorProto.isInitialized()) {
                    return false;
                }
            }
            for (EnumDescriptorProto enumDescriptorProto : getEnumTypeList()) {
                if (!enumDescriptorProto.isInitialized()) {
                    return false;
                }
            }
            return !hasOptions() || getOptions().isInitialized();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            if (hasName()) {
                codedOutputStream.a(1, getName());
            }
            for (FieldDescriptorProto fieldDescriptorProto : getFieldList()) {
                codedOutputStream.c(2, fieldDescriptorProto);
            }
            for (DescriptorProto descriptorProto : getNestedTypeList()) {
                codedOutputStream.c(3, descriptorProto);
            }
            for (EnumDescriptorProto enumDescriptorProto : getEnumTypeList()) {
                codedOutputStream.c(4, enumDescriptorProto);
            }
            for (ExtensionRange extensionRange : getExtensionRangeList()) {
                codedOutputStream.c(5, extensionRange);
            }
            for (FieldDescriptorProto fieldDescriptorProto2 : getExtensionList()) {
                codedOutputStream.c(6, fieldDescriptorProto2);
            }
            if (hasOptions()) {
                codedOutputStream.c(7, getOptions());
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class EnumDescriptorProto extends GeneratedMessage {
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int OPTIONS_FIELD_NUMBER = 3;
        public static final int VALUE_FIELD_NUMBER = 2;
        private static final EnumDescriptorProto defaultInstance = new EnumDescriptorProto(true);
        private boolean hasName;
        private boolean hasOptions;
        private int memoizedSerializedSize;
        private String name_;
        private EnumOptions options_;
        private List<EnumValueDescriptorProto> value_;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static final class Builder extends GeneratedMessage.Builder<Builder> {
            private EnumDescriptorProto result;

            private Builder() {
            }

            static /* synthetic */ Builder access$6900() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public EnumDescriptorProto buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static Builder create() {
                Builder builder = new Builder();
                builder.result = new EnumDescriptorProto();
                return builder;
            }

            public Builder addAllValue(Iterable<? extends EnumValueDescriptorProto> iterable) {
                if (this.result.value_.isEmpty()) {
                    this.result.value_ = new ArrayList();
                }
                GeneratedMessage.Builder.addAll(iterable, this.result.value_);
                return this;
            }

            public Builder addValue(EnumValueDescriptorProto.Builder builder) {
                if (this.result.value_.isEmpty()) {
                    this.result.value_ = new ArrayList();
                }
                this.result.value_.add(builder.build());
                return this;
            }

            public Builder addValue(EnumValueDescriptorProto enumValueDescriptorProto) {
                if (enumValueDescriptorProto != null) {
                    if (this.result.value_.isEmpty()) {
                        this.result.value_ = new ArrayList();
                    }
                    this.result.value_.add(enumValueDescriptorProto);
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EnumDescriptorProto build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EnumDescriptorProto buildPartial() {
                EnumDescriptorProto enumDescriptorProto = this.result;
                if (enumDescriptorProto != null) {
                    if (enumDescriptorProto.value_ != Collections.EMPTY_LIST) {
                        EnumDescriptorProto enumDescriptorProto2 = this.result;
                        enumDescriptorProto2.value_ = Collections.unmodifiableList(enumDescriptorProto2.value_);
                    }
                    EnumDescriptorProto enumDescriptorProto3 = this.result;
                    this.result = null;
                    return enumDescriptorProto3;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                if (this.result != null) {
                    this.result = new EnumDescriptorProto();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public Builder clearName() {
                this.result.hasName = false;
                this.result.name_ = EnumDescriptorProto.getDefaultInstance().getName();
                return this;
            }

            public Builder clearOptions() {
                this.result.hasOptions = false;
                this.result.options_ = EnumOptions.getDefaultInstance();
                return this;
            }

            public Builder clearValue() {
                this.result.value_ = Collections.emptyList();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3clone() {
                return create().mergeFrom(this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EnumDescriptorProto getDefaultInstanceForType() {
                return EnumDescriptorProto.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return EnumDescriptorProto.getDescriptor();
            }

            public String getName() {
                return this.result.getName();
            }

            public EnumOptions getOptions() {
                return this.result.getOptions();
            }

            public EnumValueDescriptorProto getValue(int i) {
                return this.result.getValue(i);
            }

            public int getValueCount() {
                return this.result.getValueCount();
            }

            public List<EnumValueDescriptorProto> getValueList() {
                return Collections.unmodifiableList(this.result.value_);
            }

            public boolean hasName() {
                return this.result.hasName();
            }

            public boolean hasOptions() {
                return this.result.hasOptions();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.Builder
            public EnumDescriptorProto internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 10) {
                        setName(codedInputStream.k());
                    } else if (a == 18) {
                        MessageLite.Builder newBuilder2 = EnumValueDescriptorProto.newBuilder();
                        codedInputStream.a(newBuilder2, extensionRegistryLite);
                        addValue(newBuilder2.buildPartial());
                    } else if (a == 26) {
                        EnumOptions.Builder newBuilder3 = EnumOptions.newBuilder();
                        if (hasOptions()) {
                            newBuilder3.mergeFrom(getOptions());
                        }
                        codedInputStream.a(newBuilder3, extensionRegistryLite);
                        setOptions(newBuilder3.buildPartial());
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            public Builder mergeFrom(EnumDescriptorProto enumDescriptorProto) {
                if (enumDescriptorProto == EnumDescriptorProto.getDefaultInstance()) {
                    return this;
                }
                if (enumDescriptorProto.hasName()) {
                    setName(enumDescriptorProto.getName());
                }
                if (!enumDescriptorProto.value_.isEmpty()) {
                    if (this.result.value_.isEmpty()) {
                        this.result.value_ = new ArrayList();
                    }
                    this.result.value_.addAll(enumDescriptorProto.value_);
                }
                if (enumDescriptorProto.hasOptions()) {
                    mergeOptions(enumDescriptorProto.getOptions());
                }
                mergeUnknownFields(enumDescriptorProto.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof EnumDescriptorProto) {
                    return mergeFrom((EnumDescriptorProto) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeOptions(EnumOptions enumOptions) {
                EnumDescriptorProto enumDescriptorProto;
                if (!this.result.hasOptions() || this.result.options_ == EnumOptions.getDefaultInstance()) {
                    enumDescriptorProto = this.result;
                } else {
                    enumDescriptorProto = this.result;
                    enumOptions = EnumOptions.newBuilder(enumDescriptorProto.options_).mergeFrom(enumOptions).buildPartial();
                }
                enumDescriptorProto.options_ = enumOptions;
                this.result.hasOptions = true;
                return this;
            }

            public Builder setName(String str) {
                if (str != null) {
                    this.result.hasName = true;
                    this.result.name_ = str;
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setOptions(EnumOptions.Builder builder) {
                this.result.hasOptions = true;
                this.result.options_ = builder.build();
                return this;
            }

            public Builder setOptions(EnumOptions enumOptions) {
                if (enumOptions != null) {
                    this.result.hasOptions = true;
                    this.result.options_ = enumOptions;
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setValue(int i, EnumValueDescriptorProto.Builder builder) {
                this.result.value_.set(i, builder.build());
                return this;
            }

            public Builder setValue(int i, EnumValueDescriptorProto enumValueDescriptorProto) {
                if (enumValueDescriptorProto != null) {
                    this.result.value_.set(i, enumValueDescriptorProto);
                    return this;
                }
                throw new NullPointerException();
            }
        }

        static {
            DescriptorProtos.b();
            defaultInstance.initFields();
        }

        private EnumDescriptorProto() {
            this.name_ = a.d;
            this.value_ = Collections.emptyList();
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private EnumDescriptorProto(boolean z) {
            this.name_ = a.d;
            this.value_ = Collections.emptyList();
            this.memoizedSerializedSize = -1;
        }

        public static EnumDescriptorProto getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.k;
        }

        private void initFields() {
            this.options_ = EnumOptions.getDefaultInstance();
        }

        public static Builder newBuilder() {
            return Builder.access$6900();
        }

        public static Builder newBuilder(EnumDescriptorProto enumDescriptorProto) {
            return newBuilder().mergeFrom(enumDescriptorProto);
        }

        public static EnumDescriptorProto parseDelimitedFrom(InputStream inputStream) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static EnumDescriptorProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static EnumDescriptorProto parseFrom(ByteString byteString) {
            return ((Builder) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static EnumDescriptorProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static EnumDescriptorProto parseFrom(CodedInputStream codedInputStream) {
            return ((Builder) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static EnumDescriptorProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static EnumDescriptorProto parseFrom(InputStream inputStream) {
            return ((Builder) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static EnumDescriptorProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static EnumDescriptorProto parseFrom(byte[] bArr) {
            return ((Builder) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static EnumDescriptorProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public EnumDescriptorProto getDefaultInstanceForType() {
            return defaultInstance;
        }

        public String getName() {
            return this.name_;
        }

        public EnumOptions getOptions() {
            return this.options_;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int b = hasName() ? 0 + CodedOutputStream.b(1, getName()) : 0;
            for (EnumValueDescriptorProto enumValueDescriptorProto : getValueList()) {
                b += CodedOutputStream.g(2, enumValueDescriptorProto);
            }
            if (hasOptions()) {
                b += CodedOutputStream.g(3, getOptions());
            }
            int serializedSize = b + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public EnumValueDescriptorProto getValue(int i) {
            return this.value_.get(i);
        }

        public int getValueCount() {
            return this.value_.size();
        }

        public List<EnumValueDescriptorProto> getValueList() {
            return this.value_;
        }

        public boolean hasName() {
            return this.hasName;
        }

        public boolean hasOptions() {
            return this.hasOptions;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.l;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            for (EnumValueDescriptorProto enumValueDescriptorProto : getValueList()) {
                if (!enumValueDescriptorProto.isInitialized()) {
                    return false;
                }
            }
            return !hasOptions() || getOptions().isInitialized();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            if (hasName()) {
                codedOutputStream.a(1, getName());
            }
            for (EnumValueDescriptorProto enumValueDescriptorProto : getValueList()) {
                codedOutputStream.c(2, enumValueDescriptorProto);
            }
            if (hasOptions()) {
                codedOutputStream.c(3, getOptions());
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class EnumOptions extends GeneratedMessage.ExtendableMessage<EnumOptions> {
        public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
        private static final EnumOptions defaultInstance = new EnumOptions(true);
        private int memoizedSerializedSize;
        private List<UninterpretedOption> uninterpretedOption_;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static final class Builder extends GeneratedMessage.ExtendableBuilder<EnumOptions, Builder> {
            private EnumOptions result;

            private Builder() {
            }

            static /* synthetic */ Builder access$15700() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public EnumOptions buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static Builder create() {
                Builder builder = new Builder();
                builder.result = new EnumOptions();
                return builder;
            }

            public Builder addAllUninterpretedOption(Iterable<? extends UninterpretedOption> iterable) {
                if (this.result.uninterpretedOption_.isEmpty()) {
                    this.result.uninterpretedOption_ = new ArrayList();
                }
                GeneratedMessage.ExtendableBuilder.addAll(iterable, this.result.uninterpretedOption_);
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption.Builder builder) {
                if (this.result.uninterpretedOption_.isEmpty()) {
                    this.result.uninterpretedOption_ = new ArrayList();
                }
                this.result.uninterpretedOption_.add(builder.build());
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption uninterpretedOption) {
                if (uninterpretedOption != null) {
                    if (this.result.uninterpretedOption_.isEmpty()) {
                        this.result.uninterpretedOption_ = new ArrayList();
                    }
                    this.result.uninterpretedOption_.add(uninterpretedOption);
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EnumOptions build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EnumOptions buildPartial() {
                EnumOptions enumOptions = this.result;
                if (enumOptions != null) {
                    if (enumOptions.uninterpretedOption_ != Collections.EMPTY_LIST) {
                        EnumOptions enumOptions2 = this.result;
                        enumOptions2.uninterpretedOption_ = Collections.unmodifiableList(enumOptions2.uninterpretedOption_);
                    }
                    EnumOptions enumOptions3 = this.result;
                    this.result = null;
                    return enumOptions3;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                if (this.result != null) {
                    this.result = new EnumOptions();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public Builder clearUninterpretedOption() {
                this.result.uninterpretedOption_ = Collections.emptyList();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.ExtendableBuilder, com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3clone() {
                return create().mergeFrom(this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EnumOptions getDefaultInstanceForType() {
                return EnumOptions.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return EnumOptions.getDescriptor();
            }

            public UninterpretedOption getUninterpretedOption(int i) {
                return this.result.getUninterpretedOption(i);
            }

            public int getUninterpretedOptionCount() {
                return this.result.getUninterpretedOptionCount();
            }

            public List<UninterpretedOption> getUninterpretedOptionList() {
                return Collections.unmodifiableList(this.result.uninterpretedOption_);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.ExtendableBuilder, com.google.protobuf.GeneratedMessage.Builder
            public EnumOptions internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 7994) {
                        UninterpretedOption.Builder newBuilder2 = UninterpretedOption.newBuilder();
                        codedInputStream.a(newBuilder2, extensionRegistryLite);
                        addUninterpretedOption(newBuilder2.buildPartial());
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            public Builder mergeFrom(EnumOptions enumOptions) {
                if (enumOptions == EnumOptions.getDefaultInstance()) {
                    return this;
                }
                if (!enumOptions.uninterpretedOption_.isEmpty()) {
                    if (this.result.uninterpretedOption_.isEmpty()) {
                        this.result.uninterpretedOption_ = new ArrayList();
                    }
                    this.result.uninterpretedOption_.addAll(enumOptions.uninterpretedOption_);
                }
                mergeExtensionFields(enumOptions);
                mergeUnknownFields(enumOptions.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof EnumOptions) {
                    return mergeFrom((EnumOptions) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder setUninterpretedOption(int i, UninterpretedOption.Builder builder) {
                this.result.uninterpretedOption_.set(i, builder.build());
                return this;
            }

            public Builder setUninterpretedOption(int i, UninterpretedOption uninterpretedOption) {
                if (uninterpretedOption != null) {
                    this.result.uninterpretedOption_.set(i, uninterpretedOption);
                    return this;
                }
                throw new NullPointerException();
            }
        }

        static {
            DescriptorProtos.b();
            defaultInstance.initFields();
        }

        private EnumOptions() {
            this.uninterpretedOption_ = Collections.emptyList();
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private EnumOptions(boolean z) {
            this.uninterpretedOption_ = Collections.emptyList();
            this.memoizedSerializedSize = -1;
        }

        public static EnumOptions getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.y;
        }

        private void initFields() {
        }

        public static Builder newBuilder() {
            return Builder.access$15700();
        }

        public static Builder newBuilder(EnumOptions enumOptions) {
            return newBuilder().mergeFrom(enumOptions);
        }

        public static EnumOptions parseDelimitedFrom(InputStream inputStream) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static EnumOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static EnumOptions parseFrom(ByteString byteString) {
            return ((Builder) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static EnumOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static EnumOptions parseFrom(CodedInputStream codedInputStream) {
            return ((Builder) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static EnumOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static EnumOptions parseFrom(InputStream inputStream) {
            return ((Builder) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static EnumOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static EnumOptions parseFrom(byte[] bArr) {
            return ((Builder) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static EnumOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public EnumOptions getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (UninterpretedOption uninterpretedOption : getUninterpretedOptionList()) {
                i2 += CodedOutputStream.g(999, uninterpretedOption);
            }
            int extensionsSerializedSize = i2 + extensionsSerializedSize() + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = extensionsSerializedSize;
            return extensionsSerializedSize;
        }

        public UninterpretedOption getUninterpretedOption(int i) {
            return this.uninterpretedOption_.get(i);
        }

        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        public List<UninterpretedOption> getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.z;
        }

        @Override // com.google.protobuf.GeneratedMessage.ExtendableMessage, com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            for (UninterpretedOption uninterpretedOption : getUninterpretedOptionList()) {
                if (!uninterpretedOption.isInitialized()) {
                    return false;
                }
            }
            return extensionsAreInitialized();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            GeneratedMessage.ExtendableMessage<MessageType>.ExtensionWriter newExtensionWriter = newExtensionWriter();
            for (UninterpretedOption uninterpretedOption : getUninterpretedOptionList()) {
                codedOutputStream.c(999, uninterpretedOption);
            }
            newExtensionWriter.a(536870912, codedOutputStream);
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class EnumValueDescriptorProto extends GeneratedMessage {
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int NUMBER_FIELD_NUMBER = 2;
        public static final int OPTIONS_FIELD_NUMBER = 3;
        private static final EnumValueDescriptorProto defaultInstance = new EnumValueDescriptorProto(true);
        private boolean hasName;
        private boolean hasNumber;
        private boolean hasOptions;
        private int memoizedSerializedSize;
        private String name_;
        private int number_;
        private EnumValueOptions options_;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static final class Builder extends GeneratedMessage.Builder<Builder> {
            private EnumValueDescriptorProto result;

            private Builder() {
            }

            static /* synthetic */ Builder access$7900() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public EnumValueDescriptorProto buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static Builder create() {
                Builder builder = new Builder();
                builder.result = new EnumValueDescriptorProto();
                return builder;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EnumValueDescriptorProto build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EnumValueDescriptorProto buildPartial() {
                EnumValueDescriptorProto enumValueDescriptorProto = this.result;
                if (enumValueDescriptorProto != null) {
                    this.result = null;
                    return enumValueDescriptorProto;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                if (this.result != null) {
                    this.result = new EnumValueDescriptorProto();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public Builder clearName() {
                this.result.hasName = false;
                this.result.name_ = EnumValueDescriptorProto.getDefaultInstance().getName();
                return this;
            }

            public Builder clearNumber() {
                this.result.hasNumber = false;
                this.result.number_ = 0;
                return this;
            }

            public Builder clearOptions() {
                this.result.hasOptions = false;
                this.result.options_ = EnumValueOptions.getDefaultInstance();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3clone() {
                return create().mergeFrom(this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EnumValueDescriptorProto getDefaultInstanceForType() {
                return EnumValueDescriptorProto.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return EnumValueDescriptorProto.getDescriptor();
            }

            public String getName() {
                return this.result.getName();
            }

            public int getNumber() {
                return this.result.getNumber();
            }

            public EnumValueOptions getOptions() {
                return this.result.getOptions();
            }

            public boolean hasName() {
                return this.result.hasName();
            }

            public boolean hasNumber() {
                return this.result.hasNumber();
            }

            public boolean hasOptions() {
                return this.result.hasOptions();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.Builder
            public EnumValueDescriptorProto internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 10) {
                        setName(codedInputStream.k());
                    } else if (a == 16) {
                        setNumber(codedInputStream.g());
                    } else if (a == 26) {
                        EnumValueOptions.Builder newBuilder2 = EnumValueOptions.newBuilder();
                        if (hasOptions()) {
                            newBuilder2.mergeFrom(getOptions());
                        }
                        codedInputStream.a(newBuilder2, extensionRegistryLite);
                        setOptions(newBuilder2.buildPartial());
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            public Builder mergeFrom(EnumValueDescriptorProto enumValueDescriptorProto) {
                if (enumValueDescriptorProto == EnumValueDescriptorProto.getDefaultInstance()) {
                    return this;
                }
                if (enumValueDescriptorProto.hasName()) {
                    setName(enumValueDescriptorProto.getName());
                }
                if (enumValueDescriptorProto.hasNumber()) {
                    setNumber(enumValueDescriptorProto.getNumber());
                }
                if (enumValueDescriptorProto.hasOptions()) {
                    mergeOptions(enumValueDescriptorProto.getOptions());
                }
                mergeUnknownFields(enumValueDescriptorProto.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof EnumValueDescriptorProto) {
                    return mergeFrom((EnumValueDescriptorProto) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeOptions(EnumValueOptions enumValueOptions) {
                EnumValueDescriptorProto enumValueDescriptorProto;
                if (!this.result.hasOptions() || this.result.options_ == EnumValueOptions.getDefaultInstance()) {
                    enumValueDescriptorProto = this.result;
                } else {
                    enumValueDescriptorProto = this.result;
                    enumValueOptions = EnumValueOptions.newBuilder(enumValueDescriptorProto.options_).mergeFrom(enumValueOptions).buildPartial();
                }
                enumValueDescriptorProto.options_ = enumValueOptions;
                this.result.hasOptions = true;
                return this;
            }

            public Builder setName(String str) {
                if (str != null) {
                    this.result.hasName = true;
                    this.result.name_ = str;
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setNumber(int i) {
                this.result.hasNumber = true;
                this.result.number_ = i;
                return this;
            }

            public Builder setOptions(EnumValueOptions.Builder builder) {
                this.result.hasOptions = true;
                this.result.options_ = builder.build();
                return this;
            }

            public Builder setOptions(EnumValueOptions enumValueOptions) {
                if (enumValueOptions != null) {
                    this.result.hasOptions = true;
                    this.result.options_ = enumValueOptions;
                    return this;
                }
                throw new NullPointerException();
            }
        }

        static {
            DescriptorProtos.b();
            defaultInstance.initFields();
        }

        private EnumValueDescriptorProto() {
            this.name_ = a.d;
            this.number_ = 0;
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private EnumValueDescriptorProto(boolean z) {
            this.name_ = a.d;
            this.number_ = 0;
            this.memoizedSerializedSize = -1;
        }

        public static EnumValueDescriptorProto getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.m;
        }

        private void initFields() {
            this.options_ = EnumValueOptions.getDefaultInstance();
        }

        public static Builder newBuilder() {
            return Builder.access$7900();
        }

        public static Builder newBuilder(EnumValueDescriptorProto enumValueDescriptorProto) {
            return newBuilder().mergeFrom(enumValueDescriptorProto);
        }

        public static EnumValueDescriptorProto parseDelimitedFrom(InputStream inputStream) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static EnumValueDescriptorProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static EnumValueDescriptorProto parseFrom(ByteString byteString) {
            return ((Builder) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static EnumValueDescriptorProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static EnumValueDescriptorProto parseFrom(CodedInputStream codedInputStream) {
            return ((Builder) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static EnumValueDescriptorProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static EnumValueDescriptorProto parseFrom(InputStream inputStream) {
            return ((Builder) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static EnumValueDescriptorProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static EnumValueDescriptorProto parseFrom(byte[] bArr) {
            return ((Builder) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static EnumValueDescriptorProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public EnumValueDescriptorProto getDefaultInstanceForType() {
            return defaultInstance;
        }

        public String getName() {
            return this.name_;
        }

        public int getNumber() {
            return this.number_;
        }

        public EnumValueOptions getOptions() {
            return this.options_;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int b = hasName() ? 0 + CodedOutputStream.b(1, getName()) : 0;
            if (hasNumber()) {
                b += CodedOutputStream.g(2, getNumber());
            }
            if (hasOptions()) {
                b += CodedOutputStream.g(3, getOptions());
            }
            int serializedSize = b + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public boolean hasName() {
            return this.hasName;
        }

        public boolean hasNumber() {
            return this.hasNumber;
        }

        public boolean hasOptions() {
            return this.hasOptions;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.n;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            return !hasOptions() || getOptions().isInitialized();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            if (hasName()) {
                codedOutputStream.a(1, getName());
            }
            if (hasNumber()) {
                codedOutputStream.a(2, getNumber());
            }
            if (hasOptions()) {
                codedOutputStream.c(3, getOptions());
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class EnumValueOptions extends GeneratedMessage.ExtendableMessage<EnumValueOptions> {
        public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
        private static final EnumValueOptions defaultInstance = new EnumValueOptions(true);
        private int memoizedSerializedSize;
        private List<UninterpretedOption> uninterpretedOption_;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static final class Builder extends GeneratedMessage.ExtendableBuilder<EnumValueOptions, Builder> {
            private EnumValueOptions result;

            private Builder() {
            }

            static /* synthetic */ Builder access$16300() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public EnumValueOptions buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static Builder create() {
                Builder builder = new Builder();
                builder.result = new EnumValueOptions();
                return builder;
            }

            public Builder addAllUninterpretedOption(Iterable<? extends UninterpretedOption> iterable) {
                if (this.result.uninterpretedOption_.isEmpty()) {
                    this.result.uninterpretedOption_ = new ArrayList();
                }
                GeneratedMessage.ExtendableBuilder.addAll(iterable, this.result.uninterpretedOption_);
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption.Builder builder) {
                if (this.result.uninterpretedOption_.isEmpty()) {
                    this.result.uninterpretedOption_ = new ArrayList();
                }
                this.result.uninterpretedOption_.add(builder.build());
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption uninterpretedOption) {
                if (uninterpretedOption != null) {
                    if (this.result.uninterpretedOption_.isEmpty()) {
                        this.result.uninterpretedOption_ = new ArrayList();
                    }
                    this.result.uninterpretedOption_.add(uninterpretedOption);
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EnumValueOptions build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EnumValueOptions buildPartial() {
                EnumValueOptions enumValueOptions = this.result;
                if (enumValueOptions != null) {
                    if (enumValueOptions.uninterpretedOption_ != Collections.EMPTY_LIST) {
                        EnumValueOptions enumValueOptions2 = this.result;
                        enumValueOptions2.uninterpretedOption_ = Collections.unmodifiableList(enumValueOptions2.uninterpretedOption_);
                    }
                    EnumValueOptions enumValueOptions3 = this.result;
                    this.result = null;
                    return enumValueOptions3;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                if (this.result != null) {
                    this.result = new EnumValueOptions();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public Builder clearUninterpretedOption() {
                this.result.uninterpretedOption_ = Collections.emptyList();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.ExtendableBuilder, com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3clone() {
                return create().mergeFrom(this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EnumValueOptions getDefaultInstanceForType() {
                return EnumValueOptions.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return EnumValueOptions.getDescriptor();
            }

            public UninterpretedOption getUninterpretedOption(int i) {
                return this.result.getUninterpretedOption(i);
            }

            public int getUninterpretedOptionCount() {
                return this.result.getUninterpretedOptionCount();
            }

            public List<UninterpretedOption> getUninterpretedOptionList() {
                return Collections.unmodifiableList(this.result.uninterpretedOption_);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.ExtendableBuilder, com.google.protobuf.GeneratedMessage.Builder
            public EnumValueOptions internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 7994) {
                        UninterpretedOption.Builder newBuilder2 = UninterpretedOption.newBuilder();
                        codedInputStream.a(newBuilder2, extensionRegistryLite);
                        addUninterpretedOption(newBuilder2.buildPartial());
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            public Builder mergeFrom(EnumValueOptions enumValueOptions) {
                if (enumValueOptions == EnumValueOptions.getDefaultInstance()) {
                    return this;
                }
                if (!enumValueOptions.uninterpretedOption_.isEmpty()) {
                    if (this.result.uninterpretedOption_.isEmpty()) {
                        this.result.uninterpretedOption_ = new ArrayList();
                    }
                    this.result.uninterpretedOption_.addAll(enumValueOptions.uninterpretedOption_);
                }
                mergeExtensionFields(enumValueOptions);
                mergeUnknownFields(enumValueOptions.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof EnumValueOptions) {
                    return mergeFrom((EnumValueOptions) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder setUninterpretedOption(int i, UninterpretedOption.Builder builder) {
                this.result.uninterpretedOption_.set(i, builder.build());
                return this;
            }

            public Builder setUninterpretedOption(int i, UninterpretedOption uninterpretedOption) {
                if (uninterpretedOption != null) {
                    this.result.uninterpretedOption_.set(i, uninterpretedOption);
                    return this;
                }
                throw new NullPointerException();
            }
        }

        static {
            DescriptorProtos.b();
            defaultInstance.initFields();
        }

        private EnumValueOptions() {
            this.uninterpretedOption_ = Collections.emptyList();
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private EnumValueOptions(boolean z) {
            this.uninterpretedOption_ = Collections.emptyList();
            this.memoizedSerializedSize = -1;
        }

        public static EnumValueOptions getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.A;
        }

        private void initFields() {
        }

        public static Builder newBuilder() {
            return Builder.access$16300();
        }

        public static Builder newBuilder(EnumValueOptions enumValueOptions) {
            return newBuilder().mergeFrom(enumValueOptions);
        }

        public static EnumValueOptions parseDelimitedFrom(InputStream inputStream) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static EnumValueOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static EnumValueOptions parseFrom(ByteString byteString) {
            return ((Builder) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static EnumValueOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static EnumValueOptions parseFrom(CodedInputStream codedInputStream) {
            return ((Builder) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static EnumValueOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static EnumValueOptions parseFrom(InputStream inputStream) {
            return ((Builder) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static EnumValueOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static EnumValueOptions parseFrom(byte[] bArr) {
            return ((Builder) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static EnumValueOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public EnumValueOptions getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (UninterpretedOption uninterpretedOption : getUninterpretedOptionList()) {
                i2 += CodedOutputStream.g(999, uninterpretedOption);
            }
            int extensionsSerializedSize = i2 + extensionsSerializedSize() + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = extensionsSerializedSize;
            return extensionsSerializedSize;
        }

        public UninterpretedOption getUninterpretedOption(int i) {
            return this.uninterpretedOption_.get(i);
        }

        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        public List<UninterpretedOption> getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.B;
        }

        @Override // com.google.protobuf.GeneratedMessage.ExtendableMessage, com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            for (UninterpretedOption uninterpretedOption : getUninterpretedOptionList()) {
                if (!uninterpretedOption.isInitialized()) {
                    return false;
                }
            }
            return extensionsAreInitialized();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            GeneratedMessage.ExtendableMessage<MessageType>.ExtensionWriter newExtensionWriter = newExtensionWriter();
            for (UninterpretedOption uninterpretedOption : getUninterpretedOptionList()) {
                codedOutputStream.c(999, uninterpretedOption);
            }
            newExtensionWriter.a(536870912, codedOutputStream);
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class FieldDescriptorProto extends GeneratedMessage {
        public static final int DEFAULT_VALUE_FIELD_NUMBER = 7;
        public static final int EXTENDEE_FIELD_NUMBER = 2;
        public static final int LABEL_FIELD_NUMBER = 4;
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int NUMBER_FIELD_NUMBER = 3;
        public static final int OPTIONS_FIELD_NUMBER = 8;
        public static final int TYPE_FIELD_NUMBER = 5;
        public static final int TYPE_NAME_FIELD_NUMBER = 6;
        private static final FieldDescriptorProto defaultInstance = new FieldDescriptorProto(true);
        private String defaultValue_;
        private String extendee_;
        private boolean hasDefaultValue;
        private boolean hasExtendee;
        private boolean hasLabel;
        private boolean hasName;
        private boolean hasNumber;
        private boolean hasOptions;
        private boolean hasType;
        private boolean hasTypeName;
        private Label label_;
        private int memoizedSerializedSize;
        private String name_;
        private int number_;
        private FieldOptions options_;
        private String typeName_;
        private Type type_;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static final class Builder extends GeneratedMessage.Builder<Builder> {
            private FieldDescriptorProto result;

            private Builder() {
            }

            static /* synthetic */ Builder access$4800() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public FieldDescriptorProto buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static Builder create() {
                Builder builder = new Builder();
                builder.result = new FieldDescriptorProto();
                return builder;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FieldDescriptorProto build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FieldDescriptorProto buildPartial() {
                FieldDescriptorProto fieldDescriptorProto = this.result;
                if (fieldDescriptorProto != null) {
                    this.result = null;
                    return fieldDescriptorProto;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                if (this.result != null) {
                    this.result = new FieldDescriptorProto();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public Builder clearDefaultValue() {
                this.result.hasDefaultValue = false;
                this.result.defaultValue_ = FieldDescriptorProto.getDefaultInstance().getDefaultValue();
                return this;
            }

            public Builder clearExtendee() {
                this.result.hasExtendee = false;
                this.result.extendee_ = FieldDescriptorProto.getDefaultInstance().getExtendee();
                return this;
            }

            public Builder clearLabel() {
                this.result.hasLabel = false;
                this.result.label_ = Label.LABEL_OPTIONAL;
                return this;
            }

            public Builder clearName() {
                this.result.hasName = false;
                this.result.name_ = FieldDescriptorProto.getDefaultInstance().getName();
                return this;
            }

            public Builder clearNumber() {
                this.result.hasNumber = false;
                this.result.number_ = 0;
                return this;
            }

            public Builder clearOptions() {
                this.result.hasOptions = false;
                this.result.options_ = FieldOptions.getDefaultInstance();
                return this;
            }

            public Builder clearType() {
                this.result.hasType = false;
                this.result.type_ = Type.TYPE_DOUBLE;
                return this;
            }

            public Builder clearTypeName() {
                this.result.hasTypeName = false;
                this.result.typeName_ = FieldDescriptorProto.getDefaultInstance().getTypeName();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3clone() {
                return create().mergeFrom(this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FieldDescriptorProto getDefaultInstanceForType() {
                return FieldDescriptorProto.getDefaultInstance();
            }

            public String getDefaultValue() {
                return this.result.getDefaultValue();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return FieldDescriptorProto.getDescriptor();
            }

            public String getExtendee() {
                return this.result.getExtendee();
            }

            public Label getLabel() {
                return this.result.getLabel();
            }

            public String getName() {
                return this.result.getName();
            }

            public int getNumber() {
                return this.result.getNumber();
            }

            public FieldOptions getOptions() {
                return this.result.getOptions();
            }

            public Type getType() {
                return this.result.getType();
            }

            public String getTypeName() {
                return this.result.getTypeName();
            }

            public boolean hasDefaultValue() {
                return this.result.hasDefaultValue();
            }

            public boolean hasExtendee() {
                return this.result.hasExtendee();
            }

            public boolean hasLabel() {
                return this.result.hasLabel();
            }

            public boolean hasName() {
                return this.result.hasName();
            }

            public boolean hasNumber() {
                return this.result.hasNumber();
            }

            public boolean hasOptions() {
                return this.result.hasOptions();
            }

            public boolean hasType() {
                return this.result.hasType();
            }

            public boolean hasTypeName() {
                return this.result.hasTypeName();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.Builder
            public FieldDescriptorProto internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                int n;
                int i;
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 10) {
                        setName(codedInputStream.k());
                    } else if (a == 18) {
                        setExtendee(codedInputStream.k());
                    } else if (a == 24) {
                        setNumber(codedInputStream.g());
                    } else if (a == 32) {
                        n = codedInputStream.n();
                        Label valueOf = Label.valueOf(n);
                        if (valueOf == null) {
                            i = 4;
                            newBuilder.mergeVarintField(i, n);
                        } else {
                            setLabel(valueOf);
                        }
                    } else if (a == 40) {
                        n = codedInputStream.n();
                        Type valueOf2 = Type.valueOf(n);
                        if (valueOf2 == null) {
                            i = 5;
                            newBuilder.mergeVarintField(i, n);
                        } else {
                            setType(valueOf2);
                        }
                    } else if (a == 50) {
                        setTypeName(codedInputStream.k());
                    } else if (a == 58) {
                        setDefaultValue(codedInputStream.k());
                    } else if (a == 66) {
                        FieldOptions.Builder newBuilder2 = FieldOptions.newBuilder();
                        if (hasOptions()) {
                            newBuilder2.mergeFrom(getOptions());
                        }
                        codedInputStream.a(newBuilder2, extensionRegistryLite);
                        setOptions(newBuilder2.buildPartial());
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            public Builder mergeFrom(FieldDescriptorProto fieldDescriptorProto) {
                if (fieldDescriptorProto == FieldDescriptorProto.getDefaultInstance()) {
                    return this;
                }
                if (fieldDescriptorProto.hasName()) {
                    setName(fieldDescriptorProto.getName());
                }
                if (fieldDescriptorProto.hasNumber()) {
                    setNumber(fieldDescriptorProto.getNumber());
                }
                if (fieldDescriptorProto.hasLabel()) {
                    setLabel(fieldDescriptorProto.getLabel());
                }
                if (fieldDescriptorProto.hasType()) {
                    setType(fieldDescriptorProto.getType());
                }
                if (fieldDescriptorProto.hasTypeName()) {
                    setTypeName(fieldDescriptorProto.getTypeName());
                }
                if (fieldDescriptorProto.hasExtendee()) {
                    setExtendee(fieldDescriptorProto.getExtendee());
                }
                if (fieldDescriptorProto.hasDefaultValue()) {
                    setDefaultValue(fieldDescriptorProto.getDefaultValue());
                }
                if (fieldDescriptorProto.hasOptions()) {
                    mergeOptions(fieldDescriptorProto.getOptions());
                }
                mergeUnknownFields(fieldDescriptorProto.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof FieldDescriptorProto) {
                    return mergeFrom((FieldDescriptorProto) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeOptions(FieldOptions fieldOptions) {
                FieldDescriptorProto fieldDescriptorProto;
                if (!this.result.hasOptions() || this.result.options_ == FieldOptions.getDefaultInstance()) {
                    fieldDescriptorProto = this.result;
                } else {
                    fieldDescriptorProto = this.result;
                    fieldOptions = FieldOptions.newBuilder(fieldDescriptorProto.options_).mergeFrom(fieldOptions).buildPartial();
                }
                fieldDescriptorProto.options_ = fieldOptions;
                this.result.hasOptions = true;
                return this;
            }

            public Builder setDefaultValue(String str) {
                if (str != null) {
                    this.result.hasDefaultValue = true;
                    this.result.defaultValue_ = str;
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setExtendee(String str) {
                if (str != null) {
                    this.result.hasExtendee = true;
                    this.result.extendee_ = str;
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setLabel(Label label) {
                if (label != null) {
                    this.result.hasLabel = true;
                    this.result.label_ = label;
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setName(String str) {
                if (str != null) {
                    this.result.hasName = true;
                    this.result.name_ = str;
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setNumber(int i) {
                this.result.hasNumber = true;
                this.result.number_ = i;
                return this;
            }

            public Builder setOptions(FieldOptions.Builder builder) {
                this.result.hasOptions = true;
                this.result.options_ = builder.build();
                return this;
            }

            public Builder setOptions(FieldOptions fieldOptions) {
                if (fieldOptions != null) {
                    this.result.hasOptions = true;
                    this.result.options_ = fieldOptions;
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setType(Type type) {
                if (type != null) {
                    this.result.hasType = true;
                    this.result.type_ = type;
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setTypeName(String str) {
                if (str != null) {
                    this.result.hasTypeName = true;
                    this.result.typeName_ = str;
                    return this;
                }
                throw new NullPointerException();
            }
        }

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public enum Label implements ProtocolMessageEnum {
            LABEL_OPTIONAL(0, 1),
            LABEL_REQUIRED(1, 2),
            LABEL_REPEATED(2, 3);
            
            private final int index;
            private final int value;
            private static Internal.EnumLiteMap<Label> internalValueMap = new Internal.EnumLiteMap<Label>() { // from class: com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Label.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public Label findValueByNumber(int i) {
                    return Label.valueOf(i);
                }
            };
            private static final Label[] VALUES = {LABEL_OPTIONAL, LABEL_REQUIRED, LABEL_REPEATED};

            static {
                DescriptorProtos.a();
            }

            Label(int i, int i2) {
                this.index = i;
                this.value = i2;
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return FieldDescriptorProto.getDescriptor().getEnumTypes().get(1);
            }

            public static Internal.EnumLiteMap<Label> internalGetValueMap() {
                return internalValueMap;
            }

            public static Label valueOf(int i) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            return null;
                        }
                        return LABEL_REPEATED;
                    }
                    return LABEL_REQUIRED;
                }
                return LABEL_OPTIONAL;
            }

            public static Label valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() == getDescriptor()) {
                    return VALUES[enumValueDescriptor.getIndex()];
                }
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return getDescriptor().getValues().get(this.index);
            }
        }

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public enum Type implements ProtocolMessageEnum {
            TYPE_DOUBLE(0, 1),
            TYPE_FLOAT(1, 2),
            TYPE_INT64(2, 3),
            TYPE_UINT64(3, 4),
            TYPE_INT32(4, 5),
            TYPE_FIXED64(5, 6),
            TYPE_FIXED32(6, 7),
            TYPE_BOOL(7, 8),
            TYPE_STRING(8, 9),
            TYPE_GROUP(9, 10),
            TYPE_MESSAGE(10, 11),
            TYPE_BYTES(11, 12),
            TYPE_UINT32(12, 13),
            TYPE_ENUM(13, 14),
            TYPE_SFIXED32(14, 15),
            TYPE_SFIXED64(15, 16),
            TYPE_SINT32(16, 17),
            TYPE_SINT64(17, 18);
            
            private final int index;
            private final int value;
            private static Internal.EnumLiteMap<Type> internalValueMap = new Internal.EnumLiteMap<Type>() { // from class: com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Type.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public Type findValueByNumber(int i) {
                    return Type.valueOf(i);
                }
            };
            private static final Type[] VALUES = {TYPE_DOUBLE, TYPE_FLOAT, TYPE_INT64, TYPE_UINT64, TYPE_INT32, TYPE_FIXED64, TYPE_FIXED32, TYPE_BOOL, TYPE_STRING, TYPE_GROUP, TYPE_MESSAGE, TYPE_BYTES, TYPE_UINT32, TYPE_ENUM, TYPE_SFIXED32, TYPE_SFIXED64, TYPE_SINT32, TYPE_SINT64};

            static {
                DescriptorProtos.a();
            }

            Type(int i, int i2) {
                this.index = i;
                this.value = i2;
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return FieldDescriptorProto.getDescriptor().getEnumTypes().get(0);
            }

            public static Internal.EnumLiteMap<Type> internalGetValueMap() {
                return internalValueMap;
            }

            public static Type valueOf(int i) {
                switch (i) {
                    case 1:
                        return TYPE_DOUBLE;
                    case 2:
                        return TYPE_FLOAT;
                    case 3:
                        return TYPE_INT64;
                    case 4:
                        return TYPE_UINT64;
                    case 5:
                        return TYPE_INT32;
                    case 6:
                        return TYPE_FIXED64;
                    case 7:
                        return TYPE_FIXED32;
                    case 8:
                        return TYPE_BOOL;
                    case 9:
                        return TYPE_STRING;
                    case 10:
                        return TYPE_GROUP;
                    case 11:
                        return TYPE_MESSAGE;
                    case 12:
                        return TYPE_BYTES;
                    case 13:
                        return TYPE_UINT32;
                    case 14:
                        return TYPE_ENUM;
                    case 15:
                        return TYPE_SFIXED32;
                    case 16:
                        return TYPE_SFIXED64;
                    case 17:
                        return TYPE_SINT32;
                    case 18:
                        return TYPE_SINT64;
                    default:
                        return null;
                }
            }

            public static Type valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() == getDescriptor()) {
                    return VALUES[enumValueDescriptor.getIndex()];
                }
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return getDescriptor().getValues().get(this.index);
            }
        }

        static {
            DescriptorProtos.b();
            defaultInstance.initFields();
        }

        private FieldDescriptorProto() {
            this.name_ = a.d;
            this.number_ = 0;
            this.typeName_ = a.d;
            this.extendee_ = a.d;
            this.defaultValue_ = a.d;
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private FieldDescriptorProto(boolean z) {
            this.name_ = a.d;
            this.number_ = 0;
            this.typeName_ = a.d;
            this.extendee_ = a.d;
            this.defaultValue_ = a.d;
            this.memoizedSerializedSize = -1;
        }

        public static FieldDescriptorProto getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.i;
        }

        private void initFields() {
            this.label_ = Label.LABEL_OPTIONAL;
            this.type_ = Type.TYPE_DOUBLE;
            this.options_ = FieldOptions.getDefaultInstance();
        }

        public static Builder newBuilder() {
            return Builder.access$4800();
        }

        public static Builder newBuilder(FieldDescriptorProto fieldDescriptorProto) {
            return newBuilder().mergeFrom(fieldDescriptorProto);
        }

        public static FieldDescriptorProto parseDelimitedFrom(InputStream inputStream) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static FieldDescriptorProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static FieldDescriptorProto parseFrom(ByteString byteString) {
            return ((Builder) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static FieldDescriptorProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static FieldDescriptorProto parseFrom(CodedInputStream codedInputStream) {
            return ((Builder) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static FieldDescriptorProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static FieldDescriptorProto parseFrom(InputStream inputStream) {
            return ((Builder) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static FieldDescriptorProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static FieldDescriptorProto parseFrom(byte[] bArr) {
            return ((Builder) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static FieldDescriptorProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public FieldDescriptorProto getDefaultInstanceForType() {
            return defaultInstance;
        }

        public String getDefaultValue() {
            return this.defaultValue_;
        }

        public String getExtendee() {
            return this.extendee_;
        }

        public Label getLabel() {
            return this.label_;
        }

        public String getName() {
            return this.name_;
        }

        public int getNumber() {
            return this.number_;
        }

        public FieldOptions getOptions() {
            return this.options_;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int b = hasName() ? 0 + CodedOutputStream.b(1, getName()) : 0;
            if (hasExtendee()) {
                b += CodedOutputStream.b(2, getExtendee());
            }
            if (hasNumber()) {
                b += CodedOutputStream.g(3, getNumber());
            }
            if (hasLabel()) {
                b += CodedOutputStream.j(4, getLabel().getNumber());
            }
            if (hasType()) {
                b += CodedOutputStream.j(5, getType().getNumber());
            }
            if (hasTypeName()) {
                b += CodedOutputStream.b(6, getTypeName());
            }
            if (hasDefaultValue()) {
                b += CodedOutputStream.b(7, getDefaultValue());
            }
            if (hasOptions()) {
                b += CodedOutputStream.g(8, getOptions());
            }
            int serializedSize = b + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public Type getType() {
            return this.type_;
        }

        public String getTypeName() {
            return this.typeName_;
        }

        public boolean hasDefaultValue() {
            return this.hasDefaultValue;
        }

        public boolean hasExtendee() {
            return this.hasExtendee;
        }

        public boolean hasLabel() {
            return this.hasLabel;
        }

        public boolean hasName() {
            return this.hasName;
        }

        public boolean hasNumber() {
            return this.hasNumber;
        }

        public boolean hasOptions() {
            return this.hasOptions;
        }

        public boolean hasType() {
            return this.hasType;
        }

        public boolean hasTypeName() {
            return this.hasTypeName;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.j;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            return !hasOptions() || getOptions().isInitialized();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            if (hasName()) {
                codedOutputStream.a(1, getName());
            }
            if (hasExtendee()) {
                codedOutputStream.a(2, getExtendee());
            }
            if (hasNumber()) {
                codedOutputStream.a(3, getNumber());
            }
            if (hasLabel()) {
                codedOutputStream.d(4, getLabel().getNumber());
            }
            if (hasType()) {
                codedOutputStream.d(5, getType().getNumber());
            }
            if (hasTypeName()) {
                codedOutputStream.a(6, getTypeName());
            }
            if (hasDefaultValue()) {
                codedOutputStream.a(7, getDefaultValue());
            }
            if (hasOptions()) {
                codedOutputStream.c(8, getOptions());
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class FieldOptions extends GeneratedMessage.ExtendableMessage<FieldOptions> {
        public static final int CTYPE_FIELD_NUMBER = 1;
        public static final int DEPRECATED_FIELD_NUMBER = 3;
        public static final int EXPERIMENTAL_MAP_KEY_FIELD_NUMBER = 9;
        public static final int PACKED_FIELD_NUMBER = 2;
        public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
        private static final FieldOptions defaultInstance = new FieldOptions(true);
        private CType ctype_;
        private boolean deprecated_;
        private String experimentalMapKey_;
        private boolean hasCtype;
        private boolean hasDeprecated;
        private boolean hasExperimentalMapKey;
        private boolean hasPacked;
        private int memoizedSerializedSize;
        private boolean packed_;
        private List<UninterpretedOption> uninterpretedOption_;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static final class Builder extends GeneratedMessage.ExtendableBuilder<FieldOptions, Builder> {
            private FieldOptions result;

            private Builder() {
            }

            static /* synthetic */ Builder access$14300() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public FieldOptions buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static Builder create() {
                Builder builder = new Builder();
                builder.result = new FieldOptions();
                return builder;
            }

            public Builder addAllUninterpretedOption(Iterable<? extends UninterpretedOption> iterable) {
                if (this.result.uninterpretedOption_.isEmpty()) {
                    this.result.uninterpretedOption_ = new ArrayList();
                }
                GeneratedMessage.ExtendableBuilder.addAll(iterable, this.result.uninterpretedOption_);
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption.Builder builder) {
                if (this.result.uninterpretedOption_.isEmpty()) {
                    this.result.uninterpretedOption_ = new ArrayList();
                }
                this.result.uninterpretedOption_.add(builder.build());
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption uninterpretedOption) {
                if (uninterpretedOption != null) {
                    if (this.result.uninterpretedOption_.isEmpty()) {
                        this.result.uninterpretedOption_ = new ArrayList();
                    }
                    this.result.uninterpretedOption_.add(uninterpretedOption);
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FieldOptions build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FieldOptions buildPartial() {
                FieldOptions fieldOptions = this.result;
                if (fieldOptions != null) {
                    if (fieldOptions.uninterpretedOption_ != Collections.EMPTY_LIST) {
                        FieldOptions fieldOptions2 = this.result;
                        fieldOptions2.uninterpretedOption_ = Collections.unmodifiableList(fieldOptions2.uninterpretedOption_);
                    }
                    FieldOptions fieldOptions3 = this.result;
                    this.result = null;
                    return fieldOptions3;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                if (this.result != null) {
                    this.result = new FieldOptions();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public Builder clearCtype() {
                this.result.hasCtype = false;
                this.result.ctype_ = CType.STRING;
                return this;
            }

            public Builder clearDeprecated() {
                this.result.hasDeprecated = false;
                this.result.deprecated_ = false;
                return this;
            }

            public Builder clearExperimentalMapKey() {
                this.result.hasExperimentalMapKey = false;
                this.result.experimentalMapKey_ = FieldOptions.getDefaultInstance().getExperimentalMapKey();
                return this;
            }

            public Builder clearPacked() {
                this.result.hasPacked = false;
                this.result.packed_ = false;
                return this;
            }

            public Builder clearUninterpretedOption() {
                this.result.uninterpretedOption_ = Collections.emptyList();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.ExtendableBuilder, com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3clone() {
                return create().mergeFrom(this.result);
            }

            public CType getCtype() {
                return this.result.getCtype();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FieldOptions getDefaultInstanceForType() {
                return FieldOptions.getDefaultInstance();
            }

            public boolean getDeprecated() {
                return this.result.getDeprecated();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return FieldOptions.getDescriptor();
            }

            public String getExperimentalMapKey() {
                return this.result.getExperimentalMapKey();
            }

            public boolean getPacked() {
                return this.result.getPacked();
            }

            public UninterpretedOption getUninterpretedOption(int i) {
                return this.result.getUninterpretedOption(i);
            }

            public int getUninterpretedOptionCount() {
                return this.result.getUninterpretedOptionCount();
            }

            public List<UninterpretedOption> getUninterpretedOptionList() {
                return Collections.unmodifiableList(this.result.uninterpretedOption_);
            }

            public boolean hasCtype() {
                return this.result.hasCtype();
            }

            public boolean hasDeprecated() {
                return this.result.hasDeprecated();
            }

            public boolean hasExperimentalMapKey() {
                return this.result.hasExperimentalMapKey();
            }

            public boolean hasPacked() {
                return this.result.hasPacked();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.ExtendableBuilder, com.google.protobuf.GeneratedMessage.Builder
            public FieldOptions internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 8) {
                        int n = codedInputStream.n();
                        CType valueOf = CType.valueOf(n);
                        if (valueOf == null) {
                            newBuilder.mergeVarintField(1, n);
                        } else {
                            setCtype(valueOf);
                        }
                    } else if (a == 16) {
                        setPacked(codedInputStream.j());
                    } else if (a == 24) {
                        setDeprecated(codedInputStream.j());
                    } else if (a == 74) {
                        setExperimentalMapKey(codedInputStream.k());
                    } else if (a == 7994) {
                        UninterpretedOption.Builder newBuilder2 = UninterpretedOption.newBuilder();
                        codedInputStream.a(newBuilder2, extensionRegistryLite);
                        addUninterpretedOption(newBuilder2.buildPartial());
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            public Builder mergeFrom(FieldOptions fieldOptions) {
                if (fieldOptions == FieldOptions.getDefaultInstance()) {
                    return this;
                }
                if (fieldOptions.hasCtype()) {
                    setCtype(fieldOptions.getCtype());
                }
                if (fieldOptions.hasPacked()) {
                    setPacked(fieldOptions.getPacked());
                }
                if (fieldOptions.hasDeprecated()) {
                    setDeprecated(fieldOptions.getDeprecated());
                }
                if (fieldOptions.hasExperimentalMapKey()) {
                    setExperimentalMapKey(fieldOptions.getExperimentalMapKey());
                }
                if (!fieldOptions.uninterpretedOption_.isEmpty()) {
                    if (this.result.uninterpretedOption_.isEmpty()) {
                        this.result.uninterpretedOption_ = new ArrayList();
                    }
                    this.result.uninterpretedOption_.addAll(fieldOptions.uninterpretedOption_);
                }
                mergeExtensionFields(fieldOptions);
                mergeUnknownFields(fieldOptions.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof FieldOptions) {
                    return mergeFrom((FieldOptions) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder setCtype(CType cType) {
                if (cType != null) {
                    this.result.hasCtype = true;
                    this.result.ctype_ = cType;
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setDeprecated(boolean z) {
                this.result.hasDeprecated = true;
                this.result.deprecated_ = z;
                return this;
            }

            public Builder setExperimentalMapKey(String str) {
                if (str != null) {
                    this.result.hasExperimentalMapKey = true;
                    this.result.experimentalMapKey_ = str;
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setPacked(boolean z) {
                this.result.hasPacked = true;
                this.result.packed_ = z;
                return this;
            }

            public Builder setUninterpretedOption(int i, UninterpretedOption.Builder builder) {
                this.result.uninterpretedOption_.set(i, builder.build());
                return this;
            }

            public Builder setUninterpretedOption(int i, UninterpretedOption uninterpretedOption) {
                if (uninterpretedOption != null) {
                    this.result.uninterpretedOption_.set(i, uninterpretedOption);
                    return this;
                }
                throw new NullPointerException();
            }
        }

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public enum CType implements ProtocolMessageEnum {
            STRING(0, 0),
            CORD(1, 1),
            STRING_PIECE(2, 2);
            
            private final int index;
            private final int value;
            private static Internal.EnumLiteMap<CType> internalValueMap = new Internal.EnumLiteMap<CType>() { // from class: com.google.protobuf.DescriptorProtos.FieldOptions.CType.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public CType findValueByNumber(int i) {
                    return CType.valueOf(i);
                }
            };
            private static final CType[] VALUES = {STRING, CORD, STRING_PIECE};

            static {
                DescriptorProtos.a();
            }

            CType(int i, int i2) {
                this.index = i;
                this.value = i2;
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return FieldOptions.getDescriptor().getEnumTypes().get(0);
            }

            public static Internal.EnumLiteMap<CType> internalGetValueMap() {
                return internalValueMap;
            }

            public static CType valueOf(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            return null;
                        }
                        return STRING_PIECE;
                    }
                    return CORD;
                }
                return STRING;
            }

            public static CType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() == getDescriptor()) {
                    return VALUES[enumValueDescriptor.getIndex()];
                }
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return getDescriptor().getValues().get(this.index);
            }
        }

        static {
            DescriptorProtos.b();
            defaultInstance.initFields();
        }

        private FieldOptions() {
            this.packed_ = false;
            this.deprecated_ = false;
            this.experimentalMapKey_ = a.d;
            this.uninterpretedOption_ = Collections.emptyList();
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private FieldOptions(boolean z) {
            this.packed_ = false;
            this.deprecated_ = false;
            this.experimentalMapKey_ = a.d;
            this.uninterpretedOption_ = Collections.emptyList();
            this.memoizedSerializedSize = -1;
        }

        public static FieldOptions getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.w;
        }

        private void initFields() {
            this.ctype_ = CType.STRING;
        }

        public static Builder newBuilder() {
            return Builder.access$14300();
        }

        public static Builder newBuilder(FieldOptions fieldOptions) {
            return newBuilder().mergeFrom(fieldOptions);
        }

        public static FieldOptions parseDelimitedFrom(InputStream inputStream) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static FieldOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static FieldOptions parseFrom(ByteString byteString) {
            return ((Builder) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static FieldOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static FieldOptions parseFrom(CodedInputStream codedInputStream) {
            return ((Builder) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static FieldOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static FieldOptions parseFrom(InputStream inputStream) {
            return ((Builder) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static FieldOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static FieldOptions parseFrom(byte[] bArr) {
            return ((Builder) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static FieldOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        public CType getCtype() {
            return this.ctype_;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public FieldOptions getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean getDeprecated() {
            return this.deprecated_;
        }

        public String getExperimentalMapKey() {
            return this.experimentalMapKey_;
        }

        public boolean getPacked() {
            return this.packed_;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int j = hasCtype() ? 0 + CodedOutputStream.j(1, getCtype().getNumber()) : 0;
            if (hasPacked()) {
                j += CodedOutputStream.b(2, getPacked());
            }
            if (hasDeprecated()) {
                j += CodedOutputStream.b(3, getDeprecated());
            }
            if (hasExperimentalMapKey()) {
                j += CodedOutputStream.b(9, getExperimentalMapKey());
            }
            for (UninterpretedOption uninterpretedOption : getUninterpretedOptionList()) {
                j += CodedOutputStream.g(999, uninterpretedOption);
            }
            int extensionsSerializedSize = j + extensionsSerializedSize() + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = extensionsSerializedSize;
            return extensionsSerializedSize;
        }

        public UninterpretedOption getUninterpretedOption(int i) {
            return this.uninterpretedOption_.get(i);
        }

        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        public List<UninterpretedOption> getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        public boolean hasCtype() {
            return this.hasCtype;
        }

        public boolean hasDeprecated() {
            return this.hasDeprecated;
        }

        public boolean hasExperimentalMapKey() {
            return this.hasExperimentalMapKey;
        }

        public boolean hasPacked() {
            return this.hasPacked;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.x;
        }

        @Override // com.google.protobuf.GeneratedMessage.ExtendableMessage, com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            for (UninterpretedOption uninterpretedOption : getUninterpretedOptionList()) {
                if (!uninterpretedOption.isInitialized()) {
                    return false;
                }
            }
            return extensionsAreInitialized();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            GeneratedMessage.ExtendableMessage<MessageType>.ExtensionWriter newExtensionWriter = newExtensionWriter();
            if (hasCtype()) {
                codedOutputStream.d(1, getCtype().getNumber());
            }
            if (hasPacked()) {
                codedOutputStream.a(2, getPacked());
            }
            if (hasDeprecated()) {
                codedOutputStream.a(3, getDeprecated());
            }
            if (hasExperimentalMapKey()) {
                codedOutputStream.a(9, getExperimentalMapKey());
            }
            for (UninterpretedOption uninterpretedOption : getUninterpretedOptionList()) {
                codedOutputStream.c(999, uninterpretedOption);
            }
            newExtensionWriter.a(536870912, codedOutputStream);
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class FileDescriptorProto extends GeneratedMessage {
        public static final int DEPENDENCY_FIELD_NUMBER = 3;
        public static final int ENUM_TYPE_FIELD_NUMBER = 5;
        public static final int EXTENSION_FIELD_NUMBER = 7;
        public static final int MESSAGE_TYPE_FIELD_NUMBER = 4;
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int OPTIONS_FIELD_NUMBER = 8;
        public static final int PACKAGE_FIELD_NUMBER = 2;
        public static final int SERVICE_FIELD_NUMBER = 6;
        private static final FileDescriptorProto defaultInstance = new FileDescriptorProto(true);
        private List<String> dependency_;
        private List<EnumDescriptorProto> enumType_;
        private List<FieldDescriptorProto> extension_;
        private boolean hasName;
        private boolean hasOptions;
        private boolean hasPackage;
        private int memoizedSerializedSize;
        private List<DescriptorProto> messageType_;
        private String name_;
        private FileOptions options_;
        private String package_;
        private List<ServiceDescriptorProto> service_;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static final class Builder extends GeneratedMessage.Builder<Builder> {
            private FileDescriptorProto result;

            private Builder() {
            }

            static /* synthetic */ Builder access$900() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public FileDescriptorProto buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static Builder create() {
                Builder builder = new Builder();
                builder.result = new FileDescriptorProto();
                return builder;
            }

            public Builder addAllDependency(Iterable<? extends String> iterable) {
                if (this.result.dependency_.isEmpty()) {
                    this.result.dependency_ = new ArrayList();
                }
                GeneratedMessage.Builder.addAll(iterable, this.result.dependency_);
                return this;
            }

            public Builder addAllEnumType(Iterable<? extends EnumDescriptorProto> iterable) {
                if (this.result.enumType_.isEmpty()) {
                    this.result.enumType_ = new ArrayList();
                }
                GeneratedMessage.Builder.addAll(iterable, this.result.enumType_);
                return this;
            }

            public Builder addAllExtension(Iterable<? extends FieldDescriptorProto> iterable) {
                if (this.result.extension_.isEmpty()) {
                    this.result.extension_ = new ArrayList();
                }
                GeneratedMessage.Builder.addAll(iterable, this.result.extension_);
                return this;
            }

            public Builder addAllMessageType(Iterable<? extends DescriptorProto> iterable) {
                if (this.result.messageType_.isEmpty()) {
                    this.result.messageType_ = new ArrayList();
                }
                GeneratedMessage.Builder.addAll(iterable, this.result.messageType_);
                return this;
            }

            public Builder addAllService(Iterable<? extends ServiceDescriptorProto> iterable) {
                if (this.result.service_.isEmpty()) {
                    this.result.service_ = new ArrayList();
                }
                GeneratedMessage.Builder.addAll(iterable, this.result.service_);
                return this;
            }

            public Builder addDependency(String str) {
                if (str != null) {
                    if (this.result.dependency_.isEmpty()) {
                        this.result.dependency_ = new ArrayList();
                    }
                    this.result.dependency_.add(str);
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder addEnumType(EnumDescriptorProto.Builder builder) {
                if (this.result.enumType_.isEmpty()) {
                    this.result.enumType_ = new ArrayList();
                }
                this.result.enumType_.add(builder.build());
                return this;
            }

            public Builder addEnumType(EnumDescriptorProto enumDescriptorProto) {
                if (enumDescriptorProto != null) {
                    if (this.result.enumType_.isEmpty()) {
                        this.result.enumType_ = new ArrayList();
                    }
                    this.result.enumType_.add(enumDescriptorProto);
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder addExtension(FieldDescriptorProto.Builder builder) {
                if (this.result.extension_.isEmpty()) {
                    this.result.extension_ = new ArrayList();
                }
                this.result.extension_.add(builder.build());
                return this;
            }

            public Builder addExtension(FieldDescriptorProto fieldDescriptorProto) {
                if (fieldDescriptorProto != null) {
                    if (this.result.extension_.isEmpty()) {
                        this.result.extension_ = new ArrayList();
                    }
                    this.result.extension_.add(fieldDescriptorProto);
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder addMessageType(DescriptorProto.Builder builder) {
                if (this.result.messageType_.isEmpty()) {
                    this.result.messageType_ = new ArrayList();
                }
                this.result.messageType_.add(builder.build());
                return this;
            }

            public Builder addMessageType(DescriptorProto descriptorProto) {
                if (descriptorProto != null) {
                    if (this.result.messageType_.isEmpty()) {
                        this.result.messageType_ = new ArrayList();
                    }
                    this.result.messageType_.add(descriptorProto);
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder addService(ServiceDescriptorProto.Builder builder) {
                if (this.result.service_.isEmpty()) {
                    this.result.service_ = new ArrayList();
                }
                this.result.service_.add(builder.build());
                return this;
            }

            public Builder addService(ServiceDescriptorProto serviceDescriptorProto) {
                if (serviceDescriptorProto != null) {
                    if (this.result.service_.isEmpty()) {
                        this.result.service_ = new ArrayList();
                    }
                    this.result.service_.add(serviceDescriptorProto);
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FileDescriptorProto build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FileDescriptorProto buildPartial() {
                FileDescriptorProto fileDescriptorProto = this.result;
                if (fileDescriptorProto != null) {
                    if (fileDescriptorProto.dependency_ != Collections.EMPTY_LIST) {
                        FileDescriptorProto fileDescriptorProto2 = this.result;
                        fileDescriptorProto2.dependency_ = Collections.unmodifiableList(fileDescriptorProto2.dependency_);
                    }
                    if (this.result.messageType_ != Collections.EMPTY_LIST) {
                        FileDescriptorProto fileDescriptorProto3 = this.result;
                        fileDescriptorProto3.messageType_ = Collections.unmodifiableList(fileDescriptorProto3.messageType_);
                    }
                    if (this.result.enumType_ != Collections.EMPTY_LIST) {
                        FileDescriptorProto fileDescriptorProto4 = this.result;
                        fileDescriptorProto4.enumType_ = Collections.unmodifiableList(fileDescriptorProto4.enumType_);
                    }
                    if (this.result.service_ != Collections.EMPTY_LIST) {
                        FileDescriptorProto fileDescriptorProto5 = this.result;
                        fileDescriptorProto5.service_ = Collections.unmodifiableList(fileDescriptorProto5.service_);
                    }
                    if (this.result.extension_ != Collections.EMPTY_LIST) {
                        FileDescriptorProto fileDescriptorProto6 = this.result;
                        fileDescriptorProto6.extension_ = Collections.unmodifiableList(fileDescriptorProto6.extension_);
                    }
                    FileDescriptorProto fileDescriptorProto7 = this.result;
                    this.result = null;
                    return fileDescriptorProto7;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                if (this.result != null) {
                    this.result = new FileDescriptorProto();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public Builder clearDependency() {
                this.result.dependency_ = Collections.emptyList();
                return this;
            }

            public Builder clearEnumType() {
                this.result.enumType_ = Collections.emptyList();
                return this;
            }

            public Builder clearExtension() {
                this.result.extension_ = Collections.emptyList();
                return this;
            }

            public Builder clearMessageType() {
                this.result.messageType_ = Collections.emptyList();
                return this;
            }

            public Builder clearName() {
                this.result.hasName = false;
                this.result.name_ = FileDescriptorProto.getDefaultInstance().getName();
                return this;
            }

            public Builder clearOptions() {
                this.result.hasOptions = false;
                this.result.options_ = FileOptions.getDefaultInstance();
                return this;
            }

            public Builder clearPackage() {
                this.result.hasPackage = false;
                this.result.package_ = FileDescriptorProto.getDefaultInstance().getPackage();
                return this;
            }

            public Builder clearService() {
                this.result.service_ = Collections.emptyList();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3clone() {
                return create().mergeFrom(this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FileDescriptorProto getDefaultInstanceForType() {
                return FileDescriptorProto.getDefaultInstance();
            }

            public String getDependency(int i) {
                return this.result.getDependency(i);
            }

            public int getDependencyCount() {
                return this.result.getDependencyCount();
            }

            public List<String> getDependencyList() {
                return Collections.unmodifiableList(this.result.dependency_);
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return FileDescriptorProto.getDescriptor();
            }

            public EnumDescriptorProto getEnumType(int i) {
                return this.result.getEnumType(i);
            }

            public int getEnumTypeCount() {
                return this.result.getEnumTypeCount();
            }

            public List<EnumDescriptorProto> getEnumTypeList() {
                return Collections.unmodifiableList(this.result.enumType_);
            }

            public FieldDescriptorProto getExtension(int i) {
                return this.result.getExtension(i);
            }

            public int getExtensionCount() {
                return this.result.getExtensionCount();
            }

            public List<FieldDescriptorProto> getExtensionList() {
                return Collections.unmodifiableList(this.result.extension_);
            }

            public DescriptorProto getMessageType(int i) {
                return this.result.getMessageType(i);
            }

            public int getMessageTypeCount() {
                return this.result.getMessageTypeCount();
            }

            public List<DescriptorProto> getMessageTypeList() {
                return Collections.unmodifiableList(this.result.messageType_);
            }

            public String getName() {
                return this.result.getName();
            }

            public FileOptions getOptions() {
                return this.result.getOptions();
            }

            public String getPackage() {
                return this.result.getPackage();
            }

            public ServiceDescriptorProto getService(int i) {
                return this.result.getService(i);
            }

            public int getServiceCount() {
                return this.result.getServiceCount();
            }

            public List<ServiceDescriptorProto> getServiceList() {
                return Collections.unmodifiableList(this.result.service_);
            }

            public boolean hasName() {
                return this.result.hasName();
            }

            public boolean hasOptions() {
                return this.result.hasOptions();
            }

            public boolean hasPackage() {
                return this.result.hasPackage();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.Builder
            public FileDescriptorProto internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 10) {
                        setName(codedInputStream.k());
                    } else if (a == 18) {
                        setPackage(codedInputStream.k());
                    } else if (a == 26) {
                        addDependency(codedInputStream.k());
                    } else if (a == 34) {
                        MessageLite.Builder newBuilder2 = DescriptorProto.newBuilder();
                        codedInputStream.a(newBuilder2, extensionRegistryLite);
                        addMessageType(newBuilder2.buildPartial());
                    } else if (a == 42) {
                        MessageLite.Builder newBuilder3 = EnumDescriptorProto.newBuilder();
                        codedInputStream.a(newBuilder3, extensionRegistryLite);
                        addEnumType(newBuilder3.buildPartial());
                    } else if (a == 50) {
                        MessageLite.Builder newBuilder4 = ServiceDescriptorProto.newBuilder();
                        codedInputStream.a(newBuilder4, extensionRegistryLite);
                        addService(newBuilder4.buildPartial());
                    } else if (a == 58) {
                        MessageLite.Builder newBuilder5 = FieldDescriptorProto.newBuilder();
                        codedInputStream.a(newBuilder5, extensionRegistryLite);
                        addExtension(newBuilder5.buildPartial());
                    } else if (a == 66) {
                        FileOptions.Builder newBuilder6 = FileOptions.newBuilder();
                        if (hasOptions()) {
                            newBuilder6.mergeFrom(getOptions());
                        }
                        codedInputStream.a(newBuilder6, extensionRegistryLite);
                        setOptions(newBuilder6.buildPartial());
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            public Builder mergeFrom(FileDescriptorProto fileDescriptorProto) {
                if (fileDescriptorProto == FileDescriptorProto.getDefaultInstance()) {
                    return this;
                }
                if (fileDescriptorProto.hasName()) {
                    setName(fileDescriptorProto.getName());
                }
                if (fileDescriptorProto.hasPackage()) {
                    setPackage(fileDescriptorProto.getPackage());
                }
                if (!fileDescriptorProto.dependency_.isEmpty()) {
                    if (this.result.dependency_.isEmpty()) {
                        this.result.dependency_ = new ArrayList();
                    }
                    this.result.dependency_.addAll(fileDescriptorProto.dependency_);
                }
                if (!fileDescriptorProto.messageType_.isEmpty()) {
                    if (this.result.messageType_.isEmpty()) {
                        this.result.messageType_ = new ArrayList();
                    }
                    this.result.messageType_.addAll(fileDescriptorProto.messageType_);
                }
                if (!fileDescriptorProto.enumType_.isEmpty()) {
                    if (this.result.enumType_.isEmpty()) {
                        this.result.enumType_ = new ArrayList();
                    }
                    this.result.enumType_.addAll(fileDescriptorProto.enumType_);
                }
                if (!fileDescriptorProto.service_.isEmpty()) {
                    if (this.result.service_.isEmpty()) {
                        this.result.service_ = new ArrayList();
                    }
                    this.result.service_.addAll(fileDescriptorProto.service_);
                }
                if (!fileDescriptorProto.extension_.isEmpty()) {
                    if (this.result.extension_.isEmpty()) {
                        this.result.extension_ = new ArrayList();
                    }
                    this.result.extension_.addAll(fileDescriptorProto.extension_);
                }
                if (fileDescriptorProto.hasOptions()) {
                    mergeOptions(fileDescriptorProto.getOptions());
                }
                mergeUnknownFields(fileDescriptorProto.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof FileDescriptorProto) {
                    return mergeFrom((FileDescriptorProto) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeOptions(FileOptions fileOptions) {
                FileDescriptorProto fileDescriptorProto;
                if (!this.result.hasOptions() || this.result.options_ == FileOptions.getDefaultInstance()) {
                    fileDescriptorProto = this.result;
                } else {
                    fileDescriptorProto = this.result;
                    fileOptions = FileOptions.newBuilder(fileDescriptorProto.options_).mergeFrom(fileOptions).buildPartial();
                }
                fileDescriptorProto.options_ = fileOptions;
                this.result.hasOptions = true;
                return this;
            }

            public Builder setDependency(int i, String str) {
                if (str != null) {
                    this.result.dependency_.set(i, str);
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setEnumType(int i, EnumDescriptorProto.Builder builder) {
                this.result.enumType_.set(i, builder.build());
                return this;
            }

            public Builder setEnumType(int i, EnumDescriptorProto enumDescriptorProto) {
                if (enumDescriptorProto != null) {
                    this.result.enumType_.set(i, enumDescriptorProto);
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setExtension(int i, FieldDescriptorProto.Builder builder) {
                this.result.extension_.set(i, builder.build());
                return this;
            }

            public Builder setExtension(int i, FieldDescriptorProto fieldDescriptorProto) {
                if (fieldDescriptorProto != null) {
                    this.result.extension_.set(i, fieldDescriptorProto);
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setMessageType(int i, DescriptorProto.Builder builder) {
                this.result.messageType_.set(i, builder.build());
                return this;
            }

            public Builder setMessageType(int i, DescriptorProto descriptorProto) {
                if (descriptorProto != null) {
                    this.result.messageType_.set(i, descriptorProto);
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setName(String str) {
                if (str != null) {
                    this.result.hasName = true;
                    this.result.name_ = str;
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setOptions(FileOptions.Builder builder) {
                this.result.hasOptions = true;
                this.result.options_ = builder.build();
                return this;
            }

            public Builder setOptions(FileOptions fileOptions) {
                if (fileOptions != null) {
                    this.result.hasOptions = true;
                    this.result.options_ = fileOptions;
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setPackage(String str) {
                if (str != null) {
                    this.result.hasPackage = true;
                    this.result.package_ = str;
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setService(int i, ServiceDescriptorProto.Builder builder) {
                this.result.service_.set(i, builder.build());
                return this;
            }

            public Builder setService(int i, ServiceDescriptorProto serviceDescriptorProto) {
                if (serviceDescriptorProto != null) {
                    this.result.service_.set(i, serviceDescriptorProto);
                    return this;
                }
                throw new NullPointerException();
            }
        }

        static {
            DescriptorProtos.b();
            defaultInstance.initFields();
        }

        private FileDescriptorProto() {
            this.name_ = a.d;
            this.package_ = a.d;
            this.dependency_ = Collections.emptyList();
            this.messageType_ = Collections.emptyList();
            this.enumType_ = Collections.emptyList();
            this.service_ = Collections.emptyList();
            this.extension_ = Collections.emptyList();
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private FileDescriptorProto(boolean z) {
            this.name_ = a.d;
            this.package_ = a.d;
            this.dependency_ = Collections.emptyList();
            this.messageType_ = Collections.emptyList();
            this.enumType_ = Collections.emptyList();
            this.service_ = Collections.emptyList();
            this.extension_ = Collections.emptyList();
            this.memoizedSerializedSize = -1;
        }

        public static FileDescriptorProto getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.c;
        }

        private void initFields() {
            this.options_ = FileOptions.getDefaultInstance();
        }

        public static Builder newBuilder() {
            return Builder.access$900();
        }

        public static Builder newBuilder(FileDescriptorProto fileDescriptorProto) {
            return newBuilder().mergeFrom(fileDescriptorProto);
        }

        public static FileDescriptorProto parseDelimitedFrom(InputStream inputStream) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static FileDescriptorProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static FileDescriptorProto parseFrom(ByteString byteString) {
            return ((Builder) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static FileDescriptorProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static FileDescriptorProto parseFrom(CodedInputStream codedInputStream) {
            return ((Builder) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static FileDescriptorProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static FileDescriptorProto parseFrom(InputStream inputStream) {
            return ((Builder) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static FileDescriptorProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static FileDescriptorProto parseFrom(byte[] bArr) {
            return ((Builder) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static FileDescriptorProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public FileDescriptorProto getDefaultInstanceForType() {
            return defaultInstance;
        }

        public String getDependency(int i) {
            return this.dependency_.get(i);
        }

        public int getDependencyCount() {
            return this.dependency_.size();
        }

        public List<String> getDependencyList() {
            return this.dependency_;
        }

        public EnumDescriptorProto getEnumType(int i) {
            return this.enumType_.get(i);
        }

        public int getEnumTypeCount() {
            return this.enumType_.size();
        }

        public List<EnumDescriptorProto> getEnumTypeList() {
            return this.enumType_;
        }

        public FieldDescriptorProto getExtension(int i) {
            return this.extension_.get(i);
        }

        public int getExtensionCount() {
            return this.extension_.size();
        }

        public List<FieldDescriptorProto> getExtensionList() {
            return this.extension_;
        }

        public DescriptorProto getMessageType(int i) {
            return this.messageType_.get(i);
        }

        public int getMessageTypeCount() {
            return this.messageType_.size();
        }

        public List<DescriptorProto> getMessageTypeList() {
            return this.messageType_;
        }

        public String getName() {
            return this.name_;
        }

        public FileOptions getOptions() {
            return this.options_;
        }

        public String getPackage() {
            return this.package_;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int b = hasName() ? CodedOutputStream.b(1, getName()) + 0 : 0;
            if (hasPackage()) {
                b += CodedOutputStream.b(2, getPackage());
            }
            for (String str : getDependencyList()) {
                i2 += CodedOutputStream.b(str);
            }
            int size = b + i2 + (getDependencyList().size() * 1);
            for (DescriptorProto descriptorProto : getMessageTypeList()) {
                size += CodedOutputStream.g(4, descriptorProto);
            }
            for (EnumDescriptorProto enumDescriptorProto : getEnumTypeList()) {
                size += CodedOutputStream.g(5, enumDescriptorProto);
            }
            for (ServiceDescriptorProto serviceDescriptorProto : getServiceList()) {
                size += CodedOutputStream.g(6, serviceDescriptorProto);
            }
            for (FieldDescriptorProto fieldDescriptorProto : getExtensionList()) {
                size += CodedOutputStream.g(7, fieldDescriptorProto);
            }
            if (hasOptions()) {
                size += CodedOutputStream.g(8, getOptions());
            }
            int serializedSize = size + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public ServiceDescriptorProto getService(int i) {
            return this.service_.get(i);
        }

        public int getServiceCount() {
            return this.service_.size();
        }

        public List<ServiceDescriptorProto> getServiceList() {
            return this.service_;
        }

        public boolean hasName() {
            return this.hasName;
        }

        public boolean hasOptions() {
            return this.hasOptions;
        }

        public boolean hasPackage() {
            return this.hasPackage;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.d;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            for (DescriptorProto descriptorProto : getMessageTypeList()) {
                if (!descriptorProto.isInitialized()) {
                    return false;
                }
            }
            for (EnumDescriptorProto enumDescriptorProto : getEnumTypeList()) {
                if (!enumDescriptorProto.isInitialized()) {
                    return false;
                }
            }
            for (ServiceDescriptorProto serviceDescriptorProto : getServiceList()) {
                if (!serviceDescriptorProto.isInitialized()) {
                    return false;
                }
            }
            for (FieldDescriptorProto fieldDescriptorProto : getExtensionList()) {
                if (!fieldDescriptorProto.isInitialized()) {
                    return false;
                }
            }
            return !hasOptions() || getOptions().isInitialized();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            if (hasName()) {
                codedOutputStream.a(1, getName());
            }
            if (hasPackage()) {
                codedOutputStream.a(2, getPackage());
            }
            for (String str : getDependencyList()) {
                codedOutputStream.a(3, str);
            }
            for (DescriptorProto descriptorProto : getMessageTypeList()) {
                codedOutputStream.c(4, descriptorProto);
            }
            for (EnumDescriptorProto enumDescriptorProto : getEnumTypeList()) {
                codedOutputStream.c(5, enumDescriptorProto);
            }
            for (ServiceDescriptorProto serviceDescriptorProto : getServiceList()) {
                codedOutputStream.c(6, serviceDescriptorProto);
            }
            for (FieldDescriptorProto fieldDescriptorProto : getExtensionList()) {
                codedOutputStream.c(7, fieldDescriptorProto);
            }
            if (hasOptions()) {
                codedOutputStream.c(8, getOptions());
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class FileDescriptorSet extends GeneratedMessage {
        public static final int FILE_FIELD_NUMBER = 1;
        private static final FileDescriptorSet defaultInstance = new FileDescriptorSet(true);
        private List<FileDescriptorProto> file_;
        private int memoizedSerializedSize;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static final class Builder extends GeneratedMessage.Builder<Builder> {
            private FileDescriptorSet result;

            private Builder() {
            }

            static /* synthetic */ Builder access$300() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public FileDescriptorSet buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static Builder create() {
                Builder builder = new Builder();
                builder.result = new FileDescriptorSet();
                return builder;
            }

            public Builder addAllFile(Iterable<? extends FileDescriptorProto> iterable) {
                if (this.result.file_.isEmpty()) {
                    this.result.file_ = new ArrayList();
                }
                GeneratedMessage.Builder.addAll(iterable, this.result.file_);
                return this;
            }

            public Builder addFile(FileDescriptorProto.Builder builder) {
                if (this.result.file_.isEmpty()) {
                    this.result.file_ = new ArrayList();
                }
                this.result.file_.add(builder.build());
                return this;
            }

            public Builder addFile(FileDescriptorProto fileDescriptorProto) {
                if (fileDescriptorProto != null) {
                    if (this.result.file_.isEmpty()) {
                        this.result.file_ = new ArrayList();
                    }
                    this.result.file_.add(fileDescriptorProto);
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FileDescriptorSet build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FileDescriptorSet buildPartial() {
                FileDescriptorSet fileDescriptorSet = this.result;
                if (fileDescriptorSet != null) {
                    if (fileDescriptorSet.file_ != Collections.EMPTY_LIST) {
                        FileDescriptorSet fileDescriptorSet2 = this.result;
                        fileDescriptorSet2.file_ = Collections.unmodifiableList(fileDescriptorSet2.file_);
                    }
                    FileDescriptorSet fileDescriptorSet3 = this.result;
                    this.result = null;
                    return fileDescriptorSet3;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                if (this.result != null) {
                    this.result = new FileDescriptorSet();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public Builder clearFile() {
                this.result.file_ = Collections.emptyList();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3clone() {
                return create().mergeFrom(this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FileDescriptorSet getDefaultInstanceForType() {
                return FileDescriptorSet.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return FileDescriptorSet.getDescriptor();
            }

            public FileDescriptorProto getFile(int i) {
                return this.result.getFile(i);
            }

            public int getFileCount() {
                return this.result.getFileCount();
            }

            public List<FileDescriptorProto> getFileList() {
                return Collections.unmodifiableList(this.result.file_);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.Builder
            public FileDescriptorSet internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 10) {
                        FileDescriptorProto.Builder newBuilder2 = FileDescriptorProto.newBuilder();
                        codedInputStream.a(newBuilder2, extensionRegistryLite);
                        addFile(newBuilder2.buildPartial());
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            public Builder mergeFrom(FileDescriptorSet fileDescriptorSet) {
                if (fileDescriptorSet == FileDescriptorSet.getDefaultInstance()) {
                    return this;
                }
                if (!fileDescriptorSet.file_.isEmpty()) {
                    if (this.result.file_.isEmpty()) {
                        this.result.file_ = new ArrayList();
                    }
                    this.result.file_.addAll(fileDescriptorSet.file_);
                }
                mergeUnknownFields(fileDescriptorSet.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof FileDescriptorSet) {
                    return mergeFrom((FileDescriptorSet) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder setFile(int i, FileDescriptorProto.Builder builder) {
                this.result.file_.set(i, builder.build());
                return this;
            }

            public Builder setFile(int i, FileDescriptorProto fileDescriptorProto) {
                if (fileDescriptorProto != null) {
                    this.result.file_.set(i, fileDescriptorProto);
                    return this;
                }
                throw new NullPointerException();
            }
        }

        static {
            DescriptorProtos.b();
            defaultInstance.initFields();
        }

        private FileDescriptorSet() {
            this.file_ = Collections.emptyList();
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private FileDescriptorSet(boolean z) {
            this.file_ = Collections.emptyList();
            this.memoizedSerializedSize = -1;
        }

        public static FileDescriptorSet getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.a;
        }

        private void initFields() {
        }

        public static Builder newBuilder() {
            return Builder.access$300();
        }

        public static Builder newBuilder(FileDescriptorSet fileDescriptorSet) {
            return newBuilder().mergeFrom(fileDescriptorSet);
        }

        public static FileDescriptorSet parseDelimitedFrom(InputStream inputStream) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static FileDescriptorSet parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static FileDescriptorSet parseFrom(ByteString byteString) {
            return ((Builder) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static FileDescriptorSet parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static FileDescriptorSet parseFrom(CodedInputStream codedInputStream) {
            return ((Builder) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static FileDescriptorSet parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static FileDescriptorSet parseFrom(InputStream inputStream) {
            return ((Builder) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static FileDescriptorSet parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static FileDescriptorSet parseFrom(byte[] bArr) {
            return ((Builder) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static FileDescriptorSet parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public FileDescriptorSet getDefaultInstanceForType() {
            return defaultInstance;
        }

        public FileDescriptorProto getFile(int i) {
            return this.file_.get(i);
        }

        public int getFileCount() {
            return this.file_.size();
        }

        public List<FileDescriptorProto> getFileList() {
            return this.file_;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (FileDescriptorProto fileDescriptorProto : getFileList()) {
                i2 += CodedOutputStream.g(1, fileDescriptorProto);
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.b;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            for (FileDescriptorProto fileDescriptorProto : getFileList()) {
                if (!fileDescriptorProto.isInitialized()) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            for (FileDescriptorProto fileDescriptorProto : getFileList()) {
                codedOutputStream.c(1, fileDescriptorProto);
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class FileOptions extends GeneratedMessage.ExtendableMessage<FileOptions> {
        public static final int CC_GENERIC_SERVICES_FIELD_NUMBER = 16;
        public static final int JAVA_GENERIC_SERVICES_FIELD_NUMBER = 17;
        public static final int JAVA_MULTIPLE_FILES_FIELD_NUMBER = 10;
        public static final int JAVA_OUTER_CLASSNAME_FIELD_NUMBER = 8;
        public static final int JAVA_PACKAGE_FIELD_NUMBER = 1;
        public static final int OPTIMIZE_FOR_FIELD_NUMBER = 9;
        public static final int PY_GENERIC_SERVICES_FIELD_NUMBER = 18;
        public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
        private static final FileOptions defaultInstance = new FileOptions(true);
        private boolean ccGenericServices_;
        private boolean hasCcGenericServices;
        private boolean hasJavaGenericServices;
        private boolean hasJavaMultipleFiles;
        private boolean hasJavaOuterClassname;
        private boolean hasJavaPackage;
        private boolean hasOptimizeFor;
        private boolean hasPyGenericServices;
        private boolean javaGenericServices_;
        private boolean javaMultipleFiles_;
        private String javaOuterClassname_;
        private String javaPackage_;
        private int memoizedSerializedSize;
        private OptimizeMode optimizeFor_;
        private boolean pyGenericServices_;
        private List<UninterpretedOption> uninterpretedOption_;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static final class Builder extends GeneratedMessage.ExtendableBuilder<FileOptions, Builder> {
            private FileOptions result;

            private Builder() {
            }

            static /* synthetic */ Builder access$11300() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public FileOptions buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static Builder create() {
                Builder builder = new Builder();
                builder.result = new FileOptions();
                return builder;
            }

            public Builder addAllUninterpretedOption(Iterable<? extends UninterpretedOption> iterable) {
                if (this.result.uninterpretedOption_.isEmpty()) {
                    this.result.uninterpretedOption_ = new ArrayList();
                }
                GeneratedMessage.ExtendableBuilder.addAll(iterable, this.result.uninterpretedOption_);
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption.Builder builder) {
                if (this.result.uninterpretedOption_.isEmpty()) {
                    this.result.uninterpretedOption_ = new ArrayList();
                }
                this.result.uninterpretedOption_.add(builder.build());
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption uninterpretedOption) {
                if (uninterpretedOption != null) {
                    if (this.result.uninterpretedOption_.isEmpty()) {
                        this.result.uninterpretedOption_ = new ArrayList();
                    }
                    this.result.uninterpretedOption_.add(uninterpretedOption);
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FileOptions build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FileOptions buildPartial() {
                FileOptions fileOptions = this.result;
                if (fileOptions != null) {
                    if (fileOptions.uninterpretedOption_ != Collections.EMPTY_LIST) {
                        FileOptions fileOptions2 = this.result;
                        fileOptions2.uninterpretedOption_ = Collections.unmodifiableList(fileOptions2.uninterpretedOption_);
                    }
                    FileOptions fileOptions3 = this.result;
                    this.result = null;
                    return fileOptions3;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                if (this.result != null) {
                    this.result = new FileOptions();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public Builder clearCcGenericServices() {
                this.result.hasCcGenericServices = false;
                this.result.ccGenericServices_ = true;
                return this;
            }

            public Builder clearJavaGenericServices() {
                this.result.hasJavaGenericServices = false;
                this.result.javaGenericServices_ = true;
                return this;
            }

            public Builder clearJavaMultipleFiles() {
                this.result.hasJavaMultipleFiles = false;
                this.result.javaMultipleFiles_ = false;
                return this;
            }

            public Builder clearJavaOuterClassname() {
                this.result.hasJavaOuterClassname = false;
                this.result.javaOuterClassname_ = FileOptions.getDefaultInstance().getJavaOuterClassname();
                return this;
            }

            public Builder clearJavaPackage() {
                this.result.hasJavaPackage = false;
                this.result.javaPackage_ = FileOptions.getDefaultInstance().getJavaPackage();
                return this;
            }

            public Builder clearOptimizeFor() {
                this.result.hasOptimizeFor = false;
                this.result.optimizeFor_ = OptimizeMode.SPEED;
                return this;
            }

            public Builder clearPyGenericServices() {
                this.result.hasPyGenericServices = false;
                this.result.pyGenericServices_ = true;
                return this;
            }

            public Builder clearUninterpretedOption() {
                this.result.uninterpretedOption_ = Collections.emptyList();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.ExtendableBuilder, com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3clone() {
                return create().mergeFrom(this.result);
            }

            public boolean getCcGenericServices() {
                return this.result.getCcGenericServices();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FileOptions getDefaultInstanceForType() {
                return FileOptions.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return FileOptions.getDescriptor();
            }

            public boolean getJavaGenericServices() {
                return this.result.getJavaGenericServices();
            }

            public boolean getJavaMultipleFiles() {
                return this.result.getJavaMultipleFiles();
            }

            public String getJavaOuterClassname() {
                return this.result.getJavaOuterClassname();
            }

            public String getJavaPackage() {
                return this.result.getJavaPackage();
            }

            public OptimizeMode getOptimizeFor() {
                return this.result.getOptimizeFor();
            }

            public boolean getPyGenericServices() {
                return this.result.getPyGenericServices();
            }

            public UninterpretedOption getUninterpretedOption(int i) {
                return this.result.getUninterpretedOption(i);
            }

            public int getUninterpretedOptionCount() {
                return this.result.getUninterpretedOptionCount();
            }

            public List<UninterpretedOption> getUninterpretedOptionList() {
                return Collections.unmodifiableList(this.result.uninterpretedOption_);
            }

            public boolean hasCcGenericServices() {
                return this.result.hasCcGenericServices();
            }

            public boolean hasJavaGenericServices() {
                return this.result.hasJavaGenericServices();
            }

            public boolean hasJavaMultipleFiles() {
                return this.result.hasJavaMultipleFiles();
            }

            public boolean hasJavaOuterClassname() {
                return this.result.hasJavaOuterClassname();
            }

            public boolean hasJavaPackage() {
                return this.result.hasJavaPackage();
            }

            public boolean hasOptimizeFor() {
                return this.result.hasOptimizeFor();
            }

            public boolean hasPyGenericServices() {
                return this.result.hasPyGenericServices();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.ExtendableBuilder, com.google.protobuf.GeneratedMessage.Builder
            public FileOptions internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 10) {
                        setJavaPackage(codedInputStream.k());
                    } else if (a == 66) {
                        setJavaOuterClassname(codedInputStream.k());
                    } else if (a == 72) {
                        int n = codedInputStream.n();
                        OptimizeMode valueOf = OptimizeMode.valueOf(n);
                        if (valueOf == null) {
                            newBuilder.mergeVarintField(9, n);
                        } else {
                            setOptimizeFor(valueOf);
                        }
                    } else if (a == 80) {
                        setJavaMultipleFiles(codedInputStream.j());
                    } else if (a == 128) {
                        setCcGenericServices(codedInputStream.j());
                    } else if (a == 136) {
                        setJavaGenericServices(codedInputStream.j());
                    } else if (a == 144) {
                        setPyGenericServices(codedInputStream.j());
                    } else if (a == 7994) {
                        UninterpretedOption.Builder newBuilder2 = UninterpretedOption.newBuilder();
                        codedInputStream.a(newBuilder2, extensionRegistryLite);
                        addUninterpretedOption(newBuilder2.buildPartial());
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            public Builder mergeFrom(FileOptions fileOptions) {
                if (fileOptions == FileOptions.getDefaultInstance()) {
                    return this;
                }
                if (fileOptions.hasJavaPackage()) {
                    setJavaPackage(fileOptions.getJavaPackage());
                }
                if (fileOptions.hasJavaOuterClassname()) {
                    setJavaOuterClassname(fileOptions.getJavaOuterClassname());
                }
                if (fileOptions.hasJavaMultipleFiles()) {
                    setJavaMultipleFiles(fileOptions.getJavaMultipleFiles());
                }
                if (fileOptions.hasOptimizeFor()) {
                    setOptimizeFor(fileOptions.getOptimizeFor());
                }
                if (fileOptions.hasCcGenericServices()) {
                    setCcGenericServices(fileOptions.getCcGenericServices());
                }
                if (fileOptions.hasJavaGenericServices()) {
                    setJavaGenericServices(fileOptions.getJavaGenericServices());
                }
                if (fileOptions.hasPyGenericServices()) {
                    setPyGenericServices(fileOptions.getPyGenericServices());
                }
                if (!fileOptions.uninterpretedOption_.isEmpty()) {
                    if (this.result.uninterpretedOption_.isEmpty()) {
                        this.result.uninterpretedOption_ = new ArrayList();
                    }
                    this.result.uninterpretedOption_.addAll(fileOptions.uninterpretedOption_);
                }
                mergeExtensionFields(fileOptions);
                mergeUnknownFields(fileOptions.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof FileOptions) {
                    return mergeFrom((FileOptions) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder setCcGenericServices(boolean z) {
                this.result.hasCcGenericServices = true;
                this.result.ccGenericServices_ = z;
                return this;
            }

            public Builder setJavaGenericServices(boolean z) {
                this.result.hasJavaGenericServices = true;
                this.result.javaGenericServices_ = z;
                return this;
            }

            public Builder setJavaMultipleFiles(boolean z) {
                this.result.hasJavaMultipleFiles = true;
                this.result.javaMultipleFiles_ = z;
                return this;
            }

            public Builder setJavaOuterClassname(String str) {
                if (str != null) {
                    this.result.hasJavaOuterClassname = true;
                    this.result.javaOuterClassname_ = str;
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setJavaPackage(String str) {
                if (str != null) {
                    this.result.hasJavaPackage = true;
                    this.result.javaPackage_ = str;
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setOptimizeFor(OptimizeMode optimizeMode) {
                if (optimizeMode != null) {
                    this.result.hasOptimizeFor = true;
                    this.result.optimizeFor_ = optimizeMode;
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setPyGenericServices(boolean z) {
                this.result.hasPyGenericServices = true;
                this.result.pyGenericServices_ = z;
                return this;
            }

            public Builder setUninterpretedOption(int i, UninterpretedOption.Builder builder) {
                this.result.uninterpretedOption_.set(i, builder.build());
                return this;
            }

            public Builder setUninterpretedOption(int i, UninterpretedOption uninterpretedOption) {
                if (uninterpretedOption != null) {
                    this.result.uninterpretedOption_.set(i, uninterpretedOption);
                    return this;
                }
                throw new NullPointerException();
            }
        }

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public enum OptimizeMode implements ProtocolMessageEnum {
            SPEED(0, 1),
            CODE_SIZE(1, 2),
            LITE_RUNTIME(2, 3);
            
            private final int index;
            private final int value;
            private static Internal.EnumLiteMap<OptimizeMode> internalValueMap = new Internal.EnumLiteMap<OptimizeMode>() { // from class: com.google.protobuf.DescriptorProtos.FileOptions.OptimizeMode.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public OptimizeMode findValueByNumber(int i) {
                    return OptimizeMode.valueOf(i);
                }
            };
            private static final OptimizeMode[] VALUES = {SPEED, CODE_SIZE, LITE_RUNTIME};

            static {
                DescriptorProtos.a();
            }

            OptimizeMode(int i, int i2) {
                this.index = i;
                this.value = i2;
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return FileOptions.getDescriptor().getEnumTypes().get(0);
            }

            public static Internal.EnumLiteMap<OptimizeMode> internalGetValueMap() {
                return internalValueMap;
            }

            public static OptimizeMode valueOf(int i) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            return null;
                        }
                        return LITE_RUNTIME;
                    }
                    return CODE_SIZE;
                }
                return SPEED;
            }

            public static OptimizeMode valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() == getDescriptor()) {
                    return VALUES[enumValueDescriptor.getIndex()];
                }
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return getDescriptor().getValues().get(this.index);
            }
        }

        static {
            DescriptorProtos.b();
            defaultInstance.initFields();
        }

        private FileOptions() {
            this.javaPackage_ = a.d;
            this.javaOuterClassname_ = a.d;
            this.javaMultipleFiles_ = false;
            this.ccGenericServices_ = true;
            this.javaGenericServices_ = true;
            this.pyGenericServices_ = true;
            this.uninterpretedOption_ = Collections.emptyList();
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private FileOptions(boolean z) {
            this.javaPackage_ = a.d;
            this.javaOuterClassname_ = a.d;
            this.javaMultipleFiles_ = false;
            this.ccGenericServices_ = true;
            this.javaGenericServices_ = true;
            this.pyGenericServices_ = true;
            this.uninterpretedOption_ = Collections.emptyList();
            this.memoizedSerializedSize = -1;
        }

        public static FileOptions getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.s;
        }

        private void initFields() {
            this.optimizeFor_ = OptimizeMode.SPEED;
        }

        public static Builder newBuilder() {
            return Builder.access$11300();
        }

        public static Builder newBuilder(FileOptions fileOptions) {
            return newBuilder().mergeFrom(fileOptions);
        }

        public static FileOptions parseDelimitedFrom(InputStream inputStream) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static FileOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static FileOptions parseFrom(ByteString byteString) {
            return ((Builder) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static FileOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static FileOptions parseFrom(CodedInputStream codedInputStream) {
            return ((Builder) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static FileOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static FileOptions parseFrom(InputStream inputStream) {
            return ((Builder) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static FileOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static FileOptions parseFrom(byte[] bArr) {
            return ((Builder) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static FileOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        public boolean getCcGenericServices() {
            return this.ccGenericServices_;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public FileOptions getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean getJavaGenericServices() {
            return this.javaGenericServices_;
        }

        public boolean getJavaMultipleFiles() {
            return this.javaMultipleFiles_;
        }

        public String getJavaOuterClassname() {
            return this.javaOuterClassname_;
        }

        public String getJavaPackage() {
            return this.javaPackage_;
        }

        public OptimizeMode getOptimizeFor() {
            return this.optimizeFor_;
        }

        public boolean getPyGenericServices() {
            return this.pyGenericServices_;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int b = hasJavaPackage() ? 0 + CodedOutputStream.b(1, getJavaPackage()) : 0;
            if (hasJavaOuterClassname()) {
                b += CodedOutputStream.b(8, getJavaOuterClassname());
            }
            if (hasOptimizeFor()) {
                b += CodedOutputStream.j(9, getOptimizeFor().getNumber());
            }
            if (hasJavaMultipleFiles()) {
                b += CodedOutputStream.b(10, getJavaMultipleFiles());
            }
            if (hasCcGenericServices()) {
                b += CodedOutputStream.b(16, getCcGenericServices());
            }
            if (hasJavaGenericServices()) {
                b += CodedOutputStream.b(17, getJavaGenericServices());
            }
            if (hasPyGenericServices()) {
                b += CodedOutputStream.b(18, getPyGenericServices());
            }
            for (UninterpretedOption uninterpretedOption : getUninterpretedOptionList()) {
                b += CodedOutputStream.g(999, uninterpretedOption);
            }
            int extensionsSerializedSize = b + extensionsSerializedSize() + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = extensionsSerializedSize;
            return extensionsSerializedSize;
        }

        public UninterpretedOption getUninterpretedOption(int i) {
            return this.uninterpretedOption_.get(i);
        }

        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        public List<UninterpretedOption> getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        public boolean hasCcGenericServices() {
            return this.hasCcGenericServices;
        }

        public boolean hasJavaGenericServices() {
            return this.hasJavaGenericServices;
        }

        public boolean hasJavaMultipleFiles() {
            return this.hasJavaMultipleFiles;
        }

        public boolean hasJavaOuterClassname() {
            return this.hasJavaOuterClassname;
        }

        public boolean hasJavaPackage() {
            return this.hasJavaPackage;
        }

        public boolean hasOptimizeFor() {
            return this.hasOptimizeFor;
        }

        public boolean hasPyGenericServices() {
            return this.hasPyGenericServices;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.t;
        }

        @Override // com.google.protobuf.GeneratedMessage.ExtendableMessage, com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            for (UninterpretedOption uninterpretedOption : getUninterpretedOptionList()) {
                if (!uninterpretedOption.isInitialized()) {
                    return false;
                }
            }
            return extensionsAreInitialized();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            GeneratedMessage.ExtendableMessage<MessageType>.ExtensionWriter newExtensionWriter = newExtensionWriter();
            if (hasJavaPackage()) {
                codedOutputStream.a(1, getJavaPackage());
            }
            if (hasJavaOuterClassname()) {
                codedOutputStream.a(8, getJavaOuterClassname());
            }
            if (hasOptimizeFor()) {
                codedOutputStream.d(9, getOptimizeFor().getNumber());
            }
            if (hasJavaMultipleFiles()) {
                codedOutputStream.a(10, getJavaMultipleFiles());
            }
            if (hasCcGenericServices()) {
                codedOutputStream.a(16, getCcGenericServices());
            }
            if (hasJavaGenericServices()) {
                codedOutputStream.a(17, getJavaGenericServices());
            }
            if (hasPyGenericServices()) {
                codedOutputStream.a(18, getPyGenericServices());
            }
            for (UninterpretedOption uninterpretedOption : getUninterpretedOptionList()) {
                codedOutputStream.c(999, uninterpretedOption);
            }
            newExtensionWriter.a(536870912, codedOutputStream);
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class MessageOptions extends GeneratedMessage.ExtendableMessage<MessageOptions> {
        public static final int MESSAGE_SET_WIRE_FORMAT_FIELD_NUMBER = 1;
        public static final int NO_STANDARD_DESCRIPTOR_ACCESSOR_FIELD_NUMBER = 2;
        public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
        private static final MessageOptions defaultInstance = new MessageOptions(true);
        private boolean hasMessageSetWireFormat;
        private boolean hasNoStandardDescriptorAccessor;
        private int memoizedSerializedSize;
        private boolean messageSetWireFormat_;
        private boolean noStandardDescriptorAccessor_;
        private List<UninterpretedOption> uninterpretedOption_;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static final class Builder extends GeneratedMessage.ExtendableBuilder<MessageOptions, Builder> {
            private MessageOptions result;

            private Builder() {
            }

            static /* synthetic */ Builder access$13300() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public MessageOptions buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static Builder create() {
                Builder builder = new Builder();
                builder.result = new MessageOptions();
                return builder;
            }

            public Builder addAllUninterpretedOption(Iterable<? extends UninterpretedOption> iterable) {
                if (this.result.uninterpretedOption_.isEmpty()) {
                    this.result.uninterpretedOption_ = new ArrayList();
                }
                GeneratedMessage.ExtendableBuilder.addAll(iterable, this.result.uninterpretedOption_);
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption.Builder builder) {
                if (this.result.uninterpretedOption_.isEmpty()) {
                    this.result.uninterpretedOption_ = new ArrayList();
                }
                this.result.uninterpretedOption_.add(builder.build());
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption uninterpretedOption) {
                if (uninterpretedOption != null) {
                    if (this.result.uninterpretedOption_.isEmpty()) {
                        this.result.uninterpretedOption_ = new ArrayList();
                    }
                    this.result.uninterpretedOption_.add(uninterpretedOption);
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageOptions build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageOptions buildPartial() {
                MessageOptions messageOptions = this.result;
                if (messageOptions != null) {
                    if (messageOptions.uninterpretedOption_ != Collections.EMPTY_LIST) {
                        MessageOptions messageOptions2 = this.result;
                        messageOptions2.uninterpretedOption_ = Collections.unmodifiableList(messageOptions2.uninterpretedOption_);
                    }
                    MessageOptions messageOptions3 = this.result;
                    this.result = null;
                    return messageOptions3;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                if (this.result != null) {
                    this.result = new MessageOptions();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public Builder clearMessageSetWireFormat() {
                this.result.hasMessageSetWireFormat = false;
                this.result.messageSetWireFormat_ = false;
                return this;
            }

            public Builder clearNoStandardDescriptorAccessor() {
                this.result.hasNoStandardDescriptorAccessor = false;
                this.result.noStandardDescriptorAccessor_ = false;
                return this;
            }

            public Builder clearUninterpretedOption() {
                this.result.uninterpretedOption_ = Collections.emptyList();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.ExtendableBuilder, com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3clone() {
                return create().mergeFrom(this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageOptions getDefaultInstanceForType() {
                return MessageOptions.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return MessageOptions.getDescriptor();
            }

            public boolean getMessageSetWireFormat() {
                return this.result.getMessageSetWireFormat();
            }

            public boolean getNoStandardDescriptorAccessor() {
                return this.result.getNoStandardDescriptorAccessor();
            }

            public UninterpretedOption getUninterpretedOption(int i) {
                return this.result.getUninterpretedOption(i);
            }

            public int getUninterpretedOptionCount() {
                return this.result.getUninterpretedOptionCount();
            }

            public List<UninterpretedOption> getUninterpretedOptionList() {
                return Collections.unmodifiableList(this.result.uninterpretedOption_);
            }

            public boolean hasMessageSetWireFormat() {
                return this.result.hasMessageSetWireFormat();
            }

            public boolean hasNoStandardDescriptorAccessor() {
                return this.result.hasNoStandardDescriptorAccessor();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.ExtendableBuilder, com.google.protobuf.GeneratedMessage.Builder
            public MessageOptions internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 8) {
                        setMessageSetWireFormat(codedInputStream.j());
                    } else if (a == 16) {
                        setNoStandardDescriptorAccessor(codedInputStream.j());
                    } else if (a == 7994) {
                        UninterpretedOption.Builder newBuilder2 = UninterpretedOption.newBuilder();
                        codedInputStream.a(newBuilder2, extensionRegistryLite);
                        addUninterpretedOption(newBuilder2.buildPartial());
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            public Builder mergeFrom(MessageOptions messageOptions) {
                if (messageOptions == MessageOptions.getDefaultInstance()) {
                    return this;
                }
                if (messageOptions.hasMessageSetWireFormat()) {
                    setMessageSetWireFormat(messageOptions.getMessageSetWireFormat());
                }
                if (messageOptions.hasNoStandardDescriptorAccessor()) {
                    setNoStandardDescriptorAccessor(messageOptions.getNoStandardDescriptorAccessor());
                }
                if (!messageOptions.uninterpretedOption_.isEmpty()) {
                    if (this.result.uninterpretedOption_.isEmpty()) {
                        this.result.uninterpretedOption_ = new ArrayList();
                    }
                    this.result.uninterpretedOption_.addAll(messageOptions.uninterpretedOption_);
                }
                mergeExtensionFields(messageOptions);
                mergeUnknownFields(messageOptions.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof MessageOptions) {
                    return mergeFrom((MessageOptions) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder setMessageSetWireFormat(boolean z) {
                this.result.hasMessageSetWireFormat = true;
                this.result.messageSetWireFormat_ = z;
                return this;
            }

            public Builder setNoStandardDescriptorAccessor(boolean z) {
                this.result.hasNoStandardDescriptorAccessor = true;
                this.result.noStandardDescriptorAccessor_ = z;
                return this;
            }

            public Builder setUninterpretedOption(int i, UninterpretedOption.Builder builder) {
                this.result.uninterpretedOption_.set(i, builder.build());
                return this;
            }

            public Builder setUninterpretedOption(int i, UninterpretedOption uninterpretedOption) {
                if (uninterpretedOption != null) {
                    this.result.uninterpretedOption_.set(i, uninterpretedOption);
                    return this;
                }
                throw new NullPointerException();
            }
        }

        static {
            DescriptorProtos.b();
            defaultInstance.initFields();
        }

        private MessageOptions() {
            this.messageSetWireFormat_ = false;
            this.noStandardDescriptorAccessor_ = false;
            this.uninterpretedOption_ = Collections.emptyList();
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private MessageOptions(boolean z) {
            this.messageSetWireFormat_ = false;
            this.noStandardDescriptorAccessor_ = false;
            this.uninterpretedOption_ = Collections.emptyList();
            this.memoizedSerializedSize = -1;
        }

        public static MessageOptions getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.u;
        }

        private void initFields() {
        }

        public static Builder newBuilder() {
            return Builder.access$13300();
        }

        public static Builder newBuilder(MessageOptions messageOptions) {
            return newBuilder().mergeFrom(messageOptions);
        }

        public static MessageOptions parseDelimitedFrom(InputStream inputStream) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static MessageOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static MessageOptions parseFrom(ByteString byteString) {
            return ((Builder) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static MessageOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static MessageOptions parseFrom(CodedInputStream codedInputStream) {
            return ((Builder) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static MessageOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static MessageOptions parseFrom(InputStream inputStream) {
            return ((Builder) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static MessageOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static MessageOptions parseFrom(byte[] bArr) {
            return ((Builder) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static MessageOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public MessageOptions getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean getMessageSetWireFormat() {
            return this.messageSetWireFormat_;
        }

        public boolean getNoStandardDescriptorAccessor() {
            return this.noStandardDescriptorAccessor_;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int b = hasMessageSetWireFormat() ? 0 + CodedOutputStream.b(1, getMessageSetWireFormat()) : 0;
            if (hasNoStandardDescriptorAccessor()) {
                b += CodedOutputStream.b(2, getNoStandardDescriptorAccessor());
            }
            for (UninterpretedOption uninterpretedOption : getUninterpretedOptionList()) {
                b += CodedOutputStream.g(999, uninterpretedOption);
            }
            int extensionsSerializedSize = b + extensionsSerializedSize() + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = extensionsSerializedSize;
            return extensionsSerializedSize;
        }

        public UninterpretedOption getUninterpretedOption(int i) {
            return this.uninterpretedOption_.get(i);
        }

        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        public List<UninterpretedOption> getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        public boolean hasMessageSetWireFormat() {
            return this.hasMessageSetWireFormat;
        }

        public boolean hasNoStandardDescriptorAccessor() {
            return this.hasNoStandardDescriptorAccessor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.v;
        }

        @Override // com.google.protobuf.GeneratedMessage.ExtendableMessage, com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            for (UninterpretedOption uninterpretedOption : getUninterpretedOptionList()) {
                if (!uninterpretedOption.isInitialized()) {
                    return false;
                }
            }
            return extensionsAreInitialized();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            GeneratedMessage.ExtendableMessage<MessageType>.ExtensionWriter newExtensionWriter = newExtensionWriter();
            if (hasMessageSetWireFormat()) {
                codedOutputStream.a(1, getMessageSetWireFormat());
            }
            if (hasNoStandardDescriptorAccessor()) {
                codedOutputStream.a(2, getNoStandardDescriptorAccessor());
            }
            for (UninterpretedOption uninterpretedOption : getUninterpretedOptionList()) {
                codedOutputStream.c(999, uninterpretedOption);
            }
            newExtensionWriter.a(536870912, codedOutputStream);
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class MethodDescriptorProto extends GeneratedMessage {
        public static final int INPUT_TYPE_FIELD_NUMBER = 2;
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int OPTIONS_FIELD_NUMBER = 4;
        public static final int OUTPUT_TYPE_FIELD_NUMBER = 3;
        private static final MethodDescriptorProto defaultInstance = new MethodDescriptorProto(true);
        private boolean hasInputType;
        private boolean hasName;
        private boolean hasOptions;
        private boolean hasOutputType;
        private String inputType_;
        private int memoizedSerializedSize;
        private String name_;
        private MethodOptions options_;
        private String outputType_;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static final class Builder extends GeneratedMessage.Builder<Builder> {
            private MethodDescriptorProto result;

            private Builder() {
            }

            static /* synthetic */ Builder access$10000() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public MethodDescriptorProto buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static Builder create() {
                Builder builder = new Builder();
                builder.result = new MethodDescriptorProto();
                return builder;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MethodDescriptorProto build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MethodDescriptorProto buildPartial() {
                MethodDescriptorProto methodDescriptorProto = this.result;
                if (methodDescriptorProto != null) {
                    this.result = null;
                    return methodDescriptorProto;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                if (this.result != null) {
                    this.result = new MethodDescriptorProto();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public Builder clearInputType() {
                this.result.hasInputType = false;
                this.result.inputType_ = MethodDescriptorProto.getDefaultInstance().getInputType();
                return this;
            }

            public Builder clearName() {
                this.result.hasName = false;
                this.result.name_ = MethodDescriptorProto.getDefaultInstance().getName();
                return this;
            }

            public Builder clearOptions() {
                this.result.hasOptions = false;
                this.result.options_ = MethodOptions.getDefaultInstance();
                return this;
            }

            public Builder clearOutputType() {
                this.result.hasOutputType = false;
                this.result.outputType_ = MethodDescriptorProto.getDefaultInstance().getOutputType();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3clone() {
                return create().mergeFrom(this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MethodDescriptorProto getDefaultInstanceForType() {
                return MethodDescriptorProto.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return MethodDescriptorProto.getDescriptor();
            }

            public String getInputType() {
                return this.result.getInputType();
            }

            public String getName() {
                return this.result.getName();
            }

            public MethodOptions getOptions() {
                return this.result.getOptions();
            }

            public String getOutputType() {
                return this.result.getOutputType();
            }

            public boolean hasInputType() {
                return this.result.hasInputType();
            }

            public boolean hasName() {
                return this.result.hasName();
            }

            public boolean hasOptions() {
                return this.result.hasOptions();
            }

            public boolean hasOutputType() {
                return this.result.hasOutputType();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.Builder
            public MethodDescriptorProto internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 10) {
                        setName(codedInputStream.k());
                    } else if (a == 18) {
                        setInputType(codedInputStream.k());
                    } else if (a == 26) {
                        setOutputType(codedInputStream.k());
                    } else if (a == 34) {
                        MethodOptions.Builder newBuilder2 = MethodOptions.newBuilder();
                        if (hasOptions()) {
                            newBuilder2.mergeFrom(getOptions());
                        }
                        codedInputStream.a(newBuilder2, extensionRegistryLite);
                        setOptions(newBuilder2.buildPartial());
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            public Builder mergeFrom(MethodDescriptorProto methodDescriptorProto) {
                if (methodDescriptorProto == MethodDescriptorProto.getDefaultInstance()) {
                    return this;
                }
                if (methodDescriptorProto.hasName()) {
                    setName(methodDescriptorProto.getName());
                }
                if (methodDescriptorProto.hasInputType()) {
                    setInputType(methodDescriptorProto.getInputType());
                }
                if (methodDescriptorProto.hasOutputType()) {
                    setOutputType(methodDescriptorProto.getOutputType());
                }
                if (methodDescriptorProto.hasOptions()) {
                    mergeOptions(methodDescriptorProto.getOptions());
                }
                mergeUnknownFields(methodDescriptorProto.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof MethodDescriptorProto) {
                    return mergeFrom((MethodDescriptorProto) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeOptions(MethodOptions methodOptions) {
                MethodDescriptorProto methodDescriptorProto;
                if (!this.result.hasOptions() || this.result.options_ == MethodOptions.getDefaultInstance()) {
                    methodDescriptorProto = this.result;
                } else {
                    methodDescriptorProto = this.result;
                    methodOptions = MethodOptions.newBuilder(methodDescriptorProto.options_).mergeFrom(methodOptions).buildPartial();
                }
                methodDescriptorProto.options_ = methodOptions;
                this.result.hasOptions = true;
                return this;
            }

            public Builder setInputType(String str) {
                if (str != null) {
                    this.result.hasInputType = true;
                    this.result.inputType_ = str;
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setName(String str) {
                if (str != null) {
                    this.result.hasName = true;
                    this.result.name_ = str;
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setOptions(MethodOptions.Builder builder) {
                this.result.hasOptions = true;
                this.result.options_ = builder.build();
                return this;
            }

            public Builder setOptions(MethodOptions methodOptions) {
                if (methodOptions != null) {
                    this.result.hasOptions = true;
                    this.result.options_ = methodOptions;
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setOutputType(String str) {
                if (str != null) {
                    this.result.hasOutputType = true;
                    this.result.outputType_ = str;
                    return this;
                }
                throw new NullPointerException();
            }
        }

        static {
            DescriptorProtos.b();
            defaultInstance.initFields();
        }

        private MethodDescriptorProto() {
            this.name_ = a.d;
            this.inputType_ = a.d;
            this.outputType_ = a.d;
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private MethodDescriptorProto(boolean z) {
            this.name_ = a.d;
            this.inputType_ = a.d;
            this.outputType_ = a.d;
            this.memoizedSerializedSize = -1;
        }

        public static MethodDescriptorProto getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.q;
        }

        private void initFields() {
            this.options_ = MethodOptions.getDefaultInstance();
        }

        public static Builder newBuilder() {
            return Builder.access$10000();
        }

        public static Builder newBuilder(MethodDescriptorProto methodDescriptorProto) {
            return newBuilder().mergeFrom(methodDescriptorProto);
        }

        public static MethodDescriptorProto parseDelimitedFrom(InputStream inputStream) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static MethodDescriptorProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static MethodDescriptorProto parseFrom(ByteString byteString) {
            return ((Builder) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static MethodDescriptorProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static MethodDescriptorProto parseFrom(CodedInputStream codedInputStream) {
            return ((Builder) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static MethodDescriptorProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static MethodDescriptorProto parseFrom(InputStream inputStream) {
            return ((Builder) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static MethodDescriptorProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static MethodDescriptorProto parseFrom(byte[] bArr) {
            return ((Builder) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static MethodDescriptorProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public MethodDescriptorProto getDefaultInstanceForType() {
            return defaultInstance;
        }

        public String getInputType() {
            return this.inputType_;
        }

        public String getName() {
            return this.name_;
        }

        public MethodOptions getOptions() {
            return this.options_;
        }

        public String getOutputType() {
            return this.outputType_;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int b = hasName() ? 0 + CodedOutputStream.b(1, getName()) : 0;
            if (hasInputType()) {
                b += CodedOutputStream.b(2, getInputType());
            }
            if (hasOutputType()) {
                b += CodedOutputStream.b(3, getOutputType());
            }
            if (hasOptions()) {
                b += CodedOutputStream.g(4, getOptions());
            }
            int serializedSize = b + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public boolean hasInputType() {
            return this.hasInputType;
        }

        public boolean hasName() {
            return this.hasName;
        }

        public boolean hasOptions() {
            return this.hasOptions;
        }

        public boolean hasOutputType() {
            return this.hasOutputType;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.r;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            return !hasOptions() || getOptions().isInitialized();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            if (hasName()) {
                codedOutputStream.a(1, getName());
            }
            if (hasInputType()) {
                codedOutputStream.a(2, getInputType());
            }
            if (hasOutputType()) {
                codedOutputStream.a(3, getOutputType());
            }
            if (hasOptions()) {
                codedOutputStream.c(4, getOptions());
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class MethodOptions extends GeneratedMessage.ExtendableMessage<MethodOptions> {
        public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
        private static final MethodOptions defaultInstance = new MethodOptions(true);
        private int memoizedSerializedSize;
        private List<UninterpretedOption> uninterpretedOption_;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static final class Builder extends GeneratedMessage.ExtendableBuilder<MethodOptions, Builder> {
            private MethodOptions result;

            private Builder() {
            }

            static /* synthetic */ Builder access$17500() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public MethodOptions buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static Builder create() {
                Builder builder = new Builder();
                builder.result = new MethodOptions();
                return builder;
            }

            public Builder addAllUninterpretedOption(Iterable<? extends UninterpretedOption> iterable) {
                if (this.result.uninterpretedOption_.isEmpty()) {
                    this.result.uninterpretedOption_ = new ArrayList();
                }
                GeneratedMessage.ExtendableBuilder.addAll(iterable, this.result.uninterpretedOption_);
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption.Builder builder) {
                if (this.result.uninterpretedOption_.isEmpty()) {
                    this.result.uninterpretedOption_ = new ArrayList();
                }
                this.result.uninterpretedOption_.add(builder.build());
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption uninterpretedOption) {
                if (uninterpretedOption != null) {
                    if (this.result.uninterpretedOption_.isEmpty()) {
                        this.result.uninterpretedOption_ = new ArrayList();
                    }
                    this.result.uninterpretedOption_.add(uninterpretedOption);
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MethodOptions build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MethodOptions buildPartial() {
                MethodOptions methodOptions = this.result;
                if (methodOptions != null) {
                    if (methodOptions.uninterpretedOption_ != Collections.EMPTY_LIST) {
                        MethodOptions methodOptions2 = this.result;
                        methodOptions2.uninterpretedOption_ = Collections.unmodifiableList(methodOptions2.uninterpretedOption_);
                    }
                    MethodOptions methodOptions3 = this.result;
                    this.result = null;
                    return methodOptions3;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                if (this.result != null) {
                    this.result = new MethodOptions();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public Builder clearUninterpretedOption() {
                this.result.uninterpretedOption_ = Collections.emptyList();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.ExtendableBuilder, com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3clone() {
                return create().mergeFrom(this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MethodOptions getDefaultInstanceForType() {
                return MethodOptions.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return MethodOptions.getDescriptor();
            }

            public UninterpretedOption getUninterpretedOption(int i) {
                return this.result.getUninterpretedOption(i);
            }

            public int getUninterpretedOptionCount() {
                return this.result.getUninterpretedOptionCount();
            }

            public List<UninterpretedOption> getUninterpretedOptionList() {
                return Collections.unmodifiableList(this.result.uninterpretedOption_);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.ExtendableBuilder, com.google.protobuf.GeneratedMessage.Builder
            public MethodOptions internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 7994) {
                        UninterpretedOption.Builder newBuilder2 = UninterpretedOption.newBuilder();
                        codedInputStream.a(newBuilder2, extensionRegistryLite);
                        addUninterpretedOption(newBuilder2.buildPartial());
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            public Builder mergeFrom(MethodOptions methodOptions) {
                if (methodOptions == MethodOptions.getDefaultInstance()) {
                    return this;
                }
                if (!methodOptions.uninterpretedOption_.isEmpty()) {
                    if (this.result.uninterpretedOption_.isEmpty()) {
                        this.result.uninterpretedOption_ = new ArrayList();
                    }
                    this.result.uninterpretedOption_.addAll(methodOptions.uninterpretedOption_);
                }
                mergeExtensionFields(methodOptions);
                mergeUnknownFields(methodOptions.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof MethodOptions) {
                    return mergeFrom((MethodOptions) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder setUninterpretedOption(int i, UninterpretedOption.Builder builder) {
                this.result.uninterpretedOption_.set(i, builder.build());
                return this;
            }

            public Builder setUninterpretedOption(int i, UninterpretedOption uninterpretedOption) {
                if (uninterpretedOption != null) {
                    this.result.uninterpretedOption_.set(i, uninterpretedOption);
                    return this;
                }
                throw new NullPointerException();
            }
        }

        static {
            DescriptorProtos.b();
            defaultInstance.initFields();
        }

        private MethodOptions() {
            this.uninterpretedOption_ = Collections.emptyList();
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private MethodOptions(boolean z) {
            this.uninterpretedOption_ = Collections.emptyList();
            this.memoizedSerializedSize = -1;
        }

        public static MethodOptions getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.E;
        }

        private void initFields() {
        }

        public static Builder newBuilder() {
            return Builder.access$17500();
        }

        public static Builder newBuilder(MethodOptions methodOptions) {
            return newBuilder().mergeFrom(methodOptions);
        }

        public static MethodOptions parseDelimitedFrom(InputStream inputStream) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static MethodOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static MethodOptions parseFrom(ByteString byteString) {
            return ((Builder) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static MethodOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static MethodOptions parseFrom(CodedInputStream codedInputStream) {
            return ((Builder) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static MethodOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static MethodOptions parseFrom(InputStream inputStream) {
            return ((Builder) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static MethodOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static MethodOptions parseFrom(byte[] bArr) {
            return ((Builder) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static MethodOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public MethodOptions getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (UninterpretedOption uninterpretedOption : getUninterpretedOptionList()) {
                i2 += CodedOutputStream.g(999, uninterpretedOption);
            }
            int extensionsSerializedSize = i2 + extensionsSerializedSize() + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = extensionsSerializedSize;
            return extensionsSerializedSize;
        }

        public UninterpretedOption getUninterpretedOption(int i) {
            return this.uninterpretedOption_.get(i);
        }

        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        public List<UninterpretedOption> getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.F;
        }

        @Override // com.google.protobuf.GeneratedMessage.ExtendableMessage, com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            for (UninterpretedOption uninterpretedOption : getUninterpretedOptionList()) {
                if (!uninterpretedOption.isInitialized()) {
                    return false;
                }
            }
            return extensionsAreInitialized();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            GeneratedMessage.ExtendableMessage<MessageType>.ExtensionWriter newExtensionWriter = newExtensionWriter();
            for (UninterpretedOption uninterpretedOption : getUninterpretedOptionList()) {
                codedOutputStream.c(999, uninterpretedOption);
            }
            newExtensionWriter.a(536870912, codedOutputStream);
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class ServiceDescriptorProto extends GeneratedMessage {
        public static final int METHOD_FIELD_NUMBER = 2;
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int OPTIONS_FIELD_NUMBER = 3;
        private static final ServiceDescriptorProto defaultInstance = new ServiceDescriptorProto(true);
        private boolean hasName;
        private boolean hasOptions;
        private int memoizedSerializedSize;
        private List<MethodDescriptorProto> method_;
        private String name_;
        private ServiceOptions options_;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static final class Builder extends GeneratedMessage.Builder<Builder> {
            private ServiceDescriptorProto result;

            private Builder() {
            }

            static /* synthetic */ Builder access$9000() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public ServiceDescriptorProto buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static Builder create() {
                Builder builder = new Builder();
                builder.result = new ServiceDescriptorProto();
                return builder;
            }

            public Builder addAllMethod(Iterable<? extends MethodDescriptorProto> iterable) {
                if (this.result.method_.isEmpty()) {
                    this.result.method_ = new ArrayList();
                }
                GeneratedMessage.Builder.addAll(iterable, this.result.method_);
                return this;
            }

            public Builder addMethod(MethodDescriptorProto.Builder builder) {
                if (this.result.method_.isEmpty()) {
                    this.result.method_ = new ArrayList();
                }
                this.result.method_.add(builder.build());
                return this;
            }

            public Builder addMethod(MethodDescriptorProto methodDescriptorProto) {
                if (methodDescriptorProto != null) {
                    if (this.result.method_.isEmpty()) {
                        this.result.method_ = new ArrayList();
                    }
                    this.result.method_.add(methodDescriptorProto);
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ServiceDescriptorProto build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ServiceDescriptorProto buildPartial() {
                ServiceDescriptorProto serviceDescriptorProto = this.result;
                if (serviceDescriptorProto != null) {
                    if (serviceDescriptorProto.method_ != Collections.EMPTY_LIST) {
                        ServiceDescriptorProto serviceDescriptorProto2 = this.result;
                        serviceDescriptorProto2.method_ = Collections.unmodifiableList(serviceDescriptorProto2.method_);
                    }
                    ServiceDescriptorProto serviceDescriptorProto3 = this.result;
                    this.result = null;
                    return serviceDescriptorProto3;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                if (this.result != null) {
                    this.result = new ServiceDescriptorProto();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public Builder clearMethod() {
                this.result.method_ = Collections.emptyList();
                return this;
            }

            public Builder clearName() {
                this.result.hasName = false;
                this.result.name_ = ServiceDescriptorProto.getDefaultInstance().getName();
                return this;
            }

            public Builder clearOptions() {
                this.result.hasOptions = false;
                this.result.options_ = ServiceOptions.getDefaultInstance();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3clone() {
                return create().mergeFrom(this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ServiceDescriptorProto getDefaultInstanceForType() {
                return ServiceDescriptorProto.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return ServiceDescriptorProto.getDescriptor();
            }

            public MethodDescriptorProto getMethod(int i) {
                return this.result.getMethod(i);
            }

            public int getMethodCount() {
                return this.result.getMethodCount();
            }

            public List<MethodDescriptorProto> getMethodList() {
                return Collections.unmodifiableList(this.result.method_);
            }

            public String getName() {
                return this.result.getName();
            }

            public ServiceOptions getOptions() {
                return this.result.getOptions();
            }

            public boolean hasName() {
                return this.result.hasName();
            }

            public boolean hasOptions() {
                return this.result.hasOptions();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.Builder
            public ServiceDescriptorProto internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 10) {
                        setName(codedInputStream.k());
                    } else if (a == 18) {
                        MessageLite.Builder newBuilder2 = MethodDescriptorProto.newBuilder();
                        codedInputStream.a(newBuilder2, extensionRegistryLite);
                        addMethod(newBuilder2.buildPartial());
                    } else if (a == 26) {
                        ServiceOptions.Builder newBuilder3 = ServiceOptions.newBuilder();
                        if (hasOptions()) {
                            newBuilder3.mergeFrom(getOptions());
                        }
                        codedInputStream.a(newBuilder3, extensionRegistryLite);
                        setOptions(newBuilder3.buildPartial());
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            public Builder mergeFrom(ServiceDescriptorProto serviceDescriptorProto) {
                if (serviceDescriptorProto == ServiceDescriptorProto.getDefaultInstance()) {
                    return this;
                }
                if (serviceDescriptorProto.hasName()) {
                    setName(serviceDescriptorProto.getName());
                }
                if (!serviceDescriptorProto.method_.isEmpty()) {
                    if (this.result.method_.isEmpty()) {
                        this.result.method_ = new ArrayList();
                    }
                    this.result.method_.addAll(serviceDescriptorProto.method_);
                }
                if (serviceDescriptorProto.hasOptions()) {
                    mergeOptions(serviceDescriptorProto.getOptions());
                }
                mergeUnknownFields(serviceDescriptorProto.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof ServiceDescriptorProto) {
                    return mergeFrom((ServiceDescriptorProto) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeOptions(ServiceOptions serviceOptions) {
                ServiceDescriptorProto serviceDescriptorProto;
                if (!this.result.hasOptions() || this.result.options_ == ServiceOptions.getDefaultInstance()) {
                    serviceDescriptorProto = this.result;
                } else {
                    serviceDescriptorProto = this.result;
                    serviceOptions = ServiceOptions.newBuilder(serviceDescriptorProto.options_).mergeFrom(serviceOptions).buildPartial();
                }
                serviceDescriptorProto.options_ = serviceOptions;
                this.result.hasOptions = true;
                return this;
            }

            public Builder setMethod(int i, MethodDescriptorProto.Builder builder) {
                this.result.method_.set(i, builder.build());
                return this;
            }

            public Builder setMethod(int i, MethodDescriptorProto methodDescriptorProto) {
                if (methodDescriptorProto != null) {
                    this.result.method_.set(i, methodDescriptorProto);
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setName(String str) {
                if (str != null) {
                    this.result.hasName = true;
                    this.result.name_ = str;
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setOptions(ServiceOptions.Builder builder) {
                this.result.hasOptions = true;
                this.result.options_ = builder.build();
                return this;
            }

            public Builder setOptions(ServiceOptions serviceOptions) {
                if (serviceOptions != null) {
                    this.result.hasOptions = true;
                    this.result.options_ = serviceOptions;
                    return this;
                }
                throw new NullPointerException();
            }
        }

        static {
            DescriptorProtos.b();
            defaultInstance.initFields();
        }

        private ServiceDescriptorProto() {
            this.name_ = a.d;
            this.method_ = Collections.emptyList();
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private ServiceDescriptorProto(boolean z) {
            this.name_ = a.d;
            this.method_ = Collections.emptyList();
            this.memoizedSerializedSize = -1;
        }

        public static ServiceDescriptorProto getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.o;
        }

        private void initFields() {
            this.options_ = ServiceOptions.getDefaultInstance();
        }

        public static Builder newBuilder() {
            return Builder.access$9000();
        }

        public static Builder newBuilder(ServiceDescriptorProto serviceDescriptorProto) {
            return newBuilder().mergeFrom(serviceDescriptorProto);
        }

        public static ServiceDescriptorProto parseDelimitedFrom(InputStream inputStream) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static ServiceDescriptorProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static ServiceDescriptorProto parseFrom(ByteString byteString) {
            return ((Builder) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static ServiceDescriptorProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static ServiceDescriptorProto parseFrom(CodedInputStream codedInputStream) {
            return ((Builder) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static ServiceDescriptorProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static ServiceDescriptorProto parseFrom(InputStream inputStream) {
            return ((Builder) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static ServiceDescriptorProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static ServiceDescriptorProto parseFrom(byte[] bArr) {
            return ((Builder) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static ServiceDescriptorProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public ServiceDescriptorProto getDefaultInstanceForType() {
            return defaultInstance;
        }

        public MethodDescriptorProto getMethod(int i) {
            return this.method_.get(i);
        }

        public int getMethodCount() {
            return this.method_.size();
        }

        public List<MethodDescriptorProto> getMethodList() {
            return this.method_;
        }

        public String getName() {
            return this.name_;
        }

        public ServiceOptions getOptions() {
            return this.options_;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int b = hasName() ? 0 + CodedOutputStream.b(1, getName()) : 0;
            for (MethodDescriptorProto methodDescriptorProto : getMethodList()) {
                b += CodedOutputStream.g(2, methodDescriptorProto);
            }
            if (hasOptions()) {
                b += CodedOutputStream.g(3, getOptions());
            }
            int serializedSize = b + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public boolean hasName() {
            return this.hasName;
        }

        public boolean hasOptions() {
            return this.hasOptions;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.p;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            for (MethodDescriptorProto methodDescriptorProto : getMethodList()) {
                if (!methodDescriptorProto.isInitialized()) {
                    return false;
                }
            }
            return !hasOptions() || getOptions().isInitialized();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            if (hasName()) {
                codedOutputStream.a(1, getName());
            }
            for (MethodDescriptorProto methodDescriptorProto : getMethodList()) {
                codedOutputStream.c(2, methodDescriptorProto);
            }
            if (hasOptions()) {
                codedOutputStream.c(3, getOptions());
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class ServiceOptions extends GeneratedMessage.ExtendableMessage<ServiceOptions> {
        public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
        private static final ServiceOptions defaultInstance = new ServiceOptions(true);
        private int memoizedSerializedSize;
        private List<UninterpretedOption> uninterpretedOption_;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static final class Builder extends GeneratedMessage.ExtendableBuilder<ServiceOptions, Builder> {
            private ServiceOptions result;

            private Builder() {
            }

            static /* synthetic */ Builder access$16900() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public ServiceOptions buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static Builder create() {
                Builder builder = new Builder();
                builder.result = new ServiceOptions();
                return builder;
            }

            public Builder addAllUninterpretedOption(Iterable<? extends UninterpretedOption> iterable) {
                if (this.result.uninterpretedOption_.isEmpty()) {
                    this.result.uninterpretedOption_ = new ArrayList();
                }
                GeneratedMessage.ExtendableBuilder.addAll(iterable, this.result.uninterpretedOption_);
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption.Builder builder) {
                if (this.result.uninterpretedOption_.isEmpty()) {
                    this.result.uninterpretedOption_ = new ArrayList();
                }
                this.result.uninterpretedOption_.add(builder.build());
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption uninterpretedOption) {
                if (uninterpretedOption != null) {
                    if (this.result.uninterpretedOption_.isEmpty()) {
                        this.result.uninterpretedOption_ = new ArrayList();
                    }
                    this.result.uninterpretedOption_.add(uninterpretedOption);
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ServiceOptions build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ServiceOptions buildPartial() {
                ServiceOptions serviceOptions = this.result;
                if (serviceOptions != null) {
                    if (serviceOptions.uninterpretedOption_ != Collections.EMPTY_LIST) {
                        ServiceOptions serviceOptions2 = this.result;
                        serviceOptions2.uninterpretedOption_ = Collections.unmodifiableList(serviceOptions2.uninterpretedOption_);
                    }
                    ServiceOptions serviceOptions3 = this.result;
                    this.result = null;
                    return serviceOptions3;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                if (this.result != null) {
                    this.result = new ServiceOptions();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public Builder clearUninterpretedOption() {
                this.result.uninterpretedOption_ = Collections.emptyList();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.ExtendableBuilder, com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3clone() {
                return create().mergeFrom(this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ServiceOptions getDefaultInstanceForType() {
                return ServiceOptions.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return ServiceOptions.getDescriptor();
            }

            public UninterpretedOption getUninterpretedOption(int i) {
                return this.result.getUninterpretedOption(i);
            }

            public int getUninterpretedOptionCount() {
                return this.result.getUninterpretedOptionCount();
            }

            public List<UninterpretedOption> getUninterpretedOptionList() {
                return Collections.unmodifiableList(this.result.uninterpretedOption_);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.ExtendableBuilder, com.google.protobuf.GeneratedMessage.Builder
            public ServiceOptions internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 7994) {
                        UninterpretedOption.Builder newBuilder2 = UninterpretedOption.newBuilder();
                        codedInputStream.a(newBuilder2, extensionRegistryLite);
                        addUninterpretedOption(newBuilder2.buildPartial());
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            public Builder mergeFrom(ServiceOptions serviceOptions) {
                if (serviceOptions == ServiceOptions.getDefaultInstance()) {
                    return this;
                }
                if (!serviceOptions.uninterpretedOption_.isEmpty()) {
                    if (this.result.uninterpretedOption_.isEmpty()) {
                        this.result.uninterpretedOption_ = new ArrayList();
                    }
                    this.result.uninterpretedOption_.addAll(serviceOptions.uninterpretedOption_);
                }
                mergeExtensionFields(serviceOptions);
                mergeUnknownFields(serviceOptions.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof ServiceOptions) {
                    return mergeFrom((ServiceOptions) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder setUninterpretedOption(int i, UninterpretedOption.Builder builder) {
                this.result.uninterpretedOption_.set(i, builder.build());
                return this;
            }

            public Builder setUninterpretedOption(int i, UninterpretedOption uninterpretedOption) {
                if (uninterpretedOption != null) {
                    this.result.uninterpretedOption_.set(i, uninterpretedOption);
                    return this;
                }
                throw new NullPointerException();
            }
        }

        static {
            DescriptorProtos.b();
            defaultInstance.initFields();
        }

        private ServiceOptions() {
            this.uninterpretedOption_ = Collections.emptyList();
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private ServiceOptions(boolean z) {
            this.uninterpretedOption_ = Collections.emptyList();
            this.memoizedSerializedSize = -1;
        }

        public static ServiceOptions getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.C;
        }

        private void initFields() {
        }

        public static Builder newBuilder() {
            return Builder.access$16900();
        }

        public static Builder newBuilder(ServiceOptions serviceOptions) {
            return newBuilder().mergeFrom(serviceOptions);
        }

        public static ServiceOptions parseDelimitedFrom(InputStream inputStream) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static ServiceOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static ServiceOptions parseFrom(ByteString byteString) {
            return ((Builder) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static ServiceOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static ServiceOptions parseFrom(CodedInputStream codedInputStream) {
            return ((Builder) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static ServiceOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static ServiceOptions parseFrom(InputStream inputStream) {
            return ((Builder) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static ServiceOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static ServiceOptions parseFrom(byte[] bArr) {
            return ((Builder) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static ServiceOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public ServiceOptions getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (UninterpretedOption uninterpretedOption : getUninterpretedOptionList()) {
                i2 += CodedOutputStream.g(999, uninterpretedOption);
            }
            int extensionsSerializedSize = i2 + extensionsSerializedSize() + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = extensionsSerializedSize;
            return extensionsSerializedSize;
        }

        public UninterpretedOption getUninterpretedOption(int i) {
            return this.uninterpretedOption_.get(i);
        }

        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        public List<UninterpretedOption> getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.D;
        }

        @Override // com.google.protobuf.GeneratedMessage.ExtendableMessage, com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            for (UninterpretedOption uninterpretedOption : getUninterpretedOptionList()) {
                if (!uninterpretedOption.isInitialized()) {
                    return false;
                }
            }
            return extensionsAreInitialized();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            GeneratedMessage.ExtendableMessage<MessageType>.ExtensionWriter newExtensionWriter = newExtensionWriter();
            for (UninterpretedOption uninterpretedOption : getUninterpretedOptionList()) {
                codedOutputStream.c(999, uninterpretedOption);
            }
            newExtensionWriter.a(536870912, codedOutputStream);
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class UninterpretedOption extends GeneratedMessage {
        public static final int DOUBLE_VALUE_FIELD_NUMBER = 6;
        public static final int IDENTIFIER_VALUE_FIELD_NUMBER = 3;
        public static final int NAME_FIELD_NUMBER = 2;
        public static final int NEGATIVE_INT_VALUE_FIELD_NUMBER = 5;
        public static final int POSITIVE_INT_VALUE_FIELD_NUMBER = 4;
        public static final int STRING_VALUE_FIELD_NUMBER = 7;
        private static final UninterpretedOption defaultInstance = new UninterpretedOption(true);
        private double doubleValue_;
        private boolean hasDoubleValue;
        private boolean hasIdentifierValue;
        private boolean hasNegativeIntValue;
        private boolean hasPositiveIntValue;
        private boolean hasStringValue;
        private String identifierValue_;
        private int memoizedSerializedSize;
        private List<NamePart> name_;
        private long negativeIntValue_;
        private long positiveIntValue_;
        private ByteString stringValue_;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static final class Builder extends GeneratedMessage.Builder<Builder> {
            private UninterpretedOption result;

            private Builder() {
            }

            static /* synthetic */ Builder access$19000() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public UninterpretedOption buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static Builder create() {
                Builder builder = new Builder();
                builder.result = new UninterpretedOption();
                return builder;
            }

            public Builder addAllName(Iterable<? extends NamePart> iterable) {
                if (this.result.name_.isEmpty()) {
                    this.result.name_ = new ArrayList();
                }
                GeneratedMessage.Builder.addAll(iterable, this.result.name_);
                return this;
            }

            public Builder addName(NamePart.Builder builder) {
                if (this.result.name_.isEmpty()) {
                    this.result.name_ = new ArrayList();
                }
                this.result.name_.add(builder.build());
                return this;
            }

            public Builder addName(NamePart namePart) {
                if (namePart != null) {
                    if (this.result.name_.isEmpty()) {
                        this.result.name_ = new ArrayList();
                    }
                    this.result.name_.add(namePart);
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public UninterpretedOption build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public UninterpretedOption buildPartial() {
                UninterpretedOption uninterpretedOption = this.result;
                if (uninterpretedOption != null) {
                    if (uninterpretedOption.name_ != Collections.EMPTY_LIST) {
                        UninterpretedOption uninterpretedOption2 = this.result;
                        uninterpretedOption2.name_ = Collections.unmodifiableList(uninterpretedOption2.name_);
                    }
                    UninterpretedOption uninterpretedOption3 = this.result;
                    this.result = null;
                    return uninterpretedOption3;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                if (this.result != null) {
                    this.result = new UninterpretedOption();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public Builder clearDoubleValue() {
                this.result.hasDoubleValue = false;
                this.result.doubleValue_ = 0.0d;
                return this;
            }

            public Builder clearIdentifierValue() {
                this.result.hasIdentifierValue = false;
                this.result.identifierValue_ = UninterpretedOption.getDefaultInstance().getIdentifierValue();
                return this;
            }

            public Builder clearName() {
                this.result.name_ = Collections.emptyList();
                return this;
            }

            public Builder clearNegativeIntValue() {
                this.result.hasNegativeIntValue = false;
                this.result.negativeIntValue_ = 0L;
                return this;
            }

            public Builder clearPositiveIntValue() {
                this.result.hasPositiveIntValue = false;
                this.result.positiveIntValue_ = 0L;
                return this;
            }

            public Builder clearStringValue() {
                this.result.hasStringValue = false;
                this.result.stringValue_ = UninterpretedOption.getDefaultInstance().getStringValue();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3clone() {
                return create().mergeFrom(this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public UninterpretedOption getDefaultInstanceForType() {
                return UninterpretedOption.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return UninterpretedOption.getDescriptor();
            }

            public double getDoubleValue() {
                return this.result.getDoubleValue();
            }

            public String getIdentifierValue() {
                return this.result.getIdentifierValue();
            }

            public NamePart getName(int i) {
                return this.result.getName(i);
            }

            public int getNameCount() {
                return this.result.getNameCount();
            }

            public List<NamePart> getNameList() {
                return Collections.unmodifiableList(this.result.name_);
            }

            public long getNegativeIntValue() {
                return this.result.getNegativeIntValue();
            }

            public long getPositiveIntValue() {
                return this.result.getPositiveIntValue();
            }

            public ByteString getStringValue() {
                return this.result.getStringValue();
            }

            public boolean hasDoubleValue() {
                return this.result.hasDoubleValue();
            }

            public boolean hasIdentifierValue() {
                return this.result.hasIdentifierValue();
            }

            public boolean hasNegativeIntValue() {
                return this.result.hasNegativeIntValue();
            }

            public boolean hasPositiveIntValue() {
                return this.result.hasPositiveIntValue();
            }

            public boolean hasStringValue() {
                return this.result.hasStringValue();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.Builder
            public UninterpretedOption internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 18) {
                        NamePart.Builder newBuilder2 = NamePart.newBuilder();
                        codedInputStream.a(newBuilder2, extensionRegistryLite);
                        addName(newBuilder2.buildPartial());
                    } else if (a == 26) {
                        setIdentifierValue(codedInputStream.k());
                    } else if (a == 32) {
                        setPositiveIntValue(codedInputStream.e());
                    } else if (a == 40) {
                        setNegativeIntValue(codedInputStream.f());
                    } else if (a == 49) {
                        setDoubleValue(codedInputStream.c());
                    } else if (a == 58) {
                        setStringValue(codedInputStream.l());
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            public Builder mergeFrom(UninterpretedOption uninterpretedOption) {
                if (uninterpretedOption == UninterpretedOption.getDefaultInstance()) {
                    return this;
                }
                if (!uninterpretedOption.name_.isEmpty()) {
                    if (this.result.name_.isEmpty()) {
                        this.result.name_ = new ArrayList();
                    }
                    this.result.name_.addAll(uninterpretedOption.name_);
                }
                if (uninterpretedOption.hasIdentifierValue()) {
                    setIdentifierValue(uninterpretedOption.getIdentifierValue());
                }
                if (uninterpretedOption.hasPositiveIntValue()) {
                    setPositiveIntValue(uninterpretedOption.getPositiveIntValue());
                }
                if (uninterpretedOption.hasNegativeIntValue()) {
                    setNegativeIntValue(uninterpretedOption.getNegativeIntValue());
                }
                if (uninterpretedOption.hasDoubleValue()) {
                    setDoubleValue(uninterpretedOption.getDoubleValue());
                }
                if (uninterpretedOption.hasStringValue()) {
                    setStringValue(uninterpretedOption.getStringValue());
                }
                mergeUnknownFields(uninterpretedOption.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof UninterpretedOption) {
                    return mergeFrom((UninterpretedOption) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder setDoubleValue(double d) {
                this.result.hasDoubleValue = true;
                this.result.doubleValue_ = d;
                return this;
            }

            public Builder setIdentifierValue(String str) {
                if (str != null) {
                    this.result.hasIdentifierValue = true;
                    this.result.identifierValue_ = str;
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setName(int i, NamePart.Builder builder) {
                this.result.name_.set(i, builder.build());
                return this;
            }

            public Builder setName(int i, NamePart namePart) {
                if (namePart != null) {
                    this.result.name_.set(i, namePart);
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setNegativeIntValue(long j) {
                this.result.hasNegativeIntValue = true;
                this.result.negativeIntValue_ = j;
                return this;
            }

            public Builder setPositiveIntValue(long j) {
                this.result.hasPositiveIntValue = true;
                this.result.positiveIntValue_ = j;
                return this;
            }

            public Builder setStringValue(ByteString byteString) {
                if (byteString != null) {
                    this.result.hasStringValue = true;
                    this.result.stringValue_ = byteString;
                    return this;
                }
                throw new NullPointerException();
            }
        }

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static final class NamePart extends GeneratedMessage {
            public static final int IS_EXTENSION_FIELD_NUMBER = 2;
            public static final int NAME_PART_FIELD_NUMBER = 1;
            private static final NamePart defaultInstance = new NamePart(true);
            private boolean hasIsExtension;
            private boolean hasNamePart;
            private boolean isExtension_;
            private int memoizedSerializedSize;
            private String namePart_;

            /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
            public static final class Builder extends GeneratedMessage.Builder<Builder> {
                private NamePart result;

                private Builder() {
                }

                static /* synthetic */ Builder access$18300() {
                    return create();
                }

                /* JADX INFO: Access modifiers changed from: private */
                public NamePart buildParsed() {
                    if (isInitialized()) {
                        return buildPartial();
                    }
                    throw newUninitializedMessageException((Message) this.result).b();
                }

                private static Builder create() {
                    Builder builder = new Builder();
                    builder.result = new NamePart();
                    return builder;
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public NamePart build() {
                    if (this.result == null || isInitialized()) {
                        return buildPartial();
                    }
                    throw newUninitializedMessageException((Message) this.result);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public NamePart buildPartial() {
                    NamePart namePart = this.result;
                    if (namePart != null) {
                        this.result = null;
                        return namePart;
                    }
                    throw new IllegalStateException("build() has already been called on this Builder.");
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Builder clear() {
                    if (this.result != null) {
                        this.result = new NamePart();
                        return this;
                    }
                    throw new IllegalStateException("Cannot call clear() after build().");
                }

                public Builder clearIsExtension() {
                    this.result.hasIsExtension = false;
                    this.result.isExtension_ = false;
                    return this;
                }

                public Builder clearNamePart() {
                    this.result.hasNamePart = false;
                    this.result.namePart_ = NamePart.getDefaultInstance().getNamePart();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
                /* renamed from: clone */
                public Builder mo3clone() {
                    return create().mergeFrom(this.result);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public NamePart getDefaultInstanceForType() {
                    return NamePart.getDefaultInstance();
                }

                @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
                public Descriptors.Descriptor getDescriptorForType() {
                    return NamePart.getDescriptor();
                }

                public boolean getIsExtension() {
                    return this.result.getIsExtension();
                }

                public String getNamePart() {
                    return this.result.getNamePart();
                }

                public boolean hasIsExtension() {
                    return this.result.hasIsExtension();
                }

                public boolean hasNamePart() {
                    return this.result.hasNamePart();
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.google.protobuf.GeneratedMessage.Builder
                public NamePart internalGetResult() {
                    return this.result;
                }

                @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
                public boolean isInitialized() {
                    return this.result.isInitialized();
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                    while (true) {
                        int a = codedInputStream.a();
                        if (a == 0) {
                            break;
                        } else if (a == 10) {
                            setNamePart(codedInputStream.k());
                        } else if (a == 16) {
                            setIsExtension(codedInputStream.j());
                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                            break;
                        }
                    }
                    setUnknownFields(newBuilder.build());
                    return this;
                }

                public Builder mergeFrom(NamePart namePart) {
                    if (namePart == NamePart.getDefaultInstance()) {
                        return this;
                    }
                    if (namePart.hasNamePart()) {
                        setNamePart(namePart.getNamePart());
                    }
                    if (namePart.hasIsExtension()) {
                        setIsExtension(namePart.getIsExtension());
                    }
                    mergeUnknownFields(namePart.getUnknownFields());
                    return this;
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Builder mergeFrom(Message message) {
                    if (message instanceof NamePart) {
                        return mergeFrom((NamePart) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder setIsExtension(boolean z) {
                    this.result.hasIsExtension = true;
                    this.result.isExtension_ = z;
                    return this;
                }

                public Builder setNamePart(String str) {
                    if (str != null) {
                        this.result.hasNamePart = true;
                        this.result.namePart_ = str;
                        return this;
                    }
                    throw new NullPointerException();
                }
            }

            static {
                DescriptorProtos.b();
                defaultInstance.initFields();
            }

            private NamePart() {
                this.namePart_ = a.d;
                this.isExtension_ = false;
                this.memoizedSerializedSize = -1;
                initFields();
            }

            private NamePart(boolean z) {
                this.namePart_ = a.d;
                this.isExtension_ = false;
                this.memoizedSerializedSize = -1;
            }

            public static NamePart getDefaultInstance() {
                return defaultInstance;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DescriptorProtos.I;
            }

            private void initFields() {
            }

            public static Builder newBuilder() {
                return Builder.access$18300();
            }

            public static Builder newBuilder(NamePart namePart) {
                return newBuilder().mergeFrom(namePart);
            }

            public static NamePart parseDelimitedFrom(InputStream inputStream) {
                Builder newBuilder = newBuilder();
                if (newBuilder.mergeDelimitedFrom(inputStream)) {
                    return newBuilder.buildParsed();
                }
                return null;
            }

            public static NamePart parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                Builder newBuilder = newBuilder();
                if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                    return newBuilder.buildParsed();
                }
                return null;
            }

            public static NamePart parseFrom(ByteString byteString) {
                return ((Builder) newBuilder().mergeFrom(byteString)).buildParsed();
            }

            public static NamePart parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return ((Builder) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
            }

            public static NamePart parseFrom(CodedInputStream codedInputStream) {
                return ((Builder) newBuilder().mergeFrom(codedInputStream)).buildParsed();
            }

            public static NamePart parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
            }

            public static NamePart parseFrom(InputStream inputStream) {
                return ((Builder) newBuilder().mergeFrom(inputStream)).buildParsed();
            }

            public static NamePart parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return ((Builder) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
            }

            public static NamePart parseFrom(byte[] bArr) {
                return ((Builder) newBuilder().mergeFrom(bArr)).buildParsed();
            }

            public static NamePart parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return ((Builder) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public NamePart getDefaultInstanceForType() {
                return defaultInstance;
            }

            public boolean getIsExtension() {
                return this.isExtension_;
            }

            public String getNamePart() {
                return this.namePart_;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSerializedSize;
                if (i != -1) {
                    return i;
                }
                int b = hasNamePart() ? 0 + CodedOutputStream.b(1, getNamePart()) : 0;
                if (hasIsExtension()) {
                    b += CodedOutputStream.b(2, getIsExtension());
                }
                int serializedSize = b + getUnknownFields().getSerializedSize();
                this.memoizedSerializedSize = serializedSize;
                return serializedSize;
            }

            public boolean hasIsExtension() {
                return this.hasIsExtension;
            }

            public boolean hasNamePart() {
                return this.hasNamePart;
            }

            @Override // com.google.protobuf.GeneratedMessage
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.J;
            }

            @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public final boolean isInitialized() {
                return this.hasNamePart && this.hasIsExtension;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder newBuilderForType() {
                return newBuilder();
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder toBuilder() {
                return newBuilder(this);
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) {
                getSerializedSize();
                if (hasNamePart()) {
                    codedOutputStream.a(1, getNamePart());
                }
                if (hasIsExtension()) {
                    codedOutputStream.a(2, getIsExtension());
                }
                getUnknownFields().writeTo(codedOutputStream);
            }
        }

        static {
            DescriptorProtos.b();
            defaultInstance.initFields();
        }

        private UninterpretedOption() {
            this.name_ = Collections.emptyList();
            this.identifierValue_ = a.d;
            this.positiveIntValue_ = 0L;
            this.negativeIntValue_ = 0L;
            this.doubleValue_ = 0.0d;
            this.stringValue_ = ByteString.a;
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private UninterpretedOption(boolean z) {
            this.name_ = Collections.emptyList();
            this.identifierValue_ = a.d;
            this.positiveIntValue_ = 0L;
            this.negativeIntValue_ = 0L;
            this.doubleValue_ = 0.0d;
            this.stringValue_ = ByteString.a;
            this.memoizedSerializedSize = -1;
        }

        public static UninterpretedOption getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.G;
        }

        private void initFields() {
        }

        public static Builder newBuilder() {
            return Builder.access$19000();
        }

        public static Builder newBuilder(UninterpretedOption uninterpretedOption) {
            return newBuilder().mergeFrom(uninterpretedOption);
        }

        public static UninterpretedOption parseDelimitedFrom(InputStream inputStream) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static UninterpretedOption parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            Builder newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static UninterpretedOption parseFrom(ByteString byteString) {
            return ((Builder) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static UninterpretedOption parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static UninterpretedOption parseFrom(CodedInputStream codedInputStream) {
            return ((Builder) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static UninterpretedOption parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static UninterpretedOption parseFrom(InputStream inputStream) {
            return ((Builder) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static UninterpretedOption parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static UninterpretedOption parseFrom(byte[] bArr) {
            return ((Builder) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static UninterpretedOption parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((Builder) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public UninterpretedOption getDefaultInstanceForType() {
            return defaultInstance;
        }

        public double getDoubleValue() {
            return this.doubleValue_;
        }

        public String getIdentifierValue() {
            return this.identifierValue_;
        }

        public NamePart getName(int i) {
            return this.name_.get(i);
        }

        public int getNameCount() {
            return this.name_.size();
        }

        public List<NamePart> getNameList() {
            return this.name_;
        }

        public long getNegativeIntValue() {
            return this.negativeIntValue_;
        }

        public long getPositiveIntValue() {
            return this.positiveIntValue_;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (NamePart namePart : getNameList()) {
                i2 += CodedOutputStream.g(2, namePart);
            }
            if (hasIdentifierValue()) {
                i2 += CodedOutputStream.b(3, getIdentifierValue());
            }
            if (hasPositiveIntValue()) {
                i2 += CodedOutputStream.f(4, getPositiveIntValue());
            }
            if (hasNegativeIntValue()) {
                i2 += CodedOutputStream.g(5, getNegativeIntValue());
            }
            if (hasDoubleValue()) {
                i2 += CodedOutputStream.b(6, getDoubleValue());
            }
            if (hasStringValue()) {
                i2 += CodedOutputStream.c(7, getStringValue());
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public ByteString getStringValue() {
            return this.stringValue_;
        }

        public boolean hasDoubleValue() {
            return this.hasDoubleValue;
        }

        public boolean hasIdentifierValue() {
            return this.hasIdentifierValue;
        }

        public boolean hasNegativeIntValue() {
            return this.hasNegativeIntValue;
        }

        public boolean hasPositiveIntValue() {
            return this.hasPositiveIntValue;
        }

        public boolean hasStringValue() {
            return this.hasStringValue;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.H;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            for (NamePart namePart : getNameList()) {
                if (!namePart.isInitialized()) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            for (NamePart namePart : getNameList()) {
                codedOutputStream.c(2, namePart);
            }
            if (hasIdentifierValue()) {
                codedOutputStream.a(3, getIdentifierValue());
            }
            if (hasPositiveIntValue()) {
                codedOutputStream.a(4, getPositiveIntValue());
            }
            if (hasNegativeIntValue()) {
                codedOutputStream.b(5, getNegativeIntValue());
            }
            if (hasDoubleValue()) {
                codedOutputStream.a(6, getDoubleValue());
            }
            if (hasStringValue()) {
                codedOutputStream.a(7, getStringValue());
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    static {
        Descriptors.FileDescriptor.a(new String[]{"\n google/protobuf/descriptor.proto\u0012\u000fgoogle.protobuf\"G\n\u0011FileDescriptorSet\u00122\n\u0004file\u0018\u0001 \u0003(\u000b2$.google.protobuf.FileDescriptorProto\"Ü\u0002\n\u0013FileDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007package\u0018\u0002 \u0001(\t\u0012\u0012\n\ndependency\u0018\u0003 \u0003(\t\u00126\n\fmessage_type\u0018\u0004 \u0003(\u000b2 .google.protobuf.DescriptorProto\u00127\n\tenum_type\u0018\u0005 \u0003(\u000b2$.google.protobuf.EnumDescriptorProto\u00128\n\u0007service\u0018\u0006 \u0003(\u000b2'.google.protobuf.ServiceDescriptorProto\u00128\n\textension\u0018\u0007 \u0003(\u000b2%.google.p", "rotobuf.FieldDescriptorProto\u0012-\n\u0007options\u0018\b \u0001(\u000b2\u001c.google.protobuf.FileOptions\"©\u0003\n\u000fDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u00124\n\u0005field\u0018\u0002 \u0003(\u000b2%.google.protobuf.FieldDescriptorProto\u00128\n\textension\u0018\u0006 \u0003(\u000b2%.google.protobuf.FieldDescriptorProto\u00125\n\u000bnested_type\u0018\u0003 \u0003(\u000b2 .google.protobuf.DescriptorProto\u00127\n\tenum_type\u0018\u0004 \u0003(\u000b2$.google.protobuf.EnumDescriptorProto\u0012H\n\u000fextension_range\u0018\u0005 \u0003(\u000b2/.google.protobuf.DescriptorProto.Extensi", "onRange\u00120\n\u0007options\u0018\u0007 \u0001(\u000b2\u001f.google.protobuf.MessageOptions\u001a,\n\u000eExtensionRange\u0012\r\n\u0005start\u0018\u0001 \u0001(\u0005\u0012\u000b\n\u0003end\u0018\u0002 \u0001(\u0005\"\u0094\u0005\n\u0014FieldDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u000e\n\u0006number\u0018\u0003 \u0001(\u0005\u0012:\n\u0005label\u0018\u0004 \u0001(\u000e2+.google.protobuf.FieldDescriptorProto.Label\u00128\n\u0004type\u0018\u0005 \u0001(\u000e2*.google.protobuf.FieldDescriptorProto.Type\u0012\u0011\n\ttype_name\u0018\u0006 \u0001(\t\u0012\u0010\n\bextendee\u0018\u0002 \u0001(\t\u0012\u0015\n\rdefault_value\u0018\u0007 \u0001(\t\u0012.\n\u0007options\u0018\b \u0001(\u000b2\u001d.google.protobuf.FieldOptions\"¶\u0002\n\u0004Type\u0012\u000f\n\u000bTYP", "E_DOUBLE\u0010\u0001\u0012\u000e\n\nTYPE_FLOAT\u0010\u0002\u0012\u000e\n\nTYPE_INT64\u0010\u0003\u0012\u000f\n\u000bTYPE_UINT64\u0010\u0004\u0012\u000e\n\nTYPE_INT32\u0010\u0005\u0012\u0010\n\fTYPE_FIXED64\u0010\u0006\u0012\u0010\n\fTYPE_FIXED32\u0010\u0007\u0012\r\n\tTYPE_BOOL\u0010\b\u0012\u000f\n\u000bTYPE_STRING\u0010\t\u0012\u000e\n\nTYPE_GROUP\u0010\n\u0012\u0010\n\fTYPE_MESSAGE\u0010\u000b\u0012\u000e\n\nTYPE_BYTES\u0010\f\u0012\u000f\n\u000bTYPE_UINT32\u0010\r\u0012\r\n\tTYPE_ENUM\u0010\u000e\u0012\u0011\n\rTYPE_SFIXED32\u0010\u000f\u0012\u0011\n\rTYPE_SFIXED64\u0010\u0010\u0012\u000f\n\u000bTYPE_SINT32\u0010\u0011\u0012\u000f\n\u000bTYPE_SINT64\u0010\u0012\"C\n\u0005Label\u0012\u0012\n\u000eLABEL_OPTIONAL\u0010\u0001\u0012\u0012\n\u000eLABEL_REQUIRED\u0010\u0002\u0012\u0012\n\u000eLABEL_REPEATED\u0010\u0003\"\u008c\u0001\n\u0013EnumDescriptorProto\u0012\f\n\u0004name\u0018\u0001", " \u0001(\t\u00128\n\u0005value\u0018\u0002 \u0003(\u000b2).google.protobuf.EnumValueDescriptorProto\u0012-\n\u0007options\u0018\u0003 \u0001(\u000b2\u001c.google.protobuf.EnumOptions\"l\n\u0018EnumValueDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u000e\n\u0006number\u0018\u0002 \u0001(\u0005\u00122\n\u0007options\u0018\u0003 \u0001(\u000b2!.google.protobuf.EnumValueOptions\"\u0090\u0001\n\u0016ServiceDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u00126\n\u0006method\u0018\u0002 \u0003(\u000b2&.google.protobuf.MethodDescriptorProto\u00120\n\u0007options\u0018\u0003 \u0001(\u000b2\u001f.google.protobuf.ServiceOptions\"\u007f\n\u0015MethodDescriptorProto\u0012\f\n\u0004name\u0018", "\u0001 \u0001(\t\u0012\u0012\n\ninput_type\u0018\u0002 \u0001(\t\u0012\u0013\n\u000boutput_type\u0018\u0003 \u0001(\t\u0012/\n\u0007options\u0018\u0004 \u0001(\u000b2\u001e.google.protobuf.MethodOptions\"¤\u0003\n\u000bFileOptions\u0012\u0014\n\fjava_package\u0018\u0001 \u0001(\t\u0012\u001c\n\u0014java_outer_classname\u0018\b \u0001(\t\u0012\"\n\u0013java_multiple_files\u0018\n \u0001(\b:\u0005false\u0012F\n\foptimize_for\u0018\t \u0001(\u000e2).google.protobuf.FileOptions.OptimizeMode:\u0005SPEED\u0012!\n\u0013cc_generic_services\u0018\u0010 \u0001(\b:\u0004true\u0012#\n\u0015java_generic_services\u0018\u0011 \u0001(\b:\u0004true\u0012!\n\u0013py_generic_services\u0018\u0012 \u0001(\b:\u0004true\u0012C\n\u0014uninterpreted_opti", "on\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption\":\n\fOptimizeMode\u0012\t\n\u0005SPEED\u0010\u0001\u0012\r\n\tCODE_SIZE\u0010\u0002\u0012\u0010\n\fLITE_RUNTIME\u0010\u0003*\t\bè\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"¸\u0001\n\u000eMessageOptions\u0012&\n\u0017message_set_wire_format\u0018\u0001 \u0001(\b:\u0005false\u0012.\n\u001fno_standard_descriptor_accessor\u0018\u0002 \u0001(\b:\u0005false\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\bè\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"\u0094\u0002\n\fFieldOptions\u0012:\n\u0005ctype\u0018\u0001 \u0001(\u000e2#.google.protobuf.FieldOptions.CType:\u0006STRING\u0012\u000e\n\u0006packed\u0018\u0002 \u0001(\b\u0012\u0019", "\n\ndeprecated\u0018\u0003 \u0001(\b:\u0005false\u0012\u001c\n\u0014experimental_map_key\u0018\t \u0001(\t\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption\"/\n\u0005CType\u0012\n\n\u0006STRING\u0010\u0000\u0012\b\n\u0004CORD\u0010\u0001\u0012\u0010\n\fSTRING_PIECE\u0010\u0002*\t\bè\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"]\n\u000bEnumOptions\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\bè\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"b\n\u0010EnumValueOptions\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\bè\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"`\n\u000eServiceOptions\u0012C\n", "\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\bè\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"_\n\rMethodOptions\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\bè\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"\u0085\u0002\n\u0013UninterpretedOption\u0012;\n\u0004name\u0018\u0002 \u0003(\u000b2-.google.protobuf.UninterpretedOption.NamePart\u0012\u0018\n\u0010identifier_value\u0018\u0003 \u0001(\t\u0012\u001a\n\u0012positive_int_value\u0018\u0004 \u0001(\u0004\u0012\u001a\n\u0012negative_int_value\u0018\u0005 \u0001(\u0003\u0012\u0014\n\fdouble_value\u0018\u0006 \u0001(\u0001\u0012\u0014\n\fstring_value\u0018\u0007 \u0001(\f\u001a3\n\bNamePart\u0012\u0011\n", "\tname_part\u0018\u0001 \u0002(\t\u0012\u0014\n\fis_extension\u0018\u0002 \u0002(\bB)\n\u0013com.google.protobufB\u0010DescriptorProtosH\u0001"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.google.protobuf.DescriptorProtos.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = DescriptorProtos.K = fileDescriptor;
                Descriptors.Descriptor unused2 = DescriptorProtos.a = DescriptorProtos.a().e().get(0);
                GeneratedMessage.FieldAccessorTable unused3 = DescriptorProtos.b = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.a, new String[]{"File"}, FileDescriptorSet.class, FileDescriptorSet.Builder.class);
                Descriptors.Descriptor unused4 = DescriptorProtos.c = DescriptorProtos.a().e().get(1);
                GeneratedMessage.FieldAccessorTable unused5 = DescriptorProtos.d = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.c, new String[]{"Name", "Package", "Dependency", "MessageType", "EnumType", "Service", "Extension", "Options"}, FileDescriptorProto.class, FileDescriptorProto.Builder.class);
                Descriptors.Descriptor unused6 = DescriptorProtos.e = DescriptorProtos.a().e().get(2);
                GeneratedMessage.FieldAccessorTable unused7 = DescriptorProtos.f = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.e, new String[]{"Name", "Field", "Extension", "NestedType", "EnumType", "ExtensionRange", "Options"}, DescriptorProto.class, DescriptorProto.Builder.class);
                Descriptors.Descriptor unused8 = DescriptorProtos.g = DescriptorProtos.e.getNestedTypes().get(0);
                GeneratedMessage.FieldAccessorTable unused9 = DescriptorProtos.h = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.g, new String[]{"Start", "End"}, DescriptorProto.ExtensionRange.class, DescriptorProto.ExtensionRange.Builder.class);
                Descriptors.Descriptor unused10 = DescriptorProtos.i = DescriptorProtos.a().e().get(3);
                GeneratedMessage.FieldAccessorTable unused11 = DescriptorProtos.j = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.i, new String[]{"Name", "Number", "Label", "Type", "TypeName", "Extendee", "DefaultValue", "Options"}, FieldDescriptorProto.class, FieldDescriptorProto.Builder.class);
                Descriptors.Descriptor unused12 = DescriptorProtos.k = DescriptorProtos.a().e().get(4);
                GeneratedMessage.FieldAccessorTable unused13 = DescriptorProtos.l = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.k, new String[]{"Name", "Value", "Options"}, EnumDescriptorProto.class, EnumDescriptorProto.Builder.class);
                Descriptors.Descriptor unused14 = DescriptorProtos.m = DescriptorProtos.a().e().get(5);
                GeneratedMessage.FieldAccessorTable unused15 = DescriptorProtos.n = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.m, new String[]{"Name", "Number", "Options"}, EnumValueDescriptorProto.class, EnumValueDescriptorProto.Builder.class);
                Descriptors.Descriptor unused16 = DescriptorProtos.o = DescriptorProtos.a().e().get(6);
                GeneratedMessage.FieldAccessorTable unused17 = DescriptorProtos.p = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.o, new String[]{"Name", "Method", "Options"}, ServiceDescriptorProto.class, ServiceDescriptorProto.Builder.class);
                Descriptors.Descriptor unused18 = DescriptorProtos.q = DescriptorProtos.a().e().get(7);
                GeneratedMessage.FieldAccessorTable unused19 = DescriptorProtos.r = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.q, new String[]{"Name", "InputType", "OutputType", "Options"}, MethodDescriptorProto.class, MethodDescriptorProto.Builder.class);
                Descriptors.Descriptor unused20 = DescriptorProtos.s = DescriptorProtos.a().e().get(8);
                GeneratedMessage.FieldAccessorTable unused21 = DescriptorProtos.t = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.s, new String[]{"JavaPackage", "JavaOuterClassname", "JavaMultipleFiles", "OptimizeFor", "CcGenericServices", "JavaGenericServices", "PyGenericServices", "UninterpretedOption"}, FileOptions.class, FileOptions.Builder.class);
                Descriptors.Descriptor unused22 = DescriptorProtos.u = DescriptorProtos.a().e().get(9);
                GeneratedMessage.FieldAccessorTable unused23 = DescriptorProtos.v = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.u, new String[]{"MessageSetWireFormat", "NoStandardDescriptorAccessor", "UninterpretedOption"}, MessageOptions.class, MessageOptions.Builder.class);
                Descriptors.Descriptor unused24 = DescriptorProtos.w = DescriptorProtos.a().e().get(10);
                GeneratedMessage.FieldAccessorTable unused25 = DescriptorProtos.x = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.w, new String[]{"Ctype", "Packed", "Deprecated", "ExperimentalMapKey", "UninterpretedOption"}, FieldOptions.class, FieldOptions.Builder.class);
                Descriptors.Descriptor unused26 = DescriptorProtos.y = DescriptorProtos.a().e().get(11);
                GeneratedMessage.FieldAccessorTable unused27 = DescriptorProtos.z = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.y, new String[]{"UninterpretedOption"}, EnumOptions.class, EnumOptions.Builder.class);
                Descriptors.Descriptor unused28 = DescriptorProtos.A = DescriptorProtos.a().e().get(12);
                GeneratedMessage.FieldAccessorTable unused29 = DescriptorProtos.B = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.A, new String[]{"UninterpretedOption"}, EnumValueOptions.class, EnumValueOptions.Builder.class);
                Descriptors.Descriptor unused30 = DescriptorProtos.C = DescriptorProtos.a().e().get(13);
                GeneratedMessage.FieldAccessorTable unused31 = DescriptorProtos.D = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.C, new String[]{"UninterpretedOption"}, ServiceOptions.class, ServiceOptions.Builder.class);
                Descriptors.Descriptor unused32 = DescriptorProtos.E = DescriptorProtos.a().e().get(14);
                GeneratedMessage.FieldAccessorTable unused33 = DescriptorProtos.F = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.E, new String[]{"UninterpretedOption"}, MethodOptions.class, MethodOptions.Builder.class);
                Descriptors.Descriptor unused34 = DescriptorProtos.G = DescriptorProtos.a().e().get(15);
                GeneratedMessage.FieldAccessorTable unused35 = DescriptorProtos.H = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.G, new String[]{"Name", "IdentifierValue", "PositiveIntValue", "NegativeIntValue", "DoubleValue", "StringValue"}, UninterpretedOption.class, UninterpretedOption.Builder.class);
                Descriptors.Descriptor unused36 = DescriptorProtos.I = DescriptorProtos.G.getNestedTypes().get(0);
                GeneratedMessage.FieldAccessorTable unused37 = DescriptorProtos.J = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.I, new String[]{"NamePart", "IsExtension"}, UninterpretedOption.NamePart.class, UninterpretedOption.NamePart.Builder.class);
                return null;
            }
        });
    }

    private DescriptorProtos() {
    }

    public static Descriptors.FileDescriptor a() {
        return K;
    }

    public static void a(ExtensionRegistry extensionRegistry) {
    }

    public static void b() {
    }
}
