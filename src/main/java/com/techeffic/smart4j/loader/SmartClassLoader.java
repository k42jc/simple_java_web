package com.techeffic.smart4j.loader;

import com.techeffic.smart4j.constants.Constants;
import com.techeffic.smart4j.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 项目自定义加载器
 * Created by liaoxudong on 2017/8/4.
 */
public class SmartClassLoader{
    private static final Logger logger = LoggerFactory.getLogger(SmartClassLoader.class);

    /**
     * 获取当前上下文加载器
     * @return
     */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 使用类全限定名加载类
     * @param fullClassName 类全限定名
     * @param isInitialized 是否加载完成后立即初始化
     * @return
     */
    public static Class<?> loadClass(String fullClassName,boolean isInitialized) {
        if (StringUtils.isEmpty(fullClassName)) {
            throw new IllegalArgumentException("类名无效！");
        }
        try {
            Class<?> aClass = Class.forName(fullClassName, isInitialized, getClassLoader());
            return aClass;
        } catch (ClassNotFoundException e) {
            logger.error("SmartClassLoader load class【{}】 failure!",fullClassName,e);
        }
        return null;
    }

    /**
     * 加载指定包下的所有类
     * @param packageName 包名
     * @return
     */
    public static Set<Class<?>> getClassSet(String packageName) {
        if (StringUtils.isEmpty(packageName)) {
            throw new IllegalArgumentException("包名无效");
        }
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        try {
            //通过上下文加载器获取到包名所在的文件目录
            Enumeration<URL> resources = getClassLoader().getResources(packageName.replace(Constants.POINT, Constants.SEPARATOR));
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if (Constants.FILE.equals(protocol)) {// 普通文件类型
                        // 包所在文件目录
                        String packageFilePath = url.getPath().replace(Constants.URL_BLANK_SHOW, Constants.BLANK);
                        filterClass(classSet,packageFilePath,packageName);
                    }else if (Constants.JAR.equals(protocol)) {// jar包处理
                        JarURLConnection urlConnection = (JarURLConnection)url.openConnection();
                        if (urlConnection != null) {
                            JarFile jarFile = urlConnection.getJarFile();
                            if (jarFile != null) {
                                // 遍历jar包中的class文件并加载然后加入集合
                                Enumeration<JarEntry> entries = jarFile.entries();
                                while (entries.hasMoreElements()) {
                                    JarEntry jarEntry = entries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if (jarEntryName.endsWith(Constants.CLASS_SUFFIX)) {
                                        String fullClassName = jarEntryName.substring(0, jarEntryName.lastIndexOf(Constants.POINT)).replaceAll(Constants.SEPARATOR, Constants.POINT);
                                        addClass(classSet, fullClassName);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.error("加载【{}】包下类失败!", packageName,e);
        }
        logger.info("{} 包下所有类加载完成并加入当前集合.",packageName);
        return classSet;

    }

    /**
     * 过滤待添加classSet的文件 只操作class文件
     * @param classSet 存放加载类的set
     * @param packageFilePath 包所在的文件目录
     * @param packageName 包名
     */
    private static void filterClass(Set<Class<?>> classSet, String packageFilePath, String packageName) {
        File[] files = new File(packageFilePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                // 只处理非空目录(继续往下遍历)或class文件
                return file.isDirectory() || (file.isFile() && file.getName().endsWith(Constants.CLASS_SUFFIX));
            }
        });

        for (File file :files) {
            String fileName = file.getName();
            if (file.isFile()) {
                //文件则组装类全名
                String className = fileName.substring(0, fileName.lastIndexOf(Constants.POINT));
                if (StringUtils.isNotEmpty(packageName)) {
                    className = packageName + Constants.POINT + className;
                }
                addClass(classSet, className);
            } else {
                // 还是包结构则继续往下一层
                String subPackagePath = fileName;
                if (StringUtils.isNotEmpty(subPackagePath)) {
                    subPackagePath = packageFilePath + Constants.SEPARATOR + subPackagePath;
                }
                String subPackageName = fileName;
                if (StringUtils.isNotEmpty(subPackageName)) {
                    subPackageName = packageName + Constants.POINT + subPackageName;
                }
                // 递归子包
                filterClass(classSet, subPackagePath, subPackageName);
            }
        }
    }

    /**
     * 加载类并添加到classSet
     * @param classSet 存放整包加载完成后类的集合
     * @param classFullName 类全名
     */
    private static void addClass(Set<Class<?>> classSet, String classFullName) {
        Class<?> aClass = loadClass(classFullName, false);
        classSet.add(aClass);
    }
}
