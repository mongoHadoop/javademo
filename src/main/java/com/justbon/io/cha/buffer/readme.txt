字符缓冲流BufferedReader和BufferedWriter

字符缓冲流具备文本特有的表现形式，行操作

public class BufferedReader extends Reader

(1)从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。

(2)可以指定缓冲区的大小，或者可使用默认的大小。大多数情况下，默认值就足够大了。

(3)通常，Reader 所作的每个读取请求都会导致对底层字符或字节流进行相应的读取请求。因此，建议用 BufferedReader 包装所有其 read() 操作可能开销很高的 Reader（如 FileReader 和 InputStreamReader）。例如，

 BufferedReader in
   = new BufferedReader(new FileReader("foo.in"));
(4)将缓冲指定文件的输入。如果没有缓冲，则每次调用 read() 或 readLine() 都会导致从文件中读取字节，并将其转换为字符后返回，而这是极其低效的。

public class BufferedWriter extends Writer

(1)将文本写入字符输出流，缓冲各个字符，从而提供单个字符、数组和字符串的高效写入。

(2)可以指定缓冲区的大小，或者接受默认的大小。在大多数情况下，默认值就足够大了。

(3)该类提供了 newLine() 方法，它使用平台自己的行分隔符概念，此概念由系统属性 line.separator 定义。并非所有平台都使用新行符 ('\n') 来终止各行。因此调用此方法来终止每个输出行要优于直接写入新行符。

(4)通常 Writer 将其输出立即发送到底层字符或字节流。除非要求提示输出，否则建议用 BufferedWriter 包装所有其 write() 操作可能开销很高的 Writer（如 FileWriters 和 OutputStreamWriters）。例如，

 PrintWriter out
   = new PrintWriter(new BufferedWriter(new FileWriter("foo.out")));
(5)缓冲 PrintWriter 对文件的输出。如果没有缓冲，则每次调用 print() 方法会导致将字符转换为字节，然后立即写入到文件，而这是极其低效的。



 使用BufferedReader和BufferedWriter完成文件复制

