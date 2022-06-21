package JavaDevHomeWork5.state;

import JavaDevHomeWork5.App;

public class MainState extends State{

    public MainState(App application) {
        super(application);
    }

    @Override
    public void execute() {
        String[] message = {"***MAIN MENU***\nchoose the needed point:"
                    ,"press 1 -> Operation with PET"
                    ,"press 2 -> Operation with STORE"
                    ,"press 3 -> Operation with USER"
                    ,"press 0 -> EXIT APP"};
        switch (checkCorrectInputAndReturnNumber(message)){
            case 0:
                System.exit(10101010);
                break;
            case 1:
                application.changeState(new PetState(application));
                break;
            case 2:
                application.changeState(new StoreState(application));
                break;
            case 3:
                application.changeState(new UserState(application));
                break;
        }
    }
}
