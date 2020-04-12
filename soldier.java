import java.util.ArrayList;

public class soldier extends unit{


    //constructor:
    public soldier (int id) {
        super(id);
        this.price = gameConstants.soldierPrice;
        this.loot = gameConstants.soldierLoot;
        this.moveLimit = gameConstants.soldierMoveLimit;
        this.hp = gameConstants.soldierHp;
        this.range = gameConstants.soldierRange;
        this.coolDownTime = gameConstants.soldierCoolDownTime;
        this.size = gameConstants.soldierSize;
        this.type = "Soldier";
        this.symbol = "S";
    }


}
