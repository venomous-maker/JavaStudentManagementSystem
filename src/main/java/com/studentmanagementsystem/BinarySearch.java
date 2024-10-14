/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.studentmanagementsystem;
import java.util.*;
/**
 *
 * @author Admin
 */
public class BinarySearch<T extends Comparable<? super T>> {

    public int search(T[] arr, T key) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int cmp = key.compareTo(arr[mid]);

            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}

class TernarySearch<T extends Comparable<? super T>> {

    public int search(T[] arr, T key) {
        return ternarySearch(arr, key, 0, arr.length - 1);
    }

    private int ternarySearch(T[] arr, T key, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid1 = left + (right - left) / 3;
        int mid2 = left + 2 * (right - left) / 3;

        int cmp1 = key.compareTo(arr[mid1]);
        int cmp2 = key.compareTo(arr[mid2]);

        if (cmp1 == 0) {
            return mid1;
        } else if (cmp2 == 0) {
            return mid2;
        } else if (cmp1 < 0) {
            return ternarySearch(arr, key, left, mid1 - 1);
        } else if (cmp2 > 0) {
            return ternarySearch(arr, key, mid2 + 1, right);
        } else {
            return ternarySearch(arr, key, mid1 + 1, mid2 - 1);
        }
    }
}

class BinarySearch_<T> {

    public int search(T[] arr, T key, Comparator<T> comparator) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int cmp = comparator.compare(key, arr[mid]);

            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
