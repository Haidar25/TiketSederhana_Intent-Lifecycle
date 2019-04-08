package com.example.haidar_1202164150_si4006_pab_modul2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView textviewPilihTanggalPulang, textviewPilihWaktuPulang, textviewTopUp, textviewSaldo, textviewPilihTanggalBerangkat, textviewPilihWaktuBerangkat, textviewTerimaKasih;
    DatePickerDialog datePickerDialog;
    Switch switchPulangPergi;
    Button btnBeliTiket;
    EditText editjmlTiket;
    String tanggalBerangkat, tanggalPulang, stringJumlahTiket, stringHargaTotal, Tujuan, TiketSpinner;
    int saldoSementara, hargaTiket, jumlahTiket, totalTransaksi;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity/" + this.getClass().getSimpleName(), "ini onStart");

        textviewPilihTanggalPulang = findViewById(R.id.textview_pilih_tanggal_pulang);
        textviewPilihWaktuPulang = findViewById(R.id.textview_pilih_waktu_pulang);
        textviewPilihTanggalBerangkat = findViewById(R.id.textview_pilih_tanggal_berangkat);
        textviewPilihWaktuBerangkat = findViewById(R.id.textview_pilih_waktu_berangkat);
        textviewTopUp = findViewById(R.id.textview_topup);
        textviewSaldo = findViewById(R.id.txtview_saldo);
        textviewTerimaKasih = findViewById(R.id.textview_terima_kasih);
        btnBeliTiket = findViewById(R.id.button_belitiket);
        editjmlTiket = findViewById(R.id.edit_jumlahtiket);
        saldoSementara = Integer.parseInt(textviewSaldo.getText().toString());

//        Start Spinner
        spinner = (Spinner) findViewById(R.id.spinner);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> listTujuan = new ArrayList<String>();
        listTujuan.add("Jakarta (Rp 85.000)");
        listTujuan.add("Cirebon (Rp 150.000)");
        listTujuan.add("Bekasi (Rp 70.000)");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listTujuan);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
//        End Spinner

//        Start Switch
        switchPulangPergi = (Switch) findViewById(R.id.sw_pulangpergi);
        switchPulangPergi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String statusSwitch = String.valueOf(isChecked);
                if (!isChecked) {
                    textviewPilihTanggalPulang.setVisibility(View.VISIBLE);
                    textviewPilihWaktuPulang.setVisibility(View.VISIBLE);
                } else {
                    textviewPilihTanggalPulang.setVisibility(View.INVISIBLE);
                    textviewPilihWaktuPulang.setVisibility(View.INVISIBLE);
                }
            }
        });
//        End Switch

//        Start Switch
        switchPulangPergi = (Switch) findViewById(R.id.sw_pulangpergi);
        switchPulangPergi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String statusSwitch = String.valueOf(isChecked);
                if (isChecked) {
                    textviewPilihTanggalPulang.setVisibility(View.VISIBLE);
                    textviewPilihWaktuPulang.setVisibility(View.VISIBLE);
                } else {
                    textviewPilihTanggalPulang.setVisibility(View.INVISIBLE);
                    textviewPilihWaktuPulang.setVisibility(View.INVISIBLE);
                }
            }
        });
//        End Switch



//        Fungsi Top Up
        textviewTopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogTopTup();
            }
        });

//       Fungsi Pilih Tanggal Berangkat
        textviewPilihTanggalBerangkat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                textviewPilihTanggalBerangkat.setText(
                                        dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

//      Fungsi Pilih Waktu Berangkat
        textviewPilihWaktuBerangkat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        textviewPilihWaktuBerangkat.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Pilih Waktu");
                mTimePicker.show();
            }
        });

//      Fungsi Pilih Tanggal Pulang
        textviewPilihTanggalPulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                textviewPilihTanggalPulang.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

//      Fungsi Pilih Waktu Pulang
        textviewPilihWaktuPulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        textviewPilihWaktuPulang.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Pilih Waktu");
                mTimePicker.show();
            }
        });

//      Fungsi Button Beli Tiket
        btnBeliTiket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TiketSpinner = spinner.getSelectedItem().toString();
                tanggalBerangkat = textviewPilihTanggalBerangkat.getText().toString();
                tanggalPulang = textviewPilihTanggalPulang.getText().toString();
                stringJumlahTiket = editjmlTiket.getText().toString();
                hargaTiket = Integer.parseInt(TiketSpinner.replaceAll("[\\D]", ""));
                Tujuan = spinner.getSelectedItem().toString();
//                Jika pulang pergi maka harganya 2 kali ipat
                if (switchPulangPergi.isChecked()) {
                    hargaTiket = hargaTiket * 2;
                }
//                int saldo = Integer.parseInt(tvSaldo.getText().toString().replaceAll("[\\D]", ""));
                jumlahTiket = Integer.parseInt(editjmlTiket.getText().toString());
                totalTransaksi = hargaTiket * 2;
                stringHargaTotal = Integer.toString(totalTransaksi);
//               Cek Saldo Apakah Melebihi Total Transaksi atau tidak
                if (saldoSementara >= totalTransaksi) {
                    Toast.makeText(MainActivity.this,
                            "Selamat Transaksi Anda Berhasil",
                            Toast.LENGTH_SHORT).show();
//                    saldoSementara = saldoSementara - totalTransaksi;
                    textviewSaldo.setText(Integer.toString(saldoSementara));
//                    Kalo Belum Dipilih
                    if (tanggalBerangkat.equals("Pilih Tanggal")) {
                        tanggalBerangkat = "null";
                    }
                    if (tanggalPulang.equals("Pilih Tanggal")) {
                        tanggalPulang = "null";
                    }
//                    Kalo ngga pulang pergi
                    if (switchPulangPergi.isChecked() == false) {
                        tanggalPulang = "null";
                    }

                    Intent i = new Intent(MainActivity.this, Checkout.class);
                    i.putExtra("tujuan", Tujuan);
                    i.putExtra("tanggalBerangkat", tanggalBerangkat);
                    i.putExtra("tanggalPulang", tanggalPulang);
                    i.putExtra("jumlahTiket", stringJumlahTiket);
                    i.putExtra("hargaTotal", stringHargaTotal);
                    startActivityForResult(i, 9);
//                    Munculkan TextView TerimaKasih Telah Bertransaksi
                   // textviewTerimaKasih.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(MainActivity.this,
                            "Waduh Maaf Saldo Anda Belum Cukup, Silahkan Klik Top Up di Pojok Kanan",
                            Toast.LENGTH_SHORT).show();
                   // textviewTerimaKasih.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    //    Start Log Activity LifeCycle
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity/" + this.getClass().getSimpleName(), "ini onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity/" + this.getClass().getSimpleName(), "ini onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity/" + this.getClass().getSimpleName(), "ini onDestroy");
    }
//    End Log Activity LifeCycle

    //  Fungsi Top Up
    public void openDialogTopTup() {

        // Set Custom Title
        TextView title = new TextView(this);
        // Title Properties
        title.setText("Masukkan Jumlah Saldo");
        title.setPadding(10, 10, 10, 10);   // Set Position
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.BLACK);
        title.setTextSize(20);

        // Set EditText
        final EditText topUpSaldo = new EditText(this);
        topUpSaldo.setInputType(InputType.TYPE_CLASS_NUMBER);
        // Message Properties
        topUpSaldo.setGravity(Gravity.CENTER_HORIZONTAL);
        topUpSaldo.setTextColor(Color.BLACK);
        topUpSaldo.setInputType(InputType.TYPE_CLASS_NUMBER);
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setView(topUpSaldo)
                .setCustomTitle(title)
                .create();

        //fungsi ngurang sakldo
        // Set Button
        // you can more buttons
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Tambah Saldo", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                saldoSementara = saldoSementara + Integer.parseInt(topUpSaldo.getText().toString());
                textviewSaldo.setText(Integer.toString(saldoSementara));
            }
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Perform Action on Button
            }
        });

        new Dialog(getApplicationContext());
        alertDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==9){
            if (resultCode==RESULT_OK){
                String minus=data.getStringExtra("ngurang");
                int kurang=Integer.valueOf(minus);
                int asal=saldoSementara;
                int akhir=asal-kurang;
                textviewSaldo.setText(String.valueOf(akhir));
                saldoSementara = akhir;
                textviewTerimaKasih =findViewById(R.id.textview_terima_kasih);
                textviewTerimaKasih.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

