package jprompt.terminal;

import java.io.IOException;
import java.io.PrintWriter;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;

import jprompt.prompt.InputPrompt;
import jprompt.prompt.Prompt;

public class PromptTerminal {
    private Terminal terminal;
    private StringBuilder buffer;
    private PrintWriter writer;
    private LineReader reader;

    public PromptTerminal() throws IOException {
        terminal = TerminalBuilder.builder()
                                  .encoding("UTF-8")
                                  .system(true)
                                  .build();
        reader = LineReaderBuilder.builder()
                                  .terminal(terminal)
                                  .build();
        writer = terminal.writer();
        buffer = new StringBuilder();
    }

    public String readLine(String prompt) {
        return reader.readLine(prompt);
    }
}
