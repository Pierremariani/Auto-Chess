import java.util.ArrayList;
import java.util.Random;

public class Game {

    ArrayList<Joueur> listePlayers = new ArrayList<>();
    ArrayList<Carte> listeCarte = new ArrayList<>();
    Joueur a,b,c,d,e,f,g,h;
    boolean end = false;

    public Game(Joueur a, Joueur b, Joueur c, Joueur d, Joueur e, Joueur f, Joueur g, Joueur h,ArrayList<Carte> listeCarte) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.listeCarte = listeCarte;
        listePlayers.add(a);
        listePlayers.add(b);
        listePlayers.add(c);
        listePlayers.add(d);
        listePlayers.add(e);
        listePlayers.add(f);
        listePlayers.add(g);
        listePlayers.add(h);
    }

    public Game () {

    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    void Roll (Joueur c) {
        if (c.getGold() >= 1) {
            for (int j = 0; j < a.shop.size(); j++) {
                a.shop.remove(j);
            }
            Random random = new Random();
            for (int i = 0; i < a.LVL; i++) {
                int randomCard = random.nextInt(3);  // a changer selon le nombre de carte
                Carte b = new Carte(randomCard);
                if (b.pool.size() < 9) {
                    a.shop.add(b);
                } else {
                    i -= 1;
                    System.out.println(" Il y a deja trop de Carte index " + randomCard + " en jeu");
                }

            }
            c.setGold(c.getGold() - 1);
        }
    }

    void EndCondition () {
        for (Joueur i : listePlayers) {
            if (i.getPV() <= 0){
                listePlayers.remove(i);
                System.out.println(i+" est mort");
                if (listePlayers.size() == 1) {
                    end = true;
                }
            }
        }
    }



    Joueur fight(Joueur a, Joueur b) {
        Carte[] simulatedBoardA = a.board.toArray(new Carte[a.board.size()]);
        Carte[] simulatedBoardB = b.board.toArray(new Carte[b.board.size()]);
        boolean turn = true; // TRUE == TOUR DE JOUEUR A SINON JOUEUR B
        boolean end = false;
        int nb = 0;
        int nb2 = 0;
        int nbdeath = 0;
        int nbdeath2 = 0;

        if (a.board.size() > b.board.size()) {
            while (!end) {
                if (turn) {
                    Random random = new Random();
                    int randomHit = random.nextInt(simulatedBoardB.length) ;
                    while (simulatedBoardB[randomHit].getIndex() == 999  ) {
                        randomHit = random.nextInt(simulatedBoardB.length);
                    }
                    simulatedBoardA[nb].setPV(simulatedBoardA[nb].getPV() - simulatedBoardB[randomHit].getAttack());
                    simulatedBoardB[randomHit].setPV(simulatedBoardB[randomHit].getPV() - simulatedBoardA[nb].getAttack());

                    if (simulatedBoardA[nb].getPV() <= 0) {
                        Carte mort = new Carte(999);
                        simulatedBoardA[nb] = mort;
                        nbdeath = 0;
                        for (int i = 0 ; i< simulatedBoardA.length; i++) {
                            if (simulatedBoardA[i].getIndex() == 999) {
                                nbdeath++;
                                if (nbdeath == simulatedBoardA.length) {
                                    int damage = simulatedBoardB.length;
                                    for (Carte carte : simulatedBoardB) {
                                        damage += carte.getLVL();
                                    }
                                    a.setPV(a.getPV() - damage);
                                    System.out.println("B a gagné en infligeant " + damage);
                                    return b;
                                }
                            }
                        }
                    }

                    if (simulatedBoardB[randomHit].getPV() <= 0) {
                        Carte mort = new Carte(999);
                        simulatedBoardB[randomHit] = mort;
                        nbdeath2 = 0;
                        for (int i = 0 ; i< simulatedBoardB.length; i++) {
                            if (simulatedBoardB[i].getIndex() == 999) {
                                nbdeath2++;
                                if (nbdeath2 == simulatedBoardB.length) {
                                    int damage = simulatedBoardA.length;
                                    for (Carte carte : simulatedBoardA) {
                                            damage += carte.getLVL();
                                    }
                                    b.setPV(b.getPV() - damage);
                                    System.out.println("A a gagné en infligeant " + damage);
                                    return a;
                                }
                            }
                        }
                    }

                    if (nb < simulatedBoardA.length - nbdeath) {
                        nb++;
                    } else {
                        nb = 0;
                    }
                    turn = false;
                }

                if (!turn) {
                    Random random = new Random();
                    int randomHit = random.nextInt(simulatedBoardA.length);
                    while (simulatedBoardA[randomHit].getIndex() == 999 ) {
                        randomHit = random.nextInt(simulatedBoardA.length );
                    }
                    simulatedBoardB[nb2].setPV(simulatedBoardB[nb2].getPV() - simulatedBoardA[randomHit].getAttack());
                    simulatedBoardA[randomHit].setPV(simulatedBoardA[randomHit].getPV() - simulatedBoardB[nb2].getAttack());

                    if (simulatedBoardA[randomHit].getPV() <= 0) {
                        Carte mort = new Carte(999);
                        simulatedBoardA[randomHit] = mort;
                        nbdeath = 0;
                        for (int i = 0 ; i< simulatedBoardA.length; i++) {
                            if (simulatedBoardA[i].getIndex() == 999) {
                                nbdeath++;
                                if (nbdeath == simulatedBoardA.length) {
                                    int damage = simulatedBoardB.length;
                                    for (Carte carte : simulatedBoardB) {
                                        damage += carte.getLVL();
                                    }
                                    a.setPV(a.getPV() - damage);
                                    System.out.println("B a gagné en infligeant " + damage);
                                    return b;
                                }
                            }
                        }
                    }

                    if (simulatedBoardB[nb2].getPV() <= 0) {
                        Carte mort = new Carte(999);
                        simulatedBoardB[nb2] = mort;
                        nbdeath2 = 0;
                        for (int i = 0 ; i< simulatedBoardB.length; i++) {
                            if (simulatedBoardB[i].getIndex() == 999) {
                                nbdeath2++;
                                if (nbdeath2 == simulatedBoardB.length) {
                                    int damage = simulatedBoardA.length;
                                    for (Carte carte : simulatedBoardA) {
                                        damage += carte.getLVL();
                                    }
                                    b.setPV(b.getPV() - damage);
                                    System.out.println("A a gagné en infligeant " + damage);
                                    return a;
                                }
                            }
                        }
                    }
                    if (nb2 < simulatedBoardB.length - nbdeath2) {
                        nb2++;
                    } else {
                        nb2 = 0;
                    }
                    turn = true;
                }
            }
        } if (a.board.size() < b.board.size()) {
            while (!end) {
                if (turn) {
                    Random random = new Random();
                    int randomHit = random.nextInt(simulatedBoardA.length) ;
                    while (simulatedBoardA[randomHit].getIndex() == 999 ) {
                        randomHit = random.nextInt(simulatedBoardA.length);
                    }
                    simulatedBoardB[nb2].setPV(simulatedBoardB[nb2].getPV() - simulatedBoardA[randomHit].getAttack());
                    simulatedBoardA[randomHit].setPV(simulatedBoardA[randomHit].getPV() - simulatedBoardB[nb2].getAttack());

                    if (simulatedBoardA[randomHit].getPV() <= 0) {
                        Carte mort = new Carte(999);
                        simulatedBoardA[randomHit] = mort;
                        nbdeath = 0;
                        for (int i = 0 ; i< simulatedBoardA.length; i++) {
                            if (simulatedBoardA[i].getIndex() == 999) {
                                nbdeath++;
                                if (nbdeath == simulatedBoardA.length) {
                                    int damage = simulatedBoardB.length;
                                    for (Carte carte : simulatedBoardB) {
                                        damage += carte.getLVL();
                                    }
                                    a.setPV(a.getPV() - damage);
                                    System.out.println("B a gagné en infligeant " + damage);
                                    return b;
                                }
                            }
                        }
                    }

                    if (simulatedBoardB[nb2].getPV() <= 0) {
                        Carte mort = new Carte(999);
                        simulatedBoardB[nb2] = mort;
                        nbdeath2 = 0;
                        for (int i = 0 ; i< simulatedBoardB.length; i++) {
                            if (simulatedBoardB[i].getIndex() == 999) {
                                nbdeath2++;
                                if (nbdeath2 == simulatedBoardB.length) {
                                    int damage = simulatedBoardA.length;
                                    for (Carte carte : simulatedBoardA) {
                                        damage += carte.getLVL();
                                    }
                                    b.setPV(b.getPV() - damage);
                                    System.out.println("A a gagné en infligeant " + damage);
                                    return a;
                                }
                            }
                        }
                    }

                    if (nb2 < simulatedBoardB.length - nbdeath2) {
                        nb2++;
                    } else {
                        nb2 = 0;
                    }
                    turn = false;
                }

                if (!turn) {
                    Random random = new Random();
                    int randomHit = random.nextInt(simulatedBoardB.length );
                    while (simulatedBoardB[randomHit].getIndex() == 999 ) {
                        randomHit = random.nextInt(simulatedBoardB.length);
                    }
                    simulatedBoardA[nb].setPV(simulatedBoardA[nb].getPV() - simulatedBoardB[randomHit].getAttack());
                    simulatedBoardB[randomHit].setPV(simulatedBoardB[randomHit].getPV() - simulatedBoardA[nb].getAttack());

                    if (simulatedBoardA[nb].getPV() <= 0) {
                        Carte mort = new Carte(999);
                        simulatedBoardA[nb] = mort;
                        nbdeath = 0;
                        for (int i = 0 ; i< simulatedBoardA.length; i++) {
                            if (simulatedBoardA[i].getIndex() == 999) {
                                nbdeath++;
                                if (nbdeath == simulatedBoardA.length) {
                                    int damage = simulatedBoardB.length;
                                    for (Carte carte : simulatedBoardB) {
                                        damage += carte.getLVL();
                                    }
                                    a.setPV(a.getPV() - damage);
                                    System.out.println("B a gagné en infligeant " + damage);
                                    return b;
                                }
                            }
                        }
                    }

                    if (simulatedBoardB[randomHit].getPV() <= 0) {
                        Carte mort = new Carte(999);
                        simulatedBoardB[randomHit] = mort;
                        nbdeath2 = 0;
                        for (int i = 0 ; i< simulatedBoardB.length; i++) {
                            if (simulatedBoardB[i].getIndex() == 999) {
                                nbdeath2++;
                                if (nbdeath2 == simulatedBoardB.length) {
                                    int damage = simulatedBoardA.length;
                                    for (Carte carte : simulatedBoardA) {
                                        damage += carte.getLVL();
                                    }
                                    b.setPV(b.getPV() - damage);
                                    System.out.println("A a gagné en infligeant " + damage);
                                    return a;
                                }
                            }
                        }
                    }

                    if (nb < simulatedBoardA.length - nbdeath) {
                        nb++;
                    } else {
                        nb = 0;
                    }
                    turn = true;
                }
            }
        }
        System.out.println("egalite");
        return null;
    }




}
