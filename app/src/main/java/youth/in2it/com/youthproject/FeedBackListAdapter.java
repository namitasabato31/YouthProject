package youth.in2it.com.youthproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by IN2IT on 11/19/2015.
 */
public class FeedBackListAdapter extends BaseAdapter {
    Context c;
    public FeedBackListAdapter(Context c) {
        this.c=c;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
View view;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = convertView;
        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.feedback_list_item,parent,false);

        }
        return view;
    }
}
