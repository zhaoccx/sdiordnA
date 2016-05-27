package com.zcc.activtiyintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends Activity {
	private EditText et_second_message;
	private Button btn_second_back1;
	private Button btn_second_back2;
	private OnClickListener clicklistener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (v == btn_second_back1) {
				SecondActivity.this.finish();
			} else if (v == btn_second_back2) {
				Intent intent = getIntent();
				intent.putExtra("result", et_second_message.getText().toString());
				setResult(2, intent );
				SecondActivity.this.finish();
			}
			
			
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_send);
		et_second_message = (EditText) this.findViewById(R.id.et_second_message);
		btn_second_back1 = (Button) this.findViewById(R.id.btn_secend_back1);
		btn_second_back2 = (Button) this.findViewById(R.id.btn_secend_back2);

		Intent intent = getIntent();
		String extra = intent.getStringExtra("message");
		et_second_message.setText(extra);

		btn_second_back1.setOnClickListener(clicklistener);
		btn_second_back2.setOnClickListener(clicklistener);

	}
}
