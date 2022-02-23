package com.stefane.calculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText editValor;
    private TextView textPorcentagem, textGorjeta, textTotal;
    private SeekBar seekPorcentagem;
    private double porcentagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editValor = findViewById(R.id.editValor);
        textPorcentagem = findViewById(R.id.textPorcentagem);
        textGorjeta = findViewById(R.id.textGorjeta);
        textTotal = findViewById(R.id.textTotal);
        seekPorcentagem = findViewById(R.id.seekPorcentagem);

        seekPorcentagem.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                porcentagem = progress;

                textPorcentagem.setText(Math.round(porcentagem) + " %"); // Math.round() -> arredonda um número

                calcular();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void calcular(){

        String valor = editValor.getText().toString();

        if(validarCampoValor(valor)){

            double valorConta = Double.parseDouble(valor);
            double gorjeta, total;

            gorjeta = valorConta * porcentagem / 100;
            total = valorConta + gorjeta;

            textGorjeta.setText("R$ " + gorjeta);
            textTotal.setText("R$ " + total);

        }else{
            Toast.makeText(getApplicationContext(), "O campo valor deve ser preenchido!", Toast.LENGTH_LONG).show();
        }

    }


    public Boolean validarCampoValor(String valor){
        if(valor == null || valor.equals("")){
            return false;
        }
        return true;
    }

}
