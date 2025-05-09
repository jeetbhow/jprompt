package jprompt.prompt;

import jprompt.terminal.PromptTerminal;

public class InputPrompt extends Prompt<String> {

    public InputPrompt(String prompt) {
        super(prompt);
    }

    @Override
    public Prompt<String> run(PromptTerminal terminal) {
        answer = terminal.readLine(prompt);
        return this;
    }
    
}
