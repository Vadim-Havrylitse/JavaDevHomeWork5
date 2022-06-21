package JavaDevHomeWork5.state;

import JavaDevHomeWork5.App;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public abstract class State {
    protected Scanner scanner;
    protected App application;

    public State(App application) {
        this.scanner = new Scanner(System.in);
        this.application = application;
    }

    public abstract void execute();

    protected int checkCorrectInputAndReturnNumber(String[] massage) {
        String str;
        do {
            System.out.println();
            Arrays.stream(massage).forEach(System.out::println);
            str = scanner.nextLine();
        } while (str.isBlank() || !str.matches("[0-"+(massage.length-2)+"]"));
        return Integer.parseInt(str);
    }

    protected void printUnderLines(){
        System.out.println("————————————————————————————————————————————");
    }

    protected void printErrMessage(IOException e){
        System.err.println("You do something wrong.\n" + e.getMessage());
    }

}
