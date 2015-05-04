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
    public final static String BOY = "com.agrimerkezi.mayitedavisi.boy";
    public final static String KILO = "com.agrimerkezi.mayitedavisi.kilo";
    public final static String OPERASYON_BASLANGICI = "com.agrimerkezi.mayitedavisi.opBaslangic";
    public final static String SON_YEMEK_SAATI = "com.agrimerkezi.mayitedavisi.sonYemek";
    public final static String CINSIYET = "com.agrimerkezi.mayitedavisi.cins";
    public final static String TURNIKE = "com.agrimerkezi.mayitedavisi.turnike";
    public final static String AMELIYAT_TURU = "com.agrimerkezi.mayitedavisi.ameliyat";

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
        final String sBoy = eBoy.getText().toString();
        EditText eKilo = (EditText) findViewById(R.id.kilo);
        final String sKilo = eKilo.getText().toString();
        EditText eOpBaslangic = (EditText) findViewById(R.id.op_baslangic);
        final String sOpBaslangic = eOpBaslangic.getText().toString();
        EditText eSonYemek = (EditText) findViewById(R.id.son_yemek);
        final String sSonYemek = eSonYemek.getText().toString();

//        Cinsiyet seçimi
        Switch swCinsiyet = (Switch) findViewById(R.id.cins);
        String sCinsiyet;

        if(swCinsiyet.isChecked()){
            sCinsiyet = "Erkek";
        } else {
            sCinsiyet = "Kadın";
        }

//        Turnike seçimi
        Switch swTurnike = (Switch) findViewById(R.id.turnike);
        String sTurnike;
        if(swTurnike.isChecked()){
            sTurnike = "Turnike Var";
        } else {
            sTurnike = "Turnike Yok";
        }

//        Ameliyat türü seçimi
        RadioButton artroskopi = (RadioButton) findViewById(R.id.artroskopi);
        RadioButton arif = (RadioButton) findViewById(R.id.arif);
        RadioButton parsiyel_protez = (RadioButton) findViewById(R.id.parsiyel_protez);
        RadioButton total_protez = (RadioButton) findViewById(R.id.total_protez);
        RadioButton revizyon_enfekte_protez = (RadioButton) findViewById(R.id.revizyon_enfekte_protez);
        String sAmeliyat = null;

        if(artroskopi.isChecked()) {
            sAmeliyat = "Artroskopi";
        } else if(arif.isChecked()) {
            sAmeliyat = "ARİF";
        } else if(parsiyel_protez.isChecked()) {
            sAmeliyat = "Parsiyel protez";
        } else if(total_protez.isChecked()) {
            sAmeliyat = "Total protez";
        } else if(revizyon_enfekte_protez.isChecked()) {
            sAmeliyat = "Revizyon/Enfekte protez";
        }

        Intent intent = new Intent(AnaEkran.this, MayiTablosu.class);
        intent.putExtra(BOY, sBoy);
        intent.putExtra(KILO, sKilo);
        intent.putExtra(OPERASYON_BASLANGICI, sOpBaslangic);
        intent.putExtra(SON_YEMEK_SAATI, sSonYemek);
        intent.putExtra(CINSIYET, sCinsiyet);
        intent.putExtra(TURNIKE, sTurnike);
        intent.putExtra(AMELIYAT_TURU, sAmeliyat);
        startActivity(intent);
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
