import java.util.ArrayList;
import java.util.Scanner;

public class hq extends unit{

    public hq(int id) {
        super(id);
        this.price = gameConstants.hqPrice;
        this.loot = gameConstants.hqLoot;
        this.moveLimit = gameConstants.hqMoveLimit;
        this.hp = gameConstants.hqHp;
        this.range = gameConstants.hqRange;
        this.coolDownTime = gameConstants.hqCoolDownTime;
        this.size = gameConstants.hqSize;
        this.type = "hq";
        this.symbol = "H";
    }



}
