package com.pigutu.app.test;

import com.pigutu.app.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class a
{
  public static a a = new a("*$%x#&k@");
  private static SecretKeySpec b;
  private static Cipher c;
  
  private a(String paramString)
  {
    try
    {
      byte[] paramString1 = c(paramString.getBytes());
      SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramString1, "AES");
      b = localSecretKeySpec;
      c = Cipher.getInstance("AES");
      return;
    }
    catch (Exception e)
    {
      for (;;) {}
    }
  }
  
  public String b(byte[] paramArrayOfByte)
    throws Exception
  {
    c.init(2, b);
    return new String(c.doFinal(paramArrayOfByte), "UTF-8");
  }
  
  private byte[] b(String paramString)
    throws Exception
  {
    c.init(1, b);
    return c.doFinal(paramString.getBytes("UTF-8"));
  }
  
  private byte[] c(byte[] paramArrayOfByte)
    throws Exception
  {
    return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec("2#lkd&!sl*>l@".toCharArray(), paramArrayOfByte, 1024, 128)).getEncoded();
  }
  
  public String a(String paramString)
    throws Exception
  {
    return Base64.encodeToString(b(paramString), 2);
  }
  
  public String a(byte[] paramArrayOfByte)
    throws Exception
  {
    return b(Base64.decode(paramArrayOfByte, 0));
  }
}