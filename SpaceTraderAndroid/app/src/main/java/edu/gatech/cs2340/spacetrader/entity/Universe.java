package edu.gatech.cs2340.spacetrader.entity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

/**
 * Object that represents the game's universe,
 * and all the solar systems within it.
 */
public class Universe {
    private HashSet<SolarSystem> solarSystems;

    /**
     * No arg constructor initializes solarSystems to an empty HashSet
     */
    public Universe() {
        solarSystems = new HashSet<SolarSystem>();
    }

    /**
     * method to generate the universe
     */
    public void generate(){

        // random number of solar systems between 10 and 40
        Random rand = new Random();
        int numSystems = rand.nextInt(30) + 10;

        // list of planet names - is this ok or better to get it from a json file?
        /*
        ArrayList<String> planetNames = new ArrayList<>(Arrays.asList("Acamar", "Adahn", "Aldea", "Andevian", "Antedi", "Balosnee", "Baratas", "Brax", "Bretel",
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
                */
        String[] names = Arrays.toString(PlanetNames.values()).replaceAll("^.|.$", "").split(", ");
        ArrayList<String> planetNames = new ArrayList<>(Arrays.asList(names));

        // list of the coordinates that have been used (to avoid duplicates)
        Coordinates usedCoords[] = new Coordinates[numSystems];

        // loop to create solar systems
        // does there need to be a try-catch here??
        for (int i = 0; i < numSystems; i++) {

            // assign coordinates, add them to list of usedCoords
            Coordinates coords = generateCoordinates(usedCoords);
            usedCoords[i] = coords;

            // create solar system with name and coords
            SolarSystem solarSystem = new SolarSystem();
            solarSystem.setName(planetNames.get(i));
            solarSystem.setCoordinates(coords);

            // each solar system has 0 to 10 planets
            int numPlanets;
            if (planetNames.size() >= 10) {
                numPlanets = rand.nextInt(10);
            } else {
                numPlanets = rand.nextInt(planetNames.size());
            }

            // make the planets
            ArrayList<Planet> planets = new ArrayList<>();
            for (int j = 0; j < numPlanets; j++) {
                int randomNameIndex = rand.nextInt(planetNames.size());
                String planetName = planetNames.get(randomNameIndex);
                Planet planet = makePlanet(planetName, coords);
                planets.add(planet);
            }
            solarSystem.setPlanets(planets);
            solarSystems.add(solarSystem);

        }

    }

    private Planet makePlanet(String plName, Coordinates ssCoords) {
        Random rand = new Random();
        // generate a planet with random tech level and resources

        // most commonly - No Special Resources
        Resources resources = Resources.NoSpecialResources;

        // it should sometimes be another resource level:
        if (rand.nextInt(100) < 25) {
            int randNum = rand.nextInt(Resources.values().length);
            // pretty sure this next line is wrong -
            // not sure how to assign a resource given the int (level) of the Resource
            resources = Resources.valueOf(String.valueOf(randNum));
        }

        // random tech level
        TechLevel techLevel;
        int randNum2 = rand.nextInt(TechLevel.values().length);
        // again, i think this is wrong
        techLevel = TechLevel.valueOf(String.valueOf(randNum2));

        return new Planet(plName, ssCoords, techLevel, resources);

    }

    private Coordinates generateCoordinates(Coordinates[] used) {
        Random rand = new Random();
        // generate random coords here
        Coordinates coords = new Coordinates();
        boolean validCoords = false;
        int count = 0;
        while (!validCoords) {
            if (count == 100) {
                break;
            }
            int latitude = rand.nextInt(100);
            int longitude = rand.nextInt(150);
            coords = new Coordinates(latitude, longitude);
            // edit valid coords here - this is the swift version
            /*
            validCoords = !coordinates.contains(where: { (badCoord) -> Bool in
                return coords.latitude == badCoord.latitude && coords.longitude == badCoord.longitude
            })
             */
            if (validCoords) {
                return coords;
            }
            count++;

        }
        System.out.println("No more possible coordinates without duplicating");
        return coords;
    }

    /**
     * solarSystems getter
     * @return a list of solar systems in the universe
     */
    public HashSet<SolarSystem> getSolarSystems() {
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
