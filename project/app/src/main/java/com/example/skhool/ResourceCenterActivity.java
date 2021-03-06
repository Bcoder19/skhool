package com.example.skhool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ResourceCenterActivity extends AppCompatActivity {

    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_center);
    }


    public void openResourceCategoryActivity1(View view) {
        Intent intentToOpenResourceCategoryActivity = new Intent(this,ResourceCategoryActivity.class);
        intentToOpenResourceCategoryActivity.putExtra("title","Scholarships");

        startActivity(intentToOpenResourceCategoryActivity);
    }
    public void openResourceCategoryActivity2(View view) {
        Intent intentToOpenResourceCategoryActivity = new Intent(this,ResourceCategoryActivity.class);
        intentToOpenResourceCategoryActivity.putExtra("title","Exam Prep");

        startActivity(intentToOpenResourceCategoryActivity);
    }

    public void openResourceCategoryActivity3(View view) {
        Intent intentToOpenResourceCategoryActivity = new Intent(this,ResourceCategoryActivity.class);
        intentToOpenResourceCategoryActivity.putExtra("title","College Planning");

        startActivity(intentToOpenResourceCategoryActivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (fAuth.getCurrentUser()!=null){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.main_menu, menu);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.sign_out_menu) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(ResourceCenterActivity.this, "Signed out successfully!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this.getApplicationContext(),MainActivity.class));
            return true;
        }
        else if (item.getItemId() == R.id.resource_center_menu){
            startActivity(new Intent(this.getApplicationContext(),ResourceCenterActivity.class));
            return true;
        }
        else if (item.getItemId() == R.id.profile_menu){
            startActivity(new Intent(this.getApplicationContext(),ProfileActivity.class));
            return true;
        }
        else if (item.getItemId() == R.id.dashboard_menu){
            startActivity(new Intent(this.getApplicationContext(),DashboardActivity.class));
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {
        if (fAuth.getCurrentUser()!=null)
        return;
        else{
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    }
}