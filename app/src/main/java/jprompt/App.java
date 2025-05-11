package jprompt;

import jprompt.prompt.ColorPrompt.TerminalColor;
import jprompt.prompt.Prompts;

public class App {
    public static void main(String[] args) {
        // Demonstrate terminal color pickers
        Prompts.message("Welcome to the terminal color picker examples!");

        // Explain the different types of color pickers
        if (Prompts.confirm("Would you like to try the gradient color picker? ")) {
            // Use the gradient color picker (smooth spectrum)
            Prompts.message("\nGradient Color Picker Example:");
            Prompts.message("This picker shows a smooth color gradient with many colors.");
            Prompts.message("Use arrow keys to navigate and Enter to select a color.");

            // Select background and foreground colors
            TerminalColor backgroundColor = Prompts.gradientColorPicker("Select a background color: ");
            TerminalColor textColor = Prompts.gradientColorPicker("Select a text color: ");

            // Display result with selected colors
            displayColoredText("This text uses your gradient color selections!",
                    backgroundColor.getAnsiCode(),
                    textColor.getAnsiCode());
        } else {
            // Use the standard color picker (discrete colors)
            Prompts.message("\nStandard Color Picker Example:");
            Prompts.message("This picker shows the standard terminal colors.");

            // Select background and foreground colors
            TerminalColor backgroundColor = Prompts.colorPicker("Select a background color: ");
            TerminalColor textColor = Prompts.colorPicker("Select a text color: ");

            // Display result with selected colors
            displayColoredText("This is your selected color combination!",
                    backgroundColor.getAnsiCode(),
                    textColor.getAnsiCode());
        }
    }

    // Helper method to display text with selected colors
    private static void displayColoredText(String text, String bgCode, String fgCode) {
        // Convert bg code to fg code if needed
        String foregroundCode = fgCode;
        if (foregroundCode.startsWith("4")) {
            foregroundCode = "3" + foregroundCode.substring(1);
        }

        // Print colored text
        System.out.println("\n----- Your Styled Text -----");
        System.out.println("\u001b[" + bgCode + "m\u001b[" + foregroundCode + "m" + text + "\u001b[0m");
        System.out.println("---------------------------\n");
    }
}
