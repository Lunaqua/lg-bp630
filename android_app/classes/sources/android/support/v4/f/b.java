package android.support.v4.f;

import java.io.FileDescriptor;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketImpl;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class b extends Socket {

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static class a extends SocketImpl {
        a(DatagramSocket datagramSocket, FileDescriptor fileDescriptor) {
            this.localport = datagramSocket.getLocalPort();
            this.fd = fileDescriptor;
        }

        @Override // java.net.SocketImpl
        protected void accept(SocketImpl socketImpl) {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        protected int available() {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        protected void bind(InetAddress inetAddress, int i) {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        protected void close() {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        protected void connect(String str, int i) {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        protected void connect(InetAddress inetAddress, int i) {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        protected void connect(SocketAddress socketAddress, int i) {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        protected void create(boolean z) {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        protected InputStream getInputStream() {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketOptions
        public Object getOption(int i) {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        protected OutputStream getOutputStream() {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        protected void listen(int i) {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        protected void sendUrgentData(int i) {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketOptions
        public void setOption(int i, Object obj) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(DatagramSocket datagramSocket, FileDescriptor fileDescriptor) {
        super(new a(datagramSocket, fileDescriptor));
    }
}
