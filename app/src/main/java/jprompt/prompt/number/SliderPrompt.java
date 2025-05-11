package jprompt.prompt.number;

import org.jline.keymap.KeyMap;

import jprompt.terminal.PromptTerminal;
import jprompt.keymap.KeyMaps;
import jprompt.prompt.Prompt;

public class SliderPrompt extends Prompt<Integer> {
    private static final char LEFT_BOUND = '[';
    private static final char RIGHT_BOUND = ']';
    private static final char BAR = '=';
    private static final int SEGMENTS = 20;
    private static final char SPACE = ' ';
    private int min;
    private int max;
    private int val;

    public SliderPrompt(PromptTerminal terminal, String prompt, int min, int max) {
        super(terminal, prompt);
        this.min = min;
        this.max = max;
        this.val = (min + max) / 2;
    }

    @Override
    public void run() {
        KeyMap<String> km = KeyMaps.defaulyKeyMap();
        render();
        terminal.flush();
        while (true) {
            String key = terminal.readBinding(km);
            switch (key) {
                case "left" -> val = Math.max(val - 1, min);
                case "right" -> val = Math.min(val + 1, max);
                case "enter" -> {
                    submit();
                    return;
                }
                default -> throw new IllegalStateException("Unexpected key: " + key);
            }
            render();
            terminal.flush();
        }
    }

    public void render() {
        int segmentLength = (max - min) / SEGMENTS;
        int filledSegments = (val - min) / segmentLength;
        StringBuilder slider = new StringBuilder(LEFT_BOUND);
        for (int i = 0; i < SEGMENTS; i++) {
            if (i < filledSegments) {
                slider.append(BAR);
            } else {
                slider.append(SPACE);
            }
        }
        slider.append(RIGHT_BOUND + SPACE);
        slider.append(val);
        terminal.clearLine();
        terminal.print(prompt + slider.toString());
    }

    public void submit() {
        answer = val;
        terminal.printAndFlush("\n");
    }

}
