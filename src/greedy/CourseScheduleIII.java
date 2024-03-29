package greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

public class CourseScheduleIII {
    /**
     * @Author JaneRoad
     * @Description 630. 课程表 III
     * @Date 21:27 2021/12/14
     * @Param
     * @param courses
     * @return
     * @return int
     **/
    public int scheduleCourse(int[][] courses) {
        // 以结束时间排序
        Arrays.sort(courses, (c1, c2) -> c1[1] - c2[1]);
        // 储存已选择的课程，按照持续时间排序
        PriorityQueue<int[]> heap = new PriorityQueue<>((c1, c2) -> c2[0] - c1[0]);
        int day = 0;
        for (int[] c : courses) {
            if (day + c[0] <= c[1]) {
                // 如果当前课程时间不冲突，将该课程加入队列
                // 这里的不冲突可以理解为，0~day+c[0]这段区间，我们还可以再插入当前一节课
                day += c[0];
                heap.offer(c);
            } else if (!heap.isEmpty() && heap.peek()[0] > c[0]) {
                // 课程时间冲突，且有选过其他课，这时我们找到最长时间的课程，用当前的短课替换了，余出了更多的空区间
                // 所以这里我们余出的时间其实就是两者的持续时间之差，课程变短了，day会前移，这样我们相当于变相给后面的课程增加了选择的区间
                day -= heap.poll()[0] - c[0];
                heap.offer(c);
            }
        }
        return heap.size();
    }
}
