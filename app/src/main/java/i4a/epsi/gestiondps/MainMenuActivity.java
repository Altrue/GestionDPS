package i4a.epsi.gestiondps;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import android.widget.TextView;

public class MainMenuActivity extends AppCompatActivity {
    private TextView activityFicheBilanTextView;
    private TextView activityMainCouranteTextView;
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

    public void showDatePickerDialog(View v) {
        FragmentManager fm = getFragmentManager();
        DialogFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(fm, "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        EditText editHeureDebutManif;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int annee = c.get(Calendar.YEAR);
            int mois = c.get(Calendar.MONTH);
            int jour = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, annee, mois, jour);
        }

        public void onDateSet(DatePicker view, int annee, int mois, int jour) {
            editHeureDebutManif = (EditText) getActivity().findViewById(R.id.edit_heure_debut_manif);
            editHeureDebutManif.setText(
                    new StringBuilder()
                            .append(mois + 1).append("/")
                            .append(jour).append("/")
                            .append(annee).append("")
            );
        }
    }
}
