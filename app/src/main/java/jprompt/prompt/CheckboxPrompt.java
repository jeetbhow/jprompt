package jprompt.prompt;

import java.util.ArrayList;
import java.util.List;

import org.jline.keymap.KeyMap;

import jprompt.keymap.KeyMaps;
import jprompt.terminal.PromptTerminal;

public class CheckboxPrompt extends Prompt<List<Integer>> {
    private int curr = 0;
    private String[] options;
    private boolean[] checked;

    public CheckboxPrompt(PromptTerminal terminal, String prompt, String[] options) {
        super(terminal, prompt);
        this.options = options;
        this.checked = new boolean[options.length];
    }

    public CheckboxPrompt(PromptTerminal terminal, String prompt, List<String> options) {
        super(terminal, prompt);
        this.options = options.toArray(new String[0]);
        this.checked = new boolean[options.size()];
    }

    @Override
    public void run() {
        KeyMap<String> km = KeyMaps.verticalList();
        render();
        terminal.flush();
        while (true) {
            String key = terminal.readBinding(km);
            switch (key) {
                case "up" -> curr = (curr - 1 + options.length) % options.length;
                case "down" -> curr = (curr + 1) % options.length;
                case "space" -> checked[curr] = !checked[curr];
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
            if (i == curr || checked[i]) {
                terminal.println("☑ " + options[i]);
            } else {
                terminal.println("☐ " + options[i]);
            }
        }
    }

    private void submit() {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < checked.length; i++) {
            if (checked[i]) {
                indices.add(i);
            }
        }
        answer = indices;
        List<String> toDisplay = indices
                .stream()
                .map(index -> options[index])
                .toList();
        terminal.reset();
        terminal.printAndFlush(prompt + toDisplay + "\n");
    }
}
