package com.agrimerkezi.mayitedavisi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
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

    public void hesapla(View v) {

//      Yazılanların seçimi
        final EditText eBoy = (EditText) findViewById(R.id.boy);
        final EditText eKilo = (EditText) findViewById(R.id.kilo);
        final EditText eOpBaslangic = (EditText) findViewById(R.id.op_baslangic);
        final EditText eSonYemek = (EditText) findViewById(R.id.son_yemek);

//      Cinsiyet seçimi
        final Switch swCinsiyet = (Switch) findViewById(R.id.cins);

//      Turnike seçimi
        final Switch swTurnike = (Switch) findViewById(R.id.turnike);

//      Ameliyat türü seçimi
        final RadioButton artroskopi = (RadioButton) findViewById(R.id.artroskopi);
        final RadioButton arif = (RadioButton) findViewById(R.id.arif);
        final RadioButton parsiyelProtez = (RadioButton) findViewById(R.id.parsiyel_protez);
        final RadioButton totalProtez = (RadioButton) findViewById(R.id.total_protez);
        final RadioButton revizyonEnfekteProtez = (RadioButton) findViewById(R.id.revizyon_enfekte_protez);

        int iBoy = 0;
        int iKilo = 0;
        int iOpBaslangic = 0;
        int iSonYemek = 0;

        if (eBoy.getText().toString().length() == 0){
            eBoy.setError("Hastanın boyunu girin!");
        } else {
            iBoy = Integer.parseInt(eBoy.getText().toString());
            if (eKilo.getText().toString().length() == 0) {
                eKilo.setError("Hastanın kilosunu girin!");
            } else {
                iKilo = Integer.parseInt(eKilo.getText().toString());
                if (eOpBaslangic.getText().toString().length() != 4) {
                    eOpBaslangic.setError("Operasyonun başlangıç saatini '0835' şeklinde girin!");
                } else if(Integer.parseInt(eOpBaslangic.getText().toString()) > 2359) {
                    eOpBaslangic.setError("Saati 00:00 ile 23:59 aralığında olacak şekilde girin!");
                } else if((Integer.parseInt(eOpBaslangic.getText().toString()) % 100) > 59) {
                    eOpBaslangic.setError("Dakika 0 ile 59 aralığında olmalıdır!");
                } else {
                    iOpBaslangic = Integer.parseInt(eOpBaslangic.getText().toString());
                    if (eSonYemek.getText().toString().length() != 4) {
                        eSonYemek.setError("Hastanın en son yemek yediği saati '2130' şeklinde girin!");
                    } else if(Integer.parseInt(eSonYemek.getText().toString()) > 2359) {
                        eSonYemek.setError("Saati 00:00 ile 23:59 aralığında olacak şekilde girin!");
                    } else if((Integer.parseInt(eSonYemek.getText().toString()) % 100) > 59) {
                        eSonYemek.setError("Dakika 0 ile 59 aralığında olmalıdır!");
                    } else {
                        iSonYemek = Integer.parseInt(eSonYemek.getText().toString());

                        final int finalIBoy = iBoy;
                        final int finalIKilo = iKilo;
                        final int finalISonYemek = iSonYemek;
                        final int finalIOpBaslangic = iOpBaslangic;

                        new Thread(new Runnable() {
                            public void run(){

                                int iIdealKilo;
                                if (swCinsiyet.isChecked()) {
                                    iIdealKilo = (int) Math.round(50 + ((finalIBoy / 2.54 - 60) * 2.3));
                                } else {
                                    iIdealKilo = (int) Math.round(45.5 + ((finalIBoy / 2.54 - 60) * 2.3));
                                }

                                int iSaatlikIdame = 0;
                                if (finalIKilo <= 10) {
                                    iSaatlikIdame = finalIKilo * 4;
                                } else if (finalIKilo <= 20) {
                                    iSaatlikIdame = 40 + 2 * (finalIKilo - 10);
                                } else if (finalIKilo > 20) {
                                    iSaatlikIdame = finalIKilo + 40;
                                }

                                int iSiviAcigi = iSaatlikIdame * (int) Math.round((2400 - finalISonYemek + finalIOpBaslangic) / 100) +
                                        (int) Math.round((((2400 - finalISonYemek + finalIOpBaslangic) % 100) / 60));

                                int iSaatlikKacak = 0;
                                if (swTurnike.isChecked() || artroskopi.isChecked()) {
                                    iSaatlikKacak = 0;
                                } else if (arif.isChecked()) {
                                    iSaatlikKacak = 2 * iIdealKilo;
                                } else if (parsiyelProtez.isChecked() || totalProtez.isChecked() || revizyonEnfekteProtez.isChecked()) {
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
                                    intent.putExtra(OPERASYON_BASLANGICI, finalIOpBaslangic);
                                    startActivity(intent);
                                }
                            }
                        }).start();
                    }
                }
            }
        }
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
        return 1;
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