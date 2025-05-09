package jprompt.prompt;

import jprompt.terminal.PromptTerminal;

public abstract class Prompt<T> {
    protected String prompt;  // The prompt that's displayed to the user.
    protected T answer;       // The answer to the prompt.

    public Prompt(String prompt) {
        this.prompt = prompt;
    }

    // Run the prompt and collect the answer.
    public abstract Prompt<T> run(PromptTerminal prompt);

    // Return the answer.
    public T getAnswer() {
        return answer;
    }
    
    // Return the prompt string.
    public String getPrompt() {
        return prompt;
    }
}
