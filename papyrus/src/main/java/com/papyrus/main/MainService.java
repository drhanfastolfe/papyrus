package com.papyrus.main;

import org.springframework.stereotype.Service;

@Service
public class MainService
{
    public String normalizaStr(String str)
    {
        int i, j;
        String strNorm, acentos, sinAcentos;;

        strNorm = str.toLowerCase();
        acentos = "áéíóúü";
        sinAcentos = "aeiouu";

        for(i = 0; i < strNorm.length(); i++)
        {
            j = 0;
            
            while (j < acentos.length() && strNorm.charAt(i) != acentos.charAt(j))
            {
                j++;
            }

            if(j < acentos.length())
            {
                strNorm = strNorm.substring(0, i) + sinAcentos.charAt(j) + strNorm.substring(i + 1); 
            }
        }

        return strNorm;
    }
}
