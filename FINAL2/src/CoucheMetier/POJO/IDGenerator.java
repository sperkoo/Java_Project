package CoucheMetier.POJO;

public class IDGenerator {
    private static int idCounter = 0;

    public static String generateID() {
        idCounter++;
        return String.valueOf(idCounter);
    }
}
