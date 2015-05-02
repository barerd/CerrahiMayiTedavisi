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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

import java.util.Locale;

public class AnaEkran extends ActionBarActivity {

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

    /** Fonksiyonlar */
/*      EditText boy_text = (EditText) findViewById(R.id.boy);
        final int boy = Integer.parseInt(boy_text.getText().toString());
        EditText kilo_text = (EditText) findViewById(R.id.kilo);
        final int kilo = Integer.parseInt(kilo_text.getText().toString());
        EditText op_başlangıç_text = (EditText) findViewById(R.id.op_başlangıç);
        final int op_başlangıç = Integer.parseInt(op_başlangıç_text.getText().toString());
        EditText son_yemek_text = (EditText) findViewById(R.id.son_yemek);
        final int son_yemek = Integer.parseInt(son_yemek_text.getText().toString());

        Switch cinsiyet = (Switch) findViewById(R.id.cins);
        Switch turnikeli = (Switch) findViewById(R.id.turnike);

        RadioButton artroskopi = (RadioButton) findViewById(R.id.artroskopi);
        RadioButton arif = (RadioButton) findViewById(R.id.arif);
        RadioButton parsiyel_protez = (RadioButton) findViewById(R.id.parsiyel_protez);
        RadioButton total_protez = (RadioButton) findViewById(R.id.total_protez);
        RadioButton revizyon_enfekte_protez = (RadioButton) findViewById(R.id.revizyon_enfekte_protez);

        int kaçak;
        double adjBW;
        double kanHacmi;
        double idame;

        if(artroskopi.isChecked() || turnikeli.isChecked()) {
            kaçak = 0;
        } else if(arif.isChecked()) {
            kaçak = 2;
        } else {
            kaçak = 3;
        }

        double araHesap = (boy / 2.54 - 60) * 2.3;

        if(cinsiyet.isChecked()){
            adjBW = araHesap + 50;
        } else {
            adjBW = araHesap + 45.5;
        }

        if (cinsiyet.isChecked()) {
            kanHacmi = adjBW * 75;
        } else {
            kanHacmi = adjBW * 65;
        }

        if (adjBW <= 10) {
            idame = adjBW * 4;
        } else if (adjBW > 10 && adjBW <= 20) {
            idame = 40 + ((adjBW - 10) * 2);
        } else if (adjBW > 20) {
            idame = adjBW + 40;
        }
    }*/

    public void hesapla(View v) {
        Intent intent = new Intent(AnaEkran.this, MayiTablosu.class);
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
        View rootView = inflater.inflate(R.layout.fragment_ana_ekran, container, false);
        return rootView;
    }
}

}
