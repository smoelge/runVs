package winzer.ghostrunner_app_final.Activities.Group;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import winzer.ghostrunner_app_final.R;


public class Groups extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_groups, menu);
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

    public void goToCreateGroup(View view) {
        Intent intent = new Intent(this, CreateGroup.class);
        startActivity(intent);
    }

    public void goToInvitations(View view) {
        Intent intent = new Intent(this, Invitations.class);
        startActivity(intent);
    }

    public void goToGroupView(View view) {
        Intent intent = new Intent(this, GroupView.class);
        startActivity(intent);
    }
}