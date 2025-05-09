package jprompt;

import jprompt.prompt.Prompts;

public class App {
    public static void main(String[] args) {
        boolean confirm = Prompts.confirm("Are you a human? ");
        System.out.println("You answered: " + confirm);
    }
}
