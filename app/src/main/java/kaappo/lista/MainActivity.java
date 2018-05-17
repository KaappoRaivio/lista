package kaappo.lista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

    public void createNew(View v) {
        Intent intent = new Intent(this, NewList.class);
        startActivity(intent);
    }

    public void toasti(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
