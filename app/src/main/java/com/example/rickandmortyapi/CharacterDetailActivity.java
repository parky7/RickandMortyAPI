package com.example.rickandmortyapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CharacterDetailActivity extends AppCompatActivity {
    private TextView name, status, species, type, gender;
    private ImageView image;
    private Character character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);
        Intent lastIntent = getIntent();
        character = lastIntent.getParcelableExtra(MainActivity.EXTRA_POSITION);
        wireWidgets();
        name.setText(character.getName());
        status.setText(character.getStatus());
        species.setText(character.getSpecies());
        type.setText(character.getType());
        gender.setText(character.getGender());
        Picasso.get().load(character.getImage()).into(image);

    }

    private void wireWidgets() {
        name = findViewById(R.id.textView_characterDetail_name);
        status = findViewById(R.id.textView_characterDetail_status);
        species = findViewById(R.id.textView_characterDetail_species);
        type = findViewById(R.id.textView_characterDetail_type);
        gender = findViewById(R.id.textView_characterDetail_gender);
        image = findViewById(R.id.imageView_characterDetail_picture);
    }
}
