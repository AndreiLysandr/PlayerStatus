package PlayerStatus;

public class PlayerStatus {
    protected static String gameName;
    private final String nickname;
    private int health;
    private int lives;
    private int score;
    private String weaponInHand;
    private double positionX;
    private double positionY;

//  Comportament

//  1. (3 metode de initializare cu diverse seturi de parametrii)
    public PlayerStatus(String nickname) {

        this.nickname = nickname;
    }

    public PlayerStatus(String nickname, int lives) {
        this(nickname);
        this.lives = lives;
    }

    public PlayerStatus(String nickname, int lives, int score) {
        this(nickname, lives);
        this.score = score;
    }

//  (Metoda afla daca numarul este perfect)
    private static boolean perfectNumber(int number) {
        if (number == sumaDivizorilor(number) + 1) {
            return true;
        }
        return false;
    }

    private static int sumaDivizorilor(int number) {
        int suma = 0;
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                suma += i;
            }
        }
        return suma;
    }

//  (Metoda afla daca numarul este prim)
    private boolean primeNumber(int artifactNumber) {
        for (int i = 2; i < artifactNumber / 2; i++) {
            if (artifactNumber % i == 0) {
                return false;
            }
        }
        return true;
    }
//  (Metoda afla daca suma cifrelor numarului sunt divizibile cu 3)
    private boolean sumDividedToThree(int artifactNumber) {
        int sum = 0;

        if (artifactNumber % 2 != 0) {
            return false;
        }

        while (artifactNumber > 0) {

            int lastDigit = artifactNumber % 10;
            sum += lastDigit;

            artifactNumber /= 10;
        }
        if (sum % 3 == 0) {
            return true;
        }
        return false;
    }

//  2. (Metoda artifact actualizeaza starea jucatorului in functie de numarul introdus)
    public void findArtifact(int artifactCode) {
        if (perfectNumber(artifactCode)) {
            System.out.println("Numatul introdus este perfect.\n"
                    + "\nArtefactul gasit va produce urmatoarele efecte:"
                    + "\n\t- Scorul creste cu 5000 puncte"
                    + "\n\t- Primesti 1 viata extra"
                    + "\n\t- Viata este incarcata la 100%\n");
            score += 5000;
            lives += 1;
            health = 100;
        }
        else if (primeNumber(artifactCode)) {
            System.out.println("Numarul introdus este prim.\n"
                    + "\nArtefactul gasit va produce urmatoarele efecte:"
                    + "\n\t- Scorul creste cu 1000 puncte"
                    + "\n\t- Primestu 2 vieti extra"
                    + "\n\t- viata creste cu 25%");
            score += 1000;
            lives += 2;
            if (health >= 75) {
                health = 100;
                System.out.println("Viata a fost incarcata la 100%\n");
            }
            else {
                health += 25;
                System.out.println("Viata a fost incarcata la " + getHealth() + "%\n");
            }
        }
        else if (sumDividedToThree(artifactCode)) {
            System.out.println("Numarul introdus este par si suma cifrelor este divizibila cu 3\n"
                    + "\nArtefactul gasit va produce urmatoarele efecte:"
                    + "\n\t- Scorul scade cu 3000 puncte"
                    + "\n\t- Viata scade cu 25%");
            if (score > 3000) {
                score -= 3000;
            }
            if (health <= 25 && lives <= 0) {
                health = 0;
                System.out.println("Se pare ca nu ai avut deloc noroc!"
                                   + "\nViata ta a ajuns la " + getHealth() + "% si numarul de vieti ramase este 0.\n");
            }
            else if (health <= 25) {
                health = 100;
                lives -= 1;
                System.out.println("\nViata ta a ajuns la " + getHealth() + "% iar vietile au ajuns la " + getLives());
            }
            else {
                health -= 25;
                System.out.println("Viata ta a ajuns la " + getHealth() + "%\n");
            }
        }
        else {
            System.out.println("Din pacate numarul introdus nu este vrajit,"
                    + " prin urmare, artefactul nu a putut fi deschis."
                    + "\nPrimesti urmatorul efect:"
                    + "\n\t- Scorul creste cu " + artifactCode + " puncte\n");
            score += artifactCode;
        }
    }

//  3. (Metoda schimba arma din mana jucatorului)
    public boolean setWeaponInHand(String weaponInHand) {
        // knife
        if (weaponInHand.equalsIgnoreCase("knife") && score >= 1000) {
            this.weaponInHand = weaponInHand;
            score -= 1000;
            return true;
        }
        // kalahsnikov
        else if (weaponInHand.equalsIgnoreCase("kalashnikov") && score >= 10000) {
            this.weaponInHand = weaponInHand;
            score -= 10000;
            return true;
        }
        // sniper
        else if (weaponInHand.equalsIgnoreCase("sniper") && score >= 20000) {
            this.weaponInHand = weaponInHand;
            score -= 20000;
            return true;
        }
        else {
            return false;
        }
    }

//  4. (Metoda returneaza arma jucatorului)
    public String getWeaponInHand() {
        return weaponInHand;
    }

//  5. (Metodele transforma campurile positionX si positionY in proprietati)
    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

//  7. (Metoda actualizeaza pozitia jucatorului)
    public void movePlayerTo (double positionX, double positionY) {
        setPositionX(positionX);
        setPositionY(positionY);
    }

//  6. (Metodele transforma campul clasei gameName in proprietate)
    public static String getGameName() {
        return gameName;
    }

    protected static void setGameName(String newGameName) {
        gameName = newGameName;
    }

//  8. (Metoda transforma transforma campul intr-o proprietate read-only)
    public String getNickname () {
        return nickname.toUpperCase();
    }

//  9. (Metoda simuleaza jucatorului o batalie, pentru a sfatui jucatorul daca ar trebui sa atace)
    public static boolean shouldAttackOpponent (PlayerStatus player, PlayerStatus opponent) {
        if (player.weaponInHand.equals("knife") && opponent.weaponInHand.equals("knife")) {
            return winProb(player, opponent);
        }
        else if (player.weaponInHand.equals("kalashnikov") && opponent.weaponInHand.equals("kalashnikov")) {
            return winProb(player, opponent);
        }
        else if (player.weaponInHand.equals("sniper") && opponent.weaponInHand.equals("sniper")) {
            return winProb(player, opponent);
        }
        else if (positionOfPlayers(player, opponent) > 1000) {
            if (player.weaponInHand.equals("sniper")) {
                return true;
            }
            return player.weaponInHand.equals("kalashnikov") && !opponent.weaponInHand.equals("sniper");
        }
        else if (positionOfPlayers(player, opponent) < 1000) {
            if (player.weaponInHand.equals("kalashnikov")) {
                return true;
            }
            return player.weaponInHand.equals("sniper") && !opponent.weaponInHand.equals("kalashnikov");
        }
        return false;
    }

    private static boolean winProb (PlayerStatus player, PlayerStatus opponent) {
        int playerWinRate = (3 * player.health + player.score / 1000) / 4;
        int opponentWinRate = (3 * opponent.health + opponent.score / 1000) / 4;

        return playerWinRate > opponentWinRate;
    }

    private static double positionOfPlayers(PlayerStatus player, PlayerStatus opponent) {
        double generalPosX = player.positionX - opponent.positionX;
        double generalPosY = player.positionY - opponent.positionY;

        return Math.sqrt(Math.pow(generalPosX, 2) + Math.pow(generalPosY, 2));
    }

//  Restul metodelor necesare pentru functionalitatea codului
    private void winnableScenario (PlayerStatus player) {
        if (player.health > 20) {
            player.health -= 25;
        }
        else if (player.lives > 0) {
            player.health = 100;
            player.lives -= 1;
        } else {
            player.health = 0;
            player.lives = 0;
        }
    }

    private void loosableScenario (PlayerStatus player) {
        if (player.health > 55) {
            player.health -= 70;
        }
        else if (player.lives > 0) {
            player.health = 100;
            player.lives -= 1;
        } else {
            player.health = 0;
            player.lives = 0;
        }
    }

    public static void attackOpponent (PlayerStatus player, PlayerStatus opponent) {
        if (player.weaponInHand.equals("knife") && opponent.weaponInHand.equals("knife")) {
            if (winProb(player, opponent)) {
                player.winnableScenario(player);
                opponent.loosableScenario(opponent);

                player.updateScore();
                opponent.decreaseScore();
                System.out.println("Runda a fost castigata de: " + player.getNickname());

            } else {
                player.loosableScenario(player);
                opponent.winnableScenario(opponent);

                player.decreaseScore();
                opponent.updateScore();
                System.out.println("Runda a fost castigata de: " + opponent.getNickname());
            }
        }
        else if (player.weaponInHand.equals("kalashnikov") && opponent.weaponInHand.equals("kalashnikov")) {
            if (winProb(player, opponent)) {
                player.winnableScenario(player);
                opponent.loosableScenario(opponent);

                player.updateScore();
                opponent.decreaseScore();
                System.out.println("Runda a fost castigata de: " + player.getNickname());

            } else {
                player.loosableScenario(player);
                opponent.winnableScenario(opponent);

                player.decreaseScore();
                opponent.updateScore();
                System.out.println("Runda a fost castigata de: " + opponent.getNickname());
            }
        }
        else if (player.weaponInHand.equals("sniper") && opponent.weaponInHand.equals("sniper")) {
            if (winProb(player, opponent)) {
                player.winnableScenario(player);
                opponent.loosableScenario(opponent);

                player.updateScore();
                opponent.decreaseScore();
                System.out.println("Runda a fost castigata de: " + player.getNickname());

            } else {
                player.loosableScenario(player);
                opponent.winnableScenario(opponent);

                player.decreaseScore();
                opponent.updateScore();
                System.out.println("Runda a fost castigata de: " + opponent.getNickname());
            }
        }
        else if (positionOfPlayers(player, opponent) > 1000) {
            if (player.weaponInHand.equals("sniper")) {
                player.winnableScenario(player);
                opponent.loosableScenario(opponent);

                player.updateScore();
                opponent.decreaseScore();
                System.out.println("Runda a fost castigata de: " + player.getNickname());

            } else if (player.weaponInHand.equals("kalashnikov") && !opponent.weaponInHand.equals("sniper")) {
                player.winnableScenario(player);
                opponent.loosableScenario(opponent);

                player.updateScore();
                opponent.decreaseScore();
                System.out.println("Runda a fost castigata de: " + player.getNickname());

            } else {
                player.loosableScenario(player);
                opponent.winnableScenario(opponent);

                player.decreaseScore();
                opponent.updateScore();
                System.out.println("Runda a fost castigata de: " + opponent.getNickname());
            }
        }
        else if (positionOfPlayers(player, opponent) < 1000) {
            if (player.weaponInHand.equals("kalashnikov")) {
                player.winnableScenario(player);
                opponent.loosableScenario(opponent);

                player.updateScore();
                opponent.decreaseScore();
                System.out.println("Runda a fost castigata de: " + player.getNickname());

            } else if (player.weaponInHand.equals("sniper") && !opponent.weaponInHand.equals("kalashnikov")) {
                player.winnableScenario(player);
                opponent.loosableScenario(opponent);

                player.updateScore();
                opponent.decreaseScore();
                System.out.println("Runda a fost castigata de: " + player.getNickname());

            } else {
                player.loosableScenario(player);
                opponent.winnableScenario(opponent);

                player.decreaseScore();
                opponent.updateScore();
                System.out.println("Runda a fost castigata de: " + opponent.getNickname());
            }
        }
    }

    public void playerIntel() {
        System.out.println("\nInformatiile jucatorului " + nickname.toUpperCase() + " sunt:"
                           + "\n\t- Punctele de viata: " + health + "%"
                           + "\n\t- Vieti ramase: " + lives + (lives == 1 ? " viata" : " vieti")
                           + "\n\t- Punctele de scor: " + score
                           + "\n\t- Arma din mana: " + weaponInHand
                           + "\n\t- Pozitia jucatorului: x -> " + positionX + "  y -> " + positionY);
        System.out.println();
    }

    public String chooseWeapon (String weaponNumber) {
        if (weaponNumber.equalsIgnoreCase("knife")) {
            return "knife";
        }
        else if (weaponNumber.equalsIgnoreCase("kalashnikov")) {
            return "kalashnikov";
        }
        else if (weaponNumber.equalsIgnoreCase("sniper")) {
            return "sniper";
        }
        return "unavailable";
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    private void updateScore () {
        this.score += 3000;
    }

    private void decreaseScore () {
        this.score -= 1000;
    }

    public void playerPosition () {
        System.out.println("Pozitia ta actuala este: x -> " + positionX + "  si  y -> " + positionY);
    }
}


