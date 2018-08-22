package com.example.hp.demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.hp.demo.ReadMessage;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class getMessageInfoService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBindService();
    }

    class MyBindService extends ReadMessage.Stub {

        @Override
        public String getMessage() throws RemoteException {
            return "可以接受到------------";
        }
    }

}
