package jprompt.prompt;

import jprompt.terminal.PromptTerminal;

public class SelectPrompt extends ListPrompt {
    public SelectPrompt(PromptTerminal terminal, String prompt, String[] options) {
        super(terminal, prompt, options);
    }

    @Override
    public void submit() {
        answer = curr;
        terminal.reset();
        terminal.printAndFlush(prompt + options[curr] + "\n");
    }

}
