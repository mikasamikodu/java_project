package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test2 {
	public int[] twoSum(int[] nums, int target) {
		int length = nums.length ;
		int[] result = new int[2];
		for(int i=0; i<length; i++) {
			for(int j=i+1; j<length; j++) {
				if(nums[i]+nums[j]==target) {
					result[0] = i;
					result[1] = j;
					break;
				}
			}
		}
		return result;
	}
	
	
    public int lengthOfLongestSubstring(String s) {     
    	int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
        	char charAt = s.charAt(j);
            if (map.containsKey(charAt)) {
                i = Math.max(map.get(charAt), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(charAt, j + 1);
        }
        return ans;
    }
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        if(len1 == 0)
            return (len2%2==0)?Double.valueOf(nums2[len2/2]+nums2[len2/2-1])/2:nums2[(len2-1)/2];
        if(len2 == 0)
            return (len1%2==0)?Double.valueOf(nums1[len1/2]+nums1[len1/2-1])/2:nums1[(len1-1)/2];
        List<Integer> all = new ArrayList<Integer>();
        int i, j;
        for(i = 0, j = 0;i<len1&&j<len2;){
            if(nums1[i]<nums2[j]){
                all.add(nums1[i++]);
            }else{
                all.add(nums2[j++]);
            }
        }
        while(i<len1){
            all.add(nums1[i++]);
        }
        while(j<len2){
            all.add(nums2[j++]);
        }
        int len = all.size();
        return (len%2==0)?Double.valueOf(all.get(len/2)+all.get(len/2-1))/2:all.get((len-1)/2);
    }
    
    public double findMedianSortedArrays2(int[] A, int[] B) {
        int la = A.length;//数组 A 的长度
        int lb = B.length;//数组 B 的长度

        if (la > lb) {//如果数组 A 比较长，则交换 A、B 数组，确保数组A比数组B短
            int[] temp = A;
            A = B;
            B = temp;

            int tempL = la;
            la = lb;
            lb = tempL;
        }

        int aMin = 0;     //折半查找以A数组为基准时，A数组左边界
        int aMax = la;    //折半查找以A数组为基准时，A数组右边界

        // halfLen 的作用就是中点坐标，当 A 数组中折半查找向右移动时，B 数组以 halfLen 为相对点向左移动，以保持始终均分
        int halfLen = (la + lb + 1) >> 1;
        //二分查找

        //情况一: A 数组为空，中位数在 B 数组
        //情况二: A 数组较短
        //  1) A 数组元素都较小，中位数在B数组
        //  2) A 数组元素都较大，中位数在B数组
        //  3) A、B 元素大小分布基本相当，中位数为被分割的两数组左半部分较大的那一个和右半部分较小的那一个之和的一半
        //情况三: A、B 等长
        //  1) A 数组元素都比B数组元素小，中位数为 A 数组尾元素和B数组首元素之和的一半
        //  2) B 数组元素都比A数组元素小，中位数为 B 数组尾元素和A数组首元素之和的一半
        //  3) A、B 元素大小分布基本相当，中位数为被分割的两数组左半部分较大的那一个和右半部分较小的那一个之和的一半
        while (aMin <= aMax) {
            int aIndex = (aMin + aMax) >> 1;  //A数组中分割点
            int bIndex = halfLen - aIndex;  //B数组中分割点

            //数组 A 分割点相邻左边那个元素比数组 B 分割点相邻右边那个元素大，则应该将数组 A 分割点向右移，数组 B 分割点向左移
            //数组 A 分割点有向左移趋势，需检查左边界
            if (aIndex > aMin && A[aIndex - 1] > B[bIndex]) {
                aMax = aIndex - 1;
                //数组 A 分割点相邻右边那个元素比数组 B 分割点相邻左边那个元素大，则应该将数组 A 分割点向左移，数组 B 分割点向右移
                //数组 A 分割点有向右移趋势，需检查右边界
            } else if (aIndex < aMax && B[bIndex - 1] > A[aIndex]) {
                aMin = aIndex + 1;
                //得出结果
            } else {

                int leftPart = 0;
                //情况一,情况二2，情况三2
                if (aIndex == 0) {//aIndex == 0说明A数组为空
                    leftPart = B[bIndex-1];
                    //情况三1
                } else if (bIndex == 0) {
                    leftPart = A[la - 1];
                    //情况二1,情况二3,情况三3
                } else {
                    leftPart = Math.max(A[aIndex - 1], B[bIndex-1]);
                }

                //元素个数总和为奇数
                if ((la + lb) % 2 == 1) {
                    return leftPart;
                }
                //元素个数总和为偶数
                int rightPart = 0;
                //情况一,情况二1
                if (aIndex == la) {
                    rightPart = B[bIndex];
                    //情况三2
                } else if (bIndex == lb) {
                    rightPart = A[0];
                    //情况二2、3，情况三1、3
                }else {
                    rightPart = Math.min(A[aIndex], B[bIndex]);
                }
                return (leftPart + rightPart) / 2.0;
            }

        }
        return 0;
    }
	

}
