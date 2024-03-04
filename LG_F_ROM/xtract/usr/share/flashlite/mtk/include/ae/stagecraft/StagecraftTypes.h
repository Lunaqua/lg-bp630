/*******************************************************************************
* ADOBE SYSTEMS INCORPORATED
* Copyright 2008 Adobe Systems Incorporated
* All Rights Reserved.
*
* NOTICE:  Adobe permits you to use, modify, and distribute this file in
* accordance with the  terms of the Adobe license agreement accompanying it. If
* you have received this file from a source other than Adobe, then your use,
* modification, or distribution of it requires the prior written permission of
* Adobe.
*******************************************************************************/

#ifndef _INCLUDE_AE_STAGECRAFT_STAGECRAFTTYPES_H
#define _INCLUDE_AE_STAGECRAFT_STAGECRAFTTYPES_H

#include <ae/IAEKernel.h>

namespace ae            {
namespace stagecraft    {

////////////////////////////////////////////////////////////////////////////////
// enum MouseButton
/// represents a MouseButton for input dispatch
enum MouseButton
{
    kMouseButtonLeft = 0,
    kMouseButtonMiddle = 1,
    kMouseButtonRight = 2
};

////////////////////////////////////////////////////////////////////////////////
// enum ScrollWheelDirection
/// represents a ScrollWheelDirection for input dispatch
enum ScrollWheelDirection
{
    kScrollWheelDirectionNorth = 0,
    kScrollDirectionSouth = 1,
    kScrollDirectionWest = 2,
    kScrollDirectionEast = 3
};

////////////////////////////////////////////////////////////////////////////////
// enum ColorFormat
/// Specifies bitmap pixel format types in stagecraft Planes.
enum ColorFormat
{
    kNullColorFormat,
    kARGB8888,
    kARGB5676,
    kARGB4444,
    kRGB888,
    kRGB565,
    kRGB555,
    kRGB444,
    kYUV422,
    kYUV420,
    kCLUT1,
    kCLUT2,
    kCLUT4,
    kCLUT8
};

////////////////////////////////////////////////////////////////////////////////
// BitsPerPixel
/// Returns number of bits-per-pixel for a ColorFormat.
inline u8 BitsPerPixel(ColorFormat format)
{
    const u8 bits[] =
    {
        0,          // kNullColorFormat
        32,         // kARGB8888,
        24,         // kARGB5676,
        16,         // kARGB4444,
        12,         // kARGB444,
        24,         // kRGB888,
        16,         // kRGB565,
        16,         // kRGB555,
        24,         // kYUV422,
        24,         // kYUV420
        1,          // kCLUT1
        2,          // kCLUT2
        4,          // kCLUT4
        8           // kCLUT8
    };
    if ((u32)format < (sizeof(bits) / sizeof(u8)))
    {
        return(bits[format]);
    }
    return(0);
}

////////////////////////////////////////////////////////////////////////////////
// ColorFormatHasAlpha 
/// Returns true if the ColorFormat contains an alpha element.
inline bool ColorFormatHasAlpha(ae::stagecraft::ColorFormat colorFormat)
{
    switch (colorFormat)
    {
        case ae::stagecraft::kARGB8888:
        case ae::stagecraft::kARGB5676:
        case ae::stagecraft::kARGB4444:
            return true;
        default:
            break;
    }
    return false;
}

////////////////////////////////////////////////////////////////////////////////
// class Rect
/// A rectangle class.
class Rect
{
public:
    Rect()                                  : x(0), y(0), w(0), h(0)            { }
    Rect(s32 xx, s32 yy, u32 ww, u32 hh)    : x(xx), y(yy), w(ww), h(hh)        { }
    Rect(const Rect & r)                    : x(r.x), y(r.y), w(r.w), h(r.h)    { }
    inline bool operator == (const Rect & r) const { return (h == r.h && w == r.w && x == r.x && y == r.y); }
    inline bool operator != (const Rect & r) const { return (r == *this ? false : true); }
public:
    s32 x;
    s32 y;
    u32 w;
    u32 h;
};

////////////////////////////////////////////////////////////////////////////////
// class Point
/// A point class.
class Point
{
public:
    Point()                                 : x(0), y(0)                        { }
    Point(s32 xx, s32 yy)                   : x(xx), y(yy)                      { }
    Point(const Point & p)                  : x(p.x), y(p.y)                    { }
    inline bool operator == (const Point & p) const { return (x == p.x && y == p.y); }
    inline bool operator != (const Point & p) const { return (p == *this ? false : true); }
public:
    s32 x;
    s32 y;
};

////////////////////////////////////////////////////////////////////////////////
// class Dims
/// A dimensions class.
class Dims
{
public:
    Dims()                                  : w(0), h(0)                        { }
    Dims(u32 ww, u32 hh)                    : w(ww), h(hh)                      { }
    Dims(const Dims & d)                    : w(d.w), h(d.h)                    { }
    inline bool operator == (const Dims & d) const { return ((w == d.w) && (h == d.h)); }
    inline bool operator != (const Dims & d) const { return (d == *this ? false : true); }
public:
    u32 w;
    u32 h;
};

////////////////////////////////////////////////////////////////////////////////
// class Color
/// A color class.
class Color
{
public:
    Color()                                 : red(0), green(0), blue(0), alpha(0) {};
    Color(u8 r, u8 g, u8 b)                 : red(r), green(g), blue(b), alpha(0) {};
    Color(u8 r, u8 g, u8 b, u8 a)           : red(r), green(g), blue(b), alpha(a) {};
    Color(const Color & c)                  : red(c.red), green(c.green), blue(c.blue), alpha(c.alpha) {};
    inline bool operator == (const Color & c) const
    {
        return((red == c.red) && (green == c.green) && (blue == c.blue) && (alpha == c.alpha));
    }
    inline bool operator != (const Color & c) const { return (c == *this ? false : true); }

    inline const Color & operator+=(const Color & rhs)
    {
        red = UnsaturatedAddition(red, rhs.red);
        green = UnsaturatedAddition(green, rhs.green);
        blue = UnsaturatedAddition(blue, rhs.blue);
        alpha = UnsaturatedAddition(alpha, rhs.alpha);
        return(*this);
    }

    inline u32 Val(void) const
    {
        return(((red & 0xff) << 24) | ((green & 0xff) << 16) | ((blue & 0xff) << 8) | (alpha & 0xff));
    }

public:
    u8 red;
    u8 green;
    u8 blue;
    u8 alpha;

private:
    u8 UnsaturatedAddition(u8 a, u8 b)
    {
        u32 val;
        u32 tmp1, tmp2;
        tmp1 = a;
        tmp2 = b;
        val = (tmp1 + tmp2);
        val = ((val > 0xff) ? 0xff : val);
        return(static_cast<u8>(val));
    }
};

class I2D;

////////////////////////////////////////////////////////////////////////////////
// class Plane
/// A plane class.
/// Stagecraft planes are used by the Flash core to render frames of Flash
/// animation.  They are also used in coordination with drivers in the ddk
/// to implement accelerated video decoding of Flash video streams.
///
/// <p>Stagecraft planes are basically just wrappers for device-implementations
/// of a bitmap that can be accessed and manipulated directly in memory.
/// See the StageWindow interface for a further description of render planes,
/// bitmap planes, and output planes.
///
/// <p>See the Graphics driver interface for the PlaneFactory class, which
/// is intended as a single entry point for creating and destroying planes
/// of a particular type.
class Plane
{
public:
    virtual const char *    GetClassName() const = 0;
        ///< Returns the class name of the Plane instance.
        /// StageWindows can be configured to use planes of different types
        /// to be used in different situations.  This function provides a
        /// mechanism for the Plane implementation to sense the type of
        /// Plane object being used (e.g. as the source bitmap in a Blit()
        /// operation) and to use an optimal algorithm.

    virtual Dims            GetDims() const = 0;
        ///< Returns the dimensions of the plane.
    virtual ColorFormat     GetColorFormat() const = 0;
        ///< Returns the ColorFormat of the plane.
    virtual u32             GetRowBytes() const = 0;
        ///< Returns the number of bytes used to store a scanline of the bitmap in memory.
    virtual Color           GetPixelAt(u32 x, u32 y) const = 0;
        ///< Returns the pixel value at an x, y location.
    virtual bool            GetPalette(const Color * & pPaletteToSet, u32 & nNumEntriesToSet) const = 0;
        ///< Returns the palette associated with the plane.
        /// For non-indexed-color plane formats, this function will return false.
        /// Note that the memory pointed to by pPaletteToSet is owned by the Plane.
    virtual bool            SetPalette(Color * pPalette, u32 nNumEntries) = 0;
        ///< Updates the first nNumEntries of the palette associated with the plane.
        /// For non-indexed-color plane formats, this function will return false.
        /// Will return false if nNumEntries is larger than the maximum number of
        /// palette entries for this plane.  The Plane object will maintain its own
        /// copy of the palette and apply it to subsequent Blit operations where
        /// this plane is the source.

    virtual u8 *            LockBits() = 0;
        ///< Returns a pointer to the memory representing the bitmap pixels in memory.
        /// Flash uses the return value of this function to directly manipulate the memory
        /// of planes used for compositing each frame of Flash animation.  Planes should
        /// provide a lock count such that clients (e.g., Stagecraft) can lock the plane
        /// more than one time in a row.  Stagecraft will not access the memory of a
        /// plane unless the bitmap is locked for direct access.
        ///
        /// <p>Note that the lock and unlock bits functions do not need (for Stagecraft's
        /// purposes) to implement thread-safety or areas of mutual exclusion.  However,
        /// clients may wish to provide this functionality anyway to make it easier to
        /// integrate with their platform's native application environment.
    virtual void            UnlockBits() = 0;
        ///< Signals that a client that has received a bits pointer with LockBits() is
        /// finished accessing the memory.
        /// Clients must balance calls to LockBits() and UnlockBits().
    virtual I2D *           Get2DInterface() = 0;
        ///< Returns an interface for using 2D graphics primitives on the plane.
        /// If provided by the render plane, a StageWindow will use these functions
        /// to accelerate composition of multiple planes into a completed frame
        /// of Flash animation.  This API is also used to blit from the render
        /// plane to the output plane (when an output plane is provided by the
        /// client).
        ///
        /// <p>The object returned by this function is assumed to have a lifetime
        /// equal to the lifetime of the plane object.  Note that the Stagecraft
        /// module does not require that this object's interface is thread-safe,
        /// but clients may wish to implement this feature for their own purposes.

    virtual void            OnRectUpdated(const Rect & updateRect) = 0;
        ///< A notifier function to tell the plane that part of it has been updated.
        /// Stagecraft sends this notification to the render plane when the next
        /// complete frame of Flash animation has been fully rendered and is ready
        /// for presentation.  The rectangle parameter signifies the portion of the
        /// plane that has been changed since the last frame.  Stagecraft also sends
        /// this notification to the output plane (if it exists) after blitting each
        /// complete frame into it.
        ///
        /// <p>Note that clients of the StageWindow interface can also use a
        /// StageWindowNotifier to receive notifications when the render and output
        /// planes are updated without providing an implementation of
        /// Plane::OnRectUpdated().

protected:
    virtual ~Plane() {};
};

////////////////////////////////////////////////////////////////////////////////
// class I2D
/// A 2D graphics interface.
class I2D
{
public:
    struct Transformation
    {
        Transformation()
        :   m_bHasTransparency(false)
        ,   m_bHasColorTransform(false)
        ,   m_bHasAlphaOnly(false)
        { };

        struct Matrix
        {
            Matrix()
            :   a(0), b(0), c(0), d(0)
            { };
            // Transformation matrix. These four ints are fixed point 16.16 values.
            // if a <= 0 or d <= 0 then the bitmap is reflected.
            // if b != 0 or c != 0 then the bitmap is being sheared.
            IAEKernel::FixedPoint a;
            IAEKernel::FixedPoint b;
            IAEKernel::FixedPoint c;
            IAEKernel::FixedPoint d;
        };

        struct Color64
        {
            Color64() : red(0), green(0), blue(0), alpha(0) { }
            //Color64(u16 r, u16 g, u16 b, u16 a) : red(r), green(g), blue(b), alpha(a) { }
            Color64(s16 r, s16 g, s16 b, s16 a) : red(r), green(g), blue(b), alpha(a) { }
            s16 red;
            s16 green;
            s16 blue;
            s16 alpha;
        };
        
        Matrix                  m_matrix;
        bool                    m_bHasTransparency;     // Per-pixel transparency in the source.
        bool                    m_bHasColorTransform;   // Color Transform is applied.
        bool                    m_bHasAlphaOnly;        // The color transform has alpha multiplier only.
        Color64                 m_colorMultiplier;      // Fixed Point 8.8 RGBA value specifying the color multiplier
        Color64                 m_colorOffset;          // RGBA value specifying the color offset.
    };

	struct SrcBlitInfo
	{
   		SrcBlitInfo() : x(0), y(0), dx(0), dy(0)            { }
	    SrcBlitInfo(s32 xx, s32 yy, u32 inverseScaleX, u32 inverseScaleY) : x(xx), y(yy), dx(inverseScaleX), dy(inverseScaleY)        { }
	    IAEKernel::FixedPoint x;
	    IAEKernel::FixedPoint y;
	    IAEKernel::FixedPoint dx;
	    IAEKernel::FixedPoint dy;
	};

public:
    virtual bool    Blit(Plane * pSource, const SrcBlitInfo & sourceInfo, const Rect & destRect,
                            const struct Transformation * pTrans, const Color * pOverlayColor = NULL) = 0;
        ///< Blits from a source plane with possible scaling, rotation, and blending.
    virtual bool    Fill(const Rect & rect, const Color & color, bool bBlend) = 0;
        ///< Fills a rectangle.
    virtual bool    Flush() = 0;
        ///< Flushes all previous Blit() and Fill() requests to the backing memory.
        /// The I2D implementation may use hardware acceleration to speed up the Blit()
        /// and Fill() routines.  The client should call Flush() after a set of calls
        /// to those functions when it needs to directly access the plane's bitmap memory
        /// again.
protected:
    virtual ~I2D() { }
};

}} // end namespace ae::stagecraft

#endif // #ifndef _INCLUDE_AE_STAGECRAFT_STAGECRAFTTYPES_H

////////////////////////////////////////////////////////////////////////////////
//  LOG
////////////////////////////////////////////////////////////////////////////////
//  20-Aug-08   dsletten    created
//  12-Sep-08   sshanson    added back BitsPerPixel() as this is used by the unit tests
//  12-Sep-08   sshanson    reenabled the MemGraphicsDriverTests
//  06-Oct-08   sshanson    Added ColorFormatHasAlpha()
//  29-Oct-08   dwoodward   added MouseButton and ScrollWheelDirection
//  01-Nov-08   dwoodward   got rid of CanBlit() from I2D; provide default
//                          constructors for Matrix and Color64
//  17-Nov-08   dsletten    use const Color * & in GetPalette(); add'l comments

