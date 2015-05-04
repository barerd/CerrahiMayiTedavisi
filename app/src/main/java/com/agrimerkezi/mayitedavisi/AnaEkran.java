package com.agrimerkezi.mayitedavisi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

import java.util.Locale;

public class AnaEkran extends ActionBarActivity {
    public final static String SAATLIK_IDAME = "com.agrimerkezi.mayitedavisi.iSaatlikIdame";
    public final static String SIVI_ACIGI = "com.agrimerkezi.mayitedavisi.iSiviAcigi";
    public final static String SAATLIK_KACAK = "com.agrimerkezi.mayitedavisi.iSaatlikKacak";
    public final static String OPERASYON_BASLANGICI = "com.agrimerkezi.mayitedavisi.iOpBaslangic";

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    public void hesapla(View v) {

//        Yazılanların seçimi
        EditText eBoy = (EditText) findViewById(R.id.boy);
        EditText eKilo = (EditText) findViewById(R.id.kilo);
        EditText eOpBaslangic = (EditText) findViewById(R.id.op_baslangic);
        EditText eSonYemek = (EditText) findViewById(R.id.son_yemek);

//        Cinsiyet seçimi
        Switch swCinsiyet = (Switch) findViewById(R.id.cins);

//        Turnike seçimi
        Switch swTurnike = (Switch) findViewById(R.id.turnike);

//        Ameliyat türü seçimi
        RadioButton artroskopi = (RadioButton) findViewById(R.id.artroskopi);
        RadioButton arif = (RadioButton) findViewById(R.id.arif);
        RadioButton parsiyelProtez = (RadioButton) findViewById(R.id.parsiyel_protez);
        RadioButton totalProtez = (RadioButton) findViewById(R.id.total_protez);
        RadioButton revizyonEnfekteProtez = (RadioButton) findViewById(R.id.revizyon_enfekte_protez);

        int iBoy = 0;
        if(eBoy.getText().toString().length() == 0)
            eBoy.setError( "Hastanın boyunu girin!" );
        else iBoy = Integer.parseInt(eBoy.getText().toString());

        int iKilo = 0;
        if(eKilo.getText().toString().length() == 0)
            eKilo.setError( "Hastanın kilosunu girin!" );
        else iKilo = Integer.parseInt(eKilo.getText().toString());

        int iOpBaslangic = 0;
        if(eOpBaslangic.getText().toString().length() != 4)
            eOpBaslangic.setError( "Operasyonun başlangıç saatini '0835' şeklinde girin!" );
        else iOpBaslangic = Integer.parseInt(eOpBaslangic.getText().toString());

        int iSonYemek = 0;
        if(eSonYemek.getText().toString().length() != 4)
            eSonYemek.setError( "Hastanın en son yemek yediği saati '2130' şeklinde girin!" );
        else iSonYemek = Integer.parseInt(eSonYemek.getText().toString());

        int iIdealKilo;
        if(swCinsiyet.isChecked()) {
            iIdealKilo = (int) Math.round(50 + ((iBoy / 2.54 - 60) * 2.3));
        } else {
            iIdealKilo = (int) Math.round(45.5 + ((iBoy / 2.54 - 60) * 2.3));
        }

        int iSaatlikIdame = 0;
        if(iKilo <= 10) {
            iSaatlikIdame = iKilo * 4;
        } else if(iKilo <= 20) {
            iSaatlikIdame = 40 + 2 * (iKilo - 10);
        } else if(iKilo > 20) {
            iSaatlikIdame = iKilo + 40;
        }

        int iSiviAcigi = iSaatlikIdame * (int) Math.round((2400 - iSonYemek + iOpBaslangic) / 100) +
                (int) Math.round((((2400 - iSonYemek + iOpBaslangic) % 100) / 60));

        int iSaatlikKacak = 0;
        if(swTurnike.isChecked() || artroskopi.isChecked()){
            iSaatlikKacak = 0;
        } else if(arif.isChecked()) {
            iSaatlikKacak = 2 * iIdealKilo;
        } else if(parsiyelProtez.isChecked() || totalProtez.isChecked() || revizyonEnfekteProtez.isChecked()) {
            iSaatlikKacak = 3 * iIdealKilo;
        }

        if (eBoy.getText().toString().length() > 0 &&
                eKilo.getText().toString().length() > 0 &&
                eOpBaslangic.getText().toString().length() == 4 &&
                eSonYemek.getText().toString().length() == 4) {
            Intent intent = new Intent(AnaEkran.this, MayiTablosu.class);
            intent.putExtra(SAATLIK_IDAME, iSaatlikIdame);
            intent.putExtra(SIVI_ACIGI, iSiviAcigi);
            intent.putExtra(SAATLIK_KACAK, iSaatlikKacak);
            intent.putExtra(OPERASYON_BASLANGICI, iOpBaslangic);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_ekran);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ana_ekran, menu);
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
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return getString(R.string.app_name).toUpperCase(l);
            case 1:
                return getString(R.string.app_name).toUpperCase(l);
            case 2:
                return getString(R.string.app_name).toUpperCase(l);
        }
        return null;
    }
}

/**
 * A placeholder fragment containing a simple view.
 */
public static class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ana_ekran, container, false);
    }
}

}
