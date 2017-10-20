package com.sourcedev.joaozao.retrospective;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.sourcedev.joaozao.retrospective.model.ShamrockersModel;
import java.util.List;

/**
 * Created by joaozao on 27/01/17.
 */

public class ShamRockersAdapter extends RecyclerView.Adapter<ShamRockersAdapter.MyViewHolder> {

  private final Context mContext;
  private List<ShamrockersModel> mShamrockersList;
  private ShamrockersModel shamrockersModel;

  public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView title, description;
    public ImageView thumbnail;


    public MyViewHolder(View view) {
      super(view);
      title = (TextView) view.findViewById(R.id.title);
      description = (TextView) view.findViewById(R.id.description);
      thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
      view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      Intent i = new Intent(mContext, RetrospectiveBoard.class);
      i.putExtra("shamrocker", mShamrockersList.get(getAdapterPosition()));
      mContext.startActivity(i);
    }
  }

  // Provide a suitable constructor (depends on the kind of dataset)
  public ShamRockersAdapter(Context context, List<ShamrockersModel> shamrockersList) {
    mShamrockersList = shamrockersList;
    mContext = context;
  }

  // Create new views (invoked by the layout manager)
  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.shamrockers_card, parent, false);

    return new MyViewHolder(itemView);
  }

  // Replace the contents of a view (invoked by the layout manager)
  @Override
  public void onBindViewHolder(final MyViewHolder holder, int position) {
    shamrockersModel = mShamrockersList.get(position);
    holder.title.setText(shamrockersModel.getName());
    holder.description.setText(shamrockersModel.getDescription());
    // loading album cover using Glide library
    Glide.with(mContext).load(shamrockersModel.getThumbnail()).into(holder.thumbnail);

  }


  // Return the size of your dataset (invoked by the layout manager)
  @Override
  public int getItemCount() {
    return mShamrockersList.size();
  }
}



