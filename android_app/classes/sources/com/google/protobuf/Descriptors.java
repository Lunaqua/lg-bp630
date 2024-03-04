package com.google.protobuf;

import android.support.v4.d.a.a;
import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.FieldSet;
import com.google.protobuf.Internal;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.TextFormat;
import com.google.protobuf.WireFormat;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class Descriptors {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.protobuf.Descriptors$1  reason: invalid class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] b = new int[FieldDescriptor.JavaType.values().length];

        static {
            try {
                b[FieldDescriptor.JavaType.ENUM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[FieldDescriptor.JavaType.MESSAGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            a = new int[FieldDescriptor.Type.values().length];
            try {
                a[FieldDescriptor.Type.INT32.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[FieldDescriptor.Type.SINT32.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[FieldDescriptor.Type.SFIXED32.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[FieldDescriptor.Type.UINT32.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[FieldDescriptor.Type.FIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[FieldDescriptor.Type.INT64.ordinal()] = 6;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[FieldDescriptor.Type.SINT64.ordinal()] = 7;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[FieldDescriptor.Type.SFIXED64.ordinal()] = 8;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                a[FieldDescriptor.Type.UINT64.ordinal()] = 9;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                a[FieldDescriptor.Type.FIXED64.ordinal()] = 10;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                a[FieldDescriptor.Type.FLOAT.ordinal()] = 11;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                a[FieldDescriptor.Type.DOUBLE.ordinal()] = 12;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                a[FieldDescriptor.Type.BOOL.ordinal()] = 13;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                a[FieldDescriptor.Type.STRING.ordinal()] = 14;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                a[FieldDescriptor.Type.BYTES.ordinal()] = 15;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                a[FieldDescriptor.Type.ENUM.ordinal()] = 16;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                a[FieldDescriptor.Type.MESSAGE.ordinal()] = 17;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                a[FieldDescriptor.Type.GROUP.ordinal()] = 18;
            } catch (NoSuchFieldError unused20) {
            }
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class Descriptor implements GenericDescriptor {
        private final Descriptor containingType;
        private final EnumDescriptor[] enumTypes;
        private final FieldDescriptor[] extensions;
        private final FieldDescriptor[] fields;
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private final Descriptor[] nestedTypes;
        private DescriptorProtos.DescriptorProto proto;

        private Descriptor(DescriptorProtos.DescriptorProto descriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i) {
            this.index = i;
            this.proto = descriptorProto;
            this.fullName = Descriptors.b(fileDescriptor, descriptor, descriptorProto.getName());
            this.file = fileDescriptor;
            this.containingType = descriptor;
            this.nestedTypes = new Descriptor[descriptorProto.getNestedTypeCount()];
            for (int i2 = 0; i2 < descriptorProto.getNestedTypeCount(); i2++) {
                this.nestedTypes[i2] = new Descriptor(descriptorProto.getNestedType(i2), fileDescriptor, this, i2);
            }
            this.enumTypes = new EnumDescriptor[descriptorProto.getEnumTypeCount()];
            for (int i3 = 0; i3 < descriptorProto.getEnumTypeCount(); i3++) {
                this.enumTypes[i3] = new EnumDescriptor(descriptorProto.getEnumType(i3), fileDescriptor, this, i3, null);
            }
            this.fields = new FieldDescriptor[descriptorProto.getFieldCount()];
            for (int i4 = 0; i4 < descriptorProto.getFieldCount(); i4++) {
                this.fields[i4] = new FieldDescriptor(descriptorProto.getField(i4), fileDescriptor, this, i4, false, null);
            }
            this.extensions = new FieldDescriptor[descriptorProto.getExtensionCount()];
            for (int i5 = 0; i5 < descriptorProto.getExtensionCount(); i5++) {
                this.extensions[i5] = new FieldDescriptor(descriptorProto.getExtension(i5), fileDescriptor, this, i5, true, null);
            }
            fileDescriptor.g.a(this);
        }

        /* synthetic */ Descriptor(DescriptorProtos.DescriptorProto descriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i, AnonymousClass1 anonymousClass1) {
            this(descriptorProto, fileDescriptor, descriptor, i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void crossLink() {
            for (Descriptor descriptor : this.nestedTypes) {
                descriptor.crossLink();
            }
            for (FieldDescriptor fieldDescriptor : this.fields) {
                fieldDescriptor.crossLink();
            }
            for (FieldDescriptor fieldDescriptor2 : this.extensions) {
                fieldDescriptor2.crossLink();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.DescriptorProto descriptorProto) {
            this.proto = descriptorProto;
            int i = 0;
            int i2 = 0;
            while (true) {
                Descriptor[] descriptorArr = this.nestedTypes;
                if (i2 >= descriptorArr.length) {
                    break;
                }
                descriptorArr[i2].setProto(descriptorProto.getNestedType(i2));
                i2++;
            }
            int i3 = 0;
            while (true) {
                EnumDescriptor[] enumDescriptorArr = this.enumTypes;
                if (i3 >= enumDescriptorArr.length) {
                    break;
                }
                enumDescriptorArr[i3].setProto(descriptorProto.getEnumType(i3));
                i3++;
            }
            int i4 = 0;
            while (true) {
                FieldDescriptor[] fieldDescriptorArr = this.fields;
                if (i4 >= fieldDescriptorArr.length) {
                    break;
                }
                fieldDescriptorArr[i4].setProto(descriptorProto.getField(i4));
                i4++;
            }
            while (true) {
                FieldDescriptor[] fieldDescriptorArr2 = this.extensions;
                if (i >= fieldDescriptorArr2.length) {
                    return;
                }
                fieldDescriptorArr2[i].setProto(descriptorProto.getExtension(i));
                i++;
            }
        }

        public EnumDescriptor findEnumTypeByName(String str) {
            DescriptorPool descriptorPool = this.file.g;
            GenericDescriptor a = descriptorPool.a(this.fullName + '.' + str);
            if (a == null || !(a instanceof EnumDescriptor)) {
                return null;
            }
            return (EnumDescriptor) a;
        }

        public FieldDescriptor findFieldByName(String str) {
            DescriptorPool descriptorPool = this.file.g;
            GenericDescriptor a = descriptorPool.a(this.fullName + '.' + str);
            if (a == null || !(a instanceof FieldDescriptor)) {
                return null;
            }
            return (FieldDescriptor) a;
        }

        public FieldDescriptor findFieldByNumber(int i) {
            return (FieldDescriptor) this.file.g.d.get(new DescriptorPool.DescriptorIntPair(this, i));
        }

        public Descriptor findNestedTypeByName(String str) {
            DescriptorPool descriptorPool = this.file.g;
            GenericDescriptor a = descriptorPool.a(this.fullName + '.' + str);
            if (a == null || !(a instanceof Descriptor)) {
                return null;
            }
            return (Descriptor) a;
        }

        public Descriptor getContainingType() {
            return this.containingType;
        }

        public List<EnumDescriptor> getEnumTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
        }

        public List<FieldDescriptor> getExtensions() {
            return Collections.unmodifiableList(Arrays.asList(this.extensions));
        }

        public List<FieldDescriptor> getFields() {
            return Collections.unmodifiableList(Arrays.asList(this.fields));
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.file;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        public int getIndex() {
            return this.index;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        public List<Descriptor> getNestedTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.nestedTypes));
        }

        public DescriptorProtos.MessageOptions getOptions() {
            return this.proto.getOptions();
        }

        public boolean isExtensionNumber(int i) {
            for (DescriptorProtos.DescriptorProto.ExtensionRange extensionRange : this.proto.getExtensionRangeList()) {
                if (extensionRange.getStart() <= i && i < extensionRange.getEnd()) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.DescriptorProto toProto() {
            return this.proto;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class DescriptorPool {
        static final /* synthetic */ boolean a = !Descriptors.class.desiredAssertionStatus();
        private final DescriptorPool[] b;
        private final Map<String, GenericDescriptor> c = new HashMap();
        private final Map<DescriptorIntPair, FieldDescriptor> d = new HashMap();
        private final Map<DescriptorIntPair, EnumValueDescriptor> e = new HashMap();

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static final class DescriptorIntPair {
            private final GenericDescriptor a;
            private final int b;

            DescriptorIntPair(GenericDescriptor genericDescriptor, int i) {
                this.a = genericDescriptor;
                this.b = i;
            }

            public boolean equals(Object obj) {
                if (obj instanceof DescriptorIntPair) {
                    DescriptorIntPair descriptorIntPair = (DescriptorIntPair) obj;
                    return this.a == descriptorIntPair.a && this.b == descriptorIntPair.b;
                }
                return false;
            }

            public int hashCode() {
                return (this.a.hashCode() * a.a) + this.b;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static final class PackageDescriptor implements GenericDescriptor {
            private final FileDescriptor file;
            private final String fullName;
            private final String name;

            PackageDescriptor(String str, String str2, FileDescriptor fileDescriptor) {
                this.file = fileDescriptor;
                this.fullName = str2;
                this.name = str;
            }

            @Override // com.google.protobuf.Descriptors.GenericDescriptor
            public FileDescriptor getFile() {
                return this.file;
            }

            @Override // com.google.protobuf.Descriptors.GenericDescriptor
            public String getFullName() {
                return this.fullName;
            }

            @Override // com.google.protobuf.Descriptors.GenericDescriptor
            public String getName() {
                return this.name;
            }

            @Override // com.google.protobuf.Descriptors.GenericDescriptor
            public Message toProto() {
                return this.file.a();
            }
        }

        DescriptorPool(FileDescriptor[] fileDescriptorArr) {
            this.b = new DescriptorPool[fileDescriptorArr.length];
            for (int i = 0; i < fileDescriptorArr.length; i++) {
                this.b[i] = fileDescriptorArr[i].g;
            }
            for (FileDescriptor fileDescriptor : fileDescriptorArr) {
                try {
                    a(fileDescriptor.c(), fileDescriptor);
                } catch (DescriptorValidationException unused) {
                    if (!a) {
                        throw new AssertionError();
                    }
                }
            }
        }

        static void b(GenericDescriptor genericDescriptor) {
            String name = genericDescriptor.getName();
            if (name.length() == 0) {
                throw new DescriptorValidationException(genericDescriptor, "Missing name.", (AnonymousClass1) null);
            }
            boolean z = true;
            for (int i = 0; i < name.length(); i++) {
                char charAt = name.charAt(i);
                if (charAt >= 128) {
                    z = false;
                }
                if (!Character.isLetter(charAt) && charAt != '_' && (!Character.isDigit(charAt) || i <= 0)) {
                    z = false;
                }
            }
            if (z) {
                return;
            }
            throw new DescriptorValidationException(genericDescriptor, '\"' + name + "\" is not a valid identifier.", (AnonymousClass1) null);
        }

        GenericDescriptor a(String str) {
            GenericDescriptor genericDescriptor = this.c.get(str);
            if (genericDescriptor != null) {
                return genericDescriptor;
            }
            for (DescriptorPool descriptorPool : this.b) {
                GenericDescriptor genericDescriptor2 = descriptorPool.c.get(str);
                if (genericDescriptor2 != null) {
                    return genericDescriptor2;
                }
            }
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x0058 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0059  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        com.google.protobuf.Descriptors.GenericDescriptor a(java.lang.String r9, com.google.protobuf.Descriptors.GenericDescriptor r10) {
            /*
                r8 = this;
                java.lang.String r0 = "."
                boolean r1 = r9.startsWith(r0)
                if (r1 == 0) goto L12
                r0 = 1
                java.lang.String r0 = r9.substring(r0)
            Ld:
                com.google.protobuf.Descriptors$GenericDescriptor r0 = r8.a(r0)
                goto L56
            L12:
                r1 = 46
                int r1 = r9.indexOf(r1)
                r2 = -1
                if (r1 != r2) goto L1d
                r3 = r9
                goto L22
            L1d:
                r3 = 0
                java.lang.String r3 = r9.substring(r3, r1)
            L22:
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                java.lang.String r5 = r10.getFullName()
                r4.<init>(r5)
            L2b:
                int r5 = r4.lastIndexOf(r0)
                if (r5 != r2) goto L36
                com.google.protobuf.Descriptors$GenericDescriptor r0 = r8.a(r9)
                goto L56
            L36:
                int r6 = r5 + 1
                r4.setLength(r6)
                r4.append(r3)
                java.lang.String r7 = r4.toString()
                com.google.protobuf.Descriptors$GenericDescriptor r7 = r8.a(r7)
                if (r7 == 0) goto L76
                if (r1 == r2) goto L55
                r4.setLength(r6)
                r4.append(r9)
                java.lang.String r0 = r4.toString()
                goto Ld
            L55:
                r0 = r7
            L56:
                if (r0 == 0) goto L59
                return r0
            L59:
                com.google.protobuf.Descriptors$DescriptorValidationException r0 = new com.google.protobuf.Descriptors$DescriptorValidationException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                r2 = 34
                r1.append(r2)
                r1.append(r9)
                java.lang.String r9 = "\" is not defined."
                r1.append(r9)
                java.lang.String r9 = r1.toString()
                r1 = 0
                r0.<init>(r10, r9, r1)
                throw r0
            L76:
                r4.setLength(r5)
                goto L2b
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Descriptors.DescriptorPool.a(java.lang.String, com.google.protobuf.Descriptors$GenericDescriptor):com.google.protobuf.Descriptors$GenericDescriptor");
        }

        void a(EnumValueDescriptor enumValueDescriptor) {
            DescriptorIntPair descriptorIntPair = new DescriptorIntPair(enumValueDescriptor.getType(), enumValueDescriptor.getNumber());
            EnumValueDescriptor put = this.e.put(descriptorIntPair, enumValueDescriptor);
            if (put != null) {
                this.e.put(descriptorIntPair, put);
            }
        }

        void a(FieldDescriptor fieldDescriptor) {
            DescriptorIntPair descriptorIntPair = new DescriptorIntPair(fieldDescriptor.getContainingType(), fieldDescriptor.getNumber());
            FieldDescriptor put = this.d.put(descriptorIntPair, fieldDescriptor);
            if (put == null) {
                return;
            }
            this.d.put(descriptorIntPair, put);
            throw new DescriptorValidationException(fieldDescriptor, "Field number " + fieldDescriptor.getNumber() + "has already been used in \"" + fieldDescriptor.getContainingType().getFullName() + "\" by field \"" + put.getName() + "\".", (AnonymousClass1) null);
        }

        void a(GenericDescriptor genericDescriptor) {
            b(genericDescriptor);
            String fullName = genericDescriptor.getFullName();
            int lastIndexOf = fullName.lastIndexOf(46);
            GenericDescriptor put = this.c.put(fullName, genericDescriptor);
            if (put != null) {
                this.c.put(fullName, put);
                if (genericDescriptor.getFile() != put.getFile()) {
                    throw new DescriptorValidationException(genericDescriptor, '\"' + fullName + "\" is already defined in file \"" + put.getFile().b() + "\".", (AnonymousClass1) null);
                } else if (lastIndexOf == -1) {
                    throw new DescriptorValidationException(genericDescriptor, '\"' + fullName + "\" is already defined.", (AnonymousClass1) null);
                } else {
                    throw new DescriptorValidationException(genericDescriptor, '\"' + fullName.substring(lastIndexOf + 1) + "\" is already defined in \"" + fullName.substring(0, lastIndexOf) + "\".", (AnonymousClass1) null);
                }
            }
        }

        void a(String str, FileDescriptor fileDescriptor) {
            String substring;
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf == -1) {
                substring = str;
            } else {
                a(str.substring(0, lastIndexOf), fileDescriptor);
                substring = str.substring(lastIndexOf + 1);
            }
            GenericDescriptor put = this.c.put(str, new PackageDescriptor(substring, str, fileDescriptor));
            if (put != null) {
                this.c.put(str, put);
                if (put instanceof PackageDescriptor) {
                    return;
                }
                throw new DescriptorValidationException(fileDescriptor, '\"' + substring + "\" is already defined (as something other than a package) in file \"" + put.getFile().b() + "\".", (AnonymousClass1) null);
            }
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class DescriptorValidationException extends Exception {
        private static final long a = 5750205775490483148L;
        private final String b;
        private final Message c;
        private final String d;

        private DescriptorValidationException(FileDescriptor fileDescriptor, String str) {
            super(fileDescriptor.b() + ": " + str);
            this.b = fileDescriptor.b();
            this.c = fileDescriptor.a();
            this.d = str;
        }

        /* synthetic */ DescriptorValidationException(FileDescriptor fileDescriptor, String str, AnonymousClass1 anonymousClass1) {
            this(fileDescriptor, str);
        }

        private DescriptorValidationException(GenericDescriptor genericDescriptor, String str) {
            super(genericDescriptor.getFullName() + ": " + str);
            this.b = genericDescriptor.getFullName();
            this.c = genericDescriptor.toProto();
            this.d = str;
        }

        /* synthetic */ DescriptorValidationException(GenericDescriptor genericDescriptor, String str, AnonymousClass1 anonymousClass1) {
            this(genericDescriptor, str);
        }

        private DescriptorValidationException(GenericDescriptor genericDescriptor, String str, Throwable th) {
            this(genericDescriptor, str);
            initCause(th);
        }

        /* synthetic */ DescriptorValidationException(GenericDescriptor genericDescriptor, String str, Throwable th, AnonymousClass1 anonymousClass1) {
            this(genericDescriptor, str, th);
        }

        public String a() {
            return this.b;
        }

        public Message b() {
            return this.c;
        }

        public String c() {
            return this.d;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class EnumDescriptor implements GenericDescriptor, Internal.EnumLiteMap<EnumValueDescriptor> {
        private final Descriptor containingType;
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private DescriptorProtos.EnumDescriptorProto proto;
        private EnumValueDescriptor[] values;

        private EnumDescriptor(DescriptorProtos.EnumDescriptorProto enumDescriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i) {
            this.index = i;
            this.proto = enumDescriptorProto;
            this.fullName = Descriptors.b(fileDescriptor, descriptor, enumDescriptorProto.getName());
            this.file = fileDescriptor;
            this.containingType = descriptor;
            if (enumDescriptorProto.getValueCount() == 0) {
                throw new DescriptorValidationException(this, "Enums must contain at least one value.", (AnonymousClass1) null);
            }
            this.values = new EnumValueDescriptor[enumDescriptorProto.getValueCount()];
            for (int i2 = 0; i2 < enumDescriptorProto.getValueCount(); i2++) {
                this.values[i2] = new EnumValueDescriptor(enumDescriptorProto.getValue(i2), fileDescriptor, this, i2, null);
            }
            fileDescriptor.g.a(this);
        }

        /* synthetic */ EnumDescriptor(DescriptorProtos.EnumDescriptorProto enumDescriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i, AnonymousClass1 anonymousClass1) {
            this(enumDescriptorProto, fileDescriptor, descriptor, i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.EnumDescriptorProto enumDescriptorProto) {
            this.proto = enumDescriptorProto;
            int i = 0;
            while (true) {
                EnumValueDescriptor[] enumValueDescriptorArr = this.values;
                if (i >= enumValueDescriptorArr.length) {
                    return;
                }
                enumValueDescriptorArr[i].setProto(enumDescriptorProto.getValue(i));
                i++;
            }
        }

        public EnumValueDescriptor findValueByName(String str) {
            DescriptorPool descriptorPool = this.file.g;
            GenericDescriptor a = descriptorPool.a(this.fullName + '.' + str);
            if (a == null || !(a instanceof EnumValueDescriptor)) {
                return null;
            }
            return (EnumValueDescriptor) a;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.protobuf.Internal.EnumLiteMap
        public EnumValueDescriptor findValueByNumber(int i) {
            return (EnumValueDescriptor) this.file.g.e.get(new DescriptorPool.DescriptorIntPair(this, i));
        }

        public Descriptor getContainingType() {
            return this.containingType;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.file;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        public int getIndex() {
            return this.index;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        public DescriptorProtos.EnumOptions getOptions() {
            return this.proto.getOptions();
        }

        public List<EnumValueDescriptor> getValues() {
            return Collections.unmodifiableList(Arrays.asList(this.values));
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.EnumDescriptorProto toProto() {
            return this.proto;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class EnumValueDescriptor implements GenericDescriptor, Internal.EnumLite {
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private DescriptorProtos.EnumValueDescriptorProto proto;
        private final EnumDescriptor type;

        private EnumValueDescriptor(DescriptorProtos.EnumValueDescriptorProto enumValueDescriptorProto, FileDescriptor fileDescriptor, EnumDescriptor enumDescriptor, int i) {
            this.index = i;
            this.proto = enumValueDescriptorProto;
            this.file = fileDescriptor;
            this.type = enumDescriptor;
            this.fullName = enumDescriptor.getFullName() + '.' + enumValueDescriptorProto.getName();
            fileDescriptor.g.a((GenericDescriptor) this);
            fileDescriptor.g.a(this);
        }

        /* synthetic */ EnumValueDescriptor(DescriptorProtos.EnumValueDescriptorProto enumValueDescriptorProto, FileDescriptor fileDescriptor, EnumDescriptor enumDescriptor, int i, AnonymousClass1 anonymousClass1) {
            this(enumValueDescriptorProto, fileDescriptor, enumDescriptor, i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.EnumValueDescriptorProto enumValueDescriptorProto) {
            this.proto = enumValueDescriptorProto;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.file;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        public int getIndex() {
            return this.index;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public int getNumber() {
            return this.proto.getNumber();
        }

        public DescriptorProtos.EnumValueOptions getOptions() {
            return this.proto.getOptions();
        }

        public EnumDescriptor getType() {
            return this.type;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.EnumValueDescriptorProto toProto() {
            return this.proto;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class FieldDescriptor implements GenericDescriptor, FieldSet.FieldDescriptorLite<FieldDescriptor>, Comparable<FieldDescriptor> {
        private static final WireFormat.FieldType[] table = WireFormat.FieldType.values();
        private Descriptor containingType;
        private Object defaultValue;
        private EnumDescriptor enumType;
        private final Descriptor extensionScope;
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private Descriptor messageType;
        private DescriptorProtos.FieldDescriptorProto proto;
        private Type type;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public enum JavaType {
            INT(0),
            LONG(0L),
            FLOAT(Float.valueOf(0.0f)),
            DOUBLE(Double.valueOf(0.0d)),
            BOOLEAN(false),
            STRING(com.lge.media.launcher.a.d),
            BYTE_STRING(ByteString.a),
            ENUM(null),
            MESSAGE(null);
            
            private final Object j;

            JavaType(Object obj) {
                this.j = obj;
            }
        }

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public enum Type {
            DOUBLE(JavaType.DOUBLE),
            FLOAT(JavaType.FLOAT),
            INT64(JavaType.LONG),
            UINT64(JavaType.LONG),
            INT32(JavaType.INT),
            FIXED64(JavaType.LONG),
            FIXED32(JavaType.INT),
            BOOL(JavaType.BOOLEAN),
            STRING(JavaType.STRING),
            GROUP(JavaType.MESSAGE),
            MESSAGE(JavaType.MESSAGE),
            BYTES(JavaType.BYTE_STRING),
            UINT32(JavaType.INT),
            ENUM(JavaType.ENUM),
            SFIXED32(JavaType.INT),
            SFIXED64(JavaType.LONG),
            SINT32(JavaType.INT),
            SINT64(JavaType.LONG);
            
            private JavaType s;

            Type(JavaType javaType) {
                this.s = javaType;
            }

            public static Type a(DescriptorProtos.FieldDescriptorProto.Type type) {
                return values()[type.getNumber() - 1];
            }

            public DescriptorProtos.FieldDescriptorProto.Type a() {
                return DescriptorProtos.FieldDescriptorProto.Type.valueOf(ordinal() + 1);
            }

            public JavaType b() {
                return this.s;
            }
        }

        static {
            if (Type.values().length != DescriptorProtos.FieldDescriptorProto.Type.values().length) {
                throw new RuntimeException("descriptor.proto has a new declared type but Desrciptors.java wasn't updated.");
            }
        }

        private FieldDescriptor(DescriptorProtos.FieldDescriptorProto fieldDescriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i, boolean z) {
            this.index = i;
            this.proto = fieldDescriptorProto;
            this.fullName = Descriptors.b(fileDescriptor, descriptor, fieldDescriptorProto.getName());
            this.file = fileDescriptor;
            if (fieldDescriptorProto.hasType()) {
                this.type = Type.a(fieldDescriptorProto.getType());
            }
            if (getNumber() <= 0) {
                throw new DescriptorValidationException(this, "Field numbers must be positive integers.", (AnonymousClass1) null);
            }
            if (fieldDescriptorProto.getOptions().getPacked() && !isPackable()) {
                throw new DescriptorValidationException(this, "[packed = true] can only be specified for repeated primitive fields.", (AnonymousClass1) null);
            }
            if (z) {
                if (!fieldDescriptorProto.hasExtendee()) {
                    throw new DescriptorValidationException(this, "FieldDescriptorProto.extendee not set for extension field.", (AnonymousClass1) null);
                }
                this.containingType = null;
                if (descriptor != null) {
                    this.extensionScope = descriptor;
                    fileDescriptor.g.a((GenericDescriptor) this);
                }
            } else if (fieldDescriptorProto.hasExtendee()) {
                throw new DescriptorValidationException(this, "FieldDescriptorProto.extendee set for non-extension field.", (AnonymousClass1) null);
            } else {
                this.containingType = descriptor;
            }
            this.extensionScope = null;
            fileDescriptor.g.a((GenericDescriptor) this);
        }

        /* synthetic */ FieldDescriptor(DescriptorProtos.FieldDescriptorProto fieldDescriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i, boolean z, AnonymousClass1 anonymousClass1) {
            this(fieldDescriptorProto, fileDescriptor, descriptor, i, z);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void crossLink() {
            Object obj;
            Object valueOf;
            Type type;
            if (this.proto.hasExtendee()) {
                GenericDescriptor a = this.file.g.a(this.proto.getExtendee(), this);
                if (!(a instanceof Descriptor)) {
                    throw new DescriptorValidationException(this, '\"' + this.proto.getExtendee() + "\" is not a message type.", (AnonymousClass1) null);
                }
                this.containingType = (Descriptor) a;
                if (!getContainingType().isExtensionNumber(getNumber())) {
                    throw new DescriptorValidationException(this, '\"' + getContainingType().getFullName() + "\" does not declare " + getNumber() + " as an extension number.", (AnonymousClass1) null);
                }
            }
            if (this.proto.hasTypeName()) {
                GenericDescriptor a2 = this.file.g.a(this.proto.getTypeName(), this);
                if (!this.proto.hasType()) {
                    if (a2 instanceof Descriptor) {
                        type = Type.MESSAGE;
                    } else if (!(a2 instanceof EnumDescriptor)) {
                        throw new DescriptorValidationException(this, '\"' + this.proto.getTypeName() + "\" is not a type.", (AnonymousClass1) null);
                    } else {
                        type = Type.ENUM;
                    }
                    this.type = type;
                }
                if (getJavaType() == JavaType.MESSAGE) {
                    if (!(a2 instanceof Descriptor)) {
                        throw new DescriptorValidationException(this, '\"' + this.proto.getTypeName() + "\" is not a message type.", (AnonymousClass1) null);
                    }
                    this.messageType = (Descriptor) a2;
                    if (this.proto.hasDefaultValue()) {
                        throw new DescriptorValidationException(this, "Messages can't have default values.", (AnonymousClass1) null);
                    }
                } else if (getJavaType() != JavaType.ENUM) {
                    throw new DescriptorValidationException(this, "Field with primitive type has type_name.", (AnonymousClass1) null);
                } else {
                    if (!(a2 instanceof EnumDescriptor)) {
                        throw new DescriptorValidationException(this, '\"' + this.proto.getTypeName() + "\" is not an enum type.", (AnonymousClass1) null);
                    }
                    this.enumType = (EnumDescriptor) a2;
                }
            } else if (getJavaType() == JavaType.MESSAGE || getJavaType() == JavaType.ENUM) {
                throw new DescriptorValidationException(this, "Field with message or enum type missing type_name.", (AnonymousClass1) null);
            }
            if (!this.proto.hasDefaultValue()) {
                if (isRepeated()) {
                    obj = Collections.emptyList();
                } else {
                    int i = AnonymousClass1.b[getJavaType().ordinal()];
                    if (i == 1) {
                        obj = this.enumType.getValues().get(0);
                    } else if (i != 2) {
                        obj = getJavaType().j;
                    } else {
                        this.defaultValue = null;
                    }
                }
                this.defaultValue = obj;
            } else if (isRepeated()) {
                throw new DescriptorValidationException(this, "Repeated fields cannot have default values.", (AnonymousClass1) null);
            } else {
                try {
                    switch (getType()) {
                        case INT32:
                        case SINT32:
                        case SFIXED32:
                            valueOf = Integer.valueOf(TextFormat.c(this.proto.getDefaultValue()));
                            this.defaultValue = valueOf;
                            break;
                        case UINT32:
                        case FIXED32:
                            valueOf = Integer.valueOf(TextFormat.d(this.proto.getDefaultValue()));
                            this.defaultValue = valueOf;
                            break;
                        case INT64:
                        case SINT64:
                        case SFIXED64:
                            valueOf = Long.valueOf(TextFormat.e(this.proto.getDefaultValue()));
                            this.defaultValue = valueOf;
                            break;
                        case UINT64:
                        case FIXED64:
                            valueOf = Long.valueOf(TextFormat.f(this.proto.getDefaultValue()));
                            this.defaultValue = valueOf;
                            break;
                        case FLOAT:
                            valueOf = this.proto.getDefaultValue().equals("inf") ? Float.valueOf(Float.POSITIVE_INFINITY) : this.proto.getDefaultValue().equals("-inf") ? Float.valueOf(Float.NEGATIVE_INFINITY) : this.proto.getDefaultValue().equals("nan") ? Float.valueOf(Float.NaN) : Float.valueOf(this.proto.getDefaultValue());
                            this.defaultValue = valueOf;
                            break;
                        case DOUBLE:
                            valueOf = this.proto.getDefaultValue().equals("inf") ? Double.valueOf(Double.POSITIVE_INFINITY) : this.proto.getDefaultValue().equals("-inf") ? Double.valueOf(Double.NEGATIVE_INFINITY) : this.proto.getDefaultValue().equals("nan") ? Double.valueOf(Double.NaN) : Double.valueOf(this.proto.getDefaultValue());
                            this.defaultValue = valueOf;
                            break;
                        case BOOL:
                            valueOf = Boolean.valueOf(this.proto.getDefaultValue());
                            this.defaultValue = valueOf;
                            break;
                        case STRING:
                            valueOf = this.proto.getDefaultValue();
                            this.defaultValue = valueOf;
                            break;
                        case BYTES:
                            try {
                                this.defaultValue = TextFormat.a((CharSequence) this.proto.getDefaultValue());
                                break;
                            } catch (TextFormat.InvalidEscapeSequenceException e) {
                                throw new DescriptorValidationException(this, "Couldn't parse default value: " + e.getMessage(), e, null);
                            }
                        case ENUM:
                            this.defaultValue = this.enumType.findValueByName(this.proto.getDefaultValue());
                            if (this.defaultValue == null) {
                                throw new DescriptorValidationException(this, "Unknown enum default value: \"" + this.proto.getDefaultValue() + '\"', (AnonymousClass1) null);
                            }
                            break;
                        case MESSAGE:
                        case GROUP:
                            throw new DescriptorValidationException(this, "Message type had default value.", (AnonymousClass1) null);
                    }
                } catch (NumberFormatException e2) {
                    throw new DescriptorValidationException(this, "Could not parse default value: \"" + this.proto.getDefaultValue() + '\"', e2, null);
                }
            }
            if (!isExtension()) {
                this.file.g.a(this);
            }
            Descriptor descriptor = this.containingType;
            if (descriptor == null || !descriptor.getOptions().getMessageSetWireFormat()) {
                return;
            }
            if (!isExtension()) {
                throw new DescriptorValidationException(this, "MessageSets cannot have fields, only extensions.", (AnonymousClass1) null);
            }
            if (!isOptional() || getType() != Type.MESSAGE) {
                throw new DescriptorValidationException(this, "Extensions of MessageSets must be optional messages.", (AnonymousClass1) null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.FieldDescriptorProto fieldDescriptorProto) {
            this.proto = fieldDescriptorProto;
        }

        @Override // java.lang.Comparable
        public int compareTo(FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.containingType == this.containingType) {
                return getNumber() - fieldDescriptor.getNumber();
            }
            throw new IllegalArgumentException("FieldDescriptors can only be compared to other FieldDescriptors for fields of the same message type.");
        }

        public Descriptor getContainingType() {
            return this.containingType;
        }

        public Object getDefaultValue() {
            if (getJavaType() != JavaType.MESSAGE) {
                return this.defaultValue;
            }
            throw new UnsupportedOperationException("FieldDescriptor.getDefaultValue() called on an embedded message field.");
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public EnumDescriptor getEnumType() {
            if (getJavaType() == JavaType.ENUM) {
                return this.enumType;
            }
            throw new UnsupportedOperationException("This field is not of enum type.");
        }

        public Descriptor getExtensionScope() {
            if (isExtension()) {
                return this.extensionScope;
            }
            throw new UnsupportedOperationException("This field is not an extension.");
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.file;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        public int getIndex() {
            return this.index;
        }

        public JavaType getJavaType() {
            return this.type.b();
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public WireFormat.JavaType getLiteJavaType() {
            return getLiteType().getJavaType();
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public WireFormat.FieldType getLiteType() {
            return table[this.type.ordinal()];
        }

        public Descriptor getMessageType() {
            if (getJavaType() == JavaType.MESSAGE) {
                return this.messageType;
            }
            throw new UnsupportedOperationException("This field is not of message type.");
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public int getNumber() {
            return this.proto.getNumber();
        }

        public DescriptorProtos.FieldOptions getOptions() {
            return this.proto.getOptions();
        }

        public Type getType() {
            return this.type;
        }

        public boolean hasDefaultValue() {
            return this.proto.hasDefaultValue();
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public MessageLite.Builder internalMergeFrom(MessageLite.Builder builder, MessageLite messageLite) {
            return ((Message.Builder) builder).mergeFrom((Message) messageLite);
        }

        public boolean isExtension() {
            return this.proto.hasExtendee();
        }

        public boolean isOptional() {
            return this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_OPTIONAL;
        }

        public boolean isPackable() {
            return isRepeated() && getLiteType().isPackable();
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public boolean isPacked() {
            return getOptions().getPacked();
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public boolean isRepeated() {
            return this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_REPEATED;
        }

        public boolean isRequired() {
            return this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_REQUIRED;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.FieldDescriptorProto toProto() {
            return this.proto;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class FileDescriptor {
        private DescriptorProtos.FileDescriptorProto a;
        private final Descriptor[] b;
        private final EnumDescriptor[] c;
        private final ServiceDescriptor[] d;
        private final FieldDescriptor[] e;
        private final FileDescriptor[] f;
        private final DescriptorPool g;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public interface InternalDescriptorAssigner {
            ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor);
        }

        private FileDescriptor(DescriptorProtos.FileDescriptorProto fileDescriptorProto, FileDescriptor[] fileDescriptorArr, DescriptorPool descriptorPool) {
            this.g = descriptorPool;
            this.a = fileDescriptorProto;
            this.f = (FileDescriptor[]) fileDescriptorArr.clone();
            descriptorPool.a(c(), this);
            this.b = new Descriptor[fileDescriptorProto.getMessageTypeCount()];
            for (int i = 0; i < fileDescriptorProto.getMessageTypeCount(); i++) {
                this.b[i] = new Descriptor(fileDescriptorProto.getMessageType(i), this, null, i, null);
            }
            this.c = new EnumDescriptor[fileDescriptorProto.getEnumTypeCount()];
            for (int i2 = 0; i2 < fileDescriptorProto.getEnumTypeCount(); i2++) {
                this.c[i2] = new EnumDescriptor(fileDescriptorProto.getEnumType(i2), this, null, i2, null);
            }
            this.d = new ServiceDescriptor[fileDescriptorProto.getServiceCount()];
            for (int i3 = 0; i3 < fileDescriptorProto.getServiceCount(); i3++) {
                this.d[i3] = new ServiceDescriptor(fileDescriptorProto.getService(i3), this, i3, null);
            }
            this.e = new FieldDescriptor[fileDescriptorProto.getExtensionCount()];
            for (int i4 = 0; i4 < fileDescriptorProto.getExtensionCount(); i4++) {
                this.e[i4] = new FieldDescriptor(fileDescriptorProto.getExtension(i4), this, null, i4, true, null);
            }
        }

        public static FileDescriptor a(DescriptorProtos.FileDescriptorProto fileDescriptorProto, FileDescriptor[] fileDescriptorArr) {
            FileDescriptor fileDescriptor = new FileDescriptor(fileDescriptorProto, fileDescriptorArr, new DescriptorPool(fileDescriptorArr));
            if (fileDescriptorArr.length == fileDescriptorProto.getDependencyCount()) {
                for (int i = 0; i < fileDescriptorProto.getDependencyCount(); i++) {
                    if (!fileDescriptorArr[i].b().equals(fileDescriptorProto.getDependency(i))) {
                        throw new DescriptorValidationException(fileDescriptor, "Dependencies passed to FileDescriptor.buildFrom() don't match those listed in the FileDescriptorProto.", (AnonymousClass1) null);
                    }
                }
                fileDescriptor.j();
                return fileDescriptor;
            }
            throw new DescriptorValidationException(fileDescriptor, "Dependencies passed to FileDescriptor.buildFrom() don't match those listed in the FileDescriptorProto.", (AnonymousClass1) null);
        }

        private void a(DescriptorProtos.FileDescriptorProto fileDescriptorProto) {
            this.a = fileDescriptorProto;
            int i = 0;
            int i2 = 0;
            while (true) {
                Descriptor[] descriptorArr = this.b;
                if (i2 >= descriptorArr.length) {
                    break;
                }
                descriptorArr[i2].setProto(fileDescriptorProto.getMessageType(i2));
                i2++;
            }
            int i3 = 0;
            while (true) {
                EnumDescriptor[] enumDescriptorArr = this.c;
                if (i3 >= enumDescriptorArr.length) {
                    break;
                }
                enumDescriptorArr[i3].setProto(fileDescriptorProto.getEnumType(i3));
                i3++;
            }
            int i4 = 0;
            while (true) {
                ServiceDescriptor[] serviceDescriptorArr = this.d;
                if (i4 >= serviceDescriptorArr.length) {
                    break;
                }
                serviceDescriptorArr[i4].setProto(fileDescriptorProto.getService(i4));
                i4++;
            }
            while (true) {
                FieldDescriptor[] fieldDescriptorArr = this.e;
                if (i >= fieldDescriptorArr.length) {
                    return;
                }
                fieldDescriptorArr[i].setProto(fileDescriptorProto.getExtension(i));
                i++;
            }
        }

        public static void a(String[] strArr, FileDescriptor[] fileDescriptorArr, InternalDescriptorAssigner internalDescriptorAssigner) {
            DescriptorProtos.FileDescriptorProto parseFrom;
            StringBuilder sb = new StringBuilder();
            for (String str : strArr) {
                sb.append(str);
            }
            try {
                byte[] bytes = sb.toString().getBytes("ISO-8859-1");
                try {
                    try {
                        FileDescriptor a = a(DescriptorProtos.FileDescriptorProto.parseFrom(bytes), fileDescriptorArr);
                        ExtensionRegistry assignDescriptors = internalDescriptorAssigner.assignDescriptors(a);
                        if (assignDescriptors != null) {
                            try {
                                a.a(DescriptorProtos.FileDescriptorProto.parseFrom(bytes, assignDescriptors));
                            } catch (InvalidProtocolBufferException e) {
                                throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
                            }
                        }
                    } catch (DescriptorValidationException e2) {
                        throw new IllegalArgumentException("Invalid embedded descriptor for \"" + parseFrom.getName() + "\".", e2);
                    }
                } catch (InvalidProtocolBufferException e3) {
                    throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e3);
                }
            } catch (UnsupportedEncodingException e4) {
                throw new RuntimeException("Standard encoding ISO-8859-1 not supported by JVM.", e4);
            }
        }

        private void j() {
            for (Descriptor descriptor : this.b) {
                descriptor.crossLink();
            }
            for (ServiceDescriptor serviceDescriptor : this.d) {
                serviceDescriptor.crossLink();
            }
            for (FieldDescriptor fieldDescriptor : this.e) {
                fieldDescriptor.crossLink();
            }
        }

        public DescriptorProtos.FileDescriptorProto a() {
            return this.a;
        }

        public Descriptor a(String str) {
            if (str.indexOf(46) != -1) {
                return null;
            }
            if (c().length() > 0) {
                str = c() + '.' + str;
            }
            GenericDescriptor a = this.g.a(str);
            if (a != null && (a instanceof Descriptor) && a.getFile() == this) {
                return (Descriptor) a;
            }
            return null;
        }

        public EnumDescriptor b(String str) {
            if (str.indexOf(46) != -1) {
                return null;
            }
            if (c().length() > 0) {
                str = c() + '.' + str;
            }
            GenericDescriptor a = this.g.a(str);
            if (a != null && (a instanceof EnumDescriptor) && a.getFile() == this) {
                return (EnumDescriptor) a;
            }
            return null;
        }

        public String b() {
            return this.a.getName();
        }

        public ServiceDescriptor c(String str) {
            if (str.indexOf(46) != -1) {
                return null;
            }
            if (c().length() > 0) {
                str = c() + '.' + str;
            }
            GenericDescriptor a = this.g.a(str);
            if (a != null && (a instanceof ServiceDescriptor) && a.getFile() == this) {
                return (ServiceDescriptor) a;
            }
            return null;
        }

        public String c() {
            return this.a.getPackage();
        }

        public DescriptorProtos.FileOptions d() {
            return this.a.getOptions();
        }

        public FieldDescriptor d(String str) {
            if (str.indexOf(46) != -1) {
                return null;
            }
            if (c().length() > 0) {
                str = c() + '.' + str;
            }
            GenericDescriptor a = this.g.a(str);
            if (a != null && (a instanceof FieldDescriptor) && a.getFile() == this) {
                return (FieldDescriptor) a;
            }
            return null;
        }

        public List<Descriptor> e() {
            return Collections.unmodifiableList(Arrays.asList(this.b));
        }

        public List<EnumDescriptor> f() {
            return Collections.unmodifiableList(Arrays.asList(this.c));
        }

        public List<ServiceDescriptor> g() {
            return Collections.unmodifiableList(Arrays.asList(this.d));
        }

        public List<FieldDescriptor> h() {
            return Collections.unmodifiableList(Arrays.asList(this.e));
        }

        public List<FileDescriptor> i() {
            return Collections.unmodifiableList(Arrays.asList(this.f));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface GenericDescriptor {
        FileDescriptor getFile();

        String getFullName();

        String getName();

        Message toProto();
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class MethodDescriptor implements GenericDescriptor {
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private Descriptor inputType;
        private Descriptor outputType;
        private DescriptorProtos.MethodDescriptorProto proto;
        private final ServiceDescriptor service;

        private MethodDescriptor(DescriptorProtos.MethodDescriptorProto methodDescriptorProto, FileDescriptor fileDescriptor, ServiceDescriptor serviceDescriptor, int i) {
            this.index = i;
            this.proto = methodDescriptorProto;
            this.file = fileDescriptor;
            this.service = serviceDescriptor;
            this.fullName = serviceDescriptor.getFullName() + '.' + methodDescriptorProto.getName();
            fileDescriptor.g.a(this);
        }

        /* synthetic */ MethodDescriptor(DescriptorProtos.MethodDescriptorProto methodDescriptorProto, FileDescriptor fileDescriptor, ServiceDescriptor serviceDescriptor, int i, AnonymousClass1 anonymousClass1) {
            this(methodDescriptorProto, fileDescriptor, serviceDescriptor, i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void crossLink() {
            GenericDescriptor a = this.file.g.a(this.proto.getInputType(), this);
            if (!(a instanceof Descriptor)) {
                throw new DescriptorValidationException(this, '\"' + this.proto.getInputType() + "\" is not a message type.", (AnonymousClass1) null);
            }
            this.inputType = (Descriptor) a;
            GenericDescriptor a2 = this.file.g.a(this.proto.getOutputType(), this);
            if (a2 instanceof Descriptor) {
                this.outputType = (Descriptor) a2;
                return;
            }
            throw new DescriptorValidationException(this, '\"' + this.proto.getOutputType() + "\" is not a message type.", (AnonymousClass1) null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.MethodDescriptorProto methodDescriptorProto) {
            this.proto = methodDescriptorProto;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.file;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        public int getIndex() {
            return this.index;
        }

        public Descriptor getInputType() {
            return this.inputType;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        public DescriptorProtos.MethodOptions getOptions() {
            return this.proto.getOptions();
        }

        public Descriptor getOutputType() {
            return this.outputType;
        }

        public ServiceDescriptor getService() {
            return this.service;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.MethodDescriptorProto toProto() {
            return this.proto;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class ServiceDescriptor implements GenericDescriptor {
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private MethodDescriptor[] methods;
        private DescriptorProtos.ServiceDescriptorProto proto;

        private ServiceDescriptor(DescriptorProtos.ServiceDescriptorProto serviceDescriptorProto, FileDescriptor fileDescriptor, int i) {
            this.index = i;
            this.proto = serviceDescriptorProto;
            this.fullName = Descriptors.b(fileDescriptor, null, serviceDescriptorProto.getName());
            this.file = fileDescriptor;
            this.methods = new MethodDescriptor[serviceDescriptorProto.getMethodCount()];
            for (int i2 = 0; i2 < serviceDescriptorProto.getMethodCount(); i2++) {
                this.methods[i2] = new MethodDescriptor(serviceDescriptorProto.getMethod(i2), fileDescriptor, this, i2, null);
            }
            fileDescriptor.g.a(this);
        }

        /* synthetic */ ServiceDescriptor(DescriptorProtos.ServiceDescriptorProto serviceDescriptorProto, FileDescriptor fileDescriptor, int i, AnonymousClass1 anonymousClass1) {
            this(serviceDescriptorProto, fileDescriptor, i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void crossLink() {
            for (MethodDescriptor methodDescriptor : this.methods) {
                methodDescriptor.crossLink();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.ServiceDescriptorProto serviceDescriptorProto) {
            this.proto = serviceDescriptorProto;
            int i = 0;
            while (true) {
                MethodDescriptor[] methodDescriptorArr = this.methods;
                if (i >= methodDescriptorArr.length) {
                    return;
                }
                methodDescriptorArr[i].setProto(serviceDescriptorProto.getMethod(i));
                i++;
            }
        }

        public MethodDescriptor findMethodByName(String str) {
            DescriptorPool descriptorPool = this.file.g;
            GenericDescriptor a = descriptorPool.a(this.fullName + '.' + str);
            if (a == null || !(a instanceof MethodDescriptor)) {
                return null;
            }
            return (MethodDescriptor) a;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.file;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        public int getIndex() {
            return this.index;
        }

        public List<MethodDescriptor> getMethods() {
            return Collections.unmodifiableList(Arrays.asList(this.methods));
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        public DescriptorProtos.ServiceOptions getOptions() {
            return this.proto.getOptions();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.ServiceDescriptorProto toProto() {
            return this.proto;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(FileDescriptor fileDescriptor, Descriptor descriptor, String str) {
        if (descriptor != null) {
            return descriptor.getFullName() + '.' + str;
        } else if (fileDescriptor.c().length() > 0) {
            return fileDescriptor.c() + '.' + str;
        } else {
            return str;
        }
    }
}
