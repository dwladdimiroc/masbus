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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Resultado extends Activity implements OnItemSelectedListener {

	ListView buses;
	ArrayList<Pasaje> listaPasaje = new ArrayList<Pasaje>();
	Pasaje datosPasajes;
	String url = "http://54.209.118.159:8080/AppMasBus/JSON/pasajes-";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resultados);

		Bundle bundle = getIntent().getExtras();

		ArrayList<ListaEntrada> datos = new ArrayList<ListaEntrada>();

		ListView lista = (ListView) findViewById(R.id.ListView_listado);

		Log.i("Dato", "Inicio JSON Respuesta");
		HttpClient httpClient = new DefaultHttpClient();
		url = url.concat(Integer.toString(bundle.getInt("idOrigen") + 1))
				.concat("-")
				.concat(Integer.toString(bundle.getInt("idDestino") + 1))
				.concat("/");
		Log.i("URL", url);
		HttpGet del = new HttpGet(url);
		del.setHeader("content-type", "application/json");
		try {
			HttpResponse resp = httpClient.execute(del);
			String respStr = EntityUtils.toString(resp.getEntity());
			JSONArray respJSON = new JSONArray(respStr);
			Log.i("DatosPasajes", "Respuesta JSON");
			for (int i = 0; i < respJSON.length(); i++) {
				JSONObject obj = respJSON.getJSONObject(i);
				JSONObject field = obj.getJSONObject("fields");

				datosPasajes = new Pasaje(bundle.getString("nombreOrigen"),
						bundle.getString("nombreDestino"),
						field.getString("empresaRuta"),
						field.getString("calificacionEmpresa"),
						field.getString("tiempoViaje"),
						field.getString("precio"));

				listaPasaje.add(datosPasajes);
			}
		} catch (Exception ex) {
			Log.e("ServicioRest", "Error!", ex);
		}

		for (int i = 0; i < listaPasaje.size(); i++) {
			datos.add(new ListaEntrada(R.drawable.im_no,
					listaPasaje.get(i).empresaRuta,
					listaPasaje.get(i).calificacionEmpresa,
					listaPasaje.get(i).tiempoViaje, listaPasaje.get(i).precio));
		}

		lista.setAdapter(new ListaAdaptador(this, R.layout.entrada, datos) {
			@Override
			public void onEntrada(Object entrada, View view) {
				TextView textViewNombreEmpresa = (TextView) view
						.findViewById(R.id.nombreEmpresa);

				textViewNombreEmpresa.setText(((ListaEntrada) entrada)
						.getNombreEmpresa());

				TextView textViewCalificacion = (TextView) view
						.findViewById(R.id.califacion);

				textViewCalificacion.setText(((ListaEntrada) entrada)
						.getCalificacion());

				TextView textViewTiempoTrayecto = (TextView) view
						.findViewById(R.id.tiempoTrayecto);

				textViewTiempoTrayecto.setText(((ListaEntrada) entrada)
						.getTiempoTrayectoria());

				TextView textViewPrecio = (TextView) view
						.findViewById(R.id.precio);

				textViewPrecio.setText(((ListaEntrada) entrada).getPrecio());

				ImageView imagen_entrada = (ImageView) view
						.findViewById(R.id.logoEmpresa);
				imagen_entrada.setImageResource(((ListaEntrada) entrada)
						.getLogoEmpresa());
			}
		});

		lista.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> pariente, View view,
					int posicion, long id) {
				ListaEntrada elegido = (ListaEntrada) pariente
						.getItemAtPosition(posicion);

				CharSequence texto = "Seleccionado: "
						+ elegido.getNombreEmpresa();
				Toast toast = Toast.makeText(Resultado.this, texto,
						Toast.LENGTH_LONG);
				toast.show();
			}
		});
	}

	// llama a main
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
