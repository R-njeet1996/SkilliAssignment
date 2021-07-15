package com.example.skilliassignment.api;

import android.content.Context;
import android.util.Log;


import com.example.skilliassignment.modal.ErrorModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ApiCallback<T>  implements Callback<T> {
    private final String TAG = ApiCallback.class.getSimpleName();

    Context context;


    public ApiCallback(Context context) {
        this.context = context;
    }


    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        if (response.body() != null ) {
            Log.i(TAG, "RES : " + "Success");
            onSuccess(response.body());
        }
else
        {
            ErrorModel errorModel = null;

            onError(errorModel);
        }

    }



    @Override
    public void onFailure(Call<T> call, Throwable t) {
        ErrorModel errorModel = new ErrorModel();

            errorModel.setResult(t.toString());
        onError(errorModel);

    }

    public abstract void onError(ErrorModel errorModel);


    public abstract void onSuccess(T t);



}
