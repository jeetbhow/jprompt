package jprompt.prompt;

import jprompt.terminal.PromptTerminal;

public class ProgressBarPrompt extends Prompt<Integer> {
    private static final char DEFAULT_FILL_CHAR = '█';
    private static final char DEFAULT_EMPTY_CHAR = '░';
    private static final char DEFAULT_START_CHAR = '|';
    private static final char DEFAULT_END_CHAR = '|';

    private int progress = 0;
    private final int width;
    private final char fillChar;
    private final char emptyChar;
    private final char startChar;
    private final char endChar;
    private final boolean showPercentage;
    private boolean running = true;

    public ProgressBarPrompt(PromptTerminal terminal, String prompt, int width) {
        super(terminal, prompt);
        this.width = width;
        this.fillChar = DEFAULT_FILL_CHAR;
        this.emptyChar = DEFAULT_EMPTY_CHAR;
        this.startChar = DEFAULT_START_CHAR;
        this.endChar = DEFAULT_END_CHAR;
        this.showPercentage = true;
    }

    public ProgressBarPrompt(PromptTerminal terminal, String prompt, int width,
            char fillChar, char emptyChar,
            char startChar, char endChar,
            boolean showPercentage) {
        super(terminal, prompt);
        this.width = width;
        this.fillChar = fillChar;
        this.emptyChar = emptyChar;
        this.startChar = startChar;
        this.endChar = endChar;
        this.showPercentage = showPercentage;
    }

    @Override
    public void run() {
        render();
        terminal.flush();
        answer = 0;
    }

    public void update(int progress) {
        if (!running)
            return;

        this.progress = Math.max(0, Math.min(100, progress)); // Clamp between 0 and 100
        answer = this.progress;
        terminal.clearLine();
        render();
        terminal.flush();

        if (this.progress >= 100) {
            running = false;
            terminal.printAndFlush('\n');
        }
    }

    public void complete() {
        if (!running)
            return;
        update(100);
        running = false;
    }

    private void render() {
        terminal.print(prompt);
        terminal.print(startChar);

        int filledWidth = (int) ((progress / 100.0) * width);
        for (int i = 0; i < width; i++) {
            if (i < filledWidth) {
                terminal.print(fillChar);
            } else {
                terminal.print(emptyChar);
            }
        }

        terminal.print(endChar);

        if (showPercentage) {
            terminal.print(" " + progress + "%");
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void stop() {
        running = false;
        terminal.printAndFlush('\n');
    }
}
