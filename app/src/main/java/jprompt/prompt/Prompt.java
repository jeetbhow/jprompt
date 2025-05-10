package jprompt.prompt;

import jprompt.terminal.PromptTerminal;

public abstract class Prompt<T> {
    protected PromptTerminal terminal;
    protected String prompt;
    protected T answer;

    public Prompt(PromptTerminal terminal, String prompt) {
        this.terminal = terminal;
        this.prompt = prompt;
    }

    public abstract void run();

    public T getAnswer() {
        return answer;
    }

    public String getPrompt() {
        return prompt;
    }
}
