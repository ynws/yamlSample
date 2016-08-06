package yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;

import subClass.sub1;
import subClass.sub2;

public class Main {
	private static String filename = "buf.yaml";
    public static void main(String[] args) {
    	makeYaml();
		System.out.println("--------------------------------------------------");
    	loadYaml();
    }

    private static void loadYaml() {
    	Yaml yaml = new Yaml(new Constructor(Collection.class));
    	try {
			InputStream in = new FileInputStream(new File(filename));
			@SuppressWarnings("unchecked")
			List<Object> list = yaml.loadAs(in, List.class);
			for(Object obj:list){
				System.out.println(obj.toString());
			}
			System.out.println("--------------------------------------------------");
			for(Object obj:list){
				if(obj instanceof sub1){
					System.out.println(obj.toString());
				}
			}
			System.out.println("--------------------------------------------------");
			in.close();
    	} catch (IOException e) {
    	}
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
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}
	}
}
