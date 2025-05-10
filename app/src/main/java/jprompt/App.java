package jprompt;

import java.util.List;

import jprompt.prompt.Prompts;

public class App {
    public static void main(String[] args) {
        List<Integer> indices = Prompts.checkbox(
                "What programming languages do you know? ",
                "C", "Python", "Java", "C++", "Rust");

        System.out.println(indices);
    }
}
