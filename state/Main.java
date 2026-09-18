import java.util.Scanner;

public class Main {
    private static final String[] ACTIONS = {"Train", "Meditate", "Fight"};

    interface State {
        int actionCount();
        void act(Character character, int choice);
    }

    static class Character {
        String name;
        int xp = 0;
        int hp = 100;
        State state = new Novice();

        Character(String name) {
            this.name = name;
        }
    }

    static class Novice implements State {
        public int actionCount() { return 1; }

        public void act(Character character, int choice) {
            character.xp += 50;
            if (character.xp >= 100) character.state = new Intermediate();
        }
    }

    static class Intermediate implements State {
        public int actionCount() { return 2; }

        public void act(Character character, int choice) {
            if (choice == 1) character.xp += 50;
            else character.hp += 20;
            if (character.xp >= 250) character.state = new Expert();
        }
    }

    static class Expert implements State {
        public int actionCount() { return 3; }

        public void act(Character character, int choice) {
            if (choice == 1) character.xp += 50;
            else if (choice == 2) character.hp += 20;
            else {
                character.hp -= 25;
                character.xp += 100;
            }
            if (character.xp >= 500) character.state = new Master();
        }
    }

    static class Master implements State {
        public int actionCount() { return 0; }
        public void act(Character character, int choice) { }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Character name: ");
        if (!scanner.hasNextLine()) return;
        String name = scanner.nextLine().trim();
        Character character = new Character(name.isEmpty() ? "Hero" : name);

        while (!(character.state instanceof Master)) {
            showStatus(character);
            showActions(character);
            int choice = readChoice(scanner);
            if (choice == 0) return;
            if (choice < 1 || choice > character.state.actionCount()) {
                System.out.println("Invalid choice.");
            } else {
                character.state.act(character, choice);
            }
        }

        showStatus(character);
        System.out.println("Game complete!");
    }

    private static void showStatus(Character character) {
        System.out.printf("%n%s | Level: %s | XP: %d | HP: %d%n",
                character.name, character.state.getClass().getSimpleName(),
                character.xp, character.hp);
    }

    private static void showActions(Character character) {
        System.out.println("Available actions:");
        for (int i = 0; i < character.state.actionCount(); i++) {
            System.out.println((i + 1) + ". " + ACTIONS[i]);
        }
        System.out.print("0. Quit\nChoose an action: ");
    }

    private static int readChoice(Scanner scanner) {
        if (!scanner.hasNextLine()) return 0;
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
