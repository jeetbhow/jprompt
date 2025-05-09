package jprompt.keymap;

import org.jline.keymap.KeyMap;

public class KeyMaps {

    // Return a keymap that lets you press space to toggle.
    public static KeyMap<String> toggleKeyMap() {
        KeyMap<String> km = new KeyMap<>();
        km.bind("space", " ");
        km.bind("enter", "\r");
        return km;
    }
}
