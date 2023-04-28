package com.example.traficlightnew;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;       // класс кнопок
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
     // user variables
    private Button btnStart;
    private LinearLayout b_1;
    private LinearLayout b_2, b_3;
    private boolean start_stop = false;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_1 = findViewById(R.id.bulb_1);    // Метод, который находит элемент по его id R - папка res.
        b_2 = findViewById(R.id.bulb_2);
        b_3 = findViewById(R.id.bulb_3);

        btnStart = findViewById(R.id.buttonStart);
    }
    //---------------------------- ОБРАБОТКА НАЖАТИЯ КНОПКИ ----------------------------------------
    // Метод обработки нажатия на кнопку Start (переключающая кнопка или кнопка с фиксацией)
    public void onClickStart(View view) {

        if(!start_stop) {
            start_stop =  true;
            btnStart.setText("STOP");
            b_1.setBackgroundColor(Color.RED); // Данный метод меняет цвет Linearlayout на Black

            // создаем новый поток или новую ветку (он не зависит от основной ветки)
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (start_stop) {
                        if(counter <= 20){counter ++;}
                        else counter = 0;

                        TrafficLight();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) { // метод для отработки ошибок
                            throw new RuntimeException(e);
                        }
                    }
                }
            }).start(); // старт запускает новый поток
        }
        else {
            start_stop = false;
            counter = 0;
            btnStart.setText("START"); // Изменение текста кнопки
            b_1.setBackgroundColor(Color.GRAY);
            b_2.setBackgroundColor(Color.GRAY);
            b_3.setBackgroundColor(Color.GRAY);
        }
    }
    //----------------------------- ОБРАБОТЧИК ЗАКРЫТИЯ ПРИЛОЖЕНИЯ ---------------------------------
    @Override // Метод выполняется, когда мы закрываем приложение
    protected void onDestroy() {
        super.onDestroy();
        start_stop = false;
    }
    //----------------------------------------------------------------------------------------------


    //----------------------------- ОБРАБОТЧИК ПЕРЕКЛЮЧЕНИЯ ЦВЕТА  ---------------------------------
    public void TrafficLight() {
        switch (counter) {
            case 5:
            b_1.setBackgroundColor(Color.GRAY);
            b_2.setBackgroundColor(Color.YELLOW);
            break;

            case 6:
            b_2.setBackgroundColor(Color.GRAY);
            b_3.setBackgroundColor(Color.GREEN);
        }
    }
    //----------------------------------------------------------------------------------------------
}