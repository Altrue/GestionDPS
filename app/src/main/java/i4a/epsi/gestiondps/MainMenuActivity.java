package i4a.epsi.gestiondps;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import android.widget.TextView;
import android.widget.TimePicker;

public class MainMenuActivity extends AppCompatActivity {

    //Désactivé car utilisé précédemment en tant que texte random à modifier pour des tests.
    //private TextView activityFicheBilanTextView;
    //private TextView activityMainCouranteTextView;

    public Timer cancelReturn;
    private FloatingActionButton retourMenuFab;
    private boolean wantsReturn = false;
    private Vector<DialogFragment> dialogFragments = new Vector<DialogFragment>(); // Pas utilisé pour le moment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

    }

    // Fonctions display
    public void displayFichePoste(View view) {
        setContentView(R.layout.activity_fiche_poste);

        retourMenuFab = (FloatingActionButton) findViewById(R.id.activityFichePoste_fab);
        retourMenuFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionRetourMenu(view);
            }
        });
        retourMenuFab.setImageResource(android.R.drawable.ic_menu_revert);
        wantsReturn = false;
    }

    public void displayMainCourante(View view) {
        setContentView(R.layout.activity_main_courante);
        //Désactivé car utilisé précédemment en tant que texte random à modifier pour des tests.
        //activityMainCouranteTextView = (TextView)findViewById(R.id.activityMainCourante_textview);

        retourMenuFab = (FloatingActionButton) findViewById(R.id.activityMainCourante_fab);
        retourMenuFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionRetourMenu(view);
            }
        });
        retourMenuFab.setImageResource(android.R.drawable.ic_menu_revert);
        wantsReturn = false;
    }

    //RIP
    public void displayFicheBilan(View view) {
        setContentView(R.layout.activity_fiche_bilan);
        //Désactivé car utilisé précédemment en tant que texte random à modifier pour des tests.
        //activityFicheBilanTextView = (TextView)findViewById(R.id.activityFicheBilan_textview);

        retourMenuFab = (FloatingActionButton) findViewById(R.id.activityFicheBilan_fab);
        retourMenuFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionRetourMenu(view);
            }
        });
        retourMenuFab.setImageResource(android.R.drawable.ic_menu_revert);
        wantsReturn = false;
    }

    public void setReturnFalse() {
        wantsReturn = false;
        retourMenuFab.setImageResource(android.R.drawable.ic_menu_revert);
    }

    //Fonctions action
    public void actionRetourMenu(View view) {
        if (wantsReturn == false) {
            wantsReturn = true;
            retourMenuFab.setImageResource(android.R.drawable.ic_menu_help);
            cancelReturn = new Timer();
            cancelReturn.schedule(new TimerTask() {
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setReturnFalse();
                        }
                    });
                }
            },3000); // Annulation tentative de retour après 3 sec
        }
        else {
            cancelReturn.cancel(); // Annulation de l'annulation de la tentative de retour
            setContentView(R.layout.activity_main_menu);
        }
    }

    public void actionMainCourante(View view) {
        //Désactivé car utilisé précédemment en tant que texte random à modifier pour des tests.
        //activityMainCouranteTextView.setText("Action Main Courante !");
    }

    public void actionFicheBilan(View view) {
        //Désactivé car utilisé précédemment en tant que texte random à modifier pour des tests.
        //activityFicheBilanTextView.setText("Action Fiche Bilan !");
    }

    //Datepicker Factorisé (+Timepicker enchainé)
    public void showDateTimePickerDialog(View v) {
        FragmentManager fm = getFragmentManager();
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.plugParameters((String)v.getTag(), v.getId());
        datePickerFragment.show(fm, "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        EditText returnText;
        String tag;
        int id;

        public void plugParameters(String t, int i) {
            this.tag = t;
            this.id = i;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int annee = c.get(Calendar.YEAR);
            int mois = c.get(Calendar.MONTH);
            int jour = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, annee, mois, jour);
        }

        public void onDateSet(DatePicker view, int annee, int mois, int jour) {
            returnText = (EditText) getActivity().findViewById(id);
            returnText.setText(
                    new StringBuilder()
                            .append(jour).append("/")
                            .append(mois + 1).append("/")
                            .append(annee).append(" ")
            );

            FragmentManager fm = getFragmentManager();
            TimePickerFragment timePickerFragment = new TimePickerFragment();
            timePickerFragment.plugParameters(tag, id);
            timePickerFragment.show(fm, "timePicker");
        }
    }

    //Timepicker factorisé
    public void showTimePickerDialog(View v) {
        FragmentManager fm = getFragmentManager();
        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.plugParameters((String)v.getTag(), v.getId());
        timePickerFragment.show(fm, "timePicker");
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {
        EditText returnText;
        String tag;
        int id;

        public void plugParameters(String t, int i) {
            this.tag = t;
            this.id = i;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int heure = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, heure, minute, DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int heure, int minute) {
            returnText = (EditText) getActivity().findViewById(id);
            returnText.append(
                    new StringBuilder()
                            .append(heure).append(":")
                            .append(minute).append("")
            );
        }
    }

//TODO : Jeter la suite si on est sur que la factorisation est OK.
/*
    //Début du bordel des date pickers
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
            DialogFragment timePickerFinFragment = new TimePickerFinFragment();
            timePickerFinFragment.show(fm, "timePicker");
        }
    }

    public static class TimePickerFinFragment extends DialogFragment
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

    public static class TimePickerDebutPosteFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {
        EditText editHeureDebutPoste;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int heure = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, heure, minute, DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int heure, int minute) {
            editHeureDebutPoste = (EditText) getActivity().findViewById(R.id.activityFichePoste_heureOuverture_editText);
            editHeureDebutPoste.append(
                    new StringBuilder()
                            .append(heure).append(":")
                            .append(minute).append("")
            );
        }
    }

    public void showTimeDebutPostePickerDialog(View v) {
        FragmentManager fm = getFragmentManager();
        DialogFragment timePickerDebutPosteFragment = new TimePickerDebutPosteFragment();
        timePickerDebutPosteFragment.show(fm, "timePicker");
    }

    public static class TimePickerFinPosteFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {
        EditText editHeureFinPoste;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int heure = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, heure, minute, DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int heure, int minute) {
            editHeureFinPoste = (EditText) getActivity().findViewById(R.id.activityFichePoste_heureFermeture_editText);
            editHeureFinPoste.append(
                    new StringBuilder()
                            .append(heure).append(":")
                            .append(minute).append("")
            );
        }
    }

    public void showTimeFinPostePickerDialog(View v) {
        FragmentManager fm = getFragmentManager();
        DialogFragment timePickerFinPosteFragment = new TimePickerFinPosteFragment();
        timePickerFinPosteFragment.show(fm, "timePicker");
    }*/
}
