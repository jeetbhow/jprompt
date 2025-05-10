package jprompt.prompt;

import java.util.List;

import org.jline.keymap.KeyMap;

import jprompt.keymap.KeyMaps;
import jprompt.terminal.PromptTerminal;

public class ListPrompt extends Prompt<Integer> {
    protected String[] options;
    protected int curr = 0;

    public ListPrompt(PromptTerminal terminal, String prompt, String[] options) {
        super(terminal, prompt);
        this.options = options;
    }

    public ListPrompt(PromptTerminal terminal, String prompt, List<String> options) {
        super(terminal, prompt);
        this.options = options.toArray(new String[0]);
    }

    @Override
    public void run() {
        KeyMap<String> km = KeyMaps.listKeyMap();
        render();
        terminal.flush();
        while (true) {
            String key = terminal.readBinding(km);
            switch (key) {
                case "up" -> curr = (curr - 1 + options.length) % options.length;
                case "down" -> curr = (curr + 1) % options.length;
                case "enter" -> {
                    submit();
                    return;
                }
                default -> throw new IllegalStateException(
                        String.format("Invalid key binding \"%s\"", key));
            }
            terminal.reset();
            render();
            terminal.flush();
        }
    }

    private void render() {
        terminal.println(prompt);
        for (int i = 0; i < options.length; i++) {
            if (i == curr) {
                terminal.println("-> " + options[i]);
            } else {
                terminal.println("   " + options[i]);
            }
        }
    }

    public void submit() {
        terminal.reset();
        terminal.flush();
    }
}
