package com.example.demogaru;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
public class Sound {
    public static void Vocal(String text) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceManager Sound = VoiceManager.getInstance();
        Voice sound = Sound.getVoice("kevin16");
        if (sound == null) {
            System.err.println("Không thể tìm thấy kevin16");
            System.exit(1);
        }
        sound.allocate();
        sound.speak(text);
        sound.deallocate();
    }
}