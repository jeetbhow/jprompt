package jprompt;

import jprompt.prompt.Prompts;

public class App {
    public static void main(String[] args) {
        String password = Prompts.password("Enter your password: ");
        System.out.println("Your password is: " + password);
    }
}
