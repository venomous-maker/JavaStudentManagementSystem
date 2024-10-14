/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.studentmanagementsystem;

/**
 *
 * @author Admin
 */

import java.util.*;

public class TimSort<T extends Comparable<? super T>> {

    private static final int MIN_MERGE = 32;
    private final T[] arr;

    public TimSort(T[] arr) {
        this.arr = arr;
    }

    public void sort(boolean ascending) {
        int n = arr.length;
        for (int i = 0; i < n; i += MIN_MERGE) {
            insertionSort(i, Math.min(i + MIN_MERGE - 1, n - 1), ascending);
        }

        for (int size = MIN_MERGE; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));
                merge(left, mid, right, ascending);
            }
        }
    }

    private void merge(int l, int m, int r, boolean ascending) {
        int len1 = m - l + 1;
        int len2 = r - m;

        T[] leftArr = Arrays.copyOfRange(arr, l, m + 1);
        T[] rightArr = Arrays.copyOfRange(arr, m + 1, r + 1);

        int i = 0, j = 0, k = l;

        while (i < len1 && j < len2) {
            if ((ascending && leftArr[i].compareTo(rightArr[j]) <= 0) || (!ascending && leftArr[i].compareTo(rightArr[j]) >= 0)) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        while (i < len1) {
            arr[k++] = leftArr[i++];
        }

        while (j < len2) {
            arr[k++] = rightArr[j++];
        }
    }

    private void insertionSort(int l, int r, boolean ascending) {
        for (int i = l + 1; i <= r; i++) {
            T key = arr[i];
            int j = i - 1;
            while (j >= l && ((ascending && arr[j].compareTo(key) > 0) || (!ascending && arr[j].compareTo(key) < 0))) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}

class TimSort_<T> {

    private static final int MIN_MERGE = 32;
    private final T[] arr;
    private final Comparator<T> comparator;

    public TimSort_(T[] arr, Comparator<T> comparator) {
        this.arr = arr;
        this.comparator = comparator;
    }

    public void sort() {
        int n = arr.length;
        for (int i = 0; i < n; i += MIN_MERGE) {
            insertionSort(i, Math.min(i + MIN_MERGE - 1, n - 1));
        }

        for (int size = MIN_MERGE; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));
                merge(left, mid, right);
            }
        }
    }

    private void merge(int l, int m, int r) {
        int len1 = m - l + 1;
        int len2 = r - m;

        T[] leftArr = Arrays.copyOfRange(arr, l, m + 1);
        T[] rightArr = Arrays.copyOfRange(arr, m + 1, r + 1);

        int i = 0, j = 0, k = l;

        while (i < len1 && j < len2) {
            if (comparator.compare(leftArr[i], rightArr[j]) <= 0) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        while (i < len1) {
            arr[k++] = leftArr[i++];
        }

        while (j < len2) {
            arr[k++] = rightArr[j++];
        }
    }

    private void insertionSort(int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            T key = arr[i];
            int j = i - 1;
            while (j >= l && comparator.compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
