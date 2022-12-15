import extensions.*;

class IthyphalGame extends Program {

    int DIMENSION = 0;

    // MONSTER VARIABLES
    final double ZOMBIE_SPAWN_PROBA = 0.5;
    final int ZOMBIE_ATTACK = 3;
    final int ZOMBIE_HEALT = 20;

    final double SKELETON_SPAWN_PROBA = 0.3;
    final int SKELETON_ATTACK = 5;
    final int SKELETON_HEALT = 15;

    final double VAMPIRE_SPAWN_PROBA = 0.2;
    final int VAMPIRE_ATTACK = 7;
    final int VAMPIRE_HEALT = 10;

    // LOOT VARIABLES
    final double RING_SPAWN_PROBA = 0.33;

    final double POTION_SPAWN_PROBA = 0.33;

    final double ARMOR_SPAWN_PROBA = 0.33;

    // QUESTION

    final String[] FILESQUESTION = new String[]{"init_question.csv"};
    Question[] QUESTIONS = loadAllQuestions(loadQuestionFile(FILESQUESTION[0]));


    Player newPlayer(String nom) {
        /* Constructor of class PLAYER */
        Player p = new Player();
        p.nom = nom;
        return p;
    }

    void testNewPlayer() {
        /* Test of the function newPlayer */
        Player p = newPlayer("Bob");
        assertEquals("Bob", p.nom);
        assertEquals(5, p.attack);
        assertEquals(50, p.healt);
        assertEquals(0, p.shield);
    }

    Loot newLoot(TypeLoot type, int amount) {
        /* Constructor of class LOOT */
        Loot l = new Loot();
        l.type = type;
        l.amount = amount;
        return l;
    }

    void testNewLoot() {
        /* Test of the function newLoot */
        Loot l = newLoot(TypeLoot.RING, 5);
        assertEquals(TypeLoot.RING, l.type);
        assertEquals(5, l.amount);
    }

    void addLoot(Player p, Loot l) {
        /* Add the loot to the player */
        if(l.type == TypeLoot.RING) {
            p.attack += l.amount;
        } else if(l.type == TypeLoot.POTION) {
            p.healt += l.amount;
        } else if(l.type == TypeLoot.ARMOR) {
            p.shield += l.amount;
        }
    }

    void testAddLoot() {
        /* Test of the function addLoot */
        Player p = newPlayer("Bob");
        assertEquals(5, p.attack);
        Loot l = newLoot(TypeLoot.RING, 5);
        addLoot(p, l);
        assertEquals(10, p.attack);
        assertEquals(50, p.healt);
        assertEquals(0, p.shield);
    }

    Monster newMonster(TypeMonster type, int attack, int healt) {
        /* Constructor of class MONSTER */
        Monster m = new Monster();
        m.type = type;
        m.attack = attack;
        m.healt = healt;
        return m;
    }

    void testNewMonster() {
        /* Test of the function newMonster */
        Monster m = newMonster(TypeMonster.ZOMBIE, 5, 50);
        assertEquals(TypeMonster.ZOMBIE, m.type);
        assertEquals(5, m.attack);
        assertEquals(50, m.healt);
    }

    boolean playerAttack(Player p, Monster m) {
        /* The player attack the monster */
        m.healt -= p.attack;
        if(m.healt <= 0) {
            return true; // The monster is dead
        } else {
            return false; // The monster is alive
        }
    }

    void testPlayerAttack() {
        /* Test of the function playerAttack */
        Player p = newPlayer("Bob");
        Monster m = newMonster(TypeMonster.ZOMBIE, 5, 50);
        assertEquals(50, m.healt);
        boolean dead = playerAttack(p, m);
        assertEquals(45, m.healt);
        assertFalse(dead);
        dead = playerAttack(p, m);
        assertEquals(40, m.healt);
        assertFalse(dead);
        dead = playerAttack(p, m);
        assertEquals(35, m.healt);
        assertFalse(dead);
        dead = playerAttack(p, m);
        assertEquals(30, m.healt);
        assertFalse(dead);
        dead = playerAttack(p, m);
        assertEquals(25, m.healt);
        assertFalse(dead);
        dead = playerAttack(p, m);
        assertEquals(20, m.healt);
        assertFalse(dead);
        dead = playerAttack(p, m);
        assertEquals(15, m.healt);
        assertFalse(dead);
        dead = playerAttack(p, m);
        assertEquals(10, m.healt);
        assertFalse(dead);
        dead = playerAttack(p, m);
        assertEquals(5, m.healt);
        assertFalse(dead);
        dead = playerAttack(p, m);
        assertEquals(0, m.healt);
        assertTrue(dead);
    }

    boolean monsterAttack(Monster m, Player p) {
        /* The monster attack the player */
        p.healt -= m.attack;
        if(p.healt <= 0) {
            return true; // The player is dead
        } else {
            return false; // The player is alive
        }
    }

    void testMonsterAttack() {
        /* Test of the function monsterAttack */
        Player p = newPlayer("Bob");
        Monster m = newMonster(TypeMonster.ZOMBIE, 5, 50);
        assertEquals(50, p.healt);
        boolean dead = monsterAttack(m, p);
        assertEquals(45, p.healt);
        assertFalse(dead);
        dead = monsterAttack(m, p);
        assertEquals(40, p.healt);
        assertFalse(dead);
        dead = monsterAttack(m, p);
        assertEquals(35, p.healt);
        assertFalse(dead);
        dead = monsterAttack(m, p);
        assertEquals(30, p.healt);
        assertFalse(dead);
        dead = monsterAttack(m, p);
        assertEquals(25, p.healt);
        assertFalse(dead);
        dead = monsterAttack(m, p);
        assertEquals(20, p.healt);
        assertFalse(dead);
        dead = monsterAttack(m, p);
        assertEquals(15, p.healt);
        assertFalse(dead);
        dead = monsterAttack(m, p);
        assertEquals(10, p.healt);
        assertFalse(dead);
        dead = monsterAttack(m, p);
        assertEquals(5, p.healt);
        assertFalse(dead);
        dead = monsterAttack(m, p);
        assertEquals(0, p.healt);
        assertTrue(dead);
    }

    boolean isDead(Player p) {
        /* Check if the player is dead */
        if(p.healt <= 0) {
            return true;
        } else {
            return false;
        }
    }

    void testIsDeadP() {
        /* Test of the function isDead */
        Player p = newPlayer("Bob");
        assertFalse(isDead(p));
        p.healt = 0;
        assertTrue(isDead(p));
        p.healt = -1;
        assertTrue(isDead(p));
    }

    boolean isDead(Monster m) {
        /* Check if the monster is dead */
        if(m.healt <= 0) {
            return true;
        } else {
            return false;
        }
    }

    void testIsDeadM() {
        /* Test of the function isDead */
        Monster m = newMonster(TypeMonster.ZOMBIE, 5, 50);
        assertFalse(isDead(m));
        m.healt = 0;
        assertTrue(isDead(m));
        m.healt = -1;
        assertTrue(isDead(m));
    }

    String getHealt(Player p) {
        /* Return the healt of the player */
        String s = "";
        for(int i = 0; i < p.healt; i++) {
            s += "❤️"; // Not working on the Windows console
        }
        return s;
    }

    void testGetHealtP() {
        /* Test of the function getHealt */
        Player p = newPlayer("Bob");
        p.healt = 5;
        assertEquals("❤️❤️❤️❤️❤️", getHealt(p));
        p.healt = 0;
        assertEquals("", getHealt(p));
        p.healt = -1;
        assertEquals("", getHealt(p));
    }

    String getHealt(Monster m) {
        /* Return the healt of the monster */
        String s = "";
        for(int i = 0; i < m.healt; i++) {
            s += "💔"; // Not working on the Windows console
        }
        return s;
    }

    void testGetHealtM() {
        /* Test of the function getHealt */
        Monster m = newMonster(TypeMonster.ZOMBIE, 5, 50);
        m.healt = 5;
        assertEquals("💔💔💔💔💔", getHealt(m));
        m.healt = 0;
        assertEquals("", getHealt(m));
        m.healt = -1;
        assertEquals("", getHealt(m));
    }

    /* Maps */

    Cellule newCellulePlayer(Player p) {
        /* Create a new cellule with a player */
        Cellule c = new Cellule();
        c.player = p;
        return c;
    }

    void testNewCellulePlayer() {
        /* Test of the function newCellulePlayer */
        Player p = newPlayer("Bob");
        Cellule c = newCellulePlayer(p);
        assertEquals(p, c.player);
        assertFalse(c.isExit);
        assertFalse(c.isWall);
    }

    Cellule newCelluleMonster(Monster m) {
        /* Create a new cellule with a monster */
        Cellule c = new Cellule();
        c.monster = m;
        return c;
    }

    void testNewCelluleMonster() {
        /* Test of the function newCelluleMonster */
        Monster m = newMonster(TypeMonster.ZOMBIE, 5, 50);
        Cellule c = newCelluleMonster(m);
        assertEquals(m, c.monster);
        assertFalse(c.isExit);
        assertFalse(c.isWall);
    }

    Cellule newCelluleLoot(Loot l) {
        /* Create a new cellule with a loot */
        Cellule c = new Cellule();
        c.loot = l;
        return c;
    }

    void testNewCelluleLoot() {
        /* Test of the function newCelluleLoot */
        Loot l = newLoot(TypeLoot.POTION, 5);
        Cellule c = newCelluleLoot(l);
        assertEquals(l, c.loot);
        assertFalse(c.isExit);
        assertFalse(c.isWall);
    }

    Cellule newCelluleEmpty() {
        /* Create a new empty cellule */
        Cellule c = new Cellule();
        return c;
    }

    void testNewCelluleEmpty() {
        /* Test of the function newCelluleEmpty */
        Cellule c = newCelluleEmpty();
        assertFalse(c.isExit);
        assertFalse(c.isWall);
    }

    Cellule newCellulePorte() {
        /* Create a new cellule with a door */
        Cellule c = newCelluleEmpty();
        c.isExit = true;
        return c;
    }

    void testNewCellulePorte() {
        Cellule c = newCellulePorte();
        assertTrue(c.isExit);
        assertFalse(c.isWall);
    }

    Cellule newCelluleMur() {
        /* Create a new cellule with a wall */
        Cellule c = newCelluleEmpty();
        c.isWall = true;
        return c;
    }

    void testNewCelluleMur() {
        Cellule c = newCelluleMur();
        assertFalse(c.isExit);
        assertTrue(c.isWall);
    }

    Map newMap(int width, int height) {
        /* Create a new map */
        Map m = new Map();
        m.carte = new Cellule[width][height];
        m.colonnePlayer = 0;
        m.lignePlayer = 0;
        return m;
    }

    void testNewMap() {
        /* Test of the function newMap */
        Map m = newMap(10, 10);
        assertEquals(10, length(m.carte));
        assertEquals(10, length(m.carte[0]));
    }

    Loot newLootRandom() {
        /* Create a new loot in a cellule */
        double r = random();
        if(r < POTION_SPAWN_PROBA) {
            int heal = (int) (1 + random() * 10);
            return newLoot(TypeLoot.POTION, heal);
        } else if(r < POTION_SPAWN_PROBA + RING_SPAWN_PROBA) {
            int attack = (int) (1 + random() * 10);
            return newLoot(TypeLoot.RING, attack);
        } else if(r < POTION_SPAWN_PROBA + RING_SPAWN_PROBA + ARMOR_SPAWN_PROBA) {
            int defense = (int) (1 + random() * 10);
            return newLoot(TypeLoot.ARMOR, defense);
        } else {
            return null;
        }
    }

    void testNewLootRandom() {
        /* Test of the function newLootRandom */
        Loot l = newLootRandom();
        if(l != null) {
            if(l.type == TypeLoot.POTION) {
                assertTrue(l.amount >= 1 && l.amount <= 10);
            } else if(l.type == TypeLoot.RING) {
                assertTrue(l.amount >= 1 && l.amount <= 10);
            } else if(l.type == TypeLoot.ARMOR) {
                assertTrue(l.amount >= 1 && l.amount <= 10);
            }
        }
    }

    Monster newMonsterRandom() {
        /* Create a new monster in a cellule */
        double r = random();
        if(r < ZOMBIE_SPAWN_PROBA) {
            return newMonster(TypeMonster.ZOMBIE, ZOMBIE_ATTACK, ZOMBIE_HEALT);
        } else if(r < ZOMBIE_SPAWN_PROBA + SKELETON_SPAWN_PROBA) {
            return newMonster(TypeMonster.SKELETON, SKELETON_ATTACK, SKELETON_HEALT);
        } else if(r < ZOMBIE_SPAWN_PROBA + SKELETON_SPAWN_PROBA + VAMPIRE_SPAWN_PROBA) {
            return newMonster(TypeMonster.VAMPIRE, VAMPIRE_ATTACK, VAMPIRE_HEALT);
        } else {
            return null;
        }
    }

    void testNewMonsterRandom() {
        /* Test of the function newCelluleMonsterRandom */
        int nbZombie = 0;
        int nbSkeleton = 0;
        int nbVampire = 0;
        for(int i = 0; i < 1000; i++) { // Théorie des grands nombres
            Monster m = newMonsterRandom();
            if(m != null) {
                if(m.type == TypeMonster.ZOMBIE) {
                    nbZombie++;
                } else if(m.type == TypeMonster.SKELETON) {
                    nbSkeleton++;
                } else if(m.type == TypeMonster.VAMPIRE) {
                    nbVampire++;
                }
            }
        }
        assertTrue(nbZombie > 0);
        assertTrue(nbSkeleton > 0);
        assertTrue(nbVampire > 0);
    }

    Map loadMap(String filename) {
        extensions.CSVFile file = loadCSV("./maps/" + filename);
        int ligne = rowCount(file);
        int colonne = columnCount(file);
        Map map = newMap(colonne, ligne);
        for(int i = 0; i < ligne; i++) {
            for(int j = 0; j < colonne; j++) {
                String s = getCell(file, i, j);
                if(equals(s, "P")) { // Player case
                    map.carte[i][j] = newCellulePlayer(newPlayer("Not defined"));
                    map.lignePlayer = i;
                    map.colonnePlayer = j;
                    DIMENSION = charAt(filename, 3) - '0';
                    println("DIMENSION = " + DIMENSION);
                    println("map.lignePlayer = " + map.lignePlayer);
                    println("map.colonnePlayer = " + map.colonnePlayer);
                } else if(equals(s, "M")) { // Monster case
                    map.carte[i][j] = newCelluleMonster(newMonsterRandom());
                } else if(equals(s, "L")) { // Loot case
                    map.carte[i][j] = newCelluleLoot(newLootRandom());
                } else if(equals(s, "E")) { // Exit case
                    map.carte[i][j] = newCellulePorte();
                } else if(equals(s, "W")) { // Wall case
                    map.carte[i][j] = newCelluleMur();
                } else if(equals(s, "#")) { // Empty case
                    map.carte[i][j] = newCelluleEmpty();
                }
            }
        }
        return map;
    }

    void testLoadMap() {
        /* Map format:
            WWWWW
            W#P#W
            W#M#W
            W#L#W
            WWEWW
        */
        Map m = loadMap("debuggingMap.csv");
        assertTrue(m.carte[0][0].isWall);
        assertTrue(m.carte[0][1].isWall);
        assertTrue(m.carte[0][2].isWall);
        assertTrue(m.carte[0][3].isWall);
        assertTrue(m.carte[0][4].isWall);
        assertTrue(m.carte[1][0].isWall);
        assertEquals(m.carte[1][2].player.nom, "Not defined");
        assertTrue(m.carte[1][4].isWall);
        assertTrue(m.carte[2][0].isWall);
        assertTrue(m.carte[2][2].monster.type == TypeMonster.ZOMBIE || m.carte[2][2].monster.type == TypeMonster.SKELETON || m.carte[2][2].monster.type == TypeMonster.VAMPIRE);
        assertTrue(m.carte[2][4].isWall);
        assertTrue(m.carte[3][0].isWall);
        assertTrue(m.carte[3][2].loot.type == TypeLoot.POTION || m.carte[3][2].loot.type == TypeLoot.RING || m.carte[3][2].loot.type == TypeLoot.ARMOR);
        assertTrue(m.carte[3][4].isWall);
        assertTrue(m.carte[4][0].isWall);
        assertTrue(m.carte[4][1].isWall);
        assertTrue(m.carte[4][2].isExit);
        assertTrue(m.carte[4][3].isWall);
        assertTrue(m.carte[4][4].isWall);
    }

    void afficherMap(Map map) {
        /* Display the map */
        clearScreen();
        println();
        for(int i = 0; i < length(map.carte); i++) {
            for(int j = 0; j < length(map.carte[0]); j++) {
                if(map.carte[i][j].isWall) {
                    print(ANSI_BOLD + ANSI_WHITE + "■" + ANSI_RESET);
                } else if(map.carte[i][j].isExit) {
                    if(map.carte[i][j].canExit) {
                        print(ANSI_BOLD + ANSI_GREEN + "▩" + ANSI_RESET);
                    } else {
                        print(ANSI_BOLD + ANSI_RED + "▩" + ANSI_RESET);
                    }
                } else if(map.carte[i][j].player != null) {
                    print(ANSI_BOLD + ANSI_BLUE + "◎" + ANSI_RESET);
                } else if(map.carte[i][j].monster != null) {
                    print(ANSI_BOLD + ANSI_RED + "☠" + ANSI_RESET);
                } else if(map.carte[i][j].loot != null) {
                    print(ANSI_BOLD + ANSI_YELLOW + "♦" + ANSI_RESET);
                } else {
                    print(" ");
                }
                print(" ");
            }
            println();
        }
    }

    /* End */

    boolean canGoTo(Map map, int ligne, int colonne) {
        /* Check if the player can go to the cell (ligne, colonne) */
        if(ligne < 0 || colonne < 0 || ligne >= length(map.carte) || colonne >= length(map.carte[0])) {
            return false;
        }
        if(map.carte[ligne][colonne].isExit) {
            return map.carte[ligne][colonne].canExit;
        }
        return !map.carte[ligne][colonne].isWall;
    }

    void testCanGoTo() {
        Map m = loadMap("debuggingMap.csv");
        assertTrue(canGoTo(m, 1, 1));
        assertTrue(canGoTo(m, 1, 3));
        assertTrue(canGoTo(m, 2, 1));
        assertTrue(canGoTo(m, 2, 3));
        assertTrue(canGoTo(m, 3, 1));
        assertTrue(canGoTo(m, 3, 3));
        assertFalse(canGoTo(m, 0, 0));
        assertFalse(canGoTo(m, 0, 1));
        assertFalse(canGoTo(m, 0, 2));
        assertFalse(canGoTo(m, 0, 3));
        assertFalse(canGoTo(m, 0, 4));
        assertFalse(canGoTo(m, 1, 0));
        assertFalse(canGoTo(m, 1, 4));
        assertFalse(canGoTo(m, 2, 0));
        assertFalse(canGoTo(m, 2, 4));
        assertFalse(canGoTo(m, 3, 0));
        assertFalse(canGoTo(m, 3, 4));
        assertFalse(canGoTo(m, 4, 0));
        assertFalse(canGoTo(m, 4, 1));
        assertFalse(canGoTo(m, 4, 2));
        assertFalse(canGoTo(m, 4, 3));
        assertFalse(canGoTo(m, 4, 4));
    }

    boolean isDirection(String direction) {
        /* Check if the direction is valid */
        return equals(direction, "z") || equals(direction, "s") || equals(direction, "q") || equals(direction, "d");
    }

    void testIsDirection() {
        assertTrue(isDirection("z"));
        assertTrue(isDirection("s"));
        assertTrue(isDirection("q"));
        assertTrue(isDirection("d"));
        assertFalse(isDirection("a"));
        assertFalse(isDirection("e"));
        assertFalse(isDirection("r"));
        assertFalse(isDirection("f"));
        assertFalse(isDirection("w"));
        assertFalse(isDirection("x"));
        assertFalse(isDirection("c"));
        assertFalse(isDirection("v"));
        assertFalse(isDirection("b"));
        assertFalse(isDirection("n"));
        assertFalse(isDirection("m"));
        assertFalse(isDirection("p"));
        assertFalse(isDirection("o"));
        assertFalse(isDirection("l"));
        assertFalse(isDirection("k"));
        assertFalse(isDirection("j"));
        assertFalse(isDirection("i"));
        assertFalse(isDirection("h"));
        assertFalse(isDirection("g"));
        assertFalse(isDirection("u"));
        assertFalse(isDirection("y"));
        assertFalse(isDirection("t"));
        assertFalse(isDirection("1"));
        assertFalse(isDirection("2"));
        assertFalse(isDirection("3"));
        assertFalse(isDirection("4"));
        assertFalse(isDirection("5"));
        assertFalse(isDirection("6"));
        assertFalse(isDirection("7"));
        assertFalse(isDirection("8"));
        assertFalse(isDirection("9"));
        assertFalse(isDirection("0"));
        assertFalse(isDirection(" "));
        assertFalse(isDirection("azerty"));
        assertFalse(isDirection("qsdfgh"));
        assertFalse(isDirection("wxcvbn"));
        assertFalse(isDirection("azertyuiop"));
        assertFalse(isDirection("qsdfghjklm"));
        assertFalse(isDirection("wxcvbn,;:"));
        assertFalse(isDirection("azertyuiopqsdfghjklmwxcvbn,;:"));
        assertFalse(isDirection("qsdfghjklmazertyuiopwxcvbn,;:"));
        assertFalse(isDirection("wxcvbn,;:azertyuiopqsdfghjklm"));
        assertFalse(isDirection("azertyuiopqsdfghjklmwxcvbn,;:qsdfghjklmazertyuiopwxcvbn,;:"));
        assertFalse(isDirection("qsdfghjklmazertyuiopwxcvbn,;:wxcvbn,;:azertyuiopqsdfghjklm"));
        assertFalse(isDirection("wxcvbn,;:azertyuiopqsdfghjklmazertyuiopqsdfghjklmw\n"));
    }

    int[] getDirection(String direction, int ligne, int colonne) {
        /* Return the direction in the form of an array */
        int[] directionTo = new int[2];
        if(equals(direction, "z")) {
            directionTo[0] = ligne - 1;
            directionTo[1] = colonne;
        } else if(equals(direction, "s")) {
            directionTo[0] = ligne + 1;
            directionTo[1] = colonne;
        } else if(equals(direction, "q")) {
            directionTo[0] = ligne;
            directionTo[1] = colonne - 1;
        } else if(equals(direction, "d")) {
            directionTo[0] = ligne;
            directionTo[1] = colonne + 1;
        }
        return directionTo;
    }

    void testGetDirection() {
        int[] direction = getDirection("z", 1, 1);
        assertTrue(direction[0] == 0);
        assertTrue(direction[1] == 1);
        direction = getDirection("s", 1, 1);
        assertTrue(direction[0] == 2);
        assertTrue(direction[1] == 1);
        direction = getDirection("q", 1, 1);
        assertTrue(direction[0] == 1);
        assertTrue(direction[1] == 0);
        direction = getDirection("d", 1, 1);
        assertTrue(direction[0] == 1);
        assertTrue(direction[1] == 2);
    }

    boolean movePlayer(Map map, String direction) {
        /* Move the player to the direction */
        int ligne = map.lignePlayer;
        int colonne = map.colonnePlayer;
        ligne = getDirection(direction, ligne, colonne)[0];
        colonne = getDirection(direction, ligne, colonne)[1];
        if(canGoTo(map, ligne, colonne)) {
            Player player = map.carte[map.lignePlayer][map.colonnePlayer].player;
            map.carte[map.lignePlayer][map.colonnePlayer].player = null;
            map.carte[ligne][colonne].player = player;
            map.lignePlayer = ligne;
            map.colonnePlayer = colonne;
            return true;
        }
        return false;
    }

    boolean playerGoToDoor(Map map, String direction) {
        /* Check if the player can go to the door */
        int ligne = map.lignePlayer;
        int colonne = map.colonnePlayer;
        ligne = getDirection(direction, ligne, colonne)[0];
        colonne = getDirection(direction, ligne, colonne)[1];
        return map.carte[ligne][colonne].isExit;
    }

    boolean playerGoToMonster(Map map, String direction) {
        /* Check if the player can go to the monster */
        int ligne = map.lignePlayer;
        int colonne = map.colonnePlayer;
        ligne = getDirection(direction, ligne, colonne)[0];
        colonne = getDirection(direction, ligne, colonne)[1];
        return map.carte[ligne][colonne].monster != null;
    }

    boolean playerGoToLoot(Map map, String direction) {
        /* Check if the player can go to the loot */
        int ligne = map.lignePlayer;
        int colonne = map.colonnePlayer;
        ligne = getDirection(direction, ligne, colonne)[0];
        colonne = getDirection(direction, ligne, colonne)[1];
        return map.carte[ligne][colonne].loot != null;
    }

    void welcomeMessage() {
        print("Bienvenue dans");
        for(int i = 0; i < 5; i++) {
            print(".");
            delay(500);
        }
        println(ANSI_BOLD + "IthyphalGame !" + ANSI_RESET);

        println("Vous êtes piéger dans le donjon d'Ithyphal, vous devez trouver la sortie pour vous en échapper.");
        delay(2000);
        println("Mais faites attention, vous ne serez pas seul dans ce donjon ! Des monstres sont la pour vous empêcher de sortir.");
        delay(2000);
        println("Vous pouvez vous déplacer avec les touches Z, Q, S et D. Et la touche H pour afficher l'aide.");
        delay(2000);
        println("Bonne chance jeune aventurier !");
        print("Appuyez sur entrer pour continuer...");
        readString();
    }

    int menuPrincipal() {
        clearScreen();
        for(int i = 0; i < length("IthyphalGame"); i++) {
            text(randomANSIColor());
            print(charAt("IthyphalGame", i));
        }
        println(ANSI_RESET + "\n");


        println("1. Nouvelle partie");
        println("2. Charger une partie");
        println("3. Quitter");
        print("Votre choix : ");
        return readInt();
    }

    Map[][][] generateMap() {
        Map[][][] carte = new Map[5][5][5];
        for(int dim = 0; dim < 5; dim++) {
            for(int i = 0; i < 5; i++) {
                for(int j = 0; j < 5; j++) {
                    carte[dim][i][j] = loadMap("map" + dim + i + j + ".csv");
            }
        }
        }
        return carte;
    }

    Map[][][] loadFromSave() {
        //TODO : Load from save
        Map[][][] carte = new Map[5][5][5];

        //!Code here

        return carte;
    }


    String attack(Player p, Monster m) {
        // Return: "player" if the player lost, "monster" if the monster lost.
        while(p.healt > 0 && m.healt > 0) {
            println("Vous avez " + getHealt(p) + "  points de vie.");
            println("Le monstre a " + getHealt(m) + " points de vie.\n\n");

            println("Quelles attaques voulez-vous faire ?");
            println("1. IJava (Attaque basique qui inflige " + p.attack + " dégats)");
            println("2. Java (Attaque spécial qui inflige " + (int) (p.attack * 1.5) + " dégats)");
            println("3. Python (Attaque spécial qui inflige " + p.attack * 2 + " dégats)");

            print("Votre choix : ");
            int choix;
            do {
                choix = readInt();
            } while(choix < 1 || choix > 3);

            if(choix == 1) {
                m.healt -= p.attack;
            } else if(choix == 2) {
                if(askQuestion(1)) {
                    m.healt -= (int) (p.attack * 1.5);
                }
            } else if(choix == 3) {
                if(askQuestion(3)) {
                    m.healt -= p.attack * 2;
                }
            }

            // Monster attack
            if(m.healt > 0) {
                if(random() < 0.85) {
                    p.healt -= m.attack;
                    println("Le monstre vous a infligé " + m.attack + " dégats !");
                } else {
                    println("Le monstre a raté son attaque !");
                }
            }
            delay(1000);
        }
        if(p.healt <= 0) {
            return "monster";
        } else {
            return "player";
        }
    }

    // Ask function

    String[][] loadQuestionFile(String filename) {
        CSVFile file = loadCSV("./questions/" + filename);
        String[][] questions = new String[rowCount(file)][columnCount(file)];
        for(int ligne = 1; ligne < length(questions); ligne++) {
            for(int colonne = 0; colonne < length(questions[0]); colonne++) {
                questions[ligne][colonne] = getCell(file, ligne, colonne);
            }
        }
        return questions;
    }

    Question newQuestion(String question, String bonne_rep, String[] bad_responses) {
        Question q = new Question();
        q.question = question;
        q.bonne_rep = bonne_rep;
        q.bad_responses = bad_responses;
        return q;
    }

    void testNewQuestion() {
        String[] bad_responses = {"Réponse 1", "Réponse 2", "Réponse 3"};
        Question q = newQuestion("Question 1", "Réponse 4", bad_responses);
        assertArrayEquals(q.bad_responses, bad_responses);
        assertEquals(q.question, "Question 1");
        assertEquals(q.bonne_rep, "Réponse 4");
    }

    Question[] loadAllQuestions(String[][] tabQuestions) {
        Question[] questions = new Question[length(tabQuestions)];
        for(int i = 1; i < length(tabQuestions); i++) {
            String[] bad_responses = new String[3];
            for(int j = 0; j < 3; j++) {
                bad_responses[j] = tabQuestions[i][j + 2];
            }
            questions[i] = newQuestion(tabQuestions[i][0], tabQuestions[i][1], bad_responses);
        }
        return questions;
    }

    Question getRandomQuestion() {
        // QUESTIONS
        int rdm;
        boolean find = false;
        while(!find) {
            rdm = (int) (1 + random() * (length(QUESTIONS) - 1));
            println(rdm);
            if(!QUESTIONS[rdm].dejaPosee) {
                //?QUESTIONS[rdm].dejaPosee = true;
                return QUESTIONS[rdm];
            }
        }
        return null;
    }

    void randomiseTab(String[] tab) {
        for(int i = 0; i < length(tab); i++) {
            int random = (int) (random() * length(tab));
            String tmp = tab[i];
            tab[i] = tab[random];
            tab[random] = tmp;
        }
    }

    boolean askQuestion(int nbrToAsk) {
        for(int i = 0; i < nbrToAsk; i++) {
            Question q = getRandomQuestion();
            String[] tab = new String[4];
            tab[0] = q.bonne_rep;
            tab[1] = q.bad_responses[0];
            tab[2] = q.bad_responses[1];
            tab[3] = q.bad_responses[2];
            randomiseTab(tab);
            println(ANSI_BOLD + "Question: " + q.question + ANSI_RESET);
            for(int j = 0; j < length(tab); j++) {
                println("->     " + (j + 1) + ". " + tab[j]);
            }
            println("Vous avez 60 secondes pour répondre !");
            print("Votre choix : ");
            int choix;
            do {
                choix = readInt();
            } while(choix < 1 || choix > 4);
            if(equals(tab[choix - 1], q.bonne_rep)) {
                println(ANSI_GREEN + ANSI_BOLD + "Bonne réponse !" + ANSI_RESET);
            } else {
                println(ANSI_RED + ANSI_BOLD + "Mauvaise réponse !" + ANSI_RESET);
                return false;
            }
        }
        return true;
    }

    int[] newPlayerPos(String direction, Map[][][] map, int ligne, int colonne, int etage, int newLigne, int newColonne, int newEtage, int player_x, int player_y) {
        /* This function modify the player position when i travel a door */
        Player p = map[etage][ligne][colonne].carte[player_x][player_y].player;
        Cellule[][] carte = map[newEtage][newLigne][newColonne].carte;
        int[] newPos = new int[2];
        switch (direction) {
            default:
                newPos[0] = player_x;
                newPos[1] = player_y;
                break;
            case "z":
                map[etage][ligne][colonne].carte[length(carte) - 2][player_y].player = null;
                map[newEtage][newLigne][newColonne].carte[length(carte) - 2][player_y].player = p;
                newPos[0] = length(carte) - 2;
                newPos[1] = player_y;
                break;
            case "q":
                map[etage][ligne][colonne].carte[player_x][player_y].player = null;
                map[newEtage][newLigne][newColonne].carte[player_x][length(carte[0]) - 2].player = p;
                newPos[0] = player_x;
                newPos[1] = length(carte[0]) - 2;
                break;
            case "s":
                map[etage][ligne][colonne].carte[1][player_y].player = null;
                map[newEtage][newLigne][newColonne].carte[1][player_y].player = p;
                newPos[0] = 1;
                newPos[1] = player_y;
                break;
            case "d":
                map[etage][ligne][colonne].carte[player_x][1].player = null;
                map[newEtage][newLigne][newColonne].carte[player_x][1].player = p;
                newPos[0] = player_x;
                newPos[1] = 1;
                break;
        }
        return newPos;
    }

    boolean monsterInCarte(Cellule[][] carte) {
        for(int i = 0; i < length(carte); i++) {
            for(int j = 0; j < length(carte[0]); j++) {
                if(carte[i][j].monster != null) {
                    return true;
                }
            }
        }
        return false;
    }

    void updatePorte(Map[][][] map, int ligne, int colonne, int etage) {
        Cellule[][] carte = map[etage][ligne][colonne].carte;
        if(!monsterInCarte(carte)) {
            for(int i = 0; i < length(carte); i++) {
                for(int j = 0; j < length(carte[0]); j++) {
                    if(carte[i][j].isExit) {
                        carte[i][j].canExit = true;
                    }
                }
            }
        } 
    }

    void helpCommand() {
        println("Vous êtes sur la page d'aide du jeu");
        println("Voici la liste des touches disponibles dans le jeu: ");
        println("    - Z : Déplacement vers le haut");
        println("    - Q : Déplacement vers la gauche");
        println("    - S : Déplacement vers le bas");
        println("    - D : Déplacement vers la droite");
        println("    - H : Afficher l'aide");
        println("    - I : Afficher les informations du joueur");
        println("    - X : Quitter le jeu");
        println("Nous vous rappellons que pour passer à une autre pièce du donjon, il faut canExitavoir battu tous les monstres présents dans la pièce actuelle.");
        println("Bonne chance !");
        print("Appuyez sur une entrée pour revenir au jeu");
        readString();
    }

    // Algorithm principal

    void algorithm() {
        // Main function
        int ligne = 0;
        int colonne = 0;
        boolean fini = false;
        welcomeMessage();
        int choix = menuPrincipal();
        Map[][][] carte = new Map[5][5][5];
        if(choix == 1) {
            carte = generateMap();
        } else if(choix == 2) { // Load game
            carte = loadFromSave();
        } else if(choix == 3) { // Quit game
            println("Merci d'avoir joué !");
            delay(1000);
        } else { // Error
            println("Erreur : Veuillez entrer un nombre entre 1 et 3");
            delay(1000);
            algorithm();
        }

        // Load game
        print("Chargement de la carte");
        for(int i = 0; i < 5; i++) {
            print(".");
            delay(500);
        }
        
        int player_x = carte[DIMENSION][ligne][colonne].colonnePlayer;
        int player_y = carte[DIMENSION][ligne][colonne].lignePlayer;
        boolean changementPiece = false;
        // Start game
        while(!fini && carte[DIMENSION][ligne][colonne].carte[player_x][player_y].player.healt > 0) {
            clearScreen();
            afficherMap(carte[DIMENSION][ligne][colonne]);
            print("Votre choix : ");
            String direction = toLowercase(readString());
            if(equals(direction, "z") || equals(direction, "s") || equals(direction, "q") || equals(direction, "d")) {
                int[] coordonnees_prochaine = getDirection(direction, player_x, player_y);
                if(playerGoToMonster(carte[DIMENSION][ligne][colonne], direction)) { 
                    Monster m = carte[DIMENSION][ligne][colonne].carte[coordonnees_prochaine[0]][coordonnees_prochaine[1]].monster;
                    if(m != null) {
                        println("Vous avez attaqué par un " + m.type + " !");
                        delay(1000);
                        String winner = attack(carte[DIMENSION][ligne][colonne].carte[player_x][player_y].player, m);
                        //? Optimisable
                        if(equals(winner, "player")) {
                            println("Vous avez gagné !");
                            delay(1000);
                            carte[DIMENSION][ligne][colonne].carte[coordonnees_prochaine[0]][coordonnees_prochaine[1]].monster = null;
                            updatePorte(carte, ligne, colonne, DIMENSION);
                        } else {
                            println("Vous avez perdu !");
                            delay(1000);
                            carte[DIMENSION][ligne][colonne].carte[player_x][player_y].player.healt = 0;
                        }
                    } else {
                        println("Erreur : Vous avez attaqué un monstre qui n'existe pas !");
                        println("Coordonnées : " + player_x + " " + player_y + "");
                        delay(1000);
                    }

                } else if(playerGoToLoot(carte[DIMENSION][ligne][colonne], direction)) {
                    
                    Loot l = carte[DIMENSION][ligne][colonne].carte[coordonnees_prochaine[0]][coordonnees_prochaine[1]].loot;
                    if(l == null) {
                        println("Erreur : Vous avez trouvé un loot qui n'existe pas !");
                        println("Coordonnées : " + player_x + " " + player_y + "");
                        delay(5000);
                    }

                    TypeLoot type = l.type;
                    int amount = l.amount;

                    String choix_loot;
                    do {
                        println("Vous avez trouvé un loot !");
                        println("->     1. Prendre le loot");
                        println("->     2. Ne pas prendre le loot");
                        print("Votre choix : ");
                        choix_loot = readString();
                    } while(!equals(choix_loot, "1") && !equals(choix_loot, "2"));

                    if(equals(choix_loot, "1")) {
                        if(askQuestion(1)) {
                            println("Vous avez pris le loot !");
                            delay(1000);
                            if(type == TypeLoot.POTION) {
                                carte[DIMENSION][ligne][colonne].carte[player_x][player_y].player.healt += amount;
                                println("Vous avez gagné " + amount + " points de vie !");
                                delay(1000);
                            } else if(type == TypeLoot.RING) {
                                carte[DIMENSION][ligne][colonne].carte[player_x][player_y].player.attack += amount;
                                println("Vous avez gagné " + amount + " points d'attaque !");
                                delay(1000);
                            } else if(type == TypeLoot.ARMOR) {
                                carte[DIMENSION][ligne][colonne].carte[player_x][player_y].player.shield += amount;
                                println("Vous avez gagné " + amount + " points de défense !");
                                delay(1000);
                            }
                        } else {
                            println("Vous avez perdu le loot !");
                            carte[DIMENSION][ligne][colonne].carte[coordonnees_prochaine[0]][coordonnees_prochaine[1]].loot = null;
                            delay(1000);
                            println("Un monstre vous a attaqué !");
                            delay(1000);
                            Monster m = newMonsterRandom();
                            String winner = attack(carte[DIMENSION][ligne][colonne].carte[player_x][player_y].player, m);
                            //? Optimisable
                            if(equals(winner, "player")) {
                                updatePorte(carte, ligne, colonne, DIMENSION);
                                println("Vous avez gagné !");
                                delay(1000);
                            } else {
                                println("Vous avez perdu !");
                                delay(1000);
                                carte[DIMENSION][ligne][colonne].carte[player_x][player_y].player.healt = 0;
                            }
                        }
                    } else {
                        println("Vous avez décidé de ne pas prendre le loot ! Il a disparu !");
                        delay(1000);
                    }
                    carte[DIMENSION][ligne][colonne].carte[coordonnees_prochaine[0]][coordonnees_prochaine[1]].loot = null;
                } else if(playerGoToDoor(carte[DIMENSION][ligne][colonne], direction)) {
                    if(carte[DIMENSION][ligne][colonne].carte[coordonnees_prochaine[0]][coordonnees_prochaine[1]].canExit) {
                        //! Faire une fonction pour ça parce que la on comprend plus rien !
                        print("Vous sortez de cette salle !");
                        delay(1000);
                        int[] nouvelleSalleCoord = getDirection(direction, ligne, colonne);
                        int[] newPos = newPlayerPos(direction, carte, ligne, colonne, DIMENSION, nouvelleSalleCoord[0], nouvelleSalleCoord[1], DIMENSION, player_x, player_y); //!AJOUTER LES ARGUMENTS !!!
                        carte[DIMENSION][ligne][colonne].carte[player_x][player_y].player = null;
                        player_x = newPos[0];
                        player_y = newPos[1];
                        ligne = nouvelleSalleCoord[0];
                        colonne = nouvelleSalleCoord[1];
                        carte[DIMENSION][ligne][colonne].lignePlayer = player_x;
                        carte[DIMENSION][ligne][colonne].colonnePlayer = player_y;
                        changementPiece = true;
                        updatePorte(carte, ligne, colonne, DIMENSION);
                    }
                } else {
                    println("Vous avez avancé !");
                    delay(100);
                }

                if(!changementPiece && movePlayer(carte[DIMENSION][ligne][colonne], direction)) {
                    player_x = carte[DIMENSION][ligne][colonne].lignePlayer;
                    player_y = carte[DIMENSION][ligne][colonne].colonnePlayer;
                } else if(!changementPiece) {
                    println("Erreur : Vous ne pouvez pas aller dans cette direction !");
                    delay(1000);
                } else { //! Si le joueur a changé de pièce
                    changementPiece = false;
                }
            } else if(equals(direction, "h")) {
                helpCommand();
            } else if(equals(direction, "x")) {
                println("Vous avez quitté le jeu !");
                delay(1000);
                //TODO : Save game
            } else {
                println("Erreur : Veuillez entrer une direction valide");
                delay(1000);
            }
        }
    }

    //! Problème trouver
    //? - Lors de la téléportation à une autre salle, le joueur est encore en mémoire dans l'ancienne salle // FAIT

    //! Prochaine chose à faire
    //? - Faire une fonction qui permet de sauvegarder la partie
    //? - Faire une fonction qui permet de charger une partie
    //? - Faire en sorte que le jeu détecte quand le joueur va sur une porte et le fait sortir si la porte est ouverte // FAIT
    //? - Faire en sorte que quand le joueur va sur une porte ouverte, il soit téléporté sur une autre carte // FAIT
    //? - Faire en sorte que le jeu détecte le nombre de monstres et si il n'y en a plus, il ouvre la ou les porte(s)
    //TODO Plus si j'ai le temps :)
}