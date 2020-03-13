package be.uhasselt.helloworldtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView mHelloWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelloWorld = (TextView) findViewById(R.id.HelloWorld);
        mHelloWorld.setText("Dit is een test");
        Log.i("MainActivity", "onCreate: dit is een test");
    }
}
