package youth.in2it.com.youthproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends BaseAdapter {
    Context context;
    ArrayList arrayList = new ArrayList();
    public MyRecyclerViewAdapter(Context context) {
        this.context = context;
        arrayList.add("Are the coaches required to take a concussion education and training course?");
        arrayList.add("Does the league have a general policy in how they manage concussions?");
        arrayList.add("What is the purpose of youth sports?");
        arrayList.add("Do youth sports do enough to protect the health and safety of youth?");
        arrayList.add("Is winning everything? Or does everyone deserve a trophy?");
        arrayList.add("What is ethical coaching?");

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return "";
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
View view;
    TextView textView1;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        view = convertView;
        if(view == null){

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.content_sign_in,parent,false);
            textView1 = (TextView) view.findViewById(R.id.tv_query);
            textView1.setText(arrayList.get(position).toString());

            TextView textView = (TextView) view.findViewById(R.id.tv_showmore);
textView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
String value="";
if(position == 0 || position == 2){
    value ="image";

}else if(position == 1 || position == 3){
    value = "video";
}else{
    value = "files";
}

        Intent intent = new Intent(context,FeedbackActivity.class);


        Bundle bundle = new Bundle();
        bundle.putString("Value",value);
        bundle.putString("query",textView1.getText().toString());
        intent.putExtra("bundle",bundle);
       context.startActivity(intent);
    }
});

        }





        return view;
    }
}