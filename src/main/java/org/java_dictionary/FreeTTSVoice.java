package org.java_dictionary;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FreeTTSVoice {
    public Voice voice;

    public FreeTTSVoice(String name) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        this.voice = VoiceManager.getInstance().getVoice(name);
        this.voice.allocate();
    }

    public void speak(String text) {
        this.voice.speak(text);
    }
}
