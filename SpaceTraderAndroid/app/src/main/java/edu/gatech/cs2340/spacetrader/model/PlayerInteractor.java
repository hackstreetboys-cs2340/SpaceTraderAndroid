package edu.gatech.cs2340.spacetrader.model;

import edu.gatech.cs2340.spacetrader.entity.Difficulty;
import edu.gatech.cs2340.spacetrader.entity.Player;

public class PlayerInteractor extends Interactor {

    public PlayerInteractor(Repository repo) {
        super(repo);
    }

    public void setSkillPoints(int pilotSkill, int engSkill, int tradeSkill, int fightSkill) {
        Player player = getRepository().getMyPlayer();
        player.setPilotSkill(pilotSkill);
        player.setEngSkill(engSkill);
        player.setTradeSkill(tradeSkill);
        player.setFightSkill(fightSkill);
    }

    public void setName(String name) {
        getRepository().getMyPlayer().setName(name);
    }

    public void setDifficulty(Difficulty diff) {
        getRepository().getMyPlayer().setDifficulty(diff);
    }
}
