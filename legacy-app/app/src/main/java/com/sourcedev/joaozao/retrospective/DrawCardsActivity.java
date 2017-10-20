package com.sourcedev.joaozao.retrospective;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sourcedev.joaozao.retrospective.model.RetrospectiveModel;
import com.sourcedev.joaozao.retrospective.ui.ShamRockTinderCardView;
import com.sourcedev.joaozao.retrospective.ui.ShamRockTinderStackLayout;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class DrawCardsActivity extends AppCompatActivity {

    private ArrayList<RetrospectiveModel> mRetrospectiveModelList;

    // region Constants
    private static final int STACK_SIZE = 4;
    // endregion

    // region Views
    private ShamRockTinderStackLayout tinderStackLayout;
    // endregion

    private int index = 0;
    // endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_cards);

        mRetrospectiveModelList = getIntent().getParcelableArrayListExtra("shamrockerlist");

        tinderStackLayout = (ShamRockTinderStackLayout) findViewById(R.id.tsl);

        ShamRockTinderCardView tc;
        for (int i = index; index < i + STACK_SIZE; index++) {
            tc = new ShamRockTinderCardView(this);
            tc.bind(mRetrospectiveModelList.get(index));
            tinderStackLayout.addCard(tc);
        }

        tinderStackLayout.getPublishSubject()
                .observeOn(AndroidSchedulers.mainThread()) // UI Thread
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        if (integer == 1) {
                            ShamRockTinderCardView tc;
                            for (int i = index; index < i + (STACK_SIZE - 1); index++) {
                                tc = new ShamRockTinderCardView(DrawCardsActivity.this);
                                tc.bind(mRetrospectiveModelList.get(index));
                                tinderStackLayout.addCard(tc);
                            }
                        }
                    }
                });

    }
}
