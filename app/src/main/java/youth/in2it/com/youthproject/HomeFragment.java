package youth.in2it.com.youthproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by IN2IT on 11/17/2015.
 */
public class HomeFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.content_test, container, false);
        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("QA forum");
        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(new MyRecyclerViewAdapter(getActivity()
        ));
        return rootView;



    }
}
