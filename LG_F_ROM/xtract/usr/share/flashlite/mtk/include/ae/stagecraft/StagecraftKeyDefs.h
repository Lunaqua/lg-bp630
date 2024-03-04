/*******************************************************************************
* ADOBE SYSTEMS INCORPORATED
* Copyright 2007 Adobe Systems Incorporated
* All Rights Reserved.
*
* NOTICE:  Adobe permits you to use, modify, and distribute this file in
* accordance with the  terms of the Adobe license agreement accompanying it. If
* you have received this file from a source other than Adobe, then your use,
* modification, or distribution of it requires the prior written permission of
* Adobe.
*******************************************************************************/

#ifndef _INCLUDE_AE_STAGECRAFT_STAGECRAFTKEYDEFS_H
#define _INCLUDE_AE_STAGECRAFT_STAGECRAFTKEYDEFS_H

////////////////////////////////////////////////////////////////////////////////
// Standard Flash key symbols in AEKEY space, defined so they don't collide
// with ascii characters.
//
// Example usage:
//   Call IStagecraft::DispatchKeyDown() or StageWindow::DispatchKeyDown() with
//   the AEKEY code AEKEY_UP to dispatch the AS2 Key.UP key or the AS3
//   Keyboard.UP key.  To dispatch an ampersand (which has the same ascii value
//   as Key.UP: 38), call DispatchKeyDown() with the ascii value directly - e.g.
//   DispatchKeyDown(38) or DispatchKeyDown('&').
//
#define AEKEY_ALT                       0x00400012 ///< used to dispatch Key.ALT
#define AEKEY_BACKSPACE                 0x00400008 ///< used to dispatch Key.BACKSPACE
#define AEKEY_CAPSLOCK                  0x00400014 ///< used to dispatch Key.CAPSLOCK
#define AEKEY_CONTROL                   0x00400011 ///< used to dispatch Key.CONTROL
#define AEKEY_INSERT                    0x0040002D ///< used to dispatch Key.INSERT
#define AEKEY_DELETEKEY                 0x0040002E ///< used to dispatch Key.DELETEKEY
#define AEKEY_DOWN                      0x00400028 ///< used to dispatch Key.DOWN
#define AEKEY_END                       0x00400023 ///< used to dispatch Key.END
#define AEKEY_ENTER                     0x0040000D ///< used to dispatch Key.ENTER
#define AEKEY_ESCAPE                    0x0040001B ///< used to dispatch Key.ESCAPE
#define AEKEY_HOME                      0x00400024 ///< used to dispatch Key.HOME
#define AEKEY_LEFT                      0x00400025 ///< used to dispatch Key.LEFT
#define AEKEY_PGDN                      0x00400022 ///< used to dispatch Key.PGDN
#define AEKEY_PGUP                      0x00400021 ///< used to dispatch Key.PGUP
#define AEKEY_RIGHT                     0x00400027 ///< used to dispatch Key.RIGHT
#define AEKEY_SHIFT                     0x00400010 ///< used to dispatch Key.SHIFT
#define AEKEY_SPACE                     0x00400020 ///< used to dispatch Key.SPACE
#define AEKEY_TAB                       0x00400009 ///< used to dispatch Key.TAB
#define AEKEY_UP                        0x00400026 ///< used to dispatch Key.UP

////////////////////////////////////////////////////////////////////////////////
// Special control symbols in AEKEY space
//
// Example usage:
//   Call StageWindow::DispatchKeyDown() with AEKEY_TERMINATEANIMATION to
//   unconditionally terminate the a specific StageWindow flash instance.
//   (Or use IStagecraft::DispatchKeyDown(AEKEY_TERMINATEANIMATION) to terminate
//   the currently active StageWindow).
//
#define AEKEY_TERMINATEANIMATION        0x00400100 ///< ctrl: unconditional terminate

////////////////////////////////////////////////////////////////////////////////
// Digital Home Key Codes
//
// Example Usage:
//    Dispatch an AEKEY_CODE code, and the equivalent
//    AS2 Key.CODE or AS3 Keyboard.CODE will result.
//      e.g. DispatchKeyDown(AEKEY_RED) results in Key.RED
//
#define AEKEY_POWER                     0x01000000 ///< basic power toggle
#define AEKEY_VOLUME_UP                 0x01000001 ///< volume up
#define AEKEY_VOLUME_DOWN               0x01000002 ///< volume down
#define AEKEY_VOLUME_MUTE               0x01000003 ///< volume mute
#define AEKEY_CHANNEL_UP                0x01000004 ///< channel up
#define AEKEY_CHANNEL_DOWN              0x01000005 ///< channel down
#define AEKEY_RECORD                    0x01000006 ///< record item or engage record transport mode
#define AEKEY_PLAY                      0x01000007 ///< engage play transport mode
#define AEKEY_PAUSE                     0x01000008 ///< engage pause transport mode
#define AEKEY_STOP                      0x01000009 ///< engage stop transport mode
#define AEKEY_FAST_FORWARD              0x0100000A ///< engage fast-forward transport mode
#define AEKEY_REWIND                    0x0100000B ///< engage rewind transport mode
#define AEKEY_SKIP_FORWARD              0x0100000C ///< quick skip ahead (usually 30 seconds)
#define AEKEY_SKIP_BACKWARD             0x0100000D ///< quick skip backward (usually 7-10 seconds)
#define AEKEY_NEXT                      0x0100000E ///< skip to next track or chapter
#define AEKEY_PREVIOUS                  0x0100000F ///< skip to previous track or chapter
#define AEKEY_LIVE                      0x01000010 ///< return to live [position in broadcast]
#define AEKEY_LAST                      0x01000011 ///< watch last channel or show watched
#define AEKEY_MENU                      0x01000012 ///< engage menu
#define AEKEY_INFO                      0x01000013 ///< info button
#define AEKEY_GUIDE                     0x01000014 ///< engage program guide
#define AEKEY_EXIT                      0x01000015 ///< exits current application mode
#define AEKEY_BACK                      0x01000016 ///< return back to previous page in application
#define AEKEY_AUDIO                     0x01000017 ///< select the audio mode
#define AEKEY_SUBTITLE                  0x01000018 ///< toggle subtitles
#define AEKEY_DVR                       0x01000019 ///< engage dvr application mode
#define AEKEY_VOD                       0x0100001A ///< engage video on demand
#define AEKEY_INPUT                     0x0100001B ///< cycle input
#define AEKEY_SETUP                     0x0100001C ///< engage setup application or menu
#define AEKEY_HELP                      0x0100001D ///< engage help application or context-sensitive help
#define AEKEY_MASTER_SHELL              0x0100001E ///< engage "Master Shell" e.g. TiVo or other vendor button
#define AEKEY_RED                       0x0100001F ///< red function key button
#define AEKEY_GREEN                     0x01000020 ///< green function key button
#define AEKEY_YELLOW                    0x01000021 ///< yellow function key button
#define AEKEY_BLUE                      0x01000022 ///< blue function key button
//[CJ] MTK_PORT
#define AEKEY_CLEAR                     0x01000023 ///< clear function key button
//--
#endif // #ifndef _INCLUDE_AE_STAGECRAFT_STAGECRAFTKEYDEFS_H

////////////////////////////////////////////////////////////////////////////////
//  LOG
////////////////////////////////////////////////////////////////////////////////
//  26-Oct-07   dwoodward   created
//  05-Mar-09   dwoodward   auto-regenerated by KeyName.as
