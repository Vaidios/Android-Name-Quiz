package com.example.thenamequiz;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;
//https://stackoverflow.com/questions/8166497/custom-adapter-for-list-view
public class MinAdapter extends BaseAdapter {
    //Variables
    Context context;
    int resource;
    List<Profil> profiler;
    minApplication app;


    public MinAdapter(Context context, int resource, List<Profil> profiler, minApplication app){
    this.context = context;
    this.profiler = profiler;
    this.resource = resource;
    this.app = app;
    }

    @Override
    public int getCount() {
        return profiler.size();
    }

    @Override
    public Object getItem(int position) {
        return profiler.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //view = LayoutInflater.from(context).inflate(R.layout.listeentity, null);

        //getItem(position).getNavn()...... //her er avslutta. Video under "se senere"
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listeentity, null); ; //View.inflate(context, resource, null);
        }
        Profil profil = (Profil) getItem(position);
        TextView navnTekst = convertView.findViewById(R.id.navnEntity);
        ImageView profilBilde = convertView.findViewById(R.id.bildeEntity);
        Button knapp = convertView.findViewById(R.id.knappFjern);
        knapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //profiler.remove(position); //.getNavn();
                //app.fjernFraBase(String );
                if(!app.fjern((Profil) getItem(position))){
                    //profiler.remove(position);
                    Log.i("minTag", "Fant ingen profil i shared preferences å fjerne.");
                }
                notifyDataSetChanged();
            }
        });

        navnTekst.setText(profil.getNavn());
        profilBilde.setImageBitmap(profil.getBilde());
        return convertView;
    }
}
