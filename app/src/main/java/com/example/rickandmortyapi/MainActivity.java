package com.example.rickandmortyapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private ArrayList<Character> charactersList;
    private int characterNumber;
    private ListView characterListView;
    private TextView textView_name, textView_species;
    private CharacterAdapter characterAdapter;
    public static final String EXTRA_POSITION = "position";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();
        charactersList = new ArrayList<>();




        characterAdapter = new CharacterAdapter(charactersList);
        characterListView.setAdapter(characterAdapter);
        getCharacterList();

        setOnclickListeners();
    }

    private void setOnclickListeners() {
        characterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Character characterClicked = charactersList.get(position);
                Intent listViewClicked = new Intent(MainActivity.this, CharacterDetailActivity.class);
                listViewClicked.putExtra(EXTRA_POSITION, characterClicked);
                startActivity(listViewClicked);
            }
        });
    }

    private void getCharacterList() {


        for(int i = 0; i < 19;i++) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(CharacterService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            CharacterService characterService = retrofit.create(CharacterService.class);
            characterNumber = i;

            Call<Character> characterCall = characterService.getCharacterByNumber(String.valueOf(i));
            characterCall.enqueue(new Callback<Character>() {
                @Override
                public void onResponse(Call<Character> call, Response<Character> response) {
                    // ANY CODE THAT DEPENDS ON THE RESULTS OF THE SEARCH HAS TO GO HERE
                    Character foundCharacter = response.body();
                    //check if body is null
                    if (foundCharacter != null) {
                        Log.d("test", "onResponse: " + foundCharacter.getName());

                        characterAdapter.characterList.add(foundCharacter);
                        characterAdapter.notifyDataSetChanged();

                    }
                }

                @Override
                public void onFailure(Call<Character> call, Throwable t) {
                    //Toast the Failure
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("test", "onFailure: " + t.getMessage());

                }
            });
        }
    }

    private void wireWidgets() {
        characterListView = findViewById(R.id.listView_main_characterlist);
    }
    private class CharacterAdapter extends ArrayAdapter<Character> {
        private ArrayList<Character> characterList;
        public CharacterAdapter(ArrayList<Character> characterList) {
            //since we're in the HeroListActivity class, we already have the context
            // we're hardcoding in a particular layout, so don't need to put it in the
            // constructer either
            // we'll send a place holder resource to the superclass of -1
            super(MainActivity.this, -1, characterList);
            this.characterList = characterList;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            // 1. inflate layout
            LayoutInflater inflater = getLayoutInflater();
            if(convertView == null){
                convertView = inflater.inflate(R.layout.item_character, parent, false);
            }
            // 2. wire widgets & link the hero to those widgets
            textView_name = convertView.findViewById(R.id.textView_main_characterName);
            textView_species = convertView.findViewById(R.id.textView_main_characterSpecies);


            // set values for each widget. use the position parameter variable
            // to get the hero that you need out of the list
            // and set the values for the widgets
            textView_name.setText(characterList.get(position).getName());
            textView_species.setText(String.valueOf(characterList.get(position).getSpecies()));


            //3. return inflated view
            return convertView;

        }
    }
}
