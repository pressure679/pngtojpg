//    Copyright (C) 2014  Vittus Peter Ove Maqe Mikiassen
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.List;
import java.util.ArrayList;
import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class converttojpg {
    public static void main(String args[]) {
        File curFile = new File(System.getProperty("user.dir"));
        BufferedImage img = null;
        StringBuffer strbuff;
        try {
            for (String strings : files()) {
                if (strings.substring(strings.length() - 3, strings.length()).equals("png") ||
                    strings.substring(strings.length() - 3, strings.length()).equals("PNG") ||
                    strings.substring(strings.length() - 3, strings.length()).equals("bmp") ||
                    strings.substring(strings.length() - 3, strings.length()).equals("BMP") ||
                    strings.substring(strings.length() - 3, strings.length()).equals("gif") ||
                    strings.substring(strings.length() - 3, strings.length()).equals("GIF")) {
                    strbuff = new StringBuffer(strings);
                    strbuff.replace(strings.length() - 3, strings.length(), "jpg");
                    System.out.println("Converting " + strings);
                    tojpg(strings, strbuff.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nDone.\n");
    }
    public static String[] files() throws IOException {
        ArrayList<String> readfiles = new ArrayList<>();
        Files.walk(Paths.get(System.getProperty("user.dir"))).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    readfiles.add(filePath.getFileName().toString());
                }
            });
        String[] dirfiles = new String[readfiles.size()];
        return readfiles.toArray(dirfiles);
    }
    public static void tojpg(String src, String dst) throws IOException {
        BufferedImage bufferedImage;
        File delete;

        //read image file
        bufferedImage = ImageIO.read(new File(src));
 
        // create a blank, RGB, same width and height, and a white background
        BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
                                                           bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
 
        // write to jpeg file and delete old file
        delete = new File(src);
        delete.delete();
        System.out.println("Deleted " + delete);
        ImageIO.write(newBufferedImage, "jpg", new File(dst));
        System.out.println("Created " + dst + "\n");
    }
}
