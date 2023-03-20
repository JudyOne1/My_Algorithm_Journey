package class13;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Judy
 * @create 2023-03-20-15:30
 * 派对的最大快乐值
 * 员工信息的定义如下:
 * class Employee {
 * public int happy; // 这名员工可以带来的快乐值
 * List subordinates; // 这名员工有哪些直接下级
 * }
 * 公司的每个员工都符合 Employee 类的描述。整个公司的人员结构可以看作是一棵标准的、 没有环的多叉树
 * 树的头节点是公司唯一的老板，除老板之外的每个员工都有唯一的直接上级
 * 叶节点是没有任何下属的基层员工(subordinates列表为空)，除基层员工外每个员工都有一个或多个直接下级
 * 这个公司现在要办party，你可以决定哪些员工来，哪些员工不来，规则：
 * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来
 * 2.派对的整体快乐值是所有到场员工快乐值的累加
 * 3.你的目标是让派对的整体快乐值尽量大
 * 给定一棵多叉树的头节点boss，请返回派对的最大快乐值。
 */
public class MyCode04_MaxHappy {

    public static class Employee {
        public int happy;
        public List<Employee> nexts;

        public Employee(int h) {
            happy = h;
            nexts = new ArrayList<>();
        }

    }

    /**
     * a来，a下属不能来，
     * a不来，a下属可来可不来
     */
    public static class Info {
        public int no;
        public int yes;

        public Info(int n, int y) {
            no = n;//不来的最大值
            yes = y;//来的最大值
        }
    }

    public static Info process(Employee cur) {
        if (cur == null) {
            return new Info(0, 0);
        }
        int yes = cur.happy;
        int no = 0;
        for (Employee next : cur.nexts) {
            Info nextInfo = process(next);
            no += Math.max(nextInfo.yes, nextInfo.no);
            yes += nextInfo.no;
        }
        return new Info(no,yes);
    }


}
