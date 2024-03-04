package com.lge.UDAP.ROAP.e;

import com.lge.UDAP.ROAP.e.b;
import java.io.PrintStream;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class c extends DefaultHandler {
    private b a = new b();
    private String b;
    private String c;

    public a a() {
        return this.a;
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) {
        b bVar;
        super.characters(cArr, i, i2);
        String str = new String(cArr, i, i2);
        PrintStream printStream = System.out;
        printStream.println("characters:" + str);
        if (this.c.equals(b.a.c)) {
            return;
        }
        boolean z = true;
        if (this.c.equals(b.a.d)) {
            if (str.equals("OK")) {
                bVar = this.a;
                z = false;
            } else {
                bVar = this.a;
            }
            bVar.a(z);
        } else if (this.c.equals(b.a.e)) {
            this.a.a(str);
        } else if (this.b.equals(b.a.C0057a.a)) {
            if (this.c.equals(b.a.b)) {
                if (str.equals(b.a.C0057a.b)) {
                    this.a.b(true);
                } else if (str.equals(b.a.C0057a.c)) {
                    this.a.c(true);
                } else if (str.equals(b.a.C0057a.d)) {
                    this.a.d(true);
                } else if (str.equals(b.a.C0057a.e)) {
                    this.a.e(true);
                }
            }
        } else if (this.b.equals("service")) {
            if (this.c.equals(b.a.b)) {
                this.a.b(str);
            }
        } else if (this.b.equals(b.a.C0058b.C0059a.C0060a.h.a)) {
            if (this.c.equals(b.a.b)) {
                if (str.equals(b.a.C0058b.C0059a.C0060a.h.b)) {
                    this.a.f(true);
                } else if (str.equals(b.a.C0058b.C0059a.C0060a.h.c)) {
                    this.a.g(true);
                } else if (str.equals(b.a.C0058b.C0059a.C0060a.h.d)) {
                    this.a.h(true);
                }
            }
        } else if (this.b.equals(b.a.C0058b.C0059a.C0060a.m.a)) {
            if (this.c.equals(b.a.b)) {
                if (str.equals(b.a.C0058b.C0059a.C0060a.m.b)) {
                    this.a.i(true);
                } else if (str.equals(b.a.C0058b.C0059a.C0060a.m.c)) {
                    this.a.j(true);
                }
            }
        } else if (this.b.equals(b.a.C0058b.C0059a.C0060a.l.a)) {
            this.c.equals(b.a.b);
        } else if (this.b.equals(b.a.C0058b.C0059a.C0060a.g.a)) {
            if (this.c.equals(b.a.b)) {
                if (str.equals(b.a.C0058b.C0059a.C0060a.g.b)) {
                    this.a.k(true);
                } else if (str.equals(b.a.C0058b.C0059a.C0060a.g.c)) {
                    this.a.l(true);
                } else if (str.equals(b.a.C0058b.C0059a.C0060a.g.d)) {
                    this.a.m(true);
                } else if (str.equals(b.a.C0058b.C0059a.C0060a.g.e)) {
                    this.a.n(true);
                } else if (str.equals(b.a.C0058b.C0059a.C0060a.g.f)) {
                    this.a.o(true);
                } else if (str.equals(b.a.C0058b.C0059a.C0060a.g.g)) {
                    this.a.p(true);
                } else if (str.equals(b.a.C0058b.C0059a.C0060a.g.h)) {
                    this.a.q(true);
                }
            }
        } else if (this.b.equals(b.a.C0058b.C0059a.C0060a.f.a)) {
            if (this.c.equals(b.a.b) && str.equals(b.a.C0058b.C0059a.C0060a.f.b)) {
                this.a.r(true);
            }
        } else if (this.b.equals(b.a.C0058b.C0059a.C0060a.C0062b.a)) {
            if (this.c.equals(b.a.b)) {
                if (str.equals(b.a.C0058b.C0059a.C0060a.C0062b.b)) {
                    this.a.s(true);
                } else if (str.equals(b.a.C0058b.C0059a.C0060a.C0062b.c)) {
                    this.a.t(true);
                } else if (str.equals("MediaLink")) {
                    this.a.u(true);
                }
            }
        } else if (this.b.equals(b.a.C0058b.C0059a.C0060a.c.a)) {
            if (this.c.equals(b.a.b)) {
                if (str.equals(b.a.C0058b.C0059a.C0060a.c.b)) {
                    this.a.v(true);
                } else if (str.equals(b.a.C0058b.C0059a.C0060a.c.c)) {
                    this.a.w(true);
                } else if (str.equals("MediaLink")) {
                    this.a.x(true);
                }
            }
        } else if (this.b.equals(b.a.C0058b.C0059a.C0060a.d.a)) {
            if (this.c.equals(b.a.b)) {
                if (str.equals(b.a.C0058b.C0059a.C0060a.d.b)) {
                    this.a.y(true);
                } else if (str.equals(b.a.C0058b.C0059a.C0060a.d.c)) {
                    this.a.z(true);
                }
            }
        } else if (this.b.equals(b.a.C0058b.C0059a.C0060a.p.a)) {
            if (this.c.equals(b.a.b) && str.equals(b.a.C0058b.C0059a.C0060a.p.b)) {
                this.a.A(true);
            }
        } else if (this.b.equals(b.a.C0058b.C0059a.C0060a.q.a)) {
            if (this.c.equals(b.a.b)) {
                if (str.equals(b.a.C0058b.C0059a.C0060a.q.b)) {
                    this.a.B(true);
                } else if (str.equals(b.a.C0058b.C0059a.C0060a.q.c)) {
                    this.a.C(true);
                } else if (str.equals(b.a.C0058b.C0059a.C0060a.q.d)) {
                    this.a.D(true);
                } else if (str.equals(b.a.C0058b.C0059a.C0060a.q.e)) {
                    this.a.E(true);
                }
            }
        } else if (this.b.equals(b.a.C0058b.C0059a.C0060a.e.a)) {
            if (this.c.equals(b.a.b)) {
                if (str.equals(b.a.C0058b.C0059a.C0060a.e.b)) {
                    this.a.F(true);
                } else if (str.equals(b.a.C0058b.C0059a.C0060a.e.c)) {
                    this.a.G(true);
                } else if (str.equals(b.a.C0058b.C0059a.C0060a.e.d)) {
                    this.a.H(true);
                } else if (str.equals(b.a.C0058b.C0059a.C0060a.e.e)) {
                    this.a.I(true);
                } else if (str.equals(b.a.C0058b.C0059a.C0060a.e.f)) {
                    this.a.J(true);
                } else if (str.equals(b.a.C0058b.C0059a.C0060a.e.g)) {
                    this.a.K(true);
                }
            }
        } else if (this.b.equals(b.a.C0058b.C0059a.C0060a.C0061a.a)) {
            if (this.c.equals(b.a.b) && str.equals(b.a.C0058b.C0059a.C0060a.C0061a.b)) {
                this.a.L(true);
            }
        } else if (this.b.equals(b.a.C0058b.C0059a.C0060a.n.a)) {
            this.a.M(true);
        } else if (this.b.equals(b.a.C0058b.C0059a.C0060a.o.a)) {
            this.a.N(true);
        } else if (this.b.equals(b.a.C0058b.C0059a.C0060a.k.a)) {
            this.a.O(true);
        } else if (this.b.equals(b.a.C0058b.C0059a.C0060a.i.a)) {
            this.a.P(true);
        } else if (this.b.equals(b.a.C0058b.C0059a.C0060a.j.a)) {
            this.a.Q(true);
        }
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) {
        super.endElement(str, str2, str3);
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) {
        super.startElement(str, str2, str3, attributes);
        this.c = com.lge.media.launcher.a.d;
        PrintStream printStream = System.out;
        printStream.println("start localname:" + str2);
        if (!str2.equals(b.a.a)) {
            if (!str2.equals(b.a.c) && !str2.equals(b.a.d) && !str2.equals(b.a.e)) {
                if (!str2.equals(b.a.C0057a.a)) {
                    if (!str2.equals(b.a.b)) {
                        if (!str2.equals("service") && !str2.equals(b.a.C0058b.C0059a.C0060a.h.a) && !str2.equals(b.a.C0058b.C0059a.C0060a.m.a) && !str2.equals(b.a.C0058b.C0059a.C0060a.f.a) && !str2.equals(b.a.C0058b.C0059a.C0060a.l.a) && !str2.equals(b.a.C0058b.C0059a.C0060a.C0062b.a) && !str2.equals(b.a.C0058b.C0059a.C0060a.c.a) && !str2.equals(b.a.C0058b.C0059a.C0060a.d.a) && !str2.equals(b.a.C0058b.C0059a.C0060a.p.a) && !str2.equals(b.a.C0058b.C0059a.C0060a.q.a) && !str2.equals(b.a.C0058b.C0059a.C0060a.e.a) && !str2.equals(b.a.C0058b.C0059a.C0060a.C0061a.a) && !str2.equals(b.a.C0058b.C0059a.C0060a.g.a)) {
                            if (str2.equals(b.a.C0058b.C0059a.C0060a.n.a)) {
                                this.b = str2;
                                this.a.M(true);
                                return;
                            } else if (str2.equals(b.a.C0058b.C0059a.C0060a.o.a)) {
                                this.b = str2;
                                this.a.N(true);
                                return;
                            } else if (str2.equals(b.a.C0058b.C0059a.C0060a.k.a)) {
                                this.b = str2;
                                this.a.O(true);
                                return;
                            } else if (str2.equals(b.a.C0058b.C0059a.C0060a.i.a)) {
                                this.b = str2;
                                this.a.P(true);
                                return;
                            } else if (str2.equals(b.a.C0058b.C0059a.C0060a.j.a)) {
                                this.b = str2;
                                this.a.Q(true);
                                return;
                            } else {
                                return;
                            }
                        }
                    }
                }
            }
            this.c = str2;
            return;
        }
        this.b = str2;
    }
}
