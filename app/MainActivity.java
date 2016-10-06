package com.uw.aakarsh.htmlparser;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    EditText editText;
    EditText editText2;
    TextView textView;
    Button parseButton;
    String urlFinal;
    String divName;
    ArrayList<String> result = new ArrayList<>();

    public View.OnClickListener buttonListener = new View.OnClickListener() {
        public void onClick (View view){
            urlFinal = editText.getText().toString();
            divName = editText2.getText().toString();
            new getText().execute();
        }};


    public class getText extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {


            Document doc;
            try {
                doc = Jsoup.connect(urlFinal).get();
                Elements items = doc.getElementsByClass(divName);
                for(Element item : items) {
                    result.add(item.text().toString());
                    System.out.println(item.text().toString());
                }

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getApplication(), "Bad URL", Toast.LENGTH_LONG);
            }


            return null;
        }


        public void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(result.get(0).toString() + "\n");
            for(int elementInList = 1; elementInList < result.size(); elementInList++){
                textView.append(result.get(elementInList).toString() + "\n");

            }

        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        textView = (TextView) findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());
        parseButton = (Button) findViewById(R.id.parseButton);
        parseButton.setOnClickListener(buttonListener);

    }

}






