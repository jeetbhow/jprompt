package jprompt;

import java.io.IOException;

import jprompt.prompt.Prompts;

public class App {
    public static void main(String[] args) {
        try {
            Prompts.title("Job App");
            Prompts.textbox(
                    "Optimized to maximally waste your time.",
                    45);

            boolean wantAccount = Prompts.yesNo("Would you like to create an account? ");
            while (!wantAccount) {
                Prompts.message("You must create an account to apply.");
                wantAccount = Prompts.yesNo("Would you like to create an account? ");
            }

            String email = Prompts.input("Email: ");
            String password = Prompts.password("Password: ");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
