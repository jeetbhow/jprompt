package jprompt.prompt;

import java.util.List;

import org.jline.keymap.KeyMap;

import jprompt.keymap.KeyMaps;
import jprompt.terminal.PromptTerminal;

public class ListPrompt extends Prompt<Integer> {
    protected String[] options;
    protected int curr = 0;
    protected boolean isVertical = true;

    public ListPrompt(PromptTerminal terminal, String prompt, String[] options) {
        super(terminal, prompt);
        this.options = options;
    }

    public ListPrompt(PromptTerminal terminal, String prompt, List<String> options) {
        super(terminal, prompt);
        this.options = options.toArray(new String[0]);
    }

    public ListPrompt(PromptTerminal terminal, String prompt, String[] options, boolean isVertical) {
        super(terminal, prompt);
        this.options = options;
        this.isVertical = isVertical;
    }

    @Override
    public void run() {
        KeyMap<String> km = isVertical ? KeyMaps.verticalList() : KeyMaps.horizontalList();
        render();
        terminal.flush();
        while (true) {
            String key = terminal.readBinding(km);
            switch (key) {
                case "up", "left" -> curr = (curr - 1 + options.length) % options.length;
                case "down", "right" -> curr = (curr + 1) % options.length;
                case "enter" -> {
                    submit();
                    return;
                }
                default -> throw new IllegalStateException(
                        String.format("Invalid key binding \"%s\"", key));
            }

            if (isVertical) {
                terminal.reset();
            } else {
                terminal.clearLine();
            }
            render();
            terminal.flush();
        }
    }

    private void render() {
        if (isVertical) {
            terminal.println(prompt);
            for (int i = 0; i < options.length; i++) {
                if (i == curr) {
                    terminal.println("-> " + options[i]);
                } else {
                    terminal.println("   " + options[i]);
                }
            }
        } else {
            terminal.print(prompt);
            for (int i = 0; i < options.length; i++) {
                if (i == curr) {
                    terminal.print("-> " + options[i] + "  ");
                } else {
                    terminal.print("   " + options[i] + "  ");
                }
            }
        }

    }

    public void submit() {
        if (isVertical) {
            terminal.reset();
        } else {
            terminal.clearLine();
        }
        terminal.flush();
    }
}
