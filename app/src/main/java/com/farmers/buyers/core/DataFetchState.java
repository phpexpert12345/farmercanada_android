package com.farmers.buyers.core;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.farmers.buyers.remote.StandardError;

import static com.farmers.buyers.core.Status.ERROR;
import static com.farmers.buyers.core.Status.LOADING;
import static com.farmers.buyers.core.Status.SUCCESS;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 14:38
 * mohammadsajjad679@gmail.com
 */

public class DataFetchState<T> {

    @NonNull
    public final Status status;

    @Nullable
    public final String status_message;

    @Nullable
    public final T data;

    public DataFetchState(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.status_message = message;
    }

//    public static <T> DataFetchState success() {
//        return new DataFetchState();
//    }
//
//    public static DataFetchState error(StandardError standardError) {
//        return new DataFetchState();
//    }
//
//    public static DataFetchState loading() {
//        return new DataFetchState();
//    }
//
//



    public static <T> DataFetchState<T> success(@Nullable T data, String message) {
        return new DataFetchState<>(SUCCESS, data, message);
    }

    public static <T> DataFetchState<T> error(String msg, @Nullable T data) {
        return new DataFetchState<>(ERROR, data, msg);
    }

    public static <T> DataFetchState<T> loading() {
        return new DataFetchState<T>(LOADING, null , null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DataFetchState<?> resource = (DataFetchState<?>) o;

        if (status != resource.status) {
            return false;
        }
        if (status_message != null ? !status_message.equals(resource.status_message) : resource.status_message != null) {
            return false;
        }
        return data != null ? data.equals(resource.data) : resource.data == null;
    }

    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + (status_message != null ? status_message.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "status=" + status +
                ", message='" + status_message + '\'' +
                ", data=" + data +
                '}';
    }
}