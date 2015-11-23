package youth.in2it.com.youthproject;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.ListView;

public class UsersFeedbackActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_feedback);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Feedback");
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.actionbar));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        ListView  listView = (ListView) findViewById(R.id.listView_feedback);
        listView.setAdapter(new FeedBackListAdapter(UsersFeedbackActivity.this));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id == android.R.id.home){
            UsersFeedbackActivity.this.finish();
        }

        return super.onOptionsItemSelected(item);
    }


}
