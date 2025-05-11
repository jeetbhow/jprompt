package jprompt.prompt;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import jprompt.terminal.PromptTerminal;

public class MultiInputPrompt extends Prompt<List<String>> {
    private static final String DEFAULT_DELIMITER = ",";

    public MultiInputPrompt(PromptTerminal terminal, String prompt) {
        super(terminal, prompt);
    }

    @Override
    public void run() {
        String input = terminal.readLine(prompt);
        String[] inputArray = input.split(DEFAULT_DELIMITER);

        answer = new ArrayList<>(Arrays.asList(inputArray));
    }
}
