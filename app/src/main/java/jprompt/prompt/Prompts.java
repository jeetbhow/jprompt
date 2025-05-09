package jprompt.prompt;

import java.io.IOException;

import jprompt.terminal.PromptTerminal;

public class Prompts {
    private static PromptTerminal terminal;

    static {
        try {
            terminal = new PromptTerminal();
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize terminal");
        }
    }

    public Prompts() throws IOException {
        terminal = new PromptTerminal();
    }

    public static String input(String prompt) {
        Prompt<String> inputPrompt = new InputPrompt(prompt);
        inputPrompt.run(terminal);
        return inputPrompt.getAnswer();
    }
}
