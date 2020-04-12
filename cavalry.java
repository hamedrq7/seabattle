import java.util.ArrayList;
import java.util.Scanner;

public class cavalry extends unit{

    public cavalry(int id) {
        super(id);
        this.price = gameConstants.cavalryPrice;
        this.loot = gameConstants.cavalryLoot;
        this.moveLimit = gameConstants.cavalryMoveLimit;
        this.hp = gameConstants.cavalryHp;
        this.range = gameConstants.cavalryRange;
        this.coolDownTime = gameConstants.cavalryCoolDownTime;
        this.size = gameConstants.cavalrySize;
        this.type = "Cavalry";
        this.symbol = "C";
    }



}
