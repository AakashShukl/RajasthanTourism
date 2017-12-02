package pay4free.in.rajasthantourism.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.github.library.bubbleview.BubbleTextView;

import java.util.List;

import pay4free.in.rajasthantourism.My.ChatModel;
import pay4free.in.rajasthantourism.R;

/**
 * Created by AAKASH on 03-12-2017.
 */

public class CustomAdapter extends BaseAdapter {

    private List<ChatModel> list_models;
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomAdapter(List<ChatModel> list_models, Context context) {
        this.list_models = list_models;
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list_models.size();
    }

    @Override
    public Object getItem(int position) {
        return list_models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if(view==null) {
            if (list_models.get(position).isSend)
                view = layoutInflater.inflate(R.layout.list_item_send, null);
            else
                view = layoutInflater.inflate(R.layout.list_item, null);

            BubbleTextView text = (BubbleTextView) view.findViewById(R.id.bubble);
            text.setText(list_models.get(position).message);
        }
        return view;
    }

}
