package jprompt.prompt;

import jprompt.terminal.PromptTerminal;

public abstract class Prompt<T> {
    protected PromptTerminal terminal; // The terminal used for input/output.
    protected String prompt; // The prompt that's displayed to the user.
    protected T answer; // The answer to the prompt.

    // Creates a new Prompt object.
    public Prompt(PromptTerminal terminal, String prompt) {
        this.terminal = terminal;
        this.prompt = prompt;
    }

    // Run the prompt and collect the answer.
    public abstract void run();

    // Return the answer.
    public T getAnswer() {
        return answer;
    }

    // Return the prompt string.
    public String getPrompt() {
        return prompt;
    }
}
