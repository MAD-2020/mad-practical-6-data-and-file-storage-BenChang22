package sg.edu.np.week_6_whackamole_3_0;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private EditText name;
    private EditText password;
    private Button cancel;
    private Button create;
    MyDBHandler db;
    UserData data;
    AlertDialog.Builder builder;
    /* Hint:
        1. This is the create new user page for user to log in
        2. The user can enter - Username and Password
        3. The user create is checked against the database for existence of the user and prompts
           accordingly via Toastbox if user already exists.
        4. For the purpose the practical, successful creation of new account will send the user
           back to the login page and display the "User account created successfully".
           the page remains if the user already exists and "User already exist" toastbox message will appear.
        5. There is an option to cancel. This loads the login user page.
     */


    private static final String FILENAME = "Main2Activity.java";
    private static final String TAG = "Whack-A-Mole3.0!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name = findViewById(R.id.user_EditText);
        password = findViewById(R.id.password_editText);
        create = findViewById(R.id.create_button);
        cancel = findViewById(R.id.cancel_button);
        builder = new AlertDialog.Builder(this);
        db = new MyDBHandler(this,null,null,1);

        /* Hint:
            This prepares the create and cancel account buttons and interacts with the database to determine
            if the new user created exists already or is new.
            If it exists, information is displayed to notify the user.
            If it does not exist, the user is created in the DB with default data "0" for all levels
            and the login page is loaded.

            Log.v(TAG, FILENAME + ": New user created successfully!");
            Log.v(TAG, FILENAME + ": User already exist during new user creation!");

         */
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              UserData us = db.findUser(name.getText().toString());
                if(us == null){
                    UserData data = new UserData();
                   data.setMyUserName(name.getText().toString());
                   data.setMyPassword(password.getText().toString());
                    db.addUser(data);
                   successful();
                   Intent intent = new Intent(Main2Activity.this,MainActivity.class);
                   startActivity(intent);
                }
                else{
                    existingAccount();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Do you want to go back to main menu ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(Main2Activity.this,MainActivity.class);
                                startActivity(intent);
                            }
                        }) .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Go back to main menu");
                alert.show();
            }
        });
    }

    protected void onStop() {
        super.onStop();
        finish();
    }

    public void existingAccount(){
        Toast toast = Toast.makeText(getApplicationContext(),
                "User already exist\nPlease Try again.",
                Toast.LENGTH_SHORT);

        toast.show();
    }

    public void successful(){
        Toast toast = Toast.makeText(getApplicationContext(),
                "User creation successful",
                Toast.LENGTH_SHORT);

        toast.show();
    }
}
