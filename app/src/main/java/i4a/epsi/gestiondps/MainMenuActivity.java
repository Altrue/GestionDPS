package i4a.epsi.gestiondps;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import android.widget.TextView;
import android.widget.TimePicker;

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

    public void showDateTimeDebutPickerDialog(View v) {
        FragmentManager fm = getFragmentManager();
        DialogFragment datePickerDebutFragment = new DatePickerDebutFragment();
        datePickerDebutFragment.show(fm, "datePicker");
    }

    public static class DatePickerDebutFragment extends DialogFragment
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
            editHeureDebutManif = (EditText) getActivity().findViewById(R.id.activityFichePoste_heureDebutManif_editText);
            editHeureDebutManif.setText(
                    new StringBuilder()
                            .append(jour).append("/")
                            .append(mois + 1).append("/")
                            .append(annee).append(" ")
            );

            FragmentManager fm = getFragmentManager();
            DialogFragment timePickerDebutFragment = new TimePickerDebutFragment();
            timePickerDebutFragment.show(fm, "timePicker");
        }
    }

    public static class TimePickerDebutFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {
        EditText editHeureDebutManif;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int heure = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, heure, minute, DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int heure, int minute) {
            editHeureDebutManif = (EditText) getActivity().findViewById(R.id.activityFichePoste_heureDebutManif_editText);
            editHeureDebutManif.append(
                    new StringBuilder()
                            .append(heure).append(":")
                            .append(minute).append("")
            );
        }
    }

    public void showDateTimeFinPickerDialog(View v) {
        FragmentManager fm = getFragmentManager();
        DialogFragment datePickerFinFragment = new DatePickerFinFragment();
        datePickerFinFragment.show(fm, "datePicker");
    }

    public static class DatePickerFinFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        EditText editHeureFinManif;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int annee = c.get(Calendar.YEAR);
            int mois = c.get(Calendar.MONTH);
            int jour = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, annee, mois, jour);
        }

        public void onDateSet(DatePicker view, int annee, int mois, int jour) {
            editHeureFinManif = (EditText) getActivity().findViewById(R.id.activityFichePoste_heureFinManif_editText);
            editHeureFinManif.setText(
                    new StringBuilder()
                            .append(jour).append("/")
                            .append(mois + 1).append("/")
                            .append(annee).append(" ")
            );

            FragmentManager fm = getFragmentManager();
            DialogFragment timePickerFragment = new TimePickerFragment();
            timePickerFragment.show(fm, "timePicker");
        }
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {
        EditText editHeureFinManif;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int heure = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, heure, minute, DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int heure, int minute) {
            editHeureFinManif = (EditText) getActivity().findViewById(R.id.activityFichePoste_heureFinManif_editText);
            editHeureFinManif.append(
                    new StringBuilder()
                            .append(heure).append(":")
                            .append(minute).append("")
            );
        }
    }
}
