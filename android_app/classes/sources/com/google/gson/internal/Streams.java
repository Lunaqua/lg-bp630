package com.google.gson.internal;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class Streams {

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static final class AppendableWriter extends Writer {
        private final Appendable appendable;
        private final CurrentWrite currentWrite;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        static class CurrentWrite implements CharSequence {
            char[] chars;

            CurrentWrite() {
            }

            @Override // java.lang.CharSequence
            public char charAt(int i) {
                return this.chars[i];
            }

            @Override // java.lang.CharSequence
            public int length() {
                return this.chars.length;
            }

            @Override // java.lang.CharSequence
            public CharSequence subSequence(int i, int i2) {
                return new String(this.chars, i, i2 - i);
            }
        }

        private AppendableWriter(Appendable appendable) {
            this.currentWrite = new CurrentWrite();
            this.appendable = appendable;
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        @Override // java.io.Writer
        public void write(int i) {
            this.appendable.append((char) i);
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) {
            CurrentWrite currentWrite = this.currentWrite;
            currentWrite.chars = cArr;
            this.appendable.append(currentWrite, i, i2 + i);
        }
    }

    public static JsonElement parse(JsonReader jsonReader) {
        boolean z;
        try {
            try {
                jsonReader.peek();
                z = false;
            } catch (EOFException e) {
                e = e;
                z = true;
            }
            try {
                return TypeAdapters.JSON_ELEMENT.read(jsonReader);
            } catch (EOFException e2) {
                e = e2;
                if (z) {
                    return JsonNull.INSTANCE;
                }
                throw new JsonSyntaxException(e);
            }
        } catch (MalformedJsonException e3) {
            throw new JsonSyntaxException(e3);
        } catch (IOException e4) {
            throw new JsonIOException(e4);
        } catch (NumberFormatException e5) {
            throw new JsonSyntaxException(e5);
        }
    }

    public static void write(JsonElement jsonElement, JsonWriter jsonWriter) {
        TypeAdapters.JSON_ELEMENT.write(jsonWriter, jsonElement);
    }

    public static Writer writerForAppendable(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new AppendableWriter(appendable);
    }
}