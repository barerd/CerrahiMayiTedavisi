package com.agrimerkezi.mayitedavisi;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.agrimerkezi.mayitedavisi.util.ArrayAdapterItem;
import com.agrimerkezi.mayitedavisi.util.OnItemClickListenerListViewItem;
import com.agrimerkezi.mayitedavisi.util.SaatlikTakip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MayiTablosu extends ActionBarActivity {

    public AlertDialog alertDialogStores;
    public int kanama;

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
        takipSaatleri.setOnItemClickListener(new OnItemClickListenerListViewItem() {
            public AssetManager getAssets() {
                return null;
            }

            public Resources getResources() {
                return null;
            }

            public PackageManager getPackageManager() {
                return null;
            }

            public ContentResolver getContentResolver() {
                return null;
            }

            public Looper getMainLooper() {
                return null;
            }

            public Context getApplicationContext() {
                return null;
            }

            public void setTheme(int resid) {

            }

            public Resources.Theme getTheme() {
                return null;
            }

            public ClassLoader getClassLoader() {
                return null;
            }

            public String getPackageName() {
                return null;
            }

            public ApplicationInfo getApplicationInfo() {
                return null;
            }

            public String getPackageResourcePath() {
                return null;
            }

            public String getPackageCodePath() {
                return null;
            }

            public SharedPreferences getSharedPreferences(String name, int mode) {
                return null;
            }

            public FileInputStream openFileInput(String name) throws FileNotFoundException {
                return null;
            }

            public FileOutputStream openFileOutput(String name, int mode) throws FileNotFoundException {
                return null;
            }

            public boolean deleteFile(String name) {
                return false;
            }

            public File getFileStreamPath(String name) {
                return null;
            }

            public File getFilesDir() {
                return null;
            }

            public File getNoBackupFilesDir() {
                return null;
            }

            @Nullable
            public File getExternalFilesDir(String type) {
                return null;
            }

            public File[] getExternalFilesDirs(String type) {
                return new File[0];
            }

            public File getObbDir() {
                return null;
            }

            public File[] getObbDirs() {
                return new File[0];
            }

            public File getCacheDir() {
                return null;
            }

            public File getCodeCacheDir() {
                return null;
            }

            @Nullable
            public File getExternalCacheDir() {
                return null;
            }

            public File[] getExternalCacheDirs() {
                return new File[0];
            }

            public File[] getExternalMediaDirs() {
                return new File[0];
            }

            public String[] fileList() {
                return new String[0];
            }

            public File getDir(String name, int mode) {
                return null;
            }

            public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
                return null;
            }

            public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
                return null;
            }

            public boolean deleteDatabase(String name) {
                return false;
            }

            public File getDatabasePath(String name) {
                return null;
            }

            public String[] databaseList() {
                return new String[0];
            }

            public Drawable getWallpaper() {
                return null;
            }

            public Drawable peekWallpaper() {
                return null;
            }

            public int getWallpaperDesiredMinimumWidth() {
                return 0;
            }

            public int getWallpaperDesiredMinimumHeight() {
                return 0;
            }

            public void setWallpaper(Bitmap bitmap) throws IOException {

            }

            public void setWallpaper(InputStream data) throws IOException {

            }

            public void clearWallpaper() throws IOException {

            }

            public void startActivity(Intent intent) {

            }

            public void startActivity(Intent intent, Bundle options) {

            }

            public void startActivities(Intent[] intents) {

            }

            public void startActivities(Intent[] intents, Bundle options) {

            }

            public void startIntentSender(IntentSender intent, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags) throws IntentSender.SendIntentException {

            }

            public void startIntentSender(IntentSender intent, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) throws IntentSender.SendIntentException {

            }

            public void sendBroadcast(Intent intent) {

            }

            public void sendBroadcast(Intent intent, String receiverPermission) {

            }

            public void sendOrderedBroadcast(Intent intent, String receiverPermission) {

            }

            public void sendOrderedBroadcast(Intent intent, String receiverPermission, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {

            }

            public void sendBroadcastAsUser(Intent intent, UserHandle user) {

            }

            public void sendBroadcastAsUser(Intent intent, UserHandle user, String receiverPermission) {

            }

            public void sendOrderedBroadcastAsUser(Intent intent, UserHandle user, String receiverPermission, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {

            }

            public void sendStickyBroadcast(Intent intent) {

            }

            public void sendStickyOrderedBroadcast(Intent intent, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {

            }

            public void removeStickyBroadcast(Intent intent) {

            }

            public void sendStickyBroadcastAsUser(Intent intent, UserHandle user) {

            }

            public void sendStickyOrderedBroadcastAsUser(Intent intent, UserHandle user, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {

            }

            public void removeStickyBroadcastAsUser(Intent intent, UserHandle user) {

            }

            @Nullable
            public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
                return null;
            }

            @Nullable
            public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter, String broadcastPermission, Handler scheduler) {
                return null;
            }

            public void unregisterReceiver(BroadcastReceiver receiver) {

            }

            @Nullable
            public ComponentName startService(Intent service) {
                return null;
            }

            public boolean stopService(Intent service) {
                return false;
            }

            public boolean bindService(Intent service, ServiceConnection conn, int flags) {
                return false;
            }

            public void unbindService(ServiceConnection conn) {

            }

            public boolean startInstrumentation(ComponentName className, String profileFile, Bundle arguments) {
                return false;
            }

            public Object getSystemService(String name) {
                return null;
            }

            public int checkPermission(String permission, int pid, int uid) {
                return PackageManager.PERMISSION_DENIED;
            }

            public int checkCallingPermission(String permission) {
                return PackageManager.PERMISSION_DENIED;
            }

            public int checkCallingOrSelfPermission(String permission) {
                return PackageManager.PERMISSION_DENIED;
            }

            public void enforcePermission(String permission, int pid, int uid, String message) {

            }

            public void enforceCallingPermission(String permission, String message) {

            }

            public void enforceCallingOrSelfPermission(String permission, String message) {

            }

            public void grantUriPermission(String toPackage, Uri uri, int modeFlags) {

            }

            public void revokeUriPermission(Uri uri, int modeFlags) {

            }

            public int checkUriPermission(Uri uri, int pid, int uid, int modeFlags) {
                return 0;
            }

            public int checkCallingUriPermission(Uri uri, int modeFlags) {
                return 0;
            }

            public int checkCallingOrSelfUriPermission(Uri uri, int modeFlags) {
                return 0;
            }

            public int checkUriPermission(Uri uri, String readPermission, String writePermission, int pid, int uid, int modeFlags) {
                return 0;
            }

            public void enforceUriPermission(Uri uri, int pid, int uid, int modeFlags, String message) {

            }

            public void enforceCallingUriPermission(Uri uri, int modeFlags, String message) {

            }

            public void enforceCallingOrSelfUriPermission(Uri uri, int modeFlags, String message) {

            }

            public void enforceUriPermission(Uri uri, String readPermission, String writePermission, int pid, int uid, int modeFlags, String message) {

            }

            public Context createPackageContext(String packageName, int flags) throws PackageManager.NameNotFoundException {
                return null;
            }

            public Context createConfigurationContext(Configuration overrideConfiguration) {
                return null;
            }

            public Context createDisplayContext(Display display) {
                return null;
            }
        });

        // put the ListView in the pop up
        alertDialogStores = new AlertDialog.Builder(MayiTablosu.this)
                .setView(takipSaatleri)
                .setTitle("Saat              Kristalloid   Kolloid")
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
