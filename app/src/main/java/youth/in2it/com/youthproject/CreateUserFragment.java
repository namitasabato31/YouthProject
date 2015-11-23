package youth.in2it.com.youthproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateUserFragment extends Fragment {
	Spinner spinner;
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.createuser_fragment, container, false);
		((HomeActivity) getActivity()).getSupportActionBar().setTitle("Create User");

		 spinner = (Spinner) rootView.findViewById(R.id.spinner_type_of_user);
		ImageView imageView = (ImageView) rootView.findViewById(R.id.spinner_type_of_user_helper);
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				spinner.performClick();
			}
		});
		Button btnCreateUSer = (Button) rootView.findViewById(R.id.btn_create_user);
		btnCreateUSer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(),"User Created Successfully.",Toast.LENGTH_LONG).show();
			}
		});

		addItemToYearSpinner();
		return rootView;



	}

	private void addItemToYearSpinner() {
		String[] stringYearArray = new String[]{"Select", "Student", "Faculty"};

		Log.v("stringYearArray=", "" + stringYearArray);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, stringYearArray);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);

	}

}
