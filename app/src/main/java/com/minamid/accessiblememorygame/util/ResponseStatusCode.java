package com.minamid.accessiblememorygame.util;

import android.accounts.NetworkErrorException;

import java.net.ConnectException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLException;

public enum  ResponseStatusCode {
    FAIL,
    SUCCESS,
    SSL,
    NETWORK,
    UNKNOWN;

    private ResponseStatusCode () {}

    public static ResponseStatusCode getErrorCode(Throwable throwable){
        if  (throwable instanceof SSLException) {
            return ResponseStatusCode.SSL;
        } else if (throwable instanceof ConnectException) {
            return ResponseStatusCode.NETWORK;
        } else if (throwable instanceof UnknownHostException) {
            return ResponseStatusCode.NETWORK;
        } else {
            return ResponseStatusCode.UNKNOWN;
        }
    }
}
