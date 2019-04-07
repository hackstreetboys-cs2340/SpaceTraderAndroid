package edu.gatech.cs2340.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.arch.lifecycle.AndroidViewModel;
import edu.gatech.cs2340.spacetrader.model.DatabaseInteractor;
import edu.gatech.cs2340.spacetrader.model.Model;

public class DatabaseViewModel extends AndroidViewModel {

    private DatabaseInteractor interactor;

    public DatabaseViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getDatabaseInteractor();
    }
}
