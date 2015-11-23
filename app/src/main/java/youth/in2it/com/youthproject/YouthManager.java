package youth.in2it.com.youthproject;

import android.app.Application;
import android.content.Context;


public class YouthManager extends Application {

	private static YouthManager singleton;
	
	private static Context sContext;
	

	public static YouthManager getInstance() {

		return singleton;

	}
	
	private PreferenceData mPreferenceData;

	public PreferenceData getmPreferenceData() {
		if(mPreferenceData == null){
			mPreferenceData = new PreferenceData(sContext);	
		}
		return mPreferenceData;
	}

	public void setmPreferenceData(PreferenceData mPreferenceData) {
		this.mPreferenceData = mPreferenceData;
	}

	@Override
	public void onCreate() {

		super.onCreate();
		YouthManager.sContext = getApplicationContext();
		singleton = this;

	}
	
	

	

}
