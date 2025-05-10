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

    public static KeyMap<String> listKeyMap() {
        KeyMap<String> km = new KeyMap<>();

        // Bind standard arrow key sequences
        km.bind("up", "\033[A"); // Standard VT100
        km.bind("down", "\033[B"); // Standard VT100

        // Add alternative arrow key sequences that might be used by different terminals
        km.bind("up", "\033OA"); // Alternative sequence (e.g., used in some xterm configs)
        km.bind("down", "\033OB"); // Alternative sequence

        // Add decoded key sequences
        km.bind("up", "^[[A"); // Another common representation
        km.bind("down", "^[[B"); // Another common representation

        // Bind other navigation keys
        km.bind("space", " ");
        km.bind("enter", "\r");
        km.bind("enter", "\n"); // Some terminals may send LF instead of CR

        return km;
    }
}
