package jprompt.prompt;

import java.util.List;

import jprompt.terminal.PromptTerminal;

import java.util.ArrayList;
import java.util.Arrays;

public class Textbox {
    private String text;
    private int width;
    private boolean autoWrap = true;
    private PromptTerminal terminal;

    public Textbox(PromptTerminal terminal, String text, int width) {
        this.terminal = terminal;
        this.text = text;
        this.width = width;
    }

    public Textbox(PromptTerminal terminal, String text, int width, boolean autoWrap) {
        this.terminal = terminal;
        this.text = text;
        this.width = width;
        this.autoWrap = autoWrap;
    }

    public void render() {
        List<String> lines = autoWrap ? wrapText() : Arrays.asList(text.split("\n"));
        terminal.print("┌" + "─".repeat(width - 2) + "┐\n");
        for (String line : lines) {
            terminal.print("│ " + line + " ".repeat(width - line.length() - 4) + " │\n");
        }
        terminal.print("└" + "─".repeat(width - 2) + "┘");
        terminal.flush();
    }

    public List<String> wrapText() {
        String[] words = text.split("\\s+");
        List<String> lines = new ArrayList<>();
        StringBuilder currLine = new StringBuilder();
        for (String word : words) {
            if (currLine.length() + word.length() + 4 >= width) {
                lines.add(currLine.toString());
                currLine.setLength(0);
                currLine.append(word);
            } else {
                currLine.append(word + " ");
            }
        }
        return lines;
    }
}
