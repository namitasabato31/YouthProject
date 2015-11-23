package youth.in2it.com.youthproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

@SuppressWarnings("deprecation")
@SuppressLint("NewApi")
public class RegistrationActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.white1));
		actionBar.setTitle(Html.fromHtml("<font color='#a9a9a9'>Registration</font>"));
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setLogo(getResources().getDrawable(R.drawable.ic_launcher));
		
		
		
		
		
		
		Button btnRegister = (Button) findViewById(R.id.btn_register);

		btnRegister.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(RegistrationActivity.this, "Registered Succesfully", Toast.LENGTH_SHORT).show();
				RegistrationActivity.this.finish();

			}
		});

	}

}
