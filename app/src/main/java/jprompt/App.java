package jprompt;

import jprompt.prompt.Prompts;

public class App {
    public static void main(String[] args) {
        int value = Prompts.slider("Enter a number: ", 0, 1000);
        System.out.println("Your number is: " + value);
    }
}
