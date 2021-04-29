package com.snoics.algorithms.recursive;

/**
 * 假设盘子上有n块面积大小不一的烧饼，你如何用一把锅铲进行若干次翻转，让这些烧饼的大小有序（小的在上，大的在下）？
 */
public class SortCookie {
    /**
     * 初始化的无序烧饼
     */
    private int[] cookies;
    /**
     * 最多的翻动次数
     */
    private int times=0;
    
    public SortCookie(int[] cookies){
        this.cookies = cookies;
        System.out.print("现在有一堆烧饼需要通过翻动做排序:");
        System.out.println(printCookies(cookies));
        System.out.println("--------------------------------------------------");
    }

    /**
     * 递归的方式对烧饼排序
     * @param n
     */
    public void sort(int n){
        //只剩下最上面一块的时候不需要再翻动
        if(n==0){
            System.out.println("--------------------------------------------------");
            System.out.print("烧饼共翻动"+times+"次，排序结束：");
            System.out.println(printCookies(cookies));
            return;
        }
        //翻动
        reverse(n);
        System.out.print("烧饼翻动第"+times+"次结果：");
        System.out.println(printCookies(cookies));

        //递归做下一次翻动
        sort(n-1);
    }

    /**
     * 每一组的翻动烧饼，每次翻动都保证未排序的最大烧饼被放置在已排序后的烧饼堆的最上面
     * @param cookieIndex 当前未排序烧饼数组的索引最大值
     */
    private void reverse(int cookieIndex){
        //当前未排序的烧饼中的最大值
        int max=Integer.MIN_VALUE;
        //当前未排序的烧饼中最大值的数组索引值
        int maxIndex=0;

        //循环获取当前未排序的烧饼中的最大值以及对应的索引
        for(int i=0;i<=cookieIndex;i++){
            if(max<=cookies[i]){
                max=cookies[i];
                maxIndex=i;
            }
        }
        
        /****************************************************************
         * 先做这一组翻动中的第一次翻动，把最大值的烧饼放到数组的第一位
         * 
         ***************************************************************/
        //当前未排序的烧饼中从第一位到最大一位的烧饼数量
        int length=maxIndex+1;
        //计算本次翻转过程中需要对换的次数
        int reverseTimes=length/2;
        //开始对换烧饼位置
        for(int i=0;i<reverseTimes;i++){
            int start=cookies[i];
            int end=cookies[maxIndex-i];
            cookies[i]=end;
            cookies[maxIndex-i]=start;
        }
        
        /****************************************************************
         * 再做这一组翻动中的第二次翻动，把最大值的烧饼放到已排序的烧饼的最上面
         * 
         ***************************************************************/
        //本次所有未排序的烧饼数量
        length=cookieIndex+1;
        //计算本次翻转过程中需要对换的次数
        reverseTimes=length/2;
        //开始对换烧饼位置
        for(int i=0;i<reverseTimes;i++){
            int start=cookies[i];
            int end=cookies[cookieIndex-i];
            cookies[i]=end;
            cookies[cookieIndex-i]=start;
        }

        //递增翻动次数
        times++;
    }

    private String printCookies(int[] cookies){
        StringBuilder s=new StringBuilder();
        s.append("[");
        int index=0;
        for(int i : cookies){
            s.append(i);
            if(index<cookies.length-1){
                s.append(",");
            }
            index++;
        }
        s.append("]");
        return s.toString();
    }

    public static void main(String[] args){
        int[] cookies=new int[]{234,545,66,123,444,66,89,95,2,4,5,13,-130};
        SortCookie sortCookie=new SortCookie(cookies);
        sortCookie.sort(cookies.length-1);
    }
}
