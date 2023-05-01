package com.example.traficlightnew;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;       // класс кнопок
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
     // Инициализация переменных
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
            btnStart.setText("STOP");           // Меняем текст в кнопке
            b_1.setBackgroundColor(Color.RED);  // Данный метод меняет цвет Linearlayout на Red

            // создаем новый поток или новую ветку (он не зависит от основной ветки)
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (start_stop) {
                        if(counter <= 25){counter ++;}
                        else counter = 0;

                        TrafficLight(); // метод обработки таймингов переключения цвета

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) { // метод для отработки ошибок (создан автоматически)
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
            b_1.setBackgroundColor(Color.GRAY); // Прямое изменение цвета
            b_2.setBackgroundColor(getResources().getColor(R.color.Grey)); //Изменение цвета с взятием из ресурсов.
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
            case 1:
                b_1.setBackgroundColor(getResources().getColor(R.color.Grey)); // Берем цвет из ресурсов.
                b_2.setBackgroundColor(getResources().getColor(R.color.Grey)); // Берем цвет из ресурсов.
                b_3.setBackgroundColor(Color.GREEN); // Прямое изменение цвета заливки
                 break;

            case 10:
                b_1.setBackgroundColor(getResources().getColor(R.color.Grey));
                b_2.setBackgroundColor(getResources().getColor(R.color.Grey));
                b_3.setBackgroundColor(getResources().getColor(R.color.Grey));
                break;
            case 11:
                b_1.setBackgroundColor(getResources().getColor(R.color.Grey));
                b_2.setBackgroundColor(getResources().getColor(R.color.Grey));
                b_3.setBackgroundColor(getResources().getColor(R.color.Green));
                break;
            case 13:
                b_1.setBackgroundColor(getResources().getColor(R.color.Grey));
                b_2.setBackgroundColor(getResources().getColor(R.color.Grey));
                b_3.setBackgroundColor(getResources().getColor(R.color.Grey));
                break;
            case 14:
                b_1.setBackgroundColor(getResources().getColor(R.color.Grey));
                b_2.setBackgroundColor(getResources().getColor(R.color.Grey));
                b_3.setBackgroundColor(getResources().getColor(R.color.Green));
                break;
            case 15:
                b_1.setBackgroundColor(getResources().getColor(R.color.Grey));
                b_2.setBackgroundColor(getResources().getColor(R.color.Grey));
                b_3.setBackgroundColor(getResources().getColor(R.color.Grey));
                break;
            case 16:
                b_1.setBackgroundColor(getResources().getColor(R.color.Grey));
                b_2.setBackgroundColor(getResources().getColor(R.color.Grey));
                b_3.setBackgroundColor(getResources().getColor(R.color.Green));
                break;
            case 17:
                b_1.setBackgroundColor(getResources().getColor(R.color.Grey));
                b_2.setBackgroundColor(getResources().getColor(R.color.Yellow));
                b_3.setBackgroundColor(getResources().getColor(R.color.Grey));
                break;
            case 20:
                b_1.setBackgroundColor(getResources().getColor(R.color.Red));
                b_2.setBackgroundColor(getResources().getColor(R.color.Grey));
                b_3.setBackgroundColor(getResources().getColor(R.color.Grey));
                break;
        }
    }
    //----------------------------------------------------------------------------------------------
}