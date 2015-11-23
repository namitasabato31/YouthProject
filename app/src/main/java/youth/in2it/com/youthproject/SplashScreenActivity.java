package youth.in2it.com.youthproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class SplashScreenActivity extends Activity {
	static Context context;

	String server_key = "AIzaSyDRQRzTqhTkoko3v6HcaS-2zWaFTy5YOmI;";
	String client_key ="AIzaSyCNg0mTQQiINirVUvVnM6cdCIurh0F86Qo";
	private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		context =this;

		
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Log.v("regId","dfv dfv");
				Log.v("regId",""+YouthManager.getInstance().getmPreferenceData().getRegistrationId());

			if(YouthManager.getInstance().getmPreferenceData().getRegistrationId().length() == 0){
					if (checkPlayServices()) {

						// Register Device in GCM Server
						registerInBackground();
					}
				}else{
					intentToScreen();
				}




			}
		}, 4000);
	}

	private void registerInBackground() {
		ServerPingAsyncTask serverPingAsyncTask = new ServerPingAsyncTask(SplashScreenActivity.this);
		serverPingAsyncTask.execute("");
	}


	// Check if Google Playservices is installed in Device or not
	private boolean checkPlayServices() {
		int resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(this);
		// When Play services not found in device
		if (resultCode != ConnectionResult.SUCCESS) {
			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
				// Show Error dialog to install Play services
				GooglePlayServicesUtil.getErrorDialog(resultCode, this,
						PLAY_SERVICES_RESOLUTION_REQUEST).show();
			} else {
				Toast.makeText(
						SplashScreenActivity.this,
						"This device doesn't support Play services, App will not work normally",
						Toast.LENGTH_LONG).show();
				finish();
			}
			return false;
		} else {
			
		}
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
checkPlayServices();
	}

	private void intentToScreen(){
		boolean b = YouthManager.getInstance().getmPreferenceData().getLoginstatus();
		Log.v("b",""+b);

		if (b) {
			finish();
			Intent ii = new Intent(SplashScreenActivity.this,
					HomeActivity.class);
			startActivity(ii);

		} else {
			finish();
			Intent iii = new Intent(SplashScreenActivity.this,
					LoginActivity.class);
			startActivity(iii);
		}
	}


	public static void updateUI(String result) {
		if(result.equals("success")){
			((SplashScreenActivity) context).intentToScreen();
		}
		else{
Toast.makeText(((SplashScreenActivity)context),"Error"+result,Toast.LENGTH_LONG).show();
			((SplashScreenActivity)context).finish();
		}
	}
}
