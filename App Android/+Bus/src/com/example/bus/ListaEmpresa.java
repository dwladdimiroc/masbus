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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class ListaEmpresa extends Activity implements OnItemSelectedListener {

	Spinner spinnerEmpresa;
	String[] cadena = {};
	int nombreEmpresa;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.empresa);

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet del = new HttpGet(
				"http://54.209.118.159:8080/AppMasBus/JSON/empresas/");
		del.setHeader("content-type", "application/json");
		try {
			HttpResponse resp = httpClient.execute(del);
			String respStr = EntityUtils.toString(resp.getEntity());
			JSONArray respJSON = new JSONArray(respStr);
			String[] category = new String[respJSON.length()];
			for (int i = 0; i < respJSON.length(); i++) {
				JSONObject obj = respJSON.getJSONObject(i);
				JSONObject field = obj.getJSONObject("fields");
				category[i] = field.getString("nombreEmpresa");
			}
			cadena = category;
			Log.i("WEBSERVER", cadena.toString());
		} catch (Exception ex) {
			Log.e("ServicioRest", "Error!", ex);
		}

		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, cadena);
		spinnerEmpresa = (Spinner) findViewById(R.id.spinnerEmpresa);
		spinnerEmpresa.setOnItemSelectedListener(this);
		spinnerEmpresa.setAdapter(adaptador);
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

	public void onClickVer(View botton) {
		nombreEmpresa = spinnerEmpresa.getSelectedItemPosition();
		Intent intent = new Intent();
		intent.setClass(this, datosEmpresa.class);
		intent.putExtra("idEmpresa", nombreEmpresa);
		startActivity(intent);
	}

	public void onClickBack(View botton) {
		Intent intent = new Intent();
		intent.setClass(this, ListaEmpresa.class);
		startActivity(intent);
	}

	public void onClickGoBackMain(View botton) {
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
	}

}
