package youth.in2it.com.youthproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

@SuppressLint("CommitPrefEdits")
public class PreferenceData {
	private SharedPreferences pref;
	private Context mContext;
private Editor editor;

	public String getRegistrationId() {
		return pref.getString("RegdId","");
	}

	public void setRegistrationId(String registrationId) {
		editor.putString("RegdId", registrationId);
		editor.commit();
	}

	public boolean getLoginstatus() {
	return pref.getBoolean("loginStatus", false);
}


public void setLoginstatus(boolean loginstatus) {
	editor.putBoolean("loginStatus", loginstatus);
	editor.commit();
}


public String getUsertype() {
	return pref.getString("userType", "");
}


public void setUsertype(String usertype) {
	editor.putString("userType", usertype);
	editor.commit();
}


	public PreferenceData(Context context) {
		mContext = context;
		pref = PreferenceManager.getDefaultSharedPreferences(mContext);
		editor = pref.edit();
	}





}
