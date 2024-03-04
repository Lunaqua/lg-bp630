package com.lge.UDAP.ROAP.a;

import java.io.PipedOutputStream;
import java.io.PrintStream;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class b {
    private com.lge.UDAP.ROAP.a.c a;
    private com.lge.UDAP.ROAP.a.d b;
    private com.lge.UDAP.ROAP.a.a c;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum a {
        UNKNOWN(0),
        UP(1),
        DOWN(2);
        
        private final int d;

        a(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }
    }

    /* renamed from: com.lge.UDAP.ROAP.a.b$b  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum EnumC0053b {
        UNKNOWN(0),
        TEXT_INPUT(1),
        TEXT_SUBMIT(2),
        AUTO_PAUSE(3),
        TIME_JUMP(4),
        SP_START(5),
        SP_STOP(6),
        SYNC_MEDIA_START(7),
        SYNC_SELECT(8),
        SYNC_FOLDER_CHANGE(9),
        SYNC_MEDIA_CHANGE(10),
        SYNC_DEVICE_CHANGE(11),
        MUSICID_CANCEL(12),
        SP_PLAYING(13),
        SYNC_MEDIA_CMD(14),
        SYNC_PAGE_CHANGE(15),
        SYNC_CP_EXECUTE(16),
        SYNC_EXTSPK_EXECUTE(17);
        
        private final int s;

        EnumC0053b(int i) {
            this.s = i;
        }

        public int a() {
            return this.s;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum c {
        KEYCODE_UNKNOWN(0),
        KEYCODE_POWER(1),
        KEYCODE_0(2),
        KEYCODE_1(3),
        KEYCODE_2(4),
        KEYCODE_3(5),
        KEYCODE_4(6),
        KEYCODE_5(7),
        KEYCODE_6(8),
        KEYCODE_7(9),
        KEYCODE_8(10),
        KEYCODE_9(11),
        KEYCODE_UP(12),
        KEYCODE_DOWN(13),
        KEYCODE_LEFT(14),
        KEYCODE_RIGHT(15),
        KEYCODE_UP_LEFT(16),
        KEYCODE_UP_RIGHT(17),
        KEYCODE_DOWN_LEFT(18),
        KEYCODE_DOWN_RIGHT(19),
        KEYCODE_ENTER(20),
        KEYCODE_HOME(21),
        KEYCODE_MENU(22),
        KEYCODE_BACK(23),
        KEYCODE_VOLUME_UP(24),
        KEYCODE_VOLUME_DOWN(25),
        KEYCODE_MUTE(26),
        KEYCODE_CHANNEL_UP(27),
        KEYCODE_CHANNEL_DOWN(28),
        KEYCODE_BLUE(29),
        KEYCODE_GREEN(30),
        KEYCODE_RED(31),
        KEYCODE_YELLOW(32),
        KEYCODE_PLAY(33),
        KEYCODE_PAUSE(34),
        KEYCODE_STOP(35),
        KEYCODE_FAST_FORWARD(36),
        KEYCODE_FAST_BACKWARD(37),
        KEYCODE_SKIP_FORWARD(38),
        KEYCODE_SKIP_BACKWARD(39),
        KEYCODE_RECORD(40),
        KEYCODE_RECORD_LIST(41),
        KEYCODE_REPEAT(42),
        KEYCODE_LIVE(43),
        KEYCODE_GUIDE(44),
        KEYCODE_INFO(45),
        KEYCODE_RESOLUTION(46),
        KEYCODE_INPUT(47),
        KEYCODE_VIDEO_PIP(48),
        KEYCODE_SUBTITLE(49),
        KEYCODE_PROGRAM_LIST(50),
        KEYCODE_TEXT(51),
        KEYCODE_MARKER(52),
        KEYCODE_NEXT(53),
        KEYCODE_PREVIOUS(54),
        KEYCODE_OPEN(55),
        KEYCODE_CLOSE(56),
        KEYCODE_REPEAT_A2B(57),
        KEYCODE_PLAY_PAUSE(58),
        KEYCODE_RANDOM(59),
        KEYCODE_ZOOM(60),
        KEYCODE_SEARCH(61),
        KEYCODE_TITLE(62),
        KEYCODE_POPUP(63),
        KEYCODE_CLEAR(64),
        KEYCODE_SETUP(65),
        KEYCODE_DISC_MENU(66),
        KEYCODE_TIMER_REC(67),
        KEYCODE_PICTURE(68),
        KEYCODE_LOCK(69),
        KEYCODE_TUNER(70),
        KEYCODE_EQUALIZER(71),
        KEYCODE_SLEEP(72),
        KEYCODE_WOOFER_VOL(73),
        KEYCODE_NIGHT(74),
        KEYCODE_IPOD(75),
        KEYCODE_SPEAKER_SETUP(76),
        KEYCODE_OPTICAL(77),
        KEYCODE_MIC_VOLUME_UP(78),
        KEYCODE_MIC_VOLUME_DOWN(79),
        KEYCODE_MIC_ECHO(80),
        KEYCODE_CD_ARCHIVING(81),
        KEYCODE_GRACENOTE(82),
        KEYCODE_AUDIO_PIP(83),
        KEYCODE_SUBTITLE_ONOFF(84),
        KEYCODE_AUDIO(85),
        KEYCODE_ANGLE(86),
        KEYCODE_3D_SOUND(87),
        KEYCODE_3D_VIDEO(88),
        KEYCODE_3D_LR(89),
        KEYCODE_DASH(90),
        KEYCODE_PREVIOUS_CHANNEL(91),
        KEYCODE_FAVORITE_CHANNEL(92),
        KEYCODE_QUICK_MENU(93),
        KEYCODE_TEXT_OPTION(94),
        KEYCODE_AUDIO_DESCRIPTION(95),
        KEYCODE_NETCAST(96),
        KEYCODE_ENERGY_SAVING(97),
        KEYCODE_AV_MODE(98),
        KEYCODE_SIMPLE_LINK(99),
        KEYCODE_EXIT(100),
        KEYCODE_RESERVATION_LIST(101),
        KEYCODE_PIP_CHANNEL_UP(102),
        KEYCODE_PIP_CHANNEL_DOWN(103),
        KEYCODE_PIP_INPUT(104),
        KEYCODE_3D_SOUND_ZOOMING(105),
        KEYCODE_SOUND_EFFECT(106),
        KEYCODE_SPEAKER_LEVEL(107),
        KEYCODE_FUNCTION(108),
        KEYCODE_BGM(109),
        KEYCODE_2DTO3D(110);
        
        private final int bh;

        c(int i) {
            this.bh = i;
        }

        public int a() {
            return this.bh;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum d {
        UNKNOWN(0),
        MOVE(1),
        WHEEL(2),
        ZOOM(3),
        CLICK(4),
        DRAG(5);
        
        private final int g;

        d(int i) {
            this.g = i;
        }

        public int a() {
            return this.g;
        }
    }

    public b(PipedOutputStream pipedOutputStream) {
        this.a = new com.lge.UDAP.ROAP.a.c(pipedOutputStream);
        this.b = new com.lge.UDAP.ROAP.a.d(pipedOutputStream);
        this.c = new com.lge.UDAP.ROAP.a.a(pipedOutputStream);
    }

    public void a() {
        this.b.a();
    }

    public void a(int i) {
        this.b.b(0, i, 0, 0);
    }

    public void a(int i, int i2) {
        this.b.a(i, i2, 0, 0);
    }

    public void a(a aVar, c cVar) {
        this.a.a(aVar, cVar);
    }

    public void a(String str) {
        this.c.a(str);
    }

    public void a(String str, int i) {
        System.out.println("SP start");
        this.c.a(str, i);
    }

    public void a(String str, boolean z) {
        PrintStream printStream = System.out;
        printStream.println("speakers : " + str);
        PrintStream printStream2 = System.out;
        printStream2.println("mute : " + z);
        System.out.println("ExtSpkExecute");
        this.c.a(str, z);
    }

    public void a(boolean z) {
        this.b.a(z);
    }

    public void b() {
        System.out.println("SP stop");
        this.c.a();
    }

    public void b(int i) {
        this.b.b(i, 0, 0, 0);
    }

    public void b(String str) {
        System.out.println("actionTextSubmit");
        this.c.b(str);
    }

    public void b(String str, int i) {
        System.out.println("Sync folder change");
        this.c.b(str, i);
    }

    public void b(boolean z) {
        this.c.a(z);
    }

    public void c() {
        System.out.println("SP playing");
        this.c.b();
    }

    public void c(int i) {
        this.b.a(i);
    }

    public void c(String str, int i) {
        System.out.println("Sync media start");
        this.c.c(str, i);
    }

    public void d(int i) {
        this.c.a(i);
    }

    public void d(String str, int i) {
        System.out.println("Sync media change");
        this.c.d(str, i);
    }

    public void e(int i) {
        System.out.println("Sync page change");
        this.c.c(i);
    }

    public void e(String str, int i) {
        System.out.println("Sync device change");
        this.c.e(str, i);
    }

    public void f(int i) {
        System.out.println("MusicIDCancel");
        this.c.d(i);
    }

    public void f(String str, int i) {
        System.out.println("Sync select");
        this.c.f(str, i);
    }

    public void g(int i) {
        System.out.println("SyncMediaCMD");
        this.c.b(i);
    }

    public void g(String str, int i) {
        System.out.println("Sync page change");
        this.c.g(str, i);
    }

    public void h(int i) {
        System.out.println("CPExecute");
        this.c.e(i);
    }
}
