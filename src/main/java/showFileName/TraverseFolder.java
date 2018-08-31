package showFileName;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;

public class TraverseFolder {

    public TraverseFolder(){

    }

    /**
     * 向系统剪切板写入内容
     */
    public static void setSystemClipboard(String refContent){
        String removeSpace = refContent.trim();//去除空格
        StringSelection stringSelection = new StringSelection(removeSpace);
        Clipboard sysClb = Toolkit.getDefaultToolkit().getSystemClipboard();
        sysClb.setContents(stringSelection,null);
    }

    /**
     * 递归找出当前目录下所有文件名
     *
     * @param path 当前文件夹
     * @param replace 去掉当前文件夹的前缀
     * @param tt 输出区域对象JTextArea
     *
     */
    public void traverseFolder(String path, String replace, JTextArea tt){
        File file = new File(path);
        if (file.exists()){
            File[] files = file.listFiles();
            if (files.length == 0){
                System.out.println("文件夹是空的!");
                return;
            }else {
                for (File file2 : files){
                    if (file2.isDirectory()){
                        traverseFolder(file2.getAbsolutePath(),replace,tt);
                    }else {
                        //输出目标文件夹下所有的文件名(包括下级文件)
                        tt.append(file2.getAbsolutePath().replace(replace,"")+"\n");
                    }
                }
            }
        }else {
            System.out.println("文件不存在!");
        }
    }
}
