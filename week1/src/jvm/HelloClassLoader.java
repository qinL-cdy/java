package jvm;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) throws Exception {
        Hello hello = (Hello)new HelloClassLoader().findClass("jvm.Hello").newInstance();
        hello.hello();
    }

    @Override
    protected Class<?> findClass(String name) {
        byte[] bytes = new byte[0];
        try {
            bytes = getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] getBytes() throws IOException {
        File f = new File("src\\jvm\\Hello.xlass");
        Path path = Paths.get(f.getAbsolutePath());
        byte[] buffer = Files.readAllBytes(path);
        byte[] newBuffer = new byte[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            newBuffer[i] = (byte) ((byte)255- buffer[i]);
        }
        return newBuffer;
    }
}
