import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WorkWithFile {

    public static void main(String[] args) {
        int a = 152;
        char b = 'ф';

        try (FileOutputStream fos = new FileOutputStream("text.txt")) {
            for (int i = 3; i >= 0; i--) {
                fos.write(a >> i * 8);
            }
            for (int i = 1; i >= 0; i--) {
                fos.write(b >> i * 8);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fis = new FileInputStream("text.txt")) {
            a = 0;
            for (int i = 3; i >= 0; i--) {
                a = (fis.read() << i * 8) | a;
            }
            System.out.println(a);
            a = 0;
            for (int i = 1; i >= 0; i--) {
                a = (fis.read() << i * 8) | a;
            }
            System.out.println((char) a);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
