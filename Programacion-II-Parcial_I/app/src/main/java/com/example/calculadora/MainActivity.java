
package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.IllegalFormatCodePointException;

public class MainActivity extends AppCompatActivity {
    TabHost tbhPestanas;
	RelativeLayout Contenido;
    TextView Temp;
    Button btnCalculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Contenido = findViewById(R.id.contenidoView);
		tbhPestanas = findViewById(R.id.tbhParcial);
        tbhPestanas.setup();

		tbhPestanas.addTab(tbhPestanas.newTabSpec("Agua").setContent(R.id.tabMetrosAgua).setIndicator("Agua"));
        tbhPestanas.addTab(tbhPestanas.newTabSpec("Area").setContent(R.id.tabArea).setIndicator("Area"));

		btnCalculo = (Button) findViewById(R.id.btnCalcular);
        btnCalculo.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View V){
					CalcularAgua();
				}

				private void CalcularAgua() {
					try {
						double Procedimiento;
						Temp = (TextView) findViewById(R.id.txtCantidad);
						double Cantidad = Double.parseDouble(Temp.getText().toString());
						Temp = (TextView) findViewById((R.id.lblPago));

						if (Cantidad > 0 && Cantidad < 19){
							Temp.setText("Monto a pagar: $6.00");
						} else if (Cantidad > 18 && Cantidad <29) {
							Procedimiento = ((Cantidad - 18) * 0.45) + 6;
							Temp.setText("Monto a pagar: $" + Procedimiento);
						} else {
							Procedimiento = (((Cantidad - 28)*0.65)+(10*0.45))+6;
							Temp.setText("Monto a pagar: $" + Procedimiento);
						}

					} catch (Exception e) {
						Temp = findViewById(R.id.lblPago);
						Temp.setText("Por favor rellene el campo correctamente");
					}
				}
        });
    }
	private void CalcularArea() {


			btnCalcular = findViewById(R.id.btnCalcularLongitud); //boton calcularArea
			btnCalcular.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					try {
						tempVal = (TextView) findViewById(R.id.txtCantidadArea);
						double cantidad = Double.parseDouble(tempVal.getText().toString());

						cboDe = findViewById(R.id.cboDeArea);
						cboA = findViewById(R.id.cboAArea);
						tempVal = findViewById(R.id.lblRespuestaL);
						double roundOff = Math.round((MiConversor.Convertir(3, cboDe.getSelectedItemPosition(), cboA.getSelectedItemPosition(), cantidad))*10000.0) / 10000.0;
						tempVal.setText("Respuesta: " + roundOff);
					}catch (Exception e){
						tempVal = findViewById(R.id.lblRespuestaL);
						tempVal.setText("Por favor ingrese los valores correspondiente");
						Toast.makeText(getApplicationContext(), "Por ingrese los valores correspondiente "+ e.getMessage(),Toast.LENGTH_SHORT).show();
					}
				}
			});
		}

	}
class ConversoresArea {
	double [][] conversiones = {
			{0.09290304, 0,698896, 0.836127, 1e-6, 628.8634, 6988,96, 10000}, //Area

	};
	public double Convertir(int opcion, int de, int a, double cantidad){
		return Math.round((conversiones[opcion][a] / conversiones[opcion][de] * cantidad));
	}
}