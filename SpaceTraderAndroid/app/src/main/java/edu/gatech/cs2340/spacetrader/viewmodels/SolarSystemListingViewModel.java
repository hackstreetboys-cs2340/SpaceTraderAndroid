package edu.gatech.cs2340.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.SolarSystem;
import edu.gatech.cs2340.spacetrader.entity.Universe;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.SolarSystemInteractor;

public class SolarSystemListingViewModel extends AndroidViewModel {
    private SolarSystemInteractor interactor;
    private SolarSystem currentSystem;
    private Universe currentUniverse;

    public SolarSystemListingViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getSolarSystemInteractor();
    }

    public void setCurrentUniverse(Universe currentUniverse) {
        this.currentUniverse = currentUniverse;
    }

    public List<SolarSystem> getSystems() {
        return interactor.getSystemsForUniverse(currentUniverse);
    }
}
