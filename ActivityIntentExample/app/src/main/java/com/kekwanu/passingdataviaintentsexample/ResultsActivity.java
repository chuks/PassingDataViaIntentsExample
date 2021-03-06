package com.kekwanu.passingdataviaintentsexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ResultsActivity extends ActionBarActivity {
    private String make;
    private String model;
    private String year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //enable the Android home button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent data = getIntent();
        String type = data.getStringExtra("type");

        processType(type, data);
    }

    private void processType(String type, Intent data){

        if (type.equals("primitive")){
            make = data.getStringExtra("make");
            model = data.getStringExtra("model");
            year = data.getStringExtra("year");
        }
        else if (type.equals("serializable")) {
            CarSerializable carSerializable = (CarSerializable) data.getSerializableExtra("car");

            make = carSerializable.getMake();
            model = carSerializable.getModel();
            year = carSerializable.getYear();

        }
        else if (type.equals("parcealable")){

            CarParcealable carParcealable = data.getParcelableExtra("car");
            make = carParcealable.getMake();
            model = carParcealable.getModel();
            year = carParcealable.getYear();
        }

        TextView makeTextView = (TextView)findViewById(R.id.make);
        TextView modelTextView = (TextView)findViewById(R.id.model);
        TextView yearTextView = (TextView)findViewById(R.id.year);

        makeTextView.setText(make);
        modelTextView.setText(model);
        yearTextView.setText(year);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        if (id == android.R.id.home){
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){

        super.onBackPressed(); //default, calls finish()
        finish();
    }
}
