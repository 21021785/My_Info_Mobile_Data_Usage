package sg.edu.rp.c346.id21021785.my_info_mobile_data_usage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<MobileData> dataList;

    public CustomAdapter(Context context, int resource, ArrayList<MobileData> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        dataList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvQuarter = rowView.findViewById(R.id.quarter);
        TextView tvVolume = rowView.findViewById(R.id.volume);

        // Obtain the Android Version information based on the position
        MobileData currentVersion = dataList.get(position);

        // Set values to the TextView to display the corresponding information
        tvQuarter.setText(currentVersion.getQuarter());
        double currentVersionDouble = Double.parseDouble(currentVersion.getVolume());
        double newCurrentVersionDouble = currentVersionDouble * 1000;
        tvVolume.setText(String.format("%.3f Terabytes", newCurrentVersionDouble));

        return rowView;
    }
}
