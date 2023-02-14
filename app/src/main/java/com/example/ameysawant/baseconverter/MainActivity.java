package com.example.ameysawant.baseconverter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.style.TtsSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    private EditText d;
    private EditText b;
    private EditText hd;
    private EditText o;

    private String dec,bin, hex, oct;
    private String decString,binString,hexString,octString;

    private GoogleApiClient client;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.layout1).requestFocus();
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        d = (EditText) findViewById(R.id.decimal);
        b = (EditText) findViewById(R.id.binary);
        hd = (EditText) findViewById(R.id.hexadecimal);
        o = (EditText) findViewById(R.id.octal);


        //ALL ONCLICK LISTENERS
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (d.getText().toString().equals("") || d.getText().toString().endsWith("-") || !d.getText().toString().matches("^[0-9-]+$")) {
                        Toast a1 = Toast.makeText(getApplicationContext(), "Enter An Appropriate Decimal Value", Toast.LENGTH_SHORT);
                        a1.show();
                        clearText(view);
                    } else if(d.getText().toString().length() >= 10) {
                        Toast a1 = Toast.makeText(getApplicationContext(), "Value Is Too Large For Conversion", Toast.LENGTH_SHORT);
                        a1.show();
                        clearText(view);
                    } else {
                        dec = d.getText().toString();
                        fromDecimalTo(view);
                    }
                }
                finally
                {
                    d.clearFocus();
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (b.getText().toString().equals("") || !b.getText().toString().matches("^[0-1]+$")) {
                        Toast a1 = Toast.makeText(getApplicationContext(), "Enter An Appropriate Binary Value", Toast.LENGTH_SHORT);
                        a1.show();
                        clearText(view);
                    } else if(b.getText().toString().length() >= 64) {
                        Toast a1 = Toast.makeText(getApplicationContext(), "Value Is Too Large For Conversion", Toast.LENGTH_SHORT);
                        a1.show();
                        clearText(view);
                    } else {
                        bin = b.getText().toString();
                        fromBinaryTo(view);
                    }
                }
                finally
                {
                    b.clearFocus();
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });

        hd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (hd.getText().toString().equals("") || !hd.getText().toString().matches("^[0-9A-F]+$")) {
                        Toast a1 = Toast.makeText(getApplicationContext(), "Enter An Appropriate Hexadecimal Value", Toast.LENGTH_SHORT);
                        a1.show();
                        clearText(view);
                    } else if(hd.getText().toString().length() >= 8) {
                        Toast a1 = Toast.makeText(getApplicationContext(), "Value Is Too Large For Conversion", Toast.LENGTH_SHORT);
                        a1.show();
                        clearText(view);
                    } else {
                        hex = hd.getText().toString();
                        fromHexaDecimalTo(view);
                    }
                }
                finally
                {
                    hd.clearFocus();
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });

        o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (o.getText().toString().equals("") || !o.getText().toString().matches("^[0-7]+$")) {
                        Toast a1 = Toast.makeText(getApplicationContext(), "Enter An Appropriate Octal Value", Toast.LENGTH_SHORT);
                        a1.show();
                        clearText(view);
                    } else if(o.getText().toString().length() >= 12) {
                        Toast a1 = Toast.makeText(getApplicationContext(), "Value Is Too Large For Conversion", Toast.LENGTH_SHORT);
                        a1.show();
                        clearText(view);
                    } else {
                        oct = o.getText().toString();
                        fromOctalTo(view);
                    }
                }
                finally
                {
                    o.clearFocus();
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    //ALL THE CONVERSION METHODS
    public void fromDecimalTo(View v) {

        decString = d.getText().toString();

        bin = Integer.toBinaryString(Integer.parseInt(decString,10));
        hex = Integer.toHexString(Integer.parseInt(decString,10)).toUpperCase();
        oct = Integer.toOctalString(Integer.parseInt(decString,10));

        setText(v);
    }

    public void fromBinaryTo(View v) {

        binString = b.getText().toString();

        dec = Integer.valueOf(Integer.parseInt(binString,2)).toString();
        hex = Integer.toHexString(Integer.parseInt(binString,2)).toUpperCase();
        oct = Integer.toOctalString(Integer.parseInt(binString,2));

        setText(v);
    }

    public void fromHexaDecimalTo(View v) {

        hexString = hd.getText().toString();

        dec = Integer.valueOf(Integer.parseInt(hexString,16)).toString();
        bin = Integer.toBinaryString(Integer.parseInt(hexString,16));
        oct = Integer.toOctalString(Integer.parseInt(hexString,16));

        setText(v);
    }

    public void fromOctalTo(View v) {

        octString = o.getText().toString();

        dec = Integer.valueOf(Integer.parseInt(octString,8)).toString();
        bin = Integer.toBinaryString(Integer.parseInt(octString,8));
        hex = Integer.toHexString(Integer.parseInt(octString,8)).toUpperCase();

        setText(v);
    }

    public void setText(View v)
    {
        d.setText(dec);
        b.setText(bin);
        hd.setText(hex);
        o.setText(oct);
    }

    public void clearText(View v)
    {
        d.setText("");
        b.setText("");
        hd.setText("");
        o.setText("");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
}

