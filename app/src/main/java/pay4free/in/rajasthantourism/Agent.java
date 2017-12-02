package pay4free.in.rajasthantourism;

import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import pay4free.in.rajasthantourism.Adapter.CustomAdapter;
import pay4free.in.rajasthantourism.Helper.HttpDataHandler;
import pay4free.in.rajasthantourism.My.ChatModel;
import pay4free.in.rajasthantourism.My.SimiSimi;

public class Agent extends AppCompatActivity {
    ListView listView;
    EditText editText;
    List<ChatModel> list=new ArrayList<>();
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent);
        listView=(ListView)findViewById(R.id.list);
        editText=(EditText)findViewById(R.id.edit);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=editText.getText().toString();
                ChatModel model=new ChatModel(text,true);
                list.add(model);
                new SimisimiApi().execute(list);
                editText.setText("");
            }
        });
    }
    private  class SimisimiApi extends AsyncTask<List<ChatModel>,Void,String>
    {

String stream=null;
        List<ChatModel> models;
        String text=editText.getText().toString();
        @Override
        protected String doInBackground(List<ChatModel>... params) {
           String url=String.format("http://sandbox.api.simsimi.com/request.p?key=%s&lc=en&ft=1.0&text=%s",getString(R.string.api),text);
       models=params[0];
            HttpDataHandler httpDataHandler=new HttpDataHandler();
            stream=httpDataHandler.GetHTTPData(url);
            return  stream;
        }

        @Override
        protected void onPostExecute(String s) {
            Gson gson=new Gson();
            SimiSimi response=gson.fromJson(s,SimiSimi.class);
            ChatModel chatModel=new ChatModel(response.getResponse(),false);
            models.add(chatModel);
            CustomAdapter customAdapter=new CustomAdapter(models,getApplicationContext());
            listView.setAdapter(customAdapter);
        }
    }
}
