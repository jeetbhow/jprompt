package jprompt.prompt;

import org.jline.keymap.KeyMap;

import jprompt.terminal.PromptTerminal;
import jprompt.keymap.KeyMaps;

public class ConfirmPrompt extends Prompt<Boolean> {
    private final char CHECK = '☑';
    private final char EMPTY = '☐';
    private boolean value = false;

    public ConfirmPrompt(String prompt) {
        super(prompt);
    }

    @Override
    public void run(PromptTerminal terminal) {
        KeyMap<String> km = KeyMaps.toggleKeyMap();
        render(terminal);
        while (true) {
            String key = terminal.readBinding(km);
            switch (key) {
                case "space" -> value = !value;
                case "enter" -> {
                    answer = value;
                    render(terminal);
                    terminal.printAndFlush('\n');
                    return;
                }
                default -> throw new IllegalStateException(
                        String.format("Invalid key binding \"%s\"", key));
            }
            render(terminal);
        }
    }

    private void render(PromptTerminal terminal) {
        char checkbox = value ? CHECK : EMPTY;
        terminal.clearLine();
        terminal.print(prompt + checkbox);
        terminal.flush();
    }
}
