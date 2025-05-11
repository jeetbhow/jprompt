package jprompt;

import jprompt.prompt.Prompts;

public class App {
    public static void main(String[] args) {
        Prompts.textbox("Name: Jeet Bhowmik\n" +
                "License: MIT\n" +
                "Version: 1.0.0", 30, false);
    }

}
