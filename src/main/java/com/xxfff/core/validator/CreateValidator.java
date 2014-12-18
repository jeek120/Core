package com.xxfff.core.validator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.xxfff.core.util.StringUtils;

public class CreateValidator {
	private static Map<String, Map> allValidityRuleMap = new HashMap<String, Map>();
	private static Map<String, Map> allFieldDescMap = new HashMap<String, Map>();
	private static String jsValiditerFilePath;
	private static String[] pojoBeanPackageName;

	/**
	 * 
	 将指定的包下边的所有Pojo类的标注信息一次性的读取到Map中，以备以后验证对象时使用
	 */
	public static void loadAllClassAnnotationValidityInfoInPackage(
			String[] packageNameArr) throws IllegalArgumentException,
			SecurityException, IllegalAccessException, NoSuchMethodException,
			InvocationTargetException, ClassNotFoundException, IOException {
		for (int i = 0; i < packageNameArr.length; i++) {
			List<Class> classList = new ArrayList<Class>(
					getAllClassInPackage(packageNameArr[i]));
			for (int j = 0; j < classList.size(); j++) {
				Class currClass = classList.get(j);
				String classFullName = currClass.getPackage().getName() + "."
						+ currClass.getName();
				Map[] validityRuleMapAndFieldDescMap = loadValidityRuleAndCreateValidityJsFile(currClass);
				allValidityRuleMap.put(classFullName,
						validityRuleMapAndFieldDescMap[0]);
				allFieldDescMap.put(classFullName,
						validityRuleMapAndFieldDescMap[1]);
			}
		}
	}

	/*
	 * 对具体的一个类的标注做处理，读入字段描述　信息，读入非空验证信息，读入整数验证信息，并根据这些信息生成JS代码。
	 */

	private static Map[] loadValidityRuleAndCreateValidityJsFile(Class currClass)
			throws IllegalArgumentException, IllegalAccessException,
			SecurityException, NoSuchMethodException,
			InvocationTargetException, IOException {
		StringBuffer validityCanNotNullJsStrBuf = new StringBuffer();
		StringBuffer validityIsIntegerJsStrBuf = new StringBuffer();
		Field[] fieldArr = currClass.getDeclaredFields();
		List<String> canNotNullFieldNameList = new ArrayList<String>();
		Map<String, Object> fieldDescMap = new HashMap<String, Object>();
		StringBuffer json_buf = new StringBuffer();
		for (int i = 0; i < fieldArr.length; i++) {
			//String currFieldName = fieldArr[i].getName();
			// 读入字段描述　信息
			Validator validatorAnnotation = fieldArr[i]
					.getAnnotation(Validator.class);
			if (validatorAnnotation != null) {
				json_buf.append(",{");
				String field_str = "'"+validatorAnnotation.filed()+"':{";
				//fieldDescMap.put("field", validatorAnnotation.filed());
				if(validatorAnnotation.minLength() > 0){
					field_str += ",'minLength':"+(Integer)validatorAnnotation.minLength();
					//fieldDescMap.put("minLength", (Integer)validatorAnnotation.minLength());
				}
				if(validatorAnnotation.maxLength() < Integer.MAX_VALUE){
					field_str += ",'maxLength':"+(Integer)validatorAnnotation.maxLength();
					//fieldDescMap.put("maxLength", (Integer)validatorAnnotation.maxLength());
				}
				if(validatorAnnotation.minValue() > Integer.MIN_VALUE){
					field_str += ",'minValue':"+(Integer)validatorAnnotation.minValue();
					//fieldDescMap.put("minValue", (Integer)validatorAnnotation.minValue());
				}
				if(validatorAnnotation.maxValue() < Integer.MAX_VALUE){
					field_str += ",'maxValue':"+(Integer)validatorAnnotation.maxValue();
					//fieldDescMap.put("maxValue", (Integer)validatorAnnotation.maxValue());
				}
				if(StringUtils.isNotBlank(validatorAnnotation.regexp())){
					field_str += ",'regexp':'"+validatorAnnotation.regexp()+"'";
					//fieldDescMap.put("minLength", (Integer)validatorAnnotation.minLength());
				}
				json_buf.append(field_str);
				json_buf.append("}");
			}
		}
		if(json_buf.length() > 0){
			json_buf.replace(0, 1, "{");
			json_buf.append("}");
		}
		Map<String, List> classValidityRuleMap = new HashMap<String, List>();
		classValidityRuleMap.put("canNotNullFieldNameList",
				canNotNullFieldNameList);
		// 根据这些信息生成JS代码
		BufferedWriter jsFileWriter = new BufferedWriter(new FileWriter(
				new File(jsValiditerFilePath + java.io.File.separator
						+ currClass.getName() + ".js")));
		jsFileWriter.write(validityCanNotNullJsStrBuf.toString());
		jsFileWriter.write(validityIsIntegerJsStrBuf.toString());
		jsFileWriter.close();
		return new Map[] { classValidityRuleMap, fieldDescMap };
	}

	/*
	 * 具体的后台验证过程
	 
	public static ValidityResult validity(Object currObj)
			throws IllegalArgumentException, IllegalAccessException,
			SecurityException, NoSuchMethodException, InvocationTargetException {
		Class aClass = currObj.getClass();
		String classFullName = aClass.getPackage().getName() + "."
				+ aClass.getName();
		Field[] fieldArr = aClass.getDeclaredFields();

		Map validityRuleMap = allValidityRuleMap.get(classFullName);
		Map fieldDescMap = allFieldDescMap.get(classFullName);
		List canNotNullFieldNameList = (List) validityRuleMap
				.get("canNotNullFieldNameList");
		List isIntegerValiditerRuleInfoList = (List) validityRuleMap
				.get("isIntegerValiditerRuleInfoList");
		ValidityResult validityResult = new ValidityResult();
		for (int j = 0; j < canNotNullFieldNameList.size(); j++) {
			String currFieldName = (String) canNotNullFieldNameList.get(j);
			String getMethodName = "get"
					+ currFieldName.substring(0, 1).toUpperCase()
					+ currFieldName.substring(1);
			Method getFieldValueMethod = aClass.getMethod(getMethodName);
			String fieldValue = ((String) getFieldValueMethod.invoke(currObj));
			if (fieldValue == null || fieldValue.equals("")) {
				System.out.println(fieldDescMap.get(currFieldName)
						+ " 不可以为空 ! ");
				validityResult.addValidityInfo(fieldDescMap.get(currFieldName)
						+ " 不可以为空 ! ");
			}
		}

		for (int j = 0; j < isIntegerValiditerRuleInfoList.size(); j++) {
			IsIntegerValiditerRuleInfo isIntegerValiditerRuleInfo = (IsIntegerValiditerRuleInfo) isIntegerValiditerRuleInfoList
					.get(j);
			String currFieldName = isIntegerValiditerRuleInfo.getFieldName();
			String getMethodName = "get"
					+ currFieldName.substring(0, 1).toUpperCase()
					+ currFieldName.substring(1);
			Method getFieldValueMethod = aClass.getMethod(getMethodName);
			String fieldValue = ((String) getFieldValueMethod.invoke(currObj));
			int minWidth = isIntegerValiditerRuleInfo.getMinWidth();
			int maxWidth = isIntegerValiditerRuleInfo.getMaxWidth();
			int minValue = isIntegerValiditerRuleInfo.getMinValue();
			int maxValue = isIntegerValiditerRuleInfo.getMaxValue();

			if (StringUtils.isInteger(fieldValue)) {
				if (fieldValue.length() < minWidth) {
					validityResult
							.addValidityInfo(fieldDescMap.get(currFieldName)
									+ " 宽度不可以小于 " + minWidth + "! ");
				}
				if (fieldValue.length() > maxWidth) {
					validityResult
							.addValidityInfo(fieldDescMap.get(currFieldName)
									+ " 宽度不可以大于 " + maxWidth + "! ");
				}
				if (Integer.parseInt(fieldValue) < minValue) {
					validityResult
							.addValidityInfo(fieldDescMap.get(currFieldName)
									+ " 的值不可以小于 " + minValue + "! ");
				}
				if (Integer.parseInt(fieldValue) > maxValue) {
					validityResult
							.addValidityInfo(fieldDescMap.get(currFieldName)
									+ " 的值不可以大于 " + maxValue + "! ");
				}

			} else {

				validityResult.addValidityInfo(fieldDescMap.get(currFieldName)
						+ " 必须是整数格式 ! ");

			}
		}
		return validityResult;
	}*/

	public static String[] getPojoBeanPackageName() {
		return pojoBeanPackageName;
	}

	public static void setPojoBeanPackageName(String[] pojoBeanPackageName) {
		CreateValidator.pojoBeanPackageName = pojoBeanPackageName;
	}

	public static String getJsValiditerFilePath() {
		return jsValiditerFilePath;
	}

	public static void setJsValiditerFilePath(String jsValiditerFilePath) {
		CreateValidator.jsValiditerFilePath = jsValiditerFilePath;
	}

	/**
	 * 从包package中获取所有的Class
	 * 
	 * @param pack
	 * @return
	 */
	public static Set<Class<?>> getAllClassInPackage(String pack) {

		// 第一个class类的集合
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		// 是否循环迭代
		boolean recursive = true;
		// 获取包的名字 并进行替换
		String packageName = pack;
		String packageDirName = packageName.replace('.', '/');
		// 定义一个枚举的集合 并进行循环来处理这个目录下的things
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader()
					.getResources(packageDirName);
			// 循环迭代下去
			while (dirs.hasMoreElements()) {
				// 获取下一个元素
				URL url = dirs.nextElement();
				// 得到协议的名称
				String protocol = url.getProtocol();
				// 如果是以文件的形式保存在服务器上
				if ("file".equals(protocol)) {
					System.err.println("file类型的扫描");
					// 获取包的物理路径
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					// 以文件的方式扫描整个包下的文件 并添加到集合中
					findAndAddClassesInPackageByFile(packageName, filePath,
							recursive, classes);
				} else if ("jar".equals(protocol)) {
					// 如果是jar包文件
					// 定义一个JarFile
					System.err.println("jar类型的扫描");
					JarFile jar;
					try {
						// 获取jar
						jar = ((JarURLConnection) url.openConnection())
								.getJarFile();
						// 从此jar包 得到一个枚举类
						Enumeration<JarEntry> entries = jar.entries();
						// 同样的进行循环迭代
						while (entries.hasMoreElements()) {
							// 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
							JarEntry entry = entries.nextElement();
							String name = entry.getName();
							// 如果是以/开头的
							if (name.charAt(0) == '/') {
								// 获取后面的字符串
								name = name.substring(1);
							}
							// 如果前半部分和定义的包名相同
							if (name.startsWith(packageDirName)) {
								int idx = name.lastIndexOf('/');
								// 如果以"/"结尾 是一个包
								if (idx != -1) {
									// 获取包名 把"/"替换成"."
									packageName = name.substring(0, idx)
											.replace('/', '.');
								}
								// 如果可以迭代下去 并且是一个包
								if ((idx != -1) || recursive) {
									// 如果是一个.class文件 而且不是目录
									if (name.endsWith(".class")
											&& !entry.isDirectory()) {
										// 去掉后面的".class" 获取真正的类名
										String className = name.substring(
												packageName.length() + 1,
												name.length() - 6);
										try {
											// 添加到classes
											classes.add(Class
													.forName(packageName + '.'
															+ className));
										} catch (ClassNotFoundException e) {
											// log
											// .error("添加用户自定义视图类错误 找不到此类的.class文件");
											e.printStackTrace();
										}
									}
								}
							}
						}
					} catch (IOException e) {
						// log.error("在扫描用户定义视图时从jar包获取文件出错");
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return classes;
	}

	/**
	 * 以文件的形式来获取包下的所有Class
	 * 
	 * @param packageName
	 * @param packagePath
	 * @param recursive
	 * @param classes
	 */
	public static void findAndAddClassesInPackageByFile(String packageName,
			String packagePath, final boolean recursive, Set<Class<?>> classes) {
		// 获取此包的目录 建立一个File
		File dir = new File(packagePath);
		// 如果不存在或者 也不是目录就直接返回
		if (!dir.exists() || !dir.isDirectory()) {
			// log.warn("用户定义包名 " + packageName + " 下没有任何文件");
			return;
		}
		// 如果存在 就获取包下的所有文件 包括目录
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			public boolean accept(File file) {
				return (recursive && file.isDirectory())
						|| (file.getName().endsWith(".class"));
			}
		});
		// 循环所有文件
		for (File file : dirfiles) {
			// 如果是目录 则继续扫描
			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile(
						packageName + "." + file.getName(),
						file.getAbsolutePath(), recursive, classes);
			} else {
				// 如果是java类文件 去掉后面的.class 只留下类名
				String className = file.getName().substring(0,
						file.getName().length() - 6);
				try {
					// 添加到集合中去
					// classes.add(Class.forName(packageName + '.' +
					// className));
					// 经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
					classes.add(Thread.currentThread().getContextClassLoader()
							.loadClass(packageName + '.' + className));
				} catch (ClassNotFoundException e) {
					// log.error("添加用户自定义视图类错误 找不到此类的.class文件");
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) throws IllegalArgumentException,
			IllegalAccessException, SecurityException, NoSuchMethodException,
			InvocationTargetException, ClassNotFoundException, IOException {
		CreateValidator
				.setPojoBeanPackageName(new String[] { "com.javaest.validity.test.pojo" });
		CreateValidator.setJsValiditerFilePath("JS\\VALIDITY");
		CreateValidator
				.loadAllClassAnnotationValidityInfoInPackage(new String[] { "com.javaest.validity.test.pojo" });
	}

}