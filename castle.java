import java.util.ArrayList;
import java.util.Scanner;

public class castle extends unit{

    public castle(int id) {
        super(id);
        this.price = gameConstants.castlePrice;
        this.loot = gameConstants.castleLoot;
        this.moveLimit = gameConstants.castleMoveLimit;
        this.hp = gameConstants.castleHp;
        this.range = gameConstants.castleRange;
        this.coolDownTime = gameConstants.castleCoolDownTime;
        this.size = gameConstants.castleSize;
        this.type = "Castle";
        this.symbol = "R";
    }



}
