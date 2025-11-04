package com.wifiled.gameview.snake.utils;

import android.util.Log;
import com.wifiled.gameview.snake.model.Food;
import com.wifiled.gameview.snake.model.Snake;

/* loaded from: classes3.dex */
public class SnakeUnitUtil {
    public static int randomInt(int i, int i2) {
        int random = (int) ((Math.random() * ((i2 - i) + 1)) + i);
        Log.d("akon", "#newFood#  min: " + i + " max:" + i2 + " iRet:" + random);
        return random;
    }

    public static void createOneFood(Snake snake, Food food) {
        int randomInt;
        int randomInt2;
        do {
            randomInt = randomInt(1, 62);
            randomInt2 = randomInt(1, 49);
            food.newFood(randomInt, randomInt2);
            if (!snake.isTheFoodOnMe(food)) {
                return;
            }
        } while (snake.isInside(randomInt, randomInt2));
    }
}
