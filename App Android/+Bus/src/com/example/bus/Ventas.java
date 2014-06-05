package com.example.bus;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Ventas extends Activity implements OnItemSelectedListener {

	Spinner spinnerOrigen;
	Spinner spinnerDestino;
	String[] cadena = {};
	int origen;
	int destino;
	String origenNombre;
	String destinoNombre;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ventas);

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet del = new HttpGet(
				"http://54.209.118.159:8080/AppMasBus/JSON/ciudades");
		del.setHeader("content-type", "application/json");
		try {
			HttpResponse resp = httpClient.execute(del);
			String respStr = EntityUtils.toString(resp.getEntity());
			JSONArray respJSON = new JSONArray(respStr);
			String[] category = new String[respJSON.length()];
			for (int i = 0; i < respJSON.length(); i++) {
				JSONObject obj = respJSON.getJSONObject(i);
				JSONObject field = obj.getJSONObject("fields");
				String nomb = field.getString("nombreCiudad");
				nomb.concat(Integer.toString(i+1));
				category[i] = nomb;
			}
			cadena = category;
		} catch (Exception ex) {
			Log.e("ServicioRest", "Error!", ex);
		}

		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, cadena);
		spinnerOrigen = (Spinner) findViewById(R.id.origen);
		spinnerDestino = (Spinner) findViewById(R.id.destino);
		spinnerOrigen.setOnItemSelectedListener(this);
		spinnerDestino.setOnItemSelectedListener(this);
		spinnerOrigen.setAdapter(adaptador);
		spinnerDestino.setAdapter(adaptador);
	}

	public void onClickBack(View botton) {
		Intent intent = new Intent();
		intent.setClass(this, Ventas.class);
		startActivity(intent);
	}

	public void onClickGoBackMain(View botton) {
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
	}

	public void onClickBuscar(View botton) {
		origen = spinnerOrigen.getSelectedItemPosition();
		destino = spinnerDestino.getSelectedItemPosition();
		origenNombre = spinnerOrigen.getSelectedItem().toString();
		destinoNombre = spinnerDestino.getSelectedItem().toString();

				 
		Intent intent = new Intent();
		intent.setClass(this, Resultado.class);
		intent.putExtra("idOrigen", origen);
		intent.putExtra("idDestino", destino);
		intent.putExtra("nombreOrigen", origenNombre);
		intent.putExtra("nombreDestino", destinoNombre);
		startActivity(intent);

	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
