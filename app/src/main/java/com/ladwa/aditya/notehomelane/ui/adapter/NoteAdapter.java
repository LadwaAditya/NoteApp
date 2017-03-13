package com.ladwa.aditya.notehomelane.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ladwa.aditya.notehomelane.R;
import com.ladwa.aditya.notehomelane.data.model.Note;
import com.ladwa.aditya.notehomelane.databinding.ListItemNoteBinding;
import com.ladwa.aditya.notehomelane.ui.detail.DetailActivity;

import java.util.List;

/**
 * Created by Aditya on 13-Mar-17.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private List<Note> mNotesList;

    public NoteAdapter(List<Note> mNotesList) {
        this.mNotesList = mNotesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemNoteBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item_note, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Note note = mNotesList.get(position);
        holder.bindNote(note);
    }

    @Override
    public int getItemCount() {
        return mNotesList != null ? mNotesList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ListItemNoteBinding mBinding;
        private Note mNote;


        public ViewHolder(ListItemNoteBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
            this.mBinding.setHolder(this);
        }

        public void bindNote(Note note) {
            this.mNote = note;
            mBinding.setNote(mNote);
            mBinding.executePendingBindings();
        }

        public void onClick(View view) {
            Context context = view.getContext();
            context.startActivity(DetailActivity.getStartIntent(context));
        }
    }
}
