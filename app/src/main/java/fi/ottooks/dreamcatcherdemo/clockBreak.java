package fi.ottooks.dreamcatcherdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

public class clockBreak extends AppCompatActivity {

    Button sammutaBtn;
    ImageButton breakBtn;
    Boolean sammutettu;
    int clicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_break);
        sammutettu = false;
        clicked = 0;
        sammutaBtn = findViewById(R.id.sammutaBtn);
        breakBtn = findViewById(R.id.breakBtn);
        sammutaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("", "sammutettu");
                sammutettu = true;
            }
        });
        breakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked++;
                Log.d("", String.valueOf(clicked));
                Animation animShake = AnimationUtils.loadAnimation(clockBreak.this, R.anim.shake);
                view.startAnimation(animShake);
                if (clicked >= 10) {
                    sammutettu = true;
                    Log.d("", "sammutettu");
                }
            }
        });
    }
    public boolean getBreak(){
        return sammutettu;
    }
}