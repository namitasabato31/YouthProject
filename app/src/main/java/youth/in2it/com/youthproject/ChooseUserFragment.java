package youth.in2it.com.youthproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by IN2IT on 11/18/2015.
 */
public class ChooseUserFragment extends Fragment {
    ListView listView;
    Button myButton;
    MyCustomAdapter dataAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.choose_user_fragment, container, false);
        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("Choose Users");

         listView = (ListView)rootView.findViewById(R.id.listView1);
         myButton = (Button) rootView.findViewById(R.id.findSelected);
        displayListView();
        checkButtonClick();

        return rootView;



    }

    private void hello(){

    }

    private void checkButtonClick() {



        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("The following were selected...\n");

                ArrayList<UserData> countryList = dataAdapter.countryList;
                for (int i = 0; i < countryList.size(); i++) {
                    UserData country = countryList.get(i);
                    if (country.isSelected()) {
                        responseText.append("\n" + country.getName());
                    }
                }

                Toast.makeText(getActivity(),
                        responseText, Toast.LENGTH_LONG).show();



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
        country = new UserData("User7",false);
        countryList.add(country);
        country = new UserData("User8",false);
        countryList.add(country);

        //create an ArrayAdaptar from the String Array
        dataAdapter = new MyCustomAdapter(getActivity(),
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
                LayoutInflater vi = (LayoutInflater)getActivity().getSystemService(
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
