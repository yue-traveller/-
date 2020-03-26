package com.google.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Cryptographyutil {
    public final static String SALT="code";
    public  static String md5(String str,String salt){
        return new Md5Hash(str,salt).toString();
    }
}
