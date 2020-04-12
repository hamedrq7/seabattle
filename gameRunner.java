import java.util.Scanner;

public class gameRunner {
    private player player1;
    private player player2;
    public Scanner scanner = new Scanner(System.in);

    public void prep() {
        System.out.println("Enter board Size...");
        int size = scanner.nextInt();

        player1 = new player(1, size);
        player2 = new player(2, size);

        //coordinates
        player1.pick();
        player2.pick();
    }

    public void start() {
        player1.showUnits();
        player1.show();
        player2.getAttack(player1.attack(), player1);
        player1.move();
        player1.showShop();

        player2.showUnits();
        player2.show();
        player1.getAttack(player2.attack(), player2);
        player2.move();
        player2.showShop();

        System.out.println();
        player1.showAttackHistory();
        player2.showAttackHistory();
        this.updateCoolDown();

        /////////////////////////////

        player1.showUnits();
        player1.show();
        player2.getAttack(player1.attack(), player1);
        player1.move();
        player1.showShop();

        player2.showUnits();
        player2.show();
        player1.getAttack(player2.attack(), player2);
        player2.move();
        player2.showShop();

        System.out.println();
        player1.showAttackHistory();
        player2.showAttackHistory();
        this.updateCoolDown();

        player1.showUnits();
        player1.show();
        player2.getAttack(player1.attack(), player1);
        player1.move();
        player1.showShop();

        player2.showUnits();
        player2.show();
        player1.getAttack(player2.attack(), player2);
        player2.move();
        player2.showShop();

        System.out.println();
        player1.showAttackHistory();
        player2.showAttackHistory();
        this.updateCoolDown();

        player1.showUnits();
        player1.show();
        player2.getAttack(player1.attack(), player1);
        player1.move();
        player1.showShop();

        player2.showUnits();
        player2.show();
        player1.getAttack(player2.attack(), player2);
        player2.move();
        player2.showShop();

        System.out.println();
        player1.showAttackHistory();
        player2.showAttackHistory();
        this.updateCoolDown();
    }

    private void updateCoolDown() {
        player1.updateCoolDown();
        player2.updateCoolDown();
    }
}
