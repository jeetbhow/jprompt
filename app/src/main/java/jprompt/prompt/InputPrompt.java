package jprompt.prompt;

import jprompt.terminal.PromptTerminal;

public class InputPrompt extends Prompt<String> {

    public InputPrompt(PromptTerminal terminal, String prompt) {
        super(terminal, prompt);
    }

    @Override
    public void run() {
        answer = terminal.readLine(getPrompt());
    }

}
