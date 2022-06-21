package JavaDevHomeWork5.utill;

public interface ModelService {

    default String formatOutputData(Object el){
        if (el == null){
            el = "";
        }
        return (char) 27+"[34m" + el + (char) 27+"[0m";
    }
}
