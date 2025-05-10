package jprompt.prompt;

import java.io.IOException;
import java.util.List;

import jprompt.terminal.PromptTerminal;
import com.github.lalyos.jfiglet.FigletFont;

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
        Prompt<String> inputPrompt = new InputPrompt(terminal, prompt);
        inputPrompt.run();
        return inputPrompt.getAnswer();
    }

    public static boolean confirm(String prompt) {
        Prompt<Boolean> confirmPrompt = new ConfirmPrompt(terminal, prompt);
        confirmPrompt.run();
        return confirmPrompt.getAnswer();
    }

    public static void list(String prompt, String... options) {
        Prompt<Integer> listPrompt = new ListPrompt(terminal, prompt, options);
        listPrompt.run();
    }

    public static void list(String prompt, List<String> options) {
        Prompt<Integer> listPrompt = new ListPrompt(terminal, prompt, options);
        listPrompt.run();
    }

    public static int select(String prompt, String... options) {
        Prompt<Integer> selectPrompt = new SelectPrompt(terminal, prompt, options);
        selectPrompt.run();
        return selectPrompt.getAnswer();
    }

    public static int select(String prompt, List<String> options) {
        Prompt<Integer> selectPrompt = new SelectPrompt(terminal, prompt, options);
        selectPrompt.run();
        return selectPrompt.getAnswer();
    }

    public static List<Integer> checkbox(String prompt, String... options) {
        Prompt<List<Integer>> checkboxPrompt = new CheckboxPrompt<>(terminal, prompt, options);
        checkboxPrompt.run();
        return checkboxPrompt.getAnswer();
    }

    public static boolean yesNo(String prompt) {
        Prompt<Integer> yesNoPrompt = new SelectPrompt(terminal, prompt, new String[] { "Yes", "No" }, false);
        yesNoPrompt.run();
        return yesNoPrompt.getAnswer() == 0;
    }

    public static void title(String title) throws IOException {
        terminal.print(FigletFont.convertOneLine(title));
        terminal.flush();
    }

    public static void message(String message) {
        terminal.print("\n" + message + "\n");
        terminal.flush();
    }

}
