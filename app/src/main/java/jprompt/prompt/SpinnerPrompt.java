package jprompt.prompt;

import org.jline.keymap.KeyMap;

import jprompt.keymap.KeyMaps;
import jprompt.terminal.PromptTerminal;

public class SpinnerPrompt extends Prompt<Integer> {
    private int min;
    private int max;
    private int val;

    public SpinnerPrompt(PromptTerminal terminal, String prompt, int min, int max) {
        super(terminal, prompt);
        this.min = min;
        this.max = max;
        this.val = (min + max) / 2;
    }

    @Override
    public void run() {
        KeyMap<String> km = KeyMaps.verticalList();
        terminal.print(prompt + val);
        terminal.flush();

        while (true) {
            String key = terminal.readBinding(km);
            switch (key) {
                case "up" -> val = Math.min(val + 1, max);
                case "down" -> val = Math.max(val - 1, min);
                case "enter" -> {
                    submit();
                    return;
                }
                default -> throw new IllegalStateException("Unexpected key: " + key);
            }
            terminal.clearLine();
            terminal.print(prompt + val);
            terminal.flush();
        }
    }

    public void submit() {
        answer = val;
        terminal.clearLine();
        terminal.printAndFlush(prompt + val + "\n");
    }
}
