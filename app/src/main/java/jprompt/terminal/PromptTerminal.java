package jprompt.terminal;

import java.io.IOException;
import java.io.PrintWriter;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.keymap.BindingReader;
import org.jline.keymap.KeyMap;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;

public class PromptTerminal {
    private Terminal terminal;
    private StringBuilder buffer;
    private PrintWriter writer;
    private LineReader lineReader;
    private BindingReader bindingReader;
    private int renderedLines = 0;

    public PromptTerminal() throws IOException {
        terminal = TerminalBuilder.builder()
                .encoding("UTF-8")
                .system(true)
                .build();
        lineReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();
        writer = terminal.writer();
        buffer = new StringBuilder();
        bindingReader = new BindingReader(terminal.reader());
    }

    public String readBinding(KeyMap<String> km) {
        return bindingReader.readBinding(km);
    }

    public String readLine(String prompt) {
        return lineReader.readLine(prompt);
    }

    public void print(String str) {
        buffer.append(str);
    }

    public void print(char c) {
        buffer.append(c);
    }

    public void println(String str) {
        print(str + "\n");
        renderedLines++;
    }

    public void printAndFlush(String str) {
        buffer.append(str);
        flush();
    }

    public void printAndFlush(char c) {
        buffer.append(c);
        flush();
    }

    public void clearLine() {
        buffer.append("\r\033[2K");
    }

    public void moveCursorUp() {
        buffer.append("\033[A");
    }

    public void moveCursorUp(int lines) {
        for (int i = 0; i < lines; i++) {
            buffer.append("\033[A");
        }
    }

    public void reset() {
        for (int i = 0; i < renderedLines; i++) {
            moveCursorUp();
            clearLine();
        }
        renderedLines = 0;
    }

    public void flush() {
        writer.print(buffer.toString());
        terminal.flush();
        buffer.setLength(0);
    }
}
