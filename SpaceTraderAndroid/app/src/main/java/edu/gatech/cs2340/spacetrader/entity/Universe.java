package edu.gatech.cs2340.spacetrader.entity;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Object that represents the game's universe,
 * and all the solar systems within it.
 */
public class Universe {
    private List<SolarSystem> solarSystems;
    private Set<Pair<Integer, Integer>> coordinates;

    /**
     * No arg constructor initializes solarSystems to an empty HashSet
     */
    public Universe() {
        solarSystems = new ArrayList<>();
        coordinates = new HashSet<>();
    }

    /**
     * populates the universe with solar systems
     * @param seed seed for the player
     */
    public void generate(long seed) {
        //list of possible planet names
        List<String> planetNames = new ArrayList<>(Arrays.asList("Acamar", "Adahn", "Aldea", "Andevian", "Antedi", "Balosnee", "Baratas", "Brax", "Bretel",
                "Calondia", "Campor", "Capelle", "Carzon", "Castor", "Cestus", "Cheron", "Courteney",
                "Daled", "Damast", "Davlos", "Deneb", "Deneva", "Devidia", "Draylon", "Drema", "Endor", "Esmee", "Exo", "Ferris", "Festen", "Fourmi", "Frolix",
                "Gemulon", "Guinifer", "Hades", "Hamlet", "Helena", "Hulst", "Iodine", "Iralius", "Janus", "Japori", "Jarada", "Jason",
                "Kaylon", "Khefka", "Kira", "Klaatu", "Klaestron", "Korma", "Kravat", "Krios", "Laertes", "Largo", "Lave", "Ligon", "Lowry",
                "Magrat", "Malcoria", "Melina", "Mentar", "Merik", "Mintaka", "Montor", "Mordan", "Myrthe",
                "Nelvana", "Nix", "Nyle", "Odet", "Og", "Omega", "Omphalos", "Orias", "Othello",
                "Parade", "Penthara", "Picard", "Pollux", "Quator", "Rakhar", "Ran", "Regulas", "Relva", "Rhymus", "Rochani", "Rubicum", "Rutia",
                "Sarpeidon", "Sefalla", "Seltrice", "Sigma", "Sol", "Somari", "Stakoron", "Styris",
                "Talani", "Tamus", "Tantalos", "Tanuga", "Tarchannen", "Terosa", "Thera", "Titan", "Torin", "Triacus", "Turkana", "Tyrus", "Umberlee", "Utopia",
                "Vadera", "Vagra", "Vandor", "Ventax", "Xenon", "Xerxes", "Yew", "Yojimbo", "Zalkon", "Zuul"));
        // random number of solar systems between 10 and 20
        Random rand = new Random(seed);
        int numSystems = rand.nextInt(10) + 10;
        while (numSystems > 0) {
            //creates coordinates
            int x = rand.nextInt(150);  //150x100 grid
            int y = rand.nextInt(100);
            Pair<Integer, Integer> newCoordinates = new Pair<>(x, y);
            //checks for duplicate coordinates
            if (coordinates.add(newCoordinates)) {
                //random number of planets between 6 and 1
                int numOfPlanets = rand.nextInt(5) + 1;
                SolarSystem newSystem = new SolarSystem("Solar System " + numSystems, newCoordinates);
                newSystem.generateSystem(numOfPlanets, planetNames);
                solarSystems.add(newSystem);
                numSystems--;
            }
        }
    }

    /**
     * solarSystems getter
     *
     * @return a list of solar systems in the universe
     */
    public List<SolarSystem> getSolarSystems() {
        return solarSystems;
    }

    @Override
    public String toString() {
        String str = "Universe (" + solarSystems.size() + " unique systems):\n";
        for (SolarSystem system : solarSystems) {
            str += system + "\n";
        }
        return str;
    }
}
