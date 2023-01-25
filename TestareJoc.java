package PlayerStatus;
import java.util.Scanner;

public class TestareJoc {

    public static void info() {
        System.out.println("\t1. Informatii despre caracter"
                           + "\n\t2. Cauta un artefact"
                           + "\n\t3. Schimba arma din mana"
                           + "\n\t4. Schimba pozitia in care te afli"
                           + "\n\t5. Simuleaza o batalie pentru a afla daca ai sanse reale de castig"
                           + "\n\t6. Ataca oponentul"
                           + "\n\t7. Afiseaza comenzile disponibile\n");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println();

        PlayerStatus.setGameName("~~~ Cthulhu's Nest ~~~");

        PlayerStatus opponent = new PlayerStatus("Cthulhu", 1, 22000);
        opponent.setHealth(100);
        opponent.setWeaponInHand("kalashnikov");
        opponent.setPositionX(3500);
        opponent.setPositionY(1500);


        System.out.println(PlayerStatus.getGameName() + "\n\nIntroduceti numele cu care doriti sa jucati");

        PlayerStatus player = new PlayerStatus(sc.nextLine(), 1, 12000);
        player.setHealth(100);
        player.setWeaponInHand("knife");
        player.setPositionX(1500);
        player.setPositionY(3500);


        System.out.println("Felicitari " + player.getNickname() + " profilul tau este aproape gata!\n");
        System.out.println("Mai jos ai o lista de comenzi disponibile. "
                           + "\nJocul se va termina dupa 5 runde de atac sau "
                           + "cand unul dintre jucatori ramane fara vieti si fara puncte de viata.\n");

        System.out.println("Word of Wisdom!"
                           + "\nSfatul meu este ca prima data sa iti cumperi o arma mai puternica."
                           + "\nAsa iti vei creste sansele de castig impotriva lui " + opponent.getNickname()
                           + "\nMomentan ai arma default a jocului: knife\n");

        System.out.println("Succes!");

        System.out.println("\nComenzile disponibile sunt:");
        TestareJoc.info();

        int roundCounter = 0;
        int artifactCounter = 0;

        while (roundCounter < 5) {
            if (player.getHealth() <= 0 && player.getLives() <= 0) {
                System.out.println("~~~ GAME OVER ~~~"
                                   + "\nNumarul de vieti si punctele de viata au ajuns la 0!");
                break;
            }
            else if (opponent.getHealth() <= 0 && opponent.getLives() <= 0) {
                System.out.println("!!! FELICITARI !!!"
                                   + "\nAi reusit sa il infrangi pe " + opponent.getNickname());
                break;
            }
            else {
                System.out.println("Alege urmatoarea actiune:");
            }
            int command = sc.nextInt();

            switch (command) {

                case 1:
                    player.playerIntel();
                    break;

                case 2:
                    if (artifactCounter == 0) {
                        System.out.println("\nSe pare ca ai gasit un artefact!"
                                           + "\nIntrodu un numar pentru a reusi sa deblochezi artefactul\n");

                        player.findArtifact(sc.nextInt());
                        artifactCounter = 1;
                    }
                    else {
                        System.out.println("\nPoti gasi un singur artefact pe runda!"
                                          + "\nRunda se termina dupa ce " + opponent.getNickname() + " este atacat!\n");
                    }
                    break;

                case 3:
                    System.out.println("\nMomentan ai " + player.getScore() + " puncte de scor"
                                       + "\nAlege una dintre armele de mai jos tastand numele armei:"
                                       + "\n\t- knife: 1000 puncte de scor"
                                       + "\n\t- kalashnikov: 10.000 puncte de scor"
                                       + "\n\t- sniper: 20.000 puncte de scor");
                    String weaponType = sc.next();

                    if (player.setWeaponInHand(player.chooseWeapon(weaponType))) {
                        System.out.println("\nArma a fost schimbata cu succes!"
                                           + "\nNoua ta arma este: " + player.getWeaponInHand() + "\n");
                        break;
                    }
                    else {
                        System.out.println("Nu ai suficiente puncte de scor sau arma introdusa nu este disponibila!"
                                           + "\nPoti incerca sa cumperi o arma mai ieftina repetand aceeasi actiune"
                                           + " sau poti ramane cu arma actuala.\n");
                    }
                    break;

                case 4:
                    System.out.println("\nPentru a schimba pozitia in care te afli tasteaza mai jos "
                                       + "doua numere ce vor reprezenta noua ta pozitie verticala si orizontala.");
                    player.movePlayerTo(sc.nextDouble(), sc.nextDouble());

                    System.out.println("\nPozitia a fost schimbata cu succes");
                    player.playerPosition();

                    System.out.println();
                    break;

                case 5:
                    System.out.println("\nUrmeaza sa simulam o batalie intre tine si " + opponent.getNickname() + ":");

                    if (PlayerStatus.shouldAttackOpponent(player, opponent)) {
                        System.out.println("- ai sanse sa castigi aceasta runda!"
                                           + "\n- totusi tine cont si de punctele de viata pe care le ai "
                                           + "cand doresti sa il ataci pe " + opponent.getNickname() + "\n");
                    }
                    else {
                        System.out.println("- nu ai sanse de a castiga aceasta runda!"
                                           + "\n- incearca sa cauti un artefact, daca nu ai gasit deja unul!\n");
                    }
                    break;

                case 6:
                    System.out.println("\nL-ai atacat pe " + opponent.getNickname());
                    PlayerStatus.attackOpponent(player, opponent);

                    System.out.println("\nIn urma bataliei:"
                                       + "\n\t- Punctele de viata au ajuns la: " + player.getHealth() + "%"
                                       + "\n\t- Numarul de vieti a ajuns la: " + player.getLives()
                                       + "\n\t- Scorul a ajuns la: " + player.getScore() + "\n");

                    artifactCounter = 0;
                    roundCounter++;
                    break;

                case 7:
                    System.out.println("\nComenzile disponibile sunt:");
                    TestareJoc.info();
                    break;

                default:
                    System.out.println("\nAceasta nu este o comanda disponibila!" + "\nComenzile disponibile sunt:");
                    TestareJoc.info();
                    break;
            }
            if (roundCounter >= 5) {
                System.out.println("\n~~~ GAME OVER ~~~"
                        + "\n\nS-au terminat cele 5 runde!"
                        + "\nDin pacate nu ai reusit sa il infrangi pe " + opponent.getNickname() + ".\n"
                        + "\nLa finalul luptei\n"
                        + "\n" + opponent.getNickname() + " mai avea:"
                        + "\n\t- " + opponent.getHealth() + " puncte de viata"
                        + "\n\t- " + opponent.getLives() + " vieti"
                        + "\n\n" + player.getNickname() + " mai avea:"
                        + "\n\t- " + player.getHealth() + " puncte de viata"
                        + "\n\t- " + player.getLives() + " vieti");
            }
        }
    }
}
