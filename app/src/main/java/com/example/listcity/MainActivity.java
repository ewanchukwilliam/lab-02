

package com.example.listcity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	ListView cityList;
	ArrayAdapter<String> cityAdapter;
	ArrayList<String> dataList;
	EditText cityInput;
	Button addButton;
	Button deleteButton;
	Button confirmButton;
	int selectedPosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
		// things you need for your lab. for clicking the buttons
		// onClickListener/onItemClickListener
		// EditText
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		cityList = findViewById(R.id.city_list);
		cityInput = findViewById(R.id.city_input);
		addButton = findViewById(R.id.add_button);
		deleteButton = findViewById(R.id.delete_button);
		confirmButton = findViewById(R.id.confirm_button);

		String []cities={"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

		dataList = new ArrayList<>();
		dataList.addAll(Arrays.asList(cities));

		cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
		cityList.setAdapter(cityAdapter);

		// OnClickListener for the Add button
		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Show input field and confirm button
				cityInput.setVisibility(View.VISIBLE);
				confirmButton.setVisibility(View.VISIBLE);
				cityInput.requestFocus();
			}
		});

		// OnClickListener for the Confirm button
		confirmButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Add city if text is entered
				String cityName = cityInput.getText().toString().trim();
				if (!cityName.isEmpty()) {
					dataList.add(cityName);
					cityAdapter.notifyDataSetChanged();
					cityInput.setText("");
					cityInput.setVisibility(View.GONE);
					confirmButton.setVisibility(View.GONE);
				} else {
					Toast.makeText(MainActivity.this, "Please enter a city name", Toast.LENGTH_SHORT).show();
				}
			}
		});

		// OnClickListener for the Delete button
		deleteButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (selectedPosition != -1) {
					dataList.remove(selectedPosition);
					cityAdapter.notifyDataSetChanged();
					selectedPosition = -1;
					Toast.makeText(MainActivity.this, "City deleted", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(MainActivity.this, "Please select a city to delete", Toast.LENGTH_SHORT).show();
				}
			}
		});

		// OnItemClickListener for ListView items (to select item for deletion)
		cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				selectedPosition = position;
				Toast.makeText(MainActivity.this, "Selected: " + dataList.get(position), Toast.LENGTH_SHORT).show();
			}
		});

    }
}
