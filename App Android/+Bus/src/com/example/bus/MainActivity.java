package com.example.bus;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClickVentas(View botton) {

		Intent intent = new Intent();
		intent.setClass(this, Ventas.class);
		startActivity(intent);

	}

	public void onClickMain(View botton) {
		setContentView(R.layout.activity_main);
	}

	public void onClickEmpresa(View botton) {
		Intent intent = new Intent();
		intent.setClass(this, ListaEmpresa.class);
		startActivity(intent);

	}

}
