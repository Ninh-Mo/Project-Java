package com.vam.skin;

import com.vam.utils.Constant;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class TryTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        createStorageFolder("a");

    }
    public static List<String> getPackageList(String basePackage) throws IOException, ClassNotFoundException {
        // クラスローダを取得
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        // パッケージ配下のリソースを取得（複数の場合あり）
        Enumeration<URL> e = cl.getResources(basePackage);
        // 返却用のリストを宣言
        List<String> list = new ArrayList<>();

        // パッケージ配下のリソースの数だけループ
        for (; e.hasMoreElements() ;){
            URL url = e.nextElement();
            File dir = new File(url.getPath());
            for (String path : dir.list()) {
                list.add(basePackage + "." + path);
            }
        }
        List<String> packageList = new ArrayList<>();
        for (String item : list) {
            String checkString = ".class";
            if (!item.contains(checkString)) {
                packageList.add( item.substring(basePackage.length() + 1));
            }
        }

        return packageList;
    }

    public static void createStorageFolder(String messagesId) throws IOException, ClassNotFoundException{
        List<String> baseFolder = getPackageList(Constant.BASE_FOLDER);
        System.out.println(baseFolder);

    }
}
