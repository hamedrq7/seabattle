import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class player {

    private int id;
    private String name;
    private double money;
    private int boardSize;
    private board ownBoard;
    private ArrayList<unit> units;                      //contains current unit of player in each turn
    private ArrayList<cell> myTargetCells;              //contains strikes of player
    private ArrayList<cell> enemyTargetCells;           //contains strikes that player took
    private ArrayList<cell> myLastTargetCells;
    private ArrayList<cell> enemyLastTargetCells;

    public int getBoardSize() { return boardSize; }
    public ArrayList<unit> getUnits() { return units; }
    public void setMyLastTargetCells(ArrayList<cell> cells) {this.myLastTargetCells = cells; }
    public void addToMyTargetCells(ArrayList<cell> cells) {this.myTargetCells.addAll(cells);}
    public void addToEnemyTargetCells(ArrayList<cell> cells) {this.enemyTargetCells.addAll(cells);}
    public void setEnemyLastTargetCells(ArrayList<cell> cells) {this.enemyLastTargetCells = cells;}

    public player(int id, int boardSize) {
        this.id = id;
        this.boardSize = boardSize;
        this.ownBoard = new board(boardSize);
        this.name = "player"+this.id;

        this.money = 10;
        this.units = new ArrayList<>();
        this.myTargetCells = new ArrayList<>();
        this.enemyTargetCells = new ArrayList<>();
        this.myLastTargetCells = new ArrayList<>();
        this.enemyTargetCells = new ArrayList<>();
    }

    //Attack:
    public ArrayList<cell> attack() {
        ArrayList<cell> targetCells = new ArrayList<>();
        ArrayList<unit> readyUnits = showAttackMenu();
        if(readyUnits.isEmpty()) {
            System.out.println("No unit ready to attack with!");
        }
        else {
            System.out.println("Attack...");
            unit selectedUnit = scan.scanUnitId(readyUnits);

            targetCells = selectedUnit.attack(boardSize);

            selectedUnit.setToCoolDown();
            //"set" myLastTargetCells
            ////check that if you set the "collision" attribute in one of cells that you passed to geShot function in other player, cells in myLastTargetCells change too or not!
            setMyLastTargetCells(targetCells);
            addToMyTargetCells(targetCells);
        }
        return targetCells;
    }
    private ArrayList<unit> showAttackMenu() {
        ArrayList<unit> readyUnits = new ArrayList<>();
        System.out.println("List of units:");
        for(unit currUnit : this.units) {
            if (!currUnit.isInCoolDown()) {
                //currUnit is r4eDY TO attack:
                readyUnits.add(currUnit);
                System.out.println(currUnit.id + ") " + currUnit.type);
            } else {
                //currUnit is not ready too attack:
                System.out.println(currUnit.id + ") " + currUnit.type + " is not ready yet! " +
                        "(remaining turn from coolDown: " + currUnit.remainingCoolDownTime + ")");
            }
        }
        return readyUnits;
    }
    //receive target cells and execute them on thisPlayer
    public void getAttack(ArrayList<cell> targetCells, player enemy) {
        this.setEnemyLastTargetCells(targetCells);
        this.addToEnemyTargetCells(targetCells);

        for(cell currTarget : targetCells) {

            //search through all unitCells:
            for(unit currUnit: this.units) {
                //not sure:
                if(!currUnit.unitIsAlive()) {continue;}

                for(unitCell currCell: currUnit.unitCells) {
                    //not sure:
                    if(!currCell.getUnTouched()) {continue;}

                    if(currCell.hasEqualCell(currTarget)) {

                        //we have a collision

                        //stuff about unitCell:
                        currCell.setUnTouched(false);

                        //stuff about targetCells:(strikes)
                        ////////////////////////////////if we change "Collision" attribute of cell here,
                        ////////////////////////////////does the change transfer to "myLastTargetCells" of other player too??
                        currTarget.setCollision(true);

                        break;
                    }
                }
            }
        }

        //now Checking for dead players:
        for(unit currUnit: this.units) {
            if(!currUnit.unitIsAlive()) {
                //money loot
                enemy.addMoney(currUnit.loot);
                this.units.remove(currUnit);
            }
        }
    }
    //money:
    public void addMoney(double loot) {
        this.money += loot;
    }
    //updateCoolDow:
    public void updateCoolDown() {
        for(unit currUnit : this.units) {
            currUnit.updateCoolDown();
        }
    }

    //Move:
    public void move() {
        ArrayList<unit> readyUnits = this.showMoveMenu();
        if(readyUnits.isEmpty()) {
            System.out.println("NO units have available move!!");
        }
        else {
            unit selectedUnit = scan.scanUnitId(readyUnits);
            selectedUnit.showAllPossibleMoves(this);

            move selectedMove = scan.scanMoveId(selectedUnit.getValidMoves(this));

            selectedUnit.move(selectedMove);
        }
    }
    private ArrayList<unit> showMoveMenu() {
        ArrayList<unit> movableUnits = new ArrayList<>();
        System.out.println("Move Menu:\t\tenter id of player you wanna move...");
        for(unit currUnit : this.units) {

            //units that have been hitted before cant move:
            if(currUnit.isHitted()) {continue;}


            if(!currUnit.getValidMoves(this).isEmpty()) {
                movableUnits.add(currUnit);
                System.out.println(currUnit.id + ") " + currUnit.type);
            }
            else {
                System.out.println(currUnit.id + ") " + currUnit.type + " has no possible moves!");
            }
        }
        return movableUnits;
    }

    //shop:
    public void showShop() {

        if(this.enoughMoney()) {
            System.out.println("Current Money: " + this.money);
            System.out.println("Menu:");
            for(unit unit : gameConstants.allUnits) {
                System.out.println("- " + unit.type + "\t\t" + unit.price);
            }
            System.out.println("Enter type of the unit you wanna buy...");
            Scanner scanner = new Scanner(System.in);
            String type = scanner.nextLine();
            for(unit currUnit : gameConstants.allUnits) {
                if(scan.stringUnSimilarity(currUnit.type, type) <= 1) {
                    if(currUnit.price <= this.money) {
                        System.out.println("Scanning Coordinates for " + currUnit.type + "...");
                        currUnit.setId(this.units.size());
                        scan.scanUnit(currUnit, this);
                        this.units.add(currUnit);

                        System.out.println("Unit with this credentials added to units: ");
                        System.out.println("id: " + currUnit.id);
                        System.out.println("type: " + currUnit.type);
                        System.out.print("Coordinates: ");
                        for(unitCell cell : currUnit.unitCells) {
                            System.out.print("("+cell.getY()+", "+cell.getY()+") ");
                        }
                        System.out.println("\n");
                        this.money -= currUnit.price;
                    }
                }
                else {
                    System.out.println("You dont have enough money to buy this unit!");
                }
            }
        }

    }
    private boolean enoughMoney() {
        double min_price = 1000;
        for(unit unit : gameConstants.allUnits) {
            min_price = java.lang.Math.min(min_price, unit.price);
        }
        if(this.money < min_price) {
            System.out.println("Not enough money to buy anything!");
            return false;
        }
        else {
            return true;
        }
    }

    //showBoard:
    public void show() {
        this.ownBoard.show(this);

        System.out.println("Last strikes:");
        this.ownBoard.showStrikes(this.myLastTargetCells);

        System.out.println("All strikes: ");
        this.ownBoard.showStrikes(this.myTargetCells);
    }



    //Functions about "prep":
    public void pick() {
        int index = 0;
        addSoldiers(index);
        index += gameConstants.nSoldier;
        addCavalry(index);
        index += gameConstants.nCavalry;
        addCastles(index);
        index += gameConstants.nCastle;
        addHqs(index);
        index += gameConstants.nHq;
    }
    private void addSoldiers(int index) {
        /*
        System.out.println(this.name + " enter coordinates of soldiers...");
        for(int i = 0; i < gameConstants.nSoldier; i++, index++)
            this.units.add(scan.scanSoldier(index, this));

        */
        System.out.println(this.name + " enter coordinates of soldiers...");
        for(int i = 0; i < gameConstants.nSoldier; i++, index++) {
            soldier newSoldier = new soldier(index);
            scan.scanUnit(newSoldier, this);
            this.units.add(newSoldier);
        }

    }
    private void addCavalry(int index) {
        /*
        System.out.println(this.name + " enter coordinates of cavalries...");
        for(int i = 0; i < gameConstants.nCavalry; i++, index++)
            this.units.add(scan.scanCavalry(index, this));

         */
        System.out.println(this.name + " enter coordinates of cavalries...");
        for(int i = 0; i < gameConstants.nCavalry; i++, index++) {
            cavalry newCavalry = new cavalry(index);
            scan.scanUnit(newCavalry, this);
            this.units.add(newCavalry);
        }
    }
    private void addCastles(int index) {
        /*System.out.println(this.name + " enter coordinates of castles...");
        for(int i = 0; i < gameConstants.nCastle; i++, index++)
            this.units.add(scan.scanCastle(index, this));
        */
        System.out.println(this.name + " enter coordinates of castles...");
        for(int i = 0; i < gameConstants.nCastle; i++, index++) {
            castle newCastle = new castle(index);
            scan.scanUnit(newCastle, this);
            this.units.add(newCastle);
        }
    }
    private void addHqs(int index) {
        /*System.out.println(this.name + " enter coordinates of headquarters...");
        for(int i = 0; i < gameConstants.nHq; i++, index++)
            this.units.add(scan.scanHq(index, this));*/
        System.out.println(this.name + " enter coordinates of headquarters...");
        for(int i = 0; i < gameConstants.nHq; i++, index++) {
            hq newHq = new hq(index);
            scan.scanUnit(newHq, this);
            this.units.add(newHq);
        }
    }


    //function haye kolli:




    public void showUnits() {
        for(unit a : this.units) a.show();
    }
    public void showAttackHistory() {
        System.out.println("EnemyLastAttacks:");
        for(cell cell : this.enemyLastTargetCells) {
            System.out.println("(" + cell.getX() + ", " + cell.getY() + ") collision--> " + cell.getCollision());
        }

        System.out.println("MyLastAttacks:");
        for(cell cell : this.myTargetCells) {
            System.out.println("(" + cell.getX() + ", " + cell.getY() + ") collision--> " + cell.getCollision());
        }
    }

}
