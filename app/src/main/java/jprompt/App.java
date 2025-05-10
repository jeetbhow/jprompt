package jprompt;

import java.util.List;

import jprompt.prompt.Prompts;

public class App {
    public static void main(String[] args) {
        boolean result = Prompts.yesNo("Are you sure? ");
        System.out.println("Result: " + result);
    }
}
