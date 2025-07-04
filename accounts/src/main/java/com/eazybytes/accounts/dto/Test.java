package com.eazybytes.accounts.dto;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        String str = "Amol Salekar123@";
        /***
         *
         * A count - 1 index -0 UPPERCASE
         * l count -2 index -3,8 lowercase
         * 1 count -1 index -  number
         * @ count -21 index - special chara
         *
         */

        // cap = 65 to 91  small = 97 to 123

        Map<Character,Integer> hashMapOcc = new LinkedHashMap<>();
        Map<Character,List<Integer>> indexPositonMap = new HashMap<>();



        for(int i=0; i<str.length(); i++){
            /* finding Occurance*/
            List<Integer> indexList = new ArrayList<>();
            hashMapOcc.put(str.charAt(i),hashMapOcc.getOrDefault(str.charAt(i),0)+1);
            /* finding index positon */
            if(indexPositonMap.get(str.charAt(i)) != null){
                indexList = indexPositonMap.get(str.charAt(i));
            }
            indexList.add(i);
            indexPositonMap.put(str.charAt(i), indexList);
        }


        for(Map.Entry<Character,Integer> hashMapOccEntry: hashMapOcc.entrySet()){
            Character charStr = hashMapOccEntry.getKey();

            if(charStr -'0' >=0  && charStr -'a' < 0 && charStr -'A' < 0 ){
                System.out.println(charStr +" count -"+hashMapOccEntry.getValue()+" index -"+indexPositonMap.get(charStr).toString()+" number");
            }else if (charStr-'0' >=0 && charStr -'a' >= 0 && charStr -'A' > 0  ){
                System.out.println(charStr +" count -"+hashMapOccEntry.getValue()+" index -"+indexPositonMap.get(charStr).toString()+" lowecase");
            }else if (charStr -'0' > 0  && charStr -'A' >= 0 && charStr -'a' < 0 ){
                System.out.println(charStr +" count -"+hashMapOccEntry.getValue()+" index -"+indexPositonMap.get(charStr).toString()+" uppercase");
            }else
                System.out.println(charStr +" count -"+hashMapOccEntry.getValue()+" index -"+indexPositonMap.get(charStr).toString()+" special character");

        }


            String s1 = "abc";
            String s2 = s1;
            s1 += "d";

            StringBuffer sb1 = new StringBuffer("abc");
            StringBuffer sb2 = sb1;
            sb1.append("d");
            System.out.println(sb1 + " " + sb2 + " " + (sb1 == sb2));



        }


}
