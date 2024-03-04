package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public interface ProtocolMessageEnum extends Internal.EnumLite {
    Descriptors.EnumDescriptor getDescriptorForType();

    @Override // com.google.protobuf.Internal.EnumLite
    int getNumber();

    Descriptors.EnumValueDescriptor getValueDescriptor();
}
