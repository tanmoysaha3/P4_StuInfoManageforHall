package com.example.p4stuinfomanageforhall;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.firebase.ui.firestore.paging.LoadingState;
import com.google.firebase.firestore.DocumentSnapshot;

public class FirestoreAdapterStudent extends FirestorePagingAdapter<ModelStudent, FirestoreAdapterStudent.StudentModelHolder> {

    private FirestoreAdapterStudent.OnListItemClick onListItemClick;

    public FirestoreAdapterStudent(@NonNull FirestorePagingOptions<ModelStudent> options, FirestoreAdapterStudent.OnListItemClick onListItemClick) {
        super(options);
        this.onListItemClick=onListItemClick;
    }

    @Override
    protected void onBindViewHolder(@NonNull FirestoreAdapterStudent.StudentModelHolder holder, int position, @NonNull ModelStudent model) {
        holder.item_id.setText(model.getStudentID());
        holder.item_name.setText(model.getFull_Name());
        holder.item_details.setText(model.getAddress());
    }

    @NonNull
    @Override
    public FirestoreAdapterStudent.StudentModelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single,parent,false);
        return new FirestoreAdapterStudent.StudentModelHolder(view);
    }

    @Override
    protected void onLoadingStateChanged(@NonNull LoadingState state) {
        super.onLoadingStateChanged(state);
        switch (state) {
            case LOADING_INITIAL:
                Log.d("PAGING_LOG","Loading Initial Page");
                break;
            case LOADING_MORE:
                Log.d("PAGING_LOG","Loading Next Page");
                break;
            case FINISHED:
                Log.d("PAGING_LOG","All Data Loaded");
                break;
            case ERROR:
                Log.d("PAGING_LOG","Total Loading Data");
                break;
            case LOADED:
                Log.d("PAGING_LOG","Total Items Loaded : "+getItemCount());
                break;
        }
    }

    public class StudentModelHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView item_id;
        TextView item_name;
        TextView item_details;
        public StudentModelHolder(@NonNull View itemView) {
            super(itemView);
            item_id=itemView.findViewById(R.id.itemId);
            item_name=itemView.findViewById(R.id.itemName);
            item_details=itemView.findViewById(R.id.itemDetails);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onListItemClick.onItemClick(getItem(getAdapterPosition()),getAdapterPosition());
        }
    }

    public interface OnListItemClick {
        void onItemClick(DocumentSnapshot snapshot, int position);
    }
}
