package i4a.epsi.gestiondps;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.app.Dialog;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.app.DialogFragment;
import android.widget.ArrayAdapter;
import android.app.FragmentManager;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.text.format.DateFormat;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;

import java.util.Timer;
import java.util.Vector;
import java.util.Calendar;
import java.util.TimerTask;

public class MainMenuActivity extends AppCompatActivity {

    //Désactivé car utilisé précédemment en tant que texte random à modifier pour des tests.
    //private TextView activityFicheBilanTextView;
    //private TextView activityMainCouranteTextView;

    public Timer cancelReturn;
    public int id = -1;
    private FloatingActionButton retourMenuFab;
    private boolean wantsReturn = false;
    private Vector<DialogFragment> dialogFragments = new Vector<DialogFragment>(); // Pas utilisé pour le moment

    private Button saveButtonTop;
    private Button saveButtonBottom;
    private Button loadButtonTop;
    private Button loadButtonBottom;

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
        id = -1;

        saveButtonTop = (Button) findViewById(R.id.activityFichePoste_buttonSave);
        saveButtonBottom = (Button) findViewById(R.id.activityFichePoste_buttonSaveBottom);
        loadButtonTop = (Button) findViewById(R.id.activityFichePoste_buttonLoad);
        loadButtonBottom = (Button) findViewById(R.id.activityFichePoste_buttonLoadBottom);

        saveButtonTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionFichePosteSauvegarder(view);
            }
        });

        saveButtonBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionFichePosteSauvegarder(view);
            }
        });

        loadButtonTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionFichePosteCharger(view);
            }
        });

        loadButtonBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionFichePosteCharger(view);
            }
        });
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
        id = -1;

        saveButtonTop = (Button) findViewById(R.id.mainCourante_buttonSave);
        saveButtonBottom = (Button) findViewById(R.id.mainCourante_buttonSaveBottom);
        loadButtonTop = (Button) findViewById(R.id.mainCourante_buttonLoad);
        loadButtonBottom = (Button) findViewById(R.id.mainCourante_buttonLoadBottom);

        saveButtonTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionMainCouranteSauvegarder(view);
            }
        });

        saveButtonBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionMainCouranteSauvegarder(view);
            }
        });


        loadButtonTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionMainCouranteCharger(view);
            }
        });

        loadButtonBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionMainCouranteCharger(view);
            }
        });
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

    public void actionFichePosteSauvegarder(View view) {
        EditText nom = (EditText) findViewById(R.id.activityFichePoste_nomDispositif_editText);
        EditText dateDebut = (EditText) findViewById(R.id.activityFichePoste_heureDebutManif_editText);
        EditText dateFin = (EditText) findViewById(R.id.activityFichePoste_heureFinManif_editText);
        EditText lieu = (EditText) findViewById(R.id.activityFichePoste_lieu_editText);
        EditText nature = (EditText) findViewById(R.id.activityFichePoste_nature_editText);
        EditText effectif = (EditText) findViewById(R.id.activityFichePoste_effectif_editText);
        EditText nbSecouriste = (EditText) findViewById(R.id.activityFichePoste_nbSecouristes_editText);
        EditText dateOuverture = (EditText) findViewById(R.id.activityFichePoste_heureOuverture_editText);
        EditText dateFermeture = (EditText) findViewById(R.id.activityFichePoste_heureFermeture_editText);
        EditText remarques = (EditText) findViewById(R.id.activityFichePoste_remarques_editText);
        Spinner dimentionnement = (Spinner) findViewById(R.id.activityFichePoste_dimentionnement_spinner);

        FichePoste fichePoste = new FichePoste();

        fichePoste.setId(id); // -1 = Pas une édition de fiche existante.
        fichePoste.setNom(nom.getText().toString());
        fichePoste.setDateDebut(dateDebut.getText().toString());
        fichePoste.setDateFin(dateFin.getText().toString());
        fichePoste.setLieu(lieu.getText().toString());
        fichePoste.setNature(nature.getText().toString());
        fichePoste.setEffectif(Integer.parseInt(effectif.getText().toString()));
        fichePoste.setNbSecouriste(Integer.parseInt(nbSecouriste.getText().toString()));
        fichePoste.setDateOuverture(dateOuverture.getText().toString());
        fichePoste.setDateFermeture(dateFermeture.getText().toString());
        fichePoste.setRemarques(remarques.getText().toString());
        fichePoste.setDimentionnement(dimentionnement.getSelectedItem().toString());

        GestionDpsBDD bdd = new GestionDpsBDD(getApplicationContext());
        bdd.insertFichePoste(fichePoste);
    }

    public void actionMainCouranteSauvegarder(View view) {
        EditText heureDebut = (EditText) findViewById(R.id.activityMainCourante_heureEntree_editText);
        EditText heureFin = (EditText) findViewById(R.id.activityMainCourante_heureSortie_editText);
        EditText prenom = (EditText) findViewById(R.id.activityMainCourante_prenom_editText);
        EditText nom = (EditText) findViewById(R.id.activityMainCourante_nom_editText);
        Spinner sexe = (Spinner) findViewById(R.id.activityMainCourante_sexePicker_spinner);
        EditText age = (EditText) findViewById(R.id.activityMainCourante_age_editText);
        EditText motif = (EditText) findViewById(R.id.activityMainCourante_motif_editText);
        EditText soins = (EditText) findViewById(R.id.activityMainCourante_soins_editText);
        EditText remarques = (EditText) findViewById(R.id.activityMainCourante_remarques_editText);

        MainCourante mainCourante = new MainCourante();
        mainCourante.setHeureDebut(heureDebut.getText().toString());
        mainCourante.setHeureFin(heureFin.getText().toString());
        mainCourante.setPrenom(prenom.getText().toString());
        mainCourante.setNom(nom.getText().toString());
        mainCourante.setSexe(sexe.getSelectedItem().toString());
        mainCourante.setAge(Integer.parseInt(age.getText().toString()));
        mainCourante.setMotif(motif.getText().toString());
        mainCourante.setSoins(soins.getText().toString());
        mainCourante.setRemarques(remarques.getText().toString());

        GestionDpsBDD bdd = new GestionDpsBDD(getApplicationContext());
        bdd.insertMainCourante(mainCourante);
    }

    public void actionFichePosteCharger(View view) {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(MainMenuActivity.this);
        builderSingle.setIcon(android.R.drawable.ic_dialog_info);
        builderSingle.setTitle("Chargement de Fiche Poste :");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                MainMenuActivity.this,
                android.R.layout.select_dialog_singlechoice);

        GestionDpsBDD bdd = new GestionDpsBDD(this);
        for (FichePoste fiche : bdd.getAllFichesPoste()) {
            arrayAdapter.add("(" + fiche.getId() + ") " + fiche.getNom());
        }

        builderSingle.setNegativeButton(
                "cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builderSingle.setAdapter(
                arrayAdapter,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String preParse = arrayAdapter.getItem(which);
                        String id = preParse.substring(1, preParse.indexOf(")"));

                        EditText nom = (EditText) findViewById(R.id.activityFichePoste_nomDispositif_editText);
                        EditText dateDebut = (EditText) findViewById(R.id.activityFichePoste_heureDebutManif_editText);
                        EditText dateFin = (EditText) findViewById(R.id.activityFichePoste_heureFinManif_editText);
                        EditText lieu = (EditText) findViewById(R.id.activityFichePoste_lieu_editText);
                        EditText nature = (EditText) findViewById(R.id.activityFichePoste_nature_editText);
                        EditText effectif = (EditText) findViewById(R.id.activityFichePoste_effectif_editText);
                        EditText nbSecouriste = (EditText) findViewById(R.id.activityFichePoste_nbSecouristes_editText);
                        EditText dateOuverture = (EditText) findViewById(R.id.activityFichePoste_heureOuverture_editText);
                        EditText dateFermeture = (EditText) findViewById(R.id.activityFichePoste_heureFermeture_editText);
                        EditText remarques = (EditText) findViewById(R.id.activityFichePoste_remarques_editText);
                        Spinner dimentionnement = (Spinner) findViewById(R.id.activityFichePoste_dimentionnement_spinner);

                        GestionDpsBDD bdd = new GestionDpsBDD(MainMenuActivity.this);
                        FichePoste fichePoste = bdd.getOneFichePosteById(Integer.parseInt(id));

                        MainMenuActivity.this.id = Integer.valueOf(id);
                        nom.setText(fichePoste.getNom());
                        dateDebut.setText(fichePoste.getDateDebut());
                        dateFin.setText(fichePoste.getDateFin());
                        lieu.setText(fichePoste.getLieu());
                        nature.setText(fichePoste.getNature());
                        effectif.setText(String.valueOf(fichePoste.getEffectif()));
                        nbSecouriste.setText(String.valueOf(fichePoste.getNbSecouriste()));
                        dateOuverture.setText(fichePoste.getDateOuverture());
                        dateFermeture.setText(fichePoste.getDateFermeture());
                        remarques.setText(fichePoste.getRemarques());

                        int spinnerIndex = 0;
                        int i = 0;
                        String[] spinnerStrings = getResources().getStringArray(R.array.spinnerDimentionnementItems);
                        for (String s : spinnerStrings) {
                            if (fichePoste.getDimentionnement().equals(s)) {
                                spinnerIndex = i;
                            }
                            i++;
                        }
                        dimentionnement.setSelection(spinnerIndex);
                    }
                });
        builderSingle.show();
    }

    public void actionMainCouranteCharger(View view) {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(MainMenuActivity.this);
        builderSingle.setIcon(android.R.drawable.ic_dialog_info);
        builderSingle.setTitle("Chargement de Main Courante :");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                MainMenuActivity.this,
                android.R.layout.select_dialog_singlechoice);

        GestionDpsBDD bdd = new GestionDpsBDD(this);
        for (MainCourante mainCourante : bdd.getAllMainsCourantes()) {
            arrayAdapter.add("(" + mainCourante.getId() + ") " + mainCourante.getNom());
        }

        builderSingle.setNegativeButton(
                "cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builderSingle.setAdapter(
                arrayAdapter,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String preParse = arrayAdapter.getItem(which);
                        String id = preParse.substring(1, preParse.indexOf(")"));

                        EditText heureDebut = (EditText) findViewById(R.id.activityMainCourante_heureEntree_editText);
                        EditText heureFin = (EditText) findViewById(R.id.activityMainCourante_heureSortie_editText);
                        EditText prenom = (EditText) findViewById(R.id.activityMainCourante_prenom_editText);
                        EditText nom = (EditText) findViewById(R.id.activityMainCourante_nom_editText);
                        Spinner sexe = (Spinner) findViewById(R.id.activityMainCourante_sexePicker_spinner);
                        EditText age = (EditText) findViewById(R.id.activityMainCourante_age_editText);
                        EditText motif = (EditText) findViewById(R.id.activityMainCourante_motif_editText);
                        EditText soins = (EditText) findViewById(R.id.activityMainCourante_soins_editText);
                        EditText remarques = (EditText) findViewById(R.id.activityMainCourante_remarques_editText);

                        GestionDpsBDD bdd = new GestionDpsBDD(MainMenuActivity.this);
                        MainCourante mainCourante = bdd.getOneMainCouranteById(Integer.parseInt(id));

                        MainMenuActivity.this.id = Integer.valueOf(id);
                        heureDebut.setText(mainCourante.getHeureDebut());
                        heureFin.setText(mainCourante.getHeureFin());
                        prenom.setText(mainCourante.getPrenom());
                        nom.setText(mainCourante.getNom());
                        age.setText(String.valueOf(mainCourante.getAge()));
                        motif.setText(mainCourante.getMotif());
                        soins.setText(mainCourante.getSoins());
                        remarques.setText(mainCourante.getRemarques());

                        int spinnerIndex = 0;
                        int i = 0;
                        String[] spinnerStrings = getResources().getStringArray(R.array.spinnerSexeItems);
                        for (String s : spinnerStrings) {
                            if (mainCourante.getSexe().equals(s)) {
                                spinnerIndex = i;
                            }
                            i++;
                        }
                        sexe.setSelection(spinnerIndex);
                    }
                });
        builderSingle.show();
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
}
