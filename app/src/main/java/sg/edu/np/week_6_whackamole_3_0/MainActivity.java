package sg.edu.np.week_6_whackamole_3_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*
        1. This is the main page for user to log in
        2. The user can enter - Username and Password
        3. The user login is checked against the database for existence of the user and prompts
           accordingly via Toastbox if user does not exist. This loads the level selection page.
        4. There is an option to create a new user account. This loads the create user page.
     */
    private static final String FILENAME = "MainActivity.java";
    private static final String TAG = "Whack-A-Mole3.0!";
    private EditText user;
    private EditText passEdit;
    private Button login;
    private TextView signUp;
    MyDBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Hint:
            This method creates the necessary login inputs and the new user creation ontouch.
            It also does the checks on button selected.
            Log.v(TAG, FILENAME + ": Create new user!");
            Log.v(TAG, FILENAME + ": Logging in with: " + etUsername.getText().toString() + ": " + etPassword.getText().toString());
            Log.v(TAG, FILENAME + ": Valid User! Logging in");
            Log.v(TAG, FILENAME + ": Invalid user!");

        */
        user = findViewById(R.id.user_EditText);
        passEdit = findViewById(R.id.password_editText);
        login = findViewById(R.id.login_button);
        signUp = findViewById(R.id.signUp);
        db = new MyDBHandler(this,null,null,1);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidUser(user.getText().toString(),passEdit.getText().toString())) {
                    Intent intent = new Intent(MainActivity.this,Main3Activity.class);
                    startActivity(intent);
                }
                else
                {
                    user.setText("");
                    passEdit.setText("");
                    invalidEntry();
                }
            }
        });

        onClick(signUp);


    }

    protected void onStop(){
        super.onStop();
        finish();
    }

    public boolean isValidUser(String userName, String password) {
        UserData us = db.findUser(user.getText().toString());
        if (us != null) {
            if (us.getMyUserName().equals(user.getText().toString()) && us.getMyPassword().equals(passEdit.getText().toString()))
                ;
            {
                return true;
            }

        }
        else {
            return false;
        }

    }


    public void invalidEntry(){
        Toast toast = Toast.makeText(getApplicationContext(),
                "Invalid username or password",
                Toast.LENGTH_SHORT);

        toast.show();
    }

    public void onClick(View view){
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            /*intent to go to sign up page*/
            public void onClick(View v) {
                Intent signUp =  new Intent(MainActivity.this,Main2Activity.class);
                Log.v(TAG,"signing up");
                startActivity(signUp);
            }
        });

    }

}
