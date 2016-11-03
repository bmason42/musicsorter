package org.mason.musicsorter;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

/**
 * Created by masonb on 9/7/2016.
 */
public class MainApp {
    public static void main(String [] ars){
        String homeDir = System.getProperty("user.home");
        File musicHome=new File(homeDir, "Music");

        File[] artists = musicHome.listFiles();
        for (File f:artists){
            File [] alblums=f.listFiles();
            if (alblums != null) {
                for (File alblum : alblums) {
                    File[] tracks = alblum.listFiles();
                    if (tracks != null) {
                        for (File track : tracks) {
                            try (FileInputStream in = new FileInputStream(track)) {
                                MessageDigest md = MessageDigest.getInstance("SHA-256");
                                byte[] buffer = new byte[1024];
                                int sizeRead = -1;
                                while ((sizeRead = in.read(buffer)) != -1) {
                                    md.update(buffer, 0, sizeRead);
                                }

                                byte[] digest = md.digest();
                                String hash = byteArrayToString(digest);
                                System.out.println(hash);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

    }
    public static String byteArrayToString(byte[] ba)
    {
        StringBuilder hex = new StringBuilder();
        for (byte b: ba){
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }
}
