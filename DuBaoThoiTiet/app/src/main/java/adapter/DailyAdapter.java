package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidapp.batru.dubaothoitiet.R;
import model.DailyItem;
import model.SingletonClass;

/**
 * Created by hoangkhoa on 6/3/17.
 */

public class DailyAdapter extends ArrayAdapter<DailyItem>{
    public DailyAdapter(Context context, int resourceId) {
        super(context, resourceId);
    }

    public DailyAdapter(Context context, int resourceId, ArrayList<DailyItem> items) {
        super(context, resourceId, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater v = LayoutInflater.from(getContext());
            convertView = v.inflate(R.layout.item_listview, null);
        }

        DailyItem item = getItem(position);
        if (item != null) {
            TextView tvDayOfWeek = (TextView) convertView.findViewById(R.id.dayOfWeekTextItem);
            TextView tvDateTime = (TextView) convertView.findViewById(R.id.dateTextItem);
            TextView tvDecriptions = (TextView) convertView.findViewById(R.id.decriptionText);
            ImageView imgIcon = (ImageView) convertView.findViewById(R.id.imgDailyItem);
            TextView tvHumidity = (TextView) convertView.findViewById(R.id.item_humidity);
            TextView tvSpeed = (TextView) convertView.findViewById(R.id.itemSpeed);
            TextView tvMinDeg = (TextView) convertView.findViewById(R.id.itemMinDeg);
            TextView tvMaxDeg = (TextView) convertView.findViewById(R.id.itemMaxDeg);

            String date = SingletonClass.getInstance().ConvertUnixToTime(item.getTime(), "dd/MM");
            tvDateTime.setText(date);

            tvDayOfWeek.setText(item.getDayOfWeek());
            tvDecriptions.setText(item.getDetail());
            imgIcon.setImageResource(item.getImageId());
            tvHumidity.setText("Humidity: " + item.getHumidity() + "%");
            tvSpeed.setText("Speed: " + item.getSpeed() + " m/s");
            tvMinDeg.setText("Min: " + item.getMinDeg() + "°C");
            tvMaxDeg.setText("Max: " + item.getMaxDeg() + "°C");
        }

        return convertView;
    }
}
