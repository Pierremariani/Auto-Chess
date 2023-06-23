import java.util.ArrayList;

public class Carte extends Game {
    String nom;
    int Attack,PV,index,LVL;
    ArrayList<Joueur> pool = new ArrayList<>();

    public Carte(String nom, int attack, int PV, int LVL) {
        this.nom = nom;
        Attack = attack;
        this.PV = PV;
        this.LVL = LVL;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "nom='" + nom + '\'' +
                ", Attack=" + Attack +
                ", PV=" + PV +
                ", index=" + index +
                ", LVL=" + LVL +
                ", pool=" + pool +
                '}';
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getLVL() {
        return LVL;
    }

    public void setLVL(int LVL) {
        this.LVL = LVL;
    }

    public Carte (int Index) {
        this.index = Index;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAttack() {
        return Attack;
    }

    public void setAttack(int attack) {
        Attack = attack;
    }

    public int getPV() {
        return PV;
    }

    public void setPV(int PV) {
        this.PV = PV;
    }

    public ArrayList<Joueur> getPool() {
        return pool;
    }

    public void setPool(ArrayList<Joueur> pool) {
        this.pool = pool;
    }
}
