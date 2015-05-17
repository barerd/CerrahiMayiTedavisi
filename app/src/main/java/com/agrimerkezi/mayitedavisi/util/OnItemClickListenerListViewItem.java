package com.agrimerkezi.mayitedavisi.util;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.agrimerkezi.mayitedavisi.MayiTablosu;
import com.agrimerkezi.mayitedavisi.R;

public abstract class OnItemClickListenerListViewItem extends Context implements AdapterView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        final Context context = view.getContext();

        // tıklanan saat numarasını al
        TextView numara = (TextView)view.findViewById(R.id.no);
        String sNumara = numara.getText().toString();

        final Dialog idrarKanamaGir = new Dialog(context);
        idrarKanamaGir.setContentView(R.layout.idrar_kanama_gir);
        idrarKanamaGir.setTitle(sNumara + ". saatteki idrar ve kanama miktarı");

        // set the custom dialog components - text and button
        EditText idrar = (EditText) idrarKanamaGir.findViewById(R.id.idrar);
        EditText kanama = (EditText) idrarKanamaGir.findViewById(R.id.kanama);

        Button hesaplaTusu = (Button) idrarKanamaGir.findViewById(R.id.idrar_kanama_tusu);
        // if button is clicked, close the custom dialog
        hesaplaTusu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MayiTablosu.class);
                context.startActivity(i);
                idrarKanamaGir.dismiss();
            }
        });

        idrarKanamaGir.show();
    }
}