package com.example.fit;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Add_Exercise extends Fragment {

                                                            // Fragment which allows the user to create a new exercise by simple entering the vids Id and choose the type
                                                            // The vids id is the string which is present in all youtube video links
                                                            //  youtube.com/watch?v= ___this is the id___
                                                            // This can be improved by simply allowing the user to enter the whole url and parsing the string programmatically

    // Variables
    byte [] holder;
    EditText link;
    Button add;
    String newlink;
    String choice;
    String  Desc;
    String  Title;
    boolean checker = false;
    ImageView img;
    public List<String> temporanja = new ArrayList<>();

    private RequestQueue MyQueue;
    public Add_Exercise() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       // temporanja.add(null);
         MyQueue = Volley.newRequestQueue(getContext());
         // Volley is being used to fetch  json data from a url asynchronously
         // Picasso is used to  obtain an image from a url. In this case the Id which the user enters is appended
        // To a predefined string, then using this string as a url. volley fetches the required image from said string.

          View v = inflater.inflate(R.layout.add_exercise, container, false);
          img = v.findViewById(R.id.placeholderimg);
          link =  v.findViewById(R.id.ytlink);
          add = v.findViewById(R.id.addnewex);

          // Spinner being used to allow users to choose the type of exercise this new exercise should be categorised with.
          Spinner spinner = (Spinner) v.findViewById(R.id.exer_spinner);
         ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.Exercise_Type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Spinner on item selected Listener : when a user selects one of the items in the list, the choice is saved and used later on

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
                    choice = item;
            }
        // If no selection is chosen then the choice is set to PowerLifting by default
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                        choice="PowerLifting";
            }
        });


        // on click listener for the add button , when pressed the getDataJson method is called
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataJson();

            }
        });
        return v;
    }

    // Method which changed the Image from a Drawable to a byte
    public static byte[] imageToByte(Drawable image) {
        Bitmap bitmap = ((BitmapDrawable)image).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
//
//    public static Drawable getImagefromYoutubeId(String id){            // This was a method I was trying to implement which makes use of the InputStream
//        try{                                                            //  later on Ive decided that Picasso would be much more of a good practice
                                                                          // Didnt delete in order to keep reference
//
//            InputStream is = (InputStream) new URL(Stringurl).getContent();
//            Drawable draw2 = Drawable.createFromStream(is, "src name");
//            return  draw2;
//        }catch(Exception ex){
//            return null;
//        }
//
//    }


    // Method used to fetch data from the preset url appended with the vid id the user enters
    public void getDataJson(){
//---------------------------------------------------------------------------------------------------
        newlink = link.getText().toString();
        Toast.makeText(getContext(),newlink,Toast.LENGTH_SHORT).show();

        // Stringurl appends the newlink var (vidis)with  a link which is used to fetch youtube video thumbnails.
        String Stringurl = "https://img.youtube.com/vi/"+newlink+"/mqdefault.jpg";
        Picasso.get().load(Stringurl).into(img, new com.squareup.picasso.Callback(){
            @Override
            public  void onSuccess(){
                holder = imageToByte(img.getDrawable());
            }   // Picasso is then used to display the image and imageToByte method is used to convert the newly set image to a byte[]

            @Override
            public void onError(Exception e) {
                        holder = null;
                Toast.makeText(getContext(),"Error Getting image please try again",Toast.LENGTH_SHORT).show();
                return;
            }
        });

        //--------------------------------------------------------------------------------------------------------------------------------

        final String result1="";
        String result2="";
        Array[] toReturn={};
        String url = "https://www.googleapis.com/youtube/v3/videos?part=snippet&id="+newlink+"&key=AIzaSyD7dZbEqI22VbUAw9M44qG1Uqql-I0oX8s";
        // url is the string containing the url required in order to fetch the youtube's video data as a Json Object.
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            boolean check = false;

            // Volleys onResponse method, On response the required data is obtained from the JsonObject , fetching both the Vid's Title
            // and its Description.
            @Override
            public void onResponse(JSONObject response) {

                try {

                     JSONArray jsonArray = response.getJSONArray("items");
                     Desc = jsonArray.getJSONObject(0).getJSONObject("snippet").getString("description");
                     Title = jsonArray.getJSONObject(0).getJSONObject("snippet").getString("title");


                     if(!Desc.isEmpty() && !Title.isEmpty()){    //If the data is fetched without any errors then the process can continue
                         check=true;
                     }

                    //Toast.makeText(getContext(), Desc, Toast.LENGTH_LONG).show();  // These Toasts show that the Data was fetched correctly and no error has occured
                    //Toast.makeText(getContext(), Title, Toast.LENGTH_LONG).show();
                    Ex_Model exer = new Ex_Model();
                    // A new Exercise Obj is created
                    if (check == true && holder!=null) {
                        try {
                            // If the data was fetched as expected from the Json file and holder ( the image byte array ) is not null then a new Exercise is created
                          //  Toast.makeText(getContext(), "This si the image"+holder.toString(), Toast.LENGTH_LONG).show();
                            checker = true;
                            exer = new Ex_Model(-1, choice, Title, Desc, "100", holder, newlink);
                        } catch (Exception e) {
                            checker = false;
                            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                    // If the process is going as it should and no errors have occured then checker should be true, If true then the newly created exercise is add to the db
                    if(checker == true){
                        DbHelper Dbhelper = new DbHelper(getContext());
                        Dbhelper.InsertDataExe(exer);
                        Toast.makeText(getContext(), exer.toString() + "Exercise created successfully", Toast.LENGTH_SHORT).show();
                        //Dbhelper.close();
                    }else{
                        Toast.makeText(getContext(),  "There has been an error fetching the data, Please check your connection and try again", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
//                    Log.i("vID2",e.toString());
                    e.printStackTrace();                       // On error a stack trace is printed
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        MyQueue.add(request);
        // As one can see from the method above , a lot of checks for errors are made as a lot of things can go bad especially when fetching data online.
        // Should this app be progressed further, this error handling should be looked at once more just to make sure that all errors/ possibilities are accounted for
    }

}

