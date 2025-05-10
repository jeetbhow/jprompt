package jprompt.prompt;

import org.jline.keymap.KeyMap;

import jprompt.terminal.PromptTerminal;
import jprompt.keymap.KeyMaps;

public class SliderPrompt extends Prompt<Integer> {
    private int min;
    private int max;
    private int val;
    private int segments = 20;

    public SliderPrompt(PromptTerminal terminal, String prompt, int min, int max) {
        super(terminal, prompt);
        this.min = min;
        this.max = max;
        this.val = (min + max) / 2;
    }

    @Override
    public void run() {
        KeyMap<String> km = KeyMaps.horizontalList();
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
        int segmentLength = (max - min) / segments;
        int filledSegments = (val - min) / segmentLength;
        StringBuilder slider = new StringBuilder("[");
        for (int i = 0; i < segments; i++) {
            if (i < filledSegments) {
                slider.append("=");
            } else {
                slider.append(" ");
            }
        }
        slider.append("] ");
        slider.append(val);
        terminal.clearLine();
        terminal.print(prompt + slider.toString());
    }

    public void submit() {
        answer = val;
        terminal.printAndFlush("\n");
    }

}
