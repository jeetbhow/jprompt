package jprompt.terminal;

import java.io.IOException;
import java.io.PrintWriter;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp.Capability;
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
        setCursorInvisible();
    }

    public void setCursorInvisible() {
        terminal.puts(Capability.cursor_invisible);
        terminal.flush();
    }

    public void setCursorVisible() {
        terminal.puts(Capability.cursor_visible);
        terminal.flush();
    }

    public String readBinding(KeyMap<String> km) {
        return bindingReader.readBinding(km);
    }

    // Read line from input.
    public String readLine(String prompt) {
        return lineReader.readLine(prompt);
    }

    // Add string to the buffer.
    public void print(String str) {
        buffer.append(str);
    }

    // Add char to the buffer.
    public void print(char c) {
        buffer.append(c);
    }

    // Print a string and flush right away.
    public void printAndFlush(String str) {
        buffer.append(str);
        flush();
    }

    public void printAndFlush(char c) {
        buffer.append(c);
        flush();
    }

    public void clearLine() {
        buffer.append("\033[2K\r");
    }

    // Flush terminal
    public void flush() {
        writer.print(buffer.toString());
        terminal.flush();
    }
}
