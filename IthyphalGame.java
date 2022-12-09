class IthyphalGame extends Program {

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
        for(int i = 0; i < length(map.carte); i++) {
            for(int j = 0; j < length(map.carte[0]); j++) {
                if(map.carte[i][j].isWall) {
                    print(ANSI_BOLD + ANSI_WHITE + "■" + ANSI_RESET);
                } else if(map.carte[i][j].isExit) {
                    if(map.carte[i][j].canExit) {
                        print(ANSI_BOLD + ANSI_GREEN + "~" + ANSI_RESET);
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
                    print(ANSI_BOLD + ANSI_BLACK + "▩" + ANSI_RESET);
                }
                print(" ");
            }
            println();
        }
    }

    /* End */

    void algorithm() {
        // Main function
        afficherMap(loadMap("debuggingMap.csv"));
    }
}