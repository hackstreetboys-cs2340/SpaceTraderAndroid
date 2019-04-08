package edu.gatech.cs2340.spacetrader.views;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.entity.Universe;
import edu.gatech.cs2340.spacetrader.model.ResultHandler;
import edu.gatech.cs2340.spacetrader.viewmodels.PlayerViewModel;
import edu.gatech.cs2340.spacetrader.viewmodels.UniverseViewModel;


import java.util.Arrays;
import java.util.List;

/**
 * Start up activity for game.
 */
public class MainActivity extends AppCompatActivity {
    public static final int CONFIGURE_GAME = 1;
    public static final int RC_SIGN_IN = 2;
    public static final int PLANET_ACTIVITY = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Test","TESTING");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        /*
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConfigureGameActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Choose authentication providers
                List<AuthUI.IdpConfig> providers = Arrays.asList(
                        new AuthUI.IdpConfig.EmailBuilder().build());
                Log.d("TESTING", "Clicked");
                // Create and launch sign-in intent
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setIsSmartLockEnabled(false)
                                .setAvailableProviders(providers)
                                .build(),
                        RC_SIGN_IN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                Log.d("TESTING", "Logged in ");
                String uid = FirebaseAuth.getInstance().getUid();
                final PlayerViewModel playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
                playerViewModel.setUID(uid);
                Log.d("TESTING", uid);
                playerViewModel.downloadPlayer(new ResultHandler<Player>() {
                    @Override
                    public void onSuccess(Player data) {
                        Log.d("TESTING", "Success");
                        if (data == null) {
                            Intent intent = new Intent(MainActivity.this, ConfigureGameActivity.class);
                            startActivityForResult(intent, CONFIGURE_GAME);
                        } else {
                            Log.d("TESTING", data.toString());
                            playerViewModel.addPlayer(data);
                            Intent intent = new Intent(MainActivity.this, PlanetActivity.class);
                            startActivityForResult(intent, PLANET_ACTIVITY);
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Log.d("TESTING", e.getLocalizedMessage());
                    }
                });

                long seed = playerViewModel.getPlayer().getSeed();
                Universe universe = new Universe();
                universe.generate(seed);
                UniverseViewModel universeViewModel = ViewModelProviders.of(this).get(UniverseViewModel.class);
                universeViewModel.addUniverse(universe);
            } else {
                if (response != null) {
                    Log.d("TESTING", "Error " + response.getError().getErrorCode());
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
