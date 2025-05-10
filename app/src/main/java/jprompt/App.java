package jprompt;

import java.io.IOException;

import jprompt.prompt.*;

public class App {
    public static void main(String[] args) {
        try {
            Prompts.title("JPrompt");
            Prompts.message("Welcome to JPrompt!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}