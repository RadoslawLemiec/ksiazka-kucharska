package com.example.klaudia.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;

import java.util.HashMap;

public class ComplexSearch extends AppCompatActivity
{
    GridLayout gridLayout;
    Button search;
    EditText query;
    EditText cuisine, diet, type, excludeIngredients, includeIngredients, intolerances, minCalories, maxCalories,
            minCarbs, maxCarbs, minFat, maxFat, minProteins, maxProteins;
    Searcher searcher;
    HashMap<String, String> hashMapsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_search);

        search = (Button) findViewById(R.id.buttonComplexSearch);
        cuisine = (EditText) findViewById(R.id.cuisineET);
        query = (EditText) findViewById(R.id.queryET);
        diet = (EditText) findViewById(R.id.dietET);
        type = (EditText) findViewById(R.id.typeET);
        excludeIngredients = (EditText) findViewById(R.id.excludeIngredientsET);
        includeIngredients = (EditText) findViewById(R.id.includeIngredientsET);
        intolerances = (EditText) findViewById(R.id.intolerancesET);
        minCalories = (EditText) findViewById(R.id.minCaloriesET);
        maxCalories = (EditText) findViewById(R.id.maxCaloriesET);
        minCarbs = (EditText) findViewById(R.id.minCarbsET);
        maxCarbs = (EditText) findViewById(R.id.maxCarbsET);
        minFat = (EditText) findViewById(R.id.minFatET);
        maxFat = (EditText) findViewById(R.id.maxFatET);
        minProteins = (EditText) findViewById(R.id.minProteinsET);
        maxProteins = (EditText) findViewById(R.id.maxProteinsET);

        hashMapsearch = new HashMap<String, String>();
        gridLayout = (GridLayout) findViewById(R.id.gridLayoutComplexSearch);
        searcher = ((MyApplication) this.getApplication()).getSearcher();

        search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (query.getText().toString().length() == 0)
                {
                    query.requestFocus();
                    query.setError("Tags is required!");
                }


                else
                {
                    for (int i = 0; i < gridLayout.getChildCount(); i++)
                    {
                        if ((gridLayout.getChildAt(i) instanceof EditText) && !((EditText) gridLayout.getChildAt(i)).getText().toString().matches(""))
                        {
                            String reversedKey = new StringBuffer(gridLayout.getChildAt(i).toString()).reverse().toString();
                            String key = new StringBuffer(reversedKey.substring(3, reversedKey.indexOf('/'))).reverse().toString();
                            hashMapsearch.put(key, ((EditText) gridLayout.getChildAt(i)).getText().toString());
                        }

                    }
                    searcher.complexSearch_Titles(hashMapsearch);
                    Intent intent = new Intent(getApplicationContext(), RecipesListView.class);
                    startActivity(intent);
                }


            }
        });

    }
}