package com.exerciseapp.mattiapalmas.rubrica;

/**
 * Created by mattia palmas on 2017-08-24.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by mattia palmas on 2017-08-11.
 */

public class AdaptorRecycleView extends RecyclerView.Adapter<AdaptorRecycleView.ViewHolder> {

    private List<ContactModel> listItems;
    private Context context;


    public AdaptorRecycleView(List<ContactModel> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycle_view,parent,false);
        return new ViewHolder(view);
    }

    /**
     * after created the onCreateViewHolder this method is called for binding the data.
     * @param holder is the group of views.
     * @param position is the current position.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ContactModel listItem = listItems.get(position);

        holder.name.setText(listItem.getName());
        holder.lastName.setText(listItem.getLastName());
        holder.phoneNumber.setText(listItem.getPhoneNumber());
        if (listItem.isDeleteBtnVisible()){
            holder.deleteBtn.setVisibility(View.VISIBLE);
        }
        else{
            holder.deleteBtn.setVisibility(View.GONE);
        }

    }

    /**
     * @return return how many items have the recyclerView.
     */
    @Override
    public int getItemCount() {
        return listItems.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        public TextView name;
        public TextView lastName;
        public TextView phoneNumber;
        public Button deleteBtn;


        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.nameContactText);
            phoneNumber = (TextView) itemView.findViewById(R.id.phoneText);
            lastName = (TextView) itemView.findViewById(R.id.lastName);
            deleteBtn = (Button) itemView.findViewById(R.id.deleteBtn);
            deleteBtn.setOnClickListener(this);
            deleteBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == deleteBtn.getId()){

                //delete item in recycleview
                int position = getAdapterPosition();
                RubricaActivity.dbHelper.deleteRowDatabase(listItems.get(position).getName(),listItems.get(position).getPhoneNumber());
                listItems.remove(position);
                notifyItemRemoved(position);
            } else {
                Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }


}
