package com.example.bus;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class Calificar extends Activity implements OnRatingBarChangeListener {
	RatingBar getRatingBar;
	RatingBar setRatingBar;
	TextView valorText;

	float calificacionEmpresa;
	int idEmpresa;
	String nombreEmpresa;

	String url = "http://54.209.118.159:8080/AppMasBus/JSON/calificar-";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.califica);

		Bundle bundle = getIntent().getExtras();
		idEmpresa = bundle.getInt("idEmpresa");
		nombreEmpresa = bundle.getString("nombreEmpresa");

		getRatingBar = (RatingBar) findViewById(R.id.getRating);
		valorText = (TextView) findViewById(R.id.valoracionEmpresa);

		valorText.setText("No hay valoración de la empresa");

		TextView textViewNombreEmpresa = (TextView) findViewById(R.id.nombreEmpresaCalificar);
		textViewNombreEmpresa.setText(nombreEmpresa);

		// getRatingBar.setRating(calificacionEmpresa);
		getRatingBar.setOnRatingBarChangeListener(this);
	}

	@Override
	public void onRatingChanged(RatingBar ratingBar, float rating,
			boolean fromUser) {
		// TODO Auto-generated method stub
		calificacionEmpresa = rating;
		getRatingBar.setRating(calificacionEmpresa);
		valorText.setText(calificacionEmpresa + " puntos de valoración");

	}

	public void onClickCalificar(View botton) {

		HttpClient httpClient = new DefaultHttpClient();
		url = url.concat(Integer.toString(idEmpresa)).concat("-")
				.concat(Integer.toString((int)calificacionEmpresa)).concat("/");
		Log.i("DatosCalificar", url);
		HttpGet del = new HttpGet(url);
		del.setHeader("content-type", "application/resp");
		try {
			HttpResponse resp = httpClient.execute(del);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Intent intent = new Intent();
		intent.setClass(this, datosEmpresa.class);
		intent.putExtra("idEmpresa", idEmpresa - 1);
		intent.putExtra("nombreEmpresa", nombreEmpresa);
		startActivity(intent);
	}
}