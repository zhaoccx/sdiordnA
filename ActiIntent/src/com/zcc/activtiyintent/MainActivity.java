package com.zcc.activtiyintent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class MainActivity extends Activity implements OnClickListener {

	private EditText et_main_message;
	private Button btn_main_start1;
	private Button btn_main_start2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et_main_message = (EditText) this.findViewById(R.id.et_main_message);
		btn_main_start1 = (Button) this.findViewById(R.id.btn_main_start1);
		btn_main_start2 = (Button) this.findViewById(R.id.btn_main_start2);

		btn_main_start1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(MainActivity.this, "点了一般启动", 1).show();

				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				intent.putExtra("message", et_main_message.getText().toString());
				startActivity(intent);
			}
		});

		btn_main_start2.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		 Toast.makeText(this, "点了带回调整的启动", 1).show();

		Intent intent = new Intent(MainActivity.this, SecondActivity.class);
		intent.putExtra("message", et_main_message.getText().toString());
		startActivityForResult(intent, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1 && resultCode == 2) {
			et_main_message.setText(data.getStringExtra("result"));
		}
	}

}
