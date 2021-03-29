package nl.avans.netnietflix.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nl.avans.netnietflix.DetailActivity;
import nl.avans.netnietflix.R;
import nl.avans.netnietflix.domain.BBCharacter;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterViewHolder>{
    private final String TAG = getClass().getSimpleName();
    final static String INTENT_EXTRA_CHARACTER = "character";
    private List<BBCharacter> characters;
    private List<BBCharacter> charactersOriginal;
    private Context context;

    public CharacterAdapter(List<BBCharacter> characters,Context context) {
        //De lijst met characters en de context (hier de Mainclass)
        this.characters = characters;
        charactersOriginal = new ArrayList<BBCharacter>();
        charactersOriginal.addAll(characters);
        this.context = context;
        Log.d(TAG,"Constructor is called");
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //De methode die aangeroepen wordt als de viewholder wordt gemaakt
        Log.d(TAG,"onCreateViewholder is called");
        Context context = parent.getContext();
        //Het id van het listitem wordt opgevraagd en "gemaakt" dus echt gecreate
        int layoutIdForListItem = R.layout.character_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent,shouldAttachToParentImmediately);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        //Hier wordt de data in de viewholder gezet
        Log.d(TAG,"onBindViewHolder is called");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Als er op de viewholder geklikt wordt, wordt de gebruiker doorgestuurd naar de detailpagina
                //BBCharacter wordt hier gecast als Serializable om hem te kunnen passen met de intent.
                Log.d(TAG,"onClick is called");
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(INTENT_EXTRA_CHARACTER, (Serializable) characters.get(position));
                context.startActivity(intent);
            }
        });
        BBCharacter character = characters.get(position);
        holder.characterListItemName.setText(character.getName());
        //Elk stuk tekst (behalve de naam) wordt gemaakt met een string uit Strings.xml. Hier moet de context voor worden gebruikt, omdat data ophalen uit R waarschijnlijk alleen kan in een activity
        holder.characterListItemNickName.setText(String.format("%s: %s","context.getString(R.string.nickname)",character.getNickname()));
        holder.characterListItemStatus.setText(String.format("%s: %s","context.getString(R.string.status)",character.getStatus()));
        Picasso.get().load(characters.get(position).getImgLink()).resize(350,500).into(holder.characterListItemImage);
    }

    //Hier de filters
    public void filterOnStatus(String query) {
        Log.d(TAG,"filterOnStatus has been called");
        if (!query.isEmpty()) {
            if(!characters.equals(charactersOriginal)){
                characters.clear();
                characters.addAll(charactersOriginal);
            }
            ArrayList<BBCharacter> filteredList = new ArrayList<>();
            query = query.toLowerCase();
            for (BBCharacter character : characters) {
                if (character.getStatus().toLowerCase().contains(query)) {
                    filteredList.add(character);
                }
            }
            characters.clear();
            characters.addAll(filteredList);
            this.notifyDataSetChanged();
        }
    }
    public void resetCharactersInRecyclerView(){
        Log.d(TAG,"resetCharactersInRecyclerView has been called");
        if(!characters.equals(charactersOriginal)){
            characters.clear();
            characters.addAll(charactersOriginal);
            this.notifyDataSetChanged();
        }
    }


    @Override
    public int getItemCount() {
        Log.d(TAG,"getItemCount is called");
        return characters.size();
    }
}
