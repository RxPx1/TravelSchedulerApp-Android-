package com.myappcompany.rob.d308_mobileapp.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myappcompany.rob.d308_mobileapp.R;
import com.myappcompany.rob.d308_mobileapp.entities.Vacation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class VacationAdapter extends RecyclerView.Adapter<VacationAdapter.VacationViewHolder> {

    class VacationViewHolder extends RecyclerView.ViewHolder {
        private final TextView vacationItemView;
        private  VacationViewHolder(View itemview){
            super(itemview);
            vacationItemView = itemview.findViewById(R.id.textView2);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    final Vacation current=mVacations.get(position);
                    Intent intent= new Intent(context, VacationDetails.class);
                    intent.putExtra("id", current.getVacationID());
                    intent.putExtra("name", current.getVacationName());
                    intent.putExtra("lodging", current.getVacationLodging());
                    intent.putExtra("start_Date", current.getStartDate());
                    intent.putExtra("end_Date", current.getEndDate());
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<Vacation> mVacations;
    private final Context context;
    private final LayoutInflater mInflater;

    public VacationAdapter(Context context){
        mInflater= LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public VacationAdapter.VacationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.vacation_list_item,parent,false);
        return new VacationViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull VacationAdapter.VacationViewHolder holder, int position) {
        if(mVacations!=null){
            Vacation current=mVacations.get(position);
            String name=current.getVacationName();
            holder.vacationItemView.setText(name);
            String startDate = formatDate(current.getStartDate());
            holder.vacationItemView.setText(name + "   -->    " + startDate);
        }
        else{
            holder.vacationItemView.setText("No Vacation Name");
        }
    }

    @Override
    public int getItemCount() {
        return mVacations.size();
    }

    public void setVacations(List<Vacation> vacations){
        mVacations=vacations;
        notifyDataSetChanged();
    }
    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd", Locale.getDefault());
        return sdf.format(date);
    }
}
