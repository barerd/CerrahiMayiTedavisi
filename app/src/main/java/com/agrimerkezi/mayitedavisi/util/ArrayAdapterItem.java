package com.agrimerkezi.mayitedavisi.util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.agrimerkezi.mayitedavisi.R;

public class ArrayAdapterItem extends ArrayAdapter<SaatlikTakip> {

    Context mContext;
    int layoutResourceId;
    SaatlikTakip data[] = null;

    public ArrayAdapterItem(Context mContext, int layoutResourceId, SaatlikTakip[] data) {

        super(mContext, layoutResourceId, data);

        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /*
         * The convertView argument is essentially a "ScrapView" as described is Lucas post
         * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
         * It will have a non-null value when ListView is asking you recycle the row layout.
         * So, when convertView is not null, you should simply update its contents instead of inflating a new row layout.
         */
        if(convertView==null){
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        // object item based on the position
        SaatlikTakip saatlikTakipler = data[position];

        // get the TextView and then set the text (item name) and tag (item ID) values
        TextView numara = (TextView) convertView.findViewById(R.id.no);
        numara.setText(Integer.toString(saatlikTakipler.no));
        TextView saatler = (TextView) convertView.findViewById(R.id.saatAraligi);
        saatler.setText(saatlikTakipler.saatAraligi);
        TextView izotonik = (TextView) convertView.findViewById(R.id.kristalloid);
        izotonik.setText(Integer.toString(saatlikTakipler.kristalloid));
        TextView voluven = (TextView) convertView.findViewById(R.id.kolloid);
        voluven.setText(Integer.toString(saatlikTakipler.kolloid));

        return convertView;
    }
}