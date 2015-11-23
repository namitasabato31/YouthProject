package youth.in2it.com.youthproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RemoveUSersActivity extends ActionBarActivity {
    ListView listView;
    Button myButton;
    Context context = this;
    MyCustomAdapter dataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_users);
       ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Remove users");
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.actionbar));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        listView = (ListView)findViewById(R.id.list_removeusers);
        myButton = (Button) findViewById(R.id.remove_btn);
        displayListView();
        checkButtonClick();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
       if(id == android.R.id.home){
           RemoveUSersActivity.this.finish();
       }

        return super.onOptionsItemSelected(item);
    }

    private void checkButtonClick() {



        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("The following \n");

                ArrayList<UserData> countryList = dataAdapter.countryList;
                for (int i = 0; i < countryList.size(); i++) {
                    UserData country = countryList.get(i);
                    if (country.isSelected()) {
                        responseText.append("\n" + country.getName());
                    }
                }

                Toast.makeText(context,
                        responseText+" users removed", Toast.LENGTH_LONG).show();

                RemoveUSersActivity.this.finish();



            }
        });

    }

    private void displayListView() {

        //Array list of countries
        ArrayList<UserData> countryList = new ArrayList<UserData>();
        UserData country = new UserData("User1",false);
        countryList.add(country);
        country = new UserData("User2",false);
        countryList.add(country);
        country = new UserData("User3",false);
        countryList.add(country);
        country = new UserData("User5",false);
        countryList.add(country);
        country = new UserData("User6",false);
        countryList.add(country);

        //create an ArrayAdaptar from the String Array
        dataAdapter = new MyCustomAdapter(context,
                R.layout.user_list_item, countryList);

        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                UserData country = (UserData) parent.getItemAtPosition(position);
                //  Toast.makeText(getActivity(),
                //          "Clicked on Row: " + country.getName(),
                //         Toast.LENGTH_LONG).show();
            }
        });

    }


    private class MyCustomAdapter extends ArrayAdapter<UserData> {

        private ArrayList<UserData> countryList;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<UserData> countryList) {
            super(context, textViewResourceId, countryList);
            this.countryList = new ArrayList<UserData>();
            this.countryList.addAll(countryList);
        }

        private class ViewHolder {

            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.user_list_item, null);

                holder = new ViewHolder();

                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);

                holder.name.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;
                        UserData country = (UserData) cb.getTag();

                        country.setSelected(cb.isChecked());
                    }
                });
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            UserData country = countryList.get(position);

            holder.name.setText(country.getName());
            holder.name.setChecked(country.isSelected());
            holder.name.setTag(country);

            return convertView;

        }

    }

}
