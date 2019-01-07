package com.example.ka.storagebonhotrongngoai;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText etInputText;
    private TextView tvResult;

    private Button btExternal, btSaveExternal, btReadExternal;

    private final String INTERNAL_FILE_NAME = "intermal.dat";
    private final String EXTERNAL_FILE_NAME = "extermal.dat";

    private final int REQUEST_CODE = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etInputText = (EditText)findViewById(R.id.etInputText);
        tvResult = (TextView)findViewById(R.id.tvResult);

        btExternal = (Button)findViewById(R.id.btExternal);
        btSaveExternal = (Button)findViewById(R.id.btSaveExternal);
        btReadExternal = (Button)findViewById(R.id.btReadExternal);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                ||ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED);{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        }



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE:
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

                }
                else if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
                }
                else{
                    btExternal.setEnabled(false);
                    btReadExternal.setEnabled(false);
                    btSaveExternal.setEnabled(false);
                }



        }
    }

    public void getInternalPath(View view){
        String path = "";
        path += "Files: " + getFilesDir().toString();
        path += "\nCaches: " + getCacheDir().toString();
        tvResult.setText(path);
    }
    public void SaveInternal(View view){
        FileOutputStream fos = null;
        try{
            String content = etInputText.getText().toString();
            fos = openFileOutput(INTERNAL_FILE_NAME, MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();

            etInputText.setText("");
        }
        catch(IOException ex){
            if(fos != null){
                try{
                    fos.close();
                }
                catch (Exception e){}
            }
        }
    }
    public void ReadInternal(View view){
        FileInputStream fis = null;
        try{
            fis = openFileInput(INTERNAL_FILE_NAME);
            String content = "";
            int ch = fis.read();
            while (ch != -1){
                content += (char)ch;
                ch = fis.read();
            }
            fis.close();
            tvResult.setText(content);

        }
        catch(IOException ex){
            if(fis != null){
                try{
                    fis.close();
                }
                catch (Exception e){}
            }
        }
    }
    public void getExternalPath(View view){
        String path = "";
        path += "Files: " + getExternalFilesDir(null).toString();
        path += "\nCache: " + getExternalCacheDir().toString();
        path += "\nSdcard: " + Environment.getExternalStorageDirectory().toString();
        path += "\nDownload: " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        path += "\nRingtone: " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES).toString();
        tvResult.setText(path);
    }

    public void SaveExternal(View view){
        FileOutputStream fos = null;
        String path =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()+ "/" + EXTERNAL_FILE_NAME;
        try{
            String content = etInputText.getText().toString();
            fos = new FileOutputStream(path);
            fos.write(content.getBytes());
            fos.close();

            etInputText.setText("");
        }
        catch(IOException ex){
            if(fos != null){
                try{
                    fos.close();
                }
                catch (Exception e){}
            }
        }
    }
    public void ReadExternal(View view){
        FileInputStream fis = null;
        String path =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()+ "/" + EXTERNAL_FILE_NAME;
        try{
            fis = new FileInputStream(path);
            String content = "";
            int ch = fis.read();
            while (ch != -1){
                content += (char)ch;
                ch = fis.read();
            }
            fis.close();
            tvResult.setText(content);

        }
        catch(IOException ex){
            if(fis != null){
                try{
                    fis.close();
                }
                catch (Exception e){}
            }
        }
    }
}
