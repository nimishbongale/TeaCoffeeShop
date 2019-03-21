package com.example.nimishbongale.teacoffeeshop;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText et;
    CheckBox cb1,cb2;
    Button binc,bdec,border;
    TextView tv1,tv2;
    int quan=0,res=0;

    void checkborder()
    {
        if(quan>0)
            border.setEnabled(true);
        else
            border.setEnabled(false);
    }
    int calcrel()
    {
        if(cb1.isChecked()==false&&cb2.isChecked()==false)
            res=5;
        else if(cb1.isChecked()==true&&cb2.isChecked()==true)
        res=8;
        else if (cb1.isChecked()==true)
            res=6;
        else
            res=7;

        res*=quan;
        return res;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et=findViewById(R.id.editText);
        cb1=findViewById(R.id.checkBox);
        cb2=findViewById(R.id.checkBox2);
        binc=findViewById(R.id.button2);
        bdec=findViewById(R.id.button);
        border=findViewById(R.id.button3);
        tv1=findViewById(R.id.textView3);
        tv2=findViewById(R.id.textView6);
        border.setEnabled(false);
        bdec.setEnabled(false);

     cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(res>0)
                    tv2.setText("$"+Integer.toString(calcrel()));
            }
        });

        cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(res>0)
                    tv2.setText("$"+Integer.toString(calcrel()));
            }
        });


        binc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quan++;
                bdec.setEnabled(true);
                tv1.setText(Integer.toString(quan));
                tv2.setText("$"+Integer.toString(calcrel()));
                checkborder();
            }
        });

        bdec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quan>=1)
                {
                    if(quan==1)
                        bdec.setEnabled(false);

                    quan--;
                    tv1.setText(Integer.toString(quan));
                    tv2.setText("$"+Integer.toString(calcrel()));
            }
                checkborder();
        }});

        border.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uriText =
                        "mailto:nimishnb98@gmail.com" +
                                "?subject=" + Uri.encode("Your order has been placed") +
                                "&body=" + Uri.encode("Order details:-\nName:"+et.getText()+"\nToppings: \nWhipped Cream: "+cb1.isChecked()+"\nChocolate: "+cb2.isChecked()+"\nQty: "+quan+"\nTotal cash payable: $"+res);

                Uri uri = Uri.parse(uriText);
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                sendIntent.setData(uri);
                startActivity(Intent.createChooser(sendIntent, "Send email"));
            }
        });

    }
}
