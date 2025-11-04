package com.wifiled.gameview;

/* loaded from: classes3.dex */
public interface GameAction extends IGame {
    void destroy();

    default void moveBack() {
    }

    default void moveForward() {
    }

    void moveLeft();

    void moveRight();

    void restartGame();

    void setGameCallback(GameCallback gameCallback);

    void startGame();

    void stopGame();
}
