package proyect.sharemelody.utils;

import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.List;

public class FileUtil {

    /**
     * Copia el archivo que se haya seleccionado en el explorador con formato .mp3 y lo copia a la carpeta de music
     * dentro de recursos para tener la cancion lista para reproducir
     * @param ustFile lista de string que cogera todas las extensiones que se desee que el explorador muestre
     * @param urlText TextField en el que aparecera la ruta del archivo escogida
     * @param name TextFileld el cual el usuario introducira el nombre del archivo
     * @throws IOException excepcion en caso de que el archivo encontrado sea null
     */
    public static void urlFileChooser(List ustFile, TextField urlText, TextField name) throws IOException {
        urlText.setEditable(false);
        urlText.setMouseTransparent(true);
        urlText.setFocusTraversable(false);
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("MP3 files",ustFile));
        java.io.File f = fc.showOpenDialog(null);
        java.io.File destiny = new java.io.File("src\\main\\resources\\music\\"+name.getText()+".mp3");
        InputStream in = new FileInputStream(f);
        OutputStream out = new FileOutputStream(destiny);

        if(f != null){
            urlText.setText(destiny.getAbsolutePath());
            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        }

        in.close();
        out.close();
    }

    /**
     * Copia el archivo que se haya seleccionado en el explorador con formato de imagen y lo copia a la carpeta de
     * imagenes dentro de recursos para tener la imagen en el directorio
     * @param pstFile lista de string que cogera todas las extensiones que se desee que el explorador muestre
     * @param photoText TextField en el que aparecera la ruta del archivo escogida
     * @throws IOException excepcion en caso de que el archivo encontrado sea null
     */
    public static void photoFileChooser(List pstFile, TextField photoText) throws IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Photo files",pstFile));
        File f = fc.showOpenDialog(null);
        File destiny = new File("src\\main\\resources\\images\\Songs\\"+f.getName());
        InputStream in = new FileInputStream(f);
        OutputStream out = new FileOutputStream(destiny);

        if(f != null){
            photoText.setText(destiny.getAbsolutePath());
            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        }else {
            photoText.setText("src\\main\\resources\\images\\defaultSong.png");
            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        }
        in.close();
        out.close();
    }
}
