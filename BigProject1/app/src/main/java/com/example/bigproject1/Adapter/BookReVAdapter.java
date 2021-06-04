package com.example.bigproject1.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject1.EditActivity;
import com.example.bigproject1.Model.Book;
import com.example.bigproject1.R;

import java.util.List;

public class BookReVAdapter extends RecyclerView.Adapter<BookReVAdapter.BookViewHolder> {

    List<Book> listBook;

    public void BookReVAdapter(){

    }

    public void BookReVAdapter(List<Book> listBook){
        this.listBook=listBook;
    }

    public List<Book> getListBook(){
        return listBook;
    }

    public void setListBooks(List<Book> listBook){
        this.listBook=listBook;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public BookReVAdapter.BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_home,parent,false);
        return new BookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book b = listBook.get(position);
        if (b!=null){
            holder.txtIdName.setText(b.getId()+"-"+b.getTitle());
            holder.txtAuthor.setText(b.getAuthor());
            holder.txtType.setText(b.getType());
            holder.txtDate.setText(b.getDate());
            holder.txtPrice.setText(b.getPrice()+"");
            holder.itemClickListener = new ItemClickListener() {
                @Override
                public void onClick(View v, int position) {
                    Intent intent = new Intent(v.getContext(), EditActivity.class);
                    intent.putExtra("book",b);
                    v.getContext().startActivity(intent);
                }
            };

        }
    }

    @Override
    public int getItemCount() {
        if (listBook!=null){
            return listBook.size();
        }
        return 0;
    }

    public interface ItemClickListener{
        public void onClick(View v, int position);
    }
    public class BookViewHolder extends RecyclerView.ViewHolder {
        ItemClickListener itemClickListener;
        TextView txtIdName,txtAuthor,txtType,txtDate,txtPrice;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIdName = itemView.findViewById(R.id.txtIdTitle);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtType = itemView.findViewById(R.id.txtType);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onClick(v,getAdapterPosition());
                }
            });

        }
    }
}
