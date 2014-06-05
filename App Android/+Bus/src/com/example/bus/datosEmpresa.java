package com.example.bus;

import java.util.ArrayList;

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
import android.widget.ListView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

public class datosEmpresa extends Activity implements OnItemSelectedListener {
	String[] cadena = {};
	String url = "http://54.209.118.159:8080/AppMasBus/JSON/empresas/";
	Empresa datoEmpresa;
	int idEmpresa;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datos_empresa);

		Log.i("JSON", "Entrando");

		Bundle bundle = getIntent().getExtras();
		idEmpresa = bundle.getInt("idEmpresa") + 1;

		HttpClient httpClient = new DefaultHttpClient();
		url = url.concat(Integer.toString(idEmpresa)).concat("/");
		HttpGet del = new HttpGet(url);
		del.setHeader("content-type", "application/json");

		try {
			HttpResponse resp = httpClient.execute(del);
			String respStr = EntityUtils.toString(resp.getEntity());
			JSONArray respJSON = new JSONArray(respStr);
			JSONObject obj = respJSON.getJSONObject(0);
			JSONObject field = obj.getJSONObject("fields");
			datoEmpresa = new Empresa(field.getString("tasaAccidentabilidad"),
					"0-Inf", "0-CantCtrl", "0-%Flota",
					field.getString("cantidadCalificaciones"),
					field.getString("promedioCalificaciones"),
					field.getString("nombreEmpresa"));
		} catch (Exception ex) {
			Log.e("ServicioRest", "Error!", ex);
		}

		TextView textViewNombreEmpresa = (TextView) findViewById(R.id.nombreEmpresa);
		textViewNombreEmpresa.setText(datoEmpresa.getNombreEmpresa());

		TextView datosEmpresa = (TextView) findViewById(R.id.datosEmpresa);
		datosEmpresa.setText(datoEmpresa.getAccidentabilidad().concat("\n")
				.concat(datoEmpresa.getCalificacion()));
	}

	// cambio layout
	public void onClickBack(View botton) {
		Intent intent = new Intent();
		intent.setClass(this, ListaEmpresa.class);
		startActivity(intent);
	}

	public void onClickCalificar(View botton) {
		Intent intent = new Intent();
		intent.setClass(this, Calificar.class);
		intent.putExtra("idEmpresa", idEmpresa);
		intent.putExtra("nombreEmpresa", datoEmpresa.getNombreEmpresa());
		Log.i("Calificar", "ClickBoton");
		startActivity(intent);
	}

	public void onClickGoBackMain(View botton) {
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
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
