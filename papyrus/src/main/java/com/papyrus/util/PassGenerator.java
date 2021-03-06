package com.papyrus.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassGenerator
{
  //public static void main(String[] args)
  {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);

    // El String que mandamos al metodo encode es el password que queremos
    // encriptar.
    System.out.println(bCryptPasswordEncoder.encode("1234"));
    // 4321 -> $2a$04$Hz1.PVjSxNto5mX1StnXVuHkm9Z8uxZZInqE5hw3FBt4gknuPHvNa
    // 4321 bd -> $2a$04$h5hlFEwVu8C8Kf8w7C732e4.0rDcJ0WYTnwDn.g0A6guWSSFVnaM2
    // 1231 ->
  }
}
