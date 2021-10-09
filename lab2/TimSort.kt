class TimSort {

    private var min = 32

    fun timSort(arr: IntArray, n: Int) {

        val minRun = minRunLength(min)

        var i = 0
        while (i < n) {
            insertionSort(arr, i, (i + min - 1).coerceAtMost(n - 1))
            i += minRun
        }

        var size = minRun
        while (size < n) {
            // Start point is left sub array. Merging arr[left..left + size - 1] and arr[left + size, left + 2 * size - 1].
            // Increase left by 2 * size after each merge
            var left = 0

            while (left < n) {

                val mid = left + size - 1
                val right = (left + 2 * size - 1).coerceAtMost(n - 1)

                if (mid < right) merge(arr, left, mid, right)
                left += 2 * size
            }
            size *= 2
        }
    }

    private fun minRunLength(n: Int): Int {
        var nn = n
        var r = 0

        while (nn >= min) {
            r = r or (nn and 1)
            nn = nn shr 1
        }
        return nn + r
    }

    private fun insertionSort(arr: IntArray, left: Int, right: Int) {
        for (i in left + 1..right) {
            val temp = arr[i]
            var j = i - 1
            while (j >= left && arr[j] > temp) {
                arr[j + 1] = arr[j]
                j--
            }
            arr[j + 1] = temp
        }
    }

    private fun merge(arr: IntArray, l: Int, m: Int, r: Int) {
        val len1 = m - l + 1
        val len2 = r - m

        val left = IntArray(len1)
        val right = IntArray(len2)

        for (x in 0 until len1) {
            left[x] = arr[l + x]
        }
        for (x in 0 until len2) {
            right[x] = arr[m + 1 + x]
        }

        var i = 0
        var j = 0
        var k = l

        while (i < len1 && j < len2) {
            if (left[i] <= right[j]) {
                arr[k] = left[i]
                i++
            } else {
                arr[k] = right[j]
                j++
            }
            k++
        }

        while (i < len1) {
            arr[k] = left[i]
            k++
            i++
        }

        while (j < len2) {
            arr[k] = right[j]
            k++
            j++
        }
    }
}
