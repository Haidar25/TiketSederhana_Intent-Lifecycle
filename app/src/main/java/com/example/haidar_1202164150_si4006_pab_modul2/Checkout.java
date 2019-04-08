package com.example.haidar_1202164150_si4006_pab_modul2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Checkout extends AppCompatActivity {
    TextView textviewTanggalBerangkat, textviewTanggalPulang, textviewJumlahTiket, textviewHargaTotal, textviewTujuan;
    Button buttonKonfirmasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        textviewTujuan=findViewById(R.id.textview_tujuan_summary);
        textviewTanggalBerangkat=findViewById(R.id.textview_tanggal_berangkat_summary);
        textviewTanggalPulang=findViewById(R.id.textview_tanggal_pulang_summary);
        textviewJumlahTiket=findViewById(R.id.textview_jumlah_tiket_summary);
        textviewHargaTotal=findViewById(R.id.textview_harga_total_summary);
        buttonKonfirmasi=findViewById(R.id.button_konfirmasi);
        Intent intent=getIntent();
        Bundle extras=intent.getExtras();

        if (extras!=null){
            String tujuan,tanggalBerangkat,tanggalPulang, jumlahTiket, hargaTotal;

            tujuan=extras.getString("tujuan");
            String tujuanregex = String.format(tujuan.replaceAll("[Rp1234567890().]",""));
            textviewTujuan.setText(tujuanregex.toUpperCase());
            tanggalBerangkat=extras.getString("tanggalBerangkat");
            tanggalPulang = extras.getString("tanggalPulang");
            jumlahTiket = extras.getString("jumlahTiket");
            hargaTotal = extras.getString("hargaTotal");
//            textviewTujuan.setText(tujuanregex);
            textviewTanggalBerangkat.setText(tanggalBerangkat);
            textviewTanggalPulang.setText(tanggalPulang);
            textviewJumlahTiket.setText(jumlahTiket);
            textviewHargaTotal.setText(hargaTotal);
        }
       buttonKonfirmasi.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent in= new Intent(Checkout.this, MainActivity.class);
               in.putExtra("ngurang",textviewHargaTotal.getText().toString());
               setResult(RESULT_OK, in);

               finish();
           }
       });
    }
    //    Start Log Activity LifeCycle
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity/" + this.getClass().getSimpleName(), "ini onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity/" + this.getClass().getSimpleName(), "ini onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity/" + this.getClass().getSimpleName(), "ini onPause");
    }
//    End Log Activity LifeCycle
}

