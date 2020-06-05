package com.example.fit;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    // Some main points about the app
    // By making use of intents or the Fragment supp Manager the app allows the user to navigate from one 'page to another'
    // When needed to do so - Data is passed on to said Activities or Fragments by making use of Extras for said Intents or making use of Bundles

    Timer timer;
    private static final String TAG ="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView botnav = findViewById(R.id.bottom_nav);               // View used for bottom nav - Material
        botnav.setOnNavigationItemSelectedListener(clicky);                        //sets nav item selection listener
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont,
               new exerciseFragment()).addToBackStack(null).commit();        // Fragment manager used to fill in the container in the Mainactivity with the given Fragment

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
        }else{
        }

    Toolbar toolbar = findViewById(R.id.toolbar1);       //  Toolbar is being used as an actionbar
    setSupportActionBar(toolbar);

    }

    // Method used for the bottom nav - on click, depending on the item click replaces the fragment container in the Main Activity
    // With the appropriate Fragment

    private BottomNavigationView.OnNavigationItemSelectedListener clicky = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();
            Fragment selectedFragment = null;
            if(id == R.id.foods){
                selectedFragment = new Food();

                Toast.makeText(getApplicationContext(), "Recipe",Toast.LENGTH_SHORT).show();

            }else if(id == R.id.excercises) {
                selectedFragment = new exerciseFragment();

                Toast.makeText(getApplicationContext(), "Exercise", Toast.LENGTH_SHORT).show();
            }
            else if(id == R.id.Homepg){
                selectedFragment = new Home();
                Toast.makeText(getApplicationContext(), "Home",Toast.LENGTH_SHORT).show();
            }else{
                selectedFragment = new Ingredient();

                Toast.makeText(getApplicationContext(), "Ingredient",Toast.LENGTH_SHORT).show();
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont,selectedFragment).addToBackStack(null).commit();
            return true;
        }
    };

        // Inflates the toolbar with the selected menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }
    exerciseFragment ExcerciseFragment = new exerciseFragment();


//
//    private NavigationView.OnNavigationItemSelectedListener listen = new NavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            return false;
//        }
//    };

    // On click Method for the menu items used in the toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();
        Fragment selectedFragment = null;
        if(id == R.id.Add_ingredient_menu){
            selectedFragment = new frag_AddIngredient();
            Toast.makeText(getApplicationContext(), "Ingredient",Toast.LENGTH_SHORT).show();

        }else if(id == R.id.Add_Exercise_menu) {
            selectedFragment = new Add_Exercise();
            Toast.makeText(getApplicationContext(), "Exercise", Toast.LENGTH_SHORT).show();
        }

        else{
            selectedFragment = new add_food();
            Toast.makeText(getApplicationContext(), "Food",Toast.LENGTH_SHORT).show();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont,selectedFragment).addToBackStack(null).commit();
       return true;
    }

}
