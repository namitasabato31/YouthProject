package youth.in2it.com.youthproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FeedbackActivity extends ActionBarActivity {
    private TextView textview,tvQuery;
    String value,query;
    RelativeLayout relImage,relVideo;
    Context context = this;
    String userType="";
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_feedback);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setTitle("Feedback");

        actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.actionbar));

        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.custom_actionbar_view,
                null);
        ImageView imageView = (ImageView) mCustomView
                .findViewById(R.id.img_back);
        actionBar.setCustomView(mCustomView);
        actionBar.setDisplayShowCustomEnabled(true);
        Toolbar parent = (Toolbar) mCustomView.getParent();// first get parent
        parent.setContentInsetsAbsolute(0, 0);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedbackActivity.this.finish();
                ;
            }
        });




        userType = YouthManager.getInstance().getmPreferenceData().getUsertype();
        Log.v("user type","=="+userType);

       ImageView imageView1 = (ImageView) findViewById(R.id.img_Send);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FeedbackActivity.this,"Feedback sent",Toast.LENGTH_LONG).show();
                FeedbackActivity.this.finish();
            }
        });
textview = (TextView) findViewById(R.id.tv_attachments);
        relImage = (RelativeLayout)findViewById(R.id.image_layout);
        relVideo = (RelativeLayout)findViewById(R.id.rel_add_video);
        tvQuery = (TextView) findViewById(R.id.tv_query);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        if(bundle != null){

            value = bundle.getString("Value");
query = bundle.getString("query");
            tvQuery.setText(query);
  }

        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value.equals("image")){
                    relImage.setVisibility(View.VISIBLE);
                    relVideo.setVisibility(View.GONE);

                }else if(value.equals("video")){
                    relImage.setVisibility(View.GONE);
                    relVideo.setVisibility(View.VISIBLE);

                }else if(value.equals("files")){

                }
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        MenuItem menuItem = menu.findItem(R.id.action_settings) ;
        MenuItem menuItem1 = menu.findItem(R.id.action_feedback) ;

        if(userType.equals("admin")){

            menuItem.setVisible(true);
            menuItem1.setVisible(true);
        }else{
            menuItem.setVisible(false);
            menuItem1.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (userType.equals("admin")) {


        //noinspection SimplifiableIfStatement
        if (id == R.id.add_lock) {
            Toast.makeText(FeedbackActivity.this, "Topic locked", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.add_delete) {
            Toast.makeText(FeedbackActivity.this, "Topic deleted successfully", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.remove_users) {
            context.startActivity(new Intent(context, RemoveUSersActivity.class));
        }else if(id == R.id.action_feedback){
            context.startActivity(new Intent(context, UsersFeedbackActivity.class));

        }
    }

        return super.onOptionsItemSelected(item);
    }


}
