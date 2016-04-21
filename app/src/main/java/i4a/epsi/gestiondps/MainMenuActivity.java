package i4a.epsi.gestiondps;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainMenuActivity extends AppCompatActivity {
    private TextView activityFicheBilanTextView;
    private TextView activityMainCouranteTextView;
    private TextView activityFichePosteTextView;
    private FloatingActionButton retourMenuFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void displayFichePoste(View view) {
        setContentView(R.layout.activity_fiche_poste);
        activityFichePosteTextView = (TextView)findViewById(R.id.activityFichePoste_textview);

        retourMenuFab = (FloatingActionButton) findViewById(R.id.activityFichePoste_fab);
        retourMenuFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionRetourMenu(view);
            }
        });
    }

    public void displayMainCourante(View view) {
        setContentView(R.layout.activity_main_courante);
        activityMainCouranteTextView = (TextView)findViewById(R.id.activityMainCourante_textview);

        retourMenuFab = (FloatingActionButton) findViewById(R.id.activityMainCourante_fab);
        retourMenuFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionRetourMenu(view);
            }
        });
    }

    public void displayFicheBilan(View view) {
        setContentView(R.layout.activity_fiche_bilan);
        activityFicheBilanTextView = (TextView)findViewById(R.id.activityFicheBilan_textview);

        retourMenuFab = (FloatingActionButton) findViewById(R.id.activityFicheBilan_fab);
        retourMenuFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionRetourMenu(view);
            }
        });
    }

    public void actionRetourMenu(View view) {
        setContentView(R.layout.activity_main_menu);
    }

    public void actionFichePoste(View view) {
        activityFichePosteTextView.setText("Action Fiche Poste !");
    }

    public void actionMainCourante(View view) {
        activityMainCouranteTextView.setText("Action Main Courante !");
    }

    public void actionFicheBilan(View view) {
        activityFicheBilanTextView.setText("Action Fiche Bilan !");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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
