package yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;

import subClass.sub1;
import subClass.sub2;
import subClass.subClass;

public class Main {
	private static String filename = "buf.yaml";
    public static void main(String[] args) {
    	// makeYaml();
		ShowAll();
		ShowClass(sub1.class);
		ShowClass(sub2.class);
		// ShowClass(Main.class); // error! not subClass
    }

    private static void ShowAll() {
		System.out.println("-------------------- Show All --------------------");
    	List<subClass> list = LoadAll();
		for(Object obj:list){
			System.out.println(obj.toString());
		}
    }
    
	private static <T> void ShowClass(Class<? extends subClass> clazz){
		System.out.println("-------------------- Show Class --------------------");
    	List<T> list = LoadClass(clazz);
    	for(T obj:list){
			System.out.println(obj.toString());
		}
    }

    private static List<subClass> LoadAll(){
    	Yaml yaml = new Yaml(new Constructor(Collection.class));
		try {
			InputStream in = new FileInputStream(new File(filename));
			@SuppressWarnings("unchecked")
			List<subClass> list = yaml.loadAs(in, List.class);
			in.close();
			return list;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
    }

	@SuppressWarnings("unchecked")
	private static <T> List<T> LoadClass(Class<? extends subClass> clazz){
    	List<subClass> list = LoadAll();
    	List<T> ret = new ArrayList<T>();
    	for(subClass obj:list){
			if(clazz.isInstance(obj))ret.add((T)obj);
		}
		return ret;
    }

	private static void makeYaml() {
		Yaml yaml = new Yaml();
		List<Object> list = Arrays.asList(new sub1("s1", 1), new sub2("s2", 2), new sub1("s1-2", 3), new sub2("s2-2", 4));
		String yamlData = yaml.dumpAs(list, Tag.YAML, DumperOptions.FlowStyle.BLOCK);
		System.out.println(yamlData);
		
		File file = new File(filename);
		try {
			FileWriter filewriter = new FileWriter(file);
			filewriter.write(yamlData);
			filewriter.close();
		} catch (IOException e) {
			// TODO é©ìÆê∂ê¨Ç≥ÇÍÇΩ catch ÉuÉçÉbÉN
			e.printStackTrace();
		}
	}
}
