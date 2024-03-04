package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.Message;
import com.google.protobuf.UnknownFieldSet;
import com.lge.media.launcher.a;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class TextFormat {
    private static final int a = 4096;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class InvalidEscapeSequenceException extends IOException {
        private static final long a = -8164033650142593304L;

        InvalidEscapeSequenceException(String str) {
            super(str);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class ParseException extends IOException {
        private static final long a = 3196188060225107702L;

        public ParseException(String str) {
            super(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class TextGenerator {
        private Appendable a;
        private boolean b;
        private final StringBuilder c;

        private TextGenerator(Appendable appendable) {
            this.b = true;
            this.c = new StringBuilder();
            this.a = appendable;
        }

        private void a(CharSequence charSequence, int i) {
            if (i == 0) {
                return;
            }
            if (this.b) {
                this.b = false;
                this.a.append(this.c);
            }
            this.a.append(charSequence);
        }

        public void a() {
            this.c.append("  ");
        }

        public void a(CharSequence charSequence) {
            int length = charSequence.length();
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                if (charSequence.charAt(i2) == '\n') {
                    a(charSequence.subSequence(i, length), (i2 - i) + 1);
                    i = i2 + 1;
                    this.b = true;
                }
            }
            a(charSequence.subSequence(i, length), length - i);
        }

        public void b() {
            int length = this.c.length();
            if (length == 0) {
                throw new IllegalArgumentException(" Outdent() without matching Indent().");
            }
            this.c.delete(length - 2, length);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class Tokenizer {
        private static final Pattern i = Pattern.compile("(\\s|(#.*$))++", 8);
        private static final Pattern j = Pattern.compile("[a-zA-Z_][0-9a-zA-Z_+-]*+|[.]?[0-9+-][0-9a-zA-Z_.+-]*+|\"([^\"\n\\\\]|\\\\.)*+(\"|\\\\?$)|'([^'\n\\\\]|\\\\.)*+('|\\\\?$)", 8);
        private static final Pattern k = Pattern.compile("-?inf(inity)?", 2);
        private static final Pattern l = Pattern.compile("-?inf(inity)?f?", 2);
        private static final Pattern m = Pattern.compile("nanf?", 2);
        private final CharSequence a;
        private final Matcher b;
        private String c;
        private int d;
        private int e;
        private int f;
        private int g;
        private int h;

        private Tokenizer(CharSequence charSequence) {
            this.d = 0;
            this.e = 0;
            this.f = 0;
            this.g = 0;
            this.h = 0;
            this.a = charSequence;
            this.b = i.matcher(charSequence);
            n();
            b();
        }

        private ParseException a(NumberFormatException numberFormatException) {
            return c("Couldn't parse integer: " + numberFormatException.getMessage());
        }

        private void a(List<ByteString> list) {
            char charAt = this.c.length() > 0 ? this.c.charAt(0) : (char) 0;
            if (charAt != '\"' && charAt != '\'') {
                throw c("Expected string.");
            }
            if (this.c.length() >= 2) {
                String str = this.c;
                if (str.charAt(str.length() - 1) == charAt) {
                    try {
                        ByteString a = TextFormat.a((CharSequence) this.c.substring(1, this.c.length() - 1));
                        b();
                        list.add(a);
                        return;
                    } catch (InvalidEscapeSequenceException e) {
                        throw c(e.getMessage());
                    }
                }
            }
            throw c("String missing ending quote.");
        }

        private ParseException b(NumberFormatException numberFormatException) {
            return c("Couldn't parse number: " + numberFormatException.getMessage());
        }

        private void n() {
            this.b.usePattern(i);
            if (this.b.lookingAt()) {
                Matcher matcher = this.b;
                matcher.region(matcher.end(), this.b.regionEnd());
            }
        }

        public boolean a() {
            return this.c.length() == 0;
        }

        public boolean a(String str) {
            if (this.c.equals(str)) {
                b();
                return true;
            }
            return false;
        }

        public void b() {
            Matcher matcher;
            int i2;
            int regionEnd;
            int i3;
            this.g = this.e;
            this.h = this.f;
            while (this.d < this.b.regionStart()) {
                if (this.a.charAt(this.d) == '\n') {
                    this.e++;
                    i3 = 0;
                } else {
                    i3 = this.f + 1;
                }
                this.f = i3;
                this.d++;
            }
            if (this.b.regionStart() == this.b.regionEnd()) {
                this.c = a.d;
                return;
            }
            this.b.usePattern(j);
            if (this.b.lookingAt()) {
                this.c = this.b.group();
                matcher = this.b;
                i2 = matcher.end();
                regionEnd = this.b.regionEnd();
            } else {
                this.c = String.valueOf(this.a.charAt(this.d));
                matcher = this.b;
                i2 = this.d + 1;
                regionEnd = matcher.regionEnd();
            }
            matcher.region(i2, regionEnd);
            n();
        }

        public void b(String str) {
            if (a(str)) {
                return;
            }
            throw c("Expected \"" + str + "\".");
        }

        public ParseException c(String str) {
            return new ParseException((this.e + 1) + ":" + (this.f + 1) + ": " + str);
        }

        public boolean c() {
            if (this.c.length() == 0) {
                return false;
            }
            char charAt = this.c.charAt(0);
            return ('0' <= charAt && charAt <= '9') || charAt == '-' || charAt == '+';
        }

        public ParseException d(String str) {
            return new ParseException((this.g + 1) + ":" + (this.h + 1) + ": " + str);
        }

        public String d() {
            for (int i2 = 0; i2 < this.c.length(); i2++) {
                char charAt = this.c.charAt(i2);
                if (('a' > charAt || charAt > 'z') && (('A' > charAt || charAt > 'Z') && !(('0' <= charAt && charAt <= '9') || charAt == '_' || charAt == '.'))) {
                    throw c("Expected identifier.");
                }
            }
            String str = this.c;
            b();
            return str;
        }

        public int e() {
            try {
                int c = TextFormat.c(this.c);
                b();
                return c;
            } catch (NumberFormatException e) {
                throw a(e);
            }
        }

        public int f() {
            try {
                int d = TextFormat.d(this.c);
                b();
                return d;
            } catch (NumberFormatException e) {
                throw a(e);
            }
        }

        public long g() {
            try {
                long e = TextFormat.e(this.c);
                b();
                return e;
            } catch (NumberFormatException e2) {
                throw a(e2);
            }
        }

        public long h() {
            try {
                long f = TextFormat.f(this.c);
                b();
                return f;
            } catch (NumberFormatException e) {
                throw a(e);
            }
        }

        public double i() {
            if (k.matcher(this.c).matches()) {
                boolean startsWith = this.c.startsWith("-");
                b();
                return startsWith ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
            } else if (this.c.equalsIgnoreCase("nan")) {
                b();
                return Double.NaN;
            } else {
                try {
                    double parseDouble = Double.parseDouble(this.c);
                    b();
                    return parseDouble;
                } catch (NumberFormatException e) {
                    throw b(e);
                }
            }
        }

        public float j() {
            if (l.matcher(this.c).matches()) {
                boolean startsWith = this.c.startsWith("-");
                b();
                return startsWith ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
            } else if (m.matcher(this.c).matches()) {
                b();
                return Float.NaN;
            } else {
                try {
                    float parseFloat = Float.parseFloat(this.c);
                    b();
                    return parseFloat;
                } catch (NumberFormatException e) {
                    throw b(e);
                }
            }
        }

        public boolean k() {
            if (this.c.equals("true")) {
                b();
                return true;
            } else if (this.c.equals("false")) {
                b();
                return false;
            } else {
                throw c("Expected \"true\" or \"false\".");
            }
        }

        public String l() {
            return m().e();
        }

        public ByteString m() {
            ArrayList arrayList = new ArrayList();
            while (true) {
                a(arrayList);
                if (!this.c.startsWith("'") && !this.c.startsWith("\"")) {
                    return ByteString.a(arrayList);
                }
            }
        }
    }

    private TextFormat() {
    }

    private static long a(String str, boolean z, boolean z2) {
        int i = 0;
        boolean z3 = true;
        if (!str.startsWith("-", 0)) {
            z3 = false;
        } else if (!z) {
            throw new NumberFormatException("Number must be positive: " + str);
        } else {
            i = 1;
        }
        int i2 = 10;
        if (str.startsWith("0x", i)) {
            i += 2;
            i2 = 16;
        } else if (str.startsWith("0", i)) {
            i2 = 8;
        }
        String substring = str.substring(i);
        if (substring.length() < 16) {
            long parseLong = Long.parseLong(substring, i2);
            if (z3) {
                parseLong = -parseLong;
            }
            if (z2) {
                return parseLong;
            }
            if (z) {
                if (parseLong > 2147483647L || parseLong < -2147483648L) {
                    throw new NumberFormatException("Number out of range for 32-bit signed integer: " + str);
                }
                return parseLong;
            } else if (parseLong >= 4294967296L || parseLong < 0) {
                throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + str);
            } else {
                return parseLong;
            }
        }
        BigInteger bigInteger = new BigInteger(substring, i2);
        if (z3) {
            bigInteger = bigInteger.negate();
        }
        if (z2) {
            if (z) {
                if (bigInteger.bitLength() > 63) {
                    throw new NumberFormatException("Number out of range for 64-bit signed integer: " + str);
                }
            } else if (bigInteger.bitLength() > 64) {
                throw new NumberFormatException("Number out of range for 64-bit unsigned integer: " + str);
            }
        } else if (z) {
            if (bigInteger.bitLength() > 31) {
                throw new NumberFormatException("Number out of range for 32-bit signed integer: " + str);
            }
        } else if (bigInteger.bitLength() > 32) {
            throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + str);
        }
        return bigInteger.longValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ByteString a(CharSequence charSequence) {
        int i;
        int i2;
        byte[] bArr = new byte[charSequence.length()];
        int i3 = 0;
        int i4 = 0;
        while (i3 < charSequence.length()) {
            char charAt = charSequence.charAt(i3);
            if (charAt == '\\') {
                i3++;
                if (i3 >= charSequence.length()) {
                    throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\' at end of string.");
                }
                char charAt2 = charSequence.charAt(i3);
                if (a(charAt2)) {
                    int c = c(charAt2);
                    int i5 = i3 + 1;
                    if (i5 < charSequence.length() && a(charSequence.charAt(i5))) {
                        c = (c * 8) + c(charSequence.charAt(i5));
                        i3 = i5;
                    }
                    int i6 = i3 + 1;
                    if (i6 < charSequence.length() && a(charSequence.charAt(i6))) {
                        c = (c * 8) + c(charSequence.charAt(i6));
                        i3 = i6;
                    }
                    i = i4 + 1;
                    bArr[i4] = (byte) c;
                } else {
                    if (charAt2 == '\"') {
                        i2 = i4 + 1;
                        bArr[i4] = 34;
                    } else if (charAt2 == '\'') {
                        i2 = i4 + 1;
                        bArr[i4] = 39;
                    } else if (charAt2 == '\\') {
                        i2 = i4 + 1;
                        bArr[i4] = 92;
                    } else if (charAt2 == 'f') {
                        i2 = i4 + 1;
                        bArr[i4] = 12;
                    } else if (charAt2 == 'n') {
                        i2 = i4 + 1;
                        bArr[i4] = 10;
                    } else if (charAt2 == 'r') {
                        i2 = i4 + 1;
                        bArr[i4] = 13;
                    } else if (charAt2 == 't') {
                        i2 = i4 + 1;
                        bArr[i4] = 9;
                    } else if (charAt2 == 'v') {
                        i2 = i4 + 1;
                        bArr[i4] = 11;
                    } else if (charAt2 == 'x') {
                        i3++;
                        if (i3 >= charSequence.length() || !b(charSequence.charAt(i3))) {
                            throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\x' with no digits");
                        }
                        int c2 = c(charSequence.charAt(i3));
                        int i7 = i3 + 1;
                        if (i7 < charSequence.length() && b(charSequence.charAt(i7))) {
                            c2 = (c2 * 16) + c(charSequence.charAt(i7));
                            i3 = i7;
                        }
                        bArr[i4] = (byte) c2;
                        i2 = i4 + 1;
                    } else if (charAt2 == 'a') {
                        i2 = i4 + 1;
                        bArr[i4] = 7;
                    } else if (charAt2 != 'b') {
                        throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\" + charAt2 + '\'');
                    } else {
                        i2 = i4 + 1;
                        bArr[i4] = 8;
                    }
                    i4 = i2;
                    i3++;
                }
            } else {
                i = i4 + 1;
                bArr[i4] = (byte) charAt;
            }
            i4 = i;
            i3++;
        }
        return ByteString.a(bArr, 0, i4);
    }

    private static String a(int i) {
        return i >= 0 ? Integer.toString(i) : Long.toString(i & 4294967295L);
    }

    private static String a(long j) {
        return j >= 0 ? Long.toString(j) : BigInteger.valueOf(j & Long.MAX_VALUE).setBit(63).toString();
    }

    static String a(ByteString byteString) {
        String str;
        StringBuilder sb = new StringBuilder(byteString.a());
        for (int i = 0; i < byteString.a(); i++) {
            int a2 = byteString.a(i);
            if (a2 == 34) {
                str = "\\\"";
            } else if (a2 == 39) {
                str = "\\'";
            } else if (a2 != 92) {
                switch (a2) {
                    case 7:
                        str = "\\a";
                        break;
                    case 8:
                        str = "\\b";
                        break;
                    case 9:
                        str = "\\t";
                        break;
                    case 10:
                        str = "\\n";
                        break;
                    case 11:
                        str = "\\v";
                        break;
                    case 12:
                        str = "\\f";
                        break;
                    case 13:
                        str = "\\r";
                        break;
                    default:
                        if (a2 < 32) {
                            sb.append('\\');
                            sb.append((char) (((a2 >>> 6) & 3) + 48));
                            sb.append((char) (((a2 >>> 3) & 7) + 48));
                            a2 = (a2 & 7) + 48;
                        }
                        sb.append((char) a2);
                        continue;
                }
            } else {
                str = "\\\\";
            }
            sb.append(str);
        }
        return sb.toString();
    }

    public static String a(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
        try {
            StringBuilder sb = new StringBuilder();
            a(fieldDescriptor, obj, sb);
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException("Writing to a StringBuilder threw an IOException (should never happen).", e);
        }
    }

    public static String a(Message message) {
        try {
            StringBuilder sb = new StringBuilder();
            a(message, sb);
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException("Writing to a StringBuilder threw an IOException (should never happen).", e);
        }
    }

    public static String a(UnknownFieldSet unknownFieldSet) {
        try {
            StringBuilder sb = new StringBuilder();
            a(unknownFieldSet, sb);
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException("Writing to a StringBuilder threw an IOException (should never happen).", e);
        }
    }

    static String a(String str) {
        return a(ByteString.a(str));
    }

    private static StringBuilder a(Readable readable) {
        StringBuilder sb = new StringBuilder();
        CharBuffer allocate = CharBuffer.allocate(4096);
        while (true) {
            int read = readable.read(allocate);
            if (read == -1) {
                return sb;
            }
            allocate.flip();
            sb.append((CharSequence) allocate, 0, read);
        }
    }

    private static void a(Descriptors.FieldDescriptor fieldDescriptor, Object obj, TextGenerator textGenerator) {
        if (!fieldDescriptor.isRepeated()) {
            b(fieldDescriptor, obj, textGenerator);
            return;
        }
        for (Object obj2 : (List) obj) {
            b(fieldDescriptor, obj2, textGenerator);
        }
    }

    public static void a(Descriptors.FieldDescriptor fieldDescriptor, Object obj, Appendable appendable) {
        a(fieldDescriptor, obj, new TextGenerator(appendable));
    }

    private static void a(Message message, TextGenerator textGenerator) {
        for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : message.getAllFields().entrySet()) {
            a(entry.getKey(), entry.getValue(), textGenerator);
        }
        a(message.getUnknownFields(), textGenerator);
    }

    public static void a(Message message, Appendable appendable) {
        a(message, new TextGenerator(appendable));
    }

    private static void a(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, Message.Builder builder) {
        Descriptors.FieldDescriptor fieldDescriptor;
        ExtensionRegistry.ExtensionInfo extensionInfo;
        int e;
        long g;
        String str;
        Descriptors.Descriptor descriptorForType = builder.getDescriptorForType();
        Object obj = null;
        if (tokenizer.a("[")) {
            StringBuilder sb = new StringBuilder(tokenizer.d());
            while (tokenizer.a(".")) {
                sb.append('.');
                sb.append(tokenizer.d());
            }
            extensionInfo = extensionRegistry.findExtensionByName(sb.toString());
            if (extensionInfo == null) {
                throw tokenizer.d("Extension \"" + ((Object) sb) + "\" not found in the ExtensionRegistry.");
            } else if (extensionInfo.a.getContainingType() != descriptorForType) {
                throw tokenizer.d("Extension \"" + ((Object) sb) + "\" does not extend message type \"" + descriptorForType.getFullName() + "\".");
            } else {
                tokenizer.b("]");
                fieldDescriptor = extensionInfo.a;
            }
        } else {
            String d = tokenizer.d();
            Descriptors.FieldDescriptor findFieldByName = descriptorForType.findFieldByName(d);
            if (findFieldByName == null && (findFieldByName = descriptorForType.findFieldByName(d.toLowerCase(Locale.US))) != null && findFieldByName.getType() != Descriptors.FieldDescriptor.Type.GROUP) {
                findFieldByName = null;
            }
            if (findFieldByName != null && findFieldByName.getType() == Descriptors.FieldDescriptor.Type.GROUP && !findFieldByName.getMessageType().getName().equals(d)) {
                findFieldByName = null;
            }
            if (findFieldByName == null) {
                throw tokenizer.d("Message type \"" + descriptorForType.getFullName() + "\" has no field named \"" + d + "\".");
            }
            fieldDescriptor = findFieldByName;
            extensionInfo = null;
        }
        if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            tokenizer.a(":");
            if (tokenizer.a("<")) {
                str = ">";
            } else {
                tokenizer.b("{");
                str = "}";
            }
            Message.Builder newBuilderForField = extensionInfo == null ? builder.newBuilderForField(fieldDescriptor) : extensionInfo.b.newBuilderForType();
            while (!tokenizer.a(str)) {
                if (tokenizer.a()) {
                    throw tokenizer.c("Expected \"" + str + "\".");
                }
                a(tokenizer, extensionRegistry, newBuilderForField);
            }
            obj = newBuilderForField.build();
        } else {
            tokenizer.b(":");
            switch (fieldDescriptor.getType()) {
                case INT32:
                case SINT32:
                case SFIXED32:
                    e = tokenizer.e();
                    obj = Integer.valueOf(e);
                    break;
                case INT64:
                case SINT64:
                case SFIXED64:
                    g = tokenizer.g();
                    obj = Long.valueOf(g);
                    break;
                case FLOAT:
                    obj = Float.valueOf(tokenizer.j());
                    break;
                case DOUBLE:
                    obj = Double.valueOf(tokenizer.i());
                    break;
                case BOOL:
                    obj = Boolean.valueOf(tokenizer.k());
                    break;
                case UINT32:
                case FIXED32:
                    e = tokenizer.f();
                    obj = Integer.valueOf(e);
                    break;
                case UINT64:
                case FIXED64:
                    g = tokenizer.h();
                    obj = Long.valueOf(g);
                    break;
                case STRING:
                    obj = tokenizer.l();
                    break;
                case BYTES:
                    obj = tokenizer.m();
                    break;
                case ENUM:
                    Descriptors.EnumDescriptor enumType = fieldDescriptor.getEnumType();
                    if (tokenizer.c()) {
                        int e2 = tokenizer.e();
                        Descriptors.EnumValueDescriptor findValueByNumber = enumType.findValueByNumber(e2);
                        if (findValueByNumber == null) {
                            throw tokenizer.d("Enum type \"" + enumType.getFullName() + "\" has no value with number " + e2 + '.');
                        }
                        obj = findValueByNumber;
                        break;
                    } else {
                        String d2 = tokenizer.d();
                        Descriptors.EnumValueDescriptor findValueByName = enumType.findValueByName(d2);
                        if (findValueByName == null) {
                            throw tokenizer.d("Enum type \"" + enumType.getFullName() + "\" has no value named \"" + d2 + "\".");
                        }
                        obj = findValueByName;
                        break;
                    }
                case MESSAGE:
                case GROUP:
                    throw new RuntimeException("Can't get here.");
            }
        }
        if (fieldDescriptor.isRepeated()) {
            builder.addRepeatedField(fieldDescriptor, obj);
        } else {
            builder.setField(fieldDescriptor, obj);
        }
    }

    private static void a(UnknownFieldSet unknownFieldSet, TextGenerator textGenerator) {
        for (Map.Entry<Integer, UnknownFieldSet.Field> entry : unknownFieldSet.asMap().entrySet()) {
            String str = entry.getKey().toString() + ": ";
            UnknownFieldSet.Field value = entry.getValue();
            for (Long l : value.c()) {
                long longValue = l.longValue();
                textGenerator.a(entry.getKey().toString());
                textGenerator.a(": ");
                textGenerator.a(a(longValue));
                textGenerator.a("\n");
            }
            for (Integer num : value.d()) {
                int intValue = num.intValue();
                textGenerator.a(entry.getKey().toString());
                textGenerator.a(": ");
                textGenerator.a(String.format(null, "0x%08x", Integer.valueOf(intValue)));
                textGenerator.a("\n");
            }
            for (Long l2 : value.e()) {
                long longValue2 = l2.longValue();
                textGenerator.a(entry.getKey().toString());
                textGenerator.a(": ");
                textGenerator.a(String.format(null, "0x%016x", Long.valueOf(longValue2)));
                textGenerator.a("\n");
            }
            for (ByteString byteString : value.f()) {
                textGenerator.a(entry.getKey().toString());
                textGenerator.a(": \"");
                textGenerator.a(a(byteString));
                textGenerator.a("\"\n");
            }
            for (UnknownFieldSet unknownFieldSet2 : value.g()) {
                textGenerator.a(entry.getKey().toString());
                textGenerator.a(" {\n");
                textGenerator.a();
                a(unknownFieldSet2, textGenerator);
                textGenerator.b();
                textGenerator.a("}\n");
            }
        }
    }

    public static void a(UnknownFieldSet unknownFieldSet, Appendable appendable) {
        a(unknownFieldSet, new TextGenerator(appendable));
    }

    public static void a(CharSequence charSequence, ExtensionRegistry extensionRegistry, Message.Builder builder) {
        Tokenizer tokenizer = new Tokenizer(charSequence);
        while (!tokenizer.a()) {
            a(tokenizer, extensionRegistry, builder);
        }
    }

    public static void a(CharSequence charSequence, Message.Builder builder) {
        a(charSequence, ExtensionRegistry.getEmptyRegistry(), builder);
    }

    public static void a(Readable readable, ExtensionRegistry extensionRegistry, Message.Builder builder) {
        a(a(readable), extensionRegistry, builder);
    }

    public static void a(Readable readable, Message.Builder builder) {
        a(readable, ExtensionRegistry.getEmptyRegistry(), builder);
    }

    private static boolean a(char c) {
        return '0' <= c && c <= '7';
    }

    static String b(String str) {
        return a((CharSequence) str).e();
    }

    private static void b(Descriptors.FieldDescriptor fieldDescriptor, Object obj, TextGenerator textGenerator) {
        String name;
        if (fieldDescriptor.isExtension()) {
            textGenerator.a("[");
            textGenerator.a((fieldDescriptor.getContainingType().getOptions().getMessageSetWireFormat() && fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && fieldDescriptor.isOptional() && fieldDescriptor.getExtensionScope() == fieldDescriptor.getMessageType()) ? fieldDescriptor.getMessageType().getFullName() : fieldDescriptor.getFullName());
            name = "]";
        } else {
            name = fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.GROUP ? fieldDescriptor.getMessageType().getName() : fieldDescriptor.getName();
        }
        textGenerator.a(name);
        if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            textGenerator.a(" {\n");
            textGenerator.a();
        } else {
            textGenerator.a(": ");
        }
        c(fieldDescriptor, obj, textGenerator);
        if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            textGenerator.b();
            textGenerator.a("}");
        }
        textGenerator.a("\n");
    }

    private static boolean b(char c) {
        return ('0' <= c && c <= '9') || ('a' <= c && c <= 'f') || ('A' <= c && c <= 'F');
    }

    private static int c(char c) {
        if ('0' > c || c > '9') {
            return (('a' > c || c > 'z') ? c - 'A' : c - 'a') + 10;
        }
        return c - '0';
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int c(String str) {
        return (int) a(str, true, false);
    }

    private static void c(Descriptors.FieldDescriptor fieldDescriptor, Object obj, TextGenerator textGenerator) {
        String obj2;
        String a2;
        switch (fieldDescriptor.getType()) {
            case INT32:
            case INT64:
            case SINT32:
            case SINT64:
            case SFIXED32:
            case SFIXED64:
            case FLOAT:
            case DOUBLE:
            case BOOL:
                obj2 = obj.toString();
                textGenerator.a(obj2);
                return;
            case UINT32:
            case FIXED32:
                obj2 = a(((Integer) obj).intValue());
                textGenerator.a(obj2);
                return;
            case UINT64:
            case FIXED64:
                obj2 = a(((Long) obj).longValue());
                textGenerator.a(obj2);
                return;
            case STRING:
                textGenerator.a("\"");
                a2 = a((String) obj);
                textGenerator.a(a2);
                textGenerator.a("\"");
                return;
            case BYTES:
                textGenerator.a("\"");
                a2 = a((ByteString) obj);
                textGenerator.a(a2);
                textGenerator.a("\"");
                return;
            case ENUM:
                obj2 = ((Descriptors.EnumValueDescriptor) obj).getName();
                textGenerator.a(obj2);
                return;
            case MESSAGE:
            case GROUP:
                a((Message) obj, textGenerator);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int d(String str) {
        return (int) a(str, false, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long e(String str) {
        return a(str, true, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long f(String str) {
        return a(str, false, true);
    }
}
