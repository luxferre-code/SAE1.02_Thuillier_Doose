import extensions.*;

class IthyphalGame extends Program {

    // LOGGER
    final boolean LOGGER = false;

    // DELAY

    final int DELAY = 1000;

    // MONSTER VARIABLES
    final double ZOMBIE_SPAWN_PROBA = 0.5;
    final int ZOMBIE_ATTACK = 2;
    final int ZOMBIE_HEALT = 20;

    final double SKELETON_SPAWN_PROBA = 0.3;
    final int SKELETON_ATTACK = 4;
    final int SKELETON_HEALT = 15;

    final double VAMPIRE_SPAWN_PROBA = 0.2;
    final int VAMPIRE_ATTACK = 5;
    final int VAMPIRE_HEALT = 10;

    // LOOT VARIABLES
    final double RING_SPAWN_PROBA = 0.33;
    final int MAX_LOOT_ATTACK = 2;
    final double POTION_SPAWN_PROBA = 0.33;
    final int MAX_LOOT_HEALTH = 3;
    final double ARMOR_SPAWN_PROBA = 0.33;
    final int MAX_LOOT_DEFENSE = 3;

    // PLAYER VARIABLES
    final int MAX_PLAYER_ATTACK = 15;
    final int MAX_PLAYER_HEALTH = 30;
    final int MAX_PLAYER_DEFENSE = 10;

    // QUESTION

    Question[] QUESTIONS = loadAllQuestions(loadQuestionFile("init_question.csv"));

    // ENVIROMENT VARIABLES
    
    String[] AllPosMonsterKillTab = new String[0];
    String[] AllPosLootTakeTab = new String[0];

    void logger(String s) {
        if(LOGGER) {
            print(s);
            delay(DELAY);
        }
    }


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
        assertEquals(4, p.attack);
        assertEquals(30, p.healt);
        assertEquals(5, p.shield);
    }

    String toString(Player p) {
        /* Return the string of the player */
        return "Player: " + p.nom + " attack: " + p.attack + " healt: " + p.healt + " shield: " + p.shield;
    }

    void testToString() {
        /* Test of the function toString */
        Player p = newPlayer("Bob");
        assertEquals("Player: Bob attack: 4 healt: 30 shield: 5", toString(p));
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

    String toString(Loot l) {
        /* Return the string of the loot */
        return "Loot: " + l.type + " amount: " + l.amount;
    }

    void testToStringLoot() {
        /* Test of the function toString */
        Loot l = newLoot(TypeLoot.RING, 5);
        assertEquals("Loot: RING amount: 5", toString(l));
    }

    void addLoot(Player p, Loot l) {
        /* Add the loot to the player */
        if(l.type == TypeLoot.RING && p.attack <= MAX_PLAYER_ATTACK) {
            p.attack += l.amount;
        } else if(l.type == TypeLoot.POTION && p.healt <= MAX_PLAYER_HEALTH) {
            p.healt += l.amount;
        } else if(l.type == TypeLoot.ARMOR && p.shield <= MAX_PLAYER_DEFENSE) {
            p.shield += l.amount;
        }
    }

    void testAddLoot() {
        /* Test of the function addLoot */
        Player p = newPlayer("Bob");
        assertEquals(4, p.attack);
        Loot l = newLoot(TypeLoot.RING, 5);
        addLoot(p, l);
        assertEquals(9, p.attack);
        assertEquals(30, p.healt);
        assertEquals(5, p.shield);
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

    String toString(Monster m) {
        /* Return the string of the monster */
        return "Monster: " + m.type + " attack: " + m.attack + " healt: " + m.healt;
    }

    void testToStringMonster() {
        /* Test of the function toString */
        Monster m = newMonster(TypeMonster.ZOMBIE, 5, 50);
        assertEquals("Monster: ZOMBIE attack: 5 healt: 50", toString(m));
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
        p.shield = 0;
        Monster m = newMonster(TypeMonster.ZOMBIE, 5, 50);
        int healt = p.healt;
        monsterAttack(m, p);
        assertEquals(healt - m.attack, p.healt);
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

    String getShield(Player p) {
        /* Return the shield of the player */
        String s = "";
        for(int i = 0; i < p.shield; i++) {
            s += "🛡️"; // Not working on the Windows console
        }
        return s;
    }

    void testGetShield() {
        /* Test of the function getShield */
        Player p = newPlayer("Bob");
        p.shield = 5;
        assertEquals("🛡️🛡️🛡️🛡️🛡️", getShield(p));
        p.shield = 0;
        assertEquals("", getShield(p));
        p.shield = -1;
        assertEquals("", getShield(p));
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
        /* Test of the function newCellulePorte */
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
        /* Test of the function newCelluleMur */
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
            int heal = (int) (1 + random() * MAX_LOOT_HEALTH);
            return newLoot(TypeLoot.POTION, heal);
        } else if(r < POTION_SPAWN_PROBA + RING_SPAWN_PROBA) {
            int attack = (int) (1 + random() * MAX_LOOT_ATTACK);
            return newLoot(TypeLoot.RING, attack);
        } else if(r < POTION_SPAWN_PROBA + RING_SPAWN_PROBA + ARMOR_SPAWN_PROBA) {
            int defense = (int) (1 + random() * MAX_LOOT_DEFENSE);
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

    int genereRandomNumber(int min, int max) {
        return (int) (min + random() * (max - min));
    }

    void testGenereRandomNumber() {
        /* Test of the function genereRandomNumber */
        int r = genereRandomNumber(1, 10);
        assertTrue(r >= 1 && r <= 10);
    }

    Monster newMonsterRandom() {
        /* Create a new monster in a cellule */
        double r = random();
        if(r < ZOMBIE_SPAWN_PROBA) {
            return newMonster(TypeMonster.ZOMBIE, genereRandomNumber(1, ZOMBIE_ATTACK), genereRandomNumber(5, ZOMBIE_HEALT));
        } else if(r < ZOMBIE_SPAWN_PROBA + SKELETON_SPAWN_PROBA) {
            return newMonster(TypeMonster.SKELETON, genereRandomNumber(1, SKELETON_ATTACK), genereRandomNumber(5, SKELETON_HEALT));
        } else if(r < ZOMBIE_SPAWN_PROBA + SKELETON_SPAWN_PROBA + VAMPIRE_SPAWN_PROBA) {
            return newMonster(TypeMonster.VAMPIRE, genereRandomNumber(1, VAMPIRE_ATTACK), genereRandomNumber(5, VAMPIRE_HEALT));
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
        /* Load a map from a file */
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
                } else if(equals(s, "U")) { // Monter l'escalier case
                    map.carte[i][j] = newCelluleEscalierMontant();
                } else if(equals(s, "D")) { // Descendre l'escalier case
                    map.carte[i][j] = newCelluleEscalierDescendant();
                }
            }
        }
        return map;
    }

    Cellule newCelluleEscalierMontant() {
        /* Create a new cellule with an escalier montant */
        Cellule c = newCelluleEmpty();
        c.escalierMontant = true;
        return c;
    }

    Cellule newCelluleEscalierDescendant() {
        /* Create a new cellule with an escalier descendant */
        Cellule c = newCelluleEmpty();
        c.escalierDescendant = true;
        return c;
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
        println("Les touches sont: Z pour monter, Q pour aller à gauche, D pour aller à droite, S pour descendre. H pour afficher l'aide. X pour quiiter");
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
                    print(ANSI_BOLD + ANSI_YELLOW + "☼" + ANSI_RESET);
                } else if(map.carte[i][j].escalierMontant) {
                    if(map.carte[i][j].canExit) {
                        print(ANSI_BOLD + ANSI_GREEN + "▲" + ANSI_RESET);
                    } else {
                        print(ANSI_BOLD + ANSI_RED + "▲" + ANSI_RESET);
                    }
                } else if(map.carte[i][j].escalierDescendant) {
                    if(map.carte[i][j].canExit) {
                        print(ANSI_BOLD + ANSI_GREEN + "▼" + ANSI_RESET);
                    } else {
                        print(ANSI_BOLD + ANSI_RED + "▼" + ANSI_RESET);
                    }
                } else {
                    print(" ");
                }
                print(" ");
            }
            println();
        }
    }

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
        /* Test the canGoTo function */
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
        /* Test the isDirection function */
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
        /* Test the getDirection function */
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

    boolean movePlayer(Map map, String direction, int player_x, int player_y) {
        /* Move the player to the direction */
        int ligne = getDirection(direction, player_x, player_y)[0];
        int colonne = getDirection(direction, player_x, player_y)[1];
        if(canGoTo(map, ligne, colonne)) {
            Player player = map.carte[player_x][player_y].player;
            map.carte[player_x][player_y].player = null;
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

    boolean playerGoToStairs(Map map, String direction) {
        /* Check if the player can go to the stairs */
        int ligne = map.lignePlayer;
        int colonne = map.colonnePlayer;
        ligne = getDirection(direction, ligne, colonne)[0];
        colonne = getDirection(direction, ligne, colonne)[1];
        return map.carte[ligne][colonne].escalierDescendant || map.carte[ligne][colonne].escalierMontant;
    }

    void welcomeMessage() {
        /* Print the welcome message */
        print("Bienvenue dans");
        for(int i = 0; i < 5; i++) {
            print(".");
            delay(DELAY / 2);
        }
        println(ANSI_BOLD + "IthyphalGame !" + ANSI_RESET);

        println("Vous êtes piéger dans le donjon d'Ithyphal, vous devez trouver la sortie pour vous en échapper.");
        delay(DELAY * 2);
        println("Mais faites attention, vous ne serez pas seul dans ce donjon ! Des monstres sont la pour vous empêcher de sortir.");
        delay(DELAY * 2);
        println("Vous pouvez vous déplacer avec les touches Z, Q, S et D. Et la touche H pour afficher l'aide.");
        delay(DELAY * 2);
        println("Voici les différentes cases que vous pouvez ou non intérragir: ");
        delay(DELAY * 2);
        println("    - " + ANSI_GREEN + ANSI_BOLD + "Vert" + ANSI_RESET + " : Vous pouvez intérragir avec cette case");
        delay(DELAY * 2);
        println("    - " + ANSI_RED + ANSI_BOLD + "Rouge" + ANSI_RESET + " : Vous ne pouvez pas intérragir avec cette case");
        delay(DELAY * 2);
        println("Les monstres sont représentés par un " + ANSI_RED + ANSI_BOLD + "☠" + ANSI_RESET + "  et les portes sont représentées par un " + ANSI_BLUE + ANSI_BOLD + "▩" + ANSI_RESET + " .");
        delay(DELAY * 2);
        println("Des coffres sont présents dans le donjon, ils sont représentés par un " + ANSI_YELLOW + ANSI_BOLD + "☼" + ANSI_RESET + " .");
        delay(DELAY * 2);
        println("Les escaliers sont représentés par ▲ et ▼.");
        delay(DELAY);
        println("Bonne chance jeune aventurier !");
        print("Appuyez sur entrer pour continuer...");
        readString();
    }

    int menuPrincipal() {
        /* Print the main menu and ask player to choice */
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
        int choix;
        do {
            choix = readInt();
            if(choix < 1 || choix > 3) {
                println("Veuillez entrer un nombre entre 1 et 3");
            }
        } while(choix < 1 || choix > 3);
        return choix;
    }

    Map[][][] generateMap() {
        /* Generate the map */
        Map[][][] carte = new Map[5][5][5];
        for(int dim = 0; dim < 5; dim++) {
            for(int i = 0; i < 5; i++) {
                for(int j = 0; j < 5; j++) {
                    logger("Chargement de la map: map" + dim + i + j + ".csv");
                    carte[dim][i][j] = loadMap("map" + dim + i + j + ".csv");
                    logger("Chargement de la map: map" + dim + i + j + ".csv" + " terminé");
                }
            }
        }
        return carte;
    }

    String attack(Player p, Monster m) {
        // Return: "player" if the player lost, "monster" if the monster lost.
        while(p.healt > 0 && m.healt > 0) {
            println("Vous avez " + getHealt(p) + "  points de vie.");
            println("Vous avez " + getShield(p) + "  points de bouclier.");
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
                    if(p.shield > 0) {
                        p.shield -= m.attack;
                        println("Le monstre vous a infligé " + m.attack + " dégats !");
                        if(p.shield < 0) {
                            p.healt += p.shield;
                            p.shield = 0;
                            println("Votre bouclier a été détruit !");
                        } else {
                            println("Votre bouclier a absorbé " + m.attack + " dégats !");
                        }
                    } else {
                        p.healt -= m.attack;
                        println("Le monstre vous a infligé " + m.attack + " dégats !");
                    }
                    p.healt -= m.attack;
                } else {
                    println("Le monstre a raté son attaque !");
                }
            }
            delay(DELAY);
        }
        if(p.healt <= 0) {
            return "monster";
        } else {
            return "player";
        }
    }

    String[][] loadQuestionFile(String filename) {
        /* Load all questions in a file and return a 2D array with all the questions */
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
        /* Create a new question */
        Question q = new Question();
        q.question = question;
        q.bonne_rep = bonne_rep;
        q.bad_responses = bad_responses;
        return q;
    }

    void testNewQuestion() {
        /* Test the newQuestion function */
        String[] bad_responses = {"Réponse 1", "Réponse 2", "Réponse 3"};
        Question q = newQuestion("Question 1", "Réponse 4", bad_responses);
        assertArrayEquals(q.bad_responses, bad_responses);
        assertEquals(q.question, "Question 1");
        assertEquals(q.bonne_rep, "Réponse 4");
    }

    Question[] loadAllQuestions(String[][] tabQuestions) {
        /* Load all questions from a 2D array */
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
            if(!QUESTIONS[rdm].dejaPosee) {
                QUESTIONS[rdm].dejaPosee = true;
                return QUESTIONS[rdm];
            }
        }
        return null;
    }

    void randomiseTab(String[] tab) {
        /* Randomise the order of a String array */
        for(int i = 0; i < length(tab); i++) {
            int random = (int) (random() * length(tab));
            String tmp = tab[i];
            tab[i] = tab[random];
            tab[random] = tmp;
        }
    }

    boolean askQuestion(int nbrToAsk) {
        /* Ask a question to the player */
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
            print("Votre choix : ");
            int choix = 0;
            do {
                String temps = readString();
                if(stringOnlyInt(temps)) {
                    choix = convertToInt(temps);
                }
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
        /* Check if there is a monster in the carte */
        for(int i = 0; i < length(carte); i++) {
            for(int j = 0; j < length(carte[0]); j++) {
                if(carte[i][j].monster != null) {
                    return true;
                }
            }
        }
        return false;
    }

    void update(Map[][][] map, int ligne, int colonne, int etage) {
        /* Update the map */
        Cellule[][] carte = map[etage][ligne][colonne].carte;
        if(!monsterInCarte(carte)) {
            for(int i = 0; i < length(carte); i++) {
                for(int j = 0; j < length(carte[0]); j++) {
                    carte[i][j].canExit = true;
                }
            }
        } 
    }

    void helpCommand() {
        /* Display the help */
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
        println("Voici les différentes cases que vous pouvez ou non intérragir: ");
        println("    - " + ANSI_GREEN + ANSI_BOLD + "Vert" + ANSI_RESET + " : Vous pouvez intérragir avec cette case");
        println("    - " + ANSI_RED + ANSI_BOLD + "Rouge" + ANSI_RESET + " : Vous ne pouvez pas intérragir avec cette case");
        println("Les monstres sont représentés par un " + ANSI_RED + ANSI_BOLD + "☠" + ANSI_RESET + "  et les portes sont représentées par un " + ANSI_BLUE + ANSI_BOLD + "▩" + ANSI_RESET + " .");
        println("Des coffres sont présents dans le donjon, ils sont représentés par un " + ANSI_YELLOW + ANSI_BOLD + "☼" + ANSI_RESET + " .");
        println("Les escaliers sont représentés par ▲ et ▼.");
        println("Bonne chance !");
        print("Appuyez sur une entrée pour revenir au jeu");
        readString();
    }

    void monterEscalier(Map[][][] map, int ligne, int colonne, int etageActu, int player_x, int player_y) {
        /* Move the player to the next floor */
        Cellule[][] ancienneCarte = map[etageActu][ligne][colonne].carte;
        Cellule[][] nouvelleCarte = map[etageActu + 1][ligne][colonne].carte;
        Player p = ancienneCarte[player_x][player_y].player;
        ancienneCarte[player_x][player_y].player = null;
        nouvelleCarte[player_x][player_y].player = p;
        map[etageActu + 1][ligne][colonne].lignePlayer = player_x;
        map[etageActu + 1][ligne][colonne].colonnePlayer = player_y;
        map[etageActu][ligne][colonne].carte = ancienneCarte;
        map[etageActu + 1][ligne][colonne].carte = nouvelleCarte;
    }

    void descendreEscalier(Map[][][] map, int ligne, int colonne, int etageActu, int player_x, int player_y) {
        /* Move the player to the previous floor */
        Cellule[][] ancienneCarte = map[etageActu][ligne][colonne].carte;
        Cellule[][] nouvelleCarte = map[etageActu - 1][ligne][colonne].carte;
        Player p = ancienneCarte[player_x][player_y].player;
        ancienneCarte[player_x][player_y].player = null;
        nouvelleCarte[player_x][player_y].player = p;
        map[etageActu - 1][ligne][colonne].lignePlayer = player_x;
        map[etageActu - 1][ligne][colonne].colonnePlayer = player_y;
        map[etageActu][ligne][colonne].carte = ancienneCarte;
        map[etageActu - 1][ligne][colonne].carte = nouvelleCarte;
    }

    boolean saveGame(Map[][][] map, String name, int ligne, int colonne, int etage, int player_x, int player_y) {
        /* Save the game */
        /* File format:
            name, healt, attack, shield, player_x, player_y, ligne, colonne, etage, AllPosMonsterKill, AllPosLootTake
        */
        String[][] data = new String[1][11];
        data[0][0] = name;
        data[0][1] = "" + map[etage][ligne][colonne].carte[player_x][player_y].player.healt;
        data[0][2] = "" + map[etage][ligne][colonne].carte[player_x][player_y].player.attack;
        data[0][3] = "" + map[etage][ligne][colonne].carte[player_x][player_y].player.shield;
        data[0][4] = "" + player_x;
        data[0][5] = "" + player_y;
        data[0][6] = "" + ligne;
        data[0][7] = "" + colonne;
        data[0][8] = "" + etage;
        data[0][9] = "";
        data[0][10] = "";
        for(int i = 0; i < length(AllPosMonsterKillTab); i++) {
            data[0][9] += AllPosMonsterKillTab[i] + ";";
        }
        for(int i = 0; i < length(AllPosLootTakeTab); i++) {
            data[0][10] += AllPosLootTakeTab[i] + ";";
        }
        saveCSV(data, "./saves/" + name + ".csv");
        return true;

    }

    void addMonsterKillPos(int ligne, int colonne, int etage, int x, int y) {
        /* Add the position of the monster killed to the save file */
        String[] temps = new String[length(AllPosMonsterKillTab) + 1];
        for(int i = 0; i < length(AllPosMonsterKillTab); i++) {
            temps[i] = AllPosMonsterKillTab[i];
        }
        temps[length(AllPosMonsterKillTab)] = ligne + "|" + colonne + "|" + etage + "|" + x + "|" + y;
        AllPosMonsterKillTab = temps;
    }

    void addLootTakePos(int ligne, int colonne, int etage, int x, int y) {
        /* Add the position of the loot taken to the save file */
        String[] temps = new String[length(AllPosLootTakeTab) + 1];
        for(int i = 0; i < length(AllPosLootTakeTab); i++) {
            temps[i] = AllPosLootTakeTab[i];
        }
        temps[length(AllPosLootTakeTab)] = ligne + "|" + colonne + "|" + etage + "|" + x + "|" + y;
        AllPosLootTakeTab = temps;
    }

    boolean fileInFolder(String folder, String file) {
        /* Check if a file is in a folder */
        String[] files = getAllFilesFromDirectory(folder);
        for(int i = 0; i < length(files); i++) {
            if(equals(files[i], file)) {
                return true;
            }
        }
        return false;
    }

    String[] loadDataPlayer(String name) {
        /* Load the data of the player */
        CSVFile data = loadCSV("./saves/" + name + ".csv");
        String[] dataPlayer = new String[11];
        for(int i = 0; i < 11; i++) {
            try {
                dataPlayer[i] = getCell(data, 0, i);
                logger("dataPlayer[" + i + "] = " + dataPlayer[i]);
            } catch(Exception e) {
                logger("dataPlayer[" + i + "] = null");
                dataPlayer[i] = "";
            }
        }
        return dataPlayer;
    }

    boolean stringOnlyInt(String data) {
        /* Check if a string only contains int */
        for(int i = 0; i < length(data); i++) {
            if(charAt(data, i) < '0' || charAt(data, i) > '9') {
                return false;
            }
        }
        return true;
    }

    int convertToInt(String data) {
        /* Convert a string to an int */
        if(!stringOnlyInt(data)) {
            return -1;
        }
        int result = 0;
        int puissance = 1;
        for(int i = length(data) - 1; i >= 0; i--) {
            result += (int)(charAt(data, i) - '0') * puissance;
            puissance *= 10;
        }
        return result;
    }

    String[] append(String[] data, String value) {
        /* Add a value to the end of an array */
        String[] result = new String[length(data) + 1];
        for(int i = 0; i < length(data); i++) {
            result[i] = data[i];
        }
        result[length(data)] = value;
        return result;
    }

    String[] split(String data, String separator) {
        /* Split a string */
        String[] result = new String[1];
        int index = 0;
        int indexResult = 0;
        for(int i = 0; i < length(data); i++) {
            if(charAt(data, i) == charAt(separator, 0)) {
                boolean isSeparator = true;
                for(int j = 0; j < length(separator); j++) {
                    if(charAt(data, i + j) != charAt(separator, j)) {
                        isSeparator = false;
                    }
                }
                if(isSeparator) {
                    result[indexResult] = substring(data, index, i);
                    indexResult++;
                    result = append(result, "");
                    index = i + length(separator);
                    i += length(separator) - 1;
                }
            }
        }
        result[indexResult] = substring(data, index, length(data));
        return result;
    }

    void testSplit() {
        /* Test the split function */
        String[] result = split("0|0|0|4|6", "|");
        String[] verif = new String[]{ "0", "0", "0", "4", "6" };
        for(int i = 0; i < length(result); i++) {
            assertArrayEquals(result, verif);
        }
    }

    int getLigneCSV(String filename) {
        /* Get the number of line of a csv file */
        String[] dataPlayer = loadDataPlayer(filename);
        return convertToInt(dataPlayer[6]);
    }

    int getColonneCSV(String filename) {
        /* Get the number of column of a csv file */
        String[] dataPlayer = loadDataPlayer(filename);
        return convertToInt(dataPlayer[7]);
    }

    int getEtageCSV(String filename) {
        /* Get the number of floor of a csv file */
        String[] dataPlayer = loadDataPlayer(filename);
        return convertToInt(dataPlayer[8]);
    }

    int getPlayerXCSV(String filename) {
        /* Get the x position of the player in a csv file */
        String[] dataPlayer = loadDataPlayer(filename);
        return convertToInt(dataPlayer[5]);
    }

    int getPlayerYCSV(String filename) {
        /* Get the y position of the player in a csv file */
        String[] dataPlayer = loadDataPlayer(filename);
        return convertToInt(dataPlayer[4]);
    }

    void loadFromSave(Map[][][] carte, String name) {
        String[] dataPlayer = loadDataPlayer(name);
        int ligne = getLigneCSV(name);
        int colonne = getColonneCSV(name);
        int etage = getEtageCSV(name);
        int player_x = getPlayerYCSV(name);
        int player_y = getPlayerXCSV(name);
        carte[0][0][0].carte[0][0].player = null;
        carte[etage][ligne][colonne].carte[player_x][player_y].player = newPlayer(name);
        carte[etage][ligne][colonne].carte[player_x][player_y].player.healt = convertToInt(dataPlayer[1]);
        carte[etage][ligne][colonne].carte[player_x][player_y].player.attack = convertToInt(dataPlayer[2]);
        carte[etage][ligne][colonne].carte[player_x][player_y].player.shield = convertToInt(dataPlayer[3]);
        String[] AllPosMonsterKill = split(dataPlayer[9], ";");
        String[] AllPosLootTake = split(dataPlayer[10], ";");
        for(int i = 0; i < length(AllPosMonsterKill); i++) {
            String[] data = split(AllPosMonsterKill[i], "|");
            if (length(data) >= 5) {
                logger("Monstre en " + data[0] + " " + data[1] + " " + data[2] + " " + data[3] + " " + data[4]);
                int x = convertToInt(data[3]);
                int y = convertToInt(data[4]);
                carte[convertToInt(data[2])][convertToInt(data[0])][convertToInt(data[1])].carte[x][y].monster = null;
            }
        }
        for(int i = 0; i < length(AllPosLootTake); i++) {
            String[] data = split(AllPosLootTake[i], "|");
            if (length(data) >= 5) {
                logger("Loot en " + data[0] + " " + data[1] + " " + data[2] + " " + data[3] + " " + data[4]);
                int x = convertToInt(data[3]);
                int y = convertToInt(data[4]);
                carte[convertToInt(data[2])][convertToInt(data[0])][convertToInt(data[1])].carte[x][y].loot = null;
            }
        }
        AllPosMonsterKillTab = AllPosMonsterKill;
        AllPosLootTakeTab = AllPosLootTake;
    }

    void purgeMap(Map[][][] map) {
        /* Check AllPosMonsterKillTab and AllPosLootTakeTab and remove the monster and loot if they are dead or taken */

        logger("Purge de la map en cours...");
        
        logger("Suppression du joueur de base...");
        map[0][0][0].carte[4][4].player = null;
        logger("Suppression du joueur de base terminée !");

        logger("Suppression des monstres morts... (" + length(AllPosMonsterKillTab) + " monstres à supprimer)");

        for(int i = 0; i < length(AllPosMonsterKillTab); i++) {
            String[] data = split(AllPosMonsterKillTab[i], "|");
            if(length(data) >= 5) {
                int etage = convertToInt(data[0]);
                int colonne = convertToInt(data[1]);
                int ligne = convertToInt(data[2]);
                int x = convertToInt(data[3]);
                int y = convertToInt(data[4]);
                if(map[etage][ligne][colonne].carte[x][y].monster != null) {
                    map[etage][ligne][colonne].carte[x][y].monster = null;
                    logger("Le monstre aux coordonées (" + ligne + ":" + colonne + ":" + etage + ") x=" + x + " | y=" + y + " a été supprimé !");
                } else {
                    logger("Le monstre aux coordonées (" + ligne + ":" + colonne + ":" + etage + ") x=" + x + " | y=" + y + " n'a pas été supprimé !");                    
                }
            } else {
                logger("Erreur lors de la suppression du monstre.");
            }
        }

        logger("Suppression des monstres terminée !");
        logger("Suppression des loot ... Il y a " + length(AllPosLootTakeTab) + " loot à supprimer.");

        for(int i = 0; i < length(AllPosLootTakeTab); i++) {
            String[] data = split(AllPosLootTakeTab[i], "|");
            if(length(data) >= 5) {
                int etage = convertToInt(data[0]);
                int colonne = convertToInt(data[1]);
                int ligne = convertToInt(data[2]);
                int x = convertToInt(data[3]);
                int y = convertToInt(data[4]);
                if(map[etage][ligne][colonne].carte[x][y].loot != null) {
                    map[etage][ligne][colonne].carte[x][y].loot = null;
                    logger("Le loot aux coordonées (" + ligne + ":" + colonne + ":" + etage + ") x=" + x + " | y=" + y + " a été supprimé !");
                } else {
                    logger("Le monstre aux coordonées (" + ligne + ":" + colonne + ":" + etage + ") x=" + x + " | y=" + y + " n'a pas été supprimé !");                    
                }
            } else {
                logger("Erreur lors de la suppression du loot. La taille du tableau est de " + length(data) + "");
            }
        }
    }

    void algorithm() {
        int ligne = 0;
        int colonne = 0;
        int etage = 0;
        boolean fini = false;
        String filename = "";
        welcomeMessage();
        int choix = menuPrincipal();
        Map[][][] carte = new Map[5][5][5];
        carte = generateMap();
        if(choix == 1) {
            print("");
        } else if(choix == 2) {        
            do {
                print("Nom d'utilisateur (ecrivait stop pour arrêter) : ");
                filename = readString();
                if(equals(filename, "stop")) {
                    algorithm();
                    return;
                }
            } while(!fileInFolder("./saves", filename + ".csv"));

            loadFromSave(carte, filename);
            ligne = getLigneCSV(filename);
            colonne = getColonneCSV(filename);
            etage = getEtageCSV(filename);
            carte[etage][ligne][colonne].colonnePlayer = getPlayerYCSV(filename);
            carte[etage][ligne][colonne].lignePlayer = getPlayerXCSV(filename);
            purgeMap(carte);
        } else if(choix == 3) {
            println("Merci d'avoir joué !");
            delay(DELAY);
            return;
        } else {
            println("Erreur : Veuillez entrer un nombre entre 1 et 3");
            delay(DELAY);
            algorithm();
            return;
        }

        print("Chargement de la carte");
        for(int i = 0; i < 5; i++) {
            print(".");
            delay(DELAY / 2);
        }
        
        int player_x = carte[etage][ligne][colonne].colonnePlayer;
        int player_y = carte[etage][ligne][colonne].lignePlayer;

        boolean changementPiece = false;
        while(!fini && carte[etage][ligne][colonne].carte[player_x][player_y].player.healt > 0) {
            clearScreen();
            afficherMap(carte[etage][ligne][colonne]);
            print("Votre choix : ");
            String direction = toLowerCase(readString());
            if(equals(direction, "secq")) {
                carte[etage][ligne][colonne].carte[player_x][player_y].player.attack = 1000;
                carte[etage][ligne][colonne].carte[player_x][player_y].player.healt = 100;
                println("IJAVA RENTRE EN MOIIIIII !!!!!");
                delay(DELAY);
            } else if(equals(direction, "bastecestundieu")) {
                println("Dieu vous a fais sortir de cette donjon !");
                break;
            }else if(equals(direction, "z") || equals(direction, "s") || equals(direction, "q") || equals(direction, "d")) {
                int[] coordonnees_prochaine = getDirection(direction, player_x, player_y);
                if(playerGoToMonster(carte[etage][ligne][colonne], direction)) { 
                    Monster m = carte[etage][ligne][colonne].carte[coordonnees_prochaine[0]][coordonnees_prochaine[1]].monster;
                    if(m != null) {
                        println("Vous avez attaqué par un " + m.type + " !");
                        delay(DELAY);
                        String winner = attack(carte[etage][ligne][colonne].carte[player_x][player_y].player, m);
                        if(equals(winner, "player")) {
                            println("Vous avez gagné !");
                            delay(DELAY);
                            addMonsterKillPos(ligne, colonne, etage, coordonnees_prochaine[0], coordonnees_prochaine[1]);
                            carte[etage][ligne][colonne].carte[coordonnees_prochaine[0]][coordonnees_prochaine[1]].monster = null;
                        } else {
                            println("Vous avez perdu !");
                            delay(DELAY);
                            carte[etage][ligne][colonne].carte[player_x][player_y].player.healt = 0;
                        }
                    } else {
                        println("Erreur : Vous avez attaqué un monstre qui n'existe pas !");
                        println("Coordonnées : " + player_x + " " + player_y + "");
                        delay(DELAY);
                    }

                } else if(playerGoToLoot(carte[etage][ligne][colonne], direction)) {
                    
                    Loot l = carte[etage][ligne][colonne].carte[coordonnees_prochaine[0]][coordonnees_prochaine[1]].loot;
                    if(l == null) {
                        println("Erreur : Vous avez trouvé un loot qui n'existe pas !");
                        println("Coordonnées : " + player_x + " " + player_y + "");
                        delay(DELAY * 5);
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
                            addLootTakePos(ligne, colonne, etage, coordonnees_prochaine[0], coordonnees_prochaine[1]);
                            delay(DELAY);
                            if(type == TypeLoot.POTION) {
                                carte[etage][ligne][colonne].carte[player_x][player_y].player.healt += amount;
                                println("Vous avez gagné " + amount + " points de vie !");
                                delay(DELAY);
                            } else if(type == TypeLoot.RING) {
                                carte[etage][ligne][colonne].carte[player_x][player_y].player.attack += amount;
                                println("Vous avez gagné " + amount + " points d'attaque !");
                                delay(DELAY);
                            } else if(type == TypeLoot.ARMOR) {
                                carte[etage][ligne][colonne].carte[player_x][player_y].player.shield += amount;
                                println("Vous avez gagné " + amount + " points de défense !");
                                delay(DELAY);
                            }
                        } else {
                            println("Vous avez perdu le loot !");
                            carte[etage][ligne][colonne].carte[coordonnees_prochaine[0]][coordonnees_prochaine[1]].loot = null;
                            delay(DELAY);
                            println("Un monstre vous a attaqué !");
                            delay(DELAY);
                            Monster m = newMonsterRandom();
                            String winner = attack(carte[etage][ligne][colonne].carte[player_x][player_y].player, m);
                            if(equals(winner, "player")) {
                                addMonsterKillPos(ligne, colonne, etage, coordonnees_prochaine[0], coordonnees_prochaine[1]);
                                println("Vous avez gagné !");
                                delay(DELAY);
                            } else {
                                println("Vous avez perdu !");
                                delay(DELAY);
                                carte[etage][ligne][colonne].carte[player_x][player_y].player.healt = 0;
                            }
                        }
                    } else {
                        println("Vous avez décidé de ne pas prendre le loot ! Il a disparu !");
                        delay(DELAY);
                    }
                    carte[etage][ligne][colonne].carte[coordonnees_prochaine[0]][coordonnees_prochaine[1]].loot = null;
                } else if(playerGoToDoor(carte[etage][ligne][colonne], direction)) {
                    if(carte[etage][ligne][colonne].carte[coordonnees_prochaine[0]][coordonnees_prochaine[1]].canExit) {
                        println("Vous sortez de cette salle !");
                        delay(DELAY);
                        int[] nouvelleSalleCoord = getDirection(direction, ligne, colonne);

                        if(nouvelleSalleCoord[0] < 0 || nouvelleSalleCoord[0] >= carte[etage].length || nouvelleSalleCoord[1] < 0 || nouvelleSalleCoord[1] >= carte[etage][0].length) {
                            print("Bravo ! Vous êtes sorti du donjon");
                            for(int i = 0; i < 10; i++) {
                                print(".");
                                delay(DELAY / 2);
                            }
                            println();
                            println("Mais, ou êtes-vous arrivé ?");
                            delay(DELAY);
                            println("La suite, au prochaine épisode :P");
                            carte[etage][ligne][colonne].carte[player_x][player_y].player.healt = 0;
                            break;
                        }

                        int[] newPos = newPlayerPos(direction, carte, ligne, colonne, etage, nouvelleSalleCoord[0], nouvelleSalleCoord[1], etage, player_x, player_y);
                        carte[etage][ligne][colonne].carte[player_x][player_y].player = null;
                        player_x = newPos[0];
                        player_y = newPos[1];
                        ligne = nouvelleSalleCoord[0];
                        colonne = nouvelleSalleCoord[1];
                        carte[etage][ligne][colonne].lignePlayer = player_x;
                        carte[etage][ligne][colonne].colonnePlayer = player_y;
                        changementPiece = true;
                    }
                } else if(playerGoToStairs(carte[etage][ligne][colonne], direction)) {
                    if(carte[etage][ligne][colonne].carte[coordonnees_prochaine[0]][coordonnees_prochaine[1]].canExit) {
                        if(carte[etage][ligne][colonne].carte[coordonnees_prochaine[0]][coordonnees_prochaine[1]].escalierMontant) {
                            print("Vous montez les escaliers !");
                            delay(DELAY);
                            monterEscalier(carte, ligne, colonne, etage, player_x, player_y);
                            etage++;
                        } else {
                            print("Vous descendez les escaliers !");
                            delay(DELAY);
                            descendreEscalier(carte, ligne, colonne, etage, player_x, player_y);
                            etage--;
                        }
                    }
                }
                update(carte, ligne, colonne, etage);
                if(!changementPiece && movePlayer(carte[etage][ligne][colonne], direction, player_x, player_y)) {
                    player_x = carte[etage][ligne][colonne].lignePlayer;
                    player_y = carte[etage][ligne][colonne].colonnePlayer;
                } else if(!changementPiece) {
                    println("Erreur : Vous ne pouvez pas aller dans cette direction !");
                    delay(DELAY);
                } else {
                    changementPiece = false;
                }
            } else if(equals(direction, "h")) {
                helpCommand();
            } else if(equals(direction, "x")) {
                println("Vous avez quitté le jeu !");
                delay(DELAY);
                String save;

                do {
                    println("Voulez-vous sauvegarder la partie ? (O/N)");
                    save = readString();
                } while(!equals(save, "O") && !equals(save, "N"));

                if(equals(save, "O")) {
                    println("Veuillez entrer le nom de la sauvegarde :");
                    String name = readString();
                    if(saveGame(carte, name, ligne, colonne, etage, player_x, player_y)) {
                        println("La partie a été sauvegardée !");
                        delay(DELAY);
                    } else {
                        println("Erreur : La partie n'a pas pu être sauvegardée !");
                        delay(DELAY);
                    }
                }
                break;
            } else {
                println("Erreur : Veuillez entrer une direction valide");
                delay(DELAY);
            }
        }
    }
}


    //! Problème trouver
    //* - Aucun pour le moment

    //! Prochaine chose à faire
    //* - Rien à faire pour le moment    

    //! Ce qui a été fait
    //? - Lors de la téléportation à une autre salle, le joueur est encore en mémoire dans l'ancienne salle // FAIT
    //? - Quand on monte un escalier, le jeu ne détecte pas le joueur dans la salle du dessus // FAIT
    //? - FAIRE LA 3EME etage !!! // FAIT
    //? - Faire en sorte que le jeu détecte quand le joueur va sur une porte et le fait sortir si la porte est ouverte // FAIT
    //? - Faire en sorte que quand le joueur va sur une porte ouverte, il soit téléporté sur une autre carte // FAIT
    //? - Faire en sorte que le jeu détecte le nombre de monstres et si il n'y en a plus, il ouvre la ou les porte(s) // FAIT
    //? - Faire une fonction qui permet de sauvegarder la partie // FAIT
    //? - Faire une fonction qui permet de charger une partie // FAIT


    //TODO Plus si j'ai le temps :)