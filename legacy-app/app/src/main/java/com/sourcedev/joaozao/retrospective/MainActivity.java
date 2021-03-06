package com.sourcedev.joaozao.retrospective;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.sourcedev.joaozao.retrospective.model.ShamrockersModel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  private RecyclerView mRecyclerView;
  private ShamRockersAdapter adapter;
  private List<ShamrockersModel> mShamrockersModelList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

    mShamrockersModelList = new ArrayList<>();
    adapter = new ShamRockersAdapter(this, mShamrockersModelList);

    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    mRecyclerView.setAdapter(adapter);

    prepareShamrockers();

  }

  /**
   * Adding few shamrockers for testing
   */
  private void prepareShamrockers() {
    int[] covers = new int[]{
        R.drawable.shamrockercarlos,
        R.drawable.shamrockerpatricia,
        R.drawable.shamrockerbruno,
        R.drawable.shamrockerfilipa,
        R.drawable.shamrockerjoao,
        R.drawable.shamrockerzao,
        R.drawable.shamrockerjorge,
        R.drawable.shamrockerrui,
        R.drawable.shamrockerwilliam,
        R.drawable.shamrockerzao,
        R.drawable.shamrockerzao};

    ShamrockersModel a = new ShamrockersModel("Carlos Jesus", "Eternal Sensei", covers[0]);
    mShamrockersModelList.add(a);

    a = new ShamrockersModel("Patricia Coelho", "PO", covers[1]);
    mShamrockersModelList.add(a);

    a = new ShamrockersModel("Bruno Araujo", "Eternal Sensei", covers[2]);
    mShamrockersModelList.add(a);

    a = new ShamrockersModel("Filipa Santos", "Fake Buddy", covers[3]);
    mShamrockersModelList.add(a);

    a = new ShamrockersModel("João Ferreira", "Eternal Sensei", covers[4]);
    mShamrockersModelList.add(a);

    a = new ShamrockersModel("João Zão", "Eternal Sensei", covers[5]);
    mShamrockersModelList.add(a);

    a = new ShamrockersModel("Jorge Rocha", "Eternal Sensei", covers[6]);
    mShamrockersModelList.add(a);

    a = new ShamrockersModel("Rui Alves", "Eternal Sensei", covers[7]);
    mShamrockersModelList.add(a);

    a = new ShamrockersModel("William Soares", "Eternal Sensei", covers[8]);
    mShamrockersModelList.add(a);


    adapter.notifyDataSetChanged();
  }


  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_camera) {
      // Handle the camera action
    } else if (id == R.id.nav_gallery) {

    } else if (id == R.id.nav_slideshow) {

    } else if (id == R.id.nav_manage) {

    } else if (id == R.id.nav_share) {

    } else if (id == R.id.nav_send) {

    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  /**
   * RecyclerView item decoration - give equal margin around grid item
   */
  public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;
    private boolean includeEdge;

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
      this.spanCount = spanCount;
      this.spacing = spacing;
      this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
      int position = parent.getChildAdapterPosition(view); // item position
      int column = position % spanCount; // item column

      if (includeEdge) {
        outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
        outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

        if (position < spanCount) { // top edge
          outRect.top = spacing;
        }
        outRect.bottom = spacing; // item bottom
      } else {
        outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
        outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
        if (position >= spanCount) {
          outRect.top = spacing; // item top
        }
      }
    }
  }

  /**
   * Converting dp to pixel
   */
  private int dpToPx(int dp) {
    Resources r = getResources();
    return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
  }
}
