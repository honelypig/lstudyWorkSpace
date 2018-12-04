package com.zda.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileUtils {
    //https://www.cnblogs.com/fnlingnzb-learner/p/6010165.html
    public static void downLoadRemoteResource(String remoteUrl, String targetUrl) throws IOException {
        //创建URL对象，参数传递一个String类型的URL解析地址
        URL url = new URL(remoteUrl);
        //HttpURLConnection是protected修饰的，无法直接new对象
        //url的openConnection方法返回值是URLConnection类的对象，需要强转为HttpURLConnection
        //所以直接通过url调用openConnection方法来实现创建HttpURLConnection的对象
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        //设置请求方式
        huc.setRequestMethod("GET");
        int code = 0;
        // 从 HTTP 响应消息获取状态码
        //200表示ok，500表示服务器错误，404表示找不到
        code = huc.getResponseCode();
        //如果状态码为200，表示连接ok，可以继续操作
        if (code == 200) {
            //获取输入流
            InputStream ips = huc.getInputStream();

            //采用高效输入流获取网络上的资源
            BufferedInputStream bis = new BufferedInputStream(ips);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(targetUrl));
            byte[] b = new byte[1024];
            int len = 0;
            //将数据写入文件
            while ((len = bis.read(b)) != -1) {
                bos.write(b, 0, len);
         //刷新资源------在前面IO部分提到过，write方法使用完需要刷新
                bos.flush();
            }
            //关闭流，释放资源
            bos.close();
            bis.close();
            ips.close();
        }
    }

    /**
     * 创建文件夹
     * @param dirUrl
     */
    public static void mkdirs(String dirUrl){
        File localFile=new File(dirUrl);
        if(!localFile.exists()){
            localFile.mkdirs();
        }
    }
    /**
     * 创建文件
     * @param fileUrl
     */
    public static void createFile(String fileUrl,String content) throws IOException {
        File localFile=new File(fileUrl);
        if(!localFile.exists()){
            localFile.createNewFile();
        }
        FileWriter resultFile=new FileWriter(localFile);
        PrintWriter myFile=new PrintWriter(resultFile);
        myFile.write(content);
        resultFile.close();
    }
    /**
     * Construct a file from the set of name elements.
     *
     * @param directory
     *            the parent directory
     * @param names
     *            the name elements
     * @return the file
     */
    public static File getFile(File directory, String... names) {
        if (directory == null) {
            throw new NullPointerException(
                    "directorydirectory must not be null");
        }
        if (names == null) {
            throw new NullPointerException("names must not be null");
        }
        File file = directory;
        for (String name : names) {
            file = new File(file, name);
        }
        return file;
    }

    /**
     * Construct a file from the set of name elements.
     *
     * @param names
     *            the name elements
     * @return the file
     */
    public static File getFile(String... names) {
        if (names == null) {
            throw new NullPointerException("names must not be null");
        }
        File file = null;
        for (String name : names) {
            if (file == null) {
                file = new File(name);
            }
            else {
                file = new File(file, name);
            }
        }
        return file;
    }

    /**
     * Opens a {@link FileInputStream} for the specified file, providing better
     * error messages than simply calling <code>new FileInputStream(file)</code>
     * .
     * <p>
     * At the end of the method either the stream will be successfully opened,
     * or an exception will have been thrown.
     * <p>
     * An exception is thrown if the file does not exist. An exception is thrown
     * if the file object exists but is a directory. An exception is thrown if
     * the file exists but cannot be read.
     *
     * @param file
     *            the file to open for input, must not be {@code null}
     * @return a new {@link FileInputStream} for the specified file
     * @throws FileNotFoundException
     *             if the file does not exist
     * @throws IOException
     *             if the file object is a directory
     * @throws IOException
     *             if the file cannot be read
     */
    public static FileInputStream openInputStream(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file
                        + "' exists but is a directory");
            }
            if (file.canRead() == false) {
                throw new IOException("File '" + file + "' cannot be read");
            }
        }
        else {
            throw new FileNotFoundException("File '" + file
                    + "' does not exist");
        }
        return new FileInputStream(file);
    }

    /**
     * Opens a {@link FileOutputStream} for the specified file, checking and
     * creating the parent directory if it does not exist.
     * <p>
     * At the end of the method either the stream will be successfully opened,
     * or an exception will have been thrown.
     * <p>
     * The parent directory will be created if it does not exist. The file will
     * be created if it does not exist. An exception is thrown if the file
     * object exists but is a directory. An exception is thrown if the file
     * exists but cannot be written to. An exception is thrown if the parent
     * directory cannot be created.
     *
     * @param file
     *            the file to open for output, must not be {@code null}
     * @param append
     *            if {@code true}, then bytes will be added to the
     *            end of the file rather than overwriting
     * @return a new {@link FileOutputStream} for the specified file
     * @throws IOException
     *             if the file object is a directory
     * @throws IOException
     *             if the file cannot be written to
     * @throws IOException
     *             if a parent directory needs creating but that fails
     */
    public static FileOutputStream openOutputStream(File file, boolean append)
            throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file
                        + "' exists but is a directory");
            }
            if (file.canWrite() == false) {
                throw new IOException("File '" + file
                        + "' cannot be written to");
            }
        }
        else {
            File parent = file.getParentFile();
            if (parent != null) {
                if (!parent.mkdirs() && !parent.isDirectory()) {
                    throw new IOException("Directory '" + parent
                            + "' could not be created");
                }
            }
        }
        return new FileOutputStream(file, append);
    }

    public static FileOutputStream openOutputStream(File file)
            throws IOException {
        return openOutputStream(file, false);
    }

    /**
     * Cleans a directory without deleting it.
     *
     * @param directory
     *            directory to clean
     * @throws IOException
     *             in case cleaning is unsuccessful
     */
    public static void cleanDirectory(File directory) throws IOException {
        if (!directory.exists()) {
            String message = directory + " does not exist";
            throw new IllegalArgumentException(message);
        }

        if (!directory.isDirectory()) {
            String message = directory + " is not a directory";
            throw new IllegalArgumentException(message);
        }

        File[] files = directory.listFiles();
        if (files == null) { // null if security restricted
            throw new IOException("Failed to list contents of " + directory);
        }

        IOException exception = null;
        for (File file : files) {
            try {
                forceDelete(file);
            }
            catch (IOException ioe) {
                exception = ioe;
            }
        }

        if (null != exception) {
            throw exception;
        }
    }

    // -----------------------------------------------------------------------
    /**
     * Deletes a directory recursively.
     *
     * @param directory
     *            directory to delete
     * @throws IOException
     *             in case deletion is unsuccessful
     */
    public static void deleteDirectory(File directory) throws IOException {
        if (!directory.exists()) {
            return;
        }

        cleanDirectory(directory);

        if (!directory.delete()) {
            String message = "Unable to delete directory " + directory + ".";
            throw new IOException(message);
        }
    }

    /**
     * Deletes a file. If file is a directory, delete it and all
     * sub-directories.
     * <p>
     * The difference between File.delete() and this method are:
     * <ul>
     * <li>A directory to be deleted does not have to be empty.</li>
     * <li>You get exceptions when a file or directory cannot be deleted.
     * (java.io.File methods returns a boolean)</li>
     * </ul>
     *
     * @param file
     *            file or directory to delete, must not be {@code null}
     * @throws NullPointerException
     *             if the directory is {@code null}
     * @throws FileNotFoundException
     *             if the file was not found
     * @throws IOException
     *             in case deletion is unsuccessful
     */
    public static void forceDelete(File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectory(file);
        }
        else {
            boolean filePresent = file.exists();
            if (!file.delete()) {
                if (!filePresent) {
                    throw new FileNotFoundException("File does not exist: "
                            + file);
                }
                String message = "Unable to delete file: " + file;
                throw new IOException(message);
            }
        }
    }

    /**
     * Deletes a file, never throwing an exception. If file is a directory,
     * delete it and all sub-directories.
     * <p>
     * The difference between File.delete() and this method are:
     * <ul>
     * <li>A directory to be deleted does not have to be empty.</li>
     * <li>No exceptions are thrown when a file or directory cannot be deleted.</li>
     * </ul>
     *
     * @param file
     *            file or directory to delete, can be {@code null}
     * @return {@code true} if the file or directory was deleted, otherwise
     *         {@code false}
     *
     */
    public static boolean deleteQuietly(File file) {
        if (file == null) {
            return false;
        }
        try {
            if (file.isDirectory()) {
                cleanDirectory(file);
            }
        }
        catch (Exception ignored) {
        }

        try {
            return file.delete();
        }
        catch (Exception ignored) {
            return false;
        }
    }

    /**
     * Makes a directory, including any necessary but nonexistent parent
     * directories. If a file already exists with specified name but it is
     * not a directory then an IOException is thrown.
     * If the directory cannot be created (or does not already exist)
     * then an IOException is thrown.
     *
     * @param directory
     *            directory to create, must not be {@code null}
     * @throws NullPointerException
     *             if the directory is {@code null}
     * @throws IOException
     *             if the directory cannot be created or the file already exists
     *             but is not a directory
     */
    public static void forceMkdir(File directory) throws IOException {
        if (directory.exists()) {
            if (!directory.isDirectory()) {
                String message = "File " + directory + " exists and is "
                        + "not a directory. Unable to create directory.";
                throw new IOException(message);
            }
        }
        else {
            if (!directory.mkdirs()) {
                // Double-check that some other thread or process hasn't made
                // the directory in the background
                if (!directory.isDirectory()) {
                    String message = "Unable to create directory " + directory;
                    throw new IOException(message);
                }
            }
        }
    }

    /**
     * Returns the size of the specified file or directory. If the provided
     * {@link File} is a regular file, then the file's length is returned.
     * If the argument is a directory, then the size of the directory is
     * calculated recursively. If a directory or subdirectory is security
     * restricted, its size will not be included.
     *
     * @param file
     *            the regular file or directory to return the size
     *            of (must not be {@code null}).
     *
     * @return the length of the file, or recursive size of the directory,
     *         provided (in bytes).
     *
     * @throws NullPointerException
     *             if the file is {@code null}
     * @throws IllegalArgumentException
     *             if the file does not exist.
     *
     */
    public static long sizeOf(File file) {

        if (!file.exists()) {
            String message = file + " does not exist";
            throw new IllegalArgumentException(message);
        }

        if (file.isDirectory()) {
            return sizeOfDirectory(file);
        }
        else {
            return file.length();
        }

    }

    /**
     * Counts the size of a directory recursively (sum of the length of all
     * files).
     *
     * @param directory
     *            directory to inspect, must not be {@code null}
     * @return size of directory in bytes, 0 if directory is security
     *         restricted, a negative number when the real total
     *         is greater than {@link Long#MAX_VALUE}.
     * @throws NullPointerException
     *             if the directory is {@code null}
     */
    public static long sizeOfDirectory(File directory) {
        checkDirectory(directory);

        final File[] files = directory.listFiles();
        if (files == null) { // null if security restricted
            return 0L;
        }
        long size = 0;

        for (final File file : files) {

            size += sizeOf(file);
            if (size < 0) {
                break;

            }

        }

        return size;
    }

    /**
     * Checks that the given {@code File} exists and is a directory.
     *
     * @param directory
     *            The {@code File} to check.
     * @throws IllegalArgumentException
     *             if the given {@code File} does not exist or is not a
     *             directory.
     */
    private static void checkDirectory(File directory) {
        if (!directory.exists()) {
            throw new IllegalArgumentException(directory + " does not exist");
        }
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory
                    + " is not a directory");
        }
    }
}
