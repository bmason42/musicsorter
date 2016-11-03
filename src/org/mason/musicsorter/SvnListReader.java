package org.mason.musicsorter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by masonb on 9/25/2016.
 */
public class SvnListReader {
    public static void main(String[] args){
        Set<String> top = new HashSet<>();
        Map<String,Set<String>> prjects = new HashMap<>();
        Set<String> protos = new HashSet<>();
        try(FileInputStream in=new FileInputStream("c:/Users/masonb/Downloads/svnlist.txt")){
            BufferedReader reader=new BufferedReader(new InputStreamReader(in));
            String line;
            while ( (line=reader.readLine()) != null) {
                String[] parts = line.split("/");

                if (parts.length > 0) {
                    top.add(parts[0]);
                }
                if (parts.length > 1) {
                    if ("projects".equals(parts[0])) {
                        Set<String> sublevel;
                        if (prjects.containsKey(parts[1])){
                            sublevel=prjects.get(parts[1]);
                        }else{
                            sublevel=new HashSet<>();
                            prjects.put(parts[1],sublevel);
                        }
                        if (parts.length>2) {
                            sublevel.add(parts[2]);
                        }
                    } else if ("prototypes".equals(parts[0])) {
                        protos.add(parts[1]);
                    }
                }
            }
            System.out.println("============== TOP =====================");
            for (String s: top){
                System.out.println(s);
            }
            System.out.println("============== Projects =====================");
            for (String s: prjects.keySet()){
                System.out.println(s);
                Set<String> sub=prjects.get(s);
                for (String subs:sub){
                    System.out.println("\t" + subs);
                }
            }
        }catch (Throwable t){
            t.printStackTrace();
        }
    }
}
