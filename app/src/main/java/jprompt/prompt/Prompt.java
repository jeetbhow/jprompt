package jprompt.prompt;

import jprompt.terminal.PromptTerminal;

public abstract class Prompt<T> {
    protected PromptTerminal terminal;
    protected String prompt;
    protected T answer;
    protected char mask;

    public Prompt(PromptTerminal terminal, String prompt) {
        this.terminal = terminal;
        this.prompt = prompt;
    }

    public Prompt(PromptTerminal terminal, String prompt, char mask) {
        this.terminal = terminal;
        this.prompt = prompt;
        this.mask = mask;
    }

    public abstract void run();

    public T getAnswer() {
        return answer;
    }

    public String getPrompt() {
        return prompt;
    }
}
