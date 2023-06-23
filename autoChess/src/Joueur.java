import java.util.ArrayList;
import java.util.Random;

public class Joueur extends Game{
    String name;
    int gold,PV,LVL;
    ArrayList<Carte> hand = new ArrayList<Carte>();
    ArrayList<Carte> board = new ArrayList<Carte>();
    ArrayList<Carte> shop = new ArrayList<>();

    public Joueur(String name,  int PV) {
        this.name = name;
        this.gold = 3;
        this.PV = PV;
        this.LVL = 1;
    }

    void setStart (ArrayList<Carte> card) {
        for (int i = 0; i < 3;i++){
            Random random = new Random();
            int randomstart = random.nextInt(3) ;
            shop.add(card.get(i));
        }
    }

    void Buy (Carte Card) {
        if (hand.size() < 10) {
            hand.add(Card);
            Card.pool.add(this);
            this.gold -= 3;
        }
        else {
            System.out.println("Hand is full");
        }
    }

    void UseCard (Carte Card) {
        if (board.size() < 7) {
            board.add(Card);
            boolean end = false;
            while (!end) {
                for (Carte i : board) {
                    if (i == Card) {
                        hand.remove(i);
                        end = true;
                    }
                }
            }
        } else {
            System.out.println("Board is full");
        }
    }



    void SellCard (Carte Card) {
        board.remove(Card);
        Card.pool.remove(this);
        this.gold+=1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getPV() {
        return PV;
    }

    public void setPV(int PV) {
        this.PV = PV;
    }

    public int getLVL() {
        return LVL;
    }

    public void setLVL(int LVL) {
        this.LVL = LVL;
    }

    public ArrayList<Carte> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Carte> hand) {
        this.hand = hand;
    }

    public ArrayList<Carte> getBoard() {
        return board;
    }

    public void setBoard(ArrayList<Carte> board) {
        this.board = board;
    }

    public ArrayList<Carte> getShop() {
        return shop;
    }
}
