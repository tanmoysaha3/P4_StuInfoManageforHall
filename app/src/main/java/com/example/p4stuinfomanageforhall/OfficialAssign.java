package com.example.p4stuinfomanageforhall;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfficialAssign extends AppCompatActivity {

    Spinner mAdminIdOfficialSpinner;
    Button mConfirmOfficialAssignButton;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser fUser;
    String hallId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_official_assign);

        mAdminIdOfficialSpinner=findViewById(R.id.adminIdOfficialSpinner);
        mConfirmOfficialAssignButton=findViewById(R.id.confirmOfficialAssignButton);

        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        fUser=fAuth.getCurrentUser();

        String email=fUser.getEmail();
        String documentId=email.substring(0,email.indexOf("@"));

        DocumentReference docRef=fStore.collection("Verified Admins").document(documentId);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                hallId=value.getString("AssignedHall");
                Toast.makeText(OfficialAssign.this, ". "+hallId+" .", Toast.LENGTH_SHORT).show();

                Query officialQuery=fStore.collection("Verified Admins").whereEqualTo("IsAdmin","0");
                List<String> officials = new ArrayList<>();
                ArrayAdapter<String> officialAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, officials);
                officialAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mAdminIdOfficialSpinner.setAdapter(officialAdapter);
                officialQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String official = document.getString("DocumentId");
                                officials.add(official);
                            }
                            officialAdapter.notifyDataSetChanged();
                        }
                    }
                });

                mConfirmOfficialAssignButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String adminId=mAdminIdOfficialSpinner.getSelectedItem().toString();
                        DocumentReference docRef=fStore.collection("Verified Admins").document(adminId);
                        Map<String,Object> edited=new HashMap<>();
                        edited.put("IsAdmin","3");
                        edited.put("Role","Official");
                        edited.put("AssignedHall",hallId);
                        docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("TAG","onSuccess : user profile is created for " + adminId);
                                Toast.makeText(OfficialAssign.this, "Profile updated", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }
        });
    }
}