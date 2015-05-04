package com.agrimerkezi.mayitedavisi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MayiTablosu extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_mayi_tablosu);

        Intent intent = getIntent();
        String sBoy = intent.getStringExtra(AnaEkran.BOY);
        String sKilo = intent.getStringExtra(AnaEkran.KILO);
        String sOp_baþlangýç = intent.getStringExtra(AnaEkran.OPERASYON_BASLANGICI);
        String sSon_yemek = intent.getStringExtra(AnaEkran.SON_YEMEK_SAATI);
        final String sCinsiyet = intent.getStringExtra(AnaEkran.CINSIYET);
        final String sTurnike = intent.getStringExtra(AnaEkran.TURNIKE);
        final String sAmeliyat = intent.getStringExtra(AnaEkran.AMELIYAT_TURU);
        final int iBoy = Integer.parseInt(sBoy);
        final int iKilo = Integer.parseInt(sKilo);
        final int iOp_baþlangýç = Integer.parseInt(sOp_baþlangýç);
        final int iSon_yemek = Integer.parseInt(sSon_yemek);

        TextView sonuç = new TextView(this);
        sonuç.setText(sAmeliyat);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    /*final int kilo = Integer.parseInt(kilo_text.getText().toString());
    *
    * if(cinsiyet.isChecked()){
            idealKilo = araHesap + 50;
        } else {
            idealKilo = araHesap + 45.5;
        }

        double idealKilo;

        double araHesap = (boy / 2.54 - 60) * 2.3;

        double açlýkSüresi;

        /*op_baþlangýç
        if(son_yemek)        son_yemek*/

    /*int kaçak;

    if(artroskopi.isChecked() || turnikeli.isChecked()) {
        kaçak = 0;
    } else if(arif.isChecked()) {
        kaçak = 2;
    } else {
        kaçak = 3;
    }

    double saatlikÝdame;

    if (idealKilo <= 10) {
        saatlikÝdame = idealKilo * 4;
    } else if (idealKilo > 10 && idealKilo <= 20) {
        saatlikÝdame = 40 + ((idealKilo - 10) * 2);
    } else if (idealKilo > 20) {
        saatlikÝdame = idealKilo + 40;
    }

    double sývýAçýðý;

    double kanHacmi;

    if (cinsiyet.isChecked()) {
        kanHacmi = idealKilo * 75;
    } else {
        kanHacmi = idealKilo * 65;
    }

    double cerrahiÝdame;*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mayi_tablosu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_mayi_tablosu, container, false);
        }
    }
}
