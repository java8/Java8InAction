package lambdasinaction.my;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//https://www.runoob.com/java/java8-streams.html
//https://blog.csdn.net/y_k_y/article/details/84633001
//https://www.cnblogs.com/mrhgw/p/9171883.html
/**
 * @date 21.11.18
 *
 */
public class Java8Stream {

	public static void main(String[] args) {

		//foreach 引用不能变，内部可变
		forEachTest();

		//需求：下列数字，对第5到第10个(即取前10个数字中且不要前4个)的奇数，结果按照平方和升序排序，并去重。
		List<Integer> list = Arrays.asList(9,9,8,7,6,5,4,3,3,2,1,0);
		System.out.println("====original:"+list);
		Stream<Integer> stream = list.stream().limit(10).skip(4).distinct().filter(e->e%2!=0).sorted((a,b)->a-b).map(e->e*e);
		//Collectors.toList()结果转list
		List<Integer> resultList = stream.collect(Collectors.toList());
		System.out.println("====after resultList:"+resultList);
//		Set<Integer> resultSet=stream.collect(Collectors.toSet());//结果转set。报错
//		System.out.println("====after resultSet:"+resultSet);
		//注：每个stream只能使用一次，多次使用将报错："stream has already been operated upon or closed"
		
		//Collectors.toSet()结果转set
		Set<Integer> resultSet2=list.stream().collect(Collectors.toSet());
		System.out.println("====after resultSet2:"+resultSet2);
		
		//peek：如同于map，能得到流中的每一个元素。但map接收的是一个Function表达式，有返回值；而peek接收的是Consumer表达式，没有返回值。
		List<Integer> resultList3=list.stream().peek(e->System.out.println("print e:"+e)).collect(Collectors.toList());
		System.out.println("====after resultList3:"+resultList3);
		
		/**Collectors.toMap(f1,f2)结果转map
		 * f1:生成map key的function/方法引用
		 * f2:生成map value的function
		 */
		Student s1 = new Student("Sturt",26);
		Student s2 = new Student("Willam",32);
		Student s3 = new Student("Sturt",50);
		List<Student> students = Arrays.asList(s1,s2);
		Map<String,Integer> resultMap = students.stream().collect(Collectors.toMap(Student::getName, Student::getAge));
		System.out.println("====after resultMap:"+resultMap);
		//注：map返回的key必须不同，否则报错，如都是Sturt："IllegalStateException: Duplicate key"
//		Function valueFunction = (e) -> {return "a";};
//		Map<String,Integer> resultMap2 = students.stream().collect(Collectors.toMap(Student::getName, valueFunction));//TODO 入参时自定义function
//		System.out.println("====after resultMap2:"+resultMap2);
		List<Student> students2 = Arrays.asList(s1,s2,s3);
		//多个元素的key相同时，用新key的value。解决相同key报错问题。
		Map<String,Integer> resultMap3 = students2.stream().collect(Collectors.toMap(Student::getName, Student::getAge,(oldValue,newValue)->newValue));
		System.out.println("====after resultMap3:"+resultMap3);
		
		Long count = list.stream().collect(Collectors.counting());//Collectors.counting()结果数个数
		System.out.println("====after count:"+count);
		
		//map元素类型转换.Integer->String
		List<String> resultString = list.stream().map(e->"Item"+e).collect(Collectors.toList());
		System.out.println("====after resultString:"+resultString);
		
		//flatMap 将多个子流操作后，合并成一个大流。入参：function(e,r extends stream)；e为每个小流/小集合形参，r是每个小流整体或小流的各种操作如distinct,sort等等。
		List<Integer> list2 = Arrays.asList(100,200,300);
		List<Integer> list3 = Arrays.asList(400,200,150,200);
		List<List<Integer>> bigList = new ArrayList<>();
		bigList.add(list2);
		bigList.add(list3);
		List<Integer> flatList = bigList.stream().flatMap(e->e.stream()).collect(Collectors.toList());
		System.out.println("====after flatList:"+flatList);
		List<Integer> flatList2 = bigList.stream().flatMap(e->e.stream().distinct()).collect(Collectors.toList());//子流去重后，再合入大流。
		System.out.println("====after flatList2:"+flatList2);

		//stream.forEach(Consumer)。将集合的每个元素，用Consumer指定的方法依次处理。
		list.stream().forEach(e->System.out.println("foreach的操作每个元素："+e));
		list.forEach(e->System.out.println("foreach2的操作每个元素："+e));//同上句效果

		//Stream.of()。初始化stream,将数组转成stream
		Stream<Integer> str = Stream.of(1,2,3,4);
		str.forEach(e->System.out.println("str="+e));

		/**Stream.iterate(T seed,UnaryOperator<T> f).
		 根据初始元素，按照指定function，初始化Stream
		根据第一个元素seed,调用f function才产生后续的元素,返回一个无限长元素组成的stream对象。
		每个元素都是根据前一个元素，加上f function 计算而来。
		 一般在结尾用limit()限制stream中元素长度，否则将是无限长度。
		  */
		Stream.iterate(1,e->e+2).limit(5).forEach(e->System.out.println("iterate 初始化stream:"+e));
//		Stream.iterate(1,e->e+2).forEach(e->System.out.println("iterate 初始化stream2:"+e));

		/**Stream.iterate(T seed,Predicate<? super T> hasNext, UnaryOperator<T> next).
		 * 根据初始元素,结束predicate,按照指定function，不满足predicate时结束初始化，初始化Stream
		 根据第一个元素seed,调用f function才产生后续的元素,当hasNext指代的Predicate不满足时，停止初始化。
		 例子中当下一个元素>=6时，停止。
		 */
		Stream.iterate(1,e->e<6,e->e+2).forEach(e->System.out.println("iterate 初始化stream3:"+e));

		/**Stream.iterate(Supplier s).
		 * 类似iterate.无初始元素,按照指定Supplier，初始化Stream，返回一个无限长元素组成的stream对象。

		 */
		Stream.generate(()->3).limit(4).forEach(e->System.out.println("generate 初始化stream:"+e));
		Stream.generate(()->((new Random()).nextInt(10))).limit(4).forEach(e->System.out.println("generate 初始化stream2:"+e));//随机生成4个10以内整数


		/**output
		====origainl:[9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
		====after:[9, 25, 49, 81]
		
		Stream Object: T
		以下流操作/流方法都将返回Stream 对象，即可以再管道pipeline中连续使用
		stream()
		limit(n).
		skip(n).
		distinct().
		filter().
		sorted().
		map()
		flatMap()
		peek(Consumer c) 
		...
		
		Collectors TODO
		
		流的终止操作
		stream.xx()
		of()
		、iterate()、
		generate()
		 TODO   allMatch：接收一个 Predicate 函数，当流中每个元素都符合该断言时才返回true，否则返回false
        noneMatch：接收一个 Predicate 函数，当流中每个元素都不符合该断言时才返回true，否则返回false
        anyMatch：接收一个 Predicate 函数，只要流中有一个元素满足该断言则返回true，否则返回false
        findFirst：返回流中第一个元素
        findAny：返回流中的任意元素
        count：返回流中元素的总个数
        max：返回流中元素最大值
        min：返回流中元素最小值
		
		*/
		
		//TODO parallel, Collectors, 统计, forEach, 
		//Collectors.groupingBy,Collectors.partitioningBy,Collectors.reducing,Collectors.joining,Collectors.maxBy,Collectors.summingInt,Collectors.summarizingDouble
		
	}

	public static void forEachTest(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(456);
		list.add(12);
		list.add(66);
		list.add(895);
		list.add(50);
		//排序
		list.sort((e1,e2)->e1-e2);
		System.out.println(list);

		list.forEach((ele)->{
			//试图改变元素的引用。不可以
			ele=ele*10;
		});
		System.out.println(list);


		List<Student> list2 = new ArrayList<Student>();
		Student u1 = new Student("Tom",23);
		list2.add(u1);
		list2.forEach((ele)->{
			//试图改变单个元素内部的属性，可以
			ele.setName("bbb");
			//想改变单个元素的引用，不可以。
			Student u = new Student("Jack",30);
			ele=u;
		});
		System.out.println(list2);
		list2.forEach(s->System.out.println(s));

		/**output. forEach中，每个元素整体，不能被改变(元素对象的引用，元素基本类型的值，不变)
		 * 元素对象内部的属性可以通过单个元素改变。所以集合整体的以用不能变，集合内元素不能增减，但单个元素内部属性值可变。
		 * [12, 50, 66, 456, 895]
		 [12, 50, 66, 456, 895]//想改变单个元素的引用，不可以。
		 [User [name=bbb, age=23]]//想改变单个元素内部的属性，可以。想改变单个元素的引用，不可以。
		 */
	}
	
	public static class Student{
		private String name;
		private int age;
		public Student(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "Student{" +
					"name='" + name + '\'' +
					", age=" + age +
					'}';
		}
	}
	

}
