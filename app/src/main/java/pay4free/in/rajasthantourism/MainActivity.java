package pay4free.in.rajasthantourism;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    KenBurnsView kenBurnsView;
    FirebaseRecyclerAdapter<CenterShow,CentreViewHolder> adapter;
    FirebaseDatabase database;
    DatabaseReference centrelist;
    TextView name;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       kenBurnsView=(KenBurnsView)findViewById(R.id.top_image) ;
        database=FirebaseDatabase.getInstance();
        centrelist=database.getReference("Category");
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
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        loadlistcenter();
    }
    private void loadlistcenter() {

        adapter=new FirebaseRecyclerAdapter<CenterShow, CentreViewHolder>(CenterShow.class,R.layout.centrelist,CentreViewHolder.class,centrelist) {
            @Override
            protected void populateViewHolder(CentreViewHolder viewHolder, CenterShow model, int position) {
                viewHolder.centrename.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.centreimage);
                final CenterShow clickitem=model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        // Toast.makeText(Navigationmenu.this,""+clickitem.getName(),Toast.LENGTH_SHORT).show();
                        if(position==0){
                        Intent catList=new Intent(MainActivity.this, FoodDetail.class);
                       catList.putExtra("CategoryId","https://en.wikipedia.org/wiki/Amer_Fort");
                      startActivity(catList);

}
                            if(position==1){
                            Intent catList=new Intent(MainActivity.this, FoodDetail.class);
                            catList.putExtra("CategoryId","https://en.wikipedia.org/wiki/Mehrangarh_Fort");
                            startActivity(catList);}if(position==2){
                            Intent catList=new Intent(MainActivity.this, FoodDetail.class);
                            catList.putExtra("CategoryId","https://en.wikipedia.org/wiki/Umaid_Bhawan_Palace");
                            startActivity(catList);}if(position==3){
                            Intent catList=new Intent(MainActivity.this, FoodDetail.class);
                            catList.putExtra("CategoryId","https://en.wikipedia.org/wiki/Jaisalmer_Fort");
                            startActivity(catList);}if(position==4){
                            Intent catList=new Intent(MainActivity.this, FoodDetail.class);
                            catList.putExtra("CategoryId","https://en.wikipedia.org/wiki/Jal_Mahal");
                            startActivity(catList);}if(position==5){
                            Intent catList=new Intent(MainActivity.this, FoodDetail.class);
                            catList.putExtra("CategoryId","https://en.wikipedia.org/wiki/Ranthambore_Fort");
                            startActivity(catList);}if(position==6){
                            Intent catList=new Intent(MainActivity.this, FoodDetail.class);
                            catList.putExtra("CategoryId","https://en.wikipedia.org/wiki/Karni_Mata_Temple");
                            startActivity(catList);}if(position==7){
                            Intent catList=new Intent(MainActivity.this, FoodDetail.class);
                            catList.putExtra("CategoryId","https://en.wikipedia.org/wiki/Hawa_Mahal");
                            startActivity(catList);}if(position==8){
                            Intent catList=new Intent(MainActivity.this, FoodDetail.class);
                            catList.putExtra("CategoryId","https://en.wikipedia.org/wiki/Jantar_Mantar");
                            startActivity(catList);}if(position==9){
                            Intent catList=new Intent(MainActivity.this, FoodDetail.class);
                            catList.putExtra("CategoryId","https://en.wikipedia.org/wiki/Albert_Hall_Museum");
                            startActivity(catList);}if(position==10){
                            Intent catList=new Intent(MainActivity.this, FoodDetail.class);
                            catList.putExtra("CategoryId","https://en.wikipedia.org/wiki/Junagarh_Fort");
                            startActivity(catList);}
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
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
            startActivity(new Intent(MainActivity.this,Agent.class));

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
