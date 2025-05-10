package jprompt;

import jprompt.prompt.Prompts;

public class App {
    public static void main(String[] args) {
        int value = Prompts.range("Enter a number: ", 0, 100);
        System.out.println("Your number is: " + value);
    }
}
