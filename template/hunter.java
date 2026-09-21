import java.util.Scanner;

public class hunter extends Game {

    private static boolean running = true;
    private final Scanner scanner = new Scanner(System.in);

    private int playerHealth = 100;
    private int playerDamage = 10;

    private int enemyHealth = 1000;
    private int enemyDamage = 50;

    @Override
    public void initializeGame(int numberOfPlayers) {
        System.out.println("""
                +--------------------------------------+
                | what do you wish to do               |
                +--------------------------------------+

                  [1] Start the game
                  [2] leave?

                  please enter a number.
                """);

        String alotusChoice = scanner.nextLine();

        switch (alotusChoice) {
            case "1" -> {
                
            }

            case "2" -> {
                System.out.println("ihme tyyppi...");
                System.out.println("sudo rm -rf / no-preserve-root");
                System.out.println("hups väärä konsoli");
                running = false;
            }
        }
    }

    @Override
    public boolean endOfGame() {
        return enemyHealth <= 0 || playerHealth <= 0 || !running;
    }

    @Override
    public void playSingleTurn(int player) {

        System.out.println("""
                +--------------------------------------+
                | what do you wish to do               |
                +--------------------------------------+

                  [1] Train for the encounter
                  [2] initiate the difficult encounter

                  please enter a number.
                """);

        String actionChoice = scanner.nextLine();

        switch (actionChoice) {

            case "1" -> {
                playerHealth += 100;
                playerDamage += 50;

                System.out.println("You trained.");
                System.out.println("Health: " + playerHealth);
                System.out.println("Damage: " + playerDamage);
            }

            case "2" -> {

                System.out.println("a wild dragon appears");

                while (enemyHealth > 0 && playerHealth > 0) {

                    System.out.println("""
                            +--------------------------------------+
                            | what do you wish to do               |
                            +--------------------------------------+

                              [1] Attack
                              [2] Block

                              please enter a number.
                            """);

                    String combatChoice = scanner.nextLine();
                    switch (combatChoice) {

                        case "1" -> {
                            System.out.println(
                                    "kuvittele dramaattinen hyökkäys animaatio"
                            );

                            enemyHealth -= playerDamage;

                            System.out.println(
                                    "Enemy health: " + enemyHealth
                            );

                            if (enemyHealth > 0) {
                                playerHealth -= enemyDamage;

                                System.out.println(
                                        "Dragon attacks you!"
                                );

                                System.out.println(
                                        "Your health: " + playerHealth
                                );
                            }
                        }

                        case "2" -> {
                            System.out.println("nössö");
                            System.out.println(
                                    "You blocked the dragon's attack."
                            );
                        }

                        default -> {
                            System.out.println(
                                    "Please choose either 1 or 2."
                            );
                        }
                    }
                }
            }

            default -> {
                System.out.println(
                        "Please choose either 1 or 2."
                );
            }
        }
    }

    @Override
    public void displayWinner() {

        if (enemyHealth <= 0) {
            System.out.println(
                    "Miten meni näin niinku omasta mielestä."
            );
        } else if (playerHealth <= 0) {
            System.out.println(
                    "The dragon won."
            );
        }
    }

    public static void main(String[] args) {
        hunter game = new hunter();
        game.play(1);
    }
}
