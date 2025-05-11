package jprompt.prompt.number;

import org.jline.keymap.KeyMap;

import jprompt.keymap.KeyMaps;
import jprompt.prompt.Prompt;
import jprompt.terminal.PromptTerminal;

public class RatingPrompt extends Prompt<Integer> {
    private static final char SEPARATOR = '─';
    private static final char CIRCLE = '◯';
    private static final char FILLED_CIRCLE = '●';
    private static final int NUM_DENOMINATIONS = 5;
    private static final int NUM_SEPERATORS = 5;
    public static final int MAX_VALUE = 5;
    public static final int MIN_VALUE = 1;

    int value;

    public RatingPrompt(PromptTerminal terminal, String prompt) {
        super(terminal, prompt);
        this.value = 3;
    }

    @Override
    public void run() {
        KeyMap<String> km = KeyMaps.defaulyKeyMap();
        render();
        terminal.flush();
        while (true) {
            String key = terminal.readBinding(km);
            switch (key) {
                case "left" -> value = Math.max(value - 1, 1);
                case "right" -> value = Math.min(value + 1, 5);
                case "enter" -> {
                    submit();
                    return;
                }
            }
            terminal.reset();
            render();
            terminal.flush();
        }
    }

    public void render() {
        terminal.println(prompt);
        for (int i = 0; i < NUM_DENOMINATIONS; i++) {
            if ((i + 1) == value) {
                terminal.print(FILLED_CIRCLE);
            } else {
                terminal.print(CIRCLE);
            }
            if (i < NUM_DENOMINATIONS - 1) {
                terminal.print(String.valueOf(SEPARATOR).repeat(NUM_SEPERATORS));
            }
        }
        terminal.println("");
        for (int i = 0; i < NUM_DENOMINATIONS; i++) {
            String idxStr = String.valueOf(i + 1);
            terminal.print(idxStr + " ".repeat(NUM_SEPERATORS));
        }
        terminal.println("");
        terminal.flush();
    }

    public void submit() {
        answer = value;
        terminal.reset();
        terminal.printAndFlush(prompt + value);
    }
}
