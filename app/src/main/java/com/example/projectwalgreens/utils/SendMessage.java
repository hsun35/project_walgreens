package com.example.projectwalgreens.utils;

/**
 * Created by hefen on 2/24/2018.
 */

public interface SendMessage {
    void sendData(int item_index);
    void sendCommand(String command);
    void showMessage(String message);
    //void setToolbar();
    //void sendToggle();
    void getClientTokenFromAppServer();
}
