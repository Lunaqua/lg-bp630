package com.google.protobuf;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class RpcUtil {

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class AlreadyCalledException extends RuntimeException {
        private static final long a = 5469741279507848266L;

        public AlreadyCalledException() {
            super("This RpcCallback was already called and cannot be called multiple times.");
        }
    }

    private RpcUtil() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <Type extends Message> RpcCallback<Type> a(RpcCallback<Message> rpcCallback) {
        return rpcCallback;
    }

    public static <Type extends Message> RpcCallback<Message> a(final RpcCallback<Type> rpcCallback, final Class<Type> cls, final Type type) {
        return new RpcCallback<Message>() { // from class: com.google.protobuf.RpcUtil.1
            @Override // com.google.protobuf.RpcCallback
            public void run(Message message) {
                Message b;
                try {
                    b = (Message) cls.cast(message);
                } catch (ClassCastException unused) {
                    b = RpcUtil.b(type, message);
                }
                rpcCallback.run(b);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <Type extends Message> Type b(Type type, Message message) {
        return (Type) type.newBuilderForType().mergeFrom(message).build();
    }

    public static <ParameterType> RpcCallback<ParameterType> b(final RpcCallback<ParameterType> rpcCallback) {
        return new RpcCallback<ParameterType>() { // from class: com.google.protobuf.RpcUtil.2
            private boolean alreadyCalled = false;

            @Override // com.google.protobuf.RpcCallback
            public void run(ParameterType parametertype) {
                synchronized (this) {
                    if (this.alreadyCalled) {
                        throw new AlreadyCalledException();
                    }
                    this.alreadyCalled = true;
                }
                RpcCallback.this.run(parametertype);
            }
        };
    }
}
