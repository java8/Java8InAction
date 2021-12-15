package lambdasinaction.my;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//https://www.runoob.com/java/java8-streams.html
//https://blog.csdn.net/y_k_y/article/details/84633001
//https://www.cnblogs.com/mrhgw/p/9171883.html
//https://developer.51cto.com/art/202102/646014.htm  宏观架构
/**
 * @date 21.11.18
 * stream没有比较合适的动态修改元素值的方式，如给第一个元素设置1，第二个元素设置2...
 * 还是用传统方式(非stream)去做。
 *
 */
public class Java8Stream {

	public static void main(String[] args) {

		//foreach 引用不能变，内部可变
		forEachTest();

		//parallel stream
		parallelTest();

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

		/**Collectors.groupingBy(Function<T,K> f). 根据function f的入参类型T和返回类型K，生成一个Collector对象。
		 * return Collector<T, ?, Map<K, List<T>>>。Collector对象做了分组。
		 * 配合collect()方法最后，最终返回一个map,T类型是key，List<K>是value。
		 * collect.(Collectors.groupingBy(Student::getName))表示function f的入参类型时stream
		 * 中每个元素的数据类型，这里是Student,返回类型K是getName方法的返回类型String,
		 * 所以，Collectors.groupingBy(Student::getName)= Collector<Student, ?, Map<String, List<Student>>>
		 *
		 * 已知collect()方法定义如下：
		 * public final <R, A> R collect(Collector<? super P_OUT, A, R> collector){...}
		 * (等号左边表示Collectors.groupingBy(Student::getName)的结果Collector<Student, ?, Map<String, List<Student>>>
		 * 等号右边表示collect方法的入参Collector<? super P_OUT, A, R> collector
		 * )
		 * ?=Student
		 * A=?
		 * R=Map<String, List<Student>>
		 * 所以，最终collect之后，返回Map<String, List<Student>>
		 * 注：一般Collectors下方法的第一个入参T，都是流中的元素类型。
		 *
		 */
		Map<String,List<Student>> map = students.stream().collect(Collectors.groupingBy(Student::getName));
		System.out.println("grouping by name. map:"+map);//grouping by name. map:{Willam=[Student{name='Willam', age=32}], Sturt=[Student{name='Sturt', age=26}]}

		/**
		 * partitioningBy()是groupingBy()的特殊形式。
		 * 接受一个Predicate方法实现，用来判断true/false
		 * 返回Map<Boolean, List<T>> map
		 *
		 * 下例：将年龄未达到30岁和达到30岁的学生分组。
		 */
		Map<Boolean, List<Student>> partitionMap =  students.stream().collect(Collectors.partitioningBy(stu->stu.getAge()>=30));
		System.out.println("partitionMap:"+partitionMap);//partitionMap:{false=[Student{name='Sturt', age=26}], true=[Student{name='Willam', age=32}]}

		/**
		 * 用来对流进行归约/归集/降维操作，即类似聚合。把流中的每个元素，按照每每前后两个元素进行统一的聚合操作。
		 * 下面例子运行顺序：9+9，然后18+8，然后26+7...
		 * Collectors.reducing(BinaryOperator<T> op) 接受一个Function的实现，方法实现的入参是两个，最终返回值类型和入参类型一样，T是流的元素类型Integer，返回
		 * 		Collector<Integer,?任意类型,Optional<Integer>>。
		 *  public static <T> Collector<T, ?, Optional<T>> reducing(BinaryOperator<T> op) {...}
		 *
		 *  collect(Collectors.reducing())整体最终返回Option<原始流元素类型>
		 */
		Optional<Integer> reduce = list.stream().collect(Collectors.reducing((a,b)->{return a+b;}));
		Integer val = reduce.get();
		System.out.println("before reducing list:"+list+", after reducing value:"+val);//before reducing list:[9, 9, 8, 7, 6, 5, 4, 3, 3, 2, 1, 0], after reducing value:57
		//接受两个参数。100是 identity，表示此值也要进行指定的BinaryOperator操作。
		Integer val2 = list.stream().collect(Collectors.reducing(100,(a,b)->{return a+b;}));
		System.out.println(", after reducing value2 with 2 params :"+val2);//157
		//接受3个参数。要求：将整型流，每个元素拼接成字符串，从后往前拼接。
		//第一个参数是identity 初始值，第二个参数是一个function用来将流的每个元素映射成新的值或类型，第三个参数是BinaryOperator 用来对映射后的每个元素做归约处理，
		String val3 = list.stream().collect(Collectors.reducing("100",(x)->{return Integer.toString(x);},(a,b)->{return b+a;}));
		System.out.println(", after reducing value3 with 3 params :"+val3);//012334567899100

		String joinStr1 = Stream.of("I", "love", "China").collect(Collectors.joining());//拼接且不带分隔符
		String joinStr2 = Stream.of("I", "love", "China").collect(Collectors.joining(","));//拼接且带分隔符","
		String joinStr3 = Stream.of("I", "love", "China").collect(Collectors.joining(",","{","}"));//拼接且不带分隔符",",首位整体用"{","}"包裹起来。
		System.out.println("joinStr1:"+joinStr1+", joinStr2:"+joinStr2+", joinStr3:"+joinStr3);//joinStr1:IloveChina, joinStr2:I,love,China, joinStr3:{I,love,China}

		/**
		 * 接受一个比较器方法实现，用来求最大值。
		 * Collectors.maxBy(Comparator)
		 * int Comparator.compare(T o1, T o2)
		 *
		 * 查找年龄最大/小的学生
		 */
		Optional<Student> maxVal = students2.stream().collect(Collectors.maxBy((a,b)->a.getAge()-b.getAge()));
		System.out.println("maxBy :"+maxVal.get());//maxBy :Student{name='Sturt', age=50}
		Optional<Student> maxVal2 = students2.stream().collect(Collectors.maxBy((a,b)->b.getAge()-a.getAge()));
		System.out.println("maxBy2 :"+maxVal2.get());//maxBy2 :Student{name='Sturt', age=26}
		Optional<Student> minVal = students2.stream().collect(Collectors.minBy((a,b)->a.getAge()-b.getAge()));
		System.out.println("minVal :"+minVal.get());//minVal :Student{name='Sturt', age=26}

		/**
		 * Collectors.summingInt(ToIntFunction)。 ToIntFunction 接受一个类型数据，处理后固定返回int类型
		 int applyAsInt(T value);

		 Collectors.summarizingDouble同理。
		 求字符串代表的数字之和
		 */
		int sumInt = Stream.of("1","2","3").collect(Collectors.summingInt((e)->Integer.valueOf(e)));
		System.out.println("sumInt :"+sumInt);//6

		/**
		 * allMatch(),anyMatch(),noneMatch()等接受一个Predicate方法实现，用来作为判断逻辑。
		 * 最终返回true/false
		 */
		boolean allMatch = students2.stream().allMatch(stu->stu.getAge()>0);//判断是否所有学生年龄大于0
		System.out.println("allMatch :"+allMatch);//true

		boolean anyMatch = students2.stream().anyMatch(stu->stu.getAge()>80);//判断是否有学生年龄大于80
		System.out.println("anyMatch :"+anyMatch);//false

		boolean noneMatch = students2.stream().noneMatch(stu->stu.getAge()>80);//判断是否不存在学生年龄大于80
		System.out.println("noneMatch :"+noneMatch);//true

		/**
		 * findFirst(),findAny()无入参，返回一个Optional。
		 *
		 */
		Optional<Student> optStus = students2.stream().filter(stu->stu.getAge()>28).findFirst();//查找第一个年龄超28的学生
		System.out.println("findFirst :"+optStus.get());//findFirst :Student{name='Willam', age=32}

		for(int i=1;i<=30;i++) {
			System.out.println("findAny :"+i+" time");
			Optional<Student> optStus2 = students2.stream().filter(stu->stu.getAge()>28).findAny();//查找任意一个年龄超28的学生
			System.out.println("findAny :"+optStus2.get());//都是打印。findAny :Student{name='Willam', age=32}。即查找第一个满足的。
		}

		for(int i=1;i<=30;i++) {
			System.out.println("findAny parallel:"+i+" time");
			//查找任意一个年龄超28的学生。用parallelStream并行流，搭配findAny才能完成随机查找
			Optional<Student> optStus3 = students2.parallelStream().filter(stu->stu.getAge()>28).findAny();
			System.out.println("findAny :"+optStus3.get());//有的打印Sturt，有的打印Willam.
		}





		/**
		 * stream.xx()方法源码探究
		 *
		 *
		 Stream.of(1,2,3,4).forEach(Consumer<Integer>);//forEach方法的定义：接受一个Consumer，即接收一个方法实现：此方法有入参，无返回值。
		 Stream.of(1,2,3,4).forEach(e->System.out.println(e));//传入方法具体实现
		 Stream.of(1,2,3,4).forEach(System.out::println);//传入方法引用。相当于把流的每个元素作为入参，去执行打印。
		 Stream.of(1,2,3,4).forEach(方法引用a);//上面使用方式System.out::println相当于，把整个方法引用a传入。forEach方法的实现中，流的每个元素作为方法的入参，会去调用a.accept(Integer)去具体执行打印的过程。forEach调用过程分析TODO

		 方法引用a=
		 public void println(String x) {
		 synchronized (this) {
		 print(x);
		 newLine();
		 }
		 }
		 */

		//stream.forEach(Consumer)。将集合的每个元素，用Consumer指定的方法依次处理。
		list.stream().forEach(e->System.out.println("foreach的操作每个元素："+e));
		list.forEach(e->System.out.println("foreach2的操作每个元素："+e));//同上句效果

		//Stream.of()。初始化stream,将可变参数转成stream
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

		/**Stream.generate(Supplier s).
		 * 类似iterate.无初始元素,按照指定Supplier，初始化Stream，返回一个无限长元素组成的stream对象。
		 */
		Stream.generate(()->3).limit(4).forEach(e->System.out.println("generate 初始化stream:"+e));
		Stream.generate(()->((new Random()).nextInt(10))).limit(4).forEach(e->System.out.println("generate 初始化stream2:"+e));//随机生成4个10以内整数





		/**output
		 * 注：没有其他操作时，stream结果的输出顺序，是开始放入时的顺序。
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



		 流的终止操作
		 stream.xx()
		 of()
		 、iterate()、
		 generate()
		 allMatch：接收一个 Predicate 函数，当流中每个元素都符合该断言时才返回true，否则返回false
		 noneMatch：接收一个 Predicate 函数，当流中每个元素都不符合该断言时才返回true，否则返回false
		 anyMatch：接收一个 Predicate 函数，只要流中有一个元素满足该断言则返回true，否则返回false
		 findFirst：返回流中第一个元素
		 findAny：返回流中的任意元素
		 count：返回流中元素的总个数
		 max：返回流中元素最大值
		 min：返回流中元素最小值

		 */

		//Collectors.groupingBy,,Collectors.reducing,
		// Collectors.joining,Collectors.maxBy,Collectors.summingInt,Collectors.summarizingDouble
		// parallel, 统计, Collectors.partitioningBy

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

		//根据变量，修改流中元素值
		list2.forEach((ele)->{
			int i=0;
			//试图改变单个元素内部的属性，可以
			ele.setName("bbb");
			//想改变单个元素的引用，不可以。
			Student u = new Student("Jack",30);
			ele=u;
		});
	}

	public static void parallelTest(){
		Stream.of(1,2,3,4,5,6).forEach(e -> System.out.println("original stream:"+e));//1,2,3,4,5,6
		for(int i=1;i<=3;i++) {
			System.out.println("parallel stream"+i);
			Stream.of(1,2,3,4,5,6).parallel().forEach(e -> System.out.println("parallel stream:"+e));//输出结果不是按顺序1，2，3.因为是多个线程同时给每个元素执行consumer.
		}
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
