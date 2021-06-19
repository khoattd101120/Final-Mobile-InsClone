package fud.student.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView username = findViewById(R.id.username);
        TextView phone = findViewById(R.id.phone);
        TextView email = findViewById(R.id.email);

        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser()!=null){
            if(auth.getCurrentUser().getPhoneNumber()!=null){
                phone.setText("Phone: "+auth.getCurrentUser().getPhoneNumber());
            }
            if(auth.getCurrentUser().getDisplayName()!=null){
                username.setText("Username: "+auth.getCurrentUser().getDisplayName());
            }
            if(auth.getCurrentUser().getEmail()!=null){
                email.setText("Email: "+auth.getCurrentUser().getEmail());
            }
        }
        Button logout = findViewById(R.id.logout_btn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

    }
    public void signOut() {
        // [START auth_fui_signout]
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(ProfileActivity.this,MainActivity.class));
                        finish();
                    }
                });
        // [END auth_fui_signout]
    }
}