package jprompt;

import jprompt.prompt.ProgressBarPrompt;
import jprompt.prompt.Prompts;

public class App {
    public static void main(String[] args) {
        // Example of a progress bar demo

        // Create a progress bar with 20 characters width
        ProgressBarPrompt progressBar = Prompts.progressBar("Processing: ", 20);

        // Simulate a process that takes time
        for (int i = 0; i <= 100; i += 5) {
            // Update the progress bar
            progressBar.update(i);

            // Simulate work being done
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Show a success message
        Prompts.message("Process completed successfully!");
    }
}
