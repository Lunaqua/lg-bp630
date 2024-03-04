package com.google.protobuf;

import com.google.protobuf.Descriptors;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public interface Service {
    Descriptors.ServiceDescriptor a();

    Message a(Descriptors.MethodDescriptor methodDescriptor);

    void a(Descriptors.MethodDescriptor methodDescriptor, RpcController rpcController, Message message, RpcCallback<Message> rpcCallback);

    Message b(Descriptors.MethodDescriptor methodDescriptor);
}
