package com.jphilli85.ButtonTest;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ButtonTest extends Activity 
    implements OnKeyListener, OnClickListener/*, OnGenericMotionListener, OnTouchListener */{
    Configuration mConfig;
    Set<Integer> mKeys;
    RelativeLayout mLayout;
    TextView mKeyTextView;
    int mCurrentKeyCode;
    String mCurrentKey;
    boolean mKeyPressed;
    List<Integer> mKeysList;
    int mIndex;
    Button mSkipThisButton;
    Button mSkipRestButton;
    List<Integer> mSkipped;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mConfig = getResources().getConfiguration();
        mKeys = new HashSet<Integer>();
        
        mLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        mKeyTextView = (TextView) findViewById(R.id.keyTextView);        
        
        mSkipThisButton = (Button) findViewById(R.id.skipThisButton);
        mSkipRestButton = (Button) findViewById(R.id.skipRestButton);
        
        mSkipped = new ArrayList<Integer>();
        
        mSkipThisButton.setOnClickListener(this);
        mSkipRestButton.setOnClickListener(this);
        
        mLayout.setOnKeyListener(this);
        //mLayout.setOnGenericMotionListener(this);
        //mLayout.setOnTouchListener(this);
        
        doTest();
    }
    
    void setKey() {
        if (mIndex >= mKeysList.size()) {
            mKeyTextView.setText("Done!");
            return;
        }
        mCurrentKeyCode = mKeysList.get(mIndex);
        mCurrentKey = getKeyName(mCurrentKeyCode);
        mKeyTextView.setText(mCurrentKey);
    }
    
    void doTest() {
        //int kb  = mConfig.keyboard;
        //int nav = mConfig.navigation;
        //int touch = mConfig.touchscreen;
        addUniversalKeys();
        if (mConfig.keyboard == Configuration.KEYBOARD_QWERTY) addQwertyKeys();
        if (mConfig.keyboard == Configuration.KEYBOARD_12KEY) add12KeyKeys();
        if (mConfig.navigation == Configuration.NAVIGATION_DPAD) addDpadKeys();
        
        mKeysList = new ArrayList<Integer>(mKeys); 
        mIndex = 0;
        setKey();
        
            
//        for (int kc : mKeys) {
//            mCurrentKeyCode = kc;
//            mCurrentKey = getKeyName(kc);
//            mKeyTextView.setText(mCurrentKey);
//            mKeyPressed = false;
//            while (!mKeyPressed) {
//                
//            }
//        }
    }
    
//    class ListenForKeyTask extends AsyncTask<Integer, Void, Void> {
//        @Override
//        protected void onPreExecute() 
//        {                
//            super.onPreExecute();
//            mCurrentKeyCode = kc;
//            mCurrentKey = getKeyName(kc);
//            mKeyTextView.setText(mCurrentKey);    
//        }
//        
//        @Override
//        protected Void doInBackground(Integer... params) {
//            
//            return null;
//        }
//        
//    }
    
    void addKey(int keyCode) {
        if (KeyCharacterMap.deviceHasKey(keyCode)) mKeys.add(keyCode);
    }
    
    void addDpadKeys() {
        addKey(KeyEvent.KEYCODE_DPAD_CENTER);
        addKey(KeyEvent.KEYCODE_DPAD_DOWN);
        addKey(KeyEvent.KEYCODE_DPAD_LEFT);
        addKey(KeyEvent.KEYCODE_DPAD_RIGHT);
        addKey(KeyEvent.KEYCODE_DPAD_UP);
    }
    
    void addUniversalKeys() {        
        addKey(KeyEvent.KEYCODE_BACK);        
        //addKey(KeyEvent.KEYCODE_CALL);
        addKey(KeyEvent.KEYCODE_CAMERA);        
        //addKey(KeyEvent.KEYCODE_ENDCALL);        
        //addKey(KeyEvent.KEYCODE_HOME);        
        addKey(KeyEvent.KEYCODE_MENU);        
        //addKey(KeyEvent.KEYCODE_POWER);        
        addKey(KeyEvent.KEYCODE_SEARCH);        
        addKey(KeyEvent.KEYCODE_UNKNOWN);
        addKey(KeyEvent.KEYCODE_VOLUME_DOWN);
        addKey(KeyEvent.KEYCODE_VOLUME_MUTE);
        addKey(KeyEvent.KEYCODE_VOLUME_UP);         
    }
    
    void addQwertyKeys() {
        addKey(KeyEvent.KEYCODE_A);
        addKey(KeyEvent.KEYCODE_B);
        addKey(KeyEvent.KEYCODE_C);
        addKey(KeyEvent.KEYCODE_D);
        addKey(KeyEvent.KEYCODE_E);
        addKey(KeyEvent.KEYCODE_F);
        addKey(KeyEvent.KEYCODE_G);
        addKey(KeyEvent.KEYCODE_H);
        addKey(KeyEvent.KEYCODE_I);
        addKey(KeyEvent.KEYCODE_J);
        addKey(KeyEvent.KEYCODE_K);
        addKey(KeyEvent.KEYCODE_L);
        addKey(KeyEvent.KEYCODE_M);
        addKey(KeyEvent.KEYCODE_N);
        addKey(KeyEvent.KEYCODE_O);
        addKey(KeyEvent.KEYCODE_P);
        addKey(KeyEvent.KEYCODE_Q);
        addKey(KeyEvent.KEYCODE_R);
        addKey(KeyEvent.KEYCODE_S);
        addKey(KeyEvent.KEYCODE_T);
        addKey(KeyEvent.KEYCODE_U);
        addKey(KeyEvent.KEYCODE_V);
        addKey(KeyEvent.KEYCODE_W);
        addKey(KeyEvent.KEYCODE_X);
        addKey(KeyEvent.KEYCODE_Y);
        addKey(KeyEvent.KEYCODE_Z);        
        
        addKey(KeyEvent.KEYCODE_0);
        addKey(KeyEvent.KEYCODE_1);
        addKey(KeyEvent.KEYCODE_2);
        addKey(KeyEvent.KEYCODE_3);
        addKey(KeyEvent.KEYCODE_4);
        addKey(KeyEvent.KEYCODE_5);
        addKey(KeyEvent.KEYCODE_6);
        addKey(KeyEvent.KEYCODE_7);
        addKey(KeyEvent.KEYCODE_8);
        addKey(KeyEvent.KEYCODE_9);
        
        addKey(KeyEvent.KEYCODE_ALT_LEFT);
        addKey(KeyEvent.KEYCODE_ALT_RIGHT);        
        addKey(KeyEvent.KEYCODE_APOSTROPHE);
        addKey(KeyEvent.KEYCODE_AT);
        addKey(KeyEvent.KEYCODE_BACKSLASH);        
        addKey(KeyEvent.KEYCODE_CAPS_LOCK);       
        addKey(KeyEvent.KEYCODE_COMMA);
        addKey(KeyEvent.KEYCODE_CTRL_LEFT);
        addKey(KeyEvent.KEYCODE_CTRL_RIGHT);
        addKey(KeyEvent.KEYCODE_DEL);        
        addKey(KeyEvent.KEYCODE_ENTER);
        addKey(KeyEvent.KEYCODE_EQUALS);
        addKey(KeyEvent.KEYCODE_ESCAPE);
        addKey(KeyEvent.KEYCODE_FUNCTION);
        addKey(KeyEvent.KEYCODE_GRAVE);
        addKey(KeyEvent.KEYCODE_LEFT_BRACKET);        
        addKey(KeyEvent.KEYCODE_MINUS);               
        addKey(KeyEvent.KEYCODE_PAGE_DOWN);
        addKey(KeyEvent.KEYCODE_PAGE_UP);
        addKey(KeyEvent.KEYCODE_PERIOD);
        addKey(KeyEvent.KEYCODE_PLUS);        
        addKey(KeyEvent.KEYCODE_RIGHT_BRACKET);        
        addKey(KeyEvent.KEYCODE_SEMICOLON);        
        addKey(KeyEvent.KEYCODE_SHIFT_LEFT);
        addKey(KeyEvent.KEYCODE_SHIFT_RIGHT);
        addKey(KeyEvent.KEYCODE_SLASH);
        addKey(KeyEvent.KEYCODE_SOFT_LEFT);
        addKey(KeyEvent.KEYCODE_SOFT_RIGHT);
        addKey(KeyEvent.KEYCODE_SPACE);
        addKey(KeyEvent.KEYCODE_STAR);        
        addKey(KeyEvent.KEYCODE_TAB);        
    }
    
    void add12KeyKeys() {               
        addKey(KeyEvent.KEYCODE_0);
        addKey(KeyEvent.KEYCODE_1);
        addKey(KeyEvent.KEYCODE_2);
        addKey(KeyEvent.KEYCODE_3);
        addKey(KeyEvent.KEYCODE_4);
        addKey(KeyEvent.KEYCODE_5);
        addKey(KeyEvent.KEYCODE_6);
        addKey(KeyEvent.KEYCODE_7);
        addKey(KeyEvent.KEYCODE_8);
        addKey(KeyEvent.KEYCODE_9);
        addKey(KeyEvent.KEYCODE_POUND);        
        addKey(KeyEvent.KEYCODE_STAR);        
    }
    
    String getKeyName(int keyCode) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_A: return "A";
            case KeyEvent.KEYCODE_B: return "B";
            case KeyEvent.KEYCODE_C: return "C";
            case KeyEvent.KEYCODE_D: return "D";
            case KeyEvent.KEYCODE_E: return "E";
            case KeyEvent.KEYCODE_F: return "F";
            case KeyEvent.KEYCODE_G: return "G";
            case KeyEvent.KEYCODE_H: return "H";
            case KeyEvent.KEYCODE_I: return "I";
            case KeyEvent.KEYCODE_J: return "J";
            case KeyEvent.KEYCODE_K: return "K";
            case KeyEvent.KEYCODE_L: return "L";
            case KeyEvent.KEYCODE_M: return "M";
            case KeyEvent.KEYCODE_N: return "N";
            case KeyEvent.KEYCODE_O: return "O";
            case KeyEvent.KEYCODE_P: return "P";
            case KeyEvent.KEYCODE_Q: return "Q";
            case KeyEvent.KEYCODE_R: return "R";
            case KeyEvent.KEYCODE_S: return "S";
            case KeyEvent.KEYCODE_T: return "T";
            case KeyEvent.KEYCODE_U: return "U";
            case KeyEvent.KEYCODE_V: return "V";
            case KeyEvent.KEYCODE_W: return "W";
            case KeyEvent.KEYCODE_X: return "X";
            case KeyEvent.KEYCODE_Y: return "Y";
            case KeyEvent.KEYCODE_Z: return "Z";        
            
            case KeyEvent.KEYCODE_0: return "0";
            case KeyEvent.KEYCODE_1: return "1";
            case KeyEvent.KEYCODE_2: return "2";
            case KeyEvent.KEYCODE_3: return "3";
            case KeyEvent.KEYCODE_4: return "4";
            case KeyEvent.KEYCODE_5: return "5";
            case KeyEvent.KEYCODE_6: return "6";
            case KeyEvent.KEYCODE_7: return "7";
            case KeyEvent.KEYCODE_8: return "8";
            case KeyEvent.KEYCODE_9: return "9";
            
            case KeyEvent.KEYCODE_ALT_LEFT: return "Left Alt";
            case KeyEvent.KEYCODE_ALT_RIGHT: return "Right Alt";        
            case KeyEvent.KEYCODE_APOSTROPHE: return "'";
            case KeyEvent.KEYCODE_APP_SWITCH: return "App Switch";
            case KeyEvent.KEYCODE_AT: return "@";
            case KeyEvent.KEYCODE_AVR_INPUT: return "AVR Input";
            case KeyEvent.KEYCODE_AVR_POWER: return "AVR Power";
            case KeyEvent.KEYCODE_BACK: return "Back";
            case KeyEvent.KEYCODE_BACKSLASH: return "\\";
            case KeyEvent.KEYCODE_BOOKMARK: return "Bookmark";
            case KeyEvent.KEYCODE_BREAK: return "Break";
            case KeyEvent.KEYCODE_BUTTON_1: return "Button 1";
            case KeyEvent.KEYCODE_BUTTON_2: return "Button 2";
            case KeyEvent.KEYCODE_BUTTON_3: return "Button 3";
            case KeyEvent.KEYCODE_BUTTON_4: return "Button 4";
            case KeyEvent.KEYCODE_BUTTON_5: return "Button 5";
            case KeyEvent.KEYCODE_BUTTON_6: return "Button 6";
            case KeyEvent.KEYCODE_BUTTON_7: return "Button 7";
            case KeyEvent.KEYCODE_BUTTON_8: return "Button 8";
            case KeyEvent.KEYCODE_BUTTON_9: return "Button 9";
            case KeyEvent.KEYCODE_BUTTON_10: return "Button 10";
            case KeyEvent.KEYCODE_BUTTON_11: return "Button 11";
            case KeyEvent.KEYCODE_BUTTON_12: return "Button 12";
            case KeyEvent.KEYCODE_BUTTON_13: return "Button 13";
            case KeyEvent.KEYCODE_BUTTON_14: return "Button 14";
            case KeyEvent.KEYCODE_BUTTON_15: return "Button 15";
            case KeyEvent.KEYCODE_BUTTON_16: return "Button 16";
            case KeyEvent.KEYCODE_BUTTON_A: return "A Button";
            case KeyEvent.KEYCODE_BUTTON_B: return "B Button";
            case KeyEvent.KEYCODE_BUTTON_C: return "C Button";
            case KeyEvent.KEYCODE_BUTTON_L1: return "L1 Button";
            case KeyEvent.KEYCODE_BUTTON_L2: return "L2 Button";
            case KeyEvent.KEYCODE_BUTTON_MODE: return "Mode Button";
            case KeyEvent.KEYCODE_BUTTON_R1: return "R1 Button";
            case KeyEvent.KEYCODE_BUTTON_R2: return "R2 Button";
            case KeyEvent.KEYCODE_BUTTON_SELECT: return "Select Button";
            case KeyEvent.KEYCODE_BUTTON_START: return "Start Button";
            case KeyEvent.KEYCODE_BUTTON_THUMBL: return "Left Thumb Button";
            case KeyEvent.KEYCODE_BUTTON_THUMBR: return "Right Thumb Button";
            case KeyEvent.KEYCODE_BUTTON_X: return "X Button";
            case KeyEvent.KEYCODE_BUTTON_Y: return "Y Button";
            case KeyEvent.KEYCODE_BUTTON_Z: return "Z Button";
            //case KeyEvent.KEYCODE_CALL: return "Call";
            case KeyEvent.KEYCODE_CAMERA: return "Camera";
            case KeyEvent.KEYCODE_CAPS_LOCK: return "Caps Lock";
            case KeyEvent.KEYCODE_CAPTIONS: return "Captions";
            case KeyEvent.KEYCODE_CHANNEL_DOWN: return "Channel Down";
            case KeyEvent.KEYCODE_CHANNEL_UP: return "Channel Up";
            case KeyEvent.KEYCODE_CLEAR: return "Clear";
            case KeyEvent.KEYCODE_COMMA: return ",";
            case KeyEvent.KEYCODE_CTRL_LEFT: return "Left Control";
            case KeyEvent.KEYCODE_CTRL_RIGHT: return "Right Control";
            case KeyEvent.KEYCODE_DEL: return "Delete";
            case KeyEvent.KEYCODE_DPAD_CENTER: return "DPad Center";
            case KeyEvent.KEYCODE_DPAD_DOWN: return "DPad Down";
            case KeyEvent.KEYCODE_DPAD_LEFT: return "DPad Left";
            case KeyEvent.KEYCODE_DPAD_RIGHT: return "DPad Right";
            case KeyEvent.KEYCODE_DPAD_UP: return "DPad Up";
            case KeyEvent.KEYCODE_DVR: return "DVR";
            //case KeyEvent.KEYCODE_ENDCALL: return "End Call";
            case KeyEvent.KEYCODE_ENTER: return "Enter";
            case KeyEvent.KEYCODE_ENVELOPE: return "Envelope";
            case KeyEvent.KEYCODE_EQUALS: return "=";
            case KeyEvent.KEYCODE_ESCAPE: return "Escape";
            case KeyEvent.KEYCODE_EXPLORER: return "Explorer";
            case KeyEvent.KEYCODE_F1: return "F1";
            case KeyEvent.KEYCODE_F2: return "F2";
            case KeyEvent.KEYCODE_F3: return "F3";
            case KeyEvent.KEYCODE_F4: return "F4";
            case KeyEvent.KEYCODE_F5: return "F5";
            case KeyEvent.KEYCODE_F6: return "F6";
            case KeyEvent.KEYCODE_F7: return "F7";
            case KeyEvent.KEYCODE_F8: return "F8";
            case KeyEvent.KEYCODE_F9: return "F9";
            case KeyEvent.KEYCODE_F10: return "F10";
            case KeyEvent.KEYCODE_F11: return "F11";
            case KeyEvent.KEYCODE_F12: return "F12";
            case KeyEvent.KEYCODE_FOCUS: return "Focus";
            case KeyEvent.KEYCODE_FORWARD: return "Forward";
            case KeyEvent.KEYCODE_FORWARD_DEL: return "Forward Delete";
            case KeyEvent.KEYCODE_FUNCTION: return "Function";
            case KeyEvent.KEYCODE_GRAVE: return "`";
            case KeyEvent.KEYCODE_GUIDE: return "Guide";
            case KeyEvent.KEYCODE_HEADSETHOOK: return "Headset Hook";
            //case KeyEvent.KEYCODE_HOME: return "Home";
            case KeyEvent.KEYCODE_INFO: return "Info";
            case KeyEvent.KEYCODE_INSERT: return "Insert";
            case KeyEvent.KEYCODE_LEFT_BRACKET: return "Left Bracket";
            case KeyEvent.KEYCODE_MEDIA_CLOSE: return "Media Close";
            case KeyEvent.KEYCODE_MEDIA_EJECT: return "Eject";
            case KeyEvent.KEYCODE_MEDIA_FAST_FORWARD: return "Fast Forward";
            case KeyEvent.KEYCODE_MEDIA_NEXT: return "Next";
            case KeyEvent.KEYCODE_MEDIA_PAUSE: return "Pause";
            case KeyEvent.KEYCODE_MEDIA_PLAY: return "Play";
            case KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE: return "Play Pause";
            case KeyEvent.KEYCODE_MEDIA_PREVIOUS: return "Previous";
            case KeyEvent.KEYCODE_MEDIA_RECORD: return "Record";
            case KeyEvent.KEYCODE_MEDIA_REWIND: return "Rewind";
            case KeyEvent.KEYCODE_MEDIA_STOP: return "Stop";
            case KeyEvent.KEYCODE_MENU: return "Menu";
            case KeyEvent.KEYCODE_META_LEFT: return "Meta Left";
            case KeyEvent.KEYCODE_META_RIGHT: return "Meta Right";
            case KeyEvent.KEYCODE_MINUS: return "Minus";
            case KeyEvent.KEYCODE_MOVE_END: return "Move End";
            case KeyEvent.KEYCODE_MOVE_HOME: return "Move Home";
            case KeyEvent.KEYCODE_MUTE: return "Mute";
            case KeyEvent.KEYCODE_NOTIFICATION: return "Notification";
            case KeyEvent.KEYCODE_NUM: return "Num";
            case KeyEvent.KEYCODE_NUM_LOCK: return "Num Lock";
            case KeyEvent.KEYCODE_NUMPAD_0: return "Numpad 0";
            case KeyEvent.KEYCODE_NUMPAD_1: return "Numpad 1";
            case KeyEvent.KEYCODE_NUMPAD_2: return "Numpad 2";
            case KeyEvent.KEYCODE_NUMPAD_3: return "Numpad 3";
            case KeyEvent.KEYCODE_NUMPAD_4: return "Numpad 4";
            case KeyEvent.KEYCODE_NUMPAD_5: return "Numpad 5";
            case KeyEvent.KEYCODE_NUMPAD_6: return "Numpad 6";
            case KeyEvent.KEYCODE_NUMPAD_7: return "Numpad 7";
            case KeyEvent.KEYCODE_NUMPAD_8: return "Numpad 8";
            case KeyEvent.KEYCODE_NUMPAD_9: return "Numpad 9";
            case KeyEvent.KEYCODE_NUMPAD_ADD: return "Numpad +";
            case KeyEvent.KEYCODE_NUMPAD_COMMA: return "Numpad ,";
            case KeyEvent.KEYCODE_NUMPAD_DIVIDE: return "Numpad /";
            case KeyEvent.KEYCODE_NUMPAD_DOT: return "Numpad .";
            case KeyEvent.KEYCODE_NUMPAD_ENTER: return "Numpad Enter";
            case KeyEvent.KEYCODE_NUMPAD_EQUALS: return "Numpad =";
            case KeyEvent.KEYCODE_NUMPAD_LEFT_PAREN: return "Numpad (";
            case KeyEvent.KEYCODE_NUMPAD_MULTIPLY: return "Numpad *";
            case KeyEvent.KEYCODE_NUMPAD_RIGHT_PAREN: return "Numpad )";
            case KeyEvent.KEYCODE_NUMPAD_SUBTRACT: return "Numpad -";
            case KeyEvent.KEYCODE_PAGE_DOWN: return "Page Down";
            case KeyEvent.KEYCODE_PAGE_UP: return "Page Up";
            case KeyEvent.KEYCODE_PERIOD: return ".";
            case KeyEvent.KEYCODE_PICTSYMBOLS: return "Picture Symbols";
            case KeyEvent.KEYCODE_PLUS: return "+";
            case KeyEvent.KEYCODE_POUND: return "#";
            //case KeyEvent.KEYCODE_POWER: return "Power";
            case KeyEvent.KEYCODE_PROG_BLUE: return "Blue";
            case KeyEvent.KEYCODE_PROG_GREEN: return "Green";
            case KeyEvent.KEYCODE_PROG_RED: return "Red";
            case KeyEvent.KEYCODE_PROG_YELLOW: return "Yellow";
            case KeyEvent.KEYCODE_RIGHT_BRACKET: return "Right Bracket";
            case KeyEvent.KEYCODE_SCROLL_LOCK: return "Scroll Lock";
            case KeyEvent.KEYCODE_SEARCH: return "Search";
            case KeyEvent.KEYCODE_SEMICOLON: return ";";
            case KeyEvent.KEYCODE_SETTINGS: return "Settings";
            case KeyEvent.KEYCODE_SHIFT_LEFT: return "Left Shift";
            case KeyEvent.KEYCODE_SHIFT_RIGHT: return "Right Shift";
            case KeyEvent.KEYCODE_SLASH: return "/";
            case KeyEvent.KEYCODE_SOFT_LEFT: return "Left Soft Key";
            case KeyEvent.KEYCODE_SOFT_RIGHT: return "Right Soft Key";
            case KeyEvent.KEYCODE_SPACE: return "(Space)";
            case KeyEvent.KEYCODE_STAR: return "*";
            case KeyEvent.KEYCODE_STB_INPUT: return "STB Input";
            case KeyEvent.KEYCODE_STB_POWER: return "STB Power";
            case KeyEvent.KEYCODE_SWITCH_CHARSET: return "Switch Charset";
            case KeyEvent.KEYCODE_SYM: return "Symbol";
            case KeyEvent.KEYCODE_SYSRQ: return "System Request";
            case KeyEvent.KEYCODE_TAB: return "Tab";
            case KeyEvent.KEYCODE_TV: return "TV";
            case KeyEvent.KEYCODE_TV_INPUT: return "TV Input";
            case KeyEvent.KEYCODE_TV_POWER: return "TV Power";
            case KeyEvent.KEYCODE_UNKNOWN: return "Unknown";
            case KeyEvent.KEYCODE_VOLUME_DOWN: return "Volume Down";
            case KeyEvent.KEYCODE_VOLUME_MUTE: return "Volume Mute";
            case KeyEvent.KEYCODE_VOLUME_UP: return "Volume Up";
            case KeyEvent.KEYCODE_WINDOW: return "Window";
            case KeyEvent.KEYCODE_ZOOM_IN: return "Zoom In";
            case KeyEvent.KEYCODE_ZOOM_OUT: return "Zoom Out";     
            default: return "Unknown KeyCode";
        }
    }
    
    void addAllKeys() {     
        //KeyCharacterMap km = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_0).getKeyCharacterMap();
        
        addKey(KeyEvent.KEYCODE_A);
        addKey(KeyEvent.KEYCODE_B);
        addKey(KeyEvent.KEYCODE_C);
        addKey(KeyEvent.KEYCODE_D);
        addKey(KeyEvent.KEYCODE_E);
        addKey(KeyEvent.KEYCODE_F);
        addKey(KeyEvent.KEYCODE_G);
        addKey(KeyEvent.KEYCODE_H);
        addKey(KeyEvent.KEYCODE_I);
        addKey(KeyEvent.KEYCODE_J);
        addKey(KeyEvent.KEYCODE_K);
        addKey(KeyEvent.KEYCODE_L);
        addKey(KeyEvent.KEYCODE_M);
        addKey(KeyEvent.KEYCODE_N);
        addKey(KeyEvent.KEYCODE_O);
        addKey(KeyEvent.KEYCODE_P);
        addKey(KeyEvent.KEYCODE_Q);
        addKey(KeyEvent.KEYCODE_R);
        addKey(KeyEvent.KEYCODE_S);
        addKey(KeyEvent.KEYCODE_T);
        addKey(KeyEvent.KEYCODE_U);
        addKey(KeyEvent.KEYCODE_V);
        addKey(KeyEvent.KEYCODE_W);
        addKey(KeyEvent.KEYCODE_X);
        addKey(KeyEvent.KEYCODE_Y);
        addKey(KeyEvent.KEYCODE_Z);        
        
        addKey(KeyEvent.KEYCODE_0);
        addKey(KeyEvent.KEYCODE_1);
        addKey(KeyEvent.KEYCODE_2);
        addKey(KeyEvent.KEYCODE_3);
        addKey(KeyEvent.KEYCODE_4);
        addKey(KeyEvent.KEYCODE_5);
        addKey(KeyEvent.KEYCODE_6);
        addKey(KeyEvent.KEYCODE_7);
        addKey(KeyEvent.KEYCODE_8);
        addKey(KeyEvent.KEYCODE_9);
        
        addKey(KeyEvent.KEYCODE_ALT_LEFT);
        addKey(KeyEvent.KEYCODE_ALT_RIGHT);        
        addKey(KeyEvent.KEYCODE_APOSTROPHE);
        addKey(KeyEvent.KEYCODE_APP_SWITCH);
        addKey(KeyEvent.KEYCODE_AT);
        addKey(KeyEvent.KEYCODE_AVR_INPUT);
        addKey(KeyEvent.KEYCODE_AVR_POWER);
        addKey(KeyEvent.KEYCODE_BACK);
        addKey(KeyEvent.KEYCODE_BACKSLASH);
        addKey(KeyEvent.KEYCODE_BOOKMARK);
        addKey(KeyEvent.KEYCODE_BREAK);
        addKey(KeyEvent.KEYCODE_BUTTON_1);
        addKey(KeyEvent.KEYCODE_BUTTON_2);
        addKey(KeyEvent.KEYCODE_BUTTON_3);
        addKey(KeyEvent.KEYCODE_BUTTON_4);
        addKey(KeyEvent.KEYCODE_BUTTON_5);
        addKey(KeyEvent.KEYCODE_BUTTON_6);
        addKey(KeyEvent.KEYCODE_BUTTON_7);
        addKey(KeyEvent.KEYCODE_BUTTON_8);
        addKey(KeyEvent.KEYCODE_BUTTON_9);
        addKey(KeyEvent.KEYCODE_BUTTON_10);
        addKey(KeyEvent.KEYCODE_BUTTON_11);
        addKey(KeyEvent.KEYCODE_BUTTON_12);
        addKey(KeyEvent.KEYCODE_BUTTON_13);
        addKey(KeyEvent.KEYCODE_BUTTON_14);
        addKey(KeyEvent.KEYCODE_BUTTON_15);
        addKey(KeyEvent.KEYCODE_BUTTON_16);
        addKey(KeyEvent.KEYCODE_BUTTON_A);
        addKey(KeyEvent.KEYCODE_BUTTON_B);
        addKey(KeyEvent.KEYCODE_BUTTON_C);
        addKey(KeyEvent.KEYCODE_BUTTON_L1);
        addKey(KeyEvent.KEYCODE_BUTTON_L2);
        addKey(KeyEvent.KEYCODE_BUTTON_MODE);
        addKey(KeyEvent.KEYCODE_BUTTON_R1);
        addKey(KeyEvent.KEYCODE_BUTTON_R2);
        addKey(KeyEvent.KEYCODE_BUTTON_SELECT);
        addKey(KeyEvent.KEYCODE_BUTTON_START);
        addKey(KeyEvent.KEYCODE_BUTTON_THUMBL);
        addKey(KeyEvent.KEYCODE_BUTTON_THUMBR);
        addKey(KeyEvent.KEYCODE_BUTTON_X);
        addKey(KeyEvent.KEYCODE_BUTTON_Y);
        addKey(KeyEvent.KEYCODE_BUTTON_Z);
        //addKey(KeyEvent.KEYCODE_CALL);
        addKey(KeyEvent.KEYCODE_CAMERA);
        addKey(KeyEvent.KEYCODE_CAPS_LOCK);
        addKey(KeyEvent.KEYCODE_CAPTIONS);
        addKey(KeyEvent.KEYCODE_CHANNEL_DOWN);
        addKey(KeyEvent.KEYCODE_CHANNEL_UP);
        addKey(KeyEvent.KEYCODE_CLEAR);
        addKey(KeyEvent.KEYCODE_COMMA);
        addKey(KeyEvent.KEYCODE_CTRL_LEFT);
        addKey(KeyEvent.KEYCODE_CTRL_RIGHT);
        addKey(KeyEvent.KEYCODE_DEL);
        addKey(KeyEvent.KEYCODE_DPAD_CENTER);
        addKey(KeyEvent.KEYCODE_DPAD_DOWN);
        addKey(KeyEvent.KEYCODE_DPAD_LEFT);
        addKey(KeyEvent.KEYCODE_DPAD_RIGHT);
        addKey(KeyEvent.KEYCODE_DPAD_UP);
        addKey(KeyEvent.KEYCODE_DVR);
        //addKey(KeyEvent.KEYCODE_ENDCALL);
        addKey(KeyEvent.KEYCODE_ENTER);
        addKey(KeyEvent.KEYCODE_ENVELOPE);
        addKey(KeyEvent.KEYCODE_EQUALS);
        addKey(KeyEvent.KEYCODE_ESCAPE);
        addKey(KeyEvent.KEYCODE_EXPLORER);
        addKey(KeyEvent.KEYCODE_F1);
        addKey(KeyEvent.KEYCODE_F2);
        addKey(KeyEvent.KEYCODE_F3);
        addKey(KeyEvent.KEYCODE_F4);
        addKey(KeyEvent.KEYCODE_F5);
        addKey(KeyEvent.KEYCODE_F6);
        addKey(KeyEvent.KEYCODE_F7);
        addKey(KeyEvent.KEYCODE_F8);
        addKey(KeyEvent.KEYCODE_F9);
        addKey(KeyEvent.KEYCODE_F10);
        addKey(KeyEvent.KEYCODE_F11);
        addKey(KeyEvent.KEYCODE_F12);
        addKey(KeyEvent.KEYCODE_FOCUS);
        addKey(KeyEvent.KEYCODE_FORWARD);
        addKey(KeyEvent.KEYCODE_FORWARD_DEL);
        addKey(KeyEvent.KEYCODE_FUNCTION);
        addKey(KeyEvent.KEYCODE_GRAVE);
        addKey(KeyEvent.KEYCODE_GUIDE);
        addKey(KeyEvent.KEYCODE_HEADSETHOOK);
        //addKey(KeyEvent.KEYCODE_HOME);
        addKey(KeyEvent.KEYCODE_INFO);
        addKey(KeyEvent.KEYCODE_INSERT);
        addKey(KeyEvent.KEYCODE_LEFT_BRACKET);
        addKey(KeyEvent.KEYCODE_MEDIA_CLOSE);
        addKey(KeyEvent.KEYCODE_MEDIA_EJECT);
        addKey(KeyEvent.KEYCODE_MEDIA_FAST_FORWARD);
        addKey(KeyEvent.KEYCODE_MEDIA_NEXT);
        addKey(KeyEvent.KEYCODE_MEDIA_PAUSE);
        addKey(KeyEvent.KEYCODE_MEDIA_PLAY);
        addKey(KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE);
        addKey(KeyEvent.KEYCODE_MEDIA_PREVIOUS);
        addKey(KeyEvent.KEYCODE_MEDIA_RECORD);
        addKey(KeyEvent.KEYCODE_MEDIA_REWIND);
        addKey(KeyEvent.KEYCODE_MEDIA_STOP);
        addKey(KeyEvent.KEYCODE_MENU);
        addKey(KeyEvent.KEYCODE_META_LEFT);
        addKey(KeyEvent.KEYCODE_META_RIGHT);
        addKey(KeyEvent.KEYCODE_MINUS);
        addKey(KeyEvent.KEYCODE_MOVE_END);
        addKey(KeyEvent.KEYCODE_MOVE_HOME);
        addKey(KeyEvent.KEYCODE_MUTE);
        addKey(KeyEvent.KEYCODE_NOTIFICATION);
        addKey(KeyEvent.KEYCODE_NUM);
        addKey(KeyEvent.KEYCODE_NUM_LOCK);
        addKey(KeyEvent.KEYCODE_NUMPAD_0);
        addKey(KeyEvent.KEYCODE_NUMPAD_1);
        addKey(KeyEvent.KEYCODE_NUMPAD_2);
        addKey(KeyEvent.KEYCODE_NUMPAD_3);
        addKey(KeyEvent.KEYCODE_NUMPAD_4);
        addKey(KeyEvent.KEYCODE_NUMPAD_5);
        addKey(KeyEvent.KEYCODE_NUMPAD_6);
        addKey(KeyEvent.KEYCODE_NUMPAD_7);
        addKey(KeyEvent.KEYCODE_NUMPAD_8);
        addKey(KeyEvent.KEYCODE_NUMPAD_9);
        addKey(KeyEvent.KEYCODE_NUMPAD_ADD);
        addKey(KeyEvent.KEYCODE_NUMPAD_COMMA);
        addKey(KeyEvent.KEYCODE_NUMPAD_DIVIDE);
        addKey(KeyEvent.KEYCODE_NUMPAD_DOT);
        addKey(KeyEvent.KEYCODE_NUMPAD_ENTER);
        addKey(KeyEvent.KEYCODE_NUMPAD_EQUALS);
        addKey(KeyEvent.KEYCODE_NUMPAD_LEFT_PAREN);
        addKey(KeyEvent.KEYCODE_NUMPAD_MULTIPLY);
        addKey(KeyEvent.KEYCODE_NUMPAD_RIGHT_PAREN);
        addKey(KeyEvent.KEYCODE_NUMPAD_SUBTRACT);
        addKey(KeyEvent.KEYCODE_PAGE_DOWN);
        addKey(KeyEvent.KEYCODE_PAGE_UP);
        addKey(KeyEvent.KEYCODE_PERIOD);
        addKey(KeyEvent.KEYCODE_PICTSYMBOLS);
        addKey(KeyEvent.KEYCODE_PLUS);
        addKey(KeyEvent.KEYCODE_POUND);
        //addKey(KeyEvent.KEYCODE_POWER);
        addKey(KeyEvent.KEYCODE_PROG_BLUE);
        addKey(KeyEvent.KEYCODE_PROG_GREEN);
        addKey(KeyEvent.KEYCODE_PROG_RED);
        addKey(KeyEvent.KEYCODE_PROG_YELLOW);
        addKey(KeyEvent.KEYCODE_RIGHT_BRACKET);
        addKey(KeyEvent.KEYCODE_SCROLL_LOCK);
        addKey(KeyEvent.KEYCODE_SEARCH);
        addKey(KeyEvent.KEYCODE_SEMICOLON);
        addKey(KeyEvent.KEYCODE_SETTINGS);
        addKey(KeyEvent.KEYCODE_SHIFT_LEFT);
        addKey(KeyEvent.KEYCODE_SHIFT_RIGHT);
        addKey(KeyEvent.KEYCODE_SLASH);
        addKey(KeyEvent.KEYCODE_SOFT_LEFT);
        addKey(KeyEvent.KEYCODE_SOFT_RIGHT);
        addKey(KeyEvent.KEYCODE_SPACE);
        addKey(KeyEvent.KEYCODE_STAR);
        addKey(KeyEvent.KEYCODE_STB_INPUT);
        addKey(KeyEvent.KEYCODE_STB_POWER);
        addKey(KeyEvent.KEYCODE_SWITCH_CHARSET);
        addKey(KeyEvent.KEYCODE_SYM);
        addKey(KeyEvent.KEYCODE_SYSRQ);
        addKey(KeyEvent.KEYCODE_TAB);
        addKey(KeyEvent.KEYCODE_TV);
        addKey(KeyEvent.KEYCODE_TV_INPUT);
        addKey(KeyEvent.KEYCODE_TV_POWER);
        addKey(KeyEvent.KEYCODE_UNKNOWN);
        addKey(KeyEvent.KEYCODE_VOLUME_DOWN);
        addKey(KeyEvent.KEYCODE_VOLUME_MUTE);
        addKey(KeyEvent.KEYCODE_VOLUME_UP);
        addKey(KeyEvent.KEYCODE_WINDOW);
        addKey(KeyEvent.KEYCODE_ZOOM_IN);
        addKey(KeyEvent.KEYCODE_ZOOM_OUT);        
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        //mKeyTextView.setText(String.valueOf(event.getDisplayLabel()));
        return false;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == mCurrentKeyCode) {
            ++mIndex;
            setKey();
        }
        
//        mKeyTextView.setText(String.valueOf(Integer.valueOf(event.getDisplayLabel())) + " " +
//        		getKeyName(keyCode) + "\n");
//        for (int kc : mKeys) mKeyTextView.append(getKeyName(kc) + ", ");
        //mKeyTextView.setText(String.valueOf(mKeys.size()));

        return true;//super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        if ((Button)v == mSkipThisButton) { 
            mSkipped.add(mCurrentKeyCode);
            ++mIndex;
            setKey();
        }
        if ((Button)v == mSkipRestButton) {
            for (int i=mIndex; i<mKeysList.size(); ++i) mSkipped.add(mKeysList.get(i));
            mIndex = mKeysList.size();
            setKey();
        }
        
    }
    
    

//    @Override
//    public boolean onGenericMotion(View v, MotionEvent event) {
//        //if (event.getSource(InputDevice.))
//        //if (event.getAction() == MotionEvent.ACTION_HOVER_MOVE)
//        Toast.makeText(this, "action = " + event.getAction(), Toast.LENGTH_LONG).show();
//        return false;
//    }
//
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        // TODO Auto-generated method stub
//        return false;
//    }
//    
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        Toast.makeText(this, "action = " + event.getAction(), Toast.LENGTH_LONG).show();
//        return false;
//    }
}