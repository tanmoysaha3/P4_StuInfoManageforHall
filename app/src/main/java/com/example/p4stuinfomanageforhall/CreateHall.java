package com.example.p4stuinfomanageforhall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateHall extends AppCompatActivity {

    private static final String TAG = "TAG";

    EditText mCreateHallName, mCreateHallId;
    Button mCreateNewHall;
    String userID;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser fUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hall);

        mCreateHallName=findViewById(R.id.createHallName);
        mCreateHallId=findViewById(R.id.createHallId);
        mCreateNewHall=findViewById(R.id.createNewHall);

        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        //userID=fAuth.getCurrentUser().getUid();
        //fUser=fAuth.getCurrentUser();

        mCreateNewHall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hallName=mCreateHallName.getText().toString();
                String hallId=mCreateHallId.getText().toString();

                DocumentReference docRef=fStore.collection("Halls").document(hallId);
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot docSnap=task.getResult();
                            if (docSnap.exists()){
                                Toast.makeText(CreateHall.this, "Hall exists", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "Document exists!");
                            } else {
                                DocumentReference documentReference=fStore.collection("Halls").document(hallId);
                                Map<String,Object> user = new HashMap<>();
                                user.put("Hall_Name", hallName);
                                user.put("Hall_Id", hallId);
                                user.put("Hall_Admin","X");
                                user.put("IsHallAdminAssigned","0");

                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(CreateHall.this, "Hall Created", Toast.LENGTH_SHORT).show();
                                        Log.d("TAG","onSuccess : user profile is created for " + hallId);
                                    }
                                });
                                Log.d(TAG, "Document does not exist!");
                            }
                        } else {
                            Log.d(TAG, "Failed with: ", task.getException());
                        }
                    }
                });
            }
        });
    }
}