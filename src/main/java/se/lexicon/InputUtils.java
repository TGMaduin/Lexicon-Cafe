package se.lexicon;

public final class InputUtils {

    private InputUtils(){}

    public static int readValidatedInt(String prompt, String notNumberMsg, String outOfRangeMsg, int min, int max){

        while(true){

            IO.println(prompt);
            String input = IO.readln();

            if (input == null || input.isBlank() || !input.matches("\\d+")){
                IO.println(notNumberMsg);
                continue;
            }

            int value = Integer.parseInt(input);

            if (value < min || value > max) {
                IO.println(outOfRangeMsg);
                continue;
            }
            return value;
        }
    }

    public static boolean checkMembership(){

        while (true){

            IO.print("Loyalty member? (yes/no) ");
            String input = IO.readln();

            if (input == null){
                IO.println("Only options are 'yes' or 'no'");
                continue;
            }

            input = input.trim().toLowerCase();

            if (input.equals("yes")) return true;
            if (input.equals("no")) return false;

            IO.println("Only options are 'yes' or 'no'");
        }
    }

    public static String readName(){

        String name;

        while (true){

            name = IO.readln();

            if (name == null) {
                IO.println("Name can't be empty. Please try again.");
                continue;
            }

            name = name.trim();

            if (name.isEmpty()){
                IO.println("Name can't be empty. Please try again.");
                continue;
            }

            if (!name.matches("[\\p{L}]+([ '-][\\p{L}]+)*")) {
                IO.println("Name can only contain letters, spaces, apostrophes, and hyphens.");
                continue;
            }

            return name;
        }
    }
}
