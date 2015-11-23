package youth.in2it.com.youthproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

/**
 * Created by IN2IT on 11/19/2015.
 */
public class ServerPingAsyncTask  extends AsyncTask<String,String,String>{
    GoogleCloudMessaging gcmObj;
    public static  String senderid="869201682034";
    Context context;
    String regId ;
    ProgressDialog progressDialog;
    public ServerPingAsyncTask(Context context) {
        this.context = context;
        progressDialog = new ProgressDialog(context);
    }

    public boolean isConnectingToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }
        return false;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("loading..");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String msg = "";
        if (isConnectingToInternet()) {

        try {
            if (gcmObj == null) {
                gcmObj = GoogleCloudMessaging
                        .getInstance(context);
            }
            regId = gcmObj
                    .register(senderid);
            Log.v("regId",""+regId);
            if (regId != null) {
                Log.v("regId",""+regId);
                YouthManager.getInstance().getmPreferenceData().setRegistrationId(regId);


            }
            msg = "success";

        } catch (IOException ex) {
            msg = "Error :" + ex.getMessage();
        }
    }else {
            msg="No Network connection";
        }


        return msg;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if(progressDialog.isShowing()){
            progressDialog.cancel();
        }
        SplashScreenActivity.updateUI(result);
    }
}
