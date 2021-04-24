package com.something.about.hatay_berkay.doviztakipet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionsMenu floatingActionButton;
    ListView dovizler_listview;
    ArrayList<String> doviz_isim_array = new ArrayList<>();
     ArrayList<String> doviz_alis_array = new ArrayList<>();
     ArrayList<String> doviz_satis_array = new ArrayList<>();


    String amerikan_doları_alıs , amerikan_doları_satıs , euro_alıs , euro_satıs , sterlin_alıs ,sterlin_satıs , isvicre_frangı_alıs ,isvicre_frangı_satıs
            , japon_yeni_alıs , japon_yeni_satıs , danimarka_alıs , danimarka_satıs ,isvec_kronu_alıs , isvec_kronu_satıs , norvec_alıs,norvec_satıs , canada_doları_alıs ,canada_doları_satıs
            ,avustralya_doları_alıs , avusturalya_doları_satıs;
    private static final String url = "https://www.ziraatbank.com.tr/tr/fiyatlar-ve-oranlar/";
    Document document;
    String h1elementi;
    Elements h1element;
    custom_listview custom_listview = new custom_listview();
     private static final String TAG = "activity_konusu";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = (FloatingActionsMenu) findViewById(R.id.floatingActionButton);
        dovizler_listview = (ListView)findViewById(R.id.dovizler_listview);


        new getirHtml().execute();



        FloatingActionButton refresh_button = new FloatingActionButton(this);
        floatingActionButton.addButton(refresh_button);
        refresh_button.setIcon(R.drawable.refresh);



        FloatingActionButton share_button = new FloatingActionButton(this);
        floatingActionButton.addButton(share_button);
        share_button.setIcon(R.drawable.as);




        refresh_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
            }
        });


        share_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                
                String message = "I liked your app. ";
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, message);
                
                startActivity(Intent.createChooser(share, "Title of the dialog the system will open"));
                
            }
        });













    }

    private void refresh() {

        new getirHtml().execute();
    }


    class custom_listview extends BaseAdapter {
        @Override
        public int getCount() {

            return doviz_alis_array.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.custom_listview, null);
            TextView doviz_türü_textview = (TextView) view.findViewById(R.id.doviz_türü_textview);
            TextView   döviz_alıs_textview = (TextView)view.findViewById(R.id.döviz_alıs_textview);
            TextView   doviz_satıs_textview = (TextView)view.findViewById(R.id.doviz_satıs_textview);



          doviz_türü_textview.setText(doviz_isim_array.get(i));



            döviz_alıs_textview.setText(doviz_alis_array.get(i));

            doviz_satıs_textview.setText(doviz_satis_array.get(i));










            return view;
        }
    }

    public class getirHtml extends AsyncTask<Void,Void,Void > {
        @Override
        protected Void doInBackground(Void... voids) {

            try {
                document = Jsoup.connect(url).get();


                h1element = document.select("div[data-id=rdIntBranchDoviz]");

            }catch (Exception e) {

            }





            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


            split_all_values();


            set_all_values_to_array();



            dovizler_listview.setAdapter(custom_listview);
        }
            }

    private void set_all_values_to_array() {

        doviz_alis_array.clear();
        doviz_satis_array.clear();


        doviz_isim_array.add("   ");
        doviz_isim_array.add("USD");
        doviz_isim_array.add("EUR");
        doviz_isim_array.add("GBP");
        doviz_isim_array.add("CHF");
        doviz_isim_array.add("JPY");
        doviz_isim_array.add("DKK");
        doviz_isim_array.add("SEK");
        doviz_isim_array.add("NOK");
        doviz_isim_array.add("CAD");
        doviz_isim_array.add("AUD");


        doviz_alis_array.add("Alış");
        doviz_alis_array.add(amerikan_doları_alıs);
        doviz_alis_array.add(euro_alıs);
        doviz_alis_array.add(sterlin_alıs);
        doviz_alis_array.add(isvicre_frangı_alıs);
        doviz_alis_array.add(japon_yeni_alıs);
        doviz_alis_array.add(danimarka_alıs);
        doviz_alis_array.add(isvec_kronu_alıs);
        doviz_alis_array.add(norvec_alıs);
        doviz_alis_array.add(canada_doları_alıs);
        doviz_alis_array.add(avustralya_doları_alıs);




        doviz_satis_array.add("Satıs");
        doviz_satis_array.add(amerikan_doları_satıs);
        doviz_satis_array.add(euro_satıs);
        doviz_satis_array.add(sterlin_satıs);
        doviz_satis_array.add(isvicre_frangı_satıs);
        doviz_satis_array.add(japon_yeni_satıs);
        doviz_satis_array.add(danimarka_satıs);
        doviz_satis_array.add(isvec_kronu_satıs);
        doviz_satis_array.add(norvec_satıs);
        doviz_satis_array.add(canada_doları_satıs);
        doviz_satis_array.add(avusturalya_doları_satıs);

    }

    private void split_all_values() {

        h1elementi = h1element.text().replace(",",".")  ;
        String[] separated = h1elementi.split("\\ ");

        amerikan_doları_alıs = separated[15]   ;
        amerikan_doları_satıs = separated[16]  ;

        euro_alıs = separated[21]  ;
        euro_satıs  = separated[22]  ;


        sterlin_alıs = separated[27]  ;
        sterlin_satıs = separated[28]  ;


        isvicre_frangı_alıs = separated[34]  ;
        isvicre_frangı_satıs    = separated[35]  ;


        japon_yeni_alıs   = separated[42]  ;
        japon_yeni_satıs = separated[43]  ;


        danimarka_alıs = separated[49]  ;
        danimarka_satıs = separated[50]  ;


        isvec_kronu_alıs = separated[56]  ;
        isvec_kronu_satıs = separated[57]  ;


        norvec_alıs = separated[63]  ;
        norvec_satıs = separated[64]  ;


        canada_doları_alıs = separated[70]  ;
        canada_doları_satıs = separated[71]  ;


        avustralya_doları_alıs = separated[77]  ;
        avusturalya_doları_satıs = separated[78]  ;


    }

}