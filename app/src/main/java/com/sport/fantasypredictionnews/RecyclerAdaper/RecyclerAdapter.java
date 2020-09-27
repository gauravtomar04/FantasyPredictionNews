package com.sport.fantasypredictionnews.RecyclerAdaper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.sport.fantasypredictionnews.MatchActivity;
import com.sport.fantasypredictionnews.Modal.MyModal;
import com.sport.fantasypredictionnews.R;

public class RecyclerAdapter extends FirebaseRecyclerAdapter<MyModal, RecyclerAdapter.ViewHolder> {

    private Context ctx;

    public RecyclerAdapter(FirebaseRecyclerOptions<MyModal> options, Context context)
    {
    super(options);
    this.ctx=ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(ViewHolder holder, int position, final MyModal model) {
        holder.ist.setText(model.getSeries());
        holder.sec.setText(model.getTeam());
        holder.match.setText(model.getMatch());
        holder.date.setText(model.getDate());
        holder.time.setText(model.getTime());
        holder.venue.setText(model.getVenue());
        holder.t1.setText(model.getT1());
        holder.t2.setText(model.getT2());
        holder.t3.setText(model.getT3());
        holder.t4.setText(model.getT4());
        holder.t5.setText(model.getT5());
        holder.t6.setText(model.getT6());
        holder.t7.setText(model.getT7());
        holder.t8.setText(model.getT8());
        holder.t9.setText(model.getT9());
        holder.t10.setText(model.getT10());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), MatchActivity.class);
                intent.putExtra("team", model.getTeam());
                intent.putExtra("match", model.getMatch());
                intent.putExtra("time", model.getTime());
                intent.putExtra("date", model.getDate());
                intent.putExtra("venue", model.getVenue());
                intent.putExtra("t1", model.getT1());
                intent.putExtra("t2", model.getT2());
                intent.putExtra("t3", model.getT3());
                intent.putExtra("t4", model.getT4());
                intent.putExtra("t5", model.getT5());
                intent.putExtra("t6", model.getT6());
                intent.putExtra("t7", model.getT7());
                intent.putExtra("t8", model.getT8());
                intent.putExtra("t9", model.getT9());
                intent.putExtra("t10", model.getT10());

                intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                view.getContext().startActivity(intent);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView ist,sec;
        TextView match,date,time,venue;
        TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
        ImageView i,ii;
        public ViewHolder(View itemView) {
            super(itemView);
            ist= itemView.findViewById(R.id.match_series);
            sec = itemView.findViewById(R.id.maintitle);
            match = itemView.findViewById(R.id.match);
            date = itemView.findViewById(R.id.date);
            time= itemView.findViewById(R.id.time);
            venue = itemView.findViewById(R.id.venue);
            i = itemView.findViewById(R.id.team1);
            i = itemView.findViewById(R.id.team2);
            t1 = itemView.findViewById(R.id.t1);
            t2 = itemView.findViewById(R.id.t2);
            t3 = itemView.findViewById(R.id.t3);
            t4 = itemView.findViewById(R.id.t4);
            t5 = itemView.findViewById(R.id.t5);
            t6 = itemView.findViewById(R.id.t6);
            t7 = itemView.findViewById(R.id.t7);
            t8 = itemView.findViewById(R.id.t8);
            t9 = itemView.findViewById(R.id.t9);
            t10 = itemView.findViewById(R.id.t10);
        }
    }
}
