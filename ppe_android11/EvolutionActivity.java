package com.ergo.ppe_android11;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.Series;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;

import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EvolutionActivity extends AppCompatActivity {

    private final String EXTRA_LOGIN = "undefined login";
    UserBDD userBdd;
    String mail;
    private static final String TAG = "Evolution";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evolution);

        //int i=0;
        userBdd = new UserBDD(this);
        userBdd.open();
        Intent intent = getIntent();
        mail=intent.getStringExtra(EXTRA_LOGIN);
        final User currentUser = userBdd.getUserWithMail(mail);
        final int idUser = currentUser.getId();
        Log.i(TAG, "USER  : " + currentUser.getMail());
        Log.i(TAG, "ID   : " + userBdd.getUserWithMail(mail).getId());
        Log.i(TAG, " nb  : " + userBdd.getAllUsers().size());
        /*userBdd.insertSmiley(new Smiley(idUser,"2017/04/01","NOTHAPPY" ));
        userBdd.insertSmiley(new Smiley(idUser,"2017/04/02","VERYHAPPY" ));
        userBdd.insertSmiley(new Smiley(idUser,"2017/04/03","MIDDLE" ));
        userBdd.insertSmiley(new Smiley(idUser,"2017/04/04","HAPPY" ));
       userBdd.insertSmiley(new Smiley(idUser,"2017/04/05","NOTHAPPYATALL" ));*/

        DataPoint[] list_point = null;
        List<Smiley> list_s = null;
        List<Smiley> list_smileys = userBdd.getAllSmileysofUser(idUser);
        Log.i(TAG, "SIZE   : " + list_smileys.size());
        if(list_smileys.size()!=0) {
           list_s = new ArrayList<>();
            for (int i = 1; i <= list_smileys.size(); i++) {
                if (userBdd.getSmileyWithId(i) != null) {
                    Smiley s = userBdd.getSmileyWithId(i);
                    if (s.getCol_id_user() == idUser) {
                        list_s.add(s);
                    }

                } else {
                    list_s.add(new Smiley(0, "2017/04/04", "NOOOOO"));
                }
            }
            String txt;

            list_point = new DataPoint[list_s.size()];
            list_point[0] = new DataPoint(0, 0);
            for (int i = 0; i < list_s.size(); i++) {

                Date day = new Date();
                String str = list_s.get(i).getCol_day();
                DateFormat format = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
                try {
                    day = format.parse(str);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                int smiley = 0;
                switch (list_s.get(i).getCol_smiley()) {
                    case "VERYHAPPY":
                        smiley = 5;
                        break;
                    case "HAPPY":
                        smiley = 4;
                        break;
                    case "MIDDLE":
                        smiley = 3;
                        break;
                    case "NOTHAPPY":
                        smiley = 2;
                        break;
                    case "NOTHAPPYATALL":
                        smiley = 1;
                        break;
                    default:
                        smiley = 0;
                        break;

                }

                list_point[i] = new DataPoint(day, smiley);


            }

        }else{ list_point = new DataPoint[]{};}
         GraphView graph;
        graph = (GraphView) findViewById(R.id.graph);


        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(list_point);

        // use static labels for horizontal and vertical labels
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        if(list_s!=null){staticLabelsFormatter.setHorizontalLabels(new String[] {list_s.get(list_smileys.size()-5).getCol_day().substring(6), list_s.get(list_smileys.size()-4).getCol_day().substring(6), list_s.get(list_smileys.size()-3).getCol_day().substring(6), list_s.get(list_smileys.size()-2).getCol_day().substring(6), list_s.get(list_smileys.size()-1).getCol_day().substring(6)});}
        else{staticLabelsFormatter.setHorizontalLabels(new String[] {" ", " ", " ", " ", " "});}
        staticLabelsFormatter.setVerticalLabels(new String[] {" ", " ", " ", " ", " "});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        series.setTitle("Posture");
        graph.addSeries(series);
        // set date label formatter
        //graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
        graph.getGridLabelRenderer().setNumHorizontalLabels(5); // only 4 because of the space
        graph.getGridLabelRenderer().setNumVerticalLabels(5); // only 4 because of the space

// set manual x bounds to have nice steps

        graph.getViewport().setXAxisBoundsManual(true);

// as we use dates as labels, the human rounding to nice readable numbers
// is not necessary
        graph.getGridLabelRenderer().setHumanRounding(false);
        // styling
        series.setShape(PointsGraphSeries.Shape.POINT);
        // legend

        final ImageButton backButton = (ImageButton) findViewById(R.id.backtofirsti);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(EvolutionActivity.this, MenuActivity.class);
                intent.putExtra(EXTRA_LOGIN, mail);
                startActivity(intent);
            }


        });
    }

}
