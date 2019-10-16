package com.example.lezione2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button incremento,decremento;
    EditText input;
    int minValue = 0;
    int maxValue = 100;
    int modelValue = 50;
    SeekBar seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //OnCreate viene creato appena viene create l'activity
        super.onCreate(savedInstanceState);
        //Associa il file Java al file Layout
        setContentView(R.layout.activity_main);
        //Collego i bottoni creati su XML per stabilire il loro comportamento
        incremento = (Button) findViewById(R.id.incremento);
        decremento = (Button) findViewById(R.id.decremento);
        input = (EditText) findViewById(R.id.input);
        seekbar = (SeekBar) findViewById(R.id.seekbar);

        //Posiziono la seekbar nel valore 50 (al centro)
        updateValue(modelValue);

        //Stabilisco il comportamento dei bottoni

        incremento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //input.setText("+1");
                updateValue(++modelValue);
            }
        });

        decremento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //input.setText("-1");
                updateValue(--modelValue);
            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            //Avvisa l'utente ogni volta che c'è una modifica nella seekbar
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateValue(seekBar.getProgress());
            }

            @Override
            //Avvisa l'utente quando inizia il tocco
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            //Avvisa l'utente quando finisce il tocco
            public void onStopTrackingTouch(SeekBar seekBar) {
                updateValue(seekBar.getProgress());
            }
        });

    }

    protected void updateValue(int newValue){
        //Controllo se è compreso tra 0 e 100
        newValue = newValue > maxValue ? maxValue : newValue;
        newValue = newValue < minValue ? minValue : newValue;

        //Aggiorno il valore visualizzato della seekbar
        if(this.seekbar.getProgress() != modelValue){
            this.seekbar.setProgress(modelValue);
        }
        //Aggiorno la variabile che indica il valore attuale della calcolatrice
        this.modelValue = newValue;
        input.setText(""+this.modelValue);
    }

    @Override
    protected void onStop(){
        super.onStop();
        //Prendo il testo da Android
        /*TextView testo = (TextView) findViewById(R.id.titoloModificale);
        testo.setText("Siamo in stato di onStop");*/
    }

}
