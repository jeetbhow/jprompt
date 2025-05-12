package jprompt.prompt;

import java.io.IOException;
import java.util.List;

import jprompt.prompt.bool.ConfirmPrompt;
import jprompt.prompt.decorator.ProgressBar;
import jprompt.prompt.decorator.Textbox;
import jprompt.prompt.list.CheckboxPrompt;
import jprompt.prompt.list.ListPrompt;
import jprompt.prompt.list.SelectPrompt;
import jprompt.prompt.number.RatingPrompt;
import jprompt.prompt.number.SliderPrompt;
import jprompt.prompt.number.SpinnerPrompt;
import jprompt.prompt.text.InputPrompt;
import jprompt.prompt.text.MultiInputPrompt;
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

    public static List<String> multiInput(String prompt) {
        Prompt<List<String>> multiInputPrompt = new MultiInputPrompt(terminal, prompt);
        multiInputPrompt.run();
        return multiInputPrompt.getAnswer();
    }

    public static String password(String prompt) {
        Prompt<String> passwordPrompt = new InputPrompt(terminal, prompt, '*');
        passwordPrompt.run();
        return passwordPrompt.getAnswer();
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
        Prompt<List<Integer>> checkboxPrompt = new CheckboxPrompt(terminal, prompt, options);
        checkboxPrompt.run();
        return checkboxPrompt.getAnswer();
    }

    public static int range(String prompt, int min, int max) {
        Prompt<Integer> spinnerPrompt = new SpinnerPrompt(terminal, prompt, min, max);
        spinnerPrompt.run();
        return spinnerPrompt.getAnswer();
    }

    public static boolean yesNo(String prompt) {
        Prompt<Integer> yesNoPrompt = new SelectPrompt(terminal, prompt, new String[] { "Yes", "No" }, false);
        yesNoPrompt.run();
        return yesNoPrompt.getAnswer() == 0;
    }

    public static int slider(String prompt, int min, int max) {
        Prompt<Integer> sliderPrompt = new SliderPrompt(terminal, prompt, min, max);
        sliderPrompt.run();
        return sliderPrompt.getAnswer();
    }

    public static int rating(String prompt) {
        Prompt<Integer> ratingPrompt = new RatingPrompt(terminal, prompt);
        ratingPrompt.run();
        return ratingPrompt.getAnswer();
    }

    public static void title(String title) throws IOException {
        terminal.print(FigletFont.convertOneLine(title));
        terminal.flush();
    }

    public static void message(String message) {
        terminal.printAndFlush("\n" + message + "\n\n");
    }

    public static void textbox(String text, int width) {
        textbox(text, width, true);
    }

    public static void textbox(String text, int width, boolean autoWrap) {
        Textbox textbox = new Textbox(terminal, text, width, autoWrap);
        textbox.render();
    }

    /**
     * Creates a new progress bar with default styling.
     * 
     * @param prompt The text to display before the progress bar
     * @param width  The width of the progress bar in characters
     * @return The ProgressBarPrompt object that can be updated
     */
    public static ProgressBar progressBar(String prompt, int width) {
        ProgressBar progressBar = new ProgressBar(terminal, prompt, width);
        progressBar.run();
        return progressBar;
    }

    /**
     * Creates a new progress bar with custom styling.
     * 
     * @param prompt         The text to display before the progress bar
     * @param width          The width of the progress bar in characters
     * @param fillChar       The character to use for filled portions
     * @param emptyChar      The character to use for empty portions
     * @param startChar      The character to display at the start of the bar
     * @param endChar        The character to display at the end of the bar
     * @param showPercentage Whether to show the percentage after the bar
     * @return The ProgressBarPrompt object that can be updated
     */
    public static ProgressBar progressBar(String prompt, int width,
            char fillChar, char emptyChar,
            char startChar, char endChar,
            boolean showPercentage) {
        ProgressBar progressBar = new ProgressBar(
                terminal, prompt, width, fillChar, emptyChar,
                startChar, endChar, showPercentage);
        progressBar.run();
        return progressBar;
    }
}
