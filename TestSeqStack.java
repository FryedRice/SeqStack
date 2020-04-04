/**
* @ Description: Java实现变长数组栈数据结构SeqStack<Item>
* @ Date: Feb.19th 2020
* @ Author: Jay Sonic
*/
import java.util.Iterator;

public class TestSeqStack {
    public static void main(String[] args) {
        SeqStack<Integer> test = new SeqStack<Integer>();

        System.out.printf("********入栈测试开始.********\n");
        for(int i=1; i<=21; ++i){
            test.push(i);
            System.out.printf("当前栈容量: %d.\t",test.capacity());
            System.out.printf("当前栈元素个数: %d.\n",test.size());
        }
        System.out.printf("********入栈测试结束.********\n");
        
        
        System.out.printf("********迭代器测试开始********.\n");
        Iterator<Integer> it = test.iterator();
        while(it.hasNext()){
            System.out.printf("%d  ",it.next());
        }System.out.println("");
        System.out.printf("********迭代器测试结束********.\n");
        
        
        System.out.printf("********出栈测试开始.********\n");
        int popedItem;
        for(int i=1; i<20; ++i){
            popedItem = test.pop();
            System.out.printf("元素%3d已出栈.\t",popedItem);
            System.out.printf("当前容量:%2d.\t",test.capacity());
            System.out.printf("剩余元素个数:%2d.\n",test.size());
        }
        System.out.printf("********出栈测试结束.********\n");
    }
}

class SeqStack<Item> implements Iterable<Item>{
    private Item[] a = (Item[]) new Object[1]; //注意这里的强制类型转换
    private int N = 0;
    
    public boolean isEmpty(){
        return N == 0;
    }
    public int size(){
        return N;
    }
    public int capacity(){
        return a.length;
    }
    private void reSize(int max){ //注意这里的private
        //调整数组的大小
        Item[] temp = (Item[]) new Object[max];
        for(int i=0; i<N; i++) temp[i] = a[i];
        a = temp;
    }
    public void push(Item val){ //入栈
        if(N == a.length) reSize(2*a.length); //栈满时，先翻倍扩容
        a[N++] = val;
    }
    public Item pop(){
        Item outcome = a[--N];
        a[N] = null; //为了利于垃圾对象回收
        if(N>0 && N==a.length/4) reSize(a.length/2);
        return outcome;
    }
    
    public Iterator<Item> iterator(){//实现Iterable接口
        return new ReverseArrayIterator();
    }
    private class ReverseArrayIterator implements Iterator<Item>{
        //Iterator接口三件套 hasNext(),next(),remove()
        private int i = N;
        public boolean hasNext(){
            return i>0;
        }
        public Item next(){
            return a[--i];
        }
        public void remove(){}
    }
}