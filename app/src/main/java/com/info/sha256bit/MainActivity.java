package com.info.sha256bit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.info.sha256bit.databinding.ActivityMainBinding;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final String shaData = "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.buttonSha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = binding.editTextSha.getText().toString();
                try {
                    if(shaData.equals(toHexString(getSHA(s1)))){
                        Toast.makeText(getApplicationContext(), "Şifre Doğru..", Toast.LENGTH_SHORT).show();
                        System.out.println(s1 + ": " + toHexString(getSHA(s1)));

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Şifre Yanlış !!", Toast.LENGTH_SHORT).show();
                    }
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        // SHA hashing ile static getInstance metodu çağırılır.
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        // Byte dizisini işaret (signum) gösteriminine dönüştürür.
        BigInteger number = new BigInteger(1, hash);

        // digest mesajı onaltılık(hex) değere dönüştürür.
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

}