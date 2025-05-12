package jprompt.prompt.decorator;

import java.util.List;

import jprompt.terminal.PromptTerminal;

import java.util.ArrayList;
import java.util.Arrays;

public class Textbox {
    private String text;
    private int width;
    private boolean autoWrap;
    private PromptTerminal terminal;

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
        terminal.print("\n");
        terminal.flush();
    }

    public List<String> wrapText() {
        String[] words = text.split("\\s+");
        List<String> lines = new ArrayList<>();
        StringBuilder currLine = new StringBuilder();
        for (String word : words) {
            if (currLine.length() == 0) {
                currLine.append(word);
            } else if (currLine.length() + word.length() + 1 <= width - 4) { // Adjusted width check
                currLine.append(" ").append(word);
            } else {
                lines.add(currLine.toString());
                currLine.setLength(0);
                currLine.append(word);
            }
        }
        if (currLine.length() > 0) {
            lines.add(currLine.toString().trim()); // Trim trailing space
        }
        return lines;
    }
}
