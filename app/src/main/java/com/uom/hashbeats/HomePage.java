package com.uom.hashbeats;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import org.qap.ctimelineview.TimelineRow;
import org.qap.ctimelineview.TimelineViewAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HomePage extends AppCompatActivity {

    private PopupWindow mPopupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        pendingPosts();

        final ArrayList<TimelineRow> timelineRowsList = new ArrayList<>();
        populate(timelineRowsList);


        ArrayAdapter<TimelineRow> myAdapter = new TimelineViewAdapter(this, 0, timelineRowsList,true);
        ListView myListView = (ListView) findViewById(R.id.timeline_listView);
        myListView.setAdapter(myAdapter);

        final RelativeLayout rel=(RelativeLayout)findViewById(R.id.rel);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.popup_detail,null);
                final ViewGroup root = (ViewGroup) getWindow().getDecorView().getRootView();
                applyDim(root, 0.5f);

                ImageView imageView=(ImageView)customView.findViewById(R.id.imageView);
                TextView title=(TextView) customView.findViewById(R.id.title);
                TextView desc=(TextView) customView.findViewById(R.id.desc);
                TextView date=(TextView) customView.findViewById(R.id.date);

                title.setText(timelineRowsList.get(i).getTitle());
                desc.setText(timelineRowsList.get(i).getDescription());
                date.setText(timelineRowsList.get(i).getDate().toString());
                imageView.setImageResource(timelineRowsList.get(i).getId());

                mPopupWindow = new PopupWindow(
                        customView,
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT
                );

//                mPopupWindow.setOutsideTouchable(true);
//                mPopupWindow.setFocusable(true);
                mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                if(Build.VERSION.SDK_INT>=21){
                    mPopupWindow.setElevation(5.0f);
                }
                ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPopupWindow.dismiss();
                        clearDim(root);
                    }
                });

                mPopupWindow.showAtLocation(rel, Gravity.CENTER,0,0);
            }
        });

    }

    private void pendingPosts(){
        ArrayList<MedicalRecord> vals=new ArrayList<>();
        populatePending(vals);

        PendingAdapter pendingAdapter=new PendingAdapter(getApplicationContext(),vals);
        ListView listView=(ListView)findViewById(R.id.pending_listView);
        listView.setAdapter(pendingAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                new AlertDialog.Builder(HomePage.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Pending Records")
                        .setMessage("Do you want to add this record?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }

                        })
                        .setNeutralButton("Back",null)
                        .setNegativeButton("No", null)
                        .show();
            }
        });

    }

    private void populatePending(ArrayList<MedicalRecord> vals) {
        MedicalRecord medical_record_1 = new MedicalRecord("Schizophrenia", "Dr. Varuni De Silva", 1, 37, "2011-1-27", R.drawable.prescription1);
        MedicalRecord medical_record_3 = new MedicalRecord("Asthma", "Dr. C Amarasena", 1, 752, "2012-5-27", R.drawable.prescription1);
        MedicalRecord medical_record_7 = new MedicalRecord("Chest X-Ray", "Apollo Hospital", 2, 1452, "2014-12-27", R.drawable.prescription1);

        vals.add(medical_record_1);
        vals.add(medical_record_3);
        vals.add(medical_record_7);
    }


    private void populate(ArrayList<TimelineRow> timelineRowsList) {

        MedicalRecord medical_record_1 = new MedicalRecord("Schizophrenia", "Dr. Varuni De Silva", 1, 37, "2011-1-27", R.drawable.prescription1);
        MedicalRecord medical_record_2 = new MedicalRecord("Depression", "Dr. Rohan Gunawardana", 1, 2400, "2011-5-27", R.drawable.finger);
        MedicalRecord medical_record_3 = new MedicalRecord("Asthma", "Dr. C Amarasena", 1, 752, "2012-5-27", R.drawable.prescription1);
        MedicalRecord medical_record_4 = new MedicalRecord("Hepatitis", "Dr. Godvin Constantine", 1, 122, "2012-1-27", R.drawable.prescription1);
        MedicalRecord medical_record_5 = new MedicalRecord("Lipid Profile", "Medica Labs", 2, 1204, "2013-1-27", R.drawable.prescription1);
        MedicalRecord medical_record_6 = new MedicalRecord("Cortisol Test", "Nawaloka Laboratory", 2, 2014, "2015-2-27", R.drawable.prescription1);
        MedicalRecord medical_record_7 = new MedicalRecord("Chest X-Ray", "Apollo Hospital", 2, 1452, "2014-12-27", R.drawable.prescription1);
        MedicalRecord medical_record_8 = new MedicalRecord("Serology Test", "Royal Care Hospital", 2, 354, "2011-12-27", R.drawable.prescription1);
        MedicalRecord medical_record_9 = new MedicalRecord("Cold", "Dr. P.N. Thenabadu", 0, 1444, "2016-12-27", R.drawable.prescription1);
        MedicalRecord medical_record_10 = new MedicalRecord("Cataracts", "Dr. M. Jayatilake", 0, 1700, "2017-12-27", R.drawable.prescription1);
        MedicalRecord medical_record_11 = new MedicalRecord("Actinic Keratosis", "Dr. Mohan Rajakaruna", 0, 2549, "2018-1-12", R.drawable.prescription1);
        MedicalRecord medical_record_12 = new MedicalRecord("Narcolepsy", "Dr. Gamini Ranasinghe", 0, 258, "2011-5-12", R.drawable.prescription1);


        addNode(timelineRowsList,medical_record_1);
        addNode(timelineRowsList,medical_record_2);
        addNode(timelineRowsList,medical_record_3);
        addNode(timelineRowsList,medical_record_4);
        addNode(timelineRowsList,medical_record_5);
        addNode(timelineRowsList,medical_record_6);
        addNode(timelineRowsList,medical_record_7);
        addNode(timelineRowsList,medical_record_8);
        addNode(timelineRowsList,medical_record_9);
        addNode(timelineRowsList,medical_record_10);
        addNode(timelineRowsList,medical_record_11);
        addNode(timelineRowsList,medical_record_12);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_name) {

            new AlertDialog.Builder(HomePage.this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Generate QR")
                    .setMessage("Scanning the QR will give Read/Write permission")
                    .setPositiveButton("Proceed", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(getApplicationContext(),QRactivity.class));
                            dialog.cancel();
                        }

                    })
                    .setNegativeButton("Back", null)
                    .show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void applyDim(@NonNull ViewGroup parent, float dimAmount){
        Drawable dim = new ColorDrawable(Color.BLACK);
        dim.setBounds(0, 0, parent.getWidth(), parent.getHeight());
        dim.setAlpha((int) (255 * dimAmount));

        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.add(dim);
    }

    public static void clearDim(@NonNull ViewGroup parent) {
        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.clear();
    }

    private void addNode(ArrayList<TimelineRow> timelineRowsList, MedicalRecord temp) {
        TimelineRow myRow = new TimelineRow(0);
        myRow.setDate(getDate(temp.getDate()));
        myRow.setTitle(temp.getTitle());
        myRow.setDescription(temp.getDescr());
        myRow.setId(temp.getImage());

        if(temp.getType()==0){//pres
            myRow.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.prescrip));
            myRow.setImageSize(50);
            myRow.setBackgroundSize(50);
        }else if (temp.getType()==1){//docs note
            myRow.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.docs_note));
            myRow.setImageSize(35);
            myRow.setBackgroundSize(35);
        }else if(temp.getType()==2){//report
            myRow.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.laberports));
            myRow.setImageSize(65);
            myRow.setBackgroundSize(65);
        }

        myRow.setBellowLineColor(Color.argb(255, 0, 100, 0));
        myRow.setBellowLineSize(3);


        myRow.setDateColor(Color.argb(255, 100, 0, 0));
        myRow.setTitleColor(Color.argb(255, 0, 0, 0));
        myRow.setDescriptionColor(Color.argb(255, 0, 0, 0));

        timelineRowsList.add(myRow);
    }
    private Date getDate(int i) {
        String dateInString = "2011-1-1";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, i);
        return new Date(c.getTimeInMillis());
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(HomePage.this,LoginActivity.class));
    }
}
