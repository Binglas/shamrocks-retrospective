package com.sourcedev.joaozao.retrospective;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sourcedev.joaozao.retrospective.model.RetrospectiveModel;
import com.sourcedev.joaozao.retrospective.model.ShamrockersModel;
import java.util.ArrayList;

public class RetrospectiveBoard extends AppCompatActivity {

  private static final String TAG = MainActivity.class.getSimpleName();
  private EditText inputPhrase;
  private Button btnPlus, btnMinus, btnReady;
  private FirebaseDatabase mFirebaseInstance;
  private ShamrockersModel mShamrockersModel;

  private ArrayList<RetrospectiveModel> mRetrospectiveModelList;

  private String databaseitemId;
  private DatabaseReference mDatabaseItem;
  private RelativeLayout rlWaiting;

  private boolean isReady = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_retrospective_board);

    // Displaying toolbar icon
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setIcon(R.mipmap.ic_launcher);

    mRetrospectiveModelList = new ArrayList<>();

    rlWaiting = (RelativeLayout) findViewById(R.id.rl_waiting);
    inputPhrase = (EditText) findViewById(R.id.Et_plusminus);
    btnPlus = (Button) findViewById(R.id.btn_plus);
    btnMinus = (Button) findViewById(R.id.btn_minus);
    btnReady = (Button) findViewById(R.id.btn_ready);

    mShamrockersModel = getIntent().getParcelableExtra("shamrocker");

    mFirebaseInstance = FirebaseDatabase.getInstance();

    // get reference to 'retrospectiveItem' node
    mDatabaseItem = mFirebaseInstance.getReference("retrospectiveItem");
    mDatabaseItem.keepSynced(true);

    btnReady.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        isReady = !isReady;
        for (RetrospectiveModel retrospective : mRetrospectiveModelList) {
          if (retrospective.getName().equals(mShamrockersModel.getName())) {
            retrospective.setReady(isReady);
            mDatabaseItem.child(retrospective.getID()).child("ready").setValue(isReady);
          }
        }
        rlWaiting.setVisibility(isReady ? View.VISIBLE:View.GONE);
        btnReady.setAlpha(isReady ? (float) 0.5:(float) 1);
      }
    });

    // store app title to 'app_title' node
    //mFirebaseInstance.getReference("app_title").setValue("Realtime Database");


    mDatabaseItem.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        boolean allReady = false;
        int i = 0;

        if (dataSnapshot.hasChildren()) {
          allReady = true;
        }

        mRetrospectiveModelList.clear();

        for (DataSnapshot data : dataSnapshot.getChildren()) {
          mRetrospectiveModelList.add(i++, data.getValue(RetrospectiveModel.class));
          mRetrospectiveModelList.get(i - 1).setID(data.getKey());
          if (!mRetrospectiveModelList.get(i - 1).isReady()) {
            allReady = false;
          }
        }

        if (allReady) {
          Intent intent = new Intent(RetrospectiveBoard.this, DrawCardsActivity.class);
          intent.putParcelableArrayListExtra("shamrockerlist", mRetrospectiveModelList);
          startActivity(intent);
        }
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }
    });


    // app_title change listener
    mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        Log.e(TAG, "App title updated");

        String appTitle = dataSnapshot.getValue(String.class);

        // update toolbar title
        getSupportActionBar().setTitle(appTitle);
      }

      @Override
      public void onCancelled(DatabaseError error) {
        // Failed to read value
        Log.e(TAG, "Failed to read app title value.", error.toException());
      }
    });

    // Save / update the user
    btnPlus.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String plusPhrase = inputPhrase.getText().toString();

        // Check for already existed userId
        if (TextUtils.isEmpty(databaseitemId)) {
          insertRetrospectiveMinusPlus(plusPhrase, true);
        }
      }
    });

    btnMinus.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        String plusPhrase = inputPhrase.getText().toString();

        // Check for already existed userId
        if (TextUtils.isEmpty(databaseitemId)) {
          insertRetrospectiveMinusPlus(plusPhrase, false);
        }
      }
    });

  }


  /**
   * Creating new user node under 'users'
   */
  private void insertRetrospectiveMinusPlus(String phrase, boolean plus) {
    // TODO
    // In real apps this userId should be fetched
    // by implementing firebase auth
    if (TextUtils.isEmpty(databaseitemId)) {
      databaseitemId = mDatabaseItem.push().getKey();
    }

    RetrospectiveModel retrospectiveModel = new RetrospectiveModel(phrase, plus);
    retrospectiveModel.setName(mShamrockersModel.getName());
    retrospectiveModel.setThumbnail(mShamrockersModel.getThumbnail());

    mDatabaseItem.child(databaseitemId).setValue(retrospectiveModel);



  }


  private void updateUser(String name, String email) {
    // updating the user via child nodes
    if (!TextUtils.isEmpty(name)) {
      //mMinusFirebaseDatabase.child(minusRetrospectiveId).child("name");
    }

    if (!TextUtils.isEmpty(email)) {
      //mMinusFirebaseDatabase.child(minusRetrospective).child("email").setValue(email);
    }
  }
}