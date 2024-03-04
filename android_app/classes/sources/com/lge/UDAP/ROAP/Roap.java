package com.lge.UDAP.ROAP;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Internal;
import com.google.protobuf.Message;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.UnknownFieldSet;
import java.io.InputStream;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class Roap {
    private static Descriptors.Descriptor A;
    private static GeneratedMessage.FieldAccessorTable B;
    private static Descriptors.FileDescriptor C;
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
    public static final class Action extends GeneratedMessage {
        public static final int BDATA_FIELD_NUMBER = 2;
        public static final int NDATA_FIELD_NUMBER = 3;
        public static final int STRDATA_FIELD_NUMBER = 4;
        public static final int TYPE_FIELD_NUMBER = 1;
        private static final Action defaultInstance = new Action(true);
        private boolean bData_;
        private boolean hasBData;
        private boolean hasNData;
        private boolean hasStrData;
        private boolean hasType;
        private int memoizedSerializedSize;
        private int nData_;
        private String strData_;
        private ActionType type_;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public enum ActionType implements ProtocolMessageEnum {
            UNKNOWN(0, 0),
            TEXT_INPUT(1, 1),
            TEXT_SUBMIT(2, 2),
            AUTO_PAUSE(3, 3),
            TIME_JUMP(4, 4),
            SP_START(5, 5),
            SP_STOP(6, 6),
            SYNC_MEDIA_START(7, 7),
            SYNC_SELECT(8, 8),
            SYNC_FOLDER_CHANGE(9, 9),
            SYNC_MEDIA_CHANGE(10, 10),
            SYNC_DEVICE_CHANGE(11, 11),
            MUSICID_CANCEL(12, 12),
            SP_PLAYING(13, 13),
            SYNC_MEDIA_CMD(14, 14),
            SYNC_PAGE_CHANGE(15, 15),
            SYNC_CP_EXECUTE(16, 16),
            SYNC_EXTSPK_EXECUTE(17, 17);
            
            private final int index;
            private final int value;
            private static Internal.EnumLiteMap<ActionType> internalValueMap = new Internal.EnumLiteMap<ActionType>() { // from class: com.lge.UDAP.ROAP.Roap.Action.ActionType.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public ActionType findValueByNumber(int i) {
                    return ActionType.valueOf(i);
                }
            };
            private static final ActionType[] VALUES = {UNKNOWN, TEXT_INPUT, TEXT_SUBMIT, AUTO_PAUSE, TIME_JUMP, SP_START, SP_STOP, SYNC_MEDIA_START, SYNC_SELECT, SYNC_FOLDER_CHANGE, SYNC_MEDIA_CHANGE, SYNC_DEVICE_CHANGE, MUSICID_CANCEL, SP_PLAYING, SYNC_MEDIA_CMD, SYNC_PAGE_CHANGE, SYNC_CP_EXECUTE, SYNC_EXTSPK_EXECUTE};

            static {
                Roap.a();
            }

            ActionType(int i, int i2) {
                this.index = i;
                this.value = i2;
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return Action.getDescriptor().getEnumTypes().get(0);
            }

            public static Internal.EnumLiteMap<ActionType> internalGetValueMap() {
                return internalValueMap;
            }

            public static ActionType valueOf(int i) {
                switch (i) {
                    case 0:
                        return UNKNOWN;
                    case 1:
                        return TEXT_INPUT;
                    case 2:
                        return TEXT_SUBMIT;
                    case 3:
                        return AUTO_PAUSE;
                    case 4:
                        return TIME_JUMP;
                    case 5:
                        return SP_START;
                    case 6:
                        return SP_STOP;
                    case 7:
                        return SYNC_MEDIA_START;
                    case 8:
                        return SYNC_SELECT;
                    case 9:
                        return SYNC_FOLDER_CHANGE;
                    case 10:
                        return SYNC_MEDIA_CHANGE;
                    case 11:
                        return SYNC_DEVICE_CHANGE;
                    case 12:
                        return MUSICID_CANCEL;
                    case 13:
                        return SP_PLAYING;
                    case 14:
                        return SYNC_MEDIA_CMD;
                    case 15:
                        return SYNC_PAGE_CHANGE;
                    case 16:
                        return SYNC_CP_EXECUTE;
                    case 17:
                        return SYNC_EXTSPK_EXECUTE;
                    default:
                        return null;
                }
            }

            public static ActionType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
        public static final class a extends GeneratedMessage.Builder<a> {
            private Action result;

            private a() {
            }

            static /* synthetic */ a access$13100() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public Action buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static a create() {
                a aVar = new a();
                aVar.result = new Action();
                return aVar;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Action build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Action buildPartial() {
                Action action = this.result;
                if (action != null) {
                    this.result = null;
                    return action;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public a clear() {
                if (this.result != null) {
                    this.result = new Action();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public a clearBData() {
                this.result.hasBData = false;
                this.result.bData_ = false;
                return this;
            }

            public a clearNData() {
                this.result.hasNData = false;
                this.result.nData_ = 0;
                return this;
            }

            public a clearStrData() {
                this.result.hasStrData = false;
                this.result.strData_ = Action.getDefaultInstance().getStrData();
                return this;
            }

            public a clearType() {
                this.result.hasType = false;
                this.result.type_ = ActionType.UNKNOWN;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public a mo3clone() {
                return create().mergeFrom(this.result);
            }

            public boolean getBData() {
                return this.result.getBData();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Action getDefaultInstanceForType() {
                return Action.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return Action.getDescriptor();
            }

            public int getNData() {
                return this.result.getNData();
            }

            public String getStrData() {
                return this.result.getStrData();
            }

            public ActionType getType() {
                return this.result.getType();
            }

            public boolean hasBData() {
                return this.result.hasBData();
            }

            public boolean hasNData() {
                return this.result.hasNData();
            }

            public boolean hasStrData() {
                return this.result.hasStrData();
            }

            public boolean hasType() {
                return this.result.hasType();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.Builder
            public Action internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public a mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 8) {
                        int n = codedInputStream.n();
                        ActionType valueOf = ActionType.valueOf(n);
                        if (valueOf == null) {
                            newBuilder.mergeVarintField(1, n);
                        } else {
                            setType(valueOf);
                        }
                    } else if (a == 16) {
                        setBData(codedInputStream.j());
                    } else if (a == 24) {
                        setNData(codedInputStream.g());
                    } else if (a == 34) {
                        setStrData(codedInputStream.k());
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public a mergeFrom(Message message) {
                if (message instanceof Action) {
                    return mergeFrom((Action) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public a mergeFrom(Action action) {
                if (action == Action.getDefaultInstance()) {
                    return this;
                }
                if (action.hasType()) {
                    setType(action.getType());
                }
                if (action.hasBData()) {
                    setBData(action.getBData());
                }
                if (action.hasNData()) {
                    setNData(action.getNData());
                }
                if (action.hasStrData()) {
                    setStrData(action.getStrData());
                }
                mergeUnknownFields(action.getUnknownFields());
                return this;
            }

            public a setBData(boolean z) {
                this.result.hasBData = true;
                this.result.bData_ = z;
                return this;
            }

            public a setNData(int i) {
                this.result.hasNData = true;
                this.result.nData_ = i;
                return this;
            }

            public a setStrData(String str) {
                if (str != null) {
                    this.result.hasStrData = true;
                    this.result.strData_ = str;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setType(ActionType actionType) {
                if (actionType != null) {
                    this.result.hasType = true;
                    this.result.type_ = actionType;
                    return this;
                }
                throw new NullPointerException();
            }
        }

        static {
            Roap.b();
            defaultInstance.initFields();
        }

        private Action() {
            this.bData_ = false;
            this.nData_ = 0;
            this.strData_ = com.lge.media.launcher.a.d;
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private Action(boolean z) {
            this.bData_ = false;
            this.nData_ = 0;
            this.strData_ = com.lge.media.launcher.a.d;
            this.memoizedSerializedSize = -1;
        }

        public static Action getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Roap.q;
        }

        private void initFields() {
            this.type_ = ActionType.UNKNOWN;
        }

        public static a newBuilder() {
            return a.access$13100();
        }

        public static a newBuilder(Action action) {
            return newBuilder().mergeFrom(action);
        }

        public static Action parseDelimitedFrom(InputStream inputStream) {
            a newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static Action parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            a newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static Action parseFrom(ByteString byteString) {
            return ((a) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static Action parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static Action parseFrom(CodedInputStream codedInputStream) {
            return ((a) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static Action parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static Action parseFrom(InputStream inputStream) {
            return ((a) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static Action parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static Action parseFrom(byte[] bArr) {
            return ((a) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static Action parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        public boolean getBData() {
            return this.bData_;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Action getDefaultInstanceForType() {
            return defaultInstance;
        }

        public int getNData() {
            return this.nData_;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int j = hasType() ? 0 + CodedOutputStream.j(1, getType().getNumber()) : 0;
            if (hasBData()) {
                j += CodedOutputStream.b(2, getBData());
            }
            if (hasNData()) {
                j += CodedOutputStream.g(3, getNData());
            }
            if (hasStrData()) {
                j += CodedOutputStream.b(4, getStrData());
            }
            int serializedSize = j + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public String getStrData() {
            return this.strData_;
        }

        public ActionType getType() {
            return this.type_;
        }

        public boolean hasBData() {
            return this.hasBData;
        }

        public boolean hasNData() {
            return this.hasNData;
        }

        public boolean hasStrData() {
            return this.hasStrData;
        }

        public boolean hasType() {
            return this.hasType;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Roap.r;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            return this.hasType;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public a newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public a toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            if (hasType()) {
                codedOutputStream.d(1, getType().getNumber());
            }
            if (hasBData()) {
                codedOutputStream.a(2, getBData());
            }
            if (hasNData()) {
                codedOutputStream.a(3, getNData());
            }
            if (hasStrData()) {
                codedOutputStream.a(4, getStrData());
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class Authentication extends GeneratedMessage {
        public static final int AUTHKEY_FIELD_NUMBER = 2;
        public static final int AUTHRESULT_FIELD_NUMBER = 3;
        public static final int TYPE_FIELD_NUMBER = 1;
        private static final Authentication defaultInstance = new Authentication(true);
        private String authKey_;
        private AuthResult authResult_;
        private boolean hasAuthKey;
        private boolean hasAuthResult;
        private boolean hasType;
        private int memoizedSerializedSize;
        private AuthenticationType type_;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public enum AuthResult implements ProtocolMessageEnum {
            AUTH_REQUST_KEY_OK(0, 0),
            AUTH_REQUST_KEY_FAIL(1, 1),
            AUTH_SEND_KEY_OK(2, 2),
            AUTH_SEND_KEY_FAIL(3, 3),
            AUTH_SESSION_FAIL(4, 4),
            AUTH_CANCEL_OK(5, 5),
            AUTH_CANCEL_FAIL(6, 6);
            
            private final int index;
            private final int value;
            private static Internal.EnumLiteMap<AuthResult> internalValueMap = new Internal.EnumLiteMap<AuthResult>() { // from class: com.lge.UDAP.ROAP.Roap.Authentication.AuthResult.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public AuthResult findValueByNumber(int i) {
                    return AuthResult.valueOf(i);
                }
            };
            private static final AuthResult[] VALUES = {AUTH_REQUST_KEY_OK, AUTH_REQUST_KEY_FAIL, AUTH_SEND_KEY_OK, AUTH_SEND_KEY_FAIL, AUTH_SESSION_FAIL, AUTH_CANCEL_OK, AUTH_CANCEL_FAIL};

            static {
                Roap.a();
            }

            AuthResult(int i, int i2) {
                this.index = i;
                this.value = i2;
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return Authentication.getDescriptor().getEnumTypes().get(1);
            }

            public static Internal.EnumLiteMap<AuthResult> internalGetValueMap() {
                return internalValueMap;
            }

            public static AuthResult valueOf(int i) {
                switch (i) {
                    case 0:
                        return AUTH_REQUST_KEY_OK;
                    case 1:
                        return AUTH_REQUST_KEY_FAIL;
                    case 2:
                        return AUTH_SEND_KEY_OK;
                    case 3:
                        return AUTH_SEND_KEY_FAIL;
                    case 4:
                        return AUTH_SESSION_FAIL;
                    case 5:
                        return AUTH_CANCEL_OK;
                    case 6:
                        return AUTH_CANCEL_FAIL;
                    default:
                        return null;
                }
            }

            public static AuthResult valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
        public enum AuthenticationType implements ProtocolMessageEnum {
            UNKNOWN(0, 0),
            AUTH_REQUST_KEY(1, 1),
            AUTH_SEND_KEY(2, 2),
            AUTH_RESULT(3, 3),
            AUTH_CANCEL(4, 4);
            
            private final int index;
            private final int value;
            private static Internal.EnumLiteMap<AuthenticationType> internalValueMap = new Internal.EnumLiteMap<AuthenticationType>() { // from class: com.lge.UDAP.ROAP.Roap.Authentication.AuthenticationType.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public AuthenticationType findValueByNumber(int i) {
                    return AuthenticationType.valueOf(i);
                }
            };
            private static final AuthenticationType[] VALUES = {UNKNOWN, AUTH_REQUST_KEY, AUTH_SEND_KEY, AUTH_RESULT, AUTH_CANCEL};

            static {
                Roap.a();
            }

            AuthenticationType(int i, int i2) {
                this.index = i;
                this.value = i2;
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return Authentication.getDescriptor().getEnumTypes().get(0);
            }

            public static Internal.EnumLiteMap<AuthenticationType> internalGetValueMap() {
                return internalValueMap;
            }

            public static AuthenticationType valueOf(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                if (i != 4) {
                                    return null;
                                }
                                return AUTH_CANCEL;
                            }
                            return AUTH_RESULT;
                        }
                        return AUTH_SEND_KEY;
                    }
                    return AUTH_REQUST_KEY;
                }
                return UNKNOWN;
            }

            public static AuthenticationType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
        public static final class a extends GeneratedMessage.Builder<a> {
            private Authentication result;

            private a() {
            }

            static /* synthetic */ a access$14400() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public Authentication buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static a create() {
                a aVar = new a();
                aVar.result = new Authentication();
                return aVar;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Authentication build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Authentication buildPartial() {
                Authentication authentication = this.result;
                if (authentication != null) {
                    this.result = null;
                    return authentication;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public a clear() {
                if (this.result != null) {
                    this.result = new Authentication();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public a clearAuthKey() {
                this.result.hasAuthKey = false;
                this.result.authKey_ = Authentication.getDefaultInstance().getAuthKey();
                return this;
            }

            public a clearAuthResult() {
                this.result.hasAuthResult = false;
                this.result.authResult_ = AuthResult.AUTH_REQUST_KEY_OK;
                return this;
            }

            public a clearType() {
                this.result.hasType = false;
                this.result.type_ = AuthenticationType.UNKNOWN;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public a mo3clone() {
                return create().mergeFrom(this.result);
            }

            public String getAuthKey() {
                return this.result.getAuthKey();
            }

            public AuthResult getAuthResult() {
                return this.result.getAuthResult();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Authentication getDefaultInstanceForType() {
                return Authentication.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return Authentication.getDescriptor();
            }

            public AuthenticationType getType() {
                return this.result.getType();
            }

            public boolean hasAuthKey() {
                return this.result.hasAuthKey();
            }

            public boolean hasAuthResult() {
                return this.result.hasAuthResult();
            }

            public boolean hasType() {
                return this.result.hasType();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.Builder
            public Authentication internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public a mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                int n;
                int i;
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 8) {
                        n = codedInputStream.n();
                        AuthenticationType valueOf = AuthenticationType.valueOf(n);
                        if (valueOf == null) {
                            i = 1;
                            newBuilder.mergeVarintField(i, n);
                        } else {
                            setType(valueOf);
                        }
                    } else if (a == 18) {
                        setAuthKey(codedInputStream.k());
                    } else if (a == 24) {
                        n = codedInputStream.n();
                        AuthResult valueOf2 = AuthResult.valueOf(n);
                        if (valueOf2 == null) {
                            i = 3;
                            newBuilder.mergeVarintField(i, n);
                        } else {
                            setAuthResult(valueOf2);
                        }
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public a mergeFrom(Message message) {
                if (message instanceof Authentication) {
                    return mergeFrom((Authentication) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public a mergeFrom(Authentication authentication) {
                if (authentication == Authentication.getDefaultInstance()) {
                    return this;
                }
                if (authentication.hasType()) {
                    setType(authentication.getType());
                }
                if (authentication.hasAuthKey()) {
                    setAuthKey(authentication.getAuthKey());
                }
                if (authentication.hasAuthResult()) {
                    setAuthResult(authentication.getAuthResult());
                }
                mergeUnknownFields(authentication.getUnknownFields());
                return this;
            }

            public a setAuthKey(String str) {
                if (str != null) {
                    this.result.hasAuthKey = true;
                    this.result.authKey_ = str;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setAuthResult(AuthResult authResult) {
                if (authResult != null) {
                    this.result.hasAuthResult = true;
                    this.result.authResult_ = authResult;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setType(AuthenticationType authenticationType) {
                if (authenticationType != null) {
                    this.result.hasType = true;
                    this.result.type_ = authenticationType;
                    return this;
                }
                throw new NullPointerException();
            }
        }

        static {
            Roap.b();
            defaultInstance.initFields();
        }

        private Authentication() {
            this.authKey_ = com.lge.media.launcher.a.d;
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private Authentication(boolean z) {
            this.authKey_ = com.lge.media.launcher.a.d;
            this.memoizedSerializedSize = -1;
        }

        public static Authentication getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Roap.s;
        }

        private void initFields() {
            this.type_ = AuthenticationType.UNKNOWN;
            this.authResult_ = AuthResult.AUTH_REQUST_KEY_OK;
        }

        public static a newBuilder() {
            return a.access$14400();
        }

        public static a newBuilder(Authentication authentication) {
            return newBuilder().mergeFrom(authentication);
        }

        public static Authentication parseDelimitedFrom(InputStream inputStream) {
            a newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static Authentication parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            a newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static Authentication parseFrom(ByteString byteString) {
            return ((a) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static Authentication parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static Authentication parseFrom(CodedInputStream codedInputStream) {
            return ((a) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static Authentication parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static Authentication parseFrom(InputStream inputStream) {
            return ((a) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static Authentication parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static Authentication parseFrom(byte[] bArr) {
            return ((a) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static Authentication parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        public String getAuthKey() {
            return this.authKey_;
        }

        public AuthResult getAuthResult() {
            return this.authResult_;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Authentication getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int j = hasType() ? 0 + CodedOutputStream.j(1, getType().getNumber()) : 0;
            if (hasAuthKey()) {
                j += CodedOutputStream.b(2, getAuthKey());
            }
            if (hasAuthResult()) {
                j += CodedOutputStream.j(3, getAuthResult().getNumber());
            }
            int serializedSize = j + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public AuthenticationType getType() {
            return this.type_;
        }

        public boolean hasAuthKey() {
            return this.hasAuthKey;
        }

        public boolean hasAuthResult() {
            return this.hasAuthResult;
        }

        public boolean hasType() {
            return this.hasType;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Roap.t;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            return this.hasType;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public a newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public a toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            if (hasType()) {
                codedOutputStream.d(1, getType().getNumber());
            }
            if (hasAuthKey()) {
                codedOutputStream.a(2, getAuthKey());
            }
            if (hasAuthResult()) {
                codedOutputStream.d(3, getAuthResult().getNumber());
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class CommandMessage extends GeneratedMessage {
        public static final int ACTION_MESSAGE_FIELD_NUMBER = 4;
        public static final int KEY_MESSAGE_FIELD_NUMBER = 2;
        public static final int TOUCH_MESSAGE_FIELD_NUMBER = 3;
        public static final int TYPE_FIELD_NUMBER = 1;
        private static final CommandMessage defaultInstance = new CommandMessage(true);
        private Action actionMessage_;
        private boolean hasActionMessage;
        private boolean hasKeyMessage;
        private boolean hasTouchMessage;
        private boolean hasType;
        private Key keyMessage_;
        private int memoizedSerializedSize;
        private Touch touchMessage_;
        private CommandType type_;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public enum CommandType implements ProtocolMessageEnum {
            UNKNOWN(0, 0),
            KEY(1, 1),
            TOUCH(2, 2),
            ACTION(3, 3);
            
            private final int index;
            private final int value;
            private static Internal.EnumLiteMap<CommandType> internalValueMap = new Internal.EnumLiteMap<CommandType>() { // from class: com.lge.UDAP.ROAP.Roap.CommandMessage.CommandType.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public CommandType findValueByNumber(int i) {
                    return CommandType.valueOf(i);
                }
            };
            private static final CommandType[] VALUES = {UNKNOWN, KEY, TOUCH, ACTION};

            static {
                Roap.a();
            }

            CommandType(int i, int i2) {
                this.index = i;
                this.value = i2;
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return CommandMessage.getDescriptor().getEnumTypes().get(0);
            }

            public static Internal.EnumLiteMap<CommandType> internalGetValueMap() {
                return internalValueMap;
            }

            public static CommandType valueOf(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                return null;
                            }
                            return ACTION;
                        }
                        return TOUCH;
                    }
                    return KEY;
                }
                return UNKNOWN;
            }

            public static CommandType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
        public static final class a extends GeneratedMessage.Builder<a> {
            private CommandMessage result;

            private a() {
            }

            static /* synthetic */ a access$4600() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public CommandMessage buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static a create() {
                a aVar = new a();
                aVar.result = new CommandMessage();
                return aVar;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CommandMessage build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CommandMessage buildPartial() {
                CommandMessage commandMessage = this.result;
                if (commandMessage != null) {
                    this.result = null;
                    return commandMessage;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public a clear() {
                if (this.result != null) {
                    this.result = new CommandMessage();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public a clearActionMessage() {
                this.result.hasActionMessage = false;
                this.result.actionMessage_ = Action.getDefaultInstance();
                return this;
            }

            public a clearKeyMessage() {
                this.result.hasKeyMessage = false;
                this.result.keyMessage_ = Key.getDefaultInstance();
                return this;
            }

            public a clearTouchMessage() {
                this.result.hasTouchMessage = false;
                this.result.touchMessage_ = Touch.getDefaultInstance();
                return this;
            }

            public a clearType() {
                this.result.hasType = false;
                this.result.type_ = CommandType.UNKNOWN;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public a mo3clone() {
                return create().mergeFrom(this.result);
            }

            public Action getActionMessage() {
                return this.result.getActionMessage();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CommandMessage getDefaultInstanceForType() {
                return CommandMessage.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return CommandMessage.getDescriptor();
            }

            public Key getKeyMessage() {
                return this.result.getKeyMessage();
            }

            public Touch getTouchMessage() {
                return this.result.getTouchMessage();
            }

            public CommandType getType() {
                return this.result.getType();
            }

            public boolean hasActionMessage() {
                return this.result.hasActionMessage();
            }

            public boolean hasKeyMessage() {
                return this.result.hasKeyMessage();
            }

            public boolean hasTouchMessage() {
                return this.result.hasTouchMessage();
            }

            public boolean hasType() {
                return this.result.hasType();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.Builder
            public CommandMessage internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            public a mergeActionMessage(Action action) {
                CommandMessage commandMessage;
                if (!this.result.hasActionMessage() || this.result.actionMessage_ == Action.getDefaultInstance()) {
                    commandMessage = this.result;
                } else {
                    commandMessage = this.result;
                    action = Action.newBuilder(commandMessage.actionMessage_).mergeFrom(action).buildPartial();
                }
                commandMessage.actionMessage_ = action;
                this.result.hasActionMessage = true;
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public a mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 8) {
                        int n = codedInputStream.n();
                        CommandType valueOf = CommandType.valueOf(n);
                        if (valueOf == null) {
                            newBuilder.mergeVarintField(1, n);
                        } else {
                            setType(valueOf);
                        }
                    } else if (a == 18) {
                        Key.a newBuilder2 = Key.newBuilder();
                        if (hasKeyMessage()) {
                            newBuilder2.mergeFrom(getKeyMessage());
                        }
                        codedInputStream.a(newBuilder2, extensionRegistryLite);
                        setKeyMessage(newBuilder2.buildPartial());
                    } else if (a == 26) {
                        Touch.a newBuilder3 = Touch.newBuilder();
                        if (hasTouchMessage()) {
                            newBuilder3.mergeFrom(getTouchMessage());
                        }
                        codedInputStream.a(newBuilder3, extensionRegistryLite);
                        setTouchMessage(newBuilder3.buildPartial());
                    } else if (a == 34) {
                        Action.a newBuilder4 = Action.newBuilder();
                        if (hasActionMessage()) {
                            newBuilder4.mergeFrom(getActionMessage());
                        }
                        codedInputStream.a(newBuilder4, extensionRegistryLite);
                        setActionMessage(newBuilder4.buildPartial());
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public a mergeFrom(Message message) {
                if (message instanceof CommandMessage) {
                    return mergeFrom((CommandMessage) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public a mergeFrom(CommandMessage commandMessage) {
                if (commandMessage == CommandMessage.getDefaultInstance()) {
                    return this;
                }
                if (commandMessage.hasType()) {
                    setType(commandMessage.getType());
                }
                if (commandMessage.hasKeyMessage()) {
                    mergeKeyMessage(commandMessage.getKeyMessage());
                }
                if (commandMessage.hasTouchMessage()) {
                    mergeTouchMessage(commandMessage.getTouchMessage());
                }
                if (commandMessage.hasActionMessage()) {
                    mergeActionMessage(commandMessage.getActionMessage());
                }
                mergeUnknownFields(commandMessage.getUnknownFields());
                return this;
            }

            public a mergeKeyMessage(Key key) {
                CommandMessage commandMessage;
                if (!this.result.hasKeyMessage() || this.result.keyMessage_ == Key.getDefaultInstance()) {
                    commandMessage = this.result;
                } else {
                    commandMessage = this.result;
                    key = Key.newBuilder(commandMessage.keyMessage_).mergeFrom(key).buildPartial();
                }
                commandMessage.keyMessage_ = key;
                this.result.hasKeyMessage = true;
                return this;
            }

            public a mergeTouchMessage(Touch touch) {
                CommandMessage commandMessage;
                if (!this.result.hasTouchMessage() || this.result.touchMessage_ == Touch.getDefaultInstance()) {
                    commandMessage = this.result;
                } else {
                    commandMessage = this.result;
                    touch = Touch.newBuilder(commandMessage.touchMessage_).mergeFrom(touch).buildPartial();
                }
                commandMessage.touchMessage_ = touch;
                this.result.hasTouchMessage = true;
                return this;
            }

            public a setActionMessage(Action.a aVar) {
                this.result.hasActionMessage = true;
                this.result.actionMessage_ = aVar.build();
                return this;
            }

            public a setActionMessage(Action action) {
                if (action != null) {
                    this.result.hasActionMessage = true;
                    this.result.actionMessage_ = action;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setKeyMessage(Key.a aVar) {
                this.result.hasKeyMessage = true;
                this.result.keyMessage_ = aVar.build();
                return this;
            }

            public a setKeyMessage(Key key) {
                if (key != null) {
                    this.result.hasKeyMessage = true;
                    this.result.keyMessage_ = key;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setTouchMessage(Touch.a aVar) {
                this.result.hasTouchMessage = true;
                this.result.touchMessage_ = aVar.build();
                return this;
            }

            public a setTouchMessage(Touch touch) {
                if (touch != null) {
                    this.result.hasTouchMessage = true;
                    this.result.touchMessage_ = touch;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setType(CommandType commandType) {
                if (commandType != null) {
                    this.result.hasType = true;
                    this.result.type_ = commandType;
                    return this;
                }
                throw new NullPointerException();
            }
        }

        static {
            Roap.b();
            defaultInstance.initFields();
        }

        private CommandMessage() {
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private CommandMessage(boolean z) {
            this.memoizedSerializedSize = -1;
        }

        public static CommandMessage getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Roap.g;
        }

        private void initFields() {
            this.type_ = CommandType.UNKNOWN;
            this.keyMessage_ = Key.getDefaultInstance();
            this.touchMessage_ = Touch.getDefaultInstance();
            this.actionMessage_ = Action.getDefaultInstance();
        }

        public static a newBuilder() {
            return a.access$4600();
        }

        public static a newBuilder(CommandMessage commandMessage) {
            return newBuilder().mergeFrom(commandMessage);
        }

        public static CommandMessage parseDelimitedFrom(InputStream inputStream) {
            a newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static CommandMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            a newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static CommandMessage parseFrom(ByteString byteString) {
            return ((a) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static CommandMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static CommandMessage parseFrom(CodedInputStream codedInputStream) {
            return ((a) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static CommandMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static CommandMessage parseFrom(InputStream inputStream) {
            return ((a) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static CommandMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static CommandMessage parseFrom(byte[] bArr) {
            return ((a) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static CommandMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        public Action getActionMessage() {
            return this.actionMessage_;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public CommandMessage getDefaultInstanceForType() {
            return defaultInstance;
        }

        public Key getKeyMessage() {
            return this.keyMessage_;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int j = hasType() ? 0 + CodedOutputStream.j(1, getType().getNumber()) : 0;
            if (hasKeyMessage()) {
                j += CodedOutputStream.g(2, getKeyMessage());
            }
            if (hasTouchMessage()) {
                j += CodedOutputStream.g(3, getTouchMessage());
            }
            if (hasActionMessage()) {
                j += CodedOutputStream.g(4, getActionMessage());
            }
            int serializedSize = j + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public Touch getTouchMessage() {
            return this.touchMessage_;
        }

        public CommandType getType() {
            return this.type_;
        }

        public boolean hasActionMessage() {
            return this.hasActionMessage;
        }

        public boolean hasKeyMessage() {
            return this.hasKeyMessage;
        }

        public boolean hasTouchMessage() {
            return this.hasTouchMessage;
        }

        public boolean hasType() {
            return this.hasType;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Roap.h;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            if (this.hasType) {
                if (!hasKeyMessage() || getKeyMessage().isInitialized()) {
                    if (!hasTouchMessage() || getTouchMessage().isInitialized()) {
                        return !hasActionMessage() || getActionMessage().isInitialized();
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public a newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public a toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            if (hasType()) {
                codedOutputStream.d(1, getType().getNumber());
            }
            if (hasKeyMessage()) {
                codedOutputStream.c(2, getKeyMessage());
            }
            if (hasTouchMessage()) {
                codedOutputStream.c(3, getTouchMessage());
            }
            if (hasActionMessage()) {
                codedOutputStream.c(4, getActionMessage());
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class Data extends GeneratedMessage {
        public static final int BDATA_FIELD_NUMBER = 2;
        public static final int NDATA_FIELD_NUMBER = 3;
        public static final int STRDATA_FIELD_NUMBER = 4;
        public static final int TYPE_FIELD_NUMBER = 1;
        private static final Data defaultInstance = new Data(true);
        private boolean bData_;
        private boolean hasBData;
        private boolean hasNData;
        private boolean hasStrData;
        private boolean hasType;
        private int memoizedSerializedSize;
        private int nData_;
        private String strData_;
        private DataType type_;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public enum DataType implements ProtocolMessageEnum {
            UNKNOWN(0, 0),
            TEXT_LIMIT(1, 1),
            DMR_UUID(2, 2),
            DMR_AVAILABLE(3, 3),
            USB_AVAILABLE(4, 4);
            
            private final int index;
            private final int value;
            private static Internal.EnumLiteMap<DataType> internalValueMap = new Internal.EnumLiteMap<DataType>() { // from class: com.lge.UDAP.ROAP.Roap.Data.DataType.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public DataType findValueByNumber(int i) {
                    return DataType.valueOf(i);
                }
            };
            private static final DataType[] VALUES = {UNKNOWN, TEXT_LIMIT, DMR_UUID, DMR_AVAILABLE, USB_AVAILABLE};

            static {
                Roap.a();
            }

            DataType(int i, int i2) {
                this.index = i;
                this.value = i2;
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return Data.getDescriptor().getEnumTypes().get(0);
            }

            public static Internal.EnumLiteMap<DataType> internalGetValueMap() {
                return internalValueMap;
            }

            public static DataType valueOf(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                if (i != 4) {
                                    return null;
                                }
                                return USB_AVAILABLE;
                            }
                            return DMR_AVAILABLE;
                        }
                        return DMR_UUID;
                    }
                    return TEXT_LIMIT;
                }
                return UNKNOWN;
            }

            public static DataType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
        public static final class a extends GeneratedMessage.Builder<a> {
            private Data result;

            private a() {
            }

            static /* synthetic */ a access$15500() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public Data buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static a create() {
                a aVar = new a();
                aVar.result = new Data();
                return aVar;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Data build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Data buildPartial() {
                Data data = this.result;
                if (data != null) {
                    this.result = null;
                    return data;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public a clear() {
                if (this.result != null) {
                    this.result = new Data();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public a clearBData() {
                this.result.hasBData = false;
                this.result.bData_ = false;
                return this;
            }

            public a clearNData() {
                this.result.hasNData = false;
                this.result.nData_ = 0;
                return this;
            }

            public a clearStrData() {
                this.result.hasStrData = false;
                this.result.strData_ = Data.getDefaultInstance().getStrData();
                return this;
            }

            public a clearType() {
                this.result.hasType = false;
                this.result.type_ = DataType.UNKNOWN;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public a mo3clone() {
                return create().mergeFrom(this.result);
            }

            public boolean getBData() {
                return this.result.getBData();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Data getDefaultInstanceForType() {
                return Data.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return Data.getDescriptor();
            }

            public int getNData() {
                return this.result.getNData();
            }

            public String getStrData() {
                return this.result.getStrData();
            }

            public DataType getType() {
                return this.result.getType();
            }

            public boolean hasBData() {
                return this.result.hasBData();
            }

            public boolean hasNData() {
                return this.result.hasNData();
            }

            public boolean hasStrData() {
                return this.result.hasStrData();
            }

            public boolean hasType() {
                return this.result.hasType();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.Builder
            public Data internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public a mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 8) {
                        int n = codedInputStream.n();
                        DataType valueOf = DataType.valueOf(n);
                        if (valueOf == null) {
                            newBuilder.mergeVarintField(1, n);
                        } else {
                            setType(valueOf);
                        }
                    } else if (a == 16) {
                        setBData(codedInputStream.j());
                    } else if (a == 24) {
                        setNData(codedInputStream.g());
                    } else if (a == 34) {
                        setStrData(codedInputStream.k());
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public a mergeFrom(Message message) {
                if (message instanceof Data) {
                    return mergeFrom((Data) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public a mergeFrom(Data data) {
                if (data == Data.getDefaultInstance()) {
                    return this;
                }
                if (data.hasType()) {
                    setType(data.getType());
                }
                if (data.hasBData()) {
                    setBData(data.getBData());
                }
                if (data.hasNData()) {
                    setNData(data.getNData());
                }
                if (data.hasStrData()) {
                    setStrData(data.getStrData());
                }
                mergeUnknownFields(data.getUnknownFields());
                return this;
            }

            public a setBData(boolean z) {
                this.result.hasBData = true;
                this.result.bData_ = z;
                return this;
            }

            public a setNData(int i) {
                this.result.hasNData = true;
                this.result.nData_ = i;
                return this;
            }

            public a setStrData(String str) {
                if (str != null) {
                    this.result.hasStrData = true;
                    this.result.strData_ = str;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setType(DataType dataType) {
                if (dataType != null) {
                    this.result.hasType = true;
                    this.result.type_ = dataType;
                    return this;
                }
                throw new NullPointerException();
            }
        }

        static {
            Roap.b();
            defaultInstance.initFields();
        }

        private Data() {
            this.bData_ = false;
            this.nData_ = 0;
            this.strData_ = com.lge.media.launcher.a.d;
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private Data(boolean z) {
            this.bData_ = false;
            this.nData_ = 0;
            this.strData_ = com.lge.media.launcher.a.d;
            this.memoizedSerializedSize = -1;
        }

        public static Data getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Roap.u;
        }

        private void initFields() {
            this.type_ = DataType.UNKNOWN;
        }

        public static a newBuilder() {
            return a.access$15500();
        }

        public static a newBuilder(Data data) {
            return newBuilder().mergeFrom(data);
        }

        public static Data parseDelimitedFrom(InputStream inputStream) {
            a newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static Data parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            a newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static Data parseFrom(ByteString byteString) {
            return ((a) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static Data parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static Data parseFrom(CodedInputStream codedInputStream) {
            return ((a) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static Data parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static Data parseFrom(InputStream inputStream) {
            return ((a) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static Data parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static Data parseFrom(byte[] bArr) {
            return ((a) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static Data parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        public boolean getBData() {
            return this.bData_;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Data getDefaultInstanceForType() {
            return defaultInstance;
        }

        public int getNData() {
            return this.nData_;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int j = hasType() ? 0 + CodedOutputStream.j(1, getType().getNumber()) : 0;
            if (hasBData()) {
                j += CodedOutputStream.b(2, getBData());
            }
            if (hasNData()) {
                j += CodedOutputStream.g(3, getNData());
            }
            if (hasStrData()) {
                j += CodedOutputStream.b(4, getStrData());
            }
            int serializedSize = j + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public String getStrData() {
            return this.strData_;
        }

        public DataType getType() {
            return this.type_;
        }

        public boolean hasBData() {
            return this.hasBData;
        }

        public boolean hasNData() {
            return this.hasNData;
        }

        public boolean hasStrData() {
            return this.hasStrData;
        }

        public boolean hasType() {
            return this.hasType;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Roap.v;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            return this.hasType;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public a newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public a toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            if (hasType()) {
                codedOutputStream.d(1, getType().getNumber());
            }
            if (hasBData()) {
                codedOutputStream.a(2, getBData());
            }
            if (hasNData()) {
                codedOutputStream.a(3, getNData());
            }
            if (hasStrData()) {
                codedOutputStream.a(4, getStrData());
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class Demand extends GeneratedMessage {
        public static final int BDATA_FIELD_NUMBER = 5;
        public static final int BYTEDATA_FIELD_NUMBER = 8;
        public static final int BYTELENGTH_FIELD_NUMBER = 9;
        public static final int BYTETOTALLENGTH_FIELD_NUMBER = 10;
        public static final int NDATA_FIELD_NUMBER = 6;
        public static final int RESULT_MESSAGE_FIELD_NUMBER = 3;
        public static final int SEQUENCE_FIELD_NUMBER = 4;
        public static final int STRDATA_FIELD_NUMBER = 7;
        public static final int SUBTYPE_MESSAGE_FIELD_NUMBER = 2;
        public static final int TYPE_FIELD_NUMBER = 1;
        private static final Demand defaultInstance = new Demand(true);
        private boolean bData_;
        private ByteString byteData_;
        private int byteLength_;
        private int byteTotalLength_;
        private boolean hasBData;
        private boolean hasByteData;
        private boolean hasByteLength;
        private boolean hasByteTotalLength;
        private boolean hasNData;
        private boolean hasResultMessage;
        private boolean hasSequence;
        private boolean hasStrData;
        private boolean hasSubtypeMessage;
        private boolean hasType;
        private int memoizedSerializedSize;
        private int nData_;
        private Result resultMessage_;
        private int sequence_;
        private String strData_;
        private DemandSubtype subtypeMessage_;
        private DemandType type_;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static final class DemandSubtype extends GeneratedMessage {
            public static final int CONTENTSPATH_FIELD_NUMBER = 6;
            public static final int DATATYPE_FIELD_NUMBER = 1;
            public static final int ENDINDEX_FIELD_NUMBER = 8;
            public static final int INFOTYPE_FIELD_NUMBER = 2;
            public static final int LINKEDMEDIATYPE_FIELD_NUMBER = 3;
            public static final int LISTTYPE_FIELD_NUMBER = 4;
            public static final int MEDIATYPE_FIELD_NUMBER = 5;
            public static final int OFFSETINDEX_FIELD_NUMBER = 7;
            private static final DemandSubtype defaultInstance = new DemandSubtype(true);
            private String contentsPath_;
            private ContentsDataType dataType_;
            private int endIndex_;
            private boolean hasContentsPath;
            private boolean hasDataType;
            private boolean hasEndIndex;
            private boolean hasInfoType;
            private boolean hasLinkedMediaType;
            private boolean hasListType;
            private boolean hasMediaType;
            private boolean hasOffsetIndex;
            private ContentsInfoType infoType_;
            private ContentsLinkedMediaType linkedMediaType_;
            private ContentsListType listType_;
            private ContentsMediaType mediaType_;
            private int memoizedSerializedSize;
            private int offsetIndex_;

            /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
            public enum ContentsDataType implements ProtocolMessageEnum {
                TEXT_DATA(0, 0),
                THUMBNAIL_DATA(1, 1);
                
                private final int index;
                private final int value;
                private static Internal.EnumLiteMap<ContentsDataType> internalValueMap = new Internal.EnumLiteMap<ContentsDataType>() { // from class: com.lge.UDAP.ROAP.Roap.Demand.DemandSubtype.ContentsDataType.1
                    @Override // com.google.protobuf.Internal.EnumLiteMap
                    public ContentsDataType findValueByNumber(int i) {
                        return ContentsDataType.valueOf(i);
                    }
                };
                private static final ContentsDataType[] VALUES = {TEXT_DATA, THUMBNAIL_DATA};

                static {
                    Roap.a();
                }

                ContentsDataType(int i, int i2) {
                    this.index = i;
                    this.value = i2;
                }

                public static final Descriptors.EnumDescriptor getDescriptor() {
                    return DemandSubtype.getDescriptor().getEnumTypes().get(0);
                }

                public static Internal.EnumLiteMap<ContentsDataType> internalGetValueMap() {
                    return internalValueMap;
                }

                public static ContentsDataType valueOf(int i) {
                    if (i != 0) {
                        if (i != 1) {
                            return null;
                        }
                        return THUMBNAIL_DATA;
                    }
                    return TEXT_DATA;
                }

                public static ContentsDataType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
            public enum ContentsInfoType implements ProtocolMessageEnum {
                BGM_INFO(0, 0),
                DISC_INFO(1, 1),
                MEDIALINK_INFO(2, 2);
                
                private final int index;
                private final int value;
                private static Internal.EnumLiteMap<ContentsInfoType> internalValueMap = new Internal.EnumLiteMap<ContentsInfoType>() { // from class: com.lge.UDAP.ROAP.Roap.Demand.DemandSubtype.ContentsInfoType.1
                    @Override // com.google.protobuf.Internal.EnumLiteMap
                    public ContentsInfoType findValueByNumber(int i) {
                        return ContentsInfoType.valueOf(i);
                    }
                };
                private static final ContentsInfoType[] VALUES = {BGM_INFO, DISC_INFO, MEDIALINK_INFO};

                static {
                    Roap.a();
                }

                ContentsInfoType(int i, int i2) {
                    this.index = i;
                    this.value = i2;
                }

                public static final Descriptors.EnumDescriptor getDescriptor() {
                    return DemandSubtype.getDescriptor().getEnumTypes().get(1);
                }

                public static Internal.EnumLiteMap<ContentsInfoType> internalGetValueMap() {
                    return internalValueMap;
                }

                public static ContentsInfoType valueOf(int i) {
                    if (i != 0) {
                        if (i != 1) {
                            if (i != 2) {
                                return null;
                            }
                            return MEDIALINK_INFO;
                        }
                        return DISC_INFO;
                    }
                    return BGM_INFO;
                }

                public static ContentsInfoType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
            public enum ContentsLinkedMediaType implements ProtocolMessageEnum {
                HDD(0, 0),
                USB(1, 1),
                DISC(2, 2),
                PLEX(3, 3),
                DLNA(4, 4);
                
                private final int index;
                private final int value;
                private static Internal.EnumLiteMap<ContentsLinkedMediaType> internalValueMap = new Internal.EnumLiteMap<ContentsLinkedMediaType>() { // from class: com.lge.UDAP.ROAP.Roap.Demand.DemandSubtype.ContentsLinkedMediaType.1
                    @Override // com.google.protobuf.Internal.EnumLiteMap
                    public ContentsLinkedMediaType findValueByNumber(int i) {
                        return ContentsLinkedMediaType.valueOf(i);
                    }
                };
                private static final ContentsLinkedMediaType[] VALUES = {HDD, USB, DISC, PLEX, DLNA};

                static {
                    Roap.a();
                }

                ContentsLinkedMediaType(int i, int i2) {
                    this.index = i;
                    this.value = i2;
                }

                public static final Descriptors.EnumDescriptor getDescriptor() {
                    return DemandSubtype.getDescriptor().getEnumTypes().get(2);
                }

                public static Internal.EnumLiteMap<ContentsLinkedMediaType> internalGetValueMap() {
                    return internalValueMap;
                }

                public static ContentsLinkedMediaType valueOf(int i) {
                    if (i != 0) {
                        if (i != 1) {
                            if (i != 2) {
                                if (i != 3) {
                                    if (i != 4) {
                                        return null;
                                    }
                                    return DLNA;
                                }
                                return PLEX;
                            }
                            return DISC;
                        }
                        return USB;
                    }
                    return HDD;
                }

                public static ContentsLinkedMediaType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
            public enum ContentsListType implements ProtocolMessageEnum {
                ALL_LIST(0, 0),
                FAVORITE_LIST(1, 1),
                RECENTLY_ADDED_LIST(2, 2),
                RECENTLY_VIEW_LIST(3, 3);
                
                private final int index;
                private final int value;
                private static Internal.EnumLiteMap<ContentsListType> internalValueMap = new Internal.EnumLiteMap<ContentsListType>() { // from class: com.lge.UDAP.ROAP.Roap.Demand.DemandSubtype.ContentsListType.1
                    @Override // com.google.protobuf.Internal.EnumLiteMap
                    public ContentsListType findValueByNumber(int i) {
                        return ContentsListType.valueOf(i);
                    }
                };
                private static final ContentsListType[] VALUES = {ALL_LIST, FAVORITE_LIST, RECENTLY_ADDED_LIST, RECENTLY_VIEW_LIST};

                static {
                    Roap.a();
                }

                ContentsListType(int i, int i2) {
                    this.index = i;
                    this.value = i2;
                }

                public static final Descriptors.EnumDescriptor getDescriptor() {
                    return DemandSubtype.getDescriptor().getEnumTypes().get(3);
                }

                public static Internal.EnumLiteMap<ContentsListType> internalGetValueMap() {
                    return internalValueMap;
                }

                public static ContentsListType valueOf(int i) {
                    if (i != 0) {
                        if (i != 1) {
                            if (i != 2) {
                                if (i != 3) {
                                    return null;
                                }
                                return RECENTLY_VIEW_LIST;
                            }
                            return RECENTLY_ADDED_LIST;
                        }
                        return FAVORITE_LIST;
                    }
                    return ALL_LIST;
                }

                public static ContentsListType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
            public enum ContentsMediaType implements ProtocolMessageEnum {
                ALL_MEDIA(0, 0),
                MOVIE_MEDIA(1, 1),
                MUSIC_MEDIA(2, 2),
                PHOTO_MEDIA(3, 3);
                
                private final int index;
                private final int value;
                private static Internal.EnumLiteMap<ContentsMediaType> internalValueMap = new Internal.EnumLiteMap<ContentsMediaType>() { // from class: com.lge.UDAP.ROAP.Roap.Demand.DemandSubtype.ContentsMediaType.1
                    @Override // com.google.protobuf.Internal.EnumLiteMap
                    public ContentsMediaType findValueByNumber(int i) {
                        return ContentsMediaType.valueOf(i);
                    }
                };
                private static final ContentsMediaType[] VALUES = {ALL_MEDIA, MOVIE_MEDIA, MUSIC_MEDIA, PHOTO_MEDIA};

                static {
                    Roap.a();
                }

                ContentsMediaType(int i, int i2) {
                    this.index = i;
                    this.value = i2;
                }

                public static final Descriptors.EnumDescriptor getDescriptor() {
                    return DemandSubtype.getDescriptor().getEnumTypes().get(4);
                }

                public static Internal.EnumLiteMap<ContentsMediaType> internalGetValueMap() {
                    return internalValueMap;
                }

                public static ContentsMediaType valueOf(int i) {
                    if (i != 0) {
                        if (i != 1) {
                            if (i != 2) {
                                if (i != 3) {
                                    return null;
                                }
                                return PHOTO_MEDIA;
                            }
                            return MUSIC_MEDIA;
                        }
                        return MOVIE_MEDIA;
                    }
                    return ALL_MEDIA;
                }

                public static ContentsMediaType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
            public static final class a extends GeneratedMessage.Builder<a> {
                private DemandSubtype result;

                private a() {
                }

                static /* synthetic */ a access$17000() {
                    return create();
                }

                /* JADX INFO: Access modifiers changed from: private */
                public DemandSubtype buildParsed() {
                    if (isInitialized()) {
                        return buildPartial();
                    }
                    throw newUninitializedMessageException((Message) this.result).b();
                }

                private static a create() {
                    a aVar = new a();
                    aVar.result = new DemandSubtype();
                    return aVar;
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public DemandSubtype build() {
                    if (this.result == null || isInitialized()) {
                        return buildPartial();
                    }
                    throw newUninitializedMessageException((Message) this.result);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public DemandSubtype buildPartial() {
                    DemandSubtype demandSubtype = this.result;
                    if (demandSubtype != null) {
                        this.result = null;
                        return demandSubtype;
                    }
                    throw new IllegalStateException("build() has already been called on this Builder.");
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public a clear() {
                    if (this.result != null) {
                        this.result = new DemandSubtype();
                        return this;
                    }
                    throw new IllegalStateException("Cannot call clear() after build().");
                }

                public a clearContentsPath() {
                    this.result.hasContentsPath = false;
                    this.result.contentsPath_ = DemandSubtype.getDefaultInstance().getContentsPath();
                    return this;
                }

                public a clearDataType() {
                    this.result.hasDataType = false;
                    this.result.dataType_ = ContentsDataType.TEXT_DATA;
                    return this;
                }

                public a clearEndIndex() {
                    this.result.hasEndIndex = false;
                    this.result.endIndex_ = 0;
                    return this;
                }

                public a clearInfoType() {
                    this.result.hasInfoType = false;
                    this.result.infoType_ = ContentsInfoType.BGM_INFO;
                    return this;
                }

                public a clearLinkedMediaType() {
                    this.result.hasLinkedMediaType = false;
                    this.result.linkedMediaType_ = ContentsLinkedMediaType.HDD;
                    return this;
                }

                public a clearListType() {
                    this.result.hasListType = false;
                    this.result.listType_ = ContentsListType.ALL_LIST;
                    return this;
                }

                public a clearMediaType() {
                    this.result.hasMediaType = false;
                    this.result.mediaType_ = ContentsMediaType.ALL_MEDIA;
                    return this;
                }

                public a clearOffsetIndex() {
                    this.result.hasOffsetIndex = false;
                    this.result.offsetIndex_ = 0;
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
                /* renamed from: clone */
                public a mo3clone() {
                    return create().mergeFrom(this.result);
                }

                public String getContentsPath() {
                    return this.result.getContentsPath();
                }

                public ContentsDataType getDataType() {
                    return this.result.getDataType();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public DemandSubtype getDefaultInstanceForType() {
                    return DemandSubtype.getDefaultInstance();
                }

                @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
                public Descriptors.Descriptor getDescriptorForType() {
                    return DemandSubtype.getDescriptor();
                }

                public int getEndIndex() {
                    return this.result.getEndIndex();
                }

                public ContentsInfoType getInfoType() {
                    return this.result.getInfoType();
                }

                public ContentsLinkedMediaType getLinkedMediaType() {
                    return this.result.getLinkedMediaType();
                }

                public ContentsListType getListType() {
                    return this.result.getListType();
                }

                public ContentsMediaType getMediaType() {
                    return this.result.getMediaType();
                }

                public int getOffsetIndex() {
                    return this.result.getOffsetIndex();
                }

                public boolean hasContentsPath() {
                    return this.result.hasContentsPath();
                }

                public boolean hasDataType() {
                    return this.result.hasDataType();
                }

                public boolean hasEndIndex() {
                    return this.result.hasEndIndex();
                }

                public boolean hasInfoType() {
                    return this.result.hasInfoType();
                }

                public boolean hasLinkedMediaType() {
                    return this.result.hasLinkedMediaType();
                }

                public boolean hasListType() {
                    return this.result.hasListType();
                }

                public boolean hasMediaType() {
                    return this.result.hasMediaType();
                }

                public boolean hasOffsetIndex() {
                    return this.result.hasOffsetIndex();
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.google.protobuf.GeneratedMessage.Builder
                public DemandSubtype internalGetResult() {
                    return this.result;
                }

                @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
                public boolean isInitialized() {
                    return this.result.isInitialized();
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public a mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    int n;
                    int i;
                    UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                    while (true) {
                        int a = codedInputStream.a();
                        if (a == 0) {
                            break;
                        } else if (a == 8) {
                            n = codedInputStream.n();
                            ContentsDataType valueOf = ContentsDataType.valueOf(n);
                            if (valueOf == null) {
                                i = 1;
                                newBuilder.mergeVarintField(i, n);
                            } else {
                                setDataType(valueOf);
                            }
                        } else if (a == 16) {
                            n = codedInputStream.n();
                            ContentsInfoType valueOf2 = ContentsInfoType.valueOf(n);
                            if (valueOf2 == null) {
                                i = 2;
                                newBuilder.mergeVarintField(i, n);
                            } else {
                                setInfoType(valueOf2);
                            }
                        } else if (a == 24) {
                            n = codedInputStream.n();
                            ContentsLinkedMediaType valueOf3 = ContentsLinkedMediaType.valueOf(n);
                            if (valueOf3 == null) {
                                i = 3;
                                newBuilder.mergeVarintField(i, n);
                            } else {
                                setLinkedMediaType(valueOf3);
                            }
                        } else if (a == 32) {
                            n = codedInputStream.n();
                            ContentsListType valueOf4 = ContentsListType.valueOf(n);
                            if (valueOf4 == null) {
                                i = 4;
                                newBuilder.mergeVarintField(i, n);
                            } else {
                                setListType(valueOf4);
                            }
                        } else if (a == 40) {
                            n = codedInputStream.n();
                            ContentsMediaType valueOf5 = ContentsMediaType.valueOf(n);
                            if (valueOf5 == null) {
                                i = 5;
                                newBuilder.mergeVarintField(i, n);
                            } else {
                                setMediaType(valueOf5);
                            }
                        } else if (a == 50) {
                            setContentsPath(codedInputStream.k());
                        } else if (a == 56) {
                            setOffsetIndex(codedInputStream.g());
                        } else if (a == 64) {
                            setEndIndex(codedInputStream.g());
                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                            break;
                        }
                    }
                    setUnknownFields(newBuilder.build());
                    return this;
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public a mergeFrom(Message message) {
                    if (message instanceof DemandSubtype) {
                        return mergeFrom((DemandSubtype) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public a mergeFrom(DemandSubtype demandSubtype) {
                    if (demandSubtype == DemandSubtype.getDefaultInstance()) {
                        return this;
                    }
                    if (demandSubtype.hasDataType()) {
                        setDataType(demandSubtype.getDataType());
                    }
                    if (demandSubtype.hasInfoType()) {
                        setInfoType(demandSubtype.getInfoType());
                    }
                    if (demandSubtype.hasLinkedMediaType()) {
                        setLinkedMediaType(demandSubtype.getLinkedMediaType());
                    }
                    if (demandSubtype.hasListType()) {
                        setListType(demandSubtype.getListType());
                    }
                    if (demandSubtype.hasMediaType()) {
                        setMediaType(demandSubtype.getMediaType());
                    }
                    if (demandSubtype.hasContentsPath()) {
                        setContentsPath(demandSubtype.getContentsPath());
                    }
                    if (demandSubtype.hasOffsetIndex()) {
                        setOffsetIndex(demandSubtype.getOffsetIndex());
                    }
                    if (demandSubtype.hasEndIndex()) {
                        setEndIndex(demandSubtype.getEndIndex());
                    }
                    mergeUnknownFields(demandSubtype.getUnknownFields());
                    return this;
                }

                public a setContentsPath(String str) {
                    if (str != null) {
                        this.result.hasContentsPath = true;
                        this.result.contentsPath_ = str;
                        return this;
                    }
                    throw new NullPointerException();
                }

                public a setDataType(ContentsDataType contentsDataType) {
                    if (contentsDataType != null) {
                        this.result.hasDataType = true;
                        this.result.dataType_ = contentsDataType;
                        return this;
                    }
                    throw new NullPointerException();
                }

                public a setEndIndex(int i) {
                    this.result.hasEndIndex = true;
                    this.result.endIndex_ = i;
                    return this;
                }

                public a setInfoType(ContentsInfoType contentsInfoType) {
                    if (contentsInfoType != null) {
                        this.result.hasInfoType = true;
                        this.result.infoType_ = contentsInfoType;
                        return this;
                    }
                    throw new NullPointerException();
                }

                public a setLinkedMediaType(ContentsLinkedMediaType contentsLinkedMediaType) {
                    if (contentsLinkedMediaType != null) {
                        this.result.hasLinkedMediaType = true;
                        this.result.linkedMediaType_ = contentsLinkedMediaType;
                        return this;
                    }
                    throw new NullPointerException();
                }

                public a setListType(ContentsListType contentsListType) {
                    if (contentsListType != null) {
                        this.result.hasListType = true;
                        this.result.listType_ = contentsListType;
                        return this;
                    }
                    throw new NullPointerException();
                }

                public a setMediaType(ContentsMediaType contentsMediaType) {
                    if (contentsMediaType != null) {
                        this.result.hasMediaType = true;
                        this.result.mediaType_ = contentsMediaType;
                        return this;
                    }
                    throw new NullPointerException();
                }

                public a setOffsetIndex(int i) {
                    this.result.hasOffsetIndex = true;
                    this.result.offsetIndex_ = i;
                    return this;
                }
            }

            static {
                Roap.b();
                defaultInstance.initFields();
            }

            private DemandSubtype() {
                this.contentsPath_ = com.lge.media.launcher.a.d;
                this.offsetIndex_ = 0;
                this.endIndex_ = 0;
                this.memoizedSerializedSize = -1;
                initFields();
            }

            private DemandSubtype(boolean z) {
                this.contentsPath_ = com.lge.media.launcher.a.d;
                this.offsetIndex_ = 0;
                this.endIndex_ = 0;
                this.memoizedSerializedSize = -1;
            }

            public static DemandSubtype getDefaultInstance() {
                return defaultInstance;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Roap.y;
            }

            private void initFields() {
                this.dataType_ = ContentsDataType.TEXT_DATA;
                this.infoType_ = ContentsInfoType.BGM_INFO;
                this.linkedMediaType_ = ContentsLinkedMediaType.HDD;
                this.listType_ = ContentsListType.ALL_LIST;
                this.mediaType_ = ContentsMediaType.ALL_MEDIA;
            }

            public static a newBuilder() {
                return a.access$17000();
            }

            public static a newBuilder(DemandSubtype demandSubtype) {
                return newBuilder().mergeFrom(demandSubtype);
            }

            public static DemandSubtype parseDelimitedFrom(InputStream inputStream) {
                a newBuilder = newBuilder();
                if (newBuilder.mergeDelimitedFrom(inputStream)) {
                    return newBuilder.buildParsed();
                }
                return null;
            }

            public static DemandSubtype parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                a newBuilder = newBuilder();
                if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                    return newBuilder.buildParsed();
                }
                return null;
            }

            public static DemandSubtype parseFrom(ByteString byteString) {
                return ((a) newBuilder().mergeFrom(byteString)).buildParsed();
            }

            public static DemandSubtype parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return ((a) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
            }

            public static DemandSubtype parseFrom(CodedInputStream codedInputStream) {
                return ((a) newBuilder().mergeFrom(codedInputStream)).buildParsed();
            }

            public static DemandSubtype parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
            }

            public static DemandSubtype parseFrom(InputStream inputStream) {
                return ((a) newBuilder().mergeFrom(inputStream)).buildParsed();
            }

            public static DemandSubtype parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return ((a) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
            }

            public static DemandSubtype parseFrom(byte[] bArr) {
                return ((a) newBuilder().mergeFrom(bArr)).buildParsed();
            }

            public static DemandSubtype parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return ((a) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
            }

            public String getContentsPath() {
                return this.contentsPath_;
            }

            public ContentsDataType getDataType() {
                return this.dataType_;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public DemandSubtype getDefaultInstanceForType() {
                return defaultInstance;
            }

            public int getEndIndex() {
                return this.endIndex_;
            }

            public ContentsInfoType getInfoType() {
                return this.infoType_;
            }

            public ContentsLinkedMediaType getLinkedMediaType() {
                return this.linkedMediaType_;
            }

            public ContentsListType getListType() {
                return this.listType_;
            }

            public ContentsMediaType getMediaType() {
                return this.mediaType_;
            }

            public int getOffsetIndex() {
                return this.offsetIndex_;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSerializedSize;
                if (i != -1) {
                    return i;
                }
                int j = hasDataType() ? 0 + CodedOutputStream.j(1, getDataType().getNumber()) : 0;
                if (hasInfoType()) {
                    j += CodedOutputStream.j(2, getInfoType().getNumber());
                }
                if (hasLinkedMediaType()) {
                    j += CodedOutputStream.j(3, getLinkedMediaType().getNumber());
                }
                if (hasListType()) {
                    j += CodedOutputStream.j(4, getListType().getNumber());
                }
                if (hasMediaType()) {
                    j += CodedOutputStream.j(5, getMediaType().getNumber());
                }
                if (hasContentsPath()) {
                    j += CodedOutputStream.b(6, getContentsPath());
                }
                if (hasOffsetIndex()) {
                    j += CodedOutputStream.g(7, getOffsetIndex());
                }
                if (hasEndIndex()) {
                    j += CodedOutputStream.g(8, getEndIndex());
                }
                int serializedSize = j + getUnknownFields().getSerializedSize();
                this.memoizedSerializedSize = serializedSize;
                return serializedSize;
            }

            public boolean hasContentsPath() {
                return this.hasContentsPath;
            }

            public boolean hasDataType() {
                return this.hasDataType;
            }

            public boolean hasEndIndex() {
                return this.hasEndIndex;
            }

            public boolean hasInfoType() {
                return this.hasInfoType;
            }

            public boolean hasLinkedMediaType() {
                return this.hasLinkedMediaType;
            }

            public boolean hasListType() {
                return this.hasListType;
            }

            public boolean hasMediaType() {
                return this.hasMediaType;
            }

            public boolean hasOffsetIndex() {
                return this.hasOffsetIndex;
            }

            @Override // com.google.protobuf.GeneratedMessage
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return Roap.z;
            }

            @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public a newBuilderForType() {
                return newBuilder();
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public a toBuilder() {
                return newBuilder(this);
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) {
                getSerializedSize();
                if (hasDataType()) {
                    codedOutputStream.d(1, getDataType().getNumber());
                }
                if (hasInfoType()) {
                    codedOutputStream.d(2, getInfoType().getNumber());
                }
                if (hasLinkedMediaType()) {
                    codedOutputStream.d(3, getLinkedMediaType().getNumber());
                }
                if (hasListType()) {
                    codedOutputStream.d(4, getListType().getNumber());
                }
                if (hasMediaType()) {
                    codedOutputStream.d(5, getMediaType().getNumber());
                }
                if (hasContentsPath()) {
                    codedOutputStream.a(6, getContentsPath());
                }
                if (hasOffsetIndex()) {
                    codedOutputStream.a(7, getOffsetIndex());
                }
                if (hasEndIndex()) {
                    codedOutputStream.a(8, getEndIndex());
                }
                getUnknownFields().writeTo(codedOutputStream);
            }
        }

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public enum DemandType implements ProtocolMessageEnum {
            UNKNOWN(0, 0),
            PLAY(1, 1),
            CAPABILITY(2, 2),
            CONTENTS_INFO(3, 3),
            CONTENTS_LIST(4, 4),
            SYNC_STATUS_INFO(5, 5),
            SP_INFO(6, 6),
            CP_LIST(7, 7),
            EXTSPK_LIST(8, 8);
            
            private final int index;
            private final int value;
            private static Internal.EnumLiteMap<DemandType> internalValueMap = new Internal.EnumLiteMap<DemandType>() { // from class: com.lge.UDAP.ROAP.Roap.Demand.DemandType.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public DemandType findValueByNumber(int i) {
                    return DemandType.valueOf(i);
                }
            };
            private static final DemandType[] VALUES = {UNKNOWN, PLAY, CAPABILITY, CONTENTS_INFO, CONTENTS_LIST, SYNC_STATUS_INFO, SP_INFO, CP_LIST, EXTSPK_LIST};

            static {
                Roap.a();
            }

            DemandType(int i, int i2) {
                this.index = i;
                this.value = i2;
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return Demand.getDescriptor().getEnumTypes().get(0);
            }

            public static Internal.EnumLiteMap<DemandType> internalGetValueMap() {
                return internalValueMap;
            }

            public static DemandType valueOf(int i) {
                switch (i) {
                    case 0:
                        return UNKNOWN;
                    case 1:
                        return PLAY;
                    case 2:
                        return CAPABILITY;
                    case 3:
                        return CONTENTS_INFO;
                    case 4:
                        return CONTENTS_LIST;
                    case 5:
                        return SYNC_STATUS_INFO;
                    case 6:
                        return SP_INFO;
                    case 7:
                        return CP_LIST;
                    case 8:
                        return EXTSPK_LIST;
                    default:
                        return null;
                }
            }

            public static DemandType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
        public static final class Result extends GeneratedMessage {
            public static final int CAPRESULT_FIELD_NUMBER = 4;
            public static final int CONTENTSLISTRESULT_FIELD_NUMBER = 6;
            public static final int CPLISTRESULT_FIELD_NUMBER = 9;
            public static final int EXTSPKLISTRESULT_FIELD_NUMBER = 10;
            public static final int INFORESULT_FIELD_NUMBER = 5;
            public static final int PLAYRESULT_FIELD_NUMBER = 1;
            public static final int SOUNDPRIVACYINFORESULT_FIELD_NUMBER = 8;
            public static final int SYNCSTATUSINFORESULT_FIELD_NUMBER = 7;
            private static final Result defaultInstance = new Result(true);
            private CpListResult cPListResult_;
            private CapabilityResult capResult_;
            private ContentsListResult contentsListResult_;
            private ExtspkListResult eXTSPKListResult_;
            private boolean hasCPListResult;
            private boolean hasCapResult;
            private boolean hasContentsListResult;
            private boolean hasEXTSPKListResult;
            private boolean hasInfoResult;
            private boolean hasPlayResult;
            private boolean hasSoundPrivacyInfoResult;
            private boolean hasSyncStatusInfoResult;
            private InformationResult infoResult_;
            private int memoizedSerializedSize;
            private PlayResult playResult_;
            private SoundPrivacyInfoResult soundPrivacyInfoResult_;
            private SyncStatusInfoResult syncStatusInfoResult_;

            /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
            public enum CapabilityResult implements ProtocolMessageEnum {
                CAP_OK(0, 0),
                CAP_FAIL(1, 1);
                
                private final int index;
                private final int value;
                private static Internal.EnumLiteMap<CapabilityResult> internalValueMap = new Internal.EnumLiteMap<CapabilityResult>() { // from class: com.lge.UDAP.ROAP.Roap.Demand.Result.CapabilityResult.1
                    @Override // com.google.protobuf.Internal.EnumLiteMap
                    public CapabilityResult findValueByNumber(int i) {
                        return CapabilityResult.valueOf(i);
                    }
                };
                private static final CapabilityResult[] VALUES = {CAP_OK, CAP_FAIL};

                static {
                    Roap.a();
                }

                CapabilityResult(int i, int i2) {
                    this.index = i;
                    this.value = i2;
                }

                public static final Descriptors.EnumDescriptor getDescriptor() {
                    return Result.getDescriptor().getEnumTypes().get(1);
                }

                public static Internal.EnumLiteMap<CapabilityResult> internalGetValueMap() {
                    return internalValueMap;
                }

                public static CapabilityResult valueOf(int i) {
                    if (i != 0) {
                        if (i != 1) {
                            return null;
                        }
                        return CAP_FAIL;
                    }
                    return CAP_OK;
                }

                public static CapabilityResult valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
            public enum ContentsListResult implements ProtocolMessageEnum {
                CONTENTS_LIST_OK(0, 0),
                CONTENTS_LIST_FAIL(1, 1);
                
                private final int index;
                private final int value;
                private static Internal.EnumLiteMap<ContentsListResult> internalValueMap = new Internal.EnumLiteMap<ContentsListResult>() { // from class: com.lge.UDAP.ROAP.Roap.Demand.Result.ContentsListResult.1
                    @Override // com.google.protobuf.Internal.EnumLiteMap
                    public ContentsListResult findValueByNumber(int i) {
                        return ContentsListResult.valueOf(i);
                    }
                };
                private static final ContentsListResult[] VALUES = {CONTENTS_LIST_OK, CONTENTS_LIST_FAIL};

                static {
                    Roap.a();
                }

                ContentsListResult(int i, int i2) {
                    this.index = i;
                    this.value = i2;
                }

                public static final Descriptors.EnumDescriptor getDescriptor() {
                    return Result.getDescriptor().getEnumTypes().get(3);
                }

                public static Internal.EnumLiteMap<ContentsListResult> internalGetValueMap() {
                    return internalValueMap;
                }

                public static ContentsListResult valueOf(int i) {
                    if (i != 0) {
                        if (i != 1) {
                            return null;
                        }
                        return CONTENTS_LIST_FAIL;
                    }
                    return CONTENTS_LIST_OK;
                }

                public static ContentsListResult valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
            public enum CpListResult implements ProtocolMessageEnum {
                CP_LIST_OK(0, 0),
                CP_LIST_FAIL(1, 1);
                
                private final int index;
                private final int value;
                private static Internal.EnumLiteMap<CpListResult> internalValueMap = new Internal.EnumLiteMap<CpListResult>() { // from class: com.lge.UDAP.ROAP.Roap.Demand.Result.CpListResult.1
                    @Override // com.google.protobuf.Internal.EnumLiteMap
                    public CpListResult findValueByNumber(int i) {
                        return CpListResult.valueOf(i);
                    }
                };
                private static final CpListResult[] VALUES = {CP_LIST_OK, CP_LIST_FAIL};

                static {
                    Roap.a();
                }

                CpListResult(int i, int i2) {
                    this.index = i;
                    this.value = i2;
                }

                public static final Descriptors.EnumDescriptor getDescriptor() {
                    return Result.getDescriptor().getEnumTypes().get(6);
                }

                public static Internal.EnumLiteMap<CpListResult> internalGetValueMap() {
                    return internalValueMap;
                }

                public static CpListResult valueOf(int i) {
                    if (i != 0) {
                        if (i != 1) {
                            return null;
                        }
                        return CP_LIST_FAIL;
                    }
                    return CP_LIST_OK;
                }

                public static CpListResult valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
            public enum ExtspkListResult implements ProtocolMessageEnum {
                EXTSPK_LIST_OK(0, 0),
                EXTSPK_LIST_FAIL(1, 1);
                
                private final int index;
                private final int value;
                private static Internal.EnumLiteMap<ExtspkListResult> internalValueMap = new Internal.EnumLiteMap<ExtspkListResult>() { // from class: com.lge.UDAP.ROAP.Roap.Demand.Result.ExtspkListResult.1
                    @Override // com.google.protobuf.Internal.EnumLiteMap
                    public ExtspkListResult findValueByNumber(int i) {
                        return ExtspkListResult.valueOf(i);
                    }
                };
                private static final ExtspkListResult[] VALUES = {EXTSPK_LIST_OK, EXTSPK_LIST_FAIL};

                static {
                    Roap.a();
                }

                ExtspkListResult(int i, int i2) {
                    this.index = i;
                    this.value = i2;
                }

                public static final Descriptors.EnumDescriptor getDescriptor() {
                    return Result.getDescriptor().getEnumTypes().get(7);
                }

                public static Internal.EnumLiteMap<ExtspkListResult> internalGetValueMap() {
                    return internalValueMap;
                }

                public static ExtspkListResult valueOf(int i) {
                    if (i != 0) {
                        if (i != 1) {
                            return null;
                        }
                        return EXTSPK_LIST_FAIL;
                    }
                    return EXTSPK_LIST_OK;
                }

                public static ExtspkListResult valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
            public enum InformationResult implements ProtocolMessageEnum {
                INFO_OK(0, 0),
                INFO_FAIL(1, 1),
                INFO_INVALID_STATUS(2, 2),
                INFO_IN_PROGRESS(3, 3);
                
                private final int index;
                private final int value;
                private static Internal.EnumLiteMap<InformationResult> internalValueMap = new Internal.EnumLiteMap<InformationResult>() { // from class: com.lge.UDAP.ROAP.Roap.Demand.Result.InformationResult.1
                    @Override // com.google.protobuf.Internal.EnumLiteMap
                    public InformationResult findValueByNumber(int i) {
                        return InformationResult.valueOf(i);
                    }
                };
                private static final InformationResult[] VALUES = {INFO_OK, INFO_FAIL, INFO_INVALID_STATUS, INFO_IN_PROGRESS};

                static {
                    Roap.a();
                }

                InformationResult(int i, int i2) {
                    this.index = i;
                    this.value = i2;
                }

                public static final Descriptors.EnumDescriptor getDescriptor() {
                    return Result.getDescriptor().getEnumTypes().get(2);
                }

                public static Internal.EnumLiteMap<InformationResult> internalGetValueMap() {
                    return internalValueMap;
                }

                public static InformationResult valueOf(int i) {
                    if (i != 0) {
                        if (i != 1) {
                            if (i != 2) {
                                if (i != 3) {
                                    return null;
                                }
                                return INFO_IN_PROGRESS;
                            }
                            return INFO_INVALID_STATUS;
                        }
                        return INFO_FAIL;
                    }
                    return INFO_OK;
                }

                public static InformationResult valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
            public enum PlayResult implements ProtocolMessageEnum {
                PLAY_OK(0, 0),
                PLAY_FAIL(1, 1),
                PLAY_UNSUPPORTED_VIDEOCODEC(2, 2),
                PLAY_UNSUPPORTED_AUDIOCODEC(3, 3),
                PLAY_UNKNOWN_VIDEO(4, 4);
                
                private final int index;
                private final int value;
                private static Internal.EnumLiteMap<PlayResult> internalValueMap = new Internal.EnumLiteMap<PlayResult>() { // from class: com.lge.UDAP.ROAP.Roap.Demand.Result.PlayResult.1
                    @Override // com.google.protobuf.Internal.EnumLiteMap
                    public PlayResult findValueByNumber(int i) {
                        return PlayResult.valueOf(i);
                    }
                };
                private static final PlayResult[] VALUES = {PLAY_OK, PLAY_FAIL, PLAY_UNSUPPORTED_VIDEOCODEC, PLAY_UNSUPPORTED_AUDIOCODEC, PLAY_UNKNOWN_VIDEO};

                static {
                    Roap.a();
                }

                PlayResult(int i, int i2) {
                    this.index = i;
                    this.value = i2;
                }

                public static final Descriptors.EnumDescriptor getDescriptor() {
                    return Result.getDescriptor().getEnumTypes().get(0);
                }

                public static Internal.EnumLiteMap<PlayResult> internalGetValueMap() {
                    return internalValueMap;
                }

                public static PlayResult valueOf(int i) {
                    if (i != 0) {
                        if (i != 1) {
                            if (i != 2) {
                                if (i != 3) {
                                    if (i != 4) {
                                        return null;
                                    }
                                    return PLAY_UNKNOWN_VIDEO;
                                }
                                return PLAY_UNSUPPORTED_AUDIOCODEC;
                            }
                            return PLAY_UNSUPPORTED_VIDEOCODEC;
                        }
                        return PLAY_FAIL;
                    }
                    return PLAY_OK;
                }

                public static PlayResult valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
            public enum SoundPrivacyInfoResult implements ProtocolMessageEnum {
                SP_INFO_OK(0, 0),
                SP_INFO_FAIL(1, 1);
                
                private final int index;
                private final int value;
                private static Internal.EnumLiteMap<SoundPrivacyInfoResult> internalValueMap = new Internal.EnumLiteMap<SoundPrivacyInfoResult>() { // from class: com.lge.UDAP.ROAP.Roap.Demand.Result.SoundPrivacyInfoResult.1
                    @Override // com.google.protobuf.Internal.EnumLiteMap
                    public SoundPrivacyInfoResult findValueByNumber(int i) {
                        return SoundPrivacyInfoResult.valueOf(i);
                    }
                };
                private static final SoundPrivacyInfoResult[] VALUES = {SP_INFO_OK, SP_INFO_FAIL};

                static {
                    Roap.a();
                }

                SoundPrivacyInfoResult(int i, int i2) {
                    this.index = i;
                    this.value = i2;
                }

                public static final Descriptors.EnumDescriptor getDescriptor() {
                    return Result.getDescriptor().getEnumTypes().get(5);
                }

                public static Internal.EnumLiteMap<SoundPrivacyInfoResult> internalGetValueMap() {
                    return internalValueMap;
                }

                public static SoundPrivacyInfoResult valueOf(int i) {
                    if (i != 0) {
                        if (i != 1) {
                            return null;
                        }
                        return SP_INFO_FAIL;
                    }
                    return SP_INFO_OK;
                }

                public static SoundPrivacyInfoResult valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
            public enum SyncStatusInfoResult implements ProtocolMessageEnum {
                SYNC_STATUS_INFO_OK(0, 0),
                SYNC_STATUS_INFO_FAIL(1, 1),
                SYNC_STATUS_INFO_TEXT(2, 2),
                SYNC_STATUS_INFO_TOUCH(3, 3);
                
                private final int index;
                private final int value;
                private static Internal.EnumLiteMap<SyncStatusInfoResult> internalValueMap = new Internal.EnumLiteMap<SyncStatusInfoResult>() { // from class: com.lge.UDAP.ROAP.Roap.Demand.Result.SyncStatusInfoResult.1
                    @Override // com.google.protobuf.Internal.EnumLiteMap
                    public SyncStatusInfoResult findValueByNumber(int i) {
                        return SyncStatusInfoResult.valueOf(i);
                    }
                };
                private static final SyncStatusInfoResult[] VALUES = {SYNC_STATUS_INFO_OK, SYNC_STATUS_INFO_FAIL, SYNC_STATUS_INFO_TEXT, SYNC_STATUS_INFO_TOUCH};

                static {
                    Roap.a();
                }

                SyncStatusInfoResult(int i, int i2) {
                    this.index = i;
                    this.value = i2;
                }

                public static final Descriptors.EnumDescriptor getDescriptor() {
                    return Result.getDescriptor().getEnumTypes().get(4);
                }

                public static Internal.EnumLiteMap<SyncStatusInfoResult> internalGetValueMap() {
                    return internalValueMap;
                }

                public static SyncStatusInfoResult valueOf(int i) {
                    if (i != 0) {
                        if (i != 1) {
                            if (i != 2) {
                                if (i != 3) {
                                    return null;
                                }
                                return SYNC_STATUS_INFO_TOUCH;
                            }
                            return SYNC_STATUS_INFO_TEXT;
                        }
                        return SYNC_STATUS_INFO_FAIL;
                    }
                    return SYNC_STATUS_INFO_OK;
                }

                public static SyncStatusInfoResult valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
            public static final class a extends GeneratedMessage.Builder<a> {
                private Result result;

                private a() {
                }

                static /* synthetic */ a access$19100() {
                    return create();
                }

                /* JADX INFO: Access modifiers changed from: private */
                public Result buildParsed() {
                    if (isInitialized()) {
                        return buildPartial();
                    }
                    throw newUninitializedMessageException((Message) this.result).b();
                }

                private static a create() {
                    a aVar = new a();
                    aVar.result = new Result();
                    return aVar;
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Result build() {
                    if (this.result == null || isInitialized()) {
                        return buildPartial();
                    }
                    throw newUninitializedMessageException((Message) this.result);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Result buildPartial() {
                    Result result = this.result;
                    if (result != null) {
                        this.result = null;
                        return result;
                    }
                    throw new IllegalStateException("build() has already been called on this Builder.");
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public a clear() {
                    if (this.result != null) {
                        this.result = new Result();
                        return this;
                    }
                    throw new IllegalStateException("Cannot call clear() after build().");
                }

                public a clearCPListResult() {
                    this.result.hasCPListResult = false;
                    this.result.cPListResult_ = CpListResult.CP_LIST_OK;
                    return this;
                }

                public a clearCapResult() {
                    this.result.hasCapResult = false;
                    this.result.capResult_ = CapabilityResult.CAP_OK;
                    return this;
                }

                public a clearContentsListResult() {
                    this.result.hasContentsListResult = false;
                    this.result.contentsListResult_ = ContentsListResult.CONTENTS_LIST_OK;
                    return this;
                }

                public a clearEXTSPKListResult() {
                    this.result.hasEXTSPKListResult = false;
                    this.result.eXTSPKListResult_ = ExtspkListResult.EXTSPK_LIST_OK;
                    return this;
                }

                public a clearInfoResult() {
                    this.result.hasInfoResult = false;
                    this.result.infoResult_ = InformationResult.INFO_OK;
                    return this;
                }

                public a clearPlayResult() {
                    this.result.hasPlayResult = false;
                    this.result.playResult_ = PlayResult.PLAY_OK;
                    return this;
                }

                public a clearSoundPrivacyInfoResult() {
                    this.result.hasSoundPrivacyInfoResult = false;
                    this.result.soundPrivacyInfoResult_ = SoundPrivacyInfoResult.SP_INFO_OK;
                    return this;
                }

                public a clearSyncStatusInfoResult() {
                    this.result.hasSyncStatusInfoResult = false;
                    this.result.syncStatusInfoResult_ = SyncStatusInfoResult.SYNC_STATUS_INFO_OK;
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
                /* renamed from: clone */
                public a mo3clone() {
                    return create().mergeFrom(this.result);
                }

                public CpListResult getCPListResult() {
                    return this.result.getCPListResult();
                }

                public CapabilityResult getCapResult() {
                    return this.result.getCapResult();
                }

                public ContentsListResult getContentsListResult() {
                    return this.result.getContentsListResult();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Result getDefaultInstanceForType() {
                    return Result.getDefaultInstance();
                }

                @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
                public Descriptors.Descriptor getDescriptorForType() {
                    return Result.getDescriptor();
                }

                public ExtspkListResult getEXTSPKListResult() {
                    return this.result.getEXTSPKListResult();
                }

                public InformationResult getInfoResult() {
                    return this.result.getInfoResult();
                }

                public PlayResult getPlayResult() {
                    return this.result.getPlayResult();
                }

                public SoundPrivacyInfoResult getSoundPrivacyInfoResult() {
                    return this.result.getSoundPrivacyInfoResult();
                }

                public SyncStatusInfoResult getSyncStatusInfoResult() {
                    return this.result.getSyncStatusInfoResult();
                }

                public boolean hasCPListResult() {
                    return this.result.hasCPListResult();
                }

                public boolean hasCapResult() {
                    return this.result.hasCapResult();
                }

                public boolean hasContentsListResult() {
                    return this.result.hasContentsListResult();
                }

                public boolean hasEXTSPKListResult() {
                    return this.result.hasEXTSPKListResult();
                }

                public boolean hasInfoResult() {
                    return this.result.hasInfoResult();
                }

                public boolean hasPlayResult() {
                    return this.result.hasPlayResult();
                }

                public boolean hasSoundPrivacyInfoResult() {
                    return this.result.hasSoundPrivacyInfoResult();
                }

                public boolean hasSyncStatusInfoResult() {
                    return this.result.hasSyncStatusInfoResult();
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.google.protobuf.GeneratedMessage.Builder
                public Result internalGetResult() {
                    return this.result;
                }

                @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
                public boolean isInitialized() {
                    return this.result.isInitialized();
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public a mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    int n;
                    UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                    while (true) {
                        int a = codedInputStream.a();
                        if (a == 0) {
                            break;
                        }
                        int i = 8;
                        if (a == 8) {
                            n = codedInputStream.n();
                            PlayResult valueOf = PlayResult.valueOf(n);
                            if (valueOf == null) {
                                i = 1;
                                newBuilder.mergeVarintField(i, n);
                            } else {
                                setPlayResult(valueOf);
                            }
                        } else if (a == 32) {
                            n = codedInputStream.n();
                            CapabilityResult valueOf2 = CapabilityResult.valueOf(n);
                            if (valueOf2 == null) {
                                i = 4;
                                newBuilder.mergeVarintField(i, n);
                            } else {
                                setCapResult(valueOf2);
                            }
                        } else if (a == 40) {
                            n = codedInputStream.n();
                            InformationResult valueOf3 = InformationResult.valueOf(n);
                            if (valueOf3 == null) {
                                i = 5;
                                newBuilder.mergeVarintField(i, n);
                            } else {
                                setInfoResult(valueOf3);
                            }
                        } else if (a == 48) {
                            n = codedInputStream.n();
                            ContentsListResult valueOf4 = ContentsListResult.valueOf(n);
                            if (valueOf4 == null) {
                                i = 6;
                                newBuilder.mergeVarintField(i, n);
                            } else {
                                setContentsListResult(valueOf4);
                            }
                        } else if (a == 56) {
                            n = codedInputStream.n();
                            SyncStatusInfoResult valueOf5 = SyncStatusInfoResult.valueOf(n);
                            if (valueOf5 == null) {
                                i = 7;
                                newBuilder.mergeVarintField(i, n);
                            } else {
                                setSyncStatusInfoResult(valueOf5);
                            }
                        } else if (a == 64) {
                            n = codedInputStream.n();
                            SoundPrivacyInfoResult valueOf6 = SoundPrivacyInfoResult.valueOf(n);
                            if (valueOf6 == null) {
                                newBuilder.mergeVarintField(i, n);
                            } else {
                                setSoundPrivacyInfoResult(valueOf6);
                            }
                        } else if (a == 72) {
                            n = codedInputStream.n();
                            CpListResult valueOf7 = CpListResult.valueOf(n);
                            if (valueOf7 == null) {
                                i = 9;
                                newBuilder.mergeVarintField(i, n);
                            } else {
                                setCPListResult(valueOf7);
                            }
                        } else if (a == 80) {
                            n = codedInputStream.n();
                            ExtspkListResult valueOf8 = ExtspkListResult.valueOf(n);
                            if (valueOf8 == null) {
                                i = 10;
                                newBuilder.mergeVarintField(i, n);
                            } else {
                                setEXTSPKListResult(valueOf8);
                            }
                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                            break;
                        }
                    }
                    setUnknownFields(newBuilder.build());
                    return this;
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public a mergeFrom(Message message) {
                    if (message instanceof Result) {
                        return mergeFrom((Result) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public a mergeFrom(Result result) {
                    if (result == Result.getDefaultInstance()) {
                        return this;
                    }
                    if (result.hasPlayResult()) {
                        setPlayResult(result.getPlayResult());
                    }
                    if (result.hasCapResult()) {
                        setCapResult(result.getCapResult());
                    }
                    if (result.hasInfoResult()) {
                        setInfoResult(result.getInfoResult());
                    }
                    if (result.hasContentsListResult()) {
                        setContentsListResult(result.getContentsListResult());
                    }
                    if (result.hasSyncStatusInfoResult()) {
                        setSyncStatusInfoResult(result.getSyncStatusInfoResult());
                    }
                    if (result.hasSoundPrivacyInfoResult()) {
                        setSoundPrivacyInfoResult(result.getSoundPrivacyInfoResult());
                    }
                    if (result.hasCPListResult()) {
                        setCPListResult(result.getCPListResult());
                    }
                    if (result.hasEXTSPKListResult()) {
                        setEXTSPKListResult(result.getEXTSPKListResult());
                    }
                    mergeUnknownFields(result.getUnknownFields());
                    return this;
                }

                public a setCPListResult(CpListResult cpListResult) {
                    if (cpListResult != null) {
                        this.result.hasCPListResult = true;
                        this.result.cPListResult_ = cpListResult;
                        return this;
                    }
                    throw new NullPointerException();
                }

                public a setCapResult(CapabilityResult capabilityResult) {
                    if (capabilityResult != null) {
                        this.result.hasCapResult = true;
                        this.result.capResult_ = capabilityResult;
                        return this;
                    }
                    throw new NullPointerException();
                }

                public a setContentsListResult(ContentsListResult contentsListResult) {
                    if (contentsListResult != null) {
                        this.result.hasContentsListResult = true;
                        this.result.contentsListResult_ = contentsListResult;
                        return this;
                    }
                    throw new NullPointerException();
                }

                public a setEXTSPKListResult(ExtspkListResult extspkListResult) {
                    if (extspkListResult != null) {
                        this.result.hasEXTSPKListResult = true;
                        this.result.eXTSPKListResult_ = extspkListResult;
                        return this;
                    }
                    throw new NullPointerException();
                }

                public a setInfoResult(InformationResult informationResult) {
                    if (informationResult != null) {
                        this.result.hasInfoResult = true;
                        this.result.infoResult_ = informationResult;
                        return this;
                    }
                    throw new NullPointerException();
                }

                public a setPlayResult(PlayResult playResult) {
                    if (playResult != null) {
                        this.result.hasPlayResult = true;
                        this.result.playResult_ = playResult;
                        return this;
                    }
                    throw new NullPointerException();
                }

                public a setSoundPrivacyInfoResult(SoundPrivacyInfoResult soundPrivacyInfoResult) {
                    if (soundPrivacyInfoResult != null) {
                        this.result.hasSoundPrivacyInfoResult = true;
                        this.result.soundPrivacyInfoResult_ = soundPrivacyInfoResult;
                        return this;
                    }
                    throw new NullPointerException();
                }

                public a setSyncStatusInfoResult(SyncStatusInfoResult syncStatusInfoResult) {
                    if (syncStatusInfoResult != null) {
                        this.result.hasSyncStatusInfoResult = true;
                        this.result.syncStatusInfoResult_ = syncStatusInfoResult;
                        return this;
                    }
                    throw new NullPointerException();
                }
            }

            static {
                Roap.b();
                defaultInstance.initFields();
            }

            private Result() {
                this.memoizedSerializedSize = -1;
                initFields();
            }

            private Result(boolean z) {
                this.memoizedSerializedSize = -1;
            }

            public static Result getDefaultInstance() {
                return defaultInstance;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Roap.A;
            }

            private void initFields() {
                this.playResult_ = PlayResult.PLAY_OK;
                this.capResult_ = CapabilityResult.CAP_OK;
                this.infoResult_ = InformationResult.INFO_OK;
                this.contentsListResult_ = ContentsListResult.CONTENTS_LIST_OK;
                this.syncStatusInfoResult_ = SyncStatusInfoResult.SYNC_STATUS_INFO_OK;
                this.soundPrivacyInfoResult_ = SoundPrivacyInfoResult.SP_INFO_OK;
                this.cPListResult_ = CpListResult.CP_LIST_OK;
                this.eXTSPKListResult_ = ExtspkListResult.EXTSPK_LIST_OK;
            }

            public static a newBuilder() {
                return a.access$19100();
            }

            public static a newBuilder(Result result) {
                return newBuilder().mergeFrom(result);
            }

            public static Result parseDelimitedFrom(InputStream inputStream) {
                a newBuilder = newBuilder();
                if (newBuilder.mergeDelimitedFrom(inputStream)) {
                    return newBuilder.buildParsed();
                }
                return null;
            }

            public static Result parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                a newBuilder = newBuilder();
                if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                    return newBuilder.buildParsed();
                }
                return null;
            }

            public static Result parseFrom(ByteString byteString) {
                return ((a) newBuilder().mergeFrom(byteString)).buildParsed();
            }

            public static Result parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return ((a) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
            }

            public static Result parseFrom(CodedInputStream codedInputStream) {
                return ((a) newBuilder().mergeFrom(codedInputStream)).buildParsed();
            }

            public static Result parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
            }

            public static Result parseFrom(InputStream inputStream) {
                return ((a) newBuilder().mergeFrom(inputStream)).buildParsed();
            }

            public static Result parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return ((a) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
            }

            public static Result parseFrom(byte[] bArr) {
                return ((a) newBuilder().mergeFrom(bArr)).buildParsed();
            }

            public static Result parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return ((a) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
            }

            public CpListResult getCPListResult() {
                return this.cPListResult_;
            }

            public CapabilityResult getCapResult() {
                return this.capResult_;
            }

            public ContentsListResult getContentsListResult() {
                return this.contentsListResult_;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Result getDefaultInstanceForType() {
                return defaultInstance;
            }

            public ExtspkListResult getEXTSPKListResult() {
                return this.eXTSPKListResult_;
            }

            public InformationResult getInfoResult() {
                return this.infoResult_;
            }

            public PlayResult getPlayResult() {
                return this.playResult_;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSerializedSize;
                if (i != -1) {
                    return i;
                }
                int j = hasPlayResult() ? 0 + CodedOutputStream.j(1, getPlayResult().getNumber()) : 0;
                if (hasCapResult()) {
                    j += CodedOutputStream.j(4, getCapResult().getNumber());
                }
                if (hasInfoResult()) {
                    j += CodedOutputStream.j(5, getInfoResult().getNumber());
                }
                if (hasContentsListResult()) {
                    j += CodedOutputStream.j(6, getContentsListResult().getNumber());
                }
                if (hasSyncStatusInfoResult()) {
                    j += CodedOutputStream.j(7, getSyncStatusInfoResult().getNumber());
                }
                if (hasSoundPrivacyInfoResult()) {
                    j += CodedOutputStream.j(8, getSoundPrivacyInfoResult().getNumber());
                }
                if (hasCPListResult()) {
                    j += CodedOutputStream.j(9, getCPListResult().getNumber());
                }
                if (hasEXTSPKListResult()) {
                    j += CodedOutputStream.j(10, getEXTSPKListResult().getNumber());
                }
                int serializedSize = j + getUnknownFields().getSerializedSize();
                this.memoizedSerializedSize = serializedSize;
                return serializedSize;
            }

            public SoundPrivacyInfoResult getSoundPrivacyInfoResult() {
                return this.soundPrivacyInfoResult_;
            }

            public SyncStatusInfoResult getSyncStatusInfoResult() {
                return this.syncStatusInfoResult_;
            }

            public boolean hasCPListResult() {
                return this.hasCPListResult;
            }

            public boolean hasCapResult() {
                return this.hasCapResult;
            }

            public boolean hasContentsListResult() {
                return this.hasContentsListResult;
            }

            public boolean hasEXTSPKListResult() {
                return this.hasEXTSPKListResult;
            }

            public boolean hasInfoResult() {
                return this.hasInfoResult;
            }

            public boolean hasPlayResult() {
                return this.hasPlayResult;
            }

            public boolean hasSoundPrivacyInfoResult() {
                return this.hasSoundPrivacyInfoResult;
            }

            public boolean hasSyncStatusInfoResult() {
                return this.hasSyncStatusInfoResult;
            }

            @Override // com.google.protobuf.GeneratedMessage
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return Roap.B;
            }

            @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public a newBuilderForType() {
                return newBuilder();
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public a toBuilder() {
                return newBuilder(this);
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) {
                getSerializedSize();
                if (hasPlayResult()) {
                    codedOutputStream.d(1, getPlayResult().getNumber());
                }
                if (hasCapResult()) {
                    codedOutputStream.d(4, getCapResult().getNumber());
                }
                if (hasInfoResult()) {
                    codedOutputStream.d(5, getInfoResult().getNumber());
                }
                if (hasContentsListResult()) {
                    codedOutputStream.d(6, getContentsListResult().getNumber());
                }
                if (hasSyncStatusInfoResult()) {
                    codedOutputStream.d(7, getSyncStatusInfoResult().getNumber());
                }
                if (hasSoundPrivacyInfoResult()) {
                    codedOutputStream.d(8, getSoundPrivacyInfoResult().getNumber());
                }
                if (hasCPListResult()) {
                    codedOutputStream.d(9, getCPListResult().getNumber());
                }
                if (hasEXTSPKListResult()) {
                    codedOutputStream.d(10, getEXTSPKListResult().getNumber());
                }
                getUnknownFields().writeTo(codedOutputStream);
            }
        }

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static final class a extends GeneratedMessage.Builder<a> {
            private Demand result;

            private a() {
            }

            static /* synthetic */ a access$21000() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public Demand buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static a create() {
                a aVar = new a();
                aVar.result = new Demand();
                return aVar;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Demand build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Demand buildPartial() {
                Demand demand = this.result;
                if (demand != null) {
                    this.result = null;
                    return demand;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public a clear() {
                if (this.result != null) {
                    this.result = new Demand();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public a clearBData() {
                this.result.hasBData = false;
                this.result.bData_ = false;
                return this;
            }

            public a clearByteData() {
                this.result.hasByteData = false;
                this.result.byteData_ = Demand.getDefaultInstance().getByteData();
                return this;
            }

            public a clearByteLength() {
                this.result.hasByteLength = false;
                this.result.byteLength_ = 0;
                return this;
            }

            public a clearByteTotalLength() {
                this.result.hasByteTotalLength = false;
                this.result.byteTotalLength_ = 0;
                return this;
            }

            public a clearNData() {
                this.result.hasNData = false;
                this.result.nData_ = 0;
                return this;
            }

            public a clearResultMessage() {
                this.result.hasResultMessage = false;
                this.result.resultMessage_ = Result.getDefaultInstance();
                return this;
            }

            public a clearSequence() {
                this.result.hasSequence = false;
                this.result.sequence_ = 0;
                return this;
            }

            public a clearStrData() {
                this.result.hasStrData = false;
                this.result.strData_ = Demand.getDefaultInstance().getStrData();
                return this;
            }

            public a clearSubtypeMessage() {
                this.result.hasSubtypeMessage = false;
                this.result.subtypeMessage_ = DemandSubtype.getDefaultInstance();
                return this;
            }

            public a clearType() {
                this.result.hasType = false;
                this.result.type_ = DemandType.UNKNOWN;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public a mo3clone() {
                return create().mergeFrom(this.result);
            }

            public boolean getBData() {
                return this.result.getBData();
            }

            public ByteString getByteData() {
                return this.result.getByteData();
            }

            public int getByteLength() {
                return this.result.getByteLength();
            }

            public int getByteTotalLength() {
                return this.result.getByteTotalLength();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Demand getDefaultInstanceForType() {
                return Demand.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return Demand.getDescriptor();
            }

            public int getNData() {
                return this.result.getNData();
            }

            public Result getResultMessage() {
                return this.result.getResultMessage();
            }

            public int getSequence() {
                return this.result.getSequence();
            }

            public String getStrData() {
                return this.result.getStrData();
            }

            public DemandSubtype getSubtypeMessage() {
                return this.result.getSubtypeMessage();
            }

            public DemandType getType() {
                return this.result.getType();
            }

            public boolean hasBData() {
                return this.result.hasBData();
            }

            public boolean hasByteData() {
                return this.result.hasByteData();
            }

            public boolean hasByteLength() {
                return this.result.hasByteLength();
            }

            public boolean hasByteTotalLength() {
                return this.result.hasByteTotalLength();
            }

            public boolean hasNData() {
                return this.result.hasNData();
            }

            public boolean hasResultMessage() {
                return this.result.hasResultMessage();
            }

            public boolean hasSequence() {
                return this.result.hasSequence();
            }

            public boolean hasStrData() {
                return this.result.hasStrData();
            }

            public boolean hasSubtypeMessage() {
                return this.result.hasSubtypeMessage();
            }

            public boolean hasType() {
                return this.result.hasType();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.Builder
            public Demand internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public a mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    switch (a) {
                        case 0:
                            break;
                        case 8:
                            int n = codedInputStream.n();
                            DemandType valueOf = DemandType.valueOf(n);
                            if (valueOf != null) {
                                setType(valueOf);
                                break;
                            } else {
                                newBuilder.mergeVarintField(1, n);
                                break;
                            }
                        case 18:
                            DemandSubtype.a newBuilder2 = DemandSubtype.newBuilder();
                            if (hasSubtypeMessage()) {
                                newBuilder2.mergeFrom(getSubtypeMessage());
                            }
                            codedInputStream.a(newBuilder2, extensionRegistryLite);
                            setSubtypeMessage(newBuilder2.buildPartial());
                            break;
                        case 26:
                            Result.a newBuilder3 = Result.newBuilder();
                            if (hasResultMessage()) {
                                newBuilder3.mergeFrom(getResultMessage());
                            }
                            codedInputStream.a(newBuilder3, extensionRegistryLite);
                            setResultMessage(newBuilder3.buildPartial());
                            break;
                        case 32:
                            setSequence(codedInputStream.g());
                            break;
                        case 40:
                            setBData(codedInputStream.j());
                            break;
                        case 48:
                            setNData(codedInputStream.g());
                            break;
                        case 58:
                            setStrData(codedInputStream.k());
                            break;
                        case 66:
                            setByteData(codedInputStream.l());
                            break;
                        case 72:
                            setByteLength(codedInputStream.g());
                            break;
                        case 80:
                            setByteTotalLength(codedInputStream.g());
                            break;
                        default:
                            if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                                break;
                            } else {
                                break;
                            }
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public a mergeFrom(Message message) {
                if (message instanceof Demand) {
                    return mergeFrom((Demand) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public a mergeFrom(Demand demand) {
                if (demand == Demand.getDefaultInstance()) {
                    return this;
                }
                if (demand.hasType()) {
                    setType(demand.getType());
                }
                if (demand.hasSubtypeMessage()) {
                    mergeSubtypeMessage(demand.getSubtypeMessage());
                }
                if (demand.hasResultMessage()) {
                    mergeResultMessage(demand.getResultMessage());
                }
                if (demand.hasSequence()) {
                    setSequence(demand.getSequence());
                }
                if (demand.hasBData()) {
                    setBData(demand.getBData());
                }
                if (demand.hasNData()) {
                    setNData(demand.getNData());
                }
                if (demand.hasStrData()) {
                    setStrData(demand.getStrData());
                }
                if (demand.hasByteData()) {
                    setByteData(demand.getByteData());
                }
                if (demand.hasByteLength()) {
                    setByteLength(demand.getByteLength());
                }
                if (demand.hasByteTotalLength()) {
                    setByteTotalLength(demand.getByteTotalLength());
                }
                mergeUnknownFields(demand.getUnknownFields());
                return this;
            }

            public a mergeResultMessage(Result result) {
                Demand demand;
                if (!this.result.hasResultMessage() || this.result.resultMessage_ == Result.getDefaultInstance()) {
                    demand = this.result;
                } else {
                    demand = this.result;
                    result = Result.newBuilder(demand.resultMessage_).mergeFrom(result).buildPartial();
                }
                demand.resultMessage_ = result;
                this.result.hasResultMessage = true;
                return this;
            }

            public a mergeSubtypeMessage(DemandSubtype demandSubtype) {
                Demand demand;
                if (!this.result.hasSubtypeMessage() || this.result.subtypeMessage_ == DemandSubtype.getDefaultInstance()) {
                    demand = this.result;
                } else {
                    demand = this.result;
                    demandSubtype = DemandSubtype.newBuilder(demand.subtypeMessage_).mergeFrom(demandSubtype).buildPartial();
                }
                demand.subtypeMessage_ = demandSubtype;
                this.result.hasSubtypeMessage = true;
                return this;
            }

            public a setBData(boolean z) {
                this.result.hasBData = true;
                this.result.bData_ = z;
                return this;
            }

            public a setByteData(ByteString byteString) {
                if (byteString != null) {
                    this.result.hasByteData = true;
                    this.result.byteData_ = byteString;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setByteLength(int i) {
                this.result.hasByteLength = true;
                this.result.byteLength_ = i;
                return this;
            }

            public a setByteTotalLength(int i) {
                this.result.hasByteTotalLength = true;
                this.result.byteTotalLength_ = i;
                return this;
            }

            public a setNData(int i) {
                this.result.hasNData = true;
                this.result.nData_ = i;
                return this;
            }

            public a setResultMessage(Result.a aVar) {
                this.result.hasResultMessage = true;
                this.result.resultMessage_ = aVar.build();
                return this;
            }

            public a setResultMessage(Result result) {
                if (result != null) {
                    this.result.hasResultMessage = true;
                    this.result.resultMessage_ = result;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setSequence(int i) {
                this.result.hasSequence = true;
                this.result.sequence_ = i;
                return this;
            }

            public a setStrData(String str) {
                if (str != null) {
                    this.result.hasStrData = true;
                    this.result.strData_ = str;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setSubtypeMessage(DemandSubtype.a aVar) {
                this.result.hasSubtypeMessage = true;
                this.result.subtypeMessage_ = aVar.build();
                return this;
            }

            public a setSubtypeMessage(DemandSubtype demandSubtype) {
                if (demandSubtype != null) {
                    this.result.hasSubtypeMessage = true;
                    this.result.subtypeMessage_ = demandSubtype;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setType(DemandType demandType) {
                if (demandType != null) {
                    this.result.hasType = true;
                    this.result.type_ = demandType;
                    return this;
                }
                throw new NullPointerException();
            }
        }

        static {
            Roap.b();
            defaultInstance.initFields();
        }

        private Demand() {
            this.sequence_ = 0;
            this.bData_ = false;
            this.nData_ = 0;
            this.strData_ = com.lge.media.launcher.a.d;
            this.byteData_ = ByteString.a;
            this.byteLength_ = 0;
            this.byteTotalLength_ = 0;
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private Demand(boolean z) {
            this.sequence_ = 0;
            this.bData_ = false;
            this.nData_ = 0;
            this.strData_ = com.lge.media.launcher.a.d;
            this.byteData_ = ByteString.a;
            this.byteLength_ = 0;
            this.byteTotalLength_ = 0;
            this.memoizedSerializedSize = -1;
        }

        public static Demand getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Roap.w;
        }

        private void initFields() {
            this.type_ = DemandType.UNKNOWN;
            this.subtypeMessage_ = DemandSubtype.getDefaultInstance();
            this.resultMessage_ = Result.getDefaultInstance();
        }

        public static a newBuilder() {
            return a.access$21000();
        }

        public static a newBuilder(Demand demand) {
            return newBuilder().mergeFrom(demand);
        }

        public static Demand parseDelimitedFrom(InputStream inputStream) {
            a newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static Demand parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            a newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static Demand parseFrom(ByteString byteString) {
            return ((a) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static Demand parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static Demand parseFrom(CodedInputStream codedInputStream) {
            return ((a) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static Demand parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static Demand parseFrom(InputStream inputStream) {
            return ((a) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static Demand parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static Demand parseFrom(byte[] bArr) {
            return ((a) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static Demand parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        public boolean getBData() {
            return this.bData_;
        }

        public ByteString getByteData() {
            return this.byteData_;
        }

        public int getByteLength() {
            return this.byteLength_;
        }

        public int getByteTotalLength() {
            return this.byteTotalLength_;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Demand getDefaultInstanceForType() {
            return defaultInstance;
        }

        public int getNData() {
            return this.nData_;
        }

        public Result getResultMessage() {
            return this.resultMessage_;
        }

        public int getSequence() {
            return this.sequence_;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int j = hasType() ? 0 + CodedOutputStream.j(1, getType().getNumber()) : 0;
            if (hasSubtypeMessage()) {
                j += CodedOutputStream.g(2, getSubtypeMessage());
            }
            if (hasResultMessage()) {
                j += CodedOutputStream.g(3, getResultMessage());
            }
            if (hasSequence()) {
                j += CodedOutputStream.g(4, getSequence());
            }
            if (hasBData()) {
                j += CodedOutputStream.b(5, getBData());
            }
            if (hasNData()) {
                j += CodedOutputStream.g(6, getNData());
            }
            if (hasStrData()) {
                j += CodedOutputStream.b(7, getStrData());
            }
            if (hasByteData()) {
                j += CodedOutputStream.c(8, getByteData());
            }
            if (hasByteLength()) {
                j += CodedOutputStream.g(9, getByteLength());
            }
            if (hasByteTotalLength()) {
                j += CodedOutputStream.g(10, getByteTotalLength());
            }
            int serializedSize = j + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public String getStrData() {
            return this.strData_;
        }

        public DemandSubtype getSubtypeMessage() {
            return this.subtypeMessage_;
        }

        public DemandType getType() {
            return this.type_;
        }

        public boolean hasBData() {
            return this.hasBData;
        }

        public boolean hasByteData() {
            return this.hasByteData;
        }

        public boolean hasByteLength() {
            return this.hasByteLength;
        }

        public boolean hasByteTotalLength() {
            return this.hasByteTotalLength;
        }

        public boolean hasNData() {
            return this.hasNData;
        }

        public boolean hasResultMessage() {
            return this.hasResultMessage;
        }

        public boolean hasSequence() {
            return this.hasSequence;
        }

        public boolean hasStrData() {
            return this.hasStrData;
        }

        public boolean hasSubtypeMessage() {
            return this.hasSubtypeMessage;
        }

        public boolean hasType() {
            return this.hasType;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Roap.x;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            return this.hasType;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public a newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public a toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            if (hasType()) {
                codedOutputStream.d(1, getType().getNumber());
            }
            if (hasSubtypeMessage()) {
                codedOutputStream.c(2, getSubtypeMessage());
            }
            if (hasResultMessage()) {
                codedOutputStream.c(3, getResultMessage());
            }
            if (hasSequence()) {
                codedOutputStream.a(4, getSequence());
            }
            if (hasBData()) {
                codedOutputStream.a(5, getBData());
            }
            if (hasNData()) {
                codedOutputStream.a(6, getNData());
            }
            if (hasStrData()) {
                codedOutputStream.a(7, getStrData());
            }
            if (hasByteData()) {
                codedOutputStream.a(8, getByteData());
            }
            if (hasByteLength()) {
                codedOutputStream.a(9, getByteLength());
            }
            if (hasByteTotalLength()) {
                codedOutputStream.a(10, getByteTotalLength());
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class EventMessage extends GeneratedMessage {
        public static final int BDATA_FIELD_NUMBER = 3;
        public static final int BYTEDATA_FIELD_NUMBER = 8;
        public static final int BYTELENGTH_FIELD_NUMBER = 9;
        public static final int BYTETOTALLENGTH_FIELD_NUMBER = 10;
        public static final int NDATA1_FIELD_NUMBER = 4;
        public static final int NDATA2_FIELD_NUMBER = 5;
        public static final int STATUS_MESSAGE_FIELD_NUMBER = 2;
        public static final int STRDATA_FIELD_NUMBER = 6;
        public static final int TYPE_FIELD_NUMBER = 1;
        private static final EventMessage defaultInstance = new EventMessage(true);
        private boolean bData_;
        private ByteString byteData_;
        private int byteLength_;
        private int byteTotalLength_;
        private boolean hasBData;
        private boolean hasByteData;
        private boolean hasByteLength;
        private boolean hasByteTotalLength;
        private boolean hasNData1;
        private boolean hasNData2;
        private boolean hasStatusMessage;
        private boolean hasStrData;
        private boolean hasType;
        private int memoizedSerializedSize;
        private int nData1_;
        private int nData2_;
        private StatusMessage statusMessage_;
        private String strData_;
        private EventType type_;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public enum EventType implements ProtocolMessageEnum {
            UNKNOWN(0, 0),
            VIEW_STATUS(1, 1),
            PLAYBACK_STATUS(2, 2),
            NETWORK_STATUS(3, 3),
            USB_STATUS(4, 4),
            SP_STATUS(5, 5),
            SYNC_STATUS(6, 6),
            BGM_INFO_STATUS(7, 7),
            CP_STATUS(8, 8),
            EXTSPK_STATUS(9, 9);
            
            private final int index;
            private final int value;
            private static Internal.EnumLiteMap<EventType> internalValueMap = new Internal.EnumLiteMap<EventType>() { // from class: com.lge.UDAP.ROAP.Roap.EventMessage.EventType.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public EventType findValueByNumber(int i) {
                    return EventType.valueOf(i);
                }
            };
            private static final EventType[] VALUES = {UNKNOWN, VIEW_STATUS, PLAYBACK_STATUS, NETWORK_STATUS, USB_STATUS, SP_STATUS, SYNC_STATUS, BGM_INFO_STATUS, CP_STATUS, EXTSPK_STATUS};

            static {
                Roap.a();
            }

            EventType(int i, int i2) {
                this.index = i;
                this.value = i2;
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return EventMessage.getDescriptor().getEnumTypes().get(0);
            }

            public static Internal.EnumLiteMap<EventType> internalGetValueMap() {
                return internalValueMap;
            }

            public static EventType valueOf(int i) {
                switch (i) {
                    case 0:
                        return UNKNOWN;
                    case 1:
                        return VIEW_STATUS;
                    case 2:
                        return PLAYBACK_STATUS;
                    case 3:
                        return NETWORK_STATUS;
                    case 4:
                        return USB_STATUS;
                    case 5:
                        return SP_STATUS;
                    case 6:
                        return SYNC_STATUS;
                    case 7:
                        return BGM_INFO_STATUS;
                    case 8:
                        return CP_STATUS;
                    case 9:
                        return EXTSPK_STATUS;
                    default:
                        return null;
                }
            }

            public static EventType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
        public static final class StatusMessage extends GeneratedMessage {
            public static final int BGMINFOSTATUS_FIELD_NUMBER = 7;
            public static final int CPSTATUS_FIELD_NUMBER = 8;
            public static final int EXTSPKSTATUS_FIELD_NUMBER = 9;
            public static final int NETWORKSTATUS_FIELD_NUMBER = 3;
            public static final int PLAYBACKSTATUS_FIELD_NUMBER = 2;
            public static final int SPSTATUS_FIELD_NUMBER = 5;
            public static final int SYNCSTATUS_FIELD_NUMBER = 6;
            public static final int USBSTATUS_FIELD_NUMBER = 4;
            public static final int VIEWSTATUS_FIELD_NUMBER = 1;
            private static final StatusMessage defaultInstance = new StatusMessage(true);
            private BgmInfoStatus bgmInfoStatus_;
            private CpStatus cpStatus_;
            private EXTSPKStatus extspkStatus_;
            private boolean hasBgmInfoStatus;
            private boolean hasCpStatus;
            private boolean hasExtspkStatus;
            private boolean hasNetworkStatus;
            private boolean hasPlaybackStatus;
            private boolean hasSpStatus;
            private boolean hasSyncStatus;
            private boolean hasUsbStatus;
            private boolean hasViewStatus;
            private int memoizedSerializedSize;
            private NetworkStatus networkStatus_;
            private PlaybackStatus playbackStatus_;
            private SpStatus spStatus_;
            private SyncStatus syncStatus_;
            private UsbStatus usbStatus_;
            private ViewStatus viewStatus_;

            /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
            public enum BgmInfoStatus implements ProtocolMessageEnum {
                BGM_INFO_UNKNOWN(0, 0),
                BGM_INFO_OK(1, 1),
                BGM_INFO_FAIL(2, 2),
                BGM_INFO_LOADING(3, 3),
                BGM_INFO_CANCEL(4, 4);
                
                private final int index;
                private final int value;
                private static Internal.EnumLiteMap<BgmInfoStatus> internalValueMap = new Internal.EnumLiteMap<BgmInfoStatus>() { // from class: com.lge.UDAP.ROAP.Roap.EventMessage.StatusMessage.BgmInfoStatus.1
                    @Override // com.google.protobuf.Internal.EnumLiteMap
                    public BgmInfoStatus findValueByNumber(int i) {
                        return BgmInfoStatus.valueOf(i);
                    }
                };
                private static final BgmInfoStatus[] VALUES = {BGM_INFO_UNKNOWN, BGM_INFO_OK, BGM_INFO_FAIL, BGM_INFO_LOADING, BGM_INFO_CANCEL};

                static {
                    Roap.a();
                }

                BgmInfoStatus(int i, int i2) {
                    this.index = i;
                    this.value = i2;
                }

                public static final Descriptors.EnumDescriptor getDescriptor() {
                    return StatusMessage.getDescriptor().getEnumTypes().get(6);
                }

                public static Internal.EnumLiteMap<BgmInfoStatus> internalGetValueMap() {
                    return internalValueMap;
                }

                public static BgmInfoStatus valueOf(int i) {
                    if (i != 0) {
                        if (i != 1) {
                            if (i != 2) {
                                if (i != 3) {
                                    if (i != 4) {
                                        return null;
                                    }
                                    return BGM_INFO_CANCEL;
                                }
                                return BGM_INFO_LOADING;
                            }
                            return BGM_INFO_FAIL;
                        }
                        return BGM_INFO_OK;
                    }
                    return BGM_INFO_UNKNOWN;
                }

                public static BgmInfoStatus valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
            public enum CpStatus implements ProtocolMessageEnum {
                CP_UNKNOWN(0, 0),
                CP_LIST_CHANGE(1, 1),
                CP_LOADING_START(2, 2),
                CP_LOADING_END(3, 3);
                
                private final int index;
                private final int value;
                private static Internal.EnumLiteMap<CpStatus> internalValueMap = new Internal.EnumLiteMap<CpStatus>() { // from class: com.lge.UDAP.ROAP.Roap.EventMessage.StatusMessage.CpStatus.1
                    @Override // com.google.protobuf.Internal.EnumLiteMap
                    public CpStatus findValueByNumber(int i) {
                        return CpStatus.valueOf(i);
                    }
                };
                private static final CpStatus[] VALUES = {CP_UNKNOWN, CP_LIST_CHANGE, CP_LOADING_START, CP_LOADING_END};

                static {
                    Roap.a();
                }

                CpStatus(int i, int i2) {
                    this.index = i;
                    this.value = i2;
                }

                public static final Descriptors.EnumDescriptor getDescriptor() {
                    return StatusMessage.getDescriptor().getEnumTypes().get(7);
                }

                public static Internal.EnumLiteMap<CpStatus> internalGetValueMap() {
                    return internalValueMap;
                }

                public static CpStatus valueOf(int i) {
                    if (i != 0) {
                        if (i != 1) {
                            if (i != 2) {
                                if (i != 3) {
                                    return null;
                                }
                                return CP_LOADING_END;
                            }
                            return CP_LOADING_START;
                        }
                        return CP_LIST_CHANGE;
                    }
                    return CP_UNKNOWN;
                }

                public static CpStatus valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
            public enum EXTSPKStatus implements ProtocolMessageEnum {
                EXTSPK_UNKNOWN(0, 0),
                EXTSPK_LIST_CHANGE(1, 1);
                
                private final int index;
                private final int value;
                private static Internal.EnumLiteMap<EXTSPKStatus> internalValueMap = new Internal.EnumLiteMap<EXTSPKStatus>() { // from class: com.lge.UDAP.ROAP.Roap.EventMessage.StatusMessage.EXTSPKStatus.1
                    @Override // com.google.protobuf.Internal.EnumLiteMap
                    public EXTSPKStatus findValueByNumber(int i) {
                        return EXTSPKStatus.valueOf(i);
                    }
                };
                private static final EXTSPKStatus[] VALUES = {EXTSPK_UNKNOWN, EXTSPK_LIST_CHANGE};

                static {
                    Roap.a();
                }

                EXTSPKStatus(int i, int i2) {
                    this.index = i;
                    this.value = i2;
                }

                public static final Descriptors.EnumDescriptor getDescriptor() {
                    return StatusMessage.getDescriptor().getEnumTypes().get(8);
                }

                public static Internal.EnumLiteMap<EXTSPKStatus> internalGetValueMap() {
                    return internalValueMap;
                }

                public static EXTSPKStatus valueOf(int i) {
                    if (i != 0) {
                        if (i != 1) {
                            return null;
                        }
                        return EXTSPK_LIST_CHANGE;
                    }
                    return EXTSPK_UNKNOWN;
                }

                public static EXTSPKStatus valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
            public enum NetworkStatus implements ProtocolMessageEnum {
                NETWORK_UNKNOWN(0, 0),
                NETWORK_WIRED_CONNECTED(1, 1),
                NETWORK_WIRED_DISCONNECTED(2, 2),
                NETWORK_WIRELESS_CONNECTED(3, 3),
                NETWORK_WIRELESS_DISCONNECTED(4, 4);
                
                private final int index;
                private final int value;
                private static Internal.EnumLiteMap<NetworkStatus> internalValueMap = new Internal.EnumLiteMap<NetworkStatus>() { // from class: com.lge.UDAP.ROAP.Roap.EventMessage.StatusMessage.NetworkStatus.1
                    @Override // com.google.protobuf.Internal.EnumLiteMap
                    public NetworkStatus findValueByNumber(int i) {
                        return NetworkStatus.valueOf(i);
                    }
                };
                private static final NetworkStatus[] VALUES = {NETWORK_UNKNOWN, NETWORK_WIRED_CONNECTED, NETWORK_WIRED_DISCONNECTED, NETWORK_WIRELESS_CONNECTED, NETWORK_WIRELESS_DISCONNECTED};

                static {
                    Roap.a();
                }

                NetworkStatus(int i, int i2) {
                    this.index = i;
                    this.value = i2;
                }

                public static final Descriptors.EnumDescriptor getDescriptor() {
                    return StatusMessage.getDescriptor().getEnumTypes().get(2);
                }

                public static Internal.EnumLiteMap<NetworkStatus> internalGetValueMap() {
                    return internalValueMap;
                }

                public static NetworkStatus valueOf(int i) {
                    if (i != 0) {
                        if (i != 1) {
                            if (i != 2) {
                                if (i != 3) {
                                    if (i != 4) {
                                        return null;
                                    }
                                    return NETWORK_WIRELESS_DISCONNECTED;
                                }
                                return NETWORK_WIRELESS_CONNECTED;
                            }
                            return NETWORK_WIRED_DISCONNECTED;
                        }
                        return NETWORK_WIRED_CONNECTED;
                    }
                    return NETWORK_UNKNOWN;
                }

                public static NetworkStatus valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
            public enum PlaybackStatus implements ProtocolMessageEnum {
                PLAYBACK_UNKNOWN(0, 0),
                PLAYBACK_ERROR(1, 1),
                PLAYBACK_PLAY(2, 2),
                PLAYBACK_STOP(3, 3),
                PLAYBACK_PAUSE(4, 4),
                PLAYBACK_FAST_FWD(5, 5),
                PLAYBACK_FAST_BWD(6, 6),
                PLAYBACK_SLOW_FWD(7, 7),
                PLAYBACK_NOW_PLAYING(8, 8);
                
                private final int index;
                private final int value;
                private static Internal.EnumLiteMap<PlaybackStatus> internalValueMap = new Internal.EnumLiteMap<PlaybackStatus>() { // from class: com.lge.UDAP.ROAP.Roap.EventMessage.StatusMessage.PlaybackStatus.1
                    @Override // com.google.protobuf.Internal.EnumLiteMap
                    public PlaybackStatus findValueByNumber(int i) {
                        return PlaybackStatus.valueOf(i);
                    }
                };
                private static final PlaybackStatus[] VALUES = {PLAYBACK_UNKNOWN, PLAYBACK_ERROR, PLAYBACK_PLAY, PLAYBACK_STOP, PLAYBACK_PAUSE, PLAYBACK_FAST_FWD, PLAYBACK_FAST_BWD, PLAYBACK_SLOW_FWD, PLAYBACK_NOW_PLAYING};

                static {
                    Roap.a();
                }

                PlaybackStatus(int i, int i2) {
                    this.index = i;
                    this.value = i2;
                }

                public static final Descriptors.EnumDescriptor getDescriptor() {
                    return StatusMessage.getDescriptor().getEnumTypes().get(1);
                }

                public static Internal.EnumLiteMap<PlaybackStatus> internalGetValueMap() {
                    return internalValueMap;
                }

                public static PlaybackStatus valueOf(int i) {
                    switch (i) {
                        case 0:
                            return PLAYBACK_UNKNOWN;
                        case 1:
                            return PLAYBACK_ERROR;
                        case 2:
                            return PLAYBACK_PLAY;
                        case 3:
                            return PLAYBACK_STOP;
                        case 4:
                            return PLAYBACK_PAUSE;
                        case 5:
                            return PLAYBACK_FAST_FWD;
                        case 6:
                            return PLAYBACK_FAST_BWD;
                        case 7:
                            return PLAYBACK_SLOW_FWD;
                        case 8:
                            return PLAYBACK_NOW_PLAYING;
                        default:
                            return null;
                    }
                }

                public static PlaybackStatus valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
            public enum SpStatus implements ProtocolMessageEnum {
                SP_UNKNOWN(0, 0),
                SP_SOUND_ON(1, 1),
                SP_SOUND_OFF(2, 2),
                SP_SAMPLERATE_CHANGE(3, 3),
                SP_STOP(4, 4),
                SP_NOT_SUPPORT(5, 5),
                SP_ALIVE(6, 6);
                
                private final int index;
                private final int value;
                private static Internal.EnumLiteMap<SpStatus> internalValueMap = new Internal.EnumLiteMap<SpStatus>() { // from class: com.lge.UDAP.ROAP.Roap.EventMessage.StatusMessage.SpStatus.1
                    @Override // com.google.protobuf.Internal.EnumLiteMap
                    public SpStatus findValueByNumber(int i) {
                        return SpStatus.valueOf(i);
                    }
                };
                private static final SpStatus[] VALUES = {SP_UNKNOWN, SP_SOUND_ON, SP_SOUND_OFF, SP_SAMPLERATE_CHANGE, SP_STOP, SP_NOT_SUPPORT, SP_ALIVE};

                static {
                    Roap.a();
                }

                SpStatus(int i, int i2) {
                    this.index = i;
                    this.value = i2;
                }

                public static final Descriptors.EnumDescriptor getDescriptor() {
                    return StatusMessage.getDescriptor().getEnumTypes().get(4);
                }

                public static Internal.EnumLiteMap<SpStatus> internalGetValueMap() {
                    return internalValueMap;
                }

                public static SpStatus valueOf(int i) {
                    switch (i) {
                        case 0:
                            return SP_UNKNOWN;
                        case 1:
                            return SP_SOUND_ON;
                        case 2:
                            return SP_SOUND_OFF;
                        case 3:
                            return SP_SAMPLERATE_CHANGE;
                        case 4:
                            return SP_STOP;
                        case 5:
                            return SP_NOT_SUPPORT;
                        case 6:
                            return SP_ALIVE;
                        default:
                            return null;
                    }
                }

                public static SpStatus valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
            public enum SyncStatus implements ProtocolMessageEnum {
                SS_UNKNOWN(0, 0),
                SS_VIEW_CHANGE(1, 1),
                SS_ENABLED(2, 2),
                SS_DISABLED(3, 3);
                
                private final int index;
                private final int value;
                private static Internal.EnumLiteMap<SyncStatus> internalValueMap = new Internal.EnumLiteMap<SyncStatus>() { // from class: com.lge.UDAP.ROAP.Roap.EventMessage.StatusMessage.SyncStatus.1
                    @Override // com.google.protobuf.Internal.EnumLiteMap
                    public SyncStatus findValueByNumber(int i) {
                        return SyncStatus.valueOf(i);
                    }
                };
                private static final SyncStatus[] VALUES = {SS_UNKNOWN, SS_VIEW_CHANGE, SS_ENABLED, SS_DISABLED};

                static {
                    Roap.a();
                }

                SyncStatus(int i, int i2) {
                    this.index = i;
                    this.value = i2;
                }

                public static final Descriptors.EnumDescriptor getDescriptor() {
                    return StatusMessage.getDescriptor().getEnumTypes().get(5);
                }

                public static Internal.EnumLiteMap<SyncStatus> internalGetValueMap() {
                    return internalValueMap;
                }

                public static SyncStatus valueOf(int i) {
                    if (i != 0) {
                        if (i != 1) {
                            if (i != 2) {
                                if (i != 3) {
                                    return null;
                                }
                                return SS_DISABLED;
                            }
                            return SS_ENABLED;
                        }
                        return SS_VIEW_CHANGE;
                    }
                    return SS_UNKNOWN;
                }

                public static SyncStatus valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
            public enum UsbStatus implements ProtocolMessageEnum {
                USB_UNKNOWN(0, 0),
                USB_CONNECTED(1, 1),
                USB_DISCONNECTED(2, 2);
                
                private final int index;
                private final int value;
                private static Internal.EnumLiteMap<UsbStatus> internalValueMap = new Internal.EnumLiteMap<UsbStatus>() { // from class: com.lge.UDAP.ROAP.Roap.EventMessage.StatusMessage.UsbStatus.1
                    @Override // com.google.protobuf.Internal.EnumLiteMap
                    public UsbStatus findValueByNumber(int i) {
                        return UsbStatus.valueOf(i);
                    }
                };
                private static final UsbStatus[] VALUES = {USB_UNKNOWN, USB_CONNECTED, USB_DISCONNECTED};

                static {
                    Roap.a();
                }

                UsbStatus(int i, int i2) {
                    this.index = i;
                    this.value = i2;
                }

                public static final Descriptors.EnumDescriptor getDescriptor() {
                    return StatusMessage.getDescriptor().getEnumTypes().get(3);
                }

                public static Internal.EnumLiteMap<UsbStatus> internalGetValueMap() {
                    return internalValueMap;
                }

                public static UsbStatus valueOf(int i) {
                    if (i != 0) {
                        if (i != 1) {
                            if (i != 2) {
                                return null;
                            }
                            return USB_DISCONNECTED;
                        }
                        return USB_CONNECTED;
                    }
                    return USB_UNKNOWN;
                }

                public static UsbStatus valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
            public enum ViewStatus implements ProtocolMessageEnum {
                VIEW_TOUCHPAD(0, 1),
                VIEW_TEXTINPUT(1, 2);
                
                private final int index;
                private final int value;
                private static Internal.EnumLiteMap<ViewStatus> internalValueMap = new Internal.EnumLiteMap<ViewStatus>() { // from class: com.lge.UDAP.ROAP.Roap.EventMessage.StatusMessage.ViewStatus.1
                    @Override // com.google.protobuf.Internal.EnumLiteMap
                    public ViewStatus findValueByNumber(int i) {
                        return ViewStatus.valueOf(i);
                    }
                };
                private static final ViewStatus[] VALUES = {VIEW_TOUCHPAD, VIEW_TEXTINPUT};

                static {
                    Roap.a();
                }

                ViewStatus(int i, int i2) {
                    this.index = i;
                    this.value = i2;
                }

                public static final Descriptors.EnumDescriptor getDescriptor() {
                    return StatusMessage.getDescriptor().getEnumTypes().get(0);
                }

                public static Internal.EnumLiteMap<ViewStatus> internalGetValueMap() {
                    return internalValueMap;
                }

                public static ViewStatus valueOf(int i) {
                    if (i != 1) {
                        if (i != 2) {
                            return null;
                        }
                        return VIEW_TEXTINPUT;
                    }
                    return VIEW_TOUCHPAD;
                }

                public static ViewStatus valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
            public static final class a extends GeneratedMessage.Builder<a> {
                private StatusMessage result;

                private a() {
                }

                static /* synthetic */ a access$6100() {
                    return create();
                }

                /* JADX INFO: Access modifiers changed from: private */
                public StatusMessage buildParsed() {
                    if (isInitialized()) {
                        return buildPartial();
                    }
                    throw newUninitializedMessageException((Message) this.result).b();
                }

                private static a create() {
                    a aVar = new a();
                    aVar.result = new StatusMessage();
                    return aVar;
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public StatusMessage build() {
                    if (this.result == null || isInitialized()) {
                        return buildPartial();
                    }
                    throw newUninitializedMessageException((Message) this.result);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public StatusMessage buildPartial() {
                    StatusMessage statusMessage = this.result;
                    if (statusMessage != null) {
                        this.result = null;
                        return statusMessage;
                    }
                    throw new IllegalStateException("build() has already been called on this Builder.");
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public a clear() {
                    if (this.result != null) {
                        this.result = new StatusMessage();
                        return this;
                    }
                    throw new IllegalStateException("Cannot call clear() after build().");
                }

                public a clearBgmInfoStatus() {
                    this.result.hasBgmInfoStatus = false;
                    this.result.bgmInfoStatus_ = BgmInfoStatus.BGM_INFO_UNKNOWN;
                    return this;
                }

                public a clearCpStatus() {
                    this.result.hasCpStatus = false;
                    this.result.cpStatus_ = CpStatus.CP_UNKNOWN;
                    return this;
                }

                public a clearExtspkStatus() {
                    this.result.hasExtspkStatus = false;
                    this.result.extspkStatus_ = EXTSPKStatus.EXTSPK_UNKNOWN;
                    return this;
                }

                public a clearNetworkStatus() {
                    this.result.hasNetworkStatus = false;
                    this.result.networkStatus_ = NetworkStatus.NETWORK_UNKNOWN;
                    return this;
                }

                public a clearPlaybackStatus() {
                    this.result.hasPlaybackStatus = false;
                    this.result.playbackStatus_ = PlaybackStatus.PLAYBACK_UNKNOWN;
                    return this;
                }

                public a clearSpStatus() {
                    this.result.hasSpStatus = false;
                    this.result.spStatus_ = SpStatus.SP_UNKNOWN;
                    return this;
                }

                public a clearSyncStatus() {
                    this.result.hasSyncStatus = false;
                    this.result.syncStatus_ = SyncStatus.SS_UNKNOWN;
                    return this;
                }

                public a clearUsbStatus() {
                    this.result.hasUsbStatus = false;
                    this.result.usbStatus_ = UsbStatus.USB_UNKNOWN;
                    return this;
                }

                public a clearViewStatus() {
                    this.result.hasViewStatus = false;
                    this.result.viewStatus_ = ViewStatus.VIEW_TOUCHPAD;
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
                /* renamed from: clone */
                public a mo3clone() {
                    return create().mergeFrom(this.result);
                }

                public BgmInfoStatus getBgmInfoStatus() {
                    return this.result.getBgmInfoStatus();
                }

                public CpStatus getCpStatus() {
                    return this.result.getCpStatus();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public StatusMessage getDefaultInstanceForType() {
                    return StatusMessage.getDefaultInstance();
                }

                @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
                public Descriptors.Descriptor getDescriptorForType() {
                    return StatusMessage.getDescriptor();
                }

                public EXTSPKStatus getExtspkStatus() {
                    return this.result.getExtspkStatus();
                }

                public NetworkStatus getNetworkStatus() {
                    return this.result.getNetworkStatus();
                }

                public PlaybackStatus getPlaybackStatus() {
                    return this.result.getPlaybackStatus();
                }

                public SpStatus getSpStatus() {
                    return this.result.getSpStatus();
                }

                public SyncStatus getSyncStatus() {
                    return this.result.getSyncStatus();
                }

                public UsbStatus getUsbStatus() {
                    return this.result.getUsbStatus();
                }

                public ViewStatus getViewStatus() {
                    return this.result.getViewStatus();
                }

                public boolean hasBgmInfoStatus() {
                    return this.result.hasBgmInfoStatus();
                }

                public boolean hasCpStatus() {
                    return this.result.hasCpStatus();
                }

                public boolean hasExtspkStatus() {
                    return this.result.hasExtspkStatus();
                }

                public boolean hasNetworkStatus() {
                    return this.result.hasNetworkStatus();
                }

                public boolean hasPlaybackStatus() {
                    return this.result.hasPlaybackStatus();
                }

                public boolean hasSpStatus() {
                    return this.result.hasSpStatus();
                }

                public boolean hasSyncStatus() {
                    return this.result.hasSyncStatus();
                }

                public boolean hasUsbStatus() {
                    return this.result.hasUsbStatus();
                }

                public boolean hasViewStatus() {
                    return this.result.hasViewStatus();
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.google.protobuf.GeneratedMessage.Builder
                public StatusMessage internalGetResult() {
                    return this.result;
                }

                @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
                public boolean isInitialized() {
                    return this.result.isInitialized();
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public a mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    int n;
                    UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                    while (true) {
                        int a = codedInputStream.a();
                        if (a == 0) {
                            break;
                        }
                        int i = 8;
                        if (a == 8) {
                            n = codedInputStream.n();
                            ViewStatus valueOf = ViewStatus.valueOf(n);
                            if (valueOf == null) {
                                i = 1;
                                newBuilder.mergeVarintField(i, n);
                            } else {
                                setViewStatus(valueOf);
                            }
                        } else if (a == 16) {
                            n = codedInputStream.n();
                            PlaybackStatus valueOf2 = PlaybackStatus.valueOf(n);
                            if (valueOf2 == null) {
                                i = 2;
                                newBuilder.mergeVarintField(i, n);
                            } else {
                                setPlaybackStatus(valueOf2);
                            }
                        } else if (a == 24) {
                            n = codedInputStream.n();
                            NetworkStatus valueOf3 = NetworkStatus.valueOf(n);
                            if (valueOf3 == null) {
                                i = 3;
                                newBuilder.mergeVarintField(i, n);
                            } else {
                                setNetworkStatus(valueOf3);
                            }
                        } else if (a == 32) {
                            n = codedInputStream.n();
                            UsbStatus valueOf4 = UsbStatus.valueOf(n);
                            if (valueOf4 == null) {
                                i = 4;
                                newBuilder.mergeVarintField(i, n);
                            } else {
                                setUsbStatus(valueOf4);
                            }
                        } else if (a == 40) {
                            n = codedInputStream.n();
                            SpStatus valueOf5 = SpStatus.valueOf(n);
                            if (valueOf5 == null) {
                                i = 5;
                                newBuilder.mergeVarintField(i, n);
                            } else {
                                setSpStatus(valueOf5);
                            }
                        } else if (a == 48) {
                            n = codedInputStream.n();
                            SyncStatus valueOf6 = SyncStatus.valueOf(n);
                            if (valueOf6 == null) {
                                i = 6;
                                newBuilder.mergeVarintField(i, n);
                            } else {
                                setSyncStatus(valueOf6);
                            }
                        } else if (a == 56) {
                            n = codedInputStream.n();
                            BgmInfoStatus valueOf7 = BgmInfoStatus.valueOf(n);
                            if (valueOf7 == null) {
                                i = 7;
                                newBuilder.mergeVarintField(i, n);
                            } else {
                                setBgmInfoStatus(valueOf7);
                            }
                        } else if (a == 64) {
                            n = codedInputStream.n();
                            CpStatus valueOf8 = CpStatus.valueOf(n);
                            if (valueOf8 == null) {
                                newBuilder.mergeVarintField(i, n);
                            } else {
                                setCpStatus(valueOf8);
                            }
                        } else if (a == 72) {
                            n = codedInputStream.n();
                            EXTSPKStatus valueOf9 = EXTSPKStatus.valueOf(n);
                            if (valueOf9 == null) {
                                i = 9;
                                newBuilder.mergeVarintField(i, n);
                            } else {
                                setExtspkStatus(valueOf9);
                            }
                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                            break;
                        }
                    }
                    setUnknownFields(newBuilder.build());
                    return this;
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public a mergeFrom(Message message) {
                    if (message instanceof StatusMessage) {
                        return mergeFrom((StatusMessage) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public a mergeFrom(StatusMessage statusMessage) {
                    if (statusMessage == StatusMessage.getDefaultInstance()) {
                        return this;
                    }
                    if (statusMessage.hasViewStatus()) {
                        setViewStatus(statusMessage.getViewStatus());
                    }
                    if (statusMessage.hasPlaybackStatus()) {
                        setPlaybackStatus(statusMessage.getPlaybackStatus());
                    }
                    if (statusMessage.hasNetworkStatus()) {
                        setNetworkStatus(statusMessage.getNetworkStatus());
                    }
                    if (statusMessage.hasUsbStatus()) {
                        setUsbStatus(statusMessage.getUsbStatus());
                    }
                    if (statusMessage.hasSpStatus()) {
                        setSpStatus(statusMessage.getSpStatus());
                    }
                    if (statusMessage.hasSyncStatus()) {
                        setSyncStatus(statusMessage.getSyncStatus());
                    }
                    if (statusMessage.hasBgmInfoStatus()) {
                        setBgmInfoStatus(statusMessage.getBgmInfoStatus());
                    }
                    if (statusMessage.hasCpStatus()) {
                        setCpStatus(statusMessage.getCpStatus());
                    }
                    if (statusMessage.hasExtspkStatus()) {
                        setExtspkStatus(statusMessage.getExtspkStatus());
                    }
                    mergeUnknownFields(statusMessage.getUnknownFields());
                    return this;
                }

                public a setBgmInfoStatus(BgmInfoStatus bgmInfoStatus) {
                    if (bgmInfoStatus != null) {
                        this.result.hasBgmInfoStatus = true;
                        this.result.bgmInfoStatus_ = bgmInfoStatus;
                        return this;
                    }
                    throw new NullPointerException();
                }

                public a setCpStatus(CpStatus cpStatus) {
                    if (cpStatus != null) {
                        this.result.hasCpStatus = true;
                        this.result.cpStatus_ = cpStatus;
                        return this;
                    }
                    throw new NullPointerException();
                }

                public a setExtspkStatus(EXTSPKStatus eXTSPKStatus) {
                    if (eXTSPKStatus != null) {
                        this.result.hasExtspkStatus = true;
                        this.result.extspkStatus_ = eXTSPKStatus;
                        return this;
                    }
                    throw new NullPointerException();
                }

                public a setNetworkStatus(NetworkStatus networkStatus) {
                    if (networkStatus != null) {
                        this.result.hasNetworkStatus = true;
                        this.result.networkStatus_ = networkStatus;
                        return this;
                    }
                    throw new NullPointerException();
                }

                public a setPlaybackStatus(PlaybackStatus playbackStatus) {
                    if (playbackStatus != null) {
                        this.result.hasPlaybackStatus = true;
                        this.result.playbackStatus_ = playbackStatus;
                        return this;
                    }
                    throw new NullPointerException();
                }

                public a setSpStatus(SpStatus spStatus) {
                    if (spStatus != null) {
                        this.result.hasSpStatus = true;
                        this.result.spStatus_ = spStatus;
                        return this;
                    }
                    throw new NullPointerException();
                }

                public a setSyncStatus(SyncStatus syncStatus) {
                    if (syncStatus != null) {
                        this.result.hasSyncStatus = true;
                        this.result.syncStatus_ = syncStatus;
                        return this;
                    }
                    throw new NullPointerException();
                }

                public a setUsbStatus(UsbStatus usbStatus) {
                    if (usbStatus != null) {
                        this.result.hasUsbStatus = true;
                        this.result.usbStatus_ = usbStatus;
                        return this;
                    }
                    throw new NullPointerException();
                }

                public a setViewStatus(ViewStatus viewStatus) {
                    if (viewStatus != null) {
                        this.result.hasViewStatus = true;
                        this.result.viewStatus_ = viewStatus;
                        return this;
                    }
                    throw new NullPointerException();
                }
            }

            static {
                Roap.b();
                defaultInstance.initFields();
            }

            private StatusMessage() {
                this.memoizedSerializedSize = -1;
                initFields();
            }

            private StatusMessage(boolean z) {
                this.memoizedSerializedSize = -1;
            }

            public static StatusMessage getDefaultInstance() {
                return defaultInstance;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Roap.k;
            }

            private void initFields() {
                this.viewStatus_ = ViewStatus.VIEW_TOUCHPAD;
                this.playbackStatus_ = PlaybackStatus.PLAYBACK_UNKNOWN;
                this.networkStatus_ = NetworkStatus.NETWORK_UNKNOWN;
                this.usbStatus_ = UsbStatus.USB_UNKNOWN;
                this.spStatus_ = SpStatus.SP_UNKNOWN;
                this.syncStatus_ = SyncStatus.SS_UNKNOWN;
                this.bgmInfoStatus_ = BgmInfoStatus.BGM_INFO_UNKNOWN;
                this.cpStatus_ = CpStatus.CP_UNKNOWN;
                this.extspkStatus_ = EXTSPKStatus.EXTSPK_UNKNOWN;
            }

            public static a newBuilder() {
                return a.access$6100();
            }

            public static a newBuilder(StatusMessage statusMessage) {
                return newBuilder().mergeFrom(statusMessage);
            }

            public static StatusMessage parseDelimitedFrom(InputStream inputStream) {
                a newBuilder = newBuilder();
                if (newBuilder.mergeDelimitedFrom(inputStream)) {
                    return newBuilder.buildParsed();
                }
                return null;
            }

            public static StatusMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                a newBuilder = newBuilder();
                if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                    return newBuilder.buildParsed();
                }
                return null;
            }

            public static StatusMessage parseFrom(ByteString byteString) {
                return ((a) newBuilder().mergeFrom(byteString)).buildParsed();
            }

            public static StatusMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return ((a) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
            }

            public static StatusMessage parseFrom(CodedInputStream codedInputStream) {
                return ((a) newBuilder().mergeFrom(codedInputStream)).buildParsed();
            }

            public static StatusMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
            }

            public static StatusMessage parseFrom(InputStream inputStream) {
                return ((a) newBuilder().mergeFrom(inputStream)).buildParsed();
            }

            public static StatusMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return ((a) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
            }

            public static StatusMessage parseFrom(byte[] bArr) {
                return ((a) newBuilder().mergeFrom(bArr)).buildParsed();
            }

            public static StatusMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return ((a) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
            }

            public BgmInfoStatus getBgmInfoStatus() {
                return this.bgmInfoStatus_;
            }

            public CpStatus getCpStatus() {
                return this.cpStatus_;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public StatusMessage getDefaultInstanceForType() {
                return defaultInstance;
            }

            public EXTSPKStatus getExtspkStatus() {
                return this.extspkStatus_;
            }

            public NetworkStatus getNetworkStatus() {
                return this.networkStatus_;
            }

            public PlaybackStatus getPlaybackStatus() {
                return this.playbackStatus_;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSerializedSize;
                if (i != -1) {
                    return i;
                }
                int j = hasViewStatus() ? 0 + CodedOutputStream.j(1, getViewStatus().getNumber()) : 0;
                if (hasPlaybackStatus()) {
                    j += CodedOutputStream.j(2, getPlaybackStatus().getNumber());
                }
                if (hasNetworkStatus()) {
                    j += CodedOutputStream.j(3, getNetworkStatus().getNumber());
                }
                if (hasUsbStatus()) {
                    j += CodedOutputStream.j(4, getUsbStatus().getNumber());
                }
                if (hasSpStatus()) {
                    j += CodedOutputStream.j(5, getSpStatus().getNumber());
                }
                if (hasSyncStatus()) {
                    j += CodedOutputStream.j(6, getSyncStatus().getNumber());
                }
                if (hasBgmInfoStatus()) {
                    j += CodedOutputStream.j(7, getBgmInfoStatus().getNumber());
                }
                if (hasCpStatus()) {
                    j += CodedOutputStream.j(8, getCpStatus().getNumber());
                }
                if (hasExtspkStatus()) {
                    j += CodedOutputStream.j(9, getExtspkStatus().getNumber());
                }
                int serializedSize = j + getUnknownFields().getSerializedSize();
                this.memoizedSerializedSize = serializedSize;
                return serializedSize;
            }

            public SpStatus getSpStatus() {
                return this.spStatus_;
            }

            public SyncStatus getSyncStatus() {
                return this.syncStatus_;
            }

            public UsbStatus getUsbStatus() {
                return this.usbStatus_;
            }

            public ViewStatus getViewStatus() {
                return this.viewStatus_;
            }

            public boolean hasBgmInfoStatus() {
                return this.hasBgmInfoStatus;
            }

            public boolean hasCpStatus() {
                return this.hasCpStatus;
            }

            public boolean hasExtspkStatus() {
                return this.hasExtspkStatus;
            }

            public boolean hasNetworkStatus() {
                return this.hasNetworkStatus;
            }

            public boolean hasPlaybackStatus() {
                return this.hasPlaybackStatus;
            }

            public boolean hasSpStatus() {
                return this.hasSpStatus;
            }

            public boolean hasSyncStatus() {
                return this.hasSyncStatus;
            }

            public boolean hasUsbStatus() {
                return this.hasUsbStatus;
            }

            public boolean hasViewStatus() {
                return this.hasViewStatus;
            }

            @Override // com.google.protobuf.GeneratedMessage
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return Roap.l;
            }

            @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public a newBuilderForType() {
                return newBuilder();
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public a toBuilder() {
                return newBuilder(this);
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) {
                getSerializedSize();
                if (hasViewStatus()) {
                    codedOutputStream.d(1, getViewStatus().getNumber());
                }
                if (hasPlaybackStatus()) {
                    codedOutputStream.d(2, getPlaybackStatus().getNumber());
                }
                if (hasNetworkStatus()) {
                    codedOutputStream.d(3, getNetworkStatus().getNumber());
                }
                if (hasUsbStatus()) {
                    codedOutputStream.d(4, getUsbStatus().getNumber());
                }
                if (hasSpStatus()) {
                    codedOutputStream.d(5, getSpStatus().getNumber());
                }
                if (hasSyncStatus()) {
                    codedOutputStream.d(6, getSyncStatus().getNumber());
                }
                if (hasBgmInfoStatus()) {
                    codedOutputStream.d(7, getBgmInfoStatus().getNumber());
                }
                if (hasCpStatus()) {
                    codedOutputStream.d(8, getCpStatus().getNumber());
                }
                if (hasExtspkStatus()) {
                    codedOutputStream.d(9, getExtspkStatus().getNumber());
                }
                getUnknownFields().writeTo(codedOutputStream);
            }
        }

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static final class a extends GeneratedMessage.Builder<a> {
            private EventMessage result;

            private a() {
            }

            static /* synthetic */ a access$8200() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public EventMessage buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static a create() {
                a aVar = new a();
                aVar.result = new EventMessage();
                return aVar;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EventMessage build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EventMessage buildPartial() {
                EventMessage eventMessage = this.result;
                if (eventMessage != null) {
                    this.result = null;
                    return eventMessage;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public a clear() {
                if (this.result != null) {
                    this.result = new EventMessage();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public a clearBData() {
                this.result.hasBData = false;
                this.result.bData_ = false;
                return this;
            }

            public a clearByteData() {
                this.result.hasByteData = false;
                this.result.byteData_ = EventMessage.getDefaultInstance().getByteData();
                return this;
            }

            public a clearByteLength() {
                this.result.hasByteLength = false;
                this.result.byteLength_ = 0;
                return this;
            }

            public a clearByteTotalLength() {
                this.result.hasByteTotalLength = false;
                this.result.byteTotalLength_ = 0;
                return this;
            }

            public a clearNData1() {
                this.result.hasNData1 = false;
                this.result.nData1_ = 0;
                return this;
            }

            public a clearNData2() {
                this.result.hasNData2 = false;
                this.result.nData2_ = 0;
                return this;
            }

            public a clearStatusMessage() {
                this.result.hasStatusMessage = false;
                this.result.statusMessage_ = StatusMessage.getDefaultInstance();
                return this;
            }

            public a clearStrData() {
                this.result.hasStrData = false;
                this.result.strData_ = EventMessage.getDefaultInstance().getStrData();
                return this;
            }

            public a clearType() {
                this.result.hasType = false;
                this.result.type_ = EventType.UNKNOWN;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public a mo3clone() {
                return create().mergeFrom(this.result);
            }

            public boolean getBData() {
                return this.result.getBData();
            }

            public ByteString getByteData() {
                return this.result.getByteData();
            }

            public int getByteLength() {
                return this.result.getByteLength();
            }

            public int getByteTotalLength() {
                return this.result.getByteTotalLength();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EventMessage getDefaultInstanceForType() {
                return EventMessage.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return EventMessage.getDescriptor();
            }

            public int getNData1() {
                return this.result.getNData1();
            }

            public int getNData2() {
                return this.result.getNData2();
            }

            public StatusMessage getStatusMessage() {
                return this.result.getStatusMessage();
            }

            public String getStrData() {
                return this.result.getStrData();
            }

            public EventType getType() {
                return this.result.getType();
            }

            public boolean hasBData() {
                return this.result.hasBData();
            }

            public boolean hasByteData() {
                return this.result.hasByteData();
            }

            public boolean hasByteLength() {
                return this.result.hasByteLength();
            }

            public boolean hasByteTotalLength() {
                return this.result.hasByteTotalLength();
            }

            public boolean hasNData1() {
                return this.result.hasNData1();
            }

            public boolean hasNData2() {
                return this.result.hasNData2();
            }

            public boolean hasStatusMessage() {
                return this.result.hasStatusMessage();
            }

            public boolean hasStrData() {
                return this.result.hasStrData();
            }

            public boolean hasType() {
                return this.result.hasType();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.Builder
            public EventMessage internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public a mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 8) {
                        int n = codedInputStream.n();
                        EventType valueOf = EventType.valueOf(n);
                        if (valueOf == null) {
                            newBuilder.mergeVarintField(1, n);
                        } else {
                            setType(valueOf);
                        }
                    } else if (a == 18) {
                        StatusMessage.a newBuilder2 = StatusMessage.newBuilder();
                        if (hasStatusMessage()) {
                            newBuilder2.mergeFrom(getStatusMessage());
                        }
                        codedInputStream.a(newBuilder2, extensionRegistryLite);
                        setStatusMessage(newBuilder2.buildPartial());
                    } else if (a == 24) {
                        setBData(codedInputStream.j());
                    } else if (a == 32) {
                        setNData1(codedInputStream.g());
                    } else if (a == 40) {
                        setNData2(codedInputStream.g());
                    } else if (a == 50) {
                        setStrData(codedInputStream.k());
                    } else if (a == 66) {
                        setByteData(codedInputStream.l());
                    } else if (a == 72) {
                        setByteLength(codedInputStream.g());
                    } else if (a == 80) {
                        setByteTotalLength(codedInputStream.g());
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public a mergeFrom(Message message) {
                if (message instanceof EventMessage) {
                    return mergeFrom((EventMessage) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public a mergeFrom(EventMessage eventMessage) {
                if (eventMessage == EventMessage.getDefaultInstance()) {
                    return this;
                }
                if (eventMessage.hasType()) {
                    setType(eventMessage.getType());
                }
                if (eventMessage.hasStatusMessage()) {
                    mergeStatusMessage(eventMessage.getStatusMessage());
                }
                if (eventMessage.hasBData()) {
                    setBData(eventMessage.getBData());
                }
                if (eventMessage.hasNData1()) {
                    setNData1(eventMessage.getNData1());
                }
                if (eventMessage.hasNData2()) {
                    setNData2(eventMessage.getNData2());
                }
                if (eventMessage.hasStrData()) {
                    setStrData(eventMessage.getStrData());
                }
                if (eventMessage.hasByteData()) {
                    setByteData(eventMessage.getByteData());
                }
                if (eventMessage.hasByteLength()) {
                    setByteLength(eventMessage.getByteLength());
                }
                if (eventMessage.hasByteTotalLength()) {
                    setByteTotalLength(eventMessage.getByteTotalLength());
                }
                mergeUnknownFields(eventMessage.getUnknownFields());
                return this;
            }

            public a mergeStatusMessage(StatusMessage statusMessage) {
                EventMessage eventMessage;
                if (!this.result.hasStatusMessage() || this.result.statusMessage_ == StatusMessage.getDefaultInstance()) {
                    eventMessage = this.result;
                } else {
                    eventMessage = this.result;
                    statusMessage = StatusMessage.newBuilder(eventMessage.statusMessage_).mergeFrom(statusMessage).buildPartial();
                }
                eventMessage.statusMessage_ = statusMessage;
                this.result.hasStatusMessage = true;
                return this;
            }

            public a setBData(boolean z) {
                this.result.hasBData = true;
                this.result.bData_ = z;
                return this;
            }

            public a setByteData(ByteString byteString) {
                if (byteString != null) {
                    this.result.hasByteData = true;
                    this.result.byteData_ = byteString;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setByteLength(int i) {
                this.result.hasByteLength = true;
                this.result.byteLength_ = i;
                return this;
            }

            public a setByteTotalLength(int i) {
                this.result.hasByteTotalLength = true;
                this.result.byteTotalLength_ = i;
                return this;
            }

            public a setNData1(int i) {
                this.result.hasNData1 = true;
                this.result.nData1_ = i;
                return this;
            }

            public a setNData2(int i) {
                this.result.hasNData2 = true;
                this.result.nData2_ = i;
                return this;
            }

            public a setStatusMessage(StatusMessage.a aVar) {
                this.result.hasStatusMessage = true;
                this.result.statusMessage_ = aVar.build();
                return this;
            }

            public a setStatusMessage(StatusMessage statusMessage) {
                if (statusMessage != null) {
                    this.result.hasStatusMessage = true;
                    this.result.statusMessage_ = statusMessage;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setStrData(String str) {
                if (str != null) {
                    this.result.hasStrData = true;
                    this.result.strData_ = str;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setType(EventType eventType) {
                if (eventType != null) {
                    this.result.hasType = true;
                    this.result.type_ = eventType;
                    return this;
                }
                throw new NullPointerException();
            }
        }

        static {
            Roap.b();
            defaultInstance.initFields();
        }

        private EventMessage() {
            this.bData_ = false;
            this.nData1_ = 0;
            this.nData2_ = 0;
            this.strData_ = com.lge.media.launcher.a.d;
            this.byteData_ = ByteString.a;
            this.byteLength_ = 0;
            this.byteTotalLength_ = 0;
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private EventMessage(boolean z) {
            this.bData_ = false;
            this.nData1_ = 0;
            this.nData2_ = 0;
            this.strData_ = com.lge.media.launcher.a.d;
            this.byteData_ = ByteString.a;
            this.byteLength_ = 0;
            this.byteTotalLength_ = 0;
            this.memoizedSerializedSize = -1;
        }

        public static EventMessage getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Roap.i;
        }

        private void initFields() {
            this.type_ = EventType.UNKNOWN;
            this.statusMessage_ = StatusMessage.getDefaultInstance();
        }

        public static a newBuilder() {
            return a.access$8200();
        }

        public static a newBuilder(EventMessage eventMessage) {
            return newBuilder().mergeFrom(eventMessage);
        }

        public static EventMessage parseDelimitedFrom(InputStream inputStream) {
            a newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static EventMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            a newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static EventMessage parseFrom(ByteString byteString) {
            return ((a) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static EventMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static EventMessage parseFrom(CodedInputStream codedInputStream) {
            return ((a) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static EventMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static EventMessage parseFrom(InputStream inputStream) {
            return ((a) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static EventMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static EventMessage parseFrom(byte[] bArr) {
            return ((a) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static EventMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        public boolean getBData() {
            return this.bData_;
        }

        public ByteString getByteData() {
            return this.byteData_;
        }

        public int getByteLength() {
            return this.byteLength_;
        }

        public int getByteTotalLength() {
            return this.byteTotalLength_;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public EventMessage getDefaultInstanceForType() {
            return defaultInstance;
        }

        public int getNData1() {
            return this.nData1_;
        }

        public int getNData2() {
            return this.nData2_;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int j = hasType() ? 0 + CodedOutputStream.j(1, getType().getNumber()) : 0;
            if (hasStatusMessage()) {
                j += CodedOutputStream.g(2, getStatusMessage());
            }
            if (hasBData()) {
                j += CodedOutputStream.b(3, getBData());
            }
            if (hasNData1()) {
                j += CodedOutputStream.g(4, getNData1());
            }
            if (hasNData2()) {
                j += CodedOutputStream.g(5, getNData2());
            }
            if (hasStrData()) {
                j += CodedOutputStream.b(6, getStrData());
            }
            if (hasByteData()) {
                j += CodedOutputStream.c(8, getByteData());
            }
            if (hasByteLength()) {
                j += CodedOutputStream.g(9, getByteLength());
            }
            if (hasByteTotalLength()) {
                j += CodedOutputStream.g(10, getByteTotalLength());
            }
            int serializedSize = j + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public StatusMessage getStatusMessage() {
            return this.statusMessage_;
        }

        public String getStrData() {
            return this.strData_;
        }

        public EventType getType() {
            return this.type_;
        }

        public boolean hasBData() {
            return this.hasBData;
        }

        public boolean hasByteData() {
            return this.hasByteData;
        }

        public boolean hasByteLength() {
            return this.hasByteLength;
        }

        public boolean hasByteTotalLength() {
            return this.hasByteTotalLength;
        }

        public boolean hasNData1() {
            return this.hasNData1;
        }

        public boolean hasNData2() {
            return this.hasNData2;
        }

        public boolean hasStatusMessage() {
            return this.hasStatusMessage;
        }

        public boolean hasStrData() {
            return this.hasStrData;
        }

        public boolean hasType() {
            return this.hasType;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Roap.j;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            return this.hasType;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public a newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public a toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            if (hasType()) {
                codedOutputStream.d(1, getType().getNumber());
            }
            if (hasStatusMessage()) {
                codedOutputStream.c(2, getStatusMessage());
            }
            if (hasBData()) {
                codedOutputStream.a(3, getBData());
            }
            if (hasNData1()) {
                codedOutputStream.a(4, getNData1());
            }
            if (hasNData2()) {
                codedOutputStream.a(5, getNData2());
            }
            if (hasStrData()) {
                codedOutputStream.a(6, getStrData());
            }
            if (hasByteData()) {
                codedOutputStream.a(8, getByteData());
            }
            if (hasByteLength()) {
                codedOutputStream.a(9, getByteLength());
            }
            if (hasByteTotalLength()) {
                codedOutputStream.a(10, getByteTotalLength());
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class Key extends GeneratedMessage {
        public static final int ACTION_FIELD_NUMBER = 2;
        public static final int CODE_FIELD_NUMBER = 1;
        private static final Key defaultInstance = new Key(true);
        private Action action_;
        private KeyCode code_;
        private boolean hasAction;
        private boolean hasCode;
        private int memoizedSerializedSize;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public enum Action implements ProtocolMessageEnum {
            UNKNOWN(0, 0),
            UP(1, 1),
            DOWN(2, 2);
            
            private final int index;
            private final int value;
            private static Internal.EnumLiteMap<Action> internalValueMap = new Internal.EnumLiteMap<Action>() { // from class: com.lge.UDAP.ROAP.Roap.Key.Action.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public Action findValueByNumber(int i) {
                    return Action.valueOf(i);
                }
            };
            private static final Action[] VALUES = {UNKNOWN, UP, DOWN};

            static {
                Roap.a();
            }

            Action(int i, int i2) {
                this.index = i;
                this.value = i2;
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return Key.getDescriptor().getEnumTypes().get(1);
            }

            public static Internal.EnumLiteMap<Action> internalGetValueMap() {
                return internalValueMap;
            }

            public static Action valueOf(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            return null;
                        }
                        return DOWN;
                    }
                    return UP;
                }
                return UNKNOWN;
            }

            public static Action valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
        public enum KeyCode implements ProtocolMessageEnum {
            KEYCODE_UNKNOWN(0, 0),
            KEYCODE_POWER(1, 1),
            KEYCODE_0(2, 2),
            KEYCODE_1(3, 3),
            KEYCODE_2(4, 4),
            KEYCODE_3(5, 5),
            KEYCODE_4(6, 6),
            KEYCODE_5(7, 7),
            KEYCODE_6(8, 8),
            KEYCODE_7(9, 9),
            KEYCODE_8(10, 10),
            KEYCODE_9(11, 11),
            KEYCODE_UP(12, 12),
            KEYCODE_DOWN(13, 13),
            KEYCODE_LEFT(14, 14),
            KEYCODE_RIGHT(15, 15),
            KEYCODE_UP_LEFT(16, 16),
            KEYCODE_UP_RIGHT(17, 17),
            KEYCODE_DOWN_LEFT(18, 18),
            KEYCODE_DOWN_RIGHT(19, 19),
            KEYCODE_ENTER(20, 20),
            KEYCODE_HOME(21, 21),
            KEYCODE_MENU(22, 22),
            KEYCODE_BACK(23, 23),
            KEYCODE_VOLUME_UP(24, 24),
            KEYCODE_VOLUME_DOWN(25, 25),
            KEYCODE_MUTE(26, 26),
            KEYCODE_CHANNEL_UP(27, 27),
            KEYCODE_CHANNEL_DOWN(28, 28),
            KEYCODE_BLUE(29, 29),
            KEYCODE_GREEN(30, 30),
            KEYCODE_RED(31, 31),
            KEYCODE_YELLOW(32, 32),
            KEYCODE_PLAY(33, 33),
            KEYCODE_PAUSE(34, 34),
            KEYCODE_STOP(35, 35),
            KEYCODE_FAST_FORWARD(36, 36),
            KEYCODE_FAST_BACKWARD(37, 37),
            KEYCODE_SKIP_FORWARD(38, 38),
            KEYCODE_SKIP_BACKWARD(39, 39),
            KEYCODE_RECORD(40, 40),
            KEYCODE_RECORD_LIST(41, 41),
            KEYCODE_REPEAT(42, 42),
            KEYCODE_LIVE(43, 43),
            KEYCODE_GUIDE(44, 44),
            KEYCODE_INFO(45, 45),
            KEYCODE_RESOLUTION(46, 46),
            KEYCODE_INPUT(47, 47),
            KEYCODE_VIDEO_PIP(48, 48),
            KEYCODE_SUBTITLE(49, 49),
            KEYCODE_PROGRAM_LIST(50, 50),
            KEYCODE_TEXT(51, 51),
            KEYCODE_MARKER(52, 52),
            KEYCODE_NEXT(53, 53),
            KEYCODE_PREVIOUS(54, 54),
            KEYCODE_OPEN(55, 55),
            KEYCODE_CLOSE(56, 56),
            KEYCODE_REPEAT_A2B(57, 57),
            KEYCODE_PLAY_PAUSE(58, 58),
            KEYCODE_RANDOM(59, 59),
            KEYCODE_ZOOM(60, 60),
            KEYCODE_SEARCH(61, 61),
            KEYCODE_TITLE(62, 62),
            KEYCODE_POPUP(63, 63),
            KEYCODE_CLEAR(64, 64),
            KEYCODE_SETUP(65, 65),
            KEYCODE_DISC_MENU(66, 66),
            KEYCODE_TIMER_REC(67, 67),
            KEYCODE_PICTURE(68, 68),
            KEYCODE_LOCK(69, 69),
            KEYCODE_TUNER(70, 70),
            KEYCODE_EQUALIZER(71, 71),
            KEYCODE_SLEEP(72, 72),
            KEYCODE_WOOFER_VOL(73, 73),
            KEYCODE_NIGHT(74, 74),
            KEYCODE_IPOD(75, 75),
            KEYCODE_SPEAKER_SETUP(76, 76),
            KEYCODE_OPTICAL(77, 77),
            KEYCODE_MIC_VOLUME_UP(78, 78),
            KEYCODE_MIC_VOLUME_DOWN(79, 79),
            KEYCODE_MIC_ECHO(80, 80),
            KEYCODE_CD_ARCHIVING(81, 81),
            KEYCODE_GRACENOTE(82, 82),
            KEYCODE_AUDIO_PIP(83, 83),
            KEYCODE_SUBTITLE_ONOFF(84, 84),
            KEYCODE_AUDIO(85, 85),
            KEYCODE_ANGLE(86, 86),
            KEYCODE_3D_SOUND(87, 87),
            KEYCODE_3D_VIDEO(88, 88),
            KEYCODE_3D_LR(89, 89),
            KEYCODE_DASH(90, 90),
            KEYCODE_PREVIOUS_CHANNEL(91, 91),
            KEYCODE_FAVORITE_CHANNEL(92, 92),
            KEYCODE_QUICK_MENU(93, 93),
            KEYCODE_TEXT_OPTION(94, 94),
            KEYCODE_AUDIO_DESCRIPTION(95, 95),
            KEYCODE_NETCAST(96, 96),
            KEYCODE_ENERGY_SAVING(97, 97),
            KEYCODE_AV_MODE(98, 98),
            KEYCODE_SIMPLE_LINK(99, 99),
            KEYCODE_EXIT(100, 100),
            KEYCODE_RESERVATION_LIST(101, 101),
            KEYCODE_PIP_CHANNEL_UP(102, 102),
            KEYCODE_PIP_CHANNEL_DOWN(103, 103),
            KEYCODE_PIP_INPUT(104, 104),
            KEYCODE_3D_SOUND_ZOOMING(105, 105),
            KEYCODE_SOUND_EFFECT(106, 106),
            KEYCODE_SPEAKER_LEVEL(107, 107),
            KEYCODE_FUNCTION(108, 108),
            KEYCODE_BGM(109, 109),
            KEYCODE_2DTO3D(110, 110);
            
            private final int index;
            private final int value;
            private static Internal.EnumLiteMap<KeyCode> internalValueMap = new Internal.EnumLiteMap<KeyCode>() { // from class: com.lge.UDAP.ROAP.Roap.Key.KeyCode.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public KeyCode findValueByNumber(int i) {
                    return KeyCode.valueOf(i);
                }
            };
            private static final KeyCode[] VALUES = {KEYCODE_UNKNOWN, KEYCODE_POWER, KEYCODE_0, KEYCODE_1, KEYCODE_2, KEYCODE_3, KEYCODE_4, KEYCODE_5, KEYCODE_6, KEYCODE_7, KEYCODE_8, KEYCODE_9, KEYCODE_UP, KEYCODE_DOWN, KEYCODE_LEFT, KEYCODE_RIGHT, KEYCODE_UP_LEFT, KEYCODE_UP_RIGHT, KEYCODE_DOWN_LEFT, KEYCODE_DOWN_RIGHT, KEYCODE_ENTER, KEYCODE_HOME, KEYCODE_MENU, KEYCODE_BACK, KEYCODE_VOLUME_UP, KEYCODE_VOLUME_DOWN, KEYCODE_MUTE, KEYCODE_CHANNEL_UP, KEYCODE_CHANNEL_DOWN, KEYCODE_BLUE, KEYCODE_GREEN, KEYCODE_RED, KEYCODE_YELLOW, KEYCODE_PLAY, KEYCODE_PAUSE, KEYCODE_STOP, KEYCODE_FAST_FORWARD, KEYCODE_FAST_BACKWARD, KEYCODE_SKIP_FORWARD, KEYCODE_SKIP_BACKWARD, KEYCODE_RECORD, KEYCODE_RECORD_LIST, KEYCODE_REPEAT, KEYCODE_LIVE, KEYCODE_GUIDE, KEYCODE_INFO, KEYCODE_RESOLUTION, KEYCODE_INPUT, KEYCODE_VIDEO_PIP, KEYCODE_SUBTITLE, KEYCODE_PROGRAM_LIST, KEYCODE_TEXT, KEYCODE_MARKER, KEYCODE_NEXT, KEYCODE_PREVIOUS, KEYCODE_OPEN, KEYCODE_CLOSE, KEYCODE_REPEAT_A2B, KEYCODE_PLAY_PAUSE, KEYCODE_RANDOM, KEYCODE_ZOOM, KEYCODE_SEARCH, KEYCODE_TITLE, KEYCODE_POPUP, KEYCODE_CLEAR, KEYCODE_SETUP, KEYCODE_DISC_MENU, KEYCODE_TIMER_REC, KEYCODE_PICTURE, KEYCODE_LOCK, KEYCODE_TUNER, KEYCODE_EQUALIZER, KEYCODE_SLEEP, KEYCODE_WOOFER_VOL, KEYCODE_NIGHT, KEYCODE_IPOD, KEYCODE_SPEAKER_SETUP, KEYCODE_OPTICAL, KEYCODE_MIC_VOLUME_UP, KEYCODE_MIC_VOLUME_DOWN, KEYCODE_MIC_ECHO, KEYCODE_CD_ARCHIVING, KEYCODE_GRACENOTE, KEYCODE_AUDIO_PIP, KEYCODE_SUBTITLE_ONOFF, KEYCODE_AUDIO, KEYCODE_ANGLE, KEYCODE_3D_SOUND, KEYCODE_3D_VIDEO, KEYCODE_3D_LR, KEYCODE_DASH, KEYCODE_PREVIOUS_CHANNEL, KEYCODE_FAVORITE_CHANNEL, KEYCODE_QUICK_MENU, KEYCODE_TEXT_OPTION, KEYCODE_AUDIO_DESCRIPTION, KEYCODE_NETCAST, KEYCODE_ENERGY_SAVING, KEYCODE_AV_MODE, KEYCODE_SIMPLE_LINK, KEYCODE_EXIT, KEYCODE_RESERVATION_LIST, KEYCODE_PIP_CHANNEL_UP, KEYCODE_PIP_CHANNEL_DOWN, KEYCODE_PIP_INPUT, KEYCODE_3D_SOUND_ZOOMING, KEYCODE_SOUND_EFFECT, KEYCODE_SPEAKER_LEVEL, KEYCODE_FUNCTION, KEYCODE_BGM, KEYCODE_2DTO3D};

            static {
                Roap.a();
            }

            KeyCode(int i, int i2) {
                this.index = i;
                this.value = i2;
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return Key.getDescriptor().getEnumTypes().get(0);
            }

            public static Internal.EnumLiteMap<KeyCode> internalGetValueMap() {
                return internalValueMap;
            }

            public static KeyCode valueOf(int i) {
                switch (i) {
                    case 0:
                        return KEYCODE_UNKNOWN;
                    case 1:
                        return KEYCODE_POWER;
                    case 2:
                        return KEYCODE_0;
                    case 3:
                        return KEYCODE_1;
                    case 4:
                        return KEYCODE_2;
                    case 5:
                        return KEYCODE_3;
                    case 6:
                        return KEYCODE_4;
                    case 7:
                        return KEYCODE_5;
                    case 8:
                        return KEYCODE_6;
                    case 9:
                        return KEYCODE_7;
                    case 10:
                        return KEYCODE_8;
                    case 11:
                        return KEYCODE_9;
                    case 12:
                        return KEYCODE_UP;
                    case 13:
                        return KEYCODE_DOWN;
                    case 14:
                        return KEYCODE_LEFT;
                    case 15:
                        return KEYCODE_RIGHT;
                    case 16:
                        return KEYCODE_UP_LEFT;
                    case 17:
                        return KEYCODE_UP_RIGHT;
                    case 18:
                        return KEYCODE_DOWN_LEFT;
                    case 19:
                        return KEYCODE_DOWN_RIGHT;
                    case 20:
                        return KEYCODE_ENTER;
                    case 21:
                        return KEYCODE_HOME;
                    case 22:
                        return KEYCODE_MENU;
                    case 23:
                        return KEYCODE_BACK;
                    case 24:
                        return KEYCODE_VOLUME_UP;
                    case 25:
                        return KEYCODE_VOLUME_DOWN;
                    case 26:
                        return KEYCODE_MUTE;
                    case 27:
                        return KEYCODE_CHANNEL_UP;
                    case 28:
                        return KEYCODE_CHANNEL_DOWN;
                    case 29:
                        return KEYCODE_BLUE;
                    case 30:
                        return KEYCODE_GREEN;
                    case 31:
                        return KEYCODE_RED;
                    case 32:
                        return KEYCODE_YELLOW;
                    case 33:
                        return KEYCODE_PLAY;
                    case 34:
                        return KEYCODE_PAUSE;
                    case 35:
                        return KEYCODE_STOP;
                    case 36:
                        return KEYCODE_FAST_FORWARD;
                    case 37:
                        return KEYCODE_FAST_BACKWARD;
                    case 38:
                        return KEYCODE_SKIP_FORWARD;
                    case 39:
                        return KEYCODE_SKIP_BACKWARD;
                    case 40:
                        return KEYCODE_RECORD;
                    case 41:
                        return KEYCODE_RECORD_LIST;
                    case 42:
                        return KEYCODE_REPEAT;
                    case 43:
                        return KEYCODE_LIVE;
                    case 44:
                        return KEYCODE_GUIDE;
                    case 45:
                        return KEYCODE_INFO;
                    case 46:
                        return KEYCODE_RESOLUTION;
                    case 47:
                        return KEYCODE_INPUT;
                    case 48:
                        return KEYCODE_VIDEO_PIP;
                    case 49:
                        return KEYCODE_SUBTITLE;
                    case 50:
                        return KEYCODE_PROGRAM_LIST;
                    case 51:
                        return KEYCODE_TEXT;
                    case 52:
                        return KEYCODE_MARKER;
                    case 53:
                        return KEYCODE_NEXT;
                    case 54:
                        return KEYCODE_PREVIOUS;
                    case 55:
                        return KEYCODE_OPEN;
                    case 56:
                        return KEYCODE_CLOSE;
                    case 57:
                        return KEYCODE_REPEAT_A2B;
                    case 58:
                        return KEYCODE_PLAY_PAUSE;
                    case 59:
                        return KEYCODE_RANDOM;
                    case 60:
                        return KEYCODE_ZOOM;
                    case 61:
                        return KEYCODE_SEARCH;
                    case 62:
                        return KEYCODE_TITLE;
                    case 63:
                        return KEYCODE_POPUP;
                    case 64:
                        return KEYCODE_CLEAR;
                    case 65:
                        return KEYCODE_SETUP;
                    case 66:
                        return KEYCODE_DISC_MENU;
                    case 67:
                        return KEYCODE_TIMER_REC;
                    case 68:
                        return KEYCODE_PICTURE;
                    case 69:
                        return KEYCODE_LOCK;
                    case 70:
                        return KEYCODE_TUNER;
                    case 71:
                        return KEYCODE_EQUALIZER;
                    case 72:
                        return KEYCODE_SLEEP;
                    case 73:
                        return KEYCODE_WOOFER_VOL;
                    case 74:
                        return KEYCODE_NIGHT;
                    case 75:
                        return KEYCODE_IPOD;
                    case 76:
                        return KEYCODE_SPEAKER_SETUP;
                    case 77:
                        return KEYCODE_OPTICAL;
                    case 78:
                        return KEYCODE_MIC_VOLUME_UP;
                    case 79:
                        return KEYCODE_MIC_VOLUME_DOWN;
                    case 80:
                        return KEYCODE_MIC_ECHO;
                    case 81:
                        return KEYCODE_CD_ARCHIVING;
                    case 82:
                        return KEYCODE_GRACENOTE;
                    case 83:
                        return KEYCODE_AUDIO_PIP;
                    case 84:
                        return KEYCODE_SUBTITLE_ONOFF;
                    case 85:
                        return KEYCODE_AUDIO;
                    case 86:
                        return KEYCODE_ANGLE;
                    case 87:
                        return KEYCODE_3D_SOUND;
                    case 88:
                        return KEYCODE_3D_VIDEO;
                    case 89:
                        return KEYCODE_3D_LR;
                    case 90:
                        return KEYCODE_DASH;
                    case 91:
                        return KEYCODE_PREVIOUS_CHANNEL;
                    case 92:
                        return KEYCODE_FAVORITE_CHANNEL;
                    case 93:
                        return KEYCODE_QUICK_MENU;
                    case 94:
                        return KEYCODE_TEXT_OPTION;
                    case 95:
                        return KEYCODE_AUDIO_DESCRIPTION;
                    case 96:
                        return KEYCODE_NETCAST;
                    case 97:
                        return KEYCODE_ENERGY_SAVING;
                    case 98:
                        return KEYCODE_AV_MODE;
                    case 99:
                        return KEYCODE_SIMPLE_LINK;
                    case 100:
                        return KEYCODE_EXIT;
                    case 101:
                        return KEYCODE_RESERVATION_LIST;
                    case 102:
                        return KEYCODE_PIP_CHANNEL_UP;
                    case 103:
                        return KEYCODE_PIP_CHANNEL_DOWN;
                    case 104:
                        return KEYCODE_PIP_INPUT;
                    case 105:
                        return KEYCODE_3D_SOUND_ZOOMING;
                    case 106:
                        return KEYCODE_SOUND_EFFECT;
                    case 107:
                        return KEYCODE_SPEAKER_LEVEL;
                    case 108:
                        return KEYCODE_FUNCTION;
                    case 109:
                        return KEYCODE_BGM;
                    case 110:
                        return KEYCODE_2DTO3D;
                    default:
                        return null;
                }
            }

            public static KeyCode valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
        public static final class a extends GeneratedMessage.Builder<a> {
            private Key result;

            private a() {
            }

            static /* synthetic */ a access$10500() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public Key buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static a create() {
                a aVar = new a();
                aVar.result = new Key();
                return aVar;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Key build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Key buildPartial() {
                Key key = this.result;
                if (key != null) {
                    this.result = null;
                    return key;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public a clear() {
                if (this.result != null) {
                    this.result = new Key();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public a clearAction() {
                this.result.hasAction = false;
                this.result.action_ = Action.UNKNOWN;
                return this;
            }

            public a clearCode() {
                this.result.hasCode = false;
                this.result.code_ = KeyCode.KEYCODE_UNKNOWN;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public a mo3clone() {
                return create().mergeFrom(this.result);
            }

            public Action getAction() {
                return this.result.getAction();
            }

            public KeyCode getCode() {
                return this.result.getCode();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Key getDefaultInstanceForType() {
                return Key.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return Key.getDescriptor();
            }

            public boolean hasAction() {
                return this.result.hasAction();
            }

            public boolean hasCode() {
                return this.result.hasCode();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.Builder
            public Key internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public a mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                int n;
                int i;
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 8) {
                        n = codedInputStream.n();
                        KeyCode valueOf = KeyCode.valueOf(n);
                        if (valueOf == null) {
                            i = 1;
                            newBuilder.mergeVarintField(i, n);
                        } else {
                            setCode(valueOf);
                        }
                    } else if (a == 16) {
                        n = codedInputStream.n();
                        Action valueOf2 = Action.valueOf(n);
                        if (valueOf2 == null) {
                            i = 2;
                            newBuilder.mergeVarintField(i, n);
                        } else {
                            setAction(valueOf2);
                        }
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public a mergeFrom(Message message) {
                if (message instanceof Key) {
                    return mergeFrom((Key) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public a mergeFrom(Key key) {
                if (key == Key.getDefaultInstance()) {
                    return this;
                }
                if (key.hasCode()) {
                    setCode(key.getCode());
                }
                if (key.hasAction()) {
                    setAction(key.getAction());
                }
                mergeUnknownFields(key.getUnknownFields());
                return this;
            }

            public a setAction(Action action) {
                if (action != null) {
                    this.result.hasAction = true;
                    this.result.action_ = action;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setCode(KeyCode keyCode) {
                if (keyCode != null) {
                    this.result.hasCode = true;
                    this.result.code_ = keyCode;
                    return this;
                }
                throw new NullPointerException();
            }
        }

        static {
            Roap.b();
            defaultInstance.initFields();
        }

        private Key() {
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private Key(boolean z) {
            this.memoizedSerializedSize = -1;
        }

        public static Key getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Roap.m;
        }

        private void initFields() {
            this.code_ = KeyCode.KEYCODE_UNKNOWN;
            this.action_ = Action.UNKNOWN;
        }

        public static a newBuilder() {
            return a.access$10500();
        }

        public static a newBuilder(Key key) {
            return newBuilder().mergeFrom(key);
        }

        public static Key parseDelimitedFrom(InputStream inputStream) {
            a newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static Key parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            a newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static Key parseFrom(ByteString byteString) {
            return ((a) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static Key parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static Key parseFrom(CodedInputStream codedInputStream) {
            return ((a) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static Key parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static Key parseFrom(InputStream inputStream) {
            return ((a) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static Key parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static Key parseFrom(byte[] bArr) {
            return ((a) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static Key parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        public Action getAction() {
            return this.action_;
        }

        public KeyCode getCode() {
            return this.code_;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Key getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int j = hasCode() ? 0 + CodedOutputStream.j(1, getCode().getNumber()) : 0;
            if (hasAction()) {
                j += CodedOutputStream.j(2, getAction().getNumber());
            }
            int serializedSize = j + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public boolean hasAction() {
            return this.hasAction;
        }

        public boolean hasCode() {
            return this.hasCode;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Roap.n;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            return this.hasCode && this.hasAction;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public a newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public a toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            if (hasCode()) {
                codedOutputStream.d(1, getCode().getNumber());
            }
            if (hasAction()) {
                codedOutputStream.d(2, getAction().getNumber());
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class ROAPMessage extends GeneratedMessage {
        public static final int COMMAND_MESSAGE_FIELD_NUMBER = 5;
        public static final int EVENT_MESSAGE_FIELD_NUMBER = 6;
        public static final int REQUEST_MESSAGE_FIELD_NUMBER = 3;
        public static final int RESPONSE_MESSAGE_FIELD_NUMBER = 4;
        public static final int SESSION_ID_FIELD_NUMBER = 2;
        public static final int TYPE_FIELD_NUMBER = 1;
        private static final ROAPMessage defaultInstance = new ROAPMessage(true);
        private CommandMessage commandMessage_;
        private EventMessage eventMessage_;
        private boolean hasCommandMessage;
        private boolean hasEventMessage;
        private boolean hasRequestMessage;
        private boolean hasResponseMessage;
        private boolean hasSessionId;
        private boolean hasType;
        private int memoizedSerializedSize;
        private RequestMessage requestMessage_;
        private ResponseMessage responseMessage_;
        private String sessionId_;
        private MessageType type_;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public enum MessageType implements ProtocolMessageEnum {
            REQUEST(0, 1),
            RESPONSE(1, 2),
            COMMAND(2, 3),
            EVENT(3, 4);
            
            private final int index;
            private final int value;
            private static Internal.EnumLiteMap<MessageType> internalValueMap = new Internal.EnumLiteMap<MessageType>() { // from class: com.lge.UDAP.ROAP.Roap.ROAPMessage.MessageType.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public MessageType findValueByNumber(int i) {
                    return MessageType.valueOf(i);
                }
            };
            private static final MessageType[] VALUES = {REQUEST, RESPONSE, COMMAND, EVENT};

            static {
                Roap.a();
            }

            MessageType(int i, int i2) {
                this.index = i;
                this.value = i2;
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return ROAPMessage.getDescriptor().getEnumTypes().get(0);
            }

            public static Internal.EnumLiteMap<MessageType> internalGetValueMap() {
                return internalValueMap;
            }

            public static MessageType valueOf(int i) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                return null;
                            }
                            return EVENT;
                        }
                        return COMMAND;
                    }
                    return RESPONSE;
                }
                return REQUEST;
            }

            public static MessageType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
        public static final class a extends GeneratedMessage.Builder<a> {
            private ROAPMessage result;

            private a() {
            }

            static /* synthetic */ a access$300() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public ROAPMessage buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static a create() {
                a aVar = new a();
                aVar.result = new ROAPMessage();
                return aVar;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ROAPMessage build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ROAPMessage buildPartial() {
                ROAPMessage rOAPMessage = this.result;
                if (rOAPMessage != null) {
                    this.result = null;
                    return rOAPMessage;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public a clear() {
                if (this.result != null) {
                    this.result = new ROAPMessage();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public a clearCommandMessage() {
                this.result.hasCommandMessage = false;
                this.result.commandMessage_ = CommandMessage.getDefaultInstance();
                return this;
            }

            public a clearEventMessage() {
                this.result.hasEventMessage = false;
                this.result.eventMessage_ = EventMessage.getDefaultInstance();
                return this;
            }

            public a clearRequestMessage() {
                this.result.hasRequestMessage = false;
                this.result.requestMessage_ = RequestMessage.getDefaultInstance();
                return this;
            }

            public a clearResponseMessage() {
                this.result.hasResponseMessage = false;
                this.result.responseMessage_ = ResponseMessage.getDefaultInstance();
                return this;
            }

            public a clearSessionId() {
                this.result.hasSessionId = false;
                this.result.sessionId_ = ROAPMessage.getDefaultInstance().getSessionId();
                return this;
            }

            public a clearType() {
                this.result.hasType = false;
                this.result.type_ = MessageType.REQUEST;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public a mo3clone() {
                return create().mergeFrom(this.result);
            }

            public CommandMessage getCommandMessage() {
                return this.result.getCommandMessage();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ROAPMessage getDefaultInstanceForType() {
                return ROAPMessage.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return ROAPMessage.getDescriptor();
            }

            public EventMessage getEventMessage() {
                return this.result.getEventMessage();
            }

            public RequestMessage getRequestMessage() {
                return this.result.getRequestMessage();
            }

            public ResponseMessage getResponseMessage() {
                return this.result.getResponseMessage();
            }

            public String getSessionId() {
                return this.result.getSessionId();
            }

            public MessageType getType() {
                return this.result.getType();
            }

            public boolean hasCommandMessage() {
                return this.result.hasCommandMessage();
            }

            public boolean hasEventMessage() {
                return this.result.hasEventMessage();
            }

            public boolean hasRequestMessage() {
                return this.result.hasRequestMessage();
            }

            public boolean hasResponseMessage() {
                return this.result.hasResponseMessage();
            }

            public boolean hasSessionId() {
                return this.result.hasSessionId();
            }

            public boolean hasType() {
                return this.result.hasType();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.Builder
            public ROAPMessage internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            public a mergeCommandMessage(CommandMessage commandMessage) {
                ROAPMessage rOAPMessage;
                if (!this.result.hasCommandMessage() || this.result.commandMessage_ == CommandMessage.getDefaultInstance()) {
                    rOAPMessage = this.result;
                } else {
                    rOAPMessage = this.result;
                    commandMessage = CommandMessage.newBuilder(rOAPMessage.commandMessage_).mergeFrom(commandMessage).buildPartial();
                }
                rOAPMessage.commandMessage_ = commandMessage;
                this.result.hasCommandMessage = true;
                return this;
            }

            public a mergeEventMessage(EventMessage eventMessage) {
                ROAPMessage rOAPMessage;
                if (!this.result.hasEventMessage() || this.result.eventMessage_ == EventMessage.getDefaultInstance()) {
                    rOAPMessage = this.result;
                } else {
                    rOAPMessage = this.result;
                    eventMessage = EventMessage.newBuilder(rOAPMessage.eventMessage_).mergeFrom(eventMessage).buildPartial();
                }
                rOAPMessage.eventMessage_ = eventMessage;
                this.result.hasEventMessage = true;
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public a mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 8) {
                        int n = codedInputStream.n();
                        MessageType valueOf = MessageType.valueOf(n);
                        if (valueOf == null) {
                            newBuilder.mergeVarintField(1, n);
                        } else {
                            setType(valueOf);
                        }
                    } else if (a == 18) {
                        setSessionId(codedInputStream.k());
                    } else if (a == 26) {
                        RequestMessage.a newBuilder2 = RequestMessage.newBuilder();
                        if (hasRequestMessage()) {
                            newBuilder2.mergeFrom(getRequestMessage());
                        }
                        codedInputStream.a(newBuilder2, extensionRegistryLite);
                        setRequestMessage(newBuilder2.buildPartial());
                    } else if (a == 34) {
                        ResponseMessage.a newBuilder3 = ResponseMessage.newBuilder();
                        if (hasResponseMessage()) {
                            newBuilder3.mergeFrom(getResponseMessage());
                        }
                        codedInputStream.a(newBuilder3, extensionRegistryLite);
                        setResponseMessage(newBuilder3.buildPartial());
                    } else if (a == 42) {
                        CommandMessage.a newBuilder4 = CommandMessage.newBuilder();
                        if (hasCommandMessage()) {
                            newBuilder4.mergeFrom(getCommandMessage());
                        }
                        codedInputStream.a(newBuilder4, extensionRegistryLite);
                        setCommandMessage(newBuilder4.buildPartial());
                    } else if (a == 50) {
                        EventMessage.a newBuilder5 = EventMessage.newBuilder();
                        if (hasEventMessage()) {
                            newBuilder5.mergeFrom(getEventMessage());
                        }
                        codedInputStream.a(newBuilder5, extensionRegistryLite);
                        setEventMessage(newBuilder5.buildPartial());
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public a mergeFrom(Message message) {
                if (message instanceof ROAPMessage) {
                    return mergeFrom((ROAPMessage) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public a mergeFrom(ROAPMessage rOAPMessage) {
                if (rOAPMessage == ROAPMessage.getDefaultInstance()) {
                    return this;
                }
                if (rOAPMessage.hasType()) {
                    setType(rOAPMessage.getType());
                }
                if (rOAPMessage.hasSessionId()) {
                    setSessionId(rOAPMessage.getSessionId());
                }
                if (rOAPMessage.hasRequestMessage()) {
                    mergeRequestMessage(rOAPMessage.getRequestMessage());
                }
                if (rOAPMessage.hasResponseMessage()) {
                    mergeResponseMessage(rOAPMessage.getResponseMessage());
                }
                if (rOAPMessage.hasCommandMessage()) {
                    mergeCommandMessage(rOAPMessage.getCommandMessage());
                }
                if (rOAPMessage.hasEventMessage()) {
                    mergeEventMessage(rOAPMessage.getEventMessage());
                }
                mergeUnknownFields(rOAPMessage.getUnknownFields());
                return this;
            }

            public a mergeRequestMessage(RequestMessage requestMessage) {
                ROAPMessage rOAPMessage;
                if (!this.result.hasRequestMessage() || this.result.requestMessage_ == RequestMessage.getDefaultInstance()) {
                    rOAPMessage = this.result;
                } else {
                    rOAPMessage = this.result;
                    requestMessage = RequestMessage.newBuilder(rOAPMessage.requestMessage_).mergeFrom(requestMessage).buildPartial();
                }
                rOAPMessage.requestMessage_ = requestMessage;
                this.result.hasRequestMessage = true;
                return this;
            }

            public a mergeResponseMessage(ResponseMessage responseMessage) {
                ROAPMessage rOAPMessage;
                if (!this.result.hasResponseMessage() || this.result.responseMessage_ == ResponseMessage.getDefaultInstance()) {
                    rOAPMessage = this.result;
                } else {
                    rOAPMessage = this.result;
                    responseMessage = ResponseMessage.newBuilder(rOAPMessage.responseMessage_).mergeFrom(responseMessage).buildPartial();
                }
                rOAPMessage.responseMessage_ = responseMessage;
                this.result.hasResponseMessage = true;
                return this;
            }

            public a setCommandMessage(CommandMessage.a aVar) {
                this.result.hasCommandMessage = true;
                this.result.commandMessage_ = aVar.build();
                return this;
            }

            public a setCommandMessage(CommandMessage commandMessage) {
                if (commandMessage != null) {
                    this.result.hasCommandMessage = true;
                    this.result.commandMessage_ = commandMessage;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setEventMessage(EventMessage.a aVar) {
                this.result.hasEventMessage = true;
                this.result.eventMessage_ = aVar.build();
                return this;
            }

            public a setEventMessage(EventMessage eventMessage) {
                if (eventMessage != null) {
                    this.result.hasEventMessage = true;
                    this.result.eventMessage_ = eventMessage;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setRequestMessage(RequestMessage.a aVar) {
                this.result.hasRequestMessage = true;
                this.result.requestMessage_ = aVar.build();
                return this;
            }

            public a setRequestMessage(RequestMessage requestMessage) {
                if (requestMessage != null) {
                    this.result.hasRequestMessage = true;
                    this.result.requestMessage_ = requestMessage;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setResponseMessage(ResponseMessage.a aVar) {
                this.result.hasResponseMessage = true;
                this.result.responseMessage_ = aVar.build();
                return this;
            }

            public a setResponseMessage(ResponseMessage responseMessage) {
                if (responseMessage != null) {
                    this.result.hasResponseMessage = true;
                    this.result.responseMessage_ = responseMessage;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setSessionId(String str) {
                if (str != null) {
                    this.result.hasSessionId = true;
                    this.result.sessionId_ = str;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setType(MessageType messageType) {
                if (messageType != null) {
                    this.result.hasType = true;
                    this.result.type_ = messageType;
                    return this;
                }
                throw new NullPointerException();
            }
        }

        static {
            Roap.b();
            defaultInstance.initFields();
        }

        private ROAPMessage() {
            this.sessionId_ = com.lge.media.launcher.a.d;
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private ROAPMessage(boolean z) {
            this.sessionId_ = com.lge.media.launcher.a.d;
            this.memoizedSerializedSize = -1;
        }

        public static ROAPMessage getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Roap.a;
        }

        private void initFields() {
            this.type_ = MessageType.REQUEST;
            this.requestMessage_ = RequestMessage.getDefaultInstance();
            this.responseMessage_ = ResponseMessage.getDefaultInstance();
            this.commandMessage_ = CommandMessage.getDefaultInstance();
            this.eventMessage_ = EventMessage.getDefaultInstance();
        }

        public static a newBuilder() {
            return a.access$300();
        }

        public static a newBuilder(ROAPMessage rOAPMessage) {
            return newBuilder().mergeFrom(rOAPMessage);
        }

        public static ROAPMessage parseDelimitedFrom(InputStream inputStream) {
            a newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static ROAPMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            a newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static ROAPMessage parseFrom(ByteString byteString) {
            return ((a) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static ROAPMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static ROAPMessage parseFrom(CodedInputStream codedInputStream) {
            return ((a) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static ROAPMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static ROAPMessage parseFrom(InputStream inputStream) {
            return ((a) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static ROAPMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static ROAPMessage parseFrom(byte[] bArr) {
            return ((a) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static ROAPMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        public CommandMessage getCommandMessage() {
            return this.commandMessage_;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public ROAPMessage getDefaultInstanceForType() {
            return defaultInstance;
        }

        public EventMessage getEventMessage() {
            return this.eventMessage_;
        }

        public RequestMessage getRequestMessage() {
            return this.requestMessage_;
        }

        public ResponseMessage getResponseMessage() {
            return this.responseMessage_;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int j = hasType() ? 0 + CodedOutputStream.j(1, getType().getNumber()) : 0;
            if (hasSessionId()) {
                j += CodedOutputStream.b(2, getSessionId());
            }
            if (hasRequestMessage()) {
                j += CodedOutputStream.g(3, getRequestMessage());
            }
            if (hasResponseMessage()) {
                j += CodedOutputStream.g(4, getResponseMessage());
            }
            if (hasCommandMessage()) {
                j += CodedOutputStream.g(5, getCommandMessage());
            }
            if (hasEventMessage()) {
                j += CodedOutputStream.g(6, getEventMessage());
            }
            int serializedSize = j + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public String getSessionId() {
            return this.sessionId_;
        }

        public MessageType getType() {
            return this.type_;
        }

        public boolean hasCommandMessage() {
            return this.hasCommandMessage;
        }

        public boolean hasEventMessage() {
            return this.hasEventMessage;
        }

        public boolean hasRequestMessage() {
            return this.hasRequestMessage;
        }

        public boolean hasResponseMessage() {
            return this.hasResponseMessage;
        }

        public boolean hasSessionId() {
            return this.hasSessionId;
        }

        public boolean hasType() {
            return this.hasType;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Roap.b;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            if (this.hasType) {
                if (!hasRequestMessage() || getRequestMessage().isInitialized()) {
                    if (!hasResponseMessage() || getResponseMessage().isInitialized()) {
                        if (!hasCommandMessage() || getCommandMessage().isInitialized()) {
                            return !hasEventMessage() || getEventMessage().isInitialized();
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public a newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public a toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            if (hasType()) {
                codedOutputStream.d(1, getType().getNumber());
            }
            if (hasSessionId()) {
                codedOutputStream.a(2, getSessionId());
            }
            if (hasRequestMessage()) {
                codedOutputStream.c(3, getRequestMessage());
            }
            if (hasResponseMessage()) {
                codedOutputStream.c(4, getResponseMessage());
            }
            if (hasCommandMessage()) {
                codedOutputStream.c(5, getCommandMessage());
            }
            if (hasEventMessage()) {
                codedOutputStream.c(6, getEventMessage());
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class RequestMessage extends GeneratedMessage {
        public static final int AUTH_MESSAGE_FIELD_NUMBER = 2;
        public static final int DATA_MESSAGE_FIELD_NUMBER = 3;
        public static final int DEMAND_MESSAGE_FIELD_NUMBER = 4;
        public static final int TYPE_FIELD_NUMBER = 1;
        private static final RequestMessage defaultInstance = new RequestMessage(true);
        private Authentication authMessage_;
        private Data dataMessage_;
        private Demand demandMessage_;
        private boolean hasAuthMessage;
        private boolean hasDataMessage;
        private boolean hasDemandMessage;
        private boolean hasType;
        private int memoizedSerializedSize;
        private RequestType type_;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public enum RequestType implements ProtocolMessageEnum {
            UNKNOWN(0, 0),
            AUTHENTICATION(1, 1),
            DATA(2, 2),
            DEMAND(3, 3);
            
            private final int index;
            private final int value;
            private static Internal.EnumLiteMap<RequestType> internalValueMap = new Internal.EnumLiteMap<RequestType>() { // from class: com.lge.UDAP.ROAP.Roap.RequestMessage.RequestType.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public RequestType findValueByNumber(int i) {
                    return RequestType.valueOf(i);
                }
            };
            private static final RequestType[] VALUES = {UNKNOWN, AUTHENTICATION, DATA, DEMAND};

            static {
                Roap.a();
            }

            RequestType(int i, int i2) {
                this.index = i;
                this.value = i2;
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return RequestMessage.getDescriptor().getEnumTypes().get(0);
            }

            public static Internal.EnumLiteMap<RequestType> internalGetValueMap() {
                return internalValueMap;
            }

            public static RequestType valueOf(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                return null;
                            }
                            return DEMAND;
                        }
                        return DATA;
                    }
                    return AUTHENTICATION;
                }
                return UNKNOWN;
            }

            public static RequestType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
        public static final class a extends GeneratedMessage.Builder<a> {
            private RequestMessage result;

            private a() {
            }

            static /* synthetic */ a access$2000() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public RequestMessage buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static a create() {
                a aVar = new a();
                aVar.result = new RequestMessage();
                return aVar;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public RequestMessage build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public RequestMessage buildPartial() {
                RequestMessage requestMessage = this.result;
                if (requestMessage != null) {
                    this.result = null;
                    return requestMessage;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public a clear() {
                if (this.result != null) {
                    this.result = new RequestMessage();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public a clearAuthMessage() {
                this.result.hasAuthMessage = false;
                this.result.authMessage_ = Authentication.getDefaultInstance();
                return this;
            }

            public a clearDataMessage() {
                this.result.hasDataMessage = false;
                this.result.dataMessage_ = Data.getDefaultInstance();
                return this;
            }

            public a clearDemandMessage() {
                this.result.hasDemandMessage = false;
                this.result.demandMessage_ = Demand.getDefaultInstance();
                return this;
            }

            public a clearType() {
                this.result.hasType = false;
                this.result.type_ = RequestType.UNKNOWN;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public a mo3clone() {
                return create().mergeFrom(this.result);
            }

            public Authentication getAuthMessage() {
                return this.result.getAuthMessage();
            }

            public Data getDataMessage() {
                return this.result.getDataMessage();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public RequestMessage getDefaultInstanceForType() {
                return RequestMessage.getDefaultInstance();
            }

            public Demand getDemandMessage() {
                return this.result.getDemandMessage();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return RequestMessage.getDescriptor();
            }

            public RequestType getType() {
                return this.result.getType();
            }

            public boolean hasAuthMessage() {
                return this.result.hasAuthMessage();
            }

            public boolean hasDataMessage() {
                return this.result.hasDataMessage();
            }

            public boolean hasDemandMessage() {
                return this.result.hasDemandMessage();
            }

            public boolean hasType() {
                return this.result.hasType();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.Builder
            public RequestMessage internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            public a mergeAuthMessage(Authentication authentication) {
                RequestMessage requestMessage;
                if (!this.result.hasAuthMessage() || this.result.authMessage_ == Authentication.getDefaultInstance()) {
                    requestMessage = this.result;
                } else {
                    requestMessage = this.result;
                    authentication = Authentication.newBuilder(requestMessage.authMessage_).mergeFrom(authentication).buildPartial();
                }
                requestMessage.authMessage_ = authentication;
                this.result.hasAuthMessage = true;
                return this;
            }

            public a mergeDataMessage(Data data) {
                RequestMessage requestMessage;
                if (!this.result.hasDataMessage() || this.result.dataMessage_ == Data.getDefaultInstance()) {
                    requestMessage = this.result;
                } else {
                    requestMessage = this.result;
                    data = Data.newBuilder(requestMessage.dataMessage_).mergeFrom(data).buildPartial();
                }
                requestMessage.dataMessage_ = data;
                this.result.hasDataMessage = true;
                return this;
            }

            public a mergeDemandMessage(Demand demand) {
                RequestMessage requestMessage;
                if (!this.result.hasDemandMessage() || this.result.demandMessage_ == Demand.getDefaultInstance()) {
                    requestMessage = this.result;
                } else {
                    requestMessage = this.result;
                    demand = Demand.newBuilder(requestMessage.demandMessage_).mergeFrom(demand).buildPartial();
                }
                requestMessage.demandMessage_ = demand;
                this.result.hasDemandMessage = true;
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public a mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 8) {
                        int n = codedInputStream.n();
                        RequestType valueOf = RequestType.valueOf(n);
                        if (valueOf == null) {
                            newBuilder.mergeVarintField(1, n);
                        } else {
                            setType(valueOf);
                        }
                    } else if (a == 18) {
                        Authentication.a newBuilder2 = Authentication.newBuilder();
                        if (hasAuthMessage()) {
                            newBuilder2.mergeFrom(getAuthMessage());
                        }
                        codedInputStream.a(newBuilder2, extensionRegistryLite);
                        setAuthMessage(newBuilder2.buildPartial());
                    } else if (a == 26) {
                        Data.a newBuilder3 = Data.newBuilder();
                        if (hasDataMessage()) {
                            newBuilder3.mergeFrom(getDataMessage());
                        }
                        codedInputStream.a(newBuilder3, extensionRegistryLite);
                        setDataMessage(newBuilder3.buildPartial());
                    } else if (a == 34) {
                        Demand.a newBuilder4 = Demand.newBuilder();
                        if (hasDemandMessage()) {
                            newBuilder4.mergeFrom(getDemandMessage());
                        }
                        codedInputStream.a(newBuilder4, extensionRegistryLite);
                        setDemandMessage(newBuilder4.buildPartial());
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public a mergeFrom(Message message) {
                if (message instanceof RequestMessage) {
                    return mergeFrom((RequestMessage) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public a mergeFrom(RequestMessage requestMessage) {
                if (requestMessage == RequestMessage.getDefaultInstance()) {
                    return this;
                }
                if (requestMessage.hasType()) {
                    setType(requestMessage.getType());
                }
                if (requestMessage.hasAuthMessage()) {
                    mergeAuthMessage(requestMessage.getAuthMessage());
                }
                if (requestMessage.hasDataMessage()) {
                    mergeDataMessage(requestMessage.getDataMessage());
                }
                if (requestMessage.hasDemandMessage()) {
                    mergeDemandMessage(requestMessage.getDemandMessage());
                }
                mergeUnknownFields(requestMessage.getUnknownFields());
                return this;
            }

            public a setAuthMessage(Authentication.a aVar) {
                this.result.hasAuthMessage = true;
                this.result.authMessage_ = aVar.build();
                return this;
            }

            public a setAuthMessage(Authentication authentication) {
                if (authentication != null) {
                    this.result.hasAuthMessage = true;
                    this.result.authMessage_ = authentication;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setDataMessage(Data.a aVar) {
                this.result.hasDataMessage = true;
                this.result.dataMessage_ = aVar.build();
                return this;
            }

            public a setDataMessage(Data data) {
                if (data != null) {
                    this.result.hasDataMessage = true;
                    this.result.dataMessage_ = data;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setDemandMessage(Demand.a aVar) {
                this.result.hasDemandMessage = true;
                this.result.demandMessage_ = aVar.build();
                return this;
            }

            public a setDemandMessage(Demand demand) {
                if (demand != null) {
                    this.result.hasDemandMessage = true;
                    this.result.demandMessage_ = demand;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setType(RequestType requestType) {
                if (requestType != null) {
                    this.result.hasType = true;
                    this.result.type_ = requestType;
                    return this;
                }
                throw new NullPointerException();
            }
        }

        static {
            Roap.b();
            defaultInstance.initFields();
        }

        private RequestMessage() {
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private RequestMessage(boolean z) {
            this.memoizedSerializedSize = -1;
        }

        public static RequestMessage getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Roap.c;
        }

        private void initFields() {
            this.type_ = RequestType.UNKNOWN;
            this.authMessage_ = Authentication.getDefaultInstance();
            this.dataMessage_ = Data.getDefaultInstance();
            this.demandMessage_ = Demand.getDefaultInstance();
        }

        public static a newBuilder() {
            return a.access$2000();
        }

        public static a newBuilder(RequestMessage requestMessage) {
            return newBuilder().mergeFrom(requestMessage);
        }

        public static RequestMessage parseDelimitedFrom(InputStream inputStream) {
            a newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static RequestMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            a newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static RequestMessage parseFrom(ByteString byteString) {
            return ((a) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static RequestMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static RequestMessage parseFrom(CodedInputStream codedInputStream) {
            return ((a) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static RequestMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static RequestMessage parseFrom(InputStream inputStream) {
            return ((a) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static RequestMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static RequestMessage parseFrom(byte[] bArr) {
            return ((a) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static RequestMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        public Authentication getAuthMessage() {
            return this.authMessage_;
        }

        public Data getDataMessage() {
            return this.dataMessage_;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public RequestMessage getDefaultInstanceForType() {
            return defaultInstance;
        }

        public Demand getDemandMessage() {
            return this.demandMessage_;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int j = hasType() ? 0 + CodedOutputStream.j(1, getType().getNumber()) : 0;
            if (hasAuthMessage()) {
                j += CodedOutputStream.g(2, getAuthMessage());
            }
            if (hasDataMessage()) {
                j += CodedOutputStream.g(3, getDataMessage());
            }
            if (hasDemandMessage()) {
                j += CodedOutputStream.g(4, getDemandMessage());
            }
            int serializedSize = j + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public RequestType getType() {
            return this.type_;
        }

        public boolean hasAuthMessage() {
            return this.hasAuthMessage;
        }

        public boolean hasDataMessage() {
            return this.hasDataMessage;
        }

        public boolean hasDemandMessage() {
            return this.hasDemandMessage;
        }

        public boolean hasType() {
            return this.hasType;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Roap.d;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            if (this.hasType) {
                if (!hasAuthMessage() || getAuthMessage().isInitialized()) {
                    if (!hasDataMessage() || getDataMessage().isInitialized()) {
                        return !hasDemandMessage() || getDemandMessage().isInitialized();
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public a newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public a toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            if (hasType()) {
                codedOutputStream.d(1, getType().getNumber());
            }
            if (hasAuthMessage()) {
                codedOutputStream.c(2, getAuthMessage());
            }
            if (hasDataMessage()) {
                codedOutputStream.c(3, getDataMessage());
            }
            if (hasDemandMessage()) {
                codedOutputStream.c(4, getDemandMessage());
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class ResponseMessage extends GeneratedMessage {
        public static final int AUTH_MESSAGE_FIELD_NUMBER = 2;
        public static final int DATA_MESSAGE_FIELD_NUMBER = 3;
        public static final int DEMAND_MESSAGE_FIELD_NUMBER = 4;
        public static final int TYPE_FIELD_NUMBER = 1;
        private static final ResponseMessage defaultInstance = new ResponseMessage(true);
        private Authentication authMessage_;
        private Data dataMessage_;
        private Demand demandMessage_;
        private boolean hasAuthMessage;
        private boolean hasDataMessage;
        private boolean hasDemandMessage;
        private boolean hasType;
        private int memoizedSerializedSize;
        private ResponseType type_;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public enum ResponseType implements ProtocolMessageEnum {
            UNKNOWN(0, 0),
            AUTHENTICATION(1, 1),
            DATA(2, 2),
            DEMAND(3, 3);
            
            private final int index;
            private final int value;
            private static Internal.EnumLiteMap<ResponseType> internalValueMap = new Internal.EnumLiteMap<ResponseType>() { // from class: com.lge.UDAP.ROAP.Roap.ResponseMessage.ResponseType.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public ResponseType findValueByNumber(int i) {
                    return ResponseType.valueOf(i);
                }
            };
            private static final ResponseType[] VALUES = {UNKNOWN, AUTHENTICATION, DATA, DEMAND};

            static {
                Roap.a();
            }

            ResponseType(int i, int i2) {
                this.index = i;
                this.value = i2;
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return ResponseMessage.getDescriptor().getEnumTypes().get(0);
            }

            public static Internal.EnumLiteMap<ResponseType> internalGetValueMap() {
                return internalValueMap;
            }

            public static ResponseType valueOf(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                return null;
                            }
                            return DEMAND;
                        }
                        return DATA;
                    }
                    return AUTHENTICATION;
                }
                return UNKNOWN;
            }

            public static ResponseType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
        public static final class a extends GeneratedMessage.Builder<a> {
            private ResponseMessage result;

            private a() {
            }

            static /* synthetic */ a access$3300() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public ResponseMessage buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static a create() {
                a aVar = new a();
                aVar.result = new ResponseMessage();
                return aVar;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ResponseMessage build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ResponseMessage buildPartial() {
                ResponseMessage responseMessage = this.result;
                if (responseMessage != null) {
                    this.result = null;
                    return responseMessage;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public a clear() {
                if (this.result != null) {
                    this.result = new ResponseMessage();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public a clearAuthMessage() {
                this.result.hasAuthMessage = false;
                this.result.authMessage_ = Authentication.getDefaultInstance();
                return this;
            }

            public a clearDataMessage() {
                this.result.hasDataMessage = false;
                this.result.dataMessage_ = Data.getDefaultInstance();
                return this;
            }

            public a clearDemandMessage() {
                this.result.hasDemandMessage = false;
                this.result.demandMessage_ = Demand.getDefaultInstance();
                return this;
            }

            public a clearType() {
                this.result.hasType = false;
                this.result.type_ = ResponseType.UNKNOWN;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public a mo3clone() {
                return create().mergeFrom(this.result);
            }

            public Authentication getAuthMessage() {
                return this.result.getAuthMessage();
            }

            public Data getDataMessage() {
                return this.result.getDataMessage();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ResponseMessage getDefaultInstanceForType() {
                return ResponseMessage.getDefaultInstance();
            }

            public Demand getDemandMessage() {
                return this.result.getDemandMessage();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return ResponseMessage.getDescriptor();
            }

            public ResponseType getType() {
                return this.result.getType();
            }

            public boolean hasAuthMessage() {
                return this.result.hasAuthMessage();
            }

            public boolean hasDataMessage() {
                return this.result.hasDataMessage();
            }

            public boolean hasDemandMessage() {
                return this.result.hasDemandMessage();
            }

            public boolean hasType() {
                return this.result.hasType();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.Builder
            public ResponseMessage internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            public a mergeAuthMessage(Authentication authentication) {
                ResponseMessage responseMessage;
                if (!this.result.hasAuthMessage() || this.result.authMessage_ == Authentication.getDefaultInstance()) {
                    responseMessage = this.result;
                } else {
                    responseMessage = this.result;
                    authentication = Authentication.newBuilder(responseMessage.authMessage_).mergeFrom(authentication).buildPartial();
                }
                responseMessage.authMessage_ = authentication;
                this.result.hasAuthMessage = true;
                return this;
            }

            public a mergeDataMessage(Data data) {
                ResponseMessage responseMessage;
                if (!this.result.hasDataMessage() || this.result.dataMessage_ == Data.getDefaultInstance()) {
                    responseMessage = this.result;
                } else {
                    responseMessage = this.result;
                    data = Data.newBuilder(responseMessage.dataMessage_).mergeFrom(data).buildPartial();
                }
                responseMessage.dataMessage_ = data;
                this.result.hasDataMessage = true;
                return this;
            }

            public a mergeDemandMessage(Demand demand) {
                ResponseMessage responseMessage;
                if (!this.result.hasDemandMessage() || this.result.demandMessage_ == Demand.getDefaultInstance()) {
                    responseMessage = this.result;
                } else {
                    responseMessage = this.result;
                    demand = Demand.newBuilder(responseMessage.demandMessage_).mergeFrom(demand).buildPartial();
                }
                responseMessage.demandMessage_ = demand;
                this.result.hasDemandMessage = true;
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public a mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 8) {
                        int n = codedInputStream.n();
                        ResponseType valueOf = ResponseType.valueOf(n);
                        if (valueOf == null) {
                            newBuilder.mergeVarintField(1, n);
                        } else {
                            setType(valueOf);
                        }
                    } else if (a == 18) {
                        Authentication.a newBuilder2 = Authentication.newBuilder();
                        if (hasAuthMessage()) {
                            newBuilder2.mergeFrom(getAuthMessage());
                        }
                        codedInputStream.a(newBuilder2, extensionRegistryLite);
                        setAuthMessage(newBuilder2.buildPartial());
                    } else if (a == 26) {
                        Data.a newBuilder3 = Data.newBuilder();
                        if (hasDataMessage()) {
                            newBuilder3.mergeFrom(getDataMessage());
                        }
                        codedInputStream.a(newBuilder3, extensionRegistryLite);
                        setDataMessage(newBuilder3.buildPartial());
                    } else if (a == 34) {
                        Demand.a newBuilder4 = Demand.newBuilder();
                        if (hasDemandMessage()) {
                            newBuilder4.mergeFrom(getDemandMessage());
                        }
                        codedInputStream.a(newBuilder4, extensionRegistryLite);
                        setDemandMessage(newBuilder4.buildPartial());
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public a mergeFrom(Message message) {
                if (message instanceof ResponseMessage) {
                    return mergeFrom((ResponseMessage) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public a mergeFrom(ResponseMessage responseMessage) {
                if (responseMessage == ResponseMessage.getDefaultInstance()) {
                    return this;
                }
                if (responseMessage.hasType()) {
                    setType(responseMessage.getType());
                }
                if (responseMessage.hasAuthMessage()) {
                    mergeAuthMessage(responseMessage.getAuthMessage());
                }
                if (responseMessage.hasDataMessage()) {
                    mergeDataMessage(responseMessage.getDataMessage());
                }
                if (responseMessage.hasDemandMessage()) {
                    mergeDemandMessage(responseMessage.getDemandMessage());
                }
                mergeUnknownFields(responseMessage.getUnknownFields());
                return this;
            }

            public a setAuthMessage(Authentication.a aVar) {
                this.result.hasAuthMessage = true;
                this.result.authMessage_ = aVar.build();
                return this;
            }

            public a setAuthMessage(Authentication authentication) {
                if (authentication != null) {
                    this.result.hasAuthMessage = true;
                    this.result.authMessage_ = authentication;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setDataMessage(Data.a aVar) {
                this.result.hasDataMessage = true;
                this.result.dataMessage_ = aVar.build();
                return this;
            }

            public a setDataMessage(Data data) {
                if (data != null) {
                    this.result.hasDataMessage = true;
                    this.result.dataMessage_ = data;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setDemandMessage(Demand.a aVar) {
                this.result.hasDemandMessage = true;
                this.result.demandMessage_ = aVar.build();
                return this;
            }

            public a setDemandMessage(Demand demand) {
                if (demand != null) {
                    this.result.hasDemandMessage = true;
                    this.result.demandMessage_ = demand;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setType(ResponseType responseType) {
                if (responseType != null) {
                    this.result.hasType = true;
                    this.result.type_ = responseType;
                    return this;
                }
                throw new NullPointerException();
            }
        }

        static {
            Roap.b();
            defaultInstance.initFields();
        }

        private ResponseMessage() {
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private ResponseMessage(boolean z) {
            this.memoizedSerializedSize = -1;
        }

        public static ResponseMessage getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Roap.e;
        }

        private void initFields() {
            this.type_ = ResponseType.UNKNOWN;
            this.authMessage_ = Authentication.getDefaultInstance();
            this.dataMessage_ = Data.getDefaultInstance();
            this.demandMessage_ = Demand.getDefaultInstance();
        }

        public static a newBuilder() {
            return a.access$3300();
        }

        public static a newBuilder(ResponseMessage responseMessage) {
            return newBuilder().mergeFrom(responseMessage);
        }

        public static ResponseMessage parseDelimitedFrom(InputStream inputStream) {
            a newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static ResponseMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            a newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static ResponseMessage parseFrom(ByteString byteString) {
            return ((a) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static ResponseMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static ResponseMessage parseFrom(CodedInputStream codedInputStream) {
            return ((a) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static ResponseMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static ResponseMessage parseFrom(InputStream inputStream) {
            return ((a) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static ResponseMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static ResponseMessage parseFrom(byte[] bArr) {
            return ((a) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static ResponseMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        public Authentication getAuthMessage() {
            return this.authMessage_;
        }

        public Data getDataMessage() {
            return this.dataMessage_;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public ResponseMessage getDefaultInstanceForType() {
            return defaultInstance;
        }

        public Demand getDemandMessage() {
            return this.demandMessage_;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int j = hasType() ? 0 + CodedOutputStream.j(1, getType().getNumber()) : 0;
            if (hasAuthMessage()) {
                j += CodedOutputStream.g(2, getAuthMessage());
            }
            if (hasDataMessage()) {
                j += CodedOutputStream.g(3, getDataMessage());
            }
            if (hasDemandMessage()) {
                j += CodedOutputStream.g(4, getDemandMessage());
            }
            int serializedSize = j + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public ResponseType getType() {
            return this.type_;
        }

        public boolean hasAuthMessage() {
            return this.hasAuthMessage;
        }

        public boolean hasDataMessage() {
            return this.hasDataMessage;
        }

        public boolean hasDemandMessage() {
            return this.hasDemandMessage;
        }

        public boolean hasType() {
            return this.hasType;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Roap.f;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            if (this.hasType) {
                if (!hasAuthMessage() || getAuthMessage().isInitialized()) {
                    if (!hasDataMessage() || getDataMessage().isInitialized()) {
                        return !hasDemandMessage() || getDemandMessage().isInitialized();
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public a newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public a toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            if (hasType()) {
                codedOutputStream.d(1, getType().getNumber());
            }
            if (hasAuthMessage()) {
                codedOutputStream.c(2, getAuthMessage());
            }
            if (hasDataMessage()) {
                codedOutputStream.c(3, getDataMessage());
            }
            if (hasDemandMessage()) {
                codedOutputStream.c(4, getDemandMessage());
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class Touch extends GeneratedMessage {
        public static final int BDATA_FIELD_NUMBER = 6;
        public static final int RATIO_FIELD_NUMBER = 5;
        public static final int TYPE_FIELD_NUMBER = 1;
        public static final int XDELTA_FIELD_NUMBER = 2;
        public static final int YDELTA_FIELD_NUMBER = 3;
        public static final int ZDELTA_FIELD_NUMBER = 4;
        private static final Touch defaultInstance = new Touch(true);
        private boolean bData_;
        private boolean hasBData;
        private boolean hasRatio;
        private boolean hasType;
        private boolean hasXDelta;
        private boolean hasYDelta;
        private boolean hasZDelta;
        private int memoizedSerializedSize;
        private int ratio_;
        private TouchType type_;
        private int xDelta_;
        private int yDelta_;
        private int zDelta_;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public enum TouchType implements ProtocolMessageEnum {
            UNKNOWN(0, 0),
            MOVE(1, 1),
            WHEEL(2, 2),
            ZOOM(3, 3),
            CLICK(4, 4),
            DRAG(5, 5);
            
            private final int index;
            private final int value;
            private static Internal.EnumLiteMap<TouchType> internalValueMap = new Internal.EnumLiteMap<TouchType>() { // from class: com.lge.UDAP.ROAP.Roap.Touch.TouchType.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public TouchType findValueByNumber(int i) {
                    return TouchType.valueOf(i);
                }
            };
            private static final TouchType[] VALUES = {UNKNOWN, MOVE, WHEEL, ZOOM, CLICK, DRAG};

            static {
                Roap.a();
            }

            TouchType(int i, int i2) {
                this.index = i;
                this.value = i2;
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return Touch.getDescriptor().getEnumTypes().get(0);
            }

            public static Internal.EnumLiteMap<TouchType> internalGetValueMap() {
                return internalValueMap;
            }

            public static TouchType valueOf(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                if (i != 4) {
                                    if (i != 5) {
                                        return null;
                                    }
                                    return DRAG;
                                }
                                return CLICK;
                            }
                            return ZOOM;
                        }
                        return WHEEL;
                    }
                    return MOVE;
                }
                return UNKNOWN;
            }

            public static TouchType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
        public static final class a extends GeneratedMessage.Builder<a> {
            private Touch result;

            private a() {
            }

            static /* synthetic */ a access$11400() {
                return create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public Touch buildParsed() {
                if (isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result).b();
            }

            private static a create() {
                a aVar = new a();
                aVar.result = new Touch();
                return aVar;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Touch build() {
                if (this.result == null || isInitialized()) {
                    return buildPartial();
                }
                throw newUninitializedMessageException((Message) this.result);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Touch buildPartial() {
                Touch touch = this.result;
                if (touch != null) {
                    this.result = null;
                    return touch;
                }
                throw new IllegalStateException("build() has already been called on this Builder.");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public a clear() {
                if (this.result != null) {
                    this.result = new Touch();
                    return this;
                }
                throw new IllegalStateException("Cannot call clear() after build().");
            }

            public a clearBData() {
                this.result.hasBData = false;
                this.result.bData_ = false;
                return this;
            }

            public a clearRatio() {
                this.result.hasRatio = false;
                this.result.ratio_ = 0;
                return this;
            }

            public a clearType() {
                this.result.hasType = false;
                this.result.type_ = TouchType.UNKNOWN;
                return this;
            }

            public a clearXDelta() {
                this.result.hasXDelta = false;
                this.result.xDelta_ = 0;
                return this;
            }

            public a clearYDelta() {
                this.result.hasYDelta = false;
                this.result.yDelta_ = 0;
                return this;
            }

            public a clearZDelta() {
                this.result.hasZDelta = false;
                this.result.zDelta_ = 0;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public a mo3clone() {
                return create().mergeFrom(this.result);
            }

            public boolean getBData() {
                return this.result.getBData();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Touch getDefaultInstanceForType() {
                return Touch.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder
            public Descriptors.Descriptor getDescriptorForType() {
                return Touch.getDescriptor();
            }

            public int getRatio() {
                return this.result.getRatio();
            }

            public TouchType getType() {
                return this.result.getType();
            }

            public int getXDelta() {
                return this.result.getXDelta();
            }

            public int getYDelta() {
                return this.result.getYDelta();
            }

            public int getZDelta() {
                return this.result.getZDelta();
            }

            public boolean hasBData() {
                return this.result.hasBData();
            }

            public boolean hasRatio() {
                return this.result.hasRatio();
            }

            public boolean hasType() {
                return this.result.hasType();
            }

            public boolean hasXDelta() {
                return this.result.hasXDelta();
            }

            public boolean hasYDelta() {
                return this.result.hasYDelta();
            }

            public boolean hasZDelta() {
                return this.result.hasZDelta();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.Builder
            public Touch internalGetResult() {
                return this.result;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLite.Builder
            public boolean isInitialized() {
                return this.result.isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public a mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
                while (true) {
                    int a = codedInputStream.a();
                    if (a == 0) {
                        break;
                    } else if (a == 8) {
                        int n = codedInputStream.n();
                        TouchType valueOf = TouchType.valueOf(n);
                        if (valueOf == null) {
                            newBuilder.mergeVarintField(1, n);
                        } else {
                            setType(valueOf);
                        }
                    } else if (a == 16) {
                        setXDelta(codedInputStream.g());
                    } else if (a == 24) {
                        setYDelta(codedInputStream.g());
                    } else if (a == 32) {
                        setZDelta(codedInputStream.g());
                    } else if (a == 40) {
                        setRatio(codedInputStream.g());
                    } else if (a == 48) {
                        setBData(codedInputStream.j());
                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, a)) {
                        break;
                    }
                }
                setUnknownFields(newBuilder.build());
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public a mergeFrom(Message message) {
                if (message instanceof Touch) {
                    return mergeFrom((Touch) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public a mergeFrom(Touch touch) {
                if (touch == Touch.getDefaultInstance()) {
                    return this;
                }
                if (touch.hasType()) {
                    setType(touch.getType());
                }
                if (touch.hasXDelta()) {
                    setXDelta(touch.getXDelta());
                }
                if (touch.hasYDelta()) {
                    setYDelta(touch.getYDelta());
                }
                if (touch.hasZDelta()) {
                    setZDelta(touch.getZDelta());
                }
                if (touch.hasRatio()) {
                    setRatio(touch.getRatio());
                }
                if (touch.hasBData()) {
                    setBData(touch.getBData());
                }
                mergeUnknownFields(touch.getUnknownFields());
                return this;
            }

            public a setBData(boolean z) {
                this.result.hasBData = true;
                this.result.bData_ = z;
                return this;
            }

            public a setRatio(int i) {
                this.result.hasRatio = true;
                this.result.ratio_ = i;
                return this;
            }

            public a setType(TouchType touchType) {
                if (touchType != null) {
                    this.result.hasType = true;
                    this.result.type_ = touchType;
                    return this;
                }
                throw new NullPointerException();
            }

            public a setXDelta(int i) {
                this.result.hasXDelta = true;
                this.result.xDelta_ = i;
                return this;
            }

            public a setYDelta(int i) {
                this.result.hasYDelta = true;
                this.result.yDelta_ = i;
                return this;
            }

            public a setZDelta(int i) {
                this.result.hasZDelta = true;
                this.result.zDelta_ = i;
                return this;
            }
        }

        static {
            Roap.b();
            defaultInstance.initFields();
        }

        private Touch() {
            this.xDelta_ = 0;
            this.yDelta_ = 0;
            this.zDelta_ = 0;
            this.ratio_ = 0;
            this.bData_ = false;
            this.memoizedSerializedSize = -1;
            initFields();
        }

        private Touch(boolean z) {
            this.xDelta_ = 0;
            this.yDelta_ = 0;
            this.zDelta_ = 0;
            this.ratio_ = 0;
            this.bData_ = false;
            this.memoizedSerializedSize = -1;
        }

        public static Touch getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Roap.o;
        }

        private void initFields() {
            this.type_ = TouchType.UNKNOWN;
        }

        public static a newBuilder() {
            return a.access$11400();
        }

        public static a newBuilder(Touch touch) {
            return newBuilder().mergeFrom(touch);
        }

        public static Touch parseDelimitedFrom(InputStream inputStream) {
            a newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static Touch parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            a newBuilder = newBuilder();
            if (newBuilder.mergeDelimitedFrom(inputStream, extensionRegistryLite)) {
                return newBuilder.buildParsed();
            }
            return null;
        }

        public static Touch parseFrom(ByteString byteString) {
            return ((a) newBuilder().mergeFrom(byteString)).buildParsed();
        }

        public static Touch parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(byteString, extensionRegistryLite)).buildParsed();
        }

        public static Touch parseFrom(CodedInputStream codedInputStream) {
            return ((a) newBuilder().mergeFrom(codedInputStream)).buildParsed();
        }

        public static Touch parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return newBuilder().mergeFrom(codedInputStream, extensionRegistryLite).buildParsed();
        }

        public static Touch parseFrom(InputStream inputStream) {
            return ((a) newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public static Touch parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(inputStream, extensionRegistryLite)).buildParsed();
        }

        public static Touch parseFrom(byte[] bArr) {
            return ((a) newBuilder().mergeFrom(bArr)).buildParsed();
        }

        public static Touch parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return ((a) newBuilder().mergeFrom(bArr, extensionRegistryLite)).buildParsed();
        }

        public boolean getBData() {
            return this.bData_;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Touch getDefaultInstanceForType() {
            return defaultInstance;
        }

        public int getRatio() {
            return this.ratio_;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int j = hasType() ? 0 + CodedOutputStream.j(1, getType().getNumber()) : 0;
            if (hasXDelta()) {
                j += CodedOutputStream.g(2, getXDelta());
            }
            if (hasYDelta()) {
                j += CodedOutputStream.g(3, getYDelta());
            }
            if (hasZDelta()) {
                j += CodedOutputStream.g(4, getZDelta());
            }
            if (hasRatio()) {
                j += CodedOutputStream.g(5, getRatio());
            }
            if (hasBData()) {
                j += CodedOutputStream.b(6, getBData());
            }
            int serializedSize = j + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public TouchType getType() {
            return this.type_;
        }

        public int getXDelta() {
            return this.xDelta_;
        }

        public int getYDelta() {
            return this.yDelta_;
        }

        public int getZDelta() {
            return this.zDelta_;
        }

        public boolean hasBData() {
            return this.hasBData;
        }

        public boolean hasRatio() {
            return this.hasRatio;
        }

        public boolean hasType() {
            return this.hasType;
        }

        public boolean hasXDelta() {
            return this.hasXDelta;
        }

        public boolean hasYDelta() {
            return this.hasYDelta;
        }

        public boolean hasZDelta() {
            return this.hasZDelta;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Roap.p;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final boolean isInitialized() {
            return this.hasType;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public a newBuilderForType() {
            return newBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public a toBuilder() {
            return newBuilder(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            if (hasType()) {
                codedOutputStream.d(1, getType().getNumber());
            }
            if (hasXDelta()) {
                codedOutputStream.a(2, getXDelta());
            }
            if (hasYDelta()) {
                codedOutputStream.a(3, getYDelta());
            }
            if (hasZDelta()) {
                codedOutputStream.a(4, getZDelta());
            }
            if (hasRatio()) {
                codedOutputStream.a(5, getRatio());
            }
            if (hasBData()) {
                codedOutputStream.a(6, getBData());
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    static {
        Descriptors.FileDescriptor.a(new String[]{"\n\nroap.proto\"±\u0002\n\u000bROAPMessage\u0012&\n\u0004type\u0018\u0001 \u0002(\u000e2\u0018.ROAPMessage.MessageType\u0012\u0012\n\nsession_id\u0018\u0002 \u0001(\t\u0012(\n\u000frequest_message\u0018\u0003 \u0001(\u000b2\u000f.RequestMessage\u0012*\n\u0010response_message\u0018\u0004 \u0001(\u000b2\u0010.ResponseMessage\u0012(\n\u000fcommand_message\u0018\u0005 \u0001(\u000b2\u000f.CommandMessage\u0012$\n\revent_message\u0018\u0006 \u0001(\u000b2\r.EventMessage\"@\n\u000bMessageType\u0012\u000b\n\u0007REQUEST\u0010\u0001\u0012\f\n\bRESPONSE\u0010\u0002\u0012\u000b\n\u0007COMMAND\u0010\u0003\u0012\t\n\u0005EVENT\u0010\u0004\"æ\u0001\n\u000eRequestMessage\u0012)\n\u0004type\u0018\u0001 \u0002(\u000e2\u001b.RequestMessage.RequestType\u0012%\n\fauth_message\u0018\u0002", " \u0001(\u000b2\u000f.Authentication\u0012\u001b\n\fdata_message\u0018\u0003 \u0001(\u000b2\u0005.Data\u0012\u001f\n\u000edemand_message\u0018\u0004 \u0001(\u000b2\u0007.Demand\"D\n\u000bRequestType\u0012\u000b\n\u0007UNKNOWN\u0010\u0000\u0012\u0012\n\u000eAUTHENTICATION\u0010\u0001\u0012\b\n\u0004DATA\u0010\u0002\u0012\n\n\u0006DEMAND\u0010\u0003\"ê\u0001\n\u000fResponseMessage\u0012+\n\u0004type\u0018\u0001 \u0002(\u000e2\u001d.ResponseMessage.ResponseType\u0012%\n\fauth_message\u0018\u0002 \u0001(\u000b2\u000f.Authentication\u0012\u001b\n\fdata_message\u0018\u0003 \u0001(\u000b2\u0005.Data\u0012\u001f\n\u000edemand_message\u0018\u0004 \u0001(\u000b2\u0007.Demand\"E\n\fResponseType\u0012\u000b\n\u0007UNKNOWN\u0010\u0000\u0012\u0012\n\u000eAUTHENTICATION\u0010\u0001\u0012\b\n\u0004DATA\u0010\u0002\u0012\n\n\u0006DEMAND\u0010\u0003\"Ò\u0001\n\u000eComma", "ndMessage\u0012)\n\u0004type\u0018\u0001 \u0002(\u000e2\u001b.CommandMessage.CommandType\u0012\u0019\n\u000bkey_message\u0018\u0002 \u0001(\u000b2\u0004.Key\u0012\u001d\n\rtouch_message\u0018\u0003 \u0001(\u000b2\u0006.Touch\u0012\u001f\n\u000eaction_message\u0018\u0004 \u0001(\u000b2\u0007.Action\":\n\u000bCommandType\u0012\u000b\n\u0007UNKNOWN\u0010\u0000\u0012\u0007\n\u0003KEY\u0010\u0001\u0012\t\n\u0005TOUCH\u0010\u0002\u0012\n\n\u0006ACTION\u0010\u0003\"Â\u000f\n\fEventMessage\u0012%\n\u0004type\u0018\u0001 \u0002(\u000e2\u0017.EventMessage.EventType\u00123\n\u000estatus_message\u0018\u0002 \u0001(\u000b2\u001b.EventMessage.StatusMessage\u0012\r\n\u0005bData\u0018\u0003 \u0001(\b\u0012\u000e\n\u0006nData1\u0018\u0004 \u0001(\u0005\u0012\u000e\n\u0006nData2\u0018\u0005 \u0001(\u0005\u0012\u000f\n\u0007strData\u0018\u0006 \u0001(\t\u0012\u0010\n\bbyteData\u0018\b \u0001(\f\u0012\u0012\n\nby", "teLength\u0018\t \u0001(\u0005\u0012\u0017\n\u000fbyteTotalLength\u0018\n \u0001(\u0005\u001a\u009a\f\n\rStatusMessage\u0012:\n\nviewStatus\u0018\u0001 \u0001(\u000e2&.EventMessage.StatusMessage.ViewStatus\u0012B\n\u000eplaybackStatus\u0018\u0002 \u0001(\u000e2*.EventMessage.StatusMessage.PlaybackStatus\u0012@\n\rnetworkStatus\u0018\u0003 \u0001(\u000e2).EventMessage.StatusMessage.NetworkStatus\u00128\n\tusbStatus\u0018\u0004 \u0001(\u000e2%.EventMessage.StatusMessage.UsbStatus\u00126\n\bspStatus\u0018\u0005 \u0001(\u000e2$.EventMessage.StatusMessage.SpStatus\u0012:\n\nsyncStatus\u0018\u0006 \u0001(\u000e2&.EventMessage", ".StatusMessage.SyncStatus\u0012@\n\rbgmInfoStatus\u0018\u0007 \u0001(\u000e2).EventMessage.StatusMessage.BgmInfoStatus\u00126\n\bcpStatus\u0018\b \u0001(\u000e2$.EventMessage.StatusMessage.CpStatus\u0012>\n\fextspkStatus\u0018\t \u0001(\u000e2(.EventMessage.StatusMessage.EXTSPKStatus\"3\n\nViewStatus\u0012\u0011\n\rVIEW_TOUCHPAD\u0010\u0001\u0012\u0012\n\u000eVIEW_TEXTINPUT\u0010\u0002\"Ó\u0001\n\u000ePlaybackStatus\u0012\u0014\n\u0010PLAYBACK_UNKNOWN\u0010\u0000\u0012\u0012\n\u000ePLAYBACK_ERROR\u0010\u0001\u0012\u0011\n\rPLAYBACK_PLAY\u0010\u0002\u0012\u0011\n\rPLAYBACK_STOP\u0010\u0003\u0012\u0012\n\u000ePLAYBACK_PAUSE\u0010\u0004\u0012\u0015\n\u0011PLAYBACK_FAST", "_FWD\u0010\u0005\u0012\u0015\n\u0011PLAYBACK_FAST_BWD\u0010\u0006\u0012\u0015\n\u0011PLAYBACK_SLOW_FWD\u0010\u0007\u0012\u0018\n\u0014PLAYBACK_NOW_PLAYING\u0010\b\"¤\u0001\n\rNetworkStatus\u0012\u0013\n\u000fNETWORK_UNKNOWN\u0010\u0000\u0012\u001b\n\u0017NETWORK_WIRED_CONNECTED\u0010\u0001\u0012\u001e\n\u001aNETWORK_WIRED_DISCONNECTED\u0010\u0002\u0012\u001e\n\u001aNETWORK_WIRELESS_CONNECTED\u0010\u0003\u0012!\n\u001dNETWORK_WIRELESS_DISCONNECTED\u0010\u0004\"E\n\tUsbStatus\u0012\u000f\n\u000bUSB_UNKNOWN\u0010\u0000\u0012\u0011\n\rUSB_CONNECTED\u0010\u0001\u0012\u0014\n\u0010USB_DISCONNECTED\u0010\u0002\"\u0086\u0001\n\bSpStatus\u0012\u000e\n\nSP_UNKNOWN\u0010\u0000\u0012\u000f\n\u000bSP_SOUND_ON\u0010\u0001\u0012\u0010\n\fSP_SOUND_OFF\u0010\u0002\u0012\u0018\n\u0014SP_SAMPLERATE_CH", "ANGE\u0010\u0003\u0012\u000b\n\u0007SP_STOP\u0010\u0004\u0012\u0012\n\u000eSP_NOT_SUPPORT\u0010\u0005\u0012\f\n\bSP_ALIVE\u0010\u0006\"Q\n\nSyncStatus\u0012\u000e\n\nSS_UNKNOWN\u0010\u0000\u0012\u0012\n\u000eSS_VIEW_CHANGE\u0010\u0001\u0012\u000e\n\nSS_ENABLED\u0010\u0002\u0012\u000f\n\u000bSS_DISABLED\u0010\u0003\"t\n\rBgmInfoStatus\u0012\u0014\n\u0010BGM_INFO_UNKNOWN\u0010\u0000\u0012\u000f\n\u000bBGM_INFO_OK\u0010\u0001\u0012\u0011\n\rBGM_INFO_FAIL\u0010\u0002\u0012\u0014\n\u0010BGM_INFO_LOADING\u0010\u0003\u0012\u0013\n\u000fBGM_INFO_CANCEL\u0010\u0004\"X\n\bCpStatus\u0012\u000e\n\nCP_UNKNOWN\u0010\u0000\u0012\u0012\n\u000eCP_LIST_CHANGE\u0010\u0001\u0012\u0014\n\u0010CP_LOADING_START\u0010\u0002\u0012\u0012\n\u000eCP_LOADING_END\u0010\u0003\":\n\fEXTSPKStatus\u0012\u0012\n\u000eEXTSPK_UNKNOWN\u0010\u0000\u0012\u0016\n\u0012EXTSPK_LIST_CHAN", "GE\u0010\u0001\"¹\u0001\n\tEventType\u0012\u000b\n\u0007UNKNOWN\u0010\u0000\u0012\u000f\n\u000bVIEW_STATUS\u0010\u0001\u0012\u0013\n\u000fPLAYBACK_STATUS\u0010\u0002\u0012\u0012\n\u000eNETWORK_STATUS\u0010\u0003\u0012\u000e\n\nUSB_STATUS\u0010\u0004\u0012\r\n\tSP_STATUS\u0010\u0005\u0012\u000f\n\u000bSYNC_STATUS\u0010\u0006\u0012\u0013\n\u000fBGM_INFO_STATUS\u0010\u0007\u0012\r\n\tCP_STATUS\u0010\b\u0012\u0011\n\rEXTSPK_STATUS\u0010\t\"®\u0013\n\u0003Key\u0012\u001a\n\u0004code\u0018\u0001 \u0002(\u000e2\f.Key.KeyCode\u0012\u001b\n\u0006action\u0018\u0002 \u0002(\u000e2\u000b.Key.Action\"Ä\u0012\n\u0007KeyCode\u0012\u0013\n\u000fKEYCODE_UNKNOWN\u0010\u0000\u0012\u0011\n\rKEYCODE_POWER\u0010\u0001\u0012\r\n\tKEYCODE_0\u0010\u0002\u0012\r\n\tKEYCODE_1\u0010\u0003\u0012\r\n\tKEYCODE_2\u0010\u0004\u0012\r\n\tKEYCODE_3\u0010\u0005\u0012\r\n\tKEYCODE_4\u0010\u0006\u0012\r\n\tKEYCODE_5\u0010\u0007\u0012", "\r\n\tKEYCODE_6\u0010\b\u0012\r\n\tKEYCODE_7\u0010\t\u0012\r\n\tKEYCODE_8\u0010\n\u0012\r\n\tKEYCODE_9\u0010\u000b\u0012\u000e\n\nKEYCODE_UP\u0010\f\u0012\u0010\n\fKEYCODE_DOWN\u0010\r\u0012\u0010\n\fKEYCODE_LEFT\u0010\u000e\u0012\u0011\n\rKEYCODE_RIGHT\u0010\u000f\u0012\u0013\n\u000fKEYCODE_UP_LEFT\u0010\u0010\u0012\u0014\n\u0010KEYCODE_UP_RIGHT\u0010\u0011\u0012\u0015\n\u0011KEYCODE_DOWN_LEFT\u0010\u0012\u0012\u0016\n\u0012KEYCODE_DOWN_RIGHT\u0010\u0013\u0012\u0011\n\rKEYCODE_ENTER\u0010\u0014\u0012\u0010\n\fKEYCODE_HOME\u0010\u0015\u0012\u0010\n\fKEYCODE_MENU\u0010\u0016\u0012\u0010\n\fKEYCODE_BACK\u0010\u0017\u0012\u0015\n\u0011KEYCODE_VOLUME_UP\u0010\u0018\u0012\u0017\n\u0013KEYCODE_VOLUME_DOWN\u0010\u0019\u0012\u0010\n\fKEYCODE_MUTE\u0010\u001a\u0012\u0016\n\u0012KEYCODE_CHANNEL_UP\u0010\u001b\u0012\u0018\n\u0014KEYCODE_CHANN", "EL_DOWN\u0010\u001c\u0012\u0010\n\fKEYCODE_BLUE\u0010\u001d\u0012\u0011\n\rKEYCODE_GREEN\u0010\u001e\u0012\u000f\n\u000bKEYCODE_RED\u0010\u001f\u0012\u0012\n\u000eKEYCODE_YELLOW\u0010 \u0012\u0010\n\fKEYCODE_PLAY\u0010!\u0012\u0011\n\rKEYCODE_PAUSE\u0010\"\u0012\u0010\n\fKEYCODE_STOP\u0010#\u0012\u0018\n\u0014KEYCODE_FAST_FORWARD\u0010$\u0012\u0019\n\u0015KEYCODE_FAST_BACKWARD\u0010%\u0012\u0018\n\u0014KEYCODE_SKIP_FORWARD\u0010&\u0012\u0019\n\u0015KEYCODE_SKIP_BACKWARD\u0010'\u0012\u0012\n\u000eKEYCODE_RECORD\u0010(\u0012\u0017\n\u0013KEYCODE_RECORD_LIST\u0010)\u0012\u0012\n\u000eKEYCODE_REPEAT\u0010*\u0012\u0010\n\fKEYCODE_LIVE\u0010+\u0012\u0011\n\rKEYCODE_GUIDE\u0010,\u0012\u0010\n\fKEYCODE_INFO\u0010-\u0012\u0016\n\u0012KEYCODE_RESOLUTION\u0010.\u0012\u0011\n\rKEYCODE_", "INPUT\u0010/\u0012\u0015\n\u0011KEYCODE_VIDEO_PIP\u00100\u0012\u0014\n\u0010KEYCODE_SUBTITLE\u00101\u0012\u0018\n\u0014KEYCODE_PROGRAM_LIST\u00102\u0012\u0010\n\fKEYCODE_TEXT\u00103\u0012\u0012\n\u000eKEYCODE_MARKER\u00104\u0012\u0010\n\fKEYCODE_NEXT\u00105\u0012\u0014\n\u0010KEYCODE_PREVIOUS\u00106\u0012\u0010\n\fKEYCODE_OPEN\u00107\u0012\u0011\n\rKEYCODE_CLOSE\u00108\u0012\u0016\n\u0012KEYCODE_REPEAT_A2B\u00109\u0012\u0016\n\u0012KEYCODE_PLAY_PAUSE\u0010:\u0012\u0012\n\u000eKEYCODE_RANDOM\u0010;\u0012\u0010\n\fKEYCODE_ZOOM\u0010<\u0012\u0012\n\u000eKEYCODE_SEARCH\u0010=\u0012\u0011\n\rKEYCODE_TITLE\u0010>\u0012\u0011\n\rKEYCODE_POPUP\u0010?\u0012\u0011\n\rKEYCODE_CLEAR\u0010@\u0012\u0011\n\rKEYCODE_SETUP\u0010A\u0012\u0015\n\u0011KEYCODE_DISC_MENU\u0010B\u0012\u0015", "\n\u0011KEYCODE_TIMER_REC\u0010C\u0012\u0013\n\u000fKEYCODE_PICTURE\u0010D\u0012\u0010\n\fKEYCODE_LOCK\u0010E\u0012\u0011\n\rKEYCODE_TUNER\u0010F\u0012\u0015\n\u0011KEYCODE_EQUALIZER\u0010G\u0012\u0011\n\rKEYCODE_SLEEP\u0010H\u0012\u0016\n\u0012KEYCODE_WOOFER_VOL\u0010I\u0012\u0011\n\rKEYCODE_NIGHT\u0010J\u0012\u0010\n\fKEYCODE_IPOD\u0010K\u0012\u0019\n\u0015KEYCODE_SPEAKER_SETUP\u0010L\u0012\u0013\n\u000fKEYCODE_OPTICAL\u0010M\u0012\u0019\n\u0015KEYCODE_MIC_VOLUME_UP\u0010N\u0012\u001b\n\u0017KEYCODE_MIC_VOLUME_DOWN\u0010O\u0012\u0014\n\u0010KEYCODE_MIC_ECHO\u0010P\u0012\u0018\n\u0014KEYCODE_CD_ARCHIVING\u0010Q\u0012\u0015\n\u0011KEYCODE_GRACENOTE\u0010R\u0012\u0015\n\u0011KEYCODE_AUDIO_PIP\u0010S\u0012\u001a\n\u0016KEYCODE_SUBTITLE", "_ONOFF\u0010T\u0012\u0011\n\rKEYCODE_AUDIO\u0010U\u0012\u0011\n\rKEYCODE_ANGLE\u0010V\u0012\u0014\n\u0010KEYCODE_3D_SOUND\u0010W\u0012\u0014\n\u0010KEYCODE_3D_VIDEO\u0010X\u0012\u0011\n\rKEYCODE_3D_LR\u0010Y\u0012\u0010\n\fKEYCODE_DASH\u0010Z\u0012\u001c\n\u0018KEYCODE_PREVIOUS_CHANNEL\u0010[\u0012\u001c\n\u0018KEYCODE_FAVORITE_CHANNEL\u0010\\\u0012\u0016\n\u0012KEYCODE_QUICK_MENU\u0010]\u0012\u0017\n\u0013KEYCODE_TEXT_OPTION\u0010^\u0012\u001d\n\u0019KEYCODE_AUDIO_DESCRIPTION\u0010_\u0012\u0013\n\u000fKEYCODE_NETCAST\u0010`\u0012\u0019\n\u0015KEYCODE_ENERGY_SAVING\u0010a\u0012\u0013\n\u000fKEYCODE_AV_MODE\u0010b\u0012\u0017\n\u0013KEYCODE_SIMPLE_LINK\u0010c\u0012\u0010\n\fKEYCODE_EXIT\u0010d\u0012\u001c\n\u0018KEYCODE_RESERVATI", "ON_LIST\u0010e\u0012\u001a\n\u0016KEYCODE_PIP_CHANNEL_UP\u0010f\u0012\u001c\n\u0018KEYCODE_PIP_CHANNEL_DOWN\u0010g\u0012\u0015\n\u0011KEYCODE_PIP_INPUT\u0010h\u0012\u001c\n\u0018KEYCODE_3D_SOUND_ZOOMING\u0010i\u0012\u0018\n\u0014KEYCODE_SOUND_EFFECT\u0010j\u0012\u0019\n\u0015KEYCODE_SPEAKER_LEVEL\u0010k\u0012\u0014\n\u0010KEYCODE_FUNCTION\u0010l\u0012\u000f\n\u000bKEYCODE_BGM\u0010m\u0012\u0012\n\u000eKEYCODE_2DTO3D\u0010n\"'\n\u0006Action\u0012\u000b\n\u0007UNKNOWN\u0010\u0000\u0012\u0006\n\u0002UP\u0010\u0001\u0012\b\n\u0004DOWN\u0010\u0002\"Ã\u0001\n\u0005Touch\u0012\u001e\n\u0004type\u0018\u0001 \u0002(\u000e2\u0010.Touch.TouchType\u0012\u000e\n\u0006xDelta\u0018\u0002 \u0001(\u0005\u0012\u000e\n\u0006yDelta\u0018\u0003 \u0001(\u0005\u0012\u000e\n\u0006zDelta\u0018\u0004 \u0001(\u0005\u0012\r\n\u0005ratio\u0018\u0005 \u0001(\u0005\u0012\r\n\u0005bData\u0018\u0006 \u0001(\b\"L\n\tTou", "chType\u0012\u000b\n\u0007UNKNOWN\u0010\u0000\u0012\b\n\u0004MOVE\u0010\u0001\u0012\t\n\u0005WHEEL\u0010\u0002\u0012\b\n\u0004ZOOM\u0010\u0003\u0012\t\n\u0005CLICK\u0010\u0004\u0012\b\n\u0004DRAG\u0010\u0005\"º\u0003\n\u0006Action\u0012 \n\u0004type\u0018\u0001 \u0002(\u000e2\u0012.Action.ActionType\u0012\r\n\u0005bData\u0018\u0002 \u0001(\b\u0012\r\n\u0005nData\u0018\u0003 \u0001(\u0005\u0012\u000f\n\u0007strData\u0018\u0004 \u0001(\t\"Þ\u0002\n\nActionType\u0012\u000b\n\u0007UNKNOWN\u0010\u0000\u0012\u000e\n\nTEXT_INPUT\u0010\u0001\u0012\u000f\n\u000bTEXT_SUBMIT\u0010\u0002\u0012\u000e\n\nAUTO_PAUSE\u0010\u0003\u0012\r\n\tTIME_JUMP\u0010\u0004\u0012\f\n\bSP_START\u0010\u0005\u0012\u000b\n\u0007SP_STOP\u0010\u0006\u0012\u0014\n\u0010SYNC_MEDIA_START\u0010\u0007\u0012\u000f\n\u000bSYNC_SELECT\u0010\b\u0012\u0016\n\u0012SYNC_FOLDER_CHANGE\u0010\t\u0012\u0015\n\u0011SYNC_MEDIA_CHANGE\u0010\n\u0012\u0016\n\u0012SYNC_DEVICE_CHANGE\u0010\u000b\u0012\u0012\n\u000eMUSI", "CID_CANCEL\u0010\f\u0012\u000e\n\nSP_PLAYING\u0010\r\u0012\u0012\n\u000eSYNC_MEDIA_CMD\u0010\u000e\u0012\u0014\n\u0010SYNC_PAGE_CHANGE\u0010\u000f\u0012\u0013\n\u000fSYNC_CP_EXECUTE\u0010\u0010\u0012\u0017\n\u0013SYNC_EXTSPK_EXECUTE\u0010\u0011\" \u0003\n\u000eAuthentication\u00120\n\u0004type\u0018\u0001 \u0002(\u000e2\".Authentication.AuthenticationType\u0012\u000f\n\u0007authKey\u0018\u0002 \u0001(\t\u0012.\n\nauthResult\u0018\u0003 \u0001(\u000e2\u001a.Authentication.AuthResult\"k\n\u0012AuthenticationType\u0012\u000b\n\u0007UNKNOWN\u0010\u0000\u0012\u0013\n\u000fAUTH_REQUST_KEY\u0010\u0001\u0012\u0011\n\rAUTH_SEND_KEY\u0010\u0002\u0012\u000f\n\u000bAUTH_RESULT\u0010\u0003\u0012\u000f\n\u000bAUTH_CANCEL\u0010\u0004\"\u00ad\u0001\n\nAuthResult\u0012\u0016\n\u0012AUTH_REQUST_KEY_OK\u0010\u0000\u0012\u0018", "\n\u0014AUTH_REQUST_KEY_FAIL\u0010\u0001\u0012\u0014\n\u0010AUTH_SEND_KEY_OK\u0010\u0002\u0012\u0016\n\u0012AUTH_SEND_KEY_FAIL\u0010\u0003\u0012\u0015\n\u0011AUTH_SESSION_FAIL\u0010\u0004\u0012\u0012\n\u000eAUTH_CANCEL_OK\u0010\u0005\u0012\u0014\n\u0010AUTH_CANCEL_FAIL\u0010\u0006\"°\u0001\n\u0004Data\u0012\u001c\n\u0004type\u0018\u0001 \u0002(\u000e2\u000e.Data.DataType\u0012\r\n\u0005bData\u0018\u0002 \u0001(\b\u0012\r\n\u0005nData\u0018\u0003 \u0001(\u0005\u0012\u000f\n\u0007strData\u0018\u0004 \u0001(\t\"[\n\bDataType\u0012\u000b\n\u0007UNKNOWN\u0010\u0000\u0012\u000e\n\nTEXT_LIMIT\u0010\u0001\u0012\f\n\bDMR_UUID\u0010\u0002\u0012\u0011\n\rDMR_AVAILABLE\u0010\u0003\u0012\u0011\n\rUSB_AVAILABLE\u0010\u0004\"\u0086\u0012\n\u0006Demand\u0012 \n\u0004type\u0018\u0001 \u0002(\u000e2\u0012.Demand.DemandType\u0012.\n\u000fsubtype_message\u0018\u0002 \u0001(\u000b2\u0015.Demand.Demand", "Subtype\u0012&\n\u000eresult_message\u0018\u0003 \u0001(\u000b2\u000e.Demand.Result\u0012\u0010\n\bsequence\u0018\u0004 \u0001(\u0005\u0012\r\n\u0005bData\u0018\u0005 \u0001(\b\u0012\r\n\u0005nData\u0018\u0006 \u0001(\u0005\u0012\u000f\n\u0007strData\u0018\u0007 \u0001(\t\u0012\u0010\n\bbyteData\u0018\b \u0001(\f\u0012\u0012\n\nbyteLength\u0018\t \u0001(\u0005\u0012\u0017\n\u000fbyteTotalLength\u0018\n \u0001(\u0005\u001a\u0082\u0006\n\rDemandSubtype\u00128\n\bdataType\u0018\u0001 \u0001(\u000e2&.Demand.DemandSubtype.ContentsDataType\u00128\n\binfoType\u0018\u0002 \u0001(\u000e2&.Demand.DemandSubtype.ContentsInfoType\u0012F\n\u000flinkedMediaType\u0018\u0003 \u0001(\u000e2-.Demand.DemandSubtype.ContentsLinkedMediaType\u00128\n\blistType\u0018\u0004 \u0001(\u000e2", "&.Demand.DemandSubtype.ContentsListType\u0012:\n\tmediaType\u0018\u0005 \u0001(\u000e2'.Demand.DemandSubtype.ContentsMediaType\u0012\u0014\n\fcontentsPath\u0018\u0006 \u0001(\t\u0012\u0013\n\u000boffsetIndex\u0018\u0007 \u0001(\u0005\u0012\u0010\n\bendIndex\u0018\b \u0001(\u0005\"5\n\u0010ContentsDataType\u0012\r\n\tTEXT_DATA\u0010\u0000\u0012\u0012\n\u000eTHUMBNAIL_DATA\u0010\u0001\"C\n\u0010ContentsInfoType\u0012\f\n\bBGM_INFO\u0010\u0000\u0012\r\n\tDISC_INFO\u0010\u0001\u0012\u0012\n\u000eMEDIALINK_INFO\u0010\u0002\"I\n\u0017ContentsLinkedMediaType\u0012\u0007\n\u0003HDD\u0010\u0000\u0012\u0007\n\u0003USB\u0010\u0001\u0012\b\n\u0004DISC\u0010\u0002\u0012\b\n\u0004PLEX\u0010\u0003\u0012\b\n\u0004DLNA\u0010\u0004\"d\n\u0010ContentsListType\u0012\f\n\bALL_LIST\u0010\u0000\u0012\u0011\n\rFAV", "ORITE_LIST\u0010\u0001\u0012\u0017\n\u0013RECENTLY_ADDED_LIST\u0010\u0002\u0012\u0016\n\u0012RECENTLY_VIEW_LIST\u0010\u0003\"U\n\u0011ContentsMediaType\u0012\r\n\tALL_MEDIA\u0010\u0000\u0012\u000f\n\u000bMOVIE_MEDIA\u0010\u0001\u0012\u000f\n\u000bMUSIC_MEDIA\u0010\u0002\u0012\u000f\n\u000bPHOTO_MEDIA\u0010\u0003\u001aß\b\n\u0006Result\u0012-\n\nplayResult\u0018\u0001 \u0001(\u000e2\u0019.Demand.Result.PlayResult\u00122\n\tcapResult\u0018\u0004 \u0001(\u000e2\u001f.Demand.Result.CapabilityResult\u00124\n\ninfoResult\u0018\u0005 \u0001(\u000e2 .Demand.Result.InformationResult\u0012=\n\u0012contentsListResult\u0018\u0006 \u0001(\u000e2!.Demand.Result.ContentsListResult\u0012A\n\u0014syncStatusInfoResult\u0018", "\u0007 \u0001(\u000e2#.Demand.Result.SyncStatusInfoResult\u0012E\n\u0016soundPrivacyInfoResult\u0018\b \u0001(\u000e2%.Demand.Result.SoundPrivacyInfoResult\u00121\n\fCPListResult\u0018\t \u0001(\u000e2\u001b.Demand.Result.CpListResult\u00129\n\u0010EXTSPKListResult\u0018\n \u0001(\u000e2\u001f.Demand.Result.ExtspkListResult\"\u0082\u0001\n\nPlayResult\u0012\u000b\n\u0007PLAY_OK\u0010\u0000\u0012\r\n\tPLAY_FAIL\u0010\u0001\u0012\u001f\n\u001bPLAY_UNSUPPORTED_VIDEOCODEC\u0010\u0002\u0012\u001f\n\u001bPLAY_UNSUPPORTED_AUDIOCODEC\u0010\u0003\u0012\u0016\n\u0012PLAY_UNKNOWN_VIDEO\u0010\u0004\",\n\u0010CapabilityResult\u0012\n\n\u0006CAP_OK\u0010\u0000\u0012\f\n\bCAP_FAIL", "\u0010\u0001\"^\n\u0011InformationResult\u0012\u000b\n\u0007INFO_OK\u0010\u0000\u0012\r\n\tINFO_FAIL\u0010\u0001\u0012\u0017\n\u0013INFO_INVALID_STATUS\u0010\u0002\u0012\u0014\n\u0010INFO_IN_PROGRESS\u0010\u0003\"B\n\u0012ContentsListResult\u0012\u0014\n\u0010CONTENTS_LIST_OK\u0010\u0000\u0012\u0016\n\u0012CONTENTS_LIST_FAIL\u0010\u0001\"\u0081\u0001\n\u0014SyncStatusInfoResult\u0012\u0017\n\u0013SYNC_STATUS_INFO_OK\u0010\u0000\u0012\u0019\n\u0015SYNC_STATUS_INFO_FAIL\u0010\u0001\u0012\u0019\n\u0015SYNC_STATUS_INFO_TEXT\u0010\u0002\u0012\u001a\n\u0016SYNC_STATUS_INFO_TOUCH\u0010\u0003\":\n\u0016SoundPrivacyInfoResult\u0012\u000e\n\nSP_INFO_OK\u0010\u0000\u0012\u0010\n\fSP_INFO_FAIL\u0010\u0001\"0\n\fCpListResult\u0012\u000e\n\nCP_LIST_OK\u0010\u0000\u0012\u0010\n\fCP_LIS", "T_FAIL\u0010\u0001\"<\n\u0010ExtspkListResult\u0012\u0012\n\u000eEXTSPK_LIST_OK\u0010\u0000\u0012\u0014\n\u0010EXTSPK_LIST_FAIL\u0010\u0001\"\u009a\u0001\n\nDemandType\u0012\u000b\n\u0007UNKNOWN\u0010\u0000\u0012\b\n\u0004PLAY\u0010\u0001\u0012\u000e\n\nCAPABILITY\u0010\u0002\u0012\u0011\n\rCONTENTS_INFO\u0010\u0003\u0012\u0011\n\rCONTENTS_LIST\u0010\u0004\u0012\u0014\n\u0010SYNC_STATUS_INFO\u0010\u0005\u0012\u000b\n\u0007SP_INFO\u0010\u0006\u0012\u000b\n\u0007CP_LIST\u0010\u0007\u0012\u000f\n\u000bEXTSPK_LIST\u0010\b"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.lge.UDAP.ROAP.Roap.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = Roap.C = fileDescriptor;
                Descriptors.Descriptor unused2 = Roap.a = Roap.a().e().get(0);
                GeneratedMessage.FieldAccessorTable unused3 = Roap.b = new GeneratedMessage.FieldAccessorTable(Roap.a, new String[]{"Type", "SessionId", "RequestMessage", "ResponseMessage", "CommandMessage", "EventMessage"}, ROAPMessage.class, ROAPMessage.a.class);
                Descriptors.Descriptor unused4 = Roap.c = Roap.a().e().get(1);
                GeneratedMessage.FieldAccessorTable unused5 = Roap.d = new GeneratedMessage.FieldAccessorTable(Roap.c, new String[]{"Type", "AuthMessage", "DataMessage", "DemandMessage"}, RequestMessage.class, RequestMessage.a.class);
                Descriptors.Descriptor unused6 = Roap.e = Roap.a().e().get(2);
                GeneratedMessage.FieldAccessorTable unused7 = Roap.f = new GeneratedMessage.FieldAccessorTable(Roap.e, new String[]{"Type", "AuthMessage", "DataMessage", "DemandMessage"}, ResponseMessage.class, ResponseMessage.a.class);
                Descriptors.Descriptor unused8 = Roap.g = Roap.a().e().get(3);
                GeneratedMessage.FieldAccessorTable unused9 = Roap.h = new GeneratedMessage.FieldAccessorTable(Roap.g, new String[]{"Type", "KeyMessage", "TouchMessage", "ActionMessage"}, CommandMessage.class, CommandMessage.a.class);
                Descriptors.Descriptor unused10 = Roap.i = Roap.a().e().get(4);
                GeneratedMessage.FieldAccessorTable unused11 = Roap.j = new GeneratedMessage.FieldAccessorTable(Roap.i, new String[]{"Type", "StatusMessage", "BData", "NData1", "NData2", "StrData", "ByteData", "ByteLength", "ByteTotalLength"}, EventMessage.class, EventMessage.a.class);
                Descriptors.Descriptor unused12 = Roap.k = Roap.i.getNestedTypes().get(0);
                GeneratedMessage.FieldAccessorTable unused13 = Roap.l = new GeneratedMessage.FieldAccessorTable(Roap.k, new String[]{"ViewStatus", "PlaybackStatus", "NetworkStatus", "UsbStatus", "SpStatus", "SyncStatus", "BgmInfoStatus", "CpStatus", "ExtspkStatus"}, EventMessage.StatusMessage.class, EventMessage.StatusMessage.a.class);
                Descriptors.Descriptor unused14 = Roap.m = Roap.a().e().get(5);
                GeneratedMessage.FieldAccessorTable unused15 = Roap.n = new GeneratedMessage.FieldAccessorTable(Roap.m, new String[]{"Code", "Action"}, Key.class, Key.a.class);
                Descriptors.Descriptor unused16 = Roap.o = Roap.a().e().get(6);
                GeneratedMessage.FieldAccessorTable unused17 = Roap.p = new GeneratedMessage.FieldAccessorTable(Roap.o, new String[]{"Type", "XDelta", "YDelta", "ZDelta", "Ratio", "BData"}, Touch.class, Touch.a.class);
                Descriptors.Descriptor unused18 = Roap.q = Roap.a().e().get(7);
                GeneratedMessage.FieldAccessorTable unused19 = Roap.r = new GeneratedMessage.FieldAccessorTable(Roap.q, new String[]{"Type", "BData", "NData", "StrData"}, Action.class, Action.a.class);
                Descriptors.Descriptor unused20 = Roap.s = Roap.a().e().get(8);
                GeneratedMessage.FieldAccessorTable unused21 = Roap.t = new GeneratedMessage.FieldAccessorTable(Roap.s, new String[]{"Type", "AuthKey", "AuthResult"}, Authentication.class, Authentication.a.class);
                Descriptors.Descriptor unused22 = Roap.u = Roap.a().e().get(9);
                GeneratedMessage.FieldAccessorTable unused23 = Roap.v = new GeneratedMessage.FieldAccessorTable(Roap.u, new String[]{"Type", "BData", "NData", "StrData"}, Data.class, Data.a.class);
                Descriptors.Descriptor unused24 = Roap.w = Roap.a().e().get(10);
                GeneratedMessage.FieldAccessorTable unused25 = Roap.x = new GeneratedMessage.FieldAccessorTable(Roap.w, new String[]{"Type", "SubtypeMessage", "ResultMessage", "Sequence", "BData", "NData", "StrData", "ByteData", "ByteLength", "ByteTotalLength"}, Demand.class, Demand.a.class);
                Descriptors.Descriptor unused26 = Roap.y = Roap.w.getNestedTypes().get(0);
                GeneratedMessage.FieldAccessorTable unused27 = Roap.z = new GeneratedMessage.FieldAccessorTable(Roap.y, new String[]{"DataType", "InfoType", "LinkedMediaType", "ListType", "MediaType", "ContentsPath", "OffsetIndex", "EndIndex"}, Demand.DemandSubtype.class, Demand.DemandSubtype.a.class);
                Descriptors.Descriptor unused28 = Roap.A = Roap.w.getNestedTypes().get(1);
                GeneratedMessage.FieldAccessorTable unused29 = Roap.B = new GeneratedMessage.FieldAccessorTable(Roap.A, new String[]{"PlayResult", "CapResult", "InfoResult", "ContentsListResult", "SyncStatusInfoResult", "SoundPrivacyInfoResult", "CPListResult", "EXTSPKListResult"}, Demand.Result.class, Demand.Result.a.class);
                return null;
            }
        });
    }

    private Roap() {
    }

    public static Descriptors.FileDescriptor a() {
        return C;
    }

    public static void a(ExtensionRegistry extensionRegistry) {
    }

    public static void b() {
    }
}
