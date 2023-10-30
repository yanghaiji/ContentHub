package com.javayh.content.hub.common;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-10-27
 */
@Slf4j
public class FileUtils {


    /**
     * 递归删除文件夹内的所有文件
     *
     * @param folderPath 文件路径
     */
    public static void deleteFilesInFolder(String folderPath) {
        File folder = new File(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        if (file.delete()) {
                            log.info("Deleted file: " + file.getName());
                        } else {
                            log.error("Failed to delete file: " + file.getName());
                        }
                    } else if (file.isDirectory()) {
                        deleteFilesInFolder(file.getAbsolutePath());
                    }
                }
            }
        } else {
            log.error("Folder does not exist: " + folderPath);
        }
    }

    /**
     * 删除文件夹本身（如果需要）
     *
     * @param folderPath 文件路径
     */
    public static void deleteFolder(String folderPath) {
        File folder = new File(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            if (folder.delete()) {
                log.info("Deleted folder: " + folderPath);
            } else {
                log.error("Failed to delete folder: " + folderPath);
            }
        } else {
            log.error("Folder does not exist: " + folderPath);
        }
    }


    /**
     * 删除指定的文件
     *
     * @param folderPath       文件路径
     * @param fileNameToDelete 文件名
     */
    public static boolean deleteSpecificFile(String folderPath, String fileNameToDelete) {
        File folder = new File(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().equals(fileNameToDelete)) {
                        return file.delete();
                    }
                }
            }
        }
        return false;
    }
}
