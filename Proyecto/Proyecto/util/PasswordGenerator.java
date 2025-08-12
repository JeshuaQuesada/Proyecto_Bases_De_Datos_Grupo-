/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Proyecto.Proyecto.util;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String plainPasswordJuan = "Juan123";
        String plainPasswordRebeca = "Rebeca123";
   

        String encodedPasswordJuan = passwordEncoder.encode(plainPasswordJuan);
        String encodedPasswordRebeca = passwordEncoder.encode(plainPasswordRebeca);
    

        System.out.println("Contraseña cifrada para Juan (Juan123): " + encodedPasswordJuan);
        System.out.println("Contraseña cifrada para Rebeca (Rebeca123): " + encodedPasswordRebeca);


        System.out.println("¿Coincide 'Juan123' con la cifrada de Juan? " + passwordEncoder.matches("Juan123", encodedPasswordJuan));
    }
}