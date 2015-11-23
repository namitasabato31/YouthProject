package youth.in2it.com.youthproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressWarnings("deprecation")
@SuppressLint("NewApi")
public class LoginActivity extends ActionBarActivity {
	EditText edtUSerid, edtPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();

		edtUSerid = (EditText) findViewById(R.id.edt_userid);
		edtPassword = (EditText) findViewById(R.id.edt_password);

		Button btnSignin = (Button) findViewById(R.id.btn_signin);

		btnSignin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (edtUSerid.getText().toString().length() == 0) {
					edtUSerid.requestFocus();
					edtUSerid.setError("enter userid");
				} else if (edtPassword.getText().toString().length() == 0) {
					edtUSerid.clearFocus();
					edtPassword.requestFocus();
					edtPassword.setError("enter password");
				} else {
					edtUSerid.clearFocus();
					edtPassword.clearFocus();

					if (edtUSerid.getText().toString().equals("admin")) {

						YouthManager.getInstance().getmPreferenceData().setUsertype("admin");
						YouthManager.getInstance().getmPreferenceData().setLoginstatus(true);

						LoginActivity.this.finish();
						startActivity(new Intent(LoginActivity.this, HomeActivity.class));

					} else if(edtUSerid.getText().toString().equals("user")){
						YouthManager.getInstance().getmPreferenceData().setUsertype("user");
						YouthManager.getInstance().getmPreferenceData().setLoginstatus(true);

						LoginActivity.this.finish();
						startActivity(new Intent(LoginActivity.this, HomeActivity.class));
					}else{
						Toast.makeText(LoginActivity.this,"please enter userid as user or admin.",Toast.LENGTH_LONG).show();
					}


				}

			}
		});



	}


}
