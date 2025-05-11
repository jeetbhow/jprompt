package jprompt.prompt.text;

import jprompt.prompt.Prompt;
import jprompt.terminal.PromptTerminal;

public class InputPrompt extends Prompt<String> {
    public InputPrompt(PromptTerminal terminal, String prompt) {
        super(terminal, prompt);
    }

    public InputPrompt(PromptTerminal terminal, String prompt, char mask) {
        super(terminal, prompt, mask);
    }

    @Override
    public void run() {
        answer = terminal.readLine(getPrompt(), mask);
    }
}
