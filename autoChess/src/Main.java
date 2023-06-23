import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

       Carte a = new Carte("Roi", 4, 14, 4);
       Carte b = new Carte("Reine", 8, 2, 3);
       Carte c = new Carte("Paysan", 2, 4, 2);

       ArrayList<Carte> listeCarte = new ArrayList<>();
       listeCarte.add(a);
       listeCarte.add(b);
       listeCarte.add(c);

       Joueur abc = new Joueur("Pierre", 40);
       Joueur random = new Joueur("Random", 40);
       Joueur random2 = new Joueur("Random2",40);
       Joueur random3 = new Joueur("Random3",40);
       Joueur random4 = new Joueur("Random4",40);
       Joueur random5 = new Joueur("Random5",40);
       Joueur random6 = new Joueur("Random6",40);

       Game partie = new Game(abc,random,random2,random4,random3,random4,random5,random6,listeCarte);
       Scanner scanner = new Scanner(System.in);

       while (!partie.isEnd()) {
          abc.setStart(listeCarte);
          System.out.println(abc.getShop());
          while (abc.getGold() > 0) {
             System.out.println("Tapez 1 pour Roll");
             int roll = scanner.nextInt();
             if (roll == 1) {
                partie.Roll(abc);
                System.out.println(abc.getShop());
                System.out.println(abc.getGold() +" Golds restants");
             }
          }

       }




    }
}