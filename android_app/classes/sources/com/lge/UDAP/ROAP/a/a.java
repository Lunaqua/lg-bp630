package com.lge.UDAP.ROAP.a;

import com.lge.UDAP.ROAP.Roap;
import com.lge.UDAP.ROAP.a;
import com.lge.UDAP.ROAP.a.b;
import java.io.IOException;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class a extends com.lge.UDAP.ROAP.a implements a.InterfaceC0052a {
    private final String a;

    public a(PipedOutputStream pipedOutputStream) {
        super(pipedOutputStream);
        this.a = "utf-8";
    }

    private byte[] a(b.EnumC0053b enumC0053b) {
        Roap.ROAPMessage.a newBuilder = Roap.ROAPMessage.newBuilder();
        newBuilder.setType(Roap.ROAPMessage.MessageType.COMMAND);
        Roap.CommandMessage.a newBuilder2 = Roap.CommandMessage.newBuilder();
        newBuilder2.setType(Roap.CommandMessage.CommandType.ACTION);
        Roap.Action.a newBuilder3 = Roap.Action.newBuilder();
        newBuilder3.setType(Roap.Action.ActionType.valueOf(enumC0053b.a()));
        newBuilder2.setActionMessage(newBuilder3);
        newBuilder.setCommandMessage(newBuilder2);
        return newBuilder.build().toByteArray();
    }

    private byte[] a(b.EnumC0053b enumC0053b, int i) {
        Roap.ROAPMessage.a newBuilder = Roap.ROAPMessage.newBuilder();
        newBuilder.setType(Roap.ROAPMessage.MessageType.COMMAND);
        Roap.CommandMessage.a newBuilder2 = Roap.CommandMessage.newBuilder();
        newBuilder2.setType(Roap.CommandMessage.CommandType.ACTION);
        Roap.Action.a newBuilder3 = Roap.Action.newBuilder();
        newBuilder3.setType(Roap.Action.ActionType.valueOf(enumC0053b.a()));
        newBuilder3.setNData(i);
        newBuilder2.setActionMessage(newBuilder3);
        newBuilder.setCommandMessage(newBuilder2);
        return newBuilder.build().toByteArray();
    }

    private byte[] a(b.EnumC0053b enumC0053b, String str, int i) {
        Roap.ROAPMessage.a newBuilder = Roap.ROAPMessage.newBuilder();
        newBuilder.setType(Roap.ROAPMessage.MessageType.COMMAND);
        Roap.CommandMessage.a newBuilder2 = Roap.CommandMessage.newBuilder();
        newBuilder2.setType(Roap.CommandMessage.CommandType.ACTION);
        Roap.Action.a newBuilder3 = Roap.Action.newBuilder();
        newBuilder3.setType(Roap.Action.ActionType.valueOf(enumC0053b.a()));
        newBuilder3.setStrData(str);
        newBuilder3.setNData(i);
        newBuilder2.setActionMessage(newBuilder3);
        newBuilder.setCommandMessage(newBuilder2);
        return newBuilder.build().toByteArray();
    }

    private byte[] a(b.EnumC0053b enumC0053b, String str, boolean z) {
        Roap.ROAPMessage.a newBuilder = Roap.ROAPMessage.newBuilder();
        newBuilder.setType(Roap.ROAPMessage.MessageType.COMMAND);
        Roap.CommandMessage.a newBuilder2 = Roap.CommandMessage.newBuilder();
        newBuilder2.setType(Roap.CommandMessage.CommandType.ACTION);
        Roap.Action.a newBuilder3 = Roap.Action.newBuilder();
        newBuilder3.setType(Roap.Action.ActionType.valueOf(enumC0053b.a()));
        newBuilder3.setStrData(str);
        newBuilder3.setBData(z);
        newBuilder2.setActionMessage(newBuilder3);
        newBuilder.setCommandMessage(newBuilder2);
        return newBuilder.build().toByteArray();
    }

    private byte[] a(Object... objArr) {
        Roap.ROAPMessage.a newBuilder = Roap.ROAPMessage.newBuilder();
        newBuilder.setType(Roap.ROAPMessage.MessageType.COMMAND);
        Roap.CommandMessage.a newBuilder2 = Roap.CommandMessage.newBuilder();
        newBuilder2.setType(Roap.CommandMessage.CommandType.ACTION);
        Roap.Action.a newBuilder3 = Roap.Action.newBuilder();
        if (objArr.length == 1) {
            if (objArr[0] instanceof String) {
                newBuilder3.setType(Roap.Action.ActionType.TEXT_INPUT);
                newBuilder3.setStrData((String) objArr[0]);
            } else if (objArr[0] instanceof Boolean) {
                newBuilder3.setType(Roap.Action.ActionType.AUTO_PAUSE);
                newBuilder3.setBData(((Boolean) objArr[0]).booleanValue());
            } else if (!(objArr[0] instanceof Integer)) {
                return null;
            } else {
                newBuilder3.setType(Roap.Action.ActionType.TIME_JUMP);
                newBuilder3.setNData(((Integer) objArr[0]).intValue());
            }
        }
        newBuilder2.setActionMessage(newBuilder3);
        newBuilder.setCommandMessage(newBuilder2);
        return newBuilder.build().toByteArray();
    }

    private byte[] c(String str) {
        Roap.ROAPMessage.a newBuilder = Roap.ROAPMessage.newBuilder();
        newBuilder.setType(Roap.ROAPMessage.MessageType.COMMAND);
        Roap.CommandMessage.a newBuilder2 = Roap.CommandMessage.newBuilder();
        newBuilder2.setType(Roap.CommandMessage.CommandType.ACTION);
        Roap.Action.a newBuilder3 = Roap.Action.newBuilder();
        newBuilder3.setType(Roap.Action.ActionType.TEXT_SUBMIT);
        newBuilder3.setStrData(str);
        newBuilder2.setActionMessage(newBuilder3);
        newBuilder.setCommandMessage(newBuilder2);
        return newBuilder.build().toByteArray();
    }

    @Override // com.lge.UDAP.ROAP.a.InterfaceC0052a
    public void a() {
        System.out.println("SP stop action command");
        try {
            a(a(b.EnumC0053b.SP_STOP));
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPAction ActionSP_STOP error:" + e);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.InterfaceC0052a
    public void a(int i) {
        try {
            a(a(new Integer(i)));
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPAction ActionTimeJump error:" + e);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.InterfaceC0052a
    public void a(String str) {
        System.out.println("Text input action command");
        try {
            try {
                a(a(new String(str.getBytes(), "utf-8")));
            } catch (IOException e) {
                e.printStackTrace();
                PrintStream printStream = System.out;
                printStream.println("ROAPAction ActionTextInput error:" + e);
            }
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            PrintStream printStream2 = System.out;
            printStream2.println("ROAPAction String Encoding error:" + e2);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.InterfaceC0052a
    public void a(String str, int i) {
        System.out.println("SP start action command");
        try {
            try {
                a(a(b.EnumC0053b.SP_START, new String(str.getBytes(), "utf-8"), new Integer(i).intValue()));
            } catch (IOException e) {
                e.printStackTrace();
                PrintStream printStream = System.out;
                printStream.println("ROAPAction Action error:" + e);
            }
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            PrintStream printStream2 = System.out;
            printStream2.println("ROAPAction String Encoding error:" + e2);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.InterfaceC0052a
    public void a(String str, boolean z) {
        System.out.println("ExtSpk Execute command");
        try {
            a(a(b.EnumC0053b.SYNC_EXTSPK_EXECUTE, str, z));
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPAction Action ExtSpkExecute error : " + e);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.InterfaceC0052a
    public void a(boolean z) {
        System.out.println("Auto Pause action command");
        try {
            a(a(new Boolean(z)));
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPAction ActionAutoPause error:" + e);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.InterfaceC0052a
    public void b() {
        System.out.println("SP play action command");
        try {
            a(a(b.EnumC0053b.SP_PLAYING));
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPAction ActionSP_PLAYING error:" + e);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.InterfaceC0052a
    public void b(int i) {
        System.out.println("Sync media CMD command");
        try {
            a(a(b.EnumC0053b.SYNC_MEDIA_CMD, i));
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPAction ActionMediaCMD error:" + e);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.InterfaceC0052a
    public void b(String str) {
        System.out.println("Text submit action command");
        try {
            try {
                a(c(new String(str.getBytes(), "utf-8")));
            } catch (IOException e) {
                e.printStackTrace();
                PrintStream printStream = System.out;
                printStream.println("ROAPAction ActionTextSubmit error:" + e);
            }
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            PrintStream printStream2 = System.out;
            printStream2.println("ROAPAction String Encoding error:" + e2);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.InterfaceC0052a
    public void b(String str, int i) {
        System.out.println("Sync folder change action command");
        try {
            try {
                a(a(b.EnumC0053b.SYNC_FOLDER_CHANGE, new String(str.getBytes(), "utf-8"), i));
            } catch (IOException e) {
                e.printStackTrace();
                PrintStream printStream = System.out;
                printStream.println("ROAPAction ActionSS_MEDIA_START error:" + e);
            }
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            PrintStream printStream2 = System.out;
            printStream2.println("ROAPAction String Encoding error:" + e2);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.InterfaceC0052a
    public void c(int i) {
        System.out.println("Sync page change command");
        try {
            a(a(b.EnumC0053b.SYNC_PAGE_CHANGE, i));
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPAction Action sync page change error:" + e);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.InterfaceC0052a
    public void c(String str, int i) {
        System.out.println("Sync media start action command");
        try {
            try {
                a(a(b.EnumC0053b.SYNC_MEDIA_START, new String(str.getBytes(), "utf-8"), i));
            } catch (IOException e) {
                e.printStackTrace();
                PrintStream printStream = System.out;
                printStream.println("ROAPAction ActionSS_MEDIA_START error:" + e);
            }
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            PrintStream printStream2 = System.out;
            printStream2.println("ROAPAction String Encoding error:" + e2);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.InterfaceC0052a
    public void d(int i) {
        System.out.println("Music ID action command");
        try {
            a(a(b.EnumC0053b.MUSICID_CANCEL, i));
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPAction ActionMusicIDCancel error:" + e);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.InterfaceC0052a
    public void d(String str, int i) {
        System.out.println("Sync media change action command");
        try {
            try {
                a(a(b.EnumC0053b.SYNC_MEDIA_CHANGE, new String(str.getBytes(), "utf-8"), i));
            } catch (IOException e) {
                e.printStackTrace();
                PrintStream printStream = System.out;
                printStream.println("ROAPAction ActionSS_MEDIA_CHANGE error:" + e);
            }
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            PrintStream printStream2 = System.out;
            printStream2.println("ROAPAction String Encoding error:" + e2);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.InterfaceC0052a
    public void e(int i) {
        System.out.println("CP Execute command");
        try {
            a(a(b.EnumC0053b.SYNC_CP_EXECUTE, i));
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPAction Action CPExecute error : " + e);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.InterfaceC0052a
    public void e(String str, int i) {
        System.out.println("Sync device change action command");
        try {
            try {
                a(a(b.EnumC0053b.SYNC_DEVICE_CHANGE, new String(str.getBytes(), "utf-8"), i));
            } catch (IOException e) {
                e.printStackTrace();
                PrintStream printStream = System.out;
                printStream.println("ROAPAction ActionSS_DEVICE_CHANGE error:" + e);
            }
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            PrintStream printStream2 = System.out;
            printStream2.println("ROAPAction String Encoding error:" + e2);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.InterfaceC0052a
    public void f(String str, int i) {
        System.out.println("Sync select action command");
        try {
            try {
                a(a(b.EnumC0053b.SYNC_SELECT, new String(str.getBytes(), "utf-8"), i));
            } catch (IOException e) {
                e.printStackTrace();
                PrintStream printStream = System.out;
                printStream.println("ROAPAction ActionSS_SELECT error:" + e);
            }
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            PrintStream printStream2 = System.out;
            printStream2.println("ROAPAction String Encoding error:" + e2);
        }
    }

    @Override // com.lge.UDAP.ROAP.a.InterfaceC0052a
    public void g(String str, int i) {
        System.out.println("Sync page change action command");
        try {
            try {
                a(a(b.EnumC0053b.SYNC_PAGE_CHANGE, new String(str.getBytes(), "utf-8"), i));
            } catch (IOException e) {
                e.printStackTrace();
                PrintStream printStream = System.out;
                printStream.println("ROAPAction Action sync page change error:" + e);
            }
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            PrintStream printStream2 = System.out;
            printStream2.println("ROAPAction String Encoding error:" + e2);
        }
    }
}
