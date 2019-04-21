package com.justbon.java8.stream;

import com.alibaba.fastjson.JSONObject;
import com.justbon.java8.stream.pojo.Dish;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


public class AppTestStream {

    List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    @Test
    public void streamMapFilter()
    {

        List<String> threeHighCaloricDishNames = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName) .limit(3) .collect(toList());

        System.out.println(threeHighCaloricDishNames);

    }

    @Test
    public void streamMapFilterLimit()
    {

        List<String> threeHighCaloricDishNames = menu.stream()
                .filter(Dish::isVegetarian)
                .map(Dish::getName) .limit(3) .collect(toList());

        System.out.println(threeHighCaloricDishNames);

    }
    /***
     * 流还支持一个叫作distinct的方法,它会返回一个元素各异（根据流所生成元素的
     * hashCode和equals方法实现）的流
     *
     */
    @Test
    public void streamFilterDistinct()
    {


        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);


    }
    /**
     *
     * 截短流
     */
    @Test
    public void streamFilterLimit()
    {

        List<String> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .map(Dish::getName)
                .collect(toList());

        System.out.println(dishes);
    }
    /**
     * 跳过元素
     * 流还支持skip(n)方法,返回一个扔掉了前n个元素的流
     */
    @Test
    public void streamMapSkip()
    {

        List<String> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(1)
                .map(Dish::getName)
                .collect(toList());

        System.out.println(dishes);
    }
    /***
     *
     * 对流中每一个元素应用函数 .Stream API也通过map和flatMap方法提供了类似的工具
     *
     * 因为getName方法返回一个String,所以map方法输出的流的类型就是Stream<String>
     *
     *
     *
     */

    @Test
    public void streamMap()
    {

        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(toList());

        System.out.println(dishNames);
    }
    /**
     *
     * 让我们看一个稍微不同的例子来巩固一下对map的理解.给定一个单词列表,你想要返回另
     * 一个列表,显示每个单词中有几个字母.怎么做呢？你需要对列表中的每个元素应用一个函数.
     * 这听起来正好该用map方法去做！应用的函数应该接受一个单词,并返回其长度.你可以像下面
     * 这样,给map传递一个方法引用String::length来解决这个问题：
     */
    @Test
    public void streamMap2()
    {
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Map> word = words.stream()
                .map(e->{
                    Map bean = new HashMap();
                    bean.put(e,e.length());
                    return bean;
                })
                .collect(toList());

        System.out.println(JSONObject.toJSONString(word));

        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());

        System.out.println(JSONObject.toJSONString(dishNameLengths));
    }
    /***
     * 流的扁平化
     *
     * 你已经看到如何使用map方法返回列表中每个单词的长度了.让我们拓展一下：对于一张单
     * 词表, 如何返回一张列表, 列出里面各不相同的字符呢？ 例如, 给定单词列表
     * ["Hello","World"],你想要返回列表["H","e","l", "o","W","r","d"].
     *
     *
     *
     * 你可能会认为这很容易,你可以把每个单词映射成一张字符表,然后调用distinct来过滤
     * 重复的字符.第一个版本可能是这样的：
     * words.stream()
     * .map(word -> word.split(""))
     * .distinct()
     * .collect(toList());
     * 这个方法的问题在于,传递给map方法的Lambda为每个单词返回了一个String[]（String
     * 列表）.因此, map 返回的流实际上是Stream<String[]> 类型的.你真正想要的是用
     * Stream<String>来表示一个字符流.图
     *
     * 一言以蔽之,flatmap方法让你把一个流中的每个值都换成另一个流,然后把所有的流连接
     * 起来成为一个流.
     *
     */

    @Test
    public void streamMapflatMap()
    {
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
        List<String> uniqueCharacters =
                streamOfwords
                        .map(w -> w.split(""))
                        .flatMap(Arrays::stream)
                        .distinct()
                        .collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(uniqueCharacters));
    }

    /**
     * 接受数字n作为参数,并返回从1到给定参数的所有数字的和.一个
     * 直接（也许有点土）的方法是生成一个无穷大的数字流,把它限制到给定的数目,然后用对两个
     * 数字求和的BinaryOperator来归约这个流
     * @param n
     * @return
     */
    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    /**
     * 用更为传统的Java术语来说这段代码与下面的迭代
     * @param n
     * @return
     */

    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }

    /***
     *你可以把流转换成并行流,从而让前面的函数归约过程（也就是求和）并行运行——对顺序
     * 流调用parallel方法
     * 将顺序流转换为并行流
     */

    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    /**
     *这个方法接受一个函数和一个long作为参数.它会对传给方法的long应用函数10次,记录
     * 每次执行的时间（以毫秒为单位）,并返回最短的一次执行时间.假设你把先前开发的所有方法
     * 都放进了一个名为ParallelStreams的类,你就可以用这个框架来测试顺序加法器函数对前一
     * 千万个自然数求和要用多久
     * @param adder
     * @param n
     * @return
     */
    public long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }

    /**
     * 顺序化执行
     */
    @Test
    public void streamSequential(){
        System.out.println("Sequential sum done in:" +
                measureSumPerf(AppTestStream::sequentialSum, 10_000_000) + " msecs");

        //用传统for循环的迭代版本执行起来应该会快很多,因为它更为底层,更重要的是不需要对
        //原始类型做任何装箱或拆箱操作.如果你试着测量它的性能,
        System.out.println("Iterative sum done in:" +
                measureSumPerf(AppTestStream::iterativeSum, 10_000_000) + " msecs");

    }

    /**
     * 并行化执行
     */

    @Test
    public void streamParallel1(){
        System.out.println("Iterative sum done in:" +
                measureSumPerf(AppTestStream::parallelSum, 10_000_000) + " msecs");
        /**
         * 这相当令人失望,求和方法的并行版本比顺序版本要慢很多.你如何解释这个意外的结果
         * 呢？这里实际上有两个问题：
         *
         *1. iterate生成的是装箱的对象,必须拆箱成数字才能求和;
         *2. 我们很难把iterate分成多个独立块来并行执行.
         *
         * 第二个问题更有意思一点,因为你必须意识到某些流操作比其他操作更容易并行化.具体来
         * 说,iterate很难分割成能够独立执行的小块,因为每次应用这个函数都要依赖前一次应用的
         * 果,
         *
         * 这意味着,在这个特定情况下,归纳进程不是像图7-1那样进行的；整张数字列表在归纳过
         * 146 第 7 章 并行数据处理与性能
         * 程开始时没有准备好,因而无法有效地把流划分为小块来并行处理.把流标记成并行,你其实是
         * 给顺序处理增加了开销,它还要把每次求和操作分到一个不同的线程上
         *
         * 这就说明了并行编程可能很复杂,有时候甚至有点违反直觉.如果用得不对（比如采用了一
         * 个不易并行化的操作,如iterate）,它甚至可能让程序的整体性能更差,所以在调用那个看似
         * 神奇的parallel操作时,了解背后到底发生了什么是很有必要的.
         *
         * 那到底要怎么利用多核处理器,用流来高效地并行求和呢？我们在第5章中讨论了一个叫
         * LongStream.rangeClosed的方法.这个方法与iterate相比有两个优点.
         * LongStream.rangeClosed直接产生原始类型的long数字,没有装箱拆箱的开销.
         * LongStream.rangeClosed会生成数字范围,很容易拆分为独立的小块.例如,范围1~20
         *
         *
         */

        System.out.println("Iterative sum done in:" +
                measureSumPerf(AppTestStream::rangedSum, 10_000_000) + " msecs");

    }

    /**
     * 那到底要怎么利用多核处理器,用流来高效地并行求和呢？我们在第5章中讨论了一个叫
     * LongStream.rangeClosed的方法.这个方法与iterate相比有两个优点.
     * LongStream.rangeClosed直接产生原始类型的long数字,没有装箱拆箱的开销.
     * LongStream.rangeClosed会生成数字范围,很容易拆分为独立的小块.例如,范围1~20
     *
     * @param n
     * @return
     */
    //让我们先看一下它用于顺序流时的性能如何,看看拆箱的开销到底要不要紧
    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);
        /**
         * 这个数值流比前面那个用iterate工厂方法生成数字的顺序执行版本要快得多,因为数值流
         * 避免了非针对性流那些没必要的自动装箱和拆箱操作.由此可见,选择适当的数据结构往往比并
         * 行化算法更重要
         */
    }
    //新版本并行流
    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    /**
     * 并行化执行
     *
     */

    @Test
    public void streamParallel2(){
        System.out.println("Iterative sum done in:" +
                measureSumPerf(AppTestStream::parallelRangedSum, 10_000_000) + " msecs");
        /**
         * 终于,我们得到了一个比顺序执行更快的并行归纳,因为这一次归纳操作可以像图7-1那样
         * 执行了.这也表明,使用正确的数据结构然后使其并行工作能够保证最佳的性能.
         *
         * 尽管如此,请记住,并行化并不是没有代价的.并行化过程本身需要对流做递归划分,把每
         * 个子流的归纳操作分配到不同的线程,然后把这些操作的结果合并成一个值.但在多个内核之间
         * 移动数据的代价也可能比你想的要大,所以很重要的一点是要保证在内核中并行执行工作的时间
         *
         * 比在内核之间传输数据的时间长.总而言之,很多情况下不可能或不方便并行化.然而,在使用
         * 并行Stream加速代码之前,你必须确保用得对；如果结果错了,算得快就毫无意义了.让我们
         * 来看一个常见的陷阱
         */
    }

    /**
     * 错用并行流而产生错误的首要原因,就是使用的算法改变了某些共享状态
     * 下面是另一种实
     * 现对前n个自然数求和的方法,但这会改变一个共享累加器
     */

    public static   long sideEffectSum(long n) {
        /**
         * 这种代码非常普遍,特别是对那些熟悉指令式编程范式的程序员来说.这段代码和你习惯的
         * 那种指令式迭代数字列表的方式很像：初始化一个累加器,一个个遍历列表中的元素,把它们和
         * 累加器相加
         */
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    public  static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    @Test
    public  void testErrorParalleSum(){
        System.out.println("SideEffect parallel sum done in: " + measureSumPerf(AppTestStream::sideEffectParallelSum, 10_000_000L) +" msecs" );
        /**
         * 这回方法的性能无关紧要了，唯一要紧的是每次执行都会返回不同的结果，都离正确值
         * 50000005000000差很远。这是由于多个线程在同时访问累加器，执行total += value，而这
         * 一句虽然看似简单，却不是一个原子操作。问题的根源在于，forEach中调用的方法有副作用，
         * 它会改变多个线程共享的对象的可变状态。
         *
         * 要是你想用并行Stream又不想引发类似的意外，就必须避免这种情况。
         * 现在你知道了，共享可变状态会影响并行流以及并行计算。
         */
    }
    /**
     * 现在，记住要避免共享可变状态，确保并行Stream得到正
     * 确的结果。接下来，我们会看到一些实用建议，你可以由此判断什么时候可以利用并行流来提升
     *
     * 1 如果有疑问，测量。把顺序流转成并行流轻而易举，但却不一定是好事。我们在本节中
     * 已经指出，并行流并不总是比顺序流快。此外，并行流有时候会和你的直觉不一致，所
     * 以在考虑选择顺序流还是并行流时，第一个也是最重要的建议就是用适当的基准来检查
     * 其性能。
     * 2 留意装箱。自动装箱和拆箱操作会大大降低性能。Java 8中有原始类型流（IntStream、
     * LongStream、DoubleStream）来避免这种操作，但凡有可能都应该用这些流
     *
     * 3 有些操作本身在并行流上的性能就比顺序流差。特别是limit和findFirst等依赖于元
     * 素顺序的操作，它们在并行流上执行的代价非常大。例如，findAny会比findFirst性
     * 能好，因为它不一定要按顺序来执行。你总是可以调用unordered方法来把有序流变成
     * 无序流。那么，如果你需要流中的n个元素而不是专门要前n个的话，对无序并行流调用
     * limit可能会比单个有序流（比如数据源是一个List）更高效。
     *
     * 4 对于较小的数据量，选择并行流几乎从来都不是一个好的决定。并行处理少数几个元素
     * 的好处还抵不上并行化造成的额外开销
     *
     */
}
