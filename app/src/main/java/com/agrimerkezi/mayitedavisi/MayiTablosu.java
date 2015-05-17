package com.agrimerkezi.mayitedavisi;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.agrimerkezi.mayitedavisi.util.ArrayAdapterItem;
import com.agrimerkezi.mayitedavisi.util.OnItemClickListenerListViewItem;
import com.agrimerkezi.mayitedavisi.util.SaatlikTakip;

public class MayiTablosu extends ActionBarActivity {

    public AlertDialog alertDialogStores;
    private int kanama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mayi_tablosu);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        Intent intent = getIntent();

        int iSaatlikIdame = intent.getIntExtra(AnaEkran.SAATLIK_IDAME, 0);
        int iSiviAcigi = intent.getIntExtra(AnaEkran.SIVI_ACIGI, 0);
        int iSaatlikKacak = intent.getIntExtra(AnaEkran.SAATLIK_KACAK, 0);
        int iOpBaslangic = intent.getIntExtra(AnaEkran.OPERASYON_BASLANGICI, 0);

        SaatlikTakip[] saatlikTakipler = new SaatlikTakip[3];

        saatlikTakipler[0] = takibiHesapla(1, iSaatlikIdame, iSiviAcigi, iSaatlikKacak, iOpBaslangic, 0, 0);
        saatlikTakipler[1] = takibiHesapla(2, iSaatlikIdame, iSiviAcigi, iSaatlikKacak, iOpBaslangic, 0, 0);
        saatlikTakipler[2] = takibiHesapla(3, iSaatlikIdame, iSiviAcigi, iSaatlikKacak, iOpBaslangic, 0, 0);

        showPopUp(saatlikTakipler);
    }

    protected void showPopUp(SaatlikTakip[] saatlikTakipler){

        // our adapter instance
        ArrayAdapterItem adapter = new ArrayAdapterItem(this, R.layout.list_view_row_item, saatlikTakipler);

        // create a new ListView, set the adapter and item click listener
        ListView takipSaatleri = new ListView(this);
        takipSaatleri.setAdapter(adapter);
        takipSaatleri.setOnItemClickListener(new OnItemClickListenerListViewItem());

        // put the ListView in the pop up
        alertDialogStores = new AlertDialog.Builder(MayiTablosu.this)
                .setView(takipSaatleri)
                .setTitle("Saat            | Kristalloid | Kolloid")
                .show();
    }

    private SaatlikTakip takibiHesapla(int saatNo, int iSaatlikIdame, int iSiviAcigi, int iSaatlikKacak, int iOpBaslangic, int idrar, int kanama) {
        this.kanama = kanama;
        int iSaat = (int) Math.round(Math.floor(iOpBaslangic / 100));
        int iDakika = iOpBaslangic % 100;

//        int hours = (int) new Time(System.currentTimeMillis()).getTime();

        if(saatNo == 1) {
            return new SaatlikTakip(saatNo,
                    saatDakikaIkiHaneYap(iSaat) + ":" + saatDakikaIkiHaneYap(iDakika) + "-" + saatDakikaIkiHaneYap(iSaat + 1) + ":" + saatDakikaIkiHaneYap(iDakika),
                    ((iSiviAcigi / 2) + iSaatlikIdame + iSaatlikKacak), 0);
        } else if(saatNo == 2 || saatNo == 3) {
            return new SaatlikTakip(saatNo,
                    saatDakikaIkiHaneYap(iSaat + saatNo - 1) + ":" + saatDakikaIkiHaneYap(iDakika) + "-" + saatDakikaIkiHaneYap(iSaat + saatNo) + ":" + saatDakikaIkiHaneYap(iDakika),
                    ((iSiviAcigi / 4) + iSaatlikIdame + iSaatlikKacak + idrar), 0);
        } else {
            return new SaatlikTakip(saatNo,
                    saatDakikaIkiHaneYap(iSaat + saatNo - 1) + ":" + saatDakikaIkiHaneYap(iDakika) + "-" + saatDakikaIkiHaneYap(iSaat + saatNo) + ":" + saatDakikaIkiHaneYap(iDakika),
                    (iSaatlikIdame + iSaatlikKacak + idrar), 0);
        }
    }

    public String saatDakikaIkiHaneYap(int saatVeyaDakika) {
        String sZaman;

        if(saatVeyaDakika < 10){
            sZaman = "0" + Integer.toString(saatVeyaDakika);
        } else {
            sZaman = Integer.toString(saatVeyaDakika);
        }
        return sZaman;
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
