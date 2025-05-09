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
        terminal.setCursorVisible();

        Prompt<String> inputPrompt = new InputPrompt(prompt);
        inputPrompt.run(terminal);

        terminal.setCursorInvisible();
        return inputPrompt.getAnswer();
    }

    public static boolean confirm(String prompt) {
        Prompt<Boolean> confirmPrompt = new ConfirmPrompt(prompt);
        confirmPrompt.run(terminal);
        return confirmPrompt.getAnswer();
    }
}
