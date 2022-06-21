package JavaDevHomeWork5.utill;

import java.io.IOException;
import java.util.Scanner;

public interface IdService {

    default long getIDFromCommandLine(Scanner scanner) throws IOException {
        try{
            System.out.println("Write ID:");
            return Long.parseLong(scanner.nextLine());
        }catch (NumberFormatException e){
            throw new IOException(e);
        }
    }
}
